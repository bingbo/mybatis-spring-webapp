# mybatis-spring-webapp

mybatis-spring-webapp

* mvn eclipse:eclipse 转变为eclipse工程，方便导入eclipse
* java build path里添加runtime server tomcat类库
* project Facets里转变为dynamic web module，即转换为web工程
* deployment assembly里添加相应的source及deploy path，以便找到并加载web.xml配置文件，加载相应的spring配置


## 多数据源，master和slave读写分离

1. 通过继承AbstractRoutingDataSource实现数据源的选取

    ```java
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = null;
        if (DataSourceHolder.isMaster()) {
            dataSource = master;
        } else if (DataSourceHolder.isSlave()) {
            // 简单轮循
            int count = counter.getAndIncrement();
            if (count > 100000) {
                counter.set(0);
            }
            int index = count % slaves.size();
            dataSource = slaves.get(index);
        } else {
            dataSource = master;
        }
        return dataSource;
    }
    ```
    
1. 由该线程DataSourceHolder保存要使用的数据源

    ```java
    public class DataSourceHolder {
        private static final String MASTER = "master";
        private static final String SLAVE = "slave";

        private static final ThreadLocal<String> dataSource = new ThreadLocal<>();
        private static final ThreadLocal<DataSource> masterLocal = new ThreadLocal<>();
        private static final ThreadLocal<DataSource> slaveLocal = new ThreadLocal<>();

        private static void setDataSource(String dataSourceKey) {
            dataSource.set(dataSourceKey);
        }

        private static String getDataSource() {
            return dataSource.get();
        }

        public static boolean isMaster() {
            return getDataSource() == MASTER;
        }

        public static boolean isSlave() {
            return getDataSource() == SLAVE;
        }

        public static void setSlave(DataSource dataSource) {
            slaveLocal.set(dataSource);
        }

        public static void setMaster(DataSource dataSource) {
            masterLocal.set(dataSource);
        }

        public static void setMaster() {
            setDataSource(MASTER);
        }

        public static void setSlave() {
            setDataSource(SLAVE);
        }

        public static void clearDataSource() {
            dataSource.remove();
            masterLocal.remove();
            slaveLocal.remove();
        }

    }
    ```

1. 通过事务或AOP来确定使用的数据源

> 具体可参考`com.ibingbo.support.multi`包中多数据源方案

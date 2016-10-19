# mybatis-spring-webapp
mybatis-spring-webapp

* mvn eclipse:eclipse 转变为eclipse工程，方便导入eclipse
* java build path里添加runtime server tomcat类库
* project Facets里转变为dynamic web module，即转换为web工程
* deployment assembly里添加相应的source及deploy path，以便找到并加载web.xml配置文件，加载相应的spring配置

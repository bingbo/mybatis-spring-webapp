package com.ibingbo.support.jdbc.po;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ibingbo.support.jdbc.annotation.DBColumn;
import com.ibingbo.support.jdbc.annotation.DataSource;
import com.ibingbo.support.jdbc.annotation.Table;

/**
 * POClassInfo
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class POClassInfo<T extends AbstractPO> {
    private static final Logger LOG = LoggerFactory.getLogger(POClassInfo.class);

    private final Class<T> poClass;
    private String dataSourceName;
    private String tableName;
    private String basicColumnStr;
    private final List<String> primaryKeys = new ArrayList<>();
    private final Map<String, String> field2ColumnMap = new HashMap<>();
    private final Map<String, String> column2FieldMap = new HashMap<>();
    private final Set<String> canNullFields = new HashSet<>();
    private final Map<String, Field> fieldName2FieldMap = new HashMap<>();

    public POClassInfo(Class<T> poClass) {
        this.poClass = poClass;
        this.initTableInfo();

        this.initFieldInfo();
    }

    public Class<T> getPoClass() {
        return poClass;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public Map<String, String> getField2ColumnMap() {
        return field2ColumnMap;
    }

    public Map<String, String> getColumn2FieldMap() {
        return column2FieldMap;
    }

    public Set<String> getCanNullFields() {
        return canNullFields;
    }

    public String getBasicColumnStr() {
        return basicColumnStr;
    }

    public Map<String, Field> getFieldName2FieldMap() {
        return fieldName2FieldMap;
    }

    private void initFieldInfo() {
        Field[] fields = this.poClass.getDeclaredFields();
        if (fields != null) {
            StringBuffer columns = new StringBuffer();
            for (Field field : fields) {
                if (Modifier.PRIVATE == field.getModifiers()) {
                    String fieldName = field.getName();
                    DBColumn dbColumn = field.getAnnotation(DBColumn.class);
                    String columnName = this.dbColumnName(fieldName, dbColumn);
                    this.field2ColumnMap.put(fieldName, columnName);
                    this.column2FieldMap.put(columnName, fieldName);

                    field.setAccessible(true);
                    this.fieldName2FieldMap.put(fieldName, field);

                    if (dbColumn != null) {
                        if (dbColumn.isPrimaryKey()) {
                            this.primaryKeys.add(columnName);
                        }
                        if (dbColumn.isNull()) {
                            this.canNullFields.add(fieldName);
                        }
                    }

                    columns.append("`").append(columnName).append("`,");
                }
            }
            this.basicColumnStr = columns.substring(0, columns.length() - 1);
        }
    }

    private void initTableInfo() {
        DataSource dataSource = this.poClass.getAnnotation(DataSource.class);
        Assert.notNull(dataSource, "dataSource of po class can not be null");
        Table table = this.poClass.getAnnotation(Table.class);
        Assert.notNull(table, "table of po class can not be null");
        this.tableName = table.value();
    }

    private String dbColumnName(String fieldName, DBColumn column) {
        if (column == null || column.value().length() == 0) {
            return fieldName.toLowerCase();
        }
        return column.value();
    }
}

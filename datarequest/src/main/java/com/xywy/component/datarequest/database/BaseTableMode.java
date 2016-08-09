package com.xywy.component.datarequest.database;

/**
 * Created by shijiazi on 15/12/26.
 */
public abstract class BaseTableMode {

    /**
     * 创建表语句
     */
    public abstract String createTableSql();

    /**
     * 删除表语句
     */
    public abstract String deleteTableSql();

    /**
     * @return 所有字段
     */
    public abstract String[] getFieldAll();

    /**
     * @return 表名
     */
    public abstract String getTableName();

}

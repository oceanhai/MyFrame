package step.model;

/**
 * Created by bobby on 15/11/30.
 */
public abstract class DateBaseModelBase {

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

package step.model;

/**
 * Created by yuwentao on 16/5/23.
 */
public class StepModel extends DateBaseModelBase {

    public static final String table_name = "step_info";
    public static final String userid = "userid";
    public static final String date = "date";
    public static final String isUpload = "isUpload";//是否已经上传了服务 1 已经上传   0 没有上传
    public static final String stepCount = "step_count";
    public static final String timestamp = "timestamp";
    public static final String isUploadPoints = "isUploadPoints";//是否上传了积分 1 已经上传 0 没有上传


    @Override
    public String createTableSql() {
        String USER_MESSAGE_CREATE = "CREATE TABLE " + table_name + " ( "
                + userid + " varchar(20),"
                + date + " varchar(20) ,"
                + isUpload + " INTEGER ,"
                + stepCount + " INTEGER ,"
                + isUploadPoints + " INTEGER "
                + ") ";

        return USER_MESSAGE_CREATE;
    }

    @Override
    public String deleteTableSql() {
        String str_sql = "drop table if exists " + table_name;
        return str_sql;
    }

    @Override
    public String[] getFieldAll() {
        return new String[0];
    }

    @Override
    public String getTableName() {
        return table_name;
    }
}

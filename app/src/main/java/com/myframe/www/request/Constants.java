package com.myframe.www.request;

/**
 * Created by aidonglei on 2015/11/13.
 */
public class Constants {
    public static final String MAP_KEY_ERROR = "error";
    public static final String MAP_KEY_RIGHT = "right";

    public static final String app_index_kscxs = "app_index_kscxs";
    public static final String app_index_rqcxs = "app_index_rqcxs";
    public static final String app_index_bwcxs = "app_index_bwcxs";
    public static final String app_index_mod = "app_index_mod0";
    public static final String app_index_query = "app_index_query";
    public static final String app_index_docter = "app_index_docter";
    public static final String app_index_ask = "app_index_ask";
    public static final String app_index_drug = "app_index_drug";
    public static final String app_index_my = "app_index_my";
    public static final String app_so = "app_so";
    public static final String app_doctm_kscxm = "app_doctm_kscxm";
    public static final String app_doctm_yycxm = "app_doctm_yycxm";
    public static final String app_doctm_more = "app_doctm_more";
    public static final String app_doctm_doctcard = "app_doctm_doctcard";

    public static final String SIGN = "sign";

    /**
     * 以下为请求数据的标识
     */
    public static final String FamousData = "FamousData";
    public static final String QUERY = "QUERY";
    public static final String UserInfo = "UserInfo";

    /**
     * Login ApiParams的key
     */
    public static final String UserName = "account";
    public static final String PassWord = "passwd";
    public static final String os_key = "os";
    public static final String pro_key = "pro";
    public static final String api_key = "api";
    public static final String source_key = "source";
    public static final String version_key = "version";
    /**
     * Login ApiParams的value
     */
    public static final String os_value = "android";
    public static final String pro_value = "xywyf32l24WmcqquqqTdhXaEng";
    public static final String api_value = "785";
    public static final String source_value = "xywyapp";
    public static final String source_other_value = "xywy_app";

    /**
     * 缓存标识
     */
    public static final String CACHE_QUERY = "/CACHE_QUERY";

    /**
     * request_code
     */
    public final static int FILECHOOSER_REQUESTCODE = 100011;

    /**
     * 验证码接口
     */
    public final static String api_code_value = "784";

    /**
     * 绑定手机
     */
    public final static String api_bind_value = "787";

    public final static String project_key = "project";
    public final static String act_key = "act";
    public final static String phone_key = "phone";
    public final static String code_key = "code";
    public final static String userid_key = "userid";
    public final static String account_key = "account";
    public final static String check_msg_key = "check_msg";

    public final static String act_bind_value = "phoneBind";
    public final static String project_value = "APP_XYWYCJAPP";
    public final static String act_value = "userSMS_send";

    /**
     * check_msg
     */
    public final static String uid_key = "uid";
    public final static String username_key = "username";

    /**
     * 注册相关key
     */
    public final static String devid_key = "devid";
    public final static String passwd_key = "passwd";
    public final static String nickname_key = "nickname";
    public final static String usersource_key = "usersource";
    public final static String usign_key = "usign";
    public final static String re_project_key = "project";
    public final static String re_code_key = "code";
    public final static String re_phone_key = "phone";

    /**
     * 注册相关vaule
     */
    public final static String devid_vaule = "";
    public final static String passwd_vaule = "passwd";
    public final static String nickname_vaule = "";
    public final static String usersource_vaule = "app_xywycjapp";
    public final static String usign_vaule = "c2d3ede11a284b2ed2596327ce89284f";
    public final static String re_project_vaule = "APP_XYWYCJAPP";
    public final static String re_code_vaule = "code";
    public final static String re_phone_vaule = "phone";

    /**
     * 提交注册信息 ApiParams的value
     */
    public static final String re_api_value = "786";

    /**
     * 修改密码
     */

    public static final String newpwd_key = "newpwd";

    /**
     * 图文咨询
     */
//    public final static String QUESTION_SUBMIT_API_VALUE = "809";//提交
    public final static String QUESTION_SUBMIT_API_VALUE = "916";//提交
    public final static String POST_PARAM_AGETYPE = "agetype";
    public static final String POST_PARAM_LATITUDE = "latitude";
    public static final String POST_PARAM_LONGITUDE = "longitude";
    public static final String POST_PARAM_MOBILEPHONE = "mobilephone";
    public static final String POST_PARAM_TOWN = "town";
    public static final String POST_PARAM_CITY = "city";
    public static final String POST_PARAM_PROVINCE = "province";
    public final static String POST_PARAM_IMAGE = "imgfile";
    public final static String POST_PARAM_AGE = "age";
    public final static String POST_PARAM_SEX = "sex";
    public final static String POST_PARAM_SUBJECT_PID = "subject_pid";
    public final static String POST_PARAM_SUBJECT = "subject";
    public static final String POST_PARAM_DID = "did";
    public static final String POST_PARAM_CON = "con";
    public static final String POST_PARAM_ANONYMOUS = "anonymous";
    public static final String POST_PARAM_UID = "uid";
    public static final String POST_PARAM_IMEI = "imei";
    public static final String POST_PARAM_DEVICEID = "deviceid";
    public final static String QUESTION_DOCTOR_INFO_API_VALUE = "813";//获取医生信息
    public static final String QUESTION_DOCTOR_INFO_DICID = "docid";
    public static final String INTENT_PARAM_CON = "con";//症状描述
    public static final String INTENT_PARAM_SEX = "sex";
    public static final String INTENT_PARAM_AGE = "age";
    public static final String SP_QUESTION_SEX = "question_sex";
    public static final String SP_QUESTION_AGE = "question_age";
    public static final String SP_QUESTION_AGE_TYPE = "question_age_type";
    public static final String SP_QUESTION_PHONE = "question_phone";

    /**
     * 我的提问
     */
//    public final static String MY_QUESTION_API_VALUE = "799";
    public final static String MY_QUESTION_API_VALUE = "917";
    public final static String MY_QUESTION_PAGESIZE_KEY = "pagesize";
    public final static String MY_QUESTION_PAGE_KEY = "page";
    public final static String MY_QUESTION_UID_KEY = "uid";
    public static final String INTENT_PARAM_QID = "qid";//问题ID

    /**
     * 问题详情
     */

    public final static String QUESTION_DETAIL_API_VALUE = "924";
    public final static String QUESTION_DETAIL_TYPE_DOCTOR = "doctor";
    public final static String QUESTION_DETAIL_TYPE_USER = "user";
    public final static String QUESTION_DETAIL_QID_KEY = "qid";
    public final static String QUESTION_DETAIL_UID_KEY = "uid";
    public final static String INTENT_PARAM_REPLY_DATA = "reply";
    public final static String INTENT_PARAM_FROM = "from";

    /**
     * 推荐问题详情
     */
    public final static String RECOMMEND_QUESTION_DETAIL_CHANGE_API_VALUE = "926";//修改clbu问题状态为兼职医生区
    public final static String RECOMMEND_QUESTION_DETAIL_SIMILAR_API_VALUE = "935";//推荐问题  相似病例列表
    public final static String RECOMMEND_QUESTION_DETAIL_API_VALUE = "814";
    public final static String RECOMMEND_QUESTION_DETAIL_QID_KEY = "qid";//问题id
    public final static String RECOMMEND_QUESTION_DETAIL_KID_KEY = "kid";//科室id
    public static final String INTENT_PARAM_KID = "kid";//问题ID

    /**
     * 通用补充问题
     */
    public final static String INTENT_PARAM_IS_DIAGNOSE = "isDiagnose";
    public final static String INTENT_PARAM_WENTI = "wenti";

    /**
     * 预约加号 ApiParams的key
     */
    public static final String booking_key_doctor_id = "doctor_id";
    /**
     * 预约加号 ApiParams的Postkey
     */
    public static final String booking_c_uid = "c_uid";
    public static final String booking_uname = "uname";
    public static final String booking_pdid = "pdid";
    public static final String booking_expert_id = "expert_id";
    public static final String booking_date = "date";
    public static final String booking_ill = "ill";
    public static final String booking_lastresult = "lastresult";
    public static final String booking_pnum = "pnum";
    public static final String booking_objective = "objective";
    public static final String booking_img = "img";
    public static final String booking_is_new_user = "is_new_user";
    public static final String booking_name = "name";
    public static final String booking_mphone = "mphone";
    public static final String booking_province = "province";
    public static final String booking_city = "city";
    public static final String booking_is_client = "is_client";

    public static final String booking_empty = "";
    public static final String booking = "booking";
    public static final String IS_TIME = "TIME";
    /**
     * 预约加号API
     */
    public final static String api_booking_value = "806";
    public final static String api_commit_value = "811";
    public final static String api_booking_list_value="807";

    public final static String version_value1 = "1.1";
    public final static String version_value2 = "1.2";
    public final static String version_value3 = "1.3";
    public final static String version_value = "1.0";

    public final static String version_usersource = "app_xywycjapp";
    public final static String key_usersource = "usersource";
    public final static String account_sina = "sina";
    public final static String account_qq = "qq";

    public static final String have_remark = "have_remark";
    public static final String status = "status";
    public static final String pagesize = "pagesize";
    public static final String page = "page";
    public static final String app = "app";
    public static final String have_remark_vaule = "";
    public static final String status_vaule = "0";
    public static final String pagesize_vaule = "10000";
    public static final String page_vaule = "1";
    public static final String list_pagesize ="pagesize";
    public static final String list_pagenum="pagenum";
    public static final String list_state="state";
    public static final String family_api="881";
    public static final String plus_detail_api="808";
    public static final String get_plus_detail_api="821";
    public static final String cancel_plus_detail_api="823";
    public static final String user_id_key="user_id";
    public static final String plus_id_key_="plus_id";

    //IM评论
    public static final String reason_key="reason";
    public static final String message_key="message";
    public static final String score_key="score";
    public static final String bid_key="bid";
    public static final String qaid_key="qaid";
    public static final String doctid_key="docid";
    public static final String doctid_im_key="doctid";
    public static final String im_userid_key="userid";
    //医生评论
    public static final String gcons_key="gcons";
    public static final String grade_key="grade";
    public static final String ev_did_key="did";
    public static final String ev_rid_key="rid";
    public static final String ev_qid_key="qid";
    public static final String ev_uid_key="uid";
    public static final String im_type_key="type";
    public static final String IM_DOCID="IM_DOCID";
    public static final String IM_QUESID="IM_QUESID";

    /** QQ*/

    public static final String QQ_APP_KEY =  "1103859281";


    /** WEIBO */
    public static final String WB_APP_KEY = "2422455644";

    /**
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     *
     * <p>
     * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响，
     * 但是没有定义将无法使用 SDK 认证登录。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * </p>
     */
    public static final String WB_REDIRECT_URL
            = "https://api.weibo.com/oauth2/default.html";

    /**
     * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
     * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
     * 选择赋予应用的功能。
     *
     * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
     * 使用权限，高级权限需要进行申请。
     *
     * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
     *
     * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
     * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write," +
                    "friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
                    "follow_app_official_microblog," + "invitation_write";


    public final static String LoginModelFileName = "/userinfo";
}

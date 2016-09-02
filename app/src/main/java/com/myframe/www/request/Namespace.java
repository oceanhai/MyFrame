package com.myframe.www.request;

/**
 * Created by wangjianhua on 15/12/1.
 */
public class Namespace {
//    public static final String Login = "user/userlogin/index?";
    public static final String Login = "xywyapp/userlogin/index?";
    public static final String Query = "zonghe/icon/?sign=%s&time=%s";
    public static final String Query_New = "zonghe/icon/?";//TODO 底层封装数据请求用 逻辑替换后Query 字段可删除
    public static final String Family_Doctor = "xywyapp/familydoctor/index?";
    public static final String Famous_Doctor="xywyapp/famousdoctors/index?";
    public static final String CODE = "user/userSmsPhoneCode/index?";
    public static final String Bind = "user/userSmsBindPhone/index?";
    public static final String Check_msg = "user/userCheckMsg/index?";
    public static final String Register = "xywyapp/userSmsReg/index?";
    public static final String Qusetion ="club/askNew/index?";//图文咨询 提问
    public static final String Doctor_Info ="club/docDetail/index?";//图文咨询 获取医生信息
    public static final String Change_password ="user/userPhonePwd/index?";
    public static final String Booking_doctor="zhuanjia/zhuanjiaDoctorDetail/index?";
    public static final String My_Question="xywyapp/myQues/index?";//我的提问
    public static final String Recommend_Question_Detail="club/quesDetail/index?";//相似病历 问题详情
    public static final String Question_Detail="xywyapp/quesDetail/index?";//我的提问 问题详情
    public static final String Question_Detail1="club/quesDetail/index?";//咨询详情 v4.1.1
    public static final String zhuiwenNew="club/zhuiwenNew/index?";//884 . 问题追问 v 1.1
    public static final String zhuiwenImg="club/zhuiwenImg/index?";//885 . 问题追问添加图片 v 1.1
    public static final String quesEditStatus="xywyapp/quesEditStatus/index?";//修改clbu问题状态为兼职医生区
    public static final String releQues="xywyapp/releQues/index?";//935 . 相关推荐问题
    public static final String MY_Family_doctor="jtys/userOrders/index?";
    public static final String Booking_Commit="zhuanjia/zhuanjiaPlusSubmit/index?";
    public static final String Booking_Commit_New="zhuanjia/zhuanjiaNewPlusSubmit/index?";
    public static final String Booking_Commit_Vip_Verify="zhuanjia/zhuanjiaVipVerify/index?";
    public static final String Submit_Question_More_Info="club/supply/index?";
    public static final String Userthird="xywyapp/userthird/index?";
    public static final String Booking_list="zhuanjia/zhuanjiaPlus/index?";
    public static final String fastBooking_list="zhuanjia/zhuanjiaPlusNew/index?";
    public static final String Booking_Detail="zhuanjia/zhuanjiaPlusDetail/index?";
    public static final String Booking_Get_Plus="zhuanjia/zhuanjiaGetPlus/index?";
    public static final String Booking_Cancel="zhuanjia/zhuanjiaCancelPlus/index?";
    public static final String IM_EvaluateCommit="xywyapp/imComment/index?";
    public static final String EvaluateCommit="xywyapp/docEvaluation/index?";
    public static final String JZDocInfo="xywyapp/quesDetail/index?";
    public static final String IMDocInfo="xywyapp/docterInfo/index?";
    public static final String CheckVersions="CheckVersions/index?";

    public static final String HomeData="xywyapp/IndexNewRqueImgs/index?";
    public static final String supply="xywyapp/supply/index?";
    public static final String myProdList="xywyapp/myProdList/index?";
    public static final String communityReplyDetail="quanzi/replyc_detail/index?";

    /**
     * 社区
     */
    public static final String quanziDetail="quanzi/Detail/index?";//1005 . 原生社区-圈子详情 v 1.1
    public static final String quanziTopiclist="quanzi/ThreadHuati/index?";//1007 . 原生社区-某圈子话题列表 v 1.1
    public static final String quanziAttention="quanzi/Attention/index?";//1000 . 原生社区-关注/取消关注 v 1.1
    public static final String publishTopic="quanzi/ThreadAdd/index?";//1009 . 原生社区-发布话题 v 1.1
    /** 话题详情
     *
     */
    public static final String topicDetailInfo="quanzi/thread_detail/index?";
    /**
     * 回复列表
     */
    public static final String topicReplyList="quanzi/replyc_list/index?";
    /**
     * 回复楼主
     */
    public static final String replyadd = "quanzi/replyc_add/index?";
    /**
     * 删除话题 回复 评论
     */
    public static final String delete_topic = "quanzi/replyc_del/index?";
    /**
     * 上传图片
     */
    public static final String updateImage="quanzi/uploadImg/index?";
    /**
     * 圈和广场
     */
    public static final String circleTab ="quanzi/MyCircle/index?";
    public static final String attention ="quanzi/Attention/index?";
    public static final String getMyReceivedMsg ="quanzi/get_msg_list/index?";
    public static final String getUnreadMsgCount ="/quanzi/MessageCount/index?";
    public static final String getPublishMsg ="/quanzi/SendMsgList/index?";
    public static final String squareTab ="quanzi/Square/index?";
    public static final String publishComment ="quanzi/commentAdd/index?";
    public static final String topicLike ="quanzi/likes/index?";

    /**
     * 微信支付
     */
    public static final String WxPay ="pay/WechatPay/index?";

    /** 4.1.7 首页*/
    public static final String homepageData = "xywyapp/index_home_2/index";

    /** 4.1.8 问答卡*/
    public static final String cardaskData = "xywyapp/index_card_ask/index";

    /**
     * 下导航栏 对应的tab
     */
    public static final String consultation ="xywyapp/consultation/index?";

    /**
     * 去医院界面
     */
    public static final String GOTO_HOSPITAL ="xywyapp/gohospital/index";

    public static final String SETMSGSTATUS = "quanzi/SetMsgStatus/index?";

    /**
     * V4.1.6 start广告
     */
    public static final String START_AD = "xywyapp/startAd/index?";

    /**
     * v4.1.7
     */
    public static final String ZHUAN_JIA_PATIENT_LIST ="zhuanjia/zhuanjiaPatientList/index?";
    public static final String ZHUAN_JIA_PATIENT_ADD ="zhuanjia/zhuanjiaPatientAdd/index?";
    public static final String ZHUAN_JIA_PATIENT_EDIT ="zhuanjia/zhuanjiaPatientEdit/index?";
    public static final String ZHUAN_JIA_PATIENT_DEL ="zhuanjia/zhuanjiaPatientDel/index?";
    public static final String ZHUAN_JIA_NEW_USER_NPLUS_LIST ="zhuanjianew/zhuanjianewUserNplusList/index?";
    public static final String ZHUAN_JIA_NEW_USER_PLUS_LIST ="zhuanjianew/zhuanjianewUserPlusList/index?";
    public static final String RECORD_USER_STEP_NUM ="xywyapp/recordUserStepnum/index?";

    public static final String search_medicine="yao/yao_search/index?";
    public static final String search_eatMedicine="user/userAddressList/index?";
    public static final String add_eatMedicine="/user/userAddressAdd/index?";

    /**
     * 修改用户信息
     */
    public static final String update_userinfo="/user/userInfoEdit/index?";

    /**
     * 上传用户图像
     */
    public static final String upload_image="/common/uploadImg/index?";

}

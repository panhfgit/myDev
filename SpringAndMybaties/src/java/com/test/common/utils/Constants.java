package com.test.common.utils;

/**
 * Created by dizl on 2015/5/8.
 */
public class Constants {
    public class CrudDaoSql {
        public static final String CRUD_FLAG = "$$GeneralCrud$$";
        public static final String FIND_BY_ID = "findById";//根据主键查询
        public static final String FIND_LIKE = "findLike";//根据实体类模糊查询
        public static final String FIND_LIKE_COUNT = "findLikeCount";//获取查询数量
        public static final String FIND_BY_ENTITY = "findByEntity";//根据实体查询
        public static final String FIND_BY_ENTITY_COUNT = "findByEntityCount";//获取查询数量
        public static final String FIND_NOT_THIS_ENTITY = "findNotThisEntity";//查询不是该实体的数据
        public static final String FIND_NOT_THIS_ENTITY_COUNT = "findNotThisEntityCount";//获取查询数量
        public static final String FIND_NOT_LINK_ENTITY = "findNotLikeEntity";//查询和该实体不相同的数据
        public static final String FIND_NOT_LINK_ENTITY_COUNT = "findNotLikeEntityCount";//获取查询数量
        public static final String SAVE = "save";//插入数据
        public static final String UPDATE_BY_ID = "updateById";//根据主键更新数据
        public static final String DELETE_BY_ID = "deleteById";//根据主键删除数据
        public static final String DELETE_BY_ENTITY = "deleteByEntity";//根据实体类删除数据
        public static final String INSERT_UPB_HIS = "insertUpbHis";//将数据写入到历史表中
        public static final String INSERT_DEL_HIS = "insertDelHis";//将数据写入到竣工表中
        public static final String GET_SYSDATE = "getSysDate";//获取数据库当前时间
        public static final String FIND_BY_SQL = "findBySql";//根据sql查询返回

        public static final String KEY_DAO_CLASS = "@DaoClass";
        public static final String KEY_PARAMS = "@Param";
        //edit by 肖克 sql查询增加静态变量 2016年1月22日15:30:21
        public static final String KEY_PARAM = "param";
        public static final String KEY_SQL = "sql";
        //edit end
        public static final String FOR_EACH_SAVE_FLAG = "$$forEachSave$$";

    }

    public class SplitTable {
        public static final String splitTableStartFlag = "$$";
        public static final String splitTableEndFlag = "$$";
    }

    public class State {
        public static final String Y = "Y";//是
        public static final String N = "N";//否
        public static final String E = "E";//无效状态
        public static final String U = "U";//有效状态
        public static final String I = "I";
        public static final int STATE_NORMAL = 1;//有效状态
        public static final long STATE_NORMAL_L = 1L;//有效状态
        public static final int STATE_ABNORMAL = 0;//无效状态
        public static final long STATE_ABNORMAL_L = 0L;//无效状态
    }

    public class Config {
        public static final String MODULE_NAME = "module_name";
        public static final String IS_USE_CACHE = "is_use_cache";
        public static final String LOAD_REDIS_CACHE = "load_redis_cache";
        //HTTP相关
        public static final String HTTP_CONCURRENTCAPACITY = "http.concurrentCapacity";
        public static final String HTTP_CONCURRENT_CAPACITY_ACQUIRE_TIMEOUT_SECONDS = "http.concurrentCapacityAcquireTimeoutSeconds";
        //WS相关
        public static final String WS_CONCURRENTCAPACITY = "ws.concurrentCapacity";
        public static final String WS_CONCURRENT_CAPACITY_ACQUIRE_TIMEOUT_SECONDS = "ws.concurrentCapacityAcquireTimeoutSeconds";

    }

    public class OrderType {
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
    }


    public class attrEditAssignType {
        public static final String SEPARATOR_SIGN = "&";//分割符号
        public static final String SEPARATOR_METHOD = "#";//表中配置 类和方法分割符
        public static final String SEPARATOR_METHOD_PARAM = "|";//表中配置 方法和参数分割符
        public static final String SEPARATOR_PARAMS = ",";//表中配置 方法和参数分割符
        public static final String ASSIGN_TYPE_METHOD = "2";
        public static final int EDIT_TYPE_2 = 2;
    }

    public class ModuleName {

        public static final String SEC = "SEC";
    }

    public class AZTInterfaces {
        public static final String ESOP_DEMAND_BILL_INTER_ACTIVE = "";//需求单交互接口
        public static final String ESOP_PROTOCOL_BILL_INTER_ACTIVE = "ProtocolBillInterActiveAZT";//协议PDF生成接口
        public static final String ESOP_PROTOCOL_PDF_DOWNLOAD = "PROTOCOL_PDF_DOWNLOAD_AZT";//协议PDF生成接口
        public static final String ESOP_BILL_FILE_UPLOAD = "FileUploadFromAZT";//文件上传接口
        public static final String ESOP_BILL_FILE_QUERY = "FileDownloadFromAZT";//文件查询接口
        public static final String ESOP_BILL_FILE_DELETE = "DeleteFileFromAZT";//文件删除接口
        public static final String ESOP_BILL_CHECK_PDF = "CheckPdfFromAZT";//协议审核预览时判断pdf是否生成
        public static final String ESOP_ADD_VERSION = "AddVersion";//修改协议版本号
        public static final String ESOP_CUSTOMER_UPLOAD = "CustomerUpload";//客户信息补传接口
        public static final String ESOP_AUTH_FILE_UPLOAD = "";//授权文件接口
        public static final String ESOP_AUTH_FILE_QUERY = "";//授权文件查询接口
    }

    public class EsopTo4AInterfaces{
        public static final String GET_4A_CODE = "Get4ACode";//获取4a主账号接口
    }

    public class EsopToPBossInterfaces{
        public static final String AUTO_GET_ADDRESS_PBOSS = "AutoGetAddressPBoss";//获取pboss地址联想接口
    }

    /**
     * 集团客户信息的接口
     */
    public class CustomerInterfaces{
        public static final String ESOP_BUSI_GETCUSTOMERS = "PT-SH-FS-OI3367";//获取集团列表
    }

    public class ReturnDef {
        public static final int JAVA_RULE_RETURN_CODE_YES = 1;// 允许
        public static final int JAVA_RULE_RETURN_CODE_WARNNING = 2;// 允许，但有警告
        public static final int JAVA_RULE_RETURN_CODE_NO = 3;// 不允许
        public static final int RULE_VERIFY_TYPE_WARNING = 1; // 警告
        public static final int RULE_VERIFY_TYPE_REJECT = 2; // 拒绝
    }

    public class StaticCacheDef {
        /**
         * CfgOperOffer  表      _OFFT^ + offerId
         * 获取策划对应的操作列表
         */
        public static final String OFFER_OPER_T = "_OFFT^";
        /**
         * 缓存拼接，符号
         */
        public static final String CACHE_LINK = "^";
        /**
         * 产品规格对应策划的缓存
         * _SPECT^ + spec_id
         */
        public static final String SPEC_T = "_SPECT^";
        /**
         * 业务对应属性的缓存
         * _OPERATTR^ +   BusiId()+"^"+ OperId  的缓存
         */
        public static final String OPER_ATTR_T = " _OPERATTR^";
        /**
         * 规则列表缓存
         * _RULESR^ + rulesetId
         */
        public static final String RULESET_RULE_T = "_RULET^";
        /**
         * bce缓存
         * _BCE^ + bceFrameId
         */
        public static final String BCE_FRAME = "_BCE^";

        /**
         * bceAttr缓存
         * _BCEATTR^ + bceFrameId
         */
        public static final String BCE_FRAMEATTR = "_BCEATTR^";

        /**
         * 营销活动frame的信息缓存
         * _PLOY_BCE_FRAME_INFO^ + bceFrameId_ployId
         */
        public static final String PLOY_BCE_FRAME_INFO = "_PLOY_BCE_FRAME_INFO^";


        /**
         * busiFrame缓存
         * _BUSI_FRAME^ + busiFrameId
         */
        public static final String BUSI_FRAME = "_BUSI_FRAME^";

        /**
         * busiSpecialRule缓存
         * _BUSI_SPECIAL_RULE^ + busiFrameId
         */
        public static final String BUSI_SPECIAL_RULE = "_BUSI_SPECIAL_RULE^";

        /**
         * CFG_DYNC_CRM_ATTR缓存
         * CFG_DYNC_CRM_ATTR^ + busiFrameId
         */
        public static final String CFG_DYNC_CRM_ATTR = "_CFG_DYNC_CRM_ATTR^";


        /**
         * bceFee缓存  规格对应费用缓存
         * _BCEFEE^+spec_id
         */

        public static final String BCE_FEE = "_BCEFEE^";

        /**
         * I18N国际化缓存
         * _I18N^ + res_key
         */
        public static final String I18N_RESOURCE = "_I18N^";
    }


    public class FLOW_TASK_CANDIDATE_TYPE {
        //0：人员(opId)，1：岗位stationId，2：岗位类型stationTypeId
        public static final String _USER = "0";
        public static final String _STATION_ID = "1";
        public static final String _STATION_TYPE_ID = "2";
        //add by xt 3:人员(dealStaffId)
        public static final String _GROUP_MANAGER = "3";
    }

    public static final int DATA_LENGTH = 2000;

    public class IPartyConstant {
        public final static int PARTYROLE_RELATETYPE_CUSTOMER_LINKMAN = 202;
        /**
         * 参与人角色关系--客户联系人 *
         */
        public final static int PARTYROLE_EXTEND_RELATETYPE_CUSTOMER_MAIN_LINKMAN = 206;/** 扩展的参与人角色关系--主要联系人 **/
    }

    public class ICommonConstant {
        public static final long STATIC_CODE_GROUP_LEVEL = 10007; //企业规模类型
    }

    public class IInsAppliStatusConstant {
        public static final String APPLI_STATUS_TO_CRM = "8"; //业务人员已提交，未提交到CRM处理 mod by haomeng
        public static final String APPLI_STATUS_FINISH = "9"; //业务受理完成
        public static final String APPLI_STATUS_DEALING = "10"; //提交到CRM，正在处理中
        public static final String APPLI_STATUS_AUDIT="16";       //申请单状态为已完成，进入稽核阶段
    }

    public class IInsAppliDtlStatusConstant {
        public static final String APPLI_STATUS_DTL_DCL = "1"; //待处理
        public static final String APPLI_STATUS_DTL_CLZ = "2"; //处理中
        public static final String APPLI_STATUS_DTL_CLCG = "3"; //处理成功
        public static final String APPLI_STATUS_DTL_CLSB = "4"; //处理失败
    }

    public class OperTypeConstant {
        public static final String BBOSS_OPER_TYPE_CREATE = "1";//新装
        public static final String BBOSS_OPER_TYPE_MEM_ADD = "2";//成员添加
        public static final String BBOSS_OPER_TYPE_MEM_DEL = "3";//成员删除
        public static final String BBOSS_OPER_TYPE_UPDATE = "4";//功能变更或参数变更
        public static final String BBOSS_OPER_TYPE_LOGOUT = "5";//套餐销户
        public static final String BBOSS_OPER_TYPE_COMBO_CHANGE = "6";//套餐变更
        public static final String BBOSS_OPER_TYPE_PROTOCAL_OPTION = "7";//协议续约
    }

    public class OrderSubmitFlag {
        public static final String PRO_FLAG = "PRO";//协议提交
        public static final String ORD_FLAG = "ORD";//业务订单提交
    }


    public class CustContType {
        public static final int BUSI_CONT = 1; //业务联系人
        public static final int ACCOUNT_CONT = 2; //账务联系人
    }

    public class CustGroupType {
        public static final int ARCHIVES = 0; //已建档集团
        public static final int ARCHIVES_NO = 1; //未建档集团
    }

    public class CustInfoState {
        public static final int FlOW_NO = 0; //未审核
        public static final int IN_FlOW = 1; //审核中
        public static final int FLOW_OVER = 2; //审核结束
    }
}

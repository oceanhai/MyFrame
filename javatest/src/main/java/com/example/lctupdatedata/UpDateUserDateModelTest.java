package com.example.lctupdatedata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhai on 2018/01/18 12:47.
 * 描述：
 */

public class UpDateUserDateModelTest {

    public static void main(String[] args){

        String str = ReadFileUtils.readByBytes("E:\\json1.txt");
        method01(str);

    }

    private static void method01(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject syn_dataObject = jsonObject.getJSONObject("syn_data");
            String pos_discount_rule = syn_dataObject.getString("pos_discount_rule");
            String pos_discount_rule_price = syn_dataObject.getString("pos_discount_rule_price");
            String pos_discount_to_product = syn_dataObject.getString("pos_discount_to_product");
            String pos_shop_product = syn_dataObject.getString("pos_shop_product");
            String pos_product_stock_batch = syn_dataObject.getString("pos_product_stock_batch");
            String stock_change = jsonObject.getString("stock_change");
            String pos_barcode_scale_product = syn_dataObject.getString("pos_barcode_scale_product");
            //v1.3.8版本追加
            String pos_product_unpack_rule = syn_dataObject.getString("pos_product_unpack_rule");
            //v1.4.1版本追加
            String pos_supplier = syn_dataObject.getString("pos_supplier");
            String pos_supplier_product = syn_dataObject.getString("pos_supplier_product");
            String pos_shop_product_stock = syn_dataObject.getString("pos_shop_product_stock");
            //连锁、一店多机 版本追加
            String pos_shop_order = syn_dataObject.getString("pos_shop_order");
            String pos_shop_order_product = syn_dataObject.getString("pos_shop_order_product");
            String pos_return_goods_order = syn_dataObject.getString("pos_return_goods_order");
            String pos_return_goods_order_product = syn_dataObject.getString("pos_return_goods_order_product");
            String pos_shop_order_pay_log = syn_dataObject.getString("pos_shop_order_pay_log");
            //雇员信息
            String pos_shop_employee=syn_dataObject.getString("pos_shop_employee");
            //一店多机  同步数据
            String pos_snyc_data = jsonObject.getString("pos_shop_snyc_data");

//            List<DiscountRule> rules = getPosDidCountRules(pos_discount_rule);//获取 规则
//            List<DiscountRulePrice> discountRulePrices = getPosDiscountRulePrice(pos_discount_rule_price);//获取  规则价格表
//            List<DiscountToProduct> posDiscountToProducts = getPosDiscountToProduct(pos_discount_to_product);//获取  规则 商品表
//            List<ShopGoods> shopGoodes = getShopGoods(pos_shop_product);//获取  店铺商品表
//            List<PosProductStockBatch> stockChange = getStockChange(stock_change);//  获取库存
//            List<PosProductStockBatch> productStockBatch = getProductStockBatch(pos_product_stock_batch);//获取  批次
//            List<PosBarcodeScaleProduct> posBarcodeScaleProducts = getPosBarcodeScaleProduct(pos_barcode_scale_product);//获取  条码电子秤 商品映射表
//            List<PosProductUnpackRule> posProductUnpackRules = getPosProductUnpackRule(pos_product_unpack_rule);//拆包规则
//            //v1.4.1版本追加
//            List<PosSupplier> posSupplierList = getPosSupplierList(pos_supplier);//供应商
//            List<PosSupplierProduct> posSupplierProductList = getPosSupplierProductList(pos_supplier_product);//供应商-商品
//            List<PosShopProductStock> posShopProductStockList = getPosShopProductStockList(pos_shop_product_stock);//店铺商品表库存修改记录
//            /**
//             * 连锁、一店多机 版本追加
//             */
//            List<OrderListRecord> orderListRecordList = getOrderListRecordList(pos_shop_order);//订单
//            List<ShopOrderProduct> shopOrderProductList = getShopOrderProductList(pos_shop_order_product);//订单商品
//            List<ReturnGoodsOrder> returnGoodsOrderList = getReturnGoodsOrderList(pos_return_goods_order);//退货订单
//            List<ReturnGoodsOrderProduct> returnGoodsOrderProductList = getReturnGoodsOrderProductList(pos_return_goods_order_product);//退货订单-商品
//            List<PosShopOrderPayLog> posShopOrderPayLogList = getPosShopOrderPayLogList(pos_shop_order_pay_log);//支付log表
            //会员
            List<Member> members = getSyncDataFroMember(pos_snyc_data);//主要是会员的同步
            //会员积分
            List<MemberScore> scores = getSyncDataFroScores(pos_snyc_data);//主要是会员的积分日志
//            //余额变动
//            List<PosMemberBalanceLog> balanceLogs = getSyncDataFroBalanceLogs(pos_snyc_data);//
//            //商品库存 同步
//            List<PosSyncInfo> productStockList = getSyncDataForProductStock(pos_snyc_data);//
//            //雇员信息
//            List<Pos_ShopEmployee> pos_shopEmployees = getEmployees(pos_shop_employee);


//            exeAllList(rules, discountRulePrices, posDiscountToProducts,
//                    shopGoodes, stockChange, productStockBatch, posBarcodeScaleProducts,
//                    posProductUnpackRules, posSupplierList, posSupplierProductList, posShopProductStockList,
//                    orderListRecordList, shopOrderProductList, returnGoodsOrderList, returnGoodsOrderProductList, posShopOrderPayLogList,
//                    members, scores, balanceLogs, productStockList, pos_shopEmployees,upDateVersion);
            exeAllList(members,scores);
        } catch (JSONException e) {

        }
    }


    /***
     * @param pos_snyc_data
     * @return
     */
    private static List<Member> getSyncDataFroMember(String pos_snyc_data) throws JSONException {
        JSONArray syncDataList = new JSONArray(pos_snyc_data);
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < syncDataList.length(); i++) {
            JSONObject one = syncDataList.getJSONObject(i);
            String tableName = one.getString("table_name");
            if (tableName.equals("pos_shop_member")) {
                int type = one.getInt("type");
                switch (type) {
                    case 4://model
                        Member member4 = getMemberByType4(one);
                        memberList.add(member4);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        Member member123 = getMemberByType123(one);
                        memberList.add(member123);
                        break;
                }

            }
        }
        return memberList;
    }

    /**
     * type=4 的时候的获取的member
     *
     * @param one
     * @return
     */
    private static Member getMemberByType4(JSONObject one) throws JSONException {
        Member member = new Member();
        JSONObject memberObject = new JSONObject(one.getString("value"));
        if (memberObject.has("create_time")) {
            member.setCreate_time(memberObject.getString("create_time"));
        }
        if (memberObject.has("member_credit")) {
            member.setMember_credit(memberObject.getDouble("member_credit"));
        }
        if (memberObject.has("member_score")) {
            member.setMember_score(memberObject.getInt("member_score"));
        }
        if (memberObject.has("member_allscore")) {
            member.setMember_allscore(memberObject.getInt("member_allscore"));
        }
        if (memberObject.has("member_birthd")) {
            member.setMember_birthd(memberObject.getString("member_birthd"));
        }
        if (memberObject.has("member_name")) {
            member.setMember_name(memberObject.getString("member_name"));
        }
        if (memberObject.has("is_has_car")) {
            member.setIs_has_car(memberObject.getInt("is_has_car"));
        }

        if (memberObject.has("is_solar_calendar")) {
            member.setIs_solar_calendar(memberObject.getInt("is_solar_calendar"));
        }

        if (memberObject.has("is_poor")) {
            member.setIs_poor(memberObject.getInt("is_poor"));
        }
        if (memberObject.has("last_modified")) {
            member.setLast_modified(memberObject.getString("last_modified"));
        }

        if (memberObject.has("member_balance")) {
            member.setMember_balance(memberObject.getDouble("member_balance"));
        }
        if (memberObject.has("member_balance_total")) {
            member.setMember_balance_total(memberObject.getDouble("member_balance_total"));
        }
        if (memberObject.has("member_card")) {
            member.setMember_card(memberObject.getString("member_card"));
        }
        if (memberObject.has("member_id")) {
            member.setMember_id(memberObject.getString("member_id"));
        }
        if (memberObject.has("member_name_abbreviation")) {
            member.setMember_name_abbreviation(memberObject.getString("member_name_abbreviation"));
        }

        if (memberObject.has("member_state")) {
            member.setMember_state(memberObject.getInt("member_state"));
        }

        if (memberObject.has("phone_last_four_num")) {
            member.setPhone_last_four_num(memberObject.getString("phone_last_four_num"));
        }
        if (memberObject.has("phone_num")) {
            member.setPhone_num(memberObject.getString("phone_num"));
        }

        if (memberObject.has("sex")) {
            member.setSex(memberObject.getInt("sex"));
        }
        if (memberObject.has("shop_id")) {
            member.setShop_id(memberObject.getString("shop_id"));
        }
        if (memberObject.has("total_member_credit")) {
            member.setTotal_member_credit(memberObject.getDouble("total_member_credit"));
        }
        if (memberObject.has("chain_id")) {
            member.setChain_id(memberObject.getLong("chain_id"));
        }
        if (memberObject.has("user_type")) {
            member.setUser_type(memberObject.getInt("user_type"));
        }
        if (memberObject.has("user_id")) {
            member.setUser_id(memberObject.getLong("user_id"));
        }
        if (memberObject.has("user_name")) {
            member.setUser_name(memberObject.getString("user_name"));
        }
        member.setSyncData_type(4);
        return member;

    }

    /***
     * 积分或者 余额 的加减或者set
     *
     * @param one
     * @return
     */
    private static Member getMemberByType123(JSONObject one) throws JSONException {
        Member member = new Member();
        double value = Double.parseDouble(one.getString("value"));//响应变化值
        String id = one.getString("object_pk_id");//id
        String column = one.getString("column_name");//目前只有 积分和 余额
        int type = one.getInt("type");//123 代表加减set
        member.setMember_id(id);
        member.setSyncValue(value);
        member.setColumn(column);
        member.setSyncData_type(type);
        return member;
    }

    /***
     * @param pos_snyc_data
     * @return
     */
    private static List<MemberScore> getSyncDataFroScores(String pos_snyc_data) throws JSONException {
        JSONArray syncDataList = new JSONArray(pos_snyc_data);
        List<MemberScore> scoreArrayList = new ArrayList<>();
        for (int i = 0; i < syncDataList.length(); i++) {
            JSONObject one = syncDataList.getJSONObject(i);
            String tableName = one.getString("table_name");
            if (tableName.equals("pos_member_integral")) {
                String value = one.getString("value");
                JSONObject jsonObject = new JSONObject(value);
                MemberScore memberScore = new MemberScore();

                if (jsonObject.has("create_time")) {
                    memberScore.setCreate_time(jsonObject.getString("create_time"));
                }
                if (jsonObject.has("shop_id")) {
                    memberScore.setShop_id(jsonObject.getString("shop_id"));
                }
                if (jsonObject.has("update_type")) {
                    memberScore.setUpdate_type(jsonObject.getInt("update_type"));
                }
                if (jsonObject.has("update_num")) {
                    memberScore.setUpdate_num(jsonObject.getInt("update_num"));
                }

                if (jsonObject.has("member_credit_id")) {
                    memberScore.setMember_credit_id(jsonObject.getString("member_credit_id"));
                }
                if (jsonObject.has("member_id")) {
                    memberScore.setMember_id(jsonObject.getString("member_id"));
                }

                if (jsonObject.has("order_id")) {
                    memberScore.setOrder_id(jsonObject.getString("order_id"));
                }
                if (jsonObject.has("return_goods_order_id")) {
                    memberScore.setReturn_goods_order_id(jsonObject.getString("return_goods_order_id"));
                }
                if (jsonObject.has("last_modified")) {
                    memberScore.setLast_modified(jsonObject.getString("last_modified"));
                }
                if (jsonObject.has("chain_id")) {
                    memberScore.setChain_id(jsonObject.getLong("chain_id"));
                }
                if (jsonObject.has("user_type")) {
                    memberScore.setUser_type(jsonObject.getInt("user_type"));
                }
                if (jsonObject.has("user_id")) {
                    memberScore.setUser_id(jsonObject.getLong("user_id"));
                }
                if (jsonObject.has("user_name")) {
                    memberScore.setUser_name(jsonObject.getString("user_name"));
                }
                scoreArrayList.add(memberScore);
            }
        }
        return scoreArrayList;
    }


    /*
* 执行更新
*
* @param rules
* @param discountRulePrices
* @param posDiscountToProducts
* @param shopGoodes
* @param stockChange
* @param productStockBatch
*/
//    private void exeAllList(List<DiscountRule> rules, List<DiscountRulePrice> discountRulePrices,
//                                           List<DiscountToProduct> posDiscountToProducts, List<ShopGoods> shopGoodes,
//                                           List<PosProductStockBatch> stockChange, List<PosProductStockBatch> productStockBatch,
//                                           List<PosBarcodeScaleProduct> posBarcodeScaleProducts, List<PosProductUnpackRule> posProductUnpackRules,
//                                           List<PosSupplier> posSupplierList, List<PosSupplierProduct> posSupplierProductList,
//                                           List<PosShopProductStock> posShopProductStockList,
//                                           List<OrderListRecord> orderListRecordList, List<ShopOrderProduct> shopOrderProductList, List<ReturnGoodsOrder> returnGoodsOrderList,
//                                           List<ReturnGoodsOrderProduct> returnGoodsOrderProductList, List<PosShopOrderPayLog> posShopOrderPayLogList,
//                                           List<Member> members, List<MemberScore> scores, List<PosMemberBalanceLog> balanceLogs,
//                                           List<PosSyncInfo> productStockList,List<Pos_ShopEmployee>  employees,
//                                           UpDateSqlVersion version) {
    private static void exeAllList(List<Member> members, List<MemberScore> scores) {

        try {
//            if (rules.size() > 0) {
//                log.addInfoLog("开始更新 营销规则");
//                errorMsg = "DiscountRule";
//                PosDbManager.getInstance().getSession().getDiscountRuleDao().insertOrReplaceInTx(rules);
//                log.addInfoLog("更新 营销规则完毕");
//            }
//
//            if (discountRulePrices.size() > 0) {
//                log.addInfoLog("开始更新 营销价格");
//                errorMsg = "DiscountRulePrice";
//                PosDbManager.getInstance().getSession().getDiscountRulePriceDao().insertOrReplaceInTx(discountRulePrices);
//                log.addInfoLog("更新 营销价格完毕");
//            }
//
//            if (posDiscountToProducts.size() > 0) {
//                log.addInfoLog("开始更新 营销商品");
//                errorMsg = "DiscountToProduct";
//                PosDbManager.getInstance().getSession().getDiscountToProductDao().insertOrReplaceInTx(posDiscountToProducts);
//                log.addInfoLog("更新 营销商品完毕");
//            }
//
//            if (shopGoodes.size() > 0) {
//                log.addInfoLog("开始更新  店铺商品");
//                errorMsg = "ShopGoods";
//                PosDbManager.getInstance().getSession().getShopGoodsDao().insertOrReplaceInTx(shopGoodes);
//                log.addInfoLog("更新 店铺商品完毕");
//            }
//
//            /**
//             * 连锁、一店多机、库存 版本后，只进行insert
//             */
//            if (productStockBatch.size() > 0) {
//                log.addInfoLog("开始更新  批次");
//                errorMsg = "ProductStockBatch";
//                PosDbManager.getInstance().getSession().getPosProductStockBatchDao().insertOrReplaceInTx(productStockBatch);
//                log.addInfoLog("批次  跟新完毕");
//            }
//
//            /**
//             * 连锁、一店多机、库存 版本后，转移到新同步
//             * ※注意：连锁之后，M站修改库存不在走这个地方，而是走下新
//             */
//            if (stockChange.size() > 0) { //库存修改
//                log.addInfoLog("连锁后不在执行 库存下旧逻辑");
//                errorMsg = "StockNum";
//            }
//
//            if (posBarcodeScaleProducts.size() > 0) {
//                log.addInfoLog("开始更新  条码秤商品对应表");
//                errorMsg = "PosBarcodeScaleProduct";
//                for (int x = 0; x < posBarcodeScaleProducts.size(); x++) {
//                    PosDbManager.getInstance().getSession().getPosBarcodeScaleProductDao().insertOrReplaceInTx(posBarcodeScaleProducts);
//                }
//            }
//
//            if (posProductUnpackRules.size() > 0) {
//                log.addInfoLog("开始更新  商品拆包规则表");
//                errorMsg = "PosProductUnpackRule";
//                for (int x = 0; x < posProductUnpackRules.size(); x++) {
//                    PosDbManager.getInstance().getSession().getPosProductUnpackRuleDao().insertOrReplaceInTx(posProductUnpackRules);
//                }
//            }
//
//            //v1.4.1版本追加
//            if (posSupplierList.size() > 0) {
//                log.addInfoLog("开始更新  供应商");
//                errorMsg = "PosSupplier";
//                for (int x = 0; x < posSupplierList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getPosSupplierDao().insertOrReplaceInTx(posSupplierList);
//                }
//            }
//
//            //v1.4.1版本追加
//            if (posSupplierProductList.size() > 0) {
//                log.addInfoLog("开始更新  供应商-商品");
//                errorMsg = "PosSupplierProduct";
//                for (int x = 0; x < posSupplierProductList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getPosSupplierProductDao().insertOrReplaceInTx(posSupplierProductList);
//                }
//            }
//
//            //v1.4.1版本追加
//            if (posShopProductStockList.size() > 0) {
//                log.addInfoLog("开始更新  店铺商品表库存修改记录");
//                errorMsg = "PosShopProductStock";
//                for (int x = 0; x < posShopProductStockList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getPosShopProductStockDao().insertOrReplaceInTx(posShopProductStockList);
//                }
//            }
//
//            /**
//             * 连锁 一店多机
//             */
//            //订单
//            if (orderListRecordList.size() > 0) {
//                log.addInfoLog("开始更新  订单记录");
//                errorMsg = "OrderListRecord";
//                for (int x = 0; x < orderListRecordList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getOrderListRecordDao().insertOrReplaceInTx(orderListRecordList);
//                }
//            }
//            //订单商品表
//            if (shopOrderProductList.size() > 0) {
//                log.addInfoLog("开始更新  订单商品记录");
//                errorMsg = "ShopOrderProduct";
//                for (int x = 0; x < shopOrderProductList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getShopOrderProductDao().insertOrReplaceInTx(shopOrderProductList);
//                }
//            }
//            //退货订单
//            if (returnGoodsOrderList.size() > 0) {
//                log.addInfoLog("开始更新  退货订单记录");
//                errorMsg = "ReturnGoodsOrder";
//                for (int x = 0; x < returnGoodsOrderList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getReturnGoodsOrderDao().insertOrReplaceInTx(returnGoodsOrderList);
//                }
//            }
//            //退货订单-商品
//            if (returnGoodsOrderProductList.size() > 0) {
//                log.addInfoLog("开始更新  退货订单商品记录");
//                errorMsg = "ReturnGoodsOrderProduct";
//                for (int x = 0; x < returnGoodsOrderProductList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getReturnGoodsOrderProductDao().insertOrReplaceInTx(returnGoodsOrderProductList);
//                }
//            }
//            //支付log
//            if (posShopOrderPayLogList.size() > 0) {
//                log.addInfoLog("开始更新  支付log记录");
//                errorMsg = "PosShopOrderPayLog";
//                for (int x = 0; x < posShopOrderPayLogList.size(); x++) {
//                    PosDbManager.getInstance().getSession().getPosShopOrderPayLogDao().insertOrReplaceInTx(posShopOrderPayLogList);
//                }
//            }
            //会员的同步
            if (members.size() > 0) {
                System.out.println("会员的同步members.size()="+members.size());
                addMembers(members); //添加会员去
            }
            //会员积分的同步
            if (scores.size() > 0) {
                System.out.println("会员积分的同步scores.size()="+scores.size());
            }
//            //会员余额的同步
//            if (balanceLogs.size() > 0) {
//                log.addInfoLog("开始更新 会员余额的数据");
//                errorMsg = "MemberScoreLog";
//                PosDbManager.getInstance().getSession().getPosMemberBalanceLogDao().insertOrReplaceInTx( balanceLogs);
//            }
//            //库存同步
//            if(productStockList.size()>0){
//                log.addInfoLog("开始同步 商品库存");
//                errorMsg = "product_stock";
//                synProductStockList(productStockList);
//            }
//
//
//            //员工信息
//            if (employees.size() > 0) {
//                log.addInfoLog("开始同步 员工信息");
//                errorMsg = "employees";
//                insertOrUpdateEmployess(employees);
//            }

        } catch (Exception e) {
        } finally {
        }
    }

    /***
     * 添加会员  根据会员的  这个是在事物里调用的 外层有trycath
     *
     * @param members
     */
    private static void addMembers(List<Member> members) {
        for (Member member : members) {
            if (member.getSyncData_type() == 4) {
                System.out.println("type="+member.getSyncData_type()+"  addOrUpDateMember");
            }
            //1 2 .3的做法
            else {
                Member realMember = new Member();
                String column = member.getColumn();
                double value = member.getSyncValue();
                int type = member.getSyncData_type();
                if (column.equals("member_balance")){//余额{}
                    if (type == 1) {//加余额
                        realMember.setMember_balance(realMember.getMember_balance() + value);
                        realMember.setMember_balance_total(realMember.getMember_balance_total() + value);

                    } else if (type == 2){//减少余额
                        realMember.setMember_balance(realMember.getMember_balance() - value);
                    } else if (type == 3) {//set
                        realMember.setMember_balance(value);
                    }

                    System.out.println("member_balance type="+member.getSyncData_type()+"  addOrUpDateMember");
                } else if (column.equals("member_score")) {//积分

                    if (type == 1) {//加积分
                        realMember.setMember_score(realMember.getMember_score() + (int) value);
                        realMember.setMember_allscore(realMember.getMember_allscore() + (int) value);

                    } else if (type == 2) {//减少积分
                        realMember.setMember_score(realMember.getMember_score() - (int) value);

                    } else if (type == 3) {
                        realMember.setMember_score((int) value);
                    }
                    System.out.println("member_score type="+member.getSyncData_type()+"  addOrUpDateMember");
                }else if (column.equals("member_allscore")) {//会员总积分
                    if (type == 2) {//退货时候 历史积分可能会减
                        realMember.setMember_allscore(realMember.getMember_allscore()- (int) value);
                    }
                    System.out.println("member_allscore type="+member.getSyncData_type()+"  addOrUpDateMember");
                }else if (column.equals("member_credit")){//赊账金额
                    if (type == 1) {//+赊账金额
                        realMember.setMember_credit(DoubleUtils.getDoubleByDecimalFormat(null,realMember.getMember_credit() + value));
                        realMember.setTotal_member_credit(DoubleUtils.getDoubleByDecimalFormat(null,
                                realMember.getTotal_member_credit() + value));
                    } else if (type == 2){//-赊账金额
                        realMember.setMember_credit(DoubleUtils.getDoubleByDecimalFormat(null,
                                realMember.getMember_credit() - value));
                    } else if (type == 3) {//set
                        realMember.setMember_credit(value);
                    }
                    System.out.println("member_credit type="+member.getSyncData_type()+"  addOrUpDateMember");
                }else if (column.equals("total_member_credit")){//赊账总金额
                    if (type == 2){//-赊账金额  赊账的订单进行退货
                        realMember.setTotal_member_credit(DoubleUtils.getDoubleByDecimalFormat(null,
                                realMember.getTotal_member_credit() - value));
                    }
                    System.out.println("total_member_credit type="+member.getSyncData_type()+"  addOrUpDateMember");
                }else if(column.equals("member_state")){//赊账过
                    if (type == 3){//set    = 1
                        realMember.setMember_state((int) value);
                    }
                    System.out.println("member_state type="+member.getSyncData_type()+"  addOrUpDateMember");
                }
            }
        }

    }
}

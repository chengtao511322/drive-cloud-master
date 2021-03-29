<template>
    <div class="app-container">

        <!-- 添加或修改平台账务流水明细对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="主键" prop="id">
                                <el-input v-model="form.id" placeholder="请输入主键" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="账务流水id" prop="accountId">
                                <el-input v-model="form.accountId" placeholder="请输入账务流水id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单号" prop="orderNo">
                                <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="明细项目类型(	0.考试报名驾校服务费用	1.学车报名驾校服务费用	2.报名平台服务费用	3.课时教练提成费用	4.课时平台提成费用	5.课时推荐学员提成费用	6.课时推荐教练提成费用	7.考试场地服务费用	8.考试车服务费用		9. 平台钱包退款	10.教练钱包退款	11.教练推荐人钱包退款	12.学员推荐人钱包退款	13.场地费退款	14.考试车费用退款		15.取消收入-平台提成费用	16.取消收入-学员提成费用	17.取消收入-教练提成费用	18.取消扣款-教练钱包扣款	19.取消扣款-学员订单扣款)" prop="itemType">
                                <el-input v-model="form.itemType" placeholder="请输入明细项目类型(	0.考试报名驾校服务费用	1.学车报名驾校服务费用	2.报名平台服务费用	3.课时教练提成费用	4.课时平台提成费用	5.课时推荐学员提成费用	6.课时推荐教练提成费用	7.考试场地服务费用	8.考试车服务费用		9. 平台钱包退款	10.教练钱包退款	11.教练推荐人钱包退款	12.学员推荐人钱包退款	13.场地费退款	14.考试车费用退款		15.取消收入-平台提成费用	16.取消收入-学员提成费用	17.取消收入-教练提成费用	18.取消扣款-教练钱包扣款	19.取消扣款-学员订单扣款)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="明细项目名称" prop="itemName">
                                <el-input v-model="form.itemName" placeholder="请输入明细项目名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单金额" prop="itemAmount">
                                <el-input v-model="form.itemAmount" placeholder="请输入订单金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否对账(是，否)" prop="isReconciled">
                                <el-input v-model="form.isReconciled" placeholder="请输入是否对账(是，否)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否提现(是，否)" prop="putForward">
                                <el-input v-model="form.putForward" placeholder="请输入是否提现(是，否)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付类型(1:支付宝 ;  2 : 微信 ; 3-平台支付宝钱包；4-平台微信钱包；5-司机钱包  345为退款，扣款时使用)" prop="payType">
                                <el-input v-model="form.payType" placeholder="请输入支付类型(1:支付宝 ;  2 : 微信 ; 3-平台支付宝钱包；4-平台微信钱包；5-司机钱包  345为退款，扣款时使用)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="收益人类型（1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校）" prop="incomeUserType">
                                <el-input v-model="form.incomeUserType" placeholder="请输入收益人类型（1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="收益人id" prop="incomeUserId">
                                <el-input v-model="form.incomeUserId" placeholder="请输入收益人id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="创建时间" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择创建时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                                <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" prop="tradeSubject">
                                <el-input v-model="form.tradeSubject" placeholder="请输入交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" prop="tradeSubjectItems">
                                <el-input v-model="form.tradeSubjectItems" placeholder="请输入交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="publishData">{{
                    id ? "修改" : "发布"
                    }}</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
    </div>
</template>

<script>
    import { listAccountFlowDetail,listPageAccountFlowDetail, getAccountFlowDetail, delAccountFlowDetail, addAccountFlowDetail, updateAccountFlowDetail, exportAccountFlowDetail } from "@/api/admin/accountFlowDetail";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "AccountFlowDetail",
        data() {
            return {
                // 遮罩层
                loading: true,
                // 数据ID
                id: this.$route.query.id,
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                accountId: [
                            { required: true, message: "账务流水id不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                itemType: [
                            { required: true, message: "明细项目类型(	0.考试报名驾校服务费用	1.学车报名驾校服务费用	2.报名平台服务费用	3.课时教练提成费用	4.课时平台提成费用	5.课时推荐学员提成费用	6.课时推荐教练提成费用	7.考试场地服务费用	8.考试车服务费用		9. 平台钱包退款	10.教练钱包退款	11.教练推荐人钱包退款	12.学员推荐人钱包退款	13.场地费退款	14.考试车费用退款		15.取消收入-平台提成费用	16.取消收入-学员提成费用	17.取消收入-教练提成费用	18.取消扣款-教练钱包扣款	19.取消扣款-学员订单扣款)不能为空", trigger: "blur" }
                        ],
                                                itemName: [
                            { required: true, message: "明细项目名称不能为空", trigger: "blur" }
                        ],
                                                itemAmount: [
                            { required: true, message: "订单金额不能为空", trigger: "blur" }
                        ],
                                                isReconciled: [
                            { required: true, message: "是否对账(是，否)不能为空", trigger: "blur" }
                        ],
                                                putForward: [
                            { required: true, message: "是否提现(是，否)不能为空", trigger: "blur" }
                        ],
                                                payType: [
                            { required: true, message: "支付类型(1:支付宝 ;  2 : 微信 ; 3-平台支付宝钱包；4-平台微信钱包；5-司机钱包  345为退款，扣款时使用)不能为空", trigger: "blur" }
                        ],
                                                incomeUserType: [
                            { required: true, message: "收益人类型（1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校）不能为空", trigger: "blur" }
                        ],
                                                incomeUserId: [
                            { required: true, message: "收益人id不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                tradeSubject: [
                            { required: true, message: "交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)不能为空", trigger: "blur" }
                        ],
                                                tradeSubjectItems: [
                            { required: true, message: "交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getAccountFlowDetailData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getAccountFlowDetailData(){
                console.log('getBanner请求参数{}',this.id);
                getAccountFlowDetail(this.id).then(response => {
                    console.log("getBanner请求参数列表：", response);
                    let subCode = response.subCode;
                    if (subCode == 'SUCCESS'){
                        this.form = response.data;
                    }
                });
            },

            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.form = {
                                                id: undefined,
                                                accountId: undefined,
                                                orderNo: undefined,
                                                itemType: undefined,
                                                itemName: undefined,
                                                itemAmount: undefined,
                                                isReconciled: undefined,
                                                putForward: undefined,
                                                payType: undefined,
                                                incomeUserType: undefined,
                                                incomeUserId: undefined,
                                                createTime: undefined,
                                                operatorId: undefined,
                                                tradeSubject: undefined,
                                                tradeSubjectItems: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addAccountFlowDetailData(this.form);
                        }else{
                            // 修改接口
                            this.updateAccountFlowDetailData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addAccountFlowDetailData(data){
                addAccountFlowDetail(data).then(response => {
                    console.log("接口请求数据：", response);
                    let subCode = response.subCode;
                    if (subCode == 'SUCCESS'){
                        this.$message({
                            type: "success",
                            message: "数据添加成功!"
                        });
                        // 数据重置
                        this.reset();
                        // 返回页面
                        this.backPage();
                    }
                });
            },
            // 修改数据方法
            updateAccountFlowDetailData(data){
                updateAccountFlowDetail(data).then(response => {
                    console.log("接口请求数据：", response);
                    let subCode = response.subCode;
                    if (subCode == 'SUCCESS'){
                        this.$message({
                            type: "success",
                            message: "数据修改成功!"
                        });
                        // 数据重置
                        this.reset();
                        // 返回页面
                        this.backPage();
                    }
                });
            },
        }
    };
</script>
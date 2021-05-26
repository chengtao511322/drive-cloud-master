<template>
    <div class="app-container">

        <!-- 添加或修改对话框 -->
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
                            <el-form-item label="钱包用户id" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入钱包用户id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算总金额" prop="settleAccountsSum">
                                <el-input v-model="form.settleAccountsSum" placeholder="请输入清算总金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算申请时间" prop="settleAccountsApplyTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.settleAccountsApplyTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择清算申请时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算完成时间" prop="settleAccountsCompleteTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.settleAccountsCompleteTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择清算完成时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="审核通过时间" prop="examineTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.examineTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择审核通过时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算笔数" prop="settleAccountsNumber">
                                <el-input v-model="form.settleAccountsNumber" placeholder="请输入清算笔数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算方式(1-支付宝 ,2-微信,3-银行卡)" prop="settleType">
                                <el-input v-model="form.settleType" placeholder="请输入清算方式(1-支付宝 ,2-微信,3-银行卡)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="转账唯一订单号" prop="outBizNo">
                                <el-input v-model="form.outBizNo" placeholder="请输入转账唯一订单号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="备注" prop="remarks">
                                <el-input v-model="form.remarks" placeholder="请输入备注" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="提现账号" prop="submitAccount">
                                <el-input v-model="form.submitAccount" placeholder="请输入提现账号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="提现真实姓名" prop="submitRealName">
                                <el-input v-model="form.submitRealName" placeholder="请输入提现真实姓名" />
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
                            <el-form-item label="驳回时间" prop="rejectTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.rejectTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择驳回时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="开户行" prop="openAccountBank">
                                <el-input v-model="form.openAccountBank" placeholder="请输入开户行" />
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
    import { listWalletSettlementSummary,listPageWalletSettlementSummary, getWalletSettlementSummary, delWalletSettlementSummary, addWalletSettlementSummary, updateWalletSettlementSummary, exportWalletSettlementSummary } from "@/api/admin/walletSettlementSummary";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "WalletSettlementSummary",
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
                                                userId: [
                            { required: true, message: "钱包用户id不能为空", trigger: "blur" }
                        ],
                                                settleAccountsSum: [
                            { required: true, message: "清算总金额不能为空", trigger: "blur" }
                        ],
                                                settleAccountsApplyTime: [
                            { required: true, message: "清算申请时间不能为空", trigger: "blur" }
                        ],
                                                settleAccountsCompleteTime: [
                            { required: true, message: "清算完成时间不能为空", trigger: "blur" }
                        ],
                                                examineTime: [
                            { required: true, message: "审核通过时间不能为空", trigger: "blur" }
                        ],
                                                settleAccountsNumber: [
                            { required: true, message: "清算笔数不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)不能为空", trigger: "blur" }
                        ],
                                                settleType: [
                            { required: true, message: "清算方式(1-支付宝 ,2-微信,3-银行卡)不能为空", trigger: "blur" }
                        ],
                                                outBizNo: [
                            { required: true, message: "转账唯一订单号不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                submitAccount: [
                            { required: true, message: "提现账号不能为空", trigger: "blur" }
                        ],
                                                submitRealName: [
                            { required: true, message: "提现真实姓名不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                rejectTime: [
                            { required: true, message: "驳回时间不能为空", trigger: "blur" }
                        ],
                                                openAccountBank: [
                            { required: true, message: "开户行不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getWalletSettlementSummaryData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getWalletSettlementSummaryData(){
                console.log('getBanner请求参数{}',this.id);
                getWalletSettlementSummary(this.id).then(response => {
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
                                                userId: undefined,
                                                settleAccountsSum: undefined,
                                                settleAccountsApplyTime: undefined,
                                                settleAccountsCompleteTime: undefined,
                                                examineTime: undefined,
                                                settleAccountsNumber: undefined,
                                                status: undefined,
                                                settleType: undefined,
                                                outBizNo: undefined,
                                                remarks: undefined,
                                                submitAccount: undefined,
                                                submitRealName: undefined,
                                                operatorId: undefined,
                                                rejectTime: undefined,
                                                openAccountBank: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addWalletSettlementSummaryData(this.form);
                        }else{
                            // 修改接口
                            this.updateWalletSettlementSummaryData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addWalletSettlementSummaryData(data){
                addWalletSettlementSummary(data).then(response => {
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
            updateWalletSettlementSummaryData(data){
                updateWalletSettlementSummary(data).then(response => {
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
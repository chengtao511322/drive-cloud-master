<template>
    <div class="app-container">

        <!-- 添加或修改平台交易流水日志对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="编号" prop="id">
                                <el-input v-model="form.id" placeholder="请输入编号" />
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
                            <el-form-item label="交易类型(1;支付流水；2：退款流水 )" prop="tranType">
                                <el-input v-model="form.tranType" placeholder="请输入交易类型(1;支付流水；2：退款流水 )" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付类型(1：支付宝；2：微信) " prop="payType">
                                <el-input v-model="form.payType" placeholder="请输入支付类型(1：支付宝；2：微信) " />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付方账号" prop="payAccount">
                                <el-input v-model="form.payAccount" placeholder="请输入支付方账号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单应付金额" prop="payableAmount">
                                <el-input v-model="form.payableAmount" placeholder="请输入订单应付金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)" prop="thirdOrderNo">
                                <el-input v-model="form.thirdOrderNo" placeholder="请输入第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="第三方订金额" prop="thirdOrderAmount">
                                <el-input v-model="form.thirdOrderAmount" placeholder="请输入第三方订金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)" />
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
                            <el-form-item label="更新时间" prop="updateTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.updateTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择更新时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="第三方返回时间" prop="thirdReturnTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.thirdReturnTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择第三方返回时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="提交参数" prop="commitParameter">
                                <el-input v-model="form.commitParameter" placeholder="请输入提交参数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="返回参数" prop="returnParameter">
                                <el-input v-model="form.returnParameter" placeholder="请输入返回参数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）" prop="payTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.payTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="买家支付宝账号（用于在取消扣款时，转账使用）" prop="buyerLogonId">
                                <el-input v-model="form.buyerLogonId" placeholder="请输入买家支付宝账号（用于在取消扣款时，转账使用）" />
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
    import { listPayFlowLog,listPagePayFlowLog, getPayFlowLog, delPayFlowLog, addPayFlowLog, updatePayFlowLog, exportPayFlowLog } from "@/api/admin/payFlowLog";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "PayFlowLog",
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
                            { required: true, message: "编号不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                tranType: [
                            { required: true, message: "交易类型(1;支付流水；2：退款流水 )不能为空", trigger: "blur" }
                        ],
                                                payType: [
                            { required: true, message: "支付类型(1：支付宝；2：微信) 不能为空", trigger: "blur" }
                        ],
                                                payAccount: [
                            { required: true, message: "支付方账号不能为空", trigger: "blur" }
                        ],
                                                payableAmount: [
                            { required: true, message: "订单应付金额不能为空", trigger: "blur" }
                        ],
                                                thirdOrderNo: [
                            { required: true, message: "第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)不能为空", trigger: "blur" }
                        ],
                                                thirdOrderAmount: [
                            { required: true, message: "第三方订金额不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                thirdReturnTime: [
                            { required: true, message: "第三方返回时间不能为空", trigger: "blur" }
                        ],
                                                commitParameter: [
                            { required: true, message: "提交参数不能为空", trigger: "blur" }
                        ],
                                                returnParameter: [
                            { required: true, message: "返回参数不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）不能为空", trigger: "blur" }
                        ],
                                                buyerLogonId: [
                            { required: true, message: "买家支付宝账号（用于在取消扣款时，转账使用）不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getPayFlowLogData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getPayFlowLogData(){
                console.log('getBanner请求参数{}',this.id);
                getPayFlowLog(this.id).then(response => {
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
                                                orderNo: undefined,
                                                tranType: undefined,
                                                payType: undefined,
                                                payAccount: undefined,
                                                payableAmount: undefined,
                                                thirdOrderNo: undefined,
                                                thirdOrderAmount: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                thirdReturnTime: undefined,
                                                commitParameter: undefined,
                                                returnParameter: undefined,
                                                payTime: undefined,
                                                buyerLogonId: undefined,
                                                operatorId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addPayFlowLogData(this.form);
                        }else{
                            // 修改接口
                            this.updatePayFlowLogData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addPayFlowLogData(data){
                addPayFlowLog(data).then(response => {
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
            updatePayFlowLogData(data){
                updatePayFlowLog(data).then(response => {
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
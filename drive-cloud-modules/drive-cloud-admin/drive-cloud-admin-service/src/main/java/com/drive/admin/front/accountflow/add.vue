<template>
    <div class="app-container">

        <!-- 添加或修改平台账务流水对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="id" prop="id">
                                <el-input v-model="form.id" placeholder="请输入id" />
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
                            <el-form-item label="流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))" prop="flowType">
                                <el-input v-model="form.flowType" placeholder="请输入流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付类型(1：支付宝；2：微信)" prop="payType">
                                <el-input v-model="form.payType" placeholder="请输入支付类型(1：支付宝；2：微信)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单金额" prop="orderAmount">
                                <el-input v-model="form.orderAmount" placeholder="请输入订单金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际支付金额" prop="actualPayAmount">
                                <el-input v-model="form.actualPayAmount" placeholder="请输入实际支付金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="退款金额" prop="drawbackAmount">
                                <el-input v-model="form.drawbackAmount" placeholder="请输入退款金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付时间" prop="payTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.payTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择支付时间">
                                </el-date-picker>
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
                            <el-form-item label="是否结算（0-否 ，1-是）" prop="isSettlement">
                                <el-input v-model="form.isSettlement" placeholder="请输入是否结算（0-否 ，1-是）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="退款类型（0：全额退款 1：部分退款）" prop="refundType">
                                <el-input v-model="form.refundType" placeholder="请输入退款类型（0：全额退款 1：部分退款）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="退款时间" prop="refundTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.refundTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择退款时间">
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
    import { listAccountFlow,listPageAccountFlow, getAccountFlow, delAccountFlow, addAccountFlow, updateAccountFlow, exportAccountFlow } from "@/api/admin/accountFlow";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "AccountFlow",
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
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                flowType: [
                            { required: true, message: "流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))不能为空", trigger: "blur" }
                        ],
                                                payType: [
                            { required: true, message: "支付类型(1：支付宝；2：微信)不能为空", trigger: "blur" }
                        ],
                                                orderAmount: [
                            { required: true, message: "订单金额不能为空", trigger: "blur" }
                        ],
                                                actualPayAmount: [
                            { required: true, message: "实际支付金额不能为空", trigger: "blur" }
                        ],
                                                drawbackAmount: [
                            { required: true, message: "退款金额不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                isSettlement: [
                            { required: true, message: "是否结算（0-否 ，1-是）不能为空", trigger: "blur" }
                        ],
                                                refundType: [
                            { required: true, message: "退款类型（0：全额退款 1：部分退款）不能为空", trigger: "blur" }
                        ],
                                                refundTime: [
                            { required: true, message: "退款时间不能为空", trigger: "blur" }
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
                this.getAccountFlowData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getAccountFlowData(){
                console.log('getBanner请求参数{}',this.id);
                getAccountFlow(this.id).then(response => {
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
                                                flowType: undefined,
                                                payType: undefined,
                                                orderAmount: undefined,
                                                actualPayAmount: undefined,
                                                drawbackAmount: undefined,
                                                payTime: undefined,
                                                createTime: undefined,
                                                isSettlement: undefined,
                                                refundType: undefined,
                                                refundTime: undefined,
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
                            this.addAccountFlowData(this.form);
                        }else{
                            // 修改接口
                            this.updateAccountFlowData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addAccountFlowData(data){
                addAccountFlow(data).then(response => {
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
            updateAccountFlowData(data){
                updateAccountFlow(data).then(response => {
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
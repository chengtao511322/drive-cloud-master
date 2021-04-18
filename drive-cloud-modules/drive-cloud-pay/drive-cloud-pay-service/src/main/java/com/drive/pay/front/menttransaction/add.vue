<template>
    <div class="app-container">

        <!-- 添加或修改支付交易流水信息表对话框 -->
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
                            <el-form-item label="支付金额" prop="payAmount">
                                <el-input v-model="form.payAmount" placeholder="请输入支付金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付状态;0待支付1已经支付2支付超时3支付失败" prop="paymentStatus">
                                <el-input v-model="form.paymentStatus" placeholder="请输入支付状态;0待支付1已经支付2支付超时3支付失败" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入" />
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
                            <el-form-item label="乐观锁" prop="revision">
                                <el-input v-model="form.revision" placeholder="请输入乐观锁" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用" prop="partyPayId">
                                <el-input v-model="form.partyPayId" placeholder="请输入第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="使用雪花算法生产 支付系统 支付id" prop="paymentId">
                                <el-input v-model="form.paymentId" placeholder="请输入使用雪花算法生产 支付系统 支付id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="创建人" prop="createUser">
                                <el-input v-model="form.createUser" placeholder="请输入创建人" />
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
                            <el-form-item label="修改时间" prop="updateTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.updateTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择修改时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="修改者" prop="updateUser">
                                <el-input v-model="form.updateUser" placeholder="请输入修改者" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付渠道" prop="paymentChannel">
                                <el-input v-model="form.paymentChannel" placeholder="请输入支付渠道" />
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
                            <el-form-item label="body" prop="body">
                                <el-input v-model="form.body" placeholder="请输入body" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="subject">
                                <el-input v-model="form.subject" placeholder="请输入" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="payParams">
                                <el-input v-model="form.payParams" placeholder="请输入" />
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
                            <el-form-item label="手机号码" prop="phone">
                                <el-input v-model="form.phone" placeholder="请输入手机号码" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="tenantId">
                                <el-input v-model="form.tenantId" placeholder="请输入" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="租户渠道ID" prop="platformChannelId">
                                <el-input v-model="form.platformChannelId" placeholder="请输入租户渠道ID" />
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
    import { listMentTransaction,listPageMentTransaction, getMentTransaction, delMentTransaction, addMentTransaction, updateMentTransaction, exportMentTransaction } from "@/api/pay/mentTransaction";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "MentTransaction",
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
                                                payAmount: [
                            { required: true, message: "支付金额不能为空", trigger: "blur" }
                        ],
                                                paymentStatus: [
                            { required: true, message: "支付状态;0待支付1已经支付2支付超时3支付失败不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                revision: [
                            { required: true, message: "乐观锁不能为空", trigger: "blur" }
                        ],
                                                partyPayId: [
                            { required: true, message: "第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用不能为空", trigger: "blur" }
                        ],
                                                paymentId: [
                            { required: true, message: "使用雪花算法生产 支付系统 支付id不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建人不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "修改者不能为空", trigger: "blur" }
                        ],
                                                paymentChannel: [
                            { required: true, message: "支付渠道不能为空", trigger: "blur" }
                        ],
                                                orderAmount: [
                            { required: true, message: "订单金额不能为空", trigger: "blur" }
                        ],
                                                body: [
                            { required: true, message: "body不能为空", trigger: "blur" }
                        ],
                                                subject: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                payParams: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间不能为空", trigger: "blur" }
                        ],
                                                phone: [
                            { required: true, message: "手机号码不能为空", trigger: "blur" }
                        ],
                                                tenantId: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                platformChannelId: [
                            { required: true, message: "租户渠道ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getMentTransactionData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getMentTransactionData(){
                console.log('getBanner请求参数{}',this.id);
                getMentTransaction(this.id).then(response => {
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
                                                payAmount: undefined,
                                                paymentStatus: undefined,
                                                userId: undefined,
                                                orderNo: undefined,
                                                revision: undefined,
                                                partyPayId: undefined,
                                                paymentId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                updateUser: undefined,
                                                paymentChannel: undefined,
                                                orderAmount: undefined,
                                                body: undefined,
                                                subject: undefined,
                                                payParams: undefined,
                                                payTime: undefined,
                                                phone: undefined,
                                                tenantId: undefined,
                                                platformChannelId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addMentTransactionData(this.form);
                        }else{
                            // 修改接口
                            this.updateMentTransactionData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addMentTransactionData(data){
                addMentTransaction(data).then(response => {
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
            updateMentTransactionData(data){
                updateMentTransaction(data).then(response => {
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
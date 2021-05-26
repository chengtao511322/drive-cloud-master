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
                            <el-form-item label="清算总表id" prop="settleAccountsId">
                                <el-input v-model="form.settleAccountsId" placeholder="请输入清算总表id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="钱包明细id" prop="walletDetailId">
                                <el-input v-model="form.walletDetailId" placeholder="请输入钱包明细id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="清算金额" prop="settleAccountsMoney">
                                <el-input v-model="form.settleAccountsMoney" placeholder="请输入清算金额" />
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
    import { listWalletSettlementDetail,listPageWalletSettlementDetail, getWalletSettlementDetail, delWalletSettlementDetail, addWalletSettlementDetail, updateWalletSettlementDetail, exportWalletSettlementDetail } from "@/api/admin/walletSettlementDetail";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "WalletSettlementDetail",
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
                                                settleAccountsId: [
                            { required: true, message: "清算总表id不能为空", trigger: "blur" }
                        ],
                                                walletDetailId: [
                            { required: true, message: "钱包明细id不能为空", trigger: "blur" }
                        ],
                                                settleAccountsMoney: [
                            { required: true, message: "清算金额不能为空", trigger: "blur" }
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
                this.getWalletSettlementDetailData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getWalletSettlementDetailData(){
                console.log('getBanner请求参数{}',this.id);
                getWalletSettlementDetail(this.id).then(response => {
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
                                                settleAccountsId: undefined,
                                                walletDetailId: undefined,
                                                settleAccountsMoney: undefined,
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
                            this.addWalletSettlementDetailData(this.form);
                        }else{
                            // 修改接口
                            this.updateWalletSettlementDetailData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addWalletSettlementDetailData(data){
                addWalletSettlementDetail(data).then(response => {
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
            updateWalletSettlementDetailData(data){
                updateWalletSettlementDetail(data).then(response => {
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
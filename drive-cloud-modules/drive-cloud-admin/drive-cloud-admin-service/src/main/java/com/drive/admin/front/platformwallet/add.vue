<template>
    <div class="app-container">

        <!-- 添加或修改教练钱包表对话框 -->
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
                            <el-form-item label="钱包用户Id" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入钱包用户Id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)" prop="walletType">
                                <el-input v-model="form.walletType" placeholder="请输入钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="钱包总额" prop="walletAmount">
                                <el-input v-model="form.walletAmount" placeholder="请输入钱包总额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="密码" prop="password">
                                <el-input v-model="form.password" placeholder="请输入密码" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="微信账号" prop="wechatAccount">
                                <el-input v-model="form.wechatAccount" placeholder="请输入微信账号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付宝账号" prop="aliAccount">
                                <el-input v-model="form.aliAccount" placeholder="请输入支付宝账号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="微信真实姓名" prop="wechatRealName">
                                <el-input v-model="form.wechatRealName" placeholder="请输入微信真实姓名" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="支付宝账号真实名称" prop="aliRealName">
                                <el-input v-model="form.aliRealName" placeholder="请输入支付宝账号真实名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="银行卡号" prop="bankAccount">
                                <el-input v-model="form.bankAccount" placeholder="请输入银行卡号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="持卡人姓名" prop="bankAccountName">
                                <el-input v-model="form.bankAccountName" placeholder="请输入持卡人姓名" />
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
    import { listPlatformWallet,listPagePlatformWallet, getPlatformWallet, delPlatformWallet, addPlatformWallet, updatePlatformWallet, exportPlatformWallet } from "@/api/admin/platformWallet";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "PlatformWallet",
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
                                                userId: [
                            { required: true, message: "钱包用户Id不能为空", trigger: "blur" }
                        ],
                                                walletType: [
                            { required: true, message: "钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)不能为空", trigger: "blur" }
                        ],
                                                walletAmount: [
                            { required: true, message: "钱包总额不能为空", trigger: "blur" }
                        ],
                                                password: [
                            { required: true, message: "密码不能为空", trigger: "blur" }
                        ],
                                                wechatAccount: [
                            { required: true, message: "微信账号不能为空", trigger: "blur" }
                        ],
                                                aliAccount: [
                            { required: true, message: "支付宝账号不能为空", trigger: "blur" }
                        ],
                                                wechatRealName: [
                            { required: true, message: "微信真实姓名不能为空", trigger: "blur" }
                        ],
                                                aliRealName: [
                            { required: true, message: "支付宝账号真实名称不能为空", trigger: "blur" }
                        ],
                                                bankAccount: [
                            { required: true, message: "银行卡号不能为空", trigger: "blur" }
                        ],
                                                bankAccountName: [
                            { required: true, message: "持卡人姓名不能为空", trigger: "blur" }
                        ],
                                                openAccountBank: [
                            { required: true, message: "开户行不能为空", trigger: "blur" }
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
                this.getPlatformWalletData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getPlatformWalletData(){
                console.log('getBanner请求参数{}',this.id);
                getPlatformWallet(this.id).then(response => {
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
                                                walletType: undefined,
                                                walletAmount: undefined,
                                                password: undefined,
                                                wechatAccount: undefined,
                                                aliAccount: undefined,
                                                wechatRealName: undefined,
                                                aliRealName: undefined,
                                                bankAccount: undefined,
                                                bankAccountName: undefined,
                                                openAccountBank: undefined,
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
                            this.addPlatformWalletData(this.form);
                        }else{
                            // 修改接口
                            this.updatePlatformWalletData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addPlatformWalletData(data){
                addPlatformWallet(data).then(response => {
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
            updatePlatformWalletData(data){
                updatePlatformWallet(data).then(response => {
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
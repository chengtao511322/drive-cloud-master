<template>
    <div class="app-container">

        <!-- 添加或修改提成设置表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="name">
                                <el-input v-model="form.name" placeholder="请输入" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户类型 1-个人，2-商铺,3 企业类型 4  学校类型" prop="userType">
                                <el-input v-model="form.userType" placeholder="请输入用户类型 1-个人，2-商铺,3 企业类型 4  学校类型" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="经理课时提成比例" prop="managerHourProportion">
                                <el-input v-model="form.managerHourProportion" placeholder="请输入经理课时提成比例" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="经理报名提成金额" prop="managerApplyAmount">
                                <el-input v-model="form.managerApplyAmount" placeholder="请输入经理报名提成金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="代理商课时提成比" prop="agencyHourProportion">
                                <el-input v-model="form.agencyHourProportion" placeholder="请输入代理商课时提成比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="代理商报名提成金额" prop="agencyApplyAmount">
                                <el-input v-model="form.agencyApplyAmount" placeholder="请输入代理商报名提成金额" />
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
                            <el-form-item label="渠道经理ID" prop="recommendManagerId">
                                <el-input v-model="form.recommendManagerId" placeholder="请输入渠道经理ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="代理商ID" prop="recommendAgencyId">
                                <el-input v-model="form.recommendAgencyId" placeholder="请输入代理商ID" />
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
    import { listDeductSetting,listPageDeductSetting, getDeductSetting, delDeductSetting, addDeductSetting, updateDeductSetting, exportDeductSetting } from "@/api/admin/deductSetting";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "DeductSetting",
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
                                                name: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                userType: [
                            { required: true, message: "用户类型 1-个人，2-商铺,3 企业类型 4  学校类型不能为空", trigger: "blur" }
                        ],
                                                managerHourProportion: [
                            { required: true, message: "经理课时提成比例不能为空", trigger: "blur" }
                        ],
                                                managerApplyAmount: [
                            { required: true, message: "经理报名提成金额不能为空", trigger: "blur" }
                        ],
                                                agencyHourProportion: [
                            { required: true, message: "代理商课时提成比不能为空", trigger: "blur" }
                        ],
                                                agencyApplyAmount: [
                            { required: true, message: "代理商报名提成金额不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                recommendManagerId: [
                            { required: true, message: "渠道经理ID不能为空", trigger: "blur" }
                        ],
                                                recommendAgencyId: [
                            { required: true, message: "代理商ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getDeductSettingData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getDeductSettingData(){
                console.log('getBanner请求参数{}',this.id);
                getDeductSetting(this.id).then(response => {
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
                                                name: undefined,
                                                userType: undefined,
                                                managerHourProportion: undefined,
                                                managerApplyAmount: undefined,
                                                agencyHourProportion: undefined,
                                                agencyApplyAmount: undefined,
                                                operatorId: undefined,
                                                recommendManagerId: undefined,
                                                recommendAgencyId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addDeductSettingData(this.form);
                        }else{
                            // 修改接口
                            this.updateDeductSettingData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addDeductSettingData(data){
                addDeductSetting(data).then(response => {
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
            updateDeductSettingData(data){
                updateDeductSetting(data).then(response => {
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
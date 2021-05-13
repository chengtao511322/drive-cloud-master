<template>
    <div class="app-container">

        <!-- 添加或修改运营商加盟申请对话框 -->
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
                            <el-form-item label="省" prop="provinceId">
                                <el-input v-model="form.provinceId" placeholder="请输入省" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="市" prop="cityId">
                                <el-input v-model="form.cityId" placeholder="请输入市" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="区" prop="areaId">
                                <el-input v-model="form.areaId" placeholder="请输入区" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="姓名" prop="realName">
                                <el-input v-model="form.realName" placeholder="请输入姓名" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="电话" prop="phone">
                                <el-input v-model="form.phone" placeholder="请输入电话" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="邮箱" prop="eMail">
                                <el-input v-model="form.eMail" placeholder="请输入邮箱" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="公司名称" prop="corporationName">
                                <el-input v-model="form.corporationName" placeholder="请输入公司名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="申请说明" prop="applyExplain">
                                <el-input v-model="form.applyExplain" placeholder="请输入申请说明" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否删除" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="注册时间" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择注册时间">
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
    import { listOperatorRecruit,listPageOperatorRecruit, getOperatorRecruit, delOperatorRecruit, addOperatorRecruit, updateOperatorRecruit, exportOperatorRecruit } from "@/api/admin/operatorRecruit";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "OperatorRecruit",
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
                                                provinceId: [
                            { required: true, message: "省不能为空", trigger: "blur" }
                        ],
                                                cityId: [
                            { required: true, message: "市不能为空", trigger: "blur" }
                        ],
                                                areaId: [
                            { required: true, message: "区不能为空", trigger: "blur" }
                        ],
                                                realName: [
                            { required: true, message: "姓名不能为空", trigger: "blur" }
                        ],
                                                phone: [
                            { required: true, message: "电话不能为空", trigger: "blur" }
                        ],
                                                eMail: [
                            { required: true, message: "邮箱不能为空", trigger: "blur" }
                        ],
                                                corporationName: [
                            { required: true, message: "公司名称不能为空", trigger: "blur" }
                        ],
                                                applyExplain: [
                            { required: true, message: "申请说明不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "注册时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getOperatorRecruitData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getOperatorRecruitData(){
                console.log('getBanner请求参数{}',this.id);
                getOperatorRecruit(this.id).then(response => {
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
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                realName: undefined,
                                                phone: undefined,
                                                eMail: undefined,
                                                corporationName: undefined,
                                                applyExplain: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addOperatorRecruitData(this.form);
                        }else{
                            // 修改接口
                            this.updateOperatorRecruitData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addOperatorRecruitData(data){
                addOperatorRecruit(data).then(response => {
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
            updateOperatorRecruitData(data){
                updateOperatorRecruit(data).then(response => {
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
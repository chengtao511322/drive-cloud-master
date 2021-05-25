<template>
    <div class="app-container">

        <!-- 添加或修改系统配置参数表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="参数枚举编号" prop="prmEnumId">
                                <el-input v-model="form.prmEnumId" placeholder="请输入参数枚举编号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="参数名称" prop="prmName">
                                <el-input v-model="form.prmName" placeholder="请输入参数名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="参数中文涵义" prop="prmCnName">
                                <el-input v-model="form.prmCnName" placeholder="请输入参数中文涵义" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="参数数值" prop="prmValue">
                                <el-input v-model="form.prmValue" placeholder="请输入参数数值" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="参数备注" prop="prmRemark">
                                <el-input v-model="form.prmRemark" placeholder="请输入参数备注" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="参数状态，0-正常 1-停用" prop="prmStatus">
                                <el-input v-model="form.prmStatus" placeholder="请输入参数状态，0-正常 1-停用" />
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
                            <el-form-item label="创建人" prop="createUser">
                                <el-input v-model="form.createUser" placeholder="请输入创建人" />
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
                            <el-form-item label="修改人" prop="updateUser">
                                <el-input v-model="form.updateUser" placeholder="请输入修改人" />
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
    import { listSysParam,listPageSysParam, getSysParam, delSysParam, addSysParam, updateSysParam, exportSysParam } from "@/api/admin/sysParam";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "SysParam",
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
                                                prmEnumId: [
                            { required: true, message: "参数枚举编号不能为空", trigger: "blur" }
                        ],
                                                prmName: [
                            { required: true, message: "参数名称不能为空", trigger: "blur" }
                        ],
                                                prmCnName: [
                            { required: true, message: "参数中文涵义不能为空", trigger: "blur" }
                        ],
                                                prmValue: [
                            { required: true, message: "参数数值不能为空", trigger: "blur" }
                        ],
                                                prmRemark: [
                            { required: true, message: "参数备注不能为空", trigger: "blur" }
                        ],
                                                prmStatus: [
                            { required: true, message: "参数状态，0-正常 1-停用不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建人不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "修改人不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getSysParamData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getSysParamData(){
                console.log('getBanner请求参数{}',this.id);
                getSysParam(this.id).then(response => {
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
                                                prmEnumId: undefined,
                                                prmName: undefined,
                                                prmCnName: undefined,
                                                prmValue: undefined,
                                                prmRemark: undefined,
                                                prmStatus: undefined,
                                                createTime: undefined,
                                                createUser: undefined,
                                                updateTime: undefined,
                                                updateUser: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addSysParamData(this.form);
                        }else{
                            // 修改接口
                            this.updateSysParamData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addSysParamData(data){
                addSysParam(data).then(response => {
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
            updateSysParamData(data){
                updateSysParam(data).then(response => {
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
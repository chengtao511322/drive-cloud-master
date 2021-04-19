<template>
    <div class="app-container">

        <!-- 添加或修改学员学车报名单对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="主键唯一id" prop="id">
                                <el-input v-model="form.id" placeholder="请输入主键唯一id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员ID" prop="studentId">
                                <el-input v-model="form.studentId" placeholder="请输入学员ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="科目类型" prop="testEnrollSubject">
                                <el-input v-model="form.testEnrollSubject" placeholder="请输入科目类型" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="考试时间" prop="testEnrollTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.testEnrollTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择考试时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="考试场地ID" prop="testCoachingGridId">
                                <el-input v-model="form.testCoachingGridId" placeholder="请输入考试场地ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）" prop="testResultType">
                                <el-input v-model="form.testResultType" placeholder="请输入考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="时间抽排序" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择时间抽排序">
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
    import { listStudentStudyProgressHistory,listPageStudentStudyProgressHistory, getStudentStudyProgressHistory, delStudentStudyProgressHistory, addStudentStudyProgressHistory, updateStudentStudyProgressHistory, exportStudentStudyProgressHistory } from "@/api/admin/studentStudyProgressHistory";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "StudentStudyProgressHistory",
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
                            { required: true, message: "主键唯一id不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员ID不能为空", trigger: "blur" }
                        ],
                                                testEnrollSubject: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                testEnrollTime: [
                            { required: true, message: "考试时间不能为空", trigger: "blur" }
                        ],
                                                testCoachingGridId: [
                            { required: true, message: "考试场地ID不能为空", trigger: "blur" }
                        ],
                                                testResultType: [
                            { required: true, message: "考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "时间抽排序不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getStudentStudyProgressHistoryData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getStudentStudyProgressHistoryData(){
                console.log('getBanner请求参数{}',this.id);
                getStudentStudyProgressHistory(this.id).then(response => {
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
                                                studentId: undefined,
                                                testEnrollSubject: undefined,
                                                testEnrollTime: undefined,
                                                testCoachingGridId: undefined,
                                                testResultType: undefined,
                                                createTime: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addStudentStudyProgressHistoryData(this.form);
                        }else{
                            // 修改接口
                            this.updateStudentStudyProgressHistoryData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addStudentStudyProgressHistoryData(data){
                addStudentStudyProgressHistory(data).then(response => {
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
            updateStudentStudyProgressHistoryData(data){
                updateStudentStudyProgressHistory(data).then(response => {
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
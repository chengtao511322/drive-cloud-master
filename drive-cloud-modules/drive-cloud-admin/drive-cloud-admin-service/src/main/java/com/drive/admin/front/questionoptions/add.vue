<template>
    <div class="app-container">

        <!-- 添加或修改平台题目选项对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="id">
                                <el-input v-model="form.id" placeholder="请输入" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="题库ID" prop="questionBankId">
                                <el-input v-model="form.questionBankId" placeholder="请输入题库ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="题目选项" prop="questionOption">
                                <el-input v-model="form.questionOption" placeholder="请输入题目选项" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="选项内容" prop="optionContent">
                                <el-input v-model="form.optionContent" placeholder="请输入选项内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否正确" prop="isRight">
                                <el-input v-model="form.isRight" placeholder="请输入是否正确" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="创建者" prop="createUser">
                                <el-input v-model="form.createUser" placeholder="请输入创建者" />
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
                            <el-form-item label="更新者" prop="updateUser">
                                <el-input v-model="form.updateUser" placeholder="请输入更新者" />
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
    import { listQuestionOptions,listPageQuestionOptions, getQuestionOptions, delQuestionOptions, addQuestionOptions, updateQuestionOptions, exportQuestionOptions } from "@/api/admin/questionOptions";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "QuestionOptions",
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
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                questionBankId: [
                            { required: true, message: "题库ID不能为空", trigger: "blur" }
                        ],
                                                questionOption: [
                            { required: true, message: "题目选项不能为空", trigger: "blur" }
                        ],
                                                optionContent: [
                            { required: true, message: "选项内容不能为空", trigger: "blur" }
                        ],
                                                isRight: [
                            { required: true, message: "是否正确不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "更新者不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getQuestionOptionsData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getQuestionOptionsData(){
                console.log('getBanner请求参数{}',this.id);
                getQuestionOptions(this.id).then(response => {
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
                                                questionBankId: undefined,
                                                questionOption: undefined,
                                                optionContent: undefined,
                                                isRight: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
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
                            this.addQuestionOptionsData(this.form);
                        }else{
                            // 修改接口
                            this.updateQuestionOptionsData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addQuestionOptionsData(data){
                addQuestionOptions(data).then(response => {
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
            updateQuestionOptionsData(data){
                updateQuestionOptions(data).then(response => {
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
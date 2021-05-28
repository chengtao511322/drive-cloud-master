<template>
    <div class="app-container">

        <!-- 添加或修改平台题库表对话框 -->
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
                            <el-form-item label="题号" prop="questionNo">
                                <el-input v-model="form.questionNo" placeholder="请输入题号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="科目类型" prop="subjectType">
                                <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="题目章节" prop="questionChapter">
                                <el-input v-model="form.questionChapter" placeholder="请输入题目章节" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="题型（知识点类型）" prop="questionType">
                                <el-input v-model="form.questionType" placeholder="请输入题型（知识点类型）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="媒介路径（图片或者视频）" prop="mediaUrl">
                                <el-input v-model="form.mediaUrl" placeholder="请输入媒介路径（图片或者视频）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="题目内容" prop="questionContent">
                                <el-input v-model="form.questionContent" placeholder="请输入题目内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否多选" prop="isMultipleAnswer">
                                <el-input v-model="form.isMultipleAnswer" placeholder="请输入是否多选" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="答题限时（单位秒）" prop="answerTimeLimit">
                                <el-input v-model="form.answerTimeLimit" placeholder="请输入答题限时（单位秒）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="解答说明" prop="answerRemarks">
                                <el-input v-model="form.answerRemarks" placeholder="请输入解答说明" />
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
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="难度(1,2,3,4,5)" prop="difficulty">
                                <el-input v-model="form.difficulty" placeholder="请输入难度(1,2,3,4,5)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="媒介类型(1-图片，2-视频)" prop="isView">
                                <el-input v-model="form.isView" placeholder="请输入媒介类型(1-图片，2-视频)" />
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
    import { listQuestionBank,listPageQuestionBank, getQuestionBank, delQuestionBank, addQuestionBank, updateQuestionBank, exportQuestionBank } from "@/api/admin/questionBank";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "QuestionBank",
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
                                                questionNo: [
                            { required: true, message: "题号不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                questionChapter: [
                            { required: true, message: "题目章节不能为空", trigger: "blur" }
                        ],
                                                questionType: [
                            { required: true, message: "题型（知识点类型）不能为空", trigger: "blur" }
                        ],
                                                mediaUrl: [
                            { required: true, message: "媒介路径（图片或者视频）不能为空", trigger: "blur" }
                        ],
                                                questionContent: [
                            { required: true, message: "题目内容不能为空", trigger: "blur" }
                        ],
                                                isMultipleAnswer: [
                            { required: true, message: "是否多选不能为空", trigger: "blur" }
                        ],
                                                answerTimeLimit: [
                            { required: true, message: "答题限时（单位秒）不能为空", trigger: "blur" }
                        ],
                                                answerRemarks: [
                            { required: true, message: "解答说明不能为空", trigger: "blur" }
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
                                                difficulty: [
                            { required: true, message: "难度(1,2,3,4,5)不能为空", trigger: "blur" }
                        ],
                                                isView: [
                            { required: true, message: "媒介类型(1-图片，2-视频)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getQuestionBankData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getQuestionBankData(){
                console.log('getBanner请求参数{}',this.id);
                getQuestionBank(this.id).then(response => {
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
                                                questionNo: undefined,
                                                subjectType: undefined,
                                                questionChapter: undefined,
                                                questionType: undefined,
                                                mediaUrl: undefined,
                                                questionContent: undefined,
                                                isMultipleAnswer: undefined,
                                                answerTimeLimit: undefined,
                                                answerRemarks: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
                                                difficulty: undefined,
                                                isView: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addQuestionBankData(this.form);
                        }else{
                            // 修改接口
                            this.updateQuestionBankData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addQuestionBankData(data){
                addQuestionBank(data).then(response => {
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
            updateQuestionBankData(data){
                updateQuestionBank(data).then(response => {
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
<template>
    <div class="app-container">

        <!-- 添加或修改学员教练互评表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="编号" prop="id">
                                <el-input v-model="form.id" placeholder="请输入编号" />
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
                            <el-form-item label="教练课程ID" prop="classId">
                                <el-input v-model="form.classId" placeholder="请输入教练课程ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="报名单号（考试，学车）" prop="enrollNo">
                                <el-input v-model="form.enrollNo" placeholder="请输入报名单号（考试，学车）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="评价用户id（学员id）" prop="studentId">
                                <el-input v-model="form.studentId" placeholder="请输入评价用户id（学员id）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员评价内容" prop="appraiseReplyContent">
                                <el-input v-model="form.appraiseReplyContent" placeholder="请输入学员评价内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员评价分数" prop="appraiseReplyGrade">
                                <el-input v-model="form.appraiseReplyGrade" placeholder="请输入学员评价分数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员评价时间" prop="appraiseReplyTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.appraiseReplyTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择学员评价时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="回复内容" prop="replyContent">
                                <el-input v-model="form.replyContent" placeholder="请输入回复内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="回复时间" prop="replyTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.replyTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择回复时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="被评价用户id" prop="replyId">
                                <el-input v-model="form.replyId" placeholder="请输入被评价用户id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="被评价用户类型（2-教练；3-运维）" prop="replyType">
                                <el-input v-model="form.replyType" placeholder="请输入被评价用户类型（2-教练；3-运维）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="被评价用户头像" prop="replyHeadUrl">
                                <el-input v-model="form.replyHeadUrl" placeholder="请输入被评价用户头像" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员头像" prop="studentHeadUrl">
                                <el-input v-model="form.studentHeadUrl" placeholder="请输入学员头像" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员评价类型（1-评价 2-投诉）" prop="appraiseType">
                                <el-input v-model="form.appraiseType" placeholder="请输入学员评价类型（1-评价 2-投诉）" />
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
    import { listStudentCoachAppraise,listPageStudentCoachAppraise, getStudentCoachAppraise, delStudentCoachAppraise, addStudentCoachAppraise, updateStudentCoachAppraise, exportStudentCoachAppraise } from "@/api/admin/studentCoachAppraise";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "StudentCoachAppraise",
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
                            { required: true, message: "编号不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                classId: [
                            { required: true, message: "教练课程ID不能为空", trigger: "blur" }
                        ],
                                                enrollNo: [
                            { required: true, message: "报名单号（考试，学车）不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "评价用户id（学员id）不能为空", trigger: "blur" }
                        ],
                                                appraiseReplyContent: [
                            { required: true, message: "学员评价内容不能为空", trigger: "blur" }
                        ],
                                                appraiseReplyGrade: [
                            { required: true, message: "学员评价分数不能为空", trigger: "blur" }
                        ],
                                                appraiseReplyTime: [
                            { required: true, message: "学员评价时间不能为空", trigger: "blur" }
                        ],
                                                replyContent: [
                            { required: true, message: "回复内容不能为空", trigger: "blur" }
                        ],
                                                replyTime: [
                            { required: true, message: "回复时间不能为空", trigger: "blur" }
                        ],
                                                replyId: [
                            { required: true, message: "被评价用户id不能为空", trigger: "blur" }
                        ],
                                                replyType: [
                            { required: true, message: "被评价用户类型（2-教练；3-运维）不能为空", trigger: "blur" }
                        ],
                                                replyHeadUrl: [
                            { required: true, message: "被评价用户头像不能为空", trigger: "blur" }
                        ],
                                                studentHeadUrl: [
                            { required: true, message: "学员头像不能为空", trigger: "blur" }
                        ],
                                                appraiseType: [
                            { required: true, message: "学员评价类型（1-评价 2-投诉）不能为空", trigger: "blur" }
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
                this.getStudentCoachAppraiseData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getStudentCoachAppraiseData(){
                console.log('getBanner请求参数{}',this.id);
                getStudentCoachAppraise(this.id).then(response => {
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
                                                classId: undefined,
                                                enrollNo: undefined,
                                                studentId: undefined,
                                                appraiseReplyContent: undefined,
                                                appraiseReplyGrade: undefined,
                                                appraiseReplyTime: undefined,
                                                replyContent: undefined,
                                                replyTime: undefined,
                                                replyId: undefined,
                                                replyType: undefined,
                                                replyHeadUrl: undefined,
                                                studentHeadUrl: undefined,
                                                appraiseType: undefined,
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
                            this.addStudentCoachAppraiseData(this.form);
                        }else{
                            // 修改接口
                            this.updateStudentCoachAppraiseData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addStudentCoachAppraiseData(data){
                addStudentCoachAppraise(data).then(response => {
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
            updateStudentCoachAppraiseData(data){
                updateStudentCoachAppraise(data).then(response => {
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
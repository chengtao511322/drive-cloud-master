<template>
    <div class="app-container">

        <!-- 添加或修改论坛帖子回复表对话框 -->
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
                            <el-form-item label="父id(用于记录用户评价回复的内容)" prop="superId">
                                <el-input v-model="form.superId" placeholder="请输入父id(用于记录用户评价回复的内容)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="论坛帖子表id" prop="invitationId">
                                <el-input v-model="form.invitationId" placeholder="请输入论坛帖子表id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户id" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入用户id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户操作状态(1-已发布，2-已删除，3-内容违规)" prop="userOperateStatus">
                                <el-input v-model="form.userOperateStatus" placeholder="请输入用户操作状态(1-已发布，2-已删除，3-内容违规)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="状态(1：正常；2：停用)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态(1：正常；2：停用)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="文本内容" prop="textContent">
                                <el-input v-model="form.textContent" placeholder="请输入文本内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="图片路径" prop="imgUrl">
                                <el-input v-model="form.imgUrl" placeholder="请输入图片路径" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="视频路径" prop="videoUrl">
                                <el-input v-model="form.videoUrl" placeholder="请输入视频路径" />
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
                            <el-form-item label="删除状态 1  未删除  2 已经删除" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入删除状态 1  未删除  2 已经删除" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="和谐前内容" prop="rawTextContent">
                                <el-input v-model="form.rawTextContent" placeholder="请输入和谐前内容" />
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
    import { listInvitationReply,listPageInvitationReply, getInvitationReply, delInvitationReply, addInvitationReply, updateInvitationReply, exportInvitationReply } from "@/api/bbs/invitationReply";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "InvitationReply",
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
                                                superId: [
                            { required: true, message: "父id(用于记录用户评价回复的内容)不能为空", trigger: "blur" }
                        ],
                                                invitationId: [
                            { required: true, message: "论坛帖子表id不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "用户id不能为空", trigger: "blur" }
                        ],
                                                userOperateStatus: [
                            { required: true, message: "用户操作状态(1-已发布，2-已删除，3-内容违规)不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1：正常；2：停用)不能为空", trigger: "blur" }
                        ],
                                                textContent: [
                            { required: true, message: "文本内容不能为空", trigger: "blur" }
                        ],
                                                imgUrl: [
                            { required: true, message: "图片路径不能为空", trigger: "blur" }
                        ],
                                                videoUrl: [
                            { required: true, message: "视频路径不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                replyTime: [
                            { required: true, message: "回复时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 1  未删除  2 已经删除不能为空", trigger: "blur" }
                        ],
                                                rawTextContent: [
                            { required: true, message: "和谐前内容不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getInvitationReplyData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getInvitationReplyData(){
                console.log('getBanner请求参数{}',this.id);
                getInvitationReply(this.id).then(response => {
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
                                                superId: undefined,
                                                invitationId: undefined,
                                                userId: undefined,
                                                userOperateStatus: undefined,
                                                status: undefined,
                                                textContent: undefined,
                                                imgUrl: undefined,
                                                videoUrl: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                replyTime: undefined,
                                                isDelete: undefined,
                                                rawTextContent: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addInvitationReplyData(this.form);
                        }else{
                            // 修改接口
                            this.updateInvitationReplyData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addInvitationReplyData(data){
                addInvitationReply(data).then(response => {
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
            updateInvitationReplyData(data){
                updateInvitationReply(data).then(response => {
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
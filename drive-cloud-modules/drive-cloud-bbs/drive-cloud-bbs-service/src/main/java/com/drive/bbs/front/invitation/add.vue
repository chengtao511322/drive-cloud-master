<template>
    <div class="app-container">

        <!-- 添加或修改论坛帖子表对话框 -->
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
                            <el-form-item label="视频内容" prop="videoUrl">
                                <el-input v-model="form.videoUrl" placeholder="请输入视频内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户ID" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入用户ID" />
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
                            <el-form-item label="当前位置" prop="address">
                                <el-input v-model="form.address" placeholder="请输入当前位置" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="点赞次数" prop="praiseSize">
                                <el-input v-model="form.praiseSize" placeholder="请输入点赞次数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="收藏次数" prop="collectionSize">
                                <el-input v-model="form.collectionSize" placeholder="请输入收藏次数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="浏览次数" prop="browseSize">
                                <el-input v-model="form.browseSize" placeholder="请输入浏览次数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="发布时间" prop="releaseTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.releaseTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择发布时间">
                                </el-date-picker>
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
                            <el-form-item label="状态(1：正常；2：停用)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态(1：正常；2：停用)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="删除状态 0 未删除  1 已经删除" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入删除状态 0 未删除  1 已经删除" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="评论数" prop="commentSize">
                                <el-input v-model="form.commentSize" placeholder="请输入评论数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏" prop="isGive">
                                <el-input v-model="form.isGive" placeholder="请输入是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否屏蔽  1 " prop="isShield">
                                <el-input v-model="form.isShield" placeholder="请输入是否屏蔽  1 " />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="首次发帖标识 1 首次 2 不是" prop="isInitialIssue">
                                <el-input v-model="form.isInitialIssue" placeholder="请输入首次发帖标识 1 首次 2 不是" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="和谐前内容（原内容）" prop="rawTextContent">
                                <el-input v-model="form.rawTextContent" placeholder="请输入和谐前内容（原内容）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="isTop">
                                <el-input v-model="form.isTop" placeholder="请输入" />
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
    import { listInvitation,listPageInvitation, getInvitation, delInvitation, addInvitation, updateInvitation, exportInvitation } from "@/api/bbs/invitation";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "Invitation",
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
                                                textContent: [
                            { required: true, message: "文本内容不能为空", trigger: "blur" }
                        ],
                                                imgUrl: [
                            { required: true, message: "图片路径不能为空", trigger: "blur" }
                        ],
                                                videoUrl: [
                            { required: true, message: "视频内容不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "用户ID不能为空", trigger: "blur" }
                        ],
                                                userOperateStatus: [
                            { required: true, message: "用户操作状态(1-已发布，2-已删除，3-内容违规)不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "当前位置不能为空", trigger: "blur" }
                        ],
                                                praiseSize: [
                            { required: true, message: "点赞次数不能为空", trigger: "blur" }
                        ],
                                                collectionSize: [
                            { required: true, message: "收藏次数不能为空", trigger: "blur" }
                        ],
                                                browseSize: [
                            { required: true, message: "浏览次数不能为空", trigger: "blur" }
                        ],
                                                releaseTime: [
                            { required: true, message: "发布时间不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1：正常；2：停用)不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 0 未删除  1 已经删除不能为空", trigger: "blur" }
                        ],
                                                commentSize: [
                            { required: true, message: "评论数不能为空", trigger: "blur" }
                        ],
                                                isGive: [
                            { required: true, message: "是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏不能为空", trigger: "blur" }
                        ],
                                                isShield: [
                            { required: true, message: "是否屏蔽  1 不能为空", trigger: "blur" }
                        ],
                                                isInitialIssue: [
                            { required: true, message: "首次发帖标识 1 首次 2 不是不能为空", trigger: "blur" }
                        ],
                                                rawTextContent: [
                            { required: true, message: "和谐前内容（原内容）不能为空", trigger: "blur" }
                        ],
                                                isTop: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getInvitationData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getInvitationData(){
                console.log('getBanner请求参数{}',this.id);
                getInvitation(this.id).then(response => {
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
                                                textContent: undefined,
                                                imgUrl: undefined,
                                                videoUrl: undefined,
                                                userId: undefined,
                                                userOperateStatus: undefined,
                                                address: undefined,
                                                praiseSize: undefined,
                                                collectionSize: undefined,
                                                browseSize: undefined,
                                                releaseTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                commentSize: undefined,
                                                isGive: undefined,
                                                isShield: undefined,
                                                isInitialIssue: undefined,
                                                rawTextContent: undefined,
                                                isTop: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addInvitationData(this.form);
                        }else{
                            // 修改接口
                            this.updateInvitationData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addInvitationData(data){
                addInvitation(data).then(response => {
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
            updateInvitationData(data){
                updateInvitation(data).then(response => {
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
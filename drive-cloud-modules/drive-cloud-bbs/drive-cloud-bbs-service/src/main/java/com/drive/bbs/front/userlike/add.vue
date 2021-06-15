<template>
    <div class="app-container">

        <!-- 添加或修改用户点赞表对话框 -->
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
                            <el-form-item label="被点赞的用户id" prop="likedUserId">
                                <el-input v-model="form.likedUserId" placeholder="请输入被点赞的用户id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="点赞的用户id" prop="likedPostId">
                                <el-input v-model="form.likedPostId" placeholder="请输入点赞的用户id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="点赞状态，0取消，1点赞" prop="status">
                                <el-input v-model="form.status" placeholder="请输入点赞状态，0取消，1点赞" />
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
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="点赞时间" prop="likedTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.likedTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择点赞时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="帖子ID" prop="invitationId">
                                <el-input v-model="form.invitationId" placeholder="请输入帖子ID" />
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
    import { listUserLike,listPageUserLike, getUserLike, delUserLike, addUserLike, updateUserLike, exportUserLike } from "@/api/bbs/userLike";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "UserLike",
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
                                                likedUserId: [
                            { required: true, message: "被点赞的用户id不能为空", trigger: "blur" }
                        ],
                                                likedPostId: [
                            { required: true, message: "点赞的用户id不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "点赞状态，0取消，1点赞不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                likedTime: [
                            { required: true, message: "点赞时间不能为空", trigger: "blur" }
                        ],
                                                invitationId: [
                            { required: true, message: "帖子ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getUserLikeData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getUserLikeData(){
                console.log('getBanner请求参数{}',this.id);
                getUserLike(this.id).then(response => {
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
                                                likedUserId: undefined,
                                                likedPostId: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                likedTime: undefined,
                                                invitationId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addUserLikeData(this.form);
                        }else{
                            // 修改接口
                            this.updateUserLikeData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addUserLikeData(data){
                addUserLike(data).then(response => {
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
            updateUserLikeData(data){
                updateUserLike(data).then(response => {
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
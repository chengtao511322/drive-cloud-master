<template>
    <div class="app-container">

        <!-- 添加或修改流程信息管理对话框 -->
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
                            <el-form-item label="标题" prop="title">
                                <el-input v-model="form.title" placeholder="请输入标题" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="context">
                                <el-input v-model="form.context" placeholder="请输入" />
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
                            <el-form-item label="删除状态 0未删除  1 ：删除" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入删除状态 0未删除  1 ：删除" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="启用状态 0  未启用 1 已经启用" prop="status">
                                <el-input v-model="form.status" placeholder="请输入启用状态 0  未启用 1 已经启用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="栏目ID" prop="channelId">
                                <el-input v-model="form.channelId" placeholder="请输入栏目ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="排序" prop="sort">
                                <el-input v-model="form.sort" placeholder="请输入排序" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="添加者" prop="addUser">
                                <el-input v-model="form.addUser" placeholder="请输入添加者" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="运营位置 ，存放省市区编码，多个用逗号隔开" prop="operatingPosition">
                                <el-input v-model="form.operatingPosition" placeholder="请输入运营位置 ，存放省市区编码，多个用逗号隔开" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="租户ID" prop="tenantId">
                                <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
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
    import { listDriveFlow,listPageDriveFlow, getDriveFlow, delDriveFlow, addDriveFlow, updateDriveFlow, exportDriveFlow } from "@/api/basics/driveFlow";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "DriveFlow",
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
                                                title: [
                            { required: true, message: "标题不能为空", trigger: "blur" }
                        ],
                                                context: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 0未删除  1 ：删除不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "启用状态 0  未启用 1 已经启用不能为空", trigger: "blur" }
                        ],
                                                channelId: [
                            { required: true, message: "栏目ID不能为空", trigger: "blur" }
                        ],
                                                sort: [
                            { required: true, message: "排序不能为空", trigger: "blur" }
                        ],
                                                addUser: [
                            { required: true, message: "添加者不能为空", trigger: "blur" }
                        ],
                                                operatingPosition: [
                            { required: true, message: "运营位置 ，存放省市区编码，多个用逗号隔开不能为空", trigger: "blur" }
                        ],
                                                tenantId: [
                            { required: true, message: "租户ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getDriveFlowData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getDriveFlowData(){
                console.log('getBanner请求参数{}',this.id);
                getDriveFlow(this.id).then(response => {
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
                                                title: undefined,
                                                context: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                                status: undefined,
                                                channelId: undefined,
                                                sort: undefined,
                                                addUser: undefined,
                                                operatingPosition: undefined,
                                                tenantId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addDriveFlowData(this.form);
                        }else{
                            // 修改接口
                            this.updateDriveFlowData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addDriveFlowData(data){
                addDriveFlow(data).then(response => {
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
            updateDriveFlowData(data){
                updateDriveFlow(data).then(response => {
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
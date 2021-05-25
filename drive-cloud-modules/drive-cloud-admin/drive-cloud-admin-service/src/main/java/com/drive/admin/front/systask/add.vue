<template>
    <div class="app-container">

        <!-- 添加或修改系统任务表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="任务编号" prop="stTaskId">
                                <el-input v-model="form.stTaskId" placeholder="请输入任务编号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="任务名称" prop="stTaskName">
                                <el-input v-model="form.stTaskName" placeholder="请输入任务名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="任务类" prop="stJobClass">
                                <el-input v-model="form.stJobClass" placeholder="请输入任务类" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="CRON表达式" prop="stCronExpression">
                                <el-input v-model="form.stCronExpression" placeholder="请输入CRON表达式" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="状态" prop="stState">
                                <el-input v-model="form.stState" placeholder="请输入状态" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="记入日志类型" prop="stInputLogType">
                                <el-input v-model="form.stInputLogType" placeholder="请输入记入日志类型" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="备注" prop="stRemark">
                                <el-input v-model="form.stRemark" placeholder="请输入备注" />
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
    import { listSysTask,listPageSysTask, getSysTask, delSysTask, addSysTask, updateSysTask, exportSysTask } from "@/api/admin/sysTask";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "SysTask",
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
                                                stTaskId: [
                            { required: true, message: "任务编号不能为空", trigger: "blur" }
                        ],
                                                stTaskName: [
                            { required: true, message: "任务名称不能为空", trigger: "blur" }
                        ],
                                                stJobClass: [
                            { required: true, message: "任务类不能为空", trigger: "blur" }
                        ],
                                                stCronExpression: [
                            { required: true, message: "CRON表达式不能为空", trigger: "blur" }
                        ],
                                                stState: [
                            { required: true, message: "状态不能为空", trigger: "blur" }
                        ],
                                                stInputLogType: [
                            { required: true, message: "记入日志类型不能为空", trigger: "blur" }
                        ],
                                                stRemark: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建人不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
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
                this.getSysTaskData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getSysTaskData(){
                console.log('getBanner请求参数{}',this.id);
                getSysTask(this.id).then(response => {
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
                                                stTaskId: undefined,
                                                stTaskName: undefined,
                                                stJobClass: undefined,
                                                stCronExpression: undefined,
                                                stState: undefined,
                                                stInputLogType: undefined,
                                                stRemark: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
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
                            this.addSysTaskData(this.form);
                        }else{
                            // 修改接口
                            this.updateSysTaskData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addSysTaskData(data){
                addSysTask(data).then(response => {
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
            updateSysTaskData(data){
                updateSysTask(data).then(response => {
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
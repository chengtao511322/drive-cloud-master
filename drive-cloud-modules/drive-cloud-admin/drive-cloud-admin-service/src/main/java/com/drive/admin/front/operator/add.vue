<template>
    <div class="app-container">

        <!-- 添加或修改运营商基础信息对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="主键" prop="id">
                                <el-input v-model="form.id" placeholder="请输入主键" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="运营商名称" prop="name">
                                <el-input v-model="form.name" placeholder="请输入运营商名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="运营商级别(1-省，2-市，3-区(县))" prop="level">
                                <el-input v-model="form.level" placeholder="请输入运营商级别(1-省，2-市，3-区(县))" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台提成百分比" prop="platformChargePercent">
                                <el-input v-model="form.platformChargePercent" placeholder="请输入平台提成百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否删除" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
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
                            <el-form-item label="使用状态（1：正常；2：停用）" prop="status">
                                <el-input v-model="form.status" placeholder="请输入使用状态（1：正常；2：停用）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="父级代理商id" prop="parentId">
                                <el-input v-model="form.parentId" placeholder="请输入父级代理商id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="分配课时绩效比例" prop="hourPerformanceRatio">
                                <el-input v-model="form.hourPerformanceRatio" placeholder="请输入分配课时绩效比例" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="其他benaid" prop="channelBeanId">
                                <el-input v-model="form.channelBeanId" placeholder="请输入其他benaid" />
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
    import { listOperator,listPageOperator, getOperator, delOperator, addOperator, updateOperator, exportOperator } from "@/api/admin/operator";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "Operator",
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
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "运营商名称不能为空", trigger: "blur" }
                        ],
                                                level: [
                            { required: true, message: "运营商级别(1-省，2-市，3-区(县))不能为空", trigger: "blur" }
                        ],
                                                platformChargePercent: [
                            { required: true, message: "平台提成百分比不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "使用状态（1：正常；2：停用）不能为空", trigger: "blur" }
                        ],
                                                parentId: [
                            { required: true, message: "父级代理商id不能为空", trigger: "blur" }
                        ],
                                                hourPerformanceRatio: [
                            { required: true, message: "分配课时绩效比例不能为空", trigger: "blur" }
                        ],
                                                channelBeanId: [
                            { required: true, message: "其他benaid不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getOperatorData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getOperatorData(){
                console.log('getBanner请求参数{}',this.id);
                getOperator(this.id).then(response => {
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
                                                name: undefined,
                                                level: undefined,
                                                platformChargePercent: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                status: undefined,
                                                parentId: undefined,
                                                hourPerformanceRatio: undefined,
                                                channelBeanId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addOperatorData(this.form);
                        }else{
                            // 修改接口
                            this.updateOperatorData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addOperatorData(data){
                addOperator(data).then(response => {
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
            updateOperatorData(data){
                updateOperator(data).then(response => {
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
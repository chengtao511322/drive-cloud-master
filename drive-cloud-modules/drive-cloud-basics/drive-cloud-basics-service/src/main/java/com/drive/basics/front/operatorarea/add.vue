<template>
    <div class="app-container">

        <!-- 添加或修改运营商代理区域对话框 -->
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
                            <el-form-item label="代理商id" prop="operatorId">
                                <el-input v-model="form.operatorId" placeholder="请输入代理商id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="省级编码" prop="provinceId">
                                <el-input v-model="form.provinceId" placeholder="请输入省级编码" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="市级编码" prop="cityId">
                                <el-input v-model="form.cityId" placeholder="请输入市级编码" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="区(县)级编码" prop="areaId">
                                <el-input v-model="form.areaId" placeholder="请输入区(县)级编码" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否向上查询（价格,教练在当前区划查询不到时）" prop="isUpSelect">
                                <el-input v-model="form.isUpSelect" placeholder="请输入是否向上查询（价格,教练在当前区划查询不到时）" />
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
    import { listOperatorArea,listPageOperatorArea, getOperatorArea, delOperatorArea, addOperatorArea, updateOperatorArea, exportOperatorArea } from "@/api/basics/operatorArea";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "OperatorArea",
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
                                                operatorId: [
                            { required: true, message: "代理商id不能为空", trigger: "blur" }
                        ],
                                                provinceId: [
                            { required: true, message: "省级编码不能为空", trigger: "blur" }
                        ],
                                                cityId: [
                            { required: true, message: "市级编码不能为空", trigger: "blur" }
                        ],
                                                areaId: [
                            { required: true, message: "区(县)级编码不能为空", trigger: "blur" }
                        ],
                                                isUpSelect: [
                            { required: true, message: "是否向上查询（价格,教练在当前区划查询不到时）不能为空", trigger: "blur" }
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
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getOperatorAreaData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getOperatorAreaData(){
                console.log('getBanner请求参数{}',this.id);
                getOperatorArea(this.id).then(response => {
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
                                                operatorId: undefined,
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                isUpSelect: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
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
                            this.addOperatorAreaData(this.form);
                        }else{
                            // 修改接口
                            this.updateOperatorAreaData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addOperatorAreaData(data){
                addOperatorArea(data).then(response => {
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
            updateOperatorAreaData(data){
                updateOperatorArea(data).then(response => {
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
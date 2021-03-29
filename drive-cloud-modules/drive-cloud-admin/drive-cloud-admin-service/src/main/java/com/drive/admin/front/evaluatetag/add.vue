<template>
    <div class="app-container">

        <!-- 添加或修改评价标签表对话框 -->
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
                            <el-form-item label="标签名称" prop="name">
                                <el-input v-model="form.name" placeholder="请输入标签名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="状态(1-正常，2-停用)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态(1-正常，2-停用)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" prop="orderType">
                                <el-input v-model="form.orderType" placeholder="请输入订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="评价分段（1-好评（45星），2-中评（3星），3-差评（12星））" prop="fractionParagraph">
                                <el-input v-model="form.fractionParagraph" placeholder="请输入评价分段（1-好评（45星），2-中评（3星），3-差评（12星））" />
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
                            <el-form-item label="是否删除(0否；1：是)" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入是否删除(0否；1：是)" />
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
    import { listEvaluateTag,listPageEvaluateTag, getEvaluateTag, delEvaluateTag, addEvaluateTag, updateEvaluateTag, exportEvaluateTag } from "@/api/admin/evaluateTag";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "EvaluateTag",
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
                                                name: [
                            { required: true, message: "标签名称不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1-正常，2-停用)不能为空", trigger: "blur" }
                        ],
                                                orderType: [
                            { required: true, message: "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）不能为空", trigger: "blur" }
                        ],
                                                fractionParagraph: [
                            { required: true, message: "评价分段（1-好评（45星），2-中评（3星），3-差评（12星））不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除(0否；1：是)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getEvaluateTagData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getEvaluateTagData(){
                console.log('getBanner请求参数{}',this.id);
                getEvaluateTag(this.id).then(response => {
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
                                                status: undefined,
                                                orderType: undefined,
                                                fractionParagraph: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addEvaluateTagData(this.form);
                        }else{
                            // 修改接口
                            this.updateEvaluateTagData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addEvaluateTagData(data){
                addEvaluateTag(data).then(response => {
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
            updateEvaluateTagData(data){
                updateEvaluateTag(data).then(response => {
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
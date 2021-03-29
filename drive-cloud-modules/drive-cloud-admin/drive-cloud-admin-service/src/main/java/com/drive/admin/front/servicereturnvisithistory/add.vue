<template>
    <div class="app-container">

        <!-- 添加或修改客服回访记录对话框 -->
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
                            <el-form-item label="回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" prop="returnVisitType">
                                <el-input v-model="form.returnVisitType" placeholder="请输入回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单明细单号" prop="orderDetailNo">
                                <el-input v-model="form.orderDetailNo" placeholder="请输入订单明细单号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员id" prop="studentId">
                                <el-input v-model="form.studentId" placeholder="请输入学员id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="回访时间" prop="returnVisitTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.returnVisitTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择回访时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="回访内容" prop="returnVisitContent">
                                <el-input v-model="form.returnVisitContent" placeholder="请输入回访内容" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="预计下次回访时间" prop="nextReturnVisitTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.nextReturnVisitTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择预计下次回访时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="客服id" prop="serviceId">
                                <el-input v-model="form.serviceId" placeholder="请输入客服id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否完结（是，否）" prop="isEnd">
                                <el-input v-model="form.isEnd" placeholder="请输入是否完结（是，否）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="科目类型(1-科目一，2-科目三，3-科目三，4-科目四)" prop="subjectType">
                                <el-input v-model="form.subjectType" placeholder="请输入科目类型(1-科目一，2-科目三，3-科目三，4-科目四)" />
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
    import { listServiceReturnVisitHistory,listPageServiceReturnVisitHistory, getServiceReturnVisitHistory, delServiceReturnVisitHistory, addServiceReturnVisitHistory, updateServiceReturnVisitHistory, exportServiceReturnVisitHistory } from "@/api/admin/serviceReturnVisitHistory";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "ServiceReturnVisitHistory",
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
                                                returnVisitType: [
                            { required: true, message: "回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）不能为空", trigger: "blur" }
                        ],
                                                orderDetailNo: [
                            { required: true, message: "订单明细单号不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员id不能为空", trigger: "blur" }
                        ],
                                                returnVisitTime: [
                            { required: true, message: "回访时间不能为空", trigger: "blur" }
                        ],
                                                returnVisitContent: [
                            { required: true, message: "回访内容不能为空", trigger: "blur" }
                        ],
                                                nextReturnVisitTime: [
                            { required: true, message: "预计下次回访时间不能为空", trigger: "blur" }
                        ],
                                                serviceId: [
                            { required: true, message: "客服id不能为空", trigger: "blur" }
                        ],
                                                isEnd: [
                            { required: true, message: "是否完结（是，否）不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型(1-科目一，2-科目三，3-科目三，4-科目四)不能为空", trigger: "blur" }
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
                this.getServiceReturnVisitHistoryData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getServiceReturnVisitHistoryData(){
                console.log('getBanner请求参数{}',this.id);
                getServiceReturnVisitHistory(this.id).then(response => {
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
                                                returnVisitType: undefined,
                                                orderDetailNo: undefined,
                                                studentId: undefined,
                                                returnVisitTime: undefined,
                                                returnVisitContent: undefined,
                                                nextReturnVisitTime: undefined,
                                                serviceId: undefined,
                                                isEnd: undefined,
                                                subjectType: undefined,
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
                            this.addServiceReturnVisitHistoryData(this.form);
                        }else{
                            // 修改接口
                            this.updateServiceReturnVisitHistoryData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addServiceReturnVisitHistoryData(data){
                addServiceReturnVisitHistory(data).then(response => {
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
            updateServiceReturnVisitHistoryData(data){
                updateServiceReturnVisitHistory(data).then(response => {
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
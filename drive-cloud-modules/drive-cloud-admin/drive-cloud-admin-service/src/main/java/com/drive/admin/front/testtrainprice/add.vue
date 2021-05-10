<template>
    <div class="app-container">

        <!-- 添加或修改平台报名考试练车单价表对话框 -->
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
                            <el-form-item label="省" prop="provinceId">
                                <el-input v-model="form.provinceId" placeholder="请输入省" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="市" prop="cityId">
                                <el-input v-model="form.cityId" placeholder="请输入市" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="区" prop="areaId">
                                <el-input v-model="form.areaId" placeholder="请输入区" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="驾照类型（c1；c2...）" prop="driveType">
                                <el-input v-model="form.driveType" placeholder="请输入驾照类型（c1；c2...）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="科目类型（一；二；三；四）" prop="subjectType">
                                <el-input v-model="form.subjectType" placeholder="请输入科目类型（一；二；三；四）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)" prop="priceType">
                                <el-input v-model="form.priceType" placeholder="请输入价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="单价" prop="price">
                                <el-input v-model="form.price" placeholder="请输入单价" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="详情（报名所需的费用明细介绍）" prop="remarks">
                                <el-input v-model="form.remarks" placeholder="请输入详情（报名所需的费用明细介绍）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="创建者" prop="createUser">
                                <el-input v-model="form.createUser" placeholder="请输入创建者" />
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
                            <el-form-item label="更新者" prop="updateUser">
                                <el-input v-model="form.updateUser" placeholder="请输入更新者" />
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
    import { listTestTrainPrice,listPageTestTrainPrice, getTestTrainPrice, delTestTrainPrice, addTestTrainPrice, updateTestTrainPrice, exportTestTrainPrice } from "@/api/admin/testTrainPrice";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "TestTrainPrice",
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
                                                provinceId: [
                            { required: true, message: "省不能为空", trigger: "blur" }
                        ],
                                                cityId: [
                            { required: true, message: "市不能为空", trigger: "blur" }
                        ],
                                                areaId: [
                            { required: true, message: "区不能为空", trigger: "blur" }
                        ],
                                                driveType: [
                            { required: true, message: "驾照类型（c1；c2...）不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型（一；二；三；四）不能为空", trigger: "blur" }
                        ],
                                                priceType: [
                            { required: true, message: "价格类型(1-学车报名，2-考试报名，3-常规练车，4-考试练车,5-推广商推荐报名提成金额,6-推广商课时提成百分比,7-推广商推荐新用户金额,8-新用户填写邀请码收益金额,9-首次推荐新用户奖励金额)不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "单价不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "详情（报名所需的费用明细介绍）不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "更新者不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
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
                this.getTestTrainPriceData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getTestTrainPriceData(){
                console.log('getBanner请求参数{}',this.id);
                getTestTrainPrice(this.id).then(response => {
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
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                driveType: undefined,
                                                subjectType: undefined,
                                                priceType: undefined,
                                                price: undefined,
                                                remarks: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
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
                            this.addTestTrainPriceData(this.form);
                        }else{
                            // 修改接口
                            this.updateTestTrainPriceData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addTestTrainPriceData(data){
                addTestTrainPrice(data).then(response => {
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
            updateTestTrainPriceData(data){
                updateTestTrainPrice(data).then(response => {
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
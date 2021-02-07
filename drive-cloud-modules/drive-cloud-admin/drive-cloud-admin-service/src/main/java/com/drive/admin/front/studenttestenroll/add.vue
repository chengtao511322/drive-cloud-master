<template>
    <div class="app-container">

        <!-- 添加或修改学员考试报名表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="考试报名单号" prop="testEnrollNo">
                                <el-input v-model="form.testEnrollNo" placeholder="请输入考试报名单号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员ID" prop="studentId">
                                <el-input v-model="form.studentId" placeholder="请输入学员ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际考试场地-省" prop="provinceId">
                                <el-input v-model="form.provinceId" placeholder="请输入实际考试场地-省" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际考试场地-市" prop="cityId">
                                <el-input v-model="form.cityId" placeholder="请输入实际考试场地-市" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际考试场地-区" prop="areaId">
                                <el-input v-model="form.areaId" placeholder="请输入实际考试场地-区" />
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
                            <el-form-item label="科目类型" prop="subjectType">
                                <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="报名单价" prop="price">
                                <el-input v-model="form.price" placeholder="请输入报名单价" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台服务费用" prop="serviceCharge">
                                <el-input v-model="form.serviceCharge" placeholder="请输入平台服务费用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）" prop="enrollStatus">
                                <el-input v-model="form.enrollStatus" placeholder="请输入报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="希望预约考试时间" prop="testHopeTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.testHopeTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择希望预约考试时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际考试时间" prop="testActualTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.testActualTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择实际考试时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="希望预约考试场地ID" prop="testHopeCoachingGridId">
                                <el-input v-model="form.testHopeCoachingGridId" placeholder="请输入希望预约考试场地ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际预约考试场地ID" prop="testActualCoachingGridId">
                                <el-input v-model="form.testActualCoachingGridId" placeholder="请输入实际预约考试场地ID" />
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
                            <el-form-item label="客服ID（系统用户ID）" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入客服ID（系统用户ID）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="线下客服id(考试接送客服)" prop="lineUnderUserId">
                                <el-input v-model="form.lineUnderUserId" placeholder="请输入线下客服id(考试接送客服)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否选择接送" prop="isMeetGive">
                                <el-input v-model="form.isMeetGive" placeholder="请输入是否选择接送" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="接送费用" prop="meetGicePrice">
                                <el-input v-model="form.meetGicePrice" placeholder="请输入接送费用" />
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
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" prop="examType">
                                <el-input v-model="form.examType" placeholder="请输入考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" />
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
    import { listStudentTestEnroll,listPageStudentTestEnroll, getStudentTestEnroll, delStudentTestEnroll, addStudentTestEnroll, updateStudentTestEnroll, exportStudentTestEnroll } from "@/api/admin/studentTestEnroll";
    import { Utils } from "@joyo-shared/utils";
    export default {
        name: "StudentTestEnroll",
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
                                                testEnrollNo: [
                            { required: true, message: "考试报名单号不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员ID不能为空", trigger: "blur" }
                        ],
                                                provinceId: [
                            { required: true, message: "实际考试场地-省不能为空", trigger: "blur" }
                        ],
                                                cityId: [
                            { required: true, message: "实际考试场地-市不能为空", trigger: "blur" }
                        ],
                                                areaId: [
                            { required: true, message: "实际考试场地-区不能为空", trigger: "blur" }
                        ],
                                                driveType: [
                            { required: true, message: "驾照类型（c1；c2...）不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "报名单价不能为空", trigger: "blur" }
                        ],
                                                serviceCharge: [
                            { required: true, message: "平台服务费用不能为空", trigger: "blur" }
                        ],
                                                enrollStatus: [
                            { required: true, message: "报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）不能为空", trigger: "blur" }
                        ],
                                                testHopeTime: [
                            { required: true, message: "希望预约考试时间不能为空", trigger: "blur" }
                        ],
                                                testActualTime: [
                            { required: true, message: "实际考试时间不能为空", trigger: "blur" }
                        ],
                                                testHopeCoachingGridId: [
                            { required: true, message: "希望预约考试场地ID不能为空", trigger: "blur" }
                        ],
                                                testActualCoachingGridId: [
                            { required: true, message: "实际预约考试场地ID不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "客服ID（系统用户ID）不能为空", trigger: "blur" }
                        ],
                                                lineUnderUserId: [
                            { required: true, message: "线下客服id(考试接送客服)不能为空", trigger: "blur" }
                        ],
                                                isMeetGive: [
                            { required: true, message: "是否选择接送不能为空", trigger: "blur" }
                        ],
                                                meetGicePrice: [
                            { required: true, message: "接送费用不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                examType: [
                            { required: true, message: "考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getStudentTestEnrollData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getStudentTestEnrollData(){
                console.log('getBanner请求参数{}',this.id);
                getStudentTestEnroll(this.id).then(response => {
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
                                                testEnrollNo: undefined,
                                                studentId: undefined,
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                driveType: undefined,
                                                subjectType: undefined,
                                                price: undefined,
                                                serviceCharge: undefined,
                                                enrollStatus: undefined,
                                                testHopeTime: undefined,
                                                testActualTime: undefined,
                                                testHopeCoachingGridId: undefined,
                                                testActualCoachingGridId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                userId: undefined,
                                                lineUnderUserId: undefined,
                                                isMeetGive: undefined,
                                                meetGicePrice: undefined,
                                                operatorId: undefined,
                                                examType: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addStudentTestEnrollData(this.form);
                        }else{
                            // 修改接口
                            this.updateStudentTestEnrollData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addStudentTestEnrollData(data){
                addStudentTestEnroll(data).then(response => {
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
            updateStudentTestEnrollData(data){
                updateStudentTestEnroll(data).then(response => {
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
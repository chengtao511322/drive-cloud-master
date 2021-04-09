<template>
    <div class="app-container">

        <!-- 添加或修改学员学车预约表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="预约单号" prop="trainApplyNo">
                                <el-input v-model="form.trainApplyNo" placeholder="请输入预约单号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="训练类型（1-常规练车；2-考试练车）" prop="trainType">
                                <el-input v-model="form.trainType" placeholder="请输入训练类型（1-常规练车；2-考试练车）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练课时ID" prop="classId">
                                <el-input v-model="form.classId" placeholder="请输入教练课时ID" />
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
                            <el-form-item label="单价" prop="price">
                                <el-input v-model="form.price" placeholder="请输入单价" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="总价格" prop="sumPrice">
                                <el-input v-model="form.sumPrice" placeholder="请输入总价格" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台服务费用百分比/H" prop="serviceChargePercent">
                                <el-input v-model="form.serviceChargePercent" placeholder="请输入平台服务费用百分比/H" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练课时费用百分比/H2" prop="coachChargePercent">
                                <el-input v-model="form.coachChargePercent" placeholder="请输入教练课时费用百分比/H2" />
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
                            <el-form-item label="教练课时费用" prop="coachCharge">
                                <el-input v-model="form.coachCharge" placeholder="请输入教练课时费用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练ID" prop="coachId">
                                <el-input v-model="form.coachId" placeholder="请输入教练ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="训练场地ID" prop="coachingGridId">
                                <el-input v-model="form.coachingGridId" placeholder="请输入训练场地ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="接你的时间" prop="takeTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.takeTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择接你的时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="上车时间" prop="aboardTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.aboardTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择上车时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="课程日期" prop="classDate">
                                <el-input v-model="form.classDate" placeholder="请输入课程日期" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="开始时间" prop="startTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.startTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择开始时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="结束时间" prop="endTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.endTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择结束时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学时" prop="classHours">
                                <el-input v-model="form.classHours" placeholder="请输入学时" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）" prop="applyStatus">
                                <el-input v-model="form.applyStatus" placeholder="请输入预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）" />
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
                            <el-form-item label="学员推荐用户类型（1-学员；2-教练；3-运维）" prop="studentRecommendUserType">
                                <el-input v-model="form.studentRecommendUserType" placeholder="请输入学员推荐用户类型（1-学员；2-教练；3-运维）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员推荐用户ID" prop="studentRecommendUserId">
                                <el-input v-model="form.studentRecommendUserId" placeholder="请输入学员推荐用户ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员推荐用户提成百分比" prop="studentRecommendUserChargePercent">
                                <el-input v-model="form.studentRecommendUserChargePercent" placeholder="请输入学员推荐用户提成百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员推荐用户提成费用" prop="studentRecommendUserCharge">
                                <el-input v-model="form.studentRecommendUserCharge" placeholder="请输入学员推荐用户提成费用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="学员常用地址id" prop="commonAddressId">
                                <el-input v-model="form.commonAddressId" placeholder="请输入学员常用地址id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练推荐用户类型（1-学员；2-教练；3-运营）" prop="coachRecommendUserType">
                                <el-input v-model="form.coachRecommendUserType" placeholder="请输入教练推荐用户类型（1-学员；2-教练；3-运营）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练推荐用户ID" prop="coachRecommendUserId">
                                <el-input v-model="form.coachRecommendUserId" placeholder="请输入教练推荐用户ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练推荐用户提成百分比" prop="coachRecommendUserChargePercent">
                                <el-input v-model="form.coachRecommendUserChargePercent" placeholder="请输入教练推荐用户提成百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练推荐用户提成费用" prop="coachRecommendUserCharge">
                                <el-input v-model="form.coachRecommendUserCharge" placeholder="请输入教练推荐用户提成费用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="取消时间" prop="cancelTiem">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.cancelTiem"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择取消时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="取消原因" prop="cancelReason">
                                <el-input v-model="form.cancelReason" placeholder="请输入取消原因" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="取消扣款百分比" prop="cancelWithholdPercent">
                                <el-input v-model="form.cancelWithholdPercent" placeholder="请输入取消扣款百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="取消扣款金额" prop="cancelWithholdMoney">
                                <el-input v-model="form.cancelWithholdMoney" placeholder="请输入取消扣款金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="取消退款金额" prop="cancelRefundMoney">
                                <el-input v-model="form.cancelRefundMoney" placeholder="请输入取消退款金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="考试训练用车类型（1-教练车；2-考试车）" prop="carType">
                                <el-input v-model="form.carType" placeholder="请输入考试训练用车类型（1-教练车；2-考试车）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否删除（0-否;1-是）" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入是否删除（0-否;1-是）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际开始时间" prop="actualStartTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.actualStartTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择实际开始时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="实际结束时间" prop="actualEndTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.actualEndTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择实际结束时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="课时点评" prop="classAppraise">
                                <el-input v-model="form.classAppraise" placeholder="请输入课时点评" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="公车提成费用" prop="publicCarPrice">
                                <el-input v-model="form.publicCarPrice" placeholder="请输入公车提成费用" />
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
    import { listStudentTrainCarApply,listPageStudentTrainCarApply, getStudentTrainCarApply, delStudentTrainCarApply, addStudentTrainCarApply, updateStudentTrainCarApply, exportStudentTrainCarApply } from "@/api/admin/studentTrainCarApply";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "StudentTrainCarApply",
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
                                                trainApplyNo: [
                            { required: true, message: "预约单号不能为空", trigger: "blur" }
                        ],
                                                trainType: [
                            { required: true, message: "训练类型（1-常规练车；2-考试练车）不能为空", trigger: "blur" }
                        ],
                                                classId: [
                            { required: true, message: "教练课时ID不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员ID不能为空", trigger: "blur" }
                        ],
                                                driveType: [
                            { required: true, message: "驾照类型（c1；c2...）不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "单价不能为空", trigger: "blur" }
                        ],
                                                sumPrice: [
                            { required: true, message: "总价格不能为空", trigger: "blur" }
                        ],
                                                serviceChargePercent: [
                            { required: true, message: "平台服务费用百分比/H不能为空", trigger: "blur" }
                        ],
                                                coachChargePercent: [
                            { required: true, message: "教练课时费用百分比/H2不能为空", trigger: "blur" }
                        ],
                                                serviceCharge: [
                            { required: true, message: "平台服务费用不能为空", trigger: "blur" }
                        ],
                                                coachCharge: [
                            { required: true, message: "教练课时费用不能为空", trigger: "blur" }
                        ],
                                                coachId: [
                            { required: true, message: "教练ID不能为空", trigger: "blur" }
                        ],
                                                coachingGridId: [
                            { required: true, message: "训练场地ID不能为空", trigger: "blur" }
                        ],
                                                takeTime: [
                            { required: true, message: "接你的时间不能为空", trigger: "blur" }
                        ],
                                                aboardTime: [
                            { required: true, message: "上车时间不能为空", trigger: "blur" }
                        ],
                                                classDate: [
                            { required: true, message: "课程日期不能为空", trigger: "blur" }
                        ],
                                                startTime: [
                            { required: true, message: "开始时间不能为空", trigger: "blur" }
                        ],
                                                endTime: [
                            { required: true, message: "结束时间不能为空", trigger: "blur" }
                        ],
                                                classHours: [
                            { required: true, message: "学时不能为空", trigger: "blur" }
                        ],
                                                applyStatus: [
                            { required: true, message: "预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                studentRecommendUserType: [
                            { required: true, message: "学员推荐用户类型（1-学员；2-教练；3-运维）不能为空", trigger: "blur" }
                        ],
                                                studentRecommendUserId: [
                            { required: true, message: "学员推荐用户ID不能为空", trigger: "blur" }
                        ],
                                                studentRecommendUserChargePercent: [
                            { required: true, message: "学员推荐用户提成百分比不能为空", trigger: "blur" }
                        ],
                                                studentRecommendUserCharge: [
                            { required: true, message: "学员推荐用户提成费用不能为空", trigger: "blur" }
                        ],
                                                commonAddressId: [
                            { required: true, message: "学员常用地址id不能为空", trigger: "blur" }
                        ],
                                                coachRecommendUserType: [
                            { required: true, message: "教练推荐用户类型（1-学员；2-教练；3-运营）不能为空", trigger: "blur" }
                        ],
                                                coachRecommendUserId: [
                            { required: true, message: "教练推荐用户ID不能为空", trigger: "blur" }
                        ],
                                                coachRecommendUserChargePercent: [
                            { required: true, message: "教练推荐用户提成百分比不能为空", trigger: "blur" }
                        ],
                                                coachRecommendUserCharge: [
                            { required: true, message: "教练推荐用户提成费用不能为空", trigger: "blur" }
                        ],
                                                cancelTiem: [
                            { required: true, message: "取消时间不能为空", trigger: "blur" }
                        ],
                                                cancelReason: [
                            { required: true, message: "取消原因不能为空", trigger: "blur" }
                        ],
                                                cancelWithholdPercent: [
                            { required: true, message: "取消扣款百分比不能为空", trigger: "blur" }
                        ],
                                                cancelWithholdMoney: [
                            { required: true, message: "取消扣款金额不能为空", trigger: "blur" }
                        ],
                                                cancelRefundMoney: [
                            { required: true, message: "取消退款金额不能为空", trigger: "blur" }
                        ],
                                                carType: [
                            { required: true, message: "考试训练用车类型（1-教练车；2-考试车）不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除（0-否;1-是）不能为空", trigger: "blur" }
                        ],
                                                actualStartTime: [
                            { required: true, message: "实际开始时间不能为空", trigger: "blur" }
                        ],
                                                actualEndTime: [
                            { required: true, message: "实际结束时间不能为空", trigger: "blur" }
                        ],
                                                classAppraise: [
                            { required: true, message: "课时点评不能为空", trigger: "blur" }
                        ],
                                                publicCarPrice: [
                            { required: true, message: "公车提成费用不能为空", trigger: "blur" }
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
                this.getStudentTrainCarApplyData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getStudentTrainCarApplyData(){
                console.log('getBanner请求参数{}',this.id);
                getStudentTrainCarApply(this.id).then(response => {
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
                                                trainApplyNo: undefined,
                                                trainType: undefined,
                                                classId: undefined,
                                                studentId: undefined,
                                                driveType: undefined,
                                                subjectType: undefined,
                                                price: undefined,
                                                sumPrice: undefined,
                                                serviceChargePercent: undefined,
                                                coachChargePercent: undefined,
                                                serviceCharge: undefined,
                                                coachCharge: undefined,
                                                coachId: undefined,
                                                coachingGridId: undefined,
                                                takeTime: undefined,
                                                aboardTime: undefined,
                                                classDate: undefined,
                                                startTime: undefined,
                                                endTime: undefined,
                                                classHours: undefined,
                                                applyStatus: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                studentRecommendUserType: undefined,
                                                studentRecommendUserId: undefined,
                                                studentRecommendUserChargePercent: undefined,
                                                studentRecommendUserCharge: undefined,
                                                commonAddressId: undefined,
                                                coachRecommendUserType: undefined,
                                                coachRecommendUserId: undefined,
                                                coachRecommendUserChargePercent: undefined,
                                                coachRecommendUserCharge: undefined,
                                                cancelTiem: undefined,
                                                cancelReason: undefined,
                                                cancelWithholdPercent: undefined,
                                                cancelWithholdMoney: undefined,
                                                cancelRefundMoney: undefined,
                                                carType: undefined,
                                                isDelete: undefined,
                                                actualStartTime: undefined,
                                                actualEndTime: undefined,
                                                classAppraise: undefined,
                                                publicCarPrice: undefined,
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
                            this.addStudentTrainCarApplyData(this.form);
                        }else{
                            // 修改接口
                            this.updateStudentTrainCarApplyData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addStudentTrainCarApplyData(data){
                addStudentTrainCarApply(data).then(response => {
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
            updateStudentTrainCarApplyData(data){
                updateStudentTrainCarApply(data).then(response => {
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
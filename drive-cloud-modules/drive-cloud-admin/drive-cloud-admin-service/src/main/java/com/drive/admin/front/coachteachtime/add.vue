<template>
    <div class="app-container">

        <!-- 添加或修改教练课程时间表对话框 -->
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
                            <el-form-item label="课程名称" prop="className">
                                <el-input v-model="form.className" placeholder="请输入课程名称" />
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
                            <el-form-item label="场地ID" prop="coachingGridId">
                                <el-input v-model="form.coachingGridId" placeholder="请输入场地ID" />
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
                            <el-form-item label="课时类型（1，普通训练，2考试训练）" prop="classType">
                                <el-input v-model="form.classType" placeholder="请输入课时类型（1，普通训练，2考试训练）" />
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
                            <el-form-item label="状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台提成百分比" prop="serviceChangePercent">
                                <el-input v-model="form.serviceChangePercent" placeholder="请输入平台提成百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练提成百分比" prop="coachChargePercent">
                                <el-input v-model="form.coachChargePercent" placeholder="请输入教练提成百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="公车提成百分比" prop="carChargePercent">
                                <el-input v-model="form.carChargePercent" placeholder="请输入公车提成百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="课时总费用" prop="sumCharge">
                                <el-input v-model="form.sumCharge" placeholder="请输入课时总费用" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台提成金额" prop="serviceCharge">
                                <el-input v-model="form.serviceCharge" placeholder="请输入平台提成金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="教练提成金额" prop="coaceCharge">
                                <el-input v-model="form.coaceCharge" placeholder="请输入教练提成金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="公车提成金额" prop="carCharge">
                                <el-input v-model="form.carCharge" placeholder="请输入公车提成金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="备注" prop="remarks">
                                <el-input v-model="form.remarks" placeholder="请输入备注" />
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
                            <el-form-item label="剩余课时" prop="surplusClassHours">
                                <el-input v-model="form.surplusClassHours" placeholder="请输入剩余课时" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="发布科目二场地ID" prop="coachingGridId2">
                                <el-input v-model="form.coachingGridId2" placeholder="请输入发布科目二场地ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="发布科目三场地ID" prop="coachingGridId3">
                                <el-input v-model="form.coachingGridId3" placeholder="请输入发布科目三场地ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)" prop="releaseSubjectType">
                                <el-input v-model="form.releaseSubjectType" placeholder="请输入发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="课时预扣百分比" prop="withholdPercent">
                                <el-input v-model="form.withholdPercent" placeholder="请输入课时预扣百分比" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="课时预扣金额" prop="withholdCharge">
                                <el-input v-model="form.withholdCharge" placeholder="请输入课时预扣金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)" prop="isHandleWithhole">
                                <el-input v-model="form.isHandleWithhole" placeholder="请输入是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)" />
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
                            <el-form-item label="是否为预收入(0-否，1-是)" prop="isExpect">
                                <el-input v-model="form.isExpect" placeholder="请输入是否为预收入(0-否，1-是)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="预收入班型id" prop="expectClassId">
                                <el-input v-model="form.expectClassId" placeholder="请输入预收入班型id" />
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
    import { listCoachTeachTime,listPageCoachTeachTime, getCoachTeachTime, delCoachTeachTime, addCoachTeachTime, updateCoachTeachTime, exportCoachTeachTime } from "@/api/admin/coachTeachTime";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "CoachTeachTime",
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
                                                className: [
                            { required: true, message: "课程名称不能为空", trigger: "blur" }
                        ],
                                                coachId: [
                            { required: true, message: "教练ID不能为空", trigger: "blur" }
                        ],
                                                coachingGridId: [
                            { required: true, message: "场地ID不能为空", trigger: "blur" }
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
                                                classType: [
                            { required: true, message: "课时类型（1，普通训练，2考试训练）不能为空", trigger: "blur" }
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
                                                status: [
                            { required: true, message: "状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)不能为空", trigger: "blur" }
                        ],
                                                serviceChangePercent: [
                            { required: true, message: "平台提成百分比不能为空", trigger: "blur" }
                        ],
                                                coachChargePercent: [
                            { required: true, message: "教练提成百分比不能为空", trigger: "blur" }
                        ],
                                                carChargePercent: [
                            { required: true, message: "公车提成百分比不能为空", trigger: "blur" }
                        ],
                                                sumCharge: [
                            { required: true, message: "课时总费用不能为空", trigger: "blur" }
                        ],
                                                serviceCharge: [
                            { required: true, message: "平台提成金额不能为空", trigger: "blur" }
                        ],
                                                coaceCharge: [
                            { required: true, message: "教练提成金额不能为空", trigger: "blur" }
                        ],
                                                carCharge: [
                            { required: true, message: "公车提成金额不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                aboardTime: [
                            { required: true, message: "上车时间不能为空", trigger: "blur" }
                        ],
                                                takeTime: [
                            { required: true, message: "接你的时间不能为空", trigger: "blur" }
                        ],
                                                actualStartTime: [
                            { required: true, message: "实际开始时间不能为空", trigger: "blur" }
                        ],
                                                actualEndTime: [
                            { required: true, message: "实际结束时间不能为空", trigger: "blur" }
                        ],
                                                surplusClassHours: [
                            { required: true, message: "剩余课时不能为空", trigger: "blur" }
                        ],
                                                coachingGridId2: [
                            { required: true, message: "发布科目二场地ID不能为空", trigger: "blur" }
                        ],
                                                coachingGridId3: [
                            { required: true, message: "发布科目三场地ID不能为空", trigger: "blur" }
                        ],
                                                releaseSubjectType: [
                            { required: true, message: "发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)不能为空", trigger: "blur" }
                        ],
                                                withholdPercent: [
                            { required: true, message: "课时预扣百分比不能为空", trigger: "blur" }
                        ],
                                                withholdCharge: [
                            { required: true, message: "课时预扣金额不能为空", trigger: "blur" }
                        ],
                                                isHandleWithhole: [
                            { required: true, message: "是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                isExpect: [
                            { required: true, message: "是否为预收入(0-否，1-是)不能为空", trigger: "blur" }
                        ],
                                                expectClassId: [
                            { required: true, message: "预收入班型id不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getCoachTeachTimeData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getCoachTeachTimeData(){
                console.log('getBanner请求参数{}',this.id);
                getCoachTeachTime(this.id).then(response => {
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
                                                className: undefined,
                                                coachId: undefined,
                                                coachingGridId: undefined,
                                                studentId: undefined,
                                                driveType: undefined,
                                                subjectType: undefined,
                                                classType: undefined,
                                                classDate: undefined,
                                                startTime: undefined,
                                                endTime: undefined,
                                                classHours: undefined,
                                                status: undefined,
                                                serviceChangePercent: undefined,
                                                coachChargePercent: undefined,
                                                carChargePercent: undefined,
                                                sumCharge: undefined,
                                                serviceCharge: undefined,
                                                coaceCharge: undefined,
                                                carCharge: undefined,
                                                remarks: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                aboardTime: undefined,
                                                takeTime: undefined,
                                                actualStartTime: undefined,
                                                actualEndTime: undefined,
                                                surplusClassHours: undefined,
                                                coachingGridId2: undefined,
                                                coachingGridId3: undefined,
                                                releaseSubjectType: undefined,
                                                withholdPercent: undefined,
                                                withholdCharge: undefined,
                                                isHandleWithhole: undefined,
                                                operatorId: undefined,
                                                isExpect: undefined,
                                                expectClassId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addCoachTeachTimeData(this.form);
                        }else{
                            // 修改接口
                            this.updateCoachTeachTimeData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addCoachTeachTimeData(data){
                addCoachTeachTime(data).then(response => {
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
            updateCoachTeachTimeData(data){
                updateCoachTeachTime(data).then(response => {
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
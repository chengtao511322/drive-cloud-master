<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    <el-form-item label="考试报名单号" prop="testEnrollNo">
                        <el-input
                                v-model="queryParams.testEnrollNo"
                                placeholder="请输入考试报名单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员ID" prop="studentId">
                        <el-input
                                v-model="queryParams.studentId"
                                placeholder="请输入学员ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="实际考试场地-省" prop="provinceId">
                        <el-input
                                v-model="queryParams.provinceId"
                                placeholder="请输入实际考试场地-省"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="实际考试场地-市" prop="cityId">
                        <el-input
                                v-model="queryParams.cityId"
                                placeholder="请输入实际考试场地-市"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="实际考试场地-区" prop="areaId">
                        <el-input
                                v-model="queryParams.areaId"
                                placeholder="请输入实际考试场地-区"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾照类型（c1；c2...）" prop="driveType">
                        <el-input
                                v-model="queryParams.driveType"
                                placeholder="请输入驾照类型（c1；c2...）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="科目类型" prop="subjectType">
                        <el-input
                                v-model="queryParams.subjectType"
                                placeholder="请输入科目类型"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）" prop="enrollStatus">
                        <el-input
                                v-model="queryParams.enrollStatus"
                                placeholder="请输入报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="希望预约考试时间" prop="testHopeTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.testHopeTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择希望预约考试时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="实际考试时间" prop="testActualTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.testActualTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择实际考试时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="希望预约考试场地ID" prop="testHopeCoachingGridId">
                        <el-input
                                v-model="queryParams.testHopeCoachingGridId"
                                placeholder="请输入希望预约考试场地ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="实际预约考试场地ID" prop="testActualCoachingGridId">
                        <el-input
                                v-model="queryParams.testActualCoachingGridId"
                                placeholder="请输入实际预约考试场地ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建时间" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择创建时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="更新时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择更新时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="客服ID（系统用户ID）" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入客服ID（系统用户ID）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="线下客服id(考试接送客服)" prop="lineUnderUserId">
                        <el-input
                                v-model="queryParams.lineUnderUserId"
                                placeholder="请输入线下客服id(考试接送客服)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否选择接送" prop="isMeetGive">
                        <el-input
                                v-model="queryParams.isMeetGive"
                                placeholder="请输入是否选择接送"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                        <el-input
                                v-model="queryParams.operatorId"
                                placeholder="请输入运营商id(数据权限标记)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                    <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleAdd"
                    v-hasPermi="['admin:studentTestEnroll:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:studentTestEnroll:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:studentTestEnroll:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:studentTestEnroll:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="studentTestEnrollList" @selection-change="handleSelectionChange"
                  @sort-change="sortChange"
                  ref="serviceTable"
                  stripe
                  border
                  header-row-class-name="table-header"
                  :header-row-style="{
                                        fontFamily: '微软雅黑 Bold, 微软雅黑 Regular, 微软雅黑'
                                     }"
        >
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="考试报名单号" align="center" prop="testEnrollNo" sortable="custom"/>
                                                                <el-table-column label="学员ID" align="center" prop="studentId" sortable="custom"/>
                                                                <el-table-column label="实际考试场地-省" align="center" prop="provinceId" sortable="custom"/>
                                                                <el-table-column label="实际考试场地-市" align="center" prop="cityId" sortable="custom"/>
                                                                <el-table-column label="实际考试场地-区" align="center" prop="areaId" sortable="custom"/>
                                                                <el-table-column label="驾照类型（c1；c2...）" align="center" prop="driveType" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="subjectType" sortable="custom"/>
                                                                <el-table-column label="报名单价" align="center" prop="price" sortable="custom"/>
                                                                <el-table-column label="平台服务费用" align="center" prop="serviceCharge" sortable="custom"/>
                                                                <el-table-column label="报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）" align="center" prop="enrollStatus" sortable="custom"/>
                                                                <el-table-column label="希望预约考试时间" align="center" prop="testHopeTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.testHopeTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="实际考试时间" align="center" prop="testActualTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.testActualTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="希望预约考试场地ID" align="center" prop="testHopeCoachingGridId" sortable="custom"/>
                                                                <el-table-column label="实际预约考试场地ID" align="center" prop="testActualCoachingGridId" sortable="custom"/>
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="更新时间" align="center" prop="updateTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="客服ID（系统用户ID）" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="线下客服id(考试接送客服)" align="center" prop="lineUnderUserId" sortable="custom"/>
                                                                <el-table-column label="是否选择接送" align="center" prop="isMeetGive" sortable="custom"/>
                                                                <el-table-column label="接送费用" align="center" prop="meetGicePrice" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" align="center" prop="examType" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:studentTestEnroll:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:studentTestEnroll:delete']"
                    >删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getListPage"
        />

        <!-- 添加或修改学员考试报名表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="考试报名单号" prop="testEnrollNo">
                            <el-input v-model="form.testEnrollNo" placeholder="请输入考试报名单号" />
                        </el-form-item>
                                                                                <el-form-item label="学员ID" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员ID" />
                        </el-form-item>
                                                                                <el-form-item label="实际考试场地-省" prop="provinceId">
                            <el-input v-model="form.provinceId" placeholder="请输入实际考试场地-省" />
                        </el-form-item>
                                                                                <el-form-item label="实际考试场地-市" prop="cityId">
                            <el-input v-model="form.cityId" placeholder="请输入实际考试场地-市" />
                        </el-form-item>
                                                                                <el-form-item label="实际考试场地-区" prop="areaId">
                            <el-input v-model="form.areaId" placeholder="请输入实际考试场地-区" />
                        </el-form-item>
                                                                                <el-form-item label="驾照类型（c1；c2...）" prop="driveType">
                            <el-input v-model="form.driveType" placeholder="请输入驾照类型（c1；c2...）" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型" prop="subjectType">
                            <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
                        </el-form-item>
                                                                                <el-form-item label="报名单价" prop="price">
                            <el-input v-model="form.price" placeholder="请输入报名单价" />
                        </el-form-item>
                                                                                <el-form-item label="平台服务费用" prop="serviceCharge">
                            <el-input v-model="form.serviceCharge" placeholder="请输入平台服务费用" />
                        </el-form-item>
                                                                                <el-form-item label="报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）" prop="enrollStatus">
                            <el-input v-model="form.enrollStatus" placeholder="请输入报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）" />
                        </el-form-item>
                                                                                <el-form-item label="希望预约考试时间" prop="testHopeTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.testHopeTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择希望预约考试时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="实际考试时间" prop="testActualTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.testActualTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择实际考试时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="希望预约考试场地ID" prop="testHopeCoachingGridId">
                            <el-input v-model="form.testHopeCoachingGridId" placeholder="请输入希望预约考试场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="实际预约考试场地ID" prop="testActualCoachingGridId">
                            <el-input v-model="form.testActualCoachingGridId" placeholder="请输入实际预约考试场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="更新时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择更新时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="客服ID（系统用户ID）" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入客服ID（系统用户ID）" />
                        </el-form-item>
                                                                                <el-form-item label="线下客服id(考试接送客服)" prop="lineUnderUserId">
                            <el-input v-model="form.lineUnderUserId" placeholder="请输入线下客服id(考试接送客服)" />
                        </el-form-item>
                                                                                <el-form-item label="是否选择接送" prop="isMeetGive">
                            <el-input v-model="form.isMeetGive" placeholder="请输入是否选择接送" />
                        </el-form-item>
                                                                                <el-form-item label="接送费用" prop="meetGicePrice">
                            <el-input v-model="form.meetGicePrice" placeholder="请输入接送费用" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" prop="examType">
                            <el-input v-model="form.examType" placeholder="请输入考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" />
                        </el-form-item>
                                                </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { listStudentTestEnroll,listPageStudentTestEnroll, getStudentTestEnroll, delStudentTestEnroll, addStudentTestEnroll, updateStudentTestEnroll, exportStudentTestEnroll } from "@/api/admin/studentTestEnroll";

    export default {
        name: "StudentTestEnroll",
        data() {
            return {
                // 遮罩层
                loading: true,
                // 选中数组
                ids: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 总条数
                total: 0,
                // 学员考试报名表表格数据
                    studentTestEnrollList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
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
                                    },
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
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询学员考试报名表列表 */
            getListPage() {
                this.loading = true;
                listPageStudentTestEnroll(this.queryParams).then(response => {
                    this.studentTestEnrollList = response.data.records;
                    this.total = response.data.total;
                    this.loading = false;
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
            // 后端排序排序
            sortChange(event) {
                console.log('sortChange', event )
                if(!event.order) {
                    this.queryParams.sortColumn ? delete this.queryParams.sortColumn : ''
                    this.queryParams.isAsc ? delete this.queryParams.isAsc : ''
                }
                this.queryParams.sortColumn = event.prop
                this.queryParams.isAsc = event.order === 'ascending' ? true : false
                this.handleQuery()
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getListPage();
            },
            /** 重置按钮操作 */
            resetQuery() {
                //第一版本
                //this.resetForm("queryForm");
                //this.handleQuery();

                this.queryParams.sortColumn ? delete this.queryParams.sortColumn : ''
                this.queryParams.isAsc ? delete this.queryParams.isAsc : ''
                this.$refs['serviceTable'].clearSort()
                this.resetForm("queryForm");
                this.handleQuery();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.testEnrollNo)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加学员考试报名表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const testEnrollNo = row.testEnrollNo || this.ids
                getStudentTestEnroll(testEnrollNo).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学员考试报名表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.testEnrollNo != undefined) {
                            updateStudentTestEnroll(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addStudentTestEnroll(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("新增成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const testEnrollNos = row.testEnrollNo || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delStudentTestEnroll(testEnrollNos);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学员考试报名表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportStudentTestEnroll(queryParams);
                }).then(data => {
                    this.download(data, "学员考试报名表");
                }).catch(function() {});
            }
        }
    };
</script>
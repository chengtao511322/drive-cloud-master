<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        <el-form-item label="主键" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入主键"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="教练id" prop="coachId">
                        <el-input
                                v-model="queryParams.coachId"
                                placeholder="请输入教练id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员id" prop="studentId">
                        <el-input
                                v-model="queryParams.studentId"
                                placeholder="请输入学员id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="订单号" prop="orderNo">
                        <el-input
                                v-model="queryParams.orderNo"
                                placeholder="请输入订单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="状态（正常，停用）" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态（正常，停用）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
）" prop="classType">
                        <el-input
                                v-model="queryParams.classType"
                                placeholder="请输入班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否删除" prop="isDelete">
                        <el-input
                                v-model="queryParams.isDelete"
                                placeholder="请输入是否删除"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="备注(主要存储，停用原因)" prop="remarks">
                        <el-input
                                v-model="queryParams.remarks"
                                placeholder="请输入备注(主要存储，停用原因)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="原班班型ID" prop="originalClassId">
                        <el-input
                                v-model="queryParams.originalClassId"
                                placeholder="请输入原班班型ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="现班班型ID" prop="classId">
                        <el-input
                                v-model="queryParams.classId"
                                placeholder="请输入现班班型ID"
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
                                                                                            <el-form-item label="绑定时间" prop="bindTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.bindTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择绑定时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                            <el-form-item label="解除绑定时间" prop="relieveBindTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.relieveBindTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择解除绑定时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="新绑定关联表id" prop="newBindId">
                        <el-input
                                v-model="queryParams.newBindId"
                                placeholder="请输入新绑定关联表id"
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
                    v-hasPermi="['admin:oneFeeSystemCoachStudent:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:oneFeeSystemCoachStudent:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:oneFeeSystemCoachStudent:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:oneFeeSystemCoachStudent:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="oneFeeSystemCoachStudentList" @selection-change="handleSelectionChange"
                  @sort-change="sortChange"
                  stripe
                  border
                  header-row-class-name="table-header"
                  :header-row-style="{
                                        fontFamily: '微软雅黑 Bold, 微软雅黑 Regular, 微软雅黑'
                                     }"
        >
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="主键" align="center" prop="id" />
                                                                <el-table-column label="教练id" align="center" prop="coachId" />
                                                                <el-table-column label="学员id" align="center" prop="studentId" />
                                                                <el-table-column label="订单号" align="center" prop="orderNo" />
                                                                <el-table-column label="状态（正常，停用）" align="center" prop="status" />
                                                                <el-table-column label="班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
）" align="center" prop="classType" />
                                                                <el-table-column label="是否删除" align="center" prop="isDelete" />
                                                                <el-table-column label="备注(主要存储，停用原因)" align="center" prop="remarks" />
                                                                <el-table-column label="原班班型ID" align="center" prop="originalClassId" />
                                                                <el-table-column label="现班班型ID" align="center" prop="classId" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
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
                                                                <el-table-column label="驾照类型" align="center" prop="driveType" />
                                                                <el-table-column label="绑定时间" align="center" prop="bindTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.bindTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)" align="center" prop="bindStatus" />
                                                                <el-table-column label="解除绑定时间" align="center" prop="relieveBindTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.relieveBindTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="新绑定关联表id" align="center" prop="newBindId" />
                                                                <el-table-column label="科目一免费考试次数" align="center" prop="subject1CostFreeNumber" />
                                                                <el-table-column label="科目二免费考试次数" align="center" prop="subject2CostFreeNumber" />
                                                                <el-table-column label="科目三免费考试次数" align="center" prop="subject3CostFreeNumber" />
                                                                <el-table-column label="科目四免费考试次数" align="center" prop="subject4CostFreeNumber" />
                                                                <el-table-column label="支付状态 (1-待支付, 2-已经支付)" align="center" prop="payStatus" />
                                                                <el-table-column label="科目二教练提成金额" align="center" prop="coachSubjectType2" />
                                                                <el-table-column label="科目二教练预收入金额" align="center" prop="coachSubject2ExpectIncome" />
                                                                <el-table-column label="科目三教练提成金额" align="center" prop="coachSubjectType3" />
                                                                <el-table-column label="科目三教练预收入金额" align="center" prop="coachSubject3ExpectIncome" />
                                                                <el-table-column label="科目二驾校提成金额" align="center" prop="schoolSubjectType2" />
                                                                <el-table-column label="科目三驾校提成金额" align="center" prop="schoolSubjectType3" />
                                                                <el-table-column label="运营商提成金额" align="center" prop="operatorChangeMoney" />
                                                                <el-table-column label="奖金（学员拿证后，教练获得的奖金）" align="center" prop="bonus" />
                                                                <el-table-column label="挂科扣款金额" align="center" prop="testNotPassWithholdMoney" />
                                                                <el-table-column label="平台提成金额（所有上级运营商提成金）" align="center" prop="serviceChangeMoney" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:oneFeeSystemCoachStudent:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:oneFeeSystemCoachStudent:delete']"
                    >删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
        />

        <!-- 添加或修改一费制学员教练关联表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键" />
                        </el-form-item>
                                                                                <el-form-item label="教练id" prop="coachId">
                            <el-input v-model="form.coachId" placeholder="请输入教练id" />
                        </el-form-item>
                                                                                <el-form-item label="学员id" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员id" />
                        </el-form-item>
                                                                                <el-form-item label="订单号" prop="orderNo">
                            <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                        </el-form-item>
                                                                                <el-form-item label="状态（正常，停用）" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态（正常，停用）" />
                        </el-form-item>
                                                                                <el-form-item label="班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
）" prop="classType">
                            <el-input v-model="form.classType" placeholder="请输入班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
）" />
                        </el-form-item>
                                                                                <el-form-item label="是否删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
                        </el-form-item>
                                                                                <el-form-item label="备注(主要存储，停用原因)" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入备注(主要存储，停用原因)" />
                        </el-form-item>
                                                                                <el-form-item label="原班班型ID" prop="originalClassId">
                            <el-input v-model="form.originalClassId" placeholder="请输入原班班型ID" />
                        </el-form-item>
                                                                                <el-form-item label="现班班型ID" prop="classId">
                            <el-input v-model="form.classId" placeholder="请输入现班班型ID" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
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
                                                                                <el-form-item label="驾照类型" prop="driveType">
                            <el-input v-model="form.driveType" placeholder="请输入驾照类型" />
                        </el-form-item>
                                                                                <el-form-item label="绑定时间" prop="bindTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.bindTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择绑定时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)" prop="bindStatus">
                            <el-input v-model="form.bindStatus" placeholder="请输入绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)" />
                        </el-form-item>
                                                                                <el-form-item label="解除绑定时间" prop="relieveBindTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.relieveBindTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择解除绑定时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="新绑定关联表id" prop="newBindId">
                            <el-input v-model="form.newBindId" placeholder="请输入新绑定关联表id" />
                        </el-form-item>
                                                                                <el-form-item label="科目一免费考试次数" prop="subject1CostFreeNumber">
                            <el-input v-model="form.subject1CostFreeNumber" placeholder="请输入科目一免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="科目二免费考试次数" prop="subject2CostFreeNumber">
                            <el-input v-model="form.subject2CostFreeNumber" placeholder="请输入科目二免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="科目三免费考试次数" prop="subject3CostFreeNumber">
                            <el-input v-model="form.subject3CostFreeNumber" placeholder="请输入科目三免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="科目四免费考试次数" prop="subject4CostFreeNumber">
                            <el-input v-model="form.subject4CostFreeNumber" placeholder="请输入科目四免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="支付状态 (1-待支付, 2-已经支付)" prop="payStatus">
                            <el-input v-model="form.payStatus" placeholder="请输入支付状态 (1-待支付, 2-已经支付)" />
                        </el-form-item>
                                                                                <el-form-item label="科目二教练提成金额" prop="coachSubjectType2">
                            <el-input v-model="form.coachSubjectType2" placeholder="请输入科目二教练提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目二教练预收入金额" prop="coachSubject2ExpectIncome">
                            <el-input v-model="form.coachSubject2ExpectIncome" placeholder="请输入科目二教练预收入金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目三教练提成金额" prop="coachSubjectType3">
                            <el-input v-model="form.coachSubjectType3" placeholder="请输入科目三教练提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目三教练预收入金额" prop="coachSubject3ExpectIncome">
                            <el-input v-model="form.coachSubject3ExpectIncome" placeholder="请输入科目三教练预收入金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目二驾校提成金额" prop="schoolSubjectType2">
                            <el-input v-model="form.schoolSubjectType2" placeholder="请输入科目二驾校提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目三驾校提成金额" prop="schoolSubjectType3">
                            <el-input v-model="form.schoolSubjectType3" placeholder="请输入科目三驾校提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="运营商提成金额" prop="operatorChangeMoney">
                            <el-input v-model="form.operatorChangeMoney" placeholder="请输入运营商提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="奖金（学员拿证后，教练获得的奖金）" prop="bonus">
                            <el-input v-model="form.bonus" placeholder="请输入奖金（学员拿证后，教练获得的奖金）" />
                        </el-form-item>
                                                                                <el-form-item label="挂科扣款金额" prop="testNotPassWithholdMoney">
                            <el-input v-model="form.testNotPassWithholdMoney" placeholder="请输入挂科扣款金额" />
                        </el-form-item>
                                                                                <el-form-item label="平台提成金额（所有上级运营商提成金）" prop="serviceChangeMoney">
                            <el-input v-model="form.serviceChangeMoney" placeholder="请输入平台提成金额（所有上级运营商提成金）" />
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
    import { listOneFeeSystemCoachStudent,listPageOneFeeSystemCoachStudent, getOneFeeSystemCoachStudent, delOneFeeSystemCoachStudent, addOneFeeSystemCoachStudent, updateOneFeeSystemCoachStudent, exportOneFeeSystemCoachStudent } from "@/api/admin/oneFeeSystemCoachStudent";

    export default {
        name: "OneFeeSystemCoachStudent",
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
                // 一费制学员教练关联表表格数据
                    oneFeeSystemCoachStudentList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                coachId: undefined,
                                                studentId: undefined,
                                                orderNo: undefined,
                                                status: undefined,
                                                classType: undefined,
                                                isDelete: undefined,
                                                remarks: undefined,
                                                originalClassId: undefined,
                                                classId: undefined,
                                                operatorId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                driveType: undefined,
                                                bindTime: undefined,
                                                bindStatus: undefined,
                                                relieveBindTime: undefined,
                                                newBindId: undefined,
                                                subject1CostFreeNumber: undefined,
                                                subject2CostFreeNumber: undefined,
                                                subject3CostFreeNumber: undefined,
                                                subject4CostFreeNumber: undefined,
                                                payStatus: undefined,
                                                coachSubjectType2: undefined,
                                                coachSubject2ExpectIncome: undefined,
                                                coachSubjectType3: undefined,
                                                coachSubject3ExpectIncome: undefined,
                                                schoolSubjectType2: undefined,
                                                schoolSubjectType3: undefined,
                                                operatorChangeMoney: undefined,
                                                bonus: undefined,
                                                testNotPassWithholdMoney: undefined,
                                                serviceChangeMoney: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                coachId: [
                            { required: true, message: "教练id不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员id不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态（正常，停用）不能为空", trigger: "blur" }
                        ],
                                                classType: [
                            { required: true, message: "班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
）不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注(主要存储，停用原因)不能为空", trigger: "blur" }
                        ],
                                                originalClassId: [
                            { required: true, message: "原班班型ID不能为空", trigger: "blur" }
                        ],
                                                classId: [
                            { required: true, message: "现班班型ID不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                driveType: [
                            { required: true, message: "驾照类型不能为空", trigger: "blur" }
                        ],
                                                bindTime: [
                            { required: true, message: "绑定时间不能为空", trigger: "blur" }
                        ],
                                                bindStatus: [
                            { required: true, message: "绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)不能为空", trigger: "blur" }
                        ],
                                                relieveBindTime: [
                            { required: true, message: "解除绑定时间不能为空", trigger: "blur" }
                        ],
                                                newBindId: [
                            { required: true, message: "新绑定关联表id不能为空", trigger: "blur" }
                        ],
                                                subject1CostFreeNumber: [
                            { required: true, message: "科目一免费考试次数不能为空", trigger: "blur" }
                        ],
                                                subject2CostFreeNumber: [
                            { required: true, message: "科目二免费考试次数不能为空", trigger: "blur" }
                        ],
                                                subject3CostFreeNumber: [
                            { required: true, message: "科目三免费考试次数不能为空", trigger: "blur" }
                        ],
                                                subject4CostFreeNumber: [
                            { required: true, message: "科目四免费考试次数不能为空", trigger: "blur" }
                        ],
                                                payStatus: [
                            { required: true, message: "支付状态 (1-待支付, 2-已经支付)不能为空", trigger: "blur" }
                        ],
                                                coachSubjectType2: [
                            { required: true, message: "科目二教练提成金额不能为空", trigger: "blur" }
                        ],
                                                coachSubject2ExpectIncome: [
                            { required: true, message: "科目二教练预收入金额不能为空", trigger: "blur" }
                        ],
                                                coachSubjectType3: [
                            { required: true, message: "科目三教练提成金额不能为空", trigger: "blur" }
                        ],
                                                coachSubject3ExpectIncome: [
                            { required: true, message: "科目三教练预收入金额不能为空", trigger: "blur" }
                        ],
                                                schoolSubjectType2: [
                            { required: true, message: "科目二驾校提成金额不能为空", trigger: "blur" }
                        ],
                                                schoolSubjectType3: [
                            { required: true, message: "科目三驾校提成金额不能为空", trigger: "blur" }
                        ],
                                                operatorChangeMoney: [
                            { required: true, message: "运营商提成金额不能为空", trigger: "blur" }
                        ],
                                                bonus: [
                            { required: true, message: "奖金（学员拿证后，教练获得的奖金）不能为空", trigger: "blur" }
                        ],
                                                testNotPassWithholdMoney: [
                            { required: true, message: "挂科扣款金额不能为空", trigger: "blur" }
                        ],
                                                serviceChangeMoney: [
                            { required: true, message: "平台提成金额（所有上级运营商提成金）不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询一费制学员教练关联表列表 */
            getListPage() {
                this.loading = true;
                listPageOneFeeSystemCoachStudent(this.queryParams).then(response => {
                    this.oneFeeSystemCoachStudentList = response.data.records;
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
                                                id: undefined,
                                                coachId: undefined,
                                                studentId: undefined,
                                                orderNo: undefined,
                                                status: undefined,
                                                classType: undefined,
                                                isDelete: undefined,
                                                remarks: undefined,
                                                originalClassId: undefined,
                                                classId: undefined,
                                                operatorId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                driveType: undefined,
                                                bindTime: undefined,
                                                bindStatus: undefined,
                                                relieveBindTime: undefined,
                                                newBindId: undefined,
                                                subject1CostFreeNumber: undefined,
                                                subject2CostFreeNumber: undefined,
                                                subject3CostFreeNumber: undefined,
                                                subject4CostFreeNumber: undefined,
                                                payStatus: undefined,
                                                coachSubjectType2: undefined,
                                                coachSubject2ExpectIncome: undefined,
                                                coachSubjectType3: undefined,
                                                coachSubject3ExpectIncome: undefined,
                                                schoolSubjectType2: undefined,
                                                schoolSubjectType3: undefined,
                                                operatorChangeMoney: undefined,
                                                bonus: undefined,
                                                testNotPassWithholdMoney: undefined,
                                                serviceChangeMoney: undefined,
                                    };
                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getList();
            },
            /** 重置按钮操作 */
            resetQuery() {
                this.resetForm("queryForm");
                this.handleQuery();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.id)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加一费制学员教练关联表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getOneFeeSystemCoachStudent(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改一费制学员教练关联表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateOneFeeSystemCoachStudent(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addOneFeeSystemCoachStudent(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("新增成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const ids = row.id || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delOneFeeSystemCoachStudent(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有一费制学员教练关联表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportOneFeeSystemCoachStudent(queryParams);
                }).then(data => {
                    this.download(data, "一费制学员教练关联表");
                }).catch(function() {});
            }
        }
    };
</script>
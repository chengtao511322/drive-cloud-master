<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            <el-form-item label="预约单号" prop="trainApplyNo">
                        <el-input
                                v-model="queryParams.trainApplyNo"
                                placeholder="请输入预约单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="训练类型（1-常规练车；2-考试练车）" prop="trainType">
                        <el-input
                                v-model="queryParams.trainType"
                                placeholder="请输入训练类型（1-常规练车；2-考试练车）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="教练课时ID" prop="classId">
                        <el-input
                                v-model="queryParams.classId"
                                placeholder="请输入教练课时ID"
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
                                                                                                                                                                                                                                        <el-form-item label="教练ID" prop="coachId">
                        <el-input
                                v-model="queryParams.coachId"
                                placeholder="请输入教练ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="训练场地ID" prop="coachingGridId">
                        <el-input
                                v-model="queryParams.coachingGridId"
                                placeholder="请输入训练场地ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="接你的时间" prop="takeTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.takeTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择接你的时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="上车时间" prop="aboardTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.aboardTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择上车时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                            <el-form-item label="开始时间" prop="startTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.startTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择开始时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="结束时间" prop="endTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.endTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择结束时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                            <el-form-item label="预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）" prop="applyStatus">
                        <el-input
                                v-model="queryParams.applyStatus"
                                placeholder="请输入预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）"
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
                                                                <el-form-item label="学员推荐用户类型（1-学员；2-教练；3-运维）" prop="studentRecommendUserType">
                        <el-input
                                v-model="queryParams.studentRecommendUserType"
                                placeholder="请输入学员推荐用户类型（1-学员；2-教练；3-运维）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员推荐用户ID" prop="studentRecommendUserId">
                        <el-input
                                v-model="queryParams.studentRecommendUserId"
                                placeholder="请输入学员推荐用户ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="学员常用地址id" prop="commonAddressId">
                        <el-input
                                v-model="queryParams.commonAddressId"
                                placeholder="请输入学员常用地址id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="教练推荐用户类型（1-学员；2-教练；3-运营）" prop="coachRecommendUserType">
                        <el-input
                                v-model="queryParams.coachRecommendUserType"
                                placeholder="请输入教练推荐用户类型（1-学员；2-教练；3-运营）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="教练推荐用户ID" prop="coachRecommendUserId">
                        <el-input
                                v-model="queryParams.coachRecommendUserId"
                                placeholder="请输入教练推荐用户ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="取消时间" prop="cancelTiem">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.cancelTiem"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择取消时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="取消原因" prop="cancelReason">
                        <el-input
                                v-model="queryParams.cancelReason"
                                placeholder="请输入取消原因"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                                                    <el-form-item label="考试训练用车类型（1-教练车；2-考试车）" prop="carType">
                        <el-input
                                v-model="queryParams.carType"
                                placeholder="请输入考试训练用车类型（1-教练车；2-考试车）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否删除（0-否;1-是）" prop="isDelete">
                        <el-input
                                v-model="queryParams.isDelete"
                                placeholder="请输入是否删除（0-否;1-是）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="实际开始时间" prop="actualStartTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.actualStartTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择实际开始时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="实际结束时间" prop="actualEndTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.actualEndTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择实际结束时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="课时点评" prop="classAppraise">
                        <el-input
                                v-model="queryParams.classAppraise"
                                placeholder="请输入课时点评"
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
                    v-hasPermi="['admin:studentTrainCarApply:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:studentTrainCarApply:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:studentTrainCarApply:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:studentTrainCarApply:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="studentTrainCarApplyList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="预约单号" align="center" prop="trainApplyNo" sortable="custom"/>
                                                                <el-table-column label="训练类型（1-常规练车；2-考试练车）" align="center" prop="trainType" sortable="custom"/>
                                                                <el-table-column label="教练课时ID" align="center" prop="classId" sortable="custom"/>
                                                                <el-table-column label="学员ID" align="center" prop="studentId" sortable="custom"/>
                                                                <el-table-column label="驾照类型（c1；c2...）" align="center" prop="driveType" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="subjectType" sortable="custom"/>
                                                                <el-table-column label="单价" align="center" prop="price" sortable="custom"/>
                                                                <el-table-column label="总价格" align="center" prop="sumPrice" sortable="custom"/>
                                                                <el-table-column label="平台服务费用百分比/H" align="center" prop="serviceChargePercent" sortable="custom"/>
                                                                <el-table-column label="教练课时费用百分比/H2" align="center" prop="coachChargePercent" sortable="custom"/>
                                                                <el-table-column label="平台服务费用" align="center" prop="serviceCharge" sortable="custom"/>
                                                                <el-table-column label="教练课时费用" align="center" prop="coachCharge" sortable="custom"/>
                                                                <el-table-column label="教练ID" align="center" prop="coachId" sortable="custom"/>
                                                                <el-table-column label="训练场地ID" align="center" prop="coachingGridId" sortable="custom"/>
                                                                <el-table-column label="接你的时间" align="center" prop="takeTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.takeTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="上车时间" align="center" prop="aboardTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.aboardTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="课程日期" align="center" prop="classDate" sortable="custom"/>
                                                                <el-table-column label="开始时间" align="center" prop="startTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.startTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="结束时间" align="center" prop="endTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.endTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="学时" align="center" prop="classHours" sortable="custom"/>
                                                                <el-table-column label="预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）" align="center" prop="applyStatus" sortable="custom"/>
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
                                                                <el-table-column label="学员推荐用户类型（1-学员；2-教练；3-运维）" align="center" prop="studentRecommendUserType" sortable="custom"/>
                                                                <el-table-column label="学员推荐用户ID" align="center" prop="studentRecommendUserId" sortable="custom"/>
                                                                <el-table-column label="学员推荐用户提成百分比" align="center" prop="studentRecommendUserChargePercent" sortable="custom"/>
                                                                <el-table-column label="学员推荐用户提成费用" align="center" prop="studentRecommendUserCharge" sortable="custom"/>
                                                                <el-table-column label="学员常用地址id" align="center" prop="commonAddressId" sortable="custom"/>
                                                                <el-table-column label="教练推荐用户类型（1-学员；2-教练；3-运营）" align="center" prop="coachRecommendUserType" sortable="custom"/>
                                                                <el-table-column label="教练推荐用户ID" align="center" prop="coachRecommendUserId" sortable="custom"/>
                                                                <el-table-column label="教练推荐用户提成百分比" align="center" prop="coachRecommendUserChargePercent" sortable="custom"/>
                                                                <el-table-column label="教练推荐用户提成费用" align="center" prop="coachRecommendUserCharge" sortable="custom"/>
                                                                <el-table-column label="取消时间" align="center" prop="cancelTiem" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.cancelTiem) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="取消原因" align="center" prop="cancelReason" sortable="custom"/>
                                                                <el-table-column label="取消扣款百分比" align="center" prop="cancelWithholdPercent" sortable="custom"/>
                                                                <el-table-column label="取消扣款金额" align="center" prop="cancelWithholdMoney" sortable="custom"/>
                                                                <el-table-column label="取消退款金额" align="center" prop="cancelRefundMoney" sortable="custom"/>
                                                                <el-table-column label="考试训练用车类型（1-教练车；2-考试车）" align="center" prop="carType" sortable="custom"/>
                                                                <el-table-column label="是否删除（0-否;1-是）" align="center" prop="isDelete" sortable="custom"/>
                                                                <el-table-column label="实际开始时间" align="center" prop="actualStartTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.actualStartTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="实际结束时间" align="center" prop="actualEndTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.actualEndTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="课时点评" align="center" prop="classAppraise" sortable="custom"/>
                                                                <el-table-column label="公车提成费用" align="center" prop="publicCarPrice" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:studentTrainCarApply:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:studentTrainCarApply:delete']"
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

        <!-- 添加或修改学员学车预约表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="预约单号" prop="trainApplyNo">
                            <el-input v-model="form.trainApplyNo" placeholder="请输入预约单号" />
                        </el-form-item>
                                                                                <el-form-item label="训练类型（1-常规练车；2-考试练车）" prop="trainType">
                            <el-input v-model="form.trainType" placeholder="请输入训练类型（1-常规练车；2-考试练车）" />
                        </el-form-item>
                                                                                <el-form-item label="教练课时ID" prop="classId">
                            <el-input v-model="form.classId" placeholder="请输入教练课时ID" />
                        </el-form-item>
                                                                                <el-form-item label="学员ID" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员ID" />
                        </el-form-item>
                                                                                <el-form-item label="驾照类型（c1；c2...）" prop="driveType">
                            <el-input v-model="form.driveType" placeholder="请输入驾照类型（c1；c2...）" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型" prop="subjectType">
                            <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
                        </el-form-item>
                                                                                <el-form-item label="单价" prop="price">
                            <el-input v-model="form.price" placeholder="请输入单价" />
                        </el-form-item>
                                                                                <el-form-item label="总价格" prop="sumPrice">
                            <el-input v-model="form.sumPrice" placeholder="请输入总价格" />
                        </el-form-item>
                                                                                <el-form-item label="平台服务费用百分比/H" prop="serviceChargePercent">
                            <el-input v-model="form.serviceChargePercent" placeholder="请输入平台服务费用百分比/H" />
                        </el-form-item>
                                                                                <el-form-item label="教练课时费用百分比/H2" prop="coachChargePercent">
                            <el-input v-model="form.coachChargePercent" placeholder="请输入教练课时费用百分比/H2" />
                        </el-form-item>
                                                                                <el-form-item label="平台服务费用" prop="serviceCharge">
                            <el-input v-model="form.serviceCharge" placeholder="请输入平台服务费用" />
                        </el-form-item>
                                                                                <el-form-item label="教练课时费用" prop="coachCharge">
                            <el-input v-model="form.coachCharge" placeholder="请输入教练课时费用" />
                        </el-form-item>
                                                                                <el-form-item label="教练ID" prop="coachId">
                            <el-input v-model="form.coachId" placeholder="请输入教练ID" />
                        </el-form-item>
                                                                                <el-form-item label="训练场地ID" prop="coachingGridId">
                            <el-input v-model="form.coachingGridId" placeholder="请输入训练场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="接你的时间" prop="takeTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.takeTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择接你的时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="上车时间" prop="aboardTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.aboardTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择上车时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="课程日期" prop="classDate">
                            <el-input v-model="form.classDate" placeholder="请输入课程日期" />
                        </el-form-item>
                                                                                <el-form-item label="开始时间" prop="startTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.startTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择开始时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="结束时间" prop="endTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.endTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择结束时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="学时" prop="classHours">
                            <el-input v-model="form.classHours" placeholder="请输入学时" />
                        </el-form-item>
                                                                                <el-form-item label="预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）" prop="applyStatus">
                            <el-input v-model="form.applyStatus" placeholder="请输入预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）" />
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
                                                                                <el-form-item label="学员推荐用户类型（1-学员；2-教练；3-运维）" prop="studentRecommendUserType">
                            <el-input v-model="form.studentRecommendUserType" placeholder="请输入学员推荐用户类型（1-学员；2-教练；3-运维）" />
                        </el-form-item>
                                                                                <el-form-item label="学员推荐用户ID" prop="studentRecommendUserId">
                            <el-input v-model="form.studentRecommendUserId" placeholder="请输入学员推荐用户ID" />
                        </el-form-item>
                                                                                <el-form-item label="学员推荐用户提成百分比" prop="studentRecommendUserChargePercent">
                            <el-input v-model="form.studentRecommendUserChargePercent" placeholder="请输入学员推荐用户提成百分比" />
                        </el-form-item>
                                                                                <el-form-item label="学员推荐用户提成费用" prop="studentRecommendUserCharge">
                            <el-input v-model="form.studentRecommendUserCharge" placeholder="请输入学员推荐用户提成费用" />
                        </el-form-item>
                                                                                <el-form-item label="学员常用地址id" prop="commonAddressId">
                            <el-input v-model="form.commonAddressId" placeholder="请输入学员常用地址id" />
                        </el-form-item>
                                                                                <el-form-item label="教练推荐用户类型（1-学员；2-教练；3-运营）" prop="coachRecommendUserType">
                            <el-input v-model="form.coachRecommendUserType" placeholder="请输入教练推荐用户类型（1-学员；2-教练；3-运营）" />
                        </el-form-item>
                                                                                <el-form-item label="教练推荐用户ID" prop="coachRecommendUserId">
                            <el-input v-model="form.coachRecommendUserId" placeholder="请输入教练推荐用户ID" />
                        </el-form-item>
                                                                                <el-form-item label="教练推荐用户提成百分比" prop="coachRecommendUserChargePercent">
                            <el-input v-model="form.coachRecommendUserChargePercent" placeholder="请输入教练推荐用户提成百分比" />
                        </el-form-item>
                                                                                <el-form-item label="教练推荐用户提成费用" prop="coachRecommendUserCharge">
                            <el-input v-model="form.coachRecommendUserCharge" placeholder="请输入教练推荐用户提成费用" />
                        </el-form-item>
                                                                                <el-form-item label="取消时间" prop="cancelTiem">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.cancelTiem"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择取消时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="取消原因" prop="cancelReason">
                            <el-input v-model="form.cancelReason" placeholder="请输入取消原因" />
                        </el-form-item>
                                                                                <el-form-item label="取消扣款百分比" prop="cancelWithholdPercent">
                            <el-input v-model="form.cancelWithholdPercent" placeholder="请输入取消扣款百分比" />
                        </el-form-item>
                                                                                <el-form-item label="取消扣款金额" prop="cancelWithholdMoney">
                            <el-input v-model="form.cancelWithholdMoney" placeholder="请输入取消扣款金额" />
                        </el-form-item>
                                                                                <el-form-item label="取消退款金额" prop="cancelRefundMoney">
                            <el-input v-model="form.cancelRefundMoney" placeholder="请输入取消退款金额" />
                        </el-form-item>
                                                                                <el-form-item label="考试训练用车类型（1-教练车；2-考试车）" prop="carType">
                            <el-input v-model="form.carType" placeholder="请输入考试训练用车类型（1-教练车；2-考试车）" />
                        </el-form-item>
                                                                                <el-form-item label="是否删除（0-否;1-是）" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除（0-否;1-是）" />
                        </el-form-item>
                                                                                <el-form-item label="实际开始时间" prop="actualStartTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.actualStartTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择实际开始时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="实际结束时间" prop="actualEndTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.actualEndTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择实际结束时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="课时点评" prop="classAppraise">
                            <el-input v-model="form.classAppraise" placeholder="请输入课时点评" />
                        </el-form-item>
                                                                                <el-form-item label="公车提成费用" prop="publicCarPrice">
                            <el-input v-model="form.publicCarPrice" placeholder="请输入公车提成费用" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
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
    import { listStudentTrainCarApply,listPageStudentTrainCarApply, getStudentTrainCarApply, delStudentTrainCarApply, addStudentTrainCarApply, updateStudentTrainCarApply, exportStudentTrainCarApply } from "@/api/admin/studentTrainCarApply";

    export default {
        name: "StudentTrainCarApply",
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
                // 学员学车预约表表格数据
                    studentTrainCarApplyList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
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
                                    },
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
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询学员学车预约表列表 */
            getListPage() {
                this.loading = true;
                listPageStudentTrainCarApply(this.queryParams).then(response => {
                    this.studentTrainCarApplyList = response.data.records;
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
                this.ids = selection.map(item => item.trainApplyNo)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加学员学车预约表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const trainApplyNo = row.trainApplyNo || this.ids
                getStudentTrainCarApply(trainApplyNo).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学员学车预约表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.trainApplyNo != undefined) {
                            updateStudentTrainCarApply(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addStudentTrainCarApply(this.form).then(response => {
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
                const trainApplyNos = row.trainApplyNo || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delStudentTrainCarApply(trainApplyNos);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学员学车预约表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportStudentTrainCarApply(queryParams);
                }).then(data => {
                    this.download(data, "学员学车预约表");
                }).catch(function() {});
            }
        }
    };
</script>
<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        <el-form-item label="" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="课程名称" prop="className">
                        <el-input
                                v-model="queryParams.className"
                                placeholder="请输入课程名称"
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
                                                                <el-form-item label="场地ID" prop="coachingGridId">
                        <el-input
                                v-model="queryParams.coachingGridId"
                                placeholder="请输入场地ID"
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
                                                                <el-form-item label="课时类型（1，普通训练，2考试训练）" prop="classType">
                        <el-input
                                v-model="queryParams.classType"
                                placeholder="请输入课时类型（1，普通训练，2考试训练）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
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
                                                                                            <el-form-item label="状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                                                                                                                                                                    <el-form-item label="备注" prop="remarks">
                        <el-input
                                v-model="queryParams.remarks"
                                placeholder="请输入备注"
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
                                                                <el-form-item label="上车时间" prop="aboardTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.aboardTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择上车时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="接你的时间" prop="takeTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.takeTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择接你的时间">
                        </el-date-picker>
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
                                                                                            <el-form-item label="发布科目二场地ID" prop="coachingGridId2">
                        <el-input
                                v-model="queryParams.coachingGridId2"
                                placeholder="请输入发布科目二场地ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="发布科目三场地ID" prop="coachingGridId3">
                        <el-input
                                v-model="queryParams.coachingGridId3"
                                placeholder="请输入发布科目三场地ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)" prop="releaseSubjectType">
                        <el-input
                                v-model="queryParams.releaseSubjectType"
                                placeholder="请输入发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)" prop="isHandleWithhole">
                        <el-input
                                v-model="queryParams.isHandleWithhole"
                                placeholder="请输入是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)"
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
                                                                <el-form-item label="是否为预收入(0-否，1-是)" prop="isExpect">
                        <el-input
                                v-model="queryParams.isExpect"
                                placeholder="请输入是否为预收入(0-否，1-是)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="预收入班型id" prop="expectClassId">
                        <el-input
                                v-model="queryParams.expectClassId"
                                placeholder="请输入预收入班型id"
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
                    v-hasPermi="['admin:coachTeachTime:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:coachTeachTime:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:coachTeachTime:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:coachTeachTime:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="coachTeachTimeList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="" align="center" prop="id" sortable="custom"/>
                                                                <el-table-column label="课程名称" align="center" prop="className" sortable="custom"/>
                                                                <el-table-column label="教练ID" align="center" prop="coachId" sortable="custom"/>
                                                                <el-table-column label="场地ID" align="center" prop="coachingGridId" sortable="custom"/>
                                                                <el-table-column label="学员ID" align="center" prop="studentId" sortable="custom"/>
                                                                <el-table-column label="驾照类型（c1；c2...）" align="center" prop="driveType" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="subjectType" sortable="custom"/>
                                                                <el-table-column label="课时类型（1，普通训练，2考试训练）" align="center" prop="classType" sortable="custom"/>
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
                                                                <el-table-column label="状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="平台提成百分比" align="center" prop="serviceChangePercent" sortable="custom"/>
                                                                <el-table-column label="教练提成百分比" align="center" prop="coachChargePercent" sortable="custom"/>
                                                                <el-table-column label="公车提成百分比" align="center" prop="carChargePercent" sortable="custom"/>
                                                                <el-table-column label="课时总费用" align="center" prop="sumCharge" sortable="custom"/>
                                                                <el-table-column label="平台提成金额" align="center" prop="serviceCharge" sortable="custom"/>
                                                                <el-table-column label="教练提成金额" align="center" prop="coaceCharge" sortable="custom"/>
                                                                <el-table-column label="公车提成金额" align="center" prop="carCharge" sortable="custom"/>
                                                                <el-table-column label="备注" align="center" prop="remarks" sortable="custom"/>
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
                                                                <el-table-column label="上车时间" align="center" prop="aboardTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.aboardTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="接你的时间" align="center" prop="takeTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.takeTime) }}</span>
                        </template>
                    </el-table-column>
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
                                                                <el-table-column label="剩余课时" align="center" prop="surplusClassHours" sortable="custom"/>
                                                                <el-table-column label="发布科目二场地ID" align="center" prop="coachingGridId2" sortable="custom"/>
                                                                <el-table-column label="发布科目三场地ID" align="center" prop="coachingGridId3" sortable="custom"/>
                                                                <el-table-column label="发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)" align="center" prop="releaseSubjectType" sortable="custom"/>
                                                                <el-table-column label="课时预扣百分比" align="center" prop="withholdPercent" sortable="custom"/>
                                                                <el-table-column label="课时预扣金额" align="center" prop="withholdCharge" sortable="custom"/>
                                                                <el-table-column label="是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)" align="center" prop="isHandleWithhole" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="是否为预收入(0-否，1-是)" align="center" prop="isExpect" sortable="custom"/>
                                                                <el-table-column label="预收入班型id" align="center" prop="expectClassId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:coachTeachTime:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:coachTeachTime:delete']"
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

        <!-- 添加或修改教练课程时间表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="课程名称" prop="className">
                            <el-input v-model="form.className" placeholder="请输入课程名称" />
                        </el-form-item>
                                                                                <el-form-item label="教练ID" prop="coachId">
                            <el-input v-model="form.coachId" placeholder="请输入教练ID" />
                        </el-form-item>
                                                                                <el-form-item label="场地ID" prop="coachingGridId">
                            <el-input v-model="form.coachingGridId" placeholder="请输入场地ID" />
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
                                                                                <el-form-item label="课时类型（1，普通训练，2考试训练）" prop="classType">
                            <el-input v-model="form.classType" placeholder="请输入课时类型（1，普通训练，2考试训练）" />
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
                                                                                <el-form-item label="状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)" />
                        </el-form-item>
                                                                                <el-form-item label="平台提成百分比" prop="serviceChangePercent">
                            <el-input v-model="form.serviceChangePercent" placeholder="请输入平台提成百分比" />
                        </el-form-item>
                                                                                <el-form-item label="教练提成百分比" prop="coachChargePercent">
                            <el-input v-model="form.coachChargePercent" placeholder="请输入教练提成百分比" />
                        </el-form-item>
                                                                                <el-form-item label="公车提成百分比" prop="carChargePercent">
                            <el-input v-model="form.carChargePercent" placeholder="请输入公车提成百分比" />
                        </el-form-item>
                                                                                <el-form-item label="课时总费用" prop="sumCharge">
                            <el-input v-model="form.sumCharge" placeholder="请输入课时总费用" />
                        </el-form-item>
                                                                                <el-form-item label="平台提成金额" prop="serviceCharge">
                            <el-input v-model="form.serviceCharge" placeholder="请输入平台提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="教练提成金额" prop="coaceCharge">
                            <el-input v-model="form.coaceCharge" placeholder="请输入教练提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="公车提成金额" prop="carCharge">
                            <el-input v-model="form.carCharge" placeholder="请输入公车提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入备注" />
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
                                                                                <el-form-item label="上车时间" prop="aboardTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.aboardTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择上车时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="接你的时间" prop="takeTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.takeTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择接你的时间">
                            </el-date-picker>
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
                                                                                <el-form-item label="剩余课时" prop="surplusClassHours">
                            <el-input v-model="form.surplusClassHours" placeholder="请输入剩余课时" />
                        </el-form-item>
                                                                                <el-form-item label="发布科目二场地ID" prop="coachingGridId2">
                            <el-input v-model="form.coachingGridId2" placeholder="请输入发布科目二场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="发布科目三场地ID" prop="coachingGridId3">
                            <el-input v-model="form.coachingGridId3" placeholder="请输入发布科目三场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)" prop="releaseSubjectType">
                            <el-input v-model="form.releaseSubjectType" placeholder="请输入发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)" />
                        </el-form-item>
                                                                                <el-form-item label="课时预扣百分比" prop="withholdPercent">
                            <el-input v-model="form.withholdPercent" placeholder="请输入课时预扣百分比" />
                        </el-form-item>
                                                                                <el-form-item label="课时预扣金额" prop="withholdCharge">
                            <el-input v-model="form.withholdCharge" placeholder="请输入课时预扣金额" />
                        </el-form-item>
                                                                                <el-form-item label="是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)" prop="isHandleWithhole">
                            <el-input v-model="form.isHandleWithhole" placeholder="请输入是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="是否为预收入(0-否，1-是)" prop="isExpect">
                            <el-input v-model="form.isExpect" placeholder="请输入是否为预收入(0-否，1-是)" />
                        </el-form-item>
                                                                                <el-form-item label="预收入班型id" prop="expectClassId">
                            <el-input v-model="form.expectClassId" placeholder="请输入预收入班型id" />
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
    import { listCoachTeachTime,listPageCoachTeachTime, getCoachTeachTime, delCoachTeachTime, addCoachTeachTime, updateCoachTeachTime, exportCoachTeachTime } from "@/api/admin/coachTeachTime";

    export default {
        name: "CoachTeachTime",
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
                // 教练课程时间表表格数据
                    coachTeachTimeList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
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
                                    },
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
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询教练课程时间表列表 */
            getListPage() {
                this.loading = true;
                listPageCoachTeachTime(this.queryParams).then(response => {
                    this.coachTeachTimeList = response.data.records;
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
                this.ids = selection.map(item => item.id)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加教练课程时间表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getCoachTeachTime(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改教练课程时间表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateCoachTeachTime(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addCoachTeachTime(this.form).then(response => {
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
                const ids = row.id || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delCoachTeachTime(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有教练课程时间表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportCoachTeachTime(queryParams);
                }).then(data => {
                    this.download(data, "教练课程时间表");
                }).catch(function() {});
            }
        }
    };
</script>
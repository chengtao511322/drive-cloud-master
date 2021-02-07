<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <el-form-item label="学员报名单号" prop="studyEnrollNo">
                        <el-input
                                v-model="queryParams.studyEnrollNo"
                                placeholder="请输入学员报名单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="省-学员的省" prop="provinceId">
                        <el-input
                                v-model="queryParams.provinceId"
                                placeholder="请输入省-学员的省"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="市-学员的市" prop="cityId">
                        <el-input
                                v-model="queryParams.cityId"
                                placeholder="请输入市-学员的市"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="区-学员的区" prop="areaId">
                        <el-input
                                v-model="queryParams.areaId"
                                placeholder="请输入区-学员的区"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="经度" prop="longitude">
                        <el-input
                                v-model="queryParams.longitude"
                                placeholder="请输入经度"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="纬度" prop="latitude">
                        <el-input
                                v-model="queryParams.latitude"
                                placeholder="请输入纬度"
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
                                                                <el-form-item label="真实姓名" prop="realName">
                        <el-input
                                v-model="queryParams.realName"
                                placeholder="请输入真实姓名"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="身份证号" prop="idCard">
                        <el-input
                                v-model="queryParams.idCard"
                                placeholder="请输入身份证号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="电话号码" prop="telephone">
                        <el-input
                                v-model="queryParams.telephone"
                                placeholder="请输入电话号码"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="性别（男；女）" prop="sex">
                        <el-input
                                v-model="queryParams.sex"
                                placeholder="请输入性别（男；女）"
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
                                                                                            <el-form-item label="报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）" prop="enrollStatus">
                        <el-input
                                v-model="queryParams.enrollStatus"
                                placeholder="请输入报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="报名驾校id" prop="driveSchoolId">
                        <el-input
                                v-model="queryParams.driveSchoolId"
                                placeholder="请输入报名驾校id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="报名驾校名称（非平台报名人员补充资料）" prop="driveSchoolName">
                        <el-input
                                v-model="queryParams.driveSchoolName"
                                placeholder="请输入报名驾校名称（非平台报名人员补充资料）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="身份证图片正面" prop="idCardPathPositive">
                        <el-input
                                v-model="queryParams.idCardPathPositive"
                                placeholder="请输入身份证图片正面"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="身份证图片反面" prop="idCardPathNegative">
                        <el-input
                                v-model="queryParams.idCardPathNegative"
                                placeholder="请输入身份证图片反面"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="居住证图片正面" prop="liveProvePositive">
                        <el-input
                                v-model="queryParams.liveProvePositive"
                                placeholder="请输入居住证图片正面"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="居住证图片反面" prop="liveProveNegative">
                        <el-input
                                v-model="queryParams.liveProveNegative"
                                placeholder="请输入居住证图片反面"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="联系地址" prop="address">
                        <el-input
                                v-model="queryParams.address"
                                placeholder="请输入联系地址"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="体检结果照片" prop="testResultPath">
                        <el-input
                                v-model="queryParams.testResultPath"
                                placeholder="请输入体检结果照片"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="交管网上报名账号" prop="enrollAccountNo">
                        <el-input
                                v-model="queryParams.enrollAccountNo"
                                placeholder="请输入交管网上报名账号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="交管网上报名密码" prop="enrollPassword">
                        <el-input
                                v-model="queryParams.enrollPassword"
                                placeholder="请输入交管网上报名密码"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾校实际报名时间" prop="actualEnrollTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.actualEnrollTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择驾校实际报名时间">
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
                                                                <el-form-item label="线下客服id(驾校客服，报名接送)" prop="lineUnderUserId">
                        <el-input
                                v-model="queryParams.lineUnderUserId"
                                placeholder="请输入线下客服id(驾校客服，报名接送)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="预约见面时间" prop="beSpeakMeetTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.beSpeakMeetTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择预约见面时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="预约见面地址" prop="beSpeakMeetAddress">
                        <el-input
                                v-model="queryParams.beSpeakMeetAddress"
                                placeholder="请输入预约见面地址"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="取消时间" prop="cancelTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.cancelTime"
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
                                                                <el-form-item label="是否有意向报名（是，否）" prop="isIntentEnroll">
                        <el-input
                                v-model="queryParams.isIntentEnroll"
                                placeholder="请输入是否有意向报名（是，否）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="联系时间" prop="contactTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.contactTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择联系时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="意向报名时间" prop="intentEnrollTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.intentEnrollTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择意向报名时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="意向报名驾校id" prop="intentEnrollSchoolId">
                        <el-input
                                v-model="queryParams.intentEnrollSchoolId"
                                placeholder="请输入意向报名驾校id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="线上报名时间" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择线上报名时间">
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
                                                                <el-form-item label="是否选择接送" prop="isMeetGive">
                        <el-input
                                v-model="queryParams.isMeetGive"
                                placeholder="请输入是否选择接送"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="是否为学生" prop="isStudent">
                        <el-input
                                v-model="queryParams.isStudent"
                                placeholder="请输入是否为学生"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学生所属学校id" prop="studentSchoolId">
                        <el-input
                                v-model="queryParams.studentSchoolId"
                                placeholder="请输入学生所属学校id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="身份证是否归还(是，否)" prop="idIsReturn">
                        <el-input
                                v-model="queryParams.idIsReturn"
                                placeholder="请输入身份证是否归还(是，否)"
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
                                                                                            <el-form-item label="是否还在平台学习（0-否，1-是）" prop="isInStudy">
                        <el-input
                                v-model="queryParams.isInStudy"
                                placeholder="请输入是否还在平台学习（0-否，1-是）"
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
                    v-hasPermi="['admin:studentStudyEnroll:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:studentStudyEnroll:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:studentStudyEnroll:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:studentStudyEnroll:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="studentStudyEnrollList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="学员报名单号" align="center" prop="studyEnrollNo" />
                                                                <el-table-column label="省-学员的省" align="center" prop="provinceId" />
                                                                <el-table-column label="市-学员的市" align="center" prop="cityId" />
                                                                <el-table-column label="区-学员的区" align="center" prop="areaId" />
                                                                <el-table-column label="经度" align="center" prop="longitude" />
                                                                <el-table-column label="纬度" align="center" prop="latitude" />
                                                                <el-table-column label="学员ID" align="center" prop="studentId" />
                                                                <el-table-column label="真实姓名" align="center" prop="realName" />
                                                                <el-table-column label="身份证号" align="center" prop="idCard" />
                                                                <el-table-column label="电话号码" align="center" prop="telephone" />
                                                                <el-table-column label="年龄" align="center" prop="age" />
                                                                <el-table-column label="性别（男；女）" align="center" prop="sex" />
                                                                <el-table-column label="驾照类型（c1；c2...）" align="center" prop="driveType" />
                                                                <el-table-column label="平台服务费用" align="center" prop="serviceCharge" />
                                                                <el-table-column label="报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）" align="center" prop="enrollStatus" />
                                                                <el-table-column label="报名驾校id" align="center" prop="driveSchoolId" />
                                                                <el-table-column label="报名驾校名称（非平台报名人员补充资料）" align="center" prop="driveSchoolName" />
                                                                <el-table-column label="报名单价(平台总的报名价格)" align="center" prop="price" />
                                                                <el-table-column label="身份证图片正面" align="center" prop="idCardPathPositive" />
                                                                <el-table-column label="身份证图片反面" align="center" prop="idCardPathNegative" />
                                                                <el-table-column label="居住证图片正面" align="center" prop="liveProvePositive" />
                                                                <el-table-column label="居住证图片反面" align="center" prop="liveProveNegative" />
                                                                <el-table-column label="联系地址" align="center" prop="address" />
                                                                <el-table-column label="体检结果照片" align="center" prop="testResultPath" />
                                                                <el-table-column label="交管网上报名账号" align="center" prop="enrollAccountNo" />
                                                                <el-table-column label="交管网上报名密码" align="center" prop="enrollPassword" />
                                                                <el-table-column label="驾校实际报名时间" align="center" prop="actualEnrollTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.actualEnrollTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="客服ID（系统用户ID）" align="center" prop="userId" />
                                                                <el-table-column label="线下客服id(驾校客服，报名接送)" align="center" prop="lineUnderUserId" />
                                                                <el-table-column label="预约见面时间" align="center" prop="beSpeakMeetTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.beSpeakMeetTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="预约见面地址" align="center" prop="beSpeakMeetAddress" />
                                                                <el-table-column label="取消时间" align="center" prop="cancelTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.cancelTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="取消原因" align="center" prop="cancelReason" />
                                                                <el-table-column label="是否有意向报名（是，否）" align="center" prop="isIntentEnroll" />
                                                                <el-table-column label="联系时间" align="center" prop="contactTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.contactTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="意向报名时间" align="center" prop="intentEnrollTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.intentEnrollTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="意向报名驾校id" align="center" prop="intentEnrollSchoolId" />
                                                                <el-table-column label="线上报名时间" align="center" prop="createTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="是否选择接送" align="center" prop="isMeetGive" />
                                                                <el-table-column label="接送费用" align="center" prop="meetGicePrice" />
                                                                <el-table-column label="是否为学生" align="center" prop="isStudent" />
                                                                <el-table-column label="学生所属学校id" align="center" prop="studentSchoolId" />
                                                                <el-table-column label="身份证是否归还(是，否)" align="center" prop="idIsReturn" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                                                <el-table-column label="报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" align="center" prop="enrollType" />
                                                                <el-table-column label="是否还在平台学习（0-否，1-是）" align="center" prop="isInStudy" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:studentStudyEnroll:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:studentStudyEnroll:delete']"
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

        <!-- 添加或修改学员学车报名单对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="学员报名单号" prop="studyEnrollNo">
                            <el-input v-model="form.studyEnrollNo" placeholder="请输入学员报名单号" />
                        </el-form-item>
                                                                                <el-form-item label="省-学员的省" prop="provinceId">
                            <el-input v-model="form.provinceId" placeholder="请输入省-学员的省" />
                        </el-form-item>
                                                                                <el-form-item label="市-学员的市" prop="cityId">
                            <el-input v-model="form.cityId" placeholder="请输入市-学员的市" />
                        </el-form-item>
                                                                                <el-form-item label="区-学员的区" prop="areaId">
                            <el-input v-model="form.areaId" placeholder="请输入区-学员的区" />
                        </el-form-item>
                                                                                <el-form-item label="经度" prop="longitude">
                            <el-input v-model="form.longitude" placeholder="请输入经度" />
                        </el-form-item>
                                                                                <el-form-item label="纬度" prop="latitude">
                            <el-input v-model="form.latitude" placeholder="请输入纬度" />
                        </el-form-item>
                                                                                <el-form-item label="学员ID" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员ID" />
                        </el-form-item>
                                                                                <el-form-item label="真实姓名" prop="realName">
                            <el-input v-model="form.realName" placeholder="请输入真实姓名" />
                        </el-form-item>
                                                                                <el-form-item label="身份证号" prop="idCard">
                            <el-input v-model="form.idCard" placeholder="请输入身份证号" />
                        </el-form-item>
                                                                                <el-form-item label="电话号码" prop="telephone">
                            <el-input v-model="form.telephone" placeholder="请输入电话号码" />
                        </el-form-item>
                                                                                <el-form-item label="年龄" prop="age">
                            <el-input v-model="form.age" placeholder="请输入年龄" />
                        </el-form-item>
                                                                                <el-form-item label="性别（男；女）" prop="sex">
                            <el-input v-model="form.sex" placeholder="请输入性别（男；女）" />
                        </el-form-item>
                                                                                <el-form-item label="驾照类型（c1；c2...）" prop="driveType">
                            <el-input v-model="form.driveType" placeholder="请输入驾照类型（c1；c2...）" />
                        </el-form-item>
                                                                                <el-form-item label="平台服务费用" prop="serviceCharge">
                            <el-input v-model="form.serviceCharge" placeholder="请输入平台服务费用" />
                        </el-form-item>
                                                                                <el-form-item label="报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）" prop="enrollStatus">
                            <el-input v-model="form.enrollStatus" placeholder="请输入报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）" />
                        </el-form-item>
                                                                                <el-form-item label="报名驾校id" prop="driveSchoolId">
                            <el-input v-model="form.driveSchoolId" placeholder="请输入报名驾校id" />
                        </el-form-item>
                                                                                <el-form-item label="报名驾校名称（非平台报名人员补充资料）" prop="driveSchoolName">
                            <el-input v-model="form.driveSchoolName" placeholder="请输入报名驾校名称（非平台报名人员补充资料）" />
                        </el-form-item>
                                                                                <el-form-item label="报名单价(平台总的报名价格)" prop="price">
                            <el-input v-model="form.price" placeholder="请输入报名单价(平台总的报名价格)" />
                        </el-form-item>
                                                                                <el-form-item label="身份证图片正面" prop="idCardPathPositive">
                            <el-input v-model="form.idCardPathPositive" placeholder="请输入身份证图片正面" />
                        </el-form-item>
                                                                                <el-form-item label="身份证图片反面" prop="idCardPathNegative">
                            <el-input v-model="form.idCardPathNegative" placeholder="请输入身份证图片反面" />
                        </el-form-item>
                                                                                <el-form-item label="居住证图片正面" prop="liveProvePositive">
                            <el-input v-model="form.liveProvePositive" placeholder="请输入居住证图片正面" />
                        </el-form-item>
                                                                                <el-form-item label="居住证图片反面" prop="liveProveNegative">
                            <el-input v-model="form.liveProveNegative" placeholder="请输入居住证图片反面" />
                        </el-form-item>
                                                                                <el-form-item label="联系地址" prop="address">
                            <el-input v-model="form.address" placeholder="请输入联系地址" />
                        </el-form-item>
                                                                                <el-form-item label="体检结果照片" prop="testResultPath">
                            <el-input v-model="form.testResultPath" placeholder="请输入体检结果照片" />
                        </el-form-item>
                                                                                <el-form-item label="交管网上报名账号" prop="enrollAccountNo">
                            <el-input v-model="form.enrollAccountNo" placeholder="请输入交管网上报名账号" />
                        </el-form-item>
                                                                                <el-form-item label="交管网上报名密码" prop="enrollPassword">
                            <el-input v-model="form.enrollPassword" placeholder="请输入交管网上报名密码" />
                        </el-form-item>
                                                                                <el-form-item label="驾校实际报名时间" prop="actualEnrollTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.actualEnrollTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择驾校实际报名时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="客服ID（系统用户ID）" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入客服ID（系统用户ID）" />
                        </el-form-item>
                                                                                <el-form-item label="线下客服id(驾校客服，报名接送)" prop="lineUnderUserId">
                            <el-input v-model="form.lineUnderUserId" placeholder="请输入线下客服id(驾校客服，报名接送)" />
                        </el-form-item>
                                                                                <el-form-item label="预约见面时间" prop="beSpeakMeetTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.beSpeakMeetTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择预约见面时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="预约见面地址" prop="beSpeakMeetAddress">
                            <el-input v-model="form.beSpeakMeetAddress" placeholder="请输入预约见面地址" />
                        </el-form-item>
                                                                                <el-form-item label="取消时间" prop="cancelTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.cancelTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择取消时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="取消原因" prop="cancelReason">
                            <el-input v-model="form.cancelReason" placeholder="请输入取消原因" />
                        </el-form-item>
                                                                                <el-form-item label="是否有意向报名（是，否）" prop="isIntentEnroll">
                            <el-input v-model="form.isIntentEnroll" placeholder="请输入是否有意向报名（是，否）" />
                        </el-form-item>
                                                                                <el-form-item label="联系时间" prop="contactTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.contactTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择联系时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="意向报名时间" prop="intentEnrollTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.intentEnrollTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择意向报名时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="意向报名驾校id" prop="intentEnrollSchoolId">
                            <el-input v-model="form.intentEnrollSchoolId" placeholder="请输入意向报名驾校id" />
                        </el-form-item>
                                                                                <el-form-item label="线上报名时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择线上报名时间">
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
                                                                                <el-form-item label="是否选择接送" prop="isMeetGive">
                            <el-input v-model="form.isMeetGive" placeholder="请输入是否选择接送" />
                        </el-form-item>
                                                                                <el-form-item label="接送费用" prop="meetGicePrice">
                            <el-input v-model="form.meetGicePrice" placeholder="请输入接送费用" />
                        </el-form-item>
                                                                                <el-form-item label="是否为学生" prop="isStudent">
                            <el-input v-model="form.isStudent" placeholder="请输入是否为学生" />
                        </el-form-item>
                                                                                <el-form-item label="学生所属学校id" prop="studentSchoolId">
                            <el-input v-model="form.studentSchoolId" placeholder="请输入学生所属学校id" />
                        </el-form-item>
                                                                                <el-form-item label="身份证是否归还(是，否)" prop="idIsReturn">
                            <el-input v-model="form.idIsReturn" placeholder="请输入身份证是否归还(是，否)" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" prop="enrollType">
                            <el-input v-model="form.enrollType" placeholder="请输入报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班" />
                        </el-form-item>
                                                                                <el-form-item label="是否还在平台学习（0-否，1-是）" prop="isInStudy">
                            <el-input v-model="form.isInStudy" placeholder="请输入是否还在平台学习（0-否，1-是）" />
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
    import { listStudentStudyEnroll,listPageStudentStudyEnroll, getStudentStudyEnroll, delStudentStudyEnroll, addStudentStudyEnroll, updateStudentStudyEnroll, exportStudentStudyEnroll } from "@/api/admin/studentStudyEnroll";

    export default {
        name: "StudentStudyEnroll",
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
                // 学员学车报名单表格数据
                    studentStudyEnrollList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                studyEnrollNo: undefined,
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                studentId: undefined,
                                                realName: undefined,
                                                idCard: undefined,
                                                telephone: undefined,
                                                age: undefined,
                                                sex: undefined,
                                                driveType: undefined,
                                                serviceCharge: undefined,
                                                enrollStatus: undefined,
                                                driveSchoolId: undefined,
                                                driveSchoolName: undefined,
                                                price: undefined,
                                                idCardPathPositive: undefined,
                                                idCardPathNegative: undefined,
                                                liveProvePositive: undefined,
                                                liveProveNegative: undefined,
                                                address: undefined,
                                                testResultPath: undefined,
                                                enrollAccountNo: undefined,
                                                enrollPassword: undefined,
                                                actualEnrollTime: undefined,
                                                userId: undefined,
                                                lineUnderUserId: undefined,
                                                beSpeakMeetTime: undefined,
                                                beSpeakMeetAddress: undefined,
                                                cancelTime: undefined,
                                                cancelReason: undefined,
                                                isIntentEnroll: undefined,
                                                contactTime: undefined,
                                                intentEnrollTime: undefined,
                                                intentEnrollSchoolId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isMeetGive: undefined,
                                                meetGicePrice: undefined,
                                                isStudent: undefined,
                                                studentSchoolId: undefined,
                                                idIsReturn: undefined,
                                                operatorId: undefined,
                                                enrollType: undefined,
                                                isInStudy: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                studyEnrollNo: [
                            { required: true, message: "学员报名单号不能为空", trigger: "blur" }
                        ],
                                                provinceId: [
                            { required: true, message: "省-学员的省不能为空", trigger: "blur" }
                        ],
                                                cityId: [
                            { required: true, message: "市-学员的市不能为空", trigger: "blur" }
                        ],
                                                areaId: [
                            { required: true, message: "区-学员的区不能为空", trigger: "blur" }
                        ],
                                                longitude: [
                            { required: true, message: "经度不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "纬度不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员ID不能为空", trigger: "blur" }
                        ],
                                                realName: [
                            { required: true, message: "真实姓名不能为空", trigger: "blur" }
                        ],
                                                idCard: [
                            { required: true, message: "身份证号不能为空", trigger: "blur" }
                        ],
                                                telephone: [
                            { required: true, message: "电话号码不能为空", trigger: "blur" }
                        ],
                                                age: [
                            { required: true, message: "年龄不能为空", trigger: "blur" }
                        ],
                                                sex: [
                            { required: true, message: "性别（男；女）不能为空", trigger: "blur" }
                        ],
                                                driveType: [
                            { required: true, message: "驾照类型（c1；c2...）不能为空", trigger: "blur" }
                        ],
                                                serviceCharge: [
                            { required: true, message: "平台服务费用不能为空", trigger: "blur" }
                        ],
                                                enrollStatus: [
                            { required: true, message: "报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）不能为空", trigger: "blur" }
                        ],
                                                driveSchoolId: [
                            { required: true, message: "报名驾校id不能为空", trigger: "blur" }
                        ],
                                                driveSchoolName: [
                            { required: true, message: "报名驾校名称（非平台报名人员补充资料）不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "报名单价(平台总的报名价格)不能为空", trigger: "blur" }
                        ],
                                                idCardPathPositive: [
                            { required: true, message: "身份证图片正面不能为空", trigger: "blur" }
                        ],
                                                idCardPathNegative: [
                            { required: true, message: "身份证图片反面不能为空", trigger: "blur" }
                        ],
                                                liveProvePositive: [
                            { required: true, message: "居住证图片正面不能为空", trigger: "blur" }
                        ],
                                                liveProveNegative: [
                            { required: true, message: "居住证图片反面不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "联系地址不能为空", trigger: "blur" }
                        ],
                                                testResultPath: [
                            { required: true, message: "体检结果照片不能为空", trigger: "blur" }
                        ],
                                                enrollAccountNo: [
                            { required: true, message: "交管网上报名账号不能为空", trigger: "blur" }
                        ],
                                                enrollPassword: [
                            { required: true, message: "交管网上报名密码不能为空", trigger: "blur" }
                        ],
                                                actualEnrollTime: [
                            { required: true, message: "驾校实际报名时间不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "客服ID（系统用户ID）不能为空", trigger: "blur" }
                        ],
                                                lineUnderUserId: [
                            { required: true, message: "线下客服id(驾校客服，报名接送)不能为空", trigger: "blur" }
                        ],
                                                beSpeakMeetTime: [
                            { required: true, message: "预约见面时间不能为空", trigger: "blur" }
                        ],
                                                beSpeakMeetAddress: [
                            { required: true, message: "预约见面地址不能为空", trigger: "blur" }
                        ],
                                                cancelTime: [
                            { required: true, message: "取消时间不能为空", trigger: "blur" }
                        ],
                                                cancelReason: [
                            { required: true, message: "取消原因不能为空", trigger: "blur" }
                        ],
                                                isIntentEnroll: [
                            { required: true, message: "是否有意向报名（是，否）不能为空", trigger: "blur" }
                        ],
                                                contactTime: [
                            { required: true, message: "联系时间不能为空", trigger: "blur" }
                        ],
                                                intentEnrollTime: [
                            { required: true, message: "意向报名时间不能为空", trigger: "blur" }
                        ],
                                                intentEnrollSchoolId: [
                            { required: true, message: "意向报名驾校id不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "线上报名时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                isMeetGive: [
                            { required: true, message: "是否选择接送不能为空", trigger: "blur" }
                        ],
                                                meetGicePrice: [
                            { required: true, message: "接送费用不能为空", trigger: "blur" }
                        ],
                                                isStudent: [
                            { required: true, message: "是否为学生不能为空", trigger: "blur" }
                        ],
                                                studentSchoolId: [
                            { required: true, message: "学生所属学校id不能为空", trigger: "blur" }
                        ],
                                                idIsReturn: [
                            { required: true, message: "身份证是否归还(是，否)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                enrollType: [
                            { required: true, message: "报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班不能为空", trigger: "blur" }
                        ],
                                                isInStudy: [
                            { required: true, message: "是否还在平台学习（0-否，1-是）不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询学员学车报名单列表 */
            getListPage() {
                this.loading = true;
                listPageStudentStudyEnroll(this.queryParams).then(response => {
                    this.studentStudyEnrollList = response.data.records;
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
                                                studyEnrollNo: undefined,
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                studentId: undefined,
                                                realName: undefined,
                                                idCard: undefined,
                                                telephone: undefined,
                                                age: undefined,
                                                sex: undefined,
                                                driveType: undefined,
                                                serviceCharge: undefined,
                                                enrollStatus: undefined,
                                                driveSchoolId: undefined,
                                                driveSchoolName: undefined,
                                                price: undefined,
                                                idCardPathPositive: undefined,
                                                idCardPathNegative: undefined,
                                                liveProvePositive: undefined,
                                                liveProveNegative: undefined,
                                                address: undefined,
                                                testResultPath: undefined,
                                                enrollAccountNo: undefined,
                                                enrollPassword: undefined,
                                                actualEnrollTime: undefined,
                                                userId: undefined,
                                                lineUnderUserId: undefined,
                                                beSpeakMeetTime: undefined,
                                                beSpeakMeetAddress: undefined,
                                                cancelTime: undefined,
                                                cancelReason: undefined,
                                                isIntentEnroll: undefined,
                                                contactTime: undefined,
                                                intentEnrollTime: undefined,
                                                intentEnrollSchoolId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isMeetGive: undefined,
                                                meetGicePrice: undefined,
                                                isStudent: undefined,
                                                studentSchoolId: undefined,
                                                idIsReturn: undefined,
                                                operatorId: undefined,
                                                enrollType: undefined,
                                                isInStudy: undefined,
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
                this.ids = selection.map(item => item.studyEnrollNo)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加学员学车报名单";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const studyEnrollNo = row.studyEnrollNo || this.ids
                getStudentStudyEnroll(studyEnrollNo).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学员学车报名单";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.studyEnrollNo != undefined) {
                            updateStudentStudyEnroll(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addStudentStudyEnroll(this.form).then(response => {
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
                const studyEnrollNos = row.studyEnrollNo || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delStudentStudyEnroll(studyEnrollNos);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学员学车报名单数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportStudentStudyEnroll(queryParams);
                }).then(data => {
                    this.download(data, "学员学车报名单");
                }).catch(function() {});
            }
        }
    };
</script>
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
                                                                <el-form-item label="省" prop="provinceId">
                        <el-input
                                v-model="queryParams.provinceId"
                                placeholder="请输入省"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="市" prop="cityId">
                        <el-input
                                v-model="queryParams.cityId"
                                placeholder="请输入市"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="区" prop="areaId">
                        <el-input
                                v-model="queryParams.areaId"
                                placeholder="请输入区"
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
                                                                <el-form-item label="详细地址" prop="address">
                        <el-input
                                v-model="queryParams.address"
                                placeholder="请输入详细地址"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="联系人" prop="contacts">
                        <el-input
                                v-model="queryParams.contacts"
                                placeholder="请输入联系人"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="联系电话" prop="contactPhone">
                        <el-input
                                v-model="queryParams.contactPhone"
                                placeholder="请输入联系电话"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="驾校名称" prop="schoolName">
                        <el-input
                                v-model="queryParams.schoolName"
                                placeholder="请输入驾校名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="成立时间" prop="establishTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.establishTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择成立时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="资质照片地址List" prop="seniorityPath">
                        <el-input
                                v-model="queryParams.seniorityPath"
                                placeholder="请输入资质照片地址List"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾校规模" prop="driveSchoolScale">
                        <el-input
                                v-model="queryParams.driveSchoolScale"
                                placeholder="请输入驾校规模"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾校简介" prop="schoolDescription">
                        <el-input
                                v-model="queryParams.schoolDescription"
                                placeholder="请输入驾校简介"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否可报名" prop="isEnroll">
                        <el-input
                                v-model="queryParams.isEnroll"
                                placeholder="请输入是否可报名"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾校后台管理用户id" prop="adminUserId">
                        <el-input
                                v-model="queryParams.adminUserId"
                                placeholder="请输入驾校后台管理用户id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="状态（1-正常，2-停用）" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态（1-正常，2-停用）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否独立结算(0-否，1-是)" prop="isAloneSettlement">
                        <el-input
                                v-model="queryParams.isAloneSettlement"
                                placeholder="请输入是否独立结算(0-否，1-是)"
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
                    v-hasPermi="['admin:driveSchool:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:driveSchool:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:driveSchool:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:driveSchool:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="driveSchoolList" @selection-change="handleSelectionChange"
                  @sort-change="sortChange"
                  stripe
                  border
                  header-row-class-name="table-header"
                  :header-row-style="{
                                        fontFamily: '微软雅黑 Bold, 微软雅黑 Regular, 微软雅黑'
                                     }"
        >
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="" align="center" prop="id" />
                                                                <el-table-column label="省" align="center" prop="provinceId" />
                                                                <el-table-column label="市" align="center" prop="cityId" />
                                                                <el-table-column label="区" align="center" prop="areaId" />
                                                                <el-table-column label="经度" align="center" prop="longitude" />
                                                                <el-table-column label="纬度" align="center" prop="latitude" />
                                                                <el-table-column label="详细地址" align="center" prop="address" />
                                                                <el-table-column label="联系人" align="center" prop="contacts" />
                                                                <el-table-column label="联系电话" align="center" prop="contactPhone" />
                                                                <el-table-column label="报名单价" align="center" prop="enrollPrice" />
                                                                <el-table-column label="驾校名称" align="center" prop="schoolName" />
                                                                <el-table-column label="成立时间" align="center" prop="establishTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.establishTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="资质照片地址List" align="center" prop="seniorityPath" />
                                                                <el-table-column label="驾校规模" align="center" prop="driveSchoolScale" />
                                                                <el-table-column label="驾校简介" align="center" prop="schoolDescription" />
                                                                <el-table-column label="是否可报名" align="center" prop="isEnroll" />
                                                                <el-table-column label="驾校后台管理用户id" align="center" prop="adminUserId" />
                                                                <el-table-column label="状态（1-正常，2-停用）" align="center" prop="status" />
                                                                <el-table-column label="是否独立结算(0-否，1-是)" align="center" prop="isAloneSettlement" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                                                <el-table-column label="报名单价(vip报名费)" align="center" prop="vipEnrollPrice" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:driveSchool:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:driveSchool:delete']"
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

        <!-- 添加或修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="省" prop="provinceId">
                            <el-input v-model="form.provinceId" placeholder="请输入省" />
                        </el-form-item>
                                                                                <el-form-item label="市" prop="cityId">
                            <el-input v-model="form.cityId" placeholder="请输入市" />
                        </el-form-item>
                                                                                <el-form-item label="区" prop="areaId">
                            <el-input v-model="form.areaId" placeholder="请输入区" />
                        </el-form-item>
                                                                                <el-form-item label="经度" prop="longitude">
                            <el-input v-model="form.longitude" placeholder="请输入经度" />
                        </el-form-item>
                                                                                <el-form-item label="纬度" prop="latitude">
                            <el-input v-model="form.latitude" placeholder="请输入纬度" />
                        </el-form-item>
                                                                                <el-form-item label="详细地址" prop="address">
                            <el-input v-model="form.address" placeholder="请输入详细地址" />
                        </el-form-item>
                                                                                <el-form-item label="联系人" prop="contacts">
                            <el-input v-model="form.contacts" placeholder="请输入联系人" />
                        </el-form-item>
                                                                                <el-form-item label="联系电话" prop="contactPhone">
                            <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
                        </el-form-item>
                                                                                <el-form-item label="报名单价" prop="enrollPrice">
                            <el-input v-model="form.enrollPrice" placeholder="请输入报名单价" />
                        </el-form-item>
                                                                                <el-form-item label="驾校名称" prop="schoolName">
                            <el-input v-model="form.schoolName" placeholder="请输入驾校名称" />
                        </el-form-item>
                                                                                <el-form-item label="成立时间" prop="establishTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.establishTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择成立时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="资质照片地址List" prop="seniorityPath">
                            <el-input v-model="form.seniorityPath" placeholder="请输入资质照片地址List" />
                        </el-form-item>
                                                                                <el-form-item label="驾校规模" prop="driveSchoolScale">
                            <el-input v-model="form.driveSchoolScale" placeholder="请输入驾校规模" />
                        </el-form-item>
                                                                                <el-form-item label="驾校简介" prop="schoolDescription">
                            <el-input v-model="form.schoolDescription" placeholder="请输入驾校简介" />
                        </el-form-item>
                                                                                <el-form-item label="是否可报名" prop="isEnroll">
                            <el-input v-model="form.isEnroll" placeholder="请输入是否可报名" />
                        </el-form-item>
                                                                                <el-form-item label="驾校后台管理用户id" prop="adminUserId">
                            <el-input v-model="form.adminUserId" placeholder="请输入驾校后台管理用户id" />
                        </el-form-item>
                                                                                <el-form-item label="状态（1-正常，2-停用）" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态（1-正常，2-停用）" />
                        </el-form-item>
                                                                                <el-form-item label="是否独立结算(0-否，1-是)" prop="isAloneSettlement">
                            <el-input v-model="form.isAloneSettlement" placeholder="请输入是否独立结算(0-否，1-是)" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="报名单价(vip报名费)" prop="vipEnrollPrice">
                            <el-input v-model="form.vipEnrollPrice" placeholder="请输入报名单价(vip报名费)" />
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
    import { listDriveSchool,listPageDriveSchool, getDriveSchool, delDriveSchool, addDriveSchool, updateDriveSchool, exportDriveSchool } from "@/api/admin/driveSchool";

    export default {
        name: "DriveSchool",
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
                // 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。表格数据
                    driveSchoolList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                address: undefined,
                                                contacts: undefined,
                                                contactPhone: undefined,
                                                enrollPrice: undefined,
                                                schoolName: undefined,
                                                establishTime: undefined,
                                                seniorityPath: undefined,
                                                driveSchoolScale: undefined,
                                                schoolDescription: undefined,
                                                isEnroll: undefined,
                                                adminUserId: undefined,
                                                status: undefined,
                                                isAloneSettlement: undefined,
                                                operatorId: undefined,
                                                vipEnrollPrice: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "不能为空", trigger: "blur" }
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
                                                longitude: [
                            { required: true, message: "经度不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "纬度不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "详细地址不能为空", trigger: "blur" }
                        ],
                                                contacts: [
                            { required: true, message: "联系人不能为空", trigger: "blur" }
                        ],
                                                contactPhone: [
                            { required: true, message: "联系电话不能为空", trigger: "blur" }
                        ],
                                                enrollPrice: [
                            { required: true, message: "报名单价不能为空", trigger: "blur" }
                        ],
                                                schoolName: [
                            { required: true, message: "驾校名称不能为空", trigger: "blur" }
                        ],
                                                establishTime: [
                            { required: true, message: "成立时间不能为空", trigger: "blur" }
                        ],
                                                seniorityPath: [
                            { required: true, message: "资质照片地址List不能为空", trigger: "blur" }
                        ],
                                                driveSchoolScale: [
                            { required: true, message: "驾校规模不能为空", trigger: "blur" }
                        ],
                                                schoolDescription: [
                            { required: true, message: "驾校简介不能为空", trigger: "blur" }
                        ],
                                                isEnroll: [
                            { required: true, message: "是否可报名不能为空", trigger: "blur" }
                        ],
                                                adminUserId: [
                            { required: true, message: "驾校后台管理用户id不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态（1-正常，2-停用）不能为空", trigger: "blur" }
                        ],
                                                isAloneSettlement: [
                            { required: true, message: "是否独立结算(0-否，1-是)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                vipEnrollPrice: [
                            { required: true, message: "报名单价(vip报名费)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。列表 */
            getListPage() {
                this.loading = true;
                listPageDriveSchool(this.queryParams).then(response => {
                    this.driveSchoolList = response.data.records;
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
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                address: undefined,
                                                contacts: undefined,
                                                contactPhone: undefined,
                                                enrollPrice: undefined,
                                                schoolName: undefined,
                                                establishTime: undefined,
                                                seniorityPath: undefined,
                                                driveSchoolScale: undefined,
                                                schoolDescription: undefined,
                                                isEnroll: undefined,
                                                adminUserId: undefined,
                                                status: undefined,
                                                isAloneSettlement: undefined,
                                                operatorId: undefined,
                                                vipEnrollPrice: undefined,
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
                this.title = "添加平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getDriveSchool(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateDriveSchool(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addDriveSchool(this.form).then(response => {
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
                    return delDriveSchool(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportDriveSchool(queryParams);
                }).then(data => {
                    this.download(data, "平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。");
                }).catch(function() {});
            }
        }
    };
</script>
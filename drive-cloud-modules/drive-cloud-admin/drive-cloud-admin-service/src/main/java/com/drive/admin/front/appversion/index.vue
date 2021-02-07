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
                                                                <el-form-item label="应用类型（1：学员；2：教练，3-运维）" prop="appType">
                        <el-input
                                v-model="queryParams.appType"
                                placeholder="请输入应用类型（1：学员；2：教练，3-运维）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="版本类型(ios,android)" prop="versionType">
                        <el-input
                                v-model="queryParams.versionType"
                                placeholder="请输入版本类型(ios,android)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="版本名称" prop="versionName">
                        <el-input
                                v-model="queryParams.versionName"
                                placeholder="请输入版本名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="版本号" prop="version">
                        <el-input
                                v-model="queryParams.version"
                                placeholder="请输入版本号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否强制更新" prop="isMustUpdate">
                        <el-input
                                v-model="queryParams.isMustUpdate"
                                placeholder="请输入是否强制更新"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="下载地址" prop="downloadLocation">
                        <el-input
                                v-model="queryParams.downloadLocation"
                                placeholder="请输入下载地址"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="平台密钥" prop="appKey">
                        <el-input
                                v-model="queryParams.appKey"
                                placeholder="请输入平台密钥"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="版本说明" prop="versionExplain">
                        <el-input
                                v-model="queryParams.versionExplain"
                                placeholder="请输入版本说明"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="下载次数" prop="downloadCount">
                        <el-input
                                v-model="queryParams.downloadCount"
                                placeholder="请输入下载次数"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建者" prop="createUser">
                        <el-input
                                v-model="queryParams.createUser"
                                placeholder="请输入创建者"
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
                                                                <el-form-item label="更新者" prop="updateUser">
                        <el-input
                                v-model="queryParams.updateUser"
                                placeholder="请输入更新者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="更新时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择更新时间">
                        </el-date-picker>
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
                    v-hasPermi="['admin:appVersion:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:appVersion:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:appVersion:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:appVersion:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="appVersionList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="应用类型（1：学员；2：教练，3-运维）" align="center" prop="appType" sortable="custom"/>
                                                                <el-table-column label="版本类型(ios,android)" align="center" prop="versionType" sortable="custom"/>
                                                                <el-table-column label="版本名称" align="center" prop="versionName" sortable="custom"/>
                                                                <el-table-column label="版本号" align="center" prop="version" sortable="custom"/>
                                                                <el-table-column label="是否强制更新" align="center" prop="isMustUpdate" sortable="custom"/>
                                                                <el-table-column label="下载地址" align="center" prop="downloadLocation" sortable="custom"/>
                                                                <el-table-column label="平台密钥" align="center" prop="appKey" sortable="custom"/>
                                                                <el-table-column label="版本说明" align="center" prop="versionExplain" sortable="custom"/>
                                                                <el-table-column label="下载次数" align="center" prop="downloadCount" sortable="custom"/>
                                                                <el-table-column label="创建者" align="center" prop="createUser" sortable="custom"/>
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="更新者" align="center" prop="updateUser" sortable="custom"/>
                                                                <el-table-column label="更新时间" align="center" prop="updateTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:appVersion:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:appVersion:delete']"
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

        <!-- 添加或修改平台应用版本表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="应用类型（1：学员；2：教练，3-运维）" prop="appType">
                            <el-input v-model="form.appType" placeholder="请输入应用类型（1：学员；2：教练，3-运维）" />
                        </el-form-item>
                                                                                <el-form-item label="版本类型(ios,android)" prop="versionType">
                            <el-input v-model="form.versionType" placeholder="请输入版本类型(ios,android)" />
                        </el-form-item>
                                                                                <el-form-item label="版本名称" prop="versionName">
                            <el-input v-model="form.versionName" placeholder="请输入版本名称" />
                        </el-form-item>
                                                                                <el-form-item label="版本号" prop="version">
                            <el-input v-model="form.version" placeholder="请输入版本号" />
                        </el-form-item>
                                                                                <el-form-item label="是否强制更新" prop="isMustUpdate">
                            <el-input v-model="form.isMustUpdate" placeholder="请输入是否强制更新" />
                        </el-form-item>
                                                                                <el-form-item label="下载地址" prop="downloadLocation">
                            <el-input v-model="form.downloadLocation" placeholder="请输入下载地址" />
                        </el-form-item>
                                                                                <el-form-item label="平台密钥" prop="appKey">
                            <el-input v-model="form.appKey" placeholder="请输入平台密钥" />
                        </el-form-item>
                                                                                <el-form-item label="版本说明" prop="versionExplain">
                            <el-input v-model="form.versionExplain" placeholder="请输入版本说明" />
                        </el-form-item>
                                                                                <el-form-item label="下载次数" prop="downloadCount">
                            <el-input v-model="form.downloadCount" placeholder="请输入下载次数" />
                        </el-form-item>
                                                                                <el-form-item label="创建者" prop="createUser">
                            <el-input v-model="form.createUser" placeholder="请输入创建者" />
                        </el-form-item>
                                                                                <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="更新者" prop="updateUser">
                            <el-input v-model="form.updateUser" placeholder="请输入更新者" />
                        </el-form-item>
                                                                                <el-form-item label="更新时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择更新时间">
                            </el-date-picker>
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
    import { listAppVersion,listPageAppVersion, getAppVersion, delAppVersion, addAppVersion, updateAppVersion, exportAppVersion } from "@/api/admin/appVersion";

    export default {
        name: "AppVersion",
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
                // 平台应用版本表表格数据
                    appVersionList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                appType: undefined,
                                                versionType: undefined,
                                                versionName: undefined,
                                                version: undefined,
                                                isMustUpdate: undefined,
                                                downloadLocation: undefined,
                                                appKey: undefined,
                                                versionExplain: undefined,
                                                downloadCount: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                appType: [
                            { required: true, message: "应用类型（1：学员；2：教练，3-运维）不能为空", trigger: "blur" }
                        ],
                                                versionType: [
                            { required: true, message: "版本类型(ios,android)不能为空", trigger: "blur" }
                        ],
                                                versionName: [
                            { required: true, message: "版本名称不能为空", trigger: "blur" }
                        ],
                                                version: [
                            { required: true, message: "版本号不能为空", trigger: "blur" }
                        ],
                                                isMustUpdate: [
                            { required: true, message: "是否强制更新不能为空", trigger: "blur" }
                        ],
                                                downloadLocation: [
                            { required: true, message: "下载地址不能为空", trigger: "blur" }
                        ],
                                                appKey: [
                            { required: true, message: "平台密钥不能为空", trigger: "blur" }
                        ],
                                                versionExplain: [
                            { required: true, message: "版本说明不能为空", trigger: "blur" }
                        ],
                                                downloadCount: [
                            { required: true, message: "下载次数不能为空", trigger: "blur" }
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
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询平台应用版本表列表 */
            getListPage() {
                this.loading = true;
                listPageAppVersion(this.queryParams).then(response => {
                    this.appVersionList = response.data.records;
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
                                                appType: undefined,
                                                versionType: undefined,
                                                versionName: undefined,
                                                version: undefined,
                                                isMustUpdate: undefined,
                                                downloadLocation: undefined,
                                                appKey: undefined,
                                                versionExplain: undefined,
                                                downloadCount: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
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
                this.title = "添加平台应用版本表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getAppVersion(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改平台应用版本表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateAppVersion(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addAppVersion(this.form).then(response => {
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
                    return delAppVersion(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有平台应用版本表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportAppVersion(queryParams);
                }).then(data => {
                    this.download(data, "平台应用版本表");
                }).catch(function() {});
            }
        }
    };
</script>
<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                <el-form-item label="任务编号" prop="stTaskId">
                        <el-input
                                v-model="queryParams.stTaskId"
                                placeholder="请输入任务编号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="任务名称" prop="stTaskName">
                        <el-input
                                v-model="queryParams.stTaskName"
                                placeholder="请输入任务名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="任务类" prop="stJobClass">
                        <el-input
                                v-model="queryParams.stJobClass"
                                placeholder="请输入任务类"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="CRON表达式" prop="stCronExpression">
                        <el-input
                                v-model="queryParams.stCronExpression"
                                placeholder="请输入CRON表达式"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="状态" prop="stState">
                        <el-input
                                v-model="queryParams.stState"
                                placeholder="请输入状态"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="记入日志类型" prop="stInputLogType">
                        <el-input
                                v-model="queryParams.stInputLogType"
                                placeholder="请输入记入日志类型"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="备注" prop="stRemark">
                        <el-input
                                v-model="queryParams.stRemark"
                                placeholder="请输入备注"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建人" prop="createUser">
                        <el-input
                                v-model="queryParams.createUser"
                                placeholder="请输入创建人"
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
                                                                <el-form-item label="修改人" prop="updateUser">
                        <el-input
                                v-model="queryParams.updateUser"
                                placeholder="请输入修改人"
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
                    v-hasPermi="['admin:sysTask:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:sysTask:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:sysTask:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:sysTask:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="sysTaskList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="任务编号" align="center" prop="stTaskId" sortable="custom"/>
                                                                <el-table-column label="任务名称" align="center" prop="stTaskName" sortable="custom"/>
                                                                <el-table-column label="任务类" align="center" prop="stJobClass" sortable="custom"/>
                                                                <el-table-column label="CRON表达式" align="center" prop="stCronExpression" sortable="custom"/>
                                                                <el-table-column label="状态" align="center" prop="stState" sortable="custom"/>
                                                                <el-table-column label="记入日志类型" align="center" prop="stInputLogType" sortable="custom"/>
                                                                <el-table-column label="备注" align="center" prop="stRemark" sortable="custom"/>
                                                                <el-table-column label="创建人" align="center" prop="createUser" sortable="custom"/>
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
                                                                <el-table-column label="修改人" align="center" prop="updateUser" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:sysTask:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:sysTask:delete']"
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

        <!-- 添加或修改系统任务表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="任务编号" prop="stTaskId">
                            <el-input v-model="form.stTaskId" placeholder="请输入任务编号" />
                        </el-form-item>
                                                                                <el-form-item label="任务名称" prop="stTaskName">
                            <el-input v-model="form.stTaskName" placeholder="请输入任务名称" />
                        </el-form-item>
                                                                                <el-form-item label="任务类" prop="stJobClass">
                            <el-input v-model="form.stJobClass" placeholder="请输入任务类" />
                        </el-form-item>
                                                                                <el-form-item label="CRON表达式" prop="stCronExpression">
                            <el-input v-model="form.stCronExpression" placeholder="请输入CRON表达式" />
                        </el-form-item>
                                                                                <el-form-item label="状态" prop="stState">
                            <el-input v-model="form.stState" placeholder="请输入状态" />
                        </el-form-item>
                                                                                <el-form-item label="记入日志类型" prop="stInputLogType">
                            <el-input v-model="form.stInputLogType" placeholder="请输入记入日志类型" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="stRemark">
                            <el-input v-model="form.stRemark" placeholder="请输入备注" />
                        </el-form-item>
                                                                                <el-form-item label="创建人" prop="createUser">
                            <el-input v-model="form.createUser" placeholder="请输入创建人" />
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
                                                                                <el-form-item label="修改人" prop="updateUser">
                            <el-input v-model="form.updateUser" placeholder="请输入修改人" />
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
    import { listSysTask,listPageSysTask, getSysTask, delSysTask, addSysTask, updateSysTask, exportSysTask } from "@/api/admin/sysTask";

    export default {
        name: "SysTask",
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
                // 系统任务表表格数据
                    sysTaskList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                stTaskId: undefined,
                                                stTaskName: undefined,
                                                stJobClass: undefined,
                                                stCronExpression: undefined,
                                                stState: undefined,
                                                stInputLogType: undefined,
                                                stRemark: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                updateUser: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                stTaskId: [
                            { required: true, message: "任务编号不能为空", trigger: "blur" }
                        ],
                                                stTaskName: [
                            { required: true, message: "任务名称不能为空", trigger: "blur" }
                        ],
                                                stJobClass: [
                            { required: true, message: "任务类不能为空", trigger: "blur" }
                        ],
                                                stCronExpression: [
                            { required: true, message: "CRON表达式不能为空", trigger: "blur" }
                        ],
                                                stState: [
                            { required: true, message: "状态不能为空", trigger: "blur" }
                        ],
                                                stInputLogType: [
                            { required: true, message: "记入日志类型不能为空", trigger: "blur" }
                        ],
                                                stRemark: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建人不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "修改人不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询系统任务表列表 */
            getListPage() {
                this.loading = true;
                listPageSysTask(this.queryParams).then(response => {
                    this.sysTaskList = response.data.records;
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
                                                stTaskId: undefined,
                                                stTaskName: undefined,
                                                stJobClass: undefined,
                                                stCronExpression: undefined,
                                                stState: undefined,
                                                stInputLogType: undefined,
                                                stRemark: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                updateUser: undefined,
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
                this.ids = selection.map(item => item.stTaskId)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加系统任务表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const stTaskId = row.stTaskId || this.ids
                getSysTask(stTaskId).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改系统任务表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.stTaskId != undefined) {
                            updateSysTask(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addSysTask(this.form).then(response => {
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
                const stTaskIds = row.stTaskId || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delSysTask(stTaskIds);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有系统任务表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportSysTask(queryParams);
                }).then(data => {
                    this.download(data, "系统任务表");
                }).catch(function() {});
            }
        }
    };
</script>
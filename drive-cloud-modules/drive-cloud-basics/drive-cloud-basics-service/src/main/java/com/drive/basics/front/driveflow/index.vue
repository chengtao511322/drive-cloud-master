<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                            <el-form-item label="id" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="标题" prop="title">
                        <el-input
                                v-model="queryParams.title"
                                placeholder="请输入标题"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="" prop="context">
                        <el-input
                                v-model="queryParams.context"
                                placeholder="请输入"
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
                                                                <el-form-item label="修改时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择修改时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                                                        <el-form-item label="栏目ID" prop="channelId">
                        <el-input
                                v-model="queryParams.channelId"
                                placeholder="请输入栏目ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="添加者" prop="addUser">
                        <el-input
                                v-model="queryParams.addUser"
                                placeholder="请输入添加者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="运营位置 ，存放省市区编码，多个用逗号隔开" prop="operatingPosition">
                        <el-input
                                v-model="queryParams.operatingPosition"
                                placeholder="请输入运营位置 ，存放省市区编码，多个用逗号隔开"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="租户ID" prop="tenantId">
                        <el-input
                                v-model="queryParams.tenantId"
                                placeholder="请输入租户ID"
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
                    v-hasPermi="['basics:driveFlow:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['basics:driveFlow:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['basics:driveFlow:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['basics:driveFlow:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="driveFlowList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="id" align="center" prop="id" sortable="custom"/>
                                                                <el-table-column label="标题" align="center" prop="title" sortable="custom"/>
                                                                <el-table-column label="" align="center" prop="context" sortable="custom"/>
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="修改时间" align="center" prop="updateTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="删除状态 0未删除  1 ：删除" align="center" prop="isDelete" sortable="custom"/>
                                                                <el-table-column label="启用状态 0  未启用 1 已经启用" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="栏目ID" align="center" prop="channelId" sortable="custom"/>
                                                                <el-table-column label="排序" align="center" prop="sort" sortable="custom"/>
                                                                <el-table-column label="添加者" align="center" prop="addUser" sortable="custom"/>
                                                                <el-table-column label="运营位置 ，存放省市区编码，多个用逗号隔开" align="center" prop="operatingPosition" sortable="custom"/>
                                                                <el-table-column label="租户ID" align="center" prop="tenantId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['basics:driveFlow:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['basics:driveFlow:delete']"
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

        <!-- 添加或修改流程信息管理对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="标题" prop="title">
                            <el-input v-model="form.title" placeholder="请输入标题" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="context">
                            <el-input v-model="form.context" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="修改时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择修改时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="删除状态 0未删除  1 ：删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入删除状态 0未删除  1 ：删除" />
                        </el-form-item>
                                                                                <el-form-item label="启用状态 0  未启用 1 已经启用" prop="status">
                            <el-input v-model="form.status" placeholder="请输入启用状态 0  未启用 1 已经启用" />
                        </el-form-item>
                                                                                <el-form-item label="栏目ID" prop="channelId">
                            <el-input v-model="form.channelId" placeholder="请输入栏目ID" />
                        </el-form-item>
                                                                                <el-form-item label="排序" prop="sort">
                            <el-input v-model="form.sort" placeholder="请输入排序" />
                        </el-form-item>
                                                                                <el-form-item label="添加者" prop="addUser">
                            <el-input v-model="form.addUser" placeholder="请输入添加者" />
                        </el-form-item>
                                                                                <el-form-item label="运营位置 ，存放省市区编码，多个用逗号隔开" prop="operatingPosition">
                            <el-input v-model="form.operatingPosition" placeholder="请输入运营位置 ，存放省市区编码，多个用逗号隔开" />
                        </el-form-item>
                                                                                <el-form-item label="租户ID" prop="tenantId">
                            <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
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
    import { listDriveFlow,listPageDriveFlow, getDriveFlow, delDriveFlow, addDriveFlow, updateDriveFlow, exportDriveFlow } from "@/api/basics/driveFlow";

    export default {
        name: "DriveFlow",
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
                // 流程信息管理表格数据
                    driveFlowList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                title: undefined,
                                                context: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                                status: undefined,
                                                channelId: undefined,
                                                sort: undefined,
                                                addUser: undefined,
                                                operatingPosition: undefined,
                                                tenantId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                title: [
                            { required: true, message: "标题不能为空", trigger: "blur" }
                        ],
                                                context: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 0未删除  1 ：删除不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "启用状态 0  未启用 1 已经启用不能为空", trigger: "blur" }
                        ],
                                                channelId: [
                            { required: true, message: "栏目ID不能为空", trigger: "blur" }
                        ],
                                                sort: [
                            { required: true, message: "排序不能为空", trigger: "blur" }
                        ],
                                                addUser: [
                            { required: true, message: "添加者不能为空", trigger: "blur" }
                        ],
                                                operatingPosition: [
                            { required: true, message: "运营位置 ，存放省市区编码，多个用逗号隔开不能为空", trigger: "blur" }
                        ],
                                                tenantId: [
                            { required: true, message: "租户ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询流程信息管理列表 */
            getListPage() {
                this.loading = true;
                listPageDriveFlow(this.queryParams).then(response => {
                    this.driveFlowList = response.data.records;
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
                                                title: undefined,
                                                context: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                                status: undefined,
                                                channelId: undefined,
                                                sort: undefined,
                                                addUser: undefined,
                                                operatingPosition: undefined,
                                                tenantId: undefined,
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
                this.ids = selection.map(item => item.${pkColumn.propertyName})
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加流程信息管理";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const ${pkColumn.propertyName} = row.${pkColumn.propertyName} || this.ids
                getDriveFlow(${pkColumn.propertyName}).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改流程信息管理";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.${pkColumn.propertyName} != undefined) {
                            updateDriveFlow(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addDriveFlow(this.form).then(response => {
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
                const ${pkColumn.propertyName}s = row.${pkColumn.propertyName} || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delDriveFlow(${pkColumn.propertyName}s);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有流程信息管理数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportDriveFlow(queryParams);
                }).then(data => {
                    this.download(data, "流程信息管理");
                }).catch(function() {});
            }
        }
    };
</script>
<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                            <el-form-item label="主键" prop="codeId">
                        <el-input
                                v-model="queryParams.codeId"
                                placeholder="请输入主键"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="分类" prop="category">
                        <el-input
                                v-model="queryParams.category"
                                placeholder="请输入分类"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="值" prop="codeValue">
                        <el-input
                                v-model="queryParams.codeValue"
                                placeholder="请输入值"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="显示项" prop="disText">
                        <el-input
                                v-model="queryParams.disText"
                                placeholder="请输入显示项"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="标签" prop="tags">
                        <el-input
                                v-model="queryParams.tags"
                                placeholder="请输入标签"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="排序" prop="odBy">
                        <el-input
                                v-model="queryParams.odBy"
                                placeholder="请输入排序"
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
                                                                <el-form-item label="父项" prop="supCodeId">
                        <el-input
                                v-model="queryParams.supCodeId"
                                placeholder="请输入父项"
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
                                                                <el-form-item label="更新人" prop="updateUser">
                        <el-input
                                v-model="queryParams.updateUser"
                                placeholder="请输入更新人"
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
                    v-hasPermi="['admin:code:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:code:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:code:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:code:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="codeList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="主键" align="center" prop="codeId" sortable="custom"/>
                                                                <el-table-column label="分类" align="center" prop="category" sortable="custom"/>
                                                                <el-table-column label="值" align="center" prop="codeValue" sortable="custom"/>
                                                                <el-table-column label="显示项" align="center" prop="disText" sortable="custom"/>
                                                                <el-table-column label="标签" align="center" prop="tags" sortable="custom"/>
                                                                <el-table-column label="排序" align="center" prop="odBy" sortable="custom"/>
                                                                <el-table-column label="备注" align="center" prop="remarks" sortable="custom"/>
                                                                <el-table-column label="父项" align="center" prop="supCodeId" sortable="custom"/>
                                                                <el-table-column label="创建人" align="center" prop="createUser" sortable="custom"/>
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="更新人" align="center" prop="updateUser" sortable="custom"/>
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
                        v-hasPermi="['admin:code:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:code:delete']"
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

        <!-- 添加或修改字典表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键" prop="codeId">
                            <el-input v-model="form.codeId" placeholder="请输入主键" />
                        </el-form-item>
                                                                                <el-form-item label="分类" prop="category">
                            <el-input v-model="form.category" placeholder="请输入分类" />
                        </el-form-item>
                                                                                <el-form-item label="值" prop="codeValue">
                            <el-input v-model="form.codeValue" placeholder="请输入值" />
                        </el-form-item>
                                                                                <el-form-item label="显示项" prop="disText">
                            <el-input v-model="form.disText" placeholder="请输入显示项" />
                        </el-form-item>
                                                                                <el-form-item label="标签" prop="tags">
                            <el-input v-model="form.tags" placeholder="请输入标签" />
                        </el-form-item>
                                                                                <el-form-item label="排序" prop="odBy">
                            <el-input v-model="form.odBy" placeholder="请输入排序" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入备注" />
                        </el-form-item>
                                                                                <el-form-item label="父项" prop="supCodeId">
                            <el-input v-model="form.supCodeId" placeholder="请输入父项" />
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
                                                                                <el-form-item label="更新人" prop="updateUser">
                            <el-input v-model="form.updateUser" placeholder="请输入更新人" />
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
    import { listCode,listPageCode, getCode, delCode, addCode, updateCode, exportCode } from "@/api/admin/code";

    export default {
        name: "Code",
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
                // 字典表表格数据
                    codeList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                codeId: undefined,
                                                category: undefined,
                                                codeValue: undefined,
                                                disText: undefined,
                                                tags: undefined,
                                                odBy: undefined,
                                                remarks: undefined,
                                                supCodeId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                codeId: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                category: [
                            { required: true, message: "分类不能为空", trigger: "blur" }
                        ],
                                                codeValue: [
                            { required: true, message: "值不能为空", trigger: "blur" }
                        ],
                                                disText: [
                            { required: true, message: "显示项不能为空", trigger: "blur" }
                        ],
                                                tags: [
                            { required: true, message: "标签不能为空", trigger: "blur" }
                        ],
                                                odBy: [
                            { required: true, message: "排序不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                supCodeId: [
                            { required: true, message: "父项不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建人不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "更新人不能为空", trigger: "blur" }
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
            /** 查询字典表列表 */
            getListPage() {
                this.loading = true;
                listPageCode(this.queryParams).then(response => {
                    this.codeList = response.data.records;
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
                                                codeId: undefined,
                                                category: undefined,
                                                codeValue: undefined,
                                                disText: undefined,
                                                tags: undefined,
                                                odBy: undefined,
                                                remarks: undefined,
                                                supCodeId: undefined,
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
                this.ids = selection.map(item => item.codeId)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加字典表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const codeId = row.codeId || this.ids
                getCode(codeId).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改字典表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.codeId != undefined) {
                            updateCode(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addCode(this.form).then(response => {
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
                const codeIds = row.codeId || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delCode(codeIds);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有字典表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportCode(queryParams);
                }).then(data => {
                    this.download(data, "字典表");
                }).catch(function() {});
            }
        }
    };
</script>
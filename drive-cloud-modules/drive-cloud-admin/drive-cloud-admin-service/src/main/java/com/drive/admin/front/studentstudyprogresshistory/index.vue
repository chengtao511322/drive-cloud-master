<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                <el-form-item label="主键唯一id" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入主键唯一id"
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
                                                                <el-form-item label="科目类型" prop="testEnrollSubject">
                        <el-input
                                v-model="queryParams.testEnrollSubject"
                                placeholder="请输入科目类型"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="考试时间" prop="testEnrollTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.testEnrollTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择考试时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="考试场地ID" prop="testCoachingGridId">
                        <el-input
                                v-model="queryParams.testCoachingGridId"
                                placeholder="请输入考试场地ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）" prop="testResultType">
                        <el-input
                                v-model="queryParams.testResultType"
                                placeholder="请输入考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="时间抽排序" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择时间抽排序">
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
                    v-hasPermi="['admin:studentStudyProgressHistory:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:studentStudyProgressHistory:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:studentStudyProgressHistory:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:studentStudyProgressHistory:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="studentStudyProgressHistoryList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="主键唯一id" align="center" prop="id" sortable="custom"/>
                                                                <el-table-column label="学员ID" align="center" prop="studentId" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="testEnrollSubject" sortable="custom"/>
                                                                <el-table-column label="考试时间" align="center" prop="testEnrollTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.testEnrollTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="考试场地ID" align="center" prop="testCoachingGridId" sortable="custom"/>
                                                                <el-table-column label="考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）" align="center" prop="testResultType" sortable="custom"/>
                                                                <el-table-column label="时间抽排序" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:studentStudyProgressHistory:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:studentStudyProgressHistory:delete']"
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

        <!-- 添加或修改学员学车报名单对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键唯一id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键唯一id" />
                        </el-form-item>
                                                                                <el-form-item label="学员ID" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员ID" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型" prop="testEnrollSubject">
                            <el-input v-model="form.testEnrollSubject" placeholder="请输入科目类型" />
                        </el-form-item>
                                                                                <el-form-item label="考试时间" prop="testEnrollTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.testEnrollTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择考试时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="考试场地ID" prop="testCoachingGridId">
                            <el-input v-model="form.testCoachingGridId" placeholder="请输入考试场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）" prop="testResultType">
                            <el-input v-model="form.testResultType" placeholder="请输入考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）" />
                        </el-form-item>
                                                                                <el-form-item label="时间抽排序" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择时间抽排序">
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
    import { listStudentStudyProgressHistory,listPageStudentStudyProgressHistory, getStudentStudyProgressHistory, delStudentStudyProgressHistory, addStudentStudyProgressHistory, updateStudentStudyProgressHistory, exportStudentStudyProgressHistory } from "@/api/admin/studentStudyProgressHistory";

    export default {
        name: "StudentStudyProgressHistory",
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
                    studentStudyProgressHistoryList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                studentId: undefined,
                                                testEnrollSubject: undefined,
                                                testEnrollTime: undefined,
                                                testCoachingGridId: undefined,
                                                testResultType: undefined,
                                                createTime: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键唯一id不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员ID不能为空", trigger: "blur" }
                        ],
                                                testEnrollSubject: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                testEnrollTime: [
                            { required: true, message: "考试时间不能为空", trigger: "blur" }
                        ],
                                                testCoachingGridId: [
                            { required: true, message: "考试场地ID不能为空", trigger: "blur" }
                        ],
                                                testResultType: [
                            { required: true, message: "考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "时间抽排序不能为空", trigger: "blur" }
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
                listPageStudentStudyProgressHistory(this.queryParams).then(response => {
                    this.studentStudyProgressHistoryList = response.data.records;
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
                                                studentId: undefined,
                                                testEnrollSubject: undefined,
                                                testEnrollTime: undefined,
                                                testCoachingGridId: undefined,
                                                testResultType: undefined,
                                                createTime: undefined,
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
                this.title = "添加学员学车报名单";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getStudentStudyProgressHistory(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学员学车报名单";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateStudentStudyProgressHistory(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addStudentStudyProgressHistory(this.form).then(response => {
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
                    return delStudentStudyProgressHistory(ids);
                }).then(() => {
                    this.getListPage();
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
                    return exportStudentStudyProgressHistory(queryParams);
                }).then(data => {
                    this.download(data, "学员学车报名单");
                }).catch(function() {});
            }
        }
    };
</script>
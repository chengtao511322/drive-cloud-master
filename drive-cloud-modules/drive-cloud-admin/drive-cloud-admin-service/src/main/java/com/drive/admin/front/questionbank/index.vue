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
                                                                                            <el-form-item label="科目类型" prop="subjectType">
                        <el-input
                                v-model="queryParams.subjectType"
                                placeholder="请输入科目类型"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="题目章节" prop="questionChapter">
                        <el-input
                                v-model="queryParams.questionChapter"
                                placeholder="请输入题目章节"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="题型（知识点类型）" prop="questionType">
                        <el-input
                                v-model="queryParams.questionType"
                                placeholder="请输入题型（知识点类型）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="媒介路径（图片或者视频）" prop="mediaUrl">
                        <el-input
                                v-model="queryParams.mediaUrl"
                                placeholder="请输入媒介路径（图片或者视频）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="题目内容" prop="questionContent">
                        <el-input
                                v-model="queryParams.questionContent"
                                placeholder="请输入题目内容"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否多选" prop="isMultipleAnswer">
                        <el-input
                                v-model="queryParams.isMultipleAnswer"
                                placeholder="请输入是否多选"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="解答说明" prop="answerRemarks">
                        <el-input
                                v-model="queryParams.answerRemarks"
                                placeholder="请输入解答说明"
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
                                                                <el-form-item label="难度(1,2,3,4,5)" prop="difficulty">
                        <el-input
                                v-model="queryParams.difficulty"
                                placeholder="请输入难度(1,2,3,4,5)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="媒介类型(1-图片，2-视频)" prop="isView">
                        <el-input
                                v-model="queryParams.isView"
                                placeholder="请输入媒介类型(1-图片，2-视频)"
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
                    v-hasPermi="['admin:questionBank:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:questionBank:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:questionBank:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:questionBank:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="questionBankList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="题号" align="center" prop="questionNo" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="subjectType" sortable="custom"/>
                                                                <el-table-column label="题目章节" align="center" prop="questionChapter" sortable="custom"/>
                                                                <el-table-column label="题型（知识点类型）" align="center" prop="questionType" sortable="custom"/>
                                                                <el-table-column label="媒介路径（图片或者视频）" align="center" prop="mediaUrl" sortable="custom"/>
                                                                <el-table-column label="题目内容" align="center" prop="questionContent" sortable="custom"/>
                                                                <el-table-column label="是否多选" align="center" prop="isMultipleAnswer" sortable="custom"/>
                                                                <el-table-column label="答题限时（单位秒）" align="center" prop="answerTimeLimit" sortable="custom"/>
                                                                <el-table-column label="解答说明" align="center" prop="answerRemarks" sortable="custom"/>
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
                                                                <el-table-column label="难度(1,2,3,4,5)" align="center" prop="difficulty" sortable="custom"/>
                                                                <el-table-column label="媒介类型(1-图片，2-视频)" align="center" prop="isView" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:questionBank:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:questionBank:delete']"
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

        <!-- 添加或修改平台题库表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="题号" prop="questionNo">
                            <el-input v-model="form.questionNo" placeholder="请输入题号" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型" prop="subjectType">
                            <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
                        </el-form-item>
                                                                                <el-form-item label="题目章节" prop="questionChapter">
                            <el-input v-model="form.questionChapter" placeholder="请输入题目章节" />
                        </el-form-item>
                                                                                <el-form-item label="题型（知识点类型）" prop="questionType">
                            <el-input v-model="form.questionType" placeholder="请输入题型（知识点类型）" />
                        </el-form-item>
                                                                                <el-form-item label="媒介路径（图片或者视频）" prop="mediaUrl">
                            <el-input v-model="form.mediaUrl" placeholder="请输入媒介路径（图片或者视频）" />
                        </el-form-item>
                                                                                <el-form-item label="题目内容" prop="questionContent">
                            <el-input v-model="form.questionContent" placeholder="请输入题目内容" />
                        </el-form-item>
                                                                                <el-form-item label="是否多选" prop="isMultipleAnswer">
                            <el-input v-model="form.isMultipleAnswer" placeholder="请输入是否多选" />
                        </el-form-item>
                                                                                <el-form-item label="答题限时（单位秒）" prop="answerTimeLimit">
                            <el-input v-model="form.answerTimeLimit" placeholder="请输入答题限时（单位秒）" />
                        </el-form-item>
                                                                                <el-form-item label="解答说明" prop="answerRemarks">
                            <el-input v-model="form.answerRemarks" placeholder="请输入解答说明" />
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
                                                                                <el-form-item label="难度(1,2,3,4,5)" prop="difficulty">
                            <el-input v-model="form.difficulty" placeholder="请输入难度(1,2,3,4,5)" />
                        </el-form-item>
                                                                                <el-form-item label="媒介类型(1-图片，2-视频)" prop="isView">
                            <el-input v-model="form.isView" placeholder="请输入媒介类型(1-图片，2-视频)" />
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
    import { listQuestionBank,listPageQuestionBank, getQuestionBank, delQuestionBank, addQuestionBank, updateQuestionBank, exportQuestionBank } from "@/api/admin/questionBank";

    export default {
        name: "QuestionBank",
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
                // 平台题库表表格数据
                    questionBankList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                questionNo: undefined,
                                                subjectType: undefined,
                                                questionChapter: undefined,
                                                questionType: undefined,
                                                mediaUrl: undefined,
                                                questionContent: undefined,
                                                isMultipleAnswer: undefined,
                                                answerTimeLimit: undefined,
                                                answerRemarks: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
                                                difficulty: undefined,
                                                isView: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                questionNo: [
                            { required: true, message: "题号不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                questionChapter: [
                            { required: true, message: "题目章节不能为空", trigger: "blur" }
                        ],
                                                questionType: [
                            { required: true, message: "题型（知识点类型）不能为空", trigger: "blur" }
                        ],
                                                mediaUrl: [
                            { required: true, message: "媒介路径（图片或者视频）不能为空", trigger: "blur" }
                        ],
                                                questionContent: [
                            { required: true, message: "题目内容不能为空", trigger: "blur" }
                        ],
                                                isMultipleAnswer: [
                            { required: true, message: "是否多选不能为空", trigger: "blur" }
                        ],
                                                answerTimeLimit: [
                            { required: true, message: "答题限时（单位秒）不能为空", trigger: "blur" }
                        ],
                                                answerRemarks: [
                            { required: true, message: "解答说明不能为空", trigger: "blur" }
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
                                                difficulty: [
                            { required: true, message: "难度(1,2,3,4,5)不能为空", trigger: "blur" }
                        ],
                                                isView: [
                            { required: true, message: "媒介类型(1-图片，2-视频)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询平台题库表列表 */
            getListPage() {
                this.loading = true;
                listPageQuestionBank(this.queryParams).then(response => {
                    this.questionBankList = response.data.records;
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
                                                questionNo: undefined,
                                                subjectType: undefined,
                                                questionChapter: undefined,
                                                questionType: undefined,
                                                mediaUrl: undefined,
                                                questionContent: undefined,
                                                isMultipleAnswer: undefined,
                                                answerTimeLimit: undefined,
                                                answerRemarks: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
                                                difficulty: undefined,
                                                isView: undefined,
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
                this.title = "添加平台题库表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getQuestionBank(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改平台题库表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateQuestionBank(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addQuestionBank(this.form).then(response => {
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
                    return delQuestionBank(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有平台题库表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportQuestionBank(queryParams);
                }).then(data => {
                    this.download(data, "平台题库表");
                }).catch(function() {});
            }
        }
    };
</script>
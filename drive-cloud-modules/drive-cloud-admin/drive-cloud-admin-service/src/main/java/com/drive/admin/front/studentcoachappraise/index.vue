<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            <el-form-item label="编号" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入编号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="订单号" prop="orderNo">
                        <el-input
                                v-model="queryParams.orderNo"
                                placeholder="请输入订单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="教练课程ID" prop="classId">
                        <el-input
                                v-model="queryParams.classId"
                                placeholder="请输入教练课程ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="报名单号（考试，学车）" prop="enrollNo">
                        <el-input
                                v-model="queryParams.enrollNo"
                                placeholder="请输入报名单号（考试，学车）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="评价用户id（学员id）" prop="studentId">
                        <el-input
                                v-model="queryParams.studentId"
                                placeholder="请输入评价用户id（学员id）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员评价内容" prop="appraiseReplyContent">
                        <el-input
                                v-model="queryParams.appraiseReplyContent"
                                placeholder="请输入学员评价内容"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="学员评价时间" prop="appraiseReplyTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.appraiseReplyTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择学员评价时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="回复内容" prop="replyContent">
                        <el-input
                                v-model="queryParams.replyContent"
                                placeholder="请输入回复内容"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="回复时间" prop="replyTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.replyTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择回复时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="被评价用户id" prop="replyId">
                        <el-input
                                v-model="queryParams.replyId"
                                placeholder="请输入被评价用户id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="被评价用户类型（2-教练；3-运维）" prop="replyType">
                        <el-input
                                v-model="queryParams.replyType"
                                placeholder="请输入被评价用户类型（2-教练；3-运维）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="被评价用户头像" prop="replyHeadUrl">
                        <el-input
                                v-model="queryParams.replyHeadUrl"
                                placeholder="请输入被评价用户头像"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员头像" prop="studentHeadUrl">
                        <el-input
                                v-model="queryParams.studentHeadUrl"
                                placeholder="请输入学员头像"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员评价类型（1-评价 2-投诉）" prop="appraiseType">
                        <el-input
                                v-model="queryParams.appraiseType"
                                placeholder="请输入学员评价类型（1-评价 2-投诉）"
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
                    v-hasPermi="['admin:studentCoachAppraise:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:studentCoachAppraise:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:studentCoachAppraise:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:studentCoachAppraise:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="studentCoachAppraiseList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="编号" align="center" prop="id" sortable="custom"/>
                                                                <el-table-column label="订单号" align="center" prop="orderNo" sortable="custom"/>
                                                                <el-table-column label="教练课程ID" align="center" prop="classId" sortable="custom"/>
                                                                <el-table-column label="报名单号（考试，学车）" align="center" prop="enrollNo" sortable="custom"/>
                                                                <el-table-column label="评价用户id（学员id）" align="center" prop="studentId" sortable="custom"/>
                                                                <el-table-column label="学员评价内容" align="center" prop="appraiseReplyContent" sortable="custom"/>
                                                                <el-table-column label="学员评价分数" align="center" prop="appraiseReplyGrade" sortable="custom"/>
                                                                <el-table-column label="学员评价时间" align="center" prop="appraiseReplyTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.appraiseReplyTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="回复内容" align="center" prop="replyContent" sortable="custom"/>
                                                                <el-table-column label="回复时间" align="center" prop="replyTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.replyTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="被评价用户id" align="center" prop="replyId" sortable="custom"/>
                                                                <el-table-column label="被评价用户类型（2-教练；3-运维）" align="center" prop="replyType" sortable="custom"/>
                                                                <el-table-column label="被评价用户头像" align="center" prop="replyHeadUrl" sortable="custom"/>
                                                                <el-table-column label="学员头像" align="center" prop="studentHeadUrl" sortable="custom"/>
                                                                <el-table-column label="学员评价类型（1-评价 2-投诉）" align="center" prop="appraiseType" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:studentCoachAppraise:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:studentCoachAppraise:delete']"
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

        <!-- 添加或修改学员教练互评表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="编号" prop="id">
                            <el-input v-model="form.id" placeholder="请输入编号" />
                        </el-form-item>
                                                                                <el-form-item label="订单号" prop="orderNo">
                            <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                        </el-form-item>
                                                                                <el-form-item label="教练课程ID" prop="classId">
                            <el-input v-model="form.classId" placeholder="请输入教练课程ID" />
                        </el-form-item>
                                                                                <el-form-item label="报名单号（考试，学车）" prop="enrollNo">
                            <el-input v-model="form.enrollNo" placeholder="请输入报名单号（考试，学车）" />
                        </el-form-item>
                                                                                <el-form-item label="评价用户id（学员id）" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入评价用户id（学员id）" />
                        </el-form-item>
                                                                                <el-form-item label="学员评价内容" prop="appraiseReplyContent">
                            <el-input v-model="form.appraiseReplyContent" placeholder="请输入学员评价内容" />
                        </el-form-item>
                                                                                <el-form-item label="学员评价分数" prop="appraiseReplyGrade">
                            <el-input v-model="form.appraiseReplyGrade" placeholder="请输入学员评价分数" />
                        </el-form-item>
                                                                                <el-form-item label="学员评价时间" prop="appraiseReplyTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.appraiseReplyTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择学员评价时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="回复内容" prop="replyContent">
                            <el-input v-model="form.replyContent" placeholder="请输入回复内容" />
                        </el-form-item>
                                                                                <el-form-item label="回复时间" prop="replyTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.replyTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择回复时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="被评价用户id" prop="replyId">
                            <el-input v-model="form.replyId" placeholder="请输入被评价用户id" />
                        </el-form-item>
                                                                                <el-form-item label="被评价用户类型（2-教练；3-运维）" prop="replyType">
                            <el-input v-model="form.replyType" placeholder="请输入被评价用户类型（2-教练；3-运维）" />
                        </el-form-item>
                                                                                <el-form-item label="被评价用户头像" prop="replyHeadUrl">
                            <el-input v-model="form.replyHeadUrl" placeholder="请输入被评价用户头像" />
                        </el-form-item>
                                                                                <el-form-item label="学员头像" prop="studentHeadUrl">
                            <el-input v-model="form.studentHeadUrl" placeholder="请输入学员头像" />
                        </el-form-item>
                                                                                <el-form-item label="学员评价类型（1-评价 2-投诉）" prop="appraiseType">
                            <el-input v-model="form.appraiseType" placeholder="请输入学员评价类型（1-评价 2-投诉）" />
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
    import { listStudentCoachAppraise,listPageStudentCoachAppraise, getStudentCoachAppraise, delStudentCoachAppraise, addStudentCoachAppraise, updateStudentCoachAppraise, exportStudentCoachAppraise } from "@/api/admin/studentCoachAppraise";

    export default {
        name: "StudentCoachAppraise",
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
                // 学员教练互评表表格数据
                    studentCoachAppraiseList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                orderNo: undefined,
                                                classId: undefined,
                                                enrollNo: undefined,
                                                studentId: undefined,
                                                appraiseReplyContent: undefined,
                                                appraiseReplyGrade: undefined,
                                                appraiseReplyTime: undefined,
                                                replyContent: undefined,
                                                replyTime: undefined,
                                                replyId: undefined,
                                                replyType: undefined,
                                                replyHeadUrl: undefined,
                                                studentHeadUrl: undefined,
                                                appraiseType: undefined,
                                                operatorId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "编号不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                classId: [
                            { required: true, message: "教练课程ID不能为空", trigger: "blur" }
                        ],
                                                enrollNo: [
                            { required: true, message: "报名单号（考试，学车）不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "评价用户id（学员id）不能为空", trigger: "blur" }
                        ],
                                                appraiseReplyContent: [
                            { required: true, message: "学员评价内容不能为空", trigger: "blur" }
                        ],
                                                appraiseReplyGrade: [
                            { required: true, message: "学员评价分数不能为空", trigger: "blur" }
                        ],
                                                appraiseReplyTime: [
                            { required: true, message: "学员评价时间不能为空", trigger: "blur" }
                        ],
                                                replyContent: [
                            { required: true, message: "回复内容不能为空", trigger: "blur" }
                        ],
                                                replyTime: [
                            { required: true, message: "回复时间不能为空", trigger: "blur" }
                        ],
                                                replyId: [
                            { required: true, message: "被评价用户id不能为空", trigger: "blur" }
                        ],
                                                replyType: [
                            { required: true, message: "被评价用户类型（2-教练；3-运维）不能为空", trigger: "blur" }
                        ],
                                                replyHeadUrl: [
                            { required: true, message: "被评价用户头像不能为空", trigger: "blur" }
                        ],
                                                studentHeadUrl: [
                            { required: true, message: "学员头像不能为空", trigger: "blur" }
                        ],
                                                appraiseType: [
                            { required: true, message: "学员评价类型（1-评价 2-投诉）不能为空", trigger: "blur" }
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
            /** 查询学员教练互评表列表 */
            getListPage() {
                this.loading = true;
                listPageStudentCoachAppraise(this.queryParams).then(response => {
                    this.studentCoachAppraiseList = response.data.records;
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
                                                orderNo: undefined,
                                                classId: undefined,
                                                enrollNo: undefined,
                                                studentId: undefined,
                                                appraiseReplyContent: undefined,
                                                appraiseReplyGrade: undefined,
                                                appraiseReplyTime: undefined,
                                                replyContent: undefined,
                                                replyTime: undefined,
                                                replyId: undefined,
                                                replyType: undefined,
                                                replyHeadUrl: undefined,
                                                studentHeadUrl: undefined,
                                                appraiseType: undefined,
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
                this.ids = selection.map(item => item.id)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加学员教练互评表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getStudentCoachAppraise(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学员教练互评表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateStudentCoachAppraise(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addStudentCoachAppraise(this.form).then(response => {
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
                    return delStudentCoachAppraise(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学员教练互评表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportStudentCoachAppraise(queryParams);
                }).then(data => {
                    this.download(data, "学员教练互评表");
                }).catch(function() {});
            }
        }
    };
</script>
<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                <el-form-item label="主键" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入主键"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" prop="returnVisitType">
                        <el-input
                                v-model="queryParams.returnVisitType"
                                placeholder="请输入回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="订单明细单号" prop="orderDetailNo">
                        <el-input
                                v-model="queryParams.orderDetailNo"
                                placeholder="请输入订单明细单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员id" prop="studentId">
                        <el-input
                                v-model="queryParams.studentId"
                                placeholder="请输入学员id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="回访时间" prop="returnVisitTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.returnVisitTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择回访时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="回访内容" prop="returnVisitContent">
                        <el-input
                                v-model="queryParams.returnVisitContent"
                                placeholder="请输入回访内容"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="预计下次回访时间" prop="nextReturnVisitTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.nextReturnVisitTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择预计下次回访时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="客服id" prop="serviceId">
                        <el-input
                                v-model="queryParams.serviceId"
                                placeholder="请输入客服id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否完结（是，否）" prop="isEnd">
                        <el-input
                                v-model="queryParams.isEnd"
                                placeholder="请输入是否完结（是，否）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="科目类型(1-科目一，2-科目三，3-科目三，4-科目四)" prop="subjectType">
                        <el-input
                                v-model="queryParams.subjectType"
                                placeholder="请输入科目类型(1-科目一，2-科目三，3-科目三，4-科目四)"
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
                    v-hasPermi="['admin:serviceReturnVisitHistory:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:serviceReturnVisitHistory:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:serviceReturnVisitHistory:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:serviceReturnVisitHistory:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="serviceReturnVisitHistoryList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="主键" align="center" prop="id" />
                                                                <el-table-column label="回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" align="center" prop="returnVisitType" />
                                                                <el-table-column label="订单明细单号" align="center" prop="orderDetailNo" />
                                                                <el-table-column label="学员id" align="center" prop="studentId" />
                                                                <el-table-column label="回访时间" align="center" prop="returnVisitTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.returnVisitTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="回访内容" align="center" prop="returnVisitContent" />
                                                                <el-table-column label="预计下次回访时间" align="center" prop="nextReturnVisitTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.nextReturnVisitTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="客服id" align="center" prop="serviceId" />
                                                                <el-table-column label="是否完结（是，否）" align="center" prop="isEnd" />
                                                                <el-table-column label="科目类型(1-科目一，2-科目三，3-科目三，4-科目四)" align="center" prop="subjectType" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:serviceReturnVisitHistory:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:serviceReturnVisitHistory:delete']"
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

        <!-- 添加或修改客服回访记录对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键" />
                        </el-form-item>
                                                                                <el-form-item label="回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" prop="returnVisitType">
                            <el-input v-model="form.returnVisitType" placeholder="请输入回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" />
                        </el-form-item>
                                                                                <el-form-item label="订单明细单号" prop="orderDetailNo">
                            <el-input v-model="form.orderDetailNo" placeholder="请输入订单明细单号" />
                        </el-form-item>
                                                                                <el-form-item label="学员id" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员id" />
                        </el-form-item>
                                                                                <el-form-item label="回访时间" prop="returnVisitTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.returnVisitTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择回访时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="回访内容" prop="returnVisitContent">
                            <el-input v-model="form.returnVisitContent" placeholder="请输入回访内容" />
                        </el-form-item>
                                                                                <el-form-item label="预计下次回访时间" prop="nextReturnVisitTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.nextReturnVisitTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择预计下次回访时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="客服id" prop="serviceId">
                            <el-input v-model="form.serviceId" placeholder="请输入客服id" />
                        </el-form-item>
                                                                                <el-form-item label="是否完结（是，否）" prop="isEnd">
                            <el-input v-model="form.isEnd" placeholder="请输入是否完结（是，否）" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型(1-科目一，2-科目三，3-科目三，4-科目四)" prop="subjectType">
                            <el-input v-model="form.subjectType" placeholder="请输入科目类型(1-科目一，2-科目三，3-科目三，4-科目四)" />
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
    import { listServiceReturnVisitHistory,listPageServiceReturnVisitHistory, getServiceReturnVisitHistory, delServiceReturnVisitHistory, addServiceReturnVisitHistory, updateServiceReturnVisitHistory, exportServiceReturnVisitHistory } from "@/api/admin/serviceReturnVisitHistory";

    export default {
        name: "ServiceReturnVisitHistory",
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
                // 客服回访记录表格数据
                    serviceReturnVisitHistoryList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                returnVisitType: undefined,
                                                orderDetailNo: undefined,
                                                studentId: undefined,
                                                returnVisitTime: undefined,
                                                returnVisitContent: undefined,
                                                nextReturnVisitTime: undefined,
                                                serviceId: undefined,
                                                isEnd: undefined,
                                                subjectType: undefined,
                                                operatorId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                returnVisitType: [
                            { required: true, message: "回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）不能为空", trigger: "blur" }
                        ],
                                                orderDetailNo: [
                            { required: true, message: "订单明细单号不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员id不能为空", trigger: "blur" }
                        ],
                                                returnVisitTime: [
                            { required: true, message: "回访时间不能为空", trigger: "blur" }
                        ],
                                                returnVisitContent: [
                            { required: true, message: "回访内容不能为空", trigger: "blur" }
                        ],
                                                nextReturnVisitTime: [
                            { required: true, message: "预计下次回访时间不能为空", trigger: "blur" }
                        ],
                                                serviceId: [
                            { required: true, message: "客服id不能为空", trigger: "blur" }
                        ],
                                                isEnd: [
                            { required: true, message: "是否完结（是，否）不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型(1-科目一，2-科目三，3-科目三，4-科目四)不能为空", trigger: "blur" }
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
            /** 查询客服回访记录列表 */
            getListPage() {
                this.loading = true;
                listPageServiceReturnVisitHistory(this.queryParams).then(response => {
                    this.serviceReturnVisitHistoryList = response.data.records;
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
                                                returnVisitType: undefined,
                                                orderDetailNo: undefined,
                                                studentId: undefined,
                                                returnVisitTime: undefined,
                                                returnVisitContent: undefined,
                                                nextReturnVisitTime: undefined,
                                                serviceId: undefined,
                                                isEnd: undefined,
                                                subjectType: undefined,
                                                operatorId: undefined,
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
                this.title = "添加客服回访记录";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getServiceReturnVisitHistory(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改客服回访记录";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateServiceReturnVisitHistory(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addServiceReturnVisitHistory(this.form).then(response => {
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
                    return delServiceReturnVisitHistory(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有客服回访记录数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportServiceReturnVisitHistory(queryParams);
                }).then(data => {
                    this.download(data, "客服回访记录");
                }).catch(function() {});
            }
        }
    };
</script>
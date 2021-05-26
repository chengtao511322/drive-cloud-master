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
                                                                <el-form-item label="钱包用户id" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入钱包用户id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="清算申请时间" prop="settleAccountsApplyTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.settleAccountsApplyTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择清算申请时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="清算完成时间" prop="settleAccountsCompleteTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.settleAccountsCompleteTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择清算完成时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="审核通过时间" prop="examineTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.examineTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择审核通过时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                            <el-form-item label="清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="清算方式(1-支付宝 ,2-微信,3-银行卡)" prop="settleType">
                        <el-input
                                v-model="queryParams.settleType"
                                placeholder="请输入清算方式(1-支付宝 ,2-微信,3-银行卡)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="转账唯一订单号" prop="outBizNo">
                        <el-input
                                v-model="queryParams.outBizNo"
                                placeholder="请输入转账唯一订单号"
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
                                                                <el-form-item label="提现账号" prop="submitAccount">
                        <el-input
                                v-model="queryParams.submitAccount"
                                placeholder="请输入提现账号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="提现真实姓名" prop="submitRealName">
                        <el-input
                                v-model="queryParams.submitRealName"
                                placeholder="请输入提现真实姓名"
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
                                                                <el-form-item label="驳回时间" prop="rejectTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.rejectTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择驳回时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="开户行" prop="openAccountBank">
                        <el-input
                                v-model="queryParams.openAccountBank"
                                placeholder="请输入开户行"
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
                    v-hasPermi="['admin:walletSettlementSummary:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:walletSettlementSummary:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:walletSettlementSummary:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:walletSettlementSummary:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="walletSettlementSummaryList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="主键" align="center" prop="id" sortable="custom"/>
                                                                <el-table-column label="钱包用户id" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="清算总金额" align="center" prop="settleAccountsSum" sortable="custom"/>
                                                                <el-table-column label="清算申请时间" align="center" prop="settleAccountsApplyTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.settleAccountsApplyTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="清算完成时间" align="center" prop="settleAccountsCompleteTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.settleAccountsCompleteTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="审核通过时间" align="center" prop="examineTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.examineTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="清算笔数" align="center" prop="settleAccountsNumber" sortable="custom"/>
                                                                <el-table-column label="清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="清算方式(1-支付宝 ,2-微信,3-银行卡)" align="center" prop="settleType" sortable="custom"/>
                                                                <el-table-column label="转账唯一订单号" align="center" prop="outBizNo" sortable="custom"/>
                                                                <el-table-column label="备注" align="center" prop="remarks" sortable="custom"/>
                                                                <el-table-column label="提现账号" align="center" prop="submitAccount" sortable="custom"/>
                                                                <el-table-column label="提现真实姓名" align="center" prop="submitRealName" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="驳回时间" align="center" prop="rejectTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.rejectTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="开户行" align="center" prop="openAccountBank" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:walletSettlementSummary:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:walletSettlementSummary:delete']"
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

        <!-- 添加或修改对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键" />
                        </el-form-item>
                                                                                <el-form-item label="钱包用户id" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入钱包用户id" />
                        </el-form-item>
                                                                                <el-form-item label="清算总金额" prop="settleAccountsSum">
                            <el-input v-model="form.settleAccountsSum" placeholder="请输入清算总金额" />
                        </el-form-item>
                                                                                <el-form-item label="清算申请时间" prop="settleAccountsApplyTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.settleAccountsApplyTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择清算申请时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="清算完成时间" prop="settleAccountsCompleteTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.settleAccountsCompleteTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择清算完成时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="审核通过时间" prop="examineTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.examineTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择审核通过时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="清算笔数" prop="settleAccountsNumber">
                            <el-input v-model="form.settleAccountsNumber" placeholder="请输入清算笔数" />
                        </el-form-item>
                                                                                <el-form-item label="清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)" />
                        </el-form-item>
                                                                                <el-form-item label="清算方式(1-支付宝 ,2-微信,3-银行卡)" prop="settleType">
                            <el-input v-model="form.settleType" placeholder="请输入清算方式(1-支付宝 ,2-微信,3-银行卡)" />
                        </el-form-item>
                                                                                <el-form-item label="转账唯一订单号" prop="outBizNo">
                            <el-input v-model="form.outBizNo" placeholder="请输入转账唯一订单号" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入备注" />
                        </el-form-item>
                                                                                <el-form-item label="提现账号" prop="submitAccount">
                            <el-input v-model="form.submitAccount" placeholder="请输入提现账号" />
                        </el-form-item>
                                                                                <el-form-item label="提现真实姓名" prop="submitRealName">
                            <el-input v-model="form.submitRealName" placeholder="请输入提现真实姓名" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="驳回时间" prop="rejectTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.rejectTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择驳回时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="开户行" prop="openAccountBank">
                            <el-input v-model="form.openAccountBank" placeholder="请输入开户行" />
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
    import { listWalletSettlementSummary,listPageWalletSettlementSummary, getWalletSettlementSummary, delWalletSettlementSummary, addWalletSettlementSummary, updateWalletSettlementSummary, exportWalletSettlementSummary } from "@/api/admin/walletSettlementSummary";

    export default {
        name: "WalletSettlementSummary",
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
                // 表格数据
                    walletSettlementSummaryList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                userId: undefined,
                                                settleAccountsSum: undefined,
                                                settleAccountsApplyTime: undefined,
                                                settleAccountsCompleteTime: undefined,
                                                examineTime: undefined,
                                                settleAccountsNumber: undefined,
                                                status: undefined,
                                                settleType: undefined,
                                                outBizNo: undefined,
                                                remarks: undefined,
                                                submitAccount: undefined,
                                                submitRealName: undefined,
                                                operatorId: undefined,
                                                rejectTime: undefined,
                                                openAccountBank: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "钱包用户id不能为空", trigger: "blur" }
                        ],
                                                settleAccountsSum: [
                            { required: true, message: "清算总金额不能为空", trigger: "blur" }
                        ],
                                                settleAccountsApplyTime: [
                            { required: true, message: "清算申请时间不能为空", trigger: "blur" }
                        ],
                                                settleAccountsCompleteTime: [
                            { required: true, message: "清算完成时间不能为空", trigger: "blur" }
                        ],
                                                examineTime: [
                            { required: true, message: "审核通过时间不能为空", trigger: "blur" }
                        ],
                                                settleAccountsNumber: [
                            { required: true, message: "清算笔数不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)不能为空", trigger: "blur" }
                        ],
                                                settleType: [
                            { required: true, message: "清算方式(1-支付宝 ,2-微信,3-银行卡)不能为空", trigger: "blur" }
                        ],
                                                outBizNo: [
                            { required: true, message: "转账唯一订单号不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                submitAccount: [
                            { required: true, message: "提现账号不能为空", trigger: "blur" }
                        ],
                                                submitRealName: [
                            { required: true, message: "提现真实姓名不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                rejectTime: [
                            { required: true, message: "驳回时间不能为空", trigger: "blur" }
                        ],
                                                openAccountBank: [
                            { required: true, message: "开户行不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询列表 */
            getListPage() {
                this.loading = true;
                listPageWalletSettlementSummary(this.queryParams).then(response => {
                    this.walletSettlementSummaryList = response.data.records;
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
                                                userId: undefined,
                                                settleAccountsSum: undefined,
                                                settleAccountsApplyTime: undefined,
                                                settleAccountsCompleteTime: undefined,
                                                examineTime: undefined,
                                                settleAccountsNumber: undefined,
                                                status: undefined,
                                                settleType: undefined,
                                                outBizNo: undefined,
                                                remarks: undefined,
                                                submitAccount: undefined,
                                                submitRealName: undefined,
                                                operatorId: undefined,
                                                rejectTime: undefined,
                                                openAccountBank: undefined,
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
                this.title = "添加";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getWalletSettlementSummary(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateWalletSettlementSummary(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addWalletSettlementSummary(this.form).then(response => {
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
                    return delWalletSettlementSummary(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportWalletSettlementSummary(queryParams);
                }).then(data => {
                    this.download(data, "");
                }).catch(function() {});
            }
        }
    };
</script>
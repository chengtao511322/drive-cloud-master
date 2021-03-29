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
                                                                <el-form-item label="订单号" prop="orderNo">
                        <el-input
                                v-model="queryParams.orderNo"
                                placeholder="请输入订单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))" prop="flowType">
                        <el-input
                                v-model="queryParams.flowType"
                                placeholder="请输入流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付类型(1：支付宝；2：微信)" prop="payType">
                        <el-input
                                v-model="queryParams.payType"
                                placeholder="请输入支付类型(1：支付宝；2：微信)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                                                    <el-form-item label="支付时间" prop="payTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.payTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择支付时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="创建时间" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择创建时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="是否结算（0-否 ，1-是）" prop="isSettlement">
                        <el-input
                                v-model="queryParams.isSettlement"
                                placeholder="请输入是否结算（0-否 ，1-是）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="退款类型（0：全额退款 1：部分退款）" prop="refundType">
                        <el-input
                                v-model="queryParams.refundType"
                                placeholder="请输入退款类型（0：全额退款 1：部分退款）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="退款时间" prop="refundTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.refundTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择退款时间">
                        </el-date-picker>
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
                    v-hasPermi="['admin:accountFlow:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:accountFlow:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:accountFlow:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:accountFlow:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="accountFlowList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="订单号" align="center" prop="orderNo" sortable="custom"/>
                                                                <el-table-column label="流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))" align="center" prop="flowType" sortable="custom"/>
                                                                <el-table-column label="支付类型(1：支付宝；2：微信)" align="center" prop="payType" sortable="custom"/>
                                                                <el-table-column label="订单金额" align="center" prop="orderAmount" sortable="custom"/>
                                                                <el-table-column label="实际支付金额" align="center" prop="actualPayAmount" sortable="custom"/>
                                                                <el-table-column label="退款金额" align="center" prop="drawbackAmount" sortable="custom"/>
                                                                <el-table-column label="支付时间" align="center" prop="payTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.payTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="是否结算（0-否 ，1-是）" align="center" prop="isSettlement" sortable="custom"/>
                                                                <el-table-column label="退款类型（0：全额退款 1：部分退款）" align="center" prop="refundType" sortable="custom"/>
                                                                <el-table-column label="退款时间" align="center" prop="refundTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.refundTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:accountFlow:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:accountFlow:delete']"
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

        <!-- 添加或修改平台账务流水对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="订单号" prop="orderNo">
                            <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                        </el-form-item>
                                                                                <el-form-item label="流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))" prop="flowType">
                            <el-input v-model="form.flowType" placeholder="请输入流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))" />
                        </el-form-item>
                                                                                <el-form-item label="支付类型(1：支付宝；2：微信)" prop="payType">
                            <el-input v-model="form.payType" placeholder="请输入支付类型(1：支付宝；2：微信)" />
                        </el-form-item>
                                                                                <el-form-item label="订单金额" prop="orderAmount">
                            <el-input v-model="form.orderAmount" placeholder="请输入订单金额" />
                        </el-form-item>
                                                                                <el-form-item label="实际支付金额" prop="actualPayAmount">
                            <el-input v-model="form.actualPayAmount" placeholder="请输入实际支付金额" />
                        </el-form-item>
                                                                                <el-form-item label="退款金额" prop="drawbackAmount">
                            <el-input v-model="form.drawbackAmount" placeholder="请输入退款金额" />
                        </el-form-item>
                                                                                <el-form-item label="支付时间" prop="payTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.payTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择支付时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="是否结算（0-否 ，1-是）" prop="isSettlement">
                            <el-input v-model="form.isSettlement" placeholder="请输入是否结算（0-否 ，1-是）" />
                        </el-form-item>
                                                                                <el-form-item label="退款类型（0：全额退款 1：部分退款）" prop="refundType">
                            <el-input v-model="form.refundType" placeholder="请输入退款类型（0：全额退款 1：部分退款）" />
                        </el-form-item>
                                                                                <el-form-item label="退款时间" prop="refundTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.refundTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择退款时间">
                            </el-date-picker>
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
    import { listAccountFlow,listPageAccountFlow, getAccountFlow, delAccountFlow, addAccountFlow, updateAccountFlow, exportAccountFlow } from "@/api/admin/accountFlow";

    export default {
        name: "AccountFlow",
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
                // 平台账务流水表格数据
                    accountFlowList: [],
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
                                                flowType: undefined,
                                                payType: undefined,
                                                orderAmount: undefined,
                                                actualPayAmount: undefined,
                                                drawbackAmount: undefined,
                                                payTime: undefined,
                                                createTime: undefined,
                                                isSettlement: undefined,
                                                refundType: undefined,
                                                refundTime: undefined,
                                                operatorId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                flowType: [
                            { required: true, message: "流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))不能为空", trigger: "blur" }
                        ],
                                                payType: [
                            { required: true, message: "支付类型(1：支付宝；2：微信)不能为空", trigger: "blur" }
                        ],
                                                orderAmount: [
                            { required: true, message: "订单金额不能为空", trigger: "blur" }
                        ],
                                                actualPayAmount: [
                            { required: true, message: "实际支付金额不能为空", trigger: "blur" }
                        ],
                                                drawbackAmount: [
                            { required: true, message: "退款金额不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                isSettlement: [
                            { required: true, message: "是否结算（0-否 ，1-是）不能为空", trigger: "blur" }
                        ],
                                                refundType: [
                            { required: true, message: "退款类型（0：全额退款 1：部分退款）不能为空", trigger: "blur" }
                        ],
                                                refundTime: [
                            { required: true, message: "退款时间不能为空", trigger: "blur" }
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
            /** 查询平台账务流水列表 */
            getListPage() {
                this.loading = true;
                listPageAccountFlow(this.queryParams).then(response => {
                    this.accountFlowList = response.data.records;
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
                                                flowType: undefined,
                                                payType: undefined,
                                                orderAmount: undefined,
                                                actualPayAmount: undefined,
                                                drawbackAmount: undefined,
                                                payTime: undefined,
                                                createTime: undefined,
                                                isSettlement: undefined,
                                                refundType: undefined,
                                                refundTime: undefined,
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
                this.title = "添加平台账务流水";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getAccountFlow(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改平台账务流水";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateAccountFlow(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addAccountFlow(this.form).then(response => {
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
                    return delAccountFlow(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有平台账务流水数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportAccountFlow(queryParams);
                }).then(data => {
                    this.download(data, "平台账务流水");
                }).catch(function() {});
            }
        }
    };
</script>
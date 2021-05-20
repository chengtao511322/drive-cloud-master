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
                                                                <el-form-item label="交易类型(1;支付流水；2：退款流水 )" prop="tranType">
                        <el-input
                                v-model="queryParams.tranType"
                                placeholder="请输入交易类型(1;支付流水；2：退款流水 )"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付类型(1：支付宝；2：微信) " prop="payType">
                        <el-input
                                v-model="queryParams.payType"
                                placeholder="请输入支付类型(1：支付宝；2：微信) "
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付方账号" prop="payAccount">
                        <el-input
                                v-model="queryParams.payAccount"
                                placeholder="请输入支付方账号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)" prop="thirdOrderNo">
                        <el-input
                                v-model="queryParams.thirdOrderNo"
                                placeholder="请输入第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)"
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
                                                                <el-form-item label="第三方返回时间" prop="thirdReturnTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.thirdReturnTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择第三方返回时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="提交参数" prop="commitParameter">
                        <el-input
                                v-model="queryParams.commitParameter"
                                placeholder="请输入提交参数"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="返回参数" prop="returnParameter">
                        <el-input
                                v-model="queryParams.returnParameter"
                                placeholder="请输入返回参数"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）" prop="payTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.payTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="买家支付宝账号（用于在取消扣款时，转账使用）" prop="buyerLogonId">
                        <el-input
                                v-model="queryParams.buyerLogonId"
                                placeholder="请输入买家支付宝账号（用于在取消扣款时，转账使用）"
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
                    v-hasPermi="['admin:payFlowLog:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:payFlowLog:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:payFlowLog:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:payFlowLog:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="payFlowLogList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="交易类型(1;支付流水；2：退款流水 )" align="center" prop="tranType" sortable="custom"/>
                                                                <el-table-column label="支付类型(1：支付宝；2：微信) " align="center" prop="payType" sortable="custom"/>
                                                                <el-table-column label="支付方账号" align="center" prop="payAccount" sortable="custom"/>
                                                                <el-table-column label="订单应付金额" align="center" prop="payableAmount" sortable="custom"/>
                                                                <el-table-column label="第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)" align="center" prop="thirdOrderNo" sortable="custom"/>
                                                                <el-table-column label="第三方订金额" align="center" prop="thirdOrderAmount" sortable="custom"/>
                                                                <el-table-column label="交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)" align="center" prop="status" sortable="custom"/>
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
                                                                <el-table-column label="第三方返回时间" align="center" prop="thirdReturnTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.thirdReturnTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="提交参数" align="center" prop="commitParameter" sortable="custom"/>
                                                                <el-table-column label="返回参数" align="center" prop="returnParameter" sortable="custom"/>
                                                                <el-table-column label="支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）" align="center" prop="payTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.payTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="买家支付宝账号（用于在取消扣款时，转账使用）" align="center" prop="buyerLogonId" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:payFlowLog:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:payFlowLog:delete']"
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

        <!-- 添加或修改平台交易流水日志对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="编号" prop="id">
                            <el-input v-model="form.id" placeholder="请输入编号" />
                        </el-form-item>
                                                                                <el-form-item label="订单号" prop="orderNo">
                            <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                        </el-form-item>
                                                                                <el-form-item label="交易类型(1;支付流水；2：退款流水 )" prop="tranType">
                            <el-input v-model="form.tranType" placeholder="请输入交易类型(1;支付流水；2：退款流水 )" />
                        </el-form-item>
                                                                                <el-form-item label="支付类型(1：支付宝；2：微信) " prop="payType">
                            <el-input v-model="form.payType" placeholder="请输入支付类型(1：支付宝；2：微信) " />
                        </el-form-item>
                                                                                <el-form-item label="支付方账号" prop="payAccount">
                            <el-input v-model="form.payAccount" placeholder="请输入支付方账号" />
                        </el-form-item>
                                                                                <el-form-item label="订单应付金额" prop="payableAmount">
                            <el-input v-model="form.payableAmount" placeholder="请输入订单应付金额" />
                        </el-form-item>
                                                                                <el-form-item label="第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)" prop="thirdOrderNo">
                            <el-input v-model="form.thirdOrderNo" placeholder="请输入第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)" />
                        </el-form-item>
                                                                                <el-form-item label="第三方订金额" prop="thirdOrderAmount">
                            <el-input v-model="form.thirdOrderAmount" placeholder="请输入第三方订金额" />
                        </el-form-item>
                                                                                <el-form-item label="交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)" />
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
                                                                                <el-form-item label="第三方返回时间" prop="thirdReturnTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.thirdReturnTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择第三方返回时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="提交参数" prop="commitParameter">
                            <el-input v-model="form.commitParameter" placeholder="请输入提交参数" />
                        </el-form-item>
                                                                                <el-form-item label="返回参数" prop="returnParameter">
                            <el-input v-model="form.returnParameter" placeholder="请输入返回参数" />
                        </el-form-item>
                                                                                <el-form-item label="支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）" prop="payTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.payTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="买家支付宝账号（用于在取消扣款时，转账使用）" prop="buyerLogonId">
                            <el-input v-model="form.buyerLogonId" placeholder="请输入买家支付宝账号（用于在取消扣款时，转账使用）" />
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
    import { listPayFlowLog,listPagePayFlowLog, getPayFlowLog, delPayFlowLog, addPayFlowLog, updatePayFlowLog, exportPayFlowLog } from "@/api/admin/payFlowLog";

    export default {
        name: "PayFlowLog",
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
                // 平台交易流水日志表格数据
                    payFlowLogList: [],
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
                                                tranType: undefined,
                                                payType: undefined,
                                                payAccount: undefined,
                                                payableAmount: undefined,
                                                thirdOrderNo: undefined,
                                                thirdOrderAmount: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                thirdReturnTime: undefined,
                                                commitParameter: undefined,
                                                returnParameter: undefined,
                                                payTime: undefined,
                                                buyerLogonId: undefined,
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
                                                tranType: [
                            { required: true, message: "交易类型(1;支付流水；2：退款流水 )不能为空", trigger: "blur" }
                        ],
                                                payType: [
                            { required: true, message: "支付类型(1：支付宝；2：微信) 不能为空", trigger: "blur" }
                        ],
                                                payAccount: [
                            { required: true, message: "支付方账号不能为空", trigger: "blur" }
                        ],
                                                payableAmount: [
                            { required: true, message: "订单应付金额不能为空", trigger: "blur" }
                        ],
                                                thirdOrderNo: [
                            { required: true, message: "第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)不能为空", trigger: "blur" }
                        ],
                                                thirdOrderAmount: [
                            { required: true, message: "第三方订金额不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                thirdReturnTime: [
                            { required: true, message: "第三方返回时间不能为空", trigger: "blur" }
                        ],
                                                commitParameter: [
                            { required: true, message: "提交参数不能为空", trigger: "blur" }
                        ],
                                                returnParameter: [
                            { required: true, message: "返回参数不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）不能为空", trigger: "blur" }
                        ],
                                                buyerLogonId: [
                            { required: true, message: "买家支付宝账号（用于在取消扣款时，转账使用）不能为空", trigger: "blur" }
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
            /** 查询平台交易流水日志列表 */
            getListPage() {
                this.loading = true;
                listPagePayFlowLog(this.queryParams).then(response => {
                    this.payFlowLogList = response.data.records;
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
                                                tranType: undefined,
                                                payType: undefined,
                                                payAccount: undefined,
                                                payableAmount: undefined,
                                                thirdOrderNo: undefined,
                                                thirdOrderAmount: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                thirdReturnTime: undefined,
                                                commitParameter: undefined,
                                                returnParameter: undefined,
                                                payTime: undefined,
                                                buyerLogonId: undefined,
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
                this.title = "添加平台交易流水日志";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getPayFlowLog(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改平台交易流水日志";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updatePayFlowLog(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addPayFlowLog(this.form).then(response => {
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
                    return delPayFlowLog(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有平台交易流水日志数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportPayFlowLog(queryParams);
                }).then(data => {
                    this.download(data, "平台交易流水日志");
                }).catch(function() {});
            }
        }
    };
</script>
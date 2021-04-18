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
                                                                                                                        <el-form-item label="" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入"
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
                                                                                            <el-form-item label="第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用" prop="partyPayId">
                        <el-input
                                v-model="queryParams.partyPayId"
                                placeholder="请输入第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="使用雪花算法生产 支付系统 支付id" prop="paymentId">
                        <el-input
                                v-model="queryParams.paymentId"
                                placeholder="请输入使用雪花算法生产 支付系统 支付id"
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
                                                                <el-form-item label="修改时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择修改时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="修改者" prop="updateUser">
                        <el-input
                                v-model="queryParams.updateUser"
                                placeholder="请输入修改者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付渠道" prop="paymentChannel">
                        <el-input
                                v-model="queryParams.paymentChannel"
                                placeholder="请输入支付渠道"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="body" prop="body">
                        <el-input
                                v-model="queryParams.body"
                                placeholder="请输入body"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="" prop="subject">
                        <el-input
                                v-model="queryParams.subject"
                                placeholder="请输入"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="" prop="payParams">
                        <el-input
                                v-model="queryParams.payParams"
                                placeholder="请输入"
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
                                                                <el-form-item label="手机号码" prop="phone">
                        <el-input
                                v-model="queryParams.phone"
                                placeholder="请输入手机号码"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="" prop="tenantId">
                        <el-input
                                v-model="queryParams.tenantId"
                                placeholder="请输入"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="租户渠道ID" prop="platformChannelId">
                        <el-input
                                v-model="queryParams.platformChannelId"
                                placeholder="请输入租户渠道ID"
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
                    v-hasPermi="['pay:mentTransaction:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['pay:mentTransaction:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['pay:mentTransaction:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['pay:mentTransaction:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="mentTransactionList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="支付金额" align="center" prop="payAmount" sortable="custom"/>
                                                                <el-table-column label="支付状态;0待支付1已经支付2支付超时3支付失败" align="center" prop="paymentStatus" sortable="custom"/>
                                                                <el-table-column label="" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="订单号" align="center" prop="orderNo" sortable="custom"/>
                                                                <el-table-column label="乐观锁" align="center" prop="revision" sortable="custom"/>
                                                                <el-table-column label="第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用" align="center" prop="partyPayId" sortable="custom"/>
                                                                <el-table-column label="使用雪花算法生产 支付系统 支付id" align="center" prop="paymentId" sortable="custom"/>
                                                                <el-table-column label="创建人" align="center" prop="createUser" sortable="custom"/>
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
                                                                <el-table-column label="修改者" align="center" prop="updateUser" sortable="custom"/>
                                                                <el-table-column label="支付渠道" align="center" prop="paymentChannel" sortable="custom"/>
                                                                <el-table-column label="订单金额" align="center" prop="orderAmount" sortable="custom"/>
                                                                <el-table-column label="body" align="center" prop="body" sortable="custom"/>
                                                                <el-table-column label="" align="center" prop="subject" sortable="custom"/>
                                                                <el-table-column label="" align="center" prop="payParams" sortable="custom"/>
                                                                <el-table-column label="支付时间" align="center" prop="payTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.payTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="手机号码" align="center" prop="phone" sortable="custom"/>
                                                                <el-table-column label="" align="center" prop="tenantId" sortable="custom"/>
                                                                <el-table-column label="租户渠道ID" align="center" prop="platformChannelId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['pay:mentTransaction:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['pay:mentTransaction:delete']"
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

        <!-- 添加或修改支付交易流水信息表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="支付金额" prop="payAmount">
                            <el-input v-model="form.payAmount" placeholder="请输入支付金额" />
                        </el-form-item>
                                                                                <el-form-item label="支付状态;0待支付1已经支付2支付超时3支付失败" prop="paymentStatus">
                            <el-input v-model="form.paymentStatus" placeholder="请输入支付状态;0待支付1已经支付2支付超时3支付失败" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="订单号" prop="orderNo">
                            <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                        </el-form-item>
                                                                                <el-form-item label="乐观锁" prop="revision">
                            <el-input v-model="form.revision" placeholder="请输入乐观锁" />
                        </el-form-item>
                                                                                <el-form-item label="第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用" prop="partyPayId">
                            <el-input v-model="form.partyPayId" placeholder="请输入第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用" />
                        </el-form-item>
                                                                                <el-form-item label="使用雪花算法生产 支付系统 支付id" prop="paymentId">
                            <el-input v-model="form.paymentId" placeholder="请输入使用雪花算法生产 支付系统 支付id" />
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
                                                                                <el-form-item label="修改时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择修改时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="修改者" prop="updateUser">
                            <el-input v-model="form.updateUser" placeholder="请输入修改者" />
                        </el-form-item>
                                                                                <el-form-item label="支付渠道" prop="paymentChannel">
                            <el-input v-model="form.paymentChannel" placeholder="请输入支付渠道" />
                        </el-form-item>
                                                                                <el-form-item label="订单金额" prop="orderAmount">
                            <el-input v-model="form.orderAmount" placeholder="请输入订单金额" />
                        </el-form-item>
                                                                                <el-form-item label="body" prop="body">
                            <el-input v-model="form.body" placeholder="请输入body" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="subject">
                            <el-input v-model="form.subject" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="payParams">
                            <el-input v-model="form.payParams" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="支付时间" prop="payTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.payTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择支付时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="手机号码" prop="phone">
                            <el-input v-model="form.phone" placeholder="请输入手机号码" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="tenantId">
                            <el-input v-model="form.tenantId" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="租户渠道ID" prop="platformChannelId">
                            <el-input v-model="form.platformChannelId" placeholder="请输入租户渠道ID" />
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
    import { listMentTransaction,listPageMentTransaction, getMentTransaction, delMentTransaction, addMentTransaction, updateMentTransaction, exportMentTransaction } from "@/api/pay/mentTransaction";

    export default {
        name: "MentTransaction",
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
                // 支付交易流水信息表表格数据
                    mentTransactionList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                payAmount: undefined,
                                                paymentStatus: undefined,
                                                userId: undefined,
                                                orderNo: undefined,
                                                revision: undefined,
                                                partyPayId: undefined,
                                                paymentId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                updateUser: undefined,
                                                paymentChannel: undefined,
                                                orderAmount: undefined,
                                                body: undefined,
                                                subject: undefined,
                                                payParams: undefined,
                                                payTime: undefined,
                                                phone: undefined,
                                                tenantId: undefined,
                                                platformChannelId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                payAmount: [
                            { required: true, message: "支付金额不能为空", trigger: "blur" }
                        ],
                                                paymentStatus: [
                            { required: true, message: "支付状态;0待支付1已经支付2支付超时3支付失败不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                revision: [
                            { required: true, message: "乐观锁不能为空", trigger: "blur" }
                        ],
                                                partyPayId: [
                            { required: true, message: "第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用不能为空", trigger: "blur" }
                        ],
                                                paymentId: [
                            { required: true, message: "使用雪花算法生产 支付系统 支付id不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建人不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "修改者不能为空", trigger: "blur" }
                        ],
                                                paymentChannel: [
                            { required: true, message: "支付渠道不能为空", trigger: "blur" }
                        ],
                                                orderAmount: [
                            { required: true, message: "订单金额不能为空", trigger: "blur" }
                        ],
                                                body: [
                            { required: true, message: "body不能为空", trigger: "blur" }
                        ],
                                                subject: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                payParams: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间不能为空", trigger: "blur" }
                        ],
                                                phone: [
                            { required: true, message: "手机号码不能为空", trigger: "blur" }
                        ],
                                                tenantId: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                platformChannelId: [
                            { required: true, message: "租户渠道ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询支付交易流水信息表列表 */
            getListPage() {
                this.loading = true;
                listPageMentTransaction(this.queryParams).then(response => {
                    this.mentTransactionList = response.data.records;
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
                                                payAmount: undefined,
                                                paymentStatus: undefined,
                                                userId: undefined,
                                                orderNo: undefined,
                                                revision: undefined,
                                                partyPayId: undefined,
                                                paymentId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                updateUser: undefined,
                                                paymentChannel: undefined,
                                                orderAmount: undefined,
                                                body: undefined,
                                                subject: undefined,
                                                payParams: undefined,
                                                payTime: undefined,
                                                phone: undefined,
                                                tenantId: undefined,
                                                platformChannelId: undefined,
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
                this.title = "添加支付交易流水信息表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getMentTransaction(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改支付交易流水信息表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateMentTransaction(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addMentTransaction(this.form).then(response => {
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
                    return delMentTransaction(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有支付交易流水信息表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportMentTransaction(queryParams);
                }).then(data => {
                    this.download(data, "支付交易流水信息表");
                }).catch(function() {});
            }
        }
    };
</script>
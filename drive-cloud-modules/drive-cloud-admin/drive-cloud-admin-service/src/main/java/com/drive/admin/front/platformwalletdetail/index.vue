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
                                                                <el-form-item label="钱包用户Id" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入钱包用户Id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="账务流水明细id/提现时为清算记录id" prop="accountDetailId">
                        <el-input
                                v-model="queryParams.accountDetailId"
                                placeholder="请输入账务流水明细id/提现时为清算记录id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="交易类型（1-收益、2-支出）" prop="tradeType">
                        <el-input
                                v-model="queryParams.tradeType"
                                placeholder="请输入交易类型（1-收益、2-支出）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="收入/支出名称" prop="walletDetailName">
                        <el-input
                                v-model="queryParams.walletDetailName"
                                placeholder="请输入收入/支出名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="交易状态(0-失败,1-成功)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入交易状态(0-失败,1-成功)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="交易时间" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择交易时间">
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
                                                                <el-form-item label="交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" prop="tradeSubject">
                        <el-input
                                v-model="queryParams.tradeSubject"
                                placeholder="请输入交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" prop="tradeSubjectItems">
                        <el-input
                                v-model="queryParams.tradeSubjectItems"
                                placeholder="请输入交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="数据创建时间" prop="setUpDate">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.setUpDate"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择数据创建时间">
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
                    v-hasPermi="['admin:platformWalletDetail:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:platformWalletDetail:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:platformWalletDetail:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:platformWalletDetail:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="platformWalletDetailList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="钱包用户Id" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="账务流水明细id/提现时为清算记录id" align="center" prop="accountDetailId" sortable="custom"/>
                                                                <el-table-column label="交易金额" align="center" prop="tradeAmount" sortable="custom"/>
                                                                <el-table-column label="交易类型（1-收益、2-支出）" align="center" prop="tradeType" sortable="custom"/>
                                                                <el-table-column label="收入/支出名称" align="center" prop="walletDetailName" sortable="custom"/>
                                                                <el-table-column label="交易状态(0-失败,1-成功)" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="交易时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="余额(进账，处长之前的余额)" align="center" prop="balance" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" align="center" prop="tradeSubject" sortable="custom"/>
                                                                <el-table-column label="交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" align="center" prop="tradeSubjectItems" sortable="custom"/>
                                                                <el-table-column label="数据创建时间" align="center" prop="setUpDate" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.setUpDate) }}</span>
                        </template>
                    </el-table-column>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:platformWalletDetail:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:platformWalletDetail:delete']"
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

        <!-- 添加或修改教练钱包表明细对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="钱包用户Id" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入钱包用户Id" />
                        </el-form-item>
                                                                                <el-form-item label="账务流水明细id/提现时为清算记录id" prop="accountDetailId">
                            <el-input v-model="form.accountDetailId" placeholder="请输入账务流水明细id/提现时为清算记录id" />
                        </el-form-item>
                                                                                <el-form-item label="交易金额" prop="tradeAmount">
                            <el-input v-model="form.tradeAmount" placeholder="请输入交易金额" />
                        </el-form-item>
                                                                                <el-form-item label="交易类型（1-收益、2-支出）" prop="tradeType">
                            <el-input v-model="form.tradeType" placeholder="请输入交易类型（1-收益、2-支出）" />
                        </el-form-item>
                                                                                <el-form-item label="收入/支出名称" prop="walletDetailName">
                            <el-input v-model="form.walletDetailName" placeholder="请输入收入/支出名称" />
                        </el-form-item>
                                                                                <el-form-item label="交易状态(0-失败,1-成功)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入交易状态(0-失败,1-成功)" />
                        </el-form-item>
                                                                                <el-form-item label="交易时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择交易时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="余额(进账，处长之前的余额)" prop="balance">
                            <el-input v-model="form.balance" placeholder="请输入余额(进账，处长之前的余额)" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" prop="tradeSubject">
                            <el-input v-model="form.tradeSubject" placeholder="请输入交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" />
                        </el-form-item>
                                                                                <el-form-item label="交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" prop="tradeSubjectItems">
                            <el-input v-model="form.tradeSubjectItems" placeholder="请输入交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" />
                        </el-form-item>
                                                                                <el-form-item label="数据创建时间" prop="setUpDate">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.setUpDate"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择数据创建时间">
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
    import { listPlatformWalletDetail,listPagePlatformWalletDetail, getPlatformWalletDetail, delPlatformWalletDetail, addPlatformWalletDetail, updatePlatformWalletDetail, exportPlatformWalletDetail } from "@/api/admin/platformWalletDetail";

    export default {
        name: "PlatformWalletDetail",
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
                // 教练钱包表明细表格数据
                    platformWalletDetailList: [],
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
                                                accountDetailId: undefined,
                                                tradeAmount: undefined,
                                                tradeType: undefined,
                                                walletDetailName: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                balance: undefined,
                                                operatorId: undefined,
                                                tradeSubject: undefined,
                                                tradeSubjectItems: undefined,
                                                setUpDate: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "钱包用户Id不能为空", trigger: "blur" }
                        ],
                                                accountDetailId: [
                            { required: true, message: "账务流水明细id/提现时为清算记录id不能为空", trigger: "blur" }
                        ],
                                                tradeAmount: [
                            { required: true, message: "交易金额不能为空", trigger: "blur" }
                        ],
                                                tradeType: [
                            { required: true, message: "交易类型（1-收益、2-支出）不能为空", trigger: "blur" }
                        ],
                                                walletDetailName: [
                            { required: true, message: "收入/支出名称不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "交易状态(0-失败,1-成功)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "交易时间不能为空", trigger: "blur" }
                        ],
                                                balance: [
                            { required: true, message: "余额(进账，处长之前的余额)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                tradeSubject: [
                            { required: true, message: "交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)不能为空", trigger: "blur" }
                        ],
                                                tradeSubjectItems: [
                            { required: true, message: "交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)不能为空", trigger: "blur" }
                        ],
                                                setUpDate: [
                            { required: true, message: "数据创建时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询教练钱包表明细列表 */
            getListPage() {
                this.loading = true;
                listPagePlatformWalletDetail(this.queryParams).then(response => {
                    this.platformWalletDetailList = response.data.records;
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
                                                accountDetailId: undefined,
                                                tradeAmount: undefined,
                                                tradeType: undefined,
                                                walletDetailName: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                balance: undefined,
                                                operatorId: undefined,
                                                tradeSubject: undefined,
                                                tradeSubjectItems: undefined,
                                                setUpDate: undefined,
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
                this.title = "添加教练钱包表明细";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getPlatformWalletDetail(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改教练钱包表明细";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updatePlatformWalletDetail(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addPlatformWalletDetail(this.form).then(response => {
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
                    return delPlatformWalletDetail(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有教练钱包表明细数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportPlatformWalletDetail(queryParams);
                }).then(data => {
                    this.download(data, "教练钱包表明细");
                }).catch(function() {});
            }
        }
    };
</script>
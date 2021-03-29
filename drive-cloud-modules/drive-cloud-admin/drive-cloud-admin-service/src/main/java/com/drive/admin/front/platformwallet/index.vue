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
                                                                <el-form-item label="钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)" prop="walletType">
                        <el-input
                                v-model="queryParams.walletType"
                                placeholder="请输入钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="密码" prop="password">
                        <el-input
                                v-model="queryParams.password"
                                placeholder="请输入密码"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="微信账号" prop="wechatAccount">
                        <el-input
                                v-model="queryParams.wechatAccount"
                                placeholder="请输入微信账号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付宝账号" prop="aliAccount">
                        <el-input
                                v-model="queryParams.aliAccount"
                                placeholder="请输入支付宝账号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="微信真实姓名" prop="wechatRealName">
                        <el-input
                                v-model="queryParams.wechatRealName"
                                placeholder="请输入微信真实姓名"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付宝账号真实名称" prop="aliRealName">
                        <el-input
                                v-model="queryParams.aliRealName"
                                placeholder="请输入支付宝账号真实名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="银行卡号" prop="bankAccount">
                        <el-input
                                v-model="queryParams.bankAccount"
                                placeholder="请输入银行卡号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="持卡人姓名" prop="bankAccountName">
                        <el-input
                                v-model="queryParams.bankAccountName"
                                placeholder="请输入持卡人姓名"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
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
                    v-hasPermi="['admin:platformWallet:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:platformWallet:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:platformWallet:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:platformWallet:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="platformWalletList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)" align="center" prop="walletType" sortable="custom"/>
                                                                <el-table-column label="钱包总额" align="center" prop="walletAmount" sortable="custom"/>
                                                                <el-table-column label="密码" align="center" prop="password" sortable="custom"/>
                                                                <el-table-column label="微信账号" align="center" prop="wechatAccount" sortable="custom"/>
                                                                <el-table-column label="支付宝账号" align="center" prop="aliAccount" sortable="custom"/>
                                                                <el-table-column label="微信真实姓名" align="center" prop="wechatRealName" sortable="custom"/>
                                                                <el-table-column label="支付宝账号真实名称" align="center" prop="aliRealName" sortable="custom"/>
                                                                <el-table-column label="银行卡号" align="center" prop="bankAccount" sortable="custom"/>
                                                                <el-table-column label="持卡人姓名" align="center" prop="bankAccountName" sortable="custom"/>
                                                                <el-table-column label="开户行" align="center" prop="openAccountBank" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:platformWallet:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:platformWallet:delete']"
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

        <!-- 添加或修改教练钱包表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="钱包用户Id" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入钱包用户Id" />
                        </el-form-item>
                                                                                <el-form-item label="钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)" prop="walletType">
                            <el-input v-model="form.walletType" placeholder="请输入钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)" />
                        </el-form-item>
                                                                                <el-form-item label="钱包总额" prop="walletAmount">
                            <el-input v-model="form.walletAmount" placeholder="请输入钱包总额" />
                        </el-form-item>
                                                                                <el-form-item label="密码" prop="password">
                            <el-input v-model="form.password" placeholder="请输入密码" />
                        </el-form-item>
                                                                                <el-form-item label="微信账号" prop="wechatAccount">
                            <el-input v-model="form.wechatAccount" placeholder="请输入微信账号" />
                        </el-form-item>
                                                                                <el-form-item label="支付宝账号" prop="aliAccount">
                            <el-input v-model="form.aliAccount" placeholder="请输入支付宝账号" />
                        </el-form-item>
                                                                                <el-form-item label="微信真实姓名" prop="wechatRealName">
                            <el-input v-model="form.wechatRealName" placeholder="请输入微信真实姓名" />
                        </el-form-item>
                                                                                <el-form-item label="支付宝账号真实名称" prop="aliRealName">
                            <el-input v-model="form.aliRealName" placeholder="请输入支付宝账号真实名称" />
                        </el-form-item>
                                                                                <el-form-item label="银行卡号" prop="bankAccount">
                            <el-input v-model="form.bankAccount" placeholder="请输入银行卡号" />
                        </el-form-item>
                                                                                <el-form-item label="持卡人姓名" prop="bankAccountName">
                            <el-input v-model="form.bankAccountName" placeholder="请输入持卡人姓名" />
                        </el-form-item>
                                                                                <el-form-item label="开户行" prop="openAccountBank">
                            <el-input v-model="form.openAccountBank" placeholder="请输入开户行" />
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
    import { listPlatformWallet,listPagePlatformWallet, getPlatformWallet, delPlatformWallet, addPlatformWallet, updatePlatformWallet, exportPlatformWallet } from "@/api/admin/platformWallet";

    export default {
        name: "PlatformWallet",
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
                // 教练钱包表表格数据
                    platformWalletList: [],
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
                                                walletType: undefined,
                                                walletAmount: undefined,
                                                password: undefined,
                                                wechatAccount: undefined,
                                                aliAccount: undefined,
                                                wechatRealName: undefined,
                                                aliRealName: undefined,
                                                bankAccount: undefined,
                                                bankAccountName: undefined,
                                                openAccountBank: undefined,
                                                operatorId: undefined,
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
                                                walletType: [
                            { required: true, message: "钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)不能为空", trigger: "blur" }
                        ],
                                                walletAmount: [
                            { required: true, message: "钱包总额不能为空", trigger: "blur" }
                        ],
                                                password: [
                            { required: true, message: "密码不能为空", trigger: "blur" }
                        ],
                                                wechatAccount: [
                            { required: true, message: "微信账号不能为空", trigger: "blur" }
                        ],
                                                aliAccount: [
                            { required: true, message: "支付宝账号不能为空", trigger: "blur" }
                        ],
                                                wechatRealName: [
                            { required: true, message: "微信真实姓名不能为空", trigger: "blur" }
                        ],
                                                aliRealName: [
                            { required: true, message: "支付宝账号真实名称不能为空", trigger: "blur" }
                        ],
                                                bankAccount: [
                            { required: true, message: "银行卡号不能为空", trigger: "blur" }
                        ],
                                                bankAccountName: [
                            { required: true, message: "持卡人姓名不能为空", trigger: "blur" }
                        ],
                                                openAccountBank: [
                            { required: true, message: "开户行不能为空", trigger: "blur" }
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
            /** 查询教练钱包表列表 */
            getListPage() {
                this.loading = true;
                listPagePlatformWallet(this.queryParams).then(response => {
                    this.platformWalletList = response.data.records;
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
                                                walletType: undefined,
                                                walletAmount: undefined,
                                                password: undefined,
                                                wechatAccount: undefined,
                                                aliAccount: undefined,
                                                wechatRealName: undefined,
                                                aliRealName: undefined,
                                                bankAccount: undefined,
                                                bankAccountName: undefined,
                                                openAccountBank: undefined,
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
                this.title = "添加教练钱包表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getPlatformWallet(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改教练钱包表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updatePlatformWallet(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addPlatformWallet(this.form).then(response => {
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
                    return delPlatformWallet(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有教练钱包表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportPlatformWallet(queryParams);
                }).then(data => {
                    this.download(data, "教练钱包表");
                }).catch(function() {});
            }
        }
    };
</script>
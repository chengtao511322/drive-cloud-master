<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                        <el-form-item label="" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入"
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
                                                                <el-form-item label="渠道经理ID" prop="recommendManagerId">
                        <el-input
                                v-model="queryParams.recommendManagerId"
                                placeholder="请输入渠道经理ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="代理商ID" prop="recommendAgencyId">
                        <el-input
                                v-model="queryParams.recommendAgencyId"
                                placeholder="请输入代理商ID"
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
                    v-hasPermi="['admin:deductSetting:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:deductSetting:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:deductSetting:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:deductSetting:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="deductSettingList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="" align="center" prop="name" sortable="custom"/>
                                                                <el-table-column label="用户类型 1-个人，2-商铺,3 企业类型 4  学校类型" align="center" prop="userType" sortable="custom"/>
                                                                <el-table-column label="经理课时提成比例" align="center" prop="managerHourProportion" sortable="custom"/>
                                                                <el-table-column label="经理报名提成金额" align="center" prop="managerApplyAmount" sortable="custom"/>
                                                                <el-table-column label="代理商课时提成比" align="center" prop="agencyHourProportion" sortable="custom"/>
                                                                <el-table-column label="代理商报名提成金额" align="center" prop="agencyApplyAmount" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="渠道经理ID" align="center" prop="recommendManagerId" sortable="custom"/>
                                                                <el-table-column label="代理商ID" align="center" prop="recommendAgencyId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:deductSetting:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:deductSetting:delete']"
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

        <!-- 添加或修改提成设置表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="name">
                            <el-input v-model="form.name" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="用户类型 1-个人，2-商铺,3 企业类型 4  学校类型" prop="userType">
                            <el-input v-model="form.userType" placeholder="请输入用户类型 1-个人，2-商铺,3 企业类型 4  学校类型" />
                        </el-form-item>
                                                                                <el-form-item label="经理课时提成比例" prop="managerHourProportion">
                            <el-input v-model="form.managerHourProportion" placeholder="请输入经理课时提成比例" />
                        </el-form-item>
                                                                                <el-form-item label="经理报名提成金额" prop="managerApplyAmount">
                            <el-input v-model="form.managerApplyAmount" placeholder="请输入经理报名提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="代理商课时提成比" prop="agencyHourProportion">
                            <el-input v-model="form.agencyHourProportion" placeholder="请输入代理商课时提成比" />
                        </el-form-item>
                                                                                <el-form-item label="代理商报名提成金额" prop="agencyApplyAmount">
                            <el-input v-model="form.agencyApplyAmount" placeholder="请输入代理商报名提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="渠道经理ID" prop="recommendManagerId">
                            <el-input v-model="form.recommendManagerId" placeholder="请输入渠道经理ID" />
                        </el-form-item>
                                                                                <el-form-item label="代理商ID" prop="recommendAgencyId">
                            <el-input v-model="form.recommendAgencyId" placeholder="请输入代理商ID" />
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
    import { listDeductSetting,listPageDeductSetting, getDeductSetting, delDeductSetting, addDeductSetting, updateDeductSetting, exportDeductSetting } from "@/api/admin/deductSetting";

    export default {
        name: "DeductSetting",
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
                // 提成设置表表格数据
                    deductSettingList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                name: undefined,
                                                userType: undefined,
                                                managerHourProportion: undefined,
                                                managerApplyAmount: undefined,
                                                agencyHourProportion: undefined,
                                                agencyApplyAmount: undefined,
                                                operatorId: undefined,
                                                recommendManagerId: undefined,
                                                recommendAgencyId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                name: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                userType: [
                            { required: true, message: "用户类型 1-个人，2-商铺,3 企业类型 4  学校类型不能为空", trigger: "blur" }
                        ],
                                                managerHourProportion: [
                            { required: true, message: "经理课时提成比例不能为空", trigger: "blur" }
                        ],
                                                managerApplyAmount: [
                            { required: true, message: "经理报名提成金额不能为空", trigger: "blur" }
                        ],
                                                agencyHourProportion: [
                            { required: true, message: "代理商课时提成比不能为空", trigger: "blur" }
                        ],
                                                agencyApplyAmount: [
                            { required: true, message: "代理商报名提成金额不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                recommendManagerId: [
                            { required: true, message: "渠道经理ID不能为空", trigger: "blur" }
                        ],
                                                recommendAgencyId: [
                            { required: true, message: "代理商ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询提成设置表列表 */
            getListPage() {
                this.loading = true;
                listPageDeductSetting(this.queryParams).then(response => {
                    this.deductSettingList = response.data.records;
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
                                                name: undefined,
                                                userType: undefined,
                                                managerHourProportion: undefined,
                                                managerApplyAmount: undefined,
                                                agencyHourProportion: undefined,
                                                agencyApplyAmount: undefined,
                                                operatorId: undefined,
                                                recommendManagerId: undefined,
                                                recommendAgencyId: undefined,
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
                this.ids = selection.map(item => item.${pkColumn.propertyName})
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加提成设置表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const ${pkColumn.propertyName} = row.${pkColumn.propertyName} || this.ids
                getDeductSetting(${pkColumn.propertyName}).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改提成设置表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.${pkColumn.propertyName} != undefined) {
                            updateDeductSetting(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addDeductSetting(this.form).then(response => {
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
                const ${pkColumn.propertyName}s = row.${pkColumn.propertyName} || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delDeductSetting(${pkColumn.propertyName}s);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有提成设置表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportDeductSetting(queryParams);
                }).then(data => {
                    this.download(data, "提成设置表");
                }).catch(function() {});
            }
        }
    };
</script>
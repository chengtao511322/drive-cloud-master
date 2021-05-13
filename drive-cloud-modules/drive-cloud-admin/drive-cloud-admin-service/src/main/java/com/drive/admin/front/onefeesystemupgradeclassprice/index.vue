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
                                                                <el-form-item label="原班类型id" prop="originalClassId">
                        <el-input
                                v-model="queryParams.originalClassId"
                                placeholder="请输入原班类型id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" prop="originalClassType">
                        <el-input
                                v-model="queryParams.originalClassType"
                                placeholder="请输入原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="升级班类型id" prop="upgradeClassId">
                        <el-input
                                v-model="queryParams.upgradeClassId"
                                placeholder="请输入升级班类型id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" prop="upgradeClassType">
                        <el-input
                                v-model="queryParams.upgradeClassType"
                                placeholder="请输入升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="升班价格详情介绍" prop="detailsUrl">
                        <el-input
                                v-model="queryParams.detailsUrl"
                                placeholder="请输入升班价格详情介绍"
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
                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                        <el-input
                                v-model="queryParams.operatorId"
                                placeholder="请输入运营商id(数据权限标记)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="状态(1-正常，2-停用)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态(1-正常，2-停用)"
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
                    v-hasPermi="['admin:oneFeeSystemUpgradeClassPrice:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:oneFeeSystemUpgradeClassPrice:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:oneFeeSystemUpgradeClassPrice:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:oneFeeSystemUpgradeClassPrice:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="oneFeeSystemUpgradeClassPriceList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="原班类型id" align="center" prop="originalClassId" sortable="custom"/>
                                                                <el-table-column label="原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" align="center" prop="originalClassType" sortable="custom"/>
                                                                <el-table-column label="升级班类型id" align="center" prop="upgradeClassId" sortable="custom"/>
                                                                <el-table-column label="升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" align="center" prop="upgradeClassType" sortable="custom"/>
                                                                <el-table-column label="升班班型价格" align="center" prop="upgradePrice" sortable="custom"/>
                                                                <el-table-column label="升班价格详情介绍" align="center" prop="detailsUrl" sortable="custom"/>
                                                                <el-table-column label="是否删除（0-否，1-是）" align="center" prop="isDelete" sortable="custom"/>
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
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="运营商提成金额" align="center" prop="operatorChangeMoney" sortable="custom"/>
                                                                <el-table-column label="平台提成金额（所有上级运营商提成金）" align="center" prop="serviceChangeMoney" sortable="custom"/>
                                                                <el-table-column label="状态(1-正常，2-停用)" align="center" prop="status" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:oneFeeSystemUpgradeClassPrice:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:oneFeeSystemUpgradeClassPrice:delete']"
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

        <!-- 添加或修改一费制学员升班价格控制表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="原班类型id" prop="originalClassId">
                            <el-input v-model="form.originalClassId" placeholder="请输入原班类型id" />
                        </el-form-item>
                                                                                <el-form-item label="原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" prop="originalClassType">
                            <el-input v-model="form.originalClassType" placeholder="请输入原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" />
                        </el-form-item>
                                                                                <el-form-item label="升级班类型id" prop="upgradeClassId">
                            <el-input v-model="form.upgradeClassId" placeholder="请输入升级班类型id" />
                        </el-form-item>
                                                                                <el-form-item label="升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" prop="upgradeClassType">
                            <el-input v-model="form.upgradeClassType" placeholder="请输入升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" />
                        </el-form-item>
                                                                                <el-form-item label="升班班型价格" prop="upgradePrice">
                            <el-input v-model="form.upgradePrice" placeholder="请输入升班班型价格" />
                        </el-form-item>
                                                                                <el-form-item label="升班价格详情介绍" prop="detailsUrl">
                            <el-input v-model="form.detailsUrl" placeholder="请输入升班价格详情介绍" />
                        </el-form-item>
                                                                                <el-form-item label="是否删除（0-否，1-是）" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除（0-否，1-是）" />
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
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="运营商提成金额" prop="operatorChangeMoney">
                            <el-input v-model="form.operatorChangeMoney" placeholder="请输入运营商提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="平台提成金额（所有上级运营商提成金）" prop="serviceChangeMoney">
                            <el-input v-model="form.serviceChangeMoney" placeholder="请输入平台提成金额（所有上级运营商提成金）" />
                        </el-form-item>
                                                                                <el-form-item label="状态(1-正常，2-停用)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(1-正常，2-停用)" />
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
    import { listOneFeeSystemUpgradeClassPrice,listPageOneFeeSystemUpgradeClassPrice, getOneFeeSystemUpgradeClassPrice, delOneFeeSystemUpgradeClassPrice, addOneFeeSystemUpgradeClassPrice, updateOneFeeSystemUpgradeClassPrice, exportOneFeeSystemUpgradeClassPrice } from "@/api/admin/oneFeeSystemUpgradeClassPrice";

    export default {
        name: "OneFeeSystemUpgradeClassPrice",
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
                // 一费制学员升班价格控制表表格数据
                    oneFeeSystemUpgradeClassPriceList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                originalClassId: undefined,
                                                originalClassType: undefined,
                                                upgradeClassId: undefined,
                                                upgradeClassType: undefined,
                                                upgradePrice: undefined,
                                                detailsUrl: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                operatorId: undefined,
                                                operatorChangeMoney: undefined,
                                                serviceChangeMoney: undefined,
                                                status: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                originalClassId: [
                            { required: true, message: "原班类型id不能为空", trigger: "blur" }
                        ],
                                                originalClassType: [
                            { required: true, message: "原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）不能为空", trigger: "blur" }
                        ],
                                                upgradeClassId: [
                            { required: true, message: "升级班类型id不能为空", trigger: "blur" }
                        ],
                                                upgradeClassType: [
                            { required: true, message: "升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）不能为空", trigger: "blur" }
                        ],
                                                upgradePrice: [
                            { required: true, message: "升班班型价格不能为空", trigger: "blur" }
                        ],
                                                detailsUrl: [
                            { required: true, message: "升班价格详情介绍不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除（0-否，1-是）不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                operatorChangeMoney: [
                            { required: true, message: "运营商提成金额不能为空", trigger: "blur" }
                        ],
                                                serviceChangeMoney: [
                            { required: true, message: "平台提成金额（所有上级运营商提成金）不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1-正常，2-停用)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询一费制学员升班价格控制表列表 */
            getListPage() {
                this.loading = true;
                listPageOneFeeSystemUpgradeClassPrice(this.queryParams).then(response => {
                    this.oneFeeSystemUpgradeClassPriceList = response.data.records;
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
                                                originalClassId: undefined,
                                                originalClassType: undefined,
                                                upgradeClassId: undefined,
                                                upgradeClassType: undefined,
                                                upgradePrice: undefined,
                                                detailsUrl: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                operatorId: undefined,
                                                operatorChangeMoney: undefined,
                                                serviceChangeMoney: undefined,
                                                status: undefined,
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
                this.title = "添加一费制学员升班价格控制表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getOneFeeSystemUpgradeClassPrice(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改一费制学员升班价格控制表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateOneFeeSystemUpgradeClassPrice(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addOneFeeSystemUpgradeClassPrice(this.form).then(response => {
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
                    return delOneFeeSystemUpgradeClassPrice(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有一费制学员升班价格控制表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportOneFeeSystemUpgradeClassPrice(queryParams);
                }).then(data => {
                    this.download(data, "一费制学员升班价格控制表");
                }).catch(function() {});
            }
        }
    };
</script>
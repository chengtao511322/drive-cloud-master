<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                        <el-form-item label="主键id" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入主键id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="服务类型（报名、科一、科二、科三、科四）" prop="serviceType">
                        <el-input
                                v-model="queryParams.serviceType"
                                placeholder="请输入服务类型（报名、科一、科二、科三、科四）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="t_platform_service_item表的主键id" prop="serviceItemId">
                        <el-input
                                v-model="queryParams.serviceItemId"
                                placeholder="请输入t_platform_service_item表的主键id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="状态（1、启用，0、停用）" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态（1、启用，0、停用）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否删除（1、是，0、否）" prop="isDelete">
                        <el-input
                                v-model="queryParams.isDelete"
                                placeholder="请输入是否删除（1、是，0、否）"
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
                        v-hasPermi="['admin:serviceItemPrice:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                        type="success"
                        icon="el-icon-edit"
                        size="mini"
                        :disabled="single"
                        @click="handleUpdate"
                        v-hasPermi="['admin:serviceItemPrice:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                        type="danger"
                        icon="el-icon-delete"
                        size="mini"
                        :disabled="multiple"
                        @click="handleDelete"
                        v-hasPermi="['admin:serviceItemPrice:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                        type="warning"
                        icon="el-icon-download"
                        size="mini"
                        @click="handleExport"
                        v-hasPermi="['admin:serviceItemPrice:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="serviceItemPriceList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="主键id" align="center" prop="id" />
                                                                <el-table-column label="服务类型（报名、科一、科二、科三、科四）" align="center" prop="serviceType" />
                                                                <el-table-column label="t_platform_service_item表的主键id" align="center" prop="serviceItemId" />
                                                                <el-table-column label="服务项价格" align="center" prop="price" />
                                                                <el-table-column label="状态（1、启用，0、停用）" align="center" prop="status" />
                                                                <el-table-column label="是否删除（1、是，0、否）" align="center" prop="isDelete" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-edit"
                            @click="handleUpdate(scope.row)"
                            v-hasPermi="['admin:serviceItemPrice:edit']"
                    >修改</el-button>
                    <el-button
                            size="mini"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete(scope.row)"
                            v-hasPermi="['admin:serviceItemPrice:delete']"
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

        <!-- 添加或修改服务项目价格表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键id" />
                        </el-form-item>
                                                                                <el-form-item label="服务类型（报名、科一、科二、科三、科四）" prop="serviceType">
                            <el-input v-model="form.serviceType" placeholder="请输入服务类型（报名、科一、科二、科三、科四）" />
                        </el-form-item>
                                                                                <el-form-item label="t_platform_service_item表的主键id" prop="serviceItemId">
                            <el-input v-model="form.serviceItemId" placeholder="请输入t_platform_service_item表的主键id" />
                        </el-form-item>
                                                                                <el-form-item label="服务项价格" prop="price">
                            <el-input v-model="form.price" placeholder="请输入服务项价格" />
                        </el-form-item>
                                                                                <el-form-item label="状态（1、启用，0、停用）" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态（1、启用，0、停用）" />
                        </el-form-item>
                                                                                <el-form-item label="是否删除（1、是，0、否）" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除（1、是，0、否）" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
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
                                                </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { listServiceItemPrice, getServiceItemPrice, delServiceItemPrice, addServiceItemPrice, updateServiceItemPrice, exportServiceItemPrice } from "@/api/admin/serviceItemPrice";

    export default {
        name: "ServiceItemPrice",
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
                // 服务项目价格表表格数据
                    serviceItemPriceList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                serviceType: undefined,
                                                serviceItemId: undefined,
                                                price: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                operatorId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键id不能为空", trigger: "blur" }
                        ],
                                                serviceType: [
                            { required: true, message: "服务类型（报名、科一、科二、科三、科四）不能为空", trigger: "blur" }
                        ],
                                                serviceItemId: [
                            { required: true, message: "t_platform_service_item表的主键id不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "服务项价格不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态（1、启用，0、停用）不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除（1、是，0、否）不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询服务项目价格表列表 */
            getList() {
                this.loading = true;
                listServiceItemPrice(this.queryParams).then(response => {
                    this.serviceItemPriceList = response.data.records;
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
                                                serviceType: undefined,
                                                serviceItemId: undefined,
                                                price: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                operatorId: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
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
                this.title = "添加服务项目价格表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getServiceItemPrice(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改服务项目价格表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateServiceItemPrice(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addServiceItemPrice(this.form).then(response => {
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
                    return delServiceItemPrice(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有服务项目价格表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportServiceItemPrice(queryParams);
                }).then(data => {
                    this.download(data, "服务项目价格表");
                }).catch(function() {});
            }
        }
    };
</script>
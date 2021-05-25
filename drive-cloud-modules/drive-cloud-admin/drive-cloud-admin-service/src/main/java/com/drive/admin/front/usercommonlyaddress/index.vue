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
                                                                <el-form-item label="用户id" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入用户id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="用户类型(1-学员 2-教练  3-客服 )" prop="userType">
                        <el-input
                                v-model="queryParams.userType"
                                placeholder="请输入用户类型(1-学员 2-教练  3-客服 )"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="地址名称" prop="address">
                        <el-input
                                v-model="queryParams.address"
                                placeholder="请输入地址名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="地址纬度" prop="latitude">
                        <el-input
                                v-model="queryParams.latitude"
                                placeholder="请输入地址纬度"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="地址经度" prop="longitude">
                        <el-input
                                v-model="queryParams.longitude"
                                placeholder="请输入地址经度"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否默认为地址（是，否）" prop="isDefault">
                        <el-input
                                v-model="queryParams.isDefault"
                                placeholder="请输入是否默认为地址（是，否）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="状态(正常，停用)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态(正常，停用)"
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
                                                                <el-form-item label="注册时间" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择注册时间">
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
                                                                <el-form-item label="场地ID" prop="siteId">
                        <el-input
                                v-model="queryParams.siteId"
                                placeholder="请输入场地ID"
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
                    v-hasPermi="['admin:userCommonlyAddress:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:userCommonlyAddress:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:userCommonlyAddress:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:userCommonlyAddress:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="userCommonlyAddressList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="用户id" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="用户类型(1-学员 2-教练  3-客服 )" align="center" prop="userType" sortable="custom"/>
                                                                <el-table-column label="地址名称" align="center" prop="address" sortable="custom"/>
                                                                <el-table-column label="地址纬度" align="center" prop="latitude" sortable="custom"/>
                                                                <el-table-column label="地址经度" align="center" prop="longitude" sortable="custom"/>
                                                                <el-table-column label="是否默认为地址（是，否）" align="center" prop="isDefault" sortable="custom"/>
                                                                <el-table-column label="状态(正常，停用)" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="备注" align="center" prop="remarks" sortable="custom"/>
                                                                <el-table-column label="注册时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="修改时间" align="center" prop="updateTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="场地ID" align="center" prop="siteId" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="subjectType" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:userCommonlyAddress:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:userCommonlyAddress:delete']"
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

        <!-- 添加或修改用户常用地址关联表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键" />
                        </el-form-item>
                                                                                <el-form-item label="用户id" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入用户id" />
                        </el-form-item>
                                                                                <el-form-item label="用户类型(1-学员 2-教练  3-客服 )" prop="userType">
                            <el-input v-model="form.userType" placeholder="请输入用户类型(1-学员 2-教练  3-客服 )" />
                        </el-form-item>
                                                                                <el-form-item label="地址名称" prop="address">
                            <el-input v-model="form.address" placeholder="请输入地址名称" />
                        </el-form-item>
                                                                                <el-form-item label="地址纬度" prop="latitude">
                            <el-input v-model="form.latitude" placeholder="请输入地址纬度" />
                        </el-form-item>
                                                                                <el-form-item label="地址经度" prop="longitude">
                            <el-input v-model="form.longitude" placeholder="请输入地址经度" />
                        </el-form-item>
                                                                                <el-form-item label="是否默认为地址（是，否）" prop="isDefault">
                            <el-input v-model="form.isDefault" placeholder="请输入是否默认为地址（是，否）" />
                        </el-form-item>
                                                                                <el-form-item label="状态(正常，停用)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(正常，停用)" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入备注" />
                        </el-form-item>
                                                                                <el-form-item label="注册时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择注册时间">
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
                                                                                <el-form-item label="场地ID" prop="siteId">
                            <el-input v-model="form.siteId" placeholder="请输入场地ID" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型" prop="subjectType">
                            <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
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
    import { listUserCommonlyAddress,listPageUserCommonlyAddress, getUserCommonlyAddress, delUserCommonlyAddress, addUserCommonlyAddress, updateUserCommonlyAddress, exportUserCommonlyAddress } from "@/api/admin/userCommonlyAddress";

    export default {
        name: "UserCommonlyAddress",
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
                // 用户常用地址关联表表格数据
                    userCommonlyAddressList: [],
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
                                                userType: undefined,
                                                address: undefined,
                                                latitude: undefined,
                                                longitude: undefined,
                                                isDefault: undefined,
                                                status: undefined,
                                                remarks: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                siteId: undefined,
                                                subjectType: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "用户id不能为空", trigger: "blur" }
                        ],
                                                userType: [
                            { required: true, message: "用户类型(1-学员 2-教练  3-客服 )不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "地址名称不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "地址纬度不能为空", trigger: "blur" }
                        ],
                                                longitude: [
                            { required: true, message: "地址经度不能为空", trigger: "blur" }
                        ],
                                                isDefault: [
                            { required: true, message: "是否默认为地址（是，否）不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(正常，停用)不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "注册时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                siteId: [
                            { required: true, message: "场地ID不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询用户常用地址关联表列表 */
            getListPage() {
                this.loading = true;
                listPageUserCommonlyAddress(this.queryParams).then(response => {
                    this.userCommonlyAddressList = response.data.records;
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
                                                userType: undefined,
                                                address: undefined,
                                                latitude: undefined,
                                                longitude: undefined,
                                                isDefault: undefined,
                                                status: undefined,
                                                remarks: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                siteId: undefined,
                                                subjectType: undefined,
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
                this.title = "添加用户常用地址关联表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getUserCommonlyAddress(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改用户常用地址关联表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateUserCommonlyAddress(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addUserCommonlyAddress(this.form).then(response => {
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
                    return delUserCommonlyAddress(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有用户常用地址关联表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportUserCommonlyAddress(queryParams);
                }).then(data => {
                    this.download(data, "用户常用地址关联表");
                }).catch(function() {});
            }
        }
    };
</script>
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
                                                                <el-form-item label="渠道经理表id" prop="managerId">
                        <el-input
                                v-model="queryParams.managerId"
                                placeholder="请输入渠道经理表id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="推广商类型（1-个人，2-商铺）" prop="userType">
                        <el-input
                                v-model="queryParams.userType"
                                placeholder="请输入推广商类型（1-个人，2-商铺）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学员id" prop="studentId">
                        <el-input
                                v-model="queryParams.studentId"
                                placeholder="请输入学员id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="名称(商店，个人，组织)" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入名称(商店，个人，组织)"
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
                                                                <el-form-item label="状态(1-待审核，2-通过，3-驳回)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态(1-待审核，2-通过，3-驳回)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="经度" prop="longitude">
                        <el-input
                                v-model="queryParams.longitude"
                                placeholder="请输入经度"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="纬度" prop="latitude">
                        <el-input
                                v-model="queryParams.latitude"
                                placeholder="请输入纬度"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="详细地址" prop="address">
                        <el-input
                                v-model="queryParams.address"
                                placeholder="请输入详细地址"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="是否删除(0-否，1-是)" prop="isDelete">
                        <el-input
                                v-model="queryParams.isDelete"
                                placeholder="请输入是否删除(0-否，1-是)"
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
                    v-hasPermi="['admin:recommendUser:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:recommendUser:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:recommendUser:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:recommendUser:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="recommendUserList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="id" align="center" prop="id" />
                                                                <el-table-column label="渠道经理表id" align="center" prop="managerId" />
                                                                <el-table-column label="推广商类型（1-个人，2-商铺）" align="center" prop="userType" />
                                                                <el-table-column label="学员id" align="center" prop="studentId" />
                                                                <el-table-column label="名称(商店，个人，组织)" align="center" prop="name" />
                                                                <el-table-column label="备注" align="center" prop="remarks" />
                                                                <el-table-column label="状态(1-待审核，2-通过，3-驳回)" align="center" prop="status" />
                                                                <el-table-column label="经度" align="center" prop="longitude" />
                                                                <el-table-column label="纬度" align="center" prop="latitude" />
                                                                <el-table-column label="详细地址" align="center" prop="address" />
                                                                <el-table-column label="是否删除(0-否，1-是)" align="center" prop="isDelete" />
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
                                                                <el-table-column label="课时提成百分比" align="center" prop="classTiemPercent" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                                                <el-table-column label="报名分成金额" align="center" prop="applyDivideAmount" />
                                                                <el-table-column label="VIP报名分成金额" align="center" prop="vipApplyDivideAmount" />
                                                                <el-table-column label="VIP课时提成百分比" align="center" prop="vipClassTiemPercent" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:recommendUser:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:recommendUser:delete']"
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

        <!-- 添加或修改推广人员信息表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="渠道经理表id" prop="managerId">
                            <el-input v-model="form.managerId" placeholder="请输入渠道经理表id" />
                        </el-form-item>
                                                                                <el-form-item label="推广商类型（1-个人，2-商铺）" prop="userType">
                            <el-input v-model="form.userType" placeholder="请输入推广商类型（1-个人，2-商铺）" />
                        </el-form-item>
                                                                                <el-form-item label="学员id" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入学员id" />
                        </el-form-item>
                                                                                <el-form-item label="名称(商店，个人，组织)" prop="name">
                            <el-input v-model="form.name" placeholder="请输入名称(商店，个人，组织)" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入备注" />
                        </el-form-item>
                                                                                <el-form-item label="状态(1-待审核，2-通过，3-驳回)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(1-待审核，2-通过，3-驳回)" />
                        </el-form-item>
                                                                                <el-form-item label="经度" prop="longitude">
                            <el-input v-model="form.longitude" placeholder="请输入经度" />
                        </el-form-item>
                                                                                <el-form-item label="纬度" prop="latitude">
                            <el-input v-model="form.latitude" placeholder="请输入纬度" />
                        </el-form-item>
                                                                                <el-form-item label="详细地址" prop="address">
                            <el-input v-model="form.address" placeholder="请输入详细地址" />
                        </el-form-item>
                                                                                <el-form-item label="是否删除(0-否，1-是)" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除(0-否，1-是)" />
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
                                                                                <el-form-item label="课时提成百分比" prop="classTiemPercent">
                            <el-input v-model="form.classTiemPercent" placeholder="请输入课时提成百分比" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="报名分成金额" prop="applyDivideAmount">
                            <el-input v-model="form.applyDivideAmount" placeholder="请输入报名分成金额" />
                        </el-form-item>
                                                                                <el-form-item label="VIP报名分成金额" prop="vipApplyDivideAmount">
                            <el-input v-model="form.vipApplyDivideAmount" placeholder="请输入VIP报名分成金额" />
                        </el-form-item>
                                                                                <el-form-item label="VIP课时提成百分比" prop="vipClassTiemPercent">
                            <el-input v-model="form.vipClassTiemPercent" placeholder="请输入VIP课时提成百分比" />
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
    import { listRecommendUser,listPageRecommendUser, getRecommendUser, delRecommendUser, addRecommendUser, updateRecommendUser, exportRecommendUser } from "@/api/admin/recommendUser";

    export default {
        name: "RecommendUser",
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
                // 推广人员信息表表格数据
                    recommendUserList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                managerId: undefined,
                                                userType: undefined,
                                                studentId: undefined,
                                                name: undefined,
                                                remarks: undefined,
                                                status: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                address: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                classTiemPercent: undefined,
                                                operatorId: undefined,
                                                applyDivideAmount: undefined,
                                                vipApplyDivideAmount: undefined,
                                                vipClassTiemPercent: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                managerId: [
                            { required: true, message: "渠道经理表id不能为空", trigger: "blur" }
                        ],
                                                userType: [
                            { required: true, message: "推广商类型（1-个人，2-商铺）不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "学员id不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "名称(商店，个人，组织)不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1-待审核，2-通过，3-驳回)不能为空", trigger: "blur" }
                        ],
                                                longitude: [
                            { required: true, message: "经度不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "纬度不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "详细地址不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除(0-否，1-是)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                classTiemPercent: [
                            { required: true, message: "课时提成百分比不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                applyDivideAmount: [
                            { required: true, message: "报名分成金额不能为空", trigger: "blur" }
                        ],
                                                vipApplyDivideAmount: [
                            { required: true, message: "VIP报名分成金额不能为空", trigger: "blur" }
                        ],
                                                vipClassTiemPercent: [
                            { required: true, message: "VIP课时提成百分比不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询推广人员信息表列表 */
            getListPage() {
                this.loading = true;
                listPageRecommendUser(this.queryParams).then(response => {
                    this.recommendUserList = response.data.records;
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
                                                managerId: undefined,
                                                userType: undefined,
                                                studentId: undefined,
                                                name: undefined,
                                                remarks: undefined,
                                                status: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                address: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                classTiemPercent: undefined,
                                                operatorId: undefined,
                                                applyDivideAmount: undefined,
                                                vipApplyDivideAmount: undefined,
                                                vipClassTiemPercent: undefined,
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
                this.title = "添加推广人员信息表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getRecommendUser(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改推广人员信息表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateRecommendUser(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addRecommendUser(this.form).then(response => {
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
                    return delRecommendUser(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有推广人员信息表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportRecommendUser(queryParams);
                }).then(data => {
                    this.download(data, "推广人员信息表");
                }).catch(function() {});
            }
        }
    };
</script>
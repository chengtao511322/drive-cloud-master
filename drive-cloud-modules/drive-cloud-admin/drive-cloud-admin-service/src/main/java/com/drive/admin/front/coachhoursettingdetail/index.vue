<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                        <el-form-item label="" prop="id">
                        <el-input
                                v-model="queryParams.id"
                                placeholder="请输入"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="时间段(1-上午;2-下午;3-晚上)" prop="timeSection">
                        <el-input
                                v-model="queryParams.timeSection"
                                placeholder="请输入时间段(1-上午;2-下午;3-晚上)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="开始时间(只存时分，所以使用字符串)" prop="startTime">
                        <el-input
                                v-model="queryParams.startTime"
                                placeholder="请输入开始时间(只存时分，所以使用字符串)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="结束时间(只存时分，所以使用字符串)" prop="endTime">
                        <el-input
                                v-model="queryParams.endTime"
                                placeholder="请输入结束时间(只存时分，所以使用字符串)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="运营商ID" prop="operatorId">
                        <el-input
                                v-model="queryParams.operatorId"
                                placeholder="请输入运营商ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建者" prop="createUser">
                        <el-input
                                v-model="queryParams.createUser"
                                placeholder="请输入创建者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建时间  系统自动创建" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择创建时间  系统自动创建">
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
                                                                <el-form-item label="是否删除" prop="isDelete">
                        <el-input
                                v-model="queryParams.isDelete"
                                placeholder="请输入是否删除"
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
                    v-hasPermi="['admin:coachHourSettingDetail:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:coachHourSettingDetail:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:coachHourSettingDetail:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:coachHourSettingDetail:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="coachHourSettingDetailList" @selection-change="handleSelectionChange"
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

                                                <el-table-column label="" align="center" prop="id" sortable="custom"/>
                                                                <el-table-column label="时间段(1-上午;2-下午;3-晚上)" align="center" prop="timeSection" sortable="custom"/>
                                                                <el-table-column label="开始时间(只存时分，所以使用字符串)" align="center" prop="startTime" sortable="custom"/>
                                                                <el-table-column label="结束时间(只存时分，所以使用字符串)" align="center" prop="endTime" sortable="custom"/>
                                                                <el-table-column label="运营商ID" align="center" prop="operatorId" sortable="custom"/>
                                                                <el-table-column label="创建者" align="center" prop="createUser" sortable="custom"/>
                                                                <el-table-column label="创建时间  系统自动创建" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="修改时间" align="center" prop="updateTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="是否删除" align="center" prop="isDelete" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:coachHourSettingDetail:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:coachHourSettingDetail:delete']"
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

        <!-- 添加或修改运营商教练课时设置表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="时间段(1-上午;2-下午;3-晚上)" prop="timeSection">
                            <el-input v-model="form.timeSection" placeholder="请输入时间段(1-上午;2-下午;3-晚上)" />
                        </el-form-item>
                                                                                <el-form-item label="开始时间(只存时分，所以使用字符串)" prop="startTime">
                            <el-input v-model="form.startTime" placeholder="请输入开始时间(只存时分，所以使用字符串)" />
                        </el-form-item>
                                                                                <el-form-item label="结束时间(只存时分，所以使用字符串)" prop="endTime">
                            <el-input v-model="form.endTime" placeholder="请输入结束时间(只存时分，所以使用字符串)" />
                        </el-form-item>
                                                                                <el-form-item label="运营商ID" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商ID" />
                        </el-form-item>
                                                                                <el-form-item label="创建者" prop="createUser">
                            <el-input v-model="form.createUser" placeholder="请输入创建者" />
                        </el-form-item>
                                                                                <el-form-item label="创建时间  系统自动创建" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间  系统自动创建">
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
                                                                                <el-form-item label="是否删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
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
    import { listCoachHourSettingDetail,listPageCoachHourSettingDetail, getCoachHourSettingDetail, delCoachHourSettingDetail, addCoachHourSettingDetail, updateCoachHourSettingDetail, exportCoachHourSettingDetail } from "@/api/admin/coachHourSettingDetail";

    export default {
        name: "CoachHourSettingDetail",
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
                // 运营商教练课时设置表表格数据
                    coachHourSettingDetailList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                timeSection: undefined,
                                                startTime: undefined,
                                                endTime: undefined,
                                                operatorId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                timeSection: [
                            { required: true, message: "时间段(1-上午;2-下午;3-晚上)不能为空", trigger: "blur" }
                        ],
                                                startTime: [
                            { required: true, message: "开始时间(只存时分，所以使用字符串)不能为空", trigger: "blur" }
                        ],
                                                endTime: [
                            { required: true, message: "结束时间(只存时分，所以使用字符串)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商ID不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间  系统自动创建不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询运营商教练课时设置表列表 */
            getListPage() {
                this.loading = true;
                listPageCoachHourSettingDetail(this.queryParams).then(response => {
                    this.coachHourSettingDetailList = response.data.records;
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
                                                timeSection: undefined,
                                                startTime: undefined,
                                                endTime: undefined,
                                                operatorId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
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
                this.title = "添加运营商教练课时设置表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getCoachHourSettingDetail(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改运营商教练课时设置表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateCoachHourSettingDetail(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addCoachHourSettingDetail(this.form).then(response => {
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
                    return delCoachHourSettingDetail(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有运营商教练课时设置表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportCoachHourSettingDetail(queryParams);
                }).then(data => {
                    this.download(data, "运营商教练课时设置表");
                }).catch(function() {});
            }
        }
    };
</script>
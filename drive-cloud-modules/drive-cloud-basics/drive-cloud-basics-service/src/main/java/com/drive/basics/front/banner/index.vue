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
                                                                <el-form-item label="banner的主题名称" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入banner的主题名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="banner时间控制" prop="timer">
                        <el-input
                                v-model="queryParams.timer"
                                placeholder="请输入banner时间控制"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="添加者" prop="addUser">
                        <el-input
                                v-model="queryParams.addUser"
                                placeholder="请输入添加者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="远程图片路径" prop="urlPath">
                        <el-input
                                v-model="queryParams.urlPath"
                                placeholder="请输入远程图片路径"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="banner 所属栏目  如活动" prop="channel">
                        <el-input
                                v-model="queryParams.channel"
                                placeholder="请输入banner 所属栏目  如活动"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="banner点击地址" prop="clickUrl">
                        <el-input
                                v-model="queryParams.clickUrl"
                                placeholder="请输入banner点击地址"
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
                                                                                            <el-form-item label="标题" prop="title">
                        <el-input
                                v-model="queryParams.title"
                                placeholder="请输入标题"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="扩展字段" prop="extend">
                        <el-input
                                v-model="queryParams.extend"
                                placeholder="请输入扩展字段"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="运营位置 ，存放省市区编码，多个用逗号隔开" prop="operatingPosition">
                        <el-input
                                v-model="queryParams.operatingPosition"
                                placeholder="请输入运营位置 ，存放省市区编码，多个用逗号隔开"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="租户ID" prop="tenantId">
                        <el-input
                                v-model="queryParams.tenantId"
                                placeholder="请输入租户ID"
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
                    v-hasPermi="['basics:banner:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['basics:banner:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['basics:banner:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['basics:banner:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="bannerList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="id" align="center" prop="id" />
                                                                <el-table-column label="banner的主题名称" align="center" prop="name" />
                                                                <el-table-column label="发布状态0未发布 1发布" align="center" prop="status" />
                                                                <el-table-column label="排序号" align="center" prop="sort" />
                                                                <el-table-column label="banner时间控制" align="center" prop="timer" />
                                                                <el-table-column label="删除状态 0 未删除1删除" align="center" prop="isDelete" />
                                                                <el-table-column label="添加者" align="center" prop="addUser" />
                                                                <el-table-column label="远程图片路径" align="center" prop="urlPath" />
                                                                <el-table-column label="banner 所属栏目  如活动" align="center" prop="channel" />
                                                                <el-table-column label="banner点击地址" align="center" prop="clickUrl" />
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置" align="center" prop="type" />
                                                                <el-table-column label="标题" align="center" prop="title" />
                                                                <el-table-column label="扩展字段" align="center" prop="extend" />
                                                                <el-table-column label="运营位置 ，存放省市区编码，多个用逗号隔开" align="center" prop="operatingPosition" />
                                                                <el-table-column label="租户ID" align="center" prop="tenantId" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['basics:banner:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['basics:banner:delete']"
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

        <!-- 添加或修改banner 轮播图对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="banner的主题名称" prop="name">
                            <el-input v-model="form.name" placeholder="请输入banner的主题名称" />
                        </el-form-item>
                                                                                <el-form-item label="发布状态0未发布 1发布" prop="status">
                            <el-input v-model="form.status" placeholder="请输入发布状态0未发布 1发布" />
                        </el-form-item>
                                                                                <el-form-item label="排序号" prop="sort">
                            <el-input v-model="form.sort" placeholder="请输入排序号" />
                        </el-form-item>
                                                                                <el-form-item label="banner时间控制" prop="timer">
                            <el-input v-model="form.timer" placeholder="请输入banner时间控制" />
                        </el-form-item>
                                                                                <el-form-item label="删除状态 0 未删除1删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入删除状态 0 未删除1删除" />
                        </el-form-item>
                                                                                <el-form-item label="添加者" prop="addUser">
                            <el-input v-model="form.addUser" placeholder="请输入添加者" />
                        </el-form-item>
                                                                                <el-form-item label="远程图片路径" prop="urlPath">
                            <el-input v-model="form.urlPath" placeholder="请输入远程图片路径" />
                        </el-form-item>
                                                                                <el-form-item label="banner 所属栏目  如活动" prop="channel">
                            <el-input v-model="form.channel" placeholder="请输入banner 所属栏目  如活动" />
                        </el-form-item>
                                                                                <el-form-item label="banner点击地址" prop="clickUrl">
                            <el-input v-model="form.clickUrl" placeholder="请输入banner点击地址" />
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
                                                                                <el-form-item label="banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置" prop="type">
                            <el-input v-model="form.type" placeholder="请输入banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置" />
                        </el-form-item>
                                                                                <el-form-item label="标题" prop="title">
                            <el-input v-model="form.title" placeholder="请输入标题" />
                        </el-form-item>
                                                                                <el-form-item label="扩展字段" prop="extend">
                            <el-input v-model="form.extend" placeholder="请输入扩展字段" />
                        </el-form-item>
                                                                                <el-form-item label="运营位置 ，存放省市区编码，多个用逗号隔开" prop="operatingPosition">
                            <el-input v-model="form.operatingPosition" placeholder="请输入运营位置 ，存放省市区编码，多个用逗号隔开" />
                        </el-form-item>
                                                                                <el-form-item label="租户ID" prop="tenantId">
                            <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
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
    import { listBanner, getBanner, delBanner, addBanner, updateBanner, exportBanner } from "@/api/basics/banner";

    export default {
        name: "Banner",
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
                // banner 轮播图表格数据
                    bannerList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                name: undefined,
                                                status: undefined,
                                                sort: undefined,
                                                timer: undefined,
                                                isDelete: undefined,
                                                addUser: undefined,
                                                urlPath: undefined,
                                                channel: undefined,
                                                clickUrl: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                type: undefined,
                                                title: undefined,
                                                extend: undefined,
                                                operatingPosition: undefined,
                                                tenantId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "banner的主题名称不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "发布状态0未发布 1发布不能为空", trigger: "blur" }
                        ],
                                                sort: [
                            { required: true, message: "排序号不能为空", trigger: "blur" }
                        ],
                                                timer: [
                            { required: true, message: "banner时间控制不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 0 未删除1删除不能为空", trigger: "blur" }
                        ],
                                                addUser: [
                            { required: true, message: "添加者不能为空", trigger: "blur" }
                        ],
                                                urlPath: [
                            { required: true, message: "远程图片路径不能为空", trigger: "blur" }
                        ],
                                                channel: [
                            { required: true, message: "banner 所属栏目  如活动不能为空", trigger: "blur" }
                        ],
                                                clickUrl: [
                            { required: true, message: "banner点击地址不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                type: [
                            { required: true, message: "banner位置类型 0 ：首页轮播轮播位置 1：学车报名轮播位置 2考试报名轮播位置3常规训练轮播位置4考试训练轮播位置不能为空", trigger: "blur" }
                        ],
                                                title: [
                            { required: true, message: "标题不能为空", trigger: "blur" }
                        ],
                                                extend: [
                            { required: true, message: "扩展字段不能为空", trigger: "blur" }
                        ],
                                                operatingPosition: [
                            { required: true, message: "运营位置 ，存放省市区编码，多个用逗号隔开不能为空", trigger: "blur" }
                        ],
                                                tenantId: [
                            { required: true, message: "租户ID不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询banner 轮播图列表 */
            getList() {
                this.loading = true;
                listBanner(this.queryParams).then(response => {
                    this.bannerList = response.data.records;
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
                                                name: undefined,
                                                status: undefined,
                                                sort: undefined,
                                                timer: undefined,
                                                isDelete: undefined,
                                                addUser: undefined,
                                                urlPath: undefined,
                                                channel: undefined,
                                                clickUrl: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                type: undefined,
                                                title: undefined,
                                                extend: undefined,
                                                operatingPosition: undefined,
                                                tenantId: undefined,
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
                this.title = "添加banner 轮播图";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getBanner(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改banner 轮播图";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateBanner(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addBanner(this.form).then(response => {
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
                    return delBanner(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有banner 轮播图数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportBanner(queryParams);
                }).then(data => {
                    this.download(data, "banner 轮播图");
                }).catch(function() {});
            }
        }
    };
</script>
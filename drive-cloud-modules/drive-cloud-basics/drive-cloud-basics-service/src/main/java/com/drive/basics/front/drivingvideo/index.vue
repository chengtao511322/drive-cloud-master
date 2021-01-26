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
                                                                                            <el-form-item label="名称" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
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
                                                                <el-form-item label="描述" prop="description">
                        <el-input
                                v-model="queryParams.description"
                                placeholder="请输入描述"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="缩略图路径" prop="thumb">
                        <el-input
                                v-model="queryParams.thumb"
                                placeholder="请输入缩略图路径"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="视频链接" prop="link">
                        <el-input
                                v-model="queryParams.link"
                                placeholder="请输入视频链接"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="文件路径" prop="path">
                        <el-input
                                v-model="queryParams.path"
                                placeholder="请输入文件路径"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="文件链接" prop="url">
                        <el-input
                                v-model="queryParams.url"
                                placeholder="请输入文件链接"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="文件大小" prop="size">
                        <el-input
                                v-model="queryParams.size"
                                placeholder="请输入文件大小"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="文件md5" prop="md5">
                        <el-input
                                v-model="queryParams.md5"
                                placeholder="请输入文件md5"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="sha1 散列值" prop="sha1">
                        <el-input
                                v-model="queryParams.sha1"
                                placeholder="请输入sha1 散列值"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                                                                                <el-form-item label="上传时间" prop="uploadTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.uploadTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择上传时间">
                        </el-date-picker>
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
                                                                <el-form-item label="模块名 " prop="module">
                        <el-input
                                v-model="queryParams.module"
                                placeholder="请输入模块名 "
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
                    v-hasPermi="['basics:drivingVideo:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['basics:drivingVideo:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['basics:drivingVideo:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['basics:drivingVideo:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="drivingVideoList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="" align="center" prop="id" />
                                                                <el-table-column label="是否对外开放  0 否 1 是" align="center" prop="isOpen" />
                                                                <el-table-column label="名称" align="center" prop="name" />
                                                                <el-table-column label="标题" align="center" prop="title" />
                                                                <el-table-column label="描述" align="center" prop="description" />
                                                                <el-table-column label="缩略图路径" align="center" prop="thumb" />
                                                                <el-table-column label="视频链接" align="center" prop="link" />
                                                                <el-table-column label="文件路径" align="center" prop="path" />
                                                                <el-table-column label="文件链接" align="center" prop="url" />
                                                                <el-table-column label="文件类型" align="center" prop="ext" />
                                                                <el-table-column label="文件大小" align="center" prop="size" />
                                                                <el-table-column label="文件md5" align="center" prop="md5" />
                                                                <el-table-column label="sha1 散列值" align="center" prop="sha1" />
                                                                <el-table-column label="来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows" align="center" prop="fromPlatform" />
                                                                <el-table-column label="排序" align="center" prop="sort" />
                                                                <el-table-column label="发布状态0未发布 1发布" align="center" prop="status" />
                                                                <el-table-column label="删除状态 0  未删除 1  已经删除" align="center" prop="isDelete" />
                                                                <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.uploadTime) }}</span>
                        </template>
                    </el-table-column>
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
                                                                <el-table-column label="模块名 " align="center" prop="module" />
                                                                <el-table-column label="类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有" align="center" prop="type" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['basics:drivingVideo:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['basics:drivingVideo:delete']"
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

        <!-- 添加或修改学车视频表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="是否对外开放  0 否 1 是" prop="isOpen">
                            <el-input v-model="form.isOpen" placeholder="请输入是否对外开放  0 否 1 是" />
                        </el-form-item>
                                                                                <el-form-item label="名称" prop="name">
                            <el-input v-model="form.name" placeholder="请输入名称" />
                        </el-form-item>
                                                                                <el-form-item label="标题" prop="title">
                            <el-input v-model="form.title" placeholder="请输入标题" />
                        </el-form-item>
                                                                                <el-form-item label="描述" prop="description">
                            <el-input v-model="form.description" placeholder="请输入描述" />
                        </el-form-item>
                                                                                <el-form-item label="缩略图路径" prop="thumb">
                            <el-input v-model="form.thumb" placeholder="请输入缩略图路径" />
                        </el-form-item>
                                                                                <el-form-item label="视频链接" prop="link">
                            <el-input v-model="form.link" placeholder="请输入视频链接" />
                        </el-form-item>
                                                                                <el-form-item label="文件路径" prop="path">
                            <el-input v-model="form.path" placeholder="请输入文件路径" />
                        </el-form-item>
                                                                                <el-form-item label="文件链接" prop="url">
                            <el-input v-model="form.url" placeholder="请输入文件链接" />
                        </el-form-item>
                                                                                <el-form-item label="文件类型" prop="ext">
                            <el-input v-model="form.ext" placeholder="请输入文件类型" />
                        </el-form-item>
                                                                                <el-form-item label="文件大小" prop="size">
                            <el-input v-model="form.size" placeholder="请输入文件大小" />
                        </el-form-item>
                                                                                <el-form-item label="文件md5" prop="md5">
                            <el-input v-model="form.md5" placeholder="请输入文件md5" />
                        </el-form-item>
                                                                                <el-form-item label="sha1 散列值" prop="sha1">
                            <el-input v-model="form.sha1" placeholder="请输入sha1 散列值" />
                        </el-form-item>
                                                                                <el-form-item label="来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows" prop="fromPlatform">
                            <el-input v-model="form.fromPlatform" placeholder="请输入来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows" />
                        </el-form-item>
                                                                                <el-form-item label="排序" prop="sort">
                            <el-input v-model="form.sort" placeholder="请输入排序" />
                        </el-form-item>
                                                                                <el-form-item label="发布状态0未发布 1发布" prop="status">
                            <el-input v-model="form.status" placeholder="请输入发布状态0未发布 1发布" />
                        </el-form-item>
                                                                                <el-form-item label="删除状态 0  未删除 1  已经删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入删除状态 0  未删除 1  已经删除" />
                        </el-form-item>
                                                                                <el-form-item label="上传时间" prop="uploadTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.uploadTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择上传时间">
                            </el-date-picker>
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
                                                                                <el-form-item label="模块名 " prop="module">
                            <el-input v-model="form.module" placeholder="请输入模块名 " />
                        </el-form-item>
                                                                                <el-form-item label="类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有" prop="type">
                            <el-input v-model="form.type" placeholder="请输入类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有" />
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
    import { listDrivingVideo, getDrivingVideo, delDrivingVideo, addDrivingVideo, updateDrivingVideo, exportDrivingVideo } from "@/api/basics/drivingVideo";

    export default {
        name: "DrivingVideo",
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
                // 学车视频表表格数据
                    drivingVideoList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                isOpen: undefined,
                                                name: undefined,
                                                title: undefined,
                                                description: undefined,
                                                thumb: undefined,
                                                link: undefined,
                                                path: undefined,
                                                url: undefined,
                                                ext: undefined,
                                                size: undefined,
                                                md5: undefined,
                                                sha1: undefined,
                                                fromPlatform: undefined,
                                                sort: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                uploadTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                module: undefined,
                                                type: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                isOpen: [
                            { required: true, message: "是否对外开放  0 否 1 是不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "名称不能为空", trigger: "blur" }
                        ],
                                                title: [
                            { required: true, message: "标题不能为空", trigger: "blur" }
                        ],
                                                description: [
                            { required: true, message: "描述不能为空", trigger: "blur" }
                        ],
                                                thumb: [
                            { required: true, message: "缩略图路径不能为空", trigger: "blur" }
                        ],
                                                link: [
                            { required: true, message: "视频链接不能为空", trigger: "blur" }
                        ],
                                                path: [
                            { required: true, message: "文件路径不能为空", trigger: "blur" }
                        ],
                                                url: [
                            { required: true, message: "文件链接不能为空", trigger: "blur" }
                        ],
                                                ext: [
                            { required: true, message: "文件类型不能为空", trigger: "blur" }
                        ],
                                                size: [
                            { required: true, message: "文件大小不能为空", trigger: "blur" }
                        ],
                                                md5: [
                            { required: true, message: "文件md5不能为空", trigger: "blur" }
                        ],
                                                sha1: [
                            { required: true, message: "sha1 散列值不能为空", trigger: "blur" }
                        ],
                                                fromPlatform: [
                            { required: true, message: "来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows不能为空", trigger: "blur" }
                        ],
                                                sort: [
                            { required: true, message: "排序不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "发布状态0未发布 1发布不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 0  未删除 1  已经删除不能为空", trigger: "blur" }
                        ],
                                                uploadTime: [
                            { required: true, message: "上传时间不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                module: [
                            { required: true, message: "模块名 不能为空", trigger: "blur" }
                        ],
                                                type: [
                            { required: true, message: "类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询学车视频表列表 */
            getList() {
                this.loading = true;
                listDrivingVideo(this.queryParams).then(response => {
                    this.drivingVideoList = response.data.records;
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
                                                isOpen: undefined,
                                                name: undefined,
                                                title: undefined,
                                                description: undefined,
                                                thumb: undefined,
                                                link: undefined,
                                                path: undefined,
                                                url: undefined,
                                                ext: undefined,
                                                size: undefined,
                                                md5: undefined,
                                                sha1: undefined,
                                                fromPlatform: undefined,
                                                sort: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                uploadTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                module: undefined,
                                                type: undefined,
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
                this.title = "添加学车视频表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getDrivingVideo(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学车视频表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateDrivingVideo(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addDrivingVideo(this.form).then(response => {
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
                    return delDrivingVideo(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学车视频表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportDrivingVideo(queryParams);
                }).then(data => {
                    this.download(data, "学车视频表");
                }).catch(function() {});
            }
        }
    };
</script>
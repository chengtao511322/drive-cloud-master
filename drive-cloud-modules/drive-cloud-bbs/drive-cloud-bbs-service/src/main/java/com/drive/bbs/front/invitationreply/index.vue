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
                                                                <el-form-item label="父id(用于记录用户评价回复的内容)" prop="superId">
                        <el-input
                                v-model="queryParams.superId"
                                placeholder="请输入父id(用于记录用户评价回复的内容)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="论坛帖子表id" prop="invitationId">
                        <el-input
                                v-model="queryParams.invitationId"
                                placeholder="请输入论坛帖子表id"
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
                                                                                                                        <el-form-item label="文本内容" prop="textContent">
                        <el-input
                                v-model="queryParams.textContent"
                                placeholder="请输入文本内容"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="图片路径" prop="imgUrl">
                        <el-input
                                v-model="queryParams.imgUrl"
                                placeholder="请输入图片路径"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="视频路径" prop="videoUrl">
                        <el-input
                                v-model="queryParams.videoUrl"
                                placeholder="请输入视频路径"
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
                                                                <el-form-item label="回复时间" prop="replyTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.replyTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择回复时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                            <el-form-item label="和谐前内容" prop="rawTextContent">
                        <el-input
                                v-model="queryParams.rawTextContent"
                                placeholder="请输入和谐前内容"
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
                    v-hasPermi="['bbs:invitationReply:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['bbs:invitationReply:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['bbs:invitationReply:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['bbs:invitationReply:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="invitationReplyList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="父id(用于记录用户评价回复的内容)" align="center" prop="superId" sortable="custom"/>
                                                                <el-table-column label="论坛帖子表id" align="center" prop="invitationId" sortable="custom"/>
                                                                <el-table-column label="用户id" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="用户操作状态(1-已发布，2-已删除，3-内容违规)" align="center" prop="userOperateStatus" sortable="custom"/>
                                                                <el-table-column label="状态(1：正常；2：停用)" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="文本内容" align="center" prop="textContent" sortable="custom"/>
                                                                <el-table-column label="图片路径" align="center" prop="imgUrl" sortable="custom"/>
                                                                <el-table-column label="视频路径" align="center" prop="videoUrl" sortable="custom"/>
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
                                                                <el-table-column label="回复时间" align="center" prop="replyTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.replyTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="删除状态 1  未删除  2 已经删除" align="center" prop="isDelete" sortable="custom"/>
                                                                <el-table-column label="和谐前内容" align="center" prop="rawTextContent" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['bbs:invitationReply:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['bbs:invitationReply:delete']"
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

        <!-- 添加或修改论坛帖子回复表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="父id(用于记录用户评价回复的内容)" prop="superId">
                            <el-input v-model="form.superId" placeholder="请输入父id(用于记录用户评价回复的内容)" />
                        </el-form-item>
                                                                                <el-form-item label="论坛帖子表id" prop="invitationId">
                            <el-input v-model="form.invitationId" placeholder="请输入论坛帖子表id" />
                        </el-form-item>
                                                                                <el-form-item label="用户id" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入用户id" />
                        </el-form-item>
                                                                                <el-form-item label="用户操作状态(1-已发布，2-已删除，3-内容违规)" prop="userOperateStatus">
                            <el-input v-model="form.userOperateStatus" placeholder="请输入用户操作状态(1-已发布，2-已删除，3-内容违规)" />
                        </el-form-item>
                                                                                <el-form-item label="状态(1：正常；2：停用)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(1：正常；2：停用)" />
                        </el-form-item>
                                                                                <el-form-item label="文本内容" prop="textContent">
                            <el-input v-model="form.textContent" placeholder="请输入文本内容" />
                        </el-form-item>
                                                                                <el-form-item label="图片路径" prop="imgUrl">
                            <el-input v-model="form.imgUrl" placeholder="请输入图片路径" />
                        </el-form-item>
                                                                                <el-form-item label="视频路径" prop="videoUrl">
                            <el-input v-model="form.videoUrl" placeholder="请输入视频路径" />
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
                                                                                <el-form-item label="回复时间" prop="replyTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.replyTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择回复时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="删除状态 1  未删除  2 已经删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入删除状态 1  未删除  2 已经删除" />
                        </el-form-item>
                                                                                <el-form-item label="和谐前内容" prop="rawTextContent">
                            <el-input v-model="form.rawTextContent" placeholder="请输入和谐前内容" />
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
    import { listInvitationReply,listPageInvitationReply, getInvitationReply, delInvitationReply, addInvitationReply, updateInvitationReply, exportInvitationReply } from "@/api/bbs/invitationReply";

    export default {
        name: "InvitationReply",
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
                // 论坛帖子回复表表格数据
                    invitationReplyList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                superId: undefined,
                                                invitationId: undefined,
                                                userId: undefined,
                                                userOperateStatus: undefined,
                                                status: undefined,
                                                textContent: undefined,
                                                imgUrl: undefined,
                                                videoUrl: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                replyTime: undefined,
                                                isDelete: undefined,
                                                rawTextContent: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                superId: [
                            { required: true, message: "父id(用于记录用户评价回复的内容)不能为空", trigger: "blur" }
                        ],
                                                invitationId: [
                            { required: true, message: "论坛帖子表id不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "用户id不能为空", trigger: "blur" }
                        ],
                                                userOperateStatus: [
                            { required: true, message: "用户操作状态(1-已发布，2-已删除，3-内容违规)不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1：正常；2：停用)不能为空", trigger: "blur" }
                        ],
                                                textContent: [
                            { required: true, message: "文本内容不能为空", trigger: "blur" }
                        ],
                                                imgUrl: [
                            { required: true, message: "图片路径不能为空", trigger: "blur" }
                        ],
                                                videoUrl: [
                            { required: true, message: "视频路径不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                replyTime: [
                            { required: true, message: "回复时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 1  未删除  2 已经删除不能为空", trigger: "blur" }
                        ],
                                                rawTextContent: [
                            { required: true, message: "和谐前内容不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询论坛帖子回复表列表 */
            getListPage() {
                this.loading = true;
                listPageInvitationReply(this.queryParams).then(response => {
                    this.invitationReplyList = response.data.records;
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
                                                superId: undefined,
                                                invitationId: undefined,
                                                userId: undefined,
                                                userOperateStatus: undefined,
                                                status: undefined,
                                                textContent: undefined,
                                                imgUrl: undefined,
                                                videoUrl: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                replyTime: undefined,
                                                isDelete: undefined,
                                                rawTextContent: undefined,
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
                this.title = "添加论坛帖子回复表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getInvitationReply(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改论坛帖子回复表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateInvitationReply(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addInvitationReply(this.form).then(response => {
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
                    return delInvitationReply(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有论坛帖子回复表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportInvitationReply(queryParams);
                }).then(data => {
                    this.download(data, "论坛帖子回复表");
                }).catch(function() {});
            }
        }
    };
</script>
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
                                                                <el-form-item label="视频内容" prop="videoUrl">
                        <el-input
                                v-model="queryParams.videoUrl"
                                placeholder="请输入视频内容"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="用户ID" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入用户ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="当前位置" prop="address">
                        <el-input
                                v-model="queryParams.address"
                                placeholder="请输入当前位置"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                                                    <el-form-item label="发布时间" prop="releaseTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.releaseTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择发布时间">
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
                                                                <el-form-item label="更新时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择更新时间">
                        </el-date-picker>
                    </el-form-item>
                                                                                                                                                                                                                                        <el-form-item label="和谐前内容（原内容）" prop="rawTextContent">
                        <el-input
                                v-model="queryParams.rawTextContent"
                                placeholder="请输入和谐前内容（原内容）"
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
                    v-hasPermi="['bbs:invitation:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['bbs:invitation:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['bbs:invitation:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['bbs:invitation:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="invitationList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="文本内容" align="center" prop="textContent" sortable="custom"/>
                                                                <el-table-column label="图片路径" align="center" prop="imgUrl" sortable="custom"/>
                                                                <el-table-column label="视频内容" align="center" prop="videoUrl" sortable="custom"/>
                                                                <el-table-column label="用户ID" align="center" prop="userId" sortable="custom"/>
                                                                <el-table-column label="用户操作状态(1-已发布，2-已删除，3-内容违规)" align="center" prop="userOperateStatus" sortable="custom"/>
                                                                <el-table-column label="当前位置" align="center" prop="address" sortable="custom"/>
                                                                <el-table-column label="点赞次数" align="center" prop="praiseSize" sortable="custom"/>
                                                                <el-table-column label="收藏次数" align="center" prop="collectionSize" sortable="custom"/>
                                                                <el-table-column label="浏览次数" align="center" prop="browseSize" sortable="custom"/>
                                                                <el-table-column label="发布时间" align="center" prop="releaseTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.releaseTime) }}</span>
                        </template>
                    </el-table-column>
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
                                                                <el-table-column label="状态(1：正常；2：停用)" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="删除状态 0 未删除  1 已经删除" align="center" prop="isDelete" sortable="custom"/>
                                                                <el-table-column label="评论数" align="center" prop="commentSize" sortable="custom"/>
                                                                <el-table-column label="是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏" align="center" prop="isGive" sortable="custom"/>
                                                                <el-table-column label="是否屏蔽  1 " align="center" prop="isShield" sortable="custom"/>
                                                                <el-table-column label="首次发帖标识 1 首次 2 不是" align="center" prop="isInitialIssue" sortable="custom"/>
                                                                <el-table-column label="和谐前内容（原内容）" align="center" prop="rawTextContent" sortable="custom"/>
                                                                <el-table-column label="" align="center" prop="isTop" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['bbs:invitation:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['bbs:invitation:delete']"
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

        <!-- 添加或修改论坛帖子表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入id" />
                        </el-form-item>
                                                                                <el-form-item label="文本内容" prop="textContent">
                            <el-input v-model="form.textContent" placeholder="请输入文本内容" />
                        </el-form-item>
                                                                                <el-form-item label="图片路径" prop="imgUrl">
                            <el-input v-model="form.imgUrl" placeholder="请输入图片路径" />
                        </el-form-item>
                                                                                <el-form-item label="视频内容" prop="videoUrl">
                            <el-input v-model="form.videoUrl" placeholder="请输入视频内容" />
                        </el-form-item>
                                                                                <el-form-item label="用户ID" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入用户ID" />
                        </el-form-item>
                                                                                <el-form-item label="用户操作状态(1-已发布，2-已删除，3-内容违规)" prop="userOperateStatus">
                            <el-input v-model="form.userOperateStatus" placeholder="请输入用户操作状态(1-已发布，2-已删除，3-内容违规)" />
                        </el-form-item>
                                                                                <el-form-item label="当前位置" prop="address">
                            <el-input v-model="form.address" placeholder="请输入当前位置" />
                        </el-form-item>
                                                                                <el-form-item label="点赞次数" prop="praiseSize">
                            <el-input v-model="form.praiseSize" placeholder="请输入点赞次数" />
                        </el-form-item>
                                                                                <el-form-item label="收藏次数" prop="collectionSize">
                            <el-input v-model="form.collectionSize" placeholder="请输入收藏次数" />
                        </el-form-item>
                                                                                <el-form-item label="浏览次数" prop="browseSize">
                            <el-input v-model="form.browseSize" placeholder="请输入浏览次数" />
                        </el-form-item>
                                                                                <el-form-item label="发布时间" prop="releaseTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.releaseTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择发布时间">
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
                                                                                <el-form-item label="更新时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择更新时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="状态(1：正常；2：停用)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(1：正常；2：停用)" />
                        </el-form-item>
                                                                                <el-form-item label="删除状态 0 未删除  1 已经删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入删除状态 0 未删除  1 已经删除" />
                        </el-form-item>
                                                                                <el-form-item label="评论数" prop="commentSize">
                            <el-input v-model="form.commentSize" placeholder="请输入评论数" />
                        </el-form-item>
                                                                                <el-form-item label="是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏" prop="isGive">
                            <el-input v-model="form.isGive" placeholder="请输入是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏" />
                        </el-form-item>
                                                                                <el-form-item label="是否屏蔽  1 " prop="isShield">
                            <el-input v-model="form.isShield" placeholder="请输入是否屏蔽  1 " />
                        </el-form-item>
                                                                                <el-form-item label="首次发帖标识 1 首次 2 不是" prop="isInitialIssue">
                            <el-input v-model="form.isInitialIssue" placeholder="请输入首次发帖标识 1 首次 2 不是" />
                        </el-form-item>
                                                                                <el-form-item label="和谐前内容（原内容）" prop="rawTextContent">
                            <el-input v-model="form.rawTextContent" placeholder="请输入和谐前内容（原内容）" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="isTop">
                            <el-input v-model="form.isTop" placeholder="请输入" />
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
    import { listInvitation,listPageInvitation, getInvitation, delInvitation, addInvitation, updateInvitation, exportInvitation } from "@/api/bbs/invitation";

    export default {
        name: "Invitation",
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
                // 论坛帖子表表格数据
                    invitationList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                id: undefined,
                                                textContent: undefined,
                                                imgUrl: undefined,
                                                videoUrl: undefined,
                                                userId: undefined,
                                                userOperateStatus: undefined,
                                                address: undefined,
                                                praiseSize: undefined,
                                                collectionSize: undefined,
                                                browseSize: undefined,
                                                releaseTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                commentSize: undefined,
                                                isGive: undefined,
                                                isShield: undefined,
                                                isInitialIssue: undefined,
                                                rawTextContent: undefined,
                                                isTop: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                textContent: [
                            { required: true, message: "文本内容不能为空", trigger: "blur" }
                        ],
                                                imgUrl: [
                            { required: true, message: "图片路径不能为空", trigger: "blur" }
                        ],
                                                videoUrl: [
                            { required: true, message: "视频内容不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "用户ID不能为空", trigger: "blur" }
                        ],
                                                userOperateStatus: [
                            { required: true, message: "用户操作状态(1-已发布，2-已删除，3-内容违规)不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "当前位置不能为空", trigger: "blur" }
                        ],
                                                praiseSize: [
                            { required: true, message: "点赞次数不能为空", trigger: "blur" }
                        ],
                                                collectionSize: [
                            { required: true, message: "收藏次数不能为空", trigger: "blur" }
                        ],
                                                browseSize: [
                            { required: true, message: "浏览次数不能为空", trigger: "blur" }
                        ],
                                                releaseTime: [
                            { required: true, message: "发布时间不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1：正常；2：停用)不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "删除状态 0 未删除  1 已经删除不能为空", trigger: "blur" }
                        ],
                                                commentSize: [
                            { required: true, message: "评论数不能为空", trigger: "blur" }
                        ],
                                                isGive: [
                            { required: true, message: "是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏不能为空", trigger: "blur" }
                        ],
                                                isShield: [
                            { required: true, message: "是否屏蔽  1 不能为空", trigger: "blur" }
                        ],
                                                isInitialIssue: [
                            { required: true, message: "首次发帖标识 1 首次 2 不是不能为空", trigger: "blur" }
                        ],
                                                rawTextContent: [
                            { required: true, message: "和谐前内容（原内容）不能为空", trigger: "blur" }
                        ],
                                                isTop: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询论坛帖子表列表 */
            getListPage() {
                this.loading = true;
                listPageInvitation(this.queryParams).then(response => {
                    this.invitationList = response.data.records;
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
                                                textContent: undefined,
                                                imgUrl: undefined,
                                                videoUrl: undefined,
                                                userId: undefined,
                                                userOperateStatus: undefined,
                                                address: undefined,
                                                praiseSize: undefined,
                                                collectionSize: undefined,
                                                browseSize: undefined,
                                                releaseTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                status: undefined,
                                                isDelete: undefined,
                                                commentSize: undefined,
                                                isGive: undefined,
                                                isShield: undefined,
                                                isInitialIssue: undefined,
                                                rawTextContent: undefined,
                                                isTop: undefined,
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
                this.title = "添加论坛帖子表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getInvitation(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改论坛帖子表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateInvitation(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addInvitation(this.form).then(response => {
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
                    return delInvitation(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有论坛帖子表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportInvitation(queryParams);
                }).then(data => {
                    this.download(data, "论坛帖子表");
                }).catch(function() {});
            }
        }
    };
</script>
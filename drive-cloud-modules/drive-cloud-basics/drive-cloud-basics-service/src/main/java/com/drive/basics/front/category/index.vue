<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            <el-form-item label="分类名称" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入分类名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="分类的层级 1 一级 2 二级 3 三级" prop="grade">
                        <el-input
                                v-model="queryParams.grade"
                                placeholder="请输入分类的层级 1 一级 2 二级 3 三级"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="删除标记  0未删除 1删除 默认0" prop="delFlag">
                        <el-input
                                v-model="queryParams.delFlag"
                                placeholder="请输入删除标记  0未删除 1删除 默认0"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建者名称" prop="createName">
                        <el-input
                                v-model="queryParams.createName"
                                placeholder="请输入创建者名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="修改者名称" prop="modifyName">
                        <el-input
                                v-model="queryParams.modifyName"
                                placeholder="请输入修改者名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="删除者名称" prop="delName">
                        <el-input
                                v-model="queryParams.delName"
                                placeholder="请输入删除者名称"
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
                                                                <el-form-item label="修改时间" prop="modifyTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.modifyTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择修改时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="删除时间" prop="delTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.delTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择删除时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="" prop="dictValue">
                        <el-input
                                v-model="queryParams.dictValue"
                                placeholder="请输入"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="" prop="dictType">
                        <el-input
                                v-model="queryParams.dictType"
                                placeholder="请输入"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="创建者" prop="createBy">
                        <el-input
                                v-model="queryParams.createBy"
                                placeholder="请输入创建者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="修改者" prop="updateBy">
                        <el-input
                                v-model="queryParams.updateBy"
                                placeholder="请输入修改者"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="修改时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择修改时间">
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
                    v-hasPermi="['basics:category:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['basics:category:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['basics:category:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['basics:category:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="主键id" align="center" prop="id" />
                                                                <el-table-column label="分类名称" align="center" prop="name" />
                                                                <el-table-column label="父级分类id   没有父级 则为0" align="center" prop="parentId" />
                                                                <el-table-column label="类型id，只有分类是三级分类的时候才有 一级和二级分类没有" align="center" prop="typeId" />
                                                                <el-table-column label="分类的层级 1 一级 2 二级 3 三级" align="center" prop="grade" />
                                                                <el-table-column label="分类扣率 三级分类的时候才有，主要是和店铺对账使用" align="center" prop="rate" />
                                                                <el-table-column label="排序  数值越低  排序越前" align="center" prop="sort" />
                                                                <el-table-column label="删除标记  0未删除 1删除 默认0" align="center" prop="delFlag" />
                                                                <el-table-column label="创建者名称" align="center" prop="createName" />
                                                                <el-table-column label="修改者名称" align="center" prop="modifyName" />
                                                                <el-table-column label="删除者名称" align="center" prop="delName" />
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="修改时间" align="center" prop="modifyTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.modifyTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="删除时间" align="center" prop="delTime" width="180">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.delTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="" align="center" prop="dictValue" />
                                                                <el-table-column label="" align="center" prop="dictType" />
                                                                <el-table-column label="创建者" align="center" prop="createBy" />
                                                                <el-table-column label="修改者" align="center" prop="updateBy" />
                                                                <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
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
                        v-hasPermi="['basics:category:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['basics:category:delete']"
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

        <!-- 添加或修改产品分类表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键id" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键id" />
                        </el-form-item>
                                                                                <el-form-item label="分类名称" prop="name">
                            <el-input v-model="form.name" placeholder="请输入分类名称" />
                        </el-form-item>
                                                                                <el-form-item label="父级分类id   没有父级 则为0" prop="parentId">
                            <el-input v-model="form.parentId" placeholder="请输入父级分类id   没有父级 则为0" />
                        </el-form-item>
                                                                                <el-form-item label="类型id，只有分类是三级分类的时候才有 一级和二级分类没有" prop="typeId">
                            <el-input v-model="form.typeId" placeholder="请输入类型id，只有分类是三级分类的时候才有 一级和二级分类没有" />
                        </el-form-item>
                                                                                <el-form-item label="分类的层级 1 一级 2 二级 3 三级" prop="grade">
                            <el-input v-model="form.grade" placeholder="请输入分类的层级 1 一级 2 二级 3 三级" />
                        </el-form-item>
                                                                                <el-form-item label="分类扣率 三级分类的时候才有，主要是和店铺对账使用" prop="rate">
                            <el-input v-model="form.rate" placeholder="请输入分类扣率 三级分类的时候才有，主要是和店铺对账使用" />
                        </el-form-item>
                                                                                <el-form-item label="排序  数值越低  排序越前" prop="sort">
                            <el-input v-model="form.sort" placeholder="请输入排序  数值越低  排序越前" />
                        </el-form-item>
                                                                                <el-form-item label="删除标记  0未删除 1删除 默认0" prop="delFlag">
                            <el-input v-model="form.delFlag" placeholder="请输入删除标记  0未删除 1删除 默认0" />
                        </el-form-item>
                                                                                <el-form-item label="创建者名称" prop="createName">
                            <el-input v-model="form.createName" placeholder="请输入创建者名称" />
                        </el-form-item>
                                                                                <el-form-item label="修改者名称" prop="modifyName">
                            <el-input v-model="form.modifyName" placeholder="请输入修改者名称" />
                        </el-form-item>
                                                                                <el-form-item label="删除者名称" prop="delName">
                            <el-input v-model="form.delName" placeholder="请输入删除者名称" />
                        </el-form-item>
                                                                                <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="修改时间" prop="modifyTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.modifyTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择修改时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="删除时间" prop="delTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.delTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择删除时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="" prop="dictValue">
                            <el-input v-model="form.dictValue" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="" prop="dictType">
                            <el-input v-model="form.dictType" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="创建者" prop="createBy">
                            <el-input v-model="form.createBy" placeholder="请输入创建者" />
                        </el-form-item>
                                                                                <el-form-item label="修改者" prop="updateBy">
                            <el-input v-model="form.updateBy" placeholder="请输入修改者" />
                        </el-form-item>
                                                                                <el-form-item label="修改时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择修改时间">
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
    import { listCategory, getCategory, delCategory, addCategory, updateCategory, exportCategory } from "@/api/basics/category";

    export default {
        name: "Category",
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
                // 产品分类表表格数据
                    categoryList: [],
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
                                                parentId: undefined,
                                                typeId: undefined,
                                                grade: undefined,
                                                rate: undefined,
                                                sort: undefined,
                                                delFlag: undefined,
                                                createName: undefined,
                                                modifyName: undefined,
                                                delName: undefined,
                                                createTime: undefined,
                                                modifyTime: undefined,
                                                delTime: undefined,
                                                dictValue: undefined,
                                                dictType: undefined,
                                                createBy: undefined,
                                                updateBy: undefined,
                                                updateTime: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键id不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "分类名称不能为空", trigger: "blur" }
                        ],
                                                parentId: [
                            { required: true, message: "父级分类id   没有父级 则为0不能为空", trigger: "blur" }
                        ],
                                                typeId: [
                            { required: true, message: "类型id，只有分类是三级分类的时候才有 一级和二级分类没有不能为空", trigger: "blur" }
                        ],
                                                grade: [
                            { required: true, message: "分类的层级 1 一级 2 二级 3 三级不能为空", trigger: "blur" }
                        ],
                                                rate: [
                            { required: true, message: "分类扣率 三级分类的时候才有，主要是和店铺对账使用不能为空", trigger: "blur" }
                        ],
                                                sort: [
                            { required: true, message: "排序  数值越低  排序越前不能为空", trigger: "blur" }
                        ],
                                                delFlag: [
                            { required: true, message: "删除标记  0未删除 1删除 默认0不能为空", trigger: "blur" }
                        ],
                                                createName: [
                            { required: true, message: "创建者名称不能为空", trigger: "blur" }
                        ],
                                                modifyName: [
                            { required: true, message: "修改者名称不能为空", trigger: "blur" }
                        ],
                                                delName: [
                            { required: true, message: "删除者名称不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                modifyTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                delTime: [
                            { required: true, message: "删除时间不能为空", trigger: "blur" }
                        ],
                                                dictValue: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                dictType: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                createBy: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                updateBy: [
                            { required: true, message: "修改者不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询产品分类表列表 */
            getList() {
                this.loading = true;
                listCategory(this.queryParams).then(response => {
                    this.categoryList = response.data.records;
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
                                                parentId: undefined,
                                                typeId: undefined,
                                                grade: undefined,
                                                rate: undefined,
                                                sort: undefined,
                                                delFlag: undefined,
                                                createName: undefined,
                                                modifyName: undefined,
                                                delName: undefined,
                                                createTime: undefined,
                                                modifyTime: undefined,
                                                delTime: undefined,
                                                dictValue: undefined,
                                                dictType: undefined,
                                                createBy: undefined,
                                                updateBy: undefined,
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
                this.title = "添加产品分类表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getCategory(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改产品分类表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateCategory(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addCategory(this.form).then(response => {
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
                    return delCategory(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有产品分类表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportCategory(queryParams);
                }).then(data => {
                    this.download(data, "产品分类表");
                }).catch(function() {});
            }
        }
    };
</script>
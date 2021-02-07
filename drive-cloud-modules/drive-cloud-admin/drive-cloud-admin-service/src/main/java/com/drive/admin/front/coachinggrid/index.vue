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
                                                                <el-form-item label="场地名称" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入场地名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="省" prop="provinceId">
                        <el-input
                                v-model="queryParams.provinceId"
                                placeholder="请输入省"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="市" prop="cityId">
                        <el-input
                                v-model="queryParams.cityId"
                                placeholder="请输入市"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="区" prop="areaId">
                        <el-input
                                v-model="queryParams.areaId"
                                placeholder="请输入区"
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
                                                                <el-form-item label="场地详情（图文）" prop="remarks">
                        <el-input
                                v-model="queryParams.remarks"
                                placeholder="请输入场地详情（图文）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="图片介绍" prop="imageUrlList">
                        <el-input
                                v-model="queryParams.imageUrlList"
                                placeholder="请输入图片介绍"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="科目类型" prop="subjectType">
                        <el-input
                                v-model="queryParams.subjectType"
                                placeholder="请输入科目类型"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="场地类型（1-训练场地；2-考试场地）" prop="type">
                        <el-input
                                v-model="queryParams.type"
                                placeholder="请输入场地类型（1-训练场地；2-考试场地）"
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
                                                                                            <el-form-item label="驾校id" prop="driveSchoolId">
                        <el-input
                                v-model="queryParams.driveSchoolId"
                                placeholder="请输入驾校id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾校名称" prop="driveSchooName">
                        <el-input
                                v-model="queryParams.driveSchooName"
                                placeholder="请输入驾校名称"
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
                                                                <el-form-item label="更新着" prop="updateUser">
                        <el-input
                                v-model="queryParams.updateUser"
                                placeholder="请输入更新着"
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
                                                                                            <el-form-item label="联系电话" prop="phone">
                        <el-input
                                v-model="queryParams.phone"
                                placeholder="请输入联系电话"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="状态（1-正常，2-停用）" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态（1-正常，2-停用）"
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
                    v-hasPermi="['admin:coachingGrid:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:coachingGrid:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:coachingGrid:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:coachingGrid:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="coachingGridList" @selection-change="handleSelectionChange"
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
                                                                <el-table-column label="场地名称" align="center" prop="name" sortable="custom"/>
                                                                <el-table-column label="省" align="center" prop="provinceId" sortable="custom"/>
                                                                <el-table-column label="市" align="center" prop="cityId" sortable="custom"/>
                                                                <el-table-column label="区" align="center" prop="areaId" sortable="custom"/>
                                                                <el-table-column label="详细地址" align="center" prop="address" sortable="custom"/>
                                                                <el-table-column label="场地详情（图文）" align="center" prop="remarks" sortable="custom"/>
                                                                <el-table-column label="图片介绍" align="center" prop="imageUrlList" sortable="custom"/>
                                                                <el-table-column label="科目类型" align="center" prop="subjectType" sortable="custom"/>
                                                                <el-table-column label="场地类型（1-训练场地；2-考试场地）" align="center" prop="type" sortable="custom"/>
                                                                <el-table-column label="经度" align="center" prop="longitude" sortable="custom"/>
                                                                <el-table-column label="纬度" align="center" prop="latitude" sortable="custom"/>
                                                                <el-table-column label="场地费用/H" align="center" prop="price" sortable="custom"/>
                                                                <el-table-column label="驾校id" align="center" prop="driveSchoolId" sortable="custom"/>
                                                                <el-table-column label="驾校名称" align="center" prop="driveSchooName" sortable="custom"/>
                                                                <el-table-column label="创建者" align="center" prop="createUser" sortable="custom"/>
                                                                <el-table-column label="更新着" align="center" prop="updateUser" sortable="custom"/>
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
                                                                <el-table-column label="考试车价格/H" align="center" prop="gridTestCarPrice" sortable="custom"/>
                                                                <el-table-column label="联系电话" align="center" prop="phone" sortable="custom"/>
                                                                <el-table-column label="状态（1-正常，2-停用）" align="center" prop="status" sortable="custom"/>
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" sortable="custom"/>
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:coachingGrid:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:coachingGrid:delete']"
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

        <!-- 添加或修改平台训练场地表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="" prop="id">
                            <el-input v-model="form.id" placeholder="请输入" />
                        </el-form-item>
                                                                                <el-form-item label="场地名称" prop="name">
                            <el-input v-model="form.name" placeholder="请输入场地名称" />
                        </el-form-item>
                                                                                <el-form-item label="省" prop="provinceId">
                            <el-input v-model="form.provinceId" placeholder="请输入省" />
                        </el-form-item>
                                                                                <el-form-item label="市" prop="cityId">
                            <el-input v-model="form.cityId" placeholder="请输入市" />
                        </el-form-item>
                                                                                <el-form-item label="区" prop="areaId">
                            <el-input v-model="form.areaId" placeholder="请输入区" />
                        </el-form-item>
                                                                                <el-form-item label="详细地址" prop="address">
                            <el-input v-model="form.address" placeholder="请输入详细地址" />
                        </el-form-item>
                                                                                <el-form-item label="场地详情（图文）" prop="remarks">
                            <el-input v-model="form.remarks" placeholder="请输入场地详情（图文）" />
                        </el-form-item>
                                                                                <el-form-item label="图片介绍" prop="imageUrlList">
                            <el-input v-model="form.imageUrlList" placeholder="请输入图片介绍" />
                        </el-form-item>
                                                                                <el-form-item label="科目类型" prop="subjectType">
                            <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
                        </el-form-item>
                                                                                <el-form-item label="场地类型（1-训练场地；2-考试场地）" prop="type">
                            <el-input v-model="form.type" placeholder="请输入场地类型（1-训练场地；2-考试场地）" />
                        </el-form-item>
                                                                                <el-form-item label="经度" prop="longitude">
                            <el-input v-model="form.longitude" placeholder="请输入经度" />
                        </el-form-item>
                                                                                <el-form-item label="纬度" prop="latitude">
                            <el-input v-model="form.latitude" placeholder="请输入纬度" />
                        </el-form-item>
                                                                                <el-form-item label="场地费用/H" prop="price">
                            <el-input v-model="form.price" placeholder="请输入场地费用/H" />
                        </el-form-item>
                                                                                <el-form-item label="驾校id" prop="driveSchoolId">
                            <el-input v-model="form.driveSchoolId" placeholder="请输入驾校id" />
                        </el-form-item>
                                                                                <el-form-item label="驾校名称" prop="driveSchooName">
                            <el-input v-model="form.driveSchooName" placeholder="请输入驾校名称" />
                        </el-form-item>
                                                                                <el-form-item label="创建者" prop="createUser">
                            <el-input v-model="form.createUser" placeholder="请输入创建者" />
                        </el-form-item>
                                                                                <el-form-item label="更新着" prop="updateUser">
                            <el-input v-model="form.updateUser" placeholder="请输入更新着" />
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
                                                                                <el-form-item label="考试车价格/H" prop="gridTestCarPrice">
                            <el-input v-model="form.gridTestCarPrice" placeholder="请输入考试车价格/H" />
                        </el-form-item>
                                                                                <el-form-item label="联系电话" prop="phone">
                            <el-input v-model="form.phone" placeholder="请输入联系电话" />
                        </el-form-item>
                                                                                <el-form-item label="状态（1-正常，2-停用）" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态（1-正常，2-停用）" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
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
    import { listCoachingGrid,listPageCoachingGrid, getCoachingGrid, delCoachingGrid, addCoachingGrid, updateCoachingGrid, exportCoachingGrid } from "@/api/admin/coachingGrid";

    export default {
        name: "CoachingGrid",
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
                // 平台训练场地表表格数据
                    coachingGridList: [],
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
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                address: undefined,
                                                remarks: undefined,
                                                imageUrlList: undefined,
                                                subjectType: undefined,
                                                type: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                price: undefined,
                                                driveSchoolId: undefined,
                                                driveSchooName: undefined,
                                                createUser: undefined,
                                                updateUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                gridTestCarPrice: undefined,
                                                phone: undefined,
                                                status: undefined,
                                                operatorId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "场地名称不能为空", trigger: "blur" }
                        ],
                                                provinceId: [
                            { required: true, message: "省不能为空", trigger: "blur" }
                        ],
                                                cityId: [
                            { required: true, message: "市不能为空", trigger: "blur" }
                        ],
                                                areaId: [
                            { required: true, message: "区不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "详细地址不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "场地详情（图文）不能为空", trigger: "blur" }
                        ],
                                                imageUrlList: [
                            { required: true, message: "图片介绍不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                type: [
                            { required: true, message: "场地类型（1-训练场地；2-考试场地）不能为空", trigger: "blur" }
                        ],
                                                longitude: [
                            { required: true, message: "经度不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "纬度不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "场地费用/H不能为空", trigger: "blur" }
                        ],
                                                driveSchoolId: [
                            { required: true, message: "驾校id不能为空", trigger: "blur" }
                        ],
                                                driveSchooName: [
                            { required: true, message: "驾校名称不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "更新着不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                gridTestCarPrice: [
                            { required: true, message: "考试车价格/H不能为空", trigger: "blur" }
                        ],
                                                phone: [
                            { required: true, message: "联系电话不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态（1-正常，2-停用）不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询平台训练场地表列表 */
            getListPage() {
                this.loading = true;
                listPageCoachingGrid(this.queryParams).then(response => {
                    this.coachingGridList = response.data.records;
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
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                address: undefined,
                                                remarks: undefined,
                                                imageUrlList: undefined,
                                                subjectType: undefined,
                                                type: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                price: undefined,
                                                driveSchoolId: undefined,
                                                driveSchooName: undefined,
                                                createUser: undefined,
                                                updateUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                gridTestCarPrice: undefined,
                                                phone: undefined,
                                                status: undefined,
                                                operatorId: undefined,
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
                this.title = "添加平台训练场地表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getCoachingGrid(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改平台训练场地表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateCoachingGrid(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addCoachingGrid(this.form).then(response => {
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
                    return delCoachingGrid(ids);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有平台训练场地表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportCoachingGrid(queryParams);
                }).then(data => {
                    this.download(data, "平台训练场地表");
                }).catch(function() {});
            }
        }
    };
</script>
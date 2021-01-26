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
                                                                <el-form-item label="名称" prop="name">
                        <el-input
                                v-model="queryParams.name"
                                placeholder="请输入名称"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="驾照类型" prop="driveType">
                        <el-input
                                v-model="queryParams.driveType"
                                placeholder="请输入驾照类型"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）" prop="classType">
                        <el-input
                                v-model="queryParams.classType"
                                placeholder="请输入班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                                                                                                                                                                    <el-form-item label="详情介绍" prop="details">
                        <el-input
                                v-model="queryParams.details"
                                placeholder="请输入详情介绍"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
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
                                                                                                                                                                                <el-form-item label="价格介绍" prop="priceIntroduce">
                        <el-input
                                v-model="queryParams.priceIntroduce"
                                placeholder="请输入价格介绍"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学车流程" prop="drivingFlow">
                        <el-input
                                v-model="queryParams.drivingFlow"
                                placeholder="请输入学车流程"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="是否可升级" prop="isUpgrade">
                        <el-input
                                v-model="queryParams.isUpgrade"
                                placeholder="请输入是否可升级"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="状态(1-正常，2-停用)" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入状态(1-正常，2-停用)"
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
                    v-hasPermi="['admin:oneFeeSystemPrice:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:oneFeeSystemPrice:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:oneFeeSystemPrice:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:oneFeeSystemPrice:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="oneFeeSystemPriceList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="主键" align="center" prop="id" />
                                                                <el-table-column label="名称" align="center" prop="name" />
                                                                <el-table-column label="驾照类型" align="center" prop="driveType" />
                                                                <el-table-column label="班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）" align="center" prop="classType" />
                                                                <el-table-column label="价格" align="center" prop="price" />
                                                                <el-table-column label="科目二教练提成金额" align="center" prop="coachSubjectType2" />
                                                                <el-table-column label="科目三教练提成金额" align="center" prop="coachSubjectType3" />
                                                                <el-table-column label="科目二驾校提成金额" align="center" prop="schoolSubjectType2" />
                                                                <el-table-column label="科目三驾校提成金额" align="center" prop="schoolSubjectType3" />
                                                                <el-table-column label="运营商提成金额" align="center" prop="operatorChangeMoney" />
                                                                <el-table-column label="平台提成金额（所有上级运营商提成金）" align="center" prop="serviceChangeMoney" />
                                                                <el-table-column label="详情介绍" align="center" prop="details" />
                                                                <el-table-column label="奖金（学员拿证后，教练获得的奖金）" align="center" prop="bonus" />
                                                                <el-table-column label="挂科扣款金额" align="center" prop="testNotPassWithholdMoney" />
                                                                <el-table-column label="是否删除" align="center" prop="isDelete" />
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
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                                                <el-table-column label="科目一免费考试次数" align="center" prop="subject1CostFreeNumber" />
                                                                <el-table-column label="科目二免费考试次数" align="center" prop="subject2CostFreeNumber" />
                                                                <el-table-column label="科目三免费考试次数" align="center" prop="subject3CostFreeNumber" />
                                                                <el-table-column label="科目四免费考试次数" align="center" prop="subject4CostFreeNumber" />
                                                                <el-table-column label="价格介绍" align="center" prop="priceIntroduce" />
                                                                <el-table-column label="学车流程" align="center" prop="drivingFlow" />
                                                                <el-table-column label="班型等级(用于报名升班控制，只能往大升，不能往小升)" align="center" prop="classGrade" />
                                                                <el-table-column label="原价" align="center" prop="originalPrice" />
                                                                <el-table-column label="是否可升级" align="center" prop="isUpgrade" />
                                                                <el-table-column label="状态(1-正常，2-停用)" align="center" prop="status" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:oneFeeSystemPrice:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:oneFeeSystemPrice:delete']"
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

        <!-- 添加或修改学车一费制定价表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="主键" prop="id">
                            <el-input v-model="form.id" placeholder="请输入主键" />
                        </el-form-item>
                                                                                <el-form-item label="名称" prop="name">
                            <el-input v-model="form.name" placeholder="请输入名称" />
                        </el-form-item>
                                                                                <el-form-item label="驾照类型" prop="driveType">
                            <el-input v-model="form.driveType" placeholder="请输入驾照类型" />
                        </el-form-item>
                                                                                <el-form-item label="班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）" prop="classType">
                            <el-input v-model="form.classType" placeholder="请输入班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）" />
                        </el-form-item>
                                                                                <el-form-item label="价格" prop="price">
                            <el-input v-model="form.price" placeholder="请输入价格" />
                        </el-form-item>
                                                                                <el-form-item label="科目二教练提成金额" prop="coachSubjectType2">
                            <el-input v-model="form.coachSubjectType2" placeholder="请输入科目二教练提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目三教练提成金额" prop="coachSubjectType3">
                            <el-input v-model="form.coachSubjectType3" placeholder="请输入科目三教练提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目二驾校提成金额" prop="schoolSubjectType2">
                            <el-input v-model="form.schoolSubjectType2" placeholder="请输入科目二驾校提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="科目三驾校提成金额" prop="schoolSubjectType3">
                            <el-input v-model="form.schoolSubjectType3" placeholder="请输入科目三驾校提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="运营商提成金额" prop="operatorChangeMoney">
                            <el-input v-model="form.operatorChangeMoney" placeholder="请输入运营商提成金额" />
                        </el-form-item>
                                                                                <el-form-item label="平台提成金额（所有上级运营商提成金）" prop="serviceChangeMoney">
                            <el-input v-model="form.serviceChangeMoney" placeholder="请输入平台提成金额（所有上级运营商提成金）" />
                        </el-form-item>
                                                                                <el-form-item label="详情介绍" prop="details">
                            <el-input v-model="form.details" placeholder="请输入详情介绍" />
                        </el-form-item>
                                                                                <el-form-item label="奖金（学员拿证后，教练获得的奖金）" prop="bonus">
                            <el-input v-model="form.bonus" placeholder="请输入奖金（学员拿证后，教练获得的奖金）" />
                        </el-form-item>
                                                                                <el-form-item label="挂科扣款金额" prop="testNotPassWithholdMoney">
                            <el-input v-model="form.testNotPassWithholdMoney" placeholder="请输入挂科扣款金额" />
                        </el-form-item>
                                                                                <el-form-item label="是否删除" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
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
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="科目一免费考试次数" prop="subject1CostFreeNumber">
                            <el-input v-model="form.subject1CostFreeNumber" placeholder="请输入科目一免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="科目二免费考试次数" prop="subject2CostFreeNumber">
                            <el-input v-model="form.subject2CostFreeNumber" placeholder="请输入科目二免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="科目三免费考试次数" prop="subject3CostFreeNumber">
                            <el-input v-model="form.subject3CostFreeNumber" placeholder="请输入科目三免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="科目四免费考试次数" prop="subject4CostFreeNumber">
                            <el-input v-model="form.subject4CostFreeNumber" placeholder="请输入科目四免费考试次数" />
                        </el-form-item>
                                                                                <el-form-item label="价格介绍" prop="priceIntroduce">
                            <el-input v-model="form.priceIntroduce" placeholder="请输入价格介绍" />
                        </el-form-item>
                                                                                <el-form-item label="学车流程" prop="drivingFlow">
                            <el-input v-model="form.drivingFlow" placeholder="请输入学车流程" />
                        </el-form-item>
                                                                                <el-form-item label="班型等级(用于报名升班控制，只能往大升，不能往小升)" prop="classGrade">
                            <el-input v-model="form.classGrade" placeholder="请输入班型等级(用于报名升班控制，只能往大升，不能往小升)" />
                        </el-form-item>
                                                                                <el-form-item label="原价" prop="originalPrice">
                            <el-input v-model="form.originalPrice" placeholder="请输入原价" />
                        </el-form-item>
                                                                                <el-form-item label="是否可升级" prop="isUpgrade">
                            <el-input v-model="form.isUpgrade" placeholder="请输入是否可升级" />
                        </el-form-item>
                                                                                <el-form-item label="状态(1-正常，2-停用)" prop="status">
                            <el-input v-model="form.status" placeholder="请输入状态(1-正常，2-停用)" />
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
    import { listOneFeeSystemPrice, getOneFeeSystemPrice, delOneFeeSystemPrice, addOneFeeSystemPrice, updateOneFeeSystemPrice, exportOneFeeSystemPrice } from "@/api/admin/oneFeeSystemPrice";

    export default {
        name: "OneFeeSystemPrice",
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
                // 学车一费制定价表表格数据
                    oneFeeSystemPriceList: [],
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
                                                driveType: undefined,
                                                classType: undefined,
                                                price: undefined,
                                                coachSubjectType2: undefined,
                                                coachSubjectType3: undefined,
                                                schoolSubjectType2: undefined,
                                                schoolSubjectType3: undefined,
                                                operatorChangeMoney: undefined,
                                                serviceChangeMoney: undefined,
                                                details: undefined,
                                                bonus: undefined,
                                                testNotPassWithholdMoney: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                operatorId: undefined,
                                                subject1CostFreeNumber: undefined,
                                                subject2CostFreeNumber: undefined,
                                                subject3CostFreeNumber: undefined,
                                                subject4CostFreeNumber: undefined,
                                                priceIntroduce: undefined,
                                                drivingFlow: undefined,
                                                classGrade: undefined,
                                                originalPrice: undefined,
                                                isUpgrade: undefined,
                                                status: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                name: [
                            { required: true, message: "名称不能为空", trigger: "blur" }
                        ],
                                                driveType: [
                            { required: true, message: "驾照类型不能为空", trigger: "blur" }
                        ],
                                                classType: [
                            { required: true, message: "班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "价格不能为空", trigger: "blur" }
                        ],
                                                coachSubjectType2: [
                            { required: true, message: "科目二教练提成金额不能为空", trigger: "blur" }
                        ],
                                                coachSubjectType3: [
                            { required: true, message: "科目三教练提成金额不能为空", trigger: "blur" }
                        ],
                                                schoolSubjectType2: [
                            { required: true, message: "科目二驾校提成金额不能为空", trigger: "blur" }
                        ],
                                                schoolSubjectType3: [
                            { required: true, message: "科目三驾校提成金额不能为空", trigger: "blur" }
                        ],
                                                operatorChangeMoney: [
                            { required: true, message: "运营商提成金额不能为空", trigger: "blur" }
                        ],
                                                serviceChangeMoney: [
                            { required: true, message: "平台提成金额（所有上级运营商提成金）不能为空", trigger: "blur" }
                        ],
                                                details: [
                            { required: true, message: "详情介绍不能为空", trigger: "blur" }
                        ],
                                                bonus: [
                            { required: true, message: "奖金（学员拿证后，教练获得的奖金）不能为空", trigger: "blur" }
                        ],
                                                testNotPassWithholdMoney: [
                            { required: true, message: "挂科扣款金额不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                subject1CostFreeNumber: [
                            { required: true, message: "科目一免费考试次数不能为空", trigger: "blur" }
                        ],
                                                subject2CostFreeNumber: [
                            { required: true, message: "科目二免费考试次数不能为空", trigger: "blur" }
                        ],
                                                subject3CostFreeNumber: [
                            { required: true, message: "科目三免费考试次数不能为空", trigger: "blur" }
                        ],
                                                subject4CostFreeNumber: [
                            { required: true, message: "科目四免费考试次数不能为空", trigger: "blur" }
                        ],
                                                priceIntroduce: [
                            { required: true, message: "价格介绍不能为空", trigger: "blur" }
                        ],
                                                drivingFlow: [
                            { required: true, message: "学车流程不能为空", trigger: "blur" }
                        ],
                                                classGrade: [
                            { required: true, message: "班型等级(用于报名升班控制，只能往大升，不能往小升)不能为空", trigger: "blur" }
                        ],
                                                originalPrice: [
                            { required: true, message: "原价不能为空", trigger: "blur" }
                        ],
                                                isUpgrade: [
                            { required: true, message: "是否可升级不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1-正常，2-停用)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询学车一费制定价表列表 */
            getList() {
                this.loading = true;
                listOneFeeSystemPrice(this.queryParams).then(response => {
                    this.oneFeeSystemPriceList = response.data.records;
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
                                                driveType: undefined,
                                                classType: undefined,
                                                price: undefined,
                                                coachSubjectType2: undefined,
                                                coachSubjectType3: undefined,
                                                schoolSubjectType2: undefined,
                                                schoolSubjectType3: undefined,
                                                operatorChangeMoney: undefined,
                                                serviceChangeMoney: undefined,
                                                details: undefined,
                                                bonus: undefined,
                                                testNotPassWithholdMoney: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                operatorId: undefined,
                                                subject1CostFreeNumber: undefined,
                                                subject2CostFreeNumber: undefined,
                                                subject3CostFreeNumber: undefined,
                                                subject4CostFreeNumber: undefined,
                                                priceIntroduce: undefined,
                                                drivingFlow: undefined,
                                                classGrade: undefined,
                                                originalPrice: undefined,
                                                isUpgrade: undefined,
                                                status: undefined,
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
                this.title = "添加学车一费制定价表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getOneFeeSystemPrice(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学车一费制定价表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateOneFeeSystemPrice(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                }
                            });
                        } else {
                            addOneFeeSystemPrice(this.form).then(response => {
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
                    return delOneFeeSystemPrice(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学车一费制定价表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportOneFeeSystemPrice(queryParams);
                }).then(data => {
                    this.download(data, "学车一费制定价表");
                }).catch(function() {});
            }
        }
    };
</script>
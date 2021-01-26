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
      <el-form-item label="驾校ID（如果是驾校用户必须）" prop="driveSchoolId">
        <el-input
          v-model="queryParams.driveSchoolId"
          placeholder="请输入驾校ID（如果是驾校用户必须）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-input
          v-model="queryParams.sex"
          placeholder="请输入性别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input
          v-model="queryParams.realName"
          placeholder="请输入真实姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用状态(1：正常；2：停用)" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入使用状态(1：正常；2：停用)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="头像路径" prop="headUrl">
        <el-input
          v-model="queryParams.headUrl"
          placeholder="请输入头像路径"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐人id" prop="recommendUserId">
        <el-input
          v-model="queryParams.recommendUserId"
          placeholder="请输入推荐人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="自身推荐码" prop="recommendCodeSelf">
        <el-input
          v-model="queryParams.recommendCodeSelf"
          placeholder="请输入自身推荐码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="token_key" prop="tokeKey">
        <el-input
          v-model="queryParams.tokeKey"
          placeholder="请输入token_key"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）" prop="serviceType">
        <el-input
          v-model="queryParams.serviceType"
          placeholder="请输入客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）"
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
          v-hasPermi="['admin:serviceInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:serviceInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:serviceInfo:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:serviceInfo:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="serviceInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="主键" align="center" prop="id" />
        <el-table-column label="省" align="center" prop="provinceId" />
        <el-table-column label="市" align="center" prop="cityId" />
        <el-table-column label="区" align="center" prop="areaId" />
        <el-table-column label="驾校ID（如果是驾校用户必须）" align="center" prop="driveSchoolId" />
        <el-table-column label="手机号" align="center" prop="phone" />
        <el-table-column label="密码" align="center" prop="password" />
        <el-table-column label="性别" align="center" prop="sex" />
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="身份证号" align="center" prop="idCard" />
        <el-table-column label="使用状态(1：正常；2：停用)" align="center" prop="status" />
        <el-table-column label="头像路径" align="center" prop="headUrl" />
        <el-table-column label="推荐人id" align="center" prop="recommendUserId" />
        <el-table-column label="自身推荐码" align="center" prop="recommendCodeSelf" />
        <el-table-column label="评价分数" align="center" prop="appraiseGrade" />
        <el-table-column label="token_key" align="center" prop="tokeKey" />
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
        <el-table-column label="客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）" align="center" prop="serviceType" />
        <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['admin:serviceInfo:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['admin:serviceInfo:delete']"
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

    <!-- 添加或修改客服人员信息表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="主键" prop="id">
            <el-input v-model="form.id" placeholder="请输入主键" />
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
        <el-form-item label="驾校ID（如果是驾校用户必须）" prop="driveSchoolId">
            <el-input v-model="form.driveSchoolId" placeholder="请输入驾校ID（如果是驾校用户必须）" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
            <el-input v-model="form.sex" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
            <el-input v-model="form.idCard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="使用状态(1：正常；2：停用)" prop="status">
            <el-input v-model="form.status" placeholder="请输入使用状态(1：正常；2：停用)" />
        </el-form-item>
        <el-form-item label="头像路径" prop="headUrl">
            <el-input v-model="form.headUrl" placeholder="请输入头像路径" />
        </el-form-item>
        <el-form-item label="推荐人id" prop="recommendUserId">
            <el-input v-model="form.recommendUserId" placeholder="请输入推荐人id" />
        </el-form-item>
        <el-form-item label="自身推荐码" prop="recommendCodeSelf">
            <el-input v-model="form.recommendCodeSelf" placeholder="请输入自身推荐码" />
        </el-form-item>
        <el-form-item label="评价分数" prop="appraiseGrade">
            <el-input v-model="form.appraiseGrade" placeholder="请输入评价分数" />
        </el-form-item>
        <el-form-item label="token_key" prop="tokeKey">
            <el-input v-model="form.tokeKey" placeholder="请输入token_key" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updateTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.updateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）" prop="serviceType">
            <el-input v-model="form.serviceType" placeholder="请输入客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）" />
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
import { listServiceInfo, getServiceInfo, delServiceInfo, addServiceInfo, updateServiceInfo, exportServiceInfo } from "@/api/admin/serviceInfo";

export default {
  name: "ServiceInfo",
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
      // 客服人员信息表表格数据
      serviceInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        provinceId: undefined,
        cityId: undefined,
        areaId: undefined,
        driveSchoolId: undefined,
        phone: undefined,
        password: undefined,
        sex: undefined,
        realName: undefined,
        idCard: undefined,
        status: undefined,
        headUrl: undefined,
        recommendUserId: undefined,
        recommendCodeSelf: undefined,
        appraiseGrade: undefined,
        tokeKey: undefined,
        createTime: undefined,
        updateTime: undefined,
        serviceType: undefined,
        operatorId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "主键不能为空", trigger: "blur" }
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
        driveSchoolId: [
          { required: true, message: "驾校ID（如果是驾校用户必须）不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        realName: [
          { required: true, message: "真实姓名不能为空", trigger: "blur" }
        ],
        idCard: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "使用状态(1：正常；2：停用)不能为空", trigger: "blur" }
        ],
        headUrl: [
          { required: true, message: "头像路径不能为空", trigger: "blur" }
        ],
        recommendUserId: [
          { required: true, message: "推荐人id不能为空", trigger: "blur" }
        ],
        recommendCodeSelf: [
          { required: true, message: "自身推荐码不能为空", trigger: "blur" }
        ],
        appraiseGrade: [
          { required: true, message: "评价分数不能为空", trigger: "blur" }
        ],
        tokeKey: [
          { required: true, message: "token_key不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        serviceType: [
          { required: true, message: "客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）不能为空", trigger: "blur" }
        ],
        operatorId: [
          { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客服人员信息表列表 */
    getList() {
      this.loading = true;
      listServiceInfo(this.queryParams).then(response => {
        this.serviceInfoList = response.data.records;
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
        provinceId: undefined,
        cityId: undefined,
        areaId: undefined,
        driveSchoolId: undefined,
        phone: undefined,
        password: undefined,
        sex: undefined,
        realName: undefined,
        idCard: undefined,
        status: undefined,
        headUrl: undefined,
        recommendUserId: undefined,
        recommendCodeSelf: undefined,
        appraiseGrade: undefined,
        tokeKey: undefined,
        createTime: undefined,
        updateTime: undefined,
        serviceType: undefined,
        operatorId: undefined,
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
      this.title = "添加客服人员信息表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getServiceInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客服人员信息表";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateServiceInfo(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addServiceInfo(this.form).then(response => {
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
          return delServiceInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有客服人员信息表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportServiceInfo(queryParams);
        }).then(data => {
          this.download(data, "客服人员信息表");
        }).catch(function() {});
    }
  }
};
</script>
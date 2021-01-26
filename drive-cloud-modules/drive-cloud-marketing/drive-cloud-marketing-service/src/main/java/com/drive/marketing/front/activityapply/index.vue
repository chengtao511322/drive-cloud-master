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
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
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
      <el-form-item label="活动名称" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动ID" prop="activityId">
        <el-input
          v-model="queryParams.activityId"
          placeholder="请输入活动ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="applyTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.applyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建者" prop="addUser">
        <el-input
          v-model="queryParams.addUser"
          placeholder="请输入创建者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择修改时间">
        </el-date-picker>
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
      <el-form-item label="" prop="operator">
        <el-input
          v-model="queryParams.operator"
          placeholder="请输入"
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
      <el-form-item label="" prop="promoteUserId">
        <el-input
          v-model="queryParams.promoteUserId"
          placeholder="请输入"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入"
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
          v-hasPermi="['marketing:activityApply:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketing:activityApply:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketing:activityApply:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['marketing:activityApply:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="activityApplyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="" align="center" prop="id" />
        <el-table-column label="用户名称" align="center" prop="userName" />
        <el-table-column label="手机号" align="center" prop="phone" />
        <el-table-column label="活动名称" align="center" prop="activityName" />
        <el-table-column label="" align="center" prop="userId" />
        <el-table-column label="活动ID" align="center" prop="activityId" />
        <el-table-column label="" align="center" prop="applyTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.applyTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="创建者" align="center" prop="addUser" />
        <el-table-column label="" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.updateTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="启用状态 0  未启用  1 已经启用" align="center" prop="status" />
        <el-table-column label="删除状态 0 未删除1删除" align="center" prop="isDelete" />
        <el-table-column label="运营位置 ，存放省市区编码，多个用逗号隔开" align="center" prop="operatingPosition" />
        <el-table-column label="" align="center" prop="operator" />
        <el-table-column label="租户ID" align="center" prop="tenantId" />
        <el-table-column label="" align="center" prop="promoteUserId" />
        <el-table-column label="" align="center" prop="projectId" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['marketing:activityApply:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['marketing:activityApply:delete']"
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

    <!-- 添加或修改活动参加记录表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="id">
            <el-input v-model="form.id" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="用户名称" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="活动名称" prop="activityName">
            <el-input v-model="form.activityName" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="" prop="userId">
            <el-input v-model="form.userId" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="活动ID" prop="activityId">
            <el-input v-model="form.activityId" placeholder="请输入活动ID" />
        </el-form-item>
        <el-form-item label="" prop="applyTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.applyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建者" prop="addUser">
            <el-input v-model="form.addUser" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="" prop="createTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="updateTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.updateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="启用状态 0  未启用  1 已经启用" prop="status">
            <el-input v-model="form.status" placeholder="请输入启用状态 0  未启用  1 已经启用" />
        </el-form-item>
        <el-form-item label="删除状态 0 未删除1删除" prop="isDelete">
            <el-input v-model="form.isDelete" placeholder="请输入删除状态 0 未删除1删除" />
        </el-form-item>
        <el-form-item label="运营位置 ，存放省市区编码，多个用逗号隔开" prop="operatingPosition">
            <el-input v-model="form.operatingPosition" placeholder="请输入运营位置 ，存放省市区编码，多个用逗号隔开" />
        </el-form-item>
        <el-form-item label="" prop="operator">
            <el-input v-model="form.operator" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="租户ID" prop="tenantId">
            <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
        </el-form-item>
        <el-form-item label="" prop="promoteUserId">
            <el-input v-model="form.promoteUserId" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="projectId">
            <el-input v-model="form.projectId" placeholder="请输入" />
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
import { listActivityApply, getActivityApply, delActivityApply, addActivityApply, updateActivityApply, exportActivityApply } from "@/api/marketing/activityApply";

export default {
  name: "ActivityApply",
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
      // 活动参加记录表表格数据
      activityApplyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        userName: undefined,
        phone: undefined,
        activityName: undefined,
        userId: undefined,
        activityId: undefined,
        applyTime: undefined,
        addUser: undefined,
        createTime: undefined,
        updateTime: undefined,
        status: undefined,
        isDelete: undefined,
        operatingPosition: undefined,
        operator: undefined,
        tenantId: undefined,
        promoteUserId: undefined,
        projectId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        activityName: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        activityId: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
        applyTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        addUser: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "启用状态 0  未启用  1 已经启用不能为空", trigger: "blur" }
        ],
        isDelete: [
          { required: true, message: "删除状态 0 未删除1删除不能为空", trigger: "blur" }
        ],
        operatingPosition: [
          { required: true, message: "运营位置 ，存放省市区编码，多个用逗号隔开不能为空", trigger: "blur" }
        ],
        operator: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        tenantId: [
          { required: true, message: "租户ID不能为空", trigger: "blur" }
        ],
        promoteUserId: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        projectId: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询活动参加记录表列表 */
    getList() {
      this.loading = true;
      listActivityApply(this.queryParams).then(response => {
        this.activityApplyList = response.data.records;
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
        userName: undefined,
        phone: undefined,
        activityName: undefined,
        userId: undefined,
        activityId: undefined,
        applyTime: undefined,
        addUser: undefined,
        createTime: undefined,
        updateTime: undefined,
        status: undefined,
        isDelete: undefined,
        operatingPosition: undefined,
        operator: undefined,
        tenantId: undefined,
        promoteUserId: undefined,
        projectId: undefined,
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
      this.ids = selection.map(item => item.${pkColumn.propertyName})
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动参加记录表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ${pkColumn.propertyName} = row.${pkColumn.propertyName} || this.ids
      getActivityApply(${pkColumn.propertyName}).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动参加记录表";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.${pkColumn.propertyName} != undefined) {
            updateActivityApply(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addActivityApply(this.form).then(response => {
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
      const ${pkColumn.propertyName}s = row.${pkColumn.propertyName} || this.ids;
      this.$confirm('是否确认删除?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delActivityApply(${pkColumn.propertyName}s);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有活动参加记录表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportActivityApply(queryParams);
        }).then(data => {
          this.download(data, "活动参加记录表");
        }).catch(function() {});
    }
  }
};
</script>
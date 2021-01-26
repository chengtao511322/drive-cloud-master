<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                      <el-form-item label="" prop="channelManagerId">
        <el-input
          v-model="queryParams.channelManagerId"
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
      <el-form-item label="冗余 活动名称" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入冗余 活动名称"
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
          v-hasPermi="['marketing:channelManagerActivity:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketing:channelManagerActivity:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketing:channelManagerActivity:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['marketing:channelManagerActivity:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="channelManagerActivityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="" align="center" prop="channelManagerId" />
        <el-table-column label="活动ID" align="center" prop="activityId" />
        <el-table-column label="冗余 活动名称" align="center" prop="activityName" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['marketing:channelManagerActivity:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['marketing:channelManagerActivity:delete']"
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

    <!-- 添加或修改渠道经理 可推广表配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="channelManagerId">
            <el-input v-model="form.channelManagerId" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="活动ID" prop="activityId">
            <el-input v-model="form.activityId" placeholder="请输入活动ID" />
        </el-form-item>
        <el-form-item label="冗余 活动名称" prop="activityName">
            <el-input v-model="form.activityName" placeholder="请输入冗余 活动名称" />
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
import { listChannelManagerActivity, getChannelManagerActivity, delChannelManagerActivity, addChannelManagerActivity, updateChannelManagerActivity, exportChannelManagerActivity } from "@/api/marketing/channelManagerActivity";

export default {
  name: "ChannelManagerActivity",
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
      // 渠道经理 可推广表配置表格数据
      channelManagerActivityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        channelManagerId: undefined,
        activityId: undefined,
        activityName: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        channelManagerId: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        activityId: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
        activityName: [
          { required: true, message: "冗余 活动名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询渠道经理 可推广表配置列表 */
    getList() {
      this.loading = true;
      listChannelManagerActivity(this.queryParams).then(response => {
        this.channelManagerActivityList = response.data.records;
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
        channelManagerId: undefined,
        activityId: undefined,
        activityName: undefined,
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
      this.ids = selection.map(item => item.channelManagerId)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加渠道经理 可推广表配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const channelManagerId = row.channelManagerId || this.ids
      getChannelManagerActivity(channelManagerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改渠道经理 可推广表配置";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.channelManagerId != undefined) {
            updateChannelManagerActivity(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addChannelManagerActivity(this.form).then(response => {
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
      const channelManagerIds = row.channelManagerId || this.ids;
      this.$confirm('是否确认删除?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delChannelManagerActivity(channelManagerIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有渠道经理 可推广表配置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportChannelManagerActivity(queryParams);
        }).then(data => {
          this.download(data, "渠道经理 可推广表配置");
        }).catch(function() {});
    }
  }
};
</script>
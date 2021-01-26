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
      <el-form-item label="栏目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入栏目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图标地址" prop="iconPath">
        <el-input
          v-model="queryParams.iconPath"
          placeholder="请输入图标地址"
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
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择修改时间">
        </el-date-picker>
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
      <el-form-item label="二级栏目" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入二级栏目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点击地址（执行函数）" prop="clickUrl">
        <el-input
          v-model="queryParams.clickUrl"
          placeholder="请输入点击地址（执行函数）"
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
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动说明" prop="activityExplain">
        <el-input
          v-model="queryParams.activityExplain"
          placeholder="请输入活动说明"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规则说明" prop="ruleExplain">
        <el-input
          v-model="queryParams.ruleExplain"
          placeholder="请输入规则说明"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目" prop="columnGroup">
        <el-input
          v-model="queryParams.columnGroup"
          placeholder="请输入栏目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="口令" prop="command">
        <el-input
          v-model="queryParams.command"
          placeholder="请输入口令"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="权限" prop="auth">
        <el-input
          v-model="queryParams.auth"
          placeholder="请输入权限"
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
          v-hasPermi="['basics:channel:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basics:channel:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basics:channel:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basics:channel:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="channelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="id" align="center" prop="id" />
        <el-table-column label="栏目名称" align="center" prop="name" />
        <el-table-column label="图标地址" align="center" prop="iconPath" />
        <el-table-column label="排序" align="center" prop="sort" />
        <el-table-column label="状态：0：未发表  1：已经发表  默认1" align="center" prop="status" />
        <el-table-column label="删除状态:0:未删除  1：已经删除  默认0" align="center" prop="isDelete" />
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
        <el-table-column label="添加者" align="center" prop="addUser" />
        <el-table-column label="二级栏目" align="center" prop="parentId" />
        <el-table-column label="点击地址（执行函数）" align="center" prop="clickUrl" />
        <el-table-column label="租户ID" align="center" prop="tenantId" />
        <el-table-column label="android显示状态：0不显示，1：显示" align="center" prop="androidShow" />
        <el-table-column label="ios显示状态：0不显示，1：显示" align="center" prop="iosShow" />
        <el-table-column label="描述" align="center" prop="description" />
        <el-table-column label="活动说明" align="center" prop="activityExplain" />
        <el-table-column label="规则说明" align="center" prop="ruleExplain" />
        <el-table-column label="栏目" align="center" prop="columnGroup" />
        <el-table-column label="口令" align="center" prop="command" />
        <el-table-column label="权限" align="center" prop="auth" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['basics:channel:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['basics:channel:delete']"
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

    <!-- 添加或修改栏目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="id" prop="id">
            <el-input v-model="form.id" placeholder="请输入id" />
        </el-form-item>
        <el-form-item label="栏目名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入栏目名称" />
        </el-form-item>
        <el-form-item label="图标地址" prop="iconPath">
            <el-input v-model="form.iconPath" placeholder="请输入图标地址" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
            <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="状态：0：未发表  1：已经发表  默认1" prop="status">
            <el-input v-model="form.status" placeholder="请输入状态：0：未发表  1：已经发表  默认1" />
        </el-form-item>
        <el-form-item label="删除状态:0:未删除  1：已经删除  默认0" prop="isDelete">
            <el-input v-model="form.isDelete" placeholder="请输入删除状态:0:未删除  1：已经删除  默认0" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
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
        <el-form-item label="添加者" prop="addUser">
            <el-input v-model="form.addUser" placeholder="请输入添加者" />
        </el-form-item>
        <el-form-item label="二级栏目" prop="parentId">
            <el-input v-model="form.parentId" placeholder="请输入二级栏目" />
        </el-form-item>
        <el-form-item label="点击地址（执行函数）" prop="clickUrl">
            <el-input v-model="form.clickUrl" placeholder="请输入点击地址（执行函数）" />
        </el-form-item>
        <el-form-item label="租户ID" prop="tenantId">
            <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
        </el-form-item>
        <el-form-item label="android显示状态：0不显示，1：显示" prop="androidShow">
            <el-input v-model="form.androidShow" placeholder="请输入android显示状态：0不显示，1：显示" />
        </el-form-item>
        <el-form-item label="ios显示状态：0不显示，1：显示" prop="iosShow">
            <el-input v-model="form.iosShow" placeholder="请输入ios显示状态：0不显示，1：显示" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
            <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="活动说明" prop="activityExplain">
            <el-input v-model="form.activityExplain" placeholder="请输入活动说明" />
        </el-form-item>
        <el-form-item label="规则说明" prop="ruleExplain">
            <el-input v-model="form.ruleExplain" placeholder="请输入规则说明" />
        </el-form-item>
        <el-form-item label="栏目" prop="columnGroup">
            <el-input v-model="form.columnGroup" placeholder="请输入栏目" />
        </el-form-item>
        <el-form-item label="口令" prop="command">
            <el-input v-model="form.command" placeholder="请输入口令" />
        </el-form-item>
        <el-form-item label="权限" prop="auth">
            <el-input v-model="form.auth" placeholder="请输入权限" />
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
import { listChannel, getChannel, delChannel, addChannel, updateChannel, exportChannel } from "@/api/basics/channel";

export default {
  name: "Channel",
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
      // 栏目表格数据
      channelList: [],
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
        iconPath: undefined,
        sort: undefined,
        status: undefined,
        isDelete: undefined,
        createTime: undefined,
        updateTime: undefined,
        addUser: undefined,
        parentId: undefined,
        clickUrl: undefined,
        tenantId: undefined,
        androidShow: undefined,
        iosShow: undefined,
        description: undefined,
        activityExplain: undefined,
        ruleExplain: undefined,
        columnGroup: undefined,
        command: undefined,
        auth: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "id不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "栏目名称不能为空", trigger: "blur" }
        ],
        iconPath: [
          { required: true, message: "图标地址不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态：0：未发表  1：已经发表  默认1不能为空", trigger: "blur" }
        ],
        isDelete: [
          { required: true, message: "删除状态:0:未删除  1：已经删除  默认0不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        addUser: [
          { required: true, message: "添加者不能为空", trigger: "blur" }
        ],
        parentId: [
          { required: true, message: "二级栏目不能为空", trigger: "blur" }
        ],
        clickUrl: [
          { required: true, message: "点击地址（执行函数）不能为空", trigger: "blur" }
        ],
        tenantId: [
          { required: true, message: "租户ID不能为空", trigger: "blur" }
        ],
        androidShow: [
          { required: true, message: "android显示状态：0不显示，1：显示不能为空", trigger: "blur" }
        ],
        iosShow: [
          { required: true, message: "ios显示状态：0不显示，1：显示不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "描述不能为空", trigger: "blur" }
        ],
        activityExplain: [
          { required: true, message: "活动说明不能为空", trigger: "blur" }
        ],
        ruleExplain: [
          { required: true, message: "规则说明不能为空", trigger: "blur" }
        ],
        columnGroup: [
          { required: true, message: "栏目不能为空", trigger: "blur" }
        ],
        command: [
          { required: true, message: "口令不能为空", trigger: "blur" }
        ],
        auth: [
          { required: true, message: "权限不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询栏目列表 */
    getList() {
      this.loading = true;
      listChannel(this.queryParams).then(response => {
        this.channelList = response.data.records;
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
        iconPath: undefined,
        sort: undefined,
        status: undefined,
        isDelete: undefined,
        createTime: undefined,
        updateTime: undefined,
        addUser: undefined,
        parentId: undefined,
        clickUrl: undefined,
        tenantId: undefined,
        androidShow: undefined,
        iosShow: undefined,
        description: undefined,
        activityExplain: undefined,
        ruleExplain: undefined,
        columnGroup: undefined,
        command: undefined,
        auth: undefined,
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
      this.title = "添加栏目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ${pkColumn.propertyName} = row.${pkColumn.propertyName} || this.ids
      getChannel(${pkColumn.propertyName}).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改栏目";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.${pkColumn.propertyName} != undefined) {
            updateChannel(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addChannel(this.form).then(response => {
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
          return delChannel(${pkColumn.propertyName}s);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有栏目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportChannel(queryParams);
        }).then(data => {
          this.download(data, "栏目");
        }).catch(function() {});
    }
  }
};
</script>
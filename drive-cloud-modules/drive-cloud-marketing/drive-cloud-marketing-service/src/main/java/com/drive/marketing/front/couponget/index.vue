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
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入用户手机号"
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
      <el-form-item label="优惠券ID" prop="couponId">
        <el-input
          v-model="queryParams.couponId"
          placeholder="请输入优惠券ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  " prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  "
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="领取时间" prop="getTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.getTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择领取时间">
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
      <el-form-item label="优惠券码" prop="couponCode">
        <el-input
          v-model="queryParams.couponCode"
          placeholder="请输入优惠券码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="获取类型：0->后台赠送；1->主动获取" prop="getType">
        <el-input
          v-model="queryParams.getType"
          placeholder="请输入获取类型：0->后台赠送；1->主动获取"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用时间" prop="useTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.useTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择使用时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="有效期开始日" prop="periodTimeStart">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.periodTimeStart"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择有效期开始日">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="有效期内  结束" prop="periodTimeEnd">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.periodTimeEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择有效期内  结束">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="operatingPosition">
        <el-input
          v-model="queryParams.operatingPosition"
          placeholder="请输入"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源 ID  （主要用于来源活动ID）" prop="source">
        <el-input
          v-model="queryParams.source"
          placeholder="请输入来源 ID  （主要用于来源活动ID）"
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
      <el-form-item label="" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="推广ID" prop="promoteUserId">
        <el-input
          v-model="queryParams.promoteUserId"
          placeholder="请输入推广ID"
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
          v-hasPermi="['marketing:couponGet:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketing:couponGet:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketing:couponGet:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['marketing:couponGet:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="couponGetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="id" align="center" prop="id" />
        <el-table-column label="用户名称" align="center" prop="userName" />
        <el-table-column label="用户手机号" align="center" prop="phone" />
        <el-table-column label="用户ID" align="center" prop="userId" />
        <el-table-column label="优惠券ID" align="center" prop="couponId" />
        <el-table-column label="优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  " align="center" prop="status" />
        <el-table-column label="领取时间" align="center" prop="getTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.getTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.updateTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="优惠券码" align="center" prop="couponCode" />
        <el-table-column label="获取类型：0->后台赠送；1->主动获取" align="center" prop="getType" />
        <el-table-column label="使用时间" align="center" prop="useTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.useTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="有效期开始日" align="center" prop="periodTimeStart" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.periodTimeStart) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="有效期内  结束" align="center" prop="periodTimeEnd" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.periodTimeEnd) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="" align="center" prop="operatingPosition" />
        <el-table-column label="来源 ID  （主要用于来源活动ID）" align="center" prop="source" />
        <el-table-column label="租户ID" align="center" prop="tenantId" />
        <el-table-column label="" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="推广ID" align="center" prop="promoteUserId" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['marketing:couponGet:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['marketing:couponGet:delete']"
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

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="id" prop="id">
            <el-input v-model="form.id" placeholder="请输入id" />
        </el-form-item>
        <el-form-item label="用户名称" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="用户手机号" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入用户手机号" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
            <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="优惠券ID" prop="couponId">
            <el-input v-model="form.couponId" placeholder="请输入优惠券ID" />
        </el-form-item>
        <el-form-item label="优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  " prop="status">
            <el-input v-model="form.status" placeholder="请输入优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  " />
        </el-form-item>
        <el-form-item label="领取时间" prop="getTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.getTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择领取时间">
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
        <el-form-item label="优惠券码" prop="couponCode">
            <el-input v-model="form.couponCode" placeholder="请输入优惠券码" />
        </el-form-item>
        <el-form-item label="获取类型：0->后台赠送；1->主动获取" prop="getType">
            <el-input v-model="form.getType" placeholder="请输入获取类型：0->后台赠送；1->主动获取" />
        </el-form-item>
        <el-form-item label="使用时间" prop="useTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.useTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择使用时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="有效期开始日" prop="periodTimeStart">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.periodTimeStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择有效期开始日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="有效期内  结束" prop="periodTimeEnd">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.periodTimeEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择有效期内  结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="operatingPosition">
            <el-input v-model="form.operatingPosition" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="来源 ID  （主要用于来源活动ID）" prop="source">
            <el-input v-model="form.source" placeholder="请输入来源 ID  （主要用于来源活动ID）" />
        </el-form-item>
        <el-form-item label="租户ID" prop="tenantId">
            <el-input v-model="form.tenantId" placeholder="请输入租户ID" />
        </el-form-item>
        <el-form-item label="" prop="createTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="推广ID" prop="promoteUserId">
            <el-input v-model="form.promoteUserId" placeholder="请输入推广ID" />
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
import { listCouponGet, getCouponGet, delCouponGet, addCouponGet, updateCouponGet, exportCouponGet } from "@/api/marketing/couponGet";

export default {
  name: "CouponGet",
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
      // 表格数据
      couponGetList: [],
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
        userId: undefined,
        couponId: undefined,
        status: undefined,
        getTime: undefined,
        updateTime: undefined,
        couponCode: undefined,
        getType: undefined,
        useTime: undefined,
        periodTimeStart: undefined,
        periodTimeEnd: undefined,
        operatingPosition: undefined,
        source: undefined,
        tenantId: undefined,
        createTime: undefined,
        promoteUserId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "id不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "用户手机号不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        couponId: [
          { required: true, message: "优惠券ID不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  不能为空", trigger: "blur" }
        ],
        getTime: [
          { required: true, message: "领取时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        couponCode: [
          { required: true, message: "优惠券码不能为空", trigger: "blur" }
        ],
        getType: [
          { required: true, message: "获取类型：0->后台赠送；1->主动获取不能为空", trigger: "blur" }
        ],
        useTime: [
          { required: true, message: "使用时间不能为空", trigger: "blur" }
        ],
        periodTimeStart: [
          { required: true, message: "有效期开始日不能为空", trigger: "blur" }
        ],
        periodTimeEnd: [
          { required: true, message: "有效期内  结束不能为空", trigger: "blur" }
        ],
        operatingPosition: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        source: [
          { required: true, message: "来源 ID  （主要用于来源活动ID）不能为空", trigger: "blur" }
        ],
        tenantId: [
          { required: true, message: "租户ID不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        promoteUserId: [
          { required: true, message: "推广ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      listCouponGet(this.queryParams).then(response => {
        this.couponGetList = response.data.records;
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
        userId: undefined,
        couponId: undefined,
        status: undefined,
        getTime: undefined,
        updateTime: undefined,
        couponCode: undefined,
        getType: undefined,
        useTime: undefined,
        periodTimeStart: undefined,
        periodTimeEnd: undefined,
        operatingPosition: undefined,
        source: undefined,
        tenantId: undefined,
        createTime: undefined,
        promoteUserId: undefined,
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
      this.title = "添加";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCouponGet(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCouponGet(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCouponGet(this.form).then(response => {
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
          return delCouponGet(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCouponGet(queryParams);
        }).then(data => {
          this.download(data, "");
        }).catch(function() {});
    }
  }
};
</script>
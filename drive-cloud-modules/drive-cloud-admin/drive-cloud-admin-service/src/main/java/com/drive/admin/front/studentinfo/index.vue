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
      <el-form-item label="手机" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入昵称"
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
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
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
      <el-form-item label="性别（男；女）" prop="sex">
        <el-input
          v-model="queryParams.sex"
          placeholder="请输入性别（男；女）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
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
      <el-form-item label="推荐人id" prop="recommendUserId">
        <el-input
          v-model="queryParams.recommendUserId"
          placeholder="请输入推荐人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐类型（1-学员；2-教练；3-运维）" prop="recommendUserType">
        <el-input
          v-model="queryParams.recommendUserType"
          placeholder="请输入推荐类型（1-学员；2-教练；3-运维）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐新用户类型（1-收入现金，2-收入学车币）" prop="recommendIncomeType">
        <el-input
          v-model="queryParams.recommendIncomeType"
          placeholder="请输入推荐新用户类型（1-收入现金，2-收入学车币）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐时间" prop="recommendDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.recommendDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择推荐时间">
        </el-date-picker>
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
      <el-form-item label="微信openid" prop="wechatOpenid">
        <el-input
          v-model="queryParams.wechatOpenid"
          placeholder="请输入微信openid"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="QQopenid" prop="qqOpenid">
        <el-input
          v-model="queryParams.qqOpenid"
          placeholder="请输入QQopenid"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="token_key" prop="tokenKey">
        <el-input
          v-model="queryParams.tokenKey"
          placeholder="请输入token_key"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用状态1：正常；2：停用" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入使用状态1：正常；2：停用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录时间" prop="loginTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.loginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择登录时间">
        </el-date-picker>
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
      <el-form-item label="科目一考试是否通过（是，否）" prop="subject1TestResultType">
        <el-input
          v-model="queryParams.subject1TestResultType"
          placeholder="请输入科目一考试是否通过（是，否）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="科目二考试是否通过（是，否）" prop="subject2TestResultType">
        <el-input
          v-model="queryParams.subject2TestResultType"
          placeholder="请输入科目二考试是否通过（是，否）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="科目三考试是否通过（是，否）" prop="subject3TestResultType">
        <el-input
          v-model="queryParams.subject3TestResultType"
          placeholder="请输入科目三考试是否通过（是，否）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="科目四考试是否通过（是，否）" prop="subject4TestResultType">
        <el-input
          v-model="queryParams.subject4TestResultType"
          placeholder="请输入科目四考试是否通过（是，否）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备唯一id" prop="phoneId">
        <el-input
          v-model="queryParams.phoneId"
          placeholder="请输入设备唯一id"
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
      <el-form-item label="登录设备类型" prop="platform">
        <el-input
          v-model="queryParams.platform"
          placeholder="请输入登录设备类型"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）" prop="logonChannel">
        <el-input
          v-model="queryParams.logonChannel"
          placeholder="请输入注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）"
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
          v-hasPermi="['admin:studentInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:studentInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:studentInfo:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:studentInfo:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="studentInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="" align="center" prop="id" />
        <el-table-column label="省" align="center" prop="provinceId" />
        <el-table-column label="市" align="center" prop="cityId" />
        <el-table-column label="区" align="center" prop="areaId" />
        <el-table-column label="手机" align="center" prop="phone" />
        <el-table-column label="昵称" align="center" prop="username" />
        <el-table-column label="密码" align="center" prop="password" />
        <el-table-column label="身份证号" align="center" prop="idCard" />
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="年龄" align="center" prop="age" />
        <el-table-column label="性别（男；女）" align="center" prop="sex" />
        <el-table-column label="邮箱" align="center" prop="email" />
        <el-table-column label="自身推荐码" align="center" prop="recommendCodeSelf" />
        <el-table-column label="推荐人id" align="center" prop="recommendUserId" />
        <el-table-column label="推荐类型（1-学员；2-教练；3-运维）" align="center" prop="recommendUserType" />
        <el-table-column label="推荐新用户类型（1-收入现金，2-收入学车币）" align="center" prop="recommendIncomeType" />
        <el-table-column label="推荐时间" align="center" prop="recommendDate" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.recommendDate) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="头像路径" align="center" prop="headUrl" />
        <el-table-column label="微信openid" align="center" prop="wechatOpenid" />
        <el-table-column label="QQopenid" align="center" prop="qqOpenid" />
        <el-table-column label="token_key" align="center" prop="tokenKey" />
        <el-table-column label="使用状态1：正常；2：停用" align="center" prop="status" />
        <el-table-column label="登录时间" align="center" prop="loginTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.loginTime) }}</span>
            </template>
        </el-table-column>
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
        <el-table-column label="科目一考试是否通过（是，否）" align="center" prop="subject1TestResultType" />
        <el-table-column label="科目二考试是否通过（是，否）" align="center" prop="subject2TestResultType" />
        <el-table-column label="科目三考试是否通过（是，否）" align="center" prop="subject3TestResultType" />
        <el-table-column label="科目四考试是否通过（是，否）" align="center" prop="subject4TestResultType" />
        <el-table-column label="渠道  1：扫码 2：线上推广" align="center" prop="channel" />
        <el-table-column label="设备唯一id" align="center" prop="phoneId" />
        <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
        <el-table-column label="登录设备类型" align="center" prop="platform" />
        <el-table-column label="注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）" align="center" prop="logonChannel" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['admin:studentInfo:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['admin:studentInfo:delete']"
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

    <!-- 添加或修改学员信息表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="" prop="id">
            <el-input v-model="form.id" placeholder="请输入" />
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
        <el-form-item label="手机" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机" />
        </el-form-item>
        <el-form-item label="昵称" prop="username">
            <el-input v-model="form.username" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
            <el-input v-model="form.idCard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
            <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="性别（男；女）" prop="sex">
            <el-input v-model="form.sex" placeholder="请输入性别（男；女）" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="自身推荐码" prop="recommendCodeSelf">
            <el-input v-model="form.recommendCodeSelf" placeholder="请输入自身推荐码" />
        </el-form-item>
        <el-form-item label="推荐人id" prop="recommendUserId">
            <el-input v-model="form.recommendUserId" placeholder="请输入推荐人id" />
        </el-form-item>
        <el-form-item label="推荐类型（1-学员；2-教练；3-运维）" prop="recommendUserType">
            <el-input v-model="form.recommendUserType" placeholder="请输入推荐类型（1-学员；2-教练；3-运维）" />
        </el-form-item>
        <el-form-item label="推荐新用户类型（1-收入现金，2-收入学车币）" prop="recommendIncomeType">
            <el-input v-model="form.recommendIncomeType" placeholder="请输入推荐新用户类型（1-收入现金，2-收入学车币）" />
        </el-form-item>
        <el-form-item label="推荐时间" prop="recommendDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.recommendDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择推荐时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="头像路径" prop="headUrl">
            <el-input v-model="form.headUrl" placeholder="请输入头像路径" />
        </el-form-item>
        <el-form-item label="微信openid" prop="wechatOpenid">
            <el-input v-model="form.wechatOpenid" placeholder="请输入微信openid" />
        </el-form-item>
        <el-form-item label="QQopenid" prop="qqOpenid">
            <el-input v-model="form.qqOpenid" placeholder="请输入QQopenid" />
        </el-form-item>
        <el-form-item label="token_key" prop="tokenKey">
            <el-input v-model="form.tokenKey" placeholder="请输入token_key" />
        </el-form-item>
        <el-form-item label="使用状态1：正常；2：停用" prop="status">
            <el-input v-model="form.status" placeholder="请输入使用状态1：正常；2：停用" />
        </el-form-item>
        <el-form-item label="登录时间" prop="loginTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.loginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择登录时间">
          </el-date-picker>
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
        <el-form-item label="科目一考试是否通过（是，否）" prop="subject1TestResultType">
            <el-input v-model="form.subject1TestResultType" placeholder="请输入科目一考试是否通过（是，否）" />
        </el-form-item>
        <el-form-item label="科目二考试是否通过（是，否）" prop="subject2TestResultType">
            <el-input v-model="form.subject2TestResultType" placeholder="请输入科目二考试是否通过（是，否）" />
        </el-form-item>
        <el-form-item label="科目三考试是否通过（是，否）" prop="subject3TestResultType">
            <el-input v-model="form.subject3TestResultType" placeholder="请输入科目三考试是否通过（是，否）" />
        </el-form-item>
        <el-form-item label="科目四考试是否通过（是，否）" prop="subject4TestResultType">
            <el-input v-model="form.subject4TestResultType" placeholder="请输入科目四考试是否通过（是，否）" />
        </el-form-item>
        <el-form-item label="渠道  1：扫码 2：线上推广" prop="channel">
            <el-input v-model="form.channel" placeholder="请输入渠道  1：扫码 2：线上推广" />
        </el-form-item>
        <el-form-item label="设备唯一id" prop="phoneId">
            <el-input v-model="form.phoneId" placeholder="请输入设备唯一id" />
        </el-form-item>
        <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
        </el-form-item>
        <el-form-item label="登录设备类型" prop="platform">
            <el-input v-model="form.platform" placeholder="请输入登录设备类型" />
        </el-form-item>
        <el-form-item label="注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）" prop="logonChannel">
            <el-input v-model="form.logonChannel" placeholder="请输入注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）" />
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
import { listStudentInfo, getStudentInfo, delStudentInfo, addStudentInfo, updateStudentInfo, exportStudentInfo } from "@/api/admin/studentInfo";

export default {
  name: "StudentInfo",
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
      // 学员信息表表格数据
      studentInfoList: [],
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
        phone: undefined,
        username: undefined,
        password: undefined,
        idCard: undefined,
        realName: undefined,
        age: undefined,
        sex: undefined,
        email: undefined,
        recommendCodeSelf: undefined,
        recommendUserId: undefined,
        recommendUserType: undefined,
        recommendIncomeType: undefined,
        recommendDate: undefined,
        headUrl: undefined,
        wechatOpenid: undefined,
        qqOpenid: undefined,
        tokenKey: undefined,
        status: undefined,
        loginTime: undefined,
        createTime: undefined,
        updateTime: undefined,
        subject1TestResultType: undefined,
        subject2TestResultType: undefined,
        subject3TestResultType: undefined,
        subject4TestResultType: undefined,
        channel: undefined,
        phoneId: undefined,
        operatorId: undefined,
        platform: undefined,
        logonChannel: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "不能为空", trigger: "blur" }
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
        phone: [
          { required: true, message: "手机不能为空", trigger: "blur" }
        ],
        username: [
          { required: true, message: "昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        idCard: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
        ],
        realName: [
          { required: true, message: "真实姓名不能为空", trigger: "blur" }
        ],
        age: [
          { required: true, message: "年龄不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别（男；女）不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" }
        ],
        recommendCodeSelf: [
          { required: true, message: "自身推荐码不能为空", trigger: "blur" }
        ],
        recommendUserId: [
          { required: true, message: "推荐人id不能为空", trigger: "blur" }
        ],
        recommendUserType: [
          { required: true, message: "推荐类型（1-学员；2-教练；3-运维）不能为空", trigger: "blur" }
        ],
        recommendIncomeType: [
          { required: true, message: "推荐新用户类型（1-收入现金，2-收入学车币）不能为空", trigger: "blur" }
        ],
        recommendDate: [
          { required: true, message: "推荐时间不能为空", trigger: "blur" }
        ],
        headUrl: [
          { required: true, message: "头像路径不能为空", trigger: "blur" }
        ],
        wechatOpenid: [
          { required: true, message: "微信openid不能为空", trigger: "blur" }
        ],
        qqOpenid: [
          { required: true, message: "QQopenid不能为空", trigger: "blur" }
        ],
        tokenKey: [
          { required: true, message: "token_key不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "使用状态1：正常；2：停用不能为空", trigger: "blur" }
        ],
        loginTime: [
          { required: true, message: "登录时间不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        subject1TestResultType: [
          { required: true, message: "科目一考试是否通过（是，否）不能为空", trigger: "blur" }
        ],
        subject2TestResultType: [
          { required: true, message: "科目二考试是否通过（是，否）不能为空", trigger: "blur" }
        ],
        subject3TestResultType: [
          { required: true, message: "科目三考试是否通过（是，否）不能为空", trigger: "blur" }
        ],
        subject4TestResultType: [
          { required: true, message: "科目四考试是否通过（是，否）不能为空", trigger: "blur" }
        ],
        channel: [
          { required: true, message: "渠道  1：扫码 2：线上推广不能为空", trigger: "blur" }
        ],
        phoneId: [
          { required: true, message: "设备唯一id不能为空", trigger: "blur" }
        ],
        operatorId: [
          { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
        ],
        platform: [
          { required: true, message: "登录设备类型不能为空", trigger: "blur" }
        ],
        logonChannel: [
          { required: true, message: "注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学员信息表列表 */
    getList() {
      this.loading = true;
      listStudentInfo(this.queryParams).then(response => {
        this.studentInfoList = response.data.records;
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
        phone: undefined,
        username: undefined,
        password: undefined,
        idCard: undefined,
        realName: undefined,
        age: undefined,
        sex: undefined,
        email: undefined,
        recommendCodeSelf: undefined,
        recommendUserId: undefined,
        recommendUserType: undefined,
        recommendIncomeType: undefined,
        recommendDate: undefined,
        headUrl: undefined,
        wechatOpenid: undefined,
        qqOpenid: undefined,
        tokenKey: undefined,
        status: undefined,
        loginTime: undefined,
        createTime: undefined,
        updateTime: undefined,
        subject1TestResultType: undefined,
        subject2TestResultType: undefined,
        subject3TestResultType: undefined,
        subject4TestResultType: undefined,
        channel: undefined,
        phoneId: undefined,
        operatorId: undefined,
        platform: undefined,
        logonChannel: undefined,
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
      this.title = "添加学员信息表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStudentInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学员信息表";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateStudentInfo(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addStudentInfo(this.form).then(response => {
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
          return delStudentInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有学员信息表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStudentInfo(queryParams);
        }).then(data => {
          this.download(data, "学员信息表");
        }).catch(function() {});
    }
  }
};
</script>
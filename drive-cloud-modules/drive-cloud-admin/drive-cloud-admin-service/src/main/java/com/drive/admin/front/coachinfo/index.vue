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
      <el-form-item label="电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="登录密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入登录密码"
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
      <el-form-item label="身份证号码" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证图片正面" prop="idCardPathPositive">
        <el-input
          v-model="queryParams.idCardPathPositive"
          placeholder="请输入身份证图片正面"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证图片反面" prop="idCardPathNegative">
        <el-input
          v-model="queryParams.idCardPathNegative"
          placeholder="请输入身份证图片反面"
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
      <el-form-item label="性别" prop="sex">
        <el-input
          v-model="queryParams.sex"
          placeholder="请输入性别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教练证照片" prop="teachLicensePath">
        <el-input
          v-model="queryParams.teachLicensePath"
          placeholder="请输入教练证照片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教练证号码" prop="teachLicenseNo">
        <el-input
          v-model="queryParams.teachLicenseNo"
          placeholder="请输入教练证号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为金牌教练" prop="isVip">
        <el-input
          v-model="queryParams.isVip"
          placeholder="请输入是否为金牌教练"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交
5-保险过期预警期" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交
5-保险过期预警期"
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
      <el-form-item label="推荐时间" prop="recommendDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.recommendDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择推荐时间">
        </el-date-picker>
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
      <el-form-item label="45度车辆照片" prop="carPhotoPath">
        <el-input
          v-model="queryParams.carPhotoPath"
          placeholder="请输入45度车辆照片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌型号" prop="brandModel">
        <el-input
          v-model="queryParams.brandModel"
          placeholder="请输入品牌型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="颜色" prop="color">
        <el-input
          v-model="queryParams.color"
          placeholder="请输入颜色"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号" prop="vehicleNo">
        <el-input
          v-model="queryParams.vehicleNo"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作证照片路径" prop="workPath">
        <el-input
          v-model="queryParams.workPath"
          placeholder="请输入工作证照片路径"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行驶证照片" prop="drivingLicensePath">
        <el-input
          v-model="queryParams.drivingLicensePath"
          placeholder="请输入行驶证照片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车架号" prop="carFrameNo">
        <el-input
          v-model="queryParams.carFrameNo"
          placeholder="请输入车架号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发动机排量" prop="engineDisplace">
        <el-input
          v-model="queryParams.engineDisplace"
          placeholder="请输入发动机排量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发动机号码" prop="engineId">
        <el-input
          v-model="queryParams.engineId"
          placeholder="请输入发动机号码"
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
      <el-form-item label="经度" prop="longitude">
        <el-input
          v-model="queryParams.longitude"
          placeholder="请输入经度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)" prop="ownerName">
        <el-input
          v-model="queryParams.ownerName"
          placeholder="请输入车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核结果说明" prop="examineResultExplain">
        <el-input
          v-model="queryParams.examineResultExplain"
          placeholder="请输入审核结果说明"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册时间" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择注册时间">
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
      <el-form-item label="保险单照片" prop="insurancePath">
        <el-input
          v-model="queryParams.insurancePath"
          placeholder="请输入保险单照片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车型（1-公车，2-私车）" prop="carType">
        <el-input
          v-model="queryParams.carType"
          placeholder="请输入车型（1-公车，2-私车）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公车所属驾校id" prop="carSchoolId">
        <el-input
          v-model="queryParams.carSchoolId"
          placeholder="请输入公车所属驾校id"
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
          v-hasPermi="['admin:coachInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:coachInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:coachInfo:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:coachInfo:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="coachInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

        <el-table-column label="" align="center" prop="id" />
        <el-table-column label="省" align="center" prop="provinceId" />
        <el-table-column label="市" align="center" prop="cityId" />
        <el-table-column label="区" align="center" prop="areaId" />
        <el-table-column label="电话" align="center" prop="phone" />
        <el-table-column label="登录密码" align="center" prop="password" />
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="身份证号码" align="center" prop="idCard" />
        <el-table-column label="身份证图片正面" align="center" prop="idCardPathPositive" />
        <el-table-column label="身份证图片反面" align="center" prop="idCardPathNegative" />
        <el-table-column label="邮箱" align="center" prop="email" />
        <el-table-column label="年龄" align="center" prop="age" />
        <el-table-column label="教龄" align="center" prop="teachYears" />
        <el-table-column label="性别" align="center" prop="sex" />
        <el-table-column label="教练证照片" align="center" prop="teachLicensePath" />
        <el-table-column label="教练证号码" align="center" prop="teachLicenseNo" />
        <el-table-column label="是否为金牌教练" align="center" prop="isVip" />
        <el-table-column label="状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交
5-保险过期预警期" align="center" prop="status" />
        <el-table-column label="自身推荐码" align="center" prop="recommendCodeSelf" />
        <el-table-column label="推荐人id" align="center" prop="recommendUserId" />
        <el-table-column label="推荐类型（1-学员；2-教练；3-运维）" align="center" prop="recommendUserType" />
        <el-table-column label="推荐时间" align="center" prop="recommendDate" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.recommendDate) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="评价分数" align="center" prop="appraiseGrade" />
        <el-table-column label="token_key" align="center" prop="tokeKey" />
        <el-table-column label="45度车辆照片" align="center" prop="carPhotoPath" />
        <el-table-column label="品牌型号" align="center" prop="brandModel" />
        <el-table-column label="颜色" align="center" prop="color" />
        <el-table-column label="车牌号" align="center" prop="vehicleNo" />
        <el-table-column label="工作证照片路径" align="center" prop="workPath" />
        <el-table-column label="行驶证照片" align="center" prop="drivingLicensePath" />
        <el-table-column label="车架号" align="center" prop="carFrameNo" />
        <el-table-column label="发动机排量" align="center" prop="engineDisplace" />
        <el-table-column label="发动机号码" align="center" prop="engineId" />
        <el-table-column label="纬度" align="center" prop="latitude" />
        <el-table-column label="经度" align="center" prop="longitude" />
        <el-table-column label="车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)" align="center" prop="ownerName" />
        <el-table-column label="车辆注册日期(以行驶证为准)" align="center" prop="certifyDate" />
        <el-table-column label="教练证有效期起" align="center" prop="licenseOn" />
        <el-table-column label="教练证有效期止" align="center" prop="licenseOff" />
        <el-table-column label="审核结果说明" align="center" prop="examineResultExplain" />
        <el-table-column label="注册时间" align="center" prop="createTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
            <template slot-scope="scope">
                <span>{{ parseTime(scope.row.updateTime) }}</span>
            </template>
        </el-table-column>
        <el-table-column label="保险单照片" align="center" prop="insurancePath" />
        <el-table-column label="保险开始时间" align="center" prop="insuranceStartDate" />
        <el-table-column label="保险截止时间" align="center" prop="insuranceEndDate" />
        <el-table-column label="车型（1-公车，2-私车）" align="center" prop="carType" />
        <el-table-column label="公车所属驾校id" align="center" prop="carSchoolId" />
        <el-table-column label="教练提成百分比" align="center" prop="trainCoachChargePercent" />
        <el-table-column label="公车提成百分比" align="center" prop="carChargePercent" />
        <el-table-column label="平台提成百分比" align="center" prop="serviceChangePercent" />
        <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
        <el-table-column label="自动发科 1 是 2否" align="center" prop="autoSendClass" />
        <el-table-column label="绩效归属 1  教练自己 2  驾校" align="center" prop="performanceAffiliation" />
        <el-table-column label="" align="center" prop="topSort" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['admin:coachInfo:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['admin:coachInfo:delete']"
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

    <!-- 添加或修改教练信息表对话框 -->
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
        <el-form-item label="电话" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
            <el-input v-model="form.password" placeholder="请输入登录密码" />
        </el-form-item>
        <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="身份证号码" prop="idCard">
            <el-input v-model="form.idCard" placeholder="请输入身份证号码" />
        </el-form-item>
        <el-form-item label="身份证图片正面" prop="idCardPathPositive">
            <el-input v-model="form.idCardPathPositive" placeholder="请输入身份证图片正面" />
        </el-form-item>
        <el-form-item label="身份证图片反面" prop="idCardPathNegative">
            <el-input v-model="form.idCardPathNegative" placeholder="请输入身份证图片反面" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
            <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="教龄" prop="teachYears">
            <el-input v-model="form.teachYears" placeholder="请输入教龄" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
            <el-input v-model="form.sex" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="教练证照片" prop="teachLicensePath">
            <el-input v-model="form.teachLicensePath" placeholder="请输入教练证照片" />
        </el-form-item>
        <el-form-item label="教练证号码" prop="teachLicenseNo">
            <el-input v-model="form.teachLicenseNo" placeholder="请输入教练证号码" />
        </el-form-item>
        <el-form-item label="是否为金牌教练" prop="isVip">
            <el-input v-model="form.isVip" placeholder="请输入是否为金牌教练" />
        </el-form-item>
        <el-form-item label="状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交
5-保险过期预警期" prop="status">
            <el-input v-model="form.status" placeholder="请输入状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交
5-保险过期预警期" />
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
        <el-form-item label="推荐时间" prop="recommendDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.recommendDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择推荐时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评价分数" prop="appraiseGrade">
            <el-input v-model="form.appraiseGrade" placeholder="请输入评价分数" />
        </el-form-item>
        <el-form-item label="token_key" prop="tokeKey">
            <el-input v-model="form.tokeKey" placeholder="请输入token_key" />
        </el-form-item>
        <el-form-item label="45度车辆照片" prop="carPhotoPath">
            <el-input v-model="form.carPhotoPath" placeholder="请输入45度车辆照片" />
        </el-form-item>
        <el-form-item label="品牌型号" prop="brandModel">
            <el-input v-model="form.brandModel" placeholder="请输入品牌型号" />
        </el-form-item>
        <el-form-item label="颜色" prop="color">
            <el-input v-model="form.color" placeholder="请输入颜色" />
        </el-form-item>
        <el-form-item label="车牌号" prop="vehicleNo">
            <el-input v-model="form.vehicleNo" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="工作证照片路径" prop="workPath">
            <el-input v-model="form.workPath" placeholder="请输入工作证照片路径" />
        </el-form-item>
        <el-form-item label="行驶证照片" prop="drivingLicensePath">
            <el-input v-model="form.drivingLicensePath" placeholder="请输入行驶证照片" />
        </el-form-item>
        <el-form-item label="车架号" prop="carFrameNo">
            <el-input v-model="form.carFrameNo" placeholder="请输入车架号" />
        </el-form-item>
        <el-form-item label="发动机排量" prop="engineDisplace">
            <el-input v-model="form.engineDisplace" placeholder="请输入发动机排量" />
        </el-form-item>
        <el-form-item label="发动机号码" prop="engineId">
            <el-input v-model="form.engineId" placeholder="请输入发动机号码" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
            <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
            <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)" prop="ownerName">
            <el-input v-model="form.ownerName" placeholder="请输入车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)" />
        </el-form-item>
        <el-form-item label="车辆注册日期(以行驶证为准)" prop="certifyDate">
            <el-input v-model="form.certifyDate" placeholder="请输入车辆注册日期(以行驶证为准)" />
        </el-form-item>
        <el-form-item label="教练证有效期起" prop="licenseOn">
            <el-input v-model="form.licenseOn" placeholder="请输入教练证有效期起" />
        </el-form-item>
        <el-form-item label="教练证有效期止" prop="licenseOff">
            <el-input v-model="form.licenseOff" placeholder="请输入教练证有效期止" />
        </el-form-item>
        <el-form-item label="审核结果说明" prop="examineResultExplain">
            <el-input v-model="form.examineResultExplain" placeholder="请输入审核结果说明" />
        </el-form-item>
        <el-form-item label="注册时间" prop="createTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.createTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择注册时间">
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
        <el-form-item label="保险单照片" prop="insurancePath">
            <el-input v-model="form.insurancePath" placeholder="请输入保险单照片" />
        </el-form-item>
        <el-form-item label="保险开始时间" prop="insuranceStartDate">
            <el-input v-model="form.insuranceStartDate" placeholder="请输入保险开始时间" />
        </el-form-item>
        <el-form-item label="保险截止时间" prop="insuranceEndDate">
            <el-input v-model="form.insuranceEndDate" placeholder="请输入保险截止时间" />
        </el-form-item>
        <el-form-item label="车型（1-公车，2-私车）" prop="carType">
            <el-input v-model="form.carType" placeholder="请输入车型（1-公车，2-私车）" />
        </el-form-item>
        <el-form-item label="公车所属驾校id" prop="carSchoolId">
            <el-input v-model="form.carSchoolId" placeholder="请输入公车所属驾校id" />
        </el-form-item>
        <el-form-item label="教练提成百分比" prop="trainCoachChargePercent">
            <el-input v-model="form.trainCoachChargePercent" placeholder="请输入教练提成百分比" />
        </el-form-item>
        <el-form-item label="公车提成百分比" prop="carChargePercent">
            <el-input v-model="form.carChargePercent" placeholder="请输入公车提成百分比" />
        </el-form-item>
        <el-form-item label="平台提成百分比" prop="serviceChangePercent">
            <el-input v-model="form.serviceChangePercent" placeholder="请输入平台提成百分比" />
        </el-form-item>
        <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
        </el-form-item>
        <el-form-item label="自动发科 1 是 2否" prop="autoSendClass">
            <el-input v-model="form.autoSendClass" placeholder="请输入自动发科 1 是 2否" />
        </el-form-item>
        <el-form-item label="绩效归属 1  教练自己 2  驾校" prop="performanceAffiliation">
            <el-input v-model="form.performanceAffiliation" placeholder="请输入绩效归属 1  教练自己 2  驾校" />
        </el-form-item>
        <el-form-item label="" prop="topSort">
            <el-input v-model="form.topSort" placeholder="请输入" />
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
import { listCoachInfo, getCoachInfo, delCoachInfo, addCoachInfo, updateCoachInfo, exportCoachInfo } from "@/api/admin/coachInfo";

export default {
  name: "CoachInfo",
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
      // 教练信息表表格数据
      coachInfoList: [],
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
        password: undefined,
        realName: undefined,
        idCard: undefined,
        idCardPathPositive: undefined,
        idCardPathNegative: undefined,
        email: undefined,
        age: undefined,
        teachYears: undefined,
        sex: undefined,
        teachLicensePath: undefined,
        teachLicenseNo: undefined,
        isVip: undefined,
        status: undefined,
        recommendCodeSelf: undefined,
        recommendUserId: undefined,
        recommendUserType: undefined,
        recommendDate: undefined,
        appraiseGrade: undefined,
        tokeKey: undefined,
        carPhotoPath: undefined,
        brandModel: undefined,
        color: undefined,
        vehicleNo: undefined,
        workPath: undefined,
        drivingLicensePath: undefined,
        carFrameNo: undefined,
        engineDisplace: undefined,
        engineId: undefined,
        latitude: undefined,
        longitude: undefined,
        ownerName: undefined,
        certifyDate: undefined,
        licenseOn: undefined,
        licenseOff: undefined,
        examineResultExplain: undefined,
        createTime: undefined,
        updateTime: undefined,
        insurancePath: undefined,
        insuranceStartDate: undefined,
        insuranceEndDate: undefined,
        carType: undefined,
        carSchoolId: undefined,
        trainCoachChargePercent: undefined,
        carChargePercent: undefined,
        serviceChangePercent: undefined,
        operatorId: undefined,
        autoSendClass: undefined,
        performanceAffiliation: undefined,
        topSort: undefined,
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
          { required: true, message: "电话不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "登录密码不能为空", trigger: "blur" }
        ],
        realName: [
          { required: true, message: "真实姓名不能为空", trigger: "blur" }
        ],
        idCard: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" }
        ],
        idCardPathPositive: [
          { required: true, message: "身份证图片正面不能为空", trigger: "blur" }
        ],
        idCardPathNegative: [
          { required: true, message: "身份证图片反面不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" }
        ],
        age: [
          { required: true, message: "年龄不能为空", trigger: "blur" }
        ],
        teachYears: [
          { required: true, message: "教龄不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        teachLicensePath: [
          { required: true, message: "教练证照片不能为空", trigger: "blur" }
        ],
        teachLicenseNo: [
          { required: true, message: "教练证号码不能为空", trigger: "blur" }
        ],
        isVip: [
          { required: true, message: "是否为金牌教练不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交
5-保险过期预警期不能为空", trigger: "blur" }
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
        recommendDate: [
          { required: true, message: "推荐时间不能为空", trigger: "blur" }
        ],
        appraiseGrade: [
          { required: true, message: "评价分数不能为空", trigger: "blur" }
        ],
        tokeKey: [
          { required: true, message: "token_key不能为空", trigger: "blur" }
        ],
        carPhotoPath: [
          { required: true, message: "45度车辆照片不能为空", trigger: "blur" }
        ],
        brandModel: [
          { required: true, message: "品牌型号不能为空", trigger: "blur" }
        ],
        color: [
          { required: true, message: "颜色不能为空", trigger: "blur" }
        ],
        vehicleNo: [
          { required: true, message: "车牌号不能为空", trigger: "blur" }
        ],
        workPath: [
          { required: true, message: "工作证照片路径不能为空", trigger: "blur" }
        ],
        drivingLicensePath: [
          { required: true, message: "行驶证照片不能为空", trigger: "blur" }
        ],
        carFrameNo: [
          { required: true, message: "车架号不能为空", trigger: "blur" }
        ],
        engineDisplace: [
          { required: true, message: "发动机排量不能为空", trigger: "blur" }
        ],
        engineId: [
          { required: true, message: "发动机号码不能为空", trigger: "blur" }
        ],
        latitude: [
          { required: true, message: "纬度不能为空", trigger: "blur" }
        ],
        longitude: [
          { required: true, message: "经度不能为空", trigger: "blur" }
        ],
        ownerName: [
          { required: true, message: "车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)不能为空", trigger: "blur" }
        ],
        certifyDate: [
          { required: true, message: "车辆注册日期(以行驶证为准)不能为空", trigger: "blur" }
        ],
        licenseOn: [
          { required: true, message: "教练证有效期起不能为空", trigger: "blur" }
        ],
        licenseOff: [
          { required: true, message: "教练证有效期止不能为空", trigger: "blur" }
        ],
        examineResultExplain: [
          { required: true, message: "审核结果说明不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "注册时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        insurancePath: [
          { required: true, message: "保险单照片不能为空", trigger: "blur" }
        ],
        insuranceStartDate: [
          { required: true, message: "保险开始时间不能为空", trigger: "blur" }
        ],
        insuranceEndDate: [
          { required: true, message: "保险截止时间不能为空", trigger: "blur" }
        ],
        carType: [
          { required: true, message: "车型（1-公车，2-私车）不能为空", trigger: "blur" }
        ],
        carSchoolId: [
          { required: true, message: "公车所属驾校id不能为空", trigger: "blur" }
        ],
        trainCoachChargePercent: [
          { required: true, message: "教练提成百分比不能为空", trigger: "blur" }
        ],
        carChargePercent: [
          { required: true, message: "公车提成百分比不能为空", trigger: "blur" }
        ],
        serviceChangePercent: [
          { required: true, message: "平台提成百分比不能为空", trigger: "blur" }
        ],
        operatorId: [
          { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
        ],
        autoSendClass: [
          { required: true, message: "自动发科 1 是 2否不能为空", trigger: "blur" }
        ],
        performanceAffiliation: [
          { required: true, message: "绩效归属 1  教练自己 2  驾校不能为空", trigger: "blur" }
        ],
        topSort: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询教练信息表列表 */
    getList() {
      this.loading = true;
      listCoachInfo(this.queryParams).then(response => {
        this.coachInfoList = response.data.records;
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
        password: undefined,
        realName: undefined,
        idCard: undefined,
        idCardPathPositive: undefined,
        idCardPathNegative: undefined,
        email: undefined,
        age: undefined,
        teachYears: undefined,
        sex: undefined,
        teachLicensePath: undefined,
        teachLicenseNo: undefined,
        isVip: undefined,
        status: undefined,
        recommendCodeSelf: undefined,
        recommendUserId: undefined,
        recommendUserType: undefined,
        recommendDate: undefined,
        appraiseGrade: undefined,
        tokeKey: undefined,
        carPhotoPath: undefined,
        brandModel: undefined,
        color: undefined,
        vehicleNo: undefined,
        workPath: undefined,
        drivingLicensePath: undefined,
        carFrameNo: undefined,
        engineDisplace: undefined,
        engineId: undefined,
        latitude: undefined,
        longitude: undefined,
        ownerName: undefined,
        certifyDate: undefined,
        licenseOn: undefined,
        licenseOff: undefined,
        examineResultExplain: undefined,
        createTime: undefined,
        updateTime: undefined,
        insurancePath: undefined,
        insuranceStartDate: undefined,
        insuranceEndDate: undefined,
        carType: undefined,
        carSchoolId: undefined,
        trainCoachChargePercent: undefined,
        carChargePercent: undefined,
        serviceChangePercent: undefined,
        operatorId: undefined,
        autoSendClass: undefined,
        performanceAffiliation: undefined,
        topSort: undefined,
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
      this.title = "添加教练信息表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCoachInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改教练信息表";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCoachInfo(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCoachInfo(this.form).then(response => {
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
          return delCoachInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有教练信息表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCoachInfo(queryParams);
        }).then(data => {
          this.download(data, "教练信息表");
        }).catch(function() {});
    }
  }
};
</script>
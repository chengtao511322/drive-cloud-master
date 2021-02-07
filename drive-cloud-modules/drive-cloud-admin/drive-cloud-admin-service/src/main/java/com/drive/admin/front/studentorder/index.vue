<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <el-form-item label="订单号" prop="orderNo">
                        <el-input
                                v-model="queryParams.orderNo"
                                placeholder="请输入订单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" prop="orderType">
                        <el-input
                                v-model="queryParams.orderType"
                                placeholder="请输入订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="下单客户(学员id)" prop="studentId">
                        <el-input
                                v-model="queryParams.studentId"
                                placeholder="请输入下单客户(学员id)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="客服ID" prop="userId">
                        <el-input
                                v-model="queryParams.userId"
                                placeholder="请输入客服ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付类型(1-支付宝，2-微信)" prop="payType">
                        <el-input
                                v-model="queryParams.payType"
                                placeholder="请输入支付类型(1-支付宝，2-微信)"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="考试报名单号" prop="testEnrollNo">
                        <el-input
                                v-model="queryParams.testEnrollNo"
                                placeholder="请输入考试报名单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="学车报名单号" prop="studyEnrollNo">
                        <el-input
                                v-model="queryParams.studyEnrollNo"
                                placeholder="请输入学车报名单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="预约单号" prop="trainApplyNo">
                        <el-input
                                v-model="queryParams.trainApplyNo"
                                placeholder="请输入预约单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                                                        <el-form-item label="优惠券ID" prop="columcouponId">
                        <el-input
                                v-model="queryParams.columcouponId"
                                placeholder="请输入优惠券ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                                            <el-form-item label="订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）" prop="status">
                        <el-input
                                v-model="queryParams.status"
                                placeholder="请输入订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="备注" prop="remark">
                        <el-input
                                v-model="queryParams.remark"
                                placeholder="请输入备注"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付时间（支付成功时间）" prop="payTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.payTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择支付时间（支付成功时间）">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="创建时间" prop="createTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择创建时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="修改时间" prop="updateTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.updateTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择修改时间">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="是否删除：0否；1：是" prop="isDelete">
                        <el-input
                                v-model="queryParams.isDelete"
                                placeholder="请输入是否删除：0否；1：是"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="微信预支付订单信息" prop="wxSigninfo">
                        <el-input
                                v-model="queryParams.wxSigninfo"
                                placeholder="请输入微信预支付订单信息"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付宝signInfo信息" prop="aliSigninfo">
                        <el-input
                                v-model="queryParams.aliSigninfo"
                                placeholder="请输入支付宝signInfo信息"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="支付宝退款请求号（用于退款查询）" prop="aliOutRefundNo">
                        <el-input
                                v-model="queryParams.aliOutRefundNo"
                                placeholder="请输入支付宝退款请求号（用于退款查询）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="微信退款请求号（用于退款查询）" prop="wxOutRefundNo">
                        <el-input
                                v-model="queryParams.wxOutRefundNo"
                                placeholder="请输入微信退款请求号（用于退款查询）"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="微信signInfo上一次创建时间（用于判断过期时间）" prop="createWxSigninfoTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createWxSigninfoTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择微信signInfo上一次创建时间（用于判断过期时间）">
                        </el-date-picker>
                    </el-form-item>
                                                                <el-form-item label="支付宝signInfo上一次创建时间（用于判断过期时间）" prop="createAliSigninfoTime">
                        <el-date-picker clearable size="small" style="width: 200px"
                            v-model="queryParams.createAliSigninfoTime"
                            type="datetime"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="选择支付宝signInfo上一次创建时间（用于判断过期时间）">
                        </el-date-picker>
                    </el-form-item>
                                                                                            <el-form-item label="打折产品id" prop="discountProductId">
                        <el-input
                                v-model="queryParams.discountProductId"
                                placeholder="请输入打折产品id"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="优惠卷领取表id" prop="couponUseId">
                        <el-input
                                v-model="queryParams.couponUseId"
                                placeholder="请输入优惠卷领取表id"
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
                                                                <el-form-item label="对应的产品ID" prop="productId">
                        <el-input
                                v-model="queryParams.productId"
                                placeholder="请输入对应的产品ID"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="新订单号" prop="newOrderNo">
                        <el-input
                                v-model="queryParams.newOrderNo"
                                placeholder="请输入新订单号"
                                clearable
                                size="small"
                                @keyup.enter.native="handleQuery"
                        />
                    </el-form-item>
                                                                <el-form-item label="订单类型明细id" prop="classTypeId">
                        <el-input
                                v-model="queryParams.classTypeId"
                                placeholder="请输入订单类型明细id"
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
                    v-hasPermi="['admin:studentOrder:add']"
                >新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['admin:studentOrder:edit']"
                >修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['admin:studentOrder:delete']"
                >删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['admin:studentOrder:export']"
                >导出</el-button>
            </el-col>
        </el-row>

        <el-table v-loading="loading" :data="studentOrderList" @selection-change="handleSelectionChange"
                  @sort-change="sortChange"
                  stripe
                  border
                  header-row-class-name="table-header"
                  :header-row-style="{
                                        fontFamily: '微软雅黑 Bold, 微软雅黑 Regular, 微软雅黑'
                                     }"
        >
            <el-table-column type="selection" width="55" align="center" />

                                                <el-table-column label="订单号" align="center" prop="orderNo" />
                                                                <el-table-column label="订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" align="center" prop="orderType" />
                                                                <el-table-column label="下单客户(学员id)" align="center" prop="studentId" />
                                                                <el-table-column label="客服ID" align="center" prop="userId" />
                                                                <el-table-column label="支付类型(1-支付宝，2-微信)" align="center" prop="payType" />
                                                                <el-table-column label="考试报名单号" align="center" prop="testEnrollNo" />
                                                                <el-table-column label="学车报名单号" align="center" prop="studyEnrollNo" />
                                                                <el-table-column label="预约单号" align="center" prop="trainApplyNo" />
                                                                <el-table-column label="订单金额" align="center" prop="orderAmount" />
                                                                <el-table-column label="应付金额" align="center" prop="payableAmount" />
                                                                <el-table-column label="优惠券ID" align="center" prop="columcouponId" />
                                                                <el-table-column label="优惠券金额" align="center" prop="couponAmount" />
                                                                <el-table-column label="订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）" align="center" prop="status" />
                                                                <el-table-column label="备注" align="center" prop="remark" />
                                                                <el-table-column label="支付时间（支付成功时间）" align="center" prop="payTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.payTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="修改时间" align="center" prop="updateTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.updateTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="是否删除：0否；1：是" align="center" prop="isDelete" />
                                                                <el-table-column label="微信预支付订单信息" align="center" prop="wxSigninfo" />
                                                                <el-table-column label="支付宝signInfo信息" align="center" prop="aliSigninfo" />
                                                                <el-table-column label="支付宝退款请求号（用于退款查询）" align="center" prop="aliOutRefundNo" />
                                                                <el-table-column label="微信退款请求号（用于退款查询）" align="center" prop="wxOutRefundNo" />
                                                                <el-table-column label="微信signInfo上一次创建时间（用于判断过期时间）" align="center" prop="createWxSigninfoTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createWxSigninfoTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="支付宝signInfo上一次创建时间（用于判断过期时间）" align="center" prop="createAliSigninfoTime" width="180" sortable="custom">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.createAliSigninfoTime) }}</span>
                        </template>
                    </el-table-column>
                                                                <el-table-column label="打折金额" align="center" prop="discountAmount" />
                                                                <el-table-column label="打折产品id" align="center" prop="discountProductId" />
                                                                <el-table-column label="优惠卷领取表id" align="center" prop="couponUseId" />
                                                                <el-table-column label="运营商id(数据权限标记)" align="center" prop="operatorId" />
                                                                <el-table-column label="对应的产品ID" align="center" prop="productId" />
                                                                <el-table-column label="新订单号" align="center" prop="newOrderNo" />
                                                                <el-table-column label="订单类型明细id" align="center" prop="classTypeId" />
                                        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['admin:studentOrder:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['admin:studentOrder:delete']"
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

        <!-- 添加或修改学员订单表对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                            <el-form-item label="订单号" prop="orderNo">
                            <el-input v-model="form.orderNo" placeholder="请输入订单号" />
                        </el-form-item>
                                                                                <el-form-item label="订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" prop="orderType">
                            <el-input v-model="form.orderType" placeholder="请输入订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）" />
                        </el-form-item>
                                                                                <el-form-item label="下单客户(学员id)" prop="studentId">
                            <el-input v-model="form.studentId" placeholder="请输入下单客户(学员id)" />
                        </el-form-item>
                                                                                <el-form-item label="客服ID" prop="userId">
                            <el-input v-model="form.userId" placeholder="请输入客服ID" />
                        </el-form-item>
                                                                                <el-form-item label="支付类型(1-支付宝，2-微信)" prop="payType">
                            <el-input v-model="form.payType" placeholder="请输入支付类型(1-支付宝，2-微信)" />
                        </el-form-item>
                                                                                <el-form-item label="考试报名单号" prop="testEnrollNo">
                            <el-input v-model="form.testEnrollNo" placeholder="请输入考试报名单号" />
                        </el-form-item>
                                                                                <el-form-item label="学车报名单号" prop="studyEnrollNo">
                            <el-input v-model="form.studyEnrollNo" placeholder="请输入学车报名单号" />
                        </el-form-item>
                                                                                <el-form-item label="预约单号" prop="trainApplyNo">
                            <el-input v-model="form.trainApplyNo" placeholder="请输入预约单号" />
                        </el-form-item>
                                                                                <el-form-item label="订单金额" prop="orderAmount">
                            <el-input v-model="form.orderAmount" placeholder="请输入订单金额" />
                        </el-form-item>
                                                                                <el-form-item label="应付金额" prop="payableAmount">
                            <el-input v-model="form.payableAmount" placeholder="请输入应付金额" />
                        </el-form-item>
                                                                                <el-form-item label="优惠券ID" prop="columcouponId">
                            <el-input v-model="form.columcouponId" placeholder="请输入优惠券ID" />
                        </el-form-item>
                                                                                <el-form-item label="优惠券金额" prop="couponAmount">
                            <el-input v-model="form.couponAmount" placeholder="请输入优惠券金额" />
                        </el-form-item>
                                                                                <el-form-item label="订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）" prop="status">
                            <el-input v-model="form.status" placeholder="请输入订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）" />
                        </el-form-item>
                                                                                <el-form-item label="备注" prop="remark">
                            <el-input v-model="form.remark" placeholder="请输入备注" />
                        </el-form-item>
                                                                                <el-form-item label="支付时间（支付成功时间）" prop="payTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.payTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择支付时间（支付成功时间）">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择创建时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="修改时间" prop="updateTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.updateTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择修改时间">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="是否删除：0否；1：是" prop="isDelete">
                            <el-input v-model="form.isDelete" placeholder="请输入是否删除：0否；1：是" />
                        </el-form-item>
                                                                                <el-form-item label="微信预支付订单信息" prop="wxSigninfo">
                            <el-input v-model="form.wxSigninfo" placeholder="请输入微信预支付订单信息" />
                        </el-form-item>
                                                                                <el-form-item label="支付宝signInfo信息" prop="aliSigninfo">
                            <el-input v-model="form.aliSigninfo" placeholder="请输入支付宝signInfo信息" />
                        </el-form-item>
                                                                                <el-form-item label="支付宝退款请求号（用于退款查询）" prop="aliOutRefundNo">
                            <el-input v-model="form.aliOutRefundNo" placeholder="请输入支付宝退款请求号（用于退款查询）" />
                        </el-form-item>
                                                                                <el-form-item label="微信退款请求号（用于退款查询）" prop="wxOutRefundNo">
                            <el-input v-model="form.wxOutRefundNo" placeholder="请输入微信退款请求号（用于退款查询）" />
                        </el-form-item>
                                                                                <el-form-item label="微信signInfo上一次创建时间（用于判断过期时间）" prop="createWxSigninfoTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createWxSigninfoTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择微信signInfo上一次创建时间（用于判断过期时间）">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="支付宝signInfo上一次创建时间（用于判断过期时间）" prop="createAliSigninfoTime">
                            <el-date-picker clearable size="small" style="width: 200px"
                                v-model="form.createAliSigninfoTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择支付宝signInfo上一次创建时间（用于判断过期时间）">
                            </el-date-picker>
                        </el-form-item>
                                                                                <el-form-item label="打折金额" prop="discountAmount">
                            <el-input v-model="form.discountAmount" placeholder="请输入打折金额" />
                        </el-form-item>
                                                                                <el-form-item label="打折产品id" prop="discountProductId">
                            <el-input v-model="form.discountProductId" placeholder="请输入打折产品id" />
                        </el-form-item>
                                                                                <el-form-item label="优惠卷领取表id" prop="couponUseId">
                            <el-input v-model="form.couponUseId" placeholder="请输入优惠卷领取表id" />
                        </el-form-item>
                                                                                <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                            <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                        </el-form-item>
                                                                                <el-form-item label="对应的产品ID" prop="productId">
                            <el-input v-model="form.productId" placeholder="请输入对应的产品ID" />
                        </el-form-item>
                                                                                <el-form-item label="新订单号" prop="newOrderNo">
                            <el-input v-model="form.newOrderNo" placeholder="请输入新订单号" />
                        </el-form-item>
                                                                                <el-form-item label="订单类型明细id" prop="classTypeId">
                            <el-input v-model="form.classTypeId" placeholder="请输入订单类型明细id" />
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
    import { listStudentOrder,listPageStudentOrder, getStudentOrder, delStudentOrder, addStudentOrder, updateStudentOrder, exportStudentOrder } from "@/api/admin/studentOrder";

    export default {
        name: "StudentOrder",
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
                // 学员订单表表格数据
                    studentOrderList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                                                orderNo: undefined,
                                                orderType: undefined,
                                                studentId: undefined,
                                                userId: undefined,
                                                payType: undefined,
                                                testEnrollNo: undefined,
                                                studyEnrollNo: undefined,
                                                trainApplyNo: undefined,
                                                orderAmount: undefined,
                                                payableAmount: undefined,
                                                columcouponId: undefined,
                                                couponAmount: undefined,
                                                status: undefined,
                                                remark: undefined,
                                                payTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                                wxSigninfo: undefined,
                                                aliSigninfo: undefined,
                                                aliOutRefundNo: undefined,
                                                wxOutRefundNo: undefined,
                                                createWxSigninfoTime: undefined,
                                                createAliSigninfoTime: undefined,
                                                discountAmount: undefined,
                                                discountProductId: undefined,
                                                couponUseId: undefined,
                                                operatorId: undefined,
                                                productId: undefined,
                                                newOrderNo: undefined,
                                                classTypeId: undefined,
                                    },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                orderNo: [
                            { required: true, message: "订单号不能为空", trigger: "blur" }
                        ],
                                                orderType: [
                            { required: true, message: "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）不能为空", trigger: "blur" }
                        ],
                                                studentId: [
                            { required: true, message: "下单客户(学员id)不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "客服ID不能为空", trigger: "blur" }
                        ],
                                                payType: [
                            { required: true, message: "支付类型(1-支付宝，2-微信)不能为空", trigger: "blur" }
                        ],
                                                testEnrollNo: [
                            { required: true, message: "考试报名单号不能为空", trigger: "blur" }
                        ],
                                                studyEnrollNo: [
                            { required: true, message: "学车报名单号不能为空", trigger: "blur" }
                        ],
                                                trainApplyNo: [
                            { required: true, message: "预约单号不能为空", trigger: "blur" }
                        ],
                                                orderAmount: [
                            { required: true, message: "订单金额不能为空", trigger: "blur" }
                        ],
                                                payableAmount: [
                            { required: true, message: "应付金额不能为空", trigger: "blur" }
                        ],
                                                columcouponId: [
                            { required: true, message: "优惠券ID不能为空", trigger: "blur" }
                        ],
                                                couponAmount: [
                            { required: true, message: "优惠券金额不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）不能为空", trigger: "blur" }
                        ],
                                                remark: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                payTime: [
                            { required: true, message: "支付时间（支付成功时间）不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除：0否；1：是不能为空", trigger: "blur" }
                        ],
                                                wxSigninfo: [
                            { required: true, message: "微信预支付订单信息不能为空", trigger: "blur" }
                        ],
                                                aliSigninfo: [
                            { required: true, message: "支付宝signInfo信息不能为空", trigger: "blur" }
                        ],
                                                aliOutRefundNo: [
                            { required: true, message: "支付宝退款请求号（用于退款查询）不能为空", trigger: "blur" }
                        ],
                                                wxOutRefundNo: [
                            { required: true, message: "微信退款请求号（用于退款查询）不能为空", trigger: "blur" }
                        ],
                                                createWxSigninfoTime: [
                            { required: true, message: "微信signInfo上一次创建时间（用于判断过期时间）不能为空", trigger: "blur" }
                        ],
                                                createAliSigninfoTime: [
                            { required: true, message: "支付宝signInfo上一次创建时间（用于判断过期时间）不能为空", trigger: "blur" }
                        ],
                                                discountAmount: [
                            { required: true, message: "打折金额不能为空", trigger: "blur" }
                        ],
                                                discountProductId: [
                            { required: true, message: "打折产品id不能为空", trigger: "blur" }
                        ],
                                                couponUseId: [
                            { required: true, message: "优惠卷领取表id不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                productId: [
                            { required: true, message: "对应的产品ID不能为空", trigger: "blur" }
                        ],
                                                newOrderNo: [
                            { required: true, message: "新订单号不能为空", trigger: "blur" }
                        ],
                                                classTypeId: [
                            { required: true, message: "订单类型明细id不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化分页数据
            this.getListPage();
        },
        methods: {
            /** 查询学员订单表列表 */
            getListPage() {
                this.loading = true;
                listPageStudentOrder(this.queryParams).then(response => {
                    this.studentOrderList = response.data.records;
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
                                                orderNo: undefined,
                                                orderType: undefined,
                                                studentId: undefined,
                                                userId: undefined,
                                                payType: undefined,
                                                testEnrollNo: undefined,
                                                studyEnrollNo: undefined,
                                                trainApplyNo: undefined,
                                                orderAmount: undefined,
                                                payableAmount: undefined,
                                                columcouponId: undefined,
                                                couponAmount: undefined,
                                                status: undefined,
                                                remark: undefined,
                                                payTime: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                                wxSigninfo: undefined,
                                                aliSigninfo: undefined,
                                                aliOutRefundNo: undefined,
                                                wxOutRefundNo: undefined,
                                                createWxSigninfoTime: undefined,
                                                createAliSigninfoTime: undefined,
                                                discountAmount: undefined,
                                                discountProductId: undefined,
                                                couponUseId: undefined,
                                                operatorId: undefined,
                                                productId: undefined,
                                                newOrderNo: undefined,
                                                classTypeId: undefined,
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
                this.resetForm("queryForm");
                this.handleQuery();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.orderNo)
                this.single = selection.length != 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加学员订单表";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const orderNo = row.orderNo || this.ids
                getStudentOrder(orderNo).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改学员订单表";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.orderNo != undefined) {
                            updateStudentOrder(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getListPage();
                                }
                            });
                        } else {
                            addStudentOrder(this.form).then(response => {
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
                const orderNos = row.orderNo || this.ids;
                this.$confirm('是否确认删除?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delStudentOrder(orderNos);
                }).then(() => {
                    this.getListPage();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有学员订单表数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return exportStudentOrder(queryParams);
                }).then(data => {
                    this.download(data, "学员订单表");
                }).catch(function() {});
            }
        }
    };
</script>
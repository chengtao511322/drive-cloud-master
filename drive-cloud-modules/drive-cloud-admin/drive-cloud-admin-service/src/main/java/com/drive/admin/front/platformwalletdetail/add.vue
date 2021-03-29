<template>
    <div class="app-container">

        <!-- 添加或修改教练钱包表明细对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="id" prop="id">
                                <el-input v-model="form.id" placeholder="请输入id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="钱包用户Id" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入钱包用户Id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="账务流水明细id/提现时为清算记录id" prop="accountDetailId">
                                <el-input v-model="form.accountDetailId" placeholder="请输入账务流水明细id/提现时为清算记录id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易金额" prop="tradeAmount">
                                <el-input v-model="form.tradeAmount" placeholder="请输入交易金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易类型（1-收益、2-支出）" prop="tradeType">
                                <el-input v-model="form.tradeType" placeholder="请输入交易类型（1-收益、2-支出）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="收入/支出名称" prop="walletDetailName">
                                <el-input v-model="form.walletDetailName" placeholder="请输入收入/支出名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易状态(0-失败,1-成功)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入交易状态(0-失败,1-成功)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易时间" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择交易时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="余额(进账，处长之前的余额)" prop="balance">
                                <el-input v-model="form.balance" placeholder="请输入余额(进账，处长之前的余额)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="运营商id(数据权限标记)" prop="operatorId">
                                <el-input v-model="form.operatorId" placeholder="请输入运营商id(数据权限标记)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" prop="tradeSubject">
                                <el-input v-model="form.tradeSubject" placeholder="请输入交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" prop="tradeSubjectItems">
                                <el-input v-model="form.tradeSubjectItems" placeholder="请输入交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="数据创建时间" prop="setUpDate">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.setUpDate"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择数据创建时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="publishData">{{
                    id ? "修改" : "发布"
                    }}</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
    </div>
</template>

<script>
    import { listPlatformWalletDetail,listPagePlatformWalletDetail, getPlatformWalletDetail, delPlatformWalletDetail, addPlatformWalletDetail, updatePlatformWalletDetail, exportPlatformWalletDetail } from "@/api/admin/platformWalletDetail";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "PlatformWalletDetail",
        data() {
            return {
                // 遮罩层
                loading: true,
                // 数据ID
                id: this.$route.query.id,
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                                                id: [
                            { required: true, message: "id不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "钱包用户Id不能为空", trigger: "blur" }
                        ],
                                                accountDetailId: [
                            { required: true, message: "账务流水明细id/提现时为清算记录id不能为空", trigger: "blur" }
                        ],
                                                tradeAmount: [
                            { required: true, message: "交易金额不能为空", trigger: "blur" }
                        ],
                                                tradeType: [
                            { required: true, message: "交易类型（1-收益、2-支出）不能为空", trigger: "blur" }
                        ],
                                                walletDetailName: [
                            { required: true, message: "收入/支出名称不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "交易状态(0-失败,1-成功)不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "交易时间不能为空", trigger: "blur" }
                        ],
                                                balance: [
                            { required: true, message: "余额(进账，处长之前的余额)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                                tradeSubject: [
                            { required: true, message: "交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)不能为空", trigger: "blur" }
                        ],
                                                tradeSubjectItems: [
                            { required: true, message: "交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)不能为空", trigger: "blur" }
                        ],
                                                setUpDate: [
                            { required: true, message: "数据创建时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getPlatformWalletDetailData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getPlatformWalletDetailData(){
                console.log('getBanner请求参数{}',this.id);
                getPlatformWalletDetail(this.id).then(response => {
                    console.log("getBanner请求参数列表：", response);
                    let subCode = response.subCode;
                    if (subCode == 'SUCCESS'){
                        this.form = response.data;
                    }
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
                                                userId: undefined,
                                                accountDetailId: undefined,
                                                tradeAmount: undefined,
                                                tradeType: undefined,
                                                walletDetailName: undefined,
                                                status: undefined,
                                                createTime: undefined,
                                                balance: undefined,
                                                operatorId: undefined,
                                                tradeSubject: undefined,
                                                tradeSubjectItems: undefined,
                                                setUpDate: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addPlatformWalletDetailData(this.form);
                        }else{
                            // 修改接口
                            this.updatePlatformWalletDetailData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addPlatformWalletDetailData(data){
                addPlatformWalletDetail(data).then(response => {
                    console.log("接口请求数据：", response);
                    let subCode = response.subCode;
                    if (subCode == 'SUCCESS'){
                        this.$message({
                            type: "success",
                            message: "数据添加成功!"
                        });
                        // 数据重置
                        this.reset();
                        // 返回页面
                        this.backPage();
                    }
                });
            },
            // 修改数据方法
            updatePlatformWalletDetailData(data){
                updatePlatformWalletDetail(data).then(response => {
                    console.log("接口请求数据：", response);
                    let subCode = response.subCode;
                    if (subCode == 'SUCCESS'){
                        this.$message({
                            type: "success",
                            message: "数据修改成功!"
                        });
                        // 数据重置
                        this.reset();
                        // 返回页面
                        this.backPage();
                    }
                });
            },
        }
    };
</script>
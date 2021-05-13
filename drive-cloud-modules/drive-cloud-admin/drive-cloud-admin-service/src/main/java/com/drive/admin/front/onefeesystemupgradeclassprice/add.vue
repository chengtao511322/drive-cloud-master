<template>
    <div class="app-container">

        <!-- 添加或修改一费制学员升班价格控制表对话框 -->
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
                            <el-form-item label="原班类型id" prop="originalClassId">
                                <el-input v-model="form.originalClassId" placeholder="请输入原班类型id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" prop="originalClassType">
                                <el-input v-model="form.originalClassType" placeholder="请输入原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="升级班类型id" prop="upgradeClassId">
                                <el-input v-model="form.upgradeClassId" placeholder="请输入升级班类型id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" prop="upgradeClassType">
                                <el-input v-model="form.upgradeClassType" placeholder="请输入升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="升班班型价格" prop="upgradePrice">
                                <el-input v-model="form.upgradePrice" placeholder="请输入升班班型价格" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="升班价格详情介绍" prop="detailsUrl">
                                <el-input v-model="form.detailsUrl" placeholder="请输入升班价格详情介绍" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否删除（0-否，1-是）" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入是否删除（0-否，1-是）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="创建时间" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择创建时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="更新时间" prop="updateTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.updateTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择更新时间">
                                </el-date-picker>
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
                            <el-form-item label="运营商提成金额" prop="operatorChangeMoney">
                                <el-input v-model="form.operatorChangeMoney" placeholder="请输入运营商提成金额" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台提成金额（所有上级运营商提成金）" prop="serviceChangeMoney">
                                <el-input v-model="form.serviceChangeMoney" placeholder="请输入平台提成金额（所有上级运营商提成金）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="状态(1-正常，2-停用)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态(1-正常，2-停用)" />
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
    import { listOneFeeSystemUpgradeClassPrice,listPageOneFeeSystemUpgradeClassPrice, getOneFeeSystemUpgradeClassPrice, delOneFeeSystemUpgradeClassPrice, addOneFeeSystemUpgradeClassPrice, updateOneFeeSystemUpgradeClassPrice, exportOneFeeSystemUpgradeClassPrice } from "@/api/admin/oneFeeSystemUpgradeClassPrice";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "OneFeeSystemUpgradeClassPrice",
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
                                                originalClassId: [
                            { required: true, message: "原班类型id不能为空", trigger: "blur" }
                        ],
                                                originalClassType: [
                            { required: true, message: "原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）不能为空", trigger: "blur" }
                        ],
                                                upgradeClassId: [
                            { required: true, message: "升级班类型id不能为空", trigger: "blur" }
                        ],
                                                upgradeClassType: [
                            { required: true, message: "升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）不能为空", trigger: "blur" }
                        ],
                                                upgradePrice: [
                            { required: true, message: "升班班型价格不能为空", trigger: "blur" }
                        ],
                                                detailsUrl: [
                            { required: true, message: "升班价格详情介绍不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除（0-否，1-是）不能为空", trigger: "blur" }
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
                                                operatorChangeMoney: [
                            { required: true, message: "运营商提成金额不能为空", trigger: "blur" }
                        ],
                                                serviceChangeMoney: [
                            { required: true, message: "平台提成金额（所有上级运营商提成金）不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(1-正常，2-停用)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getOneFeeSystemUpgradeClassPriceData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getOneFeeSystemUpgradeClassPriceData(){
                console.log('getBanner请求参数{}',this.id);
                getOneFeeSystemUpgradeClassPrice(this.id).then(response => {
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
                                                originalClassId: undefined,
                                                originalClassType: undefined,
                                                upgradeClassId: undefined,
                                                upgradeClassType: undefined,
                                                upgradePrice: undefined,
                                                detailsUrl: undefined,
                                                isDelete: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                operatorId: undefined,
                                                operatorChangeMoney: undefined,
                                                serviceChangeMoney: undefined,
                                                status: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addOneFeeSystemUpgradeClassPriceData(this.form);
                        }else{
                            // 修改接口
                            this.updateOneFeeSystemUpgradeClassPriceData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addOneFeeSystemUpgradeClassPriceData(data){
                addOneFeeSystemUpgradeClassPrice(data).then(response => {
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
            updateOneFeeSystemUpgradeClassPriceData(data){
                updateOneFeeSystemUpgradeClassPrice(data).then(response => {
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
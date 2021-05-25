<template>
    <div class="app-container">

        <!-- 添加或修改用户常用地址关联表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="主键" prop="id">
                                <el-input v-model="form.id" placeholder="请输入主键" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户id" prop="userId">
                                <el-input v-model="form.userId" placeholder="请输入用户id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="用户类型(1-学员 2-教练  3-客服 )" prop="userType">
                                <el-input v-model="form.userType" placeholder="请输入用户类型(1-学员 2-教练  3-客服 )" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="地址名称" prop="address">
                                <el-input v-model="form.address" placeholder="请输入地址名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="地址纬度" prop="latitude">
                                <el-input v-model="form.latitude" placeholder="请输入地址纬度" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="地址经度" prop="longitude">
                                <el-input v-model="form.longitude" placeholder="请输入地址经度" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否默认为地址（是，否）" prop="isDefault">
                                <el-input v-model="form.isDefault" placeholder="请输入是否默认为地址（是，否）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="状态(正常，停用)" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态(正常，停用)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="备注" prop="remarks">
                                <el-input v-model="form.remarks" placeholder="请输入备注" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="注册时间" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择注册时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                        
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="修改时间" prop="updateTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.updateTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择修改时间">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="场地ID" prop="siteId">
                                <el-input v-model="form.siteId" placeholder="请输入场地ID" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="科目类型" prop="subjectType">
                                <el-input v-model="form.subjectType" placeholder="请输入科目类型" />
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
    import { listUserCommonlyAddress,listPageUserCommonlyAddress, getUserCommonlyAddress, delUserCommonlyAddress, addUserCommonlyAddress, updateUserCommonlyAddress, exportUserCommonlyAddress } from "@/api/admin/userCommonlyAddress";
    import {Utils} from "@joyo-shared/utils";

    export default {
        name: "UserCommonlyAddress",
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
                            { required: true, message: "主键不能为空", trigger: "blur" }
                        ],
                                                userId: [
                            { required: true, message: "用户id不能为空", trigger: "blur" }
                        ],
                                                userType: [
                            { required: true, message: "用户类型(1-学员 2-教练  3-客服 )不能为空", trigger: "blur" }
                        ],
                                                address: [
                            { required: true, message: "地址名称不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "地址纬度不能为空", trigger: "blur" }
                        ],
                                                longitude: [
                            { required: true, message: "地址经度不能为空", trigger: "blur" }
                        ],
                                                isDefault: [
                            { required: true, message: "是否默认为地址（是，否）不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态(正常，停用)不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "备注不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "注册时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                siteId: [
                            { required: true, message: "场地ID不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getUserCommonlyAddressData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getUserCommonlyAddressData(){
                console.log('getBanner请求参数{}',this.id);
                getUserCommonlyAddress(this.id).then(response => {
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
                                                userType: undefined,
                                                address: undefined,
                                                latitude: undefined,
                                                longitude: undefined,
                                                isDefault: undefined,
                                                status: undefined,
                                                remarks: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                siteId: undefined,
                                                subjectType: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addUserCommonlyAddressData(this.form);
                        }else{
                            // 修改接口
                            this.updateUserCommonlyAddressData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addUserCommonlyAddressData(data){
                addUserCommonlyAddress(data).then(response => {
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
            updateUserCommonlyAddressData(data){
                updateUserCommonlyAddress(data).then(response => {
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
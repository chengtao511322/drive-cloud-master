<template>
    <div class="app-container">

        <!-- 添加或修改平台应用版本表对话框 -->
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                                                        <el-row>
                        <el-col :span="24">
                            <el-form-item label="" prop="id">
                                <el-input v-model="form.id" placeholder="请输入" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="应用类型（1：学员；2：教练，3-运维）" prop="appType">
                                <el-input v-model="form.appType" placeholder="请输入应用类型（1：学员；2：教练，3-运维）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="版本类型(ios,android)" prop="versionType">
                                <el-input v-model="form.versionType" placeholder="请输入版本类型(ios,android)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="版本名称" prop="versionName">
                                <el-input v-model="form.versionName" placeholder="请输入版本名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="版本号" prop="version">
                                <el-input v-model="form.version" placeholder="请输入版本号" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="是否强制更新" prop="isMustUpdate">
                                <el-input v-model="form.isMustUpdate" placeholder="请输入是否强制更新" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="下载地址" prop="downloadLocation">
                                <el-input v-model="form.downloadLocation" placeholder="请输入下载地址" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="平台密钥" prop="appKey">
                                <el-input v-model="form.appKey" placeholder="请输入平台密钥" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="版本说明" prop="versionExplain">
                                <el-input v-model="form.versionExplain" placeholder="请输入版本说明" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="下载次数" prop="downloadCount">
                                <el-input v-model="form.downloadCount" placeholder="请输入下载次数" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="创建者" prop="createUser">
                                <el-input v-model="form.createUser" placeholder="请输入创建者" />
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
                            <el-form-item label="更新者" prop="updateUser">
                                <el-input v-model="form.updateUser" placeholder="请输入更新者" />
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
    import { listAppVersion,listPageAppVersion, getAppVersion, delAppVersion, addAppVersion, updateAppVersion, exportAppVersion } from "@/api/admin/appVersion";
    import { Utils } from "@joyo-shared/utils";
    export default {
        name: "AppVersion",
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
                            { required: true, message: "不能为空", trigger: "blur" }
                        ],
                                                appType: [
                            { required: true, message: "应用类型（1：学员；2：教练，3-运维）不能为空", trigger: "blur" }
                        ],
                                                versionType: [
                            { required: true, message: "版本类型(ios,android)不能为空", trigger: "blur" }
                        ],
                                                versionName: [
                            { required: true, message: "版本名称不能为空", trigger: "blur" }
                        ],
                                                version: [
                            { required: true, message: "版本号不能为空", trigger: "blur" }
                        ],
                                                isMustUpdate: [
                            { required: true, message: "是否强制更新不能为空", trigger: "blur" }
                        ],
                                                downloadLocation: [
                            { required: true, message: "下载地址不能为空", trigger: "blur" }
                        ],
                                                appKey: [
                            { required: true, message: "平台密钥不能为空", trigger: "blur" }
                        ],
                                                versionExplain: [
                            { required: true, message: "版本说明不能为空", trigger: "blur" }
                        ],
                                                downloadCount: [
                            { required: true, message: "下载次数不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "更新者不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getAppVersionData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getAppVersionData(){
                console.log('getBanner请求参数{}',this.id);
                getAppVersion(this.id).then(response => {
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
                                                appType: undefined,
                                                versionType: undefined,
                                                versionName: undefined,
                                                version: undefined,
                                                isMustUpdate: undefined,
                                                downloadLocation: undefined,
                                                appKey: undefined,
                                                versionExplain: undefined,
                                                downloadCount: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateUser: undefined,
                                                updateTime: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addAppVersionData(this.form);
                        }else{
                            // 修改接口
                            this.updateAppVersionData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addAppVersionData(data){
                addAppVersion(data).then(response => {
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
            updateAppVersionData(data){
                updateAppVersion(data).then(response => {
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
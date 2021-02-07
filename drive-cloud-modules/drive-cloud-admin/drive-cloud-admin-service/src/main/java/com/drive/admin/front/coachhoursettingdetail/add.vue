<template>
    <div class="app-container">

        <!-- 添加或修改运营商教练课时设置表对话框 -->
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
                            <el-form-item label="时间段(1-上午;2-下午;3-晚上)" prop="timeSection">
                                <el-input v-model="form.timeSection" placeholder="请输入时间段(1-上午;2-下午;3-晚上)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="开始时间(只存时分，所以使用字符串)" prop="startTime">
                                <el-input v-model="form.startTime" placeholder="请输入开始时间(只存时分，所以使用字符串)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="结束时间(只存时分，所以使用字符串)" prop="endTime">
                                <el-input v-model="form.endTime" placeholder="请输入结束时间(只存时分，所以使用字符串)" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="运营商ID" prop="operatorId">
                                <el-input v-model="form.operatorId" placeholder="请输入运营商ID" />
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
                            <el-form-item label="创建时间  系统自动创建" prop="createTime">
                                <el-date-picker clearable size="small" style="width: 200px"
                                    v-model="form.createTime"
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择创建时间  系统自动创建">
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
                            <el-form-item label="是否删除" prop="isDelete">
                                <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
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
    import { listCoachHourSettingDetail,listPageCoachHourSettingDetail, getCoachHourSettingDetail, delCoachHourSettingDetail, addCoachHourSettingDetail, updateCoachHourSettingDetail, exportCoachHourSettingDetail } from "@/api/admin/coachHourSettingDetail";
    import { Utils } from "@joyo-shared/utils";
    export default {
        name: "CoachHourSettingDetail",
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
                                                timeSection: [
                            { required: true, message: "时间段(1-上午;2-下午;3-晚上)不能为空", trigger: "blur" }
                        ],
                                                startTime: [
                            { required: true, message: "开始时间(只存时分，所以使用字符串)不能为空", trigger: "blur" }
                        ],
                                                endTime: [
                            { required: true, message: "结束时间(只存时分，所以使用字符串)不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商ID不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间  系统自动创建不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "修改时间不能为空", trigger: "blur" }
                        ],
                                                isDelete: [
                            { required: true, message: "是否删除不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getCoachHourSettingDetailData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getCoachHourSettingDetailData(){
                console.log('getBanner请求参数{}',this.id);
                getCoachHourSettingDetail(this.id).then(response => {
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
                                                timeSection: undefined,
                                                startTime: undefined,
                                                endTime: undefined,
                                                operatorId: undefined,
                                                createUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                isDelete: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addCoachHourSettingDetailData(this.form);
                        }else{
                            // 修改接口
                            this.updateCoachHourSettingDetailData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addCoachHourSettingDetailData(data){
                addCoachHourSettingDetail(data).then(response => {
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
            updateCoachHourSettingDetailData(data){
                updateCoachHourSettingDetail(data).then(response => {
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
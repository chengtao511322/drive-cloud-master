<template>
    <div class="app-container">

        <!-- 添加或修改平台训练场地表对话框 -->
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
                            <el-form-item label="场地名称" prop="name">
                                <el-input v-model="form.name" placeholder="请输入场地名称" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="省" prop="provinceId">
                                <el-input v-model="form.provinceId" placeholder="请输入省" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="市" prop="cityId">
                                <el-input v-model="form.cityId" placeholder="请输入市" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="区" prop="areaId">
                                <el-input v-model="form.areaId" placeholder="请输入区" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="详细地址" prop="address">
                                <el-input v-model="form.address" placeholder="请输入详细地址" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="场地详情（图文）" prop="remarks">
                                <el-input v-model="form.remarks" placeholder="请输入场地详情（图文）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="图片介绍" prop="imageUrlList">
                                <el-input v-model="form.imageUrlList" placeholder="请输入图片介绍" />
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
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="场地类型（1-训练场地；2-考试场地）" prop="type">
                                <el-input v-model="form.type" placeholder="请输入场地类型（1-训练场地；2-考试场地）" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="经度" prop="longitude">
                                <el-input v-model="form.longitude" placeholder="请输入经度" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="纬度" prop="latitude">
                                <el-input v-model="form.latitude" placeholder="请输入纬度" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="场地费用/H" prop="price">
                                <el-input v-model="form.price" placeholder="请输入场地费用/H" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="驾校id" prop="driveSchoolId">
                                <el-input v-model="form.driveSchoolId" placeholder="请输入驾校id" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="驾校名称" prop="driveSchooName">
                                <el-input v-model="form.driveSchooName" placeholder="请输入驾校名称" />
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
                            <el-form-item label="更新着" prop="updateUser">
                                <el-input v-model="form.updateUser" placeholder="请输入更新着" />
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
                            <el-form-item label="考试车价格/H" prop="gridTestCarPrice">
                                <el-input v-model="form.gridTestCarPrice" placeholder="请输入考试车价格/H" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="联系电话" prop="phone">
                                <el-input v-model="form.phone" placeholder="请输入联系电话" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                                                                            <el-row>
                        <el-col :span="24">
                            <el-form-item label="状态（1-正常，2-停用）" prop="status">
                                <el-input v-model="form.status" placeholder="请输入状态（1-正常，2-停用）" />
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
    import { listCoachingGrid,listPageCoachingGrid, getCoachingGrid, delCoachingGrid, addCoachingGrid, updateCoachingGrid, exportCoachingGrid } from "@/api/admin/coachingGrid";
    import { Utils } from "@joyo-shared/utils";
    export default {
        name: "CoachingGrid",
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
                                                name: [
                            { required: true, message: "场地名称不能为空", trigger: "blur" }
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
                                                address: [
                            { required: true, message: "详细地址不能为空", trigger: "blur" }
                        ],
                                                remarks: [
                            { required: true, message: "场地详情（图文）不能为空", trigger: "blur" }
                        ],
                                                imageUrlList: [
                            { required: true, message: "图片介绍不能为空", trigger: "blur" }
                        ],
                                                subjectType: [
                            { required: true, message: "科目类型不能为空", trigger: "blur" }
                        ],
                                                type: [
                            { required: true, message: "场地类型（1-训练场地；2-考试场地）不能为空", trigger: "blur" }
                        ],
                                                longitude: [
                            { required: true, message: "经度不能为空", trigger: "blur" }
                        ],
                                                latitude: [
                            { required: true, message: "纬度不能为空", trigger: "blur" }
                        ],
                                                price: [
                            { required: true, message: "场地费用/H不能为空", trigger: "blur" }
                        ],
                                                driveSchoolId: [
                            { required: true, message: "驾校id不能为空", trigger: "blur" }
                        ],
                                                driveSchooName: [
                            { required: true, message: "驾校名称不能为空", trigger: "blur" }
                        ],
                                                createUser: [
                            { required: true, message: "创建者不能为空", trigger: "blur" }
                        ],
                                                updateUser: [
                            { required: true, message: "更新着不能为空", trigger: "blur" }
                        ],
                                                createTime: [
                            { required: true, message: "创建时间不能为空", trigger: "blur" }
                        ],
                                                updateTime: [
                            { required: true, message: "更新时间不能为空", trigger: "blur" }
                        ],
                                                gridTestCarPrice: [
                            { required: true, message: "考试车价格/H不能为空", trigger: "blur" }
                        ],
                                                phone: [
                            { required: true, message: "联系电话不能为空", trigger: "blur" }
                        ],
                                                status: [
                            { required: true, message: "状态（1-正常，2-停用）不能为空", trigger: "blur" }
                        ],
                                                operatorId: [
                            { required: true, message: "运营商id(数据权限标记)不能为空", trigger: "blur" }
                        ],
                                    }
            };
        },
        created() {
            // 初始化数据
            if (!Utils.isEmptyStr(this.id)) {
                this.getCoachingGridData();
            }
        },
        methods: {
            // 通过ID 查询数据
            getCoachingGridData(){
                console.log('getBanner请求参数{}',this.id);
                getCoachingGrid(this.id).then(response => {
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
                                                name: undefined,
                                                provinceId: undefined,
                                                cityId: undefined,
                                                areaId: undefined,
                                                address: undefined,
                                                remarks: undefined,
                                                imageUrlList: undefined,
                                                subjectType: undefined,
                                                type: undefined,
                                                longitude: undefined,
                                                latitude: undefined,
                                                price: undefined,
                                                driveSchoolId: undefined,
                                                driveSchooName: undefined,
                                                createUser: undefined,
                                                updateUser: undefined,
                                                createTime: undefined,
                                                updateTime: undefined,
                                                gridTestCarPrice: undefined,
                                                phone: undefined,
                                                status: undefined,
                                                operatorId: undefined,
                                    };
                this.resetForm("form");
            },

            /** 提交数据 */
            publishData: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (Utils.isEmptyStr(this.id)) {
                            // 添加接口
                            this.addCoachingGridData(this.form);
                        }else{
                            // 修改接口
                            this.updateCoachingGridData(this.form)
                        }
                    }
                });
            },
            // 添加数据方法
            addCoachingGridData(data){
                addCoachingGrid(data).then(response => {
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
            updateCoachingGridData(data){
                updateCoachingGrid(data).then(response => {
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
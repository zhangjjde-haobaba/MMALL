<template>
    <div style="margin-top: 60px;margin-left:80px;width: 600px" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

            <el-form-item label="商品编号" prop="id">
                <el-input v-model="ruleForm.id" readonly></el-input>
            </el-form-item>
            <el-form-item label="商品名称" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="商品描述" prop="description">
                <el-input v-model="ruleForm.description"></el-input>
            </el-form-item>
            <el-form-item
                    label="商品价格"
                    prop="price"
                    :rules="[
    { required: true, message: '价格不能为空'},
    { type: 'number', message: '价格必须为数字值'}
  ]"
            >
                <el-input v-model.number="ruleForm.price"></el-input>
            </el-form-item>
            <el-form-item
                    label="商品库存"
                    prop="stock"
                    :rules="[
    { required: true, message: '库存不能为空'},
    { type: 'number', message: '库存必须为数字值'}
  ]"
            >
                <el-input v-model.number="ruleForm.stock"></el-input>
            </el-form-item>
            <el-form-item label="一级分类" prop="categoryleveloneId">
                <el-select style="width:500px;" v-model="ruleForm.categoryleveloneId" @change="change1" placeholder="请选择">
                    <el-option
                            v-for="item in options1"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="二级分类" prop="categoryleveltwoId">
                <el-select style="width:500px;" v-model="ruleForm.categoryleveltwoId" @change="change2" placeholder="请选择">
                    <el-option
                            v-for="item in options2"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="三级分类" prop="categorylevelthreeId">
                <el-select style="width:500px;" v-model="ruleForm.categorylevelthreeId" placeholder="请选择">
                    <el-option
                            v-for="item in options3"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Update",
        data() {
            return {
                ruleForm: {
                    id: '',
                    name: '',
                    description: '',
                    price: '',
                    stock: '',
                    categoryleveloneId: '',
                    categoryleveltwoId: '',
                    categorylevelthreeId: ''
                },
                rules: {
                    id: [
                        { required: true, message: '商品编号不能为空', trigger: 'change' }
                    ],
                    name: [
                        { required: true, message: '商品名称不能为空', trigger: 'change' }
                    ],
                    description: [
                        { required: true, message: '商品描述不能为空', trigger: 'change' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    categoryleveloneId: [
                        { required: true, message: '一级分类不能为空', trigger: 'blur' }
                    ],
                    categoryleveltwoId: [
                        { required: true, message: '二级分类不能为空', trigger: 'blur' }
                    ],
                    categorylevelthreeId: [
                        { required: true, message: '三级分类不能为空', trigger: 'blur' }
                    ]
                },
                options1: '',
                options2: '',
                options3: ''
            };
        },
        created() {
            let _this = this
            axios.get('http://localhost:9292/product/findById/'+this.$route.query.id).then(function (resp) {
                console.log(resp.data)
                _this.ruleForm = resp.data
                _this.options1 = resp.data.levelOne
                _this.options2 = resp.data.levelTwo
                _this.options3 = resp.data.levelThree
            })
        },
        methods:{
            change1(){
                // alert(this.ruleForm.categoryleveloneId)
                let _this = this
                axios.get('http://localhost:9292/productCategory/children/2/'+this.ruleForm.categoryleveloneId).then(function (resp) {
                    _this.options2 = resp.data
                    //重新选择后 二级菜单不作数 赋空值
                    _this.ruleForm.categoryleveltwoId = ''
                    _this.options3 = ''
                    _this.ruleForm.categorylevelthreeId = ''
                })
            },
            change2(){
                let _this = this
                axios.get('http://localhost:9292/productCategory/children/3/'+this.ruleForm.categoryleveltwoId).then(function (resp) {
                    _this.options3 = resp.data
                    //重新选择后 二级菜单不作数 赋空值
                    _this.ruleForm.categorylevelthreeId = ''
                })
            },
            submitForm(formName) {
                let _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:9292/product/update',_this.ruleForm).then(function (resp) {
                            if(resp.data){
                                _this.$alert('【'+_this.ruleForm.name+'】修改成功', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/')
                                    }
                                });
                            }
                        })
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>
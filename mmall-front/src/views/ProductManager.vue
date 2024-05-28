<template>
    <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;" >

        <el-table
                :data="tableData"
                border
                stripe
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="编号"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="商品名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="商品描述"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="商品价格"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="stock"
                    label="商品库存"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="categoryOneName"
                    label="一级分类"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="categoryTwoName"
                    label="二级分类"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="categoryThreeName"
                    label="三级分类"
                    width="150">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 20px;float: right"
                       background
                       layout="prev, pager, next"
                       :page-size="pageSize"
                       :total="total"
                       :current-page.sync="currentPage"
                       @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "ProductManager",
        data(){
            return{
                tableData:'',
                pageSize:5,
                total:'',
                currentPage:1
            }
        },
        methods:{
            page(currentPage){
                console.log(currentPage)
                let _this = this
                axios.get('http://localhost:9292/product/list/'+currentPage+'/'+this.pageSize).then(function (resp) {
                    console.log(resp.data)
                    _this.tableData = resp.data.data
                    _this.total = resp.data.total
                })
            },
            edit(rows){
                this.$router.push('/update?id='+rows.id)
            },
            del(rows){
                console.log(rows)
                const _this = this
                this.$confirm('确认删除【'+rows.name+'】吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost:9292/product/delete/'+rows.id).then(function (resp) {
                        if(resp.data){
                            _this.$alert('【'+rows.name+'】已删除', '', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.reload()
                                }
                            });
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        },
        created() {
            let _this = this
            axios.get('http://localhost:9292/product/list/1/'+this.pageSize).then(function (resp) {
                console.log(resp.data)
                _this.tableData = resp.data.data
                _this.total = resp.data.total
            })
        }
    }
</script>

<style scoped>

</style>
<template>
    <div>
        <h1>导出数据</h1>
<!--        <el-row>-->
<!--            <el-button type="primary" @click="download">导出数据</el-button>-->
<!--        </el-row>-->
        <el-link type="primary" href="http://localhost:9292/download/exportdata" download="MMALL商品数据.xlsx">下载链接</el-link>
        <h1>管理员描述</h1>
        <el-link type="primary" href="http://localhost:9292/word/export" download="测试文档.docx">下载文档</el-link>
        <h1>商品pdf下载</h1>
        <el-link type="primary" href="http://localhost:9292/pdf/download" download="product.pdf">下载pdf</el-link>
    </div>
</template>

<script>
    export default {
        name: "Export",
        methods:{
            download(){
                //http://localhost:9292/download/exportdata
                // axios.get({
                //     url: 'http://localhost:9292/download/exportdata',
                //     method: 'get',
                //     data:{},
                //     responseType: 'blob'
                // }).then(res => {
                //     console.log(res);
                // });
                axios.get({
                    url:'http://localhost:9292/download/exportdata',
                    data:{},
                    responseType: 'blob'
                }).then(function (resp) {
                    console.log(resp)
                })
            }
        }
    }
</script>

<style scoped>

</style>
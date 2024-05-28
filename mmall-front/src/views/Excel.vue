<template>
    <div>
        <el-upload
                class="upload-demo"
                drag
                action="http://localhost:9292/product/import"
                multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传xlsx文件，且不超过500kb</div>
        </el-upload>
    </div>
</template>

<script>
    export default {
        name: "Excel"
    }
</script>

<style scoped>

</style>
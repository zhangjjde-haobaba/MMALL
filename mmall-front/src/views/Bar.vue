<template>
    <div id="myChart" style="width: 1000px;height: 500px;margin: 60px 60px"></div>
</template>

<script>
    export default {
        name: "Bar",
        mounted(){
            this.drawLine();
        },
        methods: {
            drawLine(){
                const _this = this

                    // 基于准备好的dom，初始化echarts实例
                    let myChart = _this.$echarts.init(document.getElementById('myChart'))
                    axios.get('http://localhost:9292/product/bar').then(function (resp) {
                        // 绘制图表
                        myChart.setOption({
                            xAxis: {
                                type: 'category',
                                data: resp.data.name
                            },
                            yAxis: {
                                type: 'value'
                            },
                            series: [
                                {
                                    data: resp.data.values,
                                    type: 'bar'
                                }
                            ]
                        });
                    })


            }
        }
    }
</script>

<style scoped>

</style>
var indexPage = new Vue({
    el: '#home',
    data() {
        return {
            choice: "welcome",
            viewLOs: [],
            viewLTs: [],
        }
    },
    mounted() {
        axios
            .get('http://localhost:8080/login/view')
            .then(response => {
                this.viewLOs = response.data.data.viewLOs;
                this.viewLTs = response.data.data.viewLTs;
                console.log(this.viewLOs);
                console.log(this.viewLTs);
            })
            .catch(function (error) { // 请求失败处理
                console.log("请求视图失败");
            });
    },
    methods: {
        getNULLCHOICE() {
            return "welcome";
        },
        getChoice(url) {
            this.choice = url;
        },
        // 改变链接,起到每次点一个连接地址都不一样的作用
        changePage(url) {
            this.viewLTs[1].some((item, i) => {
                if (item.urlCode == "210") {
                    item.systemsUrl = url + '?pageId=' + Math.ceil(Math.random() * 100);
                    this.getChoice(item.systemsUrl);
                    //在数组的some方法中，如果return true，就会立即终止这个数组的后续循环
                    return true
                }
            });
        },
    }
})
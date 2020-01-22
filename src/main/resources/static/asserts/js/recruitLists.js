new Vue({
    el: '#recruit_list',
    data() {
        return {
            msg: '',
            recruit: this.getNULLRECRUIT(),
            recruits: [],
            totalRecruits: 0,
            allowAddBtn: true,
            resume: this.getNULLFORMWORK(),
        }
    },
    mounted() {
        //请求全部第0页职位
        axios
            .get('http://localhost:8080/putList')
            .then(response => {
                this.recruits = response.data.data.recruitLists;
                console.log(this.recruits);
                this.totalRecruits = this.recruits.length;
            })
            .catch(function (error) { // 请求失败处理
                console.log("请求服务器错误");
            });
    },
    methods: {
        getNULLRECRUIT() {
            return {
                "id": "",
                "jobKey": "",
                "jobDescribe": "",
                "requir": "",
                "publisherId": "",
                "resumes": []
            }
        },
        closeAddForm() {
            document.getElementById("addForm").reset();
        },
        addRecruit() {
            this.recruit = this.getNULLRECRUIT();

        },
        addComf() {
            axios
                .post('http://localhost:8080/putList', {
                    "id": "",
                    "jobKey": this.recruit.jobKey,
                    "jobDescribe": this.recruit.jobDescribe,
                    "requir": this.recruit.jobDescribe,
                    "publisherId": "",
                    "resumes": []

                })
                .then(response => {
                    if (response.data.data.uuid != null) {
                        console.log(response.data.data);
                        document.getElementById("addForm").reset();
                        this.msg = "发布成功"
                        this.recruit.id = response.data.data.uuid;
                        this.recruit.publisherId = response.data.data.publisherId;
                        this.recruits.push(this.recruit);
                        this.totalRecruits = this.totalRecruits + 1;
                        this.recruit = this.getNULLRECRUIT();
                    } else {
                        this.msg = "服务器未能成功向数据库添加数据"
                        this.recruit = this.getNULLRECRUIT();
                    }
                })
                .catch(function (error) { // 请求失败处理
                    console.log("请求服务器错误");
                });
        },
        getDetails(recruit) {
            this.recruit = recruit;
        },
        closeModel() {
            this.recruit = this.getNULLRECRUIT();
        },
        delRecruit(id) {
            axios
                .delete('http://localhost:8080/putList?id=' + id, {
                    data: {}
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        //删除本地emps该员工
                        this.recruits.some((item, i) => {
                            if (item.id == id) {
                                this.recruits.splice(i, 1);
                                this.totalRecruits = this.totalRecruits - 1;
                                //在数组的some方法中，如果return true，就会立即终止这个数组的后续循环
                                return true
                            }
                        });
                        this.msg = "删除成功";
                    } else {
                        this.msg = "服务器未能成功删除，请重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.msg = "删除失败，请刷新页面重试";
                    return;
                });
        },
        putFormwork() {
            //请求出我的模板
            axios
                .get('http://localhost:8080/formwork')
                .then(response => {
                    if ("1" == response.data.data.msg) {
                        this.resume = response.data.data.formwork;
                        this.resume["id"] = "";//formwork中没有id属性
                        console.log(this.resume);
                        //请求投递公共
                        axios
                            .post('http://localhost:8080/putList/addResume?recruitId='
                                + this.recruit.id, this.resume)
                            .then(response => {
                                if ("1" == response.data.data.msg) {
                                    this.msg = "投递简历成功";
                                } else {
                                    this.msg = "投递简历失败";
                                }
                            })
                            .catch(function (error) { // 请求失败处理
                                console.log("请求服务器错误");
                                return;
                            });
                    }
                })
                .catch(function (error) { // 请求失败处理
                    console.log("请求服务器错误");
                    return;
                });
        },
        getNULLFORMWORK() {
            return {
                "name": "",
                "age": "",
                "howToContact": "",
                "workingYears": "",
                "jobSummary": "",
                "educationExperience": "",
                "projectExperience": "",
                "deliverId": "",
            }
        },
        getResumes(id) {
            //让服务器注册session
            axios
                .get('http://localhost:8080/putList/serchResumes?recruitId=' + id)
                .catch(function (error) { // 请求失败处理
                    console.log("请求服务器错误");
                });
            //改变链接
            parent.indexPage.changePage("oneRecuitsResume");
        },
    }
})
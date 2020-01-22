new Vue({
    el: '#resume_list',
    data() {
        return {
            msg: '',
            resume: this.getNULLRESUME(),
            resumes: [],
            totalResumes: 0,
        }
    },
    mounted() {
        //请求简历列表
        axios
            .get('http://localhost:8080/resume')
            .then(response => {
                this.resumes = response.data.data.resumes;
                console.log(this.resumes);
                this.totalResumes = this.resumes.length;
            })
            .catch(function (error) { // 请求失败处理
                console.log("请求服务器错误");
            });
    },
    methods: {
        getNULLRESUME() {
            return {
                "id": "",
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
        getDetails(resume) {
            this.resume = resume;
        },
        closeModel() {
            this.resume = this.getNULLRESUME;
        },
        delResume(id) {
            axios
                .delete('http://localhost:8080/resume?id=' + id, {
                    data: {}
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        this.resumes.some((item, i) => {
                            if (item.id == id) {
                                this.resumes.splice(i, 1);
                                this.totalResumes = this.totalResumes - 1;
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
        getResumes(id) {

        },
    }
})
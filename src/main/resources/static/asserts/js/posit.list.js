/**
 * @author black
 * @date 2019/12/26 - 17:44
 */

/**
 对应pojo字段
 *private String id;
 *private String positionName;
 *private int salary;
 **/

new Vue({
    el: '#posit_list',
    data() {
        return {
            msg: '',
            posit: this.getNULLPOSIT(),
            posits: [],
            totalPosits: 0,
            outListing: false,
            edting: false,
            adding: false,
            allowAddBtn: true,
            /*分页不做了*/
            /*totalPage: 0,
            getPage: 0,*/
        }
    },
    mounted() {
        //请求全部第0页职位
        axios
            .get('http://localhost:8080/position')
            .then(response => {
                this.posits = response.data.data.posits;

                this.totalPosits = this.posits.length;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    },
    methods: {
        getNULLPOSIT() {
            return {
                id: '',
                positionName: '',
                salary: 0,
            };
        },

        /*编辑或者添加职位时输入合法性检查*/
        addOrUpdateCheck() {
            if (15 < this.posit.positionName.length) {
                this.msg = '职位名最长为15个字';
                return false
            }
            if (0 == this.posit.positionName.length) {
                this.msg = '请输入职位名';
                return false
            }
            if (0 == this.posit.salary.length) {
                this.msg = '请输入工资';
                return false
            }
            if (!(0 <= parseInt(this.posit.salary) && 1000000 >= parseInt(this.posit.salary))) {
                this.msg = '工资输入字非法（最长为6位）';
                return false
            }
            return true;
        },

        /*删除职位*/
        delPosit: function (id) {
            axios
                .delete('http://localhost:8080/position?id=' + id, {
                    data: {}
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        //删除本地emps该员工
                        this.posits.some((item, i) => {
                            if (item.id == id) {
                                this.posits.splice(i, 1);
                                this.totalPosits = this.totalPosits - 1;
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

        /*编辑职位*/
        edtPosit: function (posit) {
            //编辑对象时emp才不为空
            this.posit = posit;
            this.outListing = true;
            this.edting = true;
            this.allowAddBtn = false;
            this.msg = "正在编辑";
        },
        /*编辑成功，确认上传*/
        edtComf: function () {
            //这里进行输入框语法检查
            if (!this.addOrUpdateCheck()) {
                return false;
            }
            axios
                .put('http://localhost:8080/position', {
                    id: this.posit.id,
                    positionName: this.posit.positionName,
                    salary: this.posit.salary,
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        this.posit = this.getNULLPOSIT();
                        this.outListing = false;
                        this.edting = false;
                        this.allowAddBtn = true;
                        this.msg = "修改成功";
                    } else {
                        this.posit = this.getNULLPOSIT();
                        this.outListing = false;
                        this.edting = false;
                        this.allowAddBtn = true;
                        this.msg = "服务器数据库未能成功添加数据，请刷新页面重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.posit = this.getNULLPOSIT();
                    this.outListing = false;
                    this.edting = false;
                    this.allowAddBtn = true;
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },

        //添加职位
        addGiveUp() {
            this.posit = this.getNULLPOSIT();
            this.outListing = false;
            this.adding = false;
            this.allowAddBtn = true;
            this.msg = "取消添加";
        },
        addPosit: function () {
            this.posit = this.getNULLPOSIT();
            this.outListing = true;
            this.adding = true;
            this.allowAddBtn = false;
            this.msg = "正在添加";
        },
        //确认添加
        addComf: function () {
            if (!this.addOrUpdateCheck()) {
                return false;
            }
            axios
                .post('http://localhost:8080/position', {
                    id: this.posit.id,
                    positionName: this.posit.positionName,
                    salary: this.posit.salary,
                })
                .then(response => {
                    if (response.data.data.uuid != null) {
                        this.posit.id = response.data.data.uuid;
                        this.posits.push(this.posit);
                        this.posit = this.getNULLPOSIT();
                        this.outListing = false;
                        this.adding = false;
                        this.allowAddBtn = true;
                        this.totalPosits = this.totalPosits + 1;
                        this.msg = "添加成功";
                    } else {
                        this.posit = this.getNULLPOSIT();
                        this.outListing = false;
                        this.adding = false;
                        this.allowAddBtn = true;
                        this.msg = "服务器数据库未能成功添加数据，请刷新页面重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.posit = this.getNULLPOSIT();
                    this.outListing = false;
                    this.adding = false;
                    this.allowAddBtn = true;
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },

    }
})
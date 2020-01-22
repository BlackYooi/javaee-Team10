/**
 * @author black
 * @date 2019/12/23 - 22:26
 */

/**
 对应pojo字段
 *private String id;
 *private String departmentName;
 *private String leader;
 **/

new Vue({
    el: '#depart_list',
    data() {
        return {
            msg: '',
            depart: this.getNULLDEPART(),
            departs: [],
            totalDeparts: 0,
            outListing: false,
            edting: false,
            adding: false,
            allowAddBtn: true,
            totalPage: 0,
            getPage: 0,
        }
    },
    mounted() {
        //请求全部第0页部门
        axios
            .get('http://localhost:8080/department?getPage=0')
            .then(response => {
                this.departs = response.data.data.departs;
                this.totalDeparts = response.data.data.totalDeparts;
                this.totalPage = response.data.data.totalPage;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    },
    methods: {
        getNULLDEPART() {
            return {
                id: '',
                departmentName: '',
                leader: ''
            };
        },

        /*编辑或者添加部门时输入合法性检查*/
        addOrUpdateCheck() {
            if (15 < this.depart.departmentName.length) {
                this.msg = '部门名最长为15个字';
                return false
            }
            if (0 == this.depart.departmentName.length) {
                this.msg = '请输入部门名';
                return false
            }
            if (15 < this.depart.leader.length) {
                this.msg = '领导名最长为15个字';
                return false
            }
            if (0 == this.depart.leader.length) {
                this.msg = '请输入领导名';
                return false
            }
            return true;
        },

        /*删除部门*/
        delDepart: function (id) {
            axios
                .delete('http://localhost:8080/department?id=' + id, {
                    data: {}
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        //删除本地emps该员工
                        this.departs.some((item, i) => {
                            if (item.id == id) {
                                this.departs.splice(i, 1);
                                this.totalDeparts = this.totalDeparts - 1;
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

        /*编辑部门*/
        edtDepart: function (depart) {
            //编辑对象时emp才不为空
            this.depart = depart;
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
                .put('http://localhost:8080/department', {
                    id: this.depart.id,
                    departmentName: this.depart.departmentName,
                    leader: this.depart.leader,
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        this.depart = this.getNULLDEPART();
                        this.outListing = false;
                        this.edting = false;
                        this.allowAddBtn = true;
                        this.msg = "修改成功";
                    } else {
                        this.depart = this.getNULLDEPART();
                        this.outListing = false;
                        this.edting = false;
                        this.allowAddBtn = true;
                        this.msg = "服务器数据库未能成功添加数据，请刷新页面重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.depart = this.getNULLDEPART();
                    this.outListing = false;
                    this.edting = false;
                    this.allowAddBtn = true;
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },

        //添加部门
        addGiveUp() {
            this.depart = this.getNULLDEPART();
            this.outListing = false;
            this.adding = false;
            this.allowAddBtn = true;
            this.msg = "取消添加";
        },
        addDepart: function () {
            this.depart = this.getNULLDEPART();
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
                .post('http://localhost:8080/department', {
                    id: this.depart.id,
                    departmentName: this.depart.departmentName,
                    leader: this.depart.leader,
                })
                .then(response => {
                    if (response.data.data.uuid != null) {
                        this.depart.id = response.data.data.uuid;
                        this.departs.push(this.depart);
                        this.depart = this.getNULLDEPART();
                        this.outListing = false;
                        this.adding = false;
                        this.allowAddBtn = true;
                        this.totalDeparts = this.totalDeparts + 1;
                        this.msg = "添加成功";
                    } else {
                        this.depart = this.getNULLDEPART();
                        this.outListing = false;
                        this.adding = false;
                        this.allowAddBtn = true;
                        this.msg = "服务器数据库未能成功添加数据，请刷新页面重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.depart = this.getNULLDEPART();
                    this.outListing = false;
                    this.adding = false;
                    this.allowAddBtn = true;
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },

    }
})
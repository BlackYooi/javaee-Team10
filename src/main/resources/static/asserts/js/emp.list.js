new Vue({
    el: '#emp_list',
    data() {
        return {
            msg: '',
            emps: [],
            totalEmps: 0,
            departs: [],
            posits: [],
            emp: {
                id: '',
                name: '',
                age: '',
                sex: '',
                departmentId: '',
                positionId: ''
            },
            edtOrAddEmp:'',
            getPage: 0,
            totalPage: 0,
        }
    },
    mounted() {
        //请求全部->第0页员工
        axios
            .get('http://localhost:8080/employee?getPage=0')
            .then(response => {
                this.emps = response.data.data.emps;
                this.totalEmps = response.data.data.totalEmps;
                this.totalPage = response.data.data.totalPage;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        //请求全部->部门
        axios
            .get('http://localhost:8080/department/all')
            .then(response => {
                (this.departs = response.data.data.departs);
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
        //请求全部->职位
        axios
            .get('http://localhost:8080/position')
            .then(response => {
                (this.posits = response.data.data.posits);
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    },
    methods: {

        created() {

        },

        /*删除员工*/
        delEmp: function (id) {
            axios
                .delete('http://localhost:8080/employee?id=' + id, {
                    data: {}
                })
                .then(response => {
                    if (response.data.data.msg == "1") {
                        //删除本地emps该员工
                        this.emps.some((item, i) => {
                            if (item.id == id) {
                                this.emps.splice(i, 1);
                                this.totalEmps = this.totalEmps - 1;
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

        /*封装了员工的添加和编辑,"edt"是编辑，"add"是添加*/
        edtOrAdd(edtOrAdd,emp){
            if("edt" === edtOrAdd){
                this.edtOrAddEmp = 'edt';
                this.edtEmp(emp);
            }
            else if("add" === edtOrAdd){
                this.edtOrAddEmp = 'add';
                this.addEmp();
            }
            else if("findEmp" === edtOrAdd){
                this.edtOrAddEmp = 'findEmp';
                this.findEmp();
            }
        },
        /*编辑员工*/
        edtEmp: function (emp) {
            /*编辑对象时emp才不为空*/
            this.emp = emp;
            this.msg = "正在编辑";
        },
        /*添加员工*/
        addEmp: function () {
            this.emp = this.getNULLEMP();
            this.msg = "正在添加";
        },
        /*查找员工*/
        findEmp(){
            this.emp = this.getNULLEMP();
            this.msg = "正在编辑查找信息";
        },

        /*封装了提交按钮->老规矩*/
        edtOrAddComf(){
            if("edt" === this.edtOrAddEmp){
                this.edtComf();
                //this.edtOrAddEmp = '';
            }
            else if("add" === this.edtOrAddEmp){
                this.addComf();
                //this.edtOrAddEmp = '';
            }
            else if("findEmp" === this.edtOrAddEmp){
                this.findEmpComf();
                //this.edtOrAddEmp = '';
            }
        },
        /*编辑成功，确认上传*/
        edtComf: function () {
            if (!this.addOrUpdateCheck()) {
                return false;
            }
            axios
                .put('http://localhost:8080/employee', this.emp)
                .then(response => {
                    if (response.data.data.msg == "1") {
                        this.emp = this.getNULLEMP();
                        this.msg = "修改成功";
                        $('#edtOrAddModal').modal('hide');
                        $(".modal-backdrop.fade").hide();
                    } else {
                        this.emp = this.getNULLEMP();
                        this.msg = "服务器数据库未能成功添加数据，请刷新页面重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.emp = this.getNULLEMP();
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },

        /*确认添加*/
        addComf: function () {
            if (!this.addOrUpdateCheck()) {
                return false;
            }
            axios
                .post('http://localhost:8080/employee', this.emp)
                .then(response => {
                    if (response.data.data.uuid != null) {
                        this.emp.id = response.data.data.uuid;
                        this.emps.push(this.emp);
                        this.emp = this.getNULLEMP();
                        this.totalEmps = this.totalEmps + 1;
                        this.msg = "添加成功";
                        $('#edtOrAddModal').modal('hide');
                        $(".modal-backdrop.fade").hide();
                        /*location.reload();*/
                    } else {
                        this.emp = this.getNULLEMP();
                        this.msg = "服务器数据库未能成功添加数据，请刷新页面重试";
                    }
                })
                .catch(function (error) { // 请求失败处理
                    this.emp = this.getNULLEMP();
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },
        /*确认查找*/
        findEmpComf: function () {
            axios
                .post('http://localhost:8080/employee/lookup', this.emp)
                .then(response => {
                        this.emps = response.data.data.emps;
                        this.emp = this.getNULLEMP();
                        this.totalEmps = this.emps.length;
                        this.totalPage = 1;
                        this.msg = "查出下列员工";
                        $('#edtOrAddModal').modal('hide');
                        $(".modal-backdrop.fade").hide();
                        /*location.reload();*/
                })
                .catch(function (error) { // 请求失败处理
                    this.emp = this.getNULLEMP();
                    this.msg = "上传服务器失败，请刷新页面重试";
                    return;
                });
        },

        giveUp() {
            this.emp = this.getNULLEMP();
            this.msg = "取消操作";
            return;
        },

        addOrUpdateCheck() {
            var nameRegExp = new RegExp(/^([\u4E00-\u9FA5A-Za-z ]){1,15}$/g);
            if ((-1) == this.emp.name.search(nameRegExp)) {
                this.msg = "名字只能含字母、汉字、空格 1-15位";
                return false;
            }
            var ageRegExp = new RegExp(/^([1-9][0-9]?)$/);
            if ((-1) == this.emp.age.toString().search(ageRegExp)) {
                this.msg = "年龄填写不规范(1-99)";
                return false;
            }
            if (1 != this.emp.sex.length) {
                this.msg = "请选择性别";
                return false;
            }
            if (32 > this.emp.departmentId.length) {
                this.msg = "请选择部门";
                return false;
            }
            if (32 > this.emp.positionId.length) {
                this.msg = "请选择职位";
                return false;
            }
            this.edtOrAddEmp = '';
            return true;
        },

        clickLeftPage() {
            this.getPage = this.getPage - 1;
            if (0 > this.getPage) {
                this.getPage = 0;
                return false;
            }
            this.getNextPage();

        },

        clickRightPage() {
            if (this.getPage < (this.totalPage - 1)) {
                this.getPage = this.getPage + 1;
            } else {
                return false;
            }
            this.getNextPage();
        },

        getNextPage() {
            //请求全部第getpage页员工
            axios
                .get('http://localhost:8080/employee?getPage=' + this.getPage)
                .then(response => {
                    this.emps = response.data.data.emps;
                    this.totalEmps = response.data.data.totalEmps;
                    this.totalPage = response.data.data.totalPage;
                })
                .catch(function (error) { // 请求失败处理
                    console.log(error);
                });
        },

        /*通过部门ID得到部门名*/
        getDepartName(emp) {
            var id = emp.departmentId;
            for (j = 0; j < this.departs.length; j++) {
                if (this.departs[j].id == id) {
                    return this.departs[j].departmentName;
                }
            }
            return "未分配";
        },

        /*通过职位id得到职位*/
        getPositName(emp) {
            var id = emp.positionId;
            for (j = 0; j < this.posits.length; j++) {
                if (this.posits[j].id == id)
                    return this.posits[j].positionName;
            }
            return "未分配";
        },

        /*重载页面*/
        reloadPage(){
            location.reload();
        },

        /*获取空的emp*/
        getNULLEMP() {
            var NULLEMP = {
                id: '',
                name: '',
                age: '',
                sex: '',
                departmentId: '',
                positionId: ''
            };
            return NULLEMP;
        }
    }
})
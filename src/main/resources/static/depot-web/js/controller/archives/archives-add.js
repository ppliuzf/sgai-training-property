$(function () {
    var infoId = $.getQueryString('infoId'),
    orgList = [],
    selectList = [];
    function init() {
        getContent();
        changeTitle();
        $(document).on('click', '#save', function(e){
            submit(infoId);
        }); 
        $(document).on('click', '.js-back', function(e){
            history.go(-1);
        });

        // 输入gis转化逗号
        $(document).on('change', '#whGIS', function () {
            $('#whGIS').val($('#whGIS').val().replace(/，/ig,','));
        });
    }
    init();
// 获取列表
function getContent() {
    // 如果是二次编辑的数据
    if(infoId){
        $.getData({
            url: 'depotManage/depotDetil',
            query: {
                id: infoId
            }
        }, function(data) {
            if (data) {
                    var orgName = data.whDept?data.whDept.split('/'):[];
                    selectList = data.whDept?JSON.parse(data.whDept):[];
                    console.log('113131313131313', data.whDept);
                    // 引入模板
                    renderCont(data);
                    getOrg(selectList);
                    $.counter({
                        el: '#whDesc'
                    });
                    // $.getData({
                    //     url: 'common/spaceTree',
                    //     query: {
                    //         sgaiToken: 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MTY3ODY4ODYsInN1YiI6IntcImNvbU5hbWVcIjpcIuS8gemAmlwiLFwidXNlclR5cGVcIjpcImFkbWluXCIsXCJjb21Db2RlXCI6XCIxMzAzMzdcIixcInVzZXJOYW1lXCI6XCLnrqHnkIblkZhcIixcInVzZXJJZFwiOlwiMTExMTIzXCJ9IiwiZXhwIjoxNTE2NzkwNDg2fQ.WLrveFrgASB9XLM9cqXi92DEvC1YFYzh3CuxfX71_DM'
                    //     }
                    // },function(data) {
                    //     if (data) {
                    //         orgList = data;
                    //         for(var i=0 ; i < orgName.length; i++){ 
                    //             for(var j=0 ; j < orgList.length; j++){
                    //                 if(orgName[i] == orgList[j].name){
                    //                     orgList[j].isChecked = "true";
                    //                 }
                    //             }                 
                    //         }
                    //     }
                    // });
                    // for(var i=0 ; i < orgName.length; i++){ 
                    //     for(var j=0 ; j < orgList.length; j++){
                    //         if(orgName[i] == orgList[j].name){
                    //             orgList[j].isChecked = "true";
                    //         }
                    //     }                 
                    // }

            }
        
        });
    }  else{
        // 引入模板
        renderCont({});
        $.counter({
            el: '#whDesc'
        });
        getOrg();
    }
}       

   
    
    function renderCont(data) {
        $('.stepOne').html(template('add/archives-add', {
            items: data
        }));
       
    }
    // 获取组织列表数据
    function getOrg(list){
        var arr = new Array();
        console.log(list,111);
        $.getData({
            url: 'common/spaceTree',
            // url: '/remote/getSpaceList',
            query: {
                sgaiToken: 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MTY3ODY4ODYsInN1YiI6IntcImNvbU5hbWVcIjpcIuS8gemAmlwiLFwidXNlclR5cGVcIjpcImFkbWluXCIsXCJjb21Db2RlXCI6XCIxMzAzMzdcIixcInVzZXJOYW1lXCI6XCLnrqHnkIblkZhcIixcInVzZXJJZFwiOlwiMTExMTIzXCJ9IiwiZXhwIjoxNTE2NzkwNDg2fQ.WLrveFrgASB9XLM9cqXi92DEvC1YFYzh3CuxfX71_DM'
            }
        },function(data) {
            console.log('总数据');
            console.log(data);
            if (data) {
                orgList = data;
                for(var i=0; i<data.length; i++){
                    var str = {};
                    if (list && list.length>0){
                        console.log('有选中数据');
                        for (j = 0; j<list.length; j++){
                            if (list[j].value == data[i].value && list[j].checked == true){
                                str = {"id":""+data[i].value+"","parentId":""+data[i].parent+"","checked":true,"name":""+data[i].name+"","children":"","state":"closed"};
                                break;
                            } else {
                                str = {"id":""+data[i].value+"","parentId":""+data[i].parent+"","checked":false,"name":""+data[i].name+"","children":"","state":"closed"};
                            }
                        }
                    } else {
                        str = {"id":""+data[i].value+"","parentId":""+data[i].parent+"","checked":false,"name":""+data[i].name+"","children":"","state":"closed"};
                    }
                    
                    arr.push(str);
                }
                renderOrgList(arr)
            }
        });
    }
       
     // 渲染空间数据组织树
     function renderOrgList(arr) {
        console.log(arr);
        $('#tt').tree({
            data: arr,
            "state":"closed",
            idField:'passId',
            loadFilter: function(rows){
                return $.convert(rows);
            },
            onLoadSuccess:function (node,data){
                $('#tt').tree('collapseAll');
                $('#tt').attr('data-options', "method:'get',animate:true,checkbox:true");
            }
        });
        
    }
    // 处理组织数据 
    function collectSelectedOrg(){
        var orgName = [];
        var arr =$('#orgName').val()?$('#orgName').val().split('/'):[];
        $('.js-org-list input').each(function (i) {
            var $this = $(this);
            
            if ($this.is(':checked') && $this.attr('data-isChecked') == "false") {
                orgName.push( $this.attr('data-name') );
                orgList[i].isChecked = true;
                
            } else if(!$this.is(':checked') && $this.attr('data-isChecked') == "true"){
                orgList[i].isChecked = false;

                for (var j=0 ; j < arr.length ; j++){
                    if(arr[j] == $this.attr('data-name')) {
                        arr.splice(j,1)
                    }
                }    
            }
        });
        console.log(arr)
        $('#orgName').val(arr.join('/'))
        console.log(orgName)
        orgName = collectOrgVos().concat(orgName);
        return orgName;
    }
     // 收集组织数据
     function collectOrgVos() {
        var orgName = [];
         if($('#orgName').val()){
            var orgName = $('#orgName').val().split('/')
         }
        
        return orgName;
    }
    // 根据url参数修改页面标题
    function changeTitle(){
        if(infoId){
            $(".js-title h4").html("编辑仓库");
        } else{
            $(".js-title h4").html("新建仓库");
        }
    }
    // 收集新建仓库数据
    function getData() {
        var param = {};
        param.whName = $("#whName").val(); // 仓库名称
        param.whType = $("#whType").val(); // 仓库类型
        param.whAddress = $("#whAddress").val(); // 仓库地址
        if (param.whName == '') {
            $.toast('请输入仓库名称',{timer:2000});
            return false;
        }
        if (param.whType == '') {
            $.toast('请选择仓库类型', {timer:2000});
            return false;
        }
        var gis = $("#whGIS").val()
        param.whDesc = $("#whDesc").val()
        // param.whDept = JSON.stringify($('#tt').tree('getChecked', ['checked','indeterminate']));
        var arr = [];
        var nodes = $('#tt').tree('getChecked',['checked','indeterminate']);
        for (var i = 0; i < nodes.length; i++){
            arr[i] = {};
            arr[i].value = nodes[i].id;
            arr[i].name = nodes[i].text;
            arr[i].checked = nodes[i].checked;
            if($('#tt').tree('getParent', nodes[i].target)) arr[i].parent = $('#tt').tree('getParent', nodes[i].target).id;
        }
        param.whDept = JSON.stringify(arr);

        
        if(gis){
            var reg = /^-?((0|1?[0-7]?[0-9]?)(([.][0-9]{1,4})?)|180(([.][0]{1,4})?))[\,,，]-?((0|[1-8]?[0-9]?)(([.][0-9]{1,4})?)|90(([.][0]{1,4})?))$/;
            if (!reg.test(gis)){
                $.toast('请输入正确的GIS,例如"100.52,50.52"', {timer:3000});
                return false;
            }
            else{
                param.whLatitude = gis.split(',')[0]; // 仓库经度
                param.whLongitude = gis.split(',')[1]; // 仓库纬度
            }
        }
        return param;
    }
    // 点击保存
    function submit(infoId) {
        var param = getData();
        console.log(param)
        if (param === false) {
            return false;
        } else {
            if(infoId){
                param.id = infoId;
                $.getData({
                    url: 'depotManage/addOrUpdate',
                    query: {},
                    body: param
                }, function (data) {
                    if (data) {
                            window.history.go(-1);
                            // console.log(data);
                    }
                });
            }
            else{
                $.getData({
                    url: 'depotManage/addOrUpdate',
                    query: {},
                    body: param
                }, function (data) {
                    if (data) {
                            window.history.go(-1);
                            // console.log(data);
                    }
                });
            }
            
        }
    }
});


$(function () {
    var infoId = $.getQueryString('infoId');
    function init() {
        getContent();
        // 点击返回
        $(document).on('click', '.js-back', function(e){
            history.go(-1);
        }); 
        // 点击编辑
        $(document).on('click', '.js-bj', function() {
            location.href = "./archives-add.html?infoId=" + infoId;
         });
        //  点击删除
        $(document).on('click', '.js-sc', function() {
            $.pop({
                title: '提示',
                content: '确认删除当前仓库？',
                btn: ['确认', '取消'],
                size:'sm',
                yes: function () {
                    setTimeout(function(){
                        delItem()
                    },300);
                }
            });
         }); 
    }
    init();
    // 删除
    function delItem() {
        $.getData({
            url: 'depotManage/deleteDepot',
            query: {
                id: infoId
            }
        }, function(data) {
            if (data) {
                $.msg('操作成功');
               history.go(-1);
                // getList();
            }
        });
    }
    // 获取页面所需数据
    function getContent() {
        $.getData({
            url: 'depotManage/depotDetil',
            query: {
                id:infoId
            }
        }, function (data) {
            console.log(data)
            if(data.whDept){
                data.whDept = JSON.parse(data.whDept);
            } else {
                data.whDept = []
            }
           
            console.log(data.whDept)
          renderCont(data);
        });
        
    }
    // 渲染数据
    function renderCont(data) {
        $('#detailCont').html(template('detail/archives-detail', {
            items: data
        }));
        console.log(data.whDept)
        var arr =[];
        for (var i=0;i<data.whDept.length;i++){
            var str = str = {"id":""+data.whDept[i].value+"","parentId":""+data.whDept[i].parent+"","checked":data.whDept[i].checked,"name":""+data.whDept[i].name+"","children":""};
            arr.push(str);
        }
        console.log(arr);
        $('#tt').tree({
            data:arr,
            idField:'id',
            loadFilter: function(rows){
                return $.convert(rows);
            }
        });
    }
      // 渲染无数据
      function renderEmpty() {
        $('#materielList').html(template('common/record-empty', {
            colspan: 4
        }));
    }
});
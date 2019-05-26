$(function () {
    var searchParams = {
        keyWord: "",
        typeId: "0",
        statusId: 0
    }, currPage = 1;
    var flag = false;
    // 获取类型
    function getType() {
        $.getData({
            url: '/type/findList'
        }, function (data) {
            if (data && data.length) {
                var _data = [{
                    value: '0',
                    title: '全部分类'
                }];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].typeName
                    });
                }
                $('#type').html(template('common/select', {
                    items: _data
                }));
            }
        });
    }

    // 获取列表
    function getList() {
        $.getData({
            url: '/contract/findContractPageList',
            query: {
                pageNum: currPage,
                pageSize: 10
            },
            body: searchParams
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getList();
                }
            });
            if (data.list && data.list.length) {
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染数据为空
    function renderEmpty(text) {
        $('.js-list').html(template('common/record-empty', {
            colspan: 13,
            text: '暂无数据' || text
        }));
    }

    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('contract-list', {
            items: data
        }));
        // var a = $.selectAll();
        // a.reset();
    }

    // 收集搜索条件
    function collectFilters() {
        searchParams.typeId = $('#type').val() || '';
        searchParams.keyWord = $.trim($('#name').val()) || '';
        searchParams.statusId = +$.trim($('#status').val());
    }

    // 删除
    function delItem(el, id) {
        var url = '', idQuery = {};
        url = typeof id === 'string' ? '/contract/deleteById' : '/contract/deleteByIds';
        idQuery[typeof id === 'string' ? 'id' : 'ids'] = typeof id === 'string' ? id : id.join(',');
        $.getData({
            url: url,
            query: idQuery
        }, function (data) {
            if (data.code === 0) {
                // $.msg('操作成功');
                if (id.length) {
                    el.each(function () {
                        $(this).parent().parent().remove();
                    })
                } else {
                    el.parent().parent().remove();
                }
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            } else {
                $.alert('删除失败');
            }
        });
    }

    function init() {
        getType();
        getList(1, true);
        // 搜索
        $(document).on('click', '.js-search', function () {
            collectFilters();
            getList(1, true);
        });
        $(document).on('click', '.btn-add', function () {
            window.location.href="./contract-add.html"
        });
        // 删除
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $(this).data('id');
            $.bubble({
                el: $(this),
                msg: '确定删除当前合同？',
                ok: function () {
                    $.toast('删除成功', {
                        type: 'success'
                    });
                    delItem($this, id);
                },
                cancel: function () {
                    // $.toast('您已取消删除');
                }
            });
            return false;

        });
        // 批量删除
        $(document).on('click', '.js-del-multi', function () {
            if ($(this).hasClass('disabled')) {
                return;
            }
            var idsArr = [];
            $('.js-list input[type=checkbox]').each(function () {
                if ($(this).is(':checked') && $(this).data('status') !== 1) {
                    $.confirm('已签约的合同不能删除！');
                    return;
                }
                $(this).is(':checked') && idsArr.push($(this).data('id'));
            })
            if (idsArr.length) {
                // $.confirm('确定批量删除合同？', function () {
                //     delItem($('.js-list input[type=checkbox]'), idsArr);
                // });
                $.bubble({
                    el: $(this),
                    msg: '确定批量删除合同？',
                    ok: function () {
                        $.toast('删除成功', {
                            type: 'success'
                        });
                        delItem($('.js-list input[type=checkbox]'), idsArr);
                    },
                    cancel: function () {
                        // $.toast('您已取消删除');
                    }
                });
            }
        });
        //单选
        $(document).on('click', '[name=choose]', function() {
            var checkNum = 0;
            var checkNum2 = 0
            var chooseLength = 0;
            var ch = document.getElementsByName("choose");
            chooseLength = ch.length;
            if($(this)[0].checked){
                $('.js-del-multi').attr('disabled',false)
            }else{
                for ( var i = 0; i < ch.length; i++) {
                    if(ch[i].checked){
                        checkNum2++;
                    }
                }
                if(checkNum2===0){
                    $('.js-del-multi').attr('disabled',true)
                }
            }
            for ( var i = 0; i < ch.length; i++) {
                if(ch[i].checked){
                    checkNum++;
                }
            }
            if (checkNum === chooseLength) {
                document.getElementsByName("allChecked")[0].checked = true;
            } else {
                document.getElementsByName("allChecked")[0].checked = false;
            }
        });
        // 选择-全选
        $(document).on('click', '.js-list-select-all', function() {
            var ch = document.getElementsByName("choose");
            var isChecked = false;
            if(document.getElementsByName("allChecked")[0].checked == true) {
                isChecked = true;
                for(var i = 0;i < ch.length;i++)
                {
                        ch[i].checked=true;
                }
                $('.js-del-multi').attr('disabled',false)
            } else {
                isChecked = false;
                for(var i = 0;i < ch.length;i++)
                {
                    ch[i].checked=false;
                }
                $('.js-del-multi').attr('disabled',true)
            }

        });

    }
    init();
});

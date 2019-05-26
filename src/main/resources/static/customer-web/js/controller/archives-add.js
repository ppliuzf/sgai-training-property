$(function () {
    var id = $.getQueryString('id'),
        cardList = [];
    if (id) {
        $('.header h4').text('编辑客户');
    }
    // 获取类型
    function getType(value) {
        $.getData({
            url: '/customer/customTypeInfoList',
            query: {
                typeClass: 1
            }
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].ctId,
                        title: data[i].typeName
                    });
                }
                renderSelect('#type', _data, value);
            }
        });
    }
    function getReach(value) {
        $.getData({
            url: '/customer/getSgaiDept'
        }, function (data) {
            turnDate(data, value)//渲染树
        },function () {
            $.alert("部门获取失败")
        })
    }
    //渲染树
    function turnDate(data, value) {
        var rows = data,
            arr = new Array();
        for (var i = 0; i < rows.length; i++) {
            var str = { "id": "" + rows[i].id + "", "parentId": "" + rows[i].pId + "", "checked": false, "name": "" + rows[i].name + "", "children": "" };
            arr.push(str);
        }
        console.log(arr)
        $('#tt').tree({
            data: arr,
            cascadeCheck: false,
            checkbox: true,
            animate: true,
            idField: 'passId',
            loadFilter: function (rows) {  //转换数据格式
                return convert(rows);
            },
            // onCheck: function (node, checked) { //选中回调  //复选
            //     if (checked) {
            //         // document.getElementsByName("allChecked")[0].checked = false;
            //         selTree.push(node.text);
            //         console.log(selTree)
            //     } else {
            //         selTree.splice(selTree.indexOf(node.text), 1);
            //     }
            //     $(".js-room-location").val(selTree);
            // }
            onSelect: function (node) {
                var cknodes = $('#tt').tree("getChecked"),
                    selTree = "",
                    parId;
                for (var i = 0; i < cknodes.length; i++) {
                    if (cknodes[i].id != node.id) {
                        $('#tt').tree("uncheck", cknodes[i].target);
                    }
                }
                if (node.checked) {
                    $('#tt').tree('uncheck', node.target);
                    selTree = "";
                } else {
                    selTree = getFathers();
                    console.log(selTree)
                    $('#tt').tree('check', node.target);
                }
                parId = node.id;
                $(".js-room-location").val(selTree);
                $(".js-room-location").attr("data-parid", parId);
                $("#tt").hide();
            },
            onLoadSuccess: function (node, data) {
                if (value != null) {
                    var node = $('#tt').tree('find', value);
                    $('#tt').tree('check', node.target);
                }
                $(this).find('span.tree-checkbox').unbind().click(function () {
                    $('#tt').tree('select', $(this).parent());
                    return false;
                });
            }
        });
        $('#tt').hide();
    }
    function getFathers() {  //找所有上级父节点
        var node = $('#tt').tree('getSelected'),
            parent = $('#tt').tree('getParent', node.target),
            str = [],
            sty;
        str.unshift(node.text);
        if (parent != null) {
            str.unshift(parent.text);
            while (1) {
                parent = $('#tt').tree('getParent', parent.target);
                if (parent) { str.unshift(parent.text) }
                else break;
            }
            sty = str.join("-");
        } else {
            sty = str.join("")
        }

        return sty;
    }
    //转换数据格式
    function convert(rows) {
        function exists(rows, parentId) {
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].id == parentId) return true;
            }
            return false;
        }
        var nodes = [];
        // get the top level nodes
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (!exists(rows, row.parentId)) {
                nodes.push({
                    id: row.id,
                    text: row.name,
                    url: row.url,
                    parId: row.parentId,
                    hierarchy: 1
                    //checked:row.checked
                });
            }
        }
        console.log(nodes)
        var toDo = [];
        for (var i = 0; i < nodes.length; i++) {
            toDo.push(nodes[i]);
        }
        console.log(toDo)
        while (toDo.length) {
            var node = toDo.shift();	// the parent node
            // get the children nodes
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row.parentId == node.id) {
                    var child = { id: row.id, text: row.name, url: row.url, checked: row.checked, parId: row.parentId };
                    if (node.children) {
                        node.children.push(child);
                    } else {
                        node.children = [child];
                    }
                    toDo.push(child);
                }
            }
        }
        // console.log(nodes);
        return nodes;
    }
    // 获取级别，
    function getLevel(value) {
        $.getData({
            url: '/customer/customLevelInfoList'
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].clId,
                        title: data[i].levelName
                    });
                }
                renderSelect('#level', _data, value);
            }
        });
    }
    // 获取证件类型
    function getCard() {
        $.getData({
            url: '/customer/certificateList',
            query: {
                type: 1
            }
        }, function (data) {
            if (data && data.length) {
                for (var i in data) {
                    cardList.push({
                        value: data[i].ccnId,
                        title: data[i].ccCertificateName
                    });
                }
                renderSelect('.card', cardList);
            }
        });
    }
    // 保存数据
    function saveData(params) {
        $.getData({
            url: id ? '/customer/updatePersonalRecordInfoById' : '/customer/savePersonalRecordInfo',
            body: params
        }, function (data) {
            if (data) {
                // $.msg('操作成功', function () {
                //     location.href = './archives-list.html';
                // });
                $.toast('操作成功', {
                    type: 'success',
                }, function(){
                    location.href = './archives-list.html';
                });
            }
        });
    }
    // 收集数据
    function collectData() {
        // debugger
        var rs = {};
        if ($.trim($('#name').val()) === '') {
            $.alert('请输入姓名', function () {
                $('#name').select();
            });
            return false;
        }
        if ($.trim($('.js-room-location').val()) === '') {
            $.alert('请选择部门', function () {
            });
            return false;
        }
        if ($.trim($('#tel').val()) === '') {
            $.alert('请输入手机号', function () {
                $('#tel').select();
            });
            return false;
        }
        if ($.trim($('#birth').val()) === '') {
            $.alert('请选择出生日期', function () {
                $('#birth').focus();
            });
            return false;
        }
        if ($.trim($('#type').val()) === '') {
            $.alert('请选择类型', function () {
                $('#type').focus();
            });
            return false;
        }
        if ($.trim($('#level').val()) === '') {
            $.alert('请选择级别', function () {
                $('#level').focus();
            });
            return false;
        }
        if (!$('#tel').val().match(/^1[0-9]{10}.*/ig)) {
            $.alert('手机号输入格式有误');
            return false;
        }
        if ($.trim($('#tel1').val()) !== '' && !$('#tel1').val().match(/^1[0-9]{10}.*/ig)) {
            $.alert('手机号1输入格式有误');
            return false;
        }
        if ($.trim($('#mail').val()) !== '' && !$('#mail').val().match(/\S+@\S+\.[A-Za-z]/ig)) {
            $.alert('邮箱输入格式有误');
            return false;
        }
        rs.prName = $.trim($('#name').val());
        rs.prEmail = $.trim($('#mail').val());
        rs.prPhoneFirst = $.trim($('#tel').val());
        rs.prPhoneSecond = $.trim($('#tel1').val());
        rs.prSex = $('#sex').val();
        rs.prBirth = $.getTimeStamp($('#birth').val());
        rs.ctId = $('#type').val();
        rs.clId = $('#level').val();
        rs.deptName = $('.js-room-location').val();
        rs.deptId = $('.js-room-location').data('parid');
        var card = [];;
        $('.js-card .box').each(function () {
            var v = $.trim($(this).find('.card-num').val());
            if (v !== '') {
                card.push({
                    ccCertificateName: $(this).find('.card option:selected').text(),
                    ccCertificateNo: v,
                    ccnId: $(this).find('.card').val()
                });
            }
        });
        if (card.length) {
            rs.customCardInfoDtos = card;
        }
        if (id) {
            rs.prId = id;
        }
        return rs;
    }
    // 渲染级别、类型、证件、部门
    function renderSelect(id, data, value) {
        var _data = data;
        $(id).html(template('common/select', {
            value: value,
            items: _data
        }));
    }
    // 设置证件删除按钮
    function setCardRemoveBtn() {
        $('.js-card-remove')[$('.js-card .box').length > 1 ? 'removeClass' : 'addClass']('hide');
    }
    // 获取详情
    function getDetail() {
        $.getData({
            url: '/customer/getPersonalRecordInfoById',
            query: {
                prId: id
            }
        }, function (data) {
            console.log(data)
            if (data) {
                renderDetail(data);
            } else {
                $.alert('数据不存在', function () {
                    history.back();
                });
            }
        });
    }
    // 渲染详情
    function renderDetail(params) {
        $('.js-main').html(template('archives-add', params));
        $('#birth').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            endDate: new Date(),
            autoclose: true
        });
        $('.js-card').html(template('archives-add-card', {
            options: cardList,
            items: params.customCardInfoVos,
            id: new Date().getTime()
        }));
        if (params.customCardInfoVos.length >= 5) {
            $('.js-card-add').parent().addClass('hide');
        }
        setCardRemoveBtn();
        getType(params.ctId);
        getLevel(params.clId);
        getReach(params.deptId);
        // if (params.deptId != null && params.deptName != null) {
        //     let ary = [{ "isDept": true, "id": params.deptId, "title": params.deptName }];
        //     let str = $('<span class="js-multi-select-item item" adta-id="' + params.deptId + '">' + params.deptName + '</span>');
        //     ary = JSON.stringify(ary);
        //     $('.js--selector').html(str);
        //     $(".js--dept").val(params.deptId);
        //     $(".js--all").val(ary)
        // }
        if (params.deptId != null && params.deptName != null) {
            $('.js-room-location').val(params.deptName);
            $(".js-room-location").attr("data-parid", params.deptId);
        }
    }
    function init() {
        $('.js-main').html(template('archives-add'));
        getCard();
        if (id) {
            getDetail();
        } else {
            $('#birth').datetimepicker({
                language: 'zh-CN',
                format: 'yyyy-mm-dd',
                minView: 'year',
                endDate: new Date(),
                autoclose: true
            });
            $('.js-card').html(template('archives-add-card', {
                items: [1],
                id: new Date().getTime()
            }));
            getType();
            getLevel();
            getReach();
        }
        //选取位置
        $(document).on("click", ".js-room-location", function (event) {
            event.stopPropagation();
            $('#tt').show();
        })
        // 添加多个证件
        $(document).on('click', '.js-card-add', function () {
            $('.js-card').append(template('archives-add-card', {
                items: [1]
            }));
            $('.js-card-add').text('添加多个证件号').parent()[$('.js-card .box').length >= 5 ? 'addClass' : 'removeClass']('hide');
            renderSelect('.card:last', cardList);
            setCardRemoveBtn();
            return false;
        });
        // 删除证件
        $(document).on('click', '.js-card-remove', function () {
            $(this).parent().remove();
            $('.js-card-add').parent()[$('.js-card .box').length >= 5 ? 'addClass' : 'removeClass']('hide');
            setCardRemoveBtn();
            return false;
        });
        // 保存
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            console.log(data)
            if (data) {
                saveData(data);
            }
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            location.href = './archives-list.html';
        });
        $(document).on('click','.row', function (param) {
            var dispaly = $("#tt").css('display');
            if (dispaly != 'none') {
                $("#tt").hide();
            }
        })
    }
    init();
});
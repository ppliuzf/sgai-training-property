$(function () {
    var infoId = $.getQueryString('infoId'),
        dataMain = null;
    var saveName = [],
        editCangKuType = [],
        editCangKuName = [],
        saveWuLiao = [];
    var allDataList = [],
        objAllData1 = {},
        objAllData2 = {},
        objAllData3 = {},
        objAllData4 = {},
        objAllData5 = {};
    var day1, ivtPiJson, count = 1,
        shiCangList = [],
        xuCangList = [],
        materialData1 = [],
        materialName1 = [],
        materialData2 = [],
        materialName2 = [],
        materialData3 = [],
        materialName3 = [],
        materialData4 = [],
        materialName4 = [],
        materialData5 = [],
        materialName5 = [];
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;

    };

    function getTime() {
        var date = new Date(),
            day;
        day = date.toLocaleDateString().split('/');
        if (day[1] < 10) {
            day[1] = "0" + day[1];
        }
        day1 = day.join('-');
        $('.form_date2').datetimepicker({
            // language: 'zh-CN',
            // weekStart: 1,
            // todayBtn: 1,
            autoclose: 1,
            // todayHighlight: 1,
            // startView: 2,
            // minView: 2,
            startDate: day1,
            format: "yyyy-MM-dd hh:ii"
            // forceParse: 0
        }).on('changeDate',function(ev){
            var val = $(this).val();
            var endT =  $('.form_date3').val()
            if(endT && endT < val){
                $.toast('计划开始时间不能大于计划结束时间！');
                $('.form_date2').val('')
            } 
        });
        $('.form_date3').datetimepicker({
            // language: 'zh-CN',
            // weekStart: 1,
            // todayBtn: 1,
            autoclose: 1,
            // todayHighlight: 1,
            // startView: 2,
            // minView: 2,
            startDate: day1,
            format: "yyyy-MM-dd hh:ii"
            // forceParse: 0
        }).on('changeDate',function(ev){
            var val = $(this).val();
            var saT =  $('.form_date2').val()
            if(saT && saT > val){
                $.toast('计划结束时间不能小于计划开始时间！');
                $('.form_date3').val('')
            } 
        });
        $(document).on('click', '.js-date-clean', function () {
            $(this).prev().val('');
        });
    }
    // 实仓
    function getShiCang() {
        $.getData({
            url: 'common/byWhType',
            query: {
                whType: 1
            }
        }, function (data) {
            shiCangList = data;
            window.sessionStorage.setItem('shiCang', JSON.stringify(data));
        });
    }
    // 虚仓
    function getXuCang() {
        $.getData({
            url: 'common/byWhType',
            query: {
                whType: 2
            }
        }, function (data) {
            xuCangList = data;
            window.sessionStorage.setItem('xuCang', JSON.stringify(data));
        });
    }
    // 联动 
    function style(num) {
        shiCangList = JSON.parse(window.sessionStorage.getItem('shiCang'));
        xuCangList = JSON.parse(window.sessionStorage.getItem('xuCang'));
        if (count == 1 || num == 1 || !num) {
            var type = $('#ckTypeC1').val();
            $("#ckTypeDetail1").html('');
            if (type == 1 && shiCangList) {
                for (var i = 0; i < shiCangList.length; i++) {
                    $('#ckTypeDetail1').append('<option value=' + shiCangList[i].whName + ' id=' + shiCangList[i].id + '>' + shiCangList[i].whName + '</option>');
                }
            } else if (type == 2 && xuCangList) {
                for (var k = 0; k < xuCangList.length; k++) {
                    $('#ckTypeDetail1').append('<option value=' + xuCangList[k].whName + ' id=' + xuCangList[k].id + '>' + xuCangList[k].whName + '</option>');
                }
            }
        } else if (num > 1 || count > 1) {
            $("#ckTypeDetail" + num).html('');
            var type1 = $("#ckTypeC" + num).find('option:selected').val();
            if (type1 == 1 && shiCangList) {
                for (var ii = 0; ii < shiCangList.length; ii++) {
                    $("#ckTypeDetail" + num).append('<option value="' + shiCangList[ii].whName + '" id="' + shiCangList[ii].id + '">' + shiCangList[ii].whName + '</option>');
                }
            } else if (type1 == 2 && xuCangList) {
                for (var kk = 0; kk < xuCangList.length; kk++) {
                    $("#ckTypeDetail" + num).append('<option value=' + xuCangList[kk].whName + ' id=' + xuCangList[kk].id + '>' + xuCangList[kk].whName + '</option>');
                }
            }
        }
    }
    // 列表
    function getContent() {
        if (infoId) {
            window.dataMy = null;
            $("#edit").removeClass("hidden");
            document.title = '编辑盘点';
            $.getData({
                url: '/Inventories/getInventByIvtNo',
                query: {
                    ivtNo: infoId
                }
            }, function (data) {
                window.dataMy = data;
                if (data) {
                    if (data.ivtPlanBegin) {
                        $('#planTime').val(new Date(data.ivtPlanBegin).Format("yyyy-MM-dd hh:mm"));
                    } else {
                        $('#planTime').val('');
                    }
                    if (data.ivtPlanEnd) {
                        $('#planEnd').val(new Date(data.ivtPlanEnd).Format("yyyy-MM-dd hh:mm"));
                    } else {
                        $('#planEnd').val('');
                    }
                    ivtPiJson = data.ivtPiJson;
                    // name3 = data.ivtExecEmpName;
                    $('#theme').val(data.ivtTitle);
                    $.instantSearch({
                        approvalEmpName: JSON.parse(ivtPiJson).title,
                        approvalEmpId: JSON.parse(ivtPiJson).id,
                        url: 'orgTree/searchEmpInfoByName'
                    });
                    // 仓库ID
                    count = data.whMatVoList.length;
                    var countMy = count;
                    $('.js-main-content').find('.cangKuGrounp1').remove();
                    for (var i = 0; i < data.whMatVoList.length; i++) {
                        $('.js-main-content').append('<div style="position: relative;" class="cangKuGrounp' + countMy + '">' +
                            '<i class="glyphicon glyphicon-remove-circle reduceIcon"></i>' +
                            '<div class="form-group">' +
                            '<label for="ckType"><span class="require"></span>选择仓库类型' +
                            '</label><div>' +
                            '<select id="ckTypeC' + countMy + '" class="form-control" required="required">' +
                            '<option value="1">实仓</option>' +
                            '<option value="2">虚仓</option>' +
                            '</select></div></div>' +
                            '<div class="form-group">' +
                            '<label for="ckTypeDetail"><span class="require"></span>选择仓库' +
                            '</label><div>' +
                            '<select id="ckTypeDetail' + countMy + '" class="form-control" required="required">' +
                            '</select></div></div>' +
                            '<div class="form-group">' +
                            '<label><span class="require"></span>盘点物料名称</label>' +
                            '<div><div id="mater' + countMy + '" class="form-control" required="required"></div>' +
                            '</div></div></div>')
                        style(countMy);
                        var dataList = [];
                        for (var m = 0; m < data.whMatVoList[i].inventoriesMatList.length; m++) {
                            var cur = data.whMatVoList[i].inventoriesMatList[m].matName;
                            dataList.push(cur);
                        }
                        $('[id="mater' + countMy + '"]').text(dataList);
                        editCangKuType.push(data.whMatVoList[i].whType);
                        editCangKuName.push(data.whMatVoList[i].whId);
                        countMy--;
                    }
                }
                editCangKuType = editCangKuType.reverse();
                editCangKuName = editCangKuName.reverse();
                if (editCangKuType.length == 1) {
                    $('#ckTypeDetail1').html('');
                    if (editCangKuType[0] == 1) {
                        for (var u = 0; u < shiCangList.length; u++) {
                            $('#ckTypeDetail1').append('<option value=' + shiCangList[u].whName + ' id=' + shiCangList[u].id + '>' + shiCangList[u].whName + '</option>');
                        }
                    } else {
                        for (var t = 0; t < xuCangList.length; t++) {
                            $('#ckTypeDetail1').append('<option value=' + xuCangList[t].whName + ' id=' + xuCangList[t].id + '>' + xuCangList[t].whName + '</option>')
                        }
                    }
                    $('#ckTypeC1').find('option').each(function () {
                        if (editCangKuType[0] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail1').find('option').each(function () {
                        if (editCangKuName[0] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                }
                if (editCangKuType.length == 2) {
                    $('#ckTypeC1').find('option').each(function () {
                        if (editCangKuType[0] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC2').find('option').each(function () {
                        if (editCangKuType[1] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail1').html('');
                    $('#ckTypeDetail2').html('');
                    if (editCangKuType[0] == 1) {
                        for (var uu1 = 0; uu1 < shiCangList.length; uu1++) {
                            $('#ckTypeDetail1').append('<option value=' + shiCangList[uu1].whName + ' id=' + shiCangList[uu1].id + '>' + shiCangList[uu1].whName + '</option>');
                        }
                    } else {
                        for (var tt1 = 0; tt1 < xuCangList.length; tt1++) {
                            $('#ckTypeDetail1').append('<option value=' + xuCangList[tt1].whName + ' id=' + xuCangList[tt1].id + '>' + xuCangList[tt1].whName + '</option>')
                        }
                    }
                    if (editCangKuType[1] == 1) {
                        for (var uu2 = 0; uu2 < shiCangList.length; uu2++) {
                            $('#ckTypeDetail2').append('<option value=' + shiCangList[uu2].whName + ' id=' + shiCangList[uu2].id + '>' + shiCangList[uu2].whName + '</option>');
                        }
                    } else {
                        for (var tt2 = 0; tt2 < xuCangList.length; tt2++) {
                            $('#ckTypeDetail2').append('<option value=' + xuCangList[tt2].whName + ' id=' + xuCangList[tt2].id + '>' + xuCangList[tt2].whName + '</option>')
                        }
                    }
                    if (editCangKuType[0] == 1) {
                        $('#ckTypeDetail1').append('<option value=' + shiCangList[i].whName + ' id=' + shiCangList[i].id + '>' + shiCangList[i].whName + '</option>');
                    } else {
                        $('#ckTypeDetail1').append('<option value=' + xuCangList[i].whName + ' id=' + xuCangList[i].id + '>' + xuCangList[i].whName + '</option>')
                    }
                    if (editCangKuType[1] == 1) {
                        $('#ckTypeDetail2').append('<option value=' + shiCangList[i].whName + ' id=' + shiCangList[i].id + '>' + shiCangList[i].whName + '</option>');
                    } else {
                        $('#ckTypeDetail2').append('<option value=' + xuCangList[i].whName + ' id=' + xuCangList[i].id + '>' + xuCangList[i].whName + '</option>')
                    }
                    if (editCangKuType[0] == 1) {
                        $('#ckTypeDetail1').append('<option value=' + shiCangList[i].whName + ' id=' + shiCangList[i].id + '>' + shiCangList[i].whName + '</option>');
                    } else {
                        $('#ckTypeDetail1').append('<option value=' + xuCangList[i].whName + ' id=' + xuCangList[i].id + '>' + xuCangList[i].whName + '</option>')
                    }
                    $('#ckTypeDetail1').find('option').each(function () {
                        if (editCangKuName[0] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail2').find('option').each(function () {
                        if (editCangKuName[1] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                }
                if (editCangKuType.length == 3) {
                    $('#ckTypeC1').find('option').each(function () {
                        if (editCangKuType[0] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC2').find('option').each(function () {
                        if (editCangKuType[1] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC3').find('option').each(function () {
                        if (editCangKuType[2] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail1').html('');
                    $('#ckTypeDetail2').html('');
                    $('#ckTypeDetail3').html('');
                    if (editCangKuType[0] == 1) {
                        for (var uuu1 = 0; uuu1 < shiCangList.length; uuu1++) {
                            $('#ckTypeDetail1').append('<option value=' + shiCangList[uuu1].whName + ' id=' + shiCangList[uuu1].id + '>' + shiCangList[uuu1].whName + '</option>');
                        }
                    } else {
                        for (var ttt1 = 0; ttt1 < xuCangList.length; ttt1++) {
                            $('#ckTypeDetail1').append('<option value=' + xuCangList[ttt1].whName + ' id=' + xuCangList[ttt1].id + '>' + xuCangList[ttt1].whName + '</option>')
                        }
                    }
                    if (editCangKuType[1] == 1) {
                        for (var uuu2 = 0; uuu2 < shiCangList.length; uuu2++) {
                            $('#ckTypeDetail2').append('<option value=' + shiCangList[uuu2].whName + ' id=' + shiCangList[uuu2].id + '>' + shiCangList[uuu2].whName + '</option>');
                        }
                    } else {
                        for (var ttt2 = 0; ttt2 < xuCangList.length; ttt2++) {
                            $('#ckTypeDetail2').append('<option value=' + xuCangList[ttt2].whName + ' id=' + xuCangList[ttt2].id + '>' + xuCangList[ttt2].whName + '</option>')
                        }
                    }
                    if (editCangKuType[2] == 1) {
                        for (var uuu3 = 0; uuu3 < shiCangList.length; uuu3++) {
                            $('#ckTypeDetail3').append('<option value=' + shiCangList[uuu3].whName + ' id=' + shiCangList[uuu3].id + '>' + shiCangList[uuu3].whName + '</option>');
                        }
                    } else {
                        for (var ttt3 = 0; ttt3 < xuCangList.length; ttt3++) {
                            $('#ckTypeDetail3').append('<option value=' + xuCangList[ttt3].whName + ' id=' + xuCangList[ttt3].id + '>' + xuCangList[ttt3].whName + '</option>')
                        }
                    }
                    $('#ckTypeDetail1').find('option').each(function () {
                        if (editCangKuName[0] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail2').find('option').each(function () {
                        if (editCangKuName[1] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail3').find('option').each(function () {
                        if (editCangKuName[2] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                }
                if (editCangKuType.length == 4) {
                    $('#ckTypeC1').find('option').each(function () {
                        if (editCangKuType[0] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC2').find('option').each(function () {
                        if (editCangKuType[1] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC3').find('option').each(function () {
                        if (editCangKuType[2] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC4').find('option').each(function () {
                        if (editCangKuType[3] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail1').html('');
                    $('#ckTypeDetail2').html('');
                    $('#ckTypeDetail3').html('');
                    $('#ckTypeDetail4').html('');
                    if (editCangKuType[0] == 1) {
                        for (var uuuu1 = 0; uuuu1 < shiCangList.length; uuuu1++) {
                            $('#ckTypeDetail1').append('<option value=' + shiCangList[uuuu1].whName + ' id=' + shiCangList[uuuu1].id + '>' + shiCangList[uuuu1].whName + '</option>');
                        }
                    } else {
                        for (var tttt1 = 0; tttt1 < xuCangList.length; tttt1++) {
                            $('#ckTypeDetail1').append('<option value=' + xuCangList[tttt1].whName + ' id=' + xuCangList[tttt1].id + '>' + xuCangList[tttt1].whName + '</option>')
                        }
                    }
                    if (editCangKuType[1] == 1) {
                        for (var uuuu2 = 0; uuuu2 < shiCangList.length; uuuu2++) {
                            $('#ckTypeDetail2').append('<option value=' + shiCangList[uuuu2].whName + ' id=' + shiCangList[uuuu2].id + '>' + shiCangList[uuuu2].whName + '</option>');
                        }
                    } else {
                        for (var tttt2 = 0; tttt2 < xuCangList.length; tttt2++) {
                            $('#ckTypeDetail2').append('<option value=' + xuCangList[tttt2].whName + ' id=' + xuCangList[tttt2].id + '>' + xuCangList[tttt2].whName + '</option>')
                        }
                    }
                    if (editCangKuType[2] == 1) {
                        for (var uuuu3 = 0; uuuu3 < shiCangList.length; uuuu3++) {
                            $('#ckTypeDetail3').append('<option value=' + shiCangList[uuuu3].whName + ' id=' + shiCangList[uuuu3].id + '>' + shiCangList[uuuu3].whName + '</option>');
                        }
                    } else {
                        for (var tttt3 = 0; tttt3 < xuCangList.length; tttt3++) {
                            $('#ckTypeDetail3').append('<option value=' + xuCangList[tttt3].whName + ' id=' + xuCangList[tttt3].id + '>' + xuCangList[tttt3].whName + '</option>')
                        }
                    }
                    if (editCangKuType[3] == 1) {
                        for (var uuuu4 = 0; uuuu4 < shiCangList.length; uuuu4++) {
                            $('#ckTypeDetail4').append('<option value=' + shiCangList[uuuu4].whName + ' id=' + shiCangList[uuuu4].id + '>' + shiCangList[uuuu4].whName + '</option>');
                        }
                    } else {
                        for (var tttt4 = 0; tttt4 < xuCangList.length; tttt4++) {
                            $('#ckTypeDetail4').append('<option value=' + xuCangList[tttt4].whName + ' id=' + xuCangList[tttt4].id + '>' + xuCangList[tttt4].whName + '</option>')
                        }
                    }
                    $('#ckTypeDetail1').find('option').each(function () {
                        if (editCangKuName[0] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail2').find('option').each(function () {
                        if (editCangKuName[1] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail3').find('option').each(function () {
                        if (editCangKuName[2] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail4').find('option').each(function () {
                        if (editCangKuName[3] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                }
                if (editCangKuType.length == 5) {
                    $('#ckTypeC1').find('option').each(function () {
                        if (editCangKuType[0] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC2').find('option').each(function () {
                        if (editCangKuType[1] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC3').find('option').each(function () {
                        if (editCangKuType[2] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC4').find('option').each(function () {
                        if (editCangKuType[3] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeC5').find('option').each(function () {
                        if (editCangKuType[4] == $(this).val()) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail1').html('');
                    $('#ckTypeDetail2').html('');
                    $('#ckTypeDetail3').html('');
                    $('#ckTypeDetail4').html('');
                    $('#ckTypeDetail5').html('');
                    if (editCangKuType[0] == 1) {
                        for (var uuuuu1 = 0; uuuuu1 < shiCangList.length; uuuuu1++) {
                            $('#ckTypeDetail1').append('<option value=' + shiCangList[uuuuu1].whName + ' id=' + shiCangList[uuuuu1].id + '>' + shiCangList[uuuuu1].whName + '</option>');
                        }
                    } else {
                        for (var ttttt1 = 0; ttttt1 < xuCangList.length; ttttt1++) {
                            $('#ckTypeDetail1').append('<option value=' + xuCangList[ttttt1].whName + ' id=' + xuCangList[ttttt1].id + '>' + xuCangList[ttttt1].whName + '</option>')
                        }
                    }
                    if (editCangKuType[1] == 1) {
                        for (var uuuuu2 = 0; uuuuu2 < shiCangList.length; uuuuu2++) {
                            $('#ckTypeDetail2').append('<option value=' + shiCangList[uuuuu2].whName + ' id=' + shiCangList[uuuuu2].id + '>' + shiCangList[uuuuu2].whName + '</option>');
                        }
                    } else {
                        for (var ttttt2 = 0; ttttt2 < xuCangList.length; ttttt2++) {
                            $('#ckTypeDetail2').append('<option value=' + xuCangList[ttttt2].whName + ' id=' + xuCangList[ttttt2].id + '>' + xuCangList[ttttt2].whName + '</option>')
                        }
                    }
                    if (editCangKuType[2] == 1) {
                        for (var uuuuu3 = 0; uuuuu3 < shiCangList.length; uuuuu3++) {
                            $('#ckTypeDetail3').append('<option value=' + shiCangList[uuuuu3].whName + ' id=' + shiCangList[uuuuu3].id + '>' + shiCangList[uuuuu3].whName + '</option>');
                        }
                    } else {
                        for (var ttttt3 = 0; ttttt3 < xuCangList.length; ttttt3++) {
                            $('#ckTypeDetail3').append('<option value=' + xuCangList[ttttt3].whName + ' id=' + xuCangList[ttttt3].id + '>' + xuCangList[ttttt3].whName + '</option>')
                        }
                    }
                    if (editCangKuType[3] == 1) {
                        for (var uuuuu4 = 0; uuuuu4 < shiCangList.length; uuuuu4++) {
                            $('#ckTypeDetail4').append('<option value=' + shiCangList[uuuuu4].whName + ' id=' + shiCangList[uuuuu4].id + '>' + shiCangList[uuuuu4].whName + '</option>');
                        }
                    } else {
                        for (var ttttt4 = 0; ttttt4 < xuCangList.length; ttttt4++) {
                            $('#ckTypeDetail4').append('<option value=' + xuCangList[ttttt4].whName + ' id=' + xuCangList[ttttt4].id + '>' + xuCangList[ttttt4].whName + '</option>')
                        }
                    }
                    if (editCangKuType[4] == 1) {
                        for (var uuuuu5 = 0; uuuuu5 < shiCangList.length; uuuuu5++) {
                            $('#ckTypeDetail5').append('<option value=' + shiCangList[uuuuu5].whName + ' id=' + shiCangList[uuuuu5].id + '>' + shiCangList[uuuuu5].whName + '</option>');
                        }
                    } else {
                        for (var ttttt5 = 0; ttttt5 < xuCangList.length; ttttt5++) {
                            $('#ckTypeDetail5').append('<option value=' + xuCangList[ttttt5].whName + ' id=' + xuCangList[ttttt5].id + '>' + xuCangList[ttttt5].whName + '</option>')
                        }
                    }
                    $('#ckTypeDetail1').find('option').each(function () {
                        if (editCangKuName[0] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail2').find('option').each(function () {
                        if (editCangKuName[1] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail3').find('option').each(function () {
                        if (editCangKuName[2] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail4').find('option').each(function () {
                        if (editCangKuName[3] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                    $('#ckTypeDetail5').find('option').each(function () {
                        if (editCangKuName[4] == $(this).attr('id')) {
                            $(this).attr('selected', 'selected');
                        }
                    })
                }
            });

        } else {
            // 新增页面
            $("#add").removeClass("hidden");
            document.title = '新建盘点';
            $.instantSearch({
                url: 'orgTree/searchEmpInfoByName',
                placeholder: '请输入姓名'
            });
        }
    }
    // 获取物料数据
    function getMaterial(num) {
        var mmmm = $('#ckTypeDetail' + num).find('option:selected').attr('id');
        $.getData({
            url: '/Inventories/findMatListByWhId',
            query: {
                whId: mmmm
            }
        }, function (data) {
            // console.log(JSON.stringify(data));
            if (data && data.length > 0) {
                renderMaterial(data, num);
            } else {
                $.toast('该仓库没有物料，请选择其他仓库');
            }
        })
    }
    //弹窗
    function renderMaterial(marlist, num) {
        var moban = '';
        if (marlist.length) {
            if (num == 1) {
                moban = 'check/wuliao';
                var content1 = $('#mater1').text().split(',');
                for (var aaa = 0; aaa < marlist.length; aaa++) {
                    marlist[aaa].isChecked = '0';
                    for (var aaa1 = 0; aaa1 < content1.length; aaa1++) {
                        if (marlist[aaa].matName == content1[aaa1]) {
                            marlist[aaa].isChecked = '1';
                        }
                    }
                }
            }
            if (num == 2) {
                moban = 'check/wuliao2';
                var content2 = $('#mater2').text().split(',');
                for (var aaa2 = 0; aaa2 < marlist.length; aaa2++) {
                    marlist[aaa2].isChecked = '2';
                    for (var aaa22 = 0; aaa22 < content2.length; aaa22++) {
                        if (marlist[aaa2].matName == content2[aaa22]) {
                            marlist[aaa2].isChecked = '3';
                        }
                    }
                }
            }
            if (num == 3) {
                moban = 'check/wuliao3';
                var content3 = $('#mater3').text().split(',');
                for (var aaa3 = 0; aaa3 < marlist.length; aaa3++) {
                    marlist[aaa3].isChecked = '4';
                    for (var aaa33 = 0; aaa33 < content3.length; aaa33++) {
                        if (marlist[aaa3].matName == content3[aaa33]) {
                            marlist[aaa3].isChecked = '5';
                        }
                    }
                }
            }
            if (num == 4) {
                moban = 'check/wuliao4';
                var content4 = $('#mater4').text().split(',');
                for (var aaa4 = 0; aaa4 < marlist.length; aaa4++) {
                    marlist[aaa4].isChecked = '6';
                    for (var aaa44 = 0; aaa44 < content4.length; aaa44++) {
                        if (marlist[aaa4].matName == content4[aaa44]) {
                            marlist[aaa4].isChecked = '7';
                        }
                    }
                }
            }
            if (num == 5) {
                moban = 'check/wuliao5';
                var content5 = $('#mater5').text().split(',');
                for (var aaa5 = 0; aaa5 < marlist.length; aaa5++) {
                    marlist[aaa5].isChecked = '8';
                    for (var aaa55 = 0; aaa55 < content5.length; aaa55++) {
                        if (marlist[aaa5].matName == content5[aaa55]) {
                            marlist[aaa5].isChecked = '9';
                        }
                    }
                }
            }
            $.pop({
                content: template(moban, {
                    items: marlist
                }),
                title: '选择物料',
                noIcon: true,
                yes: function () {
                    if (num == 1) {
                        materialName1 = [];
                        materialData1 = [];
                        // 选择物料
                        $('.js-materiel-list').find('input:checked').each(function () {
                            materialName1.push($(this).next('span').text());
                            var obj = {};
                            obj.matName = $(this).next('span').text();
                            obj.matSpec = $(this).attr('spec');
                            obj.matTypeCode = $(this).attr('mattypecode');
                            obj.matTypeId = $(this).attr('id');
                            obj.whId = $('#ckTypeDetail1').find('option:selected').attr('id');
                            materialData1.push(obj);
                        })
                        $('#mater1').html('');
                        $('#mater1').text(materialName1);
                        objAllData1.inventoriesMatList = materialData1;
                        objAllData1.whId = $('#ckTypeDetail1').find('option:selected').attr('id');
                        objAllData1.whName = $('#ckTypeDetail1').find('option:selected').val();
                    }
                    if (num == 2) {
                        materialName2 = [];
                        materialData2 = [];
                        // 选择物料
                        $('.js-materiel-list2').find('input:checked').each(function () {
                            materialName2.push($(this).next('span').text());
                            var obj = {};
                            obj.matName = $(this).next('span').text();
                            obj.matSpec = $(this).attr('spec');
                            obj.matTypeCode = $(this).attr('mattypecode');
                            obj.matTypeId = $(this).attr('id');
                            obj.whId = $('#ckTypeDetail2').find('option:selected').attr('id');
                            materialData2.push(obj);
                        })
                        $('#mater2').html('');
                        $('#mater2').text(materialName2);
                        objAllData2.inventoriesMatList = materialData2;
                        objAllData2.whId = $('#ckTypeDetail2').find('option:selected').attr('id');
                        objAllData2.whName = $('#ckTypeDetail2').find('option:selected').val();
                    }
                    if (num == 3) {
                        materialName3 = [];
                        materialData3 = [];
                        // 选择物料
                        $('.js-materiel-list3').find('input:checked').each(function () {
                            materialName3.push($(this).next('span').text());
                            var obj = {};
                            obj.matName = $(this).next('span').text();
                            obj.matSpec = $(this).attr('spec');
                            obj.matTypeCode = $(this).attr('mattypecode');
                            obj.matTypeId = $(this).attr('id');
                            obj.whId = $('#ckTypeDetail3').find('option:selected').attr('id');
                            materialData3.push(obj);
                        })
                        $('#mater3').html('');
                        $('#mater3').text(materialName3);
                        objAllData3.inventoriesMatList = materialData3;
                        objAllData3.whId = $('#ckTypeDetail3').find('option:selected').attr('id');
                        objAllData3.whName = $('#ckTypeDetail3').find('option:selected').val();
                    }
                    if (num == 4) {
                        materialName4 = [];
                        materialData4 = [];
                        // 选择物料
                        $('.js-materiel-list4').find('input:checked').each(function () {
                            materialName4.push($(this).next('span').text());
                            var obj = {};
                            obj.matName = $(this).next('span').text();
                            obj.matSpec = $(this).attr('spec');
                            obj.matTypeCode = $(this).attr('mattypecode');
                            obj.matTypeId = $(this).attr('id');
                            obj.whId = $('#ckTypeDetail4').find('option:selected').attr('id');
                            materialData4.push(obj);
                        })
                        $('#mater4').html('');
                        $('#mater4').text(materialName4);
                        objAllData4.inventoriesMatList = materialData4;
                        objAllData4.whId = $('#ckTypeDetail4').find('option:selected').attr('id');
                        objAllData4.whName = $('#ckTypeDetail4').find('option:selected').val();
                    }
                    if (num == 5) {
                        materialName5 = [];
                        materialData5 = [];
                        // 选择物料
                        $('.js-materiel-list5').find('input:checked').each(function () {
                            materialName5.push($(this).next('span').text());
                            var obj = {};
                            obj.matName = $(this).next('span').text();
                            obj.matSpec = $(this).attr('spec');
                            obj.matTypeCode = $(this).attr('mattypecode');
                            obj.matTypeId = $(this).attr('id');
                            obj.whId = $('#ckTypeDetail5').find('option:selected').attr('id');
                            materialData5.push(obj);
                        })
                        $('#mater5').html('');
                        $('#mater5').text(materialName5);
                        objAllData5.inventoriesMatList = materialData5;
                        objAllData5.whId = $('#ckTypeDetail5').find('option:selected').attr('id');
                        objAllData5.whName = $('#ckTypeDetail5').find('option:selected').val();
                    }
                    for (var h = 0; h < count; h++) {
                        allDataList = [];
                        if (h == 0) {
                            allDataList[0] = objAllData1;
                        }
                        if (h == 1) {
                            allDataList[0] = objAllData1;
                            allDataList[1] = objAllData2;
                        }
                        if (h == 2) {
                            allDataList[0] = objAllData1;
                            allDataList[1] = objAllData2;
                            allDataList[2] = objAllData3;
                        }
                        if (h == 3) {
                            allDataList[0] = objAllData1;
                            allDataList[1] = objAllData2;
                            allDataList[2] = objAllData3;
                            allDataList[3] = objAllData4;
                        }
                        if (h == 4) {
                            allDataList[0] = objAllData1;
                            allDataList[1] = objAllData2;
                            allDataList[2] = objAllData3;
                            allDataList[3] = objAllData4;
                            allDataList[4] = objAllData5;
                        }
                    }
                }
            });
        }
    }
    // 新增/编辑的数据
    function submitData() {
        if ($.trim($('#theme').val()) == '') {
            $.toast('请输入主题', {
                timer: 2000
            });
            return false;
        }
        if (!$.trim($('#instant-selected').attr('value'))) {
            $.toast('请输入盘点人', {
                timer: 2000
            });
            return false;
        }
        if ($.trim($("#planTime").val()) === '') {
            $.toast('请选择计划开始时间', {
                timer: 2000
            });
            return false;
        }
        if ($.trim($("#planEnd").val()) === '') {
            $.toast('请选择计划结束时间', {
                timer: 2000
            });
            return false;
        }
        var aa = $.getTimeStamp($.trim($('#planTime').val()));
        var bb = $.getTimeStamp($.trim($('#planEnd').val()), 'end');
        if (aa && bb && aa > bb) {
            $.toast('计划开始时间不能大于计划结束时间', {
                timer: 2000
            });
            return false;
        }
        // 仓库唯一
        saveName = [];
        $('[id*="ckTypeDetail"]').find('option:selected').each(function () {
            saveName.push($(this).val());
        })
        for (var iii = 0; iii < saveName.length; iii++) {
            if (saveName.indexOf(saveName[iii]) != iii) {
                $.toast('请选择不同的仓库');
                iii--;
                return false;
            }
        }
        // 盘点物料不能为空 
        saveWuLiao = [];
        $('[id*="mater"]').each(function () {
            saveWuLiao.push($(this).text());
        });
        if (saveWuLiao[length - 1] == '') {
            saveWuLiao.pop();
        }
        console.log(saveWuLiao);
        for (var ggg = 0; ggg < saveWuLiao.length; ggg++) {
            if (saveWuLiao[ggg] == '') {
                $.toast('请选择盘点物料');
                saveWuLiao.splice(ggg, 1);
                return false;
            }
        }
        var param = {};
        if (!infoId) {
            param.ivtPiJson = $.trim($('#instant-selected').val());
        }
        if (infoId) {
            param.ivtPiJson = ivtPiJson;

            if (allDataList.length == 0) {

                param.whMatParamList = window.dataMy.whMatVoList;
            } else if (allDataList.length == 1) {
                param.whMatParamList = allDataList;
            } else {
                var bb = window.dataMy.whMatVoList.length;
                allDataList.splice(0, bb);
                param.whMatParamList = window.dataMy.whMatVoList.concat(allDataList);
            }
        } else {
            param.whMatParamList = allDataList;
        }
        param.ivtTitle = $.trim($('#theme').val());
        if ($.trim($('#planTime').val())) {
            // param.ivtPlanBegin = $.trim($('#planTime').val());
            var aachen = $('#planTime').val().split(' ')[0];
            var bbchen = $('#planTime').val().split(' ')[1] + ':00';
            // param.ivtPlanBegin = aachen + 'T' + bbchen;
            param.ivtPlanBegin = new Date((aachen + ' ' + bbchen).replace(/-/g, '/'));
        } else {
            param.ivtPlanBegin = '';
        }
        if ($.trim($('#planEnd').val())) {
            // param.ivtPlanEnd = $.trim($('#planEnd').val()) + 'T00:00:00';
            var aachen1 = $('#planEnd').val().split(' ')[0];
            var bbchen1 = $('#planEnd').val().split(' ')[1] + ':00';
            param.ivtPlanEnd = new Date((aachen1 + ' ' + bbchen1).replace(/-/g, '/'));
        } else {
            param.ivtPlanEnd = '';
        }
        return param;
    }
    // 保存
    function submit(infoId) {
        dataMain = submitData();
        if (!dataMain) {
            return false;
        }
        if (infoId) {
            dataMain.ivtNo = infoId;
            // console.log(JSON.stringify(dataMain));
            $.getData({
                url: '/Inventories/updateInventoriesNew',
                body: dataMain
            }, function (data) {
                if (data) {
                    location.href = './check-list.html';
                }
            });
        } else {
            // console.log(JSON.stringify(dataMain));
            $.getData({
                url: '/Inventories/saveInventoriesNew',
                body: dataMain
            }, function (data) {
                if (data) {
                    location.href = './check-list.html';
                }
            });
        }
    }

    function init() {
        getTime();
        setTimeout(getContent, 2000);
        getShiCang();
        getXuCang();
        if (!infoId) {
            setTimeout(style, 3000);
        }
        // 保存
        $(document).on('click', '#checkSave', function () {
            submit(infoId);
        });
        // 点击取消
        $(document).on('click', '#checkCancle', function () {
            location.href = './check-list.html';
        });
        // 物料
        $(document).on('click', '[id*="mater"]', function () {
            var num = $(this).attr('id').slice(5);
            getMaterial(num);
        })
        // 仓库联动
        $(document).on('change', '[id*="ckTypeC"]', function () {
            var num = $(this).attr('id').slice(7);

            style(num);
        });

        // 点击加号
        $(document).on('click', '.addIcon', function () {
            if (count == 5) {
                $.toast('最多选择5个仓库');
                return false;
            } else {
                count++;
                $('.cangKuGrounp' + count).html('');
                $('.js-main-content').append('<div style="position: relative;" class="cangKuGrounp' + count + '">' +
                    '<i class="glyphicon glyphicon-remove-circle reduceIcon"></i>' +
                    '<div class="form-group">' +
                    '<label for="ckType"><span class="require"></span>选择仓库类型' +
                    '</label><div>' +
                    '<select id="ckTypeC' + count + '" class="form-control" required="required">' +
                    '<option value="1">实仓</option>' +
                    '<option value="2">虚仓</option>' +
                    '</select></div></div>' +
                    '<div class="form-group">' +
                    '<label for="ckTypeDetail"><span class="require"></span>选择仓库' +
                    '</label><div>' +
                    '<select id="ckTypeDetail' + count + '" class="form-control" required="required">' +
                    '</select></div></div>' +
                    '<div class="form-group">' +
                    '<label><span class="require"></span>盘点物料名称</label>' +
                    '<div><div id="mater' + count + '" class="form-control" required="required"></div>' +
                    '</div></div></div>')
                style(count);
            }
        })
        // 点击减号
        $(document).on('click', '.reduceIcon', function () {

            if (!infoId) { // 新增
                if (count == allDataList.length) {
                    allDataList.splice(count - 1);
                }
                count = count - 1;
                if (count < 1) {
                    count = 1;
                    return;
                } else {
                    $(this).parent().remove();
                }
            } else { // 编辑
                var aa = $(this).parent().attr('class').slice(12);
                count == window.dataMy.whMatVoList.length;
                window.dataMy.whMatVoList.splice(-aa, 1);
                // console.log(JSON.stringify(window.dataMy.whMatVoList));
                count = count - 1;
                if (count < 1) {
                    count = 1;
                    return;
                } else {
                    $(this).parent().remove();
                }
            }
            // var uu = $(this).next(3).find('.form-control').attr('id').replace(/[^0-9]/ig, "");
        })
        $.counter1({
            el: '#theme'
        });
    }
    init();
});
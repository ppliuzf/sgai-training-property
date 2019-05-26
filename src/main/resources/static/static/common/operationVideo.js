var videoOnlineCheckedArr = [];
var number = 9;

/*初始化宽高*/
function initwh() {
    var ww = $(".mainContent").width();
    //alert(ww);
    var w1 = (ww - 3) / ww * 100 + "%";
    var w4 = (ww - 5) / 2 / ww * 100 + "%";
    var w9 = (ww - 7) / 3 / ww * 100 + "%";
    var w16 = (ww - 9) / 4 / ww * 100 + "%";
    var h1 = (ww - 2) * 0.57;
    var h4 = (ww - 4) / 2 * 0.57;
    var h9 = (ww - 6) / 3 * 0.57;
    var h16 = (ww - 8) / 4 * 0.57;

    $(".pic1 .boxs").css({"width": w1, "height": h1});
    $(".pic4 .boxs").css({"width": w4, "height": h4});
    $(".pic9 .boxs").css({"width": w9, "height": h9});
    $(".pic16 .boxs").css({"width": w16, "height": h16});
    //console.log(w4,w9,w16,h9);
    $(".boxList .boxs").css({"left": "", "top": "", "position": "", "margin": ""});
    setTimeout(function () {
        sort();
    }, 100)
    $(window).resize(function () {
        initwh();
    });
}

//鼠标浮上显示操作
$(".mainContent").on("mouseenter", ".boxs", function () {
    $(this).find(".operation").stop().animate({
        bottom: '0px'
    });
})
$(".mainContent").on("mouseleave", ".boxs", function () {
    $(this).find(".operation").stop().animate({
        bottom: '-20px'
    });
});
$(".mainContent").on("hover", ".boxs", function () {
    $(this).find(".operations").stop().animate({
        bottom: '0px'
    });
}, function () {
    $(this).find(".operations").stop().animate({
        bottom: '-20px'
    });
})

/*移动*/
function sort() {
    $(".mainContent:visible").find(".boxs").Tdrag({
        scope: ".mainContent",
        pos: true,
        dragChange: true,
        changeMode: "sort",
        //handle:".boxs"
    });
}

/*删除*/
function deleteItem() {
    $.jBox.confirm("确定删除吗?", "提示", function (v, h, f) {
        if (v == 'ok') {
            var ids = "";
            if (!ids == "") {
                $.ajax({
                    headers: {
                        "token": localStorage.getItem("token"),
                    },
                    url: APIHost + '/admin/mdmSupplierInfo/delete',
                    type: 'POST',
                    async: false, //或false,是否异步
                    data: {
                        "ids": ids
                    },
                    timeout: 5000,
                    dataType: 'json',
                    success: function (response) {

                    },
                    error: function () {
                        console.log('错误')
                    }
                });
            } else {
                $.jBox.alert("选项不可为空！", "提示");
            }
        }
    })
}

/*最多几个监控*/
function mores() {
    $.jBox.alert("最多只能添加" + number + "项", "提示", function (v, h, f) {
        if (v == 'ok') {
        }
    })
}

/*弹框遮罩*/
function insertItem(modalId, url, iframeId, width) {
    $('#' + modalId).modal({backdrop: 'static', keyboard: false});
    $('#' + modalId).modal('show');

}

// 关闭弹框
function closePopUp() {
    $('#myModal .close').click();
}

$(function () {
    /********页面效果********/
    //makeBread();
    initwh();
    monitorimg(number);
    var btnToggleDoorKey = 0;
    $('#btnToggle').on('click', function () {
        ++btnToggleDoorKey;
        $('#part1').toggle();
        $('#part2').toggle();
        if (btnToggleDoorKey % 2) {
            $(this).text('模式切换-回放');
            $('#btnInsert').hide();
        } else {
            $('#btnInsert').show();
            $(this).text('模式切换-直播');
        }
    });
    $(".mainContent .boxs").click(function () {
        $(this).addClass("blues").siblings().removeClass("blues");
    });
    //点击按钮监控内容切换
    $(".left-nav .item").click(function () {
        var index = $(".left-nav .item").index(this);
        $(this).addClass("active").siblings().removeClass("active");

        $(".mainContent").eq(index).removeClass("hide").show().siblings().hide();
        sort();

        $(".left-nav .item.disable").addClass("active").removeClass("disable");
        $(".left-nav").removeClass("disable");

        $(".cons-right-down .btns .bgs").removeClass("blues");
        $(".cons-right-down .btns .bgs:nth-child(4)").removeClass("reds");
        $(".cel a i,.cen i,.cen2 i").removeClass("blues");
        $(".cons-right-down .times input").attr("disabled", true);
        $(".cons-right-down .times button").removeClass("small_blue");
        if (index == 0) {
            number = 1;
            $(".cons-right-down .times button").addClass("small_blue");
            $(".cons-right-down .btns .bgs").addClass("blues");
            $(".cons-right-down .btns .bgs:nth-child(4)").addClass("reds");
            $(".cel a i,.cen i,.cen2 i").addClass("blues");
            $(".cons-right-down .times button").addClass("small_blue");

        } else if (index == 1) {
            number = 4;
        } else if (index == 2) {
            number = 9;
        } else if (index == 3) {
            number = 16;
        }
        monitorimg(number);
        if (number == 1) {
            $(".operation").hide();
        }
        initwh();
        $(".mainContent .boxs").click(function () {
            $(this).addClass("blues").siblings().removeClass("blues");
        })
    });
    $(".btns .bgs").click(function () {
        $(this).addClass("qian").siblings().removeClass("qian");
    });
    /*删除*/
    //$(".boxList .boxs .icon-trash").click(function(){
    $(".boxList ").on("click", ".boxs .icon-trash", function () {
        deleteItem();
    });
    $(".cons-right-down .times button").removeClass("small_blue");
    /*放大效果*/
    $(".boxList ").on("click", ".boxs .icon-fullscreen", function () {
        var pic = $(this).closest(".boxs").find("img").attr("src");
        var picClass = $(this).closest('.boxs').find('img').attr('class');
        socketWorkSpace(2,picClass.substring(picClass.lastIndexOf('a')+1));
        $("#new img").attr("src", pic);
        $('#new img').attr('class', picClass);
        $("#new").show();
        $(".left-nav .item.active").addClass("disable").removeClass("active");
        $(".left-nav").addClass("disable");

        $(".cons-right-down .btns .bgs").addClass("blues");
        $(".cons-right-down .btns .bgs:nth-child(4)").addClass("reds");
        $(".cel a i,.cen i,.cen2 i").addClass("blues");
        $(".cons-right-down .times button").addClass("small_blue");
    });
    $(".smart-zoom-out").click(function () {
        socketWorkSpace(1,checkNode);
        $(".enlarge").hide();
        $(".left-nav .item.disable").addClass("active").removeClass("disable");
        $(".left-nav").removeClass("disable");

        $(".cons-right-down .btns .bgs").removeClass("blues");
        $(".cons-right-down .btns .bgs:nth-child(4)").removeClass("reds");
        $(".cel a i,.cen i,.cen2 i").removeClass("blues");
        $(".cons-right-down .times button").removeClass("small_blue");
    });
    var ww = $(".mainContent").width();
    var h1 = (ww - 2) * 0.57 + 12;
    $(".enlarge").css("height", h1);
    /*滑块*/
    var handle = $("#custom-handle");
    $("#slider").slider({
        create: function () {
            handle.text($(this).slider("value"));
        },
        slide: function (event, ui) {
            handle.text(ui.value);
        }
    });
    /******直播回放操作*******/
    var token = window.localStorage.getItem("token");
    /*直播操作方法----start*/
    initOlineOperation();
    $('#moveRight').on('click', function () {
        moveAorB("videoTreeAll", "videoTreeChecked", true);
    });
    $('#moveLeft').on('click', function () {
        moveAorB("videoTreeChecked", "videoTreeAll", false);
    });
    fuzzySearch('videoTreeAll', '#key', null, true); //初始化模糊搜索方法
    fuzzySearch('videoTreeChecked', '#key', null, true); //初始化模糊搜索方法
    //确定添加个数
    $("#sure").click(function () {
        var treeB = $.fn.zTree.getZTreeObj('videoTreeChecked');
        var nodeB = treeB.getNodes();
        if (nodeB.length > 0) {
            var treeArrResults = treeArrayToLevel(nodeB);
            for (var i = 0; i < treeArrResults.length; i++) {
                treeArrResults[i].videoUrl = '/camera' + treeArrResults[i].id;
            }
            checkNode =treeArrResults;
            socketWorkSpace(1,treeArrResults);
            $(".left-nav .item.active").click();
            $('#myModal').modal('hide');
        } else {
            $.jBox.alert('请选择视频');
        }
    });
    var checkNode;
    function initOlineOperation() {
        var settingVideoTree = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            }
        };
        var settingVideoTree1 = {
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            callback: {onClick: zTreeOnClick}
        };
        $.ajax({
            headers: {"token": token},
            url: APIHost + '/admin/video/getAllDevice',
            type: 'POST',
            async: false, //或false,是否异步
            data: {},
            timeout: 5000,
            dataType: 'json',
            success: function (data) {
                var ipData = data.data;
                var ipTrueData = [];
                var ipIndex = 0;
                for (var item in ipData) {
                    var ipObj = {};
                    ipObj.name = item;
                    ipObj.id = 'nason' + ipIndex;
                    ipObj.children = [];
                    var ipDataSec = ipData[item];
                    for (var i = 0; i < ipDataSec.length; i++) {
                        var itemObje = {};
                        itemObje.name = ipDataSec[i].description;
                        itemObje.ip = ipDataSec[i].ip;
                        itemObje.id = ipDataSec[i].id;
                        itemObje.aisle = ipDataSec[i].aisle;
                        itemObje.position = ipDataSec[i].position;
                        itemObje.pId = 'nason' + ipIndex;
                        ipObj.children.push(itemObje)
                    }
                    ipTrueData.push(ipObj);
                    ipIndex++
                }

                $.fn.zTree.init($("#videoTreeAll"), settingVideoTree, ipTrueData);
                $.fn.zTree.init($("#videoTreeChecked"), settingVideoTree);

                /*回放树*/
                $.fn.zTree.init($("#videoTree"), settingVideoTree1, ipTrueData);
            },
            error: function () {
                $.jBox.alert("服务器代码发生异常,请联系管理员!", '提示');
            }
        });
    }

    function zTreeOnClick(event, treeId, treeNode) {
        if (!treeNode.isParent) {
            $('#videoBackName').val(treeNode.name);
            $('#videoBackName').attr('treeId', treeNode.id);
        }
    }

    function moveAorB(A, B, C) {
        var treeA = $.fn.zTree.getZTreeObj(A);
        var nodesA = treeA.getCheckedNodes(true);
        if (nodesA.length == 0) {
            $.jBox.alert("请选择播放设备!", '提示');
            return false;
        }
        var treeB = $.fn.zTree.getZTreeObj(B);
        var maxLevel = 0;
        for (var i = 0; i < nodesA.length; i++) {
            if (nodesA[i].level > maxLevel) {
                maxLevel = nodesA[i].level
            }
        }
        for (var i = 0; i <= maxLevel; i++) {
            for (var j = 0; j < nodesA.length; j++) {
                if (nodesA[j].level == i) {
                    var nodeInfo = {
                        "id": nodesA[j].id,
                        "pId": nodesA[j].pId,
                        "name": nodesA[j].name,
                        "level": nodesA[j].level,
                        "ip": nodesA[j].ip,
                        "position": nodesA[j].position,
                        "aisle": nodesA[j].aisle,
                        "checked": C
                    }
                    var idA = nodesA[j].id;
                    var nodeB = treeB.getNodeByParam("id", idA, null);
                    if (nodeB != null) {
                        nodeB.checked = true;
                        continue;
                    }
                    if (nodesA[j].level == 0) {
                        treeB.addNodes(null, nodeInfo);
                        continue;
                    }
                    var pIdA = nodesA[j].pId;
                    var parentNodeB = treeB.getNodeByParam("id", pIdA, null);
                    treeB.addNodes(parentNodeB, nodeInfo);
                }
            }
        }
        for (var i = 0; i < nodesA.length; i++) {
            if (nodesA[i].isParent) {
                if (nodesA[i].check_Child_State == 2) {
                    treeA.removeNode(nodesA[i]);
                }
            } else {
                treeA.removeNode(nodesA[i]);
            }
        }
        if (!C) {
            var zTree1 = $.fn.zTree.getZTreeObj(B);
            zTree1.checkAllNodes(false);
        } else {
            var zTree2 = $.fn.zTree.getZTreeObj(B);
            zTree2.checkAllNodes(true);
        }
    }
});

function treeArrayToLevel(treeArr) {
    videoOnlineCheckedArr = [];
    for (var i = 0; i < treeArr.length; i++) {
        if (treeArr[i].children && treeArr[i].children.length > 0) {
            treeArrayToLevel(treeArr[i].children)
        } else {
            videoOnlineCheckedArr.push(treeArr[i]);
        }
    }
    return videoOnlineCheckedArr;
}

var socketArr = [];

/********拉取选定视频流信息**********/
var webSocket = null;
function socketWorkSpace(type,checkVideos) {
    if(webSocket){
        webSocket.close();
    }
    var ids = '';
    if(type===1){
        for (var i = 0; i < checkVideos.length; i++) {
            if (i === 0) {
                ids += checkVideos[i].id;
            } else {
                ids += "," + checkVideos[i].id;
            }
        }
    }else{
        ids=checkVideos;
    }
    if ('WebSocket' in window) {
        webSocket = new WebSocket(socketHost + "/rtsp/"+ids);
    }
    webSocket.onerror = function (event) {
        onError(event);
    };
    webSocket.onopen = function (event) {
        onOpen(event);
    };
    webSocket.onmessage = function (event) {
        onMessage(event);
    };

    function onError(event) {

    }

    function onOpen(event) {
        //webSocket.send();// 握手信息
    }

    function onMessage(event) {
        var data = JSON.parse(event.data);
        console.log(data.id);
        $('.camera' + data.id).attr('src', data.base64Code);
    }
}

/************
 * 1. 将视频流显示
 * 2. 默认几个视频流待定
 * **************/
function monitorimg(changImg) {
    /*changImg 模式播放长度*/
    $(".pic" + number + " .boxList").empty();
    if (videoOnlineCheckedArr.length > 0) {
        var arrLength = videoOnlineCheckedArr.length > changImg ? changImg : videoOnlineCheckedArr.length;
        for (var i = 0; i < arrLength; i++) {
            var str = "";
            str += '<div class="div' + number + ' boxs">';
            str += '<div class="nums">' + videoOnlineCheckedArr[i].name + '</div>';
            str += '<img class="camera' + videoOnlineCheckedArr[i].id + '" src="../../static/images/jiankong.jpg" alt=""/>';
            str += '<div class="operation">';
            str += '<i class="icon-fullscreen"></i><b></b>';
            str += '</div></div>';

            $(".pic" + number + " .boxList").append(str);
        }
    } else {
        var str = "";
        str += '<div style="text-align: center;line-height: 493px;font-size: 30px;color: #d5d9da;">请在编辑模式中添加播放设备</div>';
        $(".pic" + number + " .boxList").append(str);
    }

}
window.onunload = function(event) {
    if(webSocket){
        webSocket.close();
    }
}

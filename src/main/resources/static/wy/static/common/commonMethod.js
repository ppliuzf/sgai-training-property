function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
function addItem(modalId,url,iframeId,height) {
    $('#'+ modalId).modal('show');
    var url = APIHost + "/"+ url;
    $("#" + iframeId).attr("src",url);
    $("#" + iframeId).css("height",height+"px");
}
$("input[name='meetingScale']").change(function(){
    $("input[name='meetingScale']").removeAttrs("checked");
    $(this).attr("checked","checked");
    $("input[name='meetingScale']").val($("input[name='meetingScale']:checked").val());
})
Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};
/*TMODJS:{"version":1,"md5":"e2bb86560c997b4870862dadfa23e013"}*/
template('add-meeting-main',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,rrRoomName=$data.rrRoomName,rrRoomPeoples=$data.rrRoomPeoples,deviceDetailVoList=$data.deviceDetailVoList,$each=$utils.$each,itom=$data.itom,$index=$data.$index,meetingTime=$data.meetingTime,time=$data.time,loginUserName=$data.loginUserName,$out='';$out+='<!-- <div class="row margin-bt"> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-5">会议室名称： <span>';
$out+=$escape(rrRoomName);
$out+='</span> </div> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-2">可容纳人数： <span>';
$out+=$escape(rrRoomPeoples);
$out+='人</span> </div> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-5">会议室设施： <div> ';
if(deviceDetailVoList.length>0){
$out+=' 已启用：';
$each(deviceDetailVoList,function(itom,$index){
$out+=' ';
if(itom.rdsState==0){
$out+=' <span href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</span> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </div> <div> ';
if(deviceDetailVoList.length>0){
$out+='已禁用：';
$each(deviceDetailVoList,function(itom,$index){
if(itom.rdsState!=0){
$out+=' <span href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</span> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </div> </div> </div> <div class="row"> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-5">预约会议时间： <span>';
$out+=$escape(meetingTime + ' ' + time);
$out+='</span> </div> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-2">发起人： <span>';
$out+=$escape(loginUserName);
$out+='</span> </div> </div> --> <div class="infos-detail"> <table> <tbody> <tr> <td class="col-lg-2 col-md-2 col-sm-2 text-center">会议室名称：</td> <td class="col-lg-2 col-md-3 col-sm-3 value">';
$out+=$escape(rrRoomName);
$out+='</td> <td class="col-lg-2 col-md-2 col-sm-2 text-center">可容纳人数：</td> <td class="col-lg-2 col-md-3 col-sm-3 value">';
$out+=$escape(rrRoomPeoples);
$out+='人</td> <td class="col-lg-5 col-md-5 col-sm-5 text-center" colspan="2">会议室设施：</td> </tr> <tr> <td class="text-center" colspan="4" rowspan="2"></td> <td class="text-center"> ';
if(deviceDetailVoList.length>0){
$out+=' 已启用：</td> <td class="value"> ';
$each(deviceDetailVoList,function(itom,$index){
if(itom.rdsState==0){
$out+=' <span href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</span> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </td> </tr> <tr> <td class="text-center"> ';
if(deviceDetailVoList.length>0){
$out+='已禁用：</td> <td class="value"> ';
$each(deviceDetailVoList,function(itom,$index){
if(itom.rdsState!=0){
$out+=' <span href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</span> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </td> </tr> <tr> <td class="text-center">预约会议时间：</td> <td class="value">';
$out+=$escape(meetingTime + ' ' + time);
$out+='</td> <td class="text-center">发起人：</td> <td class="value" colspan="3">';
$out+=$escape(loginUserName);
$out+='</td> </tr> </tbody> </table> </div> <style> /* .text-center,.value{ font-size: 12px; display: block; 显示 display:none; 隐藏 } */ </style> ';
return new String($out);
});
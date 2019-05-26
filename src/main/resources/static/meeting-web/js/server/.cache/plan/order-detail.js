/*TMODJS:{"version":1,"md5":"c4d3c65426716bc1feb1a46f33f5fc3c"}*/
template('plan/order-detail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,roomName=$data.roomName,rrRoomPeoples=$data.rrRoomPeoples,deviceDetailVoList=$data.deviceDetailVoList,$each=$utils.$each,itom=$data.itom,$index=$data.$index,miMtDate=$data.miMtDate,miMtTimeSeg=$data.miMtTimeSeg,createEiName=$data.createEiName,miMtSubject=$data.miMtSubject,$string=$utils.$string,miMtContent=$data.miMtContent,inviters=$data.inviters,user=$data.user,materielDtoList=$data.materielDtoList,goods=$data.goods,$out='';$out+='<div class="infos-detail"> <table> <tbody> <tr> <td class=" text-center">会议室名称：</td> <td class=" value">';
$out+=$escape(roomName);
$out+='</td> <td class=" text-center">可容纳人数：</td> <td class=" value">';
$out+=$escape(rrRoomPeoples + ' ');
$out+=' 人</td>  </tr> <tr> <td class=" text-center" rowspan="2">会议室设备：</td>  ';
if(deviceDetailVoList.length>0){
$out+=' <td class="text-center"> 已启用：</td> <td class="value text-center" colspan="2"> ';
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
$out+=' </td> </tr> <tr> <td class="text-center"> 已禁用：</td> <td class="value text-center" colspan="2"> ';
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
$out+=' </td> </tr> ';
}
$out+=' ';
if(deviceDetailVoList.length == 0){
$out+=' <tr> <td colspan="3" class="value" style="text-align: center">无</td> </tr> ';
}
$out+=' <tr> <td class="text-center">预约会议时间：</td> <td class="value">';
$out+=$escape(miMtDate);
$out+=' ';
$out+=$escape(miMtTimeSeg);
$out+='</td> <td class="text-center">发起人：</td> <td class="value" colspan="3">';
$out+=$escape(createEiName);
$out+='</td> </tr> </tbody> </table> </div> <div class="h-box"> <div class="form-group"> <label>会议主题：</label> <div style="word-wrap:break-word;word-break:break-all;">';
$out+=$escape(miMtSubject);
$out+='</div> </div> <div class="form-group"> <label>会议摘要：</label> <div style="word-wrap:break-word;word-break:break-all;">';
$out+=$string( miMtContent || '-');
$out+='</div> </div> <div class="form-group"> <label>参会人：</label> <div class="clearfix order-detail-joiner"> ';
if(inviters && inviters.length){
$out+=' ';
$each(inviters,function(user,$index){
$out+=' <div class="pull-left"> ';
if(user.isDept === 1){
$out+=' <!-- <div class="avatar js-dispatch" data-id="';
$out+=$escape(user.inviterEiId);
$out+='"> --> <div class="js-dispatch" data-id="';
$out+=$escape(user.inviterEiId);
$out+='"> <div class="name">';
$out+=$escape(user.inviterEiName);
$out+='</div> </div> <div class="text-center">未派遣</div> ';
}else{
$out+='  <div class=""> <!-- <img src="';
$out+=$escape(user.inviterUrl || '');
$out+='" alt="';
$out+=$escape(user.inviterEiName);
$out+='"> --> <div class="name">';
$out+=$escape(user.inviterEiName || '佚名');
$out+='</div> </div> <!-- <div class="text-center">';
$out+=$escape($helpers. formatStatus2(user.isInvite ));
$out+='</div> --> ';
}
$out+=' </div> ';
});
$out+=' ';
}else{
$out+=' - ';
}
$out+=' </div> </div> <div class="col-xs-12 col-sm-12 col-md-7 col-lg-9"> <div class="row form-group"> <label>所需物料：</label> ';
if(materielDtoList && materielDtoList.length){
$out+=' ';
$each(materielDtoList,function(goods,$index){
$out+=' <span>';
$out+=$escape(goods.maName);
$out+='</span>&nbsp; ';
});
$out+=' ';
}else{
$out+=' <span>-</span> ';
}
$out+=' </div> </div> </div> <style> .name { cursor: pointer; } .h-box{ margin-top: 20px } </style>';
return new String($out);
});
/*TMODJS:{"version":1,"md5":"9d765805dbbb2519918c9b3ce9c14ce6"}*/
template('order-detail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,roomName=$data.roomName,rrRoomPeoples=$data.rrRoomPeoples,deviceDetailVoList=$data.deviceDetailVoList,$each=$utils.$each,itom=$data.itom,$index=$data.$index,miMtDate=$data.miMtDate,miMtTimeSeg=$data.miMtTimeSeg,createEiName=$data.createEiName,miMtSubject=$data.miMtSubject,$string=$utils.$string,miMtContent=$data.miMtContent,inviters=$data.inviters,user=$data.user,materielDtoList=$data.materielDtoList,goods=$data.goods,$out='';$out+='<!-- <div class="row"> <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10"> <div class="row"> <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4"> <p>会议室名称：</p> <p class="order-detail-title">';
$out+=$escape(roomName);
$out+='</p> </div> <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4"> <p>可容纳人数：</p> <p class="order-detail-title">';
$out+=$escape(rrRoomPeoples + ' ');
$out+=' 人</p> </div> <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4"> <p>会议室设备：</p> <p class="order-detail-title"> <div> ';
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
$out+=' </div> </p> </div> <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4"> <p>预约会议时间：</p> <p class="order-detail-title">';
$out+=$escape(miMtDate);
$out+=' ';
$out+=$escape(miMtTimeSeg);
$out+='</p> </div> <div class="col-xs-6 col-sm-4 col-md-4 col-lg-4"> <p>发起人：</p> <p class="order-detail-title">';
$out+=$escape(createEiName);
$out+='</p> </div> </div> </div> </div> --> <div class="infos-detail"> <table> <tbody> <tr> <td class="col-lg-2 col-md-2 col-sm-2 text-center">会议室名称：</td> <td class="col-lg-2 col-md-3 col-sm-3 value">';
$out+=$escape(roomName);
$out+='</td> <td class="col-lg-2 col-md-2 col-sm-2 text-center">可容纳人数：</td> <td class="col-lg-2 col-md-3 col-sm-3 value">';
$out+=$escape(rrRoomPeoples + ' ');
$out+=' 人</td> <td class="col-lg-5 col-md-5 col-sm-5 text-center" colspan="2">会议室设备：</td> </tr> <tr> <td class="text-center" colspan="4" rowspan="2"></td> <td class="text-center"> ';
if(deviceDetailVoList.length>0){
$out+=' 已启用：</td> <td class="value"> ';
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
$out+=$escape(miMtDate);
$out+=' ';
$out+=$escape(miMtTimeSeg);
$out+='</td> <td class="text-center">发起人：</td> <td class="value" colspan="3">';
$out+=$escape(createEiName);
$out+='</td> </tr> </tbody> </table> </div> <div class="form-group"> <label>会议主题：</label> <div style="word-wrap:break-word;word-break:break-all;">';
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
$out+='</div> </div> <div class="text-center">';
$out+=$escape($helpers. formatStatus2(user.isInvite ));
$out+='</div> ';
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
$out+='         <!--';
$each(materielDtoList,function(goods,$index){
$out+='-->  <!--<td>';
$out+=$escape(goods.maName);
$out+='</td>--> <!--<td>';
$out+=$escape(goods.maCount);
$out+='</td>-->  <!--';
});
$out+='-->   ';
}else{
$out+=' <span>-</span> ';
}
$out+=' </div> </div>';
return new String($out);
});
/*TMODJS:{"version":4,"md5":"176ccc33ca0c114209afd18d9987b1fc"}*/
template('plan/meeting-info',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,rrRoomName=$data.rrRoomName,rrRoomPeoples=$data.rrRoomPeoples,rdRoomDevice=$data.rdRoomDevice,miMtDate=$data.miMtDate,miMtTimeSeg=$data.miMtTimeSeg,createEiName=$data.createEiName,miMtSubject=$data.miMtSubject,miMtContent=$data.miMtContent,$each=$utils.$each,userInfoVoList=$data.userInfoVoList,item=$data.item,$index=$data.$index,materielDtoList=$data.materielDtoList,materielDto=$data.materielDto,$out='';$out+='<div class="row margin-bt text-primary"> <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">会议室名称：<span>';
$out+=$escape(rrRoomName);
$out+='</span></div> <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">可容纳人数：<span>';
$out+=$escape(rrRoomPeoples);
$out+='人</span></div> <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">会议室设施：<span>';
$out+=$escape(rdRoomDevice);
$out+='</span></div> </div> <div class="row margin-bt text-info"> <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">预约会议时间：<span>';
$out+=$escape(miMtDate + ' ' + miMtTimeSeg);
$out+='</span></div> <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">发起人：<span>';
$out+=$escape(createEiName);
$out+='</span></div> </div> <div class="row margin-bt padding-L padding-R text-info">会议主题：<span>';
$out+=$escape(miMtSubject);
$out+='</span></div> <div class="row margin-bt padding-L padding-R text-info">会议摘要：<span>';
$out+=$escape(miMtContent);
$out+='</span></div> <div class="row margin-bt padding-L padding-R text-info">参会人： ';
$each(userInfoVoList,function(item,$index){
$out+=' <span>';
$out+=$escape(item.userName);
$out+='</span> ';
});
$out+=' </div> <div class="col-xs-12 col-sm-12 col-md-5 col-lg-7"> <span class="margin-bt" style="display: inline-block">所需物料:</span> <table class="table table-bordered table-hover table-striped"> <thead> <tr> <th>物料名称</th> <th>数量</th> <th>操作</th> </tr> </thead> <tbody class="js-wuliao-list"> ';
$each(materielDtoList,function(materielDto,$index){
$out+=' <tr> <th>';
$out+=$escape(materielDto.maName);
$out+='</th> <th>';
$out+=$escape(materielDto.maCount);
$out+='</th> <th>-</th> </tr> ';
});
$out+=' </tbody> </table> </div>';
return new String($out);
});
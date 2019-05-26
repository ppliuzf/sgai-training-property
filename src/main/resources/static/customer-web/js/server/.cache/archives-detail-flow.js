/*TMODJS:{"version":1,"md5":"650ddf5ac765753de648c63106e601fb"}*/
template('archives-detail-flow',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,items=$data.items,$escape=$utils.$escape,$each=$utils.$each,item=$data.item,$index=$data.$index,$out='';$out+='<div> ';
if(items.emEnded){
$out+=' <div class="form-group"> <div> <div class="process">创建 <span class="underline blue"></span> </div> ';
if(items.emConfirm){
$out+=' <div class="process">核实 <span class="underline blue"></span> </div> ';
}
$out+=' ';
if(items.emAssign){
$out+=' <div class="process">指派 <span class="underline blue"></span> </div> ';
}
$out+=' ';
if(items.emProcesses.length > 0){
$out+=' <div class="process">处理 <span class="underline blue"></span> </div> ';
}
$out+=' ';
if(items.emComplaintsReturn){
$out+=' <div class="process">回访 <span class="underline blue"></span> </div> ';
}
$out+=' ';
if(items.emEnded){
$out+=' <div class="process">终止 <span class="underline bg-red"></span> </div> ';
}
$out+=' </div> </div> ';
}else{
$out+=' <div class="form-group"> <div> <div class="process">';
$out+=$escape(items.wfInstanceRecords[0].stepName);
$out+='</div> <div class="process">';
$out+=$escape(items.wfInstanceRecords[1].stepName);
$out+='</div> <div class="process">';
$out+=$escape(items.wfInstanceRecords[2].stepName);
$out+='</div> <div class="process">';
$out+=$escape(items.wfInstanceRecords[3].stepName);
$out+='</div> </div> <div> <div class="underline-no ';
$out+=$escape(items.wfInstanceRecords[0].stepCode ? 'blue' : '');
$out+='"></div> <div class="underline-no ';
$out+=$escape(items.wfInstanceRecords[1].stepCode ? 'blue' : '');
$out+='"></div> <div class="underline-no ';
$out+=$escape(items.wfInstanceRecords[2].stepCode ? 'blue' : '');
$out+='"></div> <div class="underline-no ';
$out+=$escape(items.wfInstanceRecords[3].stepCode ? 'blue' : '');
$out+='"></div> </div> </div> ';
}
$out+=' <ul class="form-group"> <li class="event-flow"> <div class="time"> ';
$out+=$escape(items.emEventCreateVo.emTime.date);
$out+=' <br/> ';
$out+=$escape(items.emEventCreateVo.emTime.time);
$out+=' </div> ';
if(items.emConfirm || items.emAssign || items.emEnded){
$out+=' <span class="bypass"></span> ';
}
$out+=' <div class="event"> <span></span> 创建 <span></span> </div> <table class="other"> <tr class="text-top"> <td class="end-time">事件编号：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.emCode || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">事件类别：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.emType || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">';
$out+=$escape(items.emEventCreateVo.emType == "TS" ? "投诉" : items.emEventCreateVo.emType == "WX" ? "报修" : "报案");
$out+='人：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.emPerson || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">联系电话：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.telphone || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">';
$out+=$escape(items.emEventCreateVo.emType == "TS" ? "投诉" : items.emEventCreateVo.emType == "WX" ? "报修" : "报案");
$out+='地址：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.address || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">';
$out+=$escape(items.emEventCreateVo.emType == "TS" ? "投诉" : items.emEventCreateVo.emType == "WX" ? "报修" : "报案");
$out+='内容：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.emContent || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">备注：</td> <td class="end-val">';
$out+=$escape(items.emEventCreateVo.desc || "--");
$out+='</td> </tr> </table> </li>  ';
if(items.emConfirm){
$out+=' <li class="event-flow"> <div class="time"> ';
$out+=$escape(items.emConfirm.confirmDate.date);
$out+=' <br> ';
$out+=$escape(items.emConfirm.confirmDate.time);
$out+=' </div> ';
if(items.emAssign || items.emEnded){
$out+=' <span class="bypass"></span> ';
}
$out+=' <div class="event"> <span></span> 核实 <span></span> </div> <table class="other"> <tr class="text-top"> <td class="end-time">核实时间：</td> <td class="end-val">';
$out+=$escape(items.emConfirm.confirmDate.date);
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">核实人：</td> <td class="end-val">';
$out+=$escape(items.emConfirm.confirmPerson || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">备注：</td> <td class="end-val">';
$out+=$escape(items.emConfirm.confirmContent || "--");
$out+='</td> </tr> </table> </li> ';
}
$out+='  ';
if(items.emAssign){
$out+=' <li class="event-flow"> <div class="time"> ';
$out+=$escape(items.emAssign.assignDatetime.date);
$out+=' <br> ';
$out+=$escape(items.emAssign.assignDatetime.time);
$out+=' </div> ';
if(items.emProcesses.length > 0 || items.emEnded){
$out+=' <span class="bypass"></span> ';
}
$out+=' <div class="event"> <span></span> 指派 <span></span> </div> <table class="other"> <tr class="text-top"> <td class="end-time">指派给：</td> <td class="end-val">';
$out+=$escape(items.emAssign.assignPerson || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">备注：</td> <td class="end-val">';
$out+=$escape(items.emAssign.assignDesc || "--");
$out+='</td> </tr> </table> </li> ';
}
$out+='  ';
if(items.emProcesses.length > 0){
$out+=' <li class="event-flow"> <div class="time"> ';
$out+=$escape(items.emProcesses[0].repairDatetime.date);
$out+=' <br> ';
$out+=$escape(items.emProcesses[0].repairDatetime.time);
$out+=' </div> ';
if(items.emComplaintsReturn || (!items.emConfirm && items.emEnded)){
$out+=' <span class="bypass"></span> ';
}
$out+=' <div class="event"> <span></span> 处理 <span></span> </div> <table class="other"> ';
$each(items.emProcesses,function(item,$index){
$out+=' <tr class="text-top"> <td class="end-time">完成时间：</td> <td class="end-val">';
$out+=$escape(item.repairDatetime.date);
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">处理说明：</td> <td class="end-val">';
$out+=$escape(item.repairContent);
$out+='</td> </tr> ';
});
$out+=' </table> </li> ';
}
$out+='  ';
if(items.emComplaintsReturn){
$out+=' <li class="event-flow"> <div class="time"> ';
$out+=$escape(items.emComplaintsReturn.returnTime.date);
$out+=' <br> ';
$out+=$escape(items.emComplaintsReturn.returnTime.time);
$out+=' </div>  <div class="event"> <span></span> 回访 <span></span> </div> <table class="other"> <tr class="text-top"> <td class="end-time">回访时间：</td> <td class="end-val">';
$out+=$escape(items.emComplaintsReturn.returnTime.date || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">回访内容：</td> <td class="end-val">';
$out+=$escape(items.emComplaintsReturn.returnContent || "--");
$out+='</td> </tr> </table> </li> ';
}
$out+='  ';
if(items.emEnded){
$out+=' <li class="event-flow"> <div class="time"> ';
$out+=$escape(items.emEnded.endTime.date);
$out+=' <br> ';
$out+=$escape(items.emEnded.endTime.time);
$out+=' </div>  <div class="event end-color"> <span></span> 终止 <span></span> </div> <table class="other"> <tr class="text-top"> <td class="end-time">终止时间：</td> <td class="end-val">';
$out+=$escape(items.emEnded.endTime.date || "--");
$out+='</td> </tr> <tr class="text-top"> <td class="end-time">终止原因：</td> <td class="end-val">';
$out+=$escape(items.emEnded.endReason || "--");
$out+='</td> </tr> </table> </li> ';
}
$out+=' </ul> </div> <style> .time, .event, .other { float: left; } .event-flow { width: 100%; overflow: hidden; position: relative; padding-bottom: 20px; } .text-top { vertical-align: top } .time { width: 15%; text-align: center } .event { width: 20%; text-align: center; color: #1785ca; font-weight: 500 } .bypass { display: block; position: absolute; width: 1px; background-color: #1785ca; left: 35px; top: 45px; bottom: -25px; } .other { width: 65%; } .form-group { overflow: hidden; width: 100%; } .end-time { width: 25% } .end-val { width: 75% } .process { float: left; width: 24%; text-align: left; margin: 0 4px 5px 0; height: 26px; } .underline { width: 100%; height: 5px; border-radius: 2px; margin-right: 2px; background-color: #eee; display: inline-block; margin-bottom:10px; margin-top: 4px; } .underline-no { width: 24%; height: 5px; float: left; border-radius: 2px; margin-right: 2px; background-color: #eee; } .blue { background-color: #1785ca; } .bg-red { background-color: red; } .event>span { display: inline-block; /* flex: 1; */ position: relative; top: -3px; width: 20px; border-bottom: 1px solid #eaf1f5; } .end-color { color: red; } </style>';
return new String($out);
});
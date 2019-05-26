/*TMODJS:{"version":1,"md5":"808e63b0f07f8c33f41695d3e8997049"}*/
template('questionnaire-detail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,smName=$data.smName,smEndTime=$data.smEndTime,$each=$utils.$each,surveyQuestionVOs=$data.surveyQuestionVOs,item=$data.item,$index=$data.$index,data=$data.data,$out='';$out+='<dl> <dt> <h3 class="wrap-word thdText">';
$out+=$escape(smName);
$out+='</h3> <div class="meta">结束时间：';
$out+=$escape(smEndTime);
$out+='</div> </dt> <dd class="detail-box"> ';
$each(surveyQuestionVOs,function(item,$index){
$out+=' <div class="item"> <h4 class="wrap-word thdText" style="font-size: 12px"> 第';
$out+=$escape(item.sqNo);
$out+='题：';
$out+=$escape(item.sqTopic);
$out+=' <span class="tip-txt"> ';
if(item.sqType===0){
$out+=' (单选) ';
}else if(item.sqType===1){
$out+=' (多选) ';
}
$out+=' </span> </h4> ';
if(item.sqType===2){
$out+=' <div class="form-posi"> <textarea class="form-control" rows="6" disabled="disabled"> </textarea> <div class="list-posi"> <span id="text-count2" value="">0</span>/200 </div> </div> ';
}else{
$out+=' <ul class="q-list"> ';
$each(item.newContent,function(data,$index){
$out+=' <li class="wrap-word"> ';
if(item.sqType===0){
$out+=' <input type="radio" disabled> ';
}else if(item.sqType===1){
$out+=' <input type="checkbox" disabled> ';
}
$out+='  <span class=" text">';
$out+=$escape(data);
$out+='</span> </li> ';
});
$out+=' </ul> ';
}
$out+=' </div> ';
});
$out+=' </dd> </dl> <style> .star { font-size: 20px; color: #666; padding-bottom: 0px; position: absolute; top: 0px; left: -20px; } .text { font-size: 12px; color: #666; /* font-weight: 600; */ position: absolute; top: 1px; left: 20px; } .thdText { font-size: 14px; font-weight: 700; color: #666; } .meta { color: #666; } .detail-box { padding-top: 10px; } .wrap-word { line-height: 18px; word-wrap: break-word; position: relative; } .tip-txt { font-size: 14px; } .form-posi { position: relative; } .list-posi { position: absolute; bottom: 5px; right: 10px; } .q-list { list-style: none; margin: 0; padding: 0; } .q-list { padding: 0 0 10px 0px; } .q-list li { padding-bottom: 5px; } </style>';
return new String($out);
});
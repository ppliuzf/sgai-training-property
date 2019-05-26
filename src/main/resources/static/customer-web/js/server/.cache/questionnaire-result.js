/*TMODJS:{"version":1,"md5":"97fc54756a156bbdb7b49ed9803ff916"}*/
template('questionnaire-result',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,smName=$data.smName,smEndTime=$data.smEndTime,smCount=$data.smCount,sqNo=$data.sqNo,sqTopic=$data.sqTopic,sqType=$data.sqType,$each=$utils.$each,textAnswerVoPageInfo=$data.textAnswerVoPageInfo,data=$data.data,index=$data.index,optionInfoPageInfo=$data.optionInfoPageInfo,$index=$data.$index,arrayNo=$data.arrayNo,$out='';$out+='<dl> <dt> <h3 class="wrap-word">';
$out+=$escape(smName);
$out+='</h3> <div class="meta">结束时间：';
$out+=$escape(smEndTime);
$out+='</div> </dt> <dd class="detail-box"> <div class="item"> <h4 style="font-size: 14px;" class="wrap-word" id="wrap-word" data-cou="';
$out+=$escape(smCount);
$out+='" data-sq="';
$out+=$escape(sqNo);
$out+='"> 第';
$out+=$escape(sqNo);
$out+='题：';
$out+=$escape(sqTopic);
$out+=' </h4> <div class="panel panel-default"> <div class="panel-heading"> <h1 class="panel-title">调查结果</h1> </div> <div class="panel-body"> ';
if(sqType===2){
$out+=' <div class="container-fluid"> <table class="table table-bordered table-hover"> <thead> <tr> <th class="text-center">序号</th> <th class="text-center">ID</th> <th class="text-center">IP地址</th> <th class="text-center">答题人姓名</th> <th class="text-center">答题人手机号</th> <th class="text-center">投票时间</th> <th width="92" class="text-center">操作</th> </tr> </thead> <tbody class="js-list" id="text-detail"> ';
$each(textAnswerVoPageInfo.list,function(data,index){
$out+=' <tr> <td class="text-center">';
$out+=$escape(index+1);
$out+='</td> <td class="text-center">';
$out+=$escape(data.userId);
$out+='</td> <td width="350" class="text-center">';
$out+=$escape(data.ip);
$out+='</td> <td class="text-center">';
$out+=$escape(data.userName);
$out+='</td> <td class="text-center">';
$out+=$escape(data.userPhone);
$out+='</td> <td class="text-center">';
$out+=$escape(data.saAnswerTime);
$out+='</td> <td class="text-center text-detail" data-txt="';
$out+=$escape(data.saContent);
$out+='" data-toggle="modal" data-target="#quest-result-modal"> <a href="javascript:;">查看详情</a> </td> </tr> ';
});
$out+=' </tbody> </table> <nav class="text-right pages"></nav> ';
}else{
$out+=' <ul class="q-list col-xs-12 col-sm-11 col-md-6 col-lg-6"> ';
$each(optionInfoPageInfo.list,function(data,$index){
$out+=' <li class="wrap-word"> <span>';
$out+=$escape(data.saContent);
$out+='</span> <div class="row"> <div class=" col-xs-10 col-sm-9 col-md-6 col-lg-6"> <div class="progress"> <div class="progress-bar ';
$out+=$escape(data.color);
$out+='" role="progressbar" aria-valuenow="';
$out+=$escape(data.scale);
$out+='" aria-valuemin="0" aria-valuemax="100" style=\'width:';
$out+=$escape(data.scale);
$out+='\'> <span class="sr-only ">(';
$out+=$escape(data.scale);
$out+=')</span> </div> </div> </div> <div class="col-xs-1 col-sm-1 col-md-2 col-lg-2 "> <span>';
$out+=$escape(data.count);
$out+='票</span> </div> <div class="col-xs-1 col-sm-1 col-md-2 col-lg-2 "> <span>(';
$out+=$escape(data.scale);
$out+=')</span> </div> <div class="col-xs-1 col-sm-1 col-md-2 col-lg-2 "> <a href="javascript:;" data-toggle="modal" data-target="#quest-result-modal" class="res-modal" data-saContent="';
$out+=$escape(data.saContent);
$out+='" data-count="';
$out+=$escape(data.count);
$out+='" data-scale="';
$out+=$escape(data.scale);
$out+='" data-color="';
$out+=$escape(data.color);
$out+='">详情</a> </div> </div> </li> ';
});
$out+=' </ul> ';
}
$out+=' </div> </div> </div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="btn-toolbar" role="toolbar" aria-label="..."> <div class="btn-group" role="group"> ';
if(sqNo===1&&smCount===1){
$out+=' <button type="button" data-cla="result-up" class="btn btn-primary btn-sm disabled">上一题</button> ';
}else if(sqNo===1&&smCount!==1){
$out+=' <button type="button" data-cla="result-up" class="btn btn-primary btn-sm disabled">上一题</button> ';
}else{
$out+=' <button type="button" data-cla="result-up" class="btn btn-primary btn-sm result-up">上一题</button> ';
}
$out+=' </div> <div class="btn-group" role="group"> ';
if(sqNo===1&&smCount===1){
$out+=' <button type="button" data-cla="result-next" class="btn btn-primary btn-sm disabled">下一题</button> ';
}else if(sqNo===smCount&&smCount!==1){
$out+=' <button type="button" data-cla="result-next" class="btn btn-primary btn-sm disabled">下一题</button> ';
}else{
$out+=' <button type="button" data-cla="result-next" class="btn btn-primary btn-sm result-next">下一题</button>';
}
$out+=' </div> <div class="btn-group dropup" role="group"> ';
if(sqNo===1&&smCount===1){
$out+=' <button type="button" class="btn btn-primary dropdown-toggle btn-sm disabled"> 跳至</button> ';
}else{
$out+=' <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">跳至</button> <ul class="dropdown-menu" id="pageNum"> ';
$each(arrayNo,function(data,$index){
$out+=' <li> ';
if(data.index==sqNo){
$out+=' <a href=\'javascript:;\' data-cla=\'result-page\' data-page="';
$out+=$escape(data.index);
$out+='" class=\'result-page\'></a> ';
}else{
$out+=' <a href=\'javascript:;\' data-cla=\'result-page\' data-page="';
$out+=$escape(data.index);
$out+='" class=\'result-page\'>第';
$out+=$escape(data.index);
$out+='题</a>';
}
$out+=' </li> ';
});
$out+=' </ul> ';
}
$out+=' </div> </div> </div> </dd> </dl> <style> .dropdown-menu{ max-height: 200px; overflow: scroll; } .success, .info, .striped, .danger, .warning{ background-color: #6EA9E2 } /* .info{ background-color: #2F7DCA } .striped{ background-color: #7F4ED8 } .danger{ background-color: #4F3DAE } .warning{ background-color: #2B1E76 } */ .meta { color: #666; font-size: 12px } .detail-box { padding-top: 10px; } .panel-title{ font-size: 14px; color: #666; font-weight: 600 } .wrap-word { word-wrap: break-word; font-size: 14px; color: #666; font-weight: 600 } .tip-txt { font-size: 14px; } .q-list { list-style: none; margin: 0; padding: 0; } .q-list { padding: 0 0 10px 15px; } .q-list li { padding-bottom: 10px; } </style>';
return new String($out);
});
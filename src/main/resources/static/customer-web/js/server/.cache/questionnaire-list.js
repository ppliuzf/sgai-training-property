/*TMODJS:{"version":1,"md5":"143761f6c064cbfd27c08ed8a0a3848a"}*/
template('questionnaire-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,items=$data.items,$each=$utils.$each,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,text=$data.text,$out='';$out+='<table class="table table-bordered table-hover"> <thead> <tr> <th width="60" class="text-center">序号</th> <th>编号</th> <th>问卷名称</th> <th>创建人</th> <th>创建日期</th> <th>结束时间</th> <th>状态</th> <th width="140" class="text-center">操作</th> </tr> </thead> <tbody class="js-lintt"> ';
if(items.length>0){
$out+=' ';
$each(items,function(item,itemIndex){
$out+=' <tr> <td class="text-center">';
$out+=$escape($helpers. fillZero(itemIndex ));
$out+='</td> <td> <a href="./questionnaire-detail.html?id=';
$out+=$escape(item.smId);
$out+='">';
$out+=$escape(item.surveyNo);
$out+='</a> </td> <td class="wrap-word" width="350">';
$out+=$escape(item.smName);
$out+='</td> <td>';
$out+=$escape(item.smCreatorName);
$out+='</td> <td>';
$out+=$escape(item.createdDt);
$out+='</td> <td>';
$out+=$escape(item.smEndTime);
$out+='</td> <td> ';
if(item.smStatus === 0){
$out+=' 未开始 ';
}
$out+=' ';
if(item.smStatus === 1){
$out+=' 进行中 ';
}
$out+=' ';
if(item.smStatus === 2){
$out+=' 已结束 ';
}
$out+=' </td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多</button> <ul class="dropdown-menu"> ';
if(item.smStatus === 0 || item.smStatus===null){
$out+=' <li> <a href="./questionnaire-add.html?id=';
$out+=$escape(item.smId);
$out+='" class="js-edit">编辑</a> </li> <li> <a href="javascript:;" class="js-start" data-id="';
$out+=$escape(item.smId);
$out+='">开始调查</a> </li> <li> <a href="javascript:;" class="js-del" data-id="';
$out+=$escape(item.smId);
$out+='">删除</a> </li> ';
}else if(item.smStatus === 1){
$out+=' <li> <a href="./questionnaire-quest.html?id=';
$out+=$escape(item.smId);
$out+='" class="js-found" data-id="';
$out+=$escape(item.smId);
$out+='">录入结果</a> </li> <li> <a href="javascript:;" class="js-end" data-id="';
$out+=$escape(item.smId);
$out+='">结束</a> </li> ';
}else if(item.smStatus === 2){
$out+=' <li> <a href="./questionnaire-result.html?id=';
$out+=$escape(item.smId);
$out+='" class="js-res">调查结果</a> </li> <li> <a href="javascript:;" class="js-del" data-id="';
$out+=$escape(item.smId);
$out+='">删除</a> </li> ';
}
$out+=' </ul> </div> </td> --> <td class="text-center"> ';
if(item.smStatus === 0 || item.smStatus===null){
$out+=' <a href="./questionnaire-add.html?id=';
$out+=$escape(item.smId);
$out+='" class="js-edit">编辑</a> <a href="javascript:;" class="js-start" data-id="';
$out+=$escape(item.smId);
$out+='">开始调查</a> <a href="javascript:;" class="js-del" data-id="';
$out+=$escape(item.smId);
$out+='">删除</a> ';
}else if(item.smStatus === 1){
$out+=' <a href="./questionnaire-quest.html?id=';
$out+=$escape(item.smId);
$out+='" class="js-found" data-id="';
$out+=$escape(item.smId);
$out+='">录入结果</a> <a href="javascript:;" class="js-end" data-id="';
$out+=$escape(item.smId);
$out+='">结束</a> ';
}else if(item.smStatus === 2){
$out+=' <a href="./questionnaire-result.html?id=';
$out+=$escape(item.smId);
$out+='" class="js-res">调查结果</a> <a href="javascript:;" class="js-del" data-id="';
$out+=$escape(item.smId);
$out+='">删除</a> ';
}
$out+=' </td> </tr> ';
});
$out+=' ';
}else{
$out+=' <tr> <td colspan="8" class="text-center">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr> ';
}
$out+=' </tbody> </table> ';
if(items.length>0){
$out+=' <nav class="text-right pages"></nav> ';
}
$out+=' <style> .wrap-word { word-wrap: break-word; word-break: break-all; } </style>';
return new String($out);
});
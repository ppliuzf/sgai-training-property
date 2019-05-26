/*TMODJS:{"version":12,"md5":"db5e4091b08649406761d08ceb7e79c8"}*/
template('plan/detail-right',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,task=$data.task,$out='';$each(items,function(item,$index){
$out+=' ';
if(items.length>1){
$out+=' <tr> <td colspan="7" class="head" style="background: #f9f9f9">';
$out+=$escape(item.periodName);
$out+='</td> </tr> ';
}
$out+=' ';
$each(item.taskList,function(task,$index){
$out+=' <tr class="item sort-';
$out+=$escape(item.periodId);
$out+=' s';
$out+=$escape(task.id);
$out+='" data-id="';
$out+=$escape(task.id);
$out+='"> <td> <div class="cont dot" title="';
$out+=$escape(task.taskName);
$out+='">';
$out+=$escape(task.taskName);
$out+='</div> </td> <td> ';
if(task.taskType == 1){
$out+=' <div class="cont dot" title="';
$out+=$escape(task.taskType);
$out+='">执行</div> ';
}else{
$out+=' <div class="cont dot" title="';
$out+=$escape(task.taskType);
$out+='">检验</div> ';
}
$out+=' </td> <td> <div class="cont dot" title="';
$out+=$escape(task.viewStat);
$out+='">';
$out+=$escape(task.viewState);
$out+='</div> </td> <td> <div class="cont dot" title="';
$out+=$escape($helpers. dateFormat(task.taskBeginTime ,  'yyyy-MM-dd'));
$out+='">';
$out+=$escape($helpers. dateFormat(task.taskBeginTime ,  'yyyy-MM-dd'));
$out+='</div> </td> <td> <div class="cont dot" title="';
$out+=$escape($helpers. dateFormat(task.taskEndTime ,  'yyyy-MM-dd'));
$out+='">';
$out+=$escape($helpers. dateFormat(task.taskEndTime ,  'yyyy-MM-dd'));
$out+='</div> </td> <td> <div class="cont dot" title="';
$out+=$escape(task.taDutyEiEmpName);
$out+='">';
$out+=$escape(task.taskCreatorEiEmpName);
$out+='</div> </td> <td class="poin-td act"> <div class="btn-group"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">查看更多 <span class="caret"></span></button> <ul class="dropdown-menu"> ';
if(task.viewState != '执行中' && task.viewState != '已完成'){
$out+=' <li><a class="js-del underline" data-taId="';
$out+=$escape(task.id);
$out+='" data-state="';
$out+=$escape(task.isAppror);
$out+='">删除</a></li> ';
}
$out+=' <li><a class="js-down underline js-movedown" data-id="s';
$out+=$escape(task.id);
$out+='">下移</a></li> <li><a class="js-up underline" data-id="s';
$out+=$escape(task.id);
$out+='">上移</a></li> <li><a class="js-moveto underline" data-id="';
$out+=$escape(task.id);
$out+='">移动至</a></li> </ul> </div> </td> </tr> ';
});
$out+=' ';
});
return new String($out);
});
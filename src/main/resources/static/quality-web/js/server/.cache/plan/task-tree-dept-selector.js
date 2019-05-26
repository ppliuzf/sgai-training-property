/*TMODJS:{"version":18,"md5":"dee3887a961ec62da1fddabc479c3e64"}*/
template('plan/task-tree-dept-selector',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,title=$data.title,name=$data.name,$string=$utils.$string,value=$data.value,dept=$data.dept,emp=$data.emp,all=$data.all,$out='';$out+='<div class="task-tree multi-selector clean search"> <div class="btn-search js-';
$out+=$escape(title);
$out+='-search 000"> <div class="content">';
$out+=$escape(name);
$out+='</div> <div class="inner js-';
$out+=$escape(title);
$out+='-selector" style="display: none;"> ';
$out+=$string( value);
$out+=' </div> <input type="hidden" class="js-';
$out+=$escape(title);
$out+='-dept" value="';
$out+=$escape(dept);
$out+='"> <input type="hidden" class="js-';
$out+=$escape(title);
$out+='-emp" value="';
$out+=$escape(emp);
$out+='"> <input type="hidden" class="js-';
$out+=$escape(title);
$out+='-all" value="';
$out+=$escape(all);
$out+='"> </div> </div> <style> .task-tree { height:auto; border:0; } .task-tree ul{ position:absolute; z-index:100; border:0; left:0; padding:0; background:none; } .task-tree ul li.btn-search{ display:block; position:static; border:0; } .task-tree .btn-search{ height:auto; width:100%; } /*.glyphicon-search:before{*/ /*display:none;*/ /*}*/ .task-tree .btn-search{ } .multi-selector .btn-search{ background: #fff; border:0; } </style>';
return new String($out);
});
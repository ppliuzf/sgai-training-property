/*TMODJS:{"version":1,"md5":"567e7ccfd02b8ceaf60b60ae551ee2fd"}*/
template('common/dept-selector',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,name=$data.name,$string=$utils.$string,value=$data.value,dept=$data.dept,emp=$data.emp,all=$data.all,$out='';$out+='<div class="multi-selector clean search"> <div class="inner js-';
$out+=$escape(name);
$out+='-selector"> ';
$out+=$string( value);
$out+=' </div> <div class="btn-search js-';
$out+=$escape(name);
$out+='-search"><i class="glyphicon glyphicon-search"></i></div> <div class="btn-clean js-';
$out+=$escape(name);
$out+='-clean"><i class="glyphicon glyphicon-remove-sign"></i></div> <input type="hidden" class="js-';
$out+=$escape(name);
$out+='-dept" value="';
$out+=$escape(dept);
$out+='"> <input type="hidden" class="js-';
$out+=$escape(name);
$out+='-emp" value="';
$out+=$escape(emp);
$out+='"> <input type="hidden" class="js-';
$out+=$escape(name);
$out+='-all" value="';
$out+=$escape(all);
$out+='"> </div>';
return new String($out);
});
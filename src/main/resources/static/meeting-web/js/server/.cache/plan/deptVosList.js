/*TMODJS:{"version":1,"md5":"c2b7e14c45f1cdbd83dfec617e1b7bc8"}*/
template('plan/deptVosList',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<div class="deptVos-list"> <div class="checkbox deptVos-item js-checked-all"> <label> <input type="checkbox"> 全选 </label> </div> ';
$each(items,function(item,$index){
$out+=' <div class="checkbox deptVos-item"> <label> <input type="checkbox" data-id="';
$out+=$escape(item.empCode);
$out+='" data-title="';
$out+=$escape(item.title);
$out+='"> <i class="glyphicon glyphicon-user"></i> ';
$out+=$escape(item.lastname);
$out+=' </label> </div> ';
});
$out+=' </div>';
return new String($out);
});
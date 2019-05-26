/*TMODJS:{"version":1,"md5":"dcac0180f3adb52a2633f3b7007406fc"}*/
template('archives-detail-card-edit',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,value=$data.value,number=$data.number,$out='';$out+='<div class="form-group"> <select id="card-type" class="form-control"> ';
$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.value);
$out+='" ';
$out+=$escape(item.value == value && 'selected');
$out+='>';
$out+=$escape(item.title);
$out+='</option> ';
});
$out+=' </select> </div> <div class="form-group"> <input type="text" id="card-number" class="form-control" value="';
$out+=$escape(number);
$out+='" maxlength="100"> </div>';
return new String($out);
});
/*TMODJS:{"version":1,"md5":"948569f32dbaafd4efb2aaf4be1ae19f"}*/
template('archives-assign-person',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<select id="card-complain" class="form-control js-card-type"> ';
$each(items,function(item,$index){
$out+=' <option data-code="';
$out+=$escape(item.userCode);
$out+='">';
$out+=$escape(item.userName);
$out+='</option> ';
});
$out+=' </select>';
return new String($out);
});
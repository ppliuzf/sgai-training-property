/*TMODJS:{"version":2,"md5":"7c0fdcc38432bd6df2c3b1ee14db686d"}*/
template('archives-event-type.1',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<select id="card-complain" class="form-control js-card-type"> ';
$each(items,function(item,$index){
$out+=' <option data-type="';
$out+=$escape(item.codeCode);
$out+='">';
$out+=$escape(item.codeName);
$out+='</option> ';
});
$out+=' </select>';
return new String($out);
});
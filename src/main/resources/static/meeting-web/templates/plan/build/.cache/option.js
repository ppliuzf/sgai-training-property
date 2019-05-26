/*TMODJS:{"version":1,"md5":"95e9157484b6127a18588c095820e048"}*/
template('option',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <option value="';
$out+=$escape(idx);
$out+='">';
$out+=$escape(item);
$out+='</option> ';
});
return new String($out);
});
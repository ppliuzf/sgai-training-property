/*TMODJS:{"version":2,"md5":"5d9b29bfe297bb40a31956fdef938fb8"}*/
template('contanct-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td style="text-align:center;">';
$out+=$escape(itemIndex+1 < 10 ? "0" + (itemIndex+1) : itemIndex+1);
$out+='</td> <td> <div class="cont dot" title="';
$out+=$escape(item.no);
$out+='"> <a href="./contract-detail.html?id=';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.no);
$out+='</a></div></td> <td> <div class="cont dot HH" title="';
$out+=$escape(item.name);
$out+='">';
$out+=$escape(item.name);
$out+='</div></td> <td> <div class="cont dot" title="';
$out+=$escape(item.typeName);
$out+='">';
$out+=$escape(item.typeName);
$out+='</div></td> <td> <div class="cont dot" title="';
$out+=$escape(item.creatTime);
$out+='">';
$out+=$escape(item.creatTime);
$out+='</div></td> <td> <div class="cont dot" title="';
$out+=$escape(item.creater);
$out+='">';
$out+=$escape(item.creater);
$out+='</div></td> <td> ';
if(item.status === 1 ){
$out+=' <div class="cont dot" title="';
$out+=$escape(item.status);
$out+='">未签约</div> ';
}else{
$out+=' <div class="cont dot" title="';
$out+=$escape(item.status);
$out+='">已签约</div> ';
}
$out+=' </td> </tr> ';
});
return new String($out);
});
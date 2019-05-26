/*TMODJS:{"version":2,"md5":"0200260e65384da43ed13057cb553b9b"}*/
template('archives/ther',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td style="text-align:center;">';
$out+=$escape(itemIndex+1 < 10 ? "0" + (itemIndex+1) : itemIndex+1);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.punishTime);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.punishType);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.punishUserName);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.punishUserPhoneNum);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.punishContent);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.punishDesc);
$out+='</td> </tr> ';
});
$out+=' ';
return new String($out);
});
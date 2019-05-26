/*TMODJS:{"version":2,"md5":"3ffc1cae8d2b613dd0b3a785917ef9fe"}*/
template('archives/two',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td style="text-align:center;">';
$out+=$escape(itemIndex+1 < 10 ? "0" + (itemIndex+1) : itemIndex+1);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.rrDate);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.rrName);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.rrPhoneNum);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.rrAddress);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.rrContent);
$out+='</td> <td class="word-warp">';
$out+=$escape(item.rrRemark);
$out+='</td> </tr> ';
});
$out+=' ';
return new String($out);
});
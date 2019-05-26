/*TMODJS:{"version":1,"md5":"e54e27d2b78946d6b698e3536213a1a6"}*/
template('archives/archives-user-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td style="text-align:center;">';
$out+=$escape(itemIndex+1 < 10 ? "0" + (itemIndex+1) : itemIndex+1);
$out+='</td> <td>';
$out+=$escape(item.riUserName);
$out+='</td> <td>';
$out+=$escape(item.riUserPhone);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.riUseStart ,  'yyyy-MM-dd'));
$out+='</td> <td> ';
if(item.riAuditStatus===4){
$out+=' ';
$out+=$escape($helpers. dateFormat(item.riUseEnd ,  'yyyy-MM-dd'));
$out+=' ';
}else{
$out+=' <span>-</span> ';
}
$out+=' </td> <td class="word-warp">';
$out+=$escape(item.riUsePurpose);
$out+='</td> </tr> ';
});
return new String($out);
});
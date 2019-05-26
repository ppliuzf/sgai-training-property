/*TMODJS:{"version":2,"md5":"885a4feb34274940eb0faab34da5ae1d"}*/
template('archives/one',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td style="text-align:center;">';
$out+=$escape(itemIndex+1 < 10 ? "0" + (itemIndex+1) : itemIndex+1);
$out+='</td> <td class="word-warp"><div class=\'r\'>';
$out+=$escape(item.maintainName);
$out+='</div></td> <td class="word-warp"><div class=\'r\'>';
$out+=$escape(item.maintainType);
$out+='</div></td> <td class="word-warp"><div class=\'r\'>';
$out+=$escape(item.maintainTime);
$out+='</div></td> <td class="word-warp"><div class=\'r\'>';
$out+=$escape(item.maintainComName);
$out+='</div></td> <td class="word-warp"><div class=\'r\'>';
$out+=$escape(item.maintainUserName);
$out+='</div></td> <td class="word-warp"><div class=\'r\'>';
$out+=$escape(item.maintainDesc);
$out+='</div></td> </tr> ';
});
$out+=' ';
return new String($out);
});
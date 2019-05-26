/*TMODJS:{"version":2,"md5":"02b44bac1bf909277d61726770b4866e"}*/
template('templates/common/record-empty',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,colspan=$data.colspan,text=$data.text,$out='';$out+=' <tr> <td colspan="';
$out+=$escape(colspan);
$out+='" class="text-center">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr>';
return new String($out);
});
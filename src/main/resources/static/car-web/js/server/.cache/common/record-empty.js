/*TMODJS:{"version":1,"md5":"13d381f60810bca26ec970a2024e9cfe"}*/
template('common/record-empty',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,colspan=$data.colspan,text=$data.text,$out='';$out+=' <tr> <td colspan="';
$out+=$escape(colspan);
$out+='" class="text-center">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr>';
return new String($out);
});
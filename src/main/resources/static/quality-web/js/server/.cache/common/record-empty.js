/*TMODJS:{"version":38,"md5":"4deffab1cfdd4a51c72ffee70e4d11a7"}*/
template('common/record-empty',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,colspan=$data.colspan,text=$data.text,$out='';$out+=' <tr> <td colspan="';
$out+=$escape(colspan);
$out+='" style="text-align: center">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr>';
return new String($out);
});
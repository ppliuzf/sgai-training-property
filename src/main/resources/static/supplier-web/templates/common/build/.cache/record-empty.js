/*TMODJS:{"version":1,"md5":"237cf5a3ce595f32c398d3c4f90a40e9"}*/
template('record-empty',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,colspan=$data.colspan,text=$data.text,$out='';$out+=' <tr> <td colspan="';
$out+=$escape(colspan);
$out+='" class="text-center">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr>';
return new String($out);
});
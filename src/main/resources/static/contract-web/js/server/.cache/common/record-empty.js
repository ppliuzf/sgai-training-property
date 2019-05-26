/*TMODJS:{"version":22,"md5":"7ffd6fd5f1d2fd40f6963c783f1fe8fc"}*/
template('common/record-empty',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,colspan=$data.colspan,text=$data.text,$out='';$out+=' <tr> <td colspan="';
$out+=$escape(colspan);
$out+='" class="no-record">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr>';
return new String($out);
});
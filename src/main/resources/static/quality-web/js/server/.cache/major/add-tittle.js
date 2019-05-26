/*TMODJS:{"version":3,"md5":"41c5aa5b5ba95a723d2cfbc9e6ee1da0"}*/
template('major/add-tittle',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$out='';$out+='<h4>';
$out+=$escape(items.title);
$out+='</h4>';
return new String($out);
});
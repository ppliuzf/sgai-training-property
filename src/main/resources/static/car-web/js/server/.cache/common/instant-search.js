/*TMODJS:{"version":1,"md5":"bfca4fbd90d57913918ae9f3c9c3fdfc"}*/
template('common/instant-search',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,placeholder=$data.placeholder,$out='';$out+='<div class="instant-search"> <input type="text" id="instant-keywords" class="form-control" placeholder="';
$out+=$escape(placeholder);
$out+='"> <input type="hidden" id="instant-selected"> <div class="instant-search-content"></div> </div>';
return new String($out);
});
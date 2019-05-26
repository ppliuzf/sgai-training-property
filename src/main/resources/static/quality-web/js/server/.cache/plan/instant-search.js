/*TMODJS:{"version":12,"md5":"ba7d84d017f2ecd4dd6e23e9ee281498"}*/
template('plan/instant-search',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,placeholder=$data.placeholder,$out='';$out+='<div class="instant-search"> <input type="text" id="instant-keywords" class="form-control" placeholder="';
$out+=$escape(placeholder);
$out+='"> <input type="hidden" id="instant-selected"> <div class="instant-search-content"></div> </div>';
return new String($out);
});
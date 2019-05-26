/*TMODJS:{"version":1,"md5":"030cb13664a97334e6d91e8f85dd8564"}*/
template('instant-search',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,placeholder=$data.placeholder,$out='';$out+='<div class="instant-search"> <input type="text" id="instant-keywords" class="form-control" placeholder="';
$out+=$escape(placeholder);
$out+='"> <input type="hidden" id="instant-selected"> <div class="instant-search-content"></div> </div>';
return new String($out);
});
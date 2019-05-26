/*TMODJS:{"version":14,"md5":"0e5914a7e64455af15cf78f9e3627bb5"}*/
template('plan/setting-word',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <div> ';
$out+=$escape(item.asName);
$out+=' </div> ';
});
return new String($out);
});
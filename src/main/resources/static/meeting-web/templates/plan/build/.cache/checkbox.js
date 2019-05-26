/*TMODJS:{"version":1,"md5":"6087533912bfea664e00b3835c02e25d"}*/
template('checkbox',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <div class="checkbox"> <label> <input type="checkbox" data-id="';
$out+=$escape(item.value);
$out+='" data-title="';
$out+=$escape(item.title);
$out+='"> ';
$out+=$escape(item.title);
$out+=' </label> </div> ';
});
return new String($out);
});
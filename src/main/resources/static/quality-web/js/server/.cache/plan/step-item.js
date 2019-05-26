/*TMODJS:{"version":16,"md5":"179c4702c83ca9183ff504b2e1de0386"}*/
template('plan/step-item',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' ';
if(item.periodName != '无'){
$out+=' <div class="item s';
$out+=$escape(item.id);
$out+='" data-id="';
$out+=$escape(item.id);
$out+='"> <div class="form-group"> <span class="inp"> <input type="text" value="';
$out+=$escape(item.periodName);
$out+='" class="layui-input" maxlength="20"> </span> <span class="act"> <a class="btn js-down" data-id="s';
$out+=$escape(item.id);
$out+='">下移</a> <a class="btn js-up" data-id="s';
$out+=$escape(item.id);
$out+='">上移</a> <a class="btn js-remove" data-id="';
$out+=$escape(item.recordId);
$out+='" data-pId="';
$out+=$escape(item.id);
$out+='">删除<input type="hidden" value="';
$out+=$escape(item.haveTask);
$out+='"> </a> </span> </div> </div> ';
}
$out+=' ';
});
$out+=' ';
return new String($out);
});
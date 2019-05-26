/*TMODJS:{"version":35,"md5":"47cf877e2e692c01f1e10c325ec23b9d"}*/
template('check/add-radio-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<ul class="select-list"> ';
$each(items,function(item,$index){
$out+=' <li data-Id="';
$out+=$escape(item.id);
$out+='"> <div class="input-div"> <input type="text" name="cre-select" class="form-control" id="sel';
$out+=$escape(item.id);
$out+='" placeholder="请输入" maxlength="10"> </div> <div class="result-div" isHg="';
$out+=$escape(item.isHg);
$out+='"> <span class="remove"><a class="js-remove underline" data-Id="';
$out+=$escape(item.id);
$out+='" href="javascript:;">移除</a></span><span class="hg"><a class="js-del underline" data-Id="';
$out+=$escape(item.id);
$out+='"><i class="icon-ok"></i>合格</a></span><span class="bhg"><a class="js-hege underline" href="javascript:;" data-Id="';
$out+=$escape(item.id);
$out+='">设为合格</a></span> </div> </li> ';
});
$out+=' </ul> <div class="create-select"> <span class="js-create-select"><i class="create-select-i"></i>添加选项</span> </div>';
return new String($out);
});
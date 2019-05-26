/*TMODJS:{"version":1,"md5":"324f9b4425fb7963b0c0423d3322eba9"}*/
template('questionnaire-add-content',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,id=$data.id,$out='';$each(items,function(item,$index){
$out+=' <div class="box"> <a href="javascript:;" class="js-card-remove hide"><i class="glyphicon glyphicon-remove-circle"></i></a> <label class="js-questionnaire-title">第<font class="js-num">';
$out+=$escape(item.sqNo);
$out+='</font>题</label> <div class="form-group"> <label for="sqTopic-';
$out+=$escape(id);
$out+='">标题</label> <input type="text" id="sqTopic-';
$out+=$escape(id);
$out+='" class="form-control js-title" maxlength="300" value="';
$out+=$escape(item.sqTopic);
$out+='" placeholder="文本，最大支持300字"> </div> <div class="form-group"> <label for="sqType">内容类型</label> <select name="" id="sqType" class="form-control js-content-type" value="';
$out+=$escape(item.sqType);
$out+='"> <option value="0">单选</option> <option value="1">多选</option> <option value="2">文本</option> </select> </div> <div class="js-items"> <label>选项内容 (最多添加10个)<a href="javascript:;" class="js-questionnaire-item-add" title="添加多个选项"><i class="glyphicon glyphicon-plus-sign"></i></a></label>                     </div> </div> ';
});
return new String($out);
});
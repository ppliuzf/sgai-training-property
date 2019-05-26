/*TMODJS:{"version":1,"md5":"7f7043e8a27980b52109caf095edbcef"}*/
template('questionnaire-add-item',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <div class="form-group js-group"> <div class="input-group"> <div class="input-group-addon js-group-num"></div> <input type="text" class="form-control js-item" maxlength="200" value="';
$out+=$escape(item);
$out+='" placeholder="文本，最大支持200"> <a class="btn input-group-addon js-questionnaire-item-del"> <i class="glyphicon glyphicon-remove"></i> </a> </div> </div> ';
});
return new String($out);
});
/*TMODJS:{"version":1,"md5":"2f10a9412af7029008b9a1b243f5b0fb"}*/
template('questionnaire-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,type=$data.type,item=$data.item,$out='';$out+='<div class="row header"> <h4>';
$out+=$escape(type);
$out+='问卷调查</h4> </div> <div class="row"> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group"> <label for="name"><span class="require"></span>问卷名称</label> <input type="text" id="name" class="form-control" value="';
$out+=$escape(item.smName);
$out+='" maxlength="20"> </div> <div class="form-group"> <label for="smEndTime"><span class="require"></span>结束时间</label> <input type="text" id="smEndTime" class="form-control" readonly value="';
$out+=$escape(item.smEndTime);
$out+='"> </div> <label><span class="require"></span>问卷内容</label> <div class="js-questionnaire-content"> <div class="js-content"></div> <div class="text-right"> <i class="glyphicon glyphicon-plus-sign"></i> <a href="javascript:;" class="js-questionnaire-box-add">添加多个题目</a> </div> </div> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div> </div>';
return new String($out);
});
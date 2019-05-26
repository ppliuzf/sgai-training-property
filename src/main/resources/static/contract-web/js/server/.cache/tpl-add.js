/*TMODJS:{"version":23,"md5":"b241e86073b13689a328b2fe4eb27f38"}*/
template('tpl-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+='<div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group"> <label for="name"> <span class="require"></span>模板名称:</label> <input type="text" id="name" class="form-control" maxlength="20" value="';
$out+=$escape(item && item.name);
$out+='"> </div> <div class="form-group"> <label for="type">合同类型</label> <select class="form-control" id="type"> </select> </div> <div class="form-group desc-box"> <label for="desc">描述</label> <textarea id="desc" rows="6" class="form-control" maxlength="200">';
$out+=$escape(item && item.description);
$out+='</textarea> <div class="text-right js-count">0/200</div> </div> <div class="form-group"> <div class="js-uploader-file"></div> </div> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div>';
return new String($out);
});
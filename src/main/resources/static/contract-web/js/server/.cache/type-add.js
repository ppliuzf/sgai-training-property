/*TMODJS:{"version":23,"md5":"ef4a95a6fd5ea4c0bf357c2194335649"}*/
template('type-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+='<div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group"> <label for="name"> <span class="require"></span>类型名称</label> <input type="text" id="name" class="form-control" maxlength="20" value="';
$out+=$escape(item && item.typeName);
$out+='"> </div> <div class="form-group spe"> <label for="protocol"> <span class="require"></span>合同规约:</label> <input type="Number" id="protocol" class="form-control" value="';
$out+=$escape(item && item.limitValue);
$out+='"> </div> <div class="form-group desc-box"> <label for="desc">描述</label> <textarea id="desc" rows="6" class="form-control" maxlength="200">';
$out+=$escape(item && item.typeDescription);
$out+='</textarea> <div class="text-right js-count">0/200</div> </div> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div>';
return new String($out);
});
/*TMODJS:{"version":1,"md5":"0d8d7ea83622b7033b514eeaa853f820"}*/
template('service-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+=' <div class="form-group"> <label for="name">供应商服务内容名称</label> <input type="text" id="name" class="form-control" maxlength="20" value="';
$out+=$escape(item && item.name);
$out+='"> </div> <div class="form-group desc-box"> <label for="desc">描述</label> <textarea id="desc" rows="6" class="form-control" maxlength="200">';
$out+=$escape(item && item.description);
$out+='</textarea> <div class="text-right js-count">0/200</div> </div> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> ';
return new String($out);
});
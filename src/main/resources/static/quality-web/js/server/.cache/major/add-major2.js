/*TMODJS:{"version":2,"md5":"3616eb38d035a632b6de0bf55b71b1f0"}*/
template('major/add-major2',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$out='';$out+='<form id="form"> <div class="form-group clearfix"> <label for="pcName"><span class="require"></span>专业范畴名称</label> <input type="text" id="pcName" class="form-control" name="pcName" value="';
$out+=$escape(items.pcName);
$out+='" maxlength="10"> </div> <div class="form-group clearfix"> <input type="text" value="" hidden name="asName" id="asName"> <label for="asId"><span class="require"></span>关联类型</label> <select name="asId" id="asId" class="form-control"> </select> </div> <div class="form-group clearfix"> <label for="pcIcon">选择icon <span style="color:#8c8c8c;font-size: 12px;">选择的icon后将显示在App端品质检验首页</span></label> <input type="text" id="pcIcon" name="pcIcon" hidden> <ul class="ul-box clearfix" id="ul-box"> </ul> </div> <div class="form-group clearfix"> <label for="pcDesc">描述</label> <div class="textarea"> <textarea id="pcDesc" rows="4" class="form-control js-textarea" name="pcDesc" maxlength="100">';
$out+=$escape(items.pcDesc);
$out+='</textarea> <div class="border"></div> <div class="number js-count"></div> </div> </div> <div class="form-group"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <a class="btn btn-primary js-save">保存</a> <a class="btn btn-default js-cancel">取消</a> </div> </div> </form>';
return new String($out);
});
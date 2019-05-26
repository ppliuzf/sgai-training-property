/*TMODJS:{"version":54,"md5":"c8b2bc808c57f74f49edb6757e0dc5a5"}*/
template('major/add-major',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$out='';$out+='<div class="row header"> <h4>';
$out+=$escape(items.title);
$out+='</h4> </div> <div class="row"> <div class="col-xs-12 col-sm-8 col-md-6 col-lg-6"> <form id="form"> <div class="form-group clearfix"> <label for="pcName"><span class="require"></span>专业范畴名称</label> <input type="text" id="pcName" class="form-control" name="pcName" value="';
$out+=$escape(items.pcName);
$out+='" maxlength="10"> </div> <div class="form-group clearfix"> <input type="text" value="" hidden name="asName" id="asName"> <label for="ul-type"><span class="require"></span>关联类型</label> <ul class="form-group clearfix ul-type" id="ul-type"> </ul> </div> <div class="form-group clearfix"> <label for="ul-box">选择icon <span style="color:#8c8c8c;font-size: 12px;">选择的icon后将显示在App端品质检验首页</span></label> <ul class="ul-box clearfix" id="ul-box"> </ul> </div> <div class="form-group clearfix"> <label for="pcDesc">描述</label> <div class="textarea"> <textarea id="pcDesc" rows="4" class="form-control js-textarea" name="pcDesc" maxlength="100">';
$out+=$escape(items.pcDesc);
$out+='</textarea> <div class="border"></div> <div class="number js-count"></div> </div> </div> <div class="form-group"> <a class="btn btn-primary js-save">保存</a> <a class="btn btn-default js-cancel">取消</a> </div> </form> </div> </div> ';
return new String($out);
});
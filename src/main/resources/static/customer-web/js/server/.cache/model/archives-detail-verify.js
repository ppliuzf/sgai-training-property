/*TMODJS:{"version":1,"md5":"c8087f488f6c34f8ed49d59831361f5d"}*/
template('model/archives-detail-verify',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+='<div class="flow-return form-horizontal row"> <div class="form-group"> <label class="col-sm-3 control-label" >  核实人</label> <div class="col-sm-7"> <input class="form-control" disabled="disabled" id="js-h-user" data-id="';
$out+=$escape(item.userId);
$out+='" value=';
$out+=$escape(item.userName);
$out+='> </div> </div> <div class="form-group"> <label class="col-sm-3 control-label">  核实时间</label> <div class="col-sm-7"> <input class="form-control" id="js-h-processingTime" readonly="readonly"> </div> </div> <div class="form-group"> <label class="col-sm-3 control-label">  备&nbsp注:</label> <div class="col-sm-7"> <textarea id="js-h-verify" class="form-control" maxlength="200"></textarea> <div class="text-right js-count">0/200</div> </div> </div> </div> <style> .star { color: red; margin-right: 10px } .control-label { margin-left: 10px } </style>';
return new String($out);
});
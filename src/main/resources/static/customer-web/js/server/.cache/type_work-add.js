/*TMODJS:{"version":1,"md5":"e0926f665340d7a281b888b4f2e11240"}*/
template('type_work-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,type=$data.type,title=$data.title,item=$data.item,$out='';$out+='<div class="row header"> <h4>';
$out+=$escape(type);
$out+=$escape(title);
$out+='</h4> </div> <div class="row"> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group"> <label for="name"> <span class="require"></span>费用名称</label> <input type="text" id="name" class="form-control" maxlength="20" value="';
$out+=$escape(item &&item.wdCostName);
$out+='"> </div> <div class="form-group"> <label for="money"> <span class="require"></span>单价(元)</label> <input type="number" id="money" class="form-control" maxlength="10" value="';
$out+=$escape(item &&item.wdPrice);
$out+='" oninput="if(value.length>10)value=value.slice(0,10)"> </div> <div class="form-group"> <label for="adress"> <span class="require"></span>单位</label> <input type="text" id="adress" class="form-control" maxlength="10" value="';
$out+=$escape(item &&item.wdUnit);
$out+='"> </div> <div class="form-group"> <label for="desc">描述</label> <textarea id="desc" rows="4" class="form-control" maxlength="200">';
$out+=$escape(item &&item.wdDesc);
$out+='</textarea> <div class="text-right js-count">0/200</div> </div> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div> </div>';
return new String($out);
});
/*TMODJS:{"version":1,"md5":"af1f2b4335ca84306fd8c2337c2a0b74"}*/
template('materielList',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<div class="form-inline js-materiel-list"> ';
$each(items,function(item,$index){
$out+=' <div class="form-group"> ';
if(!item.isSelect){
$out+=' <input type="checkbox" class="warn-btn" data-count="';
$out+=$escape(item.maCount);
$out+='" data-id="';
$out+=$escape(item.maId);
$out+='" data-name="';
$out+=$escape(item.maName);
$out+='" data-type="';
$out+=$escape(item.maTypeName);
$out+='" data-typeId="';
$out+=$escape(item.maTypeId);
$out+='" data-select="';
$out+=$escape(item.isSelect);
$out+='" data-code="';
$out+=$escape(item.maCode);
$out+='">&nbsp;';
$out+=$escape(item.maName);
$out+=' ';
}else{
$out+=' <input type="checkbox" class="warn-btn" data-count="';
$out+=$escape(item.maCount);
$out+='" data-id="';
$out+=$escape(item.maId);
$out+='" data-name="';
$out+=$escape(item.maName);
$out+='" data-type="';
$out+=$escape(item.maTypeName);
$out+='" data-typeId="';
$out+=$escape(item.maTypeId);
$out+='" data-select="';
$out+=$escape(item.isSelect);
$out+='" data-code="';
$out+=$escape(item.maCode);
$out+='" checked="checked">&nbsp;';
$out+=$escape(item.maName);
$out+=' ';
}
$out+=' </div> ';
});
$out+=' </div>';
return new String($out);
});
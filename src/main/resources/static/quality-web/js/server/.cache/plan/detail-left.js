/*TMODJS:{"version":12,"md5":"040f257535c614981d7539a1b03db01f"}*/
template('plan/detail-left',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,recordName=$data.recordName,typeName=$data.typeName,partner=$data.partner,$each=$utils.$each,p=$data.p,$index=$data.$index,desc=$data.desc,recordDesc=$data.recordDesc,$out='';$out+='<div class="form-group"></div> <div class="form-group"> <h4>基础信息</h4> <p><label>名称:</label></p> <p class="color-txt">';
$out+=$escape(recordName);
$out+='</p> </div> <div class="form-group"> <p><label>类型:</label></p> <p class="color-txt">';
$out+=$escape(typeName || '-');
$out+='</p> </div> <div class="form-group"> <p><label>参与者:</label></p> <p class="color-txt">';
if(partner && partner.length){
$out+=' ';
$each(partner,function(p,$index){
$out+=' ';
$out+=$escape(p);
$out+=' ';
});
$out+=' ';
}else{
$out+=' - ';
}
$out+='</p> </div> ';
if(desc != ''){
$out+=' <div class="item"> <p><label>描述</label></p> <div class="cont color-txt">';
$out+=$escape(recordDesc || '-');
$out+='</div> </div> ';
}
return new String($out);
});
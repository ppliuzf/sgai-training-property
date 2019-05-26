/*TMODJS:{"version":1,"md5":"2368e3533a4a653c09108af41c5b29b6"}*/
template('common/bubble',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,text=$data.text,$out='';$out+='<div id="bubble"> <div class="body"> <i class="icon-warn"></i>';
$out+=$escape(text);
$out+=' </div> <div class="btns"> <button class="btn btn-default btn-cancel">取消</button> <button class="btn btn-primary btn-ok">确定</button> </div> </div>';
return new String($out);
});
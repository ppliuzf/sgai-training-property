/*TMODJS:{"version":15,"md5":"ab1ae57bf2462685dc7e4b04169d1302"}*/
template('common/bubble',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,text=$data.text,$out='';$out+='<div id="bubble"> <div class="body"> <i class="icon-warn"></i>';
$out+=$escape(text);
$out+=' </div> <div class="btns"> <button class="btn btn-default btn-cancel">取消</button> <button class="btn btn-primary btn-ok">确定</button> </div> </div>';
return new String($out);
});
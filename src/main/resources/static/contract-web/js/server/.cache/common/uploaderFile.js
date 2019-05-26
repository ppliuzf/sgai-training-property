/*TMODJS:{"version":22,"md5":"16aa4c665f7708fce62ce696610fc227"}*/
template('common/uploaderFile',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,type=$data.type,maxLength=$data.maxLength,btnTips=$data.btnTips,isRequire=$data.isRequire,title=$data.title,tips=$data.tips,$out='';$out+='<div class="upload-file"> <form id="upload-form" enctype="multipart/form-data"> <input type="file" name="file" id="upload" accept="';
$out+=$escape(type);
$out+='"> </form> ';
if(maxLength === 1){
$out+=' <div class="upload-btn_wrap"> <span class="upload-btn reset">';
$out+=$escape(btnTips);
$out+='</span> </div> ';
}else{
$out+=' <div class="upload-btn_wrap"> <strong class=';
$out+=$escape(isRequire ? "require": "");
$out+='>';
$out+=$escape(title);
$out+='</strong><span class="upload-btn">';
$out+=$escape(btnTips);
$out+='</span> </div> ';
}
$out+=' <div class="upload-file-items"></div> ';
if(tips !== ''){
$out+=' <div class="upload-file-tips">';
$out+=$escape(tips);
$out+='</div> ';
}
$out+=' </div> ';
return new String($out);
});
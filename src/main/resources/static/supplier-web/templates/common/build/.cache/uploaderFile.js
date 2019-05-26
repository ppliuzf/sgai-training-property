/*TMODJS:{"version":1,"md5":"e0c591fc04355fae0286bb17b774fd70"}*/
template('uploaderFile',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,type=$data.type,$out='';$out+='<div class="upload-file"> <form id="upload-form" enctype="multipart/form-data"> <input type="file" name="file" id="upload" accept="';
$out+=$escape(type);
$out+='"> </form> <div class="upload-file-items"></div> <div class="upload-btn">请选择上传附件</div> <div class="upload-file-tips">注：仅限pdf、word、excel、txt、ppt格式，大小不超过50M</div> </div> ';
return new String($out);
});
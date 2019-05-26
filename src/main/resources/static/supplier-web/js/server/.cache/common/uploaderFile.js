/*TMODJS:{"version":1,"md5":"47a09bd442a4d4af634d7ae7173a7da4"}*/
template('common/uploaderFile',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,type=$data.type,$out='';$out+='<div class="upload-file"> <form id="upload-form" enctype="multipart/form-data"> <input type="file" name="file" id="upload" accept="';
$out+=$escape(type);
$out+='"> </form> <div class="upload-file-items"></div> <div class="upload-btn">请选择上传附件</div>  </div> ';
return new String($out);
});
/*TMODJS:{"version":1,"md5":"09c9fbf7a048b48b30f146baa3f3c2d2"}*/
template('uploader',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,type=$data.type,$string=$utils.$string,tips=$data.tips,$out='';$out+='<div class="uploader clearfix"> <form id="upload-form" enctype="multipart/form-data" class="hide"> <input type="file" name="file" id="upload" accept="';
$out+=$escape(type);
$out+='"> </form> <div class="upload-items clearfix pull-left"></div> <div class="upload-btn pull-left"></div> <div class="upload-tips pull-left">';
$out+=$string( tips);
$out+='</div> </div>';
return new String($out);
});
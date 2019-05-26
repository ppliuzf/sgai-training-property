/*TMODJS:{"version":1,"md5":"9cc1a11cd1cb35b1b3d839b6a7edd427"}*/
template('common/uploader-item',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,url=$data.url,imageGroup=$data.imageGroup,isSetDefault=$data.isSetDefault,$out='';$out+=' <div class="upload-item" data-url="';
$out+=$escape(url);
$out+='"> <a href="';
$out+=$escape(url);
$out+='" class="upload-item-inner" target="_blank" data-lightbox="';
$out+=$escape(imageGroup);
$out+='" style="background-image: url(\'';
$out+=$escape(url);
$out+='\')"> ';
if(isSetDefault){
$out+='<div class="upload-default">默认</div>';
}
$out+=' </a> <a class="upload-close"><i class="icon-close"></i></a> </div>';
return new String($out);
});
/*TMODJS:{"version":5,"md5":"38230e94eaeaa8988185babf88ad9044"}*/
template('uploadFileItem',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,fileUrl=$data.fileUrl,fileName=$data.fileName,id=$data.id,$out='';$out+='<div class="upload-item" data-url="';
$out+=$escape(fileUrl);
$out+='" data-name="';
$out+=$escape(fileName);
$out+='" data-id="';
$out+=$escape(id);
$out+='"> <div class="upload-item-inner">';
$out+=$escape(fileName);
$out+='</div> <a class="upload-close"> <i class="icon-close"></i> </a> </div>';
return new String($out);
});
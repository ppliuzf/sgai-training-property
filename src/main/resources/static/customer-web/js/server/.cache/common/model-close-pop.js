/*TMODJS:{"version":1,"md5":"cacfa9da6b5946df3896b1f85215a003"}*/
template('common/model-close-pop',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,el=$data.el,size=$data.size,title=$data.title,height=$data.height,$string=$utils.$string,content=$data.content,$out='';$out+='<div class="modal fade" id="';
$out+=$escape(el);
$out+='" role="dialog"> <div class="modal-dialog modal-';
$out+=$escape(size);
$out+='"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> <h4 class="modal-title" id="exampleModalLabel">';
$out+=$escape(title);
$out+='</h4> </div> <div class="modal-body" style="max-height:';
$out+=$escape(height);
$out+=';overflow:auto">';
$out+=$string( content);
$out+='</div> </div> </div> </div>';
return new String($out);
});
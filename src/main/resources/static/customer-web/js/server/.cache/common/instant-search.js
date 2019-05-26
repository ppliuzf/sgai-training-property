/*TMODJS:{"version":1,"md5":"8d80f8944ff5a665e4d74a29947492d7"}*/
template('common/instant-search',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,placeholder=$data.placeholder,approvalEmpName=$data.approvalEmpName,approvalEmpId=$data.approvalEmpId,$out='';$out+='<div class="instant-search"> <input type="text" id="instant-keywords" class="form-control" placeholder="';
$out+=$escape(placeholder);
$out+='" value="';
$out+=$escape(approvalEmpName);
$out+='" autocomplete="off"> <input type="hidden" id="instant-selected" class="';
$out+=$escape(approvalEmpName);
$out+='" value="';
$out+=$escape(approvalEmpId);
$out+='"> <div class="instant-search-content"></div> </div>';
return new String($out);
});
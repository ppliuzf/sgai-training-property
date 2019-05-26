/*TMODJS:{"version":34,"md5":"f9eaeadb9a1010da94096a6cfc53d551"}*/
template('scheme/aside-info',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,name=$data.name,pcName=$data.pcName,description=$data.description,$out='';$out+='<h4>基础信息</h4> <dl class="item"> <dt>检验方案名称</dt> <dd class="cont">';
$out+=$escape(name);
$out+='</dd> </dl> <dl class="item"> <dt>专业范畴</dt> <dd class="cont">';
$out+=$escape(pcName);
$out+='</dd> </dl> <dl class="item"> <dt>方案说明</dt> <dd class="cont FaDesc">';
$out+=$escape(description || '-');
$out+='</dd> </dl>';
return new String($out);
});
/*TMODJS:{"version":1,"md5":"a629e58a082aa89c1770fcf44defb5ac"}*/
template('plan/setting-mail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,mcAccount=$data.mcAccount,mcPassword=$data.mcPassword,mcIp=$data.mcIp,mcPort=$data.mcPort,mcEmailName=$data.mcEmailName,$out='';$out+='<div class="form-group mail-name"> <label for="mail-name">邮箱账号：</label> <input type="email" class="form-control" id="mail-name" placeholder="mail@syswin.com" maxlength="200" disabled="disabled" value="';
$out+=$escape(mcAccount);
$out+='"> </div> <div class="form-group password"> <label for="password">密码：</label> <input type="password" class="form-control" id="password" placeholder="******" maxlength="20" disabled="disabled" value="';
$out+=$escape(mcPassword);
$out+='"> </div> <div class="form-group server"> <label for="server">SMTP服务器：</label> <input type="text" class="form-control" id="server" placeholder="172.168.0.1" disabled="disabled" value="';
$out+=$escape(mcIp);
$out+='"> </div> <div class="form-group port"> <label for="port">端口：</label> <input type="text" class="form-control" id="port" placeholder="8880" disabled="disabled" value="';
$out+=$escape(mcPort);
$out+='"> </div> <div class="form-group send-name"> <label for="send-name">发信名称：</label> <input type="text" class="form-control" id="send-name" placeholder="会议应用" maxlength="10" disabled="disabled" value="';
$out+=$escape(mcEmailName);
$out+='"> </div>';
return new String($out);
});
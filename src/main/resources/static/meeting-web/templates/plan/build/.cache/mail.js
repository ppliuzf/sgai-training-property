/*TMODJS:{"version":1,"md5":"ca1d4108aa953f72f3c346a6056fbcd8"}*/
template('mail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,isSend=$data.isSend,$escape=$utils.$escape,mcAccount=$data.mcAccount,mcPassword=$data.mcPassword,mcIp=$data.mcIp,mcPort=$data.mcPort,mcEmailName=$data.mcEmailName,$out='';$out+=' <div class="row header"> <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"> <h4>邮件提醒设置</h4> <div class="switch"> ';
if(isSend = true){
$out+=' <input type="checkbox" class="js-checked" checked="checked"> ';
}else{
$out+=' <input type="checkbox" class="js-checked"> ';
}
$out+=' </div> </div>  <div class="btns text-right"> <button type="button" class="btn btn-primary">编辑</button> </div> </div> <div class="filter col-xs-12 col-sm-12 col-md-5 col-lg-7"> <div class="form-group mail-name"> <label for="mail-name">邮箱账号：</label> <input type="email" class="form-control" id="mail-name" placeholder="mail@syswin.com" maxlength="200" disabled="disabled" value="';
$out+=$escape(mcAccount);
$out+='"> </div> <div class="form-group password"> <label for="password">密码：</label> <input type="password" class="form-control" id="password" placeholder="******" maxlength="20" disabled="disabled" value="';
$out+=$escape(mcPassword);
$out+='"> </div> <div class="form-group server"> <label for="server">SMTP服务器：</label> <input type="text" class="form-control" id="server" placeholder="172.168.0.1" disabled="disabled" value="';
$out+=$escape(mcIp);
$out+='"> </div> <div class="form-group port"> <label for="port">端口：</label> <input type="text" class="form-control" id="port" placeholder="8880" disabled="disabled" value="';
$out+=$escape(mcPort);
$out+='"> </div> <div class="form-group send-name"> <label for="send-name">发信名称：</label> <input type="text" class="form-control" id="send-name" placeholder="会议应用" maxlength="10" disabled="disabled" value="';
$out+=$escape(mcEmailName);
$out+='"> </div> <div class="row bottom-btn hidden"> <button type="button" class="btn btn-primary js-save">保存</button> <button type="button" class="btn btn-default js-test">测试邮件</button> <button type="button" class="btn btn-default js-cancel">取消</button> </div> </div>';
return new String($out);
});
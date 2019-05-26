/*TMODJS:{"version":"1.0.0"}*/
!function(){function a(a,b){return(/string|function/.test(typeof b)?h:g)(a,b)}function b(a,c){return"string"!=typeof a&&(c=typeof a,"number"===c?a+="":a="function"===c?b(a.call(a)):""),a}function c(a){return l[a]}function d(a){return b(a).replace(/&(?![\w#]+;)|[<>"']/g,c)}function e(a,b){if(m(a))for(var c=0,d=a.length;d>c;c++)b.call(a,a[c],c,a);else for(c in a)b.call(a,a[c],c)}function f(a,b){var c=/(\/)[^\/]+\1\.\.\1/,d=("./"+a).replace(/[^\/]+$/,""),e=d+b;for(e=e.replace(/\/\.\//g,"/");e.match(c);)e=e.replace(c,"/");return e}function g(b,c){var d=a.get(b)||i({filename:b,name:"Render Error",message:"Template not found"});return c?d(c):d}function h(a,b){if("string"==typeof b){var c=b;b=function(){return new k(c)}}var d=j[a]=function(c){try{return new b(c,a)+""}catch(d){return i(d)()}};return d.prototype=b.prototype=n,d.toString=function(){return b+""},d}function i(a){var b="{Template Error}",c=a.stack||"";if(c)c=c.split("\n").slice(0,2).join("\n");else for(var d in a)c+="<"+d+">\n"+a[d]+"\n\n";return function(){return"object"==typeof console&&console.error(b+"\n\n"+c),b}}var j=a.cache={},k=this.String,l={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},m=Array.isArray||function(a){return"[object Array]"==={}.toString.call(a)},n=a.utils={$helpers:{},$include:function(a,b,c){return a=f(c,a),g(a,b)},$string:b,$escape:d,$each:e},o=a.helpers=n.$helpers;a.get=function(a){return j[a.replace(/^\.\//,"")]},a.helper=function(a,b){o[a]=b},"function"==typeof define?define(function(){return a}):"undefined"!=typeof exports?module.exports=a:this.template=a,a.helper("dateFormat",function(a,b){if(a){a=new Date(parseInt(a));var c={M:a.getMonth()+1,d:a.getDate(),h:a.getHours(),m:a.getMinutes(),s:a.getSeconds(),q:Math.floor((a.getMonth()+3)/3),S:a.getMilliseconds(),D:a.getDay()};return b=b.replace(/([yMdhmsqSD])+/g,function(b,d){var e=c[d];if(void 0!==e){if("D"==d)switch(e){case 1:e="\u4e00";break;case 2:e="\u4e8c";break;case 3:e="\u4e09";break;case 4:e="\u56db";break;case 5:e="\u4e94";break;case 6:e="\u516d";break;case 0:e="\u65e5"}else b.length>1&&(e="0"+e,e=e.substr(e.length-2));return e}return"y"===d?(a.getFullYear()+"").substr(4-b.length):b})}return""}),/*v:879*/
a("add/add-notice-one",function(a){"use strict";var b=this,c=b.$helpers,d=b.$escape,e=a.items,f=b.$each,g=(a.item,a.index,"");return g+='<div class="form-group"> <label for="exampleInputEmail1"><span class="require"></span>\u6807\u9898</label> <input type="text" class="form-control" id="infoTitle" value="',g+=d(e.infoTitle),g+='" placeholder="\u8bf7\u8f93\u5165\u6807\u9898" maxlength="50"> </div> <div class="form-group"> <label for="exampleInputEmail1"><span class="require"></span>\u6458\u8981</label> <textarea type="email" rows="4" class="form-control" id="infoSummary" placeholder="\u8bf7\u8f93\u5165\u6458\u8981" maxlength="200">',g+=d(e.infoSummary),g+='</textarea> <div class="conttask text-right"></div> </div>  <div class="form-group"> <div class="checkbox"> <label> ',g+=1==e.infoTimePublish?' <input type="checkbox" id="infoTimePublish" checked="checked" name="infoTimePublish" > \u9884\u7ea6\u53d1\u5e03 ':' <input type="checkbox" id="infoTimePublish" name="infoTimePublish" > \u9884\u7ea6\u53d1\u5e03 ',g+=" </label> </div> ",g+=1==e.infoTimePublish?' <div class="" id="releaseTime"> ':' <div class="isHide" id="releaseTime"> ',g+=' <label class="control-label">\u53d1\u5e03\u65f6\u95f4:</label> <div class="input-group"> <input id="publishTime" data-date-format="yyyy-MM-dd HH:ii" format="yyyy-MM-dd HH:ii" class="form-control form_date" size="16" type="text" id="infoTime" value="',g+=d(c.dateFormat(e.publishTime,"yyyy-MM-dd hh:mm")),g+='" readonly> <span class="input-group-addon js-date-clean"><span class="glyphicon glyphicon-remove"></span></span> </div> </div> </div> <div class="form-group"> <label>\u53d1\u5e03\u5230\uff1a</label> <label id="publicType"> ',e.visibilityScopeArr?(g+=" ",f(e.visibilityScopeArr,function(a){g+=" ",1==a.checked?(g+=' <input type="checkbox" id="',g+=d(a.id),g+='" checked="checked" name="" value = \'',g+=d(a.name),g+="' > ",g+=d(a.name),g+="&nbsp;&nbsp; "):(g+=' <input type="checkbox" id="',g+=d(a.id),g+='" name="" value = \'',g+=d(a.name),g+="' > ",g+=d(a.name),g+="&nbsp;&nbsp; "),g+=" "}),g+=" "):g+=' <input type="checkbox" id="appPublic" checked="checked" value = \'APP\' > APP&nbsp;&nbsp; <input type="checkbox" id="pcPublic" checked="checked" value = \'PC\' > PC&nbsp;&nbsp; <input type="checkbox" id="pcPublic" value=\'\u6295\u5c4f\'> \u6295\u5c4f ',g+=' </label> </div> <div class="form-group"> <label for="exampleInputEmail1"><span class="require"></span>\u6b63\u6587</label> <div id=\'infoContent\' name="infoContent"></div> </div> <div class="previewContent"> </div>',new k(g)}),/*v:495*/
a("add/add-notice-two",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.items,e="";return e+='<div class="form-group"> <label for="exampleInputEmail1">\u6807\u7b7e</label> <input type="email" class="form-control" id="infoLabel" value="',e+=c(d.infoLabel),e+='" placeholder="\u8bf7\u8f93\u5165\u6807\u7b7e" maxlength="4"> </div> <div class="form-group clearfix"> <label for="exampleInputEmail1">\u53d1\u5e03\u8303\u56f4</label> <div class="radio"> ',e+=1==d.infoScopeIsAll?' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" checked="checked" value="1" aria-label="..."> \u516c\u5f00\uff0c\u5168\u90e8\u53ef\u89c1 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="0" aria-label="..."> \u90e8\u5206\u53ef\u89c1 </label> ':0==d.infoScopeIsAll?' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="1" aria-label="..."> \u516c\u5f00\uff0c\u5168\u90e8\u53ef\u89c1 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" checked="checked" value="0" aria-label="..."> \u90e8\u5206\u53ef\u89c1 </label> ':' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="1" aria-label="..."> \u516c\u5f00\uff0c\u5168\u90e8\u53ef\u89c1 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="0" aria-label="..."> \u90e8\u5206\u53ef\u89c1 </label> ',e+=' </div> </div> <div class="form-group"> ',e+=0==d.infoScopeIsAll?' <div class="js-dept-selector"></div> ':' <div class="js-dept-selector isHide"></div> ',e+=' </div> <div class="form-group clearfix"> <label for="exampleInputEmail1">\u7c7b\u578b</label> <div class="radio"> ',e+=1==d.infoUrgency?' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency0" value="0" aria-label="..."> \u4e00\u822c </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency1" checked="checked" value="1" aria-label="..."> \u7d27\u6025 </label> ':' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency0" checked="checked" value="0" aria-label="..."> \u4e00\u822c </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency1" value="1" aria-label="..."> \u7d27\u6025 </label> ',e+=' </div> </div> <div id="isExamine" class="clearfix"> <div class="form-group clearfix"> <label for="exampleInputEmail1">\u9700\u8981\u5ba1\u6838</label> <div class="radio"> ',e+=1==d.infoApprovalFlag?' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" checked="checked" value="1" aria-label="..."> \u662f </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" value="0" aria-label="..."> \u5426 </label> ':' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" value="1" aria-label="..."> \u662f </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" checked="checked" value="0" aria-label="..."> \u5426 </label> ',e+=" </div> </div> ",e+=1==d.infoApprovalFlag?' <div class="form-group clearfix js-instant-search" id="selectionAuditor"> ':' <div class="form-group clearfix isHide js-instant-search" id="selectionAuditor"> ',e+=' <label for="exampleInputEmail1">\u5ba1\u6838\u4eba</label> <div class="js-instant-search"></div> <!-- <input type="text" id="instant-keywords" class="form-control" placeholder="\u8bf7\u8f93\u5165\u59d3\u540d" value="',e+=c(d.approvalEmpName),e+='"> <input type="hidden" id="instant-selected" value="',e+=c('{"id":'+d.approvalEmpId+',"title":"'+d.approvalEmpName+'"}'),e+='"> <div class="instant-search-content isHide"></div> --> </div> </div>',new k(e)}),/*v:94*/
a("add/preview",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.items,e="";return e+='<div class="preview infos-detail"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6807\u9898111</td> <td class="col-lg-5 col-md-5 col-sm-5 value">',e+=c(d.title),e+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6458\u8981</td> <td class="col-lg-5 col-md-5 col-sm-5 value">',e+=c(d.summary),e+='</td> </tr> <tr> <td class="text-center">\u6b63\u6587</td> <td class="value" colspan="3"> <div class="article"></div> </td> </tr> </tbody> </table> </div> ',new k(e)}),/*v:3*/
a("common/bubble",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.text,e="";return e+='<div id="bubble"> <div class="body"> <i class="icon-warn"></i>',e+=c(d),e+=' </div> <div class="btns"> <button class="btn btn-default btn-cancel">\u53d6\u6d88</button> <button class="btn btn-primary btn-ok">\u786e\u5b9a</button> </div> </div>',new k(e)}),/*v:53*/
a("common/dept-selector",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.name,e=b.$string,f=a.value,g=a.dept,h=a.emp,i=a.all,j="";return j+='<div class="multi-selector clean search"> <div class="inner js-',j+=c(d),j+='-selector"> ',j+=e(f),j+=' </div> <div class="btn-search js-',j+=c(d),j+='-search"><i class="glyphicon glyphicon-search"></i></div> <div class="btn-clean js-',j+=c(d),j+='-clean"><i class="glyphicon glyphicon-remove-sign"></i></div> <input type="hidden" class="js-',j+=c(d),j+='-dept" value="',j+=c(g),j+='"> <input type="hidden" class="js-',j+=c(d),j+='-emp" value="',j+=c(h),j+='"> <input type="hidden" class="js-',j+=c(d),j+='-all" value="',j+=c(i),j+='"> </div>',new k(j)}),/*v:49*/
a("common/dept-tree-list",function(a){"use strict";var b=this,c=(b.$helpers,a.items),d=b.$each,e=(a.item,a.$index,a.isDisabled),f=b.$escape,g=a.type,h=a.isSearch,i="";return i+=" ",c&&c.length?(i+=" ",d(c,function(a){i+=" ",a.disable&&"Y"===a.disable&&e?(i+=' <div class="item"> ',i+="0"==a.nodeType?' <i class="icon icon-checkbox-disabled"></i> ':' <i class="glyphicon glyphicon-user"></i> ',i+=' <span class="title">',i+=f(a.nodeName),i+="</span> </div> "):(i+=' <li data-number="',i+=f(a.empNum),i+='" data-avatar="',i+=f(a.avatar),i+='" data-id="',i+=f(a.nodeId),i+='" ',i+=f("0"==a.nodeType&&' data-group="1"'),i+='> <input type="checkbox"',"0"==a.nodeType&&"emp"===g&&(i+=" disabled"),i+="> ",i+="0"==a.nodeType?' <i class="icon icon-folder"></i> ':' <i class="glyphicon glyphicon-user"></i> ',i+=' <span class="title" data-number="',i+=f(a.empNum),i+='">',i+=f(a.nodeName),i+="</span> ","0"==a.nodeType&&a.empNum>0&&!h&&(i+=' <a href="javascript:" class="inside js-dept-inside"><i class="glyphicon glyphicon-menu-right"></i></a> '),i+=' <div class="clicker"></div> </li> '),i+=" "}),i+=" "):i+=' <li class="no-record">\u65e0\u8bb0\u5f55</li> ',new k(i)}),/*v:49*/
a("common/dept-tree-search",function(a){"use strict";var b=this,c=(b.$helpers,b.$each),d=a.list,e=(a.item,a.i,b.$escape),f=a.total,g="";return g+='<div class="inner"> <ul> ',c(d,function(a,b){g+=" ",5>b&&(g+=' <li data-id="',g+=e(a.eiId),g+='" data-no="',g+=e(a.eiId),g+='"> <div class="user dot">',g+=e(a.eiEmpName),g+='</div> <div class="dept dot">',g+=e(a.pathDeptName),g+="</div> </li> "),g+=" "}),g+=" </ul> ",f>5&&(g+=' <div class="more">\u5408\u8ba1',g+=e(f),g+="\u6761\u641c\u7d22\u7ed3\u679c\uff0c\u8bf7\u8f93\u5165\u66f4\u5b8c\u6574\u7684\u4fe1\u606f</div> "),g+=" </div>",new k(g)}),/*v:50*/
a("common/dept-tree-selected",function(a){"use strict";var b=this,c=(b.$helpers,b.$each),d=a.items,e=(a.item,a.$index,b.$escape),f="";return c(d,function(a){f+=' <span class="js-multi-select-item item" data-id="',f+=e(a.id),f+='">',f+=e(a.title),f+='<i class="js-multi-select-del del"></i></span> '}),new k(f)}),/*v:52*/
a("common/dept-tree-view",' <div class="dept-selector clearfix"> <div class="selector pull-left"> <div class="search"> <i class="glyphicon glyphicon-search"></i> <input type="text" name="" id="" class="form-control js-dept-search" placeholder="\u641c\u7d22\u4eba\u5458" maxlength="15"> <i class="glyphicon glyphicon-plus js-dept-search-cancel"></i> </div> <div class="path js-dept-path"> <div class="item"> <a href="javascript:" class="root" data-id="">\u901a\u8baf\u5f55</a> </div> </div> <div class="js-dept-select-all select-all"> <label><input type="checkbox" class="select-all-inp"> \u5168\u9009</label> </div> <div class="content"> <ul class="js-dept-tree"></ul> <ul class="js-dept-search-result"></ul> </div> <div class="js-dept-cover cover"></div> </div> <div class="shower pull-right"> <div class="shower-content"> <div class="header">\u5df2\u9009\u62e9</div> <ul class="js-dept-shower"></ul> </div> </div> </div>'),/*v:58*/
a("common/instant-search-dept",function(a){"use strict";var b=this,c=(b.$helpers,b.$each),d=a.list,e=(a.item,a.i,b.$escape),f=a.total,g="";return g+='<div class="inner"> <ul> ',c(d,function(a,b){g+=" ",5>b&&(g+=' <li data-id="',g+=e(a.eiEmpNo),g+='"> <div class="user dot">',g+=e(a.eiEmpName),g+='</div> <div class="dept dot">',g+=e(a.pathDeptName),g+="</div> </li> "),g+=" "}),g+=" </ul> ",f>5&&(g+=' <div class="more">\u5408\u8ba1',g+=e(f),g+="\u6761\u641c\u7d22\u7ed3\u679c\uff0c\u8bf7\u8f93\u5165\u66f4\u5b8c\u6574\u7684\u4fe1\u606f</div> "),g+=" </div>",new k(g)}),/*v:229*/
a("common/instant-search",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.placeholder,e=a.approvalEmpName,f=a.approvalEmpId,g="";return g+='<div class="instant-search"> <input type="text" id="instant-keywords" class="form-control" placeholder="',g+=c(d),g+='" value="',g+=c(e),g+='" autocomplete="off"> <input type="hidden" id="instant-selected" class="',g+=c(e),g+='" value="',g+=c(f),g+='"> <div class="instant-search-content"></div> </div>',new k(g)}),/*v:55*/
a("common/modal-pop",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.size,e=a.title,f=a.noIcon,g=b.$string,h=a.content,i=a.isCancel,j=a.sureText,l="";return l+='<div class="modal fade" id="modal-pop" role="dialog"> <div class="modal-dialog modal-',l+=c(d),l+='"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> <h4 class="modal-title" id="exampleModalLabel">',l+=c(e),l+='</h4> </div> <div class="modal-body',l+=c(f&&" no-icon"),l+='">',l+=c(f),l+=g(h),l+='</div> <div class="modal-footer"> ',i&&(l+=' <a class="btn btn-default" data-dismiss="modal">\u53d6\u6d88</a> '),l+=' <a class="btn btn-primary js-modal-sure" data-dismiss="modal">',l+=c(j),l+="</a> </div> </div> </div> </div> ",new k(l)}),/*v:54*/
a("common/pages",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.count,e=a.pageSize,f=a.items,g=b.$each,h=(a.item,a.$index,"");return h+='<ul class="pagination"> <li class="count"><span>\u5171 ',h+=c(d),h+=" \u6761\uff0c",h+=c(e||1),h+=' \u9875</span></li> <li class="first"><a href="javascript:">&laquo;</a></li> ',f.length?(h+=" ",g(f,function(a){h+=' <li class="item" data-page="',h+=c(a),h+='"><a href="javascript:">',h+=c(a),h+="</a></li> "}),h+=" "):h+=' <li class="item active"><a href="javascript:">1</a></li> ',h+=' <li class="last"><a href="javascript:">&raquo;</a></li> </ul>',new k(h)}),/*v:48*/
a("common/pop-msg",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.msg,e="";return e+='<textarea id="msg" class="layui-textarea" rows="5" maxlength="50" placeholder="',e+=c(d),e+='"></textarea>',new k(e)}),/*v:49*/
a("common/preview",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.items,e="";return e+=' <div class="preview infos-detail"> <div style="margin-bottom:10px;"> <div class="title" style="font-size:14px;margin-bottom:6px;">\u6807\u9898</div> <div style="font-size:16px;margin-bottom:6px;word-wrap: break-word;word-break: break-all;">',e+=c(d.infoTitle),e+='</div> </div> <div style="margin-bottom:10px;"> <div class="sum" style="font-size:14px;margin-bottom:6px;">\u6458\u8981</div> <div style="font-size:16px;margin-bottom:6px;word-wrap: break-word;word-break: break-all;">',e+=c(d.infoSummary),e+='</div> </div> <div style="margin-bottom:10px;"> <div class="title" style="font-size:14px;margin-bottom:6px;">\u6b63\u6587</div> <div style="font-size:16px;margin-bottom:6px;word-wrap: break-word;word-break: break-all;" class="cont js-article">',e+=c(d.infoContent),e+="</div> </div> </div> ",new k(e)}),/*v:53*/
a("common/record-empty",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.colspan,e=a.text,f="";return f+=' <tr> <td colspan="',f+=c(d),f+='" class="text-center">',f+=c(e||"\u6682\u65e0\u8bb0\u5f55"),f+="</td> </tr>",new k(f)}),/*v:53*/
a("common/select",function(a){"use strict";var b=this,c=(b.$helpers,b.$each),d=a.items,e=(a.item,a.$index,b.$escape),f=a.value,g="";return c(d,function(a){g+=' <option value="',g+=e(a.value),g+='" ',g+=e(a.value==f&&"selected"),g+=">",g+=e(a.title),g+="</option> "}),new k(g)}),/*v:50*/
a("common/selectedItems",function(a){"use strict";var b=this,c=(b.$helpers,b.$each),d=a.items,e=(a.item,a.$index,b.$escape),f="";return c(d,function(a){f+=' <span class="js-multi-select-item item" data-id="',f+=e(a.id),f+='">',f+=e(a.title),f+='<i class="js-multi-select-del del"></i></span> '}),new k(f)}),/*v:53*/
a("common/uploader-item",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.url,e=a.imageGroup,f=a.isSetDefault,g="";return g+='<div class="upload-item" data-url="',g+=c(d),g+='"> <a href="',g+=c(d),g+='" class="upload-item-inner" target="_blank" data-lightbox="',g+=c(e),g+='" style="background-image: url(\'',g+=c(d),g+="')\"> ",f&&(g+='<div class="upload-default">\u9ed8\u8ba4</div>'),g+=' </a> <a class="upload-close"><i class="icon-close"></i></a> </div>',new k(g)}),/*v:60*/
a("common/uploader",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.type,e=b.$string,f=a.tips,g="";return g+='<div class="uploader clearfix"> <form id="upload-form" enctype="multipart/form-data" class="hide"> <input type="file" name="file" id="upload" accept="',g+=c(d),g+='"> </form> <div class="upload-items clearfix pull-left"></div> <div class="upload-btn pull-left"></div> <div class="upload-tips pull-left">',g+=e(f),g+="</div> </div>",new k(g)}),/*v:22*/
a("common/week-selector",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.year,e=a.month,f=b.$each,g=a.items,h=(a.item,a.$index,a.day),i="";return i+='<div class="week-selector"> <div class="title text-center">',i+=c(d),i+="\u5e74",i+=c(e),i+='\u6708</div> <div class="selector"> <ul class="clearfix"> ',f(g,function(a){i+=' <li class="',i+=c(a.day===h&&"current"),i+='"> <div class="item"> <p class="day">',i+=c(a.day),i+='\u65e5</p> <p class="week">\u5468',i+=c(a.week),i+="</p> </div> </li> "}),i+=' </ul> </div> <a class="prev"><i class="glyphicon glyphicon-menu-left"></i></a> <a class="next"><i class="glyphicon glyphicon-menu-right"></i></a> </div>',new k(i)}),/*v:162*/
a("list/approval-list-detail",function(a){"use strict";var b=this,c=(b.$helpers,a.infoStatus),d=a.approvalOpinition,e=b.$escape,f=a.infoTitle,g=a.infoSummary,h=a.infoContent,i=a.infoCreatorName,j=a.infoUrgency,l=a.visibilityScope,m=a.infoScopeIsAll,n=a.infoScope,o="";return o+=' <div class="container-fluid"> <div class="row header js-title"> <h4>\u516c\u544a\u8be6\u60c5</h4> <div class="btns text-right"> ',1===c&&(o+=' <button class="layui-btn js-agree km-ripple btn btn-primary">\u540c\u610f</button> <button class="layui-btn js-refuse km-ripple btn btn-primary">\u62d2\u7edd</button> '),o+=' <button class="btn btn-default layui-btn layui-btn-blank js-preview">\u9884\u89c8</button> </div> </div> <div class="infos-detail js-main"> <table> <tbody> ',4===c||2===c||8===c?(o+=' <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u5ba1\u6838\u72b6\u6001</td> ',(4===c||8===c)&&(o+=' <td class="col-lg-3 col-md-3 col-sm-3 value">\u5df2\u901a\u8fc7</td> '),o+=" ",2===c&&(o+=' <td class="col-lg-3 col-md-3 col-sm-3 value">\u672a\u901a\u8fc7</td> '),o+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u7406\u7531</td> ',d?(o+=' <td class="col-lg-3 col-md-3 col-sm-3 value">',o+=e(d),o+="</td> "):(o+=' <td class="col-lg-3 col-md-3 col-sm-3 value">--</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6807\u9898</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-title">',o+=e(f),o+="</td> "),o+=' </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6458\u8981</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-desc">',o+=e(g),o+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6b63\u6587</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-article article">',o+=e(h),o+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> '):(o+=' <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6807\u9898</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-title">',o+=e(f),o+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6458\u8981</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-desc">',o+=e(g),o+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6b63\u6587</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-article article">',o+=e(h),o+="</td> </tr> "),o+=' </tbody> </table> </div> <div class="infos-detail js-main"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u8d77\u4eba</td> <td class="col-lg-3 col-md-3 col-sm-3 value">',o+=e(i||"--"),o+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u7c7b\u578b</td> ',o+=0===j?' <td class="col-lg-3 col-md-3 col-sm-3 value">\u4e00\u822c</td> ':' <td class="col-lg-3 col-md-3 col-sm-3 value">\u7d27\u6025</td> ',o+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u5e03\u5230</td> <td class="value">',o+=e(l||"--"),o+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u5e03\u8303\u56f4</td> ',1===m?o+=' <td class="col-lg-3 col-md-3 col-sm-3 value">\u516c\u5f00\uff0c\u5168\u90e8\u53ef\u89c1</td> ':(o+=' <td class="col-lg-3 col-md-3 col-sm-3 value">',o+=e(n),o+=' <a class="fuck-more underline js-show-more"><span></span><i class="icon"></i></a> </td> '),o+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> </tbody> </table> </div> </div> ',new k(o)}),/*v:28*/
a("list/approval-list",function(a){"use strict";var b=this,c=b.$helpers,d=b.$each,e=a.items,f=(a.item,a.$index,b.$escape),g=a.type,h="";return h+=" ",d(e,function(a){h+=' <tr data-id="',h+=f(a.id),h+='">  ',a.number1<10?(h+=' <td class="text-center" >0',h+=f(a.number1),h+="</td> "):(h+=' <td class="text-center" >',h+=f(a.number1),h+="</td> "),h+='  <td> <div class="titleList cont dot" title="',h+=f(a.infoTitle),h+='">',h+=f(a.infoTitle),h+='</div> </td>  <td> <div class="sumList cont dot"><a href="./approval-list-detail.html?id=',h+=f(a.id),h+='" class="underline js-view" title="',h+=f(a.infoSummary),h+='">',h+=f(a.infoSummary),h+='</a></div> </td>  <td> <div class="sTimeList cont dot" title="',a.initApprovalTime&&(h+=f(c.dateFormat(a.initApprovalTime,"yyyy-MM-dd hh:mm:ss"))),h+='"> ',0!==a.initApprovalTime?(h+=" ",h+=f(c.dateFormat(a.initApprovalTime,"yyyy-MM-dd hh:mm:ss")),h+=" "):h+=" -- ",h+=' </div> </td>  <td> <div class="fTimeList cont dot js-publish-time" title="',a.publishTime&&(h+=f(c.dateFormat(a.publishTime,"yyyy-MM-dd hh:mm:ss"))),h+='"> ',0!==a.publishTime?(h+=" ",h+=f(c.dateFormat(a.publishTime,"yyyy-MM-dd hh:mm:ss")),h+=" "):h+=" -- ",h+=' </div> </td>  <td> <div class="peopleList cont dot" title="item.infoCreatorName"> ',h+=f(a.infoCreatorName||"--"),h+=' </div> </td>  <td> <div class="statusList cont dot js-status" title="',"wfq"===g?h+=f(c.formatStatus(a.infoStatus)):(h+=f(1===a.infoStatus?"\u5f85":"\u5df2"),h+="\u5ba1\u6838"),h+='"> ',h+=f(1===a.infoStatus?"\u5f85":"\u5df2"),h+='\u5ba1\u6838 </div> </td>  <td class="actList js-act"> ',1===a.infoStatus&&(h+=' <a class="js-agree underline" data-id="',h+=f(a.id),h+='">\u540c\u610f</a> <a class="js-refuse underline" data-id="',h+=f(a.id),h+='">\u62d2\u7edd</a> <!-- <div class="btn-group"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\u67e5\u770b\u66f4\u591a <span class="caret"></span> </button> <ul class="dropdown-menu"> <li> <a class="js-agree underline" data-id="',h+=f(a.id),h+='">\u540c\u610f</a> </li> <li> <a class="js-refuse underline" data-id="',h+=f(a.id),h+='">\u62d2\u7edd</a> </li> </ul> </div> --> '),h+=" ",2===a.infoStatus&&(h+=" \u5df2\u5ba1\u6838\u62d2\u7edd "),h+=" ",(4===a.infoStatus||8===a.infoStatus)&&(h+=" \u5df2\u5ba1\u6838\u901a\u8fc7 "),h+=" </td> </tr> "}),h+=" ",new k(h)}),/*v:214*/
a("list/post-list-detail",function(a){"use strict";var b=this,c=(b.$helpers,a.infoStatus),d=b.$escape,e=a.id,f=a.infoTimePublish,g=a.approvalOpinition,h=a.infoTitle,i=a.infoSummary,j=a.infoContent,l=a.infoCreatorName,m=a.infoUrgency,n=a.visibilityScope,o=a.infoScopeIsAll,p=a.infoScope,q="";return q+='  <div class="container-fluid"> <div class="row header js-title"> <h4>\u516c\u544a\u8be6\u60c5</h4> <div class="btns text-right" type="button"> ',(1===c||8===c)&&(q+=' <button class="js-revoke km-ripple btn btn-primary">\u64a4\u56de</button> '),q+=" ",4===c&&(q+=' <button class="js-publish km-ripple btn btn-primary">\u53d1\u5e03</button> <button class="js-revoke km-ripple btn btn-primary">\u64a4\u56de</button> '),q+=" ",(2===c||16===c)&&(q+=' <a href="./add-notice.html?infoId=',q+=d(e),q+='" class=" js-edit km-ripple btn btn-primary">\u7f16\u8f91</a> '),q+=' <button class="js-preview btn btn-default">\u9884\u89c8</button> </div> </div> <div class="infos-detail js-main"> <table> <tbody> ',4===c||2===c?(q+=" <tr> ",1!==f&&(q+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u5ba1\u6279\u72b6\u6001</td> ',4===c&&(q+=' <td class="col-lg-3 col-md-3 col-sm-3 value js-title">\u5df2\u901a\u8fc7</td> '),q+=" ",2===c&&(q+=' <td class="col-lg-3 col-md-3 col-sm-3 value js-title">\u672a\u901a\u8fc7</td> '),q+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u7406\u7531</td> ',g?(q+=' <td class="col-lg-3 col-md-3 col-sm-3 value js-title">',q+=d(g),q+="</td> "):(q+=' <td class="col-lg-3 col-md-3 col-sm-3 value js-title">--</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6807\u9898</td> <td class="col-lg-3 col-md-3 col-sm-3 value js-title">',q+=d(h),q+="</td> "),q+=" "),q+=' </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6458\u8981</td> <td colspan=\'5\' class="col-lg-11 col-md-11 col-sm-11 value js-desc">',q+=d(i),q+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6b63\u6587</td> <td colspan=\'5\' class="col-lg-11 col-md-11 col-sm-11 value js-article article">',q+=d(j),q+="</td> </tr> "):(q+=' <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6807\u9898</td> <td class="col-lg-5 col-md-5 col-sm-5 value js-title">',q+=d(h),q+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6458\u8981</td> <td class="col-lg-5 col-md-5 col-sm-5 value js-desc">',q+=d(i),q+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6b63\u6587</td> <td colspan=\'3\' class="col-lg-11 col-md-11 col-sm-11 value js-article article">',q+=d(j),q+="</td> </tr> "),q+=' </tbody> </table> </div> <div class="infos-detail js-main"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u8d77\u4eba</td> <td class="col-lg-3 col-md-3 col-sm-3 value">',q+=d(l||"--"),q+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u7c7b\u578b</td> ',q+=0===m?' <td class="col-lg-3 col-md-3 col-sm-3 value">\u4e00\u822c</td> ':' <td class="col-lg-3 col-md-3 col-sm-3 value">\u7d27\u6025</td> ',q+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u9884\u7ea6\u53d1\u5e03</td> ',q+=1===f?' <td class="col-lg-3 col-md-3 col-sm-3 value">\u662f</td> ':' <td class="col-lg-3 col-md-3 col-sm-3 value">\u5426</td> ',q+=' </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u5e03\u5230</td> <td class="value">',q+=d(n||"--"),q+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u5e03\u8303\u56f4</td> ',1===o?q+=' <td class="col-lg-3 col-md-3 col-sm-3 value">\u516c\u5f00\uff0c\u5168\u90e8\u53ef\u89c1</td> ':(q+=' <td class="col-lg-3 col-md-3 col-sm-3 value">',q+=d(p),q+=' <a class="fuck-more underline js-show-more"><span></span><i class="icon"></i></a> </td> '),q+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> </tbody> </table> </div> </div> ',new k(q)}),/*v:42*/
a("list/post-list",function(a){"use strict";var b=this,c=b.$helpers,d=b.$each,e=a.items,f=(a.item,a.index,b.$escape),g="";return d(e,function(a,b){g+=' <tr data-id="',g+=f(a.id),g+='"> ',10>b+1?(g+=' <td class="text-center" >0',g+=f(b+1),g+="</td> "):(g+=' <td class="text-center" >',g+=f(b+1),g+="</td> "),g+="  <td> ",1===a.infoIsTop&&8===a.infoStatus?(g+=' <div class="titleList1 cont dot" title="',g+=f(a.infoTitle),g+='"><span style="color:#337ab7;">\u3010\u7f6e\u9876\u3011</span>',g+=f(a.infoTitle),g+="</div> "):(g+=' <div class="titleList1 cont dot" title="',g+=f(a.infoTitle),g+='">',g+=f(a.infoTitle),g+="</div> "),g+=' </td>  <td> <div class="sumList1 cont dot"><a href="./post-list-detail.html?id=',g+=f(a.id),g+='" class="underline js-infoSummary" title="',g+=f(a.infoSummary),g+='">',g+=f(a.infoSummary),g+='</a></div> </td>  <td> <div class="sTimeList cont dot" title="',a.initApprovalTime&&(g+=f(c.dateFormat(a.initApprovalTime,"yyyy-MM-dd hh:mm:ss"))),g+='"> ',0!==a.initApprovalTime?(g+=" ",g+=f(c.dateFormat(a.initApprovalTime,"yyyy-MM-dd hh:mm:ss")),g+=" "):g+=" -- ",g+=' </div> </td>  <td> <div class="fTimeList cont dot js-publish-time" title="',a.publishTime&&(g+=f(c.dateFormat(a.publishTime,"yyyy-MM-dd hh:mm:ss"))),g+='"> ',0!==a.publishTime?(g+=" ",g+=f(c.dateFormat(a.publishTime,"yyyy-MM-dd hh:mm:ss")),g+=" "):g+=" -- ",g+=' </div> </td>  <td> <div class="typeList cont dot"> ',g+=1===a.infoUrgency?" \u7d27\u6025 ":" \u4e00\u822c ",g+=' </div> </td>  <td> <div class="peopleList cont dot" title="item.approvalEmpName"> ',g+=f(a.approvalEmpName||"--"),g+=' </div> </td>  <td> <div class="statusList cont dot js-status"> ',1===a.infoStatus&&(g+=" \u5f85\u5ba1\u6838 "),g+=" ",16===a.infoStatus&&(g+=" \u5df2\u64a4\u56de "),g+=" ",2===a.infoStatus&&(g+=" \u5df2\u62d2\u7edd "),g+=" ",4===a.infoStatus&&(g+=" \u5f85\u53d1\u5e03 "),g+=" ",8===a.infoStatus&&(g+=" \u5df2\u53d1\u5e03 "),g+=' </div> </td>  <td class="text-center act"> ',1===a.infoIsTop&&8===a.infoStatus&&(g+=' <a class="js-top underline" data-id="',g+=f(a.infoIsTop),g+='">\u53d6\u6d88\u7f6e\u9876</a> '),g+=" ",0===a.infoIsTop&&8===a.infoStatus&&(g+=' <a class="js-cancleTop underline" data-id="',g+=f(a.infoIsTop),g+='">\u7f6e\u9876</a> '),g+=" ",(1===a.infoStatus||8===a.infoStatus)&&(g+=' <a class="js-revoke underline" data-id="',g+=f(a.infoId),g+='">\u64a4\u56de</a> '),g+=" ",4===a.infoStatus&&(g+=' <a class="js-publish underline" data-id="',g+=f(a.infoId),g+='">\u53d1\u5e03</a> <a class="js-revoke underline" data-id="',g+=f(a.infoId),g+='">\u64a4\u56de</a> '),g+=" ",(2===a.infoStatus||16===a.infoStatus)&&(g+=' <a href="./add-notice.html?infoId=',g+=f(a.id),g+='" class="js-edit underline" data-id="',g+=f(a.infoId),g+='">\u7f16\u8f91</a> '),g+=" </td> </tr> "}),g+=" ",new k(g)}),/*v:97*/
a("list/receive-list-detail",function(a){"use strict";var b=this,c=(b.$helpers,b.$escape),d=a.infoTitle,e=a.infoSummary,f=a.infoContent,g=a.infoCreatorName,h=a.infoUrgency,i=a.visibilityScope,j=a.infoScopeIsAll,l=a.infoScope,m="";return m+='<div class="container-fluid"> <div class="row header js-title"> <h4>\u516c\u544a\u8be6\u60c5</h4> <div class="btns text-right"> <button class="layui-btn layui-btn-blank js-preview btn btn-default">\u8fd4\u56de</button> </div> </div> <div class="infos-detail js-main"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6807\u9898</td> <td class="col-lg-5 col-md-5 col-sm-5 value js-title">',m+=c(d),m+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6458\u8981</td> <td class="col-lg-5 col-md-5 col-sm-5 value js-desc">',m+=c(e),m+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u6b63\u6587</td> <td colspan=\'3\' class="col-lg-11 col-md-11 col-sm-11 value js-article article">',m+=c(f),m+='</td> </tr> </tbody> </table> </div> <div class="infos-detail js-main"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u8d77\u4eba</td> <td class="col-lg-3 col-md-3 col-sm-3 value">',m+=c(g||"--"),m+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u7c7b\u578b</td> ',m+=0===h?' <td class="col-lg-3 col-md-3 col-sm-3 value">\u4e00\u822c</td> ':' <td class="col-lg-3 col-md-3 col-sm-3 value">\u7d27\u6025</td> ',m+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u5e03\u5230</td> <td class="value">',m+=c(i||"--"),m+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">\u53d1\u5e03\u8303\u56f4</td> ',1===j?m+=' <td class="col-lg-3 col-md-3 col-sm-3 value">\u516c\u5f00\uff0c\u5168\u90e8\u53ef\u89c1</td> ':(m+=' <td class="col-lg-3 col-md-3 col-sm-3 value">',m+=c(l),m+=' <a class="fuck-more underline js-show-more"><span></span><i class="icon"></i></a> </td> '),m+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> </tbody> </table> </div> </div> ',new k(m)}),/*v:56*/
a("list/receive-list",function(a){"use strict";var b=this,c=(b.$helpers,b.$each),d=a.items,e=(a.item,a.$index,b.$escape),f="";return c(d,function(a){f+=' <tr data-id="',f+=e(a.id),f+='">  ',a.number1<10?(f+=' <td class="text-center" >0',f+=e(a.number1),f+="</td> "):(f+=' <td class="text-center" >',f+=e(a.number1),f+="</td> "),f+='  <td> <div class="titleList2 cont dot" title="',f+=e(a.infoTitle),f+='">',f+=e(a.infoTitle),f+='</div> </td>  <td> <div class="sumList2 cont dot"><a href="./receive-list-detail.html?id=',f+=e(a.id),f+='" class="underline js-view" title="',f+=e(a.infoSummary),f+='">',f+=e(a.infoSummary),f+='</a></div> </td>  <td> <div class="typeList cont dot"> ',f+=1===a.infoUrgency?" \u7d27\u6025 ":" \u4e00\u822c ",f+=' </div> </td>  <td> <div class="peopleList cont dot" title="item.infoCreatorName"> ',f+=e(a.infoCreatorName||"--"),f+=" </div> </td> </tr> "}),f+=" ",new k(f)})}();
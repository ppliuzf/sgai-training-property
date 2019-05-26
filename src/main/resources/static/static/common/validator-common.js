/*
* 1. 根据项目需求追加的自定义验证规则
* 2. 业务层的配置说明（需写在对应业务页面中）
* 3. 本验证方法是基于jquery 与 jquery-validator 的基础上添加的自定义规则（需要引用其框架）
* */


/*
* 1. 经纬度效验
* */
jQuery.validator.addMethod("isXY", function (value, element) {
    var xyReg = /^(W|E)\d*\/(S|N)\d*$/;
    return this.optional(element) || xyReg.test(value);
}, "请填写E(W)20/N(S)30格式的经纬度");
/*
* 1.1 经度效验
* */
jQuery.validator.addMethod("isX", function (value, element) {
    var xReg = /^(W|E)\d*$/;
    return this.optional(element) || xReg.test(value);
}, "请填写E(W)20格式的经度");
/*
* 1.2 纬度效验
* */
jQuery.validator.addMethod("isY", function (value, element) {
    var yReg = /^(S|N)\d*$/;
    return this.optional(element) || yReg.test(value);
}, "请填写N(S)30格式的纬度");

/*
* 2. ip地址效验
* */
jQuery.validator.addMethod("isIp", function (value, element) {
    var ipReg = /^((25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))$/;
    return this.optional(element) || ipReg.test(value);
}, "请填写正确的ip地址");

/*
* 3. 手机与电话同时效验
* */
jQuery.validator.addMethod("isTel", function(value, element) {
    var telReg = /(^1[3-8]{1}\d{9}$)|(^(0[0-9]{2,3}\-)?\d{7,8}$)/;
    return this.optional(element) || telReg.test(value);
}, "请填写正确的手机或电话");

/*
* 4. 身份证效验
* */
jQuery.validator.addMethod("isCard", function(value, element) {
    var cardReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return this.optional(element) || cardReg.test(value);
}, "请填写正确的身份证号码");

/*
* 5. 车牌号效验
* */
jQuery.validator.addMethod("isvehiNo", function (value, element) {
    var vehiNo = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
    return this.optional(element) || vehiNo.test(value);
}, "请填写正确的车牌号码");
/*
* 6. 只能是数字和字母
* */
jQuery.validator.addMethod("notChinese", function (value, element) {
    var letter = /^[a-zA-Z0-9]+$/;
    return this.optional(element) || letter.test(value);
}, "请填写由字母和数字组成的格式");
/*
* 6. 非汉字且有个数限制
* */
jQuery.validator.addMethod("notChineseLenth", function (value, element, param) {
    var letters = /^[a-zA-Z0-9]+$/;
    var len =  value.length;
    console.log(len);
    return this.optional(element) || (letters.test(value) && len <= param[0]);
}, "请填写由字母和数字组成的格式");
/*
* 6. 只能是中文
* */
jQuery.validator.addMethod("isChinese", function (value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || chinese.test(value);
}, "请填写由汉字组成的格式");

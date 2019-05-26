/*
    参数说明：
        obj参数：一个DOM对象，getClass函数会取得这个对象下的全部元素节点。
        sClass：一个字符串，获取元素的类（class）名。
    */
function getClass(obj,sClass){
    // 准备一个数组 做为函数调用的结果返回
    var aResult = [];
    // 如果docuemnt对象中有getElementsByClassName这个方法 我们就用浏览器提供的因为浏览器提供的效率更高
    if('getElementsByClassName' in document){
        aResult = obj.getElementsByClassName(sClass);
    }
    // 如果浏览器没有 那么我们自己来实现 总共4步
    else{
        //1.获取obj下所有的元素
        var aEle = obj.getElementsByTagName('*');
        //2.用for循环来检查每个子元素
        for( var i=0; i<aEle.length; i++ ){
            // 3.得到每个子元素className值 这个值是字符串
            var str = aEle[i].className;
            // 4.如果该元素的class属性有我们需要的class 那么把它放进aResult
            if(str.indexOf(sClass)!=-1){
                aResult.push(aEle[i]);
            }
        }
    }
    return aResult;
}
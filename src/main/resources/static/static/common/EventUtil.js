var EventUtil = {

    // 添加事件监听
    add: function (element, type, callback) {

        if (element.addEventListener) {
            element.addEventListener(type, callback, false);
        } else if (element.attachEvent) {
            element.attachEvent('on' + type, callback);
        } else {
            element['on' + type] = callback;
        }
    },

    // 移除事件监听
    remove: function (element, type, callback) {

        if (element.removeEventListener) {
            element.removeEventListener(type, callback, false);
        } else if (element.detachEvent) {
            element.detachEvent('on' + type, callback);
        } else {
            element['on' + type] = null;
        }

    },

    // 跨浏览器获取 event 对象
    getEvent: function (event) {

        return event ? event : window.event;
    },

    // 跨浏览器获取 target 属性
    getTarget: function (event) {

        return event.target || event.srcElement;
    },

    //阻止事件的默认行为
    preventDefault: function (event) {

        if (event.preventDefault) {
            event.preventDefault();
        } else {
            event.returnValue = false;
        }
    },

    // 阻止事件流或使用 cancelBubble
    stopPropagation: function () {

        if (event.stopPropagation) {
            event.stopPropagation();
        } else {
            event.cancelBubble = true;
        }
    },
    // 设置cookie
    setCookie: function (key, value, t) {
        var oDate = new Date();
        oDate.setDate(oDate.getDate() + t);
        document.cookie = key + '=' + encodeURI(value) + ';expires=' + oDate.toGMTString();
    },
    // 获取cookie
    getCookie: function (key) {
        var arr1 = document.cookie.split('; ');
        for (var i = 0; i < arr1.length; i++) {
            var arr2 = arr1[i].split('=');
            if (arr2[0] == key) {
                return decodeURI(arr2[1]);
            }
        }
    },
    // 删除cookie
    removeCookie: function (key) {
        this.setCookie(key, '', -1);
    }

};
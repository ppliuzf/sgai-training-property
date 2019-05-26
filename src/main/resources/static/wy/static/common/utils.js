(function ($) {
    var Loading = function () {
        this.show();
    };
    Loading.prototype = {
        show: function () {
            var loading = $('<div id="loading"></div>');
            $('body').append(loading);
        },
        hide: function () {
            $('#loading').remove();
        }
    };
    var loading = function () {
        return new Loading();
    };
    $.loading = loading;
    $.extend({
        /**
         * 获取数据（默认post）
         */
        getData: function (options) {
            var setting = {
                showLoading: true,
                url: '',
                headers: {
                    token: localStorage.getItem("token")
                },
                type: 'post',
                query: '',
                body: null,
                async: true,
                crossDomain: true,
                success: function (data) {
                    if (data.code === '0') {
                        callback(data.data);
                    } else {
                        error ? error(data.message) : $.alert(data.message);
                    }
                },
                error: function () {
                    error ? error() : $.alert('服务器连接失败，请稍候再试');
                },
                complete: function () {
                    opt.showLoading && loading.hide();
                }
            };
            var opt = $.extend(setting, config);
            if (opt.showLoading) {
                var loading = $.loading();
            }
            if (!opt.url) {
                $.alert('参数配置有误，请检查');
            }
            if (opt.type === 'post' && opt.body) {
                opt.data = JSON.stringify(opt.body);
            }
            opt.url = serverUrl + opt.url + '?' + $.param(opt.query || {});
            $.ajax(opt);
        }

    });
})(jQuery);

注意：
> 新页面的 `title`，`script链接`

> 新项目 `config.js` 里的配置信息

> 移除 example


### 更新日志
2017-12-26
> 可直接覆盖

1. 增加 `switch开关` 插件，引入 js/lib/bootstrap-switch，样式需 import `bootstrap-switch.less`，详细：http://www.bootcss.com/p/bootstrap-switch/

2017-12-25
> 可直接覆盖

1. 增加周历切换方法 $.weekSelector()

2017-12-21
> 可直接覆盖

1. 增加日历插件，http://www.bootcss.com/p/bootstrap-datetimepicker/
2. 日历需要需要引入2个JS，`js/lib/bootstrap-datetimepicker.min.js`，`js/lib/bootstrap-datetimepicker.zh-CN.js`，注意顺序
3. 新增获取时间戳方法 $.getTimeStamp()
4. 删除无用的 template-web.js
5. 增加计数器方法 $.counter()
6. 增加msg方法 $.msg()，类似于 toast
7. 模板新增 dateFormat 方法，格式化时间戳为字符串
8. 模板 select.html 增加 selected 状态，用于编辑时值回显，参数为 value

$.count 示例
```js
$.count({
    el: '#id', // 文本框 id，默认 #textarea
    count: '.count', // 计数器 id，默认 .js-count
    max: 300 // 输入最大长度值，默认 200
});
```

$.msg 示例
```js
// 第二个参数为回调
$.msg('操作成功', function() {
    // 成功
});
// 第二个参数为配置项
$.msg('操作成功', {
    time: 2000
}, function() {
    // 成功
});
```

2017-12-20
> 可直接替换

1. 更新无记录模板文字居中显示
2. 更新分页，无数据时显示1页
3. 分页显示条数和页码
4. 样式新增 .box，用于新增时的分块显示，可参考 客户档案 templates/archives-add-card.html
5. 默认 .container-fluid 增加 padding-bottom 值
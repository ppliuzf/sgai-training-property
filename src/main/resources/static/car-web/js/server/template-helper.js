//时间戳格式化
template.helper('dateFormat', function(date, format) {
	if (date) {
		date = new Date(parseInt(date));
		var map = {
			'M': date.getMonth() + 1, //月
			'd': date.getDate(), //日
			'h': date.getHours(), //时
			'm': date.getMinutes(), //分
			's': date.getSeconds(), //秒
			'q': Math.floor((date.getMonth() + 3) / 3), //季度
			'S': date.getMilliseconds(), //毫秒
			'D': date.getDay()
		};
		format = format.replace(/([yMdhmsqSD])+/g, function(all, t) {
			var v = map[t];
			if (v !== undefined) {
				if (t == 'D') {
					switch (v) {
						case 1:
							v = '一';
							break;
						case 2:
							v = '二';
							break;
						case 3:
							v = '三';
							break;
						case 4:
							v = '四';
							break;
						case 5:
							v = '五';
							break;
						case 6:
							v = '六';
							break;
						case 0:
							v = '日';
							break;
					}
				} else {
					if (all.length > 1) {
						v = '0' + v;
						v = v.substr(v.length - 2);
					}
				}
				return v;
			} else if (t === 'y') {
				return (date.getFullYear() + '').substr(4 - all.length);
			}
			return all;
		});
		return format;
	}
	return '';
});
/**
 * 一些通用方法
 */
(function(exports) {

	/**
	 * 将string字符串转为html对象,默认创一个div填充
	 * 因为很常用，所以单独提取出来了
	 * @param {String} strHtml 目标字符串
	 * @return {HTMLElement} 返回处理好后的html对象,如果字符串非法,返回null
	 */
	exports.parseHtml = function(strHtml) {
		if(typeof strHtml !== 'string') {
			return strHtml;
		}
		// 创一个灵活的div
		var i,
			a = document.createElement('div');
		var b = document.createDocumentFragment();

		a.innerHTML = strHtml;

		while((i = a.firstChild)) {
			b.appendChild(i);
		}

		return b;
	};

	/**
	 * 将对象渲染到模板
	 * @param {String} template 对应的目标
	 * @param {Object} obj 目标对象
	 * @return {String} 渲染后的模板
	 */
	exports.renderTemplate = function(template, obj) {
		return template.replace(/[{]{2}([^}]+)[}]{2}/g, function($0, $1) {
			return obj[$1] || '';
		});
	};

	/**
	 * 定义一个计数器
	 */
	var counterArr = [0];

	/**
	 * 添加测试数据
	 * @param {String} dom 目标dom
	 * @param {Number} count 需要添加的数量
	 * @param {Boolean} isReset 是否需要重置，下拉刷新的时候需要
	 * @param {Number} index 属于哪一个刷新
	 */
	exports.appendTestData = function(dom, count, isReset, index) {
		if(typeof dom === 'string') {
			dom = document.querySelector(dom);
		}

		var prevTitle = typeof index !== 'undefined' ? ('Tab' + index) : '';

		var counterIndex = index || 0;

		counterArr[counterIndex] = counterArr[counterIndex] || 0;

		if(isReset) {
			dom.innerHTML = '';
			counterArr[counterIndex] = 0;
		}

		$.ajax({
			type: 'POST',
			url: "http://app.grjf365.com:9080/user/api/mercuries/info-list/MercuryInfo",
			async: true,
			data: JSON.stringify({
				"langitude": "116.66847",
				"lantitude": "39.88372"
			}),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				var template = ''
				if(data.length>=1){
					$.each(data,function(i,item){
						template += '<li class="shopList flex spaceBetween" data="'+item.id+'" ><img class="listLf" src='+item['img_license']+' /><div class="flex1 flexNowrap listRig"><div class="flex spaceBetween listRig_name"><p class="listRig_name_title">'+item['name']+'</p></div><div class="listRig_mid"><p class="star fl"><span class="star-in fl" style="width:75%;"></span></p><span class="color67 font20">| &nbsp;&nbsp;123人光临</span></div><div class="flex spaceBetween color67 font20"><p class="flex1 overflow">'+item['city']+'</p><p>8.8km</p></div></div></li>';
						console.log(template)
						counterArr[counterIndex]++;
					})
					
					// var html = '',
					// 	dateStr = (new Date()).toLocaleString();
	
					// for(var i = 0; i < count; i++) {
					// 	html += exports.renderTemplate(template, {
					// 	});
	
					// 	counterArr[counterIndex]++;
					// }
	
					// var child = exports.parseHtml(html);
					dom.appendChild(template);
					
				}else{
					
				}
				
			},
			error: function() {
				console.log('暂无数据')
			}
		});

	};

	/**
	 * 绑定监听事件 暂时先用click
	 * @param {String} dom 单个dom,或者selector
	 * @param {Function} callback 回调函数
	 * @param {String} eventName 事件名
	 */
	exports.bindEvent = function(dom, callback, eventName) {
		eventName = eventName || 'click';
		if(typeof dom === 'string') {
			// 选择
			dom = document.querySelectorAll(dom);
		}
		if(!dom) {
			return;
		}
		if(dom.length > 0) {
			for(var i = 0, len = dom.length; i < len; i++) {
				dom[i].addEventListener(eventName, callback);
			}
		} else {
			dom.addEventListener(eventName, callback);
		}
	};
})(window.Common = {});
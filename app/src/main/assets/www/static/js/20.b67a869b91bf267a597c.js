webpackJsonp([20],{"5fGY":function(t,a,e){t.exports=e.p+"static/img/微信.072aad3.png"},Ve79:function(t,a,e){"use strict";(function(t){var n=e("mvHQ"),i=e.n(n),s=e("Au9i");e.n(s);a.a={data:function(){return{value:!1}},mounted:function(){var a=this;t(".pay").click(function(){var e=t('input:radio[name="sex"]:checked').val(),n={payType:e};console.log(a),2==e&&a.$axios({method:"post",url:"order/api/leaguer-orders",data:n}).then(function(t){var a={func:"pay",param:{payType:"Ali",orderStr:t.data}};console.log(a);var e=navigator.userAgent,n=e.indexOf("Android")>-1||e.indexOf("Adr")>-1;!!e.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)?window.webkit.messageHandlers.GongrongAppModel.postMessage(a):n&&window.androidObject.JSCallAndroid(i()(a))}).catch(function(t){console.log(t)}),3==e&&a.$axios({method:"post",url:"order/api/leaguer-orders",data:n}).then(function(t){var a={func:"pay",param:{payType:"Wechat",orderStr:t.data}};console.log(a);var e=navigator.userAgent,n=e.indexOf("Android")>-1||e.indexOf("Adr")>-1;!!e.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)?window.webkit.messageHandlers.GongrongAppModel.postMessage(a):n&&window.androidObject.JSCallAndroid(i()(a))}).catch(function(t){console.log(t)})})},methods:{}}}).call(a,e("7t+N"))},"We+Y":function(t,a){},vwj0:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var n=e("Ve79"),i={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"MerchantsPayCost"},[e("header",{staticClass:"mint-header"},[e("div",{staticClass:"mint-header-button is-left"},[e("a",{staticClass:"router-link-active"},[e("router-link",{staticClass:"mint-button mint-button--default mint-button--normal",attrs:{to:{path:"/SellerCenter"},tag:"button"}},[e("mt-button",{attrs:{icon:"back"}})],1)],1)]),t._v(" "),e("h1",{staticClass:"mint-header-title"},[t._v("商家平台使用缴纳")]),t._v(" "),e("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),t._m(0),t._v(" "),e("footer",{staticClass:"footer"},[e("P",[e("span",[e("input",{attrs:{type:"radio",id:"read",name:"nan",value:"read",checked:""}}),t._v(" "),e("label",{attrs:{for:"read"}})]),t._v("\n            我已阅读并接受"),e("i",[t._v("《贡融商户协议》")]),t._v("和"),e("i",[t._v("《商家入驻须知》")])]),t._v(" "),e("h2",{staticClass:"pay"},[t._v("确认支付")])],1)])},staticRenderFns:[function(){var t=this,a=t.$createElement,n=t._self._c||a;return n("div",{staticClass:"content"},[n("ul",{staticClass:"list"},[n("li",[n("span",[t._v("会员等级:")]),n("em",[t._v("增值会员")])]),t._v(" "),n("li",[n("span",[t._v("年费状态:")]),n("em",[t._v("未缴纳年费")])]),t._v(" "),n("li",[n("span",[t._v("缴费类型:")]),n("em",[t._v("增值商家平台使用费")])]),t._v(" "),n("p",[t._v("需要支付:998元")])]),t._v(" "),n("div",[n("ul",{staticClass:"top_bottom1"},[n("li",[n("img",{attrs:{src:e("WUXe")}})]),t._v(" "),n("li",[n("p",[t._v("支付宝")]),t._v(" "),n("p",[t._v("因为信任,所以简单")]),t._v(" "),n("span",[n("input",{attrs:{type:"radio",id:"zhifubao",name:"sex",value:"2",checked:""}})])])]),t._v(" "),n("ul",{staticClass:"top_bottom1 top_bottom2"},[n("li",[n("img",{attrs:{src:e("5fGY")}})]),t._v(" "),n("li",[n("p",[t._v("微信")]),t._v(" "),n("p",[t._v("放心支付,免费生活")]),t._v(" "),n("span",[n("input",{attrs:{type:"radio",id:"kuaijiezhifu",value:"3",name:"sex"}})])])])])])}]};var s=function(t){e("We+Y")},o=e("VU/8")(n.a,i,!1,s,"data-v-b71eb718",null);a.default=o.exports}});
//# sourceMappingURL=20.b67a869b91bf267a597c.js.map
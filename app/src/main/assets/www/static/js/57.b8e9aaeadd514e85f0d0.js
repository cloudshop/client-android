webpackJsonp([57],{X4kR:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var n=e("mvHQ"),s=e.n(n),i=(e("lkvS"),{data:function(){return{arr:"null",brr:"null",audit:"null",datt:"",time:[],bol:!1}},created:function(){var t=this;this.$axios.get("wallet/api/wallets/user").then(function(a){t.arr=a.data}).catch(function(t){console.log(t)}),this.$axios.get("wallet/api/wallet/details/balance?page=0&size=10&sort=createdTime%2Cdesc").then(function(a){a.data.map(function(a){var e=a.createdTime,n=new Date(e),s=t.date(n);a.createdTime=s}),t.brr=a.data}).catch(function(t){console.log(t)}),this.$axios.get("user/api/my-auth").then(function(a){console.log(a),t.datt=a.data,t.audit=a.data.statusString}).catch(function(t){console.log(t)})},methods:{sure:function(){this.bol=!1},one:function(){},date:function(t){var a=t.getFullYear(),e=t.getMonth()+1,n=e<10?"0"+e:e,s=t.getDate(),i=s<10?"0"+s:s,o=t.getHours(),l=t.getMinutes(),r=l<10?"0"+l:l,c=t.getSeconds(),u=a+"-"+n+"-"+i+"　"+o+":"+r+":"+(c<10?"0"+c:c);return u},out:function(){var t=this.audit;console.log(t),null==t?this.bol=!0:"审核通过"==t&&this.$router.push({path:"/Cash"})},close:function(){var t={func:"closeCurrent",param:{finallyIndex:4}},a=navigator.userAgent,e=a.indexOf("Android")>-1||a.indexOf("Adr")>-1;!!a.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)?window.webkit.messageHandlers.GongrongAppModel.postMessage(t):e&&window.androidObject.JSCallAndroid(s()(t))}}}),o={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"RemainingSum"},[e("header",{staticClass:"mint-header"},[e("div",{staticClass:"mint-header-button is-left"},[e("a",{staticClass:"router-link-active"},[e("button",{staticClass:"mint-button mint-button--default mint-button--normal",on:{click:t.close}},[e("mt-button",{attrs:{icon:"back"}})],1)])]),t._v(" "),e("h1",{staticClass:"mint-header-title"},[t._v("余额")]),t._v(" "),e("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),e("div",{staticClass:"content"},[e("div",{staticClass:"banner"},[e("p",[t._v("余额")]),t._v(" "),e("h2",[t._v(t._s(t.arr.balance))])]),t._v(" "),e("div",{staticClass:"main"},t._l(t.brr,function(a){return e("ul",{staticClass:"list"},[e("li",[e("p",[e("span",[t._v(t._s(a.createdTime))]),e("span")]),t._v(" "),e("p",[e("b",[t._v(t._s(a.typeString))]),e("b",[t._v(t._s(a.balance))])])])])}))]),t._v(" "),e("footer",{staticClass:"footer"},[e("button",{on:{click:t.out}},[t._v("转出")]),t._v(" "),e("router-link",{attrs:{to:{path:"/top"},tag:"button"}},[t._v("转入")])],1),t._v(" "),e("div",{directives:[{name:"show",rawName:"v-show",value:t.bol,expression:"bol"}],staticClass:"msg"},[e("div",{staticClass:"succeed"},[e("h3",[t._v("提示")]),t._v(" "),e("p",[t._v("尚未实名认证，请前往个人中心实名认证")]),t._v(" "),e("span",{on:{click:t.sure}},[t._v("确定")])])])])},staticRenderFns:[]};var l=e("VU/8")(i,o,!1,function(t){e("XdCD")},"data-v-0d3405f8",null);a.default=l.exports},XdCD:function(t,a){}});
webpackJsonp([20],{"40+g":function(t,e,s){"use strict";(function(t){var a=s("mvHQ"),i=s.n(a),n=s("5reh");e.a={data:function(){return{phone:"",authCode:"",setPassword:"",affirmPassword:"",flag:!0,flag1:!1}},methods:{callBack:function(){0==this.flag?(this.flag=!0,this.flag1=!1):this.$router.go(-1)},upperCase:function(){var t=document.getElementsByClassName("iphones")[0].value;0==/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(t)?(alert("请填写正确电话号码!!"),document.getElementsByClassName("iphones")[0].value=""):this.iphoneYN=!0},nextPwd:function(){this.flag=!1,this.flag1=!0},next:function(){if(document.getElementById("setPassword").value!==document.getElementById("affirmPassword").value)alert("两次密码输入不符"),document.getElementById("setPassword").value="",document.getElementById("affirmPassword").value="",this.setPassword="",this.affirmPassword="";else{var t={login:this.phone,password:this.setPassword,verifyCode:this.authCode};this.$axios.post("uaa/api/account/update-password",t).then(function(t){if(200==t.status){alert("修改成功");var e={func:"openURL",param:{URL:"/#/login"}},s=navigator.userAgent,a=s.indexOf("Android")>-1||s.indexOf("Adr")>-1;!!s.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)?window.webkit.messageHandlers.GongrongAppModel.postMessage(e):a&&window.androidObject.JSCallAndroid(i()(e))}}).catch(function(t){500===t.response.status?alert("服务器繁忙，请耐心等待"):alert(t.response.data.title)})}},gain:function(e){var s=document.getElementById("mytest").value;this.$store.commit(n.e,this.theinput);0==/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(s)?(alert("请填写正确手机号！"),document.getElementById("mytest").value=""):this.$axios.get("verify/api/verify/smscode/login/"+this.phone).then(function(s){var a=60;!function e(s){if(0==a)return t(s).attr("disabled",!1),t(s).text("获取验证码"),void(a=60);t(s).attr("disabled",!0),t(s).text(a+" s 重新发送"),a--;setTimeout(function(){e(s)},1e3)}(e)}).catch(function(t){500===t.response.status?alert("服务器繁忙，请耐心等待"):alert(t.response.data.title)})}},created:function(){},mounted:function(){t("input").on("keyup",function(){t("#mytest").val().length>=1&&t("#yzms").val().length>=1?(t(".next_btn").addClass("Color"),t(".next_btn").removeAttr("disabled")):(t(".next_btn").removeClass("Color"),t(".next_btn").attr("disabled","true"))}),t("input").on("keyup",function(){t("#setPassword").val().length>=1&&t("#affirmPassword").val().length>=1?t(".nexts_btn").addClass("Color"):t(".nexts_btn").removeClass("Color")})}}}).call(e,s("7t+N"))},"7NDK":function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFcAAABXCAYAAABxyNlsAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkQwOTNCNDc3M0M5QTExRThBRDZBOUExN0Q3MkZBMzAyIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkQwOTNCNDc4M0M5QTExRThBRDZBOUExN0Q3MkZBMzAyIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RDA5M0I0NzUzQzlBMTFFOEFENkE5QTE3RDcyRkEzMDIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RDA5M0I0NzYzQzlBMTFFOEFENkE5QTE3RDcyRkEzMDIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4ZikQeAAAI9ElEQVR42uyd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"},KrQG:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("40+g"),i={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"registers"},[s("header",{staticClass:"mint-header"},[s("div",{staticClass:"mint-header-button is-left"},[s("a",{staticClass:"router-link-active"},[s("mt-button",{attrs:{icon:"back"},on:{click:t.callBack}})],1)]),t._v(" "),s("h1",{staticClass:"mint-header-title"},[t._v("重置登录密码")]),t._v(" "),s("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:t.flag,expression:"flag"}],staticClass:"register_main"},[s("div",{staticClass:"iphone"},[t._m(0),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.phone,expression:"phone"}],staticClass:"iphones",attrs:{type:"text",placeholder:"请输入手机号",id:"mytest"},domProps:{value:t.phone},on:{blur:t.upperCase,input:function(e){e.target.composing||(t.phone=e.target.value)}}}),t._v(" "),s("div",{staticClass:"iphones"},[s("span",{staticClass:"one"},[t._v("|")]),s("button",{staticClass:"iphone_btn",attrs:{id:"iphone_btn"},on:{click:function(e){t.gain(".iphone_btn")}}},[t._v("获取验证码")])])]),t._v(" "),s("div",{staticClass:"iphone"},[t._m(1),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.authCode,expression:"authCode"}],attrs:{type:"text",id:"yzms",placeholder:"请输入验证码"},domProps:{value:t.authCode},on:{input:function(e){e.target.composing||(t.authCode=e.target.value)}}})]),t._v(" "),s("div",{staticClass:"next"},[s("input",{staticClass:"next_btn",attrs:{type:"button",disabled:"",value:"下一步"},on:{click:t.nextPwd}})])]),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:t.flag1,expression:"flag1"}],staticClass:"center content"},[s("p",[t._v("设置密码"),s("input",{directives:[{name:"model",rawName:"v-model",value:t.setPassword,expression:"setPassword"}],staticClass:"input",attrs:{type:"password",id:"setPassword"},domProps:{value:t.setPassword},on:{input:function(e){e.target.composing||(t.setPassword=e.target.value)}}})]),t._v(" "),s("p",{staticClass:"yzm"},[t._v("确认密码"),s("input",{directives:[{name:"model",rawName:"v-model",value:t.affirmPassword,expression:"affirmPassword"}],staticClass:"input",attrs:{type:"password",id:"affirmPassword"},domProps:{value:t.affirmPassword},on:{input:function(e){e.target.composing||(t.affirmPassword=e.target.value)}}})]),t._v(" "),s("div",{staticClass:"nexts"},[s("input",{staticClass:"nexts_btn",attrs:{type:"button",value:"下一步"},on:{click:t.next}})])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("p",[this._v("+86 "),e("span",[this._v("∨")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("p",{staticClass:"authCode"},[e("img",{attrs:{src:s("7NDK"),alt:""}})])}]};var n=function(t){s("fC3E")},o=s("VU/8")(a.a,i,!1,n,"data-v-2e96e23b",null);e.default=o.exports},fC3E:function(t,e){}});
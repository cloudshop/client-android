webpackJsonp([6,35,50],{"1KMH":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});a("Au9i");var s={data:function(){return{type:3,user:""}},methods:{AppreciationPageClose:function(){this.$parent.$parent.AppreciationPage=!1}},created:function(){var t=this;this.$axios.get("user/api/user-annexes/userInfo").then(function(e){t.user=e.data}).catch(function(t){console.log(t)})}},i={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"Appreciation"},[a("header",{staticClass:"mint-header"},[a("div",{staticClass:"mint-header-button is-left"},[a("a",{staticClass:"router-link-active"},[a("router-link",{staticClass:"mint-button mint-button--default mint-button--normal",attrs:{to:{name:"SellerCenter",params:{type:this.type}},tag:"button"}},[a("mt-button",{attrs:{icon:"back"}})],1)],1)]),t._v(" "),a("h1",{staticClass:"mint-header-title"},[t._v("支付增值商户年费")]),t._v(" "),a("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),a("div",{staticClass:"content"},[a("div",{staticClass:"nav"},[a("dl",[a("dt",[a("img",{attrs:{src:t.user.avatar,alt:""}})]),t._v(" "),t._m(0)])]),t._v(" "),a("div",{staticClass:"PayAFee"},[a("h2",[t._v("增值商户年费")]),t._v(" "),a("p",[a("b",[t._v("￥998.00")]),t._v(" "),a("router-link",{attrs:{to:{path:"/MerchantsPayCost"},tag:"button"}},[t._v("开通")])],1)])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("dd",[e("h2",[this._v("用户名")]),this._v(" "),e("p",[this._v("当前尚未开通")])])}]};var r=a("VU/8")(s,i,!1,function(t){a("QsWh")},"data-v-491cc2d3",null);e.default=r.exports},"9iNs":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("mvHQ"),i=a.n(s),r=(a("Au9i"),a("gqfv")),n=a("1KMH"),l={data:function(){return{OrdinaryBusinessPage:!1,AppreciationPage:!1,type:1,bol:!1}},methods:{backMine:function(){this.$router.push({path:"/Mine"})},SellerCenterClose:function(){this.$parent.$parent.SellerCenterPage=!1},OrdinaryBusinessPageOpen:function(){this.OrdinaryBusinessPage=!0},AppreciationPageOpen:function(){this.AppreciationPage=!0},back:function(){var t={func:"closeCurrent",param:{}},e=navigator.userAgent,a=e.indexOf("Android")>-1||e.indexOf("Adr")>-1;!!e.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)?window.webkit.messageHandlers.GongrongAppModel.postMessage(t):a&&window.androidObject.JSCallAndroid(i()(t))}},components:{OrdinaryBusiness:r.default,Appreciation:n.default},created:function(){this.type=this.$route.params.type,console.log(this.$route.params);var t=window.location.href;this.type=t.split("?")[1].split("=")[1],5==this.type&&(this.bol=!0)}},o={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"SellerCenter"},[a("header",{staticClass:"mint-header"},[a("div",{staticClass:"mint-header-button is-left"},[a("a",{staticClass:"router-link-active"},[a("button",{staticClass:"mint-button mint-button--default mint-button--normal",on:{click:t.back}},[a("mt-button",{attrs:{icon:"back"}})],1)])]),t._v(" "),a("h1",{staticClass:"mint-header-title"},[t._v("商户中心")]),t._v(" "),a("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:1==t.type,expression:"type==1?true:false"}],staticClass:"TopContent"},[a("h2",[t._v("普通商家相关权益")]),t._v(" "),a("h3",[t._v("入驻要求")]),t._v(" "),t._m(0),t._v(" "),a("h3",[t._v("积分奖励")]),t._v(" "),t._m(1),t._v(" "),a("h3",[t._v("积分奖励")]),t._v(" "),t._m(2),t._v(" "),a("router-link",{staticClass:"MyBtn",attrs:{to:{path:"/OrdinaryBusiness"},tag:"button"}},[t._v("申请成为普通商家")])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:2==t.type,expression:"type==2?true:false"}],staticClass:"TopContent"},[a("h2",[t._v("普通商家相关权益")]),t._v(" "),a("h3",[t._v("入驻要求")]),t._v(" "),t._m(3),t._v(" "),a("h3",[t._v("积分奖励")]),t._v(" "),t._m(4),t._v(" "),a("h3",[t._v("积分奖励")]),t._v(" "),t._m(5),t._v(" "),a("router-link",{staticClass:"MyBtn",attrs:{to:{path:"/OrdinaryBusiness"},tag:"button"}},[t._v("申请成为普通商家")])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:3==t.type,expression:"type==3?true:false"}],staticClass:"BottomContent"},[a("h2",[t._v("增值商家相关权益")]),t._v(" "),a("h3",[t._v("入驻要求")]),t._v(" "),t._m(6),t._v(" "),a("h3",[t._v("积分奖励")]),t._v(" "),t._m(7),t._v(" "),a("h3",[t._v("积分奖励")]),t._v(" "),t._m(8),t._v(" "),a("router-link",{staticClass:"MyBtn",attrs:{to:{path:"/Appreciation"},tag:"button"}},[t._v("申请成为增值商家")])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:4==t.type,expression:"type==4?true:false"}],staticClass:"ServiceContent"},[a("h3",[t._v("服务商权益")]),t._v(" "),t._m(9),t._v(" "),a("h3",[t._v("商家入驻流程")]),t._v(" "),t._m(10),t._v(" "),a("router-link",{staticClass:"MyBtn",attrs:{to:{path:"/servershop"},tag:"button"}},[t._v("申请成为服务商")])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:5==t.type,expression:"type==5?true:false"}],staticClass:"ServiceContent"},[a("h3",[t._v("服务商权益")]),t._v(" "),t._m(11),t._v(" "),a("h3",[t._v("商家入驻流程")]),t._v(" "),t._m(12),t._v(" "),a("router-link",{staticClass:"MyBtn",attrs:{to:{path:"/servershop"},tag:"button"}},[t._v("申请成为服务商")])],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.bol,expression:"bol"}],staticClass:"msgg"},[a("div",{staticClass:"succeed"},[a("h3",[t._v("提示")]),t._v(" "),a("p",[t._v("您已申请为服务商")]),t._v(" "),a("span",{on:{click:t.backMine}},[t._v("确定")])])]),t._v(" "),a("mt-popup",{attrs:{position:"right",modal:!1},model:{value:t.OrdinaryBusinessPage,callback:function(e){t.OrdinaryBusinessPage=e},expression:"OrdinaryBusinessPage"}},[a("OrdinaryBusiness")],1),t._v(" "),a("mt-popup",{attrs:{position:"right",modal:!1},model:{value:t.AppreciationPage,callback:function(e){t.AppreciationPage=e},expression:"AppreciationPage"}},[a("Appreciation")],1)],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("年服务费0元;")]),this._v(" "),e("li",[this._v("提供身份证,营业执照等相关信息;")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[t._v("享受自己消费,商家让利额10倍贡融积分奖励;")]),t._v(" "),a("li",[t._v("直接分享会员消费让利10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("间接分享会员消费让利额10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("直接分享商家让利额10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("间接分享商家让利额10%的贡融积分奖励;")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("分享增值商家入驻100元现金奖励")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("年服务费0元;")]),this._v(" "),e("li",[this._v("提供身份证,营业执照等相关信息;")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[t._v("享受自己消费,商家让利额10倍贡融积分奖励;")]),t._v(" "),a("li",[t._v("直接分享会员消费让利10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("间接分享会员消费让利额10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("直接分享商家让利额10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("间接分享商家让利额10%的贡融积分奖励;")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("分享增值商家入驻100元现金奖励")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("年服务费998元/年;")]),this._v(" "),e("li",[this._v("提供身份证,营业执照等相关信息;")]),this._v(" "),e("li",[this._v("提供商家管理者身份认证;")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[t._v("享受自身让利额2倍的贡融积分奖励;")]),t._v(" "),a("li",[t._v("享受自己消费,商家让利额10倍贡融积分奖励;")]),t._v(" "),a("li",[t._v("直接分享会员消费让利10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("间接分享会员消费让利额10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("直接分享商家让利额10%的贡融积分奖励;")]),t._v(" "),a("li",[t._v("间接分享商家让利额10%的贡融积分奖励;")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("分享增值商家入驻100元现金奖励")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[t._v("1 享受自身销售让利贡融积分奖励;")]),t._v(" "),a("li",[t._v("2 享受体系内商家入住分红;")]),t._v(" "),a("li",[t._v("3 享受体系内商家销售交易手续费分红;")]),t._v(" "),a("li",[t._v("4 享受体系内无限制商家销售让利积分奖励;")]),t._v(" "),a("li",[t._v("5 享受间接体系内无线商家销售让利积分奖励;")]),t._v(" "),a("li",[t._v("6 享受间接享受体系内无限制商家销售让利积分奖励;")]),t._v(" "),a("li",[t._v("7 可建立贡融积分线下运营中心;")]),t._v(" "),a("li",[t._v("8 有机会参加贡融积分服务商大会;")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("1 已有贡融商城入驻增值商家;")]),this._v(" "),e("li",[this._v("2 缴纳全额平台代理费用（2万元）;")]),this._v(" "),e("li",[this._v("3 确定服务商申请协议，并上传公司及法人代表相关资料和资格证件;")]),this._v(" "),e("li",[this._v("4 开始拓展市场，发展商家入驻贡融商城;")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",[a("li",[t._v("1 享受自身销售让利贡融积分奖励;")]),t._v(" "),a("li",[t._v("2 享受体系内商家入住分红;")]),t._v(" "),a("li",[t._v("3 享受体系内商家销售交易手续费分红;")]),t._v(" "),a("li",[t._v("4 享受体系内无限制商家销售让利积分奖励;")]),t._v(" "),a("li",[t._v("5 享受间接体系内无线商家销售让利积分奖励;")]),t._v(" "),a("li",[t._v("6 享受间接享受体系内无限制商家销售让利积分奖励;")]),t._v(" "),a("li",[t._v("7 可建立贡融积分线下运营中心;")]),t._v(" "),a("li",[t._v("8 有机会参加贡融积分服务商大会;")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("ul",[e("li",[this._v("1 已有贡融商城入驻增值商家;")]),this._v(" "),e("li",[this._v("2 缴纳全额平台代理费用（2万元）;")]),this._v(" "),e("li",[this._v("3 确定服务商申请协议，并上传公司及法人代表相关资料和资格证件;")]),this._v(" "),e("li",[this._v("4 开始拓展市场，发展商家入驻贡融商城;")])])}]};var v=a("VU/8")(l,o,!1,function(t){a("q/3u")},"data-v-17b19fb1",null);e.default=v.exports},CxbG:function(t,e){},QrSs:function(t,e,a){"use strict";(function(t){var s=a("Au9i");a.n(s);e.a={data:function(){return{imageUrl:"",imageUrl2:"",imageUrl3:"",imageUrl4:"",idcardfront:"",idcardback:"",idcardhold:"",License:"",shopname:"",bol:!1,type:1}},methods:{back:function(){if(1!=confirm("请确定是否返回，返回后图片将需要重新上传"))return!1;this.$router.push({name:"SellerCenter",params:{type:this.type}})},handleAvatarSuccess:function(t,e){this.imageUrl=URL.createObjectURL(e.raw),console.log(t[0]),this.idcardfront=t[0]},handleAvatarSuccess2:function(t,e){this.imageUrl2=URL.createObjectURL(e.raw),console.log(t),this.idcardback=t[0]},handleAvatarSuccess3:function(t,e){this.imageUrl3=URL.createObjectURL(e.raw),console.log(t),this.idcardhold=t[0]},handleAvatarSuccess4:function(t,e){this.imageUrl4=URL.createObjectURL(e.raw),console.log(t),this.License=t[0]},beforeAvatarUpload:function(t){var e="image/jpeg"===t.type,a=t.size/1024/1024<2;return e||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),e&&a},beforeAvatarUpload2:function(t){var e="image/jpeg"===t.type,a=t.size/1024/1024<2;return e||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),e&&a},beforeAvatarUpload3:function(t){var e="image/jpeg"===t.type,a=t.size/1024/1024<2;return e||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),e&&a},beforeAvatarUpload4:function(t){var e="image/jpeg"===t.type,a=t.size/1024/1024<2;return e||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),e&&a},sub:function(){var e=t(".front").text(),a=t(".back").text(),s=t(".hold").text(),i=t(".license").text(),r=t(".shpname").val();if(""==e||""==a||""==s||""==i||""==r)alert("请上传照片和店铺名称");else{this.bol=!0;var n={imgIdcardFront:e,imgIdcardBack:a,imgIdcardHold:s,imgLicense:i,name:r};console.log(n),this.$axios.post("user/api/mercuries/uploadMercuryImages",n).then(function(t){console.log(t),this.bol=!0}).catch(function(t){console.log(t)})}}},mounted:function(){}}}).call(e,a("7t+N"))},QsWh:function(t,e){},gqfv:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("QrSs"),i={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"OrdinaryBusiness"},[a("header",{staticClass:"mint-header"},[a("div",{staticClass:"mint-header-button is-left"},[a("a",{staticClass:"router-link-active"},[a("mt-button",{attrs:{icon:"back"},on:{click:t.back}})],1)]),t._v(" "),a("h1",{staticClass:"mint-header-title"},[t._v("申请商家")]),t._v(" "),a("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),a("div",{staticClass:"content"},[a("p",[t._v("信息仅用于审核,绝不外泄!")]),t._v(" "),a("div",{staticClass:"main"},[a("h2",[t._v("本人身份证照片")]),t._v(" "),a("div",{staticClass:"self"},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":t.handleAvatarSuccess,"before-upload":t.beforeAvatarUpload}},[t.imageUrl?a("img",{staticClass:"avatar",attrs:{src:t.imageUrl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),t._v(" "),a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":t.handleAvatarSuccess2,"before-upload":t.beforeAvatarUpload2}},[t.imageUrl2?a("img",{staticClass:"avatar",attrs:{src:t.imageUrl2}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)]),t._v(" "),a("div",{staticClass:"main"},[a("h2",[t._v("本人手持身份证照片")]),t._v(" "),a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":t.handleAvatarSuccess3,"before-upload":t.beforeAvatarUpload3}},[t.imageUrl3?a("img",{staticClass:"avatar",attrs:{src:t.imageUrl3}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),t._v(" "),a("p",{staticClass:"photo",staticStyle:{display:"none"}},[a("span",{staticClass:"front"},[t._v(t._s(t.idcardfront))]),t._v(" "),a("span",{staticClass:"back"},[t._v(t._s(t.idcardback))]),t._v(" "),a("span",{staticClass:"hold"},[t._v(t._s(t.idcardhold))]),t._v(" "),a("span",{staticClass:"license"},[t._v(t._s(t.License))]),t._v(" "),a("span",{staticClass:"shopname"},[t._v(t._s(t.shopname))])])],1),t._v(" "),a("div",{staticClass:"main"},[a("h2",[t._v("企业营业执照照片")]),t._v(" "),a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":t.handleAvatarSuccess4,"before-upload":t.beforeAvatarUpload4}},[t.imageUrl4?a("img",{staticClass:"avatar",attrs:{src:t.imageUrl4}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),t._v(" "),a("div",{staticClass:"store"},[a("h2",[t._v("店铺名称")]),t._v(" "),a("input",{staticClass:"shpname",attrs:{type:"text",placeholder:"请输入店铺的名称"}}),t._v(" "),a("button",{on:{click:t.sub}},[t._v("提交申请")])]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.bol,expression:"bol"}],staticClass:"msg"},[a("div",{staticClass:"succeed"},[a("h3",[t._v("提示")]),t._v(" "),a("p",[t._v("上传成功,请等待审核")]),t._v(" "),a("router-link",{attrs:{to:"/Mine"}},[t._v("确定")])],1)])])])},staticRenderFns:[]};var r=function(t){a("CxbG")},n=a("VU/8")(s.a,i,!1,r,"data-v-3f4497a6",null);e.default=n.exports},"q/3u":function(t,e){}});
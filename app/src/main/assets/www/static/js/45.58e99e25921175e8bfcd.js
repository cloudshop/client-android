webpackJsonp([45],{"B/AQ":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=s("mvHQ"),n=s.n(o),i={data:function(){return{count:"",price:"",LeaveWord:"",nick:"",productSkuId:"",shopID:"",money:"",productName:"",productUrl:"",noAddress:"",Address:""}},methods:{gotoSetAddress:function(){this.$router.push({name:"AddAddress"})},getDefaultAddress:function(){var t=this;this.$axios.get("user/api/delivery-addresses-list").then(function(e){t.noAddress=e.data.length,t.Address=e.data.filter(function(t){return!1===t.defaultAddress})}).catch(function(t){console.log(t)})},CountAdd:function(){this.count<10&&this.count++},Reduce:function(){this.count>1&&this.count--},PreviousMenu:function(){var t={func:"closeCurrent",param:{}},e=navigator.userAgent,s=e.indexOf("Android")>-1||e.indexOf("Adr")>-1;!!e.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)?window.webkit.messageHandlers.GongrongAppModel.postMessage(t):s&&window.androidObject.JSCallAndroid(n()(t))},HarvestAddress:function(){this.$router.push({name:"addressYes"})},Pay:function(){this.nick=this.Address.contact,""==this.nick?alert("请输入收货地址"):this.$router.push({name:"Pay",params:{payment:this.price*this.count,postFee:0,buyerMessage:this.LeaveWord,buyerNick:this.nick,productSkuId:this.productSkuId,count:this.count,price:this.price,shopId:this.shopID}})}},created:function(){this.productName=localStorage.getItem("productName"),this.productUrl=localStorage.getItem("productUrl"),this.price=localStorage.getItem("price"),this.count=localStorage.getItem("count"),this.productSkuId=localStorage.getItem("Productskuid");this.$axios.get("wallet/api/wallets/user").then(function(t){console.log(t),sessionStorage.setItem("money",t.data.balance)}).catch(function(t){console.log(t)}),this.getDefaultAddress()},mounted:function(){this.GoodsID=sessionStorage.getItem("GoodsID"),this.shopID=localStorage.getItem("shopID")}},a={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"ConfirmAnOrder"},[s("header",{staticClass:"mint-header"},[s("div",{staticClass:"mint-header-button is-left"},[s("a",{staticClass:"router-link-active"},[s("button",{staticClass:"mint-button mint-button--default mint-button--normal",on:{click:t.PreviousMenu}},[s("mt-button",{attrs:{icon:"back"}})],1)])]),t._v(" "),s("h1",{staticClass:"mint-header-title"},[t._v("确认订单")]),t._v(" "),s("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),s("div",{staticClass:"content"},[s("div",{directives:[{name:"show",rawName:"v-show",value:0!==t.noAddress,expression:"noAddress !== 0"}],staticClass:"nav",on:{click:t.HarvestAddress}},[t._l(t.Address,function(e,o){return s("dl",{directives:[{name:"show",rawName:"v-show",value:""!==t.Address,expression:"Address !== ''"}]},[s("dt",[s("b",{attrs:{id:"nick"}},[t._v(t._s(e.contact))]),t._v(" "),s("b",[t._v(t._s(e.phone))])]),t._v(" "),s("dd",[s("p",[t._v(t._s(e.city))])])])}),t._v(" "),s("dl",{directives:[{name:"show",rawName:"v-show",value:""===t.Address,expression:"Address === ''"}]},[s("dt",{staticClass:"noAddress"},[t._v("亲，您还没有设置收货地址哦！")])]),t._v(" "),s("span",[t._v(">")])],2),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:0===t.noAddress,expression:"noAddress === 0"}],staticClass:"nav",on:{click:t.gotoSetAddress}},[t._m(0),t._v(" "),s("span",[t._v("马上去设置")]),t._v(" "),s("span",[t._v(">")])]),t._v(" "),s("div",{staticClass:"More"},[s("div",{staticClass:"Single"},[s("dl",[s("dt",[s("img",{attrs:{src:t.productUrl,alt:""}})]),t._v(" "),s("dd",[s("p",[t._v(t._s(t.productName))]),t._v(" "),s("div",{staticClass:"price"},[s("b",[t._v("￥"+t._s(t.price))]),s("em",[t._v("X"+t._s(t.count))])])])])]),t._v(" "),s("ul",[s("li",[s("p",[t._v("购买数量")]),t._v(" "),s("h2",[s("span",{on:{click:t.Reduce}},[t._v("-")]),s("input",{directives:[{name:"model",rawName:"v-model",value:t.count,expression:"count"}],attrs:{type:"count"},domProps:{value:t.count},on:{input:function(e){e.target.composing||(t.count=e.target.value)}}}),s("span",{on:{click:t.CountAdd}},[t._v("+")])])])])]),t._v(" "),s("ul",{staticClass:"Manner"},[t._m(1),t._v(" "),s("li",[s("p",[t._v("卖家留言:")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.LeaveWord,expression:"LeaveWord"}],attrs:{type:"count",placeholder:"选填:填写内容已和商家协商确认"},domProps:{value:t.LeaveWord},on:{input:function(e){e.target.composing||(t.LeaveWord=e.target.value)}}})])])]),t._v(" "),s("div",{staticClass:"Compute"},[s("p",[s("span",[t._v("共"+t._s(t.count)+"件商品")]),s("b",[t._v("小计:"),s("em",[t._v("￥"+t._s(t.price*t.count))])])])]),t._v(" "),s("div",{staticClass:"footer"},[s("p",[s("span"),s("b",[t._v("合计:￥"+t._s(t.price*t.count))])]),t._v(" "),s("button",{on:{click:t.Pay}},[t._v("去结算")])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("dl",[e("dt",{staticClass:"noAddress"},[this._v("亲，您还没有收货地址哦！")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("p",[this._v("配送方式")]),this._v(" "),e("h2",[this._v("快递免邮 >")])])}]};var r=s("VU/8")(i,a,!1,function(t){s("NNio")},"data-v-4b0b11f3",null);e.default=r.exports},NNio:function(t,e){}});
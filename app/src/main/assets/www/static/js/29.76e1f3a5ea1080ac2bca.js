webpackJsonp([29],{"B/AQ":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=s("BI/u"),o={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"ConfirmAnOrder"},[s("header",{staticClass:"mint-header"},[s("div",{staticClass:"mint-header-button is-left"},[s("a",{staticClass:"router-link-active"},[s("button",{staticClass:"mint-button mint-button--default mint-button--normal",on:{click:t.PreviousMenu}},[s("mt-button",{attrs:{icon:"back"}})],1)])]),t._v(" "),s("h1",{staticClass:"mint-header-title"},[t._v("确认订单")]),t._v(" "),s("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),s("div",{staticClass:"content"},[s("div",{staticClass:"nav",on:{click:t.HarvestAddress}},[s("dl",[s("dt",[s("b",{attrs:{id:"nick"}},[t._v(t._s(this.$route.params.contact))]),t._v(" "),s("b",[t._v(t._s(this.$route.params.phone))])]),t._v(" "),s("dd",[s("p",[t._v(t._s(this.$route.params.city))])])]),t._v(" "),s("span",[t._v(">")])]),t._v(" "),s("div",{staticClass:"More"},[s("div",{staticClass:"Single"},[s("dl",[s("dt",[s("img",{attrs:{src:t.productUrl,alt:""}})]),t._v(" "),s("dd",[s("p",[t._v(t._s(t.productName))]),t._v(" "),s("div",{staticClass:"price"},[s("b",[t._v("￥"+t._s(t.price))]),s("em",[t._v("X"+t._s(t.count))])])])])]),t._v(" "),s("ul",[s("li",[s("p",[t._v("购买数量")]),t._v(" "),s("h2",[s("span",{on:{click:t.Reduce}},[t._v("-")]),s("input",{directives:[{name:"model",rawName:"v-model",value:t.count,expression:"count"}],attrs:{type:"count"},domProps:{value:t.count},on:{input:function(e){e.target.composing||(t.count=e.target.value)}}}),s("span",{on:{click:t.CountAdd}},[t._v("+")])])])])]),t._v(" "),s("ul",{staticClass:"Manner"},[t._m(0),t._v(" "),s("li",[s("p",[t._v("卖家留言:")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.LeaveWord,expression:"LeaveWord"}],attrs:{type:"count",placeholder:"选填:填写内容已和商家协商确认"},domProps:{value:t.LeaveWord},on:{input:function(e){e.target.composing||(t.LeaveWord=e.target.value)}}})])])]),t._v(" "),s("div",{staticClass:"Compute"},[s("p",[s("span",[t._v("共"+t._s(t.count)+"件商品")]),s("b",[t._v("小计:"),s("em",[t._v("￥"+t._s(t.price*t.count))])])])]),t._v(" "),s("div",{staticClass:"footer"},[s("p",[s("span"),s("b",[t._v("合计:￥"+t._s(t.price*t.count))])]),t._v(" "),s("button",{on:{click:t.Pay}},[t._v("去结算")])])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("p",[this._v("配送方式")]),this._v(" "),e("h2",[this._v("快递免邮 >")])])}]};var n=function(t){s("jGXI")},a=s("VU/8")(i.a,o,!1,n,"data-v-2a0059b4",null);e.default=a.exports},"BI/u":function(t,e,s){"use strict";(function(t){e.a={data:function(){return{count:"",price:"",LeaveWord:"",nick:"",productSkuId:"",shopID:"",money:"",productName:"",productUrl:""}},methods:{CountAdd:function(){this.count<10&&this.count++},Reduce:function(){this.count>1&&this.count--},PreviousMenu:function(){this.$router.push({name:"Product"})},HarvestAddress:function(){this.$router.push({name:"addressYes",params:{address:"/ConfirmAnOrder"}})},Pay:function(){""==this.nick?alert("请输入收货地址"):this.$router.push({name:"Pay",params:{payment:this.price*this.count,postFee:0,buyerMessage:this.LeaveWord,buyerNick:this.nick,productSkuId:this.productSkuId,count:this.count,price:this.price,shopId:this.shopID}})}},created:function(){this.productName=sessionStorage.getItem("productName"),this.productUrl=sessionStorage.getItem("productUrl"),this.price=sessionStorage.getItem("price"),this.count=sessionStorage.getItem("count"),this.productSkuId=sessionStorage.getItem("Productskuid");this.$axios.get("wallet/api/wallets/user").then(function(t){console.log(t),sessionStorage.setItem("money",t.data.balance)}).catch(function(t){console.log(t)})},mounted:function(){this.nick=t("#nick").text(),this.GoodsID=sessionStorage.getItem("GoodsID"),this.shopID=sessionStorage.getItem("shopID")}}}).call(e,s("7t+N"))},jGXI:function(t,e){}});
//# sourceMappingURL=29.76e1f3a5ea1080ac2bca.js.map
webpackJsonp([58],{"2emT":function(s,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=e("4ccu"),n=e("Au9i"),r={data:function(){return{arr:"",msg:"test message",EditAddress:!1,init:null,name:"",type:"",Addressid:"",bol:""}},created:function(){this.getAddressList()},methods:{getAddressList:function(){var s=this;this.$axios.get("user/api/delivery-addresses-list").then(function(t){s.arr=[];for(var e=t.data.length,a=0;a<e;a++){s.arr.push(t.data[a]);for(var n=0;n<s.arr.length;n++){if(0==s.arr[a].default_address){s.arr[a].checked=!0;break}s.arr[a].checked=!1}}}).catch(function(s){console.log(s)})},closeAddress:function(){this.$parent.$parent.address=!1},EditAddressOpen:function(){this.EditAddress=!0},changDefaultAdd:function(s){var t=this;this.$axios.get("user/api/user-annexes-updateAddress/"+s.id).then(function(s){Object(n.Toast)("修改默认地址成功"),setTimeout(function(){return t.$router.push({name:"ConfirmAnOrder"})},1500)}).catch(function(s){console.log(s)})},PreviousMenu:function(){this.$router.push({name:"ConfirmAnOrder"})}},components:{AddAddress:a.default}},i={render:function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("div",{staticClass:"addressYes"},[e("div",{staticClass:"header"},[e("ul",[e("li",{on:{click:s.PreviousMenu}},[s._v("<")]),s._v(" "),e("li",[s._v("修改收货地址")]),s._v(" "),e("li",[s._v(" ")])])]),s._v(" "),e("div",{staticClass:"main"},s._l(s.arr,function(t,a){return e("ul",{key:a,staticClass:"list"},[e("li",[e("span",{staticClass:"name"},[e("em",[s._v(s._s(t.contact))])]),s._v(" "),e("span",{staticClass:"tel"},[e("rp",[s._v(s._s(t.phone))])])]),s._v(" "),e("li",{staticClass:"address"},[s._v("\n        "+s._s(t.city)+"\n      ")]),s._v(" "),e("li",[e("span",{staticClass:"addlist"},[e("input",{attrs:{type:"radio",id:"adress-0"+t.id,name:"sex"},domProps:{checked:t.checked}}),s._v(" "),e("label",{attrs:{for:"adress-0"+t.id},on:{click:function(e){s.changDefaultAdd(t)}}}),s._v("默认地址\n          ")])])])})),s._v(" "),e("div",{staticClass:"bottom"},[e("router-link",{attrs:{to:{name:"AddAddress",params:{bol:!0}},tag:"button"}},[s._v("＋新建地址")])],1)])},staticRenderFns:[]};var d=e("VU/8")(r,i,!1,function(s){e("sFZU")},"data-v-089f8b08",null);t.default=d.exports},sFZU:function(s,t){}});
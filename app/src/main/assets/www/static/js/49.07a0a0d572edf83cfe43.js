webpackJsonp([49],{LTHK:function(t,r){},WRlm:function(t,r,a){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var s={data:function(){return{arr:"null",iphone:"",arr2:""}},created:function(){console.log(this.$route.params),this.arr2=this.$route.params.arr,1==this.arr2.type?this.arr2.typeString="普通会员":2==this.arr2.type?this.arr2.typeString="增值会员":3==this.arr2.type?this.arr2.typeString="普通商家":4==this.arr2.type?this.arr2.typeString="增值商家":5==this.arr2.type&&(this.arr2.typeString="服务商");var t=this;this.$axios.get("user/api/user-annexes-shareUserList").then(function(r){t.arr=[];r.data.length;t.arr=r.data,console.log(t.arr);for(var a=0;a<t.arr.length;a++)1==t.arr[a].type?t.arr[a].typeStrings="普通会员":2==t.arr[a].type?t.arr[a].typeStrings="增值会员":3==t.arr[a].type?t.arr[a].typeStrings="普通商家":4==t.arr[a].type?t.arr[a].typeStrings="增值商家":5==t.arr[a].type&&(t.arr[a].typeStrings="服务商")}).catch(function(t){console.log(t)}),!0!==this.$store.getters.isAuthed?this.flag=!0:this.flag=!1},methods:{}},e={render:function(){var t=this,r=t.$createElement,a=t._self._c||r;return a("div",{staticClass:"Team"},[a("header",{staticClass:"mint-header"},[a("div",{staticClass:"mint-header-button is-left"},[a("a",{staticClass:"router-link-active"},[a("router-link",{staticClass:"mint-button mint-button--default mint-button--normal",attrs:{to:{path:"/Mine"},tag:"button"}},[a("mt-button",{attrs:{icon:"back"}})],1)],1)]),t._v(" "),a("h1",{staticClass:"mint-header-title"},[t._v("我的团队")]),t._v(" "),a("div",{staticClass:"mint-header-button is-right"})]),t._v(" "),a("div",{staticClass:"nav"},[a("div",{staticClass:"list"},[a("dl",[a("dt"),t._v(" "),a("dd",[a("img",{attrs:{src:t.arr2.avatar,alt:""}})]),t._v(" "),a("dd",[a("h3",[t._v(t._s(t.arr2.nickname))]),t._v(" "),a("p",[t._v(t._s(t.arr2.typeString))])]),t._v(" "),a("dd",[a("p",[t._v(t._s(t.arr2.phone))])])])])]),t._v(" "),a("div",{staticClass:"mainList content"},[a("h2",[t._v("团队成员")]),t._v(" "),t._l(t.arr,function(r){return a("div",{staticClass:"AppendList"},[a("dl",[a("dd",[a("img",{attrs:{src:r.avatar,alt:""}})]),t._v(" "),a("dd",[a("h3",[t._v(t._s(r.nickname))]),t._v(" "),a("p",[t._v(t._s(r.typeStrings))])]),t._v(" "),a("dd",[a("p",[t._v(t._s(r.phone))])])])])})],2)])},staticRenderFns:[]};var i=a("VU/8")(s,e,!1,function(t){a("LTHK")},"data-v-495daae7",null);r.default=i.exports}});
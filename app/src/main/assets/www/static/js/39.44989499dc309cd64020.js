webpackJsonp([39],{kYMg:function(t,s){},qoqp:function(t,s,a){"use strict";Object.defineProperty(s,"__esModule",{value:!0});a("Au9i");var i={data:function(){return{imageUrl5:"",bol:!1,headImg:"",id:"",bo:!1}},created:function(){this.id=this.$route.params.id},methods:{backMine:function(){this.$router.push({path:"/information"})},back:function(){if(1!=confirm("请确定是否返回，返回后图片将需要重新上传"))return!1;this.$router.push({path:"/information"})},handleAvatarSuccess5:function(t,s){this.imageUrl5=URL.createObjectURL(s.raw),this.idcardfront5=t[0]},beforeAvatarUpload5:function(t){var s="image/jpeg"===t.type,a=t.size/1024/1024<2;return s||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),s&&a},sub:function(){if(void 0==this.idcardfront5)this.bo=!0;else{this.bol=!0;var t={id:this.id,avatar:this.idcardfront5,type:0};console.log(t),this.$axios.post("user/api/user-annexes-useregis/updaUserInfo",t).then(function(t){console.log(t)}).catch(function(t){console.log(t)})}}}},e={render:function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"OrdinaryBusiness"},[a("header",{staticClass:"mint-header"},[a("div",{staticClass:"mint-header-button is-left"},[a("a",{staticClass:"router-link-active"},[a("mt-button",{attrs:{icon:"back"},on:{click:t.back}})],1)]),t._v(" "),a("h1",{staticClass:"mint-header-title"},[t._v("修改头像")]),t._v(" "),a("div",{staticClass:"mint-header-button is-right",on:{click:t.sub}},[t._v("确认")])]),t._v(" "),a("div",{staticClass:"content"},[a("div",{staticClass:"main"},[a("div",{staticClass:"self"},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":t.handleAvatarSuccess5,"before-upload":t.beforeAvatarUpload5}},[t.imageUrl5?a("img",{staticClass:"avatar",attrs:{src:t.imageUrl5}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)])]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.bo,expression:"bo"}],staticClass:"msg"},[a("div",{staticClass:"succeed"},[a("h3",[t._v("提示")]),t._v(" "),a("p",[t._v("请上传头像")]),t._v(" "),a("span",{on:{click:function(s){t.bo=!1}}},[t._v("确定")])])]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.bol,expression:"bol"}],staticClass:"msg"},[a("div",{staticClass:"succeed"},[a("h3",[t._v("提示")]),t._v(" "),a("p",[t._v("修改成功")]),t._v(" "),a("span",{on:{click:t.backMine}},[t._v("确定")])])])])},staticRenderFns:[]};var o=a("VU/8")(i,e,!1,function(t){a("kYMg")},"data-v-889f93ce",null);s.default=o.exports}});
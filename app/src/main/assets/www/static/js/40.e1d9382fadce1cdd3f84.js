webpackJsonp([40],{P1WT:function(t,a){},qoqp:function(t,a,s){"use strict";Object.defineProperty(a,"__esModule",{value:!0});s("Au9i");var e={data:function(){return{imageUrl5:"",bol:!1,headImg:"",id:""}},created:function(){this.id=this.$route.params.id},methods:{backMine:function(){this.$router.push({path:"/information"})},back:function(){if(1!=confirm("请确定是否返回，返回后图片将需要重新上传"))return!1;this.$router.push({path:"/information"})},handleAvatarSuccess5:function(t,a){this.imageUrl5=URL.createObjectURL(a.raw),this.idcardfront5=t[0]},beforeAvatarUpload5:function(t){var a="image/jpeg"===t.type,s=t.size/1024/1024<2;return a||this.$message.error("上传头像图片只能是 JPG 格式!"),s||this.$message.error("上传头像图片大小不能超过 2MB!"),a&&s},sub:function(){this.bol=!0,this.bol=!0;var t={id:this.id,avatar:this.idcardfront5,type:0};console.log(t),this.$axios.post("user/api/user-annexes-useregis/updaUserInfo",t).then(function(t){console.log(t)}).catch(function(t){console.log(t)})}}},i={render:function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"OrdinaryBusiness"},[s("header",{staticClass:"mint-header"},[s("div",{staticClass:"mint-header-button is-left"},[s("a",{staticClass:"router-link-active"},[s("mt-button",{attrs:{icon:"back"},on:{click:t.back}})],1)]),t._v(" "),s("h1",{staticClass:"mint-header-title"},[t._v("修改头像")]),t._v(" "),s("div",{staticClass:"mint-header-button is-right",on:{click:t.sub}},[t._v("确认")])]),t._v(" "),s("div",{staticClass:"content"},[s("div",{staticClass:"main"},[s("div",{staticClass:"self"},[s("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":t.handleAvatarSuccess5,"before-upload":t.beforeAvatarUpload5}},[t.imageUrl5?s("img",{staticClass:"avatar",attrs:{src:t.imageUrl5}}):s("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)])]),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:t.bol,expression:"bol"}],staticClass:"msg"},[s("div",{staticClass:"succeed"},[s("h3",[t._v("提示")]),t._v(" "),s("p",[t._v("修改成功")]),t._v(" "),s("span",{on:{click:t.backMine}},[t._v("确定")])])])])},staticRenderFns:[]};var r=s("VU/8")(e,i,!1,function(t){s("P1WT")},"data-v-e9b1bb66",null);a.default=r.exports}});
//# sourceMappingURL=40.e1d9382fadce1cdd3f84.js.map
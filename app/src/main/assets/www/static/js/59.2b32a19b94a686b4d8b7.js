webpackJsonp([59],{"+/+9":function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var s={data:function(){return{realName:"",idnuber:"",frontImg:"",reverseImg:"",imageUrl:"",imageUrl2:"",bol:!1,b:""}},created:function(){console.log(this.$route.params.b),this.b=this.$route.params.b},methods:{handleAvatarSuccess:function(e,a){this.imageUrl=URL.createObjectURL(a.raw),console.log(e),this.idcardfront=e[0],console.log(this.idcardfront)},handleAvatarSuccess2:function(e,a){this.imageUrl2=URL.createObjectURL(a.raw),console.log(this.imageUrl2),console.log(e),this.idcardfront2=e[0],console.log(this.idcardfront2)},beforeAvatarUpload:function(e){var a="image/jpeg"===e.type,t=e.size/1024/1024<2;return a||this.$message.error("上传头像图片只能是 JPG 格式!"),t||this.$message.error("上传头像图片大小不能超过 2MB!"),a&&t},beforeAvatarUpload2:function(e){var a="image/jpeg"===e.type,t=e.size/1024/1024<2;return a||this.$message.error("上传头像图片只能是 JPG 格式!"),t||this.$message.error("上传头像图片大小不能超过 2MB!"),a&&t},submit:function(){if(""==this.realName||""==this.realNumber||""==this.imageUrl||""==this.imageUrl2)alert("填写不完善");else{this.bol=!0;var e={realName:this.realName,idnuber:this.idnuber,frontImg:this.idcardfront,reverseImg:this.idcardfront2};console.log(e),this.$axios.post("user/api/authentication",e).then(function(e){console.log(e)}).catch(function(e){console.log(e)})}},reset:function(){if(""==this.realName||""==this.realNumber||""==this.imageUrl||""==this.imageUrl2)alert("填写不完善");else{this.bol=!0;var e={realName:this.realName,idnuber:this.idnuber,frontImg:this.idcardfront,reverseImg:this.idcardfront2};console.log(e),this.$axios.put("user/api/my-auth/update",e).then(function(e){console.log(e)}).catch(function(e){console.log(e)})}},backMine:function(){this.$router.push({path:"/information"})}}},r={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"Approve"},[t("header",[t("span",{on:{click:e.backMine}},[e._v("<")]),e._v("实名认证")]),e._v(" "),t("div",{staticClass:"main"},[t("p",[t("label",{attrs:{for:""}},[e._v("姓名：")]),e._v(" "),t("input",{directives:[{name:"model",rawName:"v-model",value:e.realName,expression:"realName"}],attrs:{type:"text",id:"name"},domProps:{value:e.realName},on:{input:function(a){a.target.composing||(e.realName=a.target.value)}}})]),e._v(" "),t("p",[t("label",{attrs:{for:""}},[e._v("身份证号：")]),e._v(" "),t("input",{directives:[{name:"model",rawName:"v-model",value:e.idnuber,expression:"idnuber"}],attrs:{type:"text",id:"number"},domProps:{value:e.idnuber},on:{input:function(a){a.target.composing||(e.idnuber=a.target.value)}}})]),e._v(" "),t("p",[t("span",[e._v("身份证正面照片:")]),e._v(" "),t("span",{staticClass:"Pic"},[t("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.imageUrl?t("img",{staticClass:"avatar",attrs:{src:e.imageUrl}}):t("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)]),e._v(" "),t("p",[t("span",[e._v("身份证反面照片:")]),e._v(" "),t("span",{staticClass:"Pic"},[t("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://app.grjf365.com:9080/file/api/ossUpload","show-file-list":!1,"on-success":e.handleAvatarSuccess2,"before-upload":e.beforeAvatarUpload2}},[e.imageUrl2?t("img",{staticClass:"avatar",attrs:{src:e.imageUrl2}}):t("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)]),e._v(" "),t("p"),e._v(" "),t("p",[t("button",{directives:[{name:"show",rawName:"v-show",value:0==e.b,expression:"b==0?true:false"}],on:{click:e.submit}},[e._v("提交")]),e._v(" "),t("button",{directives:[{name:"show",rawName:"v-show",value:1==e.b,expression:"b==1?true:false"}],on:{click:e.reset}},[e._v("提交")])])]),e._v(" "),t("div",{directives:[{name:"show",rawName:"v-show",value:e.bol,expression:"bol"}],staticClass:"msg"},[t("div",{staticClass:"succeed"},[t("h3",[e._v("提示")]),e._v(" "),t("p",[e._v("提交审核中")]),e._v(" "),t("span",{on:{click:e.backMine}},[e._v("确定")])])])])},staticRenderFns:[]};var i=t("VU/8")(s,r,!1,function(e){t("dVtl")},"data-v-07f98ef3",null);a.default=i.exports},dVtl:function(e,a){}});
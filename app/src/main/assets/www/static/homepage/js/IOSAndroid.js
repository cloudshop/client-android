
 IOSAndroid=function(val){
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; // android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); // ios终端
    if(isiOS){
       window.webkit.messageHandlers.GongrongAppModel.postMessage(val);
    }else if(isAndroid){  
       window.androidObject.JSCallAndroid(JSON.stringify(val));
    }
}
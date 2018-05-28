// http: //cloud.eyun.online:9080/verify/api/verify/smscode?phone=1
$(document).ready(function(){
    console.log(localStorage.getItem('token'))
});
$('.get').click(function() {
    var tel = $(".tel").val();
    var p1 = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
    if (p1.test(tel) == false) {
        alert("请输入正确的手机号")
        $(".tel").val() = "";
    } else {
        var countdown = 60;
        settime('.gets');
        function settime(obj) {
            if (countdown == 0) {
                $(obj).attr("disabled", false);
                $(obj).text("获取验证码");
                countdown = 60;
                return;
            } else {
                $(obj).attr("disabled", true);
                $(obj).text(countdown + " s 重新发送");
                countdown--;
            }
            setTimeout(function() {
                settime(obj)
            }, 1000)
        }
        $.ajax({
            url: 'verify/api/verify/smscode?phone=' + tel,
            method: 'get',
            callback: 'cb',
            success: function(res) {

            },
            error(res) {
                alert(res.responseJSON.title)
            }
        })
    }
})

$('.sure').click(function() {
    var tel = $(".tel").val();
    var yzm = $(".yzm").val();
    var psd = $('.psd').val();
    var urltel =window.location.search;
    var tel2 = urltel.substring(13,24);
    var p1 = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;
    var datas = { 'login': tel, 'password': psd, 'verifyCode': yzm, 'recommend':tel2}
    $.ajax({
        url: 'http://app.grjf365.com:9080/uaa/api/register/app',
        type: 'post',
        header: 'Access-Control-Allow-Origin: *',
        //callback: 'cb',
        data: JSON.stringify(datas),
        contentType: "application/json",
        success: function(res) {
            console.log(res)
            alert('注册成功,更多精彩请下载app体验')
        },
        error(error) {
            if (error.status === 500) {
                alert('服务器繁忙，请耐心等待')
            }
        }
    })
})
$('.test').click(function() {
    // var test = window.location.pathname;
    // alert(test);
    $(document).ready(function() {
        var url = window.location.pathname; //你的地址
        if (url.indexOf("?") != -1) {
            var str = url.substr(1)
            strs = str.split("&");
            for (i = 0; i < strs.length; i++) {
                alert(strs.length);
                alert(strs[i].split("=")[0]);
                alert(strs[i].split("=")[1]);
                alert(strs[i].split("=")[0], '＝', strs[i].split("=")[1], '');
            }
        }
    })
})

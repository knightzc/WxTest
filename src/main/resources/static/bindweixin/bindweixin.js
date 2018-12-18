
$(function(){
    getBindStatus()
});


var getBindStatus = function () {
    $.ajax({
        url: bindweixinUrl + "/getBindStatus",
        type: 'post',
        dataType: 'json',
        data: {
            openId: openId,
        },
        success: function (data) {
            // alert(JSON.stringify(data))
            if(data.status == 1){
                window.location.href = unBindUrl + "?openId=" + openId;
            }
        }
    });
}

var bindAccount = function () {
    var userAccount = $("#account").val();
    var password = $("#password").val()
    if(userAccount === '' || password === ''){
        $.alert("请输入账号密码");
    }else {
        $.ajax({
            url: bindweixinUrl + "/bindAccount",
            type: 'post',
            dataType: 'json',
            data: {
                openId: openId,
                userAccount:userAccount,
                password:password,
            },
            success: function (data) {
                if(data.status == 1){
                    $.alert("绑定成功",function () {
                        window.location.href = unBindUrl + "?openId=" + openId;
                    });
                }else {
                    $.alert("账号或密码有误");
                }
            }
        });
    }

}

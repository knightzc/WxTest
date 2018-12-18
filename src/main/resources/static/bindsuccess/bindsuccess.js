var unBind = function () {
    $.confirm("确定解除绑定", function() {
        $.ajax({
            url: unBindUrl + "/unBindAccount",
            type: 'post',
            dataType: 'json',
            data: {
                openId: openId,
            },
            success: function (data) {
                if(data.status == 1){
                    $.alert("解绑成功",function () {
                        window.location.href = bindweixinUrl + "?openId=" + openId;
                    });
                }else {
                    window.location.href = bindweixinUrl + "?openId=" + openId;
                }
            }
        });
    });

}
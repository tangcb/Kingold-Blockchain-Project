$(function () {
    var M = {};
    var wait=60;

    document.getElementById("codebtn").onclick=function(){time(this);}

    $("#loginbtn").click(function () {
        var phonenumber = $("#phonenumber").val();
        var authcode = $("#authcode").val();
        var re = /^(13[0-9]|15[0|3|6|7|8|9]|17[0-9]|18[1|8|9|5|6|0])\d{8}$/;
        if ($.trim(phonenumber) == "") {
            if(M.dialog13){
                return M.dialog13.show();
            }
            M.dialog13 = jqueryAlert({
                'icon'    : '../images/alertimgs/warning.png',
                'content' : '请输入手机号码',
                'closeTime' : 2000,
            })
            $("#phonenumber").focus();
            return false;
        }
        if(!re.test(phonenumber)){
            M.dialog13 = jqueryAlert({
                'icon'    : '../images/alertimgs/warning.png',
                'content' : '请输入正确的手机号码',
                'closeTime' : 2000,
            })
            M.dialog13.show();
            $("#phonenumber").val("");
            $("#phonenumber").focus();
            return false;
        }
        if ($.trim(authcode) == "") {
            M.dialog13 = jqueryAlert({
                'icon'    : '../images/alertimgs/warning.png',
                'content' : '请输入验证码',
                'closeTime' : 2000,
            })
            M.dialog13.show();
            $("#authcode").focus();
            return false;
        }
        // 验证码是否正确判断
        $.ajax({
            url: "localhost",
            data: { Access: "Login", Username: username, Password: password },
            success: function (data) {
                var arr = eval("(" + data + ")");
                if (arr.flag) {
                    $("#ErrLogMessager").html(arr.messager);
                    //为APP端传送登录信息
                    var nm = arr.data.user.NickName == "" || arr.data.user.NickName == null ? arr.data.user.UserName : arr.data.user.NickName;
                    //判断该账号是不是移动办公的账号
                    AuthUserIsYdbg(username, arr.data.guid,nm);

                } else {
                    $("#ErrMessager").html(arr.messager);
                }
            }
        });
    });

    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.value="获取验证码";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.value="重新发送(" + wait + ")";
            wait--;
            setTimeout(function() {
                    time(o)
                },
                1000)
        }
    }
});
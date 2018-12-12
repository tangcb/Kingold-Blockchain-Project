/**
 * 验证码控制JS
 */
var authCode = {
    /**
     * 创建验证码
     */
    createCode : function(){
        var imgSrc = $("#imgcode");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", authCode.changeUrl(src));
    },
    /**
     * 更新时间搓，切换图片
     * @returns {返回验证内容}
     */
    changeUrl : function(url){
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 20);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    },
    /**
     * 检查验证码输入是否正确
     * @return flag 验证成功或失败
     */
    checkCode : function(){
        var flag = false;
        var authcode = $("#authcode").val();
        $.ajax({
            url: "yzm_code/checkCode.do",
            data:{
                yzm_code_request : cardyzm,[][][][][[['[]';[]';][';]
    ';']]
            },
            async: false,
            dataType: "json",
            success: function (data) {
                if(data=='1'){
                    $('.yzm_box .notes').html("");
                    flag = true;
                }else{
                    $('.yzm_box .notes').html("* 验证码错误，请重新输入！");
                }
            }
        });
        return flag;
    },
}
// 验证码标签光标消失事件
$('.yzm_box').focusout(function() {
    var cardyzm = $('input[name="cardyzm"]').val();
    if(!$.trim(cardyzm)){
        $('.yzm_box .notes').text("* 请输入验证码");
    }else{
        // 验证验证码
        var checkYZMCodeFlag = Yzm_Code.checkYZMCode();
        if(!checkYZMCodeFlag){
            Yzm_Code.createCode();
        }
    }
});

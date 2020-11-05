$(
    function () {
        var a = $("#username"), b = $("#password");
        $("#login").on("click", function (c) {
            var d, e;
            c.preventDefault()
            d = a.val().trim()
            e = b.val().trim();
            if ("" === d) {
                alert("用户名不能为空")
                return
            }
            if ("" === e) {
                alert("密码不能为空")
                return
            }
            $.ajax(ctx + "login", {
                method: "post",
                data: {
                    username: d,
                    password: e
                },
                success: function (a) {
                    window.location.href = a.data
                },
                error: function (a) {
                    alert(a.responseJSON.message)
                }
            })
        })
    }
);

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return ("");
}

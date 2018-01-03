function login(){
   var username =  $("#login-username").val();
   var password = $("#login-password").val();
     $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/login?username="+username+"&password="+password+"",
                cache: false,
                timeout: 600000,
                success: function (data) {
                if(data=="missmatch"){
                    alert("Логин или пароль не совпадает, попробуйте еще раз!");
                }else if(data == "ok"){
                    window.location.replace("index.html");
                }
                },
                error: function (e) {
                     if(e.responseText=="missmatch"){
                    alert("Логин или пароль не совпадает, попробуйте еще раз!");
                }else if(e.responseText == "ok"){
                    window.location.replace("index");
                } else{
                    alert("Введите логин и пароль!");
                }
                }
            });
}
$(function () {
    $('#registration-form').validate({
        rules: {
            'firstName':{
                required: true
            },
            'lastName':{
                required: true
            },
            'password':{
                required: true,
                minlength :5
            },
            'confirmPassword':{
                required: true,
                confirm:true
            },
            'userName': {
                required: true,
                remote: {
                    url: "/login/validateUserName",
                    type: "post",
                    data: {
                        userName: function () {
                            return $('#userName').val()
                        }
                    }
                }
            },
            'email': {
                required: true,
                remote: {
                    url: "/login/validateEmail",
                    type: "post",
                    data: {
                        email: function () {
                            return $('#email').val()
                        }
                    }
                }
            }
        },
        messages: {
            'firstName':{
                required: "First name can't be blank",
            },
            'lastName':{
                required: "Last name can't be blank",
            },
            'password':{
                required: "Password can't be blank",
                minlength: "Password should be atleast 5 character long"
            },
            'confirmPassword':{
                required: "Confirm password can't be blank"
            },
            'email': {
                required: "Email address can't be blank",
                remote: "Email address entered is already used"
            },
            'userName': {
                required: "User name can't be blank",
                remote: "User name entered already exist"
            }
        }
    });

    jQuery.validator.addMethod("confirm", function (value, element) {
        var result = false;
        var password = $("#password").val();
        if (password === value) {
            result = true;
        }
        return result;
    }, "Confirm password not matched with password");
});
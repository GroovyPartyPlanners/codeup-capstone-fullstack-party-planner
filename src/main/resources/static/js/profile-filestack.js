"use strict"

window.addEventListener("DOMContentLoaded", function () {

    let apikey = document.querySelector('meta').content;
    const client = filestack.init(apikey);

    let options = {
        onUploadDone: (res) => {
            $("#profilePicUrl").val(res.filesUploaded[0].url);
            console.log(res.filesUploaded[0].url);
        },
        accept: ["image/*"],
        uploadConfig: {
            retry: 5,
            timeout: 60000
        },
        transformations: {
            crop: true,
            circle: true,
            rotate: true
        }
    }

    $("#upload").click(function () {
        client.picker(options).open();
    })

    $('#confirmPassword').focusout(function () {
        let password = $("#password-signup").val();
        let confirmPassword = $("#confirmPassword").val();
        if (password !== confirmPassword) {
            $("#confirmPasswordError").html("Passwords don't match.").css("color", "red");
        } else {
            $("#confirmPasswordError").html("");
        }
    });

    $("#signUpForm").keyup(function () {
        let password = $("#password-signup").val();
        let confirmPass = $("#confirmPassword").val();
        if (password === confirmPass) {
            $("#submit-btn").removeAttr("disabled");
        } else {
            $("#submit-btn").attr("disabled", "disabled");
        }
    })
});
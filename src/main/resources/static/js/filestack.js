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
});
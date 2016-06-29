$(document).ready(function(){

    $ajax.({
        url: "localhost:8080/user"
    }).then(function(data){
        $(".first-name").append(data.firstName);
    })
});
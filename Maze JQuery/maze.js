$(document).ready(function(){
    var status = "";
    var begin = 0;
    var end = 0;
    $(".boundary").hover(function(){
        if(begin == 1 && end ==0) {
            $(this).addClass("youlose");
            lose();
        }
    });

    $("#end").click(function(){
        if(status == "lose"){
            $("#status").text("You Lose!");
        }
        else{
            $("#status").text("You Win!");
        }
        begin = 0;
        end = 1;
    });

    $("#start").click(function(){
        $(".boundary").removeClass("youlose");
        $("#status").text("You have Begun... ");
        status = "begin";
        begin = 1;
        end = 0;
    });

    $("#maze").mouseleave(function() {
        if(status == "begin" && end == 0) {
            $(".boundary").addClass("youlose");
            lose();
        }
    });

    var lose = function(){
        $("#status").text("You Lose!");
        status = "lose";
        end =1;
    }

});
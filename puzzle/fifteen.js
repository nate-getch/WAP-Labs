$(document).ready(function(){

    var puzzleArea = $('#puzzlearea');
    var divs = $("#puzzlearea div");

    function getRandom(num){
        var my_num = Math.floor(Math.random()*num);
        return my_num;
    }
    // initialize each piece
    //for (var i=0; i< divs.length; i++) {

    var shuffle = function shuffle(){
        $.each( $("#puzzlearea div"), function( i, val ) {
            //var div = divs[i];

            // calculate x and y for this piece
            var x = ((i % 4) * 100) ;
            var y = (Math.floor(i / 4) * 100) ;

            // set basic style and background
            $(this).addClass("puzzlepiece");//div.className = "puzzlepiece";
            $(this).css({
                "left": x + 'px',
                "top": y + 'px',
                "backgroundImage": 'url("background.jpg")',
                "backgroundPosition": -x + 'px ' + (-y) + 'px'
            });
            //div.style.left = x + 'px';
            //div.style.top = y + 'px';
            // div.style.backgroundImage = 'url("background.jpg")';
            //div.style.backgroundPosition = -x + 'px ' + (-y) + 'px';

            // store x and y for later
           // $(this).x = x;
          //  $(this).y = y;
        });
        $('#puzzlearea').append("<div class='whitespot' style='left:300px;top:300px;'></div>");
        checkWin();
    }

    shuffle();

    $("#shufflebutton").click(
        function(){
            //shuffle();
        }
    );

    $("#puzzlearea div").click(move);

    /*
    if($("div").next("div").length > 0 ){
    	alert("hello");
    }
    */
    function move(){
        //alert($(this).css("top") + " Left:"+ $(this).css("left"));
        var top = parseInt($(this).css("top"));
        var left = parseInt($(this).css("left"));
        var current = $(this);

        var rightElt = $('#puzzlearea div').filter(function () { 
            return  $(this).css('left') ==  left+100+'px' && $(this).css('top') ==  top+'px';
        });
        var leftElt = $('#puzzlearea div').filter(function () { 
            return  $(this).css('left') ==  left-100+'px' && $(this).css('top') ==  top+'px';
        });
        var topElt = $('#puzzlearea div').filter(function () { 
            return  $(this).css('top') ==  top-100+'px' && $(this).css('left') ==  left+'px';
        });
        var bottomElt = $('#puzzlearea div').filter(function () { 
            return  $(this).css('top') ==  top+100+'px' && $(this).css('left') ==  left+'px';
        });
        
        //check right
        if(rightElt.hasClass("whitespot") ){
            current.css({'left':left+100+'px'}).delay(400);
            $(".whitespot").css({'left':left+'px'});
        }
        else if(leftElt.hasClass("whitespot") ){
            current.css({'left':left-100+'px'});
            $(".whitespot").css({'left':left+'px'});
        }
        else if(topElt.hasClass("whitespot")){
            current.css({'top':top-100+'px'});
            $(".whitespot").css({'top':top+'px'});
        }
        else if(bottomElt.hasClass("whitespot")){
            current.css({'top':top+100+'px'});
            $(".whitespot").css({'top':top+'px'});
        }
        checkWin();

        
    }
    /*
    function checkWin(){
        var win = false;

        $.each( $("#puzzlearea div"), function( i, val ) {
            var x = ((i % 4) * 100) ;
            var y = (Math.floor(i / 4) * 100) ;
            if( !$(this).hasClass("whitespot") && $(this).css("backgroundPosition") != -x + 'px ' + (-y) + 'px' ){
                win = true;
                //return false;
                return;
            }
            win = false;
            
        });
        alert(win);
    }
    */
});


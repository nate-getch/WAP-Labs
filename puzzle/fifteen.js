$(document).ready(function(){

    var initLoad = true;
    
    function getRandom(num){
        var my_num = Math.floor(Math.random()*num);
        return my_num;
    }
    // initialize each piece
    //for (var i=0; i< divs.length; i++) {
        
    var generate = function generate(){
        $.each( $("#puzzlearea div"), function( i, val ) {
            //var div = divs[i];

            // calculate x and y for this piece
            var x = ((i % 4) * 100) ;
            var y = (Math.floor(i / 4) * 100) ;

            // set basic style and background
            $(this).addClass("puzzlepiece");
            $(this).css({
                "left": x + 'px',
                "top": y + 'px',
                "backgroundImage": 'url("background.jpg")',
                "background-position-x": -x + 'px ',
                "background-position-y": -y + 'px '
                //"backgroundPosition": -x + 'px ' + (-y) + 'px'
            });

        });
        checkWin();
        if(initLoad) {
            $('#puzzlearea').append("<div class='whitespot' style='left:300px;top:300px;'></div>");
        }
        initLoad = false;
    }
    generate();

    $("#shufflebutton").click(function(){
        var pieceHolderArr = [];
         function getRandom(){
                var my_num = Math.floor((Math.random()*16));
                if(pieceHolderArr.includes(my_num)){
                    return getRandom(); // am gonna go recursive, appology for the memory :(
                }
                pieceHolderArr.push(my_num);
                return my_num;
            }

         $.each( $("#puzzlearea div"), function( i, val ) {
            var rand = getRandom();
            console.log(i + " ->" + rand);
            var x = ((rand % 4) * 100) ;
            var y = (Math.floor(rand / 4) * 100) ;

            if($(this).hasClass("whitespot")){
                $(this).css({
                    "left": x + 'px',
                    "top": y + 'px'
                });
            }
            else{
                $(this).css({
                    "left": x + 'px',
                    "top": y + 'px',
                    "cursor":'pointer'
                });
            }
        });
         checkWin();
    });

    $("#puzzlearea div").click(move);

    function move(){
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
    
    function checkWin(){
        var win = true;

        $.each( $("#puzzlearea div"), function( i, val ) {
            if($(this).hasClass("whitespot") )
                return;

            var bgx = -1 * parseInt($(this).css("background-position-x"));
            var bgy = -1 * parseInt($(this).css("background-position-y"));
            var l = parseInt(($(this).css("left")));
            var t = parseInt(($(this).css("top")));
            if( bgx != l || bgy != t ){
                win = false;
                return;
            }           
        });
        if (!initLoad && win) {
            alert("You Are a winner!");
        }
    }
    
});


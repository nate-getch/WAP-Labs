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
    }

    shuffle();

    $("#shufflebutton").click(
        function(){
            //shuffle();
        }
    );

    $("#puzzlearea div").click(function(){
        alert($(this).css("top"));
    });

    /*
    if($("div").next("div").length > 0 ){
    	alert("hello");
    }
    */
});


/**
 * Author: Natnael Demisse
 */
(function () { // no globals
    "use strict";
    var playBtn = document.getElementById("play");
    var stopBtn = document.getElementById("stop");
    var textarea = document.getElementById("textarea");
    var size = document.getElementById("size");
    var speed_selector = document.getElementById("speed_selector");
    var anim_selector = document.getElementById("anim_type");
    var anim_type = "BLANK";
    var speed = 250;
    var frame = "";
    var timer = null;
    var counter = 0;

    playBtn.onclick = beginAnimation;
    stopBtn.onclick = stopAnimation;
    anim_selector.onchange = function () {
        stopAnimation();
    };

    size.onchange = function () {
        textarea.style.fontSize = document.getElementById("size").value;
    };

    speed_selector.onchange = function () {
		if (speed_selector.checked){
			speed = 50;
		}
		else{
			speed = 250;
		}
		//only make change if already animating
		if(!stopBtn.disabled){
			clearInterval(timer);
			timer = setInterval(animate, speed);
		}
    };

    function beginAnimation() {
        if (anim_type != "BLANK") {
            playBtn.disabled = true;
            stopBtn.disabled = false;
            anim_selector.disabled = true;
            frame = ANIMATIONS[anim_type].split("=====\n");
            timer = setInterval(animate, speed);
        }
    }

    function animate() { // applying concept of closure thru inner function
        textarea.value = frame[counter];
        counter++;
        // when last frame reaches
        if (frame.length == counter) {
            counter = 0;
            beginAnimation;
        }
    }

    function stopAnimation() {
        counter = 0;
        playBtn.disabled = false;
        stopBtn.disabled = true;
        anim_selector.disabled = false;
        anim_type = document.getElementById("anim_type").value;
        textarea.value = ANIMATIONS[anim_type];
        clearInterval(timer);
    }
})();
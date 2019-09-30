

function showVideo(ele,url ) {

    console.log(ele);
    $(ele).addClass(" text-primary");
    $(ele).siblings().removeClass(" text-primary");

    var videoSize = getVideoSize();
    console.log("url " +url);
    if(! url.endsWith("m3u8")){
        $("#video").
        html("<iframe src=\"" + url  + "\" width=\" "+ videoSize.width  + "\" height=\""  +videoSize.height +"\" frameBorder=\"0\" allowfullscreen></iframe>");

    }else {

        if ($("video-js").length != 0) {
           videojs("#player").reset();
            videojs("#player").src(url);
        } else {

            $("#video").html(" <video-js id=\"player\" width=\"" + videoSize.width + "height= " + videoSize.height + "\"class=\"vjs-default-skin\" controls>\n" +
                "            <source\n" +
                "                    src=\"  " + url +
                "\"\n" +
                "                    type=\"application/x-mpegURL\">\n" +
                "        </video-js>");

        }
        videojs('player').play();
    }

}


function showCatalogue(index) {

    // $("#play-list ul").hide();
    // $("#play-list ul#catalogue-" +index).show();
    console.log(index);
    $("ul[id^=movie-catalogue]").hide();
    $("#provider-catalogue li a").removeClass("  bg-primary text-white");
    $(`#provider-catalogue li a:eq(${index})`).addClass(" bg-primary text-white");
    $("#movie-catalogue-" +index).delay(500).show(0);


}

function getVideoSize() {
    var width = (window.innerWidth || document.body.clientWidth);
    if (width <1000){
       return {width : Math.round( width*0.8) ,height : Math.round(  width*0.8*9/16)};

    }
    return  {width :960 ,height:540}

}
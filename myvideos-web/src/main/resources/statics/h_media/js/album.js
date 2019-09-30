
let curUrl = decodeURIComponent(window.location.href);
let isNoChooesd = curUrl.indexOf("?") === -1;

let urlInfo = function () {
    let urlInfo = {};
    infoArray = curUrl.split(new RegExp("\\?|&")).slice(1);
    for (let i in  infoArray) {
        let kv = infoArray[i].split("=");

        console.log(kv);
        urlInfo[kv[0]] = kv[1];
    }
    if (!urlInfo.pn) {
        urlInfo.pn = 1;

        if (isNoChooesd) {
            urlInfo['url'] = curUrl + "?pn=1";
        } else {
            urlInfo['url'] = curUrl + "&pn=1";
        }
    } else {
        urlInfo['url'] = curUrl;
        urlInfo.pn = parseInt(urlInfo.pn);
    }
    return urlInfo;
}();


function chooseUrl() {
    $(this).addClass("bg-primary");

    let name = $(this).text().trim();
    console.log(name);
    let chooseName = $(this).parent().attr("id");

    if (name === "不限") {

        window.location.href = curUrl.replace(new RegExp("&?" + chooseName + "=" + urlInfo[chooseName]), "");

    } else if (curUrl.indexOf(chooseName) === -1) {
        if (isNoChooesd) {
            window.location.href = curUrl + "?" + chooseName + "=" + name;
        } else {
            window.location.href = curUrl + "&" + chooseName + "=" + name;
        }
    } else {

        window.location.href = curUrl.replace(chooseName + "=" + urlInfo[chooseName], chooseName + "=" + name);

    }

}

function mark(key, noIndex, ele, inside_ele, className, insideClassName) {

    if (urlInfo[key]) {


        $(ele).text(function (index) {
            let li = ele + `:eq(${index}) `;
            if ($(li + inside_ele).text() === urlInfo[key]) {

                $(li).addClass(className);
                $(li + inside_ele).addClass(insideClassName)
            }
        })
    } else {
        $(ele + `:eq(${noIndex})`).addClass(className);
        $(ele + `:eq(${noIndex})` + inside_ele).addClass(insideClassName)
    }
}

function pageSetup() {
    let preLink, nextLink, firstLink;
    firstLink = urlInfo.url.replace("pn=" + urlInfo.pn, "pn=1");
    nextLink = urlInfo.url.replace("pn=" + urlInfo.pn, "pn=" + (urlInfo.pn + 1));

    //page
    if (urlInfo.pn > 1) {
        preLink = urlInfo.url.replace("pn=" + urlInfo.pn, "pn=" + (urlInfo.pn - 1));

        $("#page li:eq(1) button a").attr("href", preLink);

    }
    //search
    if (urlInfo.search) {
        $("form input").val(urlInfo.search).addClass("text-danger").mouseover(
            function () {
                $("small").fadeIn(3000).fadeOut(1000);
            }
        )
    }

    //page indicator
    $("#page li:eq(0) button a").attr("href", firstLink);
    $("#page li:eq(2) button a").html(urlInfo.pn);
    $("#page li:eq(3) button a").attr("href", nextLink);

    //types marked
    mark("videoType", 0, "#videoType li", " a", "bg-primary", " text-light");
    mark("videoType", 0, "#videoType li a", " a", "bg-primary", "");
    mark("contType", 1, "#contType li", "", "text-primary font-weight-bold", "");
    mark("area", 1, "#area li", "", "text-primary font-weight-bold", "");


// url jump
    $("#navbarSupportedContent ul li").click(chooseUrl);

    for (let i = 0; i < $("#choose ul").length; i++) {
        $(`#choose ul:eq(${i}) li:gt(0)`).click(chooseUrl);


    }
//skip
    $("#skip-page").click(
        function () {
            let  targetPage =parseInt( $("#page li input").last().val());
            if (targetPage >0 ) {

                window.location.href = urlInfo.url.replace("pn=" + urlInfo.pn, "pn=" + targetPage);
            }
        }
    );


}

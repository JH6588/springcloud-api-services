<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${movie.movieName}</title>
    <link rel="stylesheet" href="/statics/css/bootstrap-4.0.0.min.css">
    <link rel="stylesheet" href="/statics/css/font-awesome-5.9.0.min.css">
    <link href="https://vjs.zencdn.net/7.5.4/video-js.css" rel="stylesheet">
    <script src="https://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
    <script src="https://vjs.zencdn.net/7.5.4/video.js"></script>
</head>
<body>

<div class="container">
    <nav class="navbar navbar-expand-lg  navbar-light bg-light">
        <a class="display-1 navbar-brand" href="/movie">HUIDO </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul id="videoType" class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link font-weight-bold" href="/movie">首页</a>
                </li>


                <li class="nav-item text-weight-bold">
                    <a class="nav-link font-weight-bold" href="/movie?videoType=电影">电影</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link font-weight-bold " href="/movie?videoType=电视剧">电视剧</a>
                </li>


                <li class="nav-item">
                    <a class="nav-link font-weight-bold " href="/movie?videoType=动漫&pn=1">动漫</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link font-weight-bold" href="/movie?videoType=综艺&pn=1">综艺</a>
                </li>

                <li class="nav-item text-weight-bold">
                    <a class="nav-link font-weight-bold " href="/movie?videoType=福利">福利</a>
                </li>

            </ul>
            <form action="/movie?" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" name="search" placeholder="片名/演员" aria-label="Search">
                <button id="search" type="submit" class="btn btn-outline-success my-2 my-sm-0">Search</button>
            </form>

        </div>
    </nav>

    <div class="row" style="height: 50px">


    </div>

    <#--    <div class="row">-->
    <#--        <div class="col-md-4">-->
    <#--            <img src="${movie.pic}" alt="${movie.movieName !"未知"}">-->
    <#--        </div>-->
    <#--    </div>-->
    <br>
    <h3>${movie.movieName !"未知"}</h3>
    <div id="video" class="col-md-8">

        <ul>
            <li>
                <div class="col-md-4">
                    <img src="${movie.pic}" alt="${movie.movieName !"未知"}">
                </div>

            </li>
            <li><span class="text-muted">主演:</span> ${movie.cast !"未知"}</li>
            <li><span class="text-muted">导演:</span>${movie.director  !"未知" }</li>
            <li><span class="text-muted">类型:</span> ${movie.contType !"未知" } <span
                        class="text-muted">地区: </span>${movie.area !"未知"}<span
                        class="text-muted">年份:</span>
                    ${movie.year !"未知"}
                 </li>
            <li><span class="text-muted">简介:</span>
                <p>

                    ${movie.info !"未知"}
                </p></li>

        </ul>

    </div>
    <br>
    <h4>播放列表</h4>

    <div class="row">
        <ul id="provider-catalogue" class="nav nav-tabs">
            <#assign i=0>
            <#list movie.providers  as provider>

                <li class="nav-item font-weight-bold"><a href="#" onclick="showCatalogue(${i})"
                                                         class="nav-link active">${provider . provider}</a></li>
            <#--                <li class="list-inline-item border"><button class="bg-success" onclick="showCatalogue(${index})">${provider . provider}</button></li>-->
                <#assign i=i+1>
            </#list>
        </ul>
    </div>

    <div class="row">
        <#assign j=0>
        <#list movie.providers  as provider>


            <ul id="movie-catalogue-${j}" class="list-inline">

                <#list provider.episodes as episode>
                    <li class="list-inline-item m-2 border"
                        onclick="showVideo( this,'${episode.url}')">${episode.title}</li>

                </#list>

            </ul>
            <#assign j=j+1>
        </#list>
    </div>


</div>

<div class="row">

    <hr>
    <p class="text-secondary">
        免责声明:HUIDO电影网 所有视频均来自互联网收集而来，版权归原创者所有，如侵犯了你的权益，请通知我 coderslash@gmail.com，我们会及时删除侵权内容，谢谢合作。
    </p>
    <p class="text-secondary ">
        Copyright © 2018-2019 www.huido.com. All Rights Reserved.
    </p>


</div>


</div>


</body>


<script src="/statics/js/jquery-3.4.1.min.js"></script>
<script src="/statics/js/boostrap-4.0.0.min.js"></script>
<script src="/statics/js/popper-1.12.9.min.js"></script>
<script src="/statics/h_media/js/play.js"></script>
<script>
    $(function () {


        showCatalogue(0);
    });


</script>

</html>
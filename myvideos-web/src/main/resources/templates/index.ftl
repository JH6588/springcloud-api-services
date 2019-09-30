<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HUIDO</title>
    <link rel="stylesheet" href="/statics/css/bootstrap-4.0.0.min.css">
    <link rel="stylesheet" href="/statics/css/font-awesome-5.9.0.min.css">
</head>

<link rel="stylesheet" href="/statics/css/bootstrap-4.0.0.min.css"/>
<link rel="stylesheet" href="/statics/css/font-awesome-5.9.0.min.css"/>

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
                <li class="nav-item">
                    <a class="nav-link font-weight-bold" href="/movie">首页</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link font-weight-bold" href="/movie?videotype=电影">电影</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link font-weight-bold " href="/movie?videotype=电视剧">电视剧</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link font-weight-bold " href="/movie?videotype=动漫&pn=1">动漫</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link font-weight-bold" href="/movie?videotype=综艺&pn=1">综艺</a>
                </li>

                <li class="nav-item text-weight-bold">
                    <a class="nav-link font-weight-bold" name="福利" href="/movie?videotype=福利">福利</a>
                </li>
            </ul>
            <form action="/movie?" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" name="key" placeholder="片名/演员" aria-label="Search">
                <button id="search" type="submit" class="btn btn-outline-success my-2 my-sm-0">Search</button>
            </form>
        </div>
    </nav>



    <div class="row" style="height: 50px">
        <div class="col-md-2 offset-10">
            <small style="display: none">放弃本次搜索 需清空搜索框 回车</small>
            <p id="update" class="text-right font-weight-bold text-info mt-2"></p>
        </div>
    </div>
    <div id="choose" class="row">
        <ul id="conttype" class="list-inline">
            <li class="list-inline-item p-2"><span class="text-muted ">分类</span></li>
            <li class="list-inline-item p-2">不限</li>
            <li class="list-inline-item  p-2">动作</li>
            <li class="list-inline-item p-2">喜剧</li>
            <li class="list-inline-item p-2">恐怖</li>
            <li class="list-inline-item p-2">爱情</li>
            <li class="list-inline-item p-2">剧情</li>
            <li class="list-inline-item p-2">纪录</li>
            <li class="list-inline-item p-2">伦理</li>
            <li class="list-inline-item p-2">战争</li>
        </ul>
        <ul id="area" class="list-inline">
            <li class="list-inline-item p-2"><span class="text-muted ">地区</span></li>
            <li class="list-inline-item  p-2 ">不限</li>
            <li class="list-inline-item p-2">大陆</li>
            <li class="list-inline-item p-2">台湾</li>
            <li class="list-inline-item p-2">香港</li>
            <li class="list-inline-item p-2">韩国</li>
            <li class="list-inline-item p-2">日本</li>
            <li class="list-inline-item p-2">美国</li>
            <li class="list-inline-item p-2">英国</li>
            <li class="list-inline-item p-2">法国</li>
            <li class="list-inline-item p-2">西班牙</li>
            <li class="list-inline-item p-2">加拿大</li>
        </ul>
    </div>


    <div class="row">
        <#list movieList as movie >
            <div class="col-xl-2 col-md-3 col-sm-4 ">
                <a href="/col/${movie.dataId?c}" style="position: relative">
                    <img class="card-img-top" src="${movie.pic}" alt="${movie.movieName!"未知"}"/>
                    <#--                    <span class="text-right text-muted" style="position:absolute;bottom: 0px">HD</span>-->
                </a>
                <div class="card-body">
                    <h5 class="card-title"><a href="/col/${movie.dataId?c}">${movie.movieName !"未知"}</a></h5>
                    <span class="text-muted text-left d-inline-block text-truncate"
                          style="max-width: 150px; font-size:0.8em">${movie.cast !"未知"}</span>

                </div>
            </div>
        </#list>
    </div>

    <div class="row pt-5">

        <div class="col-md-6 mx-auto">
            <ul id="page" class="list-inline">
                <li class="list-inline-item ">
                    <button class="btn btn-outline-secondary"><a>首页</a></button>
                </li>
                <li class="list-inline-item ">
                    <button class="btn btn-outline-secondary"><a>上一页</a></button>
                </li>
                <li class="list-inline-item">
                    <button class="btn btn-outline-secondary"><a></a></button>
                </li>

                <li class="list-inline-item">
                    <button class="btn btn-outline-secondary"><a>下一页</a></button>
                </li>
                <li class="list-inline-item">
                    <div class="input-group" style="width: 6rem">
                        <input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                        <div class="input-group-append">
                            <button id="skip-page" class="btn btn-outline-secondary" type="button">跳转</button>
                        </div>
                    </div>

                </li>
            </ul>

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
<script src="/statics/h_media/js/album.js"></script>

<script>
pageSetup()
</script>
<
</html>
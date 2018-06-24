<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<style>
    .jinn-div {
        margin: 10px;
    }
    .query-div li span {
        margin: 5px 10px;
    }
</style>
<head>
    <title>下载页面</title>
</head>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
<body>
<div class="jinn-div">
    <label for="saveFolder">保存路径</label>
    <input type="text" id="saveFolder" style="width: 1000px;" value="F:\J.Video\jp.porn.hub">
</div>
<div class="jinn-div">
    <label for="url">下载连接</label>
    <input type="text" id="url" style="width: 1000px;">
</div>
<div class="jinn-div">
    <span id="success" style="color: green;display: none;">已经开始下载任务</span>
    <span id="error" style="color: red;display: none;">下载异常</span>
</div>
<div class="jinn-div">
    <button type="submit" id="startDownload">开始下载</button>
    <button id="query">查看下载列表</button>
</div>
<div class="jinn-div" id="queryList">
</div>
</body>
<script>
    $(function () {
        $('#startDownload').bind('click', function () {
            var params = {};
            params.url = $('#url').val();
            params.saveFloder = $('#saveFolder').val();
           $.ajax({
               async: true,
               type: 'post',
               url: '${pageContext.servletContext.contextPath}/download/start',
               dataType: 'json',
               data: params,
               success: function (data) {
                   if (data.code == 200){
                       showTips('success', data.msg);
                   }else{
                       showTips('error', data.msg);
                   }
               },
               error: function (data) {
                   alert("异常");
               }
           })
        });
        $('#query').bind('click', function () {
            query();
        });
        setInterval(function () {
            console.log('自动刷新');
            query();
        }, 2000)
    });
    function query() {
        $.ajax({
            async: true,
            type: 'post',
            url: '${pageContext.servletContext.contextPath}/download/query',
            dataType: 'json',
            data: {'url':$('#url').val()},
            success: function (data) {
                var html = '<ol class="query-div">';
                $.each(data, function (index, value) {
                    html += '<li><span>'+value.fileName+'</span><span>'+formatSize(value.size)+'</span><span>'+formatTime(value.spent)+'</span></li>';
                });
                html += '</ol>';
                $('#queryList').html(html);
            },
            error: function (data) {
                alert("异常");
            }
        })
    }
    function formatSize(size) {
        var factor = 1024.0;
        var num = Number(size);
        var unit = 'B';
        if (num > factor * factor * factor){
            num = num / (factor * factor * factor);
            unit = 'GB';
        }else if (num > factor * factor){
            num = num / (factor * factor);
            unit = 'MB';
        }else if (num > factor){
            num = num / factor;
            unit = 'KB';
        }

        return num.toFixed(2)+unit;
    }
    function formatTime(time) {
        var num = Number(time);
        var unit = '毫秒';
        if (time > 1000 * 60){
            num = num / (1000 * 60);
            unit = '分';
        }else if (time > 1000){
            num = num / 1000;
            unit = '秒';
        }
        return num.toFixed(2)+unit;
    }
    function showTips(type, msg) {
        if (type === 'success'){
            $('#success').text(msg);
            $('#success').show();
            setTimeout(function () {
                $('#success').hide();
            }, 5000);
        }else if (type === 'error'){
            $('#error').text(msg);
            $('#error').show();
            setTimeout(function () {
                $('#error').hide();
            }, 5000);
        }
    }
</script>
</html>
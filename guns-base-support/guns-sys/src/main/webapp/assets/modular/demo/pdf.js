layui.use(['layer', 'table', 'ax', 'laydate', 'upload'], function () {
    var $ = layui.$;
    var upload = layui.upload;

    //上传文件
    upload.render({
        elem: '#fileBtn'
        , url: Feng.ctxPath + '/demo/upload'
        , accept: 'file'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $("#fileNameTip").html(file.name);
            });
        }
        , done: function (res) {
            $("#fileInputHidden").val(res.data.fileId);
            Feng.success(res.message);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

    $('.drag-box-3 .drag').each(function(index){
        $(this).myDrag({
            randomPosition:true,
            direction:'all',
            handler:false
        });
    });

    $("#pdfDown").on('click',function(){
        $.post("/demo/exportReport",{},function(result){
            alret("success");
        });
    });

    /**
    $("#pdfDown").on('click',function(){
        var element = $("#pdfDiv");    // 这个dom元素是要导出pdf的div容器，即要生成PDF的div
        var w;    // 获得该容器的宽
        var h;    // 获得该容器的高　　　　　//因为我是两种分布情况，故而有了下边的if、else，如果只有一种用else里的即可
        if($(".btnActive").attr('id') == 'Hmerage'){
            h = element.outerWidth()
            w = element.outerHeight()
        }else{
            w = element.outerWidth()
            h = element.outerHeight()
        }
        var offsetTop = element.offset().top;    // 获得该容器到文档顶部的距离
        var offsetLeft = element.offset().left;    // 获得该容器到文档最左的距离
        var canvas = document.createElement("canvas");
        canvas.width = w * 2;    // 将画布宽&&高放大两倍
        canvas.height = h * 2;
        var context = canvas.getContext("2d");
        var scale = 2;
        context.scale(2, 2);
        context.translate(-offsetLeft, -offsetTop);

        var opts = {
            scale: scale,
            canvas: canvas,
            width: w,
            height: h,
            useCORS: true,
            background: '#ffffff'
        }

        html2canvas(element[0], opts).then(function (canvas) {

            var contentWidth = canvas.width;
            var contentHeight = canvas.height;
            //一页pdf显示html页面生成的canvas高度;
            var pageHeight = contentWidth / 592.28 * 841.89;
            //未生成pdf的html页面高度
            var leftHeight = contentHeight;
            //页面偏移
            var position = 0;
            //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
            var imgWidth = 595.28*1.01;
            var imgHeight = 592.28 / contentWidth * contentHeight*1.01;

            var pageData = canvas.toDataURL('image/jpeg', 1.0);
            var pdf = new jsPDF('', 'pt', 'a4');

            //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
            //当内容未超过pdf一页显示的范围，无需分页
            if (leftHeight < pageHeight) {
                pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
            } else {    // 分页
                while (leftHeight > 0) {
                    pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                    leftHeight -= pageHeight;
                    position -= 841.89;
                    //避免添加空白页
                    if (leftHeight > 0) {
                        pdf.addPage();
                    }
                }
            }
            pdf.save('content.pdf');
        })
    })
     **/
});

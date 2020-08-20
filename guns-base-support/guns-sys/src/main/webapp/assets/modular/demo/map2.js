layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    //人员标注集合
    var userMarker = new Array();

    // 添加按钮事件
    $('#btnAdd').click(function () {
        addUser();
    });

    /**
     * 地图添加人员
     */
    function addUser() {
        var newLocationMarker = new fengmap.FMLocationMarker({
            //x坐标值
            x: 12961654.36,
            //y坐标值
            y: 4861871.82,
            //图片地址
            url: '/assets/common/js/demo/map1/images/location.png',
            //楼层id
            groupID: 1,
            //图片尺寸
            size: 48,
            //marker标注高度
            height: 3,
            callback: function () {
                //回调函数
                console.log('定位点marker加载完成！');
            }
        });
        //添加定位点marker
        map.addLocationMarker(newLocationMarker);

        //坐标点移动
        userMarker.push(newLocationMarker);
        userMove(newLocationMarker);
    }


    var moveIndex = 16;
    /**
     * 模拟请求实际坐标，实现地图移动
     */
    function userMove(newLocationMarker) {
        setInterval(function () {
            if(moveIndex < 0) {
                moveIndex = 16;
            }
            var moveData = _mockdata[moveIndex];
            //移动locationMarker
            newLocationMarker.moveTo({
                x: moveData.x,
                y: moveData.y,
                groupID: 1
            });
            moveIndex--;
        }, _freq);
    }
});

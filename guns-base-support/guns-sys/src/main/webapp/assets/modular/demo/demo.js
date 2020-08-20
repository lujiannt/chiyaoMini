layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    /**
     *
     */
    var demo = {
        tableId: "demoTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    demo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'name', align: "center", sort: true, title: '名字'},
            {field: 'createTime', align: "center", sort: true, title: '时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    demo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(demo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 导出excel按钮
     */
    demo.exportExcel = function () {
        var checkRows = table.checkStatus(demo.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 详情
     */
    demo.logDetail = function (param) {
        var ajax = new $ax(Feng.ctxPath + "/log/detail/" + param.operationLogId, function (data) {
            Feng.infoDetail("日志详情", data.regularMessage);
        }, function (data) {
            Feng.error("获取详情失败!");
        });
        ajax.start();
    };

    /**
     * 清空日志
     */
    demo.cleanLog = function () {
        Feng.confirm("是否清空所有测试数据?", function () {
            var ajax = new $ax(Feng.ctxPath + "/demo/del", function (data) {
                Feng.success("清空成功!");
                demo.search();
            }, function (data) {
                Feng.error("清空失败!");
            });
            ajax.start();
        });
    };

    //渲染时间选择框
    laydate.render({
        elem: '#beginTime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime'
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + demo.tableId,
        url: Feng.ctxPath + '/demo/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: demo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        demo.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        demo.cleanLog();
    });

    // 工具条点击事件
    table.on('tool(' + demo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'detail') {
            demo.logDetail(data);
        }
    });
});

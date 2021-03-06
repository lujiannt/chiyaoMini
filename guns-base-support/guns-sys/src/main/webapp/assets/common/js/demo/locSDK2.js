/**
 * 这是一个模拟的定位sdk，用来定期返回位置更新,仅用作参考。
 * */
var _mockdata = [
    {x: 12961581.812, y: 4861817.64, angle: 0},
    {x: 12961581.85, y: 4861817.64, angle: 0},
    {x: 12961581.845, y: 4861821.941, angle: 0},
    {x: 12961581.845, y: 4861836.735, angle: -90},
    {x: 12961589.604, y: 4861836.735, angle: -90},
    {x: 12961594.922, y: 4861836.735, angle: -60},
    {x: 12961599.241, y: 4861841.487, angle: -60},
    {x: 12961602.482, y: 4861845.055, angle: -60},
    {x: 12961607.156, y: 4861850.198, angle: -90},
    {x: 12961614.931, y: 4861850.198, angle: -90},
    {x: 12961617.982, y: 4861850.198, angle: -90},
    {x: 12961625.436, y: 4861850.198, angle: -90},
    {x: 12961632.715, y: 4861850.198, angle: -90},
    {x: 12961641.638, y: 4861850.198, angle: -90},
    {x: 12961645.618, y: 4861850.198, angle: 0},
    {x: 12961645.618, y: 4861861.696, angle: 0},
    {x: 12961654.36, y: 4861871.82, angle: -90}
];
var _callback;
var _update;
var _updateInternal;
var _freq = 1000;
var _index = 0;

/**
 * 模拟更新位置，按照时间间隔更新位置信息。
 * @param {*} 回调函数
 */
function updateLocation(cb) {
    _callback = function () {
        var _data;
        if (_index > _mockdata.length - 1) {
            _index = 0;
        }
        _data = _mockdata[_index];
        _index++;
        return cb(_data)
    };
    _update = setInterval(_callback, _freq);
}

/**
 * 停止位置更新
 */
function stopUpdateLocation() {
    clearInterval(_update);
    console.log('update stoped.');
}




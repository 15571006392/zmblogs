var config = {
    model: {
        jsonPath: 'https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json' // 加载模型的json路径
    },
    display: {
        superSample: 1, // 超采样等级
        width: 220, // canvas的宽度
        height: 270, // canvas的高度
        position: 'left', // 显示位置：左或右
        hOffset: 30, // canvas水平偏移
        vOffset: 0, // canvas垂直偏移
    },
    mobile: {
        show: false, // 是否在移动设备上显示
    },
    react: {
        opacityDefault: 1, // 默认透明度
        opacityOnHover: 1 // 鼠标移上透明度
    }
}
L2Dwidget.init(config);
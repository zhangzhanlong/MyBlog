var E = window.wangEditor
var editor = new E('#wangEditor3_01', '#wangEditor3_02') // 两个参数也可以传入 elem 对象，class 选择器
// 自定义菜单配置
editor.customConfig.menus = [
  'head', // 标题
  'bold', // 粗体
  'fontSize', // 字号
  'fontName', // 字体
  'italic', // 斜体
  'underline', // 下划线
  'strikeThrough', // 删除线
  'foreColor', // 文字颜色
  'backColor', // 背景颜色
  'link', // 插入链接
  'list', // 列表
  'justify', // 对齐方式
  'quote', // 引用
  'emoticon', // 表情
  'image', // 插入图片
  'table', // 表格
  'video', // 插入视频
  'code', // 插入代码
  'undo', // 撤销
  'redo' // 重复
]
// 通过 url 参数配置 debug 模式。url 中带有 wangeditor_debug_mode=1 才会开启 debug 模式
editor.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0
//编辑区域的z-index默认为10000，可自定义修改，代码配置如下。需改之后，编辑区域和菜单的z-index会同时生效。
//editor.customConfig.zIndex = 10000

// 关闭粘贴样式的过滤
editor.customConfig.pasteFilterStyle = false
// 忽略粘贴内容中的图片
editor.customConfig.pasteIgnoreImg = false
// 自定义处理粘贴的文本内容
editor.customConfig.pasteTextHandle = function (content) {
  // content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
  return content + '<p>在粘贴内容后面追加一行</p>'
}
//插入网络图片
editor.customConfig.linkImgCallback = function (url) {
  console.log(url) // url 即插入图片的地址
}
//插入链接的校验
editor.customConfig.linkCheck = function (text, link) {
  console.log(text) // 插入的文字
  console.log(link) // 插入的链接

  return true // 返回 true 表示校验成功
  // return '验证失败' // 返回字符串，即校验失败的提示信息
}
// 自定义配置颜色（字体颜色、背景色）
editor.customConfig.colors = [
  '#000000',
  '#eeece0',
  '#1c487f',
  '#4d80bf',
  '#c24f4a',
  '#8baa4a',
  '#7b5ba1',
  '#46acc8',
  '#f9963b',
  '#ffffff'
]
// 自定义字体
editor.customConfig.fontNames = [
  '宋体',
  '微软雅黑',
  'Arial',
  'Tahoma',
  'Verdana'
]
// 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
editor.customConfig.uploadImgShowBase64 = true // 使用 base64 保存图片
// editor.customConfig.uploadImgServer = '/upload'  // 上传图片到服务器
// 隐藏“网络图片”tab
editor.customConfig.showLinkImg = true
//使用 base64 保存图片
editor.customConfig.uploadImgShowBase64 = true // 使用 base64 保存图片
// 配置上传图片服务器端地址
//editor.customConfig.uploadImgServer = '/upload'
// 进行下文提到的其他配置
// ……
// ……
// ……

// 将图片大小限制为 3M
editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024

// 限制一次最多上传 5 张图片
editor.customConfig.uploadImgMaxLength = 5
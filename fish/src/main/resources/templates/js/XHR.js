
/*
XMLHttpRequest 是 AJAX 的基础。
    XMLHttpRequest 对象
    所有现代浏览器均支持 XMLHttpRequest 对象（IE5 和 IE6 使用 ActiveXObject）。
    XMLHttpRequest 用于在后台与服务器交换数据。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
 */

function createObject() {
    //创建 XMLHttpRequest 对象的语法：
    variable=new XMLHttpRequest();

    //老版本的 Internet Explorer （IE5 和 IE6）使用 ActiveX 对象：
    variable=new ActiveXObject("Microsoft.XMLHTTP");

    //检查浏览器是否支持 XMLHttpRequest 对象。
    //如果支持，则创建 XMLHttpRequest 对象。如果不支持，则创建 ActiveXObject ：
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
}

//向服务器发送请求
function postRequest() {
    //将请求发送到服务器，使用 XMLHttpRequest对象的open()和send()方法：
    /*
    open(method,url,async)  规定请求的类型、URL 以及是否异步处理请求。
                            method：请求的类型；GET 或 POST
                            url：文件在服务器上的位置
                            async：true（异步）或 false（同步）
    send(string)            将请求发送到服务器。string仅用于post请求
    GET 还是 POST？
    与 POST 相比，GET 更简单也更快，并且在大部分情况下都能用。
    然而，在以下情况中，请使用 POST 请求：
        无法使用缓存文件（更新服务器上的文件或数据库）
        向服务器发送大量数据（POST 没有数据量限制）
        发送包含未知字符的用户输入时，POST 比 GET 更稳定也更可靠
     */
    xhr = new XMLHttpRequest();

    //GET请求
    xhr.open('GET','demo_get.asp',true);
    xhr.send();

    //向 URL 添加一个唯一的 ID：
    xhr.open("GET","demo_get.asp?t=" + Math.random(),true);
    xhr.send();

    //通过 GET 方法发送信息，向 URL 添加信息：
    xhr.open("GET","demo_get2.asp?fname=Bill&lname=Gates",true);
    xhr.send();

    //POST请求
    xhr.open('POST','demo_get.asp',true);
    xhr.send();

    //像 HTML 表单那样 POST 数据，使用 setRequestHeader() 来添加 HTTP 头。
    // 然后在 send() 方法中规定希望发送的数据：
    /*
    setRequestHeader(header,value)	向请求添加 HTTP 头。
                                    header: 规定头的名称
                                    value: 规定头的值
     */
    xhr.open("POST","ajax_test.asp",true);
    xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhr.send("fname=Bill&lname=Gates");

    /*
    url - 服务器上的文件
    open() 方法的 url 参数是服务器上文件的地址：
    该文件可以是任何类型的文件，比如 .txt 和 .xml，或者服务器脚本文件，比如 .asp 和 .php （在传回响应之前，能够在服务器上执行任务）。
     */

    /*async
    AJAX 指的是异步 JavaScript 和 XML（Asynchronous JavaScript and XML）。
    XMLHttpRequest 对象如果要用于 AJAX 的话，其 open() 方法的 async 参数必须设置为 true：
     */
}

//readyStateChange事件，专门用来监听readyState的改变，有改变就触动事件
//监听 ajax 的readyState 是不是到 4
function useReadyStateChange() {
    const xhr = new XMLHttpRequest();
    xhr.open('get','demo_get.asp');
    xhr.send();
    xhr.onreadyStateChange = function () {
        // 每次 readyState 改变的时候都会触发该事件
        // 我们就在这里判断 readyState 的值是不是到 4
        // 并且 http 的状态码是不是 200 ~ 299
        if (xhr.readyState === 4 && /^2\d{2|$/.test(xhr.status)) {
            // 这里表示验证通过
            // 我们就可以获取服务端给我们响应的内容了
        }
    };
}


//用responseText获取响应体内容
function useTesponseText() {
    const xhr = new XMLHttpRequest();
    xhr.open('get', './data.php');
    xhr.send();
    xhr.onreadyStateChange = function () {
        if (xhr.readyState === 4 && /^2\d{2|$/.test(xhr.status)) {
            // 我们在这里直接打印 xhr.responseText 来查看服务端给我们返回的内容
            console.log(xhr.responseText)
        }
    }
}


//发送一个带有参数的 get 请求
function postGet() {
    //get 请求的参数就直接在 url 后面进行拼接就可以
    const xhr = new XMLHttpRequest();
    // 直接在地址后面加一个 ?，然后以 key=value 的形式传递
    // 两个数据之间以 & 分割
    xhr.open('get', './data.php?a=100&b=200');
    xhr.send();
    /*
    这样服务端就能接受到两个参数
    一个是 a，值是 100
    一个是 b，值是 200
     */
}


//发送一个带有参数的 post 请求
//post 请求的参数是携带在请求体中的，所以不需要再 url 后面拼接
function postPost() {
    const xhr = new XMLHttpRequest();
    xhr.open('post', './data.php');
    // 如果是用 ajax 对象发送 post 请求，必须要先设置一下请求头中的 content-type
    // 告诉一下服务端我给你的是一个什么样子的数据格式
    //application/x-www-form-urlencoded 表示的数据格式就是 key=value&key=value
    xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
    // 请求体直接再 send 的时候写在 () 里面就行
    // 不需要问号，直接就是 'key=value&key=value' 的形式
    xhr.send('a=100&b=200');
}


//封装Ajax
function ajax(options) {
    // 先准备一个默认值
    var defInfo = {
        url: '', // 地址不需要默认值
        type: 'GET', // 请求方式的默认值是 GET
        async: false, // 默认值是异步
        data: '', // 参数没有默认值
        dataType: 'string', // 默认不需要执行 json.parse
        success () {}, // 默认是一个函数
    };
    // 先来判断一下有没有传递 url，如果没有，直接抛出异常
    if (!options.url) {
        throw new Error('url 必须传递');
    };
    // 有了 url 以后就，我们就把用户传递的参数和我们的默认数据合并
    for (let key in options) {
        defInfo[key] = options[key];
    };
    // 接下来的一切我们都是使用我们的 defInfo 就可以了
    // 第一步就是判断参数 data
    // data 可以不传递，可以为空
    // data 也可以是一个 key=value&key=value 格式的字符串
    // data 也可以是一个对象
    // 否则就抛出异常
    if (!(typeof defInfo.data === 'string' && /^(\w+=\w+&?)*$/.test(defInfo.data) || Object.prototype.toString.call(defInfo.data) === '[object Object]')) {
        throw new Error('请按照要求传递参数');
    };
    // 参数处理完毕以后，在判断 async 的数据类型
    // 只能传递 布尔数据类型
    if (typeof defInfo.async !== 'boolean') {
        throw new Error('async 参数只接受布尔数据类型');
    };
    // 在接下来就判断 type
    // 请求方式我们只接受 GET 或着 POST
    if (!(defInfo.type.toUpperCase() === 'GET' || defInfo.type.toUpperCase() === 'POST')) {
        throw new Error('目前本插件只接受 GET 和 POST 方式，请期待更新');
    };
    // 接下来就是判断 success 的判断，必须是一个函数
    if (Object.prototype.toString.call(defInfo.success) !== '[object Function]') {
        throw new Error('success 只接受函数数据类型');
    };
    // 参数都没有问题了
    // 我们就要把 data 处理一下了
    // 因为 data 有可能是对象，当 data 是一个对象的时候，我们要把它转换成一个字符串
    var str = '';
    if (Object.prototype.toString.call(defInfo.data) === '[object Object]') {
        for (let attr in defInfo.data) {
            str += `${attr}=${defInfo.data[attr]}&`;
        };
        str = str.slice(0, -1);
        defInfo.data = str;
    };
    // 参数全部验证过了以后，我们就可以开始进行正常的 ajax 请求了
    // 1. 准备一个 ajax 对象
    //    因为要处理兼容问题，所以我们准备一个函数
    function createXHR() {
        if (XMLHttpRequest) {
            return new XMLHttpRequest()
        } else {
            return new ActiveXObject('Microsoft.XMLHTTP')
        }
    }
    // 2. 创建一个 ajax 对象
    var xhr = createXHR()
    // 3. 进行 open
    xhr.open(defInfo.type, defInfo.url + (defInfo.type.toUpperCase() === 'GET' ? `?${defInfo.data}&_=${new Date().getTime()}` : ''), defInfo.async)
  if (defInfo.type.toUpperCase() === 'POST') {
      xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded')
  }
    // 4. 进行 send
    xhr.send((defInfo.type.toUpperCase() === 'POST' ? `${defInfo.data}` : ''))
    // 5. 接受响应
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && /2\d{2}/.test(xhr.status)) {
            // 表示成功，我们就要执行 success
            // 但是要进行 dataType 的判断
            if (defInfo.dataType === 'json') {
                defInfo.success(JSON.parse(xhr.responseText))
            } else {
                defInfo.success()
            }
        }
    }
}








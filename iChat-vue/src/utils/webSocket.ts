import { fetchUserInfo } from '../api/user'

let userId: String
let timer: any

/**
 * websocket
 */
const wsUrl = "ws://127.0.0.1:9999"; // websocket 默认连接地址
let websocket: any; // 用于存储实例化后websocket
let isConnect = false; // 连接标识 避免重复连接
let rec: any; // 断线重连后，延迟5秒重新创建WebSocket连接  rec用来存储延迟请求的代码

// 创建websocket
function creatWebSocket(id: String) {
  userId = id
  console.log("websocket==================");
  // 判断当前浏览器是否支持WebSocket
  if ("WebSocket" in window) {
    console.log("当前浏览器 windows");
  } else if ("MozWebSocket" in window) {
    console.log("当前浏览器 windows");
  } else {
    console.log("当前浏览器 Not support websocket");
  }

  try {
    initWebSocket(); // 初始化websocket连接
  } catch (e) {
    console.log("尝试创建连接失败");
    reConnect(); // 如果无法连接上 webSocket 那么重新连接！可能会因为服务器重新部署，或者短暂断网等导致无法创建连接
  }
}

// 初始化websocket
function initWebSocket() {
  websocket = new WebSocket(wsUrl);
  console.log("websocket:", websocket);

  websocket.onopen = function (e: any) {
    websocketOpen(e);
    clearInterval(timer);
    timer = setInterval(function() {
        if (websocket.readyState == WebSocket.OPEN) {
            let data = {
                type: 11,
                params: {},
            }
            websocket.send(JSON.stringify(data));
        } else {
            reConnect()
        }  
    }, 1000 * 25)
  };

  // 接收
  websocket.onmessage = function (e: any) {
    websocketonmessage(e);
  };

  // 连接发生错误
  websocket.onerror = function () {
    console.log("WebSocket连接发生错误");
    isConnect = false; // 连接断开修改标识
    reConnect(); // 连接错误 需要重连
  };

  websocket.onclose = function (e: any) {
    websocketclose(e);
  };
}

// 定义重连函数
let reConnect = () => {
  if (isConnect) return; // 如果已经连上就不在重连了
  rec && clearTimeout(rec);
  rec = setTimeout(function () {
    // 延迟5秒重连  避免过多次过频繁请求重连
    creatWebSocket(userId);
  }, 5000);
};

// 创建连接
function websocketOpen(e: any) {
    let value = {
        'userId': userId
    }
    fetchUserInfo(value).then(res => {
        let data = {
            type: 7,
            params: res.data.data
        }
        websocket.send(JSON.stringify(data));
    })
}
// 数据接收
function websocketonmessage(e: any) {
  let json = JSON.parse(e.data)
  console.log(json)
  if (json.status == 200) {
    switch(json.type) {
        case 2:     // 收到单聊信息
            break;
        case 4:     // 创建聊天
            break;
        case 10:    // 收到群聊信息
            break;
        default:
            break;
    }
  }
  // let data = JSON.parse(decodeUnicode(e.data))
}
// 关闭
function websocketclose(e: any) {
  console.log(e);
  isConnect = false; // 断开后修改标识
  console.log("connection closed (" + e.code + ")");
}

// 数据发送
function websocketsend(data: any) {
  websocket.send(JSON.stringify(data));
}

// 实际调用的方法==============

// 发送
function sendWebSocket(data: any) {
  if (websocket.readyState === websocket.OPEN) { // 开启状态
    websocketsend(data);
  } else { // 若 未开启 / 正在开启 状态 ，则等待1s后重新调用
    setTimeout(function () {
      sendWebSocket(data);
    }, 1000);
  }
}



// 关闭
let closeWebSocket = () => {
  websocket.close();
};

export {
  sendWebSocket,
  creatWebSocket,
  closeWebSocket,
};
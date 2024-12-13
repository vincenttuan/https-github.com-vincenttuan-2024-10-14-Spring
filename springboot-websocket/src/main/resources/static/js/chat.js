var webSocket;
const url = 'ws://localhost:8080/channel/chat';
const openButton = document.getElementById('openButton');
const closeButton = document.getElementById('closeButton');
const messageInput = document.getElementById('messageInput');
const sendButton = document.getElementById('sendButton');
const log = document.getElementById('log');

// 更新 log (對話紀錄)
const addLog = (message) => {
	log.textContent += `${message}\n`;
};

// 連接按鈕
openButton.onclick = () => {
	webSocket = new WebSocket(url);
	
	webSocket.onopen = () => {
		addLog("WebSocket 連線成功");
		openButton.disabled = true;
		closeButton.disabled = false;
		messageInput.disabled = false;
		sendButton.disabled = false;		
	};
	
	webSocket.onmessage = (event) => {
		addLog(`收到消息: ${event.data}`);	
	};
	
	webSocket.onclose = (event) => {
		addLog("WebSocket 連線已關閉");	
		openButton.disabled = false;
		closeButton.disabled = true;
		messageInput.disabled = true;
		sendButton.disabled = true;
	};
	
	webSocket.onerror = (event) => {
		addLog(`發生錯誤: ${event}`)	
	};
	
};

// 關閉按鈕
closeButton.onclick = () => {
	webSocket.close();
	addLog("Client 端主動關閉 WebSocket 連線");	
};

// 傳送按鈕
sendButton.onclick = () => {
	const message = messageInput.value;
	webSocket.send(message);
};


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
	
};

// 關閉按鈕
closeButton.onclick = () => {
	
};

// 傳送按鈕
sendButton.onclick = () => {
	
};


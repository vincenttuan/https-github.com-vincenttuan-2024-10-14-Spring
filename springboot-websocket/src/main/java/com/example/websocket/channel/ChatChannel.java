package com.example.websocket.channel;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

// 聊天頻道 
// @ServerEndpoint 表示一個 WebSocket 伺服端點
// Websocket 連接路徑 ws://localhost:8080/channel/chat
@ServerEndpoint(value = "/channel/chat")
public class ChatChannel {
	
	@OnOpen // 當客戶端與伺服器建立連線時觸發
	public void onOpen(Session session) {
		// 每一個新連線都會得到一個 session id
		System.out.println("客戶端[ " + session.getId() +" ] 已連線");
		session.getAsyncRemote().sendText("客戶端[ " + session.getId() +" ] 已連線");
	}
	
	@OnMessage // 當伺服端收到來自客戶端的消息時觸發
	public void onMessage(String jsonString, Session session) {
		String reply = "伺服端收到了:" + jsonString;
		session.getAsyncRemote().sendText(reply);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("[ " + session.getId() +" ] 已離線");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("[ " + session.getId() +" ] 發生錯誤 ! " + throwable);
	}
}

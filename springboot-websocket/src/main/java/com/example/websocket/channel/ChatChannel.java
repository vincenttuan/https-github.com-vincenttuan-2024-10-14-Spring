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
	
	@OnOpen
	public void onOpen(Session session) {
		
	}
	
	@OnMessage
	public void onMessage(String jsonString, Session session) {
		
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		
	}
}

package com.example.websocket.channel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
	
	// 利用 Set + synchronized 來保存所有 session
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
	
	// 廣播
	private void broadcast(String sessionId, String message) {
		sessions.forEach(session -> {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(String.format("[ %02d ]說: %s", sessionId, message));
			}
		});
	}
	
	@OnOpen // 當客戶端與伺服器建立連線時觸發
	public void onOpen(Session session) {
		// 每一個新連線都會得到一個 session id
		sessions.add(session); // 將 session 加入到集合
		broadcast(session.getId(), "已連線");
	}
	
	@OnMessage // 當伺服端收到來自客戶端的消息時觸發
	public void onMessage(String jsonString, Session session) {
		broadcast(session.getId(), jsonString);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		sessions.remove(session); // 將 session 從集合中移出
		broadcast(session.getId(), "已離線");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		broadcast(session.getId(), "發生錯誤 ! " + throwable);
	}
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 會議室預訂系統(Web API)
 * 假設您正在為一家公司開發一個會議室預訂系統。您需要實現一個控制器，該控制器可以處理會議室的預訂請求、取消請求以及查詢當前預訂狀態。
 * 
 * 會議室資訊(Room)
	 +--------+-----------+------------+
	 | roomId | roomName  |  roomSize  |
	 +--------+-----------+------------+
	 |  101   |  101(S)   |     10     |
	 |  102   |  102(M)   |     25     |
	 +--------+-----------+------------+

	 * 預約紀錄(BookingRoom)
	 +-----------+--------+----------+-------------+
	 | bookingId | roomId | username | bookingDate |
	 +-----------+--------+----------+-------------+
	 |     1     |  101   |  Tom     | 2023-12-04  |
	 |     2     |  102   |  Mary    | 2023-12-05  |
	 |     3     |  102   |  Rose    | 2023-12-06  |
	 +-----------+--------+----------+-------------+
 * 
 * 
 * 功能要求：
 * -----------------------------------------------------------------------------------------------
 * 1.預訂會議室：
 * 路徑：/booking/bookRoom
 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
 * 返回：預訂成功(會得到預約號碼 bookingId)或失敗的消息
 * 預訂會議室:會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/booking/bookRoom?roomId=101&name=Tom&date=2023-12-04
 * 範例：http://localhost:8080/booking/bookRoom?roomId=102&name=Mary&date=2023-12-05
 * 範例：http://localhost:8080/booking/bookRoom?roomId=201&name=Jack&date=2023-12-06
 * 範例：http://localhost:8080/booking/bookRoom?roomId=403&name=Rose&date=2023-12-06
 * 
 * -----------------------------------------------------------------------------------------------
 * 2.取消預訂：
 * 路徑：/booking/cancelBooking/{bookingId}
 * 參數：預訂ID (bookingId)
 * 返回：取消成功或失敗的消息
 * 取消預訂:預約 bookingId
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/booking/cancelBooking/1
 * 
 * -----------------------------------------------------------------------------------------------
 * 3.查看所有預訂：
 * 路徑：/booking/viewBookings
 * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/booking/viewBookings
 * 
 * CR
 * 4.修改預約人
 * 路徑：/booking/{bookingId}/updateName
 * 返回：是否變更成功
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/booking/1/updateName?name=Tom
 * 範例：http://localhost:8080/booking/2/updateName?name=Helen
 * 
 * */

@Controller
@RequestMapping("/booking")
public class BookingController {
	// 建立會議室 
	private List<Room> rooms = List.of(
				new Room(101, "101(S)", 10),
				new Room(202, "202(M)", 25),
				new Room(303, "303(L)", 80)
			);
	
	// 查詢所有會議室
	// 路徑: /booking/rooms
	@GetMapping("/rooms")
	public String getRooms() {
		return rooms.toString();
	}
	
	
}

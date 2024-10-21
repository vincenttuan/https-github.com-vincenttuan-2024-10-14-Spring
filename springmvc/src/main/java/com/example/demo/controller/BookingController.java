package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
	private List<Room> rooms = new ArrayList<>(List.of(
				new Room(101, "101(S)", 10),
				new Room(202, "202(M)", 25),
				new Room(303, "303(L)", 80)
			));
	
	// 查詢所有會議室
	// 路徑: /rooms
	@GetMapping("/rooms")
	@ResponseBody
	public String getRooms() {
		return rooms.toString();
	}
	
	// 查詢單筆會議室 
	// 路徑: /rooms/1
	@GetMapping("/room/{roomId}")
	@ResponseBody
	public String getRoom(@PathVariable("roomId") Integer roomId) {
		Optional<Room> optRoom = rooms.stream()
				.filter(room -> room.getRoomId().equals(roomId))
				.findAny();
		if(optRoom.isPresent()) {
			Room room = optRoom.get();
			return room.toString();
		} else {
			return "Not found";
		}
	}
	
	// 新增會議室
	// 路徑: /room/add?roomId=404&roomName=404(S)&roomSize=10
	@GetMapping("/room/add")
	@ResponseBody
	public String addRoom(@RequestParam(name = "roomId", required = true) Integer roomId,
						  @RequestParam(name = "roomName", required = true) String roomName,
						  @RequestParam(name = "roomSize", required = true) Integer roomSize) {
		// 確認是否有已有此會議室
		Predicate<Room> roomIdFilter = room -> room.getRoomId().equals(roomId); // 過濾條件
		Optional<Room> optRoom = rooms.stream().filter(roomIdFilter).findAny();
		if(optRoom.isPresent()) {
			return "新增失敗: 會議室已存在";
		}
		// 新增會議室 
		Room newRoom = new Room(roomId, roomName, roomSize);
		rooms.add(newRoom);
		return "新增成功";
	}
	
	// 修改會議室
	// 路徑: /room/update/404?roomName=404(M)&roomSize=55
	@GetMapping("/room/update/{roomId}")
	@ResponseBody
	public String updateRoom(@PathVariable(name = "roomId") Integer roomId,
							 @RequestParam(name = "roomName", required = false) String roomName,
							 @RequestParam(name = "roomSize", required = false) Integer roomSize) {
		// 確認是否有已有此會議室
		Predicate<Room> roomIdFilter = room -> room.getRoomId().equals(roomId); // 過濾條件
		Optional<Room> optRoom = rooms.stream().filter(roomIdFilter).findAny();
		if(optRoom.isEmpty()) {
			return "修改失敗: 會議室不存在";
		}
		
		Room updateRoom = optRoom.get(); // 得到要修改的會議室物件 
		// 判斷是否 roomName 有資料進行修改
		if(roomName != null) {
			updateRoom.setRoomName(roomName); // 修改 roomName
		}
		
		// 判斷是否 roomSize 有資料進行修改
		if(roomName != null) {
			updateRoom.setRoomSize(roomSize); // 修改 roomSize
		}
		
		return "修改完畢";
	}
}









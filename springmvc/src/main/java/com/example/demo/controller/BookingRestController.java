package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.RoomAlreadyExistsException;
import com.example.demo.exception.RoomException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.RoomService;

/**
 * Rest API 設計
 * Method URI
 * ----------------------------------------------------------------
 * GET    /room/{roomId}                        查詢指定會議室(單筆)
 * GET    /rooms                                查詢所有會議室(多筆)
 * POST   /room           /room/add             新增會議室
 * PUT    /room/{roomId}  /room/update/{roomId} 完整修改會議室
 * PATCH  /room/{roomId}  /room/update/{roomId} 指定修改會議室(Homework)
 * DELETE /room/{roomId}  /room/delete/{roomId} 刪除會議室
 * 
 * */

@RestController
@RequestMapping("/booking/rest")
public class BookingRestController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/room/{roomId}")
	public ResponseEntity<ApiResponse<RoomDto>> getRoom(@PathVariable("roomId") Integer roomId) {
		RoomDto roomDto = roomService.getRoomById(roomId);
		return ResponseEntity.ok(ApiResponse.success("查詢成功", roomDto));
	}
	
	@GetMapping(value = {"/room", "/rooms"})
	public ResponseEntity<ApiResponse<List<RoomDto>>> getRooms() {
		List<RoomDto> roomDtos = roomService.getAllRooms();
		String message = roomDtos.isEmpty() ? "查無任何會議室" : "查詢所有會議室成功";
		return ResponseEntity.ok(ApiResponse.success(message, roomDtos));
	}
	
	@PostMapping(value = {"/room", "/room/add"})
	public ResponseEntity<ApiResponse<Void>> addRoom(@RequestBody RoomDto roomDto) {
		roomService.addRoom(roomDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("新增成功", null));
	}
	
	@PutMapping(value = {"/room/{roomId}", "/room/update/{roomId}"})
	public ResponseEntity<ApiResponse<Void>> updateRoom(@PathVariable Integer roomId, @RequestBody RoomDto roomDto) {
		roomService.updateRoom(roomId, roomDto);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("修改成功", null));
		//return ResponseEntity.ok(ApiResponse.success("修改成功", null));
	}
	
	@DeleteMapping(value = {"/room/{roomId}", "/room/delete/{roomId}"})
	public ResponseEntity<ApiResponse<Void>> deleteRoom(@PathVariable Integer roomId) {
		roomService.deleteRoom(roomId);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("刪除成功", null));
	}
	
	// 錯誤處理: 補捉 RoomNotFoundException, RoomAlreadyExistsException
	@ExceptionHandler({RoomNotFoundException.class, RoomAlreadyExistsException.class})
	public ResponseEntity<ApiResponse<Void>> handleException(RoomException e) {
		HttpStatus httpStatus = e instanceof RoomNotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT;
		return ResponseEntity.status(httpStatus).body(ApiResponse.error(httpStatus.value(), e.getMessage()));
	}
	
}

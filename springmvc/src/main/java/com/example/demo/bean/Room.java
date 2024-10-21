package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
	private Integer roomId;
	private String roomName;
	private Integer roomSize;
	
}

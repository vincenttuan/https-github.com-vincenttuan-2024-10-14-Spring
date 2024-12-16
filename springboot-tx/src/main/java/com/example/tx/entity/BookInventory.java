package com.example.tx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInventory {
	@Id
	private Integer bookId; // 書號
	@Column
	private Integer bookAmount; // 庫存數量
}

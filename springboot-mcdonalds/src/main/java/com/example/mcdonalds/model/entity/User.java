package com.example.mcdonalds.model.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "`user`")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@ManyToMany
	@JoinTable(
			name = "user_product", // 關聯表名稱
			joinColumns = @JoinColumn(name="user_id"), // 用戶 id 外鍵 
			inverseJoinColumns = @JoinColumn(name = "product_id") // 商品 id 外鍵
	)
	private Set<Product> favoriteProducts; // 關注的商品
	
	/*
     * hashCode 方法中出現了遞迴循環，通常是因為 User 和 Product 實體之間的雙向關聯造成的，Hibernate 無法處理這種循環依賴。
     * 所以要自行實現
     * */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

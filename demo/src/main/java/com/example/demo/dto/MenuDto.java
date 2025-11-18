package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data // Getter, Setterなどを自動生成
public class MenuDto {
	// クライアントに返したいフィールドを定義
    private Long id;
    private String name;
    private List<MenuItemDto> MenuLit;
}

package com.example.demo.dto;



import lombok.Data;

@Data // Getter, Setterなどを自動生成
public class MenuItemDto {
	// クライアントに返したいフィールドを定義
    private Long id;
    private String name;
    private String parentName;
    
    public MenuItemDto(Long id, String name, String parentName) {
    	this.id = id;
    	this.name = name;
    	this.parentName = parentName;
    	
    }
}

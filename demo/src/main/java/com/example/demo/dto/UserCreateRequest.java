package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data // Getter, Setterなどを自動生成
public class UserCreateRequest {
	// クライアントに返したいフィールドを定義
	private Long id;
	private String name;
	private LocalDateTime createdAt;
}
package com.example.demo.dto;

import lombok.Data;

@Data
public class HeroDto {
	private String name;
	private int hp, mp;
	
	// コンストラクタ
	public HeroDto(String name, int hp) {
		this.name = name;
		this.hp = hp;
	}
	
	// toString のオーバーライド
	public String toString() {
		return "勇者(名前" + this.name
				+ "/HP=" + this.hp + "/MP=" + this.mp + ")";
	}
}

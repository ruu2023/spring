package com.example.demo.controller;

//このクラスがJSONに変換されます
public class ApiResponse {

 private int id;
 private String message;

 // 1. コンストラクタ (データをセットするため)
 public ApiResponse(int id, String message) {
     this.id = id;
     this.message = message;
 }

 // 2. ゲッター (Spring BootがJSONに変換するために必要)
 public int getId() {
     return id;
 }

 public String getMessage() {
     return message;
 }

 // ゲッターさえあれば、セッター (setter) は無くても動作します
}
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * REST APIのエンドポイントを定義するコントローラー
 */
@RestController // 1. このクラスがREST APIのコントローラーであることを示します
public class DemoController {

    /**
     * "/hello" というURLへのGETリクエストに応答します
     * @return "Hello, Spring Boot API!" という文字列
     */
    @GetMapping("/hello") // 2. HTTP GETリクエストをこのメソッドにマッピング
    public String sayHello() {
        // 3. ブラウザやAPIクライアントに返すレスポンス
        return "Hello, Spring Boot API!";
    }
    
    /**
     * "/api/data" というURLへのGETリクエストに応答します
     * @return ApiResponse オブジェクト (これがJSONに変換されます)
     */
    @GetMapping("/api/data")
    public ApiResponse getJsonData() {
        // 1. 先ほど作成した ApiResponse クラスのインスタンスを作成
        ApiResponse response = new ApiResponse(1, "This is JSON data!");
        
        // 2. オブジェクトをそのまま return する
        return response;
    }
}

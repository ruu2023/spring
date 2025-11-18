package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // 2. HTTPステータスコード用
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MenuItemDto;
// 1. 必要なDTOとService、Annotationsをimport
import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;

@RestController // 3. このクラスがREST APIのコントローラーであることを示す
@RequestMapping("/api/users") // 4. このコントローラー内のAPIのベースURL
public class UserController {

    // 5. UserServiceを依存性注入(DI)
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * [GET] /api/users
     * ユーザー一覧を取得します。
     * @return ユーザーレスポンスDTOのリスト
     */
    @GetMapping // 6. HTTP GETリクエストをこのメソッドにマッピング
    public List<UserResponse> getAllUsers() {
        // 7. Serviceを呼び出し、DTOのリストをそのまま返す
        return userService.getAllUsers();
    }

    /**
     * [POST] /api/users
     * 新規ユーザーを作成します。
     * @param requestDto リクエストボディのJSON (例: {"name": "Taro"})
     * @return 作成されたユーザーのレスポンスDTO
     */
    @PostMapping // 8. HTTP POSTリクエストをこのメソッドにマッピング
    @ResponseStatus(HttpStatus.CREATED) // 9. 成功時、HTTPステータス 201 (Created) を返す
    public UserResponse createUser(@RequestBody UserCreateRequest requestDto) {
        // 10. @RequestBodyでJSONをDTOに変換し、Serviceに渡す
        return userService.createUser(requestDto);
    }
    
    @GetMapping("/test")
    public List<MenuItemDto> getData () {
    	return List.of(
    				new MenuItemDto(1L, "data")
    			);
    }
}
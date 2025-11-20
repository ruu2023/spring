package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // 2. HTTPステータスコード用
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MenuDto;
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
    
    
    private final Map<String, Object> menuMap = new HashMap<>();
    private List<String> uniqueParent = new ArrayList<>();
    @GetMapping("/test")
    public List<MenuDto> getData () {
    	List<MenuItemDto> menus = List.of(
    				new MenuItemDto(1L, "data1", "tab1"),
    				new MenuItemDto(2L, "data2", "tab1"),
    				new MenuItemDto(3L, "data3", "tab2"),
    				new MenuItemDto(4L, "data4", "tab2")
    			);

    	for(MenuItemDto menu : menus) {
    		if(uniqueParent.contains(menu.getParentName())) {  continue; }
    		uniqueParent.add(menu.getParentName());
    	}
    	
    	for(String parent : uniqueParent) {
    		List<Long> ids = menus.stream()
    				.filter(m->m.getParentName() == parent)
    				.map(m->m.getId())
    				.toList();
    		menuMap.put(parent, ids);
    	}
    	
    	List<MenuDto> menuList = new ArrayList<>();
    	Long id = 1L;
    	for(String parent : uniqueParent) {
    		List<Long> retrievedIds = (List<Long>) menuMap.get(parent);
    		List<MenuItemDto> foundMenu = menus.stream()
    				.filter(m->retrievedIds.contains( m.getId()))
    				.toList();
    		if(foundMenu == null) { continue; }
    		
    		menuList.add(new MenuDto(id, parent, foundMenu));
    		id++;
    				
    	}
    	
    	return menuList;
    			
    	
    }
}


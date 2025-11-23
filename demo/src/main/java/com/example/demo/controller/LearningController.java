package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ApiPath;
import com.example.demo.dto.HeroDto;
import com.example.demo.dto.MenuDto;
import com.example.demo.dto.MenuItemDto;

@RestController
public class LearningController {

	private final Map<String, Object> menuMap = new HashMap<>();
	private List<String> uniqueParent = new ArrayList<>();
	
    // Java 学習
    @GetMapping(ApiPath.V1.Learning.LIST)
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
    		@SuppressWarnings("unchecked")
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
    
    record Mapping(int id, String name) {
    	public String getName() {
    		return this.name;
    	}
    	
    	public static Mapping ofGuest() {
    		return new Mapping(999, "GUest");
    	}
    }
    
    @GetMapping(ApiPath.V1.Learning.NAMELIST)
    public List<String> getTestes () {
    	
    	List<Mapping> listData = List.of(
    			new Mapping(1, "カール"),
    			new Mapping(2, "マーカス"),
    			new Mapping(3, "コナー"),
    			Mapping.ofGuest()
    			);
    	
    	return listData.stream().map(m -> m.getName()).toList();
    }
    
    // スッキリJava 実践編2版
    @GetMapping(ApiPath.V1.Learning.KADAI1)
    public void get() {
    	String s1 = "スッキリJava";
    	String s2 = "Java";
    	String s3 = "java";
    	if(s2.equals(s3)) {
    		System.out.println("s2とs3は等しい");
    	}
    	if(s2.equalsIgnoreCase(s3)) {
    		System.out.println("s2とs3はケース区別しなければ等しい");
    	}
    	System.out.println("s1の長さは" + s1.length() + "です");
    	if(s1.isEmpty()) {
    		System.out.println("s1は空文字です");
    	}
    	System.out.println("Javaが最初に登場する位置は" + s1.indexOf(s3) + "です");
    	System.out.println("s1の2から4文字目は" + s1.substring(1,4) + "です");
    }
    

    // スッキリJava 実践編2版 ちょい重い
    @GetMapping(ApiPath.V1.Learning.KADAI2)
    public String get2() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < 100000; i++) {
    		sb.append("Java");
    	}
    	return sb.toString();
//    	String s = "Java";
//    	for(int i = 0; i < 100000; i++) {
//    		s = s + "Java";
//    	}
//    	return s;
    }
    

    // スッキリJava 実践編2版 パターンマッチ
    record Data(String name, Boolean match) {}
    @GetMapping(ApiPath.V1.Learning.KADAI3)
    public List<Data> isMatch() {
    	String s = "Java";    	
    	List<String> patternList = List.of(
    				"J.va",
    				"Ja*va",
    				".*",
    				"J..a",
    				"\\w*",
    				// false
    				"j..a",
    				"J.a"
    			);
    	
    	List<Data> patternMatchList = new ArrayList();
    	for(String pattern: patternList) {
    		patternMatchList.add(new Data(pattern, s.matches(pattern)));
    	}
    	
    	return patternMatchList;
    }
    

    // スッキリJava 実践編2版 パターンマッチ
    @GetMapping(ApiPath.V1.Learning.KADAI4)
    public List<String> useSplit() {
    	String delimiter = "|";
    	String[] array = "new|free".split(Pattern.quote(delimiter));
        return Arrays.asList(array);
    }
    
    
    // スッキリJava 実践編2版 フォーマット
    @GetMapping(ApiPath.V1.Learning.KADAI5)
    public List<String> kadai5() {
        return List.of(
        		String.format("・%2d日で%sわかる", 3, "スッキリ"),
        		String.format("・%2d日で%sわかる", 13, "少し")
        		);
    }
    
    // スッキリJava 実践編3版 オーバーライド
    @GetMapping(ApiPath.V1.Learning.THIRD1)
    public String third1() {
    	List<HeroDto> heroList = new ArrayList<>();
    	HeroDto hero = new HeroDto("みなと", 100);
    	heroList.add(hero);
    	hero = new HeroDto("みなと", 100);
    	heroList.remove(hero);
    	return "要素数=" + heroList.size();
    }
    
}

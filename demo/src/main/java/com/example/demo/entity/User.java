package com.example.demo.entity;

import java.time.LocalDateTime; // Java 8以降の標準の日時クラス

import jakarta.persistence.Column;
// 1. 必要なアノテーションをimportします
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data; // 4. (任意) Lombokを使う場合

/**
 * データベースのテーブル構造に対応するクラス (エンティティ)
 */
@Entity // 2. このクラスがJPAのエンティティであることを示す
@Table(name = "user") // 3. (推奨) 実際のテーブル名を指定 (指定しないとクラス名(User)が使われる)
@Data // 4. (任意) Lombok: Getter/Setter, toString
@EntityListeners(AuditingEntityListener.class)
public class User {

    /**
     * プライマリキー
     */
    @Id // 5. このフィールドがプライマリキー(ID)であることを示す
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 6. IDの自動生成戦略を指定
    private Long id;

    /**
     * 名前
     */
    private String name;
    
    @CreatedDate // 3. 作成日時を示すアノテーション
    @Column(nullable = false, updatable = false) // DB設定: null不可、更新不可
    private LocalDateTime createdAt; // 命名規則: Java(camelCase), DB(snake_case) ※

}
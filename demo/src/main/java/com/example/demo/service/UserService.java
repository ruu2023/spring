package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 1. 必要なクラスをimport
import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service // 2. このクラスがサービスコンポーネントであることを示す
public class UserService {

    // 3. UserRepositoryを依存性注入(DI)
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * ユーザーを新規作成します。
     * @param requestDto ユーザー作成リクエストDTO
     * @return 作成されたユーザーのレスポンスDTO
     */
    @Transactional // 4. データベースの変更を伴うため、トランザクションを有効化
    public UserResponse createUser(UserCreateRequest requestDto) {
        
        // 5. DTO (リクエスト) から Entity へ変換
        User user = new User();
        user.setName(requestDto.getName());
        
        // (createdAtはJPA監査機能により自動で設定される)

        // 6. Repositoryを呼び出して、Entityをデータベースに保存
        User savedUser = userRepository.save(user);

        // 7. 保存された Entity を DTO (レスポンス) に変換して返す
        return convertToResponseDto(savedUser);
    }

    /**
     * すべてのユーザーを取得します。
     * @return ユーザーレスポンスDTOのリスト
     */
    @Transactional(readOnly = true) // 読み取り専用トランザクション（パフォーマンス向上）
    public List<UserResponse> getAllUsers() {
        
        // 1. Repositoryを呼び出して、Entityのリストを取得
        List<User> users = userRepository.findAll();

        // 2. Entityのリスト を DTO(レスポンス)のリスト に変換
        return users.stream()
                .map(this::convertToResponseDto) // .map(user -> convertToResponseDto(user)) と同じ
                .collect(Collectors.toList());
    }

    // --- マッパー (変換用ヘルパーメソッド) ---
    
    /**
     * Entity (User) を DTO (UserResponse) に変換します。
     */
    private UserResponse convertToResponseDto(User entity) {
        UserResponse dto = new UserResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
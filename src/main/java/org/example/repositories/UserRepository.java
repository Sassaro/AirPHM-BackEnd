package org.example.repositories;

import org.example.Serializers.QueryUserFriends;
import org.example.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("""
        SELECT a.id as friendId,a.name as friendName,a.surname as friendSurname,a.country as friendCountry,a.imageURL as friendURLImage FROM UserEntity u
        INNER JOIN u.friendList a
        WHERE u.id = :userId
    """)
    List<QueryUserFriends> findUserFriendsByid(Long userId);
}

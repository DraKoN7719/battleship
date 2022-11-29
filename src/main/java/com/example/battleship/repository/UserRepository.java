package com.example.battleship.repository;

import com.example.battleship.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true,
        value = """
                       SELECT * FROM "user" WHERE login = :login
                """)
    Optional<User> getUserByLogin(@Param("login") String login);

    @Query(nativeQuery = true,
        value = """
                       SELECT * FROM "user" WHERE login = :login AND password = :password
                """)
    Optional<User> getUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}

package com.example.languappserver.repos;

import com.example.languappserver.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Репозторий - механизм взаимодействия с базой
// CrudRepository несёт в себе некоторые стандартные функции(Например, findById), которые не нужно потом прописывать
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.mail=:mail")
    User findUserById(@Param("mail") String mail);
}

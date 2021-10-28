package com.TestAssignment.Blogging_App.users.repository;

import com.TestAssignment.Blogging_App.users.entity.UsersEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    @NotFound(action= NotFoundAction.IGNORE)
    List<UsersEntity> findAll();

    @NotFound(action= NotFoundAction.IGNORE)
    UsersEntity findByOid(String oid);

    @NotFound(action= NotFoundAction.IGNORE)
    UsersEntity findByUserName(String userName);
}

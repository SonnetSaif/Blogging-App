package com.TestAssignment.Blogging_App.posts.repository;

import com.TestAssignment.Blogging_App.posts.entity.PostsEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, String> {
    @NotFound(action= NotFoundAction.IGNORE)
    List<PostsEntity> findAll();

    @NotFound(action= NotFoundAction.IGNORE)
    PostsEntity findByOid(String oid);
}

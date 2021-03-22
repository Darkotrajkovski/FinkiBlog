package com.darkotrajkovski.finkiblog.repository;

import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.model.PostsPerUser;
import com.darkotrajkovski.finkiblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PostsPerUserRepository extends JpaRepository<PostsPerUser, Long> {
    Optional<PostsPerUser> findByUser(User user);
    Optional<PostsPerUser> findByUserUsername(String username);
}

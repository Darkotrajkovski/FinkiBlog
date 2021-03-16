package com.darkotrajkovski.finkiblog.repository;

import com.darkotrajkovski.finkiblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

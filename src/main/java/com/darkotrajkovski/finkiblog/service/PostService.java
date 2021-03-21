package com.darkotrajkovski.finkiblog.service;
import com.darkotrajkovski.finkiblog.exceptions.PostNotFoundException;
import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.model.User;
import com.darkotrajkovski.finkiblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PostService {
    List<Post> listAllPosts();
    void createPost(String title, String content, String username);
    Optional<Post> findById(Long id);

}

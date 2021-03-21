package com.darkotrajkovski.finkiblog.service.impl;

import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.repository.PostRepository;
import com.darkotrajkovski.finkiblog.service.AuthService;
import com.darkotrajkovski.finkiblog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> listAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(String title, String content, String username) {
        Post post = new Post(title, content, username);
        postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

}
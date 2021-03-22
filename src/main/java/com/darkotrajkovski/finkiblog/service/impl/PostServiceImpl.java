package com.darkotrajkovski.finkiblog.service.impl;

import com.darkotrajkovski.finkiblog.exceptions.PostNotFoundException;
import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.repository.PostRepository;
import com.darkotrajkovski.finkiblog.service.AuthService;
import com.darkotrajkovski.finkiblog.service.PostService;
import com.darkotrajkovski.finkiblog.service.PostsPerUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostsPerUserService postsPerUserService;

    public PostServiceImpl(PostRepository postRepository, PostsPerUserService postsPerUserService) {
        this.postRepository = postRepository;
        this.postsPerUserService = postsPerUserService;
    }

    @Transactional
    public List<Post> listAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(String title, String content, String username) {
        Post post = new Post(title, content, username);
        postRepository.save(post);
        postsPerUserService.addPost(username, post);
    }

    @Override
    public void edit(Long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        post.setTitle(title);
        post.setContent(content);
        postsPerUserService.editPost(post.getUsername(), post);
        postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).get();
        postsPerUserService.deletePost(post.getUsername(), post);
        postRepository.deleteById(id);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

}
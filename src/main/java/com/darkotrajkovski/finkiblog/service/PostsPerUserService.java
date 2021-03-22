package com.darkotrajkovski.finkiblog.service;

import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.model.PostsPerUser;

import java.util.List;
import java.util.Optional;

public interface PostsPerUserService {
    List<Post> listAllPosts(String username);
    PostsPerUser addPost(String username, Post post);
    void editPost(String username, Post post);
    void deletePost(String username, Post post);
}

package com.darkotrajkovski.finkiblog.service.impl;

import com.darkotrajkovski.finkiblog.exceptions.PostNotFoundException;
import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.model.PostsPerUser;
import com.darkotrajkovski.finkiblog.model.User;
import com.darkotrajkovski.finkiblog.repository.PostsPerUserRepository;
import com.darkotrajkovski.finkiblog.repository.UserRepository;
import com.darkotrajkovski.finkiblog.service.PostService;
import com.darkotrajkovski.finkiblog.service.PostsPerUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PostsPerUserServiceImpl implements PostsPerUserService {

    private final PostsPerUserRepository postsPerUserRepository;
    private final UserRepository userRepository;

    public PostsPerUserServiceImpl(PostsPerUserRepository postsPerUserRepository, UserRepository userRepository) {
        this.postsPerUserRepository = postsPerUserRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<Post> listAllPosts(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return postsPerUserRepository.findByUser(user).get().getPosts();
    }

    @Transactional
    @Override
    public PostsPerUser addPost(String username, Post post) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        PostsPerUser postsPerUser = null;
        if(postsPerUserRepository.findByUser(user).isPresent()){
            postsPerUser = postsPerUserRepository.findByUser(user).get();
        } else{
            postsPerUser = new PostsPerUser(user);
        }
        postsPerUser.addPost(post);
        return postsPerUserRepository.save(postsPerUser);
    }

    @Transactional
    @Override
    public void editPost(String username, Post post) {
        List<Post> posts = listAllPosts(username);
        Post p = posts.stream().filter(i -> i.getTitle().equals(post.getTitle())).findFirst().orElseThrow(() -> new PostNotFoundException(post.getTitle()));
        PostsPerUser postsPerUser = postsPerUserRepository.findByUserUsername(username).orElseThrow(() -> new PostNotFoundException(post.getTitle()));

        postsPerUser.removePost(p);
        postsPerUser.addPost(post);
    }

    @Transactional
    @Override
    public void deletePost(String username, Post post) {
        PostsPerUser postsPerUser = postsPerUserRepository.findByUserUsername(username).orElseThrow(() -> new PostNotFoundException(post.getTitle()));
        postsPerUser.removePost(post);
    }
}

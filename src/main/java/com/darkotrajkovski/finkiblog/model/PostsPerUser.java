package com.darkotrajkovski.finkiblog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class PostsPerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Post> posts;

    public PostsPerUser(User user) {
        this.user = user;
        this.posts = new ArrayList<>();
    }

    public PostsPerUser() {
    }

    public void addPost(Post post){
        this.posts.add(post);
    }

    public void removePost(Post post){
        this.posts.remove(post);
    }
}

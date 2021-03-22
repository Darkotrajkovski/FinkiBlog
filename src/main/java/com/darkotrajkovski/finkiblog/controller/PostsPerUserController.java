package com.darkotrajkovski.finkiblog.controller;

import com.darkotrajkovski.finkiblog.model.PostsPerUser;
import com.darkotrajkovski.finkiblog.service.PostsPerUserService;
import com.darkotrajkovski.finkiblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/posts-per-user")
public class PostsPerUserController {

    private final PostsPerUserService postsPerUserService;
    private final UserService userService;

    public PostsPerUserController(PostsPerUserService postsPerUserService, UserService userService) {
        this.postsPerUserService = postsPerUserService;
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String getPostsPerUserPage(@PathVariable String username, Model model){
        model.addAttribute("posts", postsPerUserService.listAllPosts(username));
        model.addAttribute("bodyContent", "posts-per-user");
        return "master-template";
    }
}

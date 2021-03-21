package com.darkotrajkovski.finkiblog.controller;

import com.darkotrajkovski.finkiblog.model.Post;
import com.darkotrajkovski.finkiblog.model.User;
import com.darkotrajkovski.finkiblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String showAllPosts(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Post> posts = postService.listAllPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("bodyContent", "posts");
        return "master-template";
    }

    @GetMapping("/add-post")
    public String addPostPage(Model model){
        model.addAttribute("bodyContent", "add-post");
        return "master-template";
    }

    @PostMapping("/add")
    public String createPost(@RequestParam(required = false) Long id, HttpServletRequest request, @RequestParam String title, @RequestParam String mytextarea) {
        if(id != null){
            postService.edit(id, title, mytextarea);
        } else{
            String username = request.getRemoteUser();
            postService.createPost(title, mytextarea, username);
        }
        return "redirect:/posts";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        postService.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model){
        if(postService.findById(id).isPresent()){
            Post post = postService.findById(id).get();
            model.addAttribute("post", post);
            model.addAttribute("bodyContent", "add-post");
            return "master-template";
        }
        return "redirect:/posts?error=ProductNotFound";
    }

}

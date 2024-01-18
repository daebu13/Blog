package com.post.blog.controller;

import com.post.blog.dto.BlogDto;
import com.post.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto dto){
        BlogDto blogDto=blogService.createBlog(dto);
        return  new ResponseEntity<>(blogDto, HttpStatus.CREATED);
    }
    @GetMapping("/particular")
    public ResponseEntity<BlogDto> getBlogById(@RequestParam long id){
        BlogDto dto=blogService.getBlogById(id);
        return  new ResponseEntity<>(dto , HttpStatus.OK);
    }

    //http://localhost:8083/api/posts/pageNo=0&pageSize=3 ....> pagination url
    @GetMapping
    public List<BlogDto> getAllBlogs(@RequestParam(name = "pageNo",required = false,defaultValue = "0")int pageNo,
                                     @RequestParam(name = "pageSize",required = false,defaultValue = "3")int pageSize){
        List<BlogDto> allBlogs = blogService.getAllBlogs(pageNo, pageSize);
        return  allBlogs;
    }
}

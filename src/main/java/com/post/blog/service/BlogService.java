package com.post.blog.service;

import com.post.blog.dto.BlogDto;

import java.util.List;

public interface BlogService {


    BlogDto createBlog(BlogDto dto);

    BlogDto getBlogById(long id);

    List<BlogDto> getAllBlogs(int pageNo, int pageSize);
}

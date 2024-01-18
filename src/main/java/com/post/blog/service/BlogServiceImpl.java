package com.post.blog.service;

import com.post.blog.dto.BlogDto;
import com.post.blog.entity.Blog;
import com.post.blog.exception.ResourceNotFoundException;
import com.post.blog.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService{

    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto createBlog(BlogDto dto) {

        Blog blog = mapToEntity(dto);
        Blog savedBlog = blogRepository.save(blog);
        BlogDto blogDto = mapToDto(savedBlog);
        return blogDto;
    }

    @Override
    public BlogDto getBlogById(long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Blog not found with id " + id)
         );
        BlogDto blogDto = mapToDto(blog);

        return blogDto;
    }

    @Override
    public List<BlogDto> getAllBlogs(int pageNo, int pageSize) {
        Pageable pageable =PageRequest.of(pageNo,pageSize);
        Page<Blog> pageBlog = blogRepository.findAll(pageable);
        List<BlogDto> blogDto = pageBlog.stream().map(blog -> mapToDto(blog)).collect(Collectors.toList());

        return blogDto;
    }


    BlogDto mapToDto(Blog blog){
        BlogDto dto = new BlogDto();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setDescription(blog.getDescription());
        dto.setContent(blog.getContent());
        return  dto;
   }

    Blog mapToEntity(BlogDto dto){
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setDescription(dto.getDescription());
        blog.setContent(dto.getContent());
        return  blog;
   }
}

package com.blog.projeto.service;

import com.blog.projeto.model.PostBlog;

import java.util.List;

public interface BlogService {
    List<PostBlog> findAll();
    PostBlog findById(Long id);
    PostBlog save(PostBlog postBlog);
    void deletar(Long id);
}

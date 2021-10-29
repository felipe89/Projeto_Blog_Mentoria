package com.blog.projeto.repository;

import com.blog.projeto.model.PostBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<PostBlog, Long> {
}

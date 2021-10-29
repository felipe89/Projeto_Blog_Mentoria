package com.blog.projeto.service.serviceImpl;


import com.blog.projeto.model.PostBlog;
import com.blog.projeto.repository.BlogRepository;
import com.blog.projeto.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

        @Autowired
        BlogRepository blogRepository;

        @Override
        public List<PostBlog> findAll(){
            return  blogRepository.findAll();
        }

        @Override
        public PostBlog findById(Long id){
            return blogRepository.findById(id).get();
        }

        @Override
        public PostBlog save(PostBlog postBlog){
            return blogRepository.save(postBlog);
        }

        @Override
        public void deletar(Long id){
            blogRepository.deleteById(id);
        }

}


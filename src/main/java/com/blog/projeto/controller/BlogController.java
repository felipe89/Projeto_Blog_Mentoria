package com.blog.projeto.controller;


import com.blog.projeto.model.PostBlog;
import com.blog.projeto.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
public class BlogController {

    @Autowired
    BlogRepository repository;

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PostBlog> listarPost(){
        List<PostBlog> lista = repository.findAll();
       return lista;
    }

    @RequestMapping(value = "/novopost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String criarPost(@RequestBody PostBlog postBlog,
                            BindingResult resultado,
                            RedirectAttributes attributes)
    {
        if (resultado.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verique que todos os campos foram preenchidos corretamente!");
            return "redirect:/novopost";
        }
        postBlog.setData(LocalDate.now());
        repository.save(postBlog);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public Optional<PostBlog> buscarPorId(@PathVariable("id") Long id){
        return repository.findById(id);
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    public void atualizarPost(@PathVariable("id") Long id, @RequestBody PostBlog atualizarPost){
        repository.findById(id)
                  .map(atualiPostBlog ->{
                      atualiPostBlog.setTitulo(atualiPostBlog.getTitulo());
                      atualiPostBlog.setAutor(atualiPostBlog.getAutor());
                      atualiPostBlog.setData(atualiPostBlog.getData());
                      atualiPostBlog.setTexto(atualiPostBlog.getTexto());
                      atualiPostBlog.setHora(atualiPostBlog.getHora());
                      return repository.save(atualizarPost);
                  });
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPost(@PathVariable("id") Long id){
        repository.findById(id)
                  .map(postBlog -> {
                      repository.delete(postBlog);
                      return Void.TYPE;
                  });
    }
}

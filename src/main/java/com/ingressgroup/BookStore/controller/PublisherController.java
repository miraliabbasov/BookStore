package com.ingressgroup.BookStore.controller;


import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.dao.repository.PublisherRepository;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import com.ingressgroup.BookStore.model.dto.BookDto;
import com.ingressgroup.BookStore.model.dto.PublisherDto;
import com.ingressgroup.BookStore.model.dto.UserDto;
import com.ingressgroup.BookStore.service.BookService;
import com.ingressgroup.BookStore.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/publisher")
public class PublisherController {
    private final BookService bookService;
    private final PublisherService publisherService;


    @GetMapping("/signIn")
    public String  login(@RequestParam String email, @RequestParam String password){
        return publisherService.loginPublisher(email,password);
    }

    @PostMapping("/signUp")
    public void register(@RequestBody PublisherDto publisherDto){
        publisherService.registerUser(publisherDto);
    }

    @PostMapping("/add-book")
    public void addBook(@RequestBody BookCreate bookDto, @RequestParam String publisher){
        publisherService.addBook(bookDto,publisher);
    }

    @GetMapping("/books")
    public List<BookDto> sortedBySize( int page, int size, String field){
        return bookService.sortedByPage(page,size,field);
    }


    @GetMapping("/name-and-author")
    public List<BookCreate> getNameAndAuthorOfBook(){
        return bookService.getNameAndAuthorOfBook();
    }

    @GetMapping("/listbooks")
    public List<String> getNameOfBooks(){
        return bookService.getBook();
    }

    @PutMapping("/update")
    public void updateBook(@RequestParam String name ,@RequestParam String author,@RequestParam String publisher){
        bookService.update(name,author,publisher);
    }

    @GetMapping("/ownbooks")
    public List<BookCreate> getBookWithPublisher(String publisher){
       return bookService.getBookWithPublisher(publisher);
    }
}

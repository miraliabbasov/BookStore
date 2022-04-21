package com.ingressgroup.BookStore.controller;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import com.ingressgroup.BookStore.model.dto.BookDto;
import com.ingressgroup.BookStore.model.dto.UserDto;
import com.ingressgroup.BookStore.service.BookService;
import com.ingressgroup.BookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    private final BookService bookService;
    private final UserService userService;


    @GetMapping("/login")
    public String  login(@RequestParam String email, @RequestParam String password){
       return userService.loginUser(email,password);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto){
        userService.registerUser(userDto);
    }



    @GetMapping("/books_name")
    public List<String> getNameOfBooks(){
        return bookService.getBook();
    }


    @GetMapping("/book_publisher")
    public List<BookCreate> getBookWithPublisher(@RequestParam String publisher){
       return bookService.getBookWithPublisher(publisher);
    }

    @GetMapping("/books")
    public Page<BookEntity> sortedBySize(@RequestParam int size, @RequestParam int page){
        return bookService.sortedByPage(page,size);
    }


    @GetMapping("/name_and_author")
    public List<BookCreate> getNameAndAuthorOfBook(){
        return bookService.getNameAndAuthorOfBook();
    }

}

package com.ingressgroup.BookStore.service;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.dao.repository.BookRepository;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import com.ingressgroup.BookStore.model.dto.BookDto;
import com.ingressgroup.BookStore.model.map.BookMap;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;


    public List<String> getBook(){
        return bookRepository.findAllNameOfBook();
    }


    public List<BookCreate> getBookWithPublisher(String publisher){
        var bookEntity = bookRepository.findByPublisher(publisher);

        return BookMap.INSTANCE.entityToBookCreates(bookEntity);
    }

    public Page<BookEntity> sortedByPage(int page, int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        var entity = bookRepository.findAll(pageRequest);
        return entity;
    }

    public List<BookCreate> getNameAndAuthorOfBook(){
            var entity = bookRepository.findAll();
            return BookMap.INSTANCE.entityToBookCreates(entity);
    }

    public void update(String name ,String author, String publisher){
        var entity = bookRepository.findByNameAndPublisher(name,publisher);
        entity.setAuthor(author);
        bookRepository.save(entity);
    }

}

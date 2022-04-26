package com.ingressgroup.BookStore.service;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.dao.repository.BookRepository;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import com.ingressgroup.BookStore.model.dto.BookDto;
import com.ingressgroup.BookStore.model.dto.PageableDto;
import com.ingressgroup.BookStore.model.map.BookMap;
import com.ingressgroup.BookStore.utill.BookStoreCriteria;
import com.ingressgroup.BookStore.utill.BookStoreSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
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

    public List<BookDto> sortedByPage(int page, int size,String field){
        PageRequest pageRequest =   PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,field));
        var entity = bookRepository.findAll(pageRequest).toList();
        return BookMap.INSTANCE.entityToDtos(entity) ;
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

    public PageableDto getBooksFilterByName(BookStoreCriteria bookStoreCriteria, Integer page, Integer count){

         Pageable pageRequest =  PageRequest.of(page,count);
        var specification = new BookStoreSpecification(bookStoreCriteria);


        Page<BookEntity> books = bookRepository.findAll(specification, pageRequest);
        List<BookEntity> content = books.getContent();
        var bookPageCounts = books.getTotalPages();

        return PageableDto.builder()
                .bookDtos(BookMap.INSTANCE.entityToDtos(content))
                .lastPage(bookPageCounts)
                .hasNext(books.hasNext())
                .build();
    }

}

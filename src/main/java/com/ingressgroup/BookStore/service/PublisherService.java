package com.ingressgroup.BookStore.service;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.dao.repository.BookRepository;
import com.ingressgroup.BookStore.dao.repository.PublisherRepository;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import com.ingressgroup.BookStore.model.dto.BookDto;
import com.ingressgroup.BookStore.model.dto.PublisherDto;
import com.ingressgroup.BookStore.model.dto.UserDto;
import com.ingressgroup.BookStore.model.exception.NotFoundException;
import com.ingressgroup.BookStore.model.map.BookMap;
import com.ingressgroup.BookStore.model.map.PublisherMap;
import com.ingressgroup.BookStore.model.map.UserMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public String loginPublisher(String email, String password) {
        var entity = publisherRepository.findByEmailAndPassword(email, password)
                .orElseThrow(
                        () -> new NotFoundException("PUBLISHER_NOT_FOUND", "404")
                );
        return "Hello  " + entity.getName();
    }


    public void addBook(BookCreate bookDto, String publisher) {
        var entity =BookMap.INSTANCE.bookRequestToEntity(bookDto);
        entity.setPublisher(publisher);
        bookRepository.save(entity);
    }

    public void registerUser(PublisherDto publisherDto){
//        if (userDto.getEmail().substring(userDto.getEmail().charAt('@'),userDto.getEmail().length()-1).equals("gmail.com")){
//            userRepository.save(UserMap.INSTANCE.dtoToEntity(userDto));
//        }else {
//            throw new NotFoundException("EMAIL_NOT_VALID","403");
//        }
        publisherRepository.save(PublisherMap.INSTANCE.dtoToEntity(publisherDto));

    }

}

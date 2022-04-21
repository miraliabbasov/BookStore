package com.ingressgroup.BookStore.service;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.dao.repository.BookRepository;
import com.ingressgroup.BookStore.dao.repository.UserRepository;
import com.ingressgroup.BookStore.model.dto.BookDto;
import com.ingressgroup.BookStore.model.dto.UserDto;
import com.ingressgroup.BookStore.model.exception.NotFoundException;
import com.ingressgroup.BookStore.model.map.BookMap;
import com.ingressgroup.BookStore.model.map.UserMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public String  loginUser(String email,String password){
        var entity= userRepository.findByEmailAndPassword(email,password)
                .orElseThrow(
                        ()->new NotFoundException("USER_NOT_FOUND","404")
                );
        return "Hello  "+entity.getName()+" "+entity.getSurname();
    }

    public void registerUser(UserDto userDto){
//        if (userDto.getEmail().substring(userDto.getEmail().charAt('@'),userDto.getEmail().length()-1).equals("gmail.com")){
//            userRepository.save(UserMap.INSTANCE.dtoToEntity(userDto));
//        }else {
//            throw new NotFoundException("EMAIL_NOT_VALID","403");
//        }
        userRepository.save(UserMap.INSTANCE.dtoToEntity(userDto));

    }




}

package com.ingressgroup.BookStore.dao.repository;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import com.ingressgroup.BookStore.model.dto.BookCreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long>, JpaSpecificationExecutor<BookEntity> {


    List<BookEntity> findByPublisher(String publisher);

    @Query(value = "select  b.name from BookEntity b")
     List<String> findAllNameOfBook ();

    @Query(value = "select b.name, b.author from BookEntity b")
    List<BookCreate> findNameAndAuthorOfBook();

    BookEntity findByNameAndPublisher( String author, String publisher);



}

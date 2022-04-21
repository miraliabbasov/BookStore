package com.ingressgroup.BookStore.dao.repository;

import com.ingressgroup.BookStore.dao.entity.PublisherEntity;
import com.ingressgroup.BookStore.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity,Long> {

    Optional<PublisherEntity> findByEmailAndPassword(String email, String password);

}

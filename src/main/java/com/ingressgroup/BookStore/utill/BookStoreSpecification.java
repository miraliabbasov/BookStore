package com.ingressgroup.BookStore.utill;

import com.ingressgroup.BookStore.dao.entity.BookEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BookStoreSpecification implements Specification<BookEntity> {

    private BookStoreCriteria bookStoreCriteria;

    public BookStoreSpecification(BookStoreCriteria bookStoreCriteria) {
        this.bookStoreCriteria = bookStoreCriteria;

    }


    @Override
    public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
            if(bookStoreCriteria!= null){
                if (bookStoreCriteria.getName() != null){
                    predicates.add(criteriaBuilder.like(root.get("name"),
                            "%" + bookStoreCriteria.getName() + "%"));
                }
            }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}

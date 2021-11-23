package com.disneychallenge.library.repository;

import com.disneychallenge.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    boolean existsByTitle(String name);

}

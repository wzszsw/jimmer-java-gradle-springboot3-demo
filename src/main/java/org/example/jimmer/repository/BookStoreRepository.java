package org.example.jimmer.repository;

import org.babyfish.jimmer.spring.repository.JRepository;
import org.example.jimmer.entity.BookStore;


public interface BookStoreRepository extends JRepository<BookStore, Long> { // ❶
}

/*----------------Documentation Links----------------
❶ https://babyfish-ct.github.io/jimmer/docs/spring/repository/concept
---------------------------------------------------*/

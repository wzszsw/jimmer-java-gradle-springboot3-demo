package org.example.jimmer.repository;

import org.babyfish.jimmer.spring.repository.JRepository;
import org.babyfish.jimmer.spring.repository.SpringOrders;
import org.babyfish.jimmer.sql.ast.query.specification.JSpecification;

import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.example.jimmer.entity.Author;
import org.example.jimmer.entity.AuthorTable;
import org.example.jimmer.entity.Tables;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.example.jimmer.entity.Tables.AUTHOR_TABLE;

public interface AuthorRepository extends JRepository<Author, Long>, Tables {

    AuthorTable table = AUTHOR_TABLE;

    default List<Author> find(
            JSpecification<?, AuthorTable> specification,
            Sort sort,
            Fetcher<Author> fetcher
    ) {
        return sql().createQuery(table)
                .where(specification)
                .orderBy(SpringOrders.toOrders(table, sort))
                .select(table.fetch(fetcher))
                .execute();
    }
}

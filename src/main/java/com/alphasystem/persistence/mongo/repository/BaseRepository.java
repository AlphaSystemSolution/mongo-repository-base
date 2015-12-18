/**
 *
 */
package com.alphasystem.persistence.mongo.repository;

import com.alphasystem.persistence.model.AbstractDocument;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author sali
 */
@NoRepositoryBean
public interface BaseRepository<T extends AbstractDocument> extends PagingAndSortingRepository<T, String>,
        QueryDslPredicateExecutor<T> {

    /**
     * @param displayName
     * @return
     */
    public T findByDisplayName(String displayName);

}

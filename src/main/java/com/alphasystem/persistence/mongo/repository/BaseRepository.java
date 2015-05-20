/**
 * 
 */
package com.alphasystem.persistence.mongo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alphasystem.persistence.mongo.model.AbstractDocument;


/**
 * @author sali
 * 
 */
@NoRepositoryBean
public interface BaseRepository<T extends AbstractDocument> extends
		PagingAndSortingRepository<T, String> {

	/**
	 * @param displayName
	 * @return
	 */
	public T findByDisplayName(String displayName);

}

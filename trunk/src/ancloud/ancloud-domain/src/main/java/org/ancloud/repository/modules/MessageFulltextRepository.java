package org.ancloud.repository.modules;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ancloud.domain.modules.message.Message;
import org.apache.lucene.search.Query;
import org.hibernate.Criteria;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MessageFulltextRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public Page<Message> search(String text,Pageable pageable) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
									.buildQueryBuilder()
									.forEntity(Message.class).get();

		Query query = queryBuilder.keyword()
							.onFields("message","messageKey","basename","language","country")
							.matching(text)
							.createQuery();

		// wrap Lucene query in an Hibernate Query object
		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Message.class);
		
		Integer totalElements = jpaQuery.getResultSize();
		
		jpaQuery.setFirstResult(pageable.getPageNumber()*pageable.getPageSize());
		jpaQuery.setMaxResults((pageable.getPageNumber()+1)*pageable.getPageSize()-1);
		
		
		PageImpl<Message> page = new PageImpl<Message>(jpaQuery.getResultList(),pageable,totalElements);
		return page;
	} 
}

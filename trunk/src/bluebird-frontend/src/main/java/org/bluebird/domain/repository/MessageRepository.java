package org.bluebird.domain.repository;

import java.util.List;

import org.bluebird.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public class MessageRepository implements JpaRepository<Message, Integer> {

	@Override
	public Page<Message> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Message> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message findOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Message> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll(Sort paramSort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll(Iterable<Integer> paramIterable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Message> List<S> save(Iterable<S> paramIterable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Message> S saveAndFlush(S paramS) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Message> paramIterable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message getOne(Integer paramID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

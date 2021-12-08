package com.sts.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sts.test.model.TT0001Model;

@Repository
public interface TT0001Repository extends MongoRepository<TT0001Model, String> {
	public TT0001Model findById(Integer id);
	public void deleteById(Integer id);
}

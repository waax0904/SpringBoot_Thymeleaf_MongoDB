package com.sts.test.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.sts.test.dao.TT0001Repository;
import com.sts.test.model.TT0001Model;

@Service
@Configurable
public class TT0001Service {
	@Autowired
	private TT0001Repository repository;

	public List<TT0001Model> getAllTT0001() {
		List<TT0001Model> list_ = this.repository.findAll();
		
		Collections.sort(list_ , new Comparator<TT0001Model>() {
  		  @Override
  		  public int compare(TT0001Model tt1, TT0001Model tt2) {
  		    return tt1.getId().compareTo(tt2.getId());
  		  }
  		});
		
		return list_;
	}
}

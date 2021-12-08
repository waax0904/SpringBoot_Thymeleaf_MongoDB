package com.sts.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sts.test.model.TT0001Model;
import com.sts.test.repository.TT0001Repository;

@Service
public class TT0001Service {

	@Autowired
	TT0001Repository repository;

	public TT0001Model get_ (Integer id) {
	    return repository.findById(id);
	}
	
	public List<TT0001Model> getAll_ (){
		return repository.findAll(Sort.by(Sort.Direction.ASC, "_id"));
	}

	@Transactional
	public void add_(TT0001Model req) {
		TT0001Model s = get_(0);
		int seq_id = s.getSeq();

	    TT0001Model vo = new TT0001Model();
	    vo.setId(seq_id+1);
	    vo.setItem(req.getItem());
	    vo.setQty(req.getQty());

	    seq_();

	    repository.insert(vo);
	}

	@Transactional
	public TT0001Model update_(TT0001Model req) {
	    TT0001Model vo = new TT0001Model();
	    vo.setId(req.getId().intValue());
	    vo.setItem(req.getItem());
	    vo.setQty(req.getQty());

	    return repository.save(vo);
	}

	@Transactional
	public void delete_(Integer id) {
	    repository.deleteById(id);
	}

	@Transactional
	public void seq_() {
		TT0001Model seqVo = get_(0);
		seqVo.setSeq(seqVo.getSeq()+1);
		repository.save(seqVo);
	}
}

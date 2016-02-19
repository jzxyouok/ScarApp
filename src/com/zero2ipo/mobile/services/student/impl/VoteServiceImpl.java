package com.zero2ipo.mobile.services.student.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.impl.Vote;
import com.zero2ipo.common.entity.Student;
import com.zero2ipo.common.entity.UserVote;
import com.zero2ipo.mobile.dao.student.IStudentDao;
import com.zero2ipo.mobile.dao.student.IVoteDao;
import com.zero2ipo.mobile.services.student.IStudentService;
import com.zero2ipo.mobile.services.student.IVoteService;

@Component("voteService")
public class VoteServiceImpl implements IVoteService{

	@Autowired
	protected IVoteDao voteDao ;
	@Override
	public int add(UserVote bo) {
		return voteDao.add(bo);
	}

	@Override
	public List<UserVote> findAllList(Map<String, Object> queryMap) {
		return voteDao.findAllList(queryMap);
	}

	@Override
	public UserVote findById(Map<String, Object> queryMap) {
		return voteDao.findById(queryMap);
	}
	@Override
	public boolean update(UserVote student) {
		return voteDao.update(student);
	}
	
}


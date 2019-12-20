package com.p.boot.interview.mgmt.repository.jdbc;

import java.util.List;

import com.p.boot.interview.mgmt.pojo.QuestionDTO;
import com.p.boot.interview.mgmt.repository.QuestionRepository;

public class JdbcQuestionRepository implements QuestionRepository {

	@Override
	public boolean keyExists(QuestionDTO objQuestionDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int generateNextsrno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(QuestionDTO objQuestionDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(QuestionDTO objQuestionDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionDTO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] batchInsert(List<QuestionDTO> objQuestionDTOs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] batchInsert(List<QuestionDTO> objQuestionDTOs, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] batchUpdate(List<QuestionDTO> objQuestionDTOs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] batchUpdate(List<QuestionDTO> objQuestionDTOs, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

}

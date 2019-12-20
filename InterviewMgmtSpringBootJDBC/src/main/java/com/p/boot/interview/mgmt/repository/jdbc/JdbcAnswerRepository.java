package com.p.boot.interview.mgmt.repository.jdbc;

import java.util.List;

import com.p.boot.interview.mgmt.pojo.AnswerDTO;
import com.p.boot.interview.mgmt.repository.AnswerRepository;

public class JdbcAnswerRepository implements AnswerRepository {

	@Override
	public boolean keyExists(AnswerDTO objAnswerDTO) {
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
	public int save(AnswerDTO book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AnswerDTO book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnswerDTO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] batchInsert(List<AnswerDTO> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] batchInsert(List<AnswerDTO> books, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] batchUpdate(List<AnswerDTO> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] batchUpdate(List<AnswerDTO> books, int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

}

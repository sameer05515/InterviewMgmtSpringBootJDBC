package com.p.boot.interview.mgmt.repository;

import java.util.List;

import com.p.boot.interview.mgmt.pojo.QuestionDTO;

public interface QuestionRepository {

	boolean keyExists(QuestionDTO objQuestionDTO);

	int generateNextsrno();

	///

	int count();

	int save(QuestionDTO book);

	int update(QuestionDTO book);

	String deleteById(int id);

	List<QuestionDTO> findAll();

	// List<QuestionDTO> findByNameAndPrice(String name, BigDecimal price);

	QuestionDTO findById(int id);

	// String findNameById(int id);

	int[] batchInsert(List<QuestionDTO> books);

	int[][] batchInsert(List<QuestionDTO> books, int batchSize);

	int[] batchUpdate(List<QuestionDTO> books);

	int[][] batchUpdate(List<QuestionDTO> books, int batchSize);

	// void saveImage(Long bookId, File image);

	// List<Map<String, InputStream>> findImageByBookId(Long bookId);
}

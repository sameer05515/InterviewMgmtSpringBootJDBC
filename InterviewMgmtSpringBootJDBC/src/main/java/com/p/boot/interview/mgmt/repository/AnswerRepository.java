package com.p.boot.interview.mgmt.repository;

import java.util.List;

import com.p.boot.interview.mgmt.pojo.AnswerDTO;

public interface AnswerRepository {

	boolean keyExists(AnswerDTO objAnswerDTO);

	int generateNextsrno();

	///

	int count();

	int save(AnswerDTO objAnswerDTO);

	int update(AnswerDTO objAnswerDTO);

	String deleteById(int id);

	List<AnswerDTO> findAll();

	// List<AnswerDTO> findByNameAndPrice(String name, BigDecimal price);

	AnswerDTO findById(int id);

	// String findNameById(int id);

	int[] batchInsert(List<AnswerDTO> objAnswerDTOs);

	int[][] batchInsert(List<AnswerDTO> objAnswerDTOs, int batchSize);

	int[] batchUpdate(List<AnswerDTO> objAnswerDTOs);

	int[][] batchUpdate(List<AnswerDTO> objAnswerDTOs, int batchSize);

	// void saveImage(Long objAnswerDTOId, File image);

	// List<Map<String, InputStream>> findImageByobjAnswerDTOId(Long objAnswerDTOId);
}

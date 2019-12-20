package com.p.boot.interview.mgmt.repository;

import java.util.List;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;

public interface CategoryRepository {

	boolean keyExists(CategoryDTO objCategoryDTO);

	int generateNextsrno();

	///

	int count();

	int save(CategoryDTO book);

	int update(CategoryDTO book);

	String deleteById(int id);

	List<CategoryDTO> findAll();

	// List<CategoryDTO> findByNameAndPrice(String name, BigDecimal price);

	CategoryDTO findById(int id);

	// String findNameById(int id);

	int[] batchInsert(List<CategoryDTO> books);

	int[][] batchInsert(List<CategoryDTO> books, int batchSize);

	int[] batchUpdate(List<CategoryDTO> books);

	int[][] batchUpdate(List<CategoryDTO> books, int batchSize);

	// void saveImage(Long bookId, File image);

	// List<Map<String, InputStream>> findImageByBookId(Long bookId);

}

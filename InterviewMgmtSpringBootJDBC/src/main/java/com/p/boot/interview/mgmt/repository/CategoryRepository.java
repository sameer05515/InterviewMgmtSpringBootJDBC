package com.p.boot.interview.mgmt.repository;



import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;

public interface CategoryRepository {
	
	
	boolean keyExists(CategoryDTO objCategoryDTO);
	
	int generateNextsrno();
	
	///

    int count();

    int save(CategoryDTO book);

    int update(CategoryDTO book);

    int deleteById(Long id);

    List<CategoryDTO> findAll();

    List<CategoryDTO> findByNameAndPrice(String name, BigDecimal price);

    Optional<CategoryDTO> findById(Long id);

    String findNameById(Long id);

    int[] batchInsert(List<CategoryDTO> books);

    int[][] batchInsert(List<CategoryDTO> books, int batchSize);

    int[] batchUpdate(List<CategoryDTO> books);

    int[][] batchUpdate(List<CategoryDTO> books, int batchSize);

    void saveImage(Long bookId, File image);

    List<Map<String, InputStream>> findImageByBookId(Long bookId);

}

package com.p.boot.interview.mgmt.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;

@Repository
public class JdbcCategoryRepository implements CategoryRepository {

	@Override
	public final boolean keyExists(CategoryDTO objCategoryDTO) {

		int i = jdbcTemplate.queryForObject(
				"select cat_id,cat_name,creation_date,last_updation_date,rating " + "from t_category where cat_id=?",
				new Object[] { objCategoryDTO.getCatID() }, Integer.class);

		return i > 0;
	}

	@Override
	public final synchronized int generateNextsrno() {

		int maxId = jdbcTemplate.queryForObject("select max(cat_id) from t_category", Integer.class);

		return maxId;
	}

	////////////////

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	LobHandler lobHandler;

	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from t_category", Integer.class);
	}

	@Override
	public int save(CategoryDTO objCategoryDTO) {

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

		return jdbcTemplate.update(
				"insert into t_category (cat_id,cat_name,creation_date,last_updation_date,rating) values (?,?,?,?,?)",
				generateNextsrno(), objCategoryDTO.getCatgoryName(), date, date, objCategoryDTO.getRating());
	}

	@Override
	public int update(CategoryDTO book) {
		return jdbcTemplate.update("update books set price = ? where id = ?", book.getCatgoryName(), book.getCatID());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update("delete books where id = ?", id);
	}

	@Override
	public List<CategoryDTO> findAll() {
		return jdbcTemplate.query("select * from books", (rs, rowNum) -> new CategoryDTO(
		// rs.getLong("id"),
		// rs.getString("name"),
		// rs.getBigDecimal("price")
		));
	}

	// jdbcTemplate.queryForObject, populates a single object
	@Override
	public Optional<CategoryDTO> findById(Long id) {
		return jdbcTemplate.queryForObject("select * from books where id = ?", new Object[] { id },
				(rs, rowNum) -> Optional.of(new CategoryDTO(
				// rs.getLong("id"),
				// rs.getString("name"),
				// rs.getBigDecimal("price")
				)));
	}

	@Override
	public List<CategoryDTO> findByNameAndPrice(String name, BigDecimal price) {
		return jdbcTemplate.query("select * from books where name like ? and price <= ?",
				new Object[] { "%" + name + "%", price }, (rs, rowNum) -> new CategoryDTO(
				// rs.getLong("id"),
				// rs.getString("name"),
				// rs.getBigDecimal("price")
				));
	}

	@Override
	public String findNameById(Long id) {
		return jdbcTemplate.queryForObject("select name from books where id = ?", new Object[] { id }, String.class);
	}

	@Override
	public int[] batchUpdate(List<CategoryDTO> books) {

		return this.jdbcTemplate.batchUpdate("update books set price = ? where id = ?",
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// ps.setBigDecimal(1, books.get(i).getPrice());
						// ps.setLong(2, books.get(i).getId());
					}

					public int getBatchSize() {
						return books.size();
					}

				});

	}

	@Override
	public int[][] batchUpdate(List<CategoryDTO> books, int batchSize) {

		int[][] updateCounts = jdbcTemplate.batchUpdate("update books set price = ? where id = ?", books, batchSize,
				new ParameterizedPreparedStatementSetter<CategoryDTO>() {
					public void setValues(PreparedStatement ps, CategoryDTO argument) throws SQLException {
						// ps.setBigDecimal(1, argument.getPrice());
						// ps.setLong(2, argument.getId());
					}
				});
		return updateCounts;

	}

	@Override
	public int[] batchInsert(List<CategoryDTO> books) {

		return this.jdbcTemplate.batchUpdate("insert into books (name, price) values(?,?)",
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// ps.setString(1, books.get(i).getName());
						// ps.setBigDecimal(2, books.get(i).getPrice());
					}

					public int getBatchSize() {
						return books.size();
					}

				});
	}

	// Any failure causes the entire operation to roll back, none of the book will
	// be added
	@Transactional
	@Override
	public int[][] batchInsert(List<CategoryDTO> books, int batchSize) {

		int[][] updateCounts = jdbcTemplate.batchUpdate("insert into books (name, price) values(?,?)", books, batchSize,
				new ParameterizedPreparedStatementSetter<CategoryDTO>() {
					public void setValues(PreparedStatement ps, CategoryDTO argument) throws SQLException {
						// ps.setString(1, argument.getName());
						// ps.setBigDecimal(2, argument.getPrice());
					}
				});
		return updateCounts;

	}

	// https://www.postgresql.org/docs/7.3/jdbc-binary-data.html
	// https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-lob
	@Override
	public void saveImage(Long bookId, File image) {

		try (InputStream imageInStream = new FileInputStream(image)) {

			jdbcTemplate.execute("INSERT INTO book_image (book_id, filename, blob_image) VALUES (?, ?, ?)",
					new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
						protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
							ps.setLong(1, 1L);
							ps.setString(2, image.getName());
							lobCreator.setBlobAsBinaryStream(ps, 3, imageInStream, (int) image.length());
						}
					});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Map<String, InputStream>> findImageByBookId(Long bookId) {

		List<Map<String, InputStream>> result = jdbcTemplate.query(
				"select id, book_id, filename, blob_image from book_image where book_id = ?", new Object[] { bookId },
				new RowMapper<Map<String, InputStream>>() {
					public Map<String, InputStream> mapRow(ResultSet rs, int i) throws SQLException {

						String fileName = rs.getString("filename");
						InputStream blob_image_stream = lobHandler.getBlobAsBinaryStream(rs, "blob_image");

						// byte array
						// Map<String, Object> results = new HashMap<>();
						// byte[] blobBytes = lobHandler.getBlobAsBytes(rs, "blob_image");
						// results.put("BLOB", blobBytes);

						Map<String, InputStream> results = new HashMap<>();
						results.put(fileName, blob_image_stream);

						return results;

					}
				});

		return result;
	}

}
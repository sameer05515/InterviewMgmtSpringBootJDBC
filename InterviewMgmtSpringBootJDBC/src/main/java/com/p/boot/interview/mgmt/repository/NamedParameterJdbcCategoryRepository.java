package com.p.boot.interview.mgmt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJdbcCategoryRepository extends JdbcCategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int update(CategoryDTO book) {
		return namedParameterJdbcTemplate.update("update books set price = :price where id = :id",
				new BeanPropertySqlParameterSource(book));
	}

	@Override
	public CategoryDTO findById(int id) {
		return namedParameterJdbcTemplate.queryForObject("select * from t_category where cat_id = :id",
				new MapSqlParameterSource("id", id), new RowMapper<CategoryDTO>() {

					@Override
					public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						CategoryDTO objCategoryDTO = new CategoryDTO();
						objCategoryDTO.setCatID(rs.getInt("cat_id"));
						objCategoryDTO.setCatgoryName(rs.getString("cat_name"));

						java.sql.Timestamp timestamp = rs.getTimestamp("creation_date");
						objCategoryDTO.setDateCreated(Date.from(timestamp.toInstant()));
						timestamp = rs.getTimestamp("last_updation_date");
						objCategoryDTO.setDateLastModified(Date.from(timestamp.toInstant()));

						objCategoryDTO.setRating(rs.getInt("rating"));

						return objCategoryDTO;
					}

				});
	}

	@Override
	public List<CategoryDTO> findByNameAndPrice(String name, BigDecimal price) {

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("name", "%" + name + "%");
		mapSqlParameterSource.addValue("price", price);

		return namedParameterJdbcTemplate.query("select * from books where name like :name and price <= :price",
				mapSqlParameterSource, (rs, rowNum) -> new CategoryDTO(
				// rs.getLong("id"),
				// rs.getString("name"),
				// rs.getBigDecimal("price")
				));
	}

}

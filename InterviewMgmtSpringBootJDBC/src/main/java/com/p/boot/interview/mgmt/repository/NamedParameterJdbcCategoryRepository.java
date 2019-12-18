package com.p.boot.interview.mgmt.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.p.boot.interview.mgmt.pojo.CategoryDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJdbcCategoryRepository extends JdbcCategoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int update(CategoryDTO book) {
        return namedParameterJdbcTemplate.update(
                "update books set price = :price where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from books where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        Optional.of(new CategoryDTO(
//                                rs.getLong("id"),
//                                rs.getString("name"),
//                                rs.getBigDecimal("price")
                        ))
        );
    }

    @Override
    public List<CategoryDTO> findByNameAndPrice(String name, BigDecimal price) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", "%" + name + "%");
        mapSqlParameterSource.addValue("price", price);

        return namedParameterJdbcTemplate.query(
                "select * from books where name like :name and price <= :price",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new CategoryDTO(
//                                rs.getLong("id"),
//                                rs.getString("name"),
//                                rs.getBigDecimal("price")
                        )
        );
    }

}

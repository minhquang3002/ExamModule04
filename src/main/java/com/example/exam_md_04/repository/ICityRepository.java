package com.example.exam_md_04.repository;

import com.example.exam_md_04.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends CrudRepository<City, Long> {
}

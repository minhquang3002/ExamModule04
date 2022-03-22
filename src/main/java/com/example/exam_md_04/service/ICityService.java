package com.example.exam_md_04.service;

import com.example.exam_md_04.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> findAll();

    Optional<City> findOne(Long id);

    City save(City city);

    void delete(Long id);
}

package com.example.exam_md_04.service;

import com.example.exam_md_04.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAll();

    Optional<Country> findOne(Long id);
}

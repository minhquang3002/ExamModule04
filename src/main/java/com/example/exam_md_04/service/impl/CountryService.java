package com.example.exam_md_04.service.impl;

import com.example.exam_md_04.model.Country;
import com.example.exam_md_04.repository.ICountryRepository;
import com.example.exam_md_04.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    public Iterable<Country> findAll() {
        return iCountryRepository.findAll();
    }

    @Override
    public Optional<Country> findOne(Long id) {
        return iCountryRepository.findById(id);
    }
}

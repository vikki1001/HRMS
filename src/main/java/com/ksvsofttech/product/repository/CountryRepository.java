package com.ksvsofttech.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.CountryModel;

@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Integer>{

}

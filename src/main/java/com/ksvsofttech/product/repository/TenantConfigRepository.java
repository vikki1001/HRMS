package com.ksvsofttech.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.TenantConfig;

@Repository
public interface TenantConfigRepository extends JpaRepository<TenantConfig, Integer> {

}

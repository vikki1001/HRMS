package com.ksvsofttech.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.PasswordPolicy;

@Repository
public interface PasswordPolicyRepository extends JpaRepository<PasswordPolicy, Long> {

}

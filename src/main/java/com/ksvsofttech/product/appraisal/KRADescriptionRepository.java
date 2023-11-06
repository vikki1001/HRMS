package com.ksvsofttech.product.appraisal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface KRADescriptionRepository extends JpaRepository<KRADescription, Long> {


}

package com.wecodee.myntra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecodee.myntra.model.MtPayment;

@Repository
public interface MtPaymentRepository extends JpaRepository<MtPayment, String> {

}

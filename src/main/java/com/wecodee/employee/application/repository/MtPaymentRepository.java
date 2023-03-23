package com.wecodee.employee.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecodee.employee.application.model.MtPayment;

@Repository
public interface MtPaymentRepository extends JpaRepository<MtPayment, String> {

}

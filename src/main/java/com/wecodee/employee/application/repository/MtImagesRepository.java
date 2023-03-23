package com.wecodee.employee.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecodee.employee.application.model.MtImages;

@Repository
public interface MtImagesRepository extends JpaRepository<MtImages, Integer> {

}

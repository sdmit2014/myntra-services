package com.wecodee.myntra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wecodee.myntra.model.MtImages;

@Repository
public interface MtImagesRepository extends JpaRepository<MtImages, Integer> {

}

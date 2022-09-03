package com.Dona.ilService.repository;


import com.Dona.ilService.model.il;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ilRepository extends JpaRepository<il, String> {
    List<il> findAllByName(String name);
    Optional<il> findByName(String name);

}

package com.sisvita_grupo12.test_service.repository;

import com.sisvita_grupo12.test_service.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer>{
    List<Test> findByIdPaciente(Integer id);

}

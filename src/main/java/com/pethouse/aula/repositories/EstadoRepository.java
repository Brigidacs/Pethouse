package com.pethouse.aula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pethouse.aula.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
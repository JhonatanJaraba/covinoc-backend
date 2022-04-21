package com.example.pruebacovinoc.repository;

import com.example.pruebacovinoc.entity.Rol;
import com.example.pruebacovinoc.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}

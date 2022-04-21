package com.example.pruebacovinoc.repository;

import com.example.pruebacovinoc.entity.UpdateUsuario;
import com.example.pruebacovinoc.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UpdateUsuarioRepository extends JpaRepository<UpdateUsuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}

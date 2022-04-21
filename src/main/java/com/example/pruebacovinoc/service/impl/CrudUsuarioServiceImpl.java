package com.example.pruebacovinoc.service.impl;

import com.example.pruebacovinoc.entity.Rol;
import com.example.pruebacovinoc.entity.UpdateUsuario;
import com.example.pruebacovinoc.entity.Usuario;
import com.example.pruebacovinoc.enums.RolNombre;
import com.example.pruebacovinoc.exceptions.CovinocException;
import com.example.pruebacovinoc.exceptions.InternalServerErrorException;
import com.example.pruebacovinoc.exceptions.NotFountException;
import com.example.pruebacovinoc.json.UpdateUsuarioRest;
import com.example.pruebacovinoc.json.UserByIdRest;
import com.example.pruebacovinoc.json.UsuarioRest;
import com.example.pruebacovinoc.repository.RolRepository;
import com.example.pruebacovinoc.repository.UpdateUsuarioRepository;
import com.example.pruebacovinoc.repository.UsuarioRepository;
import com.example.pruebacovinoc.service.CrudUsuarioService;
import com.example.pruebacovinoc.service.RolService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrudUsuarioServiceImpl implements CrudUsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudUsuarioServiceImpl.class);

    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolService rolRepository;

    @Autowired
    UpdateUsuarioRepository updateUsuarioRepository;

    @Override
    public List<UsuarioRest> getUsuarios() throws CovinocException {
        final List<Usuario> usuariosentity = usuarioRepository.findAll();
        return usuariosentity.stream().map(service -> modelMapper.map(service, UsuarioRest.class))
                .collect(Collectors.toList());
    }

    @Override
    public String delete_usuarios(int id) throws CovinocException {

        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(() -> new NotFountException("NOT_FOUND_USUARIO", "NOT_FOUND_USUARIO"));

        try {
            usuarioRepository.deleteById(id);
        }catch (Exception e){
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        return "Se ha eliminado correctamente";
    }

    @Override
    public String actualizar_usuarios(UpdateUsuarioRest usuarioRest) throws CovinocException {

        UpdateUsuario usuarios = updateUsuarioRepository.findById(usuarioRest.getId())
                .orElseThrow(() -> new NotFountException("USUARIO_NOT_FOUND","USUARIO_NOT_FOUND"));

        UpdateUsuario usuario;
        if(usuarios.getNombreUsuario().equals(usuarioRest.getNombreUsuario())){
            usuario = modelMapper.map(usuarioRest, UpdateUsuario.class);
        }else{
            final ResponseEntity error = new ResponseEntity(HttpStatus.BAD_REQUEST);
            if(usuarioRepository.existsByNombreUsuario(usuarioRest.getNombreUsuario()))
                throw new InternalServerErrorException(("Este nombre usuario ya existe").concat(error.getStatusCode().toString()),
                        ("Este nombre usuario ya existe").concat(error.getStatusCode().toString()));

            usuario = modelMapper.map(usuarioRest, UpdateUsuario.class);
        }

        final Set<Rol> roles = new HashSet<>();
        if(usuarioRest.getRoles().contains("user"))
        roles.add(rolRepository.getByRolNombre(RolNombre.ROLE_USER).get());
        if(usuarioRest.getRoles().contains("admin"))
            roles.add(rolRepository.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        try {
            usuario.setRoles(roles);
            updateUsuarioRepository.save(usuario);
        }catch (final Exception e){
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "Se ha actualizado correctamente";

    }

    @Override
    public UserByIdRest Usuarios_id(int id) throws CovinocException {
        return modelMapper.map(getUsuarioByIds(id), UserByIdRest.class);
    }

    public UpdateUsuario getUsuarioByIds(int id) throws CovinocException {
        return updateUsuarioRepository.findById(id)
                .orElseThrow(() -> new NotFountException("SNOT-404-1", "USUARIO_NOT_FOUND"));
    }

}

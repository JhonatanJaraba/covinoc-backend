package com.example.pruebacovinoc.service;

import com.example.pruebacovinoc.exceptions.CovinocException;
import com.example.pruebacovinoc.json.UpdateUsuarioRest;
import com.example.pruebacovinoc.json.UserByIdRest;
import com.example.pruebacovinoc.json.UsuarioRest;

import java.util.List;

public interface CrudUsuarioService {

    public List<UsuarioRest> getUsuarios() throws CovinocException;

    public String delete_usuarios(int id) throws CovinocException;

    public String actualizar_usuarios(UpdateUsuarioRest usuarioRest) throws CovinocException;

    public UserByIdRest Usuarios_id(int id) throws CovinocException;
}

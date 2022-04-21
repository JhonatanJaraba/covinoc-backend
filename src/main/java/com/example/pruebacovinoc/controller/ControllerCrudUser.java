package com.example.pruebacovinoc.controller;

import com.example.pruebacovinoc.exceptions.CovinocException;
import com.example.pruebacovinoc.json.UpdateUsuarioRest;
import com.example.pruebacovinoc.json.UserByIdRest;
import com.example.pruebacovinoc.json.UsuarioRest;
import com.example.pruebacovinoc.response.CovinocResponse;
import com.example.pruebacovinoc.service.impl.CrudUsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class ControllerCrudUser {

    @Autowired
    CrudUsuarioServiceImpl crudUsuarioServiceImpl;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getusuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CovinocResponse<List<UsuarioRest>> Listar_usuarios() throws CovinocException {
        return new CovinocResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", crudUsuarioServiceImpl.getUsuarios());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteusuario", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CovinocResponse<String> deleteUsuario(@RequestParam int id) throws CovinocException {
        return new CovinocResponse<>("Succes", String.valueOf(HttpStatus.NO_CONTENT), "OK",
                crudUsuarioServiceImpl.delete_usuarios(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "update_usuario", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public CovinocResponse<String> updateUsuario(@RequestBody @Valid UpdateUsuarioRest updateUsuarioRest)
            throws CovinocException {
        return new CovinocResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK"
                ,crudUsuarioServiceImpl.actualizar_usuarios(updateUsuarioRest));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "user" + "/{" + "id"
            + "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CovinocResponse<UserByIdRest> getUserById(@PathVariable int id) throws CovinocException {
        return new CovinocResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                crudUsuarioServiceImpl.Usuarios_id(id));
    }
}

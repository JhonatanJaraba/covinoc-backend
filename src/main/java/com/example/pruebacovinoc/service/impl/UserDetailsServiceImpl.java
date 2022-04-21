package com.example.pruebacovinoc.service.impl;

import com.example.pruebacovinoc.entity.Usuario;
import com.example.pruebacovinoc.entity.UsuarioPrincipal;
import com.example.pruebacovinoc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreOrEmail) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.getByNombreUsuarioOrEmail(nombreOrEmail).get();
        return UsuarioPrincipal.build(usuario);
    }
}

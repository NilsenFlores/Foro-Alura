package com.nilsen.ForoHub.controller;

import com.nilsen.ForoHub.domain.Usuarios.DatosAutenticarUsuario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    ResponseEntity autenticarUsuario(DatosAutenticarUsuario datos){
        Authentication token = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
        manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}

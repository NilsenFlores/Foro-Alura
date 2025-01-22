package com.nilsen.ForoHub.controller;

import com.nilsen.ForoHub.domain.Usuarios.DatosAutenticarUsuario;
import com.nilsen.ForoHub.domain.Usuarios.Usuario;
import com.nilsen.ForoHub.infra.DatosJWT;
import com.nilsen.ForoHub.infra.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticarUsuario datos){
        Authentication token = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
        var usuarioAutenticado = manager.authenticate(token);
        var tokenJWT = tokenService.generaToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWT(tokenJWT));
    }
}

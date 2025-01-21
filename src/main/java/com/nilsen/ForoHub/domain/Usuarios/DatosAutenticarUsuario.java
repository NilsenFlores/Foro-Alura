package com.nilsen.ForoHub.domain.Usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticarUsuario(

        @NotBlank
        String login,

        @NotBlank
        String clave) {
}

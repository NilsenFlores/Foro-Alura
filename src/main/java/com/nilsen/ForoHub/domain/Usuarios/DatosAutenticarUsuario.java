package com.nilsen.ForoHub.domain.Usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticarUsuario(String login, String clave) {
}

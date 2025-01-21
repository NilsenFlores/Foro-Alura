package com.nilsen.ForoHub.domain.Usuarios;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;
    String clave;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getLogin() {
        return login;
    }
}

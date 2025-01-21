package com.nilsen.ForoHub.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Topicos")
/*
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor*/

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String autor;
    private String curso;


    public Topico(DatosRegistroTopico datos) {
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.mensaje = datos.mensaje();
        this.titulo = datos.titulo();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;
    }

    public Topico() {
    }

    public void actualizar(@Valid DatosTopicoActualizados datos) {
        if(datos.autor() != null){
            this.autor = datos.autor();
        }

        if(datos.status() != null){
            this.status = datos.status();
        }

        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }

        if(datos.curso() != null){
            this.curso = datos.curso();
        }

        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Status getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }


}

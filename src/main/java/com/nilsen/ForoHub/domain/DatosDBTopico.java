package com.nilsen.ForoHub.domain;

import java.time.LocalDateTime;

public record DatosDBTopico(
        String titulo, String mensaje, String autor,
        Status status, String curso, LocalDateTime fechaCreacion)
{
    public DatosDBTopico(Topico topico){
        this(topico.getTitulo(),topico.getMensaje(),topico.getAutor(),topico.getStatus(),topico.getCurso(),topico.getFechaCreacion());
    }
}

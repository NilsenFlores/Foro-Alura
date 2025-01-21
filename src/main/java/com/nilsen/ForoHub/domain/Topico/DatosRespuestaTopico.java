package com.nilsen.ForoHub.domain.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, String autor,
                                   Status status, String curso, LocalDateTime fechaCreacion) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getAutor(),topico.getStatus(),topico.getCurso(),topico.getFechaCreacion());
    }
}

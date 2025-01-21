package com.nilsen.ForoHub.domain.Topico;


public record DatosTopicoActualizados(


        String autor,
        String titulo,
        String mensaje,
        Status status,
        String curso

) {
}

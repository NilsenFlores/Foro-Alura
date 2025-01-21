package com.nilsen.ForoHub.domain.Topico;


import com.nilsen.ForoHub.domain.Status;

public record DatosTopicoActualizados(


        String autor,
        String titulo,
        String mensaje,
        Status status,
        String curso

) {
}

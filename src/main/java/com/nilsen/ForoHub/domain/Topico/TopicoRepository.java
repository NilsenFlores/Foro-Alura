package com.nilsen.ForoHub.domain.Topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    boolean existeTopicoConTituloYMensaje(String titulo, String mensaje);


    Topico findByid(Long id);

    void deleteById(Long Id);

}

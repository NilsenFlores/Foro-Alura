package com.nilsen.ForoHub.controller;

import com.nilsen.ForoHub.domain.DatosDBTopico;
import com.nilsen.ForoHub.domain.DatosRegistroTopico;
import com.nilsen.ForoHub.domain.Topico;
import com.nilsen.ForoHub.domain.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tópicos")
public class topicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
        if(!repository.existeTopicoConTituloYMensaje(datos.titulo(), datos.mensaje())){
            repository.save(new Topico(datos));
            System.out.println("Topico registrado");
        }else {
            System.out.println("Topico repetido");
        }
    }

    @GetMapping
    public ResponseEntity<List<DatosDBTopico>> MostrarTodosTopicos(){
        var body = repository.findAll().stream()
                .map(DatosDBTopico::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDBTopico> MostrarUnicoTopidoId(@PathVariable Long id){

        Topico topico = repository.findByid(id);
        DatosDBTopico topicoMostrado = new DatosDBTopico(topico);
        return ResponseEntity.ok(topicoMostrado);
    }
}

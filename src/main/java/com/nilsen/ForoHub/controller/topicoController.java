package com.nilsen.ForoHub.controller;

import com.nilsen.ForoHub.domain.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/tópicos")
public class topicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        if(!repository.existeTopicoConTituloYMensaje(datos.titulo(), datos.mensaje())){
            Topico topico = new Topico(datos);
            repository.save(topico);
            DatosRespuestaTopico datosRT = new DatosRespuestaTopico(topico);
            URI url = uriComponentsBuilder.path("/tópicos/{id}")
                    .buildAndExpand(topico.getId())
                    .toUri();

            return ResponseEntity.created(url).body(datosRT);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tópico duplicado");
        }
    }

    @GetMapping
    public ResponseEntity<List<DatosDBTopico>> MostrarTodosTopicos(){
        var body = repository.findAll().stream()
                .map(DatosDBTopico::new)
                .collect(Collectors.toList());
        if(body.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDBTopico> MostrarUnicoTopidoId(@PathVariable Long id){
        Topico topico = repository.findByid(id);
        if(topico == null){
            return ResponseEntity.notFound().build();
        }
        DatosDBTopico topicoMostrado = new DatosDBTopico(topico);
        return ResponseEntity.ok(topicoMostrado);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosTopicoActualizados datos, @PathVariable Long id){

        if(!repository.existeTopicoConTituloYMensaje(datos.titulo(), datos.mensaje())){
            Topico topico = repository.findByid(id);
            if(topico == null){
                return ResponseEntity.notFound().build();
            }
            topico.actualizar(datos);
            return ResponseEntity.ok(new DatosDBTopico(topico));
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No es posible actualizar, debido a que ya exite otro tópico con el mismo título-mensaje");
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = repository.findByid(id);
        if(topico != null){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}

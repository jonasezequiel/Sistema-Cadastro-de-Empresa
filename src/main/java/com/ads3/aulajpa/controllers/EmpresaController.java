package com.ads3.aulajpa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads3.aulajpa.entities.Empresa;
import com.ads3.aulajpa.repository.EmpresaRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    EmpresaRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(Empresa empresa) {

        repository.save(empresa);

        return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK);

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Empresa>> listar() {
        List<Empresa> empresas = new ArrayList<>();
        empresas = repository.findAll();

        return new ResponseEntity<>(empresas, HttpStatus.OK);

    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable("id") int id) {

        Empresa empresa = repository.findById(id).orElse(null);

        if (empresa != null) {
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        }

        return new ResponseEntity<>(empresa, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") int id) {
        Empresa empresa = repository.findById(id).orElse(null);

        if (empresa != null) {
            repository.delete(empresa);
            return new ResponseEntity<>("Tabela excluída", HttpStatus.OK);
        }

        return new ResponseEntity<>("Registro não encontrado", HttpStatus.NOT_FOUND);

    }

    /// ReponseEntity retorna respostas ou mensagens
    // findByid busca

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(Empresa newEmpresa, @PathVariable("id") int id) {
        Empresa empresa = repository.findById(id).orElse(null);

        empresa.setNome(newEmpresa.getNome());
        empresa.setCnpj(newEmpresa.getCnpj());

        repository.save(empresa);

        return new ResponseEntity<>("Editado com sucesso", HttpStatus.OK);

    }
}
package com.benoni.spring.interview.demo.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.services.IClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ClientesController {

    @Inject
    private IClienteService clienteService;

    @RequestMapping(value = "/clientes", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> getClientes(@RequestParam(value = "nome", required = false) String nome) {
        List<Cliente> clientes = clienteService.consultarClienteByNome(nome);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    ResponseEntity<Cliente> insertCliente(@RequestBody @Valid Cliente body) {
        Cliente ClienteSalva = clienteService.cadastrarCliente(body);
        return new ResponseEntity<>(ClienteSalva, HttpStatus.OK);
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.PUT)
    ResponseEntity<Cliente> updateCliente(@RequestBody @Valid Cliente body, @PathVariable(value = "id") Integer id) {
        body.setId(id);
        Cliente ClienteSalva = clienteService.updateCliente(body);
        return new ResponseEntity<>(ClienteSalva, HttpStatus.OK);
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> updateCliente(@PathVariable(value = "id") Integer id) {
        clienteService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    ResponseEntity<Cliente> getCliente(@PathVariable(value = "id") Integer id) {
        Cliente cliente = clienteService.getById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}

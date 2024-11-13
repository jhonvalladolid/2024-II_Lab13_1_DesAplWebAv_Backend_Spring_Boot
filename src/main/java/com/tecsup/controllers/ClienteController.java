package com.tecsup.controllers;

import com.tecsup.exceptions.ResourceNotFoundException;
import com.tecsup.models.ClienteModel;
import com.tecsup.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")

public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<ClienteModel> ListarClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    public ClienteModel guardarCliente(@RequestBody ClienteModel cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteModel> ListarClientePorId(@PathVariable long id){
        ClienteModel cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("El cliente no existe"+ id));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteModel> ActualizarCliente(@PathVariable long id, @RequestBody ClienteModel clienteRequest){
        ClienteModel cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("El cliente no existe"+ id));

        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellidos(clienteRequest.getApellidos());
        cliente.setEmail(clienteRequest.getEmail());

        ClienteModel clienteActualizado= clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarCliente(@PathVariable long id){
        ClienteModel cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("El cliente no existe"+ id));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

package com.tecsup.controllers;

import com.tecsup.models.ClienteModel;
import com.tecsup.services.ClienteService;
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
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<ClienteModel> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteModel> obtenerClientePorId(@PathVariable Long id) {
        ClienteModel cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes")
    public ClienteModel guardarCliente(@RequestBody ClienteModel cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteModel> actualizarCliente(@PathVariable Long id, @RequestBody ClienteModel clienteDetalles) {
        ClienteModel clienteActualizado = clienteService.actualizarCliente(id, clienteDetalles);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

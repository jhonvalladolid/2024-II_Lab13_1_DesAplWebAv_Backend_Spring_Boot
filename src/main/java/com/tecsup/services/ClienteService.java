package com.tecsup.services;

import com.tecsup.exceptions.ResourceNotFoundException;
import com.tecsup.models.ClienteModel;
import com.tecsup.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public ClienteModel obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    public ClienteModel guardarCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public ClienteModel actualizarCliente(Long id, ClienteModel clienteDetalles) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));

        cliente.setNombre(clienteDetalles.getNombre());
        cliente.setApellidos(clienteDetalles.getApellidos());
        cliente.setEmail(clienteDetalles.getEmail());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        clienteRepository.delete(cliente);
    }
}

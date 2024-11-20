package com.tecsup.services;

import com.tecsup.exceptions.ResourceNotFoundException;
import com.tecsup.models.EmpleadoModel;
import com.tecsup.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<EmpleadoModel> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    public EmpleadoModel obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
    }

    public EmpleadoModel guardarEmpleado(EmpleadoModel empleado) {
        return empleadoRepository.save(empleado);
    }

    public EmpleadoModel actualizarEmpleado(Long id, EmpleadoModel empleadoDetalles) {
        EmpleadoModel empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        empleado.setNombre(empleadoDetalles.getNombre());
        empleado.setApellidos(empleadoDetalles.getApellidos());
        empleado.setEmail(empleadoDetalles.getEmail());
        empleado.setTelefono(empleadoDetalles.getTelefono());
        empleado.setCargo(empleadoDetalles.getCargo());
        empleado.setFechaContratacion(empleadoDetalles.getFechaContratacion());
        empleado.setSalario(empleadoDetalles.getSalario());

        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(Long id) {
        EmpleadoModel empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        empleadoRepository.delete(empleado);
    }
}

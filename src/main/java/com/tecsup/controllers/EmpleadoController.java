package com.tecsup.controllers;

import com.tecsup.models.EmpleadoModel;
import com.tecsup.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<EmpleadoModel> obtenerTodosLosEmpleados() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<EmpleadoModel> obtenerEmpleadoPorId(@PathVariable Long id) {
        EmpleadoModel empleado = empleadoService.obtenerEmpleadoPorId(id);
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/empleados")
    public EmpleadoModel guardarEmpleado(@RequestBody EmpleadoModel empleado) {
        return empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<EmpleadoModel> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoModel empleadoDetalles) {
        EmpleadoModel empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDetalles);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

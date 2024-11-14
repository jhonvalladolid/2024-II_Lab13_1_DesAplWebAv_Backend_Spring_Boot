package com.tecsup.controllers;

import com.tecsup.models.ProductoModel;
import com.tecsup.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public List<ProductoModel> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoModel> obtenerProductoPorId(@PathVariable Long id) {
        ProductoModel producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping("/productos")
    public ProductoModel guardarProducto(@RequestBody ProductoModel producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(@PathVariable Long id, @RequestBody ProductoModel productoDetalles) {
        ProductoModel productoActualizado = productoService.actualizarProducto(id, productoDetalles);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

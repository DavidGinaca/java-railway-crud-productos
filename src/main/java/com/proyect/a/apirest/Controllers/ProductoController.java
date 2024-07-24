package com.proyect.a.apirest.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.a.apirest.Repositories.ProductoRepositoy;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.proyect.a.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositoy productoRepository;

    @GetMapping
    public List<Producto> getProductos() {

        return productoRepository.findAll() ;
    }
    
    @GetMapping("{id}")
    public Producto getProductoById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("no se encontro el producto"));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {        
        
        return productoRepository.save(producto);
    }



   @PutMapping("{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detalleProducto) {
        
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("no se encontro el producto"));


        producto.setName(detalleProducto.getName());
        producto.setPrice(detalleProducto.getPrice());
        producto.setDescription(detalleProducto.getDescription());

        return productoRepository.save(producto);
    }
    
    @DeleteMapping("{id}")
    public String deleteProducto(@PathVariable Long id){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("no se encontro el producto"));
        
        productoRepository.delete(producto);

        return "El producto fue eliminado de los registros";
    }
    
}
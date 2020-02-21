package com.aplicacionfacturas.aplFacturas.controller;

import java.util.NoSuchElementException;

import com.aplicacionfacturas.aplFacturas.model.Producto;
import com.aplicacionfacturas.aplFacturas.model.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * FacturasController
 */
@RestController

@ResponseBody@RequestMapping("/api") //http://localhost:8080/api

public class FacturasController {

    @Autowired
    private ProductoRepository pr;

    @GetMapping("/")
    public Iterable<Producto> getAllProductos() {
        return pr.findAll(); // Devolvemos objetos del tipo Producto
    }

    @GetMapping("/producto") // http://localhost:8080/api/producto
    @ResponseBody
    public String insertarproducto(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return "Guardado correctamente";
    }

   

    @PostMapping("/producto")//http://localhost:8080/api/producto
    @ResponseBody
    public ModelAndView insproductoHTMLPost(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        ModelAndView modelAndView = new ModelAndView("producto");
        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return modelAndView;
    }

    @GetMapping("/contar")//http://localhost:8080/api/contar
    @ResponseBody
    public String Contar() {

        return "Tienes:  " + pr.count() + " añadidos.";
    }

    @GetMapping("/insertar")//http://localhost:8080/api/insertar
    public ModelAndView InsertarHTML() {
        ModelAndView modelAndView = new ModelAndView("Insertar");
        modelAndView.addObject("mensaje", "Añadido correctamente");

        return modelAndView;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/insertar")//http://localhost:8080/api/insertar

    public ModelAndView InsertarHTMLPost(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        ModelAndView modelAndView = new ModelAndView("insertar");

        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return modelAndView;
    }

    @GetMapping("/{id}/")//http://localhost:8080/api/{id}/

    public Producto GetProducto(@PathVariable("id") Long id) {
        Producto product;
        try {
            product = pr.findById(id).get();
        } catch (NoSuchElementException e) {
            e = new NoSuchElementException("El producto no existe: " + id);
            System.out.println(e.getMessage());

            return null;
        }
        return product;
    }

    @PutMapping("/{id}/")
    public Producto modificarProducto(
        @PathVariable("id") Long id, 
        @RequestBody Producto producto){
        Producto aModif = pr.findById(id).get();
        aModif.setDescripcion(producto.getDescripcion());
        aModif.setFabricante(producto.getFabricante());
        aModif.setPrecio(producto.getPrecio());
        return pr.save(aModif);
    }

    @PatchMapping("/{id}/")
    public Producto modificarProductoAnyadeEuros(
        @PathVariable("id") Long id,
        @RequestBody Producto producto){
            Producto aModif = pr.findById(id).get();
            aModif.setPrecio(aModif.getPrecio() + producto.getPrecio());
            return pr.save(aModif);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity eliminarProducto(@PathVariable("id") Long id){
        try{
            pr.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

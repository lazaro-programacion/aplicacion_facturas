package com.aplicacionfacturas.aplFacturas.controller;

import java.util.NoSuchElementException;



import com.aplicacionfacturas.aplFacturas.model.Producto;
import com.aplicacionfacturas.aplFacturas.model.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * FacturasController
 */
@RestController
public class FacturasController {

    @Autowired
    private ProductoRepository pr;

    @GetMapping("/")
    public Iterable<Producto> getAllProductos() {
        return pr.findAll(); // Devolvemos objetos del tipo Producto
    }

    @GetMapping("/insproducto")
    @ResponseBody
    public String insertarproducto(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return "Guardado correctamente";
    }

    @GetMapping("/contar")
    @ResponseBody
    public String Contar() {

        return "Tienes:  " + pr.count() + " añadidos.";
    }

    @PostMapping("/insproducto")
    @ResponseBody
    public ModelAndView insproductoHTMLPost(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        ModelAndView modelAndView = new ModelAndView("producto");
        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return modelAndView;
    }

    @GetMapping("/insertar")
    public ModelAndView InsertarHTML() {
        ModelAndView modelAndView = new ModelAndView("Insertar");
        modelAndView.addObject("mensaje", "Añadido correctamente");

        return modelAndView;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/insertar")

    public ModelAndView InsertarHTMLPost(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        ModelAndView modelAndView = new ModelAndView("insertar");

        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return modelAndView;
    }
  


    @GetMapping("/{id}/")

    public Producto GetProducto(@PathVariable("id") Long id){
        Producto product;
        try{
            product = pr.findById(id).get(); 
        }catch(NoSuchElementException e){
            e = new NoSuchElementException("El producto no existe: "+id);
            System.out.println(e.getMessage());
                 
            return null;
        }
        return product;
    }
  
    }

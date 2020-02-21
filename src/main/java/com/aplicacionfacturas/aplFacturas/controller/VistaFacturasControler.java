package com.aplicacionfacturas.aplFacturas.controller;

import com.aplicacionfacturas.aplFacturas.model.Producto;
import com.aplicacionfacturas.aplFacturas.model.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * VistaFacturasControler
 */

@Controller
public class VistaFacturasControler {
    @Autowired
    private ProductoRepository pr;

    @GetMapping("/producto") // http://localhost:8080/producto
    public ModelAndView InsertarHTML() {
        ModelAndView modelAndView = new ModelAndView("Insertar");
        modelAndView.addObject("mensaje", "Añadido correctamente");

        return modelAndView;
    }

    @PostMapping("/producto") // http://localhost:8080/producto

    public ModelAndView InsertarHTMLPost(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        ModelAndView modelAndView = new ModelAndView("Insertar");

        Producto producto = new Producto(descripcion, fabricante, precio);
        pr.save(producto);
        return modelAndView;
    }


    @GetMapping("/lista")
    @ResponseBody
    public ModelAndView creaProducto() {

        ModelAndView modelAndView=new ModelAndView("VistaProducto");
        modelAndView.addObject("productos", pr.findAll());

        Long total=pr.count();
        modelAndView.addObject("mensaje", "Total artículos: "+String.valueOf(total));

        
        return modelAndView;
    }

    @PostMapping("/lista")
    public ModelAndView showListaProductoAndViewPost(@RequestParam("descripcion") String descripcion,
            @RequestParam("fabricante") String fabricante, @RequestParam("precio") Float precio) {

        {
            ModelAndView mdl = new ModelAndView();
            try {

                mdl.setViewName("VistaProducto");
                Producto producto = new Producto(descripcion, fabricante, precio);
                pr.save(producto);
                mdl.addObject("productos", pr.findAll());

                // añadimos el contador de elementos
                Long total = pr.count();
                mdl.addObject("mensaje", "Total artículos: " + String.valueOf(total));

                return mdl;
            } catch (Exception e) {
                mdl.setViewName("404");
                mdl.addObject("errormsg", e.getMessage());
                return mdl;
            }
        }

    }


    @GetMapping("/delete/{id}")//http://localhost:8080/api/{id}/
    public ModelAndView deleteProducto(
        @PathVariable("id") Long id
        ) {
       
        try{
            pr.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }

        ModelAndView modelAndView=new ModelAndView("VistaProducto");
        modelAndView.addObject("productos", pr.findAll());

        Long total=pr.count();
        modelAndView.addObject("mensaje", "Total artículos: "+String.valueOf(total));
        return  modelAndView;
    }

}
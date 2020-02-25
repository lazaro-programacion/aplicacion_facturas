package com.aplicacionfacturas.aplFacturas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * usuario
 */

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id // pk no nula
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    private Long id;

    @NotNull
    private String usuario;

    @NotNull //no nulo
    private String password;

    @NotNull
    private String email;

    @NotNull
    private Integer telefono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

    public Usuario(@NotNull String usuario, @NotNull String password, @NotNull String email, @NotNull Integer telefono) {
     
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
    }

	
  


}
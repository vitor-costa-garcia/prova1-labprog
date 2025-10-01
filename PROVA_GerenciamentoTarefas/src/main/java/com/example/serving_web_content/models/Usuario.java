package com.example.serving_web_content.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUsuario", nullable = false, unique = true)
    private Integer idUsuario;;

    @NotBlank(message = "O nome não pode ser nulo.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name="nome", nullable = false, unique = true, length = 100)
    private String nome;

    @NotBlank(message = "O email não pode ser nulo")
    @Size(max = 200, message = "O email deve ter no máximo 50 caracteres")
    @Email(message = "O email deve ser válido.")
    @Column(name="email", nullable = false, unique = true, length = 200)
    private String email;

    public Usuario(Integer idUsuario,
                   String nome,
                   String email
                  ) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
    }

    public Usuario() {

    }

    public Integer getId(){ return idUsuario; }
    public String getNome(){ return nome; }
    public String getEmail(){ return email; }

    public void setId(Integer idUsuario){ this.idUsuario = idUsuario; }
    public void setNome(String nome){ this.nome = nome; }
    public void setEmail(String email){ this.email = email; }
// getters e setters
}

package com.example.serving_web_content.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="contatos")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O nome não pode ser nulo.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name="nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "o email não pode ser nulo")
    @Email(message = "O email deve ser válido.")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
    @Column(name="email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "O telefone não pode ser nulo")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    @Column(name="telefone", nullable = false, unique = true, length = 20)
    private String telefone;

    @Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    @Column(name="endereco", nullable = true, unique = false, length = 200)
    private String endereco;

    @DateTimeFormat()
    @Size(max = 12, message = "A data de nascimento deve ter no máximo 12 caracteres")
    @Column(name="data_nascimento", nullable = true, unique = false, length = 12)
    private String data_nascimento;

    public Contact(int id, String nome, String telefone, String email, String endereco, String data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
    }

    public Contact() {

    }

    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getTelefone(){
        return telefone;
    }
    public String getEmail(){
        return email;
    }
    public String getDataNascimento(){ return data_nascimento; }
    public String getEndereco(){ return endereco; }

    public void setId(int id){ this.id = id; }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDataNascimento(String data_nascimento){ this.data_nascimento = data_nascimento; }
    public void setEndereco(String endereco){ this.endereco = endereco; }
// getters e setters
}

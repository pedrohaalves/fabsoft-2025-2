package br.univille.entity;

public class Cidade {
    
    private String nome;
//construtor (inicializador de variaveis)
    public Cidade(String nome){
       this.nome = nome;
    }

    public String getNome(){
        return this.nome; 
    }

}

package br.univille;

import br.univille.entity.Cidade;
import br.univille.entity.Cliente;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
    
     var cliente = new Cliente ();
     cliente.setNome("pedro");
     cliente.setIdade(20);
     cliente.setPeso(70);

     Cidade cidade = new Cidade("laguna");

cliente.setCidade(cidade);

    }
}
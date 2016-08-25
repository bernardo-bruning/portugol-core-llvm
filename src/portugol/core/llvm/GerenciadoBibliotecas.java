/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bernardo
 */
public class GerenciadoBibliotecas {
    Map<String, Biblioteca> bibliotecas;

    public GerenciadoBibliotecas() {
        bibliotecas = new HashMap<>();
    }
    
    public Biblioteca getBiblioteca(String nome) {
        return (Biblioteca) bibliotecas.get(nome);
    }

    public void registrarBiblioteca(String nome, Biblioteca biblioteca) {
        bibliotecas.put(nome, biblioteca);
    }
}

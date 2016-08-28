/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import portugol.core.llvm.bibliotecas.BibliotecaEntedaSaida;
import portugol.core.llvm.bibliotecas.BibliotecaGraficos;

/**
 *
 * @author Bernardo
 */
public class Construtor {
    public GerenciadorBibliotecas construtorGerenciadorBibliotecas() {
        GerenciadorBibliotecas gerenciador = new GerenciadorBibliotecas();
        gerenciador.registrarBiblioteca("ES", new BibliotecaEntedaSaida());
        gerenciador.registrarBiblioteca("Graficos", new BibliotecaGraficos());
        return gerenciador;
    }
}

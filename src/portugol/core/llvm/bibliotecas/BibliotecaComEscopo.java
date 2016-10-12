/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm.bibliotecas;

import org.llvm.Module;
import portugol.core.llvm.Escopo;

/**
 *
 * @author Bernardo
 */
public abstract class BibliotecaComEscopo extends AbstractBiblioteca { 
    public abstract void inicializarEscopo(Module module, Escopo escopo);
}

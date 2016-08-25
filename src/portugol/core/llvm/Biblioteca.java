/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import org.llvm.Module;

/**
 *
 * @author Bernardo
 */
public interface Biblioteca {
    void inicializar(Module modulo);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm.bibliotecas;

import org.llvm.Module;
import org.llvm.TypeRef;
import portugol.core.llvm.Biblioteca;

/**
 *
 * @author Bernardo
 */
public class BibliotecaTipos extends AbstractBiblioteca {
    
    @Override
    public void inicializar(Module modulo) {
        assinarFuncao(modulo, "real_para_inteiro", TypeRef.int32Type(), new TypeRef[]{ TypeRef.doubleType() });
    }
    
}

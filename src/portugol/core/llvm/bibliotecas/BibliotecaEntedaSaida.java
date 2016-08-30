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
public class BibliotecaEntedaSaida implements Biblioteca {
    
    @Override
    public String getNomePacote() {
        return this.getClass().getPackage().getName() + "." + getClass().getName() + ".";
    }

    @Override
    public void inicializar(Module modulo) {
        modulo.addFunction(getNomePacote()+"escreva", TypeRef.functionType(TypeRef.int32Type(), true, TypeRef.int8Type().pointerType()));
        //TODO: Implementar saída
    }
    
}

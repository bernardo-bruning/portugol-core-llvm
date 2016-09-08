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
public class BibliotecaUtil implements Biblioteca {
        
    @Override
    public String getNomePacote() {
        return (this.getClass().getPackage().getName() + "." + getClass().getName() + ".").replace(".", "_");
    }

    
    @Override
    public void inicializar(Module modulo) {
        String pacote = getNomePacote();
        //TODO: Implementar
        modulo.addFunction(pacote+"aguarde", TypeRef
                .functionType(
                        TypeRef.voidType(), false, /*retorno*/
                        TypeRef.int32Type()
                ));
    }
    
}

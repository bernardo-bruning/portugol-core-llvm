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
public class BibliotecaGraficos implements Biblioteca {
        
    @Override
    public String getNomePacote() {
        return this.getClass().getPackage().getName() + "." + getClass().getName() + ".";
    }

    
    @Override
    public void inicializar(Module modulo) {
        String pacote = getNomePacote();
        //TODO: Implementar
        modulo.addFunction(pacote+"criar_cor", TypeRef
                .functionType(
                        TypeRef.int32Type(), false, /*retorno*/
                        TypeRef.int32Type(), /*vermelho*/
                        TypeRef.int32Type(), /*verde*/
                        TypeRef.int32Type()  /*azul*/
                ));
        
        modulo.addFunction(pacote+"definir_cor", TypeRef
                .functionType(
                        TypeRef.int32Type(), false,/*retorno nulo*/
                        TypeRef.int32Type() /*cor*/
                ));
        
        modulo.addFunction(pacote+"desenhar_ponto", TypeRef
                .functionType(
                        TypeRef.int32Type(), false,/*retorno nulo*/
                        TypeRef.int32Type(), /*x*/
                        TypeRef.int32Type()  /*y*/
                ));
    }
    
}

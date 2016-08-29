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
    private static final String concat = ".";
    
    @Override
    public void inicializar(Module modulo, String alias) {
        //TODO: Implementar
        modulo.addFunction("criar_cor", TypeRef
                .functionType(
                        TypeRef.int32Type(), false, /*retorno*/
                        TypeRef.int32Type(), /*vermelho*/
                        TypeRef.int32Type(), /*verde*/
                        TypeRef.int32Type()  /*azul*/
                ));
        
        modulo.addFunction("definir_cor", TypeRef
                .functionType(
                        TypeRef.int32Type(), false,/*retorno nulo*/
                        TypeRef.int32Type() /*cor*/
                ));
        
        modulo.addFunction(alias + concat + "desenhar_ponto", TypeRef
                .functionType(
                        TypeRef.int32Type(), false,/*retorno nulo*/
                        TypeRef.int32Type(), /*x*/
                        TypeRef.int32Type()  /*y*/
                ));
    }
    
}

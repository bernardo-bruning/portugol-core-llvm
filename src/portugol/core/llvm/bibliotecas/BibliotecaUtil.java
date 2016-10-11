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
public class BibliotecaUtil extends AbstractBiblioteca{

    @Override
    public void inicializar(Module modulo) {
        String pacote = getNomePacote();
        
        modulo.addFunction(pacote+"aguarde", TypeRef
                .functionType(
                        TypeRef.voidType(), false, /*retorno*/
                        TypeRef.int32Type()
                ));
        
        modulo.addFunction(pacote+"sorteia", TypeRef
                .functionType(
                        TypeRef.int32Type(), false, /*retorno*/
                        TypeRef.int32Type(), //Inicio
                        TypeRef.int32Type()  //Fim
                ));
        
        modulo.addFunction(pacote+"tempo_decorrido", TypeRef
                .functionType(
                        TypeRef.int32Type(), false /*retorno*/
                ));
        
        assinarFuncao(modulo, "arredondar", TypeRef.int32Type(), 
                new TypeRef[]{ TypeRef.doubleType(), TypeRef.int32Type() });
    }
    
}

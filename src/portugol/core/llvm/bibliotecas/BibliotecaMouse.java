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
public class BibliotecaMouse implements Biblioteca {

    @Override
    public String getNomePacote() {
        return (this.getClass().getPackage().getName() + "." + getClass().getName() + ".").replace(".", "_");
    }

    @Override
    public void inicializar(Module modulo) {
         String pacote = getNomePacote();
        
        modulo.addFunction(pacote+"posicao_x", TypeRef
                .functionType(
                        TypeRef.int32Type(), false /*retorno*/
                ));
        
        modulo.addFunction(pacote+"posicao_y", TypeRef
                .functionType(
                        TypeRef.int32Type(), false /*retorno*/
                ));
        
        modulo.addFunction(pacote+"ler_botao", TypeRef
                .functionType(
                        TypeRef.int32Type(), false /*retorno*/
                ));
        
        modulo.addFunction(pacote+"botao_pressionado", TypeRef
                .functionType(
                        TypeRef.int1Type(), false, /*retorno*/
                        TypeRef.int32Type() //Botão
                ));
        
        modulo.addFunction(pacote+"algum_botao_pressionado", TypeRef
                .functionType(
                        TypeRef.int1Type(), false /*retorno*/
                ));
    }
    
}

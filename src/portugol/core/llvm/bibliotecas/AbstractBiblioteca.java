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
public abstract class AbstractBiblioteca implements Biblioteca {
    
    @Override
    public String getNomePacote() {
        return this.getClass().getPackage().getName() + "." + getClass().getName() + ".";
    }
    
    protected void assinarFuncao(Module modulo, String nomeFuncao, TypeRef retorno, TypeRef[] args) {
        String pacote = getNomePacote();
        modulo.addFunction(pacote+nomeFuncao, TypeRef
            .functionType(
                    retorno, false,
                    args
            ));
    }
    
    protected void assinarProcedimento(Module modulo, String nomeFuncao) {
        assinarProcedimento(modulo, nomeFuncao, new TypeRef[0]);
    }
    
    protected void assinarProcedimento(Module modulo, String nomeFuncao, TypeRef[] args) {
        assinarFuncao(modulo, nomeFuncao, TypeRef.voidType(), args);
    }    
}

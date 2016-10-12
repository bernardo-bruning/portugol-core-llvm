/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm.bibliotecas;

import org.llvm.Module;
import org.llvm.TypeRef;

/**
 *
 * @author Bernardo
 */
public class BibliotecaMatematica extends AbstractBiblioteca {

    @Override
    public void inicializar(Module modulo) {
        assinarFuncao(modulo, "arredondar", TypeRef.doubleType(), new TypeRef[] { TypeRef.doubleType(), TypeRef.int32Type() });
        assinarFuncao(modulo, "valor_absoluto", TypeRef.int32Type(), new TypeRef[] { TypeRef.doubleType() });
    }
    
}

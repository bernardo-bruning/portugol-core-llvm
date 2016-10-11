/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm.bibliotecas;

import org.llvm.Module;
import org.llvm.TypeRef;
import portugol.core.llvm.Biblioteca;
import portugol.core.llvm.Escopo;

/**
 *
 * @author Bernardo
 */
public class BibliotecaTeclado extends BibliotecaComEscopo {

    @Override
    public void inicializar(Module modulo) {
        this.assinarFuncao(modulo, "tecla_pressionada", TypeRef.int1Type(), new TypeRef[]{TypeRef.int32Type()});
    }

    private void addGlobalInt(Escopo escopo, String name) {
        escopo.adicionar(name, TypeRef.int32Type().constInt(0, true));
    }

    @Override
    public void inicializarEscopo(Escopo escopo) {
        addGlobalInt(escopo, "TECLA_W");
        addGlobalInt(escopo, "TECLA_SETA_ACIMA");
        addGlobalInt(escopo, "TECLA_ESC");
        addGlobalInt(escopo, "TECLA_A");
        addGlobalInt(escopo, "TECLA_SETA_ESQUERDA");
        addGlobalInt(escopo, "TECLA_ENTER");
        addGlobalInt(escopo, "TECLA_D");
        addGlobalInt(escopo, "TECLA_SETA_DIREITA");
    }
    
}

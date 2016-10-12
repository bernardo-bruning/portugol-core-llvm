/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm.bibliotecas;

import org.llvm.Module;
import org.llvm.TypeRef;
import org.llvm.Value;
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

    @Override
    public void inicializarEscopo(Module module, Escopo escopo) {
        addGlobalInt(module, escopo, "TECLA_W", 0);
        addGlobalInt(module, escopo, "TECLA_SETA_ACIMA", 0);
        addGlobalInt(module, escopo, "TECLA_ESC", 0);
        addGlobalInt(module, escopo, "TECLA_A", 0);
        addGlobalInt(module, escopo, "TECLA_SETA_ESQUERDA", 0);
        addGlobalInt(module, escopo, "TECLA_ENTER", 0);
        addGlobalInt(module, escopo, "TECLA_D", 0);
        addGlobalInt(module, escopo, "TECLA_SETA_DIREITA", 0);
    }
    
}

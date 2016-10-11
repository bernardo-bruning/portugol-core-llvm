/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import java.util.HashMap;
import org.llvm.Value;

/**
 *
 * @author Bernardo
 */
public class Escopo {
    private Escopo parent;
    private HashMap<String, Value> referencias;

    public Escopo() {
        this.referencias = new HashMap<>();
    }

    public Escopo(Escopo parent) {
        this();
        this.parent = parent;
    }
    
    public void adicionar(String nome, Value inicializacao) {
        if(referencias.containsKey(nome))
            referencias.replace(nome, inicializacao);
        else
            referencias.put(nome, inicializacao);
    }

    public Value referenciar(String nome) throws ExcecaoVisitaASA {
        Value referencia = referencias.get(nome);
        if(referencia == null && parent != null) return parent.referenciar(nome);
        return referencia;
    }

    public Escopo getParent() {
        return this.parent;
    }
    
}

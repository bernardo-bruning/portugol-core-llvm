/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import br.univali.portugol.nucleo.asa.TipoDado;
import org.llvm.Value;

/**
 *
 * @author Bernardo
 */
public class No {
    private TipoDado tipoDado;
    private Value value;

    public No(TipoDado tipoDado, Value value) {
        this.tipoDado = tipoDado;
        this.value = value;
    }
    
    public No(Value value) {
        this.value = value;
    }

    public void setTipoDado(TipoDado tipoDado) {
        this.tipoDado = tipoDado;
    }

    public TipoDado getTipoDado() {
        return tipoDado;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
    
    
}

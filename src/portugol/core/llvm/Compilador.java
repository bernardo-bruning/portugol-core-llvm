/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portugol.core.llvm;

import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.Portugol;
import br.univali.portugol.nucleo.Programa;
import br.univali.portugol.nucleo.asa.ArvoreSintaticaAbstrataPrograma;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.portugol.nucleo.asa.NoBitwiseNao;
import br.univali.portugol.nucleo.asa.NoBloco;
import br.univali.portugol.nucleo.asa.NoCadeia;
import br.univali.portugol.nucleo.asa.NoCaracter;
import br.univali.portugol.nucleo.asa.NoCaso;
import br.univali.portugol.nucleo.asa.NoChamadaFuncao;
import br.univali.portugol.nucleo.asa.NoContinue;
import br.univali.portugol.nucleo.asa.NoDeclaracao;
import br.univali.portugol.nucleo.asa.NoDeclaracaoFuncao;
import br.univali.portugol.nucleo.asa.NoDeclaracaoMatriz;
import br.univali.portugol.nucleo.asa.NoDeclaracaoParametro;
import br.univali.portugol.nucleo.asa.NoDeclaracaoVariavel;
import br.univali.portugol.nucleo.asa.NoDeclaracaoVetor;
import br.univali.portugol.nucleo.asa.NoEnquanto;
import br.univali.portugol.nucleo.asa.NoEscolha;
import br.univali.portugol.nucleo.asa.NoExpressao;
import br.univali.portugol.nucleo.asa.NoFacaEnquanto;
import br.univali.portugol.nucleo.asa.NoInclusaoBiblioteca;
import br.univali.portugol.nucleo.asa.NoInteiro;
import br.univali.portugol.nucleo.asa.NoLogico;
import br.univali.portugol.nucleo.asa.NoMatriz;
import br.univali.portugol.nucleo.asa.NoMenosUnario;
import br.univali.portugol.nucleo.asa.NoNao;
import br.univali.portugol.nucleo.asa.NoOperacaoAtribuicao;
import br.univali.portugol.nucleo.asa.NoOperacaoBitwiseE;
import br.univali.portugol.nucleo.asa.NoOperacaoBitwiseLeftShift;
import br.univali.portugol.nucleo.asa.NoOperacaoBitwiseOu;
import br.univali.portugol.nucleo.asa.NoOperacaoBitwiseRightShift;
import br.univali.portugol.nucleo.asa.NoOperacaoBitwiseXOR;
import br.univali.portugol.nucleo.asa.NoOperacaoDivisao;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaDiferenca;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaE;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaIgualdade;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaMaior;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaMaiorIgual;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaMenor;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaMenorIgual;
import br.univali.portugol.nucleo.asa.NoOperacaoLogicaOU;
import br.univali.portugol.nucleo.asa.NoOperacaoModulo;
import br.univali.portugol.nucleo.asa.NoOperacaoMultiplicacao;
import br.univali.portugol.nucleo.asa.NoOperacaoSoma;
import br.univali.portugol.nucleo.asa.NoOperacaoSubtracao;
import br.univali.portugol.nucleo.asa.NoPara;
import br.univali.portugol.nucleo.asa.NoPare;
import br.univali.portugol.nucleo.asa.NoReal;
import br.univali.portugol.nucleo.asa.NoReferencia;
import br.univali.portugol.nucleo.asa.NoReferenciaMatriz;
import br.univali.portugol.nucleo.asa.NoReferenciaVariavel;
import br.univali.portugol.nucleo.asa.NoReferenciaVetor;
import br.univali.portugol.nucleo.asa.NoRetorne;
import br.univali.portugol.nucleo.asa.NoSe;
import br.univali.portugol.nucleo.asa.NoTitulo;
import br.univali.portugol.nucleo.asa.NoVaPara;
import br.univali.portugol.nucleo.asa.NoVetor;
import br.univali.portugol.nucleo.asa.VisitanteASA;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.llvm.BasicBlock;
import org.llvm.Builder;
import org.llvm.Module;
import org.llvm.TypeRef;
import org.llvm.Value;
import org.llvm.binding.LLVMLibrary;

/**
 *
 * @author Bernardo
 */
public class Compilador implements VisitanteASA {
    private final Module module;
    private Builder _currentBuilder;
    private Map<String, Value> scope;
    private BasicBlock blocoAtual;

    public Compilador(String source) throws ErroCompilacao, ExcecaoVisitaASA {
        Programa programa = Portugol.compilar(source);
        this.module = Module.createWithName("programa");
        this.module.addFunction("escreva", TypeRef.functionType(TypeRef.int32Type(), true, TypeRef.int8Type().pointerType()));
        
        programa.getArvoreSintaticaAbstrata().aceitar(this);
    }

    @Override
    public Object visitar(ArvoreSintaticaAbstrataPrograma asap) throws ExcecaoVisitaASA {
        for (NoInclusaoBiblioteca inclusao : asap.getListaInclusoesBibliotecas())
        {
            inclusao.aceitar(this);
        }

        List<NoDeclaracao> listaDeclaracoesGlobais = asap.getListaDeclaracoesGlobais();
        for (NoDeclaracao noDeclaracao : listaDeclaracoesGlobais)
        {
            noDeclaracao.aceitar(this);
        }
        return null;
    }

    @Override
    public Object visitar(NoCadeia nc) throws ExcecaoVisitaASA {
        return _currentBuilder.buildGlobalStringPtr(nc.getValor(), "");
    }

    @Override
    public Object visitar(NoCaracter nc) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoCaso nocaso) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoChamadaFuncao ncf) throws ExcecaoVisitaASA {
        List<Value> args = new ArrayList<>();
        List<NoExpressao> parametros = ncf.getParametros();
        for (NoExpressao param : parametros) {
            args.add((Value)param.aceitar(this));
        }
        
        Value[] arr = new Value[args.size()];
        
        return _currentBuilder.buildCall(module.getNamedFunction(ncf.getNome()), "", args.toArray(arr));
    }

    @Override
    public Object visitar(NoContinue nc) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao ndf) throws ExcecaoVisitaASA {
        scope = new HashMap<String, Value>();
        String fName = ndf.getNome().equals("inicio")?"main":ndf.getNome();
        Value function = this.module.addFunction(fName, TypeRef.functionType(TypeRef.int32Type(), TypeRef.int32Type()));
        function.setFunctionCallConv(LLVMLibrary.LLVMCallConv.LLVMCCallConv);
        this.blocoAtual = function.appendBasicBlock("entry");
        Builder builder = Builder.createBuilder();
        _currentBuilder = builder;
        builder.positionBuilderAtEnd(this.blocoAtual);
        for (NoBloco bloco : ndf.getBlocos()) {
            bloco.aceitar(this);
        }
        builder.buildRet(TypeRef.int32Type().constInt(1, true));
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoMatriz ndm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoVariavel ndv) throws ExcecaoVisitaASA {
        Value inicializacao = (Value)ndv.getInicializacao().aceitar(this);
        Value ponteiro = _currentBuilder.buildAlloca(inicializacao.typeOf().type(), ndv.getNome());
        _currentBuilder.buildStore(inicializacao, ponteiro);
        this.scope.put(ndv.getNome(), ponteiro);
        return ponteiro;
    }

    @Override
    public Object visitar(NoDeclaracaoVetor ndv) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoEnquanto ne) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoAtual.getBasicBlockParent().appendBasicBlock("enquanto.condicao");
        BasicBlock blocoEntrada = this.blocoAtual.getBasicBlockParent().appendBasicBlock("enquanto.entrada");
        BasicBlock blocoSaida = this.blocoAtual.getBasicBlockParent().appendBasicBlock("enquanto.saida");
        
        _currentBuilder.buildBr(blocoCondicao);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoCondicao);
        blocoAtual = blocoCondicao;
        Value condicao = (Value)ne.getCondicao().aceitar(this);
        _currentBuilder.buildCondBr(condicao, blocoEntrada, blocoSaida);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoEntrada);
        blocoAtual = blocoEntrada;
        for (NoBloco bloco : ne.getBlocos()) {
            bloco.aceitar(this);
        }
        _currentBuilder.buildBr(blocoCondicao);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoSaida);        
        blocoAtual = blocoSaida;
        return null;
    }

    @Override
    public Object visitar(NoEscolha ne) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoFacaEnquanto nfe) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoAtual.getBasicBlockParent().appendBasicBlock("enquanto.condicao");
        BasicBlock blocoEntrada = this.blocoAtual.getBasicBlockParent().appendBasicBlock("enquanto.entrada");
        BasicBlock blocoSaida = this.blocoAtual.getBasicBlockParent().appendBasicBlock("enquanto.saida");
        
        _currentBuilder.buildBr(blocoEntrada);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoCondicao);
        blocoAtual = blocoCondicao;
        Value condicao = (Value)nfe.getCondicao().aceitar(this);
        _currentBuilder.buildCondBr(condicao, blocoEntrada, blocoSaida);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoEntrada);
        blocoAtual = blocoEntrada;
        for (NoBloco bloco : nfe.getBlocos()) {
            bloco.aceitar(this);
        }
        _currentBuilder.buildBr(blocoCondicao);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoSaida);        
        blocoAtual = blocoSaida;
        
        return null;
    }

    @Override
    public Object visitar(NoInteiro ni) throws ExcecaoVisitaASA {
        return TypeRef.int32Type().constInt(ni.getValor(), false);
    }

    @Override
    public Object visitar(NoLogico nl) throws ExcecaoVisitaASA {
        return TypeRef.int1Type().constInt(nl.getValor()?1:0, false);
    }

    @Override
    public Object visitar(NoMatriz nm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoMenosUnario nmu) throws ExcecaoVisitaASA {
        Value expressao = (Value)nmu.getExpressao().aceitar(this);
        return _currentBuilder.buildSub(expressao, TypeRef.int32Type().constInt(1, false), "");
    }

    @Override
    public Object visitar(NoNao nonao) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaIgualdade noli) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)noli.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)noli.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildICmp(LLVMLibrary.LLVMIntPredicate.LLVMIntEQ, valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaDiferenca nold) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nold.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nold.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildICmp(LLVMLibrary.LLVMIntPredicate.LLVMIntNE, valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoAtribuicao noa) throws ExcecaoVisitaASA {
        NoReferencia operandoEsquerdo = (NoReferencia)noa.getOperandoEsquerdo();
        Value operandoDireito = (Value)noa.getOperandoDireito().aceitar(this);
        
        return _currentBuilder.buildStore(operandoDireito, this.scope.get(operandoEsquerdo.getNome()));
    }

    @Override
    public Object visitar(NoOperacaoLogicaE nole) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nole.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nole.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildAnd(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaOU nolou) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolou.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolou.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildOr(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaMaior nolm) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolm.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolm.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildICmp(LLVMLibrary.LLVMIntPredicate.LLVMIntSGT , valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaMaiorIgual nolmi) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolmi.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolmi.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildICmp(LLVMLibrary.LLVMIntPredicate.LLVMIntSGE , valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaMenor nolm) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolm.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolm.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildICmp(LLVMLibrary.LLVMIntPredicate.LLVMIntULT  , valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaMenorIgual nolmi) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolmi.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolmi.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildICmp(LLVMLibrary.LLVMIntPredicate.LLVMIntULE  , valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoSoma nos) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nos.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nos.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildAdd(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoSubtracao nos) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nos.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nos.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildSub(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoDivisao nod) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nod.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nod.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildSDiv(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoMultiplicacao nom) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nom.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nom.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildMul(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoModulo nom) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoBitwiseLeftShift nobls) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobls.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobls.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildShl(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseRightShift nobrs) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobrs.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobrs.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildLShr(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseE nobe) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobe.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobe.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildAnd(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseOu nobo) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobo.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobo.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildOr(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseXOR nobxor) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobxor.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobxor.getOperandoDireito().aceitar(this);
        return _currentBuilder.buildXor(valueEsquerdo, valueDireito, "");
    }

    @Override
    public Object visitar(NoBitwiseNao nbn) throws ExcecaoVisitaASA {
        Value expressao = (Value)nbn.getExpressao().aceitar(this);
        return _currentBuilder.buildNot(expressao, "");
    }

    @Override
    public Object visitar(NoPara nopara) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoAtual.getBasicBlockParent().appendBasicBlock("para.condicao");
        BasicBlock blocoEntrada = this.blocoAtual.getBasicBlockParent().appendBasicBlock("para.entrada");
        BasicBlock blocoSaida = this.blocoAtual.getBasicBlockParent().appendBasicBlock("para.saida");
        
        nopara.getInicializacao().aceitar(this);
        _currentBuilder.buildBr(blocoEntrada);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoCondicao);
        blocoAtual = blocoCondicao;
        Value condicao = (Value)nopara.getCondicao().aceitar(this);
        _currentBuilder.buildCondBr(condicao, blocoEntrada, blocoSaida);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoEntrada);
        blocoAtual = blocoEntrada;
        for (NoBloco bloco : nopara.getBlocos()) {
            bloco.aceitar(this);
        }
        
        nopara.getIncremento().aceitar(this);
        _currentBuilder.buildBr(blocoCondicao);
        
        _currentBuilder.clearInsertionPosition();
        _currentBuilder.positionBuilderAtEnd(blocoSaida);        
        blocoAtual = blocoSaida;
        return null;
    }

    @Override
    public Object visitar(NoPare nopare) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoReal noreal) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoReferenciaMatriz nrm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoReferenciaVariavel nrv) throws ExcecaoVisitaASA {
        if(this.scope.containsKey(nrv.getNome())) return _currentBuilder.buildLoad(this.scope.get(nrv.getNome()), nrv.getNome() + ".carregado");
        return null;
    }

    @Override
    public Object visitar(NoReferenciaVetor nrv) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoRetorne nr) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoSe nose) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoTitulo nt) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoVaPara nvp) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoVetor nv) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoParametro ndp) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoInclusaoBiblioteca nib) throws ExcecaoVisitaASA {
        return null;
    }

    public Module getLLVM() {
        return this.module;
    }
}

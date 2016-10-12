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
import br.univali.portugol.nucleo.asa.TipoDado;
import br.univali.portugol.nucleo.asa.VisitanteASA;
import br.univali.portugol.nucleo.asa.VisitanteASABasico;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.llvm.BasicBlock;
import org.llvm.Builder;
import org.llvm.Module;
import org.llvm.TypeRef;
import org.llvm.Value;
import org.llvm.binding.LLVMLibrary;
import portugol.core.llvm.bibliotecas.BibliotecaComEscopo;

/**
 *
 * @author Bernardo
 */
public class Compilador implements VisitanteASA {
    private final Module module;
    private Builder construtor;
    private Escopo escopo; //Renomear para escopo
    private final Map<String, String> pacotes;
    private BasicBlock blocoEscopo;
    private GerenciadorBibliotecas gerenciadorBibliotecas;
    private ArvoreSintaticaAbstrataPrograma arvoreSintaticaAbstrata;
    private boolean isGlobal = true;

    public Compilador(String source) throws ErroCompilacao, ExcecaoVisitaASA {
        this.gerenciadorBibliotecas = Construtor.construtorGerenciadorBibliotecas();
        this.pacotes = new HashMap<>();
        this.escopo = new Escopo();
        
        Programa programa = Portugol.compilar(source);
        
        this.module = Module.createWithName("programa");
        this.module.addFunction("escreva", TypeRef.functionType(TypeRef.int32Type(), true, TypeRef.int8Type().pointerType()));
        this.module.addFunction("leia", TypeRef.functionType(TypeRef.voidType(), true, TypeRef.int32Type()));
        
        this.construtor = Builder.createBuilder();
        
        
        programa.getArvoreSintaticaAbstrata().aceitar(this);
    }

    @Override
    public Object visitar(ArvoreSintaticaAbstrataPrograma asap) throws ExcecaoVisitaASA {
        arvoreSintaticaAbstrata = asap;
        AssinarFuncoes assinarFuncoes = new AssinarFuncoes(module, asap);
        
        for (NoInclusaoBiblioteca inclusao : asap.getListaInclusoesBibliotecas())
        {
            inclusao.aceitar(this);
        }

        List<NoDeclaracao> listaDeclaracoesGlobais = asap.getListaDeclaracoesGlobais();
        for (NoDeclaracao noDeclaracao : listaDeclaracoesGlobais)
        {
            noDeclaracao.aceitar(assinarFuncoes);
        }
        
        for (NoDeclaracao noDeclaracao : listaDeclaracoesGlobais)
        {
            noDeclaracao.aceitar(this);
        }
        return null;
    }

    @Override
    public Object visitar(NoCadeia nc) throws ExcecaoVisitaASA {
        return construtor.buildGlobalStringPtr(nc.getValor(), "");
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
        try {
            String pacote = pacotes.containsKey(ncf.getEscopo())?pacotes.get(ncf.getEscopo()):"";
            Value function = module.getNamedFunction(pacote + ncf.getNome());
            
            List<Value> args = new ArrayList<>();
            List<NoExpressao> parametros = ncf.getParametros();
            if(parametros != null) {
                for (int i = 0; i < parametros.size(); i++) {
                    Value valor = (Value)parametros.get(i).aceitar(this);
//                    if(declaracao != null){
//                        
//                    }
//                    else {
//                        TypeRef tipo = function.getParam(i).typeOf();
//                        if(!Util.hasType(valor, tipo)){ 
//                            System.out.println("Aplicando conversão de tipo!");
//                            valor = Util.convertTo(construtor, valor, tipo);
//                        }   
//                        
//                        System.out.println(String.format("Declaração não identificada da função '%s' na linha %d", ncf.getNome(), ncf.getTrechoCodigoFonte().getLinha()));
//
//                    }

                    TypeRef tipo = function.getParam(i).typeOf();
                    if(!Util.hasType(valor, tipo)) 
                        valor = Util.convertTo(construtor, valor, tipo);   
                    args.add(valor);
                }
            }
            Value[] arr = new Value[args.size()];
            
            return construtor.buildCall(function, "", args.toArray(arr));
        } catch (Exception e) {
            throw new ExcecaoVisitaASA(String.format("Não foi possível chamar a função %s", ncf.getNome()), arvoreSintaticaAbstrata, ncf);
        }
    }

    @Override
    public Object visitar(NoContinue nc) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao ndf) throws ExcecaoVisitaASA {
        isGlobal = false;        
        escopo = new Escopo(escopo);
        String nomeFuncao = ndf.getNome();
        Value funcao = this.module.getNamedFunction(nomeFuncao);
        
        funcao.setFunctionCallConv(LLVMLibrary.LLVMCallConv.LLVMCCallConv);
        blocoEscopo = funcao.appendBasicBlock("incio_funcao");
        construtor = Builder.createBuilder();
        construtor.positionBuilderAtEnd(this.blocoEscopo);
        
        for (NoDeclaracaoParametro parametro : ndf.getParametros()) {
            Value valor = funcao.getParam(parametro.getIndice());
//            Value vParametro = construtor.buildAlloca(valor.typeOf().type(), parametro.getNome());
//            construtor.buildStore(valor, vParametro);
//            escopo.adicionar(parametro.getNome(), vParametro);
            escopo.adicionar(parametro.getNome(), valor);
        }
        
        for (NoBloco bloco : ndf.getBlocos()) {
            bloco.aceitar(this);
        }
        
        if(ndf.getBlocos().size() > 0 && !(ndf.getBlocos().get(ndf.getBlocos().size()-1) instanceof NoRetorne))
            construtor.buildRetVoid();
        
        escopo = escopo.getParent();
        isGlobal = true;
        return funcao;
    }

    @Override
    public Object visitar(NoDeclaracaoMatriz ndm) throws ExcecaoVisitaASA {
        TypeRef tipo = Util.convertType(ndm.getTipoDado());
        Value numeroColunas = (Value)ndm.getNumeroColunas().aceitar(this);
        Value numeroLinhas = (Value)ndm.getNumeroLinhas().aceitar(this);
        Value tamanhoTotalMatriz = construtor.buildMul(numeroLinhas, numeroColunas, "");
        Value matriz = construtor.buildArrayAlloca(tipo.type(), tamanhoTotalMatriz, "");
        escopo.adicionar(ndm.getNome(), matriz);
        return matriz;
    }

    @Override
    public Object visitar(NoDeclaracaoVariavel ndv) throws ExcecaoVisitaASA {
        Value inicializacao;
        TypeRef tipo = Util.convertType(ndv.getTipoDado());
        if(ndv.getInicializacao() == null) {
            inicializacao = tipo.constNull();
        }
        else {
            inicializacao = (Value)ndv.getInicializacao().aceitar(this);
        }
        
        
        Value ponteiro;
        if(isGlobal){
            ponteiro = this.module.addGlobal(tipo, ndv.getNome());
            ponteiro.setInitializer(inicializacao);
        }
        else{
            ponteiro = construtor.buildAlloca(tipo.type(), ndv.getNome());
            if(!Util.hasType(inicializacao, ponteiro.typeOf())) 
                inicializacao = Util.convertTo(construtor, inicializacao, tipo);
            construtor.buildStore(inicializacao, ponteiro);
        }
        
        this.escopo.adicionar(ndv.getNome(), ponteiro);
        return ponteiro;
    }

    @Override
    public Object visitar(NoDeclaracaoVetor ndv) throws ExcecaoVisitaASA {
        TypeRef tipo = Util.convertType(ndv.getTipoDado());
        Value vetor = construtor.buildArrayAlloca(tipo.type(), (Value)ndv.getTamanho().aceitar(this), "");
        escopo.adicionar(ndv.getNome(), vetor);
        return vetor;
    }

    @Override
    public Object visitar(NoEnquanto ne) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("enquanto.condicao");
        BasicBlock blocoEntrada = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("enquanto.entrada");
        BasicBlock blocoSaida = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("enquanto.saida");
        
        construtor.buildBr(blocoCondicao);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoCondicao);
        blocoEscopo = blocoCondicao;
        Value condicao = (Value)ne.getCondicao().aceitar(this);
        construtor.buildCondBr(condicao, blocoEntrada, blocoSaida);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoEntrada);
        blocoEscopo = blocoEntrada;
        for (NoBloco bloco : ne.getBlocos()) {
            bloco.aceitar(this);
        }
        construtor.buildBr(blocoCondicao);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoSaida);        
        blocoEscopo = blocoSaida;
        return null;
    }

    @Override
    public Object visitar(NoEscolha ne) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoFacaEnquanto nfe) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("enquanto.condicao");
        BasicBlock blocoEntrada = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("enquanto.entrada");
        BasicBlock blocoSaida = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("enquanto.saida");
        
        construtor.buildBr(blocoEntrada);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoCondicao);
        blocoEscopo = blocoCondicao;
        Value condicao = (Value)nfe.getCondicao().aceitar(this);
        construtor.buildCondBr(condicao, blocoEntrada, blocoSaida);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoEntrada);
        blocoEscopo = blocoEntrada;
        for (NoBloco bloco : nfe.getBlocos()) {
            bloco.aceitar(this);
        }
        construtor.buildBr(blocoCondicao);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoSaida);        
        blocoEscopo = blocoSaida;
        
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
        for (LLVMLibrary.LLVMTypeKind tipo : expressao.typeOf().getTypeKind()) {
            if(tipo == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind)
                    return construtor.buildFNeg(expressao, "");
                
            if(tipo == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind)
                return construtor.buildNeg(expressao, "");

            if(tipo == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind)
                return construtor.buildFNeg(expressao, "");
        }
        Value fExpressao = construtor.buildFPToUI(expressao, TypeRef.doubleType().type(), "");
        return construtor.buildFNeg(fExpressao, "");
    }

    @Override
    public Object visitar(NoNao nonao) throws ExcecaoVisitaASA {
        Value expressao = (Value)nonao.getExpressao().aceitar(this);
        
        for (LLVMLibrary.LLVMTypeKind tipo : expressao.typeOf().getTypeKind()) {            
            if(tipo == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind)
                    return construtor.buildFNeg(expressao, "");
                
            if(tipo == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind)
                return construtor.buildNeg(expressao, "");

            if(tipo == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind)
                return construtor.buildFNeg(expressao, "");
        }
        
        Value fExpressao = construtor.buildFPToUI(expressao, TypeRef.doubleType().type(), "");
        return construtor.buildFNeg(fExpressao, "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaIgualdade noli) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)noli.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)noli.getOperandoDireito().aceitar(this);        
        return operacao(LLVMLibrary.LLVMIntPredicate.LLVMIntEQ, 
                Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), noli);
    }

    @Override
    public Object visitar(NoOperacaoLogicaDiferenca nold) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nold.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nold.getOperandoDireito().aceitar(this);
        return operacao(LLVMLibrary.LLVMIntPredicate.LLVMIntNE, valueEsquerdo, valueDireito, nold);
    }

    @Override
    public Object visitar(NoOperacaoAtribuicao noa) throws ExcecaoVisitaASA {
        NoReferencia operandoEsquerdo = (NoReferencia)noa.getOperandoEsquerdo();
        Value operandoDireito = (Value)noa.getOperandoDireito().aceitar(this);
        TypeRef tipo = Util.convertType(operandoEsquerdo.getOrigemDaReferencia().getTipoDado());
        if(operandoEsquerdo == null) throw new ExcecaoVisitaASA("Erro ao obter referencia!", arvoreSintaticaAbstrata, noa);
        
        Value ponteiro = this.escopo.referenciar(operandoEsquerdo.getNome());
        Value valor = Util.convertTo(construtor, operandoDireito, tipo);
        construtor.buildStore(valor, ponteiro);
        
        return ponteiro;
    }

    @Override
    public Object visitar(NoOperacaoLogicaE nole) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nole.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nole.getOperandoDireito().aceitar(this);
        return construtor.buildAnd(Util.convertToInteger(construtor, valueEsquerdo), 
                                   Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaOU nolou) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolou.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolou.getOperandoDireito().aceitar(this);
        return construtor.buildOr(Util.convertToInteger(construtor, valueEsquerdo),
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoLogicaMaior nolm) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolm.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolm.getOperandoDireito().aceitar(this);
        
        return operacao(LLVMLibrary.LLVMIntPredicate.LLVMIntSGT, valueEsquerdo, valueDireito, nolm);
    }

    @Override
    public Object visitar(NoOperacaoLogicaMaiorIgual nolmi) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolmi.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolmi.getOperandoDireito().aceitar(this);
        return operacao(LLVMLibrary.LLVMIntPredicate.LLVMIntSGE, valueEsquerdo, valueDireito, nolmi);
    }

    @Override
    public Object visitar(NoOperacaoLogicaMenor nolm) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolm.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolm.getOperandoDireito().aceitar(this);
        return operacao(LLVMLibrary.LLVMIntPredicate.LLVMIntULT, valueEsquerdo, valueDireito, nolm);
    }

    @Override
    public Object visitar(NoOperacaoLogicaMenorIgual nolmi) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nolmi.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nolmi.getOperandoDireito().aceitar(this);
        return operacao(LLVMLibrary.LLVMIntPredicate.LLVMIntULE, valueEsquerdo, valueDireito, nolmi);
    }

    @Override
    public Object visitar(NoOperacaoSoma nos) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nos.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nos.getOperandoDireito().aceitar(this);
        for (LLVMLibrary.LLVMTypeKind tipoDireito : valueDireito.typeOf().getTypeKind()) {
            for (LLVMLibrary.LLVMTypeKind tipoEsquerdo : valueEsquerdo.typeOf().getTypeKind()) {
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind)
                    return construtor.buildFAdd(valueEsquerdo, valueDireito, "");
                
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind)
                    return construtor.buildAdd(valueEsquerdo, valueDireito, "");
                
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind)
                    return construtor.buildNSWAdd(valueEsquerdo, valueDireito, "");
            }
        }
        Value esquerdo = Util.convertToDouble(construtor, valueEsquerdo);
        Value direito = Util.convertToDouble(construtor, valueDireito);
        return construtor.buildFAdd(esquerdo, direito, "");
    }

    @Override
    public Object visitar(NoOperacaoSubtracao nos) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nos.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nos.getOperandoDireito().aceitar(this);
        
        for (LLVMLibrary.LLVMTypeKind tipoDireito : valueDireito.typeOf().getTypeKind()) {
            for (LLVMLibrary.LLVMTypeKind tipoEsquerdo : valueEsquerdo.typeOf().getTypeKind()) {
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind)
                    return construtor.buildFSub(valueEsquerdo, valueDireito, "");
                
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind)
                    return construtor.buildSub(valueEsquerdo, valueDireito, "");
                
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind)
                    return construtor.buildNSWSub(valueEsquerdo, valueDireito, "");
            }
        }
        
        return construtor.buildFSub(Util.convertToDouble(construtor, valueEsquerdo), Util.convertToDouble(construtor, valueDireito), "");
        //throw new ExcecaoVisitaASA("Erro ao efetuar operação de subtração, tipos inválidos", arvoreSintaticaAbstrata, nos);
    }

    @Override
    public Object visitar(NoOperacaoDivisao nod) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nod.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nod.getOperandoDireito().aceitar(this);
        
        return construtor.buildFDiv(Util.convertToDouble(construtor, valueEsquerdo), 
                Util.convertToDouble(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoMultiplicacao nom) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nom.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nom.getOperandoDireito().aceitar(this);
        for (LLVMLibrary.LLVMTypeKind tipoDireita : valueDireito.typeOf().getTypeKind()) {
            for (LLVMLibrary.LLVMTypeKind tipoEsquerda : valueEsquerdo.typeOf().getTypeKind()) {
                if(tipoDireita == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind && tipoEsquerda == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind)
                    return construtor.buildMul(valueEsquerdo, valueDireito, "");
                if(tipoDireita == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind && tipoEsquerda == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind)
                    return construtor.buildFMul(valueEsquerdo, valueDireito, "");
                
                if(tipoDireita == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind && tipoEsquerda == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind)
                    return construtor.buildNSWMul(valueDireito, valueDireito, "");
            }
        }
        
        return construtor.buildFMul(Util.convertToDouble(construtor, valueEsquerdo), 
                Util.convertToDouble(construtor, valueDireito), "");
        //throw new ExcecaoVisitaASA("Erro ao efetuar operação de multiplicação", arvoreSintaticaAbstrata, nom);
    }

    @Override
    public Object visitar(NoOperacaoModulo nom) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nom.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nom.getOperandoDireito().aceitar(this);
        return construtor.buildSDiv(Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseLeftShift nobls) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobls.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobls.getOperandoDireito().aceitar(this);
        return construtor.buildShl(Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseRightShift nobrs) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobrs.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobrs.getOperandoDireito().aceitar(this);
        return construtor.buildLShr(Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseE nobe) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobe.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobe.getOperandoDireito().aceitar(this);
        
        return construtor.buildAnd(Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseOu nobo) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobo.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobo.getOperandoDireito().aceitar(this);
        
        return construtor.buildOr(Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoOperacaoBitwiseXOR nobxor) throws ExcecaoVisitaASA {
        Value valueEsquerdo = (Value)nobxor.getOperandoEsquerdo().aceitar(this);
        Value valueDireito = (Value)nobxor.getOperandoDireito().aceitar(this);
        return construtor.buildXor(Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    @Override
    public Object visitar(NoBitwiseNao nbn) throws ExcecaoVisitaASA {
        Value expressao = (Value)nbn.getExpressao().aceitar(this);
        
        return construtor.buildNot(Util.convertToInteger(construtor, expressao), "");
    }

    @Override
    public Object visitar(NoPara nopara) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("para.condicao");
        BasicBlock blocoEntrada = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("para.entrada");
        BasicBlock blocoSaida = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("para.saida");
        
        nopara.getInicializacao().aceitar(this);
        construtor.buildBr(blocoEntrada);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoCondicao);
        blocoEscopo = blocoCondicao;
        Value condicao = (Value)nopara.getCondicao().aceitar(this);
        construtor.buildCondBr(condicao, blocoEntrada, blocoSaida);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoEntrada);
        blocoEscopo = blocoEntrada;
        for (NoBloco bloco : nopara.getBlocos()) {
            bloco.aceitar(this);
        }
        
        nopara.getIncremento().aceitar(this);
        construtor.buildBr(blocoCondicao);
        
        construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoSaida);        
        blocoEscopo = blocoSaida;
        return null;
    }

    @Override
    public Object visitar(NoPare nopare) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoReal noreal) throws ExcecaoVisitaASA {
        return TypeRef.doubleType().constReal(noreal.getValor());
    }

    @Override
    public Object visitar(NoReferenciaMatriz nrm) throws ExcecaoVisitaASA {
        Value value = escopo.referenciar(nrm.getNome());
        Value linha = (Value)nrm.getLinha().aceitar(this);
        Value coluna = (Value)nrm.getColuna().aceitar(this);
        Value acessoLinha = construtor.buildMul(linha, (Value)((NoDeclaracaoMatriz)nrm.getOrigemDaReferencia()).getNumeroColunas().aceitar(this), "");
        Value indice = construtor.buildAdd(acessoLinha, coluna, "");
        return construtor.buildGEP(value, indice, 1, "");
    }

    @Override
    public Object visitar(NoReferenciaVariavel nrv) throws ExcecaoVisitaASA {
        Value referencia = this.escopo.referenciar(nrv.getNome());
        if(referencia == null) {
            String mensagem = String.format("Erro ao referenciar a váriavel %s", nrv.getNome());
            throw new ExcecaoVisitaASA(mensagem, arvoreSintaticaAbstrata, nrv);
        }
        if(nrv.getOrigemDaReferencia() instanceof NoDeclaracaoParametro) return referencia;
        return construtor.buildLoad(referencia, nrv.getNome());
    }

    @Override
    public Object visitar(NoReferenciaVetor nrv) throws ExcecaoVisitaASA {
        Value value = escopo.referenciar(nrv.getNome());
        Value indice = (Value)nrv.aceitar(this);
        construtor.buildGEP(value, indice, 1, "");
        return null;
    }

    @Override
    public Object visitar(NoRetorne nr) throws ExcecaoVisitaASA {
        Value value = (Value)nr.getExpressao().aceitar(this);
        construtor.buildRet(value);
        return value;
    }

    @Override
    public Object visitar(NoSe nose) throws ExcecaoVisitaASA {
        BasicBlock blocoCondicao = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("condicao");
        BasicBlock blocoSe = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("se");
        BasicBlock blocoSeNao = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("senao");
        BasicBlock blocoSaida = this.blocoEscopo.getBasicBlockParent().appendBasicBlock("saida");
        
        construtor.positionBuilderAtEnd(blocoCondicao
        );
        Value condicao = (Value)nose.getCondicao().aceitar(this);
        construtor.buildCondBr(condicao, blocoSe, blocoSeNao);
        
        construtor.buildBr(blocoSaida);
        
        //construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoSe);
        blocoEscopo = blocoSe;
        for (NoBloco bloco : nose.getBlocosVerdadeiros()) {
            bloco.aceitar(this);
        }
        
        construtor.buildBr(blocoSaida);
        
        //construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoSeNao);
        blocoEscopo = blocoSeNao;
        if(nose.getBlocosFalsos() != null)
            for (NoBloco bloco : nose.getBlocosFalsos()) {
                bloco.aceitar(this);
            }
        
        construtor.buildBr(blocoSaida);
        
        //construtor.clearInsertionPosition();
        construtor.positionBuilderAtEnd(blocoSaida);
        blocoEscopo = blocoSaida;
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
        Biblioteca biblioteca;
        biblioteca = gerenciadorBibliotecas.getBiblioteca(nib.getNome());
        if(biblioteca == null) 
            throw new ExcecaoVisitaASA(String.format("Biblioteca %s não suportada!", nib.getNome()), arvoreSintaticaAbstrata, nib);
        
        pacotes.put(nib.getAlias(), biblioteca.getNomePacote());
        biblioteca.inicializar(module);
        if(biblioteca instanceof BibliotecaComEscopo) ((BibliotecaComEscopo)biblioteca).inicializarEscopo(module, escopo);
        return null;
    }

    public Module getLLVM() {
        return this.module;
    }

    private Object operacao(LLVMLibrary.LLVMIntPredicate llvmIntPredicate, Value valueEsquerdo, Value valueDireito, NoBloco noBloco) throws ExcecaoVisitaASA {
        for (LLVMLibrary.LLVMTypeKind tipoDireito : valueDireito.typeOf().getTypeKind()) {
            for (LLVMLibrary.LLVMTypeKind tipoEsquerdo : valueEsquerdo.typeOf().getTypeKind()) {
                if((tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind) || (tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind))
                    return construtor.buildFCmp(convertPredicate(llvmIntPredicate) , valueEsquerdo, valueDireito, "");
                
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind)
                    return construtor.buildICmp(llvmIntPredicate , valueEsquerdo, valueDireito, "");
                
                if(tipoDireito == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind && tipoEsquerdo == LLVMLibrary.LLVMTypeKind.LLVMDoubleTypeKind)
                    return construtor.buildFCmp(convertPredicate(llvmIntPredicate) , valueEsquerdo, valueDireito, "");
            }
        }
        
        return construtor.buildFCmp(convertPredicate(llvmIntPredicate), 
                Util.convertToInteger(construtor, valueEsquerdo), 
                Util.convertToInteger(construtor, valueDireito), "");
    }

    private IntValuedEnum<LLVMLibrary.LLVMRealPredicate> convertPredicate(LLVMLibrary.LLVMIntPredicate llvmIntPredicate) throws ExcecaoVisitaASA {
        switch(llvmIntPredicate) {
            case LLVMIntEQ:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealOEQ;
            case LLVMIntSGE:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealOGE;
            case LLVMIntNE:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealONE;
            case LLVMIntSGT:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealOGT;
            case LLVMIntSLE:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealOLE;
            case LLVMIntSLT:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealOLT;
            case LLVMIntUGE:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealUGE;
            case LLVMIntUGT:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealUGT;
            case LLVMIntULE:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealULE;            
            case LLVMIntULT:
                return LLVMLibrary.LLVMRealPredicate.LLVMRealULT;
        }
        
        throw new ExcecaoVisitaASA("Erro ao converter tipos", arvoreSintaticaAbstrata, null);
    }

}

class Util {
    
    public static TypeRef convertType(TipoDado tipoDado) {
        switch(tipoDado){
            case INTEIRO:
                return TypeRef.int32Type();
            case CADEIA:
                return TypeRef.int8Type().pointerType();
            case LOGICO:
                return TypeRef.int1Type();
            case REAL:
                return TypeRef.doubleType();
            case VAZIO:
                return TypeRef.voidType();
            default:
                return TypeRef.voidType();
        }
    }
    
    public static boolean hasType(Value value, TypeRef isType) {
        return hasType(value, isType.getTypeKind());
    }
    
    public static boolean hasType(Value value, LLVMLibrary.LLVMTypeKind isType) {
        for (LLVMLibrary.LLVMTypeKind type : value.typeOf().getTypeKind()) {
            if(type == isType)
                return true;
        }
        
        return false;
    }
    
    public static boolean hasType(Value value, IntValuedEnum<LLVMLibrary.LLVMTypeKind> isType) {
        LLVMLibrary.LLVMTypeKind type = null;
        Iterator<LLVMLibrary.LLVMTypeKind> i = isType.iterator();
        while (i.hasNext()) {
            type = i.next();
        }
        
        for (LLVMLibrary.LLVMTypeKind t : value.typeOf().getTypeKind()) {
            if(t == type)
                return true;
        }
        
        return false;
    }

    public static Value convertToInteger(Builder construtor, Value value){
        return convertTo(construtor, value, TypeRef.int32Type());
    }
    
    public static Value convertToDouble(Builder construtor, Value value) {
        return convertTo(construtor, value, TypeRef.doubleType());
    }
    
    public static Value convertTo(Builder construtor, Value value, TypeRef type)
    {
        if(hasType(value, type.getTypeKind()))
            return value;
        
        if(hasType(value, LLVMLibrary.LLVMTypeKind.LLVMIntegerTypeKind))
            return construtor.buildUIToFP(value, type.type(), "");
        
        if(hasType(value, LLVMLibrary.LLVMTypeKind.LLVMFloatTypeKind))
            return construtor.buildFPToUI(value, type.type(), "");
        
        return null;
    }
}

class AssinarFuncoes extends VisitanteASABasico{

    private final ArvoreSintaticaAbstrataPrograma asa;
    private final Module modulo;

    public AssinarFuncoes(Module modulo, ArvoreSintaticaAbstrataPrograma asa) {
        this.asa = asa;
        this.modulo = modulo;
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao declaracaoFuncao) throws ExcecaoVisitaASA {
        TypeRef tipoRetorno = Util.convertType(declaracaoFuncao.getTipoDado());
        
        List<TypeRef> parametros = new ArrayList<>();
        
        for (NoDeclaracaoParametro parametro : declaracaoFuncao.getParametros()) {
            parametros.add(Util.convertType(parametro.getTipoDado()));
        }
        
        modulo.addFunction(declaracaoFuncao.getNome(), TypeRef.functionType(tipoRetorno, false, parametros.toArray(new TypeRef[parametros.size()])));
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoVariavel noDeclaracaoVariavel) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoParametro noDeclaracaoParametro) throws ExcecaoVisitaASA {
        return null;
    }    
}

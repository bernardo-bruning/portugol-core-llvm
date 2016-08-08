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
import br.univali.portugol.nucleo.execucao.Depurador;
import java.util.List;
import org.llvm.Module;
import org.llvm.TypeRef;
import org.llvm.Value;
import org.llvm.binding.LLVMLibrary;

/**
 *
 * @author Bernardo
 */
public class Compilador implements VisitanteASA {

    private final Programa programa;
    private final Module module;

    public Compilador(String source) throws ErroCompilacao, ExcecaoVisitaASA {
        this.programa = Portugol.compilar(source);
        this.module = Module.createWithName("programa");
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
        return null;
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
        return null;
    }

    @Override
    public Object visitar(NoContinue nc) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoFuncao ndf) throws ExcecaoVisitaASA {
        Value function = this.module.addFunction(ndf.getNome(), TypeRef.functionType(TypeRef.int32Type(), TypeRef.int32Type()));
        function.setFunctionCallConv(LLVMLibrary.LLVMCallConv.LLVMCCallConv);
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoMatriz ndm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoVariavel ndv) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoDeclaracaoVetor ndv) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoEnquanto ne) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoEscolha ne) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoFacaEnquanto nfe) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoInteiro ni) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoLogico nl) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoMatriz nm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoMenosUnario nmu) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoNao nonao) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaIgualdade noli) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaDiferenca nold) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoAtribuicao noa) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaE nole) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaOU nolou) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaMaior nolm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaMaiorIgual nolmi) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaMenor nolm) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoLogicaMenorIgual nolmi) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoSoma nos) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoSubtracao nos) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoDivisao nod) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoMultiplicacao nom) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoModulo nom) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoBitwiseLeftShift nobls) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoBitwiseRightShift nobrs) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoBitwiseE nobe) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoBitwiseOu nobo) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoOperacaoBitwiseXOR nobxor) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoBitwiseNao nbn) throws ExcecaoVisitaASA {
        return null;
    }

    @Override
    public Object visitar(NoPara nopara) throws ExcecaoVisitaASA {
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

    private TypeRef conversorTipo(TipoDado tipoDado) {
        return null;
    }
}

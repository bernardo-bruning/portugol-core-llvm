package portugol.core.llvm;

import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.llvm.Module;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Bernardo
 */
public class CompiladorTeste {
    public static String fileTest = "programa";
    public static String biteCodeExtension = ".bc";
    public static String portugolExtension = ".por";
    public static String llvmExtension = ".ll";

    @Test
    public void simplesTeste() throws Exception {
        testarArquivo("simple");
    }
    
    @Test
    public void cadeiaTeste() throws Exception {
        testarArquivo("string");
    }
    
    @Test
    public void declararExpressaoAdicaoTest() throws Exception {
        testarArquivo("declarar-expressao-adicao-simples");
    }
    
    @Test
    public void declararExpressaoComplexaTest() throws Exception {
        testarArquivo("declaracao-expressao-complexa");
    }
    
    @Test
    public void declararInteiroTest() throws Exception {
        testarArquivo("declarar-inteiro");
    }
    
    @Test
    public void operacaoLogicaTest() throws Exception {
        testarArquivo("operacao-logica");
    }
    
    @Test
    public void lacoRepeticaoEnquanto() throws Exception {
        testarArquivo("laco-repeticao-enquanto");
    }
    
    @Test
    public void lacoRepeticaoFacaEnquanto() throws Exception {
        testarArquivo("laco-repeticao-faca-enquanto");
    }
    
    @Test
    public void lacoRepeticaoPara() throws Exception {
        testarArquivo("laco-repeticao-para");
    }
    
    @Test
    public void se() throws Exception {
        testarArquivo("se");
    }
    
    private void testarArquivo(String fileName) throws FileNotFoundException, ErroCompilacao, ExcecaoVisitaASA {
        String codigoEntrada = obterCodigoArquivo("test/" + fileName + portugolExtension);
        String codigoSaida = obterCodigoArquivo("test/" + fileName + llvmExtension);
        Compilador compilador = new Compilador(codigoEntrada);
        Module module = compilador.getLLVM();
        module.dumpModule();
        module.writeBitcodeToFile(fileTest);
        assertFile(codigoSaida);
    }
    
    private String obterCodigoArquivo(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String codigo = scanner.useDelimiter("\\A").next();
        return codigo;
    }
    
    private void assertFile(String expected) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "llvm-dis", fileTest);
            pb.start();
            
            TimeUnit.SECONDS.sleep(1);
            Scanner scanner = new Scanner(new File(fileTest.concat(llvmExtension)));
            String llvmCode = scanner.useDelimiter("\\A").next();
            scanner.close(); // Put this call in a finally block

            assertEquals(expected.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", ""), llvmCode.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", ""));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.bridj.BridJ;
import static org.junit.Assert.assertEquals;
import org.llvm.Module;
import org.llvm.Value;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import portugol.core.llvm.Compilador;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Bernardo
 */
public class CompiladorTeste {
    public static String fileTest = "programa";
    public static String biteCodeExtension = ".bc";
    public static String llvmExtension = ".ll";
    
    public CompiladorTeste() {
    }

     @Test
     public void programaSemNadaNoInicio() throws ErroCompilacao, ExcecaoVisitaASA, FileNotFoundException {
        String fonte = "programa\n" +
"{\n" +
"	funcao inicio()\n" +
"	{\n" +
"	}\n" +
"}";
        String output = "; ModuleID = 'programa'\n\n" +
"declare i32 @main(i32)\n";
      
        System.out.println("Teste");
        
        Compilador compilador;
        compilador = new Compilador(fonte);
        Module module = compilador.getLLVM();
        Value func = module.getFirstFunction();
        
        module.dumpModule();
        module.writeBitcodeToFile(fileTest);
        
        assertEquals(func.getValueName(), "main");
        assertFile(output);
     }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    private void assertFile(String expected) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "llvm-dis", fileTest);
            pb.start();
            
            TimeUnit.SECONDS.sleep(1);
            Scanner scanner = new Scanner(new File(fileTest.concat(llvmExtension)), "UTF-8" );
            String llvmCode = scanner.useDelimiter("\\A").next();
            scanner.close(); // Put this call in a finally block

            assertEquals(llvmCode, expected);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}

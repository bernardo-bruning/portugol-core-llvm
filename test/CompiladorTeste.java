/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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

/**
 *
 * @author Bernardo
 */
public class CompiladorTeste {
    public static String fileTest = "programa";
    public static String biteCodeExtension = ".bc";
    public static String portugolExtension = ".por";
    public static String llvmExtension = ".ll";
    
    public CompiladorTeste() {
    }

    @Test
    public void programaSemNadaNoInicio() throws ErroCompilacao, ExcecaoVisitaASA, FileNotFoundException {
       Map<String, String> fontes = getTestes(portugolExtension);
       Map<String, String> expecteds = getTestes(llvmExtension);
       
        for (Map.Entry<String, String> entry : fontes.entrySet()) {
            String nome = entry.getKey();
            String fonte = entry.getValue();
            
            String expected = expecteds.get(nome);
            
            Compilador compilador;
            compilador = new Compilador(fonte);
            Module module = compilador.getLLVM();
            Value func = module.getFirstFunction();

            module.dumpModule();
            module.writeBitcodeToFile(fileTest);

//            assertEquals(func.getValueName(), "main");
            assertFile(expected);
        }
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

    private Map<String, String> getTestes(String extensions) throws FileNotFoundException {
        File folder = new File("test");
        File[] listOfFiles = folder.listFiles();
        Map<String, String> files = new HashMap<>();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() & listOfFiles[i].getName().contains(extensions)) {
                Scanner scanner = new Scanner(listOfFiles[i]);
                String fonte = scanner.useDelimiter("\\A").next();
                files.put(listOfFiles[i].getName().replaceFirst(extensions, ""), fonte);
                scanner.close();                
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        
        return files;
    }
    
    private void assertFile(String expected) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "llvm-dis", fileTest);
            pb.start();
            
            TimeUnit.SECONDS.sleep(1);
            Scanner scanner = new Scanner(new File(fileTest.concat(llvmExtension)));
            String llvmCode = scanner.useDelimiter("\\A").next();
            scanner.close(); // Put this call in a finally block

            assertEquals(expected.replace("\n", "").replace("\r", ""), llvmCode.replace("\n", "").replace("\r", ""));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}

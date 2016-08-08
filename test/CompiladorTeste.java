/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.llvm.Module;
import org.llvm.Value;
import portugol.core.llvm.Compilador;

/**
 *
 * @author Bernardo
 */
public class CompiladorTeste {
    
    public CompiladorTeste() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void declaraFuncaoInicioTeste() throws ErroCompilacao, ExcecaoVisitaASA{
        String fonte = "programa\n" +
"{\n" +
"	funcao inicio()\n" +
"	{\n" +
"	}\n" +
"}";
        
        Compilador compilador;
        compilador = new Compilador(fonte);
        Module module = compilador.getLLVM();
        Value func = module.getFirstFunction();
        
        assertEquals(func.getValueName(), "inicio");
    }
}

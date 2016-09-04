programa 
{
        inclua biblioteca Graficos --> g
	funcao inicio () 
	{ 
            g.iniciar_modo_grafico(falso)
            inteiro cor = g.criar_cor(20, 20, 20)
            g.definir_cor(cor)
            g.desenhar_ponto(10, 10)
            g.encerrar_modo_grafico()
	} 
}
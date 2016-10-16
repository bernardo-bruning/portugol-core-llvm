programa 
{
        inclua biblioteca Graficos --> g
	inclua biblioteca Teclado --> t
	inclua biblioteca Util --> u
	inclua biblioteca Tipos --> tp
	inclua biblioteca Matematica --> m
	funcao inicio () 
	{ 
            g.iniciar_modo_grafico(falso)
            inteiro largura g.largura_janela()
            inteiro cor = g.criar_cor(20, 20, 20)
            g.definir_cor(cor)
            g.desenhar_ponto(10, 10)
            g.encerrar_modo_grafico()
	} 
}
programa 
{
        inclua biblioteca Graficos --> g
        funcao dois_param(real numero, inteiro numero2)
        {
            
        }


        funcao teste(real numero)
        {
            inteiro t = numero
            se(t > 100) {
                g.desenhar_porcao_imagem(0, 0, 0, 0, 0, 0, 0)
            } senao {
            }
        }

	funcao inicio() 
	{		
            inteiro numero = 1.43+1
            teste(numero)
            dois_param(numero, numero)
	}
}


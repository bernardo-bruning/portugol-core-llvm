programa 
{
        inteiro numero_global = 10
        real real_global = 0.18
        
	funcao inicio () 
	{
            inteiro numero = 10 + numero_global
            //escreva("%d", numero)
            //escreva("%d", numero_global)
            
            numero_global += 10
            segunda_operacao()
	}

        funcao segunda_operacao()
        {
            //escreva("%d", numero_global)
            inteiro numero = 4
            numero_global += numero
            //escreva("%d", numero_global)
        }
}
programa 
{
        inteiro numero_global = 5
	funcao inicio () 
	{
            inteiro numero = 10 + numero_global
            escreva("%d", numero_global)
            escreva("%d", numero)

            segunda_operacao()
	}

        funcao segunda_operacao()
        {
            numero_global += 10
            escreva("%d", numero_global)
        }
}
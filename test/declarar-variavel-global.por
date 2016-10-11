programa 
{
        inclua biblioteca Util --> u
        inteiro numero_global = 5
	funcao inicio () 
	{
            inteiro numero = 10 + numero_global
            escreva("%d", numero_global)
            escreva("%d", numero)
            numero_global += 10
            segunda_operacao()
	}

        funcao segunda_operacao()
        {
            escreva("%d", numero_global)
            inteiro numero = u.sorteia(0, 100)
            numero_global += numero
            escreva("%d", numero_global)
        }
}
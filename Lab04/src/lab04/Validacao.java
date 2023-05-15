package lab04;

public class Validacao 
{
    // Verificar se o CPF informado e valido
    public static boolean validaCPF(String cpf)
    {
        // retira tudo que nao for digito
        cpf = cpf.replaceAll("[^0-9]+","");
        if (cpf.length() != 11) return false;
        // Dígitos não podem ser todos iguais
        boolean digitos_iguais = caracteresTodosIguais(cpf);
        if (digitos_iguais) return false;
        // Digitos verificadores
        boolean primDigito = verificaDigitoCPF(cpf, 1);
        if (!primDigito) return false;
        boolean segDigito = verificaDigitoCPF(cpf, 2);
        if (!segDigito) return false;
        return true;
    }

    // Implementa o algoritmo de verificacao dos digitos do cpf
    private static boolean verificaDigitoCPF(String cpf, int digito)
    {
        // CPF deve ter 11 dígitos 
        // digito 1 ou 2 (1o ou 2o digito)
        int digitoFinal = 0, soma = 0, verifica = 0;

        if (digito == 1) digitoFinal = 9;
        else if (digito == 2) digitoFinal = 10;

        for (int i = 0; i < digitoFinal; i ++)
            soma += (int)(cpf.charAt(i) - 48) * (digitoFinal + 1 - i);

        verifica = (soma * 10) % 11;
        if (verifica == 10) verifica = 0;
        if ((cpf.charAt(digitoFinal) - 48) != verifica) return false;
        return true;
    }

    // Verificar se o CNPJ informado e valido
    public static boolean validaCNPJ(String cnpj)
    {
        // retira tudo que nao for digito
        cnpj = cnpj.replaceAll("[^0-9]+","");
        if (cnpj.length() != 14) return false;
        // Dígitos não podem ser todos iguais
        boolean digitos_iguais = caracteresTodosIguais(cnpj);
        if (digitos_iguais) return false;
        // Digitos verificadores
        boolean primDigito = verificaDigitoCNPJ(cnpj, 1);
        if (!primDigito) return false;
        boolean segDigito = verificaDigitoCNPJ(cnpj, 2);
        if (!segDigito) return false;
        return true;
    }

    // Implementa o algoritmo de verificacao dos digitos do cnpj
    private static boolean verificaDigitoCNPJ(String cnpj, int digito)
    {
        // CNPJ deve ter 15 dígitos 
        // digito 1 ou 2 (1o ou 2o digito)
        int digitoFinal = 0, soma = 0, verifica = 0;

        if (digito == 1)
        {
            digitoFinal = 12;
            int[] numeros = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < digitoFinal; i ++)
                soma += (int)(cnpj.charAt(i) - 48) * (numeros[i]);
        }
        else if (digito == 2) 
        {
            digitoFinal = 13;
            int[] numeros = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < digitoFinal; i ++)
                soma += (int)(cnpj.charAt(i) - 48) * (numeros[i]);
        }
        verifica = soma % 11;
        if (verifica < 2) verifica = 0;
        else verifica = 11 - verifica;

        if ((cnpj.charAt(digitoFinal) - 48) != verifica) return false;
        return true;
    }
    
    // Verifica se a string e formada apenas por um caracter
    private static boolean caracteresTodosIguais(String nome)
    {
        // Compara com o 1o caracter
        int digitos_iguais = 1;
        for (int i = 1; i < nome.length(); i++)
            if (nome.charAt(i) == nome.charAt(0)) digitos_iguais++;
        if (digitos_iguais != nome.length()) return false;
        return true;
    }

    // se true, entao o nome eh valido
    public static boolean validarNome(String nome)
    {
        String verificavel = nome.replaceAll("[^A-Z,^a-z,^\s]", nome);
        return verificavel.equals(nome);
    }

    public static boolean validarTipoCliente(String tipo)
    {
        if (tipo.equals("PF") || tipo.equals("PJ")) return true;
        return false;
    }
}

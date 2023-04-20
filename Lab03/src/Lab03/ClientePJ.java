package Lab03;

import java.util.Calendar;

public class ClientePJ extends Cliente
{
    private final String CNPJ;
    private Calendar dataFundacao;

    // Construtor: chamar apenas com cnpj válido
    public ClientePJ(String nome, String endereco, String CNPJ, Calendar dataFundacao)
    {
        super(nome, endereco);
        this.setDataFundacao(dataFundacao);
        this.CNPJ = CNPJ;
    }

    public String getCNPJ()
    {
        return CNPJ;
    }

    public Calendar getDataFundacao()
    {
        return dataFundacao;
    }

    public void setDataFundacao(Calendar dataFundacao)
    {
        this.dataFundacao = dataFundacao;
    }

    // Verificar se o CNPJ informado e valido
    public static boolean validarCNPJ(String cnpj)
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

    private String imprimeData(Calendar data)
    {
        String str;
        str = Integer.toString(data.get(Calendar.DATE)) + "/" +
            Integer.toString(data.get(Calendar.MONTH)) + "/" +
            Integer.toString(data.get(Calendar.YEAR));
        return str;
    }

    public String toString()
    {
        String str;
        str = "Dados do cliente:\n" + "Nome do cliente: " + getNome() + 
                "\nEndereco: " + getEndereco() + "\nCNPJ: " + getCNPJ() + 
                "\nVeiculos: " + listaVeiculos() +
                "\nData de fundacao: " + imprimeData(dataFundacao);
        return str;
    }        
}
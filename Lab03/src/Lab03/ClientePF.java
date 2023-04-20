package Lab03;

import java.util.Calendar;

public class ClientePF extends Cliente
{
    private Calendar dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private final String CPF;
    private Calendar dataNascimento;

    // Construtor: chamar apenas com cpf válido
    public ClientePF(String nome, String endereco, Calendar dataLicenca,
                String educacao, String genero, String classeEconomica,
                String CPF, Calendar dataNascimento)
    {
        super(nome, endereco);
        this.setDataLicenca(dataLicenca);
        this.setEducacao(educacao);
        this.setGenero(genero);
        this.setClasseEconomica(classeEconomica);
        this.setDataNascimento(dataNascimento);
        this.CPF = CPF;
    }

    // Getters e setters
    public String getCPF()
    {
        return CPF;
    }

    public Calendar getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public Calendar getDataLicenca()
    {
        return dataLicenca;
    }

    public void setDataLicenca(Calendar dataLicenca)
    {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() 
    {
        return educacao;
    }
    
    public void setEducacao(String educacao)
    {
        this.educacao = educacao;
    }

    public String getGenero() 
    {
        return genero;
    }
    
    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    public String getClasseEconomica() 
    {
        return classeEconomica;
    }
    
    public void setClasseEconomica(String classeEconomica)
    {
        this.classeEconomica = classeEconomica;
    }

    // Verificar se o CPF informado e valido
    public static boolean validarCPF(String cpf)
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
                "\nEndereco: " + getEndereco() + "\nCPF: " + getCPF() + 
                "\nVeiculos: " + listaVeiculos() +
                "\nData da licenca: " + imprimeData(dataLicenca) + 
                "\nData de nascimento: " + imprimeData(dataNascimento) + 
                "\nGenero: " + getEndereco() + "\nEducacao: " + getEducacao() + 
                "\nClasse economica: " + getClasseEconomica();
        return str;
    }
}

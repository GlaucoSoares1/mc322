package lab04;

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

    @Override
    public double calculaScore()
    {
        long dataHoje = Calendar.getInstance().getTime().getTime();
        long nascimento = dataNascimento.getTime().getTime();
        long diferenca = dataHoje - nascimento;
        long diferencaAnos = (diferenca / (1000*60*60*24*365));

        double base = CalcSeguro.VALOR_BASE.getOperacao();

        if (diferencaAnos >= 18 && diferencaAnos < 30)
        {
            double fatorIdade = CalcSeguro.FATOR_18_30.getOperacao();
            return base * fatorIdade * getVeiculos().size();
        }
        else if (diferencaAnos >= 30 && diferencaAnos < 60)
        {
            double fatorIdade = CalcSeguro.FATOR_30_60.getOperacao();
            return base * fatorIdade * getVeiculos().size();
        }
        else if (diferencaAnos >= 60)
        {
            double fatorIdade = CalcSeguro.FATOR_60_90.getOperacao();
            return base * fatorIdade * getVeiculos().size();
        }
        return 0;
    }

    private String imprimeData(Calendar data)
    {
        String str;
        str = Integer.toString(data.get(Calendar.DATE)) + "/" +
            Integer.toString(data.get(Calendar.MONTH)) + "/" +
            Integer.toString(data.get(Calendar.YEAR));
        return str;
    }
    
    @Override
    public String toString()
    {
        String str;
        str = "Dados do cliente:\n" + "Nome do cliente: " + getNome() + 
                "\nEndereco: " + getEndereco() + "\nCPF: " + getCPF() + 
                "\nVeiculos: " + listarVeiculos() +
                "Data da licenca: " + imprimeData(dataLicenca) + 
                "\nData de nascimento: " + imprimeData(dataNascimento) + 
                "\nGenero: " + getEndereco() + "\nEducacao: " + getEducacao() + 
                "\nClasse economica: " + getClasseEconomica();
        return str;
    }
}

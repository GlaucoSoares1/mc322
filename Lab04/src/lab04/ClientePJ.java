package lab04;

import java.util.Calendar;

public class ClientePJ extends Cliente
{
    private final String CNPJ;
    private Calendar dataFundacao;
    private int quantFuncionarios;

    // Construtor: chamar apenas com cnpj válido
    public ClientePJ(String nome, String endereco, String CNPJ, Calendar dataFundacao, int quantFuncionarios)
    {
        super(nome, endereco);
        this.setDataFundacao(dataFundacao);
        this.setQuantFuncionarios(quantFuncionarios);
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

    public int getQuantFuncionarios()
    {
        return quantFuncionarios;
    }

    public void setQuantFuncionarios(int quantFuncionarios)
    {
        this.quantFuncionarios = quantFuncionarios;
    }

    @Override
    public double calculaScore()
    {
        int quantCarros = this.getVeiculos().size();
        double base = CalcSeguro.VALOR_BASE.getOperacao();
        return base * (1 + quantFuncionarios / 100) * quantCarros;
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
                "\nVeiculos: " + listarVeiculos() +
                "Data de fundacao: " + imprimeData(dataFundacao);
        return str;
    }        
}

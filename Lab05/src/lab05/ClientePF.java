package lab05;

import java.util.Calendar;
import java.util.ArrayList;

public class ClientePF extends Cliente
{
    private Calendar dataLicenca;
    private String educacao;
    private String genero;
    private final String CPF;
    private Calendar dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor: chamar apenas com cpf válido
    public ClientePF(String nome, String endereco, String telefone, String email, 
                Calendar dataLicenca, String educacao, String genero, 
                String CPF, Calendar dataNascimento)
    {
        super(nome, endereco, telefone, email);
        setDataLicenca(dataLicenca);
        setEducacao(educacao);
        setGenero(genero);
        setDataNascimento(dataNascimento);
        this.CPF = CPF;
        listaVeiculos = new ArrayList<Veiculo>();
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

    public ArrayList<Veiculo> getListaVeiculos()
    {
        return listaVeiculos;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo, Seguradora seg)
    {
        boolean existe = listaVeiculos.contains(veiculo);
        if (!existe)
        {
            listaVeiculos.add(veiculo);
            ArrayList<Seguro> seguros = seg.getListaSeguroPorCliente(getCPF());
            for (Seguro seguro: seguros) seguro.calcularValor();
            return true;
        }
        return false;
    }

    public boolean removerVeiculo(String placa, Seguradora seg)
    {
        Veiculo veiculo = procuraVeiculo(placa);
        if (veiculo == (null)) return false;
        listaVeiculos.remove(veiculo);
        ArrayList<Seguro> seguros = seg.getListaSeguroPorCliente(getCPF());
        for (Seguro seguro: seguros) seguro.calcularValor();
        return true;
    }

    public Veiculo procuraVeiculo(String placa)
    {
        Veiculo veiculoProcurado = null;
        for (Veiculo veiculo: listaVeiculos)
            if (veiculo.getPlaca().equals(placa)) return veiculo;
        return veiculoProcurado;
    }

    public String listarVeiculos()
    {
        String str = "";
        for (int i = 0; i < listaVeiculos.size(); i++)
            str += "\nVeiculo " + (i + 1) + ":\n\t" + listaVeiculos.get(i).toString() + "\n";
        return str;
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
        str = "Dados do cliente:\n" + super.toString() + "\nCPF: " + getCPF() + 
                "\nVeiculos: " + listarVeiculos() +
                "Data da licenca: " + imprimeData(dataLicenca) + 
                "\nData de nascimento: " + imprimeData(dataNascimento) + 
                "\nGenero: " + getEndereco() + "\nEducacao: " + getEducacao();
        return str;
    }
}

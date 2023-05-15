package lab04;

import java.util.ArrayList;

public abstract class Cliente 
{
    private String nome;
    private String endereco;
    private double valorSeguro;
    private int quantSinistros;
    private ArrayList<Veiculo> veiculos;

    // Construtor
    public Cliente(String nome, String endereco)
    {
        this.setNome(nome);
        this.setEndereco(endereco);
        this.valorSeguro = 0;
        this.quantSinistros = 0;
        this.veiculos = new ArrayList<Veiculo>();
    }

    // Getters e setters
    public String getNome() 
    {
        return nome;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEndereco() 
    {
        return endereco;
    }
    
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public double getValorSeguro() 
    {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) 
    {
        this.valorSeguro = valorSeguro;
    }

    public int getQuantSinistros() 
    {
        return quantSinistros;
    }

    public void adicionarSinistro()
    {
        quantSinistros ++;
    }

    public void retirarSinistro()
    {
        if (quantSinistros > 0) quantSinistros --;
    }

    public ArrayList<Veiculo> getVeiculos()
    {
        return veiculos;
    }

    public Veiculo procuraVeiculo(String placa)
    {
        Veiculo veiculoProcurado = null;
        for (Veiculo veiculo: veiculos)
        {
            if (veiculo.getPlaca().equals(placa)) veiculoProcurado = veiculo;
            break;
        }
        return veiculoProcurado;
    }

    public abstract double calculaScore();

    public String listarVeiculos()
    {
        String str;
        str = "";
        for (int i = 0; i < veiculos.size(); i++)
            str += "\nVeiculo " + (i + 1) + ":\n\t" + veiculos.get(i).toString() + "\n";
        return str;
    }

    @Override
    public String toString()
    {
        String str;
        str = "Nome do cliente: " + getNome() + "\nEndereco: " + getEndereco() + 
            "\nLista de veiculos: " + listarVeiculos();
        return str;
    }
}

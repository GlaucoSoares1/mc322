package Lab03;
import java.util.ArrayList;

public class Cliente 
{
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> veiculos;

    // Construtor
    public Cliente(String nome, String endereco)
    {
        this.setNome(nome);
        this.setEndereco(endereco);
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

    // Veiculos
    public void insereVeiculo(Veiculo veiculo)
    {
        veiculos.add(veiculo);
    }

    public void removeVeiculo(Veiculo veiculo)
    {
        veiculos.remove(veiculo);
    }

    public Veiculo procuraVeiculo(String placa)
    {
        Veiculo veiculoProcurado = null;
        for (Veiculo veiculo: veiculos)
            if (veiculo.getPlaca().equals(placa)) veiculoProcurado = veiculo;
        return veiculoProcurado;
    }

    public String listaVeiculos()
    {
        String str;
        str = "Lista de veiculos:";
        for (int i = 0; i < veiculos.size(); i++)
            str += "\nVeiculo " + (i + 1) + ":\n\t" + veiculos.get(i).toString() + "\n";
        return str;
    }

    public String toString()
    {
        String str;
        str = "Nome do cliente: " + getNome() + "\nEndereco: " + getEndereco() + 
            "\nLista de veiculos: " + listaVeiculos();
        return str;
    }
}

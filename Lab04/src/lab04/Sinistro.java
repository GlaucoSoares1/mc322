package lab04;

import java.util.Calendar;
import java.util.Random;

public class Sinistro 
{
	private final int ID;
	private Calendar data;
	private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
	private Cliente cliente;
	
	// Construtor
	public Sinistro(Calendar data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente)
	{
		this.ID = gerarID(seguradora);
		this.setData(data);
		this.setEndereco(endereco);
		this.setSeguradora(seguradora);
		this.setVeiculo(veiculo);
		this.setCliente(cliente);
	}

	// Getters e setters
	public int getId()
	{
		return ID;
	}

	public Calendar getData()
	{
		return data;
	}
	
	public void setData(Calendar data)
	{
		this.data = data;
	}

	public String getEndereco()
	{
		return endereco;
	}
	
	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}

	public Seguradora getSeguradora()
	{
		return seguradora;
	}
	
	public void setSeguradora(Seguradora seguradora)
	{
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo()
	{
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo)
	{
		this.veiculo = veiculo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}
	
	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	// Numero aleatorio
	private int gerar_int_aleatorio()
	{
		Random random = new Random();
		return random.nextInt(1, 1000000);
	}

	private int gerarID(Seguradora seguradora)
	{
		int num = gerar_int_aleatorio();
		while (seguradora.getListaSinistro().containsKey(num))
			num = gerar_int_aleatorio();
		return num;
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
        str = "Cliente: " + getCliente().getNome() + "\nId: " + getId() + 
			"\nData: " + imprimeData(getData()) + "\nEndereco: " + getEndereco() + 
			"\nVeiculo: " + getVeiculo() + "\nSeguradora: " + getSeguradora().getNome() + "\n"; 
        return str;
    }
}


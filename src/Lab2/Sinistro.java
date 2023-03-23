package Lab2;
import java.util.Random;

public class Sinistro 
{
	private int id;
	private String data;
	private String endereco;
	
	// Construtor
	public Sinistro(String data, String endereco)
	{
		this.setId();
		this.setData(data);
		this.setEndereco(endereco);
	}

	// Getters e setters
	public int getId()
	{
		return id;
	}
	
	private void setId()
	{
		this.id = gerar_int_aleatorio();
	}

	public String getData()
	{
		return data;
	}
	
	public void setData(String data)
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

	// Numero aleatorio
	private int gerar_int_aleatorio()
	{
		Random random = new Random();
		return random.nextInt(1, 1000000);
	}

	public String toString()
    {
        String str;
        str = "Id: " + getId() + "\nData: " + getData() + "\nEndereco: " + getEndereco();
        return str;
    }
}

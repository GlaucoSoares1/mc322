package Lab06;

import java.util.Calendar;
import java.util.Random;

public class Sinistro 
{
	private final int ID;
	private Calendar data;
	private String endereco;
	private Condutor condutor;
    private Seguro seguro;
	
	// Construtor
	public Sinistro(Calendar data, String endereco, Condutor condutor, Seguro seguro)
	{
		ID = gerarID(seguro);
		setData(data);
		setEndereco(endereco);
		setCondutor(condutor);
		setSeguro(seguro);
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

	public Condutor getCondutor()
	{
		return condutor;
	}

	public void setCondutor(Condutor condutor)
	{
		this.condutor = condutor;
	}

	public Seguro getSeguro()
	{
		return seguro;
	}

	public void setSeguro(Seguro seguro)
	{
		this.seguro = seguro;
	}

	// Numero aleatorio
	private int gerarID(Seguro seguro)
	{
		Random random = new Random();
        int lim = 1000000;
        int numAleatorio = random.nextInt(1, lim);
        while (seguro.getListaSinistros().containsKey(numAleatorio))
            numAleatorio = random.nextInt(1, lim);
        return numAleatorio;
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
        str = "Condutor: " + getCondutor().getNome() + "\nId: " + getId() + 
			"\nData: " + imprimeData(getData()) + "\nEndereco: " + getEndereco() + 
			"\nSeguro: " + getSeguro().getId() + "\n"; 
        return str;
    }
}


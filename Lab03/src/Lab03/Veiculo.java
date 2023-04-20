package Lab03;

public class Veiculo 
{
    private String placa;
	private String marca;
	private String modelo;
    private int anoFabricacao;
	
	// Construtor
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao)
	{
		this.setPlaca(placa);
		this.setMarca(marca);
		this.setModelo(modelo);
        this.setAnoFabricacao(anoFabricacao);
	}
	
	// Getters e setters
	public String getPlaca() 
	{
		return placa;
	}
	
	public void setPlaca(String placa)
	{
		this.placa = placa;
	}
	
	public String getMarca() 
	{
		return marca;
	}
	
	public void setMarca(String marca)
	{
		this.marca = marca;
	}
	
	public String getModelo() 
	{
		return modelo;
	}
	
	public void setModelo(String modelo)
	{
		this.modelo = modelo;
	}

    public int getAnoFabricacao() 
	{
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(int anoFabricacao)
	{
		this.anoFabricacao = anoFabricacao;
	}

	public String toString()
    {
        String string;
        string = "Placa: " + getPlaca() + "\n\tMarca: " + getMarca() + 
				"\n\tModelo: " + getModelo() + "\n\tAno de Fabricacao: " + 
                getAnoFabricacao();
        return string;
    }
}

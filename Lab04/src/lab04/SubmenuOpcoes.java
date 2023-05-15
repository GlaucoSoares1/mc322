package lab04;

//Define as constantes dos submenus
public enum SubmenuOpcoes 
{
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros por seguradora"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculo por seguradora"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculo por cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	//atributo
	private final String descricao;
	
	//Construtor
	SubmenuOpcoes(String descricao)
	{
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() 
	{
		return descricao;
	}
}

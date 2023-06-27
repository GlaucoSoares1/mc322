package Lab06;

//Define as constantes dos submenus
public enum SubmenuOpcoes 
{
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	ATUALIZAR_FROTA("Atualizar frota"),
	AUTORIZAR_CONDUTOR("Autorizar condutor"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
	LISTAR_SEGUROS_CLIENTE("Listar seguros por cliente"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
	LISTAR_VEICULOS_FROTA("Listar veiculos por frota"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_SEGURO("Cancelar seguro"),
	EXCLUIR_CONDUTOR("Desautorizar condutor"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_FROTA("Excluir frota"),
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

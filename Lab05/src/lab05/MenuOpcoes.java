package lab05;

/* enum para menu externo
Cada constante é vista como uma descrição e uma lista de outras constantes 
(que são as constantes do submenu)
new SubmenuOpcoes[]{} cria uma lista de constantes do submenu.
*/
public enum MenuOpcoes 
{
	CADASTROS("Cadastros e atualizações", new SubmenuOpcoes[] {
			SubmenuOpcoes.CADASTRAR_CLIENTE,
			SubmenuOpcoes.CADASTRAR_VEICULO,
			SubmenuOpcoes.CADASTRAR_FROTA,
			SubmenuOpcoes.ATUALIZAR_FROTA,
			SubmenuOpcoes.AUTORIZAR_CONDUTOR,
			SubmenuOpcoes.CADASTRAR_SEGURADORA,
			SubmenuOpcoes.VOLTAR}),
	LISTAR("Listar", new SubmenuOpcoes[] {
			SubmenuOpcoes.LISTAR_CLIENTES,
			SubmenuOpcoes.LISTAR_SINISTROS_CLIENTE,
			SubmenuOpcoes.LISTAR_SEGUROS_CLIENTE,
			SubmenuOpcoes.LISTAR_VEICULOS_CLIENTE,
			SubmenuOpcoes.LISTAR_VEICULOS_FROTA,
			SubmenuOpcoes.VOLTAR}),
	EXCLUIR("Excluir", new SubmenuOpcoes[] {
			SubmenuOpcoes.EXCLUIR_CLIENTE,
			SubmenuOpcoes.EXCLUIR_SEGURO,
			SubmenuOpcoes.EXCLUIR_CONDUTOR,
			SubmenuOpcoes.EXCLUIR_VEICULO,
			SubmenuOpcoes.EXCLUIR_FROTA,
			SubmenuOpcoes.EXCLUIR_SINISTRO,
			SubmenuOpcoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	GERAR_SEGURO("Gerar Seguro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	CONSULTAR_VALOR_SEGURO("Consultar valor do seguro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	VISUALIZAR_SINISTRO("Visualizar sinistro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	SAIR("Sair", new SubmenuOpcoes[] {});
	
	//atributos
	private final String descricao;
	private final SubmenuOpcoes[] submenu;
	
	//Construtor
	MenuOpcoes(String descricao, SubmenuOpcoes[] submenu)
	{
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	//getters
	public String getDescricao() 
	{
		return descricao;
	}
	
	public SubmenuOpcoes[] getSubmenu() 
	{
		return submenu;
	}
}


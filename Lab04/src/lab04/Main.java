package lab04;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main 
{
    // método main ao final da classe

    private static String leCPF(Scanner entrada)
    {
        String cpf = entrada.nextLine();
        boolean cpfValido = Validacao.validaCPF(cpf);
        while (!cpfValido)
        {
            System.out.println("CPF inválido! Digite novamente:");
            cpf = entrada.nextLine();
            cpfValido = Validacao.validaCPF(cpf);
        }
        System.out.println("CPF válido!");
        return cpf;
    }

    private static String leCNPJ(Scanner entrada)
    {
        String cnpj = entrada.nextLine();
        boolean cnpjValido = Validacao.validaCNPJ(cnpj);
        while (!cnpjValido)
        {
            System.out.println("CNPJ inválido! Digite novamente:");
            cnpj = entrada.nextLine();
            cnpjValido = Validacao.validaCNPJ(cnpj);
        }
        System.out.println("CNPJ válido!");
        return cnpj;
    }

    private static Calendar leData(Scanner entrada)
    {
        try
        {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data = entrada.nextLine();
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(formato.parse(data));
            return calendario;
        }
        catch (Exception exception) {}
        return null;
    }

    private static Calendar leData(String data)
    {
        try
        {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(formato.parse(data));
            return calendario;
        }
        catch (Exception exception) {}
        return null;
    }

    private static Seguradora leSeguradora(Scanner entrada)
    {
        System.out.println("Dados da seguradora");
        System.out.println("Digite o nome da seguradora: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o telefone da seguradora: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o email da seguradora: ");
        String email = entrada.nextLine();
        System.out.println("Digite o endereço da seguradora: ");
        String endereco = entrada.nextLine();
        System.out.println();
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        return seguradora;
    }

    private static void cadastrarSeguradora(ArrayList<Seguradora> seguradoras, Scanner entrada)
    {
        Seguradora seguradora = leSeguradora(entrada);
        seguradoras.add(seguradora);
    }

    private static Seguradora procurarSeguradora(ArrayList<Seguradora> seguradoras, String nome)
    {   
        Seguradora seguradoraProcurada = null;
        for (Seguradora seg: seguradoras)
        {
            if (seg.getNome().equals(nome)) seguradoraProcurada = seg;
            break;
        }
        return seguradoraProcurada; 
    }

    private static Seguradora procurarSeguradoraValida(ArrayList<Seguradora> seguradoras, String nome, Scanner entrada)
    {
        Seguradora seg = procurarSeguradora(seguradoras, nome);
        while (seg.equals(null))
        {
            System.out.println("Nome inválido! Tente novamente: ");
            nome = entrada.nextLine();
            seg = procurarSeguradora(seguradoras, nome);
        }
        return seg;
    }

    private static ClientePF leClientePF(Scanner entrada)
    {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        boolean nomeValido = Validacao.validarNome(nome);
        while (!nomeValido)
        {
            System.out.println("Nome inválido! Tente novamente: ");
            nome = entrada.nextLine();
        }
        System.out.println("Digite o endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite o grau de educação: ");
        String educacao = entrada.nextLine();
        System.out.println("Digite o gênero: ");
        String genero = entrada.nextLine();
        System.out.println("Digite a classe econômica: ");
        String classeEconomica = entrada.nextLine();
        System.out.println("Digite a data da licença no formato dd/MM/yyyy: ");
        Calendar dataLicenca = leData(entrada);
        System.out.println("Digite a data de nascimento no formato dd/MM/yyyy: ");
        Calendar dataNascimento = leData(entrada);
        System.out.println("Digite o CPF: ");
        String cpf = leCPF(entrada);
        ClientePF cliente = new ClientePF(nome, endereco, dataLicenca, educacao,
                                        genero, classeEconomica, cpf, dataNascimento);
        System.out.println();
        return cliente;
    }

    private static ClientePJ leClientePJ(Scanner entrada)
    {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        boolean nomeValido = Validacao.validarNome(nome);
        while (!nomeValido)
        {
            System.out.println("Nome inválido! Tente novamente: ");
            nome = entrada.nextLine();
        }
        System.out.println("Digite o endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite a data de fundação no formato dd/MM/yyyy: ");
        Calendar dataFundacao = leData(entrada);
        System.out.println("Digite o CNPJ: ");
        String cnpj = leCNPJ(entrada);
        System.out.println("Digite a quantidade de funcionários");
        int funcionários = Integer.parseInt(entrada.nextLine());
        ClientePJ cliente = new ClientePJ(nome, endereco, cnpj, dataFundacao, funcionários);
        System.out.println();
        return cliente;
    }

    private static void cadastrarCliente(Seguradora seguradora, Scanner scanner)
    {
        System.out.println("Informe o tipo do cliente (PF/PJ): ");
        String tipo = scanner.nextLine();
        boolean tipoValido = Validacao.validarTipoCliente(tipo);
        while (!tipoValido)
        {
            System.out.println("Tipo inválido! Tente novamente: ");
            tipo = scanner.nextLine();
        }
        if (tipo.toUpperCase().equals("PF"))
        {
            ClientePF cliente = leClientePF(scanner);
            seguradora.cadastrarCliente(cliente);
        }
        else if (tipo.toUpperCase().equals("PJ"))
        {
            ClientePJ cliente = leClientePJ(scanner);
            seguradora.cadastrarCliente(cliente);
        }
    }

    private static Cliente procurarClienteValido(Seguradora seg, String nome, Scanner entrada)
    {
        Cliente cliente = seg.procurarCliente(nome);
        while (cliente.equals(null))
        {
            System.out.println("Nome inválido! Não existe cliente de nome " + nome + " na seguradora");
            System.out.println("Tente novamente: ");
            nome = entrada.nextLine();
            cliente = seg.procurarCliente(nome);
        }
        return cliente;
    }

    private static Veiculo leVeiculo(Scanner entrada)
    {
        System.out.println("Digite a placa: ");
        String placa = entrada.nextLine();
        System.out.println("Digite a marca: ");
        String marca = entrada.nextLine();
        System.out.println("Digite o modelo: ");
        String modelo = entrada.nextLine();
        System.out.println("Digite o ano de fabricacao: ");
        int ano = Integer.parseInt(entrada.nextLine());
        System.out.println();
        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
        return veiculo;
    }

    private static void cadastrarVeiculo(Seguradora seguradora, Cliente cliente, Scanner entrada)
    {
        Veiculo veiculo = leVeiculo(entrada);
        seguradora.adicionarVeiculoCliente(cliente, veiculo);
    }

    private static Veiculo procurarVeiculoValido(Cliente cliente, String placa, Scanner entrada)
    {
        Veiculo veiculo = cliente.procuraVeiculo(placa);
        while (veiculo.equals(null))
        {
            System.out.println("Placa inválida! Tente novamente: ");
            placa = entrada.nextLine();
            veiculo = cliente.procuraVeiculo(placa);
        }
        return veiculo;
    }

    //exibir menu externo
	private static void exibirMenuExterno() 
    {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for(MenuOpcoes op: menuOpcoes) 
			System.out.println(op.ordinal() + " - " + op.getDescricao());
	}
	
	/* exibir submenus
	se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, 
    a opção Voltar é printada com a posição que está na lista do enum (9 - Voltar). 
    Por isso, a lista é percorrida de forma diferente, tendo i como o inteiro correspondente. 
    Assim, para listar o submenu de cadastros, por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) 
    {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i = 0; i < submenu.length; i++) 
			System.out.println(i + " - " + submenu[i].getDescricao());
	}
	
	//ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno(Scanner entrada) 
    {
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do 
        {
			System.out.println("Digite uma opção: ");
			opUsuario = Integer.parseInt(entrada.nextLine());
            System.out.println();
		} while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op, Scanner entrada) 
    {
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do 
        {
			System.out.println("Digite uma opção: ");
			opUsuario = Integer.parseInt(entrada.nextLine());
		} while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	//executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op, ArrayList<Seguradora> seguradoras, Scanner entrada) 
    {
		switch(op) 
        {
			case CADASTROS:
                executarSubmenu(op, seguradoras, entrada);
                break;
			case LISTAR:
                executarSubmenu(op, seguradoras, entrada);
                break;
			case EXCLUIR:
				executarSubmenu(op, seguradoras, entrada);
				break;
			case GERAR_SINISTRO:
                System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
                String nomeSeg = entrada.nextLine();
                Seguradora seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
                System.out.println("Digite o nome do cliente: ");
                String nomeCliente = entrada.nextLine();
                Cliente cliente = procurarClienteValido(seg, nomeCliente, entrada);
                System.out.println("Digite a placa do veiculo: ");
                String placa = entrada.nextLine();
                Veiculo veiculo = procurarVeiculoValido(cliente, placa, entrada);
                System.out.println("Informe a data do sinistro: ");
                Calendar data = leData(entrada);
                System.out.println("Informe o endereço a que se refere o sinistro: ");
                String endereco = entrada.nextLine();
                boolean sinistroGerado = seg.gerarSinistro(data, endereco, veiculo, cliente);
                if (sinistroGerado) 
                {
                    System.out.println("Sinistro gerado com sucesso!");
                    seg.visualizarSinistro(nomeCliente);
                }
                else System.out.println("Operação inválida! Sinistro já existente.");
				break;
			case TRANSFERIR_SEGURO:
                System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
                nomeSeg = entrada.nextLine();
                seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
                if (seg.getListaClientes().size() > 1)
                {
                    System.out.println("Digite o nome do cliente que irá transferir o seguro: ");
                    String nomeDoador = entrada.nextLine();
                    Cliente doador = procurarClienteValido(seg, nomeDoador, entrada);
                    System.out.println("Digite o nome do cliente que irá receber o seguro: ");
                    String nomeReceptor = entrada.nextLine();
                    Cliente receptor = procurarClienteValido(seg, nomeReceptor, entrada);
                    seg.transferirSeguro(doador, receptor);
                    System.out.println("Transferência realizada!");
                }
                else if (seg.getListaClientes().size() == 1)
                    System.out.println("Operação inválida! Seguradora possui um único cliente");
                else if (seg.getListaClientes().size() == 0)
                    System.out.println("Operação inválida! Seguradora não possui clientes");
				break;
			case CALCULAR_RECEITA:
                System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
                nomeSeg = entrada.nextLine();
                seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
                seg.calcularReceita();
                double receita = seg.getReceita();
                System.out.println("O valor da receita da seguradora é " + receita);
				break;
			case SAIR:
                break;
		}
	}

    //executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op, ArrayList<Seguradora> seguradoras, Scanner entrada) 
    {
		SubmenuOpcoes opSubmenu;
		do 
        {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op, entrada);
			executarOpcaoSubMenu(opSubmenu, seguradoras, entrada);
		} while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, ArrayList<Seguradora> seguradoras, Scanner entrada) 
    {
		switch(opSubmenu) 
        {
		case CADASTRAR_CLIENTE:
            System.out.println("Digite o nome da seguradora em que deseja cadastrar o cliente: ");
            String nomeSeg = entrada.nextLine();
            Seguradora seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            cadastrarCliente(seg, entrada);
			break;
		case CADASTRAR_VEICULO:
            System.out.println("Digite o nome da seguradora em que deseja cadastrar o veículo: ");
            nomeSeg = entrada.nextLine();
            seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            System.out.println("Digite o nome do cliente que possui o veículo: ");
            String nomeCliente = entrada.nextLine();
            Cliente cliente = procurarClienteValido(seg, nomeCliente, entrada);
			cadastrarVeiculo(seg, cliente, entrada);
			break;
		case CADASTRAR_SEGURADORA:
            cadastrarSeguradora(seguradoras, entrada);
			break;
		case LISTAR_CLIENTES:
            String listaClientes = "";
            for (Seguradora seguradora: seguradoras)
            {
                listaClientes += "SEGURADORA " + seguradora.getNome() + ":\n\t";
                listaClientes += seguradora.listarClientes();
            }
            listaClientes += "\n";
            System.out.println(listaClientes);
			break;
		case LISTAR_SINISTROS_SEGURADORA:
            String listaSinistrosSeg = "";
            for (Seguradora seguradora: seguradoras)
            {
                listaSinistrosSeg += "SEGURADORA " + seguradora.getNome() + ":\n\t";
                listaSinistrosSeg += seguradora.listarSinistrosSeguradora();
            }
            listaSinistrosSeg += "\n";
            System.out.println(listaSinistrosSeg);
            break;
        case LISTAR_SINISTROS_CLIENTE:
            System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
            nomeSeg = entrada.nextLine();
            seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            System.out.println("Digite o nome do cliente: ");
            nomeCliente = entrada.nextLine();
            cliente = procurarClienteValido(seg, nomeCliente, entrada);
            System.out.println(seg.listarSinistrosCliente(cliente));
            break;
        case LISTAR_VEICULOS_SEGURADORA:
            String listaVeiculosSeg = "";
            for (Seguradora seguradora: seguradoras)
            {
                listaVeiculosSeg += "SEGURADORA " + seguradora.getNome() + ":\n\t";
                listaVeiculosSeg += seguradora.listarVeiculos();
            }
            listaVeiculosSeg += "\n";
            System.out.println(listaVeiculosSeg);
            break;
		case LISTAR_VEICULOS_CLIENTE:
            System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
            nomeSeg = entrada.nextLine();
            seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            System.out.println("Digite o nome do cliente: ");
            nomeCliente = entrada.nextLine();
            cliente = procurarClienteValido(seg, nomeCliente, entrada);
			System.out.println(cliente.listarVeiculos());
			break;
		case EXCLUIR_CLIENTE:
            System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
            nomeSeg = entrada.nextLine();
            seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            System.out.println("Digite o nome do cliente: ");
            nomeCliente = entrada.nextLine();
            boolean clienteExcluido = seg.removerCliente(nomeCliente);
            if (!clienteExcluido)
            {
                System.out.println("Não foi possível achar o cliente. Deseja tentar novamente? S/N");
                String resposta = entrada.nextLine();
                while (resposta.toUpperCase().equals("S") && !clienteExcluido)
                {
                    System.out.println("Digite o nome do cliente: ");
                    nomeCliente = entrada.nextLine();
                    clienteExcluido = seg.removerCliente(nomeCliente);
                    if (!clienteExcluido)
                    {
                        System.out.println("Não foi possível achar o cliente. Deseja tentar novamente? S/N");
                        resposta = entrada.nextLine();
                    }
                }
            }
            else System.out.println("Cliente removido com sucesso");
			break;
		case EXCLUIR_VEICULO:
            System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
            nomeSeg = entrada.nextLine();
            seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            System.out.println("Digite o nome do cliente: ");
            nomeCliente = entrada.nextLine();
            cliente = procurarClienteValido(seg, nomeCliente, entrada);
            System.out.println("Digite a placa do veiculo: ");
            String placa = entrada.nextLine();
            boolean veiculoExcluido = seg.removerVeiculoCliente(cliente, placa);
            if (!veiculoExcluido)
            {
                System.out.println("Não foi possível achar o veiculo. Deseja tentar novamente? S/N");
                String resposta = entrada.nextLine();
                while (resposta.toUpperCase().equals("S") && !veiculoExcluido)
                {
                    System.out.println("Digite a placa do veiculo: ");
                    placa = entrada.nextLine();
                    veiculoExcluido = seg.removerVeiculoCliente(cliente, placa);
                    if (!veiculoExcluido)
                    {
                        System.out.println("Não foi possível achar o veiculo. Deseja tentar novamente? S/N");
                        resposta = entrada.nextLine();
                    }
                }
            }
            else System.out.println("Veiculo removido com sucesso");
			break;
		case EXCLUIR_SINISTRO:
            System.out.println("Digite o nome da seguradora em que o cliente está cadastrado: ");
            nomeSeg = entrada.nextLine();
            seg = procurarSeguradoraValida(seguradoras, nomeSeg, entrada);
            System.out.println("Digite o nome do cliente: ");
            nomeCliente = entrada.nextLine();
            cliente = procurarClienteValido(seg, nomeCliente, entrada);
            System.out.println("Informe a data do sinistro: ");
            Calendar data = leData(entrada);
            System.out.println("Informe o endereço a que se refere o sinistro: ");
            String endereco = entrada.nextLine();
            boolean sinistroExcluido = seg.removerSinistro(data, endereco, cliente);
            if (!sinistroExcluido)
            {
                System.out.println("Não foi possível achar o sinistro. Deseja tentar novamente? S/N");
                String resposta = entrada.nextLine();
                while (resposta.toUpperCase().equals("S") && !sinistroExcluido)
                {            
                    System.out.println("Informe a data do sinistro: ");
                    data = leData(entrada);
                    System.out.println("Informe o endereço a que se refere o sinistro: ");
                    endereco = entrada.nextLine();
                    sinistroExcluido = seg.removerSinistro(data, endereco, cliente);
                    if (!sinistroExcluido)
                    {
                        System.out.println("Não foi possível achar o veiculo. Deseja tentar novamente? S/N");
                        resposta = entrada.nextLine();
                    }
                }
            }
            else System.out.println("Sinistro removido com sucesso");
			break;
		case VOLTAR:
            System.out.println();
			break;
		}
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) 
    {
        Seguradora seg = new Seguradora("Seguradora de Barão", "(19)99999-9999", 
                                        "seguradora@gmail.com", "Avenida 2");
        Calendar dataLicenca = leData("10/05/2023");
        Calendar dataNasc = leData("01/01/1999");
        Calendar dataFund = leData("25/08/1978");
        Cliente clientepf = new ClientePF("Diogo Silva", "Rua 1", dataLicenca, "Médio", 
                                "masculino", "média", "899.196.340-42", dataNasc);
        Cliente clientepj = new ClientePJ("Oficina de Barão", "Rua 2", 
                                "82.222.822/0001-81", dataFund, 30);
        Veiculo veiculo1 = new Veiculo("JQL-3592", "Koenigsegg", "Agera", 2011);
        Veiculo veiculo2 = new Veiculo("MFP-4595", "Bugatti", "Veyron", 2015);

        seg.cadastrarCliente(clientepf);
        seg.cadastrarCliente(clientepj);
        seg.adicionarVeiculoCliente(clientepf, veiculo1);
        seg.adicionarVeiculoCliente(clientepj, veiculo2);

        Calendar dataSinistro1 = leData("15/05/23");
        Calendar dataSinistro2 = leData("10/04/2022");
        seg.gerarSinistro(dataSinistro1, "Avenida 1", veiculo1, clientepf);
        seg.gerarSinistro(dataSinistro2, "Rua 3", veiculo2, clientepj);

        System.out.println("Método listar clientes");
        System.out.println(seg.listarClientes());
        System.out.println();
        System.out.println("Método visualizar sinistros");
        seg.visualizarSinistro("Diogo Silva");
        seg.visualizarSinistro("Oficina de Barão");
        System.out.println();
        System.out.println(seg.listarSinistrosSeguradora());

        seg.calcularPrecoSeguroCliente(clientepf);
        System.out.println("Valor do seguro de " + clientepf.getNome() +": " + clientepf.getValorSeguro());
        seg.calcularPrecoSeguroCliente(clientepj);
        System.out.println("Valor do seguro de " + clientepj.getNome() +": " + clientepj.getValorSeguro());
        seg.calcularReceita();
        System.out.println("Valor da receita da seguradora " + seg.getNome() + ": " + seg.getReceita());
        
        Scanner entrada = new Scanner(System.in);
        ArrayList<Seguradora> seguradoras = new ArrayList<Seguradora>();
		MenuOpcoes op;
		do 
        {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno(entrada);
			executarOpcaoMenuExterno(op, seguradoras, entrada);
		} while (op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
        entrada.close();
	}
}



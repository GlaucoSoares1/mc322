package Lab06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class OperacoesMenu 
{
    //exibir menu externo
	public static void exibirMenuExterno() 
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
	public static MenuOpcoes lerOpcaoMenuExterno(Scanner entrada) 
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
	public static void executarOpcaoMenuExterno(MenuOpcoes op, ArrayList<Seguradora> seguradoras, Scanner entrada) 
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
                System.out.println("Digite o CNPJ da seguradora: ");
                String cnpj = OperacoesLeitura.leCNPJ(entrada);
                Seguradora seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
                System.out.println("Digite o ID do seguro: ");
                Integer idSeguro = Integer.parseInt(entrada.nextLine());
                Seguro seguro = procurarSeguroValido(seg, idSeguro, entrada);
                System.out.println("Digite o cpf do condutor: ");
                String cpf = OperacoesLeitura.leCPF(entrada);
                Condutor condutor = procurarCondutorValido(seguro, cpf, entrada);
                System.out.println("Digite a data de ocorrência no formato dd/MM/yyyy: ");
                Calendar data = OperacoesLeitura.leData(entrada);
                System.out.println("Informe o endereço a que se refere o sinistro: ");
                String endereco = entrada.nextLine();
                boolean sinistroGerado = seguro.gerarSinistro(data, endereco, condutor);
                if (sinistroGerado) System.out.println("Sinistro gerado com sucesso!");
                else System.out.println("Não foi possível gerar o sinistro");
				break;
            case GERAR_SEGURO:
                System.out.println("Digite o CNPJ da seguradora: ");
                cnpj = OperacoesLeitura.leCNPJ(entrada);
                seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
                System.out.println("Digite a data de início do seguro no formato dd/MM/yyyy: ");
                data = OperacoesLeitura.leData(entrada);
                System.out.println("Informe o CPF ou CNPJ do cliente: ");
                String codigo = entrada.nextLine();
                Cliente cliente = procurarClienteValido(seg, codigo, entrada);
                if (cliente instanceof ClientePF)
                {
                    System.out.println("Informe a placa do veículo a que irá se referir o seguro: ");
                    String placa = entrada.nextLine();
                    Veiculo veiculo = procurarVeiculoValido((ClientePF) cliente, placa, entrada);
                    boolean seguroGerado = seg.gerarSeguro(data, (ClientePF)cliente, veiculo);
                    if (seguroGerado) System.out.println("Seguro gerado com sucesso, a duração é de 1 ano");
                }
                else
                {
                    System.out.println("Informe o código da frota a que irá se referir o seguro: ");
                    String codigoFrota = entrada.nextLine();
                    Frota frota = procurarFrotaValida((ClientePJ)cliente, codigoFrota, entrada);
                    boolean seguroGerado = seg.gerarSeguro(data, (ClientePJ)cliente, frota);
                    if (seguroGerado) System.out.println("Seguro gerado com sucesso, a duração é de 1 ano");
                }
                break;
			case VISUALIZAR_SINISTRO:
                System.out.println("Digite o CNPJ da seguradora: ");
                cnpj = OperacoesLeitura.leCNPJ(entrada);
                seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
                System.out.println("Digite o ID do seguro: ");
                idSeguro = Integer.parseInt(entrada.nextLine());
                seguro = procurarSeguroValido(seg, idSeguro, entrada);
                System.out.println("Informe o CPF ou CNPJ do cliente: ");
                codigo = entrada.nextLine();
                cliente = procurarClienteValido(seg, codigo, entrada);
                System.out.println("Digite o ID do sinistro: ");
                Integer id = Integer.parseInt(entrada.nextLine());
                Sinistro sinistro = procurarSinistroValido(seguro, id, entrada);
                if (cliente instanceof ClientePF)
                {
                    boolean sinistroValido = seg.visualizarSinistro(((ClientePF)cliente).getCPF(), sinistro.getId());
                    if (!sinistroValido) System.out.println("Operação inválida");
                }
                if (cliente instanceof ClientePJ)
                {
                    boolean sinistroValido = seg.visualizarSinistro(((ClientePJ)cliente).getCNPJ(), sinistro.getId());
                    if (!sinistroValido) System.out.println("Operação inválida");
                }
				break;
            case CONSULTAR_VALOR_SEGURO:
                System.out.println("Digite o CNPJ da seguradora: ");
                cnpj = OperacoesLeitura.leCNPJ(entrada);
                seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
                System.out.println("Digite o ID do seguro: ");
                idSeguro = Integer.parseInt(entrada.nextLine());
                seguro = procurarSeguroValido(seg, idSeguro, entrada);
                seguro.calcularValor();
                System.out.printf("O valor do seguro é %.2f\n", seguro.getValorMensal());
                break;
			case CALCULAR_RECEITA:
                System.out.println("Digite o CNPJ da seguradora: ");
                cnpj = OperacoesLeitura.leCNPJ(entrada);
                seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
                seg.calcularReceita();
                System.out.printf("A receita da seguradora é %.2f\n", seg.getReceita());
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

    private static void cadastrarSeguradora(ArrayList<Seguradora> seguradoras, Scanner entrada)
    {
        Seguradora seguradora = OperacoesLeitura.leSeguradora(entrada);
        seguradoras.add(seguradora);
    }

    private static Seguradora procurarSeguradora(ArrayList<Seguradora> seguradoras, String cnpj)
    {   
        Seguradora seguradoraProcurada = null;
        for (Seguradora seg: seguradoras)
            if (seg.getCNPJ().equals(cnpj)) return seg;
        return seguradoraProcurada; 
    }

    private static Seguradora procurarSeguradoraValida(ArrayList<Seguradora> seguradoras, String cnpj, Scanner entrada)
    {
        Seguradora seg = procurarSeguradora(seguradoras, cnpj);
        while (seg == (null))
        {
            System.out.println("CNPJ inválido! Tente novamente: ");
            cnpj = entrada.nextLine();
            seg = procurarSeguradora(seguradoras, cnpj);
        }
        return seg;
    }

    private static Cliente procurarClienteValido(Seguradora seg, String codigo, Scanner entrada)
    {
        Cliente cliente = seg.procurarCliente(codigo);
        while (cliente == (null))
        {
            System.out.println("CPF ou CNPJ inválido! Não existe cliente " + codigo + " na seguradora");
            System.out.println("Tente novamente: ");
            codigo = entrada.nextLine();
            cliente = seg.procurarCliente(codigo);
        }
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
            ClientePF cliente = OperacoesLeitura.leClientePF(scanner);
            if (seguradora.procurarCliente(cliente.getCPF()) == (null))
                seguradora.cadastrarCliente(cliente);
            else System.out.println("Cliente já cadastrado");
        }
        else if (tipo.toUpperCase().equals("PJ"))
        {
            ClientePJ cliente = OperacoesLeitura.leClientePJ(scanner);
            if (seguradora.procurarCliente(cliente.getCNPJ()) == (null))
                seguradora.cadastrarCliente(cliente);
            else System.out.println("Cliente já cadastrado");
        }
    }

    private static void cadastrarVeiculo(ClientePF cliente, Seguradora seg, Scanner entrada)
    {
        Veiculo veiculo = OperacoesLeitura.leVeiculo(entrada);
        if (cliente.procuraVeiculo(veiculo.getPlaca()) == (null))
            cliente.cadastrarVeiculo(veiculo, seg);
        else System.out.println("Veículo já cadastrado");
    }

    private static Veiculo procurarVeiculoValido(ClientePF cliente, String placa, Scanner entrada)
    {
        Veiculo veiculo = cliente.procuraVeiculo(placa);
        while (veiculo == (null))
        {
            System.out.println("Placa inválida! Tente novamente: ");
            placa = entrada.nextLine();
            veiculo = cliente.procuraVeiculo(placa);
        }
        return veiculo;
    }

    private static void cadastrarFrota(ClientePJ cliente, Seguradora seg, Scanner entrada)
    {
        System.out.println("Quantos veículos tem a frota? ");
        int quant = Integer.parseInt(entrada.nextLine());
        ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
        for (int i = 0; i < quant; i++)
        {
            Veiculo veiculo = OperacoesLeitura.leVeiculo(entrada);
            if (cliente.veiculoCadastrado(veiculo.getPlaca()))
                System.out.println("Veículo já cadastrado em alguma frota.");
            else lista.add(veiculo);
        }
        cliente.cadastrarFrota(lista, seg);
    }

    private static Frota procurarFrotaValida(ClientePJ cliente, String codigo, Scanner entrada)
    {
        Frota frota = cliente.procurarFrota(codigo);
        while (frota == (null))
        {
            System.out.println("Código inválido! Tente novamente: ");
            codigo = entrada.nextLine();
            frota = cliente.procurarFrota(codigo);
        }
        return frota;
    }

    private static void atualizarFrota(ClientePJ cliente, Frota frota, Seguradora seg, Scanner entrada)
    {
        System.out.println("Digite 1 para adicionar veículo, 2 para remover veículo ou " + 
                                "3 para remover a frota inteira");
        try 
        {
            Integer opcao = Integer.parseInt(entrada.nextLine());
            switch (opcao)
            {
                case 1:
                    Veiculo veiculo = OperacoesLeitura.leVeiculo(entrada);
                    boolean atualizado = cliente.atualizarFrota("adicionar", frota.getCode(), veiculo, seg);
                    if (atualizado) System.out.println("Veículo adicionado com sucesso");
                    else System.out.println("Veículo já cadastrado em outra frota");
                    break;
                case 2:
                    veiculo = OperacoesLeitura.leVeiculo(entrada);
                    atualizado = cliente.atualizarFrota("remover", frota.getCode(), veiculo, seg);
                    if (atualizado) System.out.println("Veículo removido com sucesso");
                    else System.out.println("Veículo não existente na frota");
                    break;
                case 3:
                    atualizado = cliente.atualizarFrota("remover toda", frota.getCode(), seg);
                    if (atualizado) System.out.println("Frota removida com sucesso");
                    else System.out.println("Frota não existente");
                    break;
                default:
                    System.out.println("Deve-se digitar apenas 1, 2 ou 3");
                    break;
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Deve-se digitar apenas 1, 2 ou 3");
        }
    }

    private static Seguro procurarSeguroValido(Seguradora seg, int idSeguro, Scanner entrada)
    {
        Seguro seguro = seg.procurarSeguro(idSeguro);
        while (seguro == (null))
        {
            System.out.println("Valor de ID inválido, tente novamente: ");
            idSeguro = Integer.parseInt(entrada.nextLine());
            seguro = seg.procurarSeguro(idSeguro);
        }
        return seguro;
    }

    private static void cadastrarCondutor(Seguro seguro, Scanner entrada)
    {
        Condutor condutor = OperacoesLeitura.leCondutor(entrada);
        boolean cadastrado = seguro.autorizarCondutor(condutor);
        if(cadastrado) System.out.println("Condutor autorizado com sucesso");
        else System.out.println("Condutor já autorizado anteriormente");
    }

    private static Condutor procurarCondutorValido(Seguro seguro, String cpf, Scanner entrada)
    {
        Condutor condutor = seguro.procurarCondutor(cpf);
        while (condutor == (null))
        {
            System.out.println("CPF inválido, tente novamente: ");
            cpf = OperacoesLeitura.leCPF(entrada);
            condutor = seguro.procurarCondutor(cpf);
        }
        return condutor;
    }

    private static Sinistro procurarSinistroValido(Seguro seguro, int idSinistro, Scanner entrada)
    {
        Sinistro sinistro = seguro.procurarSinistro(idSinistro);
        while (sinistro == (null))
        {
            System.out.println("ID inválida, tente novamente: ");
            Integer idInt = Integer.parseInt(entrada.nextLine());
            sinistro = seguro.procurarSinistro(idInt);
        }
        return sinistro;
    }
	
	private static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, ArrayList<Seguradora> seguradoras, Scanner entrada) 
    {
		switch(opSubmenu) 
        {
		case CADASTRAR_CLIENTE:
            System.out.println("Digite o CNPJ da seguradora em que deseja cadastrar o cliente: ");
            String cnpj = OperacoesLeitura.leCNPJ(entrada);
            Seguradora seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            cadastrarCliente(seg, entrada);
			break;
		case CADASTRAR_VEICULO:
            System.out.println("Digite o CNPJ da seguradora em que deseja cadastrar o veículo: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o CPF do cliente que possui o veículo: ");
            String cpf = OperacoesLeitura.leCPF(entrada);
            Cliente cliente = procurarClienteValido(seg, cpf, entrada);
			cadastrarVeiculo((ClientePF)cliente, seg, entrada);
			break;
        case CADASTRAR_FROTA:
            System.out.println("Digite o CNPJ da seguradora em que deseja cadastrar a frota: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o CNPJ da empresa que possui a frota: ");
            String cnpjEmp = OperacoesLeitura.leCNPJ(entrada);
            cliente = procurarClienteValido(seg, cnpjEmp, entrada);
            cadastrarFrota((ClientePJ)cliente, seg, entrada);
            break;
        case ATUALIZAR_FROTA:
            System.out.println("Digite o CNPJ da seguradora em que deseja atualizar a frota: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o CNPJ da empresa que possui a frota: ");
            cnpjEmp = OperacoesLeitura.leCNPJ(entrada);
            cliente = procurarClienteValido(seg, cnpjEmp, entrada);
            System.out.println("Digite o código da frota: ");
            String codigoFrota = entrada.nextLine();
            Frota frota = procurarFrotaValida((ClientePJ)cliente, codigoFrota, entrada);
            atualizarFrota((ClientePJ)cliente, frota, seg, entrada);
            break;
        case AUTORIZAR_CONDUTOR:
            System.out.println("Digite o CNPJ da seguradora em que deseja autorizar o condutor: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o ID do seguro: ");
            int idSeguro = Integer.parseInt(entrada.nextLine());
            Seguro seguro = procurarSeguroValido(seg, idSeguro, entrada);
            cadastrarCondutor(seguro, entrada);
            break;
		case CADASTRAR_SEGURADORA:
            cadastrarSeguradora(seguradoras, entrada);
			break;
		case LISTAR_CLIENTES:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println(seg.listarClientes());
			break;
		case LISTAR_SINISTROS_CLIENTE:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Informe o CPF ou CNPJ do cliente: ");
            String codigo = entrada.nextLine();
            cliente = procurarClienteValido(seg, codigo, entrada);
            if (cliente instanceof ClientePF)
                System.out.println(seg.listarSinistrosPorCliente(((ClientePF)cliente).getCPF()));
            else System.out.println(seg.listarSinistrosPorCliente(((ClientePJ)cliente).getCNPJ()));
            break;
        case LISTAR_SEGUROS_CLIENTE:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Informe o CPF ou CNPJ do cliente: ");
            codigo = entrada.nextLine();
            cliente = procurarClienteValido(seg, codigo, entrada);
            if (cliente instanceof ClientePF)
                System.out.println(seg.listarSegurosPorCliente(((ClientePF)cliente).getCPF()));
            else System.out.println(seg.listarSegurosPorCliente(((ClientePJ)cliente).getCNPJ()));
            break;
        case LISTAR_VEICULOS_CLIENTE:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Informe o CPF do cliente: ");
            cpf = OperacoesLeitura.leCPF(entrada);
            cliente = procurarClienteValido(seg, cpf, entrada);
            System.out.println(((ClientePF)cliente).listarVeiculos());
            break;
		case LISTAR_VEICULOS_FROTA:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o CNPJ da empresa que possui a frota: ");
            cnpjEmp = OperacoesLeitura.leCNPJ(entrada);
            cliente = procurarClienteValido(seg, cnpjEmp, entrada);
            System.out.println("Digite o código da frota: ");
            codigoFrota = entrada.nextLine();
            frota = procurarFrotaValida((ClientePJ)cliente, codigoFrota, entrada);
            System.out.println(frota.listarVeiculos());
			break;
		case EXCLUIR_CLIENTE:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Informe o CPF ou CNPJ do cliente: ");
            codigo = entrada.nextLine();
            cliente = procurarClienteValido(seg, codigo, entrada);
            if (cliente instanceof ClientePF)
            {
                boolean excluido = seg.removerCliente(((ClientePF)cliente).getCPF());
                if (excluido) System.out.println("Cliente removido com sucesso");
                else System.out.println("Não foi possível remover o cliente");
            }
            else 
            {
                boolean excluido = seg.removerCliente(((ClientePJ)cliente).getCNPJ());
                if (excluido) System.out.println("Cliente removido com sucesso");
                else System.out.println("Não foi possível remover o cliente");
            }
			break;
        case EXCLUIR_CONDUTOR:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o ID do seguro: ");
            idSeguro = Integer.parseInt(entrada.nextLine());
            seguro = procurarSeguroValido(seg, idSeguro, entrada);
            System.out.println("Digite o cpf do condutor: ");
            cpf = OperacoesLeitura.leCPF(entrada);
            Condutor condutor = procurarCondutorValido(seguro, cpf, entrada);
            boolean desautorizado = seguro.desautorizarCondutor(condutor);
            if (desautorizado) System.out.println("Condutor desautorizado");
            else System.out.println("Não foi possível desautorizar");
            break;
		case EXCLUIR_VEICULO:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Informe o CPF do cliente: ");
            cpf = OperacoesLeitura.leCPF(entrada);
            cliente = procurarClienteValido(seg, cpf, entrada);
            System.out.println("Digite a placa do veículo: ");
            String placa = entrada.nextLine();
            Veiculo veiculo = procurarVeiculoValido((ClientePF)cliente, placa, entrada);
            boolean excluido = ((ClientePF)cliente).removerVeiculo(veiculo.getPlaca(), seg);
            if (excluido) System.out.println("Veiculo excluído com sucesso");
            else System.out.println("Não foi possível excluir o veículo");
			break;
        case EXCLUIR_FROTA:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o CNPJ da empresa que possui a frota: ");
            cnpjEmp = OperacoesLeitura.leCNPJ(entrada);
            cliente = procurarClienteValido(seg, cnpjEmp, entrada);
            System.out.println("Digite o código da frota: ");
            codigoFrota = entrada.nextLine();
            frota = procurarFrotaValida((ClientePJ)cliente, codigoFrota, entrada);
            excluido = ((ClientePJ)cliente).atualizarFrota("remover toda", frota.getCode(), seg);
            if (excluido) System.out.println("Frota removida com sucesso");
            else System.out.println("Frota não existente");
            break;
		case EXCLUIR_SINISTRO:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o ID do seguro: ");
            idSeguro = Integer.parseInt(entrada.nextLine());
            seguro = procurarSeguroValido(seg, idSeguro, entrada);
            System.out.println("Digite o ID do sinistro: ");
            Integer id = Integer.parseInt(entrada.nextLine());
            Sinistro sinistro = procurarSinistroValido(seguro, id, entrada);
            excluido = seguro.removerSinistro(sinistro.getId());
            if (excluido) System.out.println("Sinistro removido com sucesso");
            else System.out.println("Não foi possível remover o sinistro");
			break;
        case EXCLUIR_SEGURO:
            System.out.println("Digite o CNPJ da seguradora: ");
            cnpj = OperacoesLeitura.leCNPJ(entrada);
            seg = procurarSeguradoraValida(seguradoras, cnpj, entrada);
            System.out.println("Digite o ID do seguro: ");
            idSeguro = Integer.parseInt(entrada.nextLine());
            seguro = procurarSeguroValido(seg, idSeguro, entrada);
            excluido = seg.cancelarSeguro(seguro.getId());
            if (excluido) System.out.println("Seguro removido com sucesso");
            else System.out.println("Não foi possível remover o seguro");
            break;
		case VOLTAR:
            System.out.println();
			break;
		}
	}
}

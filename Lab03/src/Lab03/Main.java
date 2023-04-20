package Lab03;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner entrada = new Scanner(System.in);
        Seguradora seguradora = leSeguradora(entrada);

        System.out.println("Cadastro de cliente pessoa fisica");
        System.out.println("Quantos clientes PF serao inseridos? ");
        int numPF = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < numPF; i++)
        {
            ClientePF cliente = leClientePF(entrada);
            seguradora.cadastrarCliente(cliente);
        }

        System.out.println("Cadastro de cliente pessoa juridica");
        System.out.println("Quantos clientes PJ serao inseridos? ");
        int numPJ = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < numPJ; i++)
        {
            ClientePJ cliente = leClientePJ(entrada);
            seguradora.cadastrarCliente(cliente);
        }
        System.out.println(seguradora.listarClientes());
        System.out.println();

        System.out.println("Deseja gerar algum sinistro? S/N");
        String opcao = entrada.nextLine();
        if (opcao.equals("S"))
        {
            boolean sinistro = leSinistro(entrada, seguradora);
            if (sinistro) System.out.println("Sinistro adicionado com sucesso!");
            else System.out.println("Nao foi possivel gerar sinistro.");
        }
        System.out.println();
        
        System.out.println("Deseja remover algum cliente? S/N");
        opcao = entrada.nextLine();
        if (opcao.equals("S"))
        {
            System.out.println("Digite o nome do cliente a ser removido");
            seguradora.removerCliente(entrada.nextLine());
        }
        System.out.println();
        System.out.println(seguradora.listarSinistros());
        System.out.println();
        System.out.println(seguradora);
        entrada.close();
    }

    private static String leCPF(Scanner entrada)
    {
        String cpf = entrada.nextLine();
        boolean cpfValido = ClientePF.validarCPF(cpf);
        while (!cpfValido)
        {
            System.out.println("CPF inválido! Digite novamente:");
            cpf = entrada.nextLine();
            cpfValido = ClientePF.validarCPF(cpf);
        }
        System.out.println("CPF válido!");
        return cpf;
    }

    private static String leCNPJ(Scanner entrada)
    {
        String cnpj = entrada.nextLine();
        boolean cnpjValido = ClientePJ.validarCNPJ(cnpj);
        while (!cnpjValido)
        {
            System.out.println("CNPJ inválido! Digite novamente:");
            cnpj = entrada.nextLine();
            cnpjValido = ClientePJ.validarCNPJ(cnpj);
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

    private static Seguradora leSeguradora(Scanner entrada)
    {
        System.out.println("Dados da seguradora");
        System.out.println("Digite o nome da seguradora: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o telefone da seguradora: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o email da seguradora: ");
        String email = entrada.nextLine();
        System.out.println("Digite o endereco da seguradora: ");
        String endereco = entrada.nextLine();
        System.out.println();
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        return seguradora;
    }

    private static ClientePF leClientePF(Scanner entrada)
    {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o endereco: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite o grau de educacao: ");
        String educacao = entrada.nextLine();
        System.out.println("Digite o genero: ");
        String genero = entrada.nextLine();
        System.out.println("Digite a classe economica: ");
        String classeEconomica = entrada.nextLine();
        System.out.println("Digite a data da licenca: ");
        Calendar dataLicenca = leData(entrada);
        System.out.println("Digite a data de nascimento: ");
        Calendar dataNascimento = leData(entrada);
        System.out.println("Digite o CPF: ");
        String cpf = leCPF(entrada);
        ClientePF cliente = new ClientePF(nome, endereco, dataLicenca, educacao,
                                        genero, classeEconomica, cpf, dataNascimento);
        System.out.println();
        System.out.println("Deseja adicionar quantos veiculos? ");
        int num1 = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < num1; i ++) 
        {
            cliente.insereVeiculo(leVeiculo(entrada));
            System.out.println();
        }

        System.out.println("Deseja remover quantos veiculos? ");
        int num2 = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < num2; i ++) 
        {
            System.out.println("Digite a placa do veiculo: ");
            Veiculo veiculo = cliente.procuraVeiculo(entrada.nextLine());
            cliente.removeVeiculo(veiculo);
            System.out.println();
        }
        return cliente;
    }

    private static ClientePJ leClientePJ(Scanner entrada)
    {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o endereco: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite a data de fundacao: ");
        Calendar dataFundacao = leData(entrada);
        System.out.println("Digite o CNPJ: ");
        String cnpj = leCNPJ(entrada);
        System.out.println();
        ClientePJ cliente = new ClientePJ(nome, endereco, cnpj, dataFundacao);

        System.out.println("Deseja adicionar quantos veiculos? ");
        int num1 = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < num1; i ++)
        {
            cliente.insereVeiculo(leVeiculo(entrada));
            System.out.println();
        } 

        System.out.println("Deseja remover quantos veiculos? ");
        int num2 = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < num2; i ++)
        {
            System.out.println("Digite a placa do veiculo: ");
            Veiculo veiculo = cliente.procuraVeiculo(entrada.nextLine());
            cliente.removeVeiculo(veiculo);
            System.out.println();
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

    private static boolean leSinistro(Scanner entrada, Seguradora seguradora)
    {
        System.out.println("Digite a data: ");
        Calendar data = leData(entrada);
        System.out.println("Digite o endereco: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = entrada.nextLine();
        Cliente cliente = seguradora.procurarCliente(nomeCliente);
        System.out.println("Digite a placa do veiculo: ");
        Veiculo veiculo = cliente.procuraVeiculo(entrada.nextLine());
        seguradora.visualizarSinistro(nomeCliente);
        return seguradora.gerarSinistro(data, endereco, veiculo, cliente);
    }
}

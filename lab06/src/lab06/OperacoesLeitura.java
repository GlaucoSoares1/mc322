package Lab06;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class OperacoesLeitura 
{
    public static String leCPF(Scanner entrada)
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

    public static String leCNPJ(Scanner entrada)
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

    public static Calendar leData(Scanner entrada)
    {
        try
        {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data = entrada.nextLine();
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(formato.parse(data));
            return calendario;
        }
        catch (Exception exception) 
        {
            System.out.println("Formato inválido, tente novamente: ");
            leData(entrada);
        }
        return null;
    }

    public static Calendar leData(String data)
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

    public static Seguradora leSeguradora(Scanner entrada)
    {
        System.out.println("Dados da seguradora");
        System.out.println("Digite o CNPJ da seguradora: ");
        String cnpj = leCNPJ(entrada);
        System.out.println("Digite o nome da seguradora: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o telefone da seguradora: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o endereço da seguradora: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite o email da seguradora: ");
        String email = entrada.nextLine();
        System.out.println();
        Seguradora seguradora = new Seguradora(cnpj, nome, telefone, endereco, email);
        return seguradora;
    }

    public static ClientePF leClientePF(Scanner entrada)
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
        System.out.println("Digite o telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o email: ");
        String email = entrada.nextLine();
        System.out.println("Digite a data da licença no formato dd/MM/yyyy: ");
        Calendar dataLicenca = leData(entrada);
        System.out.println("Digite o grau de educação: ");
        String educacao = entrada.nextLine();
        System.out.println("Digite o gênero: ");
        String genero = entrada.nextLine();
        System.out.println("Digite a data de nascimento no formato dd/MM/yyyy: ");
        Calendar dataNascimento = leData(entrada);
        System.out.println("Digite o CPF: ");
        String cpf = leCPF(entrada);
        ClientePF cliente = new ClientePF(nome, endereco, telefone, email, dataLicenca,
                                        educacao, genero, cpf, dataNascimento);
        System.out.println();
        return cliente;
    }

    public static ClientePJ leClientePJ(Scanner entrada)
    {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite o telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o email: ");
        String email = entrada.nextLine();
        System.out.println("Digite o CNPJ: ");
        String cnpj = leCNPJ(entrada);
        System.out.println("Digite a data de fundação no formato dd/MM/yyyy: ");
        Calendar dataFundacao = leData(entrada);
        System.out.println("Digite a quantidade de funcionários");
        int funcionários = Integer.parseInt(entrada.nextLine());
        ClientePJ cliente = new ClientePJ(nome, endereco, telefone, email, cnpj, 
                                            dataFundacao, funcionários);
        System.out.println();
        return cliente;
    }

    public static Veiculo leVeiculo(Scanner entrada)
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

    public static Condutor leCondutor(Scanner entrada)
    {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        boolean nomeValido = Validacao.validarNome(nome);
        while (!nomeValido)
        {
            System.out.println("Nome inválido! Tente novamente: ");
            nome = entrada.nextLine();
        }
        System.out.println("Digite o telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("Digite o email: ");
        String email = entrada.nextLine();
        System.out.println("Digite a data de nascimento no formato dd/MM/yyyy: ");
        Calendar dataNasc = leData(entrada);
        System.out.println("Digite o CPF: ");
        String cpf = leCPF(entrada);
        Condutor condutor = new Condutor(cpf, nome, telefone, endereco, email, dataNasc);
        return condutor;
    }
}

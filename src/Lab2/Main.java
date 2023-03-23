package Lab2;
import java.util.Scanner;

public class Main
{
	public static void main(String args[]) 
	{
		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite o nome da seguradora:");
		String nomeSeguradora = entrada.nextLine();
		System.out.println("Digite o telefone da seguradora:");
		String telSeguradora = entrada.nextLine();
		System.out.println("Digite o email da seguradora:");
		String emailSeguradora = entrada.nextLine();
		System.out.println("Digite o endereco da seguradora:");
		String endeSeguradora = entrada.nextLine();
		Seguradora seguradora = new Seguradora(nomeSeguradora, telSeguradora, emailSeguradora, endeSeguradora);
		System.out.println("\nOs dados da seguradora sao:");
		System.out.println(seguradora.toString() + "\n");
		
		System.out.println("Digite o nome do cliente:");
		String nomeCliente = entrada.nextLine();
		System.out.println("Digite a data de nascimento:");
		String nascimentoCliente = entrada.nextLine();
		System.out.println("Digite o endereco do cliente:");
		String endeCliente = entrada.nextLine();
		System.out.println("Digite o CPF do cliente:");
		String cpfCliente = entrada.nextLine();
		System.out.println("Digite a idade do cliente:");
		int idadeCliente = entrada.nextInt();
		Cliente cliente = new Cliente(nomeCliente, cpfCliente, nascimentoCliente, idadeCliente, endeCliente);
		boolean cpfValido = cliente.validarCPF(cpfCliente);
		if (cpfValido)
			System.out.println("Seu CPF e valido!");
		else
			System.out.println("Seu CPF nao e valido!");
		System.out.println("\nOs dados do cliente sao: ");
		System.out.println(cliente.toString() + "\n");

		System.out.println("Digite a placa do veiculo:");
		String placaVeiculo = entrada.nextLine();
		System.out.println("Digite a marca do veiculo:");
		String marcaVeiculo = entrada.nextLine();
		System.out.println("Digite o modelo do veiculo:");
		String modeloVeiculo = entrada.nextLine();
		Veiculo veiculo = new Veiculo(placaVeiculo, marcaVeiculo, modeloVeiculo);
		System.out.println("\nOs dados do veiculo sao:");
		System.out.println(veiculo.toString() + "\n");

		Sinistro sinistro = new Sinistro(null, null);
		System.out.println("Digite a data do acidente:");
		sinistro.setData(entrada.nextLine());
		System.out.println("Digite o local do acidente:");
		sinistro.setEndereco(entrada.nextLine());
		System.out.println("\nOs dados do sinistro sao:");
		System.out.println(sinistro.toString() + "\n");

		Sinistro sinistro2 = new Sinistro(null, null);
		System.out.println("Digite a data do acidente:");
		sinistro2.setData(entrada.nextLine());
		System.out.println("Digite o local do acidente:");
		sinistro2.setEndereco(entrada.nextLine());
		System.out.println("\nOs dados do sinistro sao:");
		System.out.println(sinistro2.toString() + "\n");

		entrada.close();
	}
}

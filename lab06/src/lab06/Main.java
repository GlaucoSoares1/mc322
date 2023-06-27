package Lab06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main 
{
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
    public static void main(String[] args) 
    {
        try
        {
            Seguradora seg = new Seguradora("57.117.881/0001-42","Seguradora de Barão", 
                            "(19)99999-9999", "Avenida 2", "seguradora@gmail.com");
            
            Calendar dataLicenca = OperacoesLeitura.leData("10/05/2023");
            Calendar dataNasc = OperacoesLeitura.leData("01/01/1999");
            Calendar dataFund = OperacoesLeitura.leData("25/08/1978");
            ClientePF clientepf = new ClientePF("Diogo Silva", "Rua 1", "(24) 3187-1649",
                                "diogo@gmail.com", dataLicenca, "Médio",  "masculino",  
                                "899.196.340-42", dataNasc);
            ClientePJ clientepj = new ClientePJ("Oficina de Barão", "Rua 2", "(98) 2874-5040", 
                                "oficina@gmail.com", "82.222.822/0001-81", dataFund, 30);

            seg.cadastrarCliente(clientepf);
            seg.cadastrarCliente(clientepj);

            Veiculo veiculo1 = new Veiculo("JQL3592", "Koenigsegg", "Agera", 2011);
            clientepf.cadastrarVeiculo(veiculo1, seg);

            Veiculo frota1 = new Veiculo("MFP4595", "Fiat", "Uno", 1998);
            Veiculo frota2 = new Veiculo("ICZ7920", "Fiat", "Uno", 1998);
            Veiculo frota3 = new Veiculo("NER0225", "Fiat", "Uno", 1998);
            Veiculo frota4 = new Veiculo("GUO8509", "Fiat", "Uno", 1998);
            ArrayList<Veiculo> veiculosFrota = new ArrayList<Veiculo>();
            veiculosFrota.add(frota1);
            veiculosFrota.add(frota2);
            veiculosFrota.add(frota3);
            veiculosFrota.add(frota4);
            clientepj.cadastrarFrota(veiculosFrota, seg);
            System.out.println(clientepj.listarFrotas());
            String codigo = clientepj.getListaFrota().get(0).getCode();
            Frota frota = clientepj.procurarFrota(codigo);
            Veiculo frota5 = new Veiculo("MMY6604", "Fiat", "Uno", 1998);
            clientepj.atualizarFrota("adicionar", codigo, frota5, seg);
            clientepj.atualizarFrota("remover", codigo, frota3, seg);
            System.out.println(clientepj.listarFrotas());

            System.out.println("Seguro cliente PF");
            Calendar iniciopf = OperacoesLeitura.leData("04/06/2023");
            seg.gerarSeguro(iniciopf, clientepf, veiculo1);
            int id1 = seg.getListaSeguroPorCliente(clientepf.getCPF()).get(0).getId();
            Seguro seguropf = seg.procurarSeguro(id1);
            seguropf.calcularValor();
            System.out.printf("O valor do seguro do cliente PF é %.2f\n", seguropf.getValorMensal());
            System.out.println();

            System.out.println("Seguro cliente PJ");
            Calendar iniciopj = OperacoesLeitura.leData("03/06/2023");
            seg.gerarSeguro(iniciopj, clientepj, frota); 
            int id2 = seg.getListaSeguroPorCliente(clientepj.getCNPJ()).get(0).getId();
            Seguro seguropj = seg.procurarSeguro(id2);
            seguropj.calcularValor();
            System.out.printf("O valor do seguro do cliente PJ é %.2f\n", seguropj.getValorMensal());
            System.out.println();

            System.out.println("Sinistro 1 - cliente PF");
            Calendar dataSinistro1 = OperacoesLeitura.leData("15/05/23");
            Calendar nascCondPf = OperacoesLeitura.leData("15/05/88");
            Condutor condutorpf = new Condutor("242.327.185-91", "Joao", "(48) 2653-8894", 
                                    "Rua 3", "joao@gmail.com", nascCondPf);
            seguropf.autorizarCondutor(condutorpf);
            seguropf.gerarSinistro(dataSinistro1, "Avenida 1", condutorpf); 
            seguropf.calcularValor();
            System.out.printf("O valor do seguro do cliente PF é %.2f\n", seguropf.getValorMensal());
            System.out.println();   
            
            System.out.println("Sinistro 2 - cliente PJ");
            Calendar dataSinistro2 = OperacoesLeitura.leData("10/04/2023");
            Calendar nascCondPj = OperacoesLeitura.leData("10/04/2000");
            Condutor condutorpj = new Condutor("729.367.476-78", "Jose", "(68) 3546-1991", 
                                    "Rua 3", "jose@gmail.com", nascCondPj);
            seguropj.autorizarCondutor(condutorpj);
            seguropj.gerarSinistro(dataSinistro2, "Avenida 2", condutorpj);
            seguropj.calcularValor();
            System.out.printf("O valor do seguro do cliente PJ é %.2f\n", seguropj.getValorMensal());
            System.out.println();

            seg.calcularReceita();
            System.out.printf("O valor da receita da seguradora é %.2f\n", seg.getReceita());
            System.out.println();
            
            Scanner entrada = new Scanner(System.in);
            ArrayList<Seguradora> seguradoras = new ArrayList<Seguradora>();
            MenuOpcoes op;
            do 
            {
            	OperacoesMenu.exibirMenuExterno();
            	op = OperacoesMenu.lerOpcaoMenuExterno(entrada);
            	OperacoesMenu.executarOpcaoMenuExterno(op, seguradoras, entrada);
            } while (op != MenuOpcoes.SAIR);
            System.out.println("Saiu do sistema");
            entrada.close();
        }
        catch (Exception except) {}
    }   
}



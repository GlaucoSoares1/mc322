package lab05;

import java.util.ArrayList;
import java.util.Calendar;

public class ClientePJ extends Cliente
{
    private final String CNPJ;
    private Calendar dataFundacao;
    private ArrayList<Frota> listaFrota;
    private int quantFuncionarios;

    // Construtor: chamar apenas com cnpj válido
    public ClientePJ(String nome, String endereco, String telefone, String email,
                    String CNPJ, Calendar dataFundacao, int quantFuncionarios)
    {
        super(nome, endereco, telefone, email);
        setDataFundacao(dataFundacao);
        setQuantFuncionarios(quantFuncionarios);
        listaFrota = new ArrayList<Frota>();
        this.CNPJ = CNPJ;
    }

    public String getCNPJ()
    {
        return CNPJ;
    }

    public Calendar getDataFundacao()
    {
        return dataFundacao;
    }

    public void setDataFundacao(Calendar dataFundacao)
    {
        this.dataFundacao = dataFundacao;
    }

    public int getQuantFuncionarios()
    {
        return quantFuncionarios;
    }

    public void setQuantFuncionarios(int quantFuncionarios)
    {
        this.quantFuncionarios = quantFuncionarios;
    }

    public ArrayList<Frota> getListaFrota()
    {
        return listaFrota;
    }

    public int quantVeiculos()
    {
        int quant = 0;
        for (Frota frota: listaFrota) quant += frota.getListaVeiculos().size();
        return quant;
    }

    private String imprimeData(Calendar data)
    {
        String str;
        str = Integer.toString(data.get(Calendar.DATE)) + "/" +
            Integer.toString(data.get(Calendar.MONTH)) + "/" +
            Integer.toString(data.get(Calendar.YEAR));
        return str;
    }

    public boolean cadastrarFrota(ArrayList<Veiculo> veiculos, Seguradora seg)
    {
        // considerando que um veículo não pode estar em mais de uma frota
        for (Veiculo veiculo: veiculos)
        {
            boolean veiculoExiste = veiculoCadastrado(veiculo.getPlaca());
            if (veiculoExiste) return false;
        }
        Frota frotaNova = new Frota(this);
        for (Veiculo veiculo: veiculos) frotaNova.addVeiculo(veiculo);
        listaFrota.add(frotaNova);
        ArrayList<Seguro> seguros = seg.getListaSeguroPorCliente(getCNPJ());
        for (Seguro seguro: seguros) seguro.calcularValor();
        System.out.println("O código da frota é " + frotaNova.getCode());
        return true;
    }

    public boolean veiculoCadastrado(String placa)
    {
        // retorna true se o veículo está em alguma frota
        boolean veiculoExiste = false;
        for (Frota frota: listaFrota)
        {
            Veiculo veiculoFrota = frota.procurarVeiculo(placa);
            if (veiculoFrota == (null)) veiculoExiste = false;
            else return true;
        }
        return veiculoExiste;
    }

    public Frota procurarFrota(String codigo)
    {
        Frota achou = null;
        for (Frota frota: listaFrota)
            if (frota.getCode().equals(codigo)) return frota;
        return achou;
    }

    public boolean frotaExistente(String codigo)
    {
        Frota frota = procurarFrota(codigo);
        if (frota == (null)) return false;
        return true;
    }

    public String listarFrotas()
    {
        String str = "Lista de frotas:\n";
        for (Frota frota: listaFrota) str += frota.toString() + "\n";
        return str;
    }

    public boolean atualizarFrota(String operacao, String codigoFrota, Veiculo veiculo, Seguradora seg)
    {
        boolean operacaoFeita = false;
        Frota frota = procurarFrota(codigoFrota);
        if (frota == (null)) return false;
        
        if (operacao.equals("remover"))
        {
            Veiculo veiculoFrota = frota.procurarVeiculo(veiculo.getPlaca());
            if (veiculoFrota == (null)) return false;
            operacaoFeita = frota.removeVeiculo(veiculoFrota);
            ArrayList<Seguro> seguros = seg.getListaSeguroPorCliente(getCNPJ());
            for (Seguro seguro: seguros) seguro.calcularValor();
            return operacaoFeita;
        }
        else if (operacao.equals("adicionar"))
        {
            boolean veiculoExiste = veiculoCadastrado(veiculo.getPlaca());
            if (veiculoExiste) return false;
            operacaoFeita = frota.addVeiculo(veiculo);
            ArrayList<Seguro> seguros = seg.getListaSeguroPorCliente(getCNPJ());
            for (Seguro seguro: seguros) seguro.calcularValor();
            return operacaoFeita;
        }
        return operacaoFeita;
    }

    public boolean atualizarFrota(String operacao, String codigoFrota, Seguradora seg)
    {
        Frota frota = procurarFrota(codigoFrota);
        if (frota == (null)) return false;
        if (!operacao.equals("remover toda")) return false;
        listaFrota.remove(frota);
        ArrayList<Seguro> seguros = seg.getListaSeguroPorCliente(getCNPJ());
        for (Seguro seguro: seguros) seguro.calcularValor();
        return true;
    }

    public String getVeiculosPorFrota(String codigo)
    {
        Frota frota = procurarFrota(codigo);
        return frota.listarVeiculos();
    }

    public String toString()
    {
        String str;
        str = "Dados do cliente:\n" + super.toString() + "\nCNPJ: " + getCNPJ() + 
                "\nData de fundacao: " + imprimeData(dataFundacao) + 
                "\nQuantidade de funcionários: " + getQuantFuncionarios() + "\n" +
                listarFrotas();
        return str;
    }        
}

package Lab06;

import java.util.Calendar;

public class SeguroPF extends Seguro 
{
    Veiculo veiculo;
    ClientePF cliente;

    public SeguroPF(Calendar dataInicio, Calendar dataFim, Seguradora seguradora,
                    Veiculo veiculo, ClientePF cliente) 
    {
        super(dataInicio, dataFim, seguradora);
        setVeiculo(veiculo);
        setCliente(cliente);
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente()
    {
        return cliente;
    }

    public void setCliente(ClientePF cliente)
    {
        this.cliente = cliente;
    }

    @Override
    public void calcularValor()
    {
        double quantSinistroCliente = (double) getListaSinistros().size();
        double sinistrosCondutor = (double) quantSinistroCondutor();
        double quantVeiculos = (double) getCliente().getListaVeiculos().size();
        long idade = calcularTempoAnos(getCliente().getDataNascimento());
        double base = CalcSeguro.VALOR_BASE.getOperacao();

        if (idade >= 18 && idade < 30)
        {
            double fatorIdade = CalcSeguro.FATOR_18_30.getOperacao();
            double seguro = base * fatorIdade * ( 1.0 + 1.0 / (quantVeiculos + 2.0)) * 
                (2.0 + quantSinistroCliente / 10.0) * (5 + sinistrosCondutor / 10.0);
            setValorMensal(seguro);
        }
        else if (idade >= 30 && idade <= 60)
        {
            double fatorIdade = CalcSeguro.FATOR_30_60.getOperacao();
            double seguro = base * fatorIdade * ( 1.0 + 1.0 / (quantVeiculos + 2.0)) * 
                (2.0 + quantSinistroCliente / 10.0) * (5.0 + sinistrosCondutor / 10.0);
            setValorMensal(seguro);
        }
        else if (idade > 60)
        {
            double fatorIdade = CalcSeguro.FATOR_60_90.getOperacao();
            double seguro = base * fatorIdade * ( 1.0 + 1.0 / (quantVeiculos + 2.0)) * 
                (2.0 + quantSinistroCliente / 10.0) * (5.0 + sinistrosCondutor / 10.0);
            setValorMensal(seguro);
        }
    }

    @Override
    public String toString()
    {
        String str = super.toString();
        str += "Cliente: " + getCliente().getNome() + "\nCPF: " + getCliente().getCPF() +
            "\nVeiculo: " + getVeiculo().toString();
        return str;
    }
}

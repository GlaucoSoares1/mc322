package lab05;
import java.util.Calendar;

public class SeguroPJ extends Seguro
{
    Frota frota;
    ClientePJ cliente;

    public SeguroPJ(Calendar dataInicio, Calendar dataFim, Seguradora seguradora, 
                    Frota frota, ClientePJ cliente) 
    {
        super(dataInicio, dataFim, seguradora);
        setFrota(frota);
        setCliente(cliente);
    }

    public Frota getFrota() 
    {
        return this.frota;
    }

    public void setFrota(Frota frota) 
    {
        this.frota = frota;
    }

    public ClientePJ getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientePJ cliente) 
    {
        this.cliente = cliente;
    }
    
    public void calcularValor()
    {
        double quantSinistroCliente = getListaSinistros().size();
        double sinistrosCondutor = quantSinistroCondutor();
        double anosExistencia = (int) calcularTempoAnos(getCliente().getDataFundacao());
        double quantVeiculos = getCliente().quantVeiculos();
        double base = CalcSeguro.VALOR_BASE.getOperacao();

        double seguro = base * (10.0 + getCliente().getQuantFuncionarios() / 10.0) *
                        (1.0 + 1.0 / (quantVeiculos + 2.0)) * (1.0 + 1.0 / (anosExistencia + 2.0)) *
                        (2.0 + quantSinistroCliente / 10.0) * (5.0 + sinistrosCondutor / 10.0);
        setValorMensal(seguro);
    }

    public String toString()
    {
        String str = super.toString();
        str += "Cliente: " + getCliente().getNome() + "\nCNPJ: " + getCliente().getCNPJ() + 
            "\nFrota:\n\t" + getFrota().toString();
        return str;
    }
}

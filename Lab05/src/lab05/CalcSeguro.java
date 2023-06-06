package lab05;

public enum CalcSeguro 
{
    VALOR_BASE (10.0), 
    FATOR_18_30 (1.25), // menor que 30
    FATOR_30_60 (1.0), // 30 a 60
    FATOR_60_90 (1.5); // maior que 60

    public final double fator;

    CalcSeguro (double fator)
    {
        this.fator = fator;
    }

    public double getOperacao()
    {
        return fator;
    }
}
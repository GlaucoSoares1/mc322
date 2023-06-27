package Lab06;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public abstract class Seguro 
{
    private final int id;
    private Calendar dataInicio;
    private Calendar dataFim;
    private Seguradora seguradora;
    private HashMap<Integer, Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;

    public Seguro(Calendar dataInicio, Calendar dataFim, Seguradora seguradora) 
    {
        id = gerarID(seguradora);
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setSeguradora(seguradora);
        listaSinistros = new HashMap<Integer, Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
        valorMensal = 0;
    }

    public int getId()
    {
        return id;
    }

    public Calendar getDataInicio() 
    {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) 
    {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() 
    {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) 
    {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() 
    {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) 
    {
        this.seguradora = seguradora;
    }

    public HashMap<Integer, Sinistro> getListaSinistros() 
    {
        return listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores()
    {
        return listaCondutores;
    }

    public double getValorMensal() 
    {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) 
    {
        this.valorMensal = valorMensal;
    }

    public boolean autorizarCondutor(Condutor condutor)
    {
        boolean existe = listaCondutores.contains(condutor);
        if (!existe)
        {
            listaCondutores.add(condutor);
            calcularValor();
            return true;
        }
        return false;
    }

    public boolean desautorizarCondutor(Condutor condutor)
    {
        boolean existe = listaCondutores.contains(condutor);
        if (existe) listaCondutores.remove(condutor);
        calcularValor();
        return existe;
    }

    public Condutor procurarCondutor(String cpf)
    {
        Condutor condutor = null;
        for (Condutor condutorExistente: listaCondutores)
            if (condutorExistente.getCPF().equals(cpf)) 
                return condutorExistente;
        return condutor;
    }

    public String listarSinistros()
    {
        String str;
        str = "Lista de sinistros:\n";
        for (Sinistro sinistro: listaSinistros.values())
            str += sinistro.toString() + "\n\n";
        return str;
    }

    public String listarCondutores()
    {
        String str;
        str = "Condutores cadastrados:\n\n";
        for (Condutor condutor : listaCondutores) 
            str += condutor.toString() + "\n\n";
        return str;
    }
    
    public boolean gerarSinistro(Calendar data, String endereco, Condutor condutor)
    {
        boolean sinistroValido = true;
        for (Sinistro sinistro: listaSinistros.values())
        {
            if (sinistro.getCondutor().equals(condutor) && sinistro.getData().equals(data) 
                && sinistro.getEndereco().equals(endereco))
            {
                sinistroValido = false;
                break;
            }
        }
        if (sinistroValido)
        {
            Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
            System.out.println("A ID do sinistro é " + sinistro.getId());
            listaSinistros.put(sinistro.getId(), sinistro);
            condutor.adicionarSinistro(sinistro);
            calcularValor();
        }
        return sinistroValido;
    }

    public boolean removerSinistro(int id)
    {
        Integer idInt = id;
        if (!listaSinistros.containsKey(idInt)) return false;
        Sinistro sinistro = procurarSinistro(id);
        Condutor condutor = sinistro.getCondutor();
        condutor.removerSinistro(sinistro);
        listaSinistros.remove(idInt);
        calcularValor();
        return true;
    }

    public Sinistro procurarSinistro(int id)
    {
        Integer idInt = id;
        if (listaSinistros.containsKey(idInt)) return listaSinistros.get(idInt);
        return null;
    }

    private int gerarID(Seguradora seguradora)
    {
        Random random = new Random();
        int lim = 1000000;
        int numAleatorio = random.nextInt(1, lim);
        if (!seguradora.getListaSeguros().isEmpty())
            while (seguradora.getListaSeguros().containsKey(numAleatorio))
                numAleatorio = random.nextInt(1, lim);
        return numAleatorio;
    }

    protected long calcularTempoAnos(Calendar data)
    {
        long dataHoje = Calendar.getInstance().getTime().getTime();
        long nascimento = data.getTime().getTime();
        long diferenca = dataHoje - nascimento;
        long diferencaAnos = (diferenca / (1000*60*60*24*365));
        return diferencaAnos;
    }

    public int quantSinistroCondutor()
    {
        int quant = 0;
        for (Condutor condutor: listaCondutores)
            quant += condutor.getListaSinistro().size();
        return quant;
    }

    public abstract void calcularValor();

    protected String imprimeData(Calendar data)
    {
        String str;
        str = Integer.toString(data.get(Calendar.DATE)) + "/" +
            Integer.toString(data.get(Calendar.MONTH)) + "/" +
            Integer.toString(data.get(Calendar.YEAR));
        return str;
    }

    @Override
    public String toString()
    {
        String str = "Dados do seguro:\n";
        str += "ID: " + getId() + "\nData de início: " + imprimeData(dataInicio) + 
            "\nData do fim: " + imprimeData(dataFim) + "\nSeguradora: " + 
            getSeguradora().getNome() + "\n Valor mensal do seguro: " + getValorMensal() + "\n" + 
            listarSinistros() + listarCondutores();
        return str;
    }
}

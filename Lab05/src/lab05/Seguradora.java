package lab05;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Seguradora
{
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private double receita;
    private ArrayList<Cliente> listaClientes;
    private HashMap<Integer, Seguro> listaSeguros;

    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email)
    {
        CNPJ = cnpj;
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
        setEmail(email);
        receita = 0;
        listaClientes = new ArrayList<Cliente>();
        listaSeguros = new HashMap<Integer, Seguro>();
    }

    public String getCNPJ() 
    {
        return CNPJ;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getTelefone() 
    {
        return telefone;
    }

    public void setTelefone(String telefone) 
    {
        this.telefone = telefone;
    }

    public String getEndereco() 
    {
        return endereco;
    }

    public void setEndereco(String endereco) 
    {
        this.endereco = endereco;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public double getReceita()
    {
        return receita;
    }

    public void setReceita(double receita)
    {
        this.receita = receita;
    }

    public ArrayList<Cliente> getListaClientes()
    {
        return listaClientes;
    }

    public HashMap<Integer, Seguro> getListaSeguros() 
    {
        return listaSeguros;
    }

    public boolean cadastrarCliente(Cliente cliente)
    {
        if (listaClientes.contains(cliente)) return false;
        listaClientes.add(cliente);
        return true;
    }

    public Cliente procurarCliente(String codigo)
    {
        if (listaClientes.isEmpty()) return null;
        String codigoModificado = codigo.replaceAll("[^0-9]+","");
        if ((codigoModificado.length() != 11) && (codigoModificado.length() != 14))
            return null;
        int tamanhoCodigo = codigoModificado.length();
        for (Cliente cliente: listaClientes)
        {
            if ((tamanhoCodigo == 11) && (cliente instanceof ClientePF))
            {
                String cpfModificado = ((ClientePF)cliente).getCPF().replaceAll("[^0-9]+","");
                if (cpfModificado.equals(codigoModificado)) return cliente;
            }
            else if ((tamanhoCodigo == 14) && (cliente instanceof ClientePJ))
            {
                String cnpjModificado = ((ClientePJ)cliente).getCNPJ().replaceAll("[^0-9]+","");
                if (cnpjModificado.equals(codigoModificado)) return cliente;
            }
        }
        return null;
    }

    public boolean removerCliente(String codigo)
    {
        Cliente clienteProcurado = procurarCliente(codigo);
        if (clienteProcurado == (null)) return false;
        String codigoModificado = codigo.replaceAll("[^0-9]+","");
        Integer tamanhoCodigo = codigoModificado.length();
        for (Seguro seguro: listaSeguros.values())
        {
            if ((tamanhoCodigo == 11) && (seguro instanceof SeguroPF))
            {
                String cpfModificado = ((SeguroPF)seguro).getCliente().getCPF().replaceAll("[^0-9]+","");
                if (cpfModificado.equals(codigoModificado));
                    listaSeguros.remove(seguro.getId(), seguro);
            }
            else if ((tamanhoCodigo == 14) && (seguro instanceof SeguroPJ))
            {
                String cnpjModificado = ((SeguroPJ)seguro).getCliente().getCNPJ().replaceAll("[^0-9]+","");
                if (cnpjModificado.equals(codigoModificado)) 
                    listaSeguros.remove(seguro.getId(), seguro);
            }
        }
        listaClientes.remove(clienteProcurado);
        return true;
    }

    public String listarClientes()
    {
        String str;
        str = "Clientes cadastrados:\n\n";
        for (Cliente cliente : listaClientes) 
            str += cliente.toString() + "\n\n";
        return str;
    }

    public boolean gerarSeguro(Calendar dataInicio, ClientePF cliente, Veiculo veiculo)
    {
        // Seguro tem validade de 1 ano
        Cliente clienteSeg = procurarCliente(cliente.getCPF());
        if (clienteSeg == (null)) return false;
        Veiculo veiculoCliente = cliente.procuraVeiculo(veiculo.getPlaca());
        if (veiculoCliente == (null)) return false;
        Calendar dataFim = (Calendar)dataInicio.clone();
        dataFim.add(Calendar.YEAR, 1);
        SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        listaSeguros.put(seguro.getId(), seguro);
        System.out.println("A id do seguro é " + seguro.getId());
        seguro.calcularValor();
        return true;
    }

    public boolean gerarSeguro(Calendar dataInicio, ClientePJ cliente, Frota frota)
    {
        // Seguro tem validade de 1 ano 
        Cliente clienteSeg = procurarCliente(cliente.getCNPJ());
        if (clienteSeg == (null)) return false;
        boolean frotaCliente = cliente.frotaExistente(frota.getCode());
        if (!frotaCliente) return false;
        Calendar dataFim = (Calendar)dataInicio.clone();
        dataFim.add(Calendar.YEAR, 1);
        SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, this, frota, cliente);
        listaSeguros.put(seguro.getId(), seguro);
        System.out.println("A id do seguro é " + seguro.getId());
        seguro.calcularValor();
        return true;
    }

    public boolean cancelarSeguro(int idSeguro)
    {
        Integer idSegInt = idSeguro;
        if (!listaSeguros.containsKey(idSegInt)) return false;
        listaSeguros.remove(idSegInt);
        return true;
    }

    public Seguro procurarSeguro(int idSeguro)
    {
        Seguro seguro = null;
        if (listaSeguros.containsKey(idSeguro)) return listaSeguros.get(idSeguro);
        return seguro;
    }

    public String listarSeguros()
    {
        String str = "Seguros cadastrados:\n\n";
        for (Seguro seguro: listaSeguros.values())
            str += seguro.toString() + "\n\n";
        return str;
    }

    public ArrayList<Seguro> getListaSeguroPorCliente(String codigo)
    {
        Cliente cliente = procurarCliente(codigo);
        ArrayList<Seguro> listaSegurosCliente = new ArrayList<Seguro>();
        for (Seguro seguro: listaSeguros.values())
        {
            if ((seguro instanceof SeguroPF) && (cliente instanceof ClientePF))
            {
                if (((SeguroPF)seguro).getCliente().equals(cliente))
                    listaSegurosCliente.add(seguro);
            }
            else if ((seguro instanceof SeguroPJ) && (cliente instanceof ClientePJ))
            {
                if (((SeguroPJ)seguro).getCliente().equals(cliente))
                    listaSegurosCliente.add(seguro);
            }
        }
        return listaSegurosCliente;
    }

    public String listarSegurosPorCliente(String codigo)
    {
        ArrayList<Seguro> listaSeguros = getListaSeguroPorCliente(codigo);
        String str = "Lista de seguros:\n\n";
        for (Seguro seguro: listaSeguros) str += seguro.toString() + "\n\n";
        return str;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(String codigo)
    {
        Cliente cliente = procurarCliente(codigo);
        ArrayList<Sinistro> listaSinistrosCliente = new ArrayList<Sinistro>();
        for (Seguro seguro: listaSeguros.values())
        {
            if ((seguro instanceof SeguroPF) && (cliente instanceof ClientePF))
            {
                if (((SeguroPF)seguro).getCliente().equals(cliente))
                    for (Sinistro sinistro: seguro.getListaSinistros().values())
                        listaSinistrosCliente.add(sinistro);
            } 
            else if ((seguro instanceof SeguroPJ) && (cliente instanceof ClientePJ))
            {
                if (((SeguroPJ)seguro).getCliente().equals(cliente))
                    for (Sinistro sinistro: seguro.getListaSinistros().values())
                        listaSinistrosCliente.add(sinistro);
            }
        }
        return listaSinistrosCliente;
    }

    public String listarSinistrosPorCliente(String codigo)
    {
        ArrayList<Sinistro> listaSinistros = getSinistrosPorCliente(codigo);
        String str = "Lista de sinistros:\n\n";
        for (Sinistro sinistro: listaSinistros)
            str += sinistro.toString() + "\n\n";
        return str;
    }

    public boolean visualizarSinistro(String codigoCliente, int idSinistro)
    {
        Cliente cliente = procurarCliente(codigoCliente);
        if (cliente == (null)) return false;
        ArrayList<Sinistro> listaSinistros = getSinistrosPorCliente(codigoCliente);
        Integer idSinistroInt = idSinistro;
        for (Sinistro sinistro: listaSinistros)
            if (idSinistroInt.equals(sinistro.getId())) 
            {
                System.out.println(sinistro);
                return true;
            }
        return false;
    }

    public void calcularReceita()
    {
        double receita = 0;
        for (Seguro seguro: listaSeguros.values())
        {
            seguro.calcularValor();
            receita += seguro.getValorMensal();
        }
        setReceita(receita);
    }

    @Override
    public String toString()
    {
        String str;
        str = "Nome: " + getNome() + "\nCNPJ: " + getCNPJ() + 
            "\nTelefone: " + getTelefone() + "\nEndereço: " + getEndereco() + 
            "\nEmail: " + getEmail() + "\nQuantidade de clientes: " + 
            listaClientes.size() + "\nQuantidade de seguros cadastrados: " + 
            listaSeguros.size() + "\nReceita total: " + getReceita();
        return str;
    }
}
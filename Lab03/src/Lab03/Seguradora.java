package Lab03;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Seguradora 
{
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private HashMap<Integer, Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco)
    {
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSinistros = new HashMap<Integer, Sinistro>();
    }

    // Getters e setters
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    // Clientes
    public boolean cadastrarCliente(ClientePF cliente)
    {
        if (this.listaClientes.contains(cliente)) return false;
        this.listaClientes.add(cliente);
        return true;
    }

    public boolean cadastrarCliente(ClientePJ cliente)
    {
        if (this.listaClientes.contains(cliente)) return false;
        this.listaClientes.add(cliente);
        return true;
    }

    public boolean removerCliente(String nome)
    {
        boolean achouCliente = false;
        for (Cliente cliente: listaClientes)
            if (cliente.getNome().equals(nome))
            {
                achouCliente = true;
                listaClientes.remove(cliente);
                for (Sinistro sinistro: listaSinistros.values())
                    if (sinistro.getCliente().getNome().equals(nome))
                        listaSinistros.remove(sinistro.getId(), sinistro);
                break;
            }
        return achouCliente;
    }

    public Cliente procurarCliente(String nome)
    {
        int pos_ini = 0;
        for (Cliente cliente : listaClientes) 
            if (cliente.getNome().equals(nome))
                pos_ini = listaClientes.indexOf(cliente);
        return listaClientes.get(pos_ini);
    }

    public String listarClientes()
    {
        String str;
        str = "Clientes cadastrados:\n ";
        for (Cliente cliente : listaClientes) 
            str += cliente.toString() + "\n\t";
        return str;
    }
    
    // Sinistros
    public HashMap<Integer, Sinistro> getListaSinistro()
    {
        return listaSinistros;
    }

    public boolean gerarSinistro(Calendar data, String endereco, Veiculo veiculo, Cliente cliente)
    {
        boolean sinistroValido = true;
        for (Sinistro sinistro: listaSinistros.values())
        {
            if (sinistro.getCliente().equals(cliente) && sinistro.getVeiculo().equals(veiculo) && 
                sinistro.getData().equals(data) && sinistro.getEndereco().equals(endereco))
            {
                sinistroValido = false;
                break;
            }
        }
        if (sinistroValido)
        {
            Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
            insereSinistro(sinistro);
        }
        return sinistroValido;
    }

    private void insereSinistro(Sinistro sinistro)
    {
        listaSinistros.put(sinistro.getId(), sinistro);
    }

    public boolean visualizarSinistro(String nome)
    {
        Cliente cliente = procurarCliente(nome);
        boolean sinistroValido = false;
        for (Sinistro sinistro: listaSinistros.values())
        {
            if (sinistro.getCliente().equals(cliente))
            {
                sinistroValido = true;
                break;
            }
        }
        if (sinistroValido)
        {
            for (Sinistro sinistro: listaSinistros.values())
                if (sinistro.getCliente().equals(cliente))
                    System.out.println(sinistro);
        }
        return sinistroValido;
    }

    public String listarSinistros()
    {
        String str;
        str = "Lista de sinistros:\n";
        for (Sinistro sinistro: listaSinistros.values())
            str += sinistro.toString() + "\n\n";
        return str;
    }

    public String toString()
    {
        String str;
        str = "Nome: " + getNome() + "\nTelefone: " + getTelefone() 
            + "\nEndereco: " + getEndereco() + "\nNumero de sinistros registrados: " + 
            listaSinistros.size() + "\nNumero de clientes registrados: " + listaClientes.size();
        return str;
    }
}

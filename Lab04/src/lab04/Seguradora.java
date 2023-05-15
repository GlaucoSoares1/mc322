package lab04;
// REMOVER E ADICIONAR VEICULO PARA CLIENTE
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Seguradora 
{
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private double receita;
    private HashMap<Integer, Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco)
    {
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
        this.receita = 0;
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

    public HashMap<Integer, Sinistro> getListaSinistro()
    {
        return listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes()
    {
        return listaClientes;
    }

    public double getReceita()
    {
        return receita;
    }

    // Clientes

    // cadastra e calcula o valor do seguro, se for possivel o cadastro
    public boolean cadastrarCliente(Cliente cliente)
    {
        if (listaClientes.contains(cliente)) return false;
        listaClientes.add(cliente);
        calcularPrecoSeguroCliente(cliente);
        return true;
    }

    public boolean removerVeiculoCliente(Cliente cliente, String placa)
    {
        Veiculo veiculo = cliente.procuraVeiculo(placa);
        if (veiculo.equals(null)) return false;
        cliente.getVeiculos().remove(veiculo);
        calcularPrecoSeguroCliente(cliente);
        return true;
    }

    public boolean adicionarVeiculoCliente(Cliente cliente, Veiculo veiculo)
    {
        for (Veiculo veiculoCliente: cliente.getVeiculos())
            if (veiculoCliente.getPlaca().equals(veiculo.getPlaca()))
                return false;
        cliente.getVeiculos().add(veiculo);
        calcularPrecoSeguroCliente(cliente);
        return true;
    }

    // remove cliente e, se tiver sinistros, deleta os sinistros
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
                    {
                        listaSinistros.remove(sinistro.getId(), sinistro);
                        cliente.retirarSinistro();
                    }
                break;
            }
        return achouCliente;
    }

    public Cliente procurarCliente(String nome)
    {
        Cliente clienteProcurado = null;
        for (Cliente cliente: listaClientes)
        {
            if (cliente.getNome().equals(nome)) clienteProcurado = cliente;
            break;
        }
        return clienteProcurado;
    }

    public String listarClientes()
    {
        String str;
        str = "Clientes cadastrados:\n\n";
        for (Cliente cliente : listaClientes) 
            str += cliente.toString() + "\n\n";
        return str;
    }

    public String listarVeiculos()
    {
        String str;
        str = "Veiculos da seguradora:\n ";
        for (Cliente cliente : listaClientes)
            if (cliente.getVeiculos().size() > 0)
            {
                for (Veiculo veiculo: cliente.getVeiculos())
                str += "\nVeiculo:\n\t" + veiculo.toString() + "\n";
            }
        return str;
    }
    
    // Transfere os veiculos de um doador para um receptor
    // Na sequencia recalcula os precos dos seguros
    public void transferirSeguro(Cliente doador, Cliente receptor)
    {
        for (Veiculo veiculo: doador.getVeiculos())
            receptor.getVeiculos().add(veiculo);
        doador.getVeiculos().clear();
        calcularPrecoSeguroCliente(doador);
        calcularPrecoSeguroCliente(receptor);
    }

    // Sinistros

    // Se for possivel gerar o sinistro, atualiza o valor do seguro do cliente
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
            cliente.adicionarSinistro();
            listaSinistros.put(sinistro.getId(), sinistro);
            calcularPrecoSeguroCliente(cliente);
        }
        return sinistroValido;
    }

    public boolean visualizarSinistro(String nome)
    {
        boolean sinistroValido = false;
        for (Sinistro sinistro: listaSinistros.values())
        {
            if (sinistro.getCliente().getNome().equals(nome))
            {
                sinistroValido = true;
                System.out.println(sinistro);
                break;
            }
        }
        return sinistroValido;
    }

    public boolean removerSinistro(Calendar data, String endereco, Cliente cliente)
    {
        boolean removido = false;
        for (Sinistro sinistro: listaSinistros.values())
        {
            if (sinistro.getCliente().equals(cliente) && sinistro.getData().equals(data)
                && sinistro.getEndereco().equals(endereco))
            {
                listaSinistros.remove(sinistro.getId(), sinistro);
                cliente.retirarSinistro();
                calcularPrecoSeguroCliente(cliente);
                removido = true;
            }
        }
        return removido;
    }

    public String listarSinistrosSeguradora()
    {
        String str;
        str = "Lista de sinistros:\n";
        for (Sinistro sinistro: listaSinistros.values())
            str += sinistro.toString() + "\n\n";
        return str;
    }

    public String listarSinistrosCliente(Cliente cliente)
    {
        String str;
        str = "Lista de sinistros do cliente " + cliente.getNome() + ":\n";
        for (Sinistro sinistro: listaSinistros.values())
            if (sinistro.getCliente().equals(cliente))
                str += sinistro.toString() + "\n\n";
        return str;
    }

    public void calcularPrecoSeguroCliente(Cliente cliente)
    {
        double preco = cliente.calculaScore() * (1 + cliente.getQuantSinistros());
        cliente.setValorSeguro(preco);
    }

    public void calcularReceita()
    {
        double receita = 0;
        for (Cliente cliente: listaClientes) receita += cliente.getValorSeguro();
        this.receita = receita;
    }

    @Override
    public String toString()
    {
        String str;
        str = "Nome: " + getNome() + "\nTelefone: " + getTelefone() 
            + "\nEndereco: " + getEndereco() + "\nNumero de sinistros registrados: " + 
            listaSinistros.size() + "\nNumero de clientes registrados: " + listaClientes.size();
        return str;
    }
}

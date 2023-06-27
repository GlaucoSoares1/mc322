package Lab06;

import java.util.ArrayList;
import java.util.Calendar;

public class Condutor 
{
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Calendar dataNasc;
    private ArrayList<Sinistro> listaSinistro;

    public Condutor(String cpf, String nome, String telefone, String endereco, String email, Calendar dataNasc) 
    {
        this.cpf = cpf;
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
        setEmail(email);
        setDataNasc(dataNasc);
        listaSinistro = new ArrayList<Sinistro>();
    }
    
    public String getCPF()
    {
        return cpf;
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

    public Calendar getDataNasc() 
    {
        return dataNasc;
    }

    public void setDataNasc(Calendar dataNasc) 
    {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Sinistro> getListaSinistro() 
    {
        return listaSinistro;
    }

    public boolean adicionarSinistro(Sinistro sinistro)
    {
        boolean existe = listaSinistro.contains(sinistro);
        if (!existe)
        {
            listaSinistro.add(sinistro);
            return true;
        }
        return false;
    }

    public boolean removerSinistro(Sinistro sinistro)
    {
        boolean existe = listaSinistro.contains(sinistro);
        if (existe) listaSinistro.remove(sinistro);
        return existe;
    }

    public String listarSinistros()
    {
        String str;
        str = "Lista de sinistros do condutor " + getNome() + ":\n";
        for (Sinistro sinistro: listaSinistro) str += sinistro.toString() + "\n\n";
        return str;
    }

    private String imprimeData(Calendar data)
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
        String str;
        str = "Nome do condutor: " + getNome() + "\nCPF: " + getCPF() + 
                "\nTelefone: " + getTelefone() + "\nEndereco: " + getEndereco() + 
                "\nEmail: " + getEmail() + "\nData de nascimento: " + imprimeData(dataNasc) + 
                "\nSinistros:\n\t" + listarSinistros();
        return str;
    }
}

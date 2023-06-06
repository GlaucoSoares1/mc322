package lab05;

public abstract class Cliente 
{
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    // Construtor
    public Cliente(String nome, String endereco, String telefone, String email)
    {
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        setEmail(email);
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

    public String getEndereco() 
    {
        return endereco;
    }
    
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
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

    @Override
    public String toString()
    {
        String str;
        str = "Nome do cliente: " + getNome() + "\nTelefone: " + getTelefone() +
            "\nEndereco: " + getEndereco() + "\nEmail: " + getEmail();
        return str;
    }
}

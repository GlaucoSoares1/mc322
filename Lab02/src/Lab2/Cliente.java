package Lab2;
public class Cliente 
{
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco)
    {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);
        this.setIdade(idade);
        this.setEndereco(endereco);
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

    public String getCpf() 
    {
        return cpf;
    }
    
    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getDataNascimento() 
    {
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() 
    {
        return idade;
    }
    
    public void setIdade(int idade)
    {
        this.idade = idade;
    }

    public String getEndereco() 
    {
        return endereco;
    }
    
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    // Verificar se o CPF informado e valido
    public boolean validarCPF(String cpf)
    {
        // retira tudo que nao for digito
        cpf = cpf.replaceAll("[^0-9]+","");
        if (cpf.length() != 11) return false;

        // Dígitos não podem ser todos iguais
        boolean digitos_iguais = caracteresTodosIguais(cpf);
        if (digitos_iguais) return false;

        // Digitos verificadores
        boolean primDigito = verificaDigitoCPF(cpf, 1);
        if (!primDigito) return false;
        boolean segDigito = verificaDigitoCPF(cpf, 2);
        if (!segDigito) return false;
        return true;
    }

    // Verifica se a string e formada apenas por um caracter
    private boolean caracteresTodosIguais(String nome)
    {
        // Compara com o 1o caracter
        int digitos_iguais = 1;
        for (int i = 1; i < nome.length(); i++)
        {
            if (nome.charAt(i) == nome.charAt(0))
                digitos_iguais++;
        }
        if (digitos_iguais != nome.length())
            return false;
        return true;
    }

    // Implementa o algoritmo de verificacao dos digitos do cpf
    private boolean verificaDigitoCPF(String cpf, int digito)
    {
        // CPF deve ter 11 dígitos 
        // digito 1 ou 2 (1o ou 2o digito)
        int digitoFinal = 0, soma = 0, verifica = 0;

        if (digito == 1) digitoFinal = 9;
        else if (digito == 2) digitoFinal = 10;

        for (int i = 0; i < digitoFinal; i ++)
            soma += (int)(cpf.charAt(i) - 48) * (digitoFinal + 1 - i);

        verifica = (soma * 10) % 11;
        if (verifica == 10) verifica = 0;
        if ((cpf.charAt(digitoFinal) - 48) != verifica)
            return false;
        return true;
    }

    public String toString()
    {
        String string;
        string = "Nome: " + getNome() + "\nCPF: " + getCpf() + "\nNascimento: " + getDataNascimento() +
                "\nEndereco: " + getEndereco() + "\nIdade: " + getIdade();
        return string;
    }
}

package lab05;

import java.util.ArrayList;

public class Frota 
{
    private static final int SIZE = 7;
    private final String code; // Código aleatório de tamanho 7
    private ArrayList<Veiculo> listaVeiculos;

    public Frota(ClientePJ cliente)
    {
        code = gerarStringAleatoria(SIZE, cliente);
        listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCode() 
    {
        return code;
    }

    public ArrayList<Veiculo> getListaVeiculos() 
    {
        return listaVeiculos;
    }

    public boolean addVeiculo(Veiculo veiculo)
    {
        boolean existe = listaVeiculos.contains(veiculo);
        if (!existe) 
        {
            listaVeiculos.add(veiculo);
            return true;
        }
        return false;
    }

    public boolean removeVeiculo(Veiculo veiculo)
    {
        boolean existe = listaVeiculos.contains(veiculo);
        if (existe) listaVeiculos.remove(veiculo);
        return existe;
    }

    public Veiculo procurarVeiculo(String placa)
    {
        Veiculo achou = null;
        for (Veiculo veiculo: listaVeiculos)
            if (veiculo.getPlaca().equals(placa))
            {
                achou = veiculo;
                break;
            }
        return achou;
    }

    public String listarVeiculos()
    {
        String str = "";
        for (int i = 0; i < listaVeiculos.size(); i++)
            str += "\nVeiculo " + (i + 1) + ":\n\t" + listaVeiculos.get(i).toString() + "\n";
        return str;
    }

    private static String gerarStringAleatoria(int tamanho, ClientePJ cliente)
    {
        // Valores de caracteres permitidos (letras maiúsculas e algarismos)
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        StringBuilder codigo = new StringBuilder(tamanho);
        do 
        {
            for (int i = 0; i < tamanho; i++)
            {
                // escolha aleatória de um caracter na string base
                int indice = (int) (string.length() * Math.random());
                // adiciona o caracter no código
                codigo.append(string.charAt(indice));
            }
        } while(!(cliente.procurarFrota(codigo.toString()) == (null)));
        return codigo.toString();
    }

    @Override
    public String toString()
    {
        String str;
        str = "Dados da frota:\n" + "Código da frota: " + getCode() + 
                "\nVeiculos: " + listarVeiculos();
        return str;
    }
}

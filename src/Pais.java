
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author willi
 */

public class Pais {
    private String codigo;
    private String nome;
    private String capital;
    private long populacao;
    private long area;
    private ArrayList<String> vizinhos;

    Pais(String codigo, String nome, String capital,
         long populacao, long area)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.capital = capital;
        this.populacao = populacao;
        this.area = area;

        // criar lista de vizinhos
        vizinhos = new ArrayList<String>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public ArrayList<String> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(ArrayList<String> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public void addVizinho(String codigo) {
        if (vizinhos.indexOf(codigo) < 0)
            vizinhos.add(codigo.toUpperCase());
    }

    public float densidade()
    {
        if (area == 0)
            return 0;
        else
            return (float)populacao / area;
    }

    public boolean equals(Pais p) {
        return codigo.equals(p.codigo);
    }

    public boolean seVizinho (Pais p){
        return (vizinhos.indexOf(p.codigo.toUpperCase()) >= 0);
    }

    public ArrayList<String> vizinhosEmComum(Pais p) {
        ArrayList<String> emComum = new ArrayList<String>();

        for (String cod : vizinhos) {
            if (p.vizinhos.indexOf(cod) >= 0)
                emComum.add(cod);
        }

        return emComum;
    }

    public void mostraDados () {
        System.out.println (codigo + " - " + nome);
        System.out.println ("    Capital: " + capital);

        System.out.println ("    Populacao: " +
                NumberFormat.getNumberInstance().format(populacao));

        System.out.println ("    Área: " +
                NumberFormat.getNumberInstance().format(area) + " Km2");

        if (vizinhos.size() > 0)
        {
            System.out.print ("    Vizinhos:");
            for (String str : vizinhos) {
                System.out.print(" " + str);
            }
            System.out.println();
        }
    }
}

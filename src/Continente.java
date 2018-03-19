import java.util.ArrayList;

/**
 *
 * @author Rodrigo Aguiar
 */
public class Continente{
    private String nome;
    private ArrayList<Pais> paisesDoContinente = new ArrayList<>();

    Continente(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Pais> getPaisesDoContinente() {
        return paisesDoContinente;
    }

    public void setPaisesDoContinente(ArrayList<Pais> paisesDoContinente) {
        this.paisesDoContinente = paisesDoContinente;
    }

    public void adicionarPais(Pais P){
        paisesDoContinente.add(P);
    }

    public long dimensao() {
        long dimensaoTotal = 0;
        for (Pais p : paisesDoContinente) {
            dimensaoTotal = p.getArea();
        }
        return dimensaoTotal;
    }

    public long populacao(){
        long populacaoTotal = 0;
        for (Pais p : paisesDoContinente)
        {
            populacaoTotal = p.getPopulacao();
        }
        return populacaoTotal;
    }

    public float densidade(){
        if(this.dimensao() == 0)
            return 0;
        else
            return (float)this.populacao()/this.dimensao();
    }

    public Pais maiorPopulacao(){
        Pais paisPopuloso = null;
        long populacaoTotal = 0;
        for (Pais p :
                paisesDoContinente) {
            if(p.getPopulacao() > populacaoTotal){
                paisPopuloso = p;
                populacaoTotal = p.getPopulacao();            }
        }
        return paisPopuloso;
    }

    public Pais menorPopulacao(){
        Pais paisNaoPopuloso = null;
        long populacaoTotal = 0;
        for (Pais p :
                paisesDoContinente) {
            if(p.getPopulacao() < populacaoTotal){
                paisNaoPopuloso = p;
                populacaoTotal = p.getPopulacao();            }
        }
        return paisNaoPopuloso;
    }

    public Pais maiorDimensao(){
        Pais maiorPais = null;
        long dimensao = 0;
        for (Pais p :
                paisesDoContinente) {
            if(p.getArea() > dimensao){
                maiorPais = p;
                dimensao = p.getArea();            }
        }
        return maiorPais;
    }

    public Pais menorDimensao(){
        Pais menorPais = null;
        long dimensao = 0;
        for (Pais p :
                paisesDoContinente) {
            if(p.getArea() < dimensao){
                menorPais = p;
                dimensao = p.getArea();            }
        }
        return menorPais;
    }

    public float razaoMaiorMenorPais(){
        return (float)this.maiorDimensao().getArea()/this.menorDimensao().getArea();
    }

}

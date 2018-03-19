import java.io.*;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 * @author willi
 */
public class Paises {

    private static Scanner leia = new Scanner(System.in);
    private static ArrayList<Pais> paises = new ArrayList<>();
    private static ArrayList<Continente> continentes = new ArrayList<>();
    final static private String NOME_ARQ_PAISES = "paises.txt";
    final static private String NOME_ARQ_CONTINENTES = "continentes.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int opcao;
        do
        {
            opcao = menu();
            switch(opcao)
            {
                case 1: incluiPais(); break;
                case 2: incluiVizinhos(); break;
                case 3: listarPaises(); break;
                case 4: paisesVizinhos(); break;
                case 5: densidadePopulacional(); break;
                case 6: vizinhosEmComum(); break;
                case 7: lerDados(); break;
                case 8: gravarDados(); break;
                case 9: gravarDadosContinentes(); break;
                case 10: lerDadosContinentes(); break;
                case 11: gravarDados(); break;
            }
        }while(opcao != 12);
    }

    private static int menu(){
        System.out.println();
        System.out.println("+===============================+");
        System.out.println("|          O P Ç Õ E S          |");
        System.out.println("+===============================+");
        System.out.println("| 1. Incluir País               |");
        System.out.println("| 2. Incluir Vizinhos           |");
        System.out.println("| 3. Listar Países              |");
        System.out.println("| 4. Países são Vizinhos?       |");
        System.out.println("| 5. Densidade Populacional     |");
        System.out.println("| 6. Vizinhos em Comum          |");
        System.out.println("| 7. Ler Dados em Arquivo       |");
        System.out.println("| 8. Gravar Dados em Arquivo    |");
        System.out.println("| 9. Gravar Dados de Continentes|");
        System.out.println("| 10. Ler Dados de Continentes  |");
        System.out.println("| 11. Encerrar o Programa       |");
        System.out.println("| 12. Encerrar o Programa       |");
        System.out.println("+===============================+");

        int opcao;
        do {
            try {
                System.out.print("Digite a sua opção (1 a 12): ");
                opcao = leia.nextInt();
            }
            catch(Exception e)
            {
                leia.nextLine();
                opcao = 0;
            }
        }while (opcao < 1 || opcao > 12);

        return opcao;
    }

    static void incluiPais()
    {
        Pais novo;
        String codigo;
        String nome;
        String capital;
        long populacao;
        long area;

        while(true) {
            System.out.println();
            System.out.println("----------------");
            System.out.println("Inclusão de País:");
            System.out.print("   Código ISO (ou 'Fim' para encerrar): ");
            codigo = leia.next();

            if (codigo.equalsIgnoreCase("fim"))
                break;

            // verificar se país já foi inserido na lista
            if (buscaPais (codigo) != null) {
                System.out.println("ERRO: País já cadastrado.");
                continue;
            }
            System.out.print("   Nome.....: ");
            do {
                nome = leia.nextLine();
            }while (nome.isEmpty());

            System.out.print("   Capital..: ");
            do {
                capital = leia.nextLine();
            }while (capital.isEmpty());

            do
            {
                try{
                    System.out.print("   População: ");
                    populacao = leia.nextLong();
                }
                catch (Exception e) {
                    System.out.println(" Favor digitar um número inteiro.");
                    populacao = -1;
                    leia.nextLine();
                }
            }while (populacao < 0);

            System.out.print("   Área.....: ");
            area = leia.nextLong();

            novo = new Pais (codigo, nome, capital, populacao, area);
            paises.add(novo);
        }
    }

    // verificar se país já foi inserido na lista
    private static Pais buscaPais (String codigo) {
        for (Pais p: paises) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    private static void incluiVizinhos()
    {
        String codigo;

        while (true) {
            System.out.println();
            System.out.println("----------------");
            System.out.println("Inclusão de Vizinhos:");

            System.out.print("   Código ISO do País(ou 'Fim' para encerrar): ");
            codigo = leia.next();
            if (codigo.equalsIgnoreCase("fim"))
                break;

            // verificar se país já foi inserido na lista
            Pais p = buscaPais (codigo);
            if (p == null) {
                System.out.println("ERRO: País não encontrado.");
                continue;
            }

            // inclusao dos vizinhos
            while (true) {
                System.out.print("   Código ISO do Vizinho(ou 'Fim' para encerrar): ");
                codigo = leia.next();
                if (codigo.equalsIgnoreCase("fim"))
                    break;

                if (codigo.equalsIgnoreCase(p.getCodigo())){
                    System.out.println("ERRO: país não pode ser vizinho dele mesmo.");
                    continue;
                }

                // verifica se código do vizinho é válido
                Pais p1 = buscaPais(codigo);
                if(p1 == null){
                    System.out.println("ERRO: Vizinho não encontrado.");
                }
                else {
                    // adiciona vizinho
                    p.addVizinho(codigo);

                    // adicionar vizinhança dupla
                    p1.addVizinho(p.getCodigo());
                }
            }
        }
    }

    private static void listarPaises()
    {
        for (Pais p: paises) {
            p.mostraDados();
            System.out.println();
        }
    }

    private static void paisesVizinhos()
    {
        String codigo;
        int i;
        boolean bFim = false;
        Pais p, p1 = null, p2 = null;

        while (true) {
            System.out.println();
            System.out.println("----------------");
            System.out.println("Indica se dois países são vizinhos:");

            i = 0;
            while (i < 2) {
                System.out.print("   Código ISO do " + (i + 1) + "o. País(ou 'Fim' para encerrar): ");
                codigo = leia.next();
                if (codigo.equalsIgnoreCase("fim"))
                {
                    bFim = true;
                    break;
                }

                // verificar se país já foi inserido na lista
                p = buscaPais (codigo);
                if (p == null) {
                    System.out.println("ERRO: País não encontrado.");
                    continue;
                }

                if (i == 0)
                    p1 = p;
                else
                    p2 = p;
                ++i;
            }
            if (bFim)
                break;

            // verifica se paises sao vizinhos
            System.out.print (p1.getCodigo() + " e " + p2.getCodigo());
            if (p1.seVizinho (p2))
                System.out.println (" são vizinhos!");
            else
                System.out.println (" não são vizinhos!");

        }
    }

    private static void densidadePopulacional()
    {
        String codigo;

        while (true) {
            System.out.println();
            System.out.println("----------------");
            System.out.println("Densidade Populacional:");

            System.out.print("   Código ISO do País(ou 'Fim' para encerrar): ");
            codigo = leia.next();
            if (codigo.equalsIgnoreCase("fim"))
                break;

            // verificar se país já foi inserido na lista
            Pais p = buscaPais (codigo);
            if (p == null) {
                System.out.println("ERRO: País não encontrado.");
                continue;
            }

            // mostra a densidade populacional do país
            System.out.println ("Densidade Populacional: " +
                    NumberFormat.getNumberInstance().format(p.densidade()) +
                    " habitantes / Km2");

        }
    }

    private static void vizinhosEmComum()
    {
        String codigo;
        int i, j;
        boolean bFim = false, bAchou;
        Pais p;
        ArrayList<String> v1 = null, v2 = null;

        while (true) {
            System.out.println();
            System.out.println("----------------");
            System.out.println("Lista vizinhos em comum entre dois países:");

            i = 0;
            while (i < 2) {
                System.out.print("   Código ISO do " + (i + 1) + "o. País(ou 'Fim' para encerrar): ");
                codigo = leia.next();
                if (codigo.equalsIgnoreCase("fim"))
                {
                    bFim = true;
                    break;
                }

                // verificar se país já foi inserido na lista
                p = buscaPais (codigo);
                if (p == null) {
                    System.out.println("ERRO: País não encontrado.");
                    continue;
                }

                if (i == 0)
                    v1 = p.getVizinhos();
                else
                    v2 = p.getVizinhos();
                ++i;
            }
            if (bFim)
                break;

            // lista vizinhos em comum
            bAchou = false;
            System.out.print ("Vizinhos em comum:");
            for (i = 0; i < v1.size(); ++i){
                if (v2.indexOf(v1.get(i)) >= 0)
                {
                    bAchou = true;
                    System.out.print (" " + v1.get(i));
                }
            }
            if (bAchou)
                System.out.println ("");
            else
                System.out.println (" nenhum.");

        }
    }

    private static void lerDados()
    {
        File arquivo = new File(NOME_ARQ_PAISES);
        Pais p;
        String linha;
        String[] vet;
        int i;

        if (!arquivo.exists()) {
            System.out.println ("Arquivo de dados não existe!");
            return;
        }

        // limpar países
        paises.clear();

        try
        {
            //faz a leitura do arquivo
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            // leitura dos dados dos paises (enquanto houver linhas)
            while (br.ready())
            {
                //lê a proxima linha
                linha = br.readLine();
                vet = linha.split(";");

                p = new Pais (vet[0], vet[1], vet[2],
                        Long.parseLong(vet[3]),
                        Long.parseLong(vet[4]));
                paises.add (p);

                // adiciona vizinhos
                for (i = 5; i < vet.length; ++i) {
                    p.addVizinho(vet[i]);
                }
            }

            br.close();
            fr.close();

            System.out.println ("Dados lidos com sucesso!");
            System.out.println ("Total de países lidos: " + paises.size());

        } catch (IOException ex) {
            System.out.println ("ERRO: leitura do arquivo de dados!");
        }
    }

    private static void lerDadosContinentes(){
        File arquivo = new File(NOME_ARQ_CONTINENTES);
        Continente c;
        Pais p;
        String linha;
        String[] vet;
        int i;

        if(!arquivo.exists()){
            System.out.println("Arquivo de dados não existe!");
            return;
        }

        continentes.clear();

        try{
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()){
                linha = br.readLine();
                vet = linha.split(";");

                if(vet[0] == "c"){
                    c = new Continente(vet[0]);
                    continentes.add(c);
                }else if (vet[0] == "p"){
                    p = new Pais (vet[0], vet[1], vet[2],
                            Long.parseLong(vet[3]),
                            Long.parseLong(vet[4]));
                    paises.add (p);
                }
            }
            br.close();
            fr.close();

            System.out.println("Todos os conteúdos foram lidos com sucesso");

        } catch (IOException ex) {
            System.out.println ("ERRO: leitura do arquivo de dados!");
        }
    }

    private static void gravarDados()
    {
        File arquivo = new File(NOME_ARQ_PAISES);
        ArrayList<String> vizinhos;

        try
        {
            // cria o arquivo (caso ele não exista)
            arquivo.createNewFile();

            // variáveis para manipulação do arquivo
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);

            // grava dados das contas
            String linha;   // dados a serem gravados no arquivo
            for (Pais p : paises)
            {
                linha = p.getCodigo() + ";" + p.getNome() + ";" +
                        p.getCapital() + ";" +
                        Long.toString(p.getPopulacao()) + ";" +
                        Long.toString(p.getArea()) + ";";

                // adiciona vizinhos
                vizinhos = p.getVizinhos();
                for (String cod : p.getVizinhos()) {
                    linha += ";" + cod;
                }

                bw.write(linha);
                bw.newLine();
            }

            bw.close();
            fw.close();

            System.out.println ("Dados gravados com sucesso!");
            System.out.println ("Total de países gravados: " + paises.size());

        } catch (IOException ex) {
            System.out.println("ERRO: gravação do arquivo de dados!");
            System.out.println(ex.getLocalizedMessage());
        }
    }

    private static void gravarDadosContinentes(){
        File arquivo = new File(NOME_ARQ_CONTINENTES);

        try{
            arquivo.createNewFile();

            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);

            String linha;
            for (Continente c: continentes
                 ) {
                linha = "c" + ";" + c.getNome() + ";";
                for (Pais p: c.getPaisesDoContinente()
                     ) {
                    linha = linha + "p" + ";" +
                            p.getCodigo() + ";" + p.getNome() + ";" +
                            p.getCapital() + ";" +
                            Long.toString(p.getPopulacao()) + ";" +
                            Long.toString(p.getArea()) + ";";
                }
                bw.write(linha);
                bw.newLine();
            }
            bw.close();
            fw.close();

            System.out.println ("Dados gravados com sucesso!");
            System.out.println ("Total de continentes gravados: " + continentes.size());
        } catch (IOException ex){
            System.out.println("ERRO: gravação do arquivo de dados!");
            System.out.println(ex.getLocalizedMessage());
        }
    }
}

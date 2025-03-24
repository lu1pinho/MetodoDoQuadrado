import java.io.*;
import java.util.*;

public class MeioDoQuadrado {

    public static void gerarChaves(String nomeArquivo, int quantidade) {
        // Cria a pasta "keys" se não existir
        File pastaKeys = new File("keys");
        if (!pastaKeys.exists()) {
            pastaKeys.mkdir();
        }

        // Caminho completo do arquivo de chaves
        String caminhoChaves = pastaKeys.getPath() + File.separator + nomeArquivo;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoChaves))) {
            Random random = new Random();
            for (int i = 0; i < quantidade; i++) {
                int key = random.nextInt(1000000); // Gera números entre 0 e 999.999
                writer.write(key + "\n");
            }
            System.out.println(quantidade + " chaves geradas e salvas em " + caminhoChaves);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo da multiplicação
    public static int meioDoQuadrado(int key) {
        long quadrado = (long) key * key;
        String quadradoStr = Long.toString(quadrado);
        int tamanho = quadradoStr.length();
        int inicio = (tamanho / 2) - 2; // Ajuste para pegar 4 dígitos do meio
        if (inicio < 0) inicio = 0;
        String meioStr = quadradoStr.substring(inicio, inicio + Math.min(4, tamanho));
        return Integer.parseInt(meioStr);
    }

    public static void gerarHashes(String arquivoEntrada, String arquivoSaida) {
        File pastaResult = new File("result");
        if (!pastaResult.exists()) {
            pastaResult.mkdir();
        }

        String caminhoSaida = pastaResult.getPath() + File.separator + arquivoSaida;

        Map<Integer, Integer> contadorColisoes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada));
             BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoSaida))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                int key = Integer.parseInt(linha.trim());
                int hash = meioDoQuadrado(key);

                contadorColisoes.put(hash, contadorColisoes.getOrDefault(hash, 0) + 1);
            }

            int totalColisoes = 0;
            for (int count : contadorColisoes.values()) {
                if (count > 1) {
                    totalColisoes += count - 1;
                }
            }
            writer.write("Colisões: " + totalColisoes + "\n");

            // Escreve as hashes
            try (BufferedReader readerHash = new BufferedReader(new FileReader(arquivoEntrada))) {
                while ((linha = readerHash.readLine()) != null) {
                    int key = Integer.parseInt(linha.trim());
                    int hash = meioDoQuadrado(key);
                    writer.write(hash + "\n");
                }
            }

            System.out.println("Hashes geradas e salvas em " + caminhoSaida + " com " + totalColisoes + " colisões.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        gerarChaves("chaves_1000.txt", 1000);
//        gerarChaves("chaves_10000.txt", 10000);
//        gerarChaves("chaves_1000000.txt", 1000000);

//        gerarHashes("keys/chaves_1000.txt", "hashes_1000.txt");
//        gerarHashes("keys/chaves_10000.txt", "hashes_10000.txt");
//        gerarHashes("keys/chaves_1000000.txt", "hashes_1000000.txt");
    }
}
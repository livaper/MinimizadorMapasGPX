package main;

import dominio.Trajetoria;
import escritaXML.EscritorGPX;
import leituraXML.LeitorGPX;
import redutorPontos.RedutorDePontos;

/**
 * Classe main que executa a minimização de mapas. A minimização consiste em
 * reduzir a quantidade de pontos presentes em uma trajetória realizada em um
 * mapa. Inicialmente o usuário entrará via teclado a opção que deseja utilizar
 * na minimização. A redução poderá ser feita de duas formas. A primeira forma
 * consistirá no recebimento de um valor em metros via teclado. Desta forma a
 * minimização será feita descartando os pontos que estiverem em uma distância
 * do arco formado pelos pontos adjacentes menor ou igual a uma distância limite
 * espeficicada pelo usuário. A segunda forma consistirá no recebimento de um
 * valor em porcentagem via teclado (valor entre 0 e 100). Desta forma a
 * minimização será feita mantendo na trajetória apenas o percentual de pontos
 * informado, ou seja, descartando "100 menos o valor entrado no teclado %" de
 * pontos que estiverem mais próximos do arco formada pelos pontos adjacentes.
 * 
 * A trajetória será lida a partir de um arquivo de formato GPX e a trajetória
 * reduzida será escrita em um novo arquivo de mesmo formato. Tanto o arquivo de
 * entrada quanto o de saída podem ser abertos no aplicativo Google Earth, para
 * a visualização das respectivas trajetórias no mapa Mundi.
 * 
 * 
 * @author liviapereira
 *
 */
public class MinimizadorMapasMain {

	public static final int OPCAO_DISTANCIA_LIMITE = 1;
	public static final int OPCAO_PORCENTAGEM = 0;

	public static void main(String[] args) {

		int opcaoEscolhida;
		Double distancia = null;
		Double porcentagem = null;
		String nomeArquivoEntrada = args[1];
		String nomeArquivoSaida = args[2];

		if (args[0].endsWith("%")) {
			opcaoEscolhida = OPCAO_PORCENTAGEM;
			porcentagem = Double.valueOf(args[0].substring(0, args[0].length() - 1));
		} else {
			opcaoEscolhida = OPCAO_DISTANCIA_LIMITE;
			distancia = Double.valueOf(args[0]);

		}

		// LEITURA DO ARQUIVO GPX DE ENTRADA E PREENCHIMENTO DO OBJETO DA
		// TRAJETORIA
		LeitorGPX leitor = new LeitorGPX();
		Trajetoria trajetoriaOriginal = leitor.lerXML(nomeArquivoEntrada);

		// ADICAO DAS DISTANCIAS DO PONTO A RETA FORMADA PELOS PONTOS ADJACENTES
		trajetoriaOriginal.adicionaDistanciaGeograficaDosPontos();

		RedutorDePontos redutor = new RedutorDePontos();
		Trajetoria trajetoriaReduzida = null;

		// TRATA A MINIMIZACAO COM A DISTANCIA LIMITE
		if (opcaoEscolhida == OPCAO_DISTANCIA_LIMITE) {
			trajetoriaReduzida = redutor.reduzTrajetoriaPorDistancia(trajetoriaOriginal, distancia);

		}
		// TRATA A MINIMIZACAO COM A PORCENTAGEM
		else if (opcaoEscolhida == OPCAO_PORCENTAGEM) {
			trajetoriaReduzida = redutor.reduzTrajetoriaPorPorcentagem(trajetoriaOriginal, porcentagem);
		}

		// ESCREVE ARQUIVO SAIDA GPX

		trajetoriaReduzida.setNome(trajetoriaOriginal.getNome());
		EscritorGPX escritor = new EscritorGPX();
		escritor.escreverGPX(trajetoriaReduzida, nomeArquivoSaida);

		System.out.println("Tamanho trajetoria entrada = " + trajetoriaOriginal.getPontosMarcados().size());
		System.out.println("Tamanho trajetoria saida = " + trajetoriaReduzida.getPontosMarcados().size());
	}

}

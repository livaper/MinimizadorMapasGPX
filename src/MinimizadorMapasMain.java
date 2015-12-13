/**
 * Classe main que executa a minimização de mapas. A minimização consiste em
 * reduzir a quantidade de pontos presentes em uma trajetória realizada em um
 * mapa. Inicialmente o usuário entrará via teclado a opção que deseja utilizar
 * na minimização. A redução poderá ser feita de duas formas. A primeira forma
 * consistirá no recebimento de um valor em metros via teclado. Desta forma a
 * minimização será feita descartando os pontos que estiverem em uma distância
 * da reta formada pelos pontos adjacentes menor ou igual àquela espeficicada
 * pelo usuário. A segunda forma consistirá no recebimento de um valor em
 * porcentagem via teclado (valor entre 0 e 100). Desta forma a minimização será
 * feita descartando o percentual de pontos da trajetória que estiverem mais
 * próximos da reta formada pelos pontos adjacentes.
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
		Trajetoria trajetoria = leitor.lerXML(nomeArquivoEntrada);
		
		// TODO

		// ADICAO DAS DISTANCIAS DO PONTO A RETA FORMADA PELOS PONTOS ADJACENTES
		trajetoria.adicionaDistanciaARetaAdjacenteDosPontos();

		RedutorDePontos redutor = new RedutorDePontos();

		// TRATA A MINIMIZACAO COM A DISTANCIA LIMITE
		if (opcaoEscolhida == OPCAO_DISTANCIA_LIMITE) {
			Trajetoria trajetoriaReduzida = redutor.reduzTrajetoriaPorDistancia(trajetoria, distancia);

		}
		// TRATA A MINIMIZACAO COM A PORCENTAGEM
		else if (opcaoEscolhida == OPCAO_PORCENTAGEM) {
			Trajetoria trajetoriaReduzida = redutor.reduzTrajetoriaPorPorcentagem(trajetoria, porcentagem);
		}

		// ESCREVE ARQUIVO SAIDA GPX
		EscritorGPX escritor = new EscritorGPX();
		

	}

}

import java.util.Scanner;

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

		System.out.println("Olá! Bem vindo ao minimizador de pontos em uma trajetória. Digite o opção desejada:");
		System.out.println("Digite 1 para minimizar através de uma distância limite: ");
		System.out.println("Digite 2 para minimizar através de uma porcentagem: ");

		Scanner scanner = new Scanner(System.in);
		int opcaoEscolhida = scanner.nextInt();

		// LEITURA DO ARQUIVO GPX DE ENTRADA E PREENCHIMENTO DO OBJETO DA
		// TRAJETORIA

		// TRATA A MINIMIZACAO COM A DISTANCIA LIMITE
		if (opcaoEscolhida == OPCAO_DISTANCIA_LIMITE) {
			System.out.println("Digite a distância em metros:");
			
		}
		// TRATA A MINIMIZACAO COM A PORCENTAGEM
		else if (opcaoEscolhida == OPCAO_PORCENTAGEM) {
			System.out.println("Digite a porcentagem entre 0 e 100:");
			
		}

	}

}

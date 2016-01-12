package teste;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dominio.PontoMarcado;
import dominio.Trajetoria;
import redutorPontos.RedutorDePontos;

public class RedutorDePontosTest {

	private Trajetoria trajetoriaInicial;

	@Before
	public void inicializaTrajetoriaInicial() {
		Date data1 = null;
		Date data2 = null;
		Date data3 = null;
		Date data4 = null;
		Date data5 = null;
		Date data6 = null;
		Date data7 = null;
		Date data8 = null;
		Date data9 = null;
		Date data10 = null;

		try {
			data1 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:26Z");
			data2 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:30Z");
			data3 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:40Z");
			data4 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:50Z");
			data5 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:34:00Z");
			data6 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:34:10Z");
			data7 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:34:20Z");
			data8 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:34:30Z");
			data9 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:34:40Z");
			data10 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:34:50Z");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		PontoMarcado ponto1 = new PontoMarcado(1.0, 20.000000, 20.000000, data1);
		PontoMarcado ponto2 = new PontoMarcado(2.0, 20.000000, 20.000000, data2);
		PontoMarcado ponto3 = new PontoMarcado(3.0, 20.000000, 20.000000, data3);
		PontoMarcado ponto4 = new PontoMarcado(4.0, 20.000000, 20.000000, data4);
		PontoMarcado ponto5 = new PontoMarcado(5.0, 20.000000, 20.000000, data5);
		PontoMarcado ponto6 = new PontoMarcado(6.0, 20.000000, 20.000000, data6);
		PontoMarcado ponto7 = new PontoMarcado(7.0, 20.000000, 20.000000, data7);
		PontoMarcado ponto8 = new PontoMarcado(8.0, 20.000000, 20.000000, data8);
		PontoMarcado ponto9 = new PontoMarcado(9.0, 20.000000, 20.000000, data9);
		PontoMarcado ponto10 = new PontoMarcado(10.0, 20.000000, 20.000000, data10);

		ponto2.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(2.0);
		ponto3.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(3.0);
		ponto4.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(4.0);
		ponto5.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(5.0);
		ponto6.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(6.0);
		ponto7.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(7.0);
		ponto8.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(8.0);
		ponto9.setDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes(9.0);

		List<PontoMarcado> pontos = new ArrayList<PontoMarcado>();
		pontos.add(ponto1);
		pontos.add(ponto2);
		pontos.add(ponto3);
		pontos.add(ponto4);
		pontos.add(ponto5);
		pontos.add(ponto6);
		pontos.add(ponto7);
		pontos.add(ponto8);
		pontos.add(ponto9);
		pontos.add(ponto10);

		trajetoriaInicial = new Trajetoria(pontos);
		trajetoriaInicial.setNome("TrajetoriaTeste");
	}

	/**
	 * Verifica quantidade de Pontos reduzidos pelo parametro porcentagem
	 * 
	 */
	@Test
	public void testTrajetoriaPorPorcentagem() {

		RedutorDePontos redutor = new RedutorDePontos();
		int numeroPontosTrajetoriaReduzida = (int) (trajetoriaInicial.getPontosMarcados().size() * 0.7);
		Trajetoria trajetoriaReduzida = redutor.reduzTrajetoriaPorPorcentagem(trajetoriaInicial, 70.0);
		assertEquals(numeroPontosTrajetoriaReduzida, trajetoriaReduzida.getPontosMarcados().size());
	}
	
	/**
	 * Verifica quantidade de Pontos reduzidos pelo parametro porcentagem
	 * 
	 */
	@Test
	public void testTrajetoriaPorDistancia() {

		RedutorDePontos redutor = new RedutorDePontos();
		int numeroPontosTrajetoriaReduzida = 8;
		Trajetoria trajetoriaReduzida = redutor.reduzTrajetoriaPorDistancia(trajetoriaInicial, 5.0);
		System.out.println("pontos");
		for (PontoMarcado ponto : trajetoriaReduzida.getPontosMarcados()) {
			System.out.println(ponto.getDistanciaGeograficaAoArcoFormadoPelosPontosAdjacentes());
		}

		assertEquals(numeroPontosTrajetoriaReduzida, trajetoriaReduzida.getPontosMarcados().size());

	}


}

package teste;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dominio.PontoMarcado;

import redutorPontos.CalculadoraDistanciaGeografica;

public class CalculadoraDistanciaGeograficaTest {
	PontoMarcado pontoAnterior;
	PontoMarcado pontoSeguinte;
	PontoMarcado pontoAtual;

	@Before
	public void inicializaTrajetoriaInicial() {
		Date data1 = null;
		Date data2 = null;
		Date data3 = null;

		try {
			data1 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:26Z");
			data2 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:30Z");
			data3 = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'").parse("2007-01-18T03:33:40Z");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		pontoAnterior = new PontoMarcado(1.0, -77.037852, 38.898556, data1);
		pontoAtual = new PontoMarcado(3.0, -77.038934, 38.898056, data3);
		pontoSeguinte = new PontoMarcado(2.0, -77.043934, 38.897147, data2);
		

	}

	//FONTE: http://andrew.hedges.name/experiments/haversine/
	@Test
	public void testCalculaDistanciaFormulaHaversine() {
		String distanciaEsperada = "0,549";
		DecimalFormat df = new DecimalFormat("0.000");
		String distancia = df.format(CalculadoraDistanciaGeografica.formulaDeHaversine(pontoAnterior.getLatitude(),
				pontoAnterior.getLongitude(), pontoSeguinte.getLatitude(), pontoSeguinte.getLongitude()));
		assertEquals(distanciaEsperada, distancia);
	}

	@Test
	public void testCalculaDistanciaGeografica() {
		String distanciaEsperada = "0,027";
		DecimalFormat df = new DecimalFormat("0.000");
		CalculadoraDistanciaGeografica calculadora = new CalculadoraDistanciaGeografica();
		String distanciaGeograficaCalculada = df.format(calculadora.calculaDistanciaDeUmPontoAoArcoAdjacente(pontoAtual, pontoAnterior, pontoSeguinte));
		assertEquals(distanciaEsperada, distanciaGeograficaCalculada);
	}
}
package dominio;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe de domínio do ponto marcado contido na Trajetória
 * 
 * @author liviapereira
 *
 */

public class PontoMarcado {
	private Double identificadorElemento;
	private Double longitude;
	private Double latitude;
	private Double distanciaARetaAdjacente;
	private Date data;

	/**
	 * 
	 * @param identificador Double
	 * @param longitude Double
	 * @param latitude Double
	 * @param horario Date
	 */
	public PontoMarcado(Double identificador, Double longitude, Double latitude, Date horario) {
		super();
		this.identificadorElemento = identificador;
		this.longitude = longitude;
		this.latitude = latitude;
		this.data = horario;
		this.distanciaARetaAdjacente = null;
	}

	public Double getIdentificador() {
		return identificadorElemento;
	}

	public void setIdentificador(Double identificador) {
		this.identificadorElemento = identificador;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date horario) {
		this.data = horario;
	}

	public Double getDistanciaARetaAdjacente() {
		return distanciaARetaAdjacente;
	}

	public void setDistanciaARetaAdjacente(Double distanciaARetaAdjacente) {
		this.distanciaARetaAdjacente = distanciaARetaAdjacente;
	}

	public static Comparator<PontoMarcado> getComparatorDataCrescente() {
		return new Comparator<PontoMarcado>() {
			@Override
			public int compare(PontoMarcado ponto1, PontoMarcado ponto2) {
				return ponto1.getData().compareTo(ponto2.getData());
			}
		};
	}

	public static Comparator<PontoMarcado> getComparatorDistanciaARetaCrescente() {
		return new Comparator<PontoMarcado>() {
			@Override
			public int compare(PontoMarcado ponto1, PontoMarcado ponto2) {
				return ponto1.getDistanciaARetaAdjacente().compareTo(ponto2.getDistanciaARetaAdjacente());
			}
		};
	}
}

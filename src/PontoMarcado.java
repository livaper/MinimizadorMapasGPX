import java.util.Comparator;
import java.util.Date;
import java.math.BigDecimal;

/**
 * Classe de domínio do ponto marcado contido na Trajetória
 * 
 * @author liviapereira
 *
 */

public class PontoMarcado {
	private BigDecimal identificadorElemento;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private Double distanciaARetaAdjacente;
	private Date horario;

	public PontoMarcado(BigDecimal identificador, BigDecimal longitude, BigDecimal latitude, Date horario) {
		super();
		this.identificadorElemento = identificador;
		this.longitude = longitude;
		this.latitude = latitude;
		this.horario = horario;
		this.distanciaARetaAdjacente = null;
	}

	public BigDecimal getIdentificador() {
		return identificadorElemento;
	}

	public void setIdentificador(BigDecimal identificador) {
		this.identificadorElemento = identificador;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
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
				return ponto1.getHorario().compareTo(ponto2.getHorario());
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

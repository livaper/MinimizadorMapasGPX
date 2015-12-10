import java.util.Date;

/**
 * Classe de domínio do ponto marcado contido na Trajetória
 * 
 * @author liviapereira
 *
 */

public class PontoMarcado implements Comparable<PontoMarcado>{
	private Double identificador;
	private Double longitude;
	private Double latitude;
	private Double distanciaARetaAdjacente;
	private Date horario;

	public PontoMarcado(Double identificador, Double longitude, Double latitude, Date horario) {
		super();
		this.identificador = identificador;
		this.longitude = longitude;
		this.latitude = latitude;
		this.horario = horario;
		this.distanciaARetaAdjacente = null;
	}

	public Double getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Double identificador) {
		this.identificador = identificador;
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

	@Override
	public int compareTo(PontoMarcado outroPonto) {
		if (this.distanciaARetaAdjacente < outroPonto.distanciaARetaAdjacente) {
            return -1;
        }
        if (this.distanciaARetaAdjacente > outroPonto.distanciaARetaAdjacente) {
            return 1;
        }
        return 0;
    }

}

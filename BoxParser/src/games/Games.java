package games;

import teams.Teams;

public class Games {
	
	private String mandante;
	private String visitante;
	private String vencedor;
	private String perdedor;
	private int pontosVencedor;
	private int pontosPerdedor;
	private Teams timeMandante;
	private Teams timeVisitante;
	private String quartos;	

	public Games(String mandante, String visitante, String vencedor,
			String perdedor, int pontosVencedor, int pontosPerdedor, String quartos) {
		super();
		this.mandante = mandante;
		this.visitante = visitante;
		this.vencedor = vencedor;
		this.perdedor = perdedor;
		this.pontosVencedor = pontosVencedor;
		this.pontosPerdedor = pontosPerdedor;
		this.timeMandante = new Teams();
		this.timeVisitante = new Teams();
		this.quartos = quartos;
	}
	
	public String getQuartos() {
		return quartos;
	}
	
	public String getMandante() {
		return mandante;
	}
	public String getVisitante() {
		return visitante;
	}
	public String getVencedor() {
		return vencedor;
	}
	public String getPerdedor() {
		return perdedor;
	}
	public int getPontosVencedor() {
		return pontosVencedor;
	}
	public int getPontosPerdedor() {
		return pontosPerdedor;
	}

	public Teams getTimeMandante() {
		return timeMandante;
	}

	public Teams getTimeVisitante() {
		return timeVisitante;
	}		
	
}
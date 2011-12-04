package players;

public class Players {
	
	private String name;
	private int minutes;
	private int fgmk;
	private int fgat;
	private int threemk;
	private int threeat;
	private int freemk;
	private int freeat;
	private int offreb;
	private int defreb;
	private int totreb;
	private int ass;
	private int foults;
	private int steals;
	private int turnovers;
	private int blocks;
	private int pa;
	private int points;
	private Float mep;
	
	
	
	public Players(String name, int minutes, int fgmk, int fgat, int threemk,
			int threeat, int freemk, int freeat, int offreb, int defreb, int totreb, int ass, int foults,
			int steals, int turnovers, int blocks, int pa, int points, Float mep) {
		super();
		this.name = name;
		this.minutes = minutes;
		this.fgmk = fgmk;
		this.fgat = fgat;
		this.threemk = threemk;
		this.threeat = threeat;
		this.freemk = freemk;
		this.freeat = freeat;
		this.offreb = offreb;
		this.defreb = defreb;
		this.totreb = totreb;
		this.ass = ass;
		this.foults = foults;
		this.steals = steals;
		this.turnovers = turnovers;
		this.blocks = blocks;
		this.pa = pa;
		this.points = points;
		this.mep = mep;
	}
	
	public int getFreemk() {
		return freemk;
	}

	public int getFreeat() {
		return freeat;
	}

	public int getFoults() {
		return foults;
	}

	public int getPoints() {
		return points;
	}

	public String getName() {
		return name;
	}
	public int getMinutes() {
		return minutes;
	}
	public int getFgmk() {
		return fgmk;
	}
	public int getFgat() {
		return fgat;
	}
	public int getThreemk() {
		return threemk;
	}
	public int getThreeat() {
		return threeat;
	}
	public int getOffreb() {
		return offreb;
	}
	public int getDefreb() {
		return defreb;
	}
	public int getTotreb() {
		return totreb;
	}
	public int getAss() {
		return ass;
	}
	public int getSteals() {
		return steals;
	}
	public int getTurnovers() {
		return turnovers;
	}
	public int getBlocks() {
		return blocks;
	}
	public int getPa() {
		return pa;
	}
	public Float getMep() {
		return mep;
	}

}

package teams;

import java.util.ArrayList;

import players.Players;

public class Teams {
	
	private ArrayList<Players> players;

	public Teams() {
		super();
		this.players = new ArrayList<Players>();
	}

	public ArrayList<Players> getPlayers() {
		return players;
	}	

}

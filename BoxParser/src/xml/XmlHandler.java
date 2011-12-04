package xml;

import games.Games;

import java.io.File;
import java.io.FileWriter;

import players.Players;

public class XmlHandler {

	private File file;

	public XmlHandler(File file) {
		super();
		this.file = file;
		
	}
	
	public boolean montaXml(Games jogo){
		try{
			FileWriter writer = new FileWriter(this.file);
			writer.write("<?xml version=\"1.0\"?>\n<game>\n");
			writer.write("<info>\n");
			writer.write("<mandante>"+jogo.getMandante()+"</mandante>\n");
			writer.write("<visitante>"+jogo.getVisitante()+"</visitante>\n");
			writer.write("<vencedor>\n<time>"+jogo.getVencedor()+"</time>\n<pontos>"+jogo.getPontosVencedor()+"</pontos>\n</vencedor>\n");
			writer.write("<perdedor>\n<time>"+jogo.getPerdedor()+"</time>\n<pontos>"+jogo.getPontosPerdedor()+"</pontos>\n</perdedor>\n");
			writer.write("<quartos>\n"+jogo.getQuartos()+"</quartos>\n</info>\n");
			writer.write("<time_mandante>\n<jogadores>\n");
			for(int i=0; i<jogo.getTimeMandante().getPlayers().size(); i++){
				writer.write("<jogador>\n");
				Players player = jogo.getTimeMandante().getPlayers().get(i);
				String entry = "<nome>"+player.getName()+"</nome>\n<minutos>"+player.getMinutes()+"</minutos>\n"+
				"<fgm>"+player.getFgmk()+"</fgm>\n<fga>"+player.getFgat()+"</fga>\n<threem>"+player.getThreemk()+"</threem>\n<threea>"+
				player.getThreeat()+"</threea>\n<ftm>"+player.getFreemk()+"</ftm>\n<fta>"+player.getFreeat()+"</fta>\n<oreb>"+
				player.getOffreb()+"</oreb>\n<dreb>"+player.getDefreb()+"</dreb>\n<treb>"+player.getTotreb()+"</treb>\n<assists>"+
				player.getAss()+"</assists>\n<foult>"+player.getFoults()+"</foult>\n<steal>"+player.getSteals()+"</steal>\n<turnovers>"+
				player.getTurnovers()+"</turnovers>\n<blocks>"+player.getBlocks()+"</blocks>\n<points>"+player.getPoints()+"</points>\n<pa>"+
				player.getPa()+"</pa>\n<mep>"+player.getMep()+"</mep>";
				writer.write(entry+"\n</jogador>\n");
			}
			writer.write("</jogadores>\n</time_mandante>\n");
			writer.write("<time_visitante>\n<jogadores>\n");
			for(int i=0; i<jogo.getTimeVisitante().getPlayers().size(); i++){
				writer.write("<jogador>\n");
				Players player = jogo.getTimeVisitante().getPlayers().get(i);
				String entry = "<nome>"+player.getName()+"</nome>\n<minutos>"+player.getMinutes()+"</minutos>\n"+
				"<fgm>"+player.getFgmk()+"</fgm>\n<fga>"+player.getFgat()+"</fga>\n<threem>"+player.getThreemk()+"</threem>\n<threea>"+
				player.getThreeat()+"</threea>\n<ftm>"+player.getFreemk()+"</ftm>\n<fta>"+player.getFreeat()+"</fta>\n<oreb>"+
				player.getOffreb()+"</oreb>\n<dreb>"+player.getDefreb()+"</dreb>\n<treb>"+player.getTotreb()+"</treb>\n<assists>"+
				player.getAss()+"</assists>\n<foult>"+player.getFoults()+"</foult>\n<steal>"+player.getSteals()+"</steal>\n<turnovers>"+
				player.getTurnovers()+"</turnovers>\n<blocks>"+player.getBlocks()+"</blocks>\n<points>"+player.getPoints()+"</points>\n<pa>"+
				player.getPa()+"</pa>\n<mep>"+player.getMep()+"</mep>";
				writer.write(entry+"\n</jogador>\n");
			}
			writer.write("</jogadores>\n</time_visitante>\n");
			writer.write("</game>");
			writer.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}

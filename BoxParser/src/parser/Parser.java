package parser;

import ftp.FtpHandler;
import games.Games;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import players.Players;

import teams.Teams;
import xml.XmlHandler;
import zip.ZipHelper;

public class Parser {
	
	public static boolean parseBox(String path, String id, DateTime date){
		String auxiliar = "";
		//String dateString = Integer.toString(date.getDayOfMonth())+"-"+Integer.toString(date.getMonthOfYear())+"-"+
		Integer.toString(date.getYear());
		try{
		File inputFile = new File(path);
		String timesPath = inputFile.getName().toString();
		String outputPath = inputFile.getParent()+"\\"+id+"_"+timesPath+".xml";
		outputPath = outputPath.replace(".txt", "");;
		//System.out.println(outputPath);
		File outputFile = new File(outputPath);
		if(!inputFile.exists()){
			System.out.println("Não existe box.");
			return false;
		}
		if(!outputFile.exists()){
			outputFile.createNewFile();
		}		
		
		Scanner scanner = new Scanner(inputFile);
		while(scanner.hasNext()){
			String teste = scanner.next();
			if(teste.matches("[A-Z]{1}"))
				auxiliar = auxiliar+ "<>";
			if(teste.length()>10){
				String name = teste.substring(0, 10);
				String minutes = teste.replaceAll("[a-zA-Z]", "");
				if(StringUtils.isNotBlank(minutes))
					teste = name+"<>"+minutes;
			}
			auxiliar = auxiliar + teste + "<>";
		}
		scanner.close();
				
		String header = auxiliar.replaceAll("<>Date:.*?$", "").replaceAll("^.*?SCORE:<>", "");
		String bottom = auxiliar.replaceAll("^.*?OT.*?TOT.*?--<>---<>", "").replaceAll("<>\\s*$", "");
		String middle = auxiliar.replaceAll("^.*?MEP<>","").replaceAll("<>TEAM.*?$","");
		String mandante = middle.replaceAll("=.*?$", "").replaceFirst("<>", "");
		String visitante = middle.replaceAll("^.*?=.*?MEP<>", "").replaceAll("=.*?$", "").replaceFirst("<>", "");
		String[] auxiliar2 = header.split("<>");
		String[] auxiliar3 = bottom.split("<>");
		String[] auxiliar4 = mandante.split("<><>");
		String[] auxiliar5 = visitante.split("<><>");
		//System.out.println(header);
		//System.out.println(bottom);
		int pontosMandanteq1 = Integer.parseInt(auxiliar3[1]);
		int pontosMandanteq2 = Integer.parseInt(auxiliar3[2]);
		int pontosMandanteq3 = Integer.parseInt(auxiliar3[3]);
		int pontosMandanteq4 = Integer.parseInt(auxiliar3[4]);
		int pontosMandanteq5 = Integer.parseInt(auxiliar3[5]);
		int pontosVisitanteq1 = Integer.parseInt(auxiliar3[8]);
		int pontosVisitanteq2 = Integer.parseInt(auxiliar3[9]);
		int pontosVisitanteq3 = Integer.parseInt(auxiliar3[10]);
		int pontosVisitanteq4 = Integer.parseInt(auxiliar3[11]);
		int pontosVisitanteq5 = Integer.parseInt(auxiliar3[12]);
		
		String quartos = "<q1>\n<home>"+pontosMandanteq1+"</home>\n<visitor>"+pontosVisitanteq1+"</visitor>\n</q1>\n" +
				"<q2>\n<home>"+pontosMandanteq2+"</home>\n<visitor>"+pontosVisitanteq2+"</visitor>\n</q2>\n" +
				"<q3>\n<home>"+pontosMandanteq3+"</home>\n<visitor>"+pontosVisitanteq3+"</visitor>\n</q3>\n" +
				"<q4>\n<home>"+pontosMandanteq4+"</home>\n<visitor>"+pontosVisitanteq4+"</visitor>\n</q4>\n" +
				"<q5>\n<home>"+pontosMandanteq5+"</home>\n<visitor>"+pontosVisitanteq5+"</visitor>\n</q5>\n";
		
 		int pontosMandante = Integer.parseInt(auxiliar3[6]);
		int pontosVisitante = Integer.parseInt(auxiliar3[13]);
		
		if((pontosMandante!=(pontosMandanteq1+pontosMandanteq2+pontosMandanteq3+pontosMandanteq4+pontosMandanteq5))||
				(pontosVisitante!=(pontosVisitanteq1+pontosVisitanteq2+pontosVisitanteq3+pontosVisitanteq4+pontosVisitanteq5))){
			System.out.println("Erro quarters.");
		}
		/*System.out.println("Mandante: "+auxiliar2[2]+"\nVisitante: "+auxiliar2[0]+
				"\nPontos Mandante: "+pontosMandante+"\nPontos Visitante: "+pontosVisitante+"\nWinner: "+(pontosMandante>pontosVisitante? auxiliar2[2] : auxiliar2[0]));
		*/
		Games game = new Games(auxiliar2[2], auxiliar2[0], 
				(pontosMandante>pontosVisitante? auxiliar2[2] : auxiliar2[0]), 
				(pontosMandante<pontosVisitante? auxiliar2[2] : auxiliar2[0]), 
				(pontosMandante>pontosVisitante? pontosMandante : pontosVisitante), 
				(pontosMandante<pontosVisitante? pontosMandante : pontosVisitante), quartos);
		
		Teams timeMandante = game.getTimeMandante();
		Teams timeVisitante = game.getTimeVisitante();
		ArrayList<Players> jogadoresMandante = timeMandante.getPlayers();
		ArrayList<Players> jogadoresVisitante = timeVisitante.getPlayers();
		
		for(int i=0; i<auxiliar4.length; i++){
			auxiliar4[i] = auxiliar4[i].replaceAll(">0.<", ">0<");
			String[] auxiliar6 = auxiliar4[i].split("<>");
			String name = auxiliar6[0]+" "+auxiliar6[1];			
			int minutes = Integer.parseInt(cleanTag(auxiliar6[2]));
			int fgmk = Integer.parseInt(auxiliar6[3]);
			int fgat = Integer.parseInt(auxiliar6[4]);
			int threemk = Integer.parseInt(auxiliar6[5]);
			int threeat = Integer.parseInt(auxiliar6[6]);
			int freemk = Integer.parseInt(auxiliar6[7]);
			int freeat = Integer.parseInt(auxiliar6[8]);
			int offreb = Integer.parseInt(auxiliar6[9]);
			int defreb = Integer.parseInt(auxiliar6[10]);
			int totreb = offreb + defreb;
			int ass = Integer.parseInt(auxiliar6[12]);
			int foults = Integer.parseInt(auxiliar6[13]);
			int steals = Integer.parseInt(auxiliar6[14]);
			int turnovers = Integer.parseInt(auxiliar6[15]);
			int blocks = Integer.parseInt(auxiliar6[16]);
			int pa = Integer.parseInt(auxiliar6[18]);
			int points = (fgmk-threemk)*2 + threemk*3 + freemk;
			Float mep = Float.parseFloat(auxiliar6[19]);
			
			Players player = new Players(name, minutes, fgmk, fgat, threemk, threeat, freemk, freeat, offreb, defreb, totreb, ass, foults,
					steals, turnovers, blocks, pa, points, mep);
			jogadoresMandante.add(player);
			
		}
		
		for(int i=0; i<auxiliar5.length; i++){
			auxiliar5[i] = auxiliar5[i].replaceAll(">0.<", ">0<");
			String[] auxiliar7 = auxiliar5[i].split("<>");
			String name = auxiliar7[0]+" "+auxiliar7[1];			
			int minutes = Integer.parseInt(cleanTag(auxiliar7[2]));
			int fgmk = Integer.parseInt(auxiliar7[3]);
			int fgat = Integer.parseInt(auxiliar7[4]);
			int threemk = Integer.parseInt(auxiliar7[5]);
			int threeat = Integer.parseInt(auxiliar7[6]);
			int freemk = Integer.parseInt(auxiliar7[7]);
			int freeat = Integer.parseInt(auxiliar7[8]);
			int offreb = Integer.parseInt(auxiliar7[9]);
			int defreb = Integer.parseInt(auxiliar7[10]);
			int totreb = offreb + defreb;
			int ass = Integer.parseInt(auxiliar7[12]);
			int foults = Integer.parseInt(auxiliar7[13]);
			int steals = Integer.parseInt(auxiliar7[14]);
			int turnovers = Integer.parseInt(auxiliar7[15]);
			int blocks = Integer.parseInt(auxiliar7[16]);
			int pa = Integer.parseInt(auxiliar7[18]);
			int points = (fgmk-threemk)*2 + threemk*3 + freemk;
			Float mep = Float.parseFloat(auxiliar7[19]);
			
			Players player = new Players(name, minutes, fgmk, fgat, threemk, threeat, freemk, freeat, offreb, defreb, totreb, ass, foults,
					steals, turnovers, blocks, pa, points, mep);
			jogadoresVisitante.add(player);
			
		}
		
		XmlHandler xml = new XmlHandler(outputFile);
		if(xml.montaXml(game)){
			String[] args = new String[1];
        	args[0] = outputPath;
        	//FtpHandler.enviaZip(args);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static String cleanTag(String tag){
		if(tag.matches("^\\d{1,2}$")){
			return tag;
		}
		for(int i=0; i<100; i++){
			tag = tag.replaceAll("^"+i+".$",Integer.toString(i));
		}
		return tag;
	}
	
	public static boolean deleteDir(File dir) {  
        if (dir.isDirectory()) {  
            String[] children = dir.list();  
            for (int i=0; i<children.length; i++) {   
               boolean success = deleteDir(new File(dir, children[i]));  
                if (!success) {  
                    return false;  
                }  
            }  
        }  
      
        // Agora o diretório está vazio, restando apenas deletá-lo.  
        return dir.delete();  
    } 
	
	public static boolean buildParser(String arquivo, Boolean isZip) {
		if(!isZip){
			/*File singleFile = new File(arquivo);
			File dir = new File(singleFile.getParent()+"\\boxes");
			
			if(dir.exists()){
				deleteDir(dir);
			}			
			dir.mkdir();*/
			
			String caminho = arquivo;
			DateTime date = new DateTime();
			String dateMacro = Integer.toString(date.getYearOfCentury())+Integer.toString(date.getDayOfYear());
			String dateMicro = Integer.toString(date.getMillisOfDay());
			String id = dateMacro+dateMicro;						
			parseBox(caminho, id, date);
			System.out.println("Parseado: "+caminho);
			
			File temp = new File(caminho);
			temp.delete();
			return true;
		}
		else{
			ZipHelper zipper = new ZipHelper();
			File zip = new File(arquivo);
			//File zip = new File("C:\\boxes.zip");
			if(!zip.exists()){
				System.out.println("Não existe zip");
				return false;
			}
			File dir = new File(zip.getParent()+"\\boxes");
			
			if(dir.exists()){
				deleteDir(dir);
			}
			
			dir.mkdir();
			
			try {
				zipper.unzip(zip, dir);
				String[] files = dir.list();
				if(files!=null){
					for(int i=0; i<files.length; i++){
						String caminho = dir.getPath()+"\\"+files[i];
						if((!files[i].contains("%"))&&(!files[i].contains(" ts "))&&(!files[i].contains(" TS "))&&(!files[i].contains("top"))
								&&(!files[i].contains("TOP"))&&(!files[i].matches("^[tT][sS]$"))){
							DateTime date = new DateTime();
							String dateMacro = Integer.toString(date.getYearOfCentury())+Integer.toString(date.getDayOfYear());
							String dateMicro = Integer.toString(date.getMillisOfDay());
							String id = dateMacro+dateMicro;						
							parseBox(caminho, id, date);
							System.out.println("Parseado: "+caminho);
						}
						else{
							System.out.println("Erro nome: "+caminho);
						}
						
						File temp = new File(caminho);
						temp.delete();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}		
	}	
}

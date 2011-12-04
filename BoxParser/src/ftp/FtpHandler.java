package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpHandler {
	
	public static void enviaZip(String[] args){
		String nomeArquivo = null;  
        FTPClient ftp = new FTPClient();  
        try {  
            ftp.connect( "ftp.sdmanager.org" );  
              
            //verifica se conectou com sucesso!  
            if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {  
            	ftp.login("", "");
            } else {  
                //erro ao se conectar  
                ftp.disconnect();  
                System.out.println("Conexão recusada");  
                System.exit(1);  
            }  
              
            //para cada arquivo informado...  
            for( int i=0; i<args.length; i++ ) {  
                //abre um stream com o arquivo a ser enviado  
                InputStream is = new FileInputStream( args[i] );  
                //pega apenas o nome do arquivo  
                int idx = args[i].lastIndexOf(File.separator);  
                if( idx < 0 ) idx = 0;  
                else idx++;  
                nomeArquivo = args[i].substring( idx, args[i].length() );  
                  
                //ajusta o tipo do arquivo a ser enviado  
                if( args[i].endsWith(".txt") ) {  
                    ftp.setFileType( FTPClient.ASCII_FILE_TYPE );  
                } else if( args[i].endsWith(".jpg") ) {  
                    ftp.setFileType( FTPClient.BINARY_FILE_TYPE );  
                } else {  
                    ftp.setFileType( FTPClient.ASCII_FILE_TYPE );  
                }  
                System.out.println("Enviando arquivo "+nomeArquivo+"...");  
                  
                //faz o envio do arquivo
                ftp.changeWorkingDirectory("/www/xmlboxes");
                ftp.storeFile( nomeArquivo, is );  
                System.out.println("Arquivo "+nomeArquivo+" enviado com sucesso!");  
            }  
              
            ftp.disconnect();  
            System.out.println("Fim. Tchau!");  
        } catch( Exception e ) {  
            System.out.println("Ocorreu um erro: "+e);  
            System.exit(1);  
        }  
	}
}

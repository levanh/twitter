package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeConfig {

	public static void standardConfig(){
		try{
			FileWriter writer = new FileWriter("twitter4j.properties");
			PrintWriter printerC = new PrintWriter(writer);
			printerC.println("debug=true");
			printerC.println("oauth.consumerKey=9YdbphGThJQX9Ni67W3RlGCw5");
			printerC.println("oauth.consumerSecret=xeM2qEuOYfKpf68cNXpiHxciFlyrDHaTnYwsjLAcARbCrN7o1s");
			printerC.println("oauth.accessToken=3878360123-WJx9zZqe34nNHEmc4fXwrIkSZm3dHrvt5QmNzV0");
			printerC.println("oauth.accessTokenSecret=AcwQLlqhFy2XI9EFvcRgKuebtoOglVvhuZ7WFHlB4CeH3");
			System.out.println("Changed to standard configuration");
			printerC.close();
		}
		catch(IOException e){
			System.out.println("Error while changing configuration file");
		}
	}
	
	public static void proxyConfig(){
		try{
			FileWriter writer = new FileWriter("twitter4j.properties");
			PrintWriter printerC = new PrintWriter(writer);
			printerC.println("debug=true");
			printerC.println("oauth.consumerKey=9YdbphGThJQX9Ni67W3RlGCw5");
			printerC.println("oauth.consumerSecret=xeM2qEuOYfKpf68cNXpiHxciFlyrDHaTnYwsjLAcARbCrN7o1s");
			printerC.println("oauth.accessToken=3878360123-WJx9zZqe34nNHEmc4fXwrIkSZm3dHrvt5QmNzV0");
			printerC.println("oauth.accessTokenSecret=AcwQLlqhFy2XI9EFvcRgKuebtoOglVvhuZ7WFHlB4CeH3");
			printerC.println("http.proxyHost=cache-etu.univ-lille1.fr");
			printerC.println("http.proxyPort=3128");
			System.out.println("Changed to proxy configuration");
			printerC.close();
		}
		catch(IOException e){
			System.out.println("Error while changing configuration file");
		}
	}
	
	public static void main(String[] args){
		ChangeConfig.proxyConfig();
	}
	
}

/*debug=true
oauth.consumerKey=9YdbphGThJQX9Ni67W3RlGCw5
oauth.consumerSecret=xeM2qEuOYfKpf68cNXpiHxciFlyrDHaTnYwsjLAcARbCrN7o1s
oauth.accessToken=3878360123-WJx9zZqe34nNHEmc4fXwrIkSZm3dHrvt5QmNzV0
oauth.accessTokenSecret=AcwQLlqhFy2XI9EFvcRgKuebtoOglVvhuZ7WFHlB4CeH3
http.proxyHost=cache-etu.univ-lille1.fr
http.proxyPort=3128*/

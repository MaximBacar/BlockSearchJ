package ca.stronegroup.BlockSearchJ.explorer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BlockchainHTTP {
	
	private static String proxy = null;
	
	public static String blockchainRequest (String url) {
			
			HttpClient client;
			String data = "";
			
			if (proxy != null) {
			client = HttpClient.newBuilder()
					.proxy(ProxySelector.of(new InetSocketAddress(getProxyIp(),getProxyPort())))
					.build();
			}else {
				client = HttpClient.newHttpClient();
			}
		
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.header("user-agent" , "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1', value")
				.header("accept" , "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
				.header("X-Forwarded", "127.0.0.1")
				.header("X-Forwarded-By", "127.0.0.1")
				.header("X-Forwarded-For", "127.0.0.1")
				.header("X-Forwarded-For-Original", "127.0.0.1")
				.header("X-Forwarder-For", "127.0.0.1")
				.header("X-Forward-For", "127.0.0.1")
				.header("Forwarded-For", "127.0.0.1")
				.header("Forwarded-For-Ip", "127.0.0.1")
				.header("X-Custom-IP-Authorization", "127.0.0.1")
				.header("X-Originating-IP", "127.0.0.1")
				.header("X-Remote-IP", "127.0.0.1")
				.header("X-Remote-Addr", "127.0.0.1")
				.uri(URI.create(url))
				.build();
		try {
			HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
			data = reponse.body();
			
			
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	private static String getProxyIp() {
		if (proxy != null) {
			return proxy.substring(0, proxy.charAt(':'));
		}
		return null;
	}
	
	private static int getProxyPort() {
		if (proxy != null) {
			return Integer.parseInt(proxy.substring(proxy.charAt(':') + 1));
		}
		return 0;
	}

}

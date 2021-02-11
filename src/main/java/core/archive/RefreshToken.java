package core.archive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RefreshToken implements Runnable {

	private boolean isStopped = true;
	private Properties prop;
	private static final Logger log = LoggerFactory.getLogger(RefreshToken.class);

	public RefreshToken(FileInputStream fis) throws IOException {
		prop = new Properties();
		prop.load(fis);
	}

	public void refreshToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange(prop.getProperty("refresfh.token.url"),
				HttpMethod.POST, entity, String.class);

		String result = response.getBody();
		

	}

	@Override
	public void run() {
		while(!isStopped) {
			try {
			 	log.info("Token refreshed");
				System.out.println("token refreshed");
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				isStopped = true;
				e.printStackTrace();
			}
		}
	}

	public void start() {
		isStopped = false;
		ExecutorService exec = Executors.newSingleThreadExecutor(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setName("RefreshToken");
				t.setDaemon(true);
				return t;
			}
		});

		exec.execute(this);
	}

	public void stop() {
		isStopped = true;
	}

}

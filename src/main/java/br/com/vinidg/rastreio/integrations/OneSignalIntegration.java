package br.com.vinidg.rastreio.integrations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.vinidg.rastreio.domain.Content;
import br.com.vinidg.rastreio.domain.Data;
import br.com.vinidg.rastreio.domain.Headings;
import br.com.vinidg.rastreio.domain.Notification;
import br.com.vinidg.rastreio.domain.Notification.Builder;
import br.com.vinidg.rastreio.enums.PushMessage;

@Component
public class OneSignalIntegration {
	
	@Value("${onesignal.url}")
	private String url;
	
	@Value("${onesignal.app-id}")
	private String appId;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(OneSignalIntegration.class);
	
	public void createNotification(PushMessage message, List<String> playerIds) {
		Gson gson = new Gson();
		Data data = new Data();
		data.setPage("PedidosPage");
		Content content = new Content();
		content.setEn(message.getMensagem());
		Headings heading = new Headings();
		heading.setEn(message.getTitulo());
		
		Notification builderNotification = new Builder()
				.app_id(appId)
				.include_player_ids(playerIds)
				.data(data)
				.contents(content)
				.headings(heading)
				.small_icon("ic_stat_onesignal_default")
				.android_accent_color("003366")
				.build();
		log.info("================== Request - ENVIO DO PUSH " + gson.toJson(builderNotification) + " ===========================");
		String send = send(builderNotification);
		
		log.info("================== Response - ENVIO DO PUSH " + send + " ===========================");
	}
	
	private String send(Notification notification) {
		return restTemplate.postForObject(url+"/notifications", notification, String.class);
	}

}

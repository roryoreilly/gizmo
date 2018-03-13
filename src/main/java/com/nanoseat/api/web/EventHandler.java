
package com.nanoseat.api.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nanoseat.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
@RepositoryEventHandler(User.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	private Gson gson;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
		gson = new GsonBuilder().create();
	}

	public void updateBalance() {
		websocket.convertAndSend(
				WebSocketConfiguration.MESSAGE_PREFIX + "/updateBalance", "Gizmo");
	}

	public void timerUpdate(String timer) {
		websocket.convertAndSend(
				WebSocketConfiguration.MESSAGE_PREFIX + "/timer", gson.toJson(timer));
	}

	@HandleAfterCreate
	public void newUser(User user) {
		this.websocket.convertAndSend(
				WebSocketConfiguration.MESSAGE_PREFIX + "/newUser", getPath(user));
	}

	@HandleAfterDelete
	public void deleteUser(User user) {
		this.websocket.convertAndSend(
				WebSocketConfiguration.MESSAGE_PREFIX + "/deleteUser", getPath(user));
	}

	@HandleAfterSave
	public void updateUser(User user) {
		this.websocket.convertAndSend(
				WebSocketConfiguration.MESSAGE_PREFIX + "/updateUser", getPath(user));
	}

	/**
	 * Take an {@link User} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param user
	 */
	private String getPath(User user) {
		String path = "http://localhost:8080" + this.entityLinks.linkForSingleResource(user.getClass(),
				user.getId()).toUri().getPath();
		System.out.println(path);
		return path;
	}
}

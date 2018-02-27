/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nanoseat.api.web;

import com.nanoseat.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(User.class)
public class EventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	public void updateBalance() {
		websocket.convertAndSend(
				WebSocketConfiguration.MESSAGE_PREFIX + "/updateBalance", "Gizmo");
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
		return this.entityLinks.linkForSingleResource(user.getClass(),
				user.getId()).toUri().getPath();
	}

}

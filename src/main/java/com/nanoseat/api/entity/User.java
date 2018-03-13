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
package com.nanoseat.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String balance;
	private String account;
	private String mostRecentSendHash;
	private boolean isMoneySent;
	private boolean inHotSeat;

	private @Version @JsonIgnore Long version;

	private User() {}

	public User(String firstName, String balance, String account, boolean isMoneySent, boolean inHotSeat) {
		this.firstName = firstName;
		this.balance = balance;
		this.account = account;
		this.isMoneySent = isMoneySent;
		this.inHotSeat = inHotSeat;
	}
}
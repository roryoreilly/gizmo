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
package com.nanoseat.api.init;

import com.nanoseat.api.entity.User;
import com.nanoseat.api.repositories.UserRepo;
import com.nanoseat.api.rpc.NanoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private NanoClient client;

	@Override
	public void run(String... strings) throws Exception {
		this.userRepo.save(new User("User 1",
				"0",
				"xrb_34tzxgtrxpkwxxp7bq7thdnk4mgqopy3bhnu8kopnfby7ofh4mowfxucj19o",
				true,
				false));
//		this.userRepo.save(new User("Seconds Guy", "0", "xrb_3rpnsxput4t6hffjcjp7rr9d4i9nhfsqutnfhbtm7t5t4xww497gzh9bgetm"));
	}
}
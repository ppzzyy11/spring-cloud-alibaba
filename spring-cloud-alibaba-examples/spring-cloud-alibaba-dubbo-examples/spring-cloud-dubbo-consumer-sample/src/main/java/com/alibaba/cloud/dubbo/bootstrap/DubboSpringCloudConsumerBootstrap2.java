/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.dubbo.bootstrap;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.bootstrap.EchoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Dubbo Spring Cloud Consumer Bootstrap.
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 */
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableFeignClients
@EnableScheduling
@EnableCaching
public class DubboSpringCloudConsumerBootstrap2 {


	@Value("${provider.application.name}")
	private String providerApplicationName;

	@DubboReference
	private EchoService echoService;

	public static void main(String[] args) {
		new SpringApplicationBuilder(DubboSpringCloudConsumerBootstrap2.class)
//				.properties("spring.profiles.active=nacos").run(args);
				.properties("spring.profiles.active=zookeeper").run(args);
	}

	@Bean
	public ApplicationRunner echoServiceRunner() {
		return arguments -> {

//			DubboBootstrap bootstrap = DubboBootstrap.getInstance();
//			bootstrap.stop();
//			bootstrap
//					.application("zookeeper-dubbo-consumer", app -> app.metadata(DEFAULT_METADATA_STORAGE_TYPE))
//					.registry("zookeeper", builder -> builder.address("zookeeper://127.0.0.1:2181")
////                        .parameter(REGISTRY_TYPE_KEY, SERVICE_REGISTRY_TYPE)//force application
//									.useAsConfigCenter(true)
//									.useAsMetadataCenter(false)
//					)
//					.reference("echo", builder -> builder.interfaceClass(EchoService.class).protocol("dubbo").services("zookeeper-dubbo-provider"))
//					.start();
//
//			EchoService echoService = bootstrap.getCache().get(EchoService.class);

			for (int i = 0; i < 5000000; i++) {
				System.out.println(echoService.echo("Hello,World"));
				Thread.sleep(2000L);
//            System.out.println(userService.getUser(i * 1L));
			}



			for(int i=0; i<500000; i++){
				System.out.println("No."+i+" "+echoService.echo("Hello,World"));
				Thread.sleep(2000L);
			}

		};
	}

}

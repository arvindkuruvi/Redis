package com.arvind.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

//redis configuration
@Configuration
public class RedisConfig{ 

	@Autowired
	RedisConnectionFactory factory;

	@SuppressWarnings("deprecation")
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		 JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("localhost");
	    factory.setPort(6379);
	    factory.setUsePool(true);
		return  factory;
	}
	
	
//	@SuppressWarnings("deprecation")
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		 JedisConnectionFactory factory = new JedisConnectionFactory();
//		factory.setHostName("presence_system_redis");
//	    factory.setPort(6379);
//	    factory.setUsePool(true);
//		return  factory;
//	}
	
	@Bean
	 RedisTemplate< String, Object > redisTemplate() {
	  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
	  template.setConnectionFactory( jedisConnectionFactory() );
	  template.setKeySerializer( new StringRedisSerializer() );
	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  return template;
	 }

	public @Bean RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setEnableTransactionSupport(true);
		return template;
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
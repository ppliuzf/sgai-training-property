package com.sgai.property.commonService.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redicache 工具类
 * 
 */
@SuppressWarnings("unchecked")
@Component
public class RedisClient {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}


	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
//			CacheChannel cache = J2Cache.getChannel();
//			cache.evict(Constants.J2CACHE_CHANNEL_NAME,key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
//		Object obj=get(key);
//		if(obj==null){
//			return false;
//		}else{
//			return true;
//		}
		return redisTemplate.hasKey(key);
	}


	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
//			CacheChannel cache = J2Cache.getChannel();
//			cache.set(Constants.J2CACHE_CHANNEL_NAME,key,value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * @param key
	 * @return
	 */
	public synchronized boolean setNx(final String key) {
		////redisTemplate.opsForValue().setIfAbsent(key,key).booleanValue();
//		try {
//			if(exists(key)){
//				return true;
//			}else{
//				set(key,key);
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return redisTemplate.opsForValue().setIfAbsent(key,key).booleanValue();
	}

	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public Object get(String key){
//		CacheChannel cache = J2Cache.getChannel();
//		CacheObject object=cache.get(Constants.J2CACHE_CHANNEL_NAME,key);
//		return object.getValue();
		Object result = null;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			result = operations.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * @param key
	 * @param expireTime 秒
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
//			CacheChannel cache = J2Cache.getChannel();
//			cache.set(Constants.J2CACHE_CHANNEL_NAME,key,value,expireTime.intValue());
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
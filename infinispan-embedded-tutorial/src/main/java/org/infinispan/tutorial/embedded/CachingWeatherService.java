package org.infinispan.tutorial.embedded;

import org.infinispan.Cache;

public abstract class CachingWeatherService implements WeatherService {
	
	private Cache<String, LocationWeather> cache;
	
	public CachingWeatherService(Cache<String, LocationWeather> cache) {
		this.cache = cache;
	}

	@Override
	public LocationWeather getWeatherForLocation(String location) {
		LocationWeather weather = cache.get(location);
		if (weather == null) {
			weather = fetchWeather(location);
			cache.put(location, weather);
		}
		return weather;
	}

	protected abstract LocationWeather fetchWeather(String location) ;
}

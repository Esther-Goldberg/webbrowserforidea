package com.github.esthergoldberg.devrantclient;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.*;

class MyCookieStore implements CookieStore {
	private Map<URI, List<HttpCookie>> map = new HashMap<URI, List<HttpCookie>>();

	public void add(URI uri, HttpCookie cookie) {
		List<HttpCookie> cookies = map.get(uri);
		if (cookies == null) {
			cookies = new ArrayList<HttpCookie>();
			map.put(uri, cookies);
		}
		cookies.add(cookie);
	}

	public List<HttpCookie> get(URI uri) {
		List<HttpCookie> cookies = map.get(uri);
		if (cookies == null) {
			cookies = new ArrayList<HttpCookie>();
			map.put(uri, cookies);
		}
		return cookies;
	}

	public List<HttpCookie> getCookies() {
		Collection<List<HttpCookie>> values = map.values();
		List<HttpCookie> result = new ArrayList<HttpCookie>();
		for (List<HttpCookie> value : values) {
			result.addAll(value);
		}
		return result;
	}

	public List<URI> getURIs() {
		Set<URI> keys = map.keySet();
		return new ArrayList<URI>(keys);

	}

	public boolean remove(URI uri, HttpCookie cookie) {
		List<HttpCookie> cookies = map.get(uri);
		if (cookies == null) {
			return false;
		}
		return cookies.remove(cookie);
	}

	public boolean removeAll() {
		map.clear();
		return true;
	}
}
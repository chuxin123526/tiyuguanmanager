package cn.wheel.tiyuguanmanager.user.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	private Map<String, Object> map;

	public MapUtils() {
		this.map = new HashMap<String, Object>();
	}

	public MapUtils put(String key, Object value) {
		this.map.put(key, value);
		return this;
	}

	public Map<String, Object> toMap() {
		return this.map;
	}
}

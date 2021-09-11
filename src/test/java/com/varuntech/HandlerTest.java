package com.varuntech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class HandlerTest {

	public HandlerTest() {
	
	}
	
	@Test
	public void testLogs() {
		Handler h = new Handler();
		
		List<String> l = new ArrayList<>();
		l.add("ABC");
		l.add("BCD");
		Map <String, Object> m = new HashMap<>();
		m.put("name", l);
		h.handleRequest(m, null);
	}

}

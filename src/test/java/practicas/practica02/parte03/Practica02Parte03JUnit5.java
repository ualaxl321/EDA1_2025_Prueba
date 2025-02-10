package practicas.practica02.parte03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte03JUnit5 {	
	@Test
	@Order(0)
	public void test00() {
		MyTreeMap<String, ArrayList<Double>> datos = new MyTreeMap<>();
		ArrayList<Double> aux;
		
		assertTrue(datos.isEmpty());
		assertTrue(datos.put("Oswald", new ArrayList<>()) == null);
		assertTrue(datos.get("Oswald") != null);
		
		assertTrue(datos.put("Oswald", null) != null);
		assertTrue(datos.get("Oswald") == null);
		
		assertTrue(datos.put("Oswald", new ArrayList<>()) == null);
		
		assertTrue(datos.get("Pepe") == null);
		
		datos.get("Oswald").add(3.0);
		datos.get("Oswald").add(2.0);
		datos.get("Oswald").add(4.0);
		datos.get("Oswald").add(7.0);
		
		assertTrue(datos.get("Oswald").size() == 4);
		
		aux = new ArrayList<>();
		aux.add(3.0);
		aux.add(2.0);
		aux.add(8.0);
		aux.add(3.0);
		assertTrue(datos.put("Edward", aux) == null);
		assertTrue(datos.put("Edward", aux) != null);
		
		assertTrue(datos.get("Edward").size() == 4);
		
		assertTrue(datos.size() == 2);
				
		assertEquals("[Edward, Oswald]", datos.keySet().toString());
		assertEquals("[[3.0, 2.0, 8.0, 3.0], [3.0, 2.0, 4.0, 7.0]]", datos.values().toString());
		assertEquals("[Edward <[3.0, 2.0, 8.0, 3.0]>, Oswald <[3.0, 2.0, 4.0, 7.0]>]", datos.pairSet().toString());
		
		assertEquals(datos.toString(), datos.pairSet().toString());
		
		assertEquals("[3.0, 2.0, 8.0, 3.0]", datos.get("Edward").toString());
		aux.clear();
		assertEquals("[]", datos.get("Edward").toString());
		
		
		assertTrue(datos.put("Edward", new ArrayList<>()) != null);
		assertEquals("[Edward <[]>, Oswald <[3.0, 2.0, 4.0, 7.0]>]", datos.pairSet().toString());
		
		assertTrue(datos.put("Alfred", new ArrayList<>()) == null);
		assertTrue(datos.size() == 3);
		assertEquals("[Alfred <[]>, Edward <[]>, Oswald <[3.0, 2.0, 4.0, 7.0]>]", datos.pairSet().toString());
		
		aux = datos.get("Alfred");
		
		assertTrue(datos.containsKey("Alfred"));
		assertFalse(datos.containsKey("alfred"));
		
		assertTrue(datos.get("Alfred").size() == 0);
		assertTrue(datos.get("alfred") == null);
		
		aux.add(23.3);
		aux.add(34.3);
		
		assertTrue(datos.get("Alfred").size() == 2);
		assertTrue(datos.put("Oswald", aux) != null);
		
		assertEquals("[23.3, 34.3]", datos.get("Oswald").toString());
		assertEquals("[23.3, 34.3]", datos.get("Alfred").toString());
		
		aux.add(.5);
		aux.add(.6);
		aux.add(.3); 
		
		assertEquals("[23.3, 34.3, 0.5, 0.6, 0.3]", datos.get("Oswald").toString());
		
		assertEquals("[23.3, 34.3, 0.5, 0.6, 0.3]", datos.get("Alfred").toString());
		
		assertNull(datos.get("Bruce"));
		assertFalse(datos.containsKey("Bruce"));

		assertEquals("[Alfred <[23.3, 34.3, 0.5, 0.6, 0.3]>, Edward <[]>, Oswald <[23.3, 34.3, 0.5, 0.6, 0.3]>]", datos.toString());
		
		datos.clear();
		aux.clear();
		
		assertEquals("[]", datos.pairSet().toString());
		assertEquals("[]", datos.keySet().toString());
		assertEquals("[]", datos.values().toString());
		assertEquals(datos.toString(), datos.pairSet().toString());
	}
	
	@Test
	@Order(1)
	public void test01() {
		Usuario usuario01 = new Usuario("@oswald");
		Usuario usuario02 = new Usuario("@edwarD");
		Usuario usuario03 = new Usuario("@Alfred");
		
		usuario01.add("dispositivo01", "hola", "hola", "que", "tal", "esto", "es");
		usuario01.add("dispositivo01", "una", "prueba");
		usuario02.add("dispositivo01", "buenas", "que", "tal");
		usuario02.add("dispositivo02", "hola", "que", "tal");
		usuario03.add("dispositivo01", "hola", "como", "estas");
		usuario03.add("dispositivo02", "como", "te", "llamas");
		
	
		assertEquals("@oswald=<1 dispositivo>", usuario01.toString());
		assertEquals("@edwarD=<2 dispositivos>", usuario02.toString());
		assertEquals("@Alfred=<2 dispositivos>", usuario03.toString());
		
		usuario01.add("dispositivo02");
		usuario01.add("dispositivo03");
		usuario01.add("dispositivo04");
		usuario01.add("dispositivo02", "mas", "mensajes", "de", "prueba");
		usuario01.add("dispositivo03", "para", "ver", "si", "lo", "esperado", "coincide", "con", "lo", "real");
		
		assertEquals("@oswald=<4 dispositivos>", usuario01.toString());

		String expected = "dispositivo01 <[es, esto, hola, prueba, que, tal, una]>\n" +
						  "dispositivo02 <[de, mas, mensajes, prueba]>\n" +
						  "dispositivo03 <[coincide, con, esperado, lo, para, real, si, ver]>\n" +
						  "dispositivo04 <[]>\n";
								
		String actual = "";
		for (Par<String, AVLTree<String>> par : usuario01) {
			actual += par.toString() + "\n";
		}
		assertEquals(expected, actual);
		
		usuario01.clear();
		usuario02.clear();
		usuario03.clear();
	}
}
package practicas.practica02.parte02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import practicas.auxiliar.Par;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica02Parte02JUnit5 {
	String directorioEntrada = System.getProperty("user.dir") + File.separator +
			                  "src" + File.separator +
			                  "main" + File.separator +
			                  "java" + File.separator +
						      "practicas" + File.separator +
			   				  "practica02" + File.separator +
			   				  "parte02" + File.separator;

	@Test
	@Order(0)
	public void test00() {
		Par<String, Integer> par1 = new Par<>("hola", 0);
		Par<String, Integer> par2 = new Par<>("hola", 0);
		Par<String, Integer> par3 = new Par<>("adios", 2);
		
				
		assertEquals("hola <0>", par1.toString());
		assertEquals("hola <0>", par2.toString());
		assertEquals("adios <2>", par3.toString());
		
		assertTrue(par1.compareTo(par2) == 0); 
		assertTrue(par1.compareTo(par3) > 0); 
		assertTrue(par3.compareTo(par2) < 0);
		assertTrue(par1.equals(par2));
		assertFalse(par1.equals(par3));
		assertFalse(par3.equals(par2));
		
		for (int i=0; i<1000; i++) { 
			par1.setValue(par1.getValue()+1);
			par2.setValue(par1.getValue()+2);
			par3.setValue(par1.getValue()+3);
		}
		
		assertEquals("hola <1000>", par1.toString());
		assertEquals("hola <1002>", par2.toString());
		assertEquals("adios <1003>", par3.toString());
		
		
		ArrayList<Par<String, Integer>> listaPares = new ArrayList<>();
		
		listaPares.add(par1);
		if (!listaPares.contains(par2)) listaPares.add(par2);
		if (!listaPares.contains(par3)) listaPares.add(par3);
		
		assertEquals("[hola <1000>, adios <1003>]",listaPares.toString());

		int pos1 = listaPares.indexOf(new Par<>("adios", null));
		assertTrue(pos1 == 1);

		int pos2 = listaPares.indexOf(new Par<>("nunca", null));
		assertTrue(pos2 == -1);

		listaPares.clear();
		
		
		Par<String, ArrayList<Integer>> parColeccion = new Par<>("pepe", new ArrayList<>());
		parColeccion.getValue().add(3);
		parColeccion.getValue().add(5);
		parColeccion.getValue().add(7);
		
		assertEquals("pepe <[3, 5, 7]>", parColeccion.toString());
		

		ArrayList<Integer> datos = new ArrayList<>();
		datos.add(4);
		datos.add(2);
		datos.add(1);
		
		assertTrue(parColeccion.setValue(datos) != null);
		
		assertEquals("pepe <[4, 2, 1]>", parColeccion.toString()); 
		
		datos.clear();
		
		assertEquals("pepe <[]>", parColeccion.toString()); 

		parColeccion.getValue().clear();

	}
	
	@Test
	@Order(1)
	public void test01() {
		ArrayList<Par<String, String>> lista = new ArrayList<>();
		lista.add(new Par<>("pedra", "10.00"));
		lista.add(new Par<>("juan", "10.00"));
		lista.add(new Par<>("martina", "4.00"));
		lista.add(new Par<>("amelia", "4.00"));

		assertEquals("[pedra <10.00>, juan <10.00>, martina <4.00>, amelia <4.00>]",lista.toString());

		lista.sort(null);
		assertEquals("[amelia <4.00>, juan <10.00>, martina <4.00>, pedra <10.00>]", lista.toString());

		lista.sort(Comparator.naturalOrder());
		assertEquals("[amelia <4.00>, juan <10.00>, martina <4.00>, pedra <10.00>]", lista.toString());

		lista.sort(Comparator.reverseOrder());
		assertEquals("[pedra <10.00>, martina <4.00>, juan <10.00>, amelia <4.00>]",lista.toString());

		lista.clear();
	}
	
	@Test
	@Order(2)
	public void test02() {
		Libro libro01 = new Libro("Libro01");
		Libro libro02 = new Libro("Libro02");

		assertEquals("libro01 -> [empty]", libro01.toString());
		assertEquals("libro02 -> [empty]", libro02.toString());

		libro01.add("adios", "mundo", "cruel", "adios");

		assertEquals("libro01 -> [adios <2>, cruel <1>, mundo <1>]", libro01.toString());

		libro02.add("hola", "mundo", "maravilloso", "adios", "mundo", "adios");

		assertEquals("libro02 -> [adios <2>, hola <1>, maravilloso <1>, mundo <2>]", libro02.toString());

		assertNotEquals(libro01, libro02);
		assertEquals(libro02, new Libro("   libRO02     "));
		assertTrue(libro01.compareTo(libro02) < 0);
		assertTrue(libro01.compareTo(new Libro("libro01")) == 0);
		assertTrue(libro01.compareTo(new Libro("libro00")) > 0);


		ArrayList<String> salidaEsperada = new ArrayList<>();

		int cont = 0;
		salidaEsperada.add("adios <2>");
		salidaEsperada.add("cruel <1>");
		salidaEsperada.add("mundo <1>");

		for (Par<String, Integer> palabraFreq : libro01) {
			assertEquals(salidaEsperada.get(cont++), palabraFreq.toString());
		}
		salidaEsperada.clear();

		salidaEsperada.add("adios <2>");
		salidaEsperada.add("hola <1>");
		salidaEsperada.add("maravilloso <1>");
		salidaEsperada.add("mundo <2>");

		cont = 0;
		for (Par<String, Integer> palabraFreq : libro02) {
			assertEquals(salidaEsperada.get(cont++), palabraFreq.toString());
		}
		salidaEsperada.clear();
		libro01.clear();
		libro02.clear();
	}

	@Test
	@Order(3)
	public void test03() {
		String usuario01 = "Pedra";
		String usuario02 = "Martina";
		String usuario03 = "Juan";
		String usuario04 = "Juan";
		Libro libro01 = new Libro("libro001");
		Libro libro02 = new Libro("libro002");

		assertEquals("Pedra", usuario01.toString());
		assertEquals("Martina", usuario02.toString());
		assertEquals("Juan", usuario03.toString());
		assertEquals("Juan", usuario04.toString());

		assertEquals(usuario03, usuario04);
		assertTrue(usuario01.compareTo(usuario03) > 0);
		assertTrue(usuario02.compareTo(usuario03) > 0);
		assertTrue(usuario03.compareTo(usuario04) == 0);

		libro01.add("hola", "mundo", "mundo", "mundo", "hola");
		libro02.add("adios", "mundo", "mundo", "mundo");

		assertEquals("libro001 -> [hola <2>, mundo <3>]", libro01.toString());
		assertEquals("libro002 -> [adios <1>, mundo <3>]", libro02.toString());

		libro01.clear();
		libro02.clear();

		assertEquals("libro001 -> [empty]", libro01.toString());
		assertEquals("libro002 -> [empty]", libro02.toString());
	}

	@Test
	@Order(4)
	public void test04() {
		Biblioteca biblioteca = new Biblioteca("Biblioteca UAL");
		final int NUM_LIBROS = 5;
		final int NUM_USUARIOS = 2;

		biblioteca.addLibro("l1");
		biblioteca.addLibro("l2");
		biblioteca.addLibro("l3");
		biblioteca.addLibro("l4");
		biblioteca.addLibro("l5");
		biblioteca.addUsuario("u1");
		biblioteca.addUsuario("u2");

		assertEquals("Biblioteca UAL (" + NUM_LIBROS + " libros y " + NUM_USUARIOS + " usuarios)", biblioteca.toString());

		assertFalse(biblioteca.prestarLibro("u19", "l1"));
		assertFalse(biblioteca.prestarLibro("u1", "l19")); 
		assertTrue(biblioteca.prestarLibro("u1", "l1"));
		assertFalse(biblioteca.prestarLibro("u2", "l1"));
		assertFalse(biblioteca.prestarLibro("u01", "l1"));
		assertTrue(biblioteca.prestarLibro("u1", "l2"));
		assertTrue(biblioteca.devolverLibro("u1", "l1"));
		assertTrue(biblioteca.prestarLibro("u1",  "l1"));
		assertTrue(biblioteca.devolverLibro("u1",  "l1"));
		assertFalse(biblioteca.devolverLibro("u1", "l1"));
		assertFalse(biblioteca.devolverLibro("u2", "l1")); 
		assertTrue(biblioteca.devolverLibro("u1", "l2"));
		assertFalse(biblioteca.prestarLibro("u2", "libro3"));
		assertTrue(biblioteca.prestarLibro("u2", "l1"));
		assertTrue(biblioteca.prestarLibro("u2", "l2"));
		assertTrue(biblioteca.prestarLibro("u2", "l3"));
		assertTrue(biblioteca.prestarLibro("u2", "l4"));
		assertTrue(biblioteca.devolverLibro("u2", "l1"));
		assertTrue(biblioteca.devolverLibro("u2", "l2"));
		assertTrue(biblioteca.devolverLibro("u2", "l3"));
		assertTrue(biblioteca.devolverLibro("u2", "l4"));
		assertTrue(biblioteca.prestarLibro("u2", "l1"));
		assertTrue(biblioteca.devolverLibro("u2", "l1"));

		assertEquals("[u1, u2]",biblioteca.getUsuariosLibro("l1").toString());
		assertEquals("[u1, u2]", biblioteca.getUsuariosLibro("l2").toString());
		assertEquals("[u2]", biblioteca.getUsuariosLibro("l3").toString());
		assertEquals("[u2]", biblioteca.getUsuariosLibro("l4").toString());
		assertEquals("[]", biblioteca.getUsuariosLibro("l5").toString());
		assertTrue(biblioteca.getUsuariosLibro("l5").size() == 0);
		assertTrue(biblioteca.getUsuariosLibro("l25") == null); 


		assertEquals("[l1, l2, l1]", biblioteca.getLibrosPrestados("u1").toString());
		assertEquals("[l1, l2, l3, l4, l1]", biblioteca.getLibrosPrestados("u2").toString());
		assertTrue(biblioteca.getLibrosPrestados("user03") == null);


		assertEquals("[]", biblioteca.getLibrosPrestados().toString());
		assertTrue(biblioteca.getLibrosPrestados().isEmpty());

		assertTrue(biblioteca.prestarLibro("u2", "l1"));
		assertTrue(biblioteca.prestarLibro("u1", "l3"));

		assertEquals("[l1, l3]", biblioteca.getLibrosPrestados().toString());

		assertTrue(biblioteca.devolverLibro("u2", "l1"));
		assertTrue(biblioteca.devolverLibro("u1", "l3"));

		assertEquals("[]", biblioteca.getLibrosPrestados().toString());


		for (int i=1; i <= NUM_LIBROS; i++) {
			biblioteca.prestarLibro("u" + (i%2==0?1:2), "l" + i);
		}

		assertEquals("[l1, l2, l3, l4, l5]", biblioteca.getLibrosPrestados().toString());
		assertEquals("[l1, l2, l1, l3, l2, l4]", biblioteca.getLibrosPrestados("u1").toString());
		assertEquals("[l1, l2, l3, l4, l1, l1, l1, l3, l5]", biblioteca.getLibrosPrestados("u2").toString());

		for (int i=1; i <= NUM_LIBROS; i++) {
			if (i%2 == 0) continue;
			assertTrue(biblioteca.devolverLibro("u2", "l" + i));
		}

		assertEquals("[l2, l4]", biblioteca.getLibrosPrestados().toString());

		assertTrue(biblioteca.devolverLibro("u1",  "l2"));
		assertTrue(biblioteca.devolverLibro("u1",  "l4"));

		assertEquals("[l1, l2, l1, l3, l2, l4]", biblioteca.getLibrosPrestados("u1").toString());
		assertEquals("[l1, l2, l3, l4, l1, l1, l1, l3, l5]", biblioteca.getLibrosPrestados("u2").toString());
		assertTrue(biblioteca.getLibrosPrestados().isEmpty());

		biblioteca.clear();
	}

	@Test
	@Order(5)
	public void test05() {
		Biblioteca biblioteca = new Biblioteca("Picasso");
		String usuario01 = "Luisa";
		String usuario02 = "Carmen";
		String usuario03 = "Juan";
		String usuario04 = "Pedro";
		Libro libro01 = new Libro("El bosque de los cuatro vientos");
		Libro libro02 = new Libro("Y Julia reto a los dioses");
		Libro libro03 = new Libro("Las alas de Sophie");
		Libro libro04 = new Libro("La madre de Frankestein");

		assertFalse(libro01.load(directorioEntrada + "archivo099"));
		assertTrue(libro01.load(directorioEntrada + "archivo01.txt"));
		assertTrue(libro02.load(directorioEntrada + "archivo02.txt"));
		assertTrue(libro02.load(directorioEntrada + "archivo02.txt"));
		assertTrue(libro03.load(directorioEntrada + "archivo03.txt"));
		assertTrue(libro04.load(directorioEntrada + "archivo03.txt"));
		assertTrue(libro04.load(directorioEntrada + "archivo04.txt"));

		biblioteca.addLibro(libro01);
		biblioteca.addLibro(libro02);
		biblioteca.addLibro(libro03);
		biblioteca.addLibro(libro04);
		biblioteca.addUsuario(usuario01);
		biblioteca.addUsuario(usuario02);
		biblioteca.addUsuario(usuario03);
		biblioteca.addUsuario(usuario04);

		assertEquals("Picasso (4 libros y 4 usuarios)", biblioteca.toString());

		assertTrue(biblioteca.prestarLibro("Luisa", "La madre de Frankestein"));
		assertTrue(biblioteca.prestarLibro("Juan",  "El bosque de los cuatro vientos"));
		assertTrue(biblioteca.prestarLibro("Pedro", "Las alas de Sophie"));
		assertTrue(biblioteca.prestarLibro("Carmen", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.devolverLibro("Luisa", "La madre de Frankestein"));
		assertTrue(biblioteca.prestarLibro("Pedro", "La madre de Frankestein"));
		assertTrue(biblioteca.devolverLibro("Carmen", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.prestarLibro("Juan", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.devolverLibro("Juan", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.prestarLibro("Pedro", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.devolverLibro("Pedro", "La madre de Frankestein"));
		assertTrue(biblioteca.prestarLibro("Luisa", "La madre de Frankestein"));
		assertTrue(biblioteca.devolverLibro("Luisa", "La madre de Frankestein"));

		assertTrue(biblioteca.getLibrosPrestados().size() == 3);
		assertEquals("[el bosque de los cuatro vientos, las alas de sophie, y julia reto a los dioses]", biblioteca.getLibrosPrestados().toString());


		assertEquals("[la madre de frankestein, la madre de frankestein]", biblioteca.getLibrosPrestados("Luisa").toString());
		assertEquals("[las alas de sophie, la madre de frankestein, y julia reto a los dioses]", biblioteca.getLibrosPrestados("Pedro").toString());
		assertEquals("[el bosque de los cuatro vientos, y julia reto a los dioses]", biblioteca.getLibrosPrestados("Juan").toString());

		assertEquals("[Juan]", biblioteca.getUsuariosLibro("El bosque de los cuatro vientos").toString());
		assertEquals("[Luisa, Pedro]", biblioteca.getUsuariosLibro("La madre de Frankestein").toString());

		libro01.clear();
		libro02.clear();
		libro03.clear();
		libro04.clear();
		biblioteca.clear();
	}
	
	@Test
	@Order(6)
	public void test06() { 
		Libro libro01 = new Libro("libro01");
		Libro libro02 = new Libro("libro02");
		
		assertTrue(libro01.load(directorioEntrada + "archivo01.txt"));
		assertTrue(libro02.load(directorioEntrada + "archivo02.txt"));
		
		assertEquals("[4 <[del, el, que]>, 5 <[la, un, y]>, 6 <[en]>, 7 <[de]>]", libro01.getPalabrasFrecuentes(3).toString());
		assertEquals("[2 <[a, becquer, con, galicia, los, marina, mas, monasterio, para, se, su, una, xix]>, 4 <[del, el, que]>, 5 <[la, un, y]>, 6 <[en]>, 7 <[de]>]", libro01.getPalabrasFrecuentes(2).toString());
		assertEquals("[3 <[del, y]>, 4 <[el, julia]>, 6 <[en]>, 7 <[de]>, 8 <[la, que]>]", libro02.getPalabrasFrecuentes(3).toString());
		assertEquals("[7 <[de]>, 8 <[la, que]>]", libro02.getPalabrasFrecuentes(7).toString());
		
		libro01.clear();
		libro02.clear();
		
		assertEquals("[]", libro01.getPalabrasFrecuentes(3).toString());
		assertTrue(libro01.getPalabrasFrecuentes(2).isEmpty());
		assertTrue(libro02.getPalabrasFrecuentes(2).isEmpty());
		assertTrue(libro02.getPalabrasFrecuentes(7).size() == 0);
	}
}
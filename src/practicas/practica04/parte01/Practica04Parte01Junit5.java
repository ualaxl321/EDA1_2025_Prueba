package practicas.practica04.parte01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import practicas.practica04.Network;
import practicas.practica04.NetworkIterator;

import java.io.File;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Practica04Parte01Junit5 {
	private final String directorioEntrada = System.getProperty("user.dir") + File.separator +
			                                 "src" + File.separator +
			                                 "main" + File.separator +
			                                 "java" + File.separator +
									         "practicas" + File.separator +
			                           		 "practica04" + File.separator +
			                           		 "parte01" + File.separator;

	@Test
	@Order(0)
	public void test00() {
		Network<Integer> net = new Network<>();
		assertTrue(net.isDirected());
		assertTrue(net.isEmpty());
		net.addVertex(0);
		net.addVertex(1);
		net.addVertex(2);
		net.addVertex(3);
		net.addVertex(4);
		net.addVertex(5);
		net.addVertex(6);

		net.addEdge(4, 0, 1);
		net.addEdge(4, 6, 1);
		net.addEdge(4, 3, 1);
		net.addEdge(1, 4, 1);
		net.addEdge(3, 1, 1);
		net.addEdge(3, 2, 1); 
		net.addEdge(2, 5, 1);
		net.addEdge(5, 4, 1);

		assertEquals("{0={}, 1={4=1.0}, 2={5=1.0}, 3={1=1.0, 2=1.0}, 4={0=1.0, 3=1.0, 6=1.0}, 5={4=1.0}, 6={}}", net.toString());
		assertEquals("[0, 3, 6]", net.getNeighbors(4).toString());
		assertEquals("[1, 2]", net.getNeighbors(3).toString());
		assertNull(net.getNeighbors(8));
		assertNull(net.getNeighbors(8));
		assertTrue(net.getWeight(2, 5) == 1.0);
		assertTrue(net.getWeight(0, 3) == -1);
		assertTrue(net.numberOfVertices() == 7);
		assertNull(net.toArrayBreadthFirst(8));
		assertNull(net.toArrayDepthFirst(8));
		assertEquals("[3, 1, 2, 4, 5, 0, 6]", net.toArrayBreadthFirst(3).toString());
		assertEquals("[3, 2, 5, 4, 6, 0, 1]", net.toArrayDepthFirst(3).toString());

		net.removeVertex(0);
		net.removeVertex(1);
		net.removeVertex(2);
		net.removeVertex(3);
		assertTrue(net.numberOfVertices() == 3);
		net.removeEdge(5, 4);
		net.setWeight(4, 6, 2.0);
		assertEquals("{4={6=2.0}, 5={}, 6={}}", net.toString());
		assertTrue(net.vertexSet().size() == 3);
		net.clear();
		assertTrue(net.vertexSet().size() == 0);
		assertEquals("{}", net.toString());
	}

	@Test
	@Order(1)
	public void test01() {
		CarreterasNetwork net = new CarreterasNetwork();
		assertFalse(net.load("datos.txt"));
		assertTrue(net.load(directorioEntrada + "datos.txt"));

		assertTrue(net.numberOfVertices() == 21);
		assertTrue(net.numberOfEdges() == 58);

		String cadena = "{Albacete={Madrid=251.0, Murcia=150.0, Valencia=191.0}, "
					   + "Almeria={Granada=173.0, Murcia=224.0}, " + "Badajoz={Caceres=90.0, Huelva=234.0, Madrid=403.0}, "
					   + "Barcelona={Gerona=100.0, Valencia=349.0, Zaragoza=296.0}, "
					   + "Bilbao={Madrid=395.0, Oviedo=304.0, Valladolid=280.0, Zaragoza=324.0}, " + "Caceres={Badajoz=90.0}, "
					   + "Cadiz={Sevilla=125.0}, " + "Corunya={Valladolid=455.0, Vigo=171.0}, "
					   + "Gerona={Barcelona=100.0, Lerida=222.0}, "
					   + "Granada={Almeria=173.0, Jaen=99.0, Murcia=278.0, Sevilla=256.0}, "
					   + "Huelva={Badajoz=234.0, Sevilla=92.0}, " + "Jaen={Granada=99.0, Madrid=335.0, Sevilla=242.0}, "
					   + "Lerida={Gerona=222.0}, "
					   + "Madrid={Albacete=251.0, Badajoz=403.0, Bilbao=395.0, Jaen=335.0, Valladolid=193.0, Zaragoza=325.0}, "
					   + "Murcia={Albacete=150.0, Almeria=224.0, Granada=278.0, Valencia=241.0}, " + "Oviedo={Bilbao=304.0}, "
					   + "Sevilla={Cadiz=125.0, Granada=256.0, Huelva=92.0, Jaen=242.0}, "
					   + "Valencia={Albacete=191.0, Barcelona=349.0, Murcia=241.0}, "
					   + "Valladolid={Bilbao=280.0, Corunya=455.0, Madrid=193.0, Vigo=356.0}, "
					   + "Vigo={Corunya=171.0, Valladolid=356.0}, "
					   + "Zaragoza={Barcelona=296.0, Bilbao=324.0, Madrid=325.0}}";

		assertEquals(cadena, net.toString());

		net.clear();
	}

	@Test
	@Order(2)
	public void test02() {
		CarreterasNetwork net = new CarreterasNetwork();
		net.load(directorioEntrada + "datos.txt");

		String str = "";

		for (String v: NetworkIterator.normalIterator(net)) {
			str += v + " ";
		}
		assertEquals("Albacete Almeria Badajoz Barcelona Bilbao Caceres Cadiz Corunya Gerona Granada Huelva Jaen Lerida Madrid Murcia Oviedo Sevilla Valencia Valladolid Vigo Zaragoza ", str);

		str = "";
		for (String v : NetworkIterator.breadthFirstIterator(net, "Almeria")) { // Iteración en anchura
			str += v + " ";
		}
		assertEquals("Almeria Granada Murcia Jaen Sevilla Albacete Valencia Madrid Cadiz Huelva Barcelona Badajoz Bilbao Valladolid Zaragoza Gerona Caceres Oviedo Corunya Vigo Lerida ", str);

		str = "";
		for (String v : NetworkIterator.depthFirstIterator(net, "Almeria")) { // Iteración en profundidad
			str += v + " ";
		}
		assertEquals("Almeria Murcia Valencia Barcelona Zaragoza Madrid Valladolid Vigo Corunya Bilbao Oviedo Jaen Sevilla Huelva Badajoz Caceres Granada Cadiz Albacete Gerona Lerida ", str);

		net.clear();
	}

	@Test
	@Order(3)
	public void test03() {
		CarreterasNetwork net = new CarreterasNetwork();
		assertTrue(net.load(directorioEntrada + "datos.txt"));
		

		assertNull(net.getDijkstra(null, "Granada"));
		assertNull(net.getDijkstra("Granada", null));
		assertNull(net.getDijkstra("Sevilla", "Sevilla"));
		assertNull(net.getDijkstra("Almeria", "Sinsinati"));

		assertEquals("[Granada <0.0>, Jaen <99.0>, Madrid <434.0>, Bilbao <829.0>, Oviedo <1133.0>]", net.getDijkstra("Granada", "Oviedo").toString());
		assertEquals("[Almeria <0.0>, Granada <173.0>, Sevilla <429.0>, Huelva <521.0>, Badajoz <755.0>, Caceres <845.0>]", net.getDijkstra("Almeria", "Caceres").toString());
		assertEquals("[Oviedo <0.0>, Bilbao <304.0>, Madrid <699.0>, Albacete <950.0>, Murcia <1100.0>]", net.getDijkstra("Oviedo",  "Murcia").toString());
		
		net.clear();
	}
}
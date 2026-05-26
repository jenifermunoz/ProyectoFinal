package org.uniquindio.proyectofinalcodigo.model;

/**
 * Clase de pruebas unitarias para la clase Parque.
 * Verifica los métodos de búsqueda y filtrado de personas dentro del parque.
 *
 * @author [Tu nombre aquí]
 * @since 2026-05
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueTest {

    private static final Logger LOG = Logger.getLogger(ParqueTest.class.getName());

    // Objetos que usamos en todas las pruebas (se recrean antes de cada una)
    private Parque parque;
    private Visitante visitante1;
    private Operador operador1;

    /**
     * Se ejecuta ANTES de cada prueba.
     * Aquí preparamos los datos base que necesitamos.
     */
    @BeforeEach
    public void setUp() {
        // Creamos un parque de prueba
        parque = new Parque(
                "Tech-Park UQ",
                "Calle 12 # 15-23",
                "Soleado",
                "123456789",
                "www.techparkuq.com",
                true,
                500,
                0,
                true,
                LocalDate.now(),
                null
        );

        // Creamos un visitante de prueba y lo añadimos al parque
        visitante1 = new Visitante(
                200000, 25, false, parque,
                "1001234567", "Carlos Perez",
                LocalDate.of(2000, 3, 15),
                1.75, "3101234567", "Colombiana"
        );
        parque.getListPersonas().add(visitante1);

        // Creamos un operador de prueba y lo añadimos al parque
        operador1 = new Operador(
                3, parque, null,
                "1009876543", "Ana Lopez",
                LocalDate.of(1995, 7, 20),
                1.65, "3209876543", "Colombiana"
        );
        parque.getListPersonas().add(operador1);
    }

    /**
     * PRUEBA 1 - assertEquals
     * Verifica que buscarVisitantebyCedula devuelve el índice correcto (0)
     * cuando el visitante SÍ existe en el parque.
     */
    @Test
    public void testBuscarVisitanteExistente() {
        LOG.info("Inicio prueba: testBuscarVisitanteExistente...");

        int resultado = parque.buscarVisitantebyCedula("1001234567");
        assertEquals(0, resultado);

        LOG.info("Fin prueba: testBuscarVisitanteExistente");
    }

    /**
     * PRUEBA 2 - assertEquals
     * Verifica que buscarVisitantebyCedula devuelve -1
     * cuando el visitante NO existe en el parque.
     */
    @Test
    public void testBuscarVisitanteNoExistente() {
        LOG.info("Inicio prueba: testBuscarVisitanteNoExistente...");

        int resultado = parque.buscarVisitantebyCedula("9999999999");
        assertEquals(-1, resultado);

        LOG.info("Fin prueba: testBuscarVisitanteNoExistente");
    }

    /**
     * PRUEBA 3 - assertEquals
     * Verifica que buscarOperadorByCedula devuelve el índice correcto (0)
     * cuando el operador SÍ existe.
     */
    @Test
    public void testBuscarOperadorExistente() {
        LOG.info("Inicio prueba: testBuscarOperadorExistente...");

        int resultado = parque.buscarOperadorByCedula("1009876543");
        assertEquals(0, resultado);

        LOG.info("Fin prueba: testBuscarOperadorExistente");
    }

    /**
     * PRUEBA 4 - assertTrue
     * Verifica que el parque está abierto al inicializarlo con abierto = true.
     */
    @Test
    public void testParqueEstaAbierto() {
        LOG.info("Inicio prueba: testParqueEstaAbierto...");

        assertTrue(parque.isAbierto());

        LOG.info("Fin prueba: testParqueEstaAbierto");
    }

    /**
     * PRUEBA 5 - assertNotNull
     * Verifica que la lista de personas del parque no es null después de crearlo.
     */
    @Test
    public void testListaPersonasNoEsNull() {
        LOG.info("Inicio prueba: testListaPersonasNoEsNull...");

        assertNotNull(parque.getListPersonas());

        LOG.info("Fin prueba: testListaPersonasNoEsNull");
    }

}

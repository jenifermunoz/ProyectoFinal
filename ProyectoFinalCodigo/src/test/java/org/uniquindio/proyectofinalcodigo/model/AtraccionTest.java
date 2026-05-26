package org.uniquindio.proyectofinalcodigo.model;

/**
 * Clase de pruebas unitarias para la clase Atraccion.
 * Verifica el comportamiento de los métodos de ingreso y salida de visitantes.
 *
 * @author Gabriel Garcia Duque
 * @since 2026-05
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class AtraccionTest {

    private static final Logger LOG = Logger.getLogger(AtraccionTest.class.getName());

    private Parque parque;
    private Zona zona;
    private Atraccion atraccion;
    private ColaVirtual cola;
    private Visitante visitante1;

    /**
     * Se ejecuta ANTES de cada prueba.
     * Construimos el árbol completo: Parque → Zona → Atraccion.
     */
    @BeforeEach
    public void setUp() {
        parque = new Parque(
                "Tech-Park UQ", "Calle 12 # 15-23", "Soleado",
                "123456789", "www.techparkuq.com",
                true, 500, 0, true, LocalDate.now(), null
        );

        zona = new Zona("Z1", "Zona Aventura", parque, "Norte", 100);
        parque.getListZonas().add(zona);

        // Cola de la atraccion (tiempo inicial 0)
        cola = new ColaVirtual(null, 0);

        // Atraccion activa, altura mínima 1.20, edad mínima 10
        atraccion = new Atraccion(
                "A1", "Montaña Rusa", 10,
                1.20, 10, 0,
                5000, 15, 0, 0,
                TipoAtraccion.MECANICA_DE_ALTURA,
                null,
                EstadoAtraccion.ACTIVA,
                zona, cola
        );
        cola.setAtraccion(atraccion);
        zona.getListAtracciones().add(atraccion);

        visitante1 = new Visitante(
                100000, 20, false, parque,
                "1001234567", "Luis Torres",
                LocalDate.of(2004, 5, 10),
                1.70, "3001234567", "Colombiana"
        );
        parque.getListPersonas().add(visitante1);
    }

    /**
     * PRUEBA 1 - assertEquals
     * Verifica que el contador de visitantes empieza en 0.
     */
    @Test
    public void testContadorVisitantesInicialEsCero() {
        LOG.info("Inicio prueba: testContadorVisitantesInicialEsCero...");

        assertEquals(0, atraccion.getContadorVisitantes());

        LOG.info("Fin prueba: testContadorVisitantesInicialEsCero");
    }

    /**
     * PRUEBA 2 - assertEquals
     * Verifica que ingresarVisitante agrega correctamente 1 visitante a la lista.
     */
    @Test
    public void testIngresarVisitanteAumentaLista() {
        LOG.info("Inicio prueba: testIngresarVisitanteAumentaLista...");

        atraccion.ingresarVisitante(visitante1);
        assertEquals(1, atraccion.getListVisitantes().size());

        LOG.info("Fin prueba: testIngresarVisitanteAumentaLista");
    }

    /**
     * PRUEBA 3 - assertEquals
     * Verifica que sacarVisitantes vacía la lista de visitantes.
     */
    @Test
    public void testSacarVisitantesVaciaLista() {
        LOG.info("Inicio prueba: testSacarVisitantesVaciaLista...");

        atraccion.ingresarVisitante(visitante1);
        atraccion.sacarVisitantes();
        assertEquals(0, atraccion.getListVisitantes().size());

        LOG.info("Fin prueba: testSacarVisitantesVaciaLista");
    }

    /**
     * PRUEBA 4 - assertEquals
     * Verifica que el estado inicial de la atraccion es ACTIVA.
     */
    @Test
    public void testEstadoInicialActiva() {
        LOG.info("Inicio prueba: testEstadoInicialActiva...");

        assertEquals(EstadoAtraccion.ACTIVA, atraccion.getEstado());

        LOG.info("Fin prueba: testEstadoInicialActiva");
    }

    /**
     * PRUEBA 5 - assertEquals
     * Verifica que setEstado cambia el estado correctamente a EN_MANTENIMIENTO.
     */
    @Test
    public void testCambiarEstadoAMantenimiento() {
        LOG.info("Inicio prueba: testCambiarEstadoAMantenimiento...");

        atraccion.setEstado(EstadoAtraccion.EN_MANTENIMIENTO);
        assertEquals(EstadoAtraccion.EN_MANTENIMIENTO, atraccion.getEstado());

        LOG.info("Fin prueba: testCambiarEstadoAMantenimiento");
    }

}

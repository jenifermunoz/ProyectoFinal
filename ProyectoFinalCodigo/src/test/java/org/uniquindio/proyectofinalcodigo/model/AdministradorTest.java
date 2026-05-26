package org.uniquindio.proyectofinalcodigo.model;

/**
 * Clase de pruebas unitarias para la clase Administrador.
 * Verifica el registro, modificación y eliminación de operadores,
 * y la creación de zonas y atracciones.
 *
 * @author Gabriel Garcia Duque
 * @since 2026-05
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    private static final Logger LOG = Logger.getLogger(AdministradorTest.class.getName());

    private Parque parque;
    private Administrador admin;

    @BeforeEach
    public void setUp() {
        parque = new Parque(
                "Tech-Park UQ", "Calle 12 # 15-23", "Soleado",
                "123456789", "www.techparkuq.com",
                true, 500, 0, true, LocalDate.now(), null
        );

        admin = new Administrador(
                parque,
                "9990000001", "Admin Principal",
                LocalDate.of(1985, 2, 14),
                1.75, "3001112233", "Colombiana"
        );
        parque.getListPersonas().add(admin);
    }

    /**
     * PRUEBA 1 - assertTrue
     * Verifica que registrarOperador devuelve true cuando el operador es nuevo
     * (cedula que no existe en el parque).
     */

    @Test
    public void testRegistrarOperadorNuevo() {
        LOG.info("Inicio prueba: testRegistrarOperadorNuevo...");

        boolean resultado = admin.registrarOperador(
                parque, "3001111111", "Camilo Vega",
                LocalDate.of(1992, 5, 20),
                1.72, "3101111111", "Colombiana"
        );
        assertTrue(resultado);

        LOG.info("Fin prueba: testRegistrarOperadorNuevo");
    }

    /**
     * PRUEBA 2 - assertFalse
     * Verifica que registrarOperador devuelve false si ya existe un operador
     * con la misma cedula (no permite duplicados).
     */
    @Test
    public void testRegistrarOperadorDuplicado() {
        LOG.info("Inicio prueba: testRegistrarOperadorDuplicado...");

        // Lo registramos por primera vez
        admin.registrarOperador(
                parque, "3001111111", "Camilo Vega",
                LocalDate.of(1992, 5, 20),
                1.72, "3101111111", "Colombiana"
        );

        // Intentamos registrarlo de nuevo con la misma cedula
        boolean resultado = admin.registrarOperador(
                parque, "3001111111", "Camilo Vega Copia",
                LocalDate.of(1992, 5, 20),
                1.72, "3101111112", "Colombiana"
        );
        assertFalse(resultado);

        LOG.info("Fin prueba: testRegistrarOperadorDuplicado");
    }

    /**
     * PRUEBA 3 - assertTrue
     * Verifica que modificarOperador devuelve true cuando el operador existe
     * y sus datos se actualizan.
     */

    @Test
    public void testModificarOperadorExistente() {
        LOG.info("Inicio prueba: testModificarOperadorExistente...");

        admin.registrarOperador(
                parque, "3001111111", "Camilo Vega",
                LocalDate.of(1992, 5, 20),
                1.72, "3101111111", "Colombiana"
        );

        boolean resultado = admin.modificarOperador(
                parque, "3001111111",
                "Camilo Vega Actualizado", 1.73, "3191111111", "Colombiana"
        );
        assertTrue(resultado);

        LOG.info("Fin prueba: testModificarOperadorExistente");
    }

    /**
     * PRUEBA 4 - assertTrue
     * Verifica que registrarZona devuelve true cuando se crea una zona nueva.
     */
    @Test
    public void testRegistrarZonaNueva() {
        LOG.info("Inicio prueba: testRegistrarZonaNueva...");

        boolean resultado = admin.registrarZona(parque, "Zona Acuatica", "Sur", 150);
        assertTrue(resultado);

        LOG.info("Fin prueba: testRegistrarZonaNueva");
    }

    /**
     * PRUEBA 5 - assertEquals
     * Verifica que después de registrar una zona, la lista de zonas del parque
     * tiene exactamente 1 zona.
     */
    
    @Test
    public void testCantidadZonasDespuesDeRegistrar() {
        LOG.info("Inicio prueba: testCantidadZonasDespuesDeRegistrar...");

        admin.registrarZona(parque, "Zona Acuatica", "Sur", 150);
        assertEquals(1, parque.getListZonas().size());

        LOG.info("Fin prueba: testCantidadZonasDespuesDeRegistrar");
    }

}

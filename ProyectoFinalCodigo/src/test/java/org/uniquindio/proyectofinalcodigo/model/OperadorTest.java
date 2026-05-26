package org.uniquindio.proyectofinalcodigo.model;

/**
 * Clase de pruebas unitarias para la clase Operador.
 * Verifica la validación de acceso a atracciones y cambios de capacidad.
 *
 * @author Gabriel Garcia Duque
 * @since 2026-05
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class OperadorTest {

    private static final Logger LOG = Logger.getLogger(OperadorTest.class.getName());

    private Parque parque;
    private Zona zona;
    private Atraccion atraccion;
    private ColaVirtual cola;
    private Operador operador;
    private Visitante visitanteCumple;
    private Visitante visitanteNoCumpleEdad;

    
    // Crear Parque, Operador y otras clases base para pruebas

    @BeforeEach
    public void setUp() {
        parque = new Parque("Tech-Park UQ", "Calle 12 # 15-23", "Soleado", "123456789", "www.techparkuq.com", true, 500, 0, true, LocalDate.now(), null);

        zona = new Zona("Z1", "Zona Aventura", parque, "Norte", 100);

        parque.getListZonas().add(zona);

        cola = new ColaVirtual(null, 0);

        // Atraccion con edad mínima 12 y altura mínima 1.40

        atraccion = new Atraccion(
                "A1", "Montaña Rusa", 10,
                1.40, 12, 0,
                0, 10, 0, 0,
                TipoAtraccion.MECANICA_DE_ALTURA,
                null,
                EstadoAtraccion.ACTIVA,
                zona, cola
        );

        cola.setAtraccion(atraccion);

        zona.getListAtracciones().add(atraccion);

        operador = new Operador(5, parque, zona, "2001111111", "Martin Sevallos", LocalDate.of(1990, 1, 10), 1.80, "3201111111", "Colombiana");

        parque.getListPersonas().add(operador);

        atraccion.getListOperadores().add(operador);

        // Visitante que si cumple requisitos (18 años, estatura 1.60, entrada activa GENERAL)

        visitanteCumple = new Visitante(100000, 18, false, parque, "1001000001", "Pedro Ramirez", LocalDate.of(2006, 4, 1), 1.60, "3001000001", "Colombiana");

        Entrada entradaActiva = new Entrada("G1", LocalDate.now(), LocalDate.now().plusDays(5), true, TipoEntrada.GENERAL);

        visitanteCumple.getListEntradas().add(entradaActiva);

        parque.getListPersonas().add(visitanteCumple);

        // Visitante que no cumple requisitos (8 años (debajo de la mínima de 12 años) )

        visitanteNoCumpleEdad = new Visitante(100000, 8, false, parque, "1001000002", "Sofia Nino", LocalDate.of(2016, 6, 15), 1.20, "3001000002", "Colombiana");

        Entrada entradaActiva2 = new Entrada("G2", LocalDate.now(), LocalDate.now().plusDays(5), true, TipoEntrada.GENERAL);

        visitanteNoCumpleEdad.getListEntradas().add(entradaActiva2);

        parque.getListPersonas().add(visitanteNoCumpleEdad);
    }

    // Prueba 1 - assertTrue / Funcionamiento correcto de un validarAccesoCola exitoso (devuelve True)
        // El visitante cumple con la edad, estatura y tiene entrada activa.
    
    @Test
    public void testValidarAccesoVisitanteValido() {
        LOG.info("Inicio prueba: testValidarAccesoVisitanteValido...");

        boolean resultado = operador.validarAccesoCola(visitanteCumple, atraccion);
        assertTrue(resultado);

        LOG.info("Fin prueba: testValidarAccesoVisitanteValido");
    }

    // Prueba 2 - assertFalse / Funcionamiento correcto de un validarAccesoCola fallido (devuelve False)
        // El visitante NO cumple con la edad minima

    @Test
    public void testValidarAccesoVisitanteSinEdad() {
        LOG.info("Inicio prueba: testValidarAccesoVisitanteSinEdad...");

        boolean resultado = operador.validarAccesoCola(visitanteNoCumpleEdad, atraccion);
        assertFalse(resultado);

        LOG.info("Fin prueba: testValidarAccesoVisitanteSinEdad");
    }

    // Prueba 3 - assertFalse / Funcionamiento correcto de un validarAccesoCola fallido (devuelve false)
        // Esto debido a que la atraccion esta cerrada.
    @Test
    public void testValidarAccesoAtraccionCerrada() {
        LOG.info("Inicio prueba: testValidarAccesoAtraccionCerrada...");

        atraccion.setEstado(EstadoAtraccion.CERRADA);
        boolean resultado = operador.validarAccesoCola(visitanteCumple, atraccion);
        assertFalse(resultado);

        LOG.info("Fin prueba: testValidarAccesoAtraccionCerrada");
    }

    // Prueba 4 - assertEquals / Funcionamiento correcto de un cambiarCapacidadAtraccion exitoso (coincide con los resultados esperados)

    @Test
    public void testCambiarCapacidadAtraccion() {
        LOG.info("Inicio prueba: testCambiarCapacidadAtraccion...");

        operador.cambiarCapacidadAtraccion(20, atraccion);
        assertEquals(20, atraccion.getCapacidadMaxima());

        LOG.info("Fin prueba: testCambiarCapacidadAtraccion");
    }

    // Prueba 5 - assertTrue / Funcionamiento correcto de un registrarEstadoAtraccion que invoca un registrarRevisionTecnica

    @Test
    public void testRegistrarEstadoAtraccion() {
        LOG.info("Inicio prueba: testRegistrarEstadoAtraccion...");

        boolean resultado = operador.registrarEstadoAtraccion(atraccion, 2, "Texto de ejemplo");
        assertTrue(resultado);

        LOG.info("Fin prueba: testRegistrarEstadoAtraccion");
    }

    // Prueba 6 - assertTrue / Funcionamiento correcto de un registrarRevisionTecnica que invoca un registrarEstadoAtraccion

    @Test
    public void testRegistrarRevisionTecnica(){
        LOG.info("Inicio prueba: testRegistrarRevisionTecnica...");

        boolean resultado = operador.registrarRevisionTecnica(atraccion, "Texto de ejemplo");
        assertTrue(resultado);

        LOG.info("Fin prueba: testRegistrarRevisionTecnica");
    }

    // Prueba 7 - assertTrue / Funcionamiento correcto de un finalizarRevisionTecnica que invoca un registrarEstadoAtraccion

    @Test
    public void testFinalizarRevisionTecnica(){
        LOG.info("Inicio prueba: testFinalizarRevisionTecnica...");

        operador.registrarRevisionTecnica(atraccion, "Texto de ejemplo");
        boolean resultado = operador.finalizarRevisionTecnica(atraccion, "Texto de ejemplo");
        assertTrue(resultado);

        LOG.info("Fin prueba: testFinalizarRevisionTecnica");
    }


}

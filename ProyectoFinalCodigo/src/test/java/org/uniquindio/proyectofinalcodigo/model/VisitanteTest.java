package org.uniquindio.proyectofinalcodigo.model;

/**
 * Clase de pruebas unitarias para la clase Visitante.
 * Verifica el manejo del saldo virtual y los datos del perfil.
 *
 * @author Gabriel Garcia Duque
 * @since 2026-05
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class VisitanteTest {

    private static final Logger LOG = Logger.getLogger(VisitanteTest.class.getName());

    private Parque parque;
    private Visitante visitante;

    // Crear Parque y Visitante base para pruebas

    @BeforeEach
    public void setUp() {
        parque = new Parque("Tech-Park UQ", "Calle 12 # 15-23", "Soleado", "123456789", "www.techparkuq.com", true, 500, 0, true, LocalDate.now(), null);

        visitante = new Visitante(200000, 22, false, parque, "1001111111", "Maria Gomez", LocalDate.of(2002, 8, 5), 1.60, "3111111111", "Colombiana");
    }

    // Prueba 1 - assertTrue / Funcionamiento correcto de un pagarConSaldoVirtual exitoso (devolviendo true)

    @Test
    public void testPagarConSaldoSuficiente() {
        LOG.info("Inicio prueba: testPagarConSaldoSuficiente...");

        boolean resultado = visitante.pagarConSaldoVirtual(50000);
        assertTrue(resultado);

        LOG.info("Fin prueba: testPagarConSaldoSuficiente");
    }

    // Prueba 2 - assertFalse / Funcionamiento correcto de un pagarConSaldoVirtual fallido (devolviendo false)

    @Test
    public void testPagarConSaldoInsuficiente() {
        LOG.info("Inicio prueba: testPagarConSaldoInsuficiente...");

        boolean resultado = visitante.pagarConSaldoVirtual(500000);
        assertFalse(resultado);

        LOG.info("Fin prueba: testPagarConSaldoInsuficiente");
    }

    // Prueba 3 - assertEquals / Funcionamiento correcto de registrar el perfil de un visitante (devolviendo el resultado esperado)


    @Test
    public void testRegistrarPerfil(){
        LOG.info("Inicio prueba: testRegistrarPerfil...");

        Visitante visitanteEsperado = new Visitante(0, 15, false, parque, "12345654321", "Jorge Castillos", LocalDate.of(2008, 7, 1), 1.60, "3111111111", "Colombiana");
        visitante.registrarPerfil(parque, "12345654321", "Jorge Castillos", LocalDate.of(2008, 7, 1), 1.60, "3111111111", "Colombiana", 15);
        assertEquals(visitanteEsperado.toString(), parque.getListPersonas().get(parque.getListPersonas().size()-1).toString());

        LOG.info("Fin prueba: testRegistrarPerfil");
    }


    // Prueba 4 - assertEquals / Funcionamiento correcto de actualizar el perfil de un visitante (devolviendo el resultado esperado)

    @Test
    public void testActualizarPerfil(){
        LOG.info("Inicio prueba: testActualizarPerfil...");

        Visitante visitanteEsperado = new Visitante(0, 15, false, parque, "12345654321", "Jorge Castillos", LocalDate.of(2008, 7, 1), 1.60, "3111111111", "Colombiana");
        visitante.registrarPerfil(parque, "12345654321", "NO SOY Jorge Castillos", LocalDate.of(2008, 7, 1), 1.80, "3111111111-01", "NO Colombiana", 20);
        ((Visitante) parque.getListPersonas().get(parque.getListPersonas().size()-1)).actualizarPerfil("Jorge Castillos", 1.60, "3111111111", "Colombiana", 15);
        assertEquals(visitanteEsperado.toString(), parque.getListPersonas().get(parque.getListPersonas().size()-1).toString());

        LOG.info("Fin prueba: testActualizarPerfil");
    }

    // Prueba 5 - assertTrue / Funcionamiento correcto de una compra de Tiquetes exitosa (devolviendo true)

    @Test
    public void testComprarTickets() {
        LOG.info("Inicio prueba: testComprarTickets...");

        String cedula = visitante.getCedula();
        boolean resultado = visitante.comprarTickets(parque, cedula, 2);
        assertTrue(resultado);

        LOG.info("Fin prueba: testComprarTickets");
    }


}

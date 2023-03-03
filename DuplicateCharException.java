/*
 * Autor: Pepe Calo
 * Realizado con fines educativos.
 * Puede modificarlo siempre que no lo haga con fines comerciales.
 */
package com.pepinho.programacion.sudoku;

/**
 * Excepción que se lanza cuando un alfabeto tiene un carácter duplicado. NO SE
 * PIDE PARA LA TAREA. Tiene un único atributo, caracter, de tipo char, que
 * representa el carácter que está duplicado (podría guardarse una lista de
 * caracteres).
 *
 * @author pepecalo
 * @see java.lang.Exception
 */
public class DuplicateCharException extends Exception {

    /**
     * No se pide.
     * <a href="https://www.baeldung.com/java-serial-version-uid">What is the
     * serialVersionUID?</a>
     *
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Carácter que ha lanzado la excepción. Se trata de un carácter que tiene
     * valor duplicado en el Alfabeto.
     */
    private char caracter;

    /**
     * Construye la excepción llamando al constructor de la clase padre,
     * Exception, con el mensaje "El alfabeto tienen caracteres duplicados."
     */
    public DuplicateCharException() {
        super("El alfabeto tienen caracteres duplicados.");
    }

    /**
     * Construye la excepción llamando al constructor de la clase padre,
     * Exception, con el mensaje "El alfabeto tienen caracteres duplicados." y
     * asigna el carácter al atributo caracter.
     *
     * @param caracter tamaño que ha lanzado la excepción.
     */
    public DuplicateCharException(char caracter) {
        super("El alfabeto tienen caracteres duplicados.");
        this.caracter = caracter;
    }

    /**
     * Devuelve el carácter que ha lanzado la excepción. Se trata de un carácter
     * que tiene duplicados en el Alfabeto de Sudoku.
     *
     * @return caácter que ha lanzado la excepción.
     */
    public int getCaracter() {
        return caracter;
    }

}

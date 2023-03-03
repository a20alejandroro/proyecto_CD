/*
 * Autor: Pepe Calo
 * Realizado con fines educativos.
 * Puede modificarlo siempre que no lo haga con fines comerciales.
 */
package com.pepinho.programacion.sudoku;

/**
 * Excepción que se lanza cuando un alfabeto no tiene el tamaño adecuado. Tiene
 * un único atributo, tamanho, de tipo int, que representa el tamaño que ha
 * lanzado la excepción y no es correcto.
 *
 * @author pepecalo
 * @see java.lang.Exception
 */
public class AlfabetoSizeException extends Exception {

    /**
     * No se pide.
     * <a href="https://www.baeldung.com/java-serial-version-uid">What is the
     * serialVersionUID?</a>
     *
     *
     */
    private static final long serialVersionUID = -2729948888285226613L;
    /**
     * Tamaño que ha lanzado la excepción. Se trata de un tamaño no válido (no
     * tiene un valor positivo que sea un cuadrado perfecto).
     */
    private int tamanho;

    /**
     * Construye la excepción llamando al constructor de la clase padre,
     * Exception, con el mensaje "El tamaño del alfabeto no es correcto."
     */
    public AlfabetoSizeException() {
        super("El tamaño del alfabeto no es correcto.");
    }

    /**
     * Construye la excepción llamando al constructor de la clase padre,
     * Exception, con el mensaje "El tamaño del alfabeto no es correcto." y
     * asigna el tamaño al atributo tamanho.
     *
     * @param tamanho tamaño que ha lanzado la excepción.
     */
    public AlfabetoSizeException(int tamanho) {
        super("El tamaño del alfabeto no es correcto.");
        this.tamanho = tamanho;
    }

    /**
     * Devuelve el tamaño que ha lanzado la excepción. Se trata de un tamaño no
     * válido para un alfabeto de Sudoku.
     *
     * @return tamaño no correcto que ha lanzado la excepción.
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Devuelve el mensaje de error indicando el tamaño que ha lanzado la excepción.
     * @return Mensaje de la excepción y el tamaño que ha lanzado la excepción.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + ". Tamaño: " + tamanho; 
    }

}

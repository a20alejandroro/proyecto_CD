/*
 * Autor: Pepe Calo
 * Realizado con fines educativos.
 * Puede modificarlo siempre que no lo haga con fines comerciales.
 */
package com.pepinho.programacion.sudoku;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Un Sudoku está definido por un alfabeto. Usualmente tiene un alfabeto formado
 * por caracteres numéricos del 1 al 9, pero pueden ser cualquier carácter y
 * tener otro tamaño diferente.<p>
 * El tamaño más usual del Sudoku es de 9x9, pero puede ser cualquier tamaño que
 * sea una raíz perfecta. Ese tamaño debe ser el del alfabeto.</p>
 * Por ejemplo:
 * <pre>
 * 4 (2x2), alfabeto de 4 caracteres.
 * 9 (3x3), alfabeto de 9 caracteres.
 * 16 (4x4), alfabeto de 16 caracteres.
 * ...
 * </pre>
 *
 * La clase alfabeto representa el alfabeto de un sudoku. Usualmente consta de 9
 * números {1,2,3,..., 9}, pero hay sudokus de 16 caracteres (4x4) o mayor
 * tamaño.
 * <p>
 * <strong>Tiene un único atributo privado: alfabeto, que representa un array de
 * caracteres.</strong>
 * </p>
 *
 * @version 1.0.1 %I%
 * @author pepecalo
 * @see <a href="https://es.wikipedia.org/wiki/Sudoku">Sudoku</a>
 */
public class Alfabeto {

    /**
     * Tamaño por defecto del alfabeto en el caso de que no se especifique.
     *
     */
    public static final int DEFAULT_SIZE = 9;

    /**
     * Alfabeto por defecto como array de caracteres: {'1',..., '9'}
     */
    public static final char[] DEFAULT_CHARS = {'1', '2', '3', '4', '5',
        '6', '7', '8', '9'}; // O "123456789".toCharArray(); 

    /**
     * Alfabeto creado con los caracteres por defecto, DEFAULT_CHARS. Está
     * versión captura una excepción, pues el constructor que recoge un array de
     * caracteres puede lanzar una excepción de tipo
     * {@link AlfabetoSizeException}. En este caso se precisa un bloque estático
     * para capturar la excepción pues no es posible encerrar la variable entre
     * try{}catch(){}:
     * <pre>
     *
     * static {
     * Alfabeto tmp;
     * try {
     * tmp = new Alfabeto(DEFAULT_CHARS);
     * } catch (AlfabetoSizeException e) {
     * tmp = new Alfabeto();
     * }
     * DEFAULT_ALPHABET = tmp;
     * }
     * </pre>
     *
     * No se usa en esta clase, pero sí en la aplicación.
     *
     * @see #DEFAULT_CHARS
     * @see AlfabetoSizeException
     */
    public static final Alfabeto DEFAULT_ALPHABET;

    static {
        Alfabeto tmp;
        try {
            tmp = new Alfabeto(DEFAULT_CHARS);
        } catch (AlfabetoSizeException | DuplicateCharException e) {
            tmp = new Alfabeto();
        }
        DEFAULT_ALPHABET = tmp;
    }

//    /**
//     * Versión sin excepciones.      
//     * Alfabeto creado con los caracteres por defecto, DEFAULT_CHARS.
//     * No se usa en esta clase.
//     * @see #DEFAULT_CHARS 
//     */    
//    public static final Alfabeto DEFAULT_ALPHABET = new Alfabeto(DEFAULT_CHARS);
    /**
     * Alfabeto como un array de tipo char.
     */
    private char[] alfabeto;

    /**
     * Crea un alfabeto con el array pasado por argumento. ESta versión lanza
     * una excepción cuanod no se ajusta al tamaño.
     *
     * @param alfabeto alfabeto del sudoku como array de caracteres.
     * @throws AlfabetoSizeException excepción lanzada cuando el alfabeto es
     * nulo o no tiene un tamaño adecuado.
     * @throws com.pepinho.programacion.sudoku.DuplicateCharException se lanza
     * cuando el alfabeto tiene valores duplicados.
     */
    public Alfabeto(char[] alfabeto) throws AlfabetoSizeException,
            DuplicateCharException {

        if (alfabeto == null || !hasCorrectSize(alfabeto)) {
            throw new AlfabetoSizeException((alfabeto != null) ? alfabeto.length : 0);
        }
        char c;
        if ((c = hasDuplicates(alfabeto)) != Character.SPACE_SEPARATOR) {
            throw new DuplicateCharException(c);
        }
        this.alfabeto = alfabeto;

    }

//    /**
//     * Versión sin excepciones.
//     * Crea un alfabeto con el array pasado por argumento.
//     * Si array de caracteres (parámetro alfabeto) es nulo o no tienen el 
//     * tamaño adecuado asigna al alfabeto al alfabeto los caracteres por defecto.
//     *
//     * @param alfabeto alfabeto del sudoku como array de caracteres.
//     * @see #DEFAULT_CHARS
//     */
//    public Alfabeto(char[] alfabeto) {
//        if (alfabeto == null || !hasCorrectSize(alfabeto)) {
//            alfabeto = DEFAULT_CHARS;
//        }
//        this.alfabeto = alfabeto;
//    }
    /**
     * Crea un alfabeto con los caracteres por defecto {@link #DEFAULT_CHARS}
     *
     * @see #DEFAULT_CHARS
     */
    public Alfabeto() {
        alfabeto = DEFAULT_CHARS;
    }

    /**
     * Crea un alfabeto con el tamaño recogido por argumento Llama al
     * constructor que recoge un array de caracteres.
     *
     * @param tamanho tamaño del alfabeto.
     * @throws com.pepinho.programacion.sudoku.AlfabetoSizeException Excepción
     * cuando el tamaño no es correcto.
     * @throws com.pepinho.programacion.sudoku.DuplicateCharException Excepción 
     * lanzada cuando tiene valores duplicados.
     */
    public Alfabeto(int tamanho) throws AlfabetoSizeException, 
            DuplicateCharException {
        this(new char[tamanho]);
    }

    /**
     * Comprueba si el tamaño del alfabeto es un cuadrado perfecto. <br>
     * Antes comprueba que no sea nulo y que la longitud del array sea distinta de cero.
     * En ese caso devuelve <code>false</code>.
     * Por ejemplo, son sólo válidos tamaño de alfabeto: 1, 4 (2*2), 9 (3*3), 
     * 16 (4*4), etc. Si el alfabeto es
     * nulo o la longitud es cero devuelve <code>false</code>. En caso contrario
     * comprueba que el tamaño sea un raíz perfecta. Esto es, que la raíz
     * cuadrada del tamaño sea un número entero.
     *
     * @return verdadero si el tamaño del alfabeto es un cuadrado perfecto.
     */
    public final boolean hasCorrectSize() {
        if (alfabeto == null || alfabeto.length == 0) {
            return false;
        }
        double raiz = Math.sqrt(alfabeto.length);
        return raiz == (int) raiz;
    }

    /**
     * Versión estática del método {@link #hasCorrectSize() }. Comprueba si el
     * tamaño del alfabeto recogido por argumento es un cuadrado perfecto.<br>
     * Antes comprueba que no sea nulo y que la longitud sea distinta de cero.
     * En ese caso devuelve <code>false</code>.
     *
     * Por ejemplo, son sólo válidos tamaño de alfabeto: 1, 4 (2*2), 9 (3*3), 16
     * (4*4), etc. Si el alfabeto es nulo o la longitud es cero devuelve
     * <code>false</code>. En caso contrario comprueba que el tamaño sea un raíz
     * perfecta. Esto es, que la raíz cuadrada del tamaño sea un número entero.
     *
     * @param alfabeto array del que quiero comprobar el tamaño.
     * @return verdadero si el tamaño del alfabeto es un cuadrado perfecto.
     */
    public static final boolean hasCorrectSize(char[] alfabeto) {
        if (alfabeto == null || alfabeto.length == 0) {
            return false;
        }
        double raiz = Math.sqrt(alfabeto.length);
        return raiz == (int) raiz;
    }

    /**
     * Devuelve el tamaño del alfabeto. Si es nulo devuelve 0. Aclaración:
     * emplea operador terciario.
     *
     * @return tamaño del alfabeto o 0 si el alfabeto es nulo.
     */
    public int getSize() {
		// COMPLETA: si el alfabeto es null devuelve 0. En caso contrario devuelve el tamaño. 
        return 0;
    }

    /**
     * Devuelve el elemento que está en la posición i. Si el alfabeto es nulo,
     * el índice es menor que cero o el índice es mayor que el número de
     * elementos del array debe devolver 0. Aclaración: emplea operador
     * terciario.
     *
     * @param i índice del elemento del alfabeto
     * @return elemento que está en la posición i. 0 en el caso de que el
     * alfabeto sea nulo o el valor pasado por parámetro no sea correcto.
     */
    public char getElementAt(int i) {
		// COMPLETA: devolve o carácter que se está en esa posición. Comprueba nulos.
        return 0;
    }

    /**
     *
     * Devuelve si el alfabeto tiene caracteres duplicados. Versión tradicional,
     * con doble bucle. Comprueba que el alfabeto no sea nulo y que el tamaño
     * sea mayor que cero. Si la comparación encuentra que hay dos iguales
     * devuelve false. Se precisa de un bucle que recorra el array de caracteres
     * del alfabeto y, para cada uno, compararlo con lo siguientes (bucle dentro
     * de otro). Si el bucle externo tiene un índice <code>i</code> y el interno
     * un índice <code>j</code>, <code>i</code> va hasta el tamaño y
     * <code>j</code> empieza en <code>i+1</code> hasta el final. Se realiza una
     * comparación de cada par de elementos. Si hay dos iguales devuelve
     * <code>false</code>
     *
     * @return si tiene duplicados o no.
     */
    public boolean hasDuplicates() {
        boolean tenDuplicados = false;
        // // COMPLETA
        return tenDuplicados;
    }

    /**
     * Versión estática del método {@link #hasDuplicates() }
     * NO SE PIDE, pero se precisa si se quiere emplear excepciones...
     * Devuelve si el alfabeto tiene caracteres duplicados.Versión tradicional,
     * con doble bucle. Comprueba que el alfabeto no sea nulo y que el tamaño
     * sea mayor que cero. Si la comparación encuentra que hay dos iguales
     * devuelve false. Se precisa de un bucle que recorra el array de caracteres
     * del alfabeto y, para cada uno, compararlo con lo siguientes (bucle dentro
     * de otro). Si el bucle externo tiene un índice <code>i</code> y el interno
     * un índice <code>j</code>, <code>i</code> va hasta el tamaño y
     * <code>j</code> empieza en <code>i+1</code> hasta el final. Se realiza una
     * comparación de cada par de elementos. Si hay dos iguales devuelve
     * <code>false</code>
     *
     * @param alfabeto array de caracteres a comprobar.
     * @return si tiene duplicados o no.
     */
    public static char hasDuplicates(char[] alfabeto) {
        // COMPLETA: si no sabes, no lo uses.
        return Character.SPACE_SEPARATOR;
    }

    /**
     * No se pide. Desde Java 8, podemos hacer uso de streams ({@link Stream#of(java.lang.Object...)
     * }) para contar distintos elementos que tiene un array. Si el número de
     * elementos no es el mismo que la longitud del array, entonces tiene
     * valores duplicados. En esta versión se realiza con genéricos. Puede
     * sustituirse la T por el tipo deseado. Es una versión estática del método {@link #hasDuplicates()
     * }. Si se quiere un método de instancia, simplemente se elimina la palabra
     * "static" y que no recoja ningún argumento.
     * <strong>No se pide. No por ahora</strong>
     *
     * @param <T> Tipo de elementos del array a comprobar (char en nuestro caso)
     * @param alfabeto array con los elementos a comprobar
     * @return verdadero si tiene duplicados.
     */
    public static <T> boolean hasDuplicatesV1(T... alfabeto) {
        Long numeroDistintos = Stream.of(alfabeto).distinct().count();
        return alfabeto.length != numeroDistintos;
    }

    // o
    /**
     * No se pide. Versión 2 del uso de flujos de la clase Arrays Si el número
     * de elementos no es el mismo que la longitud del array, entonces tiene
     * valores duplicados. En esta versión se realiza con genéricos. Puede
     * sustituirse la T por el tipo deseado. Es una versión estática del método {@link #hasDuplicates()
     * }. Si se quiere un método de instancia, simplemente se elimina la palabra
     * "static" y que no recoja ningún argumento.
     * <strong>No se pide. Por ahora</strong>
     *
     * @param <T> Tipo de elementos del array a comprobar
     * @param alfabeto array con los elementos a comprobar
     * @return verdadero si tiene duplicados.
     */
    public static <T> boolean hasDuplicatesV2(final T[] alfabeto) {
        return Arrays.stream(alfabeto).distinct().count() != alfabeto.length;
    }

    /**
     * Devuelve si el alfabeto es válido. Esto es, si no es un array nulo, tiene
     * un tamaño adecuado {@link hasCorrectSize()} y no tiene duplicados {@link #hasDuplicates()
     * }. En realidad, el control del tamaño se hace al crear el objeto, por lo
     * que no sería necesario comprobar el tamaño.
     *
     * @return si es un alfabeto válido (no nulo, de tamaño correcto y sin
     * dublicados)
     * @see #hasCorrectSize()
     * @see #hasDuplicates()
     */
    public boolean isValid() {
		// COMPLETA: si es distinto de null y tiene el tamaño adecuado y sin duplicados.
        return true;
    }

    /**
     * Indica si un carácter está en el alfabeto. Debe realizarse una búsqueda
     * elemento a elemento dentro de los caracteres que tiene el alfabeto.
     *
     * @param c carácter a consultar.
     * @return si el alfabeto contiene o no el carácter buscado.
     */
    public boolean isInAlphabet(char c) {
        boolean contiene = false;
        // COMPLETA
        return contiene;
    }

    /**
     * Devuelve los caracteres del alfabeto
     *
     * @return array de caracteres del alfabeto.
     */
    public char[] getAlfabeto() {
        return alfabeto;
    }

    /**
     * Asigna el alfabeto.
     *
     * @param alfabeto alfabeto del sudoku como array de caracteres.
     * @throws com.pepinho.programacion.sudoku.AlfabetoSizeException excepción
     * lanzada cuando el alfabeto es nulo o no tiene un tamaño adecuado.
     */
    public void setAlfabeto(char[] alfabeto) throws AlfabetoSizeException {
        // COMPLETA que se ajusta.
        this.alfabeto = alfabeto;
    }

    /**
     * El método toString() sobreescribe al método de Object. De este modo 
     * personalizamos el comportamiento de la impresión del objeto cuando 
     * lo concatenamos con una cadena o lo queremos imprimir.
     * Cuando Java concatena un objeto con una cadena o precisa una conversión
     * de modo implícito a una cadena de texto, se llamda de modo automático 
     * al método toString() de Object (recordad que <strong>todas las clases
     * heredan de Object</strong>, directa o indirectamente.
     * Por ejemplo:
     * <pre>System.out.println(objeto)</pre> es equivalente a
     * <pre>System.out.println(objeto.toString())</pre> Devuelve la cadena que
     * representa al alfabeto. Un ejemplo que podría devolver este método sería:
     * <code>Alfabeto [1, 2, 3, 4, 5, 6, 7, 8, 9]</code>
     * <P>
     * <em>Nota: debe emplearse, como las cadenas son inmutables y por temas de
     * eficiencia, la clase StringBuilder. Esta clase crea cadenas que pueden
     * cambiar su tamaño y se concatena con el método "append(...)". Para
     * convertir un StringBuilder a cadena se usa el método "toString()".</em>
     *
     * @return La representación del objeto como cadena.
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // COMPLETA: concatena.
        return '[' + sb.toString() + ']';
    }
}

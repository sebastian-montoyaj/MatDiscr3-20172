package Tarea3;

// Importes necesarios para la clase
import java.util.Arrays;
import java.util.Random;

// Clase cuyo proprosito es la respresentacion de un polinomio
// NOTA:
// 1. Los datos son almacenados en un arreglo y cada coeficiente del polinomio es accedido por su indice numerico.
// 2. Coeficientes mas grandes dentro del polinomio seran almacenados en posiciones mas grandes del arreglo.
public class Polynomial
{
    private long[] elements; // Variable para almacenar los coeficientes del polinomio
    private int size; // Variable para almacenar el tamaño del polinomio
    
    // Constructor de un polinomio vacio
    public Polynomial()
    {
        elements = new long[0];
        size = 0;
    }
    
    // Constructor de un polinomio de tamaño especificado, donde todos los coeficientes inicialmente seran puestos como cero.
    public Polynomial(int size)
    {
        elements = new long[size];
        this.size = size;
    }
    
    // Constructor de un polinomio a partir de uno ya creado
    public Polynomial(Polynomial polynomial)
    {
        this(polynomial.getSize());
        for (int x = 0; x < size; x++)
        {
            elements[x] = polynomial.getElement(x);
        }
    }

    // Constructor que crea el polinomio a partir de una arreglo que ya tiene los coeficientes
    public Polynomial(long[] polynomial)
    {
        size = polynomial.length;
        for (int x = 0; x < size; x++)
        {
            elements[x] = polynomial[x];
        }
    }
    
    // Constructor de un polinomio, en el cual se especifica su tamaño.
    // Donde el coeficiente mas alto es un numero positivo diferente de cero y los demas coeficientes estaran aleatoriamente entre 0 y and 2^bitSize - 1
    public Polynomial(int size, int bitSize) {
        this(size);
        if (bitSize < 1)
        {
            return;
        }

        Random rn = new Random();

        for (int x = 0; x < size - 1; x++)
        {
            elements[x] = Math.abs(rn.nextLong()) & generateBitMask(bitSize);
        }

        long value = 0;
        do
        {
            elements[size - 1] = (Math.abs(rn.nextLong()) & generateBitMask(bitSize));
        } while (elements[size - 1] == 0);
    }
    
    // Metodo para establecer el coeficiente dado en el indice especificado
    public void setElement(int index, long value)
    {
        elements[index] = value;
    }
    
    // Metodod para obtener el coeficiente del polinomio en el indice especificado
    public long getElement(int index)
    {
        return elements[index];
    }
    
    // Metodo para establecer el tamaño del polinomio
    // Todos los coeficientes seran puestos en cero
    public void setSize(int size)
    {
        this.size = size;
        elements = new long[size];
    }
    
    // Metodo para obtener el tamaño del polinomio (o sea, su grado - 1)
    public int getSize()
    {
        return size;
    }
    
    // Metodo para limpiar el contenido del polinomio, osea, se eliminan todos los coeficientes y el grado del polinomio es cambiado (vuelve a cero)
    public Polynomial clearZeroValues()
    {
        long[] newElements;
        for (int x = size - 1; x >= 0; x--)
        {
            if (elements[x] != 0)
            {
                newElements = new long[x + 1];
                for (int y = 0; y < x + 1; y++)
                {
                    newElements[y] = elements[y];
                }
                size = x + 1;
                return this;
            }
        }
        size = 0;
        elements = new long[0];
        return this;
    }
    
    // Metodo para obtener la representacion del polinomio en cuestion
    // El retorno de este metodo es de la forma: (a + bx^1 + cx^2 ... zx^n)
    public String toStringAsPoly()
    {
        String result = new String();

        result += "[";
        if (size > 0)
        {
            result += elements[0] + " + ";
        }
        
        for (int x = 1; x < size; x++)
        {
            if (x == size - 1)
            {
                result += elements[x] + "x^" + x;
            }
            else
            {
                result += elements[x] + "x^" + x + " + ";
            }
        }
        result += "]";
        return result;
    }
    
    // Metodo para generar la representacion binaria del numero dado
    private long generateBitMask(int length)
    {
        int result = 0;
        for (int x = 0; x < length; x++)
        {
            result ^= (1 << x);
        }
        return result;
    }
    
    // Los siguientes tres metodos son para la comparacion de objetos polinomio
    @Override
    public boolean equals(Object polynomial) {
        if (!(polynomial instanceof Polynomial)) {
            return false;
        }

        if (((Polynomial) polynomial).getSize() != size) {
            return false;
        }

        for (int x = 0; x < size; x++) {
            if (elements[x] != ((Polynomial) polynomial).getElement(x)) {
                return false;
            }
        }

        return true;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + Arrays.hashCode(this.elements);
        return hash;
    }

    @Override
    public String toString()
    {
        String result = new String();

        result += "[ ";
        for (int x = 0; x < size; x++)
        {
            if (x == size - 1)
            {
                result += elements[x];
            }
            else
            {
                result += elements[x] + ", ";
            }
        }
        result += " ]";

        return result;
    }
}

package Tarea3;

// Importe necesario para la clase
import java.util.ArrayList;

// Clase encargada de implementar los metodos para operar campos finitos binarios
public class GF2N
{
    // Atributos para almacenar las caracteristicas del campo y facilitar las operaciones entre los elementos del mismo
    private static final long[] BINARY_POWERS = {1L, 2L, 4L, 8L, 16L, 32L, 64L, 128L, 256L, 512L, 1024L, 2048L,
        4096L, 8192L, 16384L, 32768L, 65536L, 131072L, 262144L, 524288L, 1048576L, 2097152L, 4194304L, 8388608L,
        16777216L, 33554432L, 67108864L, 134217728L, 268435456L, 536870912L, 1073741824L, 2147483648L,
        4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L,
        549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L,
        35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L,
        2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L,
        72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, 1152921504606846976L,
        2305843009213693952L, 4611686018427387904L, 9223372036854775807L};
    private long reducingPolynomial;
    private short fieldSize;
    
    // Constructor del campo.
    // Parametro - reducingPolynomial: Este polinomio es usado como el polinomio carasteristico del campo y es el que define las operaciones que se pueden efectuar.
    public GF2N(long reducingPolynomial) throws Exception
    {
        if (reducingPolynomial <= 0)
        {
            throw new Exception("Reducing polynomial must be represented by positive number.");
        }

        this.reducingPolynomial = reducingPolynomial;
        fieldSize = countBinarySize(reducingPolynomial);
    }
    
    // Metodo para sumar dos elementos del campo
    public long add(long element1, long element2) throws Exception
    {
        isInField(element1, element2);
        return element1 ^ element2;
    }
    
    // Metodo para restar dos elementos del campo
    public long subtract(long element1, long element2) throws Exception
    {
        isInField(element1, element2);
        return element1 ^ element2;
    }
    
    // Metodo para multiplicar dos elementos del campo
    public long multiply(long element1, long element2) throws Exception
    {
        isInField(element1, element2);

        long result = 0;
        long element2Actual = element2;

        while (element2Actual != 0)
        {
            long actualResult = element1;
            int actualElement2Size = countBinarySize(element2Actual);

            for (int x = 0; x < actualElement2Size; x++)
            {
                actualResult <<= 1;
                if (actualResult > (BINARY_POWERS[fieldSize] - 1))
                {
                    actualResult ^= reducingPolynomial;
                }
            }
            element2Actual ^= BINARY_POWERS[countBinarySize(element2Actual)];
            result ^= actualResult;
        }

        return result;
    }
    
    // Metodo para dividir dos elementos del campo
    public long divide(long element1, long element2) throws Exception
    {
        isInField(element1, element2);
        if (element2 == 0)
        {
            throw new Exception("Division by zero.");
        }
        
        return (multiply(element1, invert(element2)));
    }
    
    // Metodo para encontrar el inverso del elemento definido o dado en los parametros
    public long invert(long element) throws Exception
    {
        isInField(element);

        if (element == 1)
        {
            return 1;
        }

        if (element == 0)
        {
            throw new Exception("Cannot find inverse for ZERO.");
        }

        // Preparandose para la division
        long remainder = element;
        long numerator = reducingPolynomial;
        long denumerator = element;

        // Preparandose para encontrar la identidad de Bezout
        long temp = 0;
        ArrayList<Long> resultList = new ArrayList<>();
        ArrayList<Long> bezoutIdentity = new ArrayList<>();
        
        // Encontrando el maximo comun divisor, ultimo resto positivo
        while (remainder != 0)
        {
            short numeratorBinarySize = countBinarySize(numerator);
            short denumeratorBinarySize = countBinarySize(denumerator);
            long tempNumerator = numerator;
            
            // division binaria del polinomio
            long result = 0;
            while (numeratorBinarySize >= denumeratorBinarySize)
            {
                temp = 1;
                temp <<= (numeratorBinarySize - denumeratorBinarySize);
                result ^= temp;
                tempNumerator ^= (denumerator << (numeratorBinarySize - denumeratorBinarySize));

                numeratorBinarySize = countBinarySize(tempNumerator);
                denumeratorBinarySize = countBinarySize(denumerator);
            }
            remainder = tempNumerator;

            if (remainder == 0 && denumerator != 1)
            {
                throw new Exception("Cannot compute inverse" + " for this element.");
            }
            
            // Informacion de resultList que es necesaria para encontrar la identidad de Bezout
            resultList.add(numerator);
            resultList.add(denumerator);
            resultList.add(result);

            numerator = denumerator;
            denumerator = remainder;
        }

        if (resultList.size() > 3)
        {
            // No son necesarios los ultimos 3 valores
            resultList.remove(resultList.size() - 1);
            resultList.remove(resultList.size() - 1);
            resultList.remove(resultList.size() - 1);
        }

        bezoutIdentity.add(1l);
        bezoutIdentity.add(resultList.get(resultList.size() - 3));
        bezoutIdentity.add(resultList.get(resultList.size() - 2));
        bezoutIdentity.add(resultList.get(resultList.size() - 1));
        
        // Encontrando la identidad de Bezout a partir de la informacion de resultList en el algortimo Euclidiano
        while (resultList.size() != 3)
        {
            resultList.remove(resultList.size() - 1);
            resultList.remove(resultList.size() - 1);
            resultList.remove(resultList.size() - 1);

            temp = bezoutIdentity.get(0);
            bezoutIdentity.set(0, bezoutIdentity.get(3));
            bezoutIdentity.set(1, resultList.get(resultList.size() - 3));
            bezoutIdentity.set(2, resultList.get(resultList.size() - 2));
            bezoutIdentity.set(3, add(temp, multiply(bezoutIdentity.get(3), resultList.get(resultList.size() - 1))));
        }
        
        // Finalmente, se retona el inverso si se encontro
        return bezoutIdentity.get(3);
    }
    
    // Eleva al cuadrado y multiplica para calcular la potencia
    public long power(long element, long exponent) throws Exception
    {
        isInField(element);

        if (exponent < 0)
        {
            throw new Exception("Cannot compute power with negative exponent!");
        }

        if (exponent == 0 && element != 0)
        {
            return 1l;
        }

        if (exponent == 0 && element == 0)
        {
            return 0l;
        }

        long result = 1;
        long a = element;
        long x = exponent;

        while (x != 1)
        {
            if ((x % 2) == 1)
            {
                result = multiply(result, a);
            }
            x /= 2;
            a = multiply(a, a);
        }

        return multiply(result, a);
    }
    
    // Metodo estatico para determinar si el polinomio dado es irreducible o no.
    // El retorno es verdadero SI el polinomio es irreducible, falso de lo contrario.
    public static boolean isIrreducible(long polynomial) throws Exception
    {
        // En caso que el polinomio sea negativo entonces se lanza de inmediato una excepcion porque un polinomio binario solo puede ser positivo
        if (polynomial <= 0)
        {
            throw new Exception("Polynomial must be represented by positive number.");
        }
        
        // Si el polinomio es 2 (O sea, x) entonces si es reducible por lo que se retorna falso
        if (polynomial == 2)
        {
            return false;
        }
        
        // Si el polinomio es muy grande entonces se lanza una excepcion porque ya es muy dificil operar con un polinomio de mas de 30 componentes
        if (countBinarySize(polynomial) > 30)
        {
            throw new Exception("Cannot test irreducibility for such a big number.");
        }
        
        // Si pasa las validaciones anteriores entonces se crea la representacion del polinomio a partir del numero(Long) ingresado
        Polynomial poly = new Polynomial((int) countBinarySize(polynomial) + 1);
        int degree = poly.getSize() - 1;
        long value = polynomial;
        
        for (int x = 0; x < poly.getSize(); x++)
        {
            if ((value & 1) == 1)
            {
                poly.setElement(x, 1);
            }
            else
            {
                poly.setElement(x, 0);
            }
            value >>= 1;
        }

        PolynomialGF2N polyGF = new PolynomialGF2N(3);
        long[] primes = new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61};
        ArrayList<Long> divisors = new ArrayList<>();
        
        // Se buscan los divisores primos del mismo grado del polinomio
        for (int x = 0; primes[x] <= degree; x++)
        {
            if (degree % primes[x] == 0)
            {
                divisors.add(primes[x]);
            }
        }
        
        // Se divide el grado por todos los divisores primos
        for (int x = 0; x < divisors.size(); x++)
        {
            divisors.set(x, (long) (degree / divisors.get(x)));
        }
        
        for (int x = 0; x < divisors.size(); x++)
        {
            // Se prepara tempPoly como:  x^(2^divisors(x)) + x
            Polynomial tempPoly = new Polynomial((int) Math.pow(2, divisors.get(x)) + 1);
            tempPoly.setElement(1, 1);
            tempPoly.setElement(tempPoly.getSize() - 1, 1);

            Polynomial remainder = new Polynomial(tempPoly.getSize());
            polyGF.divide(tempPoly, poly, remainder);
            
            // Se calcula el resto: remainder = tempPoly % poly
            remainder.clearZeroValues();
            
            // El maximo comun divisor debe ser 1 para cada polinomio irreducible
            Polynomial gcd = polyGF.gcd(poly, remainder);
            if (!(gcd.getSize() == 1 && gcd.getElement(0) == 1))
            {
                return false;
            }
        }

        // Se clacula tempPoly como: x^(2^degree) + x
        Polynomial tempPoly = new Polynomial((int) Math.pow(2, degree) + 1);
        tempPoly.setElement(1, 1);
        tempPoly.setElement(tempPoly.getSize() - 1, 1);

        // Se calcula el resto: remainder = tempPoly % poly
        Polynomial remainder = new Polynomial(tempPoly.getSize());
        polyGF.divide(tempPoly, poly, remainder);
        remainder.clearZeroValues();

        // tempPoly % poly debe ser 1 para cada polinomio irreducible
        if (remainder.getSize() == 0)
        {
            return true;
        }
        
        return false;
    }
    
    // Metodo para obtener la representacion polinomica de un polinomio el cual originalmente esta como un numero
    public static String obtenerExpresionPolinomio(long polinomio)
    {
        if (polinomio <= 0)
        {
            return "[0 + ]";
        }
        
        Polynomial poly = new Polynomial((int) countBinarySize(polinomio) + 1);
        int degree = poly.getSize() - 1;
        long value = polinomio;
        
        for (int x = 0; x < poly.getSize(); x++)
        {
            if ((value & 1) == 1)
            {
                poly.setElement(x, 1);
            }
            else
            {
                poly.setElement(x, 0);
            }
            value >>= 1;
        }
        
        return poly.toStringAsPoly();
    }
    
    // Metodo para obtener el polinomio reductir del campo
    public long getReducingPolynomial()
    {
        return reducingPolynomial;
    }
    
    // Metodo para establecer el polinomio reductor del campo
    public void setReducingPolynomial(long reducingPolynomial) throws Exception
    {
        if (reducingPolynomial <= 0)
        {
            throw new Exception("Reducing polynomial must be represented by positive number.");
        }

        this.reducingPolynomial = reducingPolynomial;
        fieldSize = countBinarySize(reducingPolynomial);
    }
    
    // Metodo para obtener el tamaño del campo
    public short getFieldSize()
    {
        return fieldSize;
    }
    
    // Metodo auxiliar que sirve para calcular el tamaño en bianrio del valor de entrada
    public static short countBinarySize(long value)
    {
        short result = -1;

        while (value != 0)
        {
            value >>= 1;
            result++;
        }
        
        return result;
    }
    
    // Metodo para determinar si el elemento en cuestion pertence al campo
    private void isInField(long element) throws Exception
    {
        if ((element >= BINARY_POWERS[fieldSize]) || (element < 0))
        {
            throw new Exception("Values for this reducing polynomial must be in [0, " + (BINARY_POWERS[fieldSize] - 1) + "].");
        }
    }
    
    // Metodo para determinar si la operacion de dos elementos del campo quedara dentro del mismo campo
    private void isInField(long element1, long element2) throws Exception
    {
        if ((element1 >= BINARY_POWERS[fieldSize]) || (element2 >= BINARY_POWERS[fieldSize]) || (element1 < 0) || (element2 < 0))
        {
            throw new Exception("Values for this reducing polynomial must be in [0, " + (BINARY_POWERS[fieldSize] - 1) + "].");
        }
    }
}
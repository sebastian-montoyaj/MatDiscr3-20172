package Tarea3;

// Importe necesario para la clase
import java.util.ArrayList;

// Clase de apoyo a GF2N y la cual sirve para realizar la representacion polinomial de los elementos de un campo
public class PolynomialGF2N
{
    // Atributo para almacenar el campo con el que se esta trabajando
    private GF2N galoisField;
    
    // Construtor de la clase y que solo requiere del campo que se quiere estudiar
    public PolynomialGF2N(GF2N galoisField)
    {
        this.galoisField = galoisField;
    }
    
    // Construtor de la clase y que crea el campo a partir del polinomio reductor que esta representado como un numero(Long)
    public PolynomialGF2N(long reducingPolynomial) throws Exception
    {
        galoisField = new GF2N(reducingPolynomial);
    }
    
    // Metodo para sumar dos elementos del campo representados como polinomios
    public Polynomial add(Polynomial polynomial1, Polynomial polynomial2) throws Exception
    {
        int length = Math.max(polynomial1.getSize(), polynomial2.getSize());
        Polynomial result = new Polynomial(length);

        for (int x = 0; x < length; x++)
        {
            try
            {
                long value = galoisField.add(polynomial1.getElement(x), polynomial2.getElement(x));
                result.setElement(x, value);
            }
            catch (IndexOutOfBoundsException ex)
            {
                if (x >= polynomial1.getSize())
                {
                    result.setElement(x, polynomial2.getElement(x));
                }
                else
                {
                    result.setElement(x, polynomial1.getElement(x));
                }
            }
        }

        return result.clearZeroValues();
    }
    
    // Metodo para restar dos elementos del campo representados como polinomios
    public Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) throws Exception
    {
        return add(polynomial1, polynomial2);
    }
    
    // Metodo para multiplicar dos elementos del campo representados como polinomios
    public Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2) throws Exception
    {
        if (isZero(polynomial1) || isZero(polynomial2))
        {
            return new Polynomial();
        }

        Polynomial result = new Polynomial(polynomial1.getSize() + polynomial2.getSize() - 1);

        for (int x = 0; x < polynomial2.getSize(); x++)
        {
            for (int y = 0; y < polynomial1.getSize(); y++)
            {
                if (polynomial1.getElement(y) != 0)
                {
                    int index = y + x;
                    long multiplyResult = galoisField.multiply(polynomial1.getElement(y), polynomial2.getElement(x));
                    long originalValue = result.getElement(index);
                    result.setElement(index, galoisField.add(originalValue, multiplyResult));
                }
            }
        }

        return result.clearZeroValues();
    }
    
    // Metodos para dividir dos elementos del campo representados como polinomios
    public Polynomial divide(Polynomial polynomial1, Polynomial polynomial2) throws Exception
    {
        if (isZero(polynomial2))
        {
            throw new Exception("Division by zero!");
        }

        Polynomial remainder = new Polynomial(Math.max(polynomial1.getSize(), polynomial2.getSize()));
        return divide(polynomial1, polynomial2, remainder);
    }
    
    public Polynomial divide(Polynomial polynomial1, Polynomial polynomial2, Polynomial remainder) throws Exception
    {
        if (isZero(polynomial2))
        {
            throw new Exception("Division by zero!");
        }

        Polynomial result;
        Polynomial rem;

        // Se preparan arreglos de un buen tamaño
        if (polynomial1.getSize() >= polynomial2.getSize())
        {
            result = new Polynomial(polynomial1.getSize() - polynomial2.getSize() + 1);
            rem = new Polynomial(polynomial1.getSize() - polynomial2.getSize() + 1);
        }
        else
        {
            result = new Polynomial(1);
            rem = new Polynomial(polynomial1.getSize());
        }

        Polynomial numerator = polynomial1;
        Polynomial mulResult = new Polynomial(polynomial1.getSize());

        while ((numerator.getSize() >= polynomial2.getSize()))
        {
            // Se procede con la division
            long value = galoisField.divide(numerator.getElement(numerator.getSize() - 1), polynomial2.getElement(polynomial2.getSize() - 1));
            result.setElement(numerator.getSize() - polynomial2.getSize(), value);

            // Se resta parte de la division del numerador
            rem.setElement(numerator.getSize() - polynomial2.getSize(), value);
            mulResult = multiply(polynomial2, rem);
            numerator = subtract(numerator, mulResult);
            
            // Se establece el resto para corregir la longitud necesaria para la division
            while ((numerator.getSize() - polynomial2.getSize() + 1) < rem.getSize())
            {
                if (rem.getSize() == 0)
                {
                    break;
                }
                else
                {
                    Polynomial newRem = new Polynomial(rem.getSize() - 1);
                    for (int x = 0; x < newRem.getSize(); x++)
                    {
                        newRem.setElement(x, rem.getElement(x));
                    }
                    rem = newRem;
                }
            }
        }
        
        // Se copia el resto de la division al resto del atributo de este metodo
        remainder.setSize(numerator.getSize());
        for (int x = 0; x < numerator.getSize(); x++)
        {
            remainder.setElement(x, numerator.getElement(x));
        }

        return result;
    }

    // result = gcd(polynomial1, polynomial2) = a*polynomial1 + b*polynomial2
    private Polynomial xgcd(Polynomial polynomial1, Polynomial polynomial2, Polynomial a, Polynomial b) throws Exception
    {
        if (polynomial2.getSize() > polynomial1.getSize())
        {
            // Se intercambia
            Polynomial swapPoly = polynomial1;
            polynomial1 = polynomial2;
            polynomial2 = swapPoly;
            swapPoly = a;
            a = b;
            b = swapPoly;
        }
        
        // Se prepara para la division
        Polynomial result = new Polynomial(polynomial2.getSize());
        Polynomial remainder = polynomial2;
        Polynomial numerator = polynomial1;
        Polynomial denumerator = polynomial2;
        
        // Se prepara para buscar la identidad de Bezout
        Polynomial temp;
        ArrayList<Polynomial> resultList = new ArrayList<>();
        ArrayList<Polynomial> bezoutIdentity = new ArrayList<>();
        
        // Se busca el maximo comun divisor, ultimo resto positivo
        while (!(remainder.getSize() == 0))
        {
            result = remainder;
            remainder = new Polynomial(Math.max(numerator.getSize(), denumerator.getSize()));
            temp = divide(numerator, denumerator, remainder);
            
            // Informacion de resultList que es necesaria para encontrar la identidad de Bezout
            resultList.add(numerator);
            resultList.add(denumerator);
            resultList.add(temp);

            remainder.clearZeroValues();
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

        Polynomial value = new Polynomial(1, 1);
        bezoutIdentity.add(value);
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

        //save Bezout's coefficients and return gcd
        // Se guardan los coeficientes de Bezout y se retorna el gcd
        for (int x = 0; x < bezoutIdentity.get(0).getSize(); x++)
        {
            a.setElement(x, bezoutIdentity.get(0).getElement(x));
        }
        
        for (int x = 0; x < bezoutIdentity.get(3).getSize(); x++)
        {
            b.setElement(x, bezoutIdentity.get(3).getElement(x));
        }

        // El gcd, no esta normalizado
        return result;
    }
    
    // Metodo para encontrar el maximo comun divisor
    public Polynomial gcd(Polynomial polynomial1, Polynomial polynomial2) throws Exception
    {
        if (!isZero(polynomial1) && isZero(polynomial2))
        {
            return polynomial1;
        }
        
        if (isZero(polynomial1) && !isZero(polynomial2))
        {
            return polynomial2;
        }
        
        if (isZero(polynomial1) && isZero(polynomial2))
        {
            return new Polynomial(0);
        }

        Polynomial result = new Polynomial(polynomial2.getSize());
        Polynomial remainder = polynomial2;
        Polynomial numerator = polynomial1;
        Polynomial denumerator = polynomial2;
        
        // Encontrando el maximo comun divisor, ultimo resto positivo
        while (!(remainder.getSize() == 0))
        {
            result = remainder;
            remainder = new Polynomial(Math.max(numerator.getSize(), denumerator.getSize()));
            divide(numerator, denumerator, remainder);
            remainder.clearZeroValues();
            numerator = denumerator;
            denumerator = remainder;
        }
        
        // Se hace el gcd monico
        Polynomial monicDiv = new Polynomial(1);
        monicDiv.setElement(0, result.getElement(result.getSize() - 1));
        return divide(result, monicDiv);
    }
    
    public Polynomial invert(Polynomial polynomial, Polynomial moduloPolynomial) throws Exception
    {
        if (isZero(polynomial) || isZero(moduloPolynomial))
        {
            throw new Exception("Cannot compute inverse for zero polynomials.");
        }

        if ((polynomial.getSize() >= moduloPolynomial.getSize()) || (moduloPolynomial.getSize() == 1))
        {
            throw new Exception("Cannot compute inverse for this args.");
        }

        if (polynomial.getSize() == 1)
        {
            // Solucion especial para un caso especial
            return divide(add(new Polynomial(1, 1), multiply(moduloPolynomial, polynomial)), polynomial);
        }

        Polynomial gcd;
        Polynomial result = new Polynomial(moduloPolynomial.getSize());
        Polynomial temp = new Polynomial(moduloPolynomial.getSize());
        
        gcd = xgcd(moduloPolynomial, polynomial, temp, result);

        if (gcd.getSize() != 1)
        {
            throw new Exception("Cannot compute inverse for this args.");
        }
        
        // Se normaliza el resultado, despues de esto el resto despues de la division por moduloPolynomial sera 1
        result.clearZeroValues();
        return divide(result, gcd);
    }
    
    // Metodo para calcular la potencia
    public Polynomial power(Polynomial polynomial, long exponent) throws Exception
    {
        if (exponent <= 0)
        {
            throw new Exception("Exponent must be positive number!");
        }

        if (isZero(polynomial))
        {
            return new Polynomial();
        }

        Polynomial result = new Polynomial(1, 1);

        Polynomial a = polynomial;
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
    
    // Metodo para revisar, si el polinomio no es igual a CERO
    private boolean isZero(Polynomial polynomial)
    {
        if (polynomial.getSize() == 0)
        {
            return true;
        }

        for (int x = 0; x < polynomial.getSize(); x++)
        {
            if (polynomial.getElement(x) != 0)
            {
                return false;
            }
        }
        return true;
    }
}
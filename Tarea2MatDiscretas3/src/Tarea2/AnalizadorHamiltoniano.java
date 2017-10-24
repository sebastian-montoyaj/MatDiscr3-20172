package Tarea2;

// Esta clase fue basada de:
// https://github.com/shubhampuranik/HamiltonianCycle/blob/master/HamiltonianCycle.java

// Importes necesarios para la clase
import java.util.ArrayList;
import java.util.List;

// Clase con la mision de estudiar los posibles circuitos hamiltonianos de un grafo dado
public class AnalizadorHamiltoniano
{
    /* ATRIBUTOS DE LA CLASE */
    int[][] grafo; // Variable/matriz que representa el grafo a estudiar (Como tal el grafo se representara como una matriz de adyacencia)
    int[] aux; // Variable para construir los ciclos
    int n; // Variable que indicara el numero de vertices o nodos del grafo
    //int conta; // Variable que ayuda a contar el numero de ciclos encontrados
    List<int[]> ciclosH; // Variable/Lista en donde se almacenanan todos los circuitos validos encontrados
    
    // Constructor de la clase. Su proposito es iniciar las variables principales de trabajo
    public AnalizadorHamiltoniano(int[][] g)
    {
        n = g.length;
        grafo = g;
        aux = new int[n];
    }
    
    // Metodo para recuperar cuantas soluciones/circuitos fueron encontrados
    public int obtenerNumeroCiclosHEncontrados()
    {
        return ciclosH.size();
    }
    
    // Metodo getter para recuperar los ciclos hamiltonianos encontrados del grafo dado
    public List obtenerListaCiclosHEncontrados()
    {
        return ciclosH;
    }
    
    // Metodo para iniciar el proceso de analisis/busqueda de los ciclos hamiltonianos
    public void comenzarAnalisis()
    {
        //conta = 1; // Se inicia el contador de soluciones
        ciclosH = new ArrayList(); // Se inicializa como vacia la lista de soluciones
        aux[0] = 0; // Se "encola" en primera posicion el primer vertice (o vertice CERO)
        hamiltonian(1); // Y se inicia el proceso de busqueda de los ciclos, invocando el metodo recursivo hamiltonian 
    }
    
    // Metodo recursivo que gestiona el proceso de analisis de circuitos
    // Parametros:
    // k = Posicion especifica que se quiere estudiar
    private void hamiltonian(int k)
    {
        // Mientras no se llegue a la condicion de parada HAGA:
        while (true)
        {
            // Revise cual es el siguiente vertice valido para k (Este se retornara por medio de aux[k])
            nextValue(k);
            
            // CONDICION DE PARADA:
            // Si ya se examinaron todos los vertices y se volvio al vertice CERO, RETORNE/PARE esta llamada recursiva
            if (aux[k] == 0)
            {
                return;
            }
            
            // Si para la posicion k se encontro un vertice diferente de cero y se llegaron a examinar n-1 vertices de manera exitosa es porque se encontro una solucion (circuito) y por tanto:
            if (k == n - 1)
            {
                // Se guarda la solucion en la lista definida para ella
                ciclosH.add(aux.clone());
                
                // [Opcional] Si se desea se puede imprimir el ciclo/solucion valida encontrada por consola descomentando el siguiente codigo:
                //System.out.print("Hamiltonian Cycle " + conta + ": ");
                //for (int i = 0; i < n; i++)
                //{
                //    System.out.print(aux[i] + " ");
                //}
                //System.out.println();
                //conta++;
            }
            else // Si todavia faltan vertices por analizar entonces
            {
                // Se hace llamada recursiva estudiando que vertice es valido para la siguiente posicion
                hamiltonian(k + 1);
            }
        }
    }
    
    // Metodo que se encarga de determinar que vertice se debe poner en la posicion k
    private void nextValue(int k)
    {
        // Mientras no se llegue a un vertice valido o al vertice CERO HAGA:
        while (true)
        {
            // Se selecciona un vertice a estudiar...
            // Donde, la primera vez que se llama este metodo aux[k] debe contener un cero (0)
            // por lo que aux[k] + 1 sera 1 (2do vertice) el cual sera el primero a analizar,
            // si el vertice 1 no fue entonces pasa al 2, luego al 3 y asi hasta que llega al
            // vertice n-1, donde en la siguiente iteracion si tampoco fue entonces vuelve a 
            // cero y termina al no encontrar un vertice valido.
            aux[k] = (aux[k] + 1) % n;
            
            // Si se llega al vertice CERO, es porque ninguno de los vertices entre 1 y n-1 fue valido, por lo que se termina el metodo
            if (aux[k] == 0)
            {
                return;
            }
            
            // Ahora, si entre el vertice ya valido de la posicion anterior y el que se esta analizando hay una arista (Osea un 1 en la matriz de adyacencia) entonces
            if (grafo[aux[k - 1]][aux[k]] != 0)
            {
                // Luego, se revisa que el vertice elegido "de momento" no se encuentre ya dentro de los vertices previos validos
                // por lo que se recorren los vertices validos desde la posicion 0 hasta la posicion actual de analisis
                int j;
                for (j = 0; j < k; j++)
                {
                    if (aux[k] == aux[j])
                    {
                        break;
                    }
                }
                
                // Si al terminar el ciclo anterior, j es igual a k es porque el vertice que se 
                // esta analizando aun no se ha considerado dentro del ciclo solucion, por lo que...
                if (j == k)
                {
                    // En caso que aun faltan posiciones para completar el ciclo entonces
                    if (k < n - 1)
                    {
                        return; // Se retorna de inmediato porque este vertice posiblemente sea valido para una solucion!
                    }
                    
                    // En caso que este sea el ultimo vertice de una posible solucion y tiene conexion con el primer vertice (O vertice CERO) entonces
                    if ((k == n - 1) && (grafo[aux[k]][aux[0]] != 0))
                    {
                        return; // Se retorna de inmediato porque se ACABO DE ENCONTRAR UNA SOLUCION O CICLO HAMILTONIANO VALIDO!!!
                    }
                }
            }
            
            // Si pasa el condicional anterior si entrar es porque no habia conexion entre el vertice que
            // se eligio de manera tentativa y el vertice valido de una posicion anterior.
        }
    }
}

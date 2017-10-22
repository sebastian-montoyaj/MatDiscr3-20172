package Tarea2;

// Clase con la mision de estudiar los posibles circuitos hamiltonianos de un grafo dado

import java.util.ArrayList;
import java.util.List;

public class AnalizadorHamiltoniano
{
    int[][] grafo;
    int[] aux;
    int n;
    //int conta;
    
    List<int[]> ciclosH;

    public AnalizadorHamiltoniano(int[][] g)
    {
        n = g.length;
        grafo = g;
        aux = new int[n];
    }

    public void nextValue(int k)
    {
        while (true)
        {
            aux[k] = (aux[k] + 1) % n;
            
            if (aux[k] == 0)
            {
                return;
            }
            
            if (grafo[aux[k - 1]][aux[k]] != 0)
            {
                int j;
                for (j = 0; j < k; j++)
                {
                    if (aux[k] == aux[j]) {
                        break;
                    }
                }
                if (j == k)
                {
                    if (k < n - 1)
                    {
                        return;
                    }
                    if ((k == n - 1) && (grafo[aux[k]][aux[0]] != 0))
                    {
                        return;
                    }
                }
            }
        }
    }

    public void hamiltonian(int k)
    {
        while (true)
        {
            nextValue(k);
            
            if (aux[k] == 0)
            {
                return;
            }
            
            if (k == n - 1)
            {
                ciclosH.add(aux.clone());
                
                // [Opcional] Si se desea se puede imprimir por consola 
                //System.out.print("Hamiltonian Cycle " + conta + ": ");
                //for (int i = 0; i < n; i++)
                //{
                //    System.out.print(aux[i] + " ");
                //}
                //System.out.println();
                //conta++;
            }
            else
            {
                hamiltonian(k + 1);
            }
        }
    }
    
    public void comenzarAnalisis()
    {
        //conta = 1;
        ciclosH = new ArrayList();
        aux[0] = 0;
        hamiltonian(1);
    }
    
    public int obtenerNumeroCiclosHEncontrados()
    {
        return ciclosH.size();
    }
    
    public List obtenerListaCiclosHEncontrados()
    {
        return ciclosH;
    }
}

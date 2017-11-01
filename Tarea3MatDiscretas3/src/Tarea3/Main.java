package Tarea3;

// Clase cuyo unico proposito es invocar las ventanas con las que debe interactuar el usuario
public class Main
{
    public static void main(String[] args)
    {
        // Aqui se inicia la ventana de bienvenida (o presentacion), esta solo aparece 5 segundos y despues se muestra la ventana principal
        new Thread (new VentanaBienvenida()).start();
//        VentanaPrincipal vPrueba = new VentanaPrincipal();
//        vPrueba.setVisible(true);
    }
}

package conversor;

import javax.swing.JOptionPane;

public class MenuPrincipal {
    private static final int CONVERSOR_MONEDA = 0;
    private static final int CONVERSOR_TEMPERATURA = 1;
    private static final int SALIR = 2;

    public static void main(String[] args) {
        // Establecer el tema Nimbus para un aspecto más moderno
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }

        menuPrincipal();
    }

    static void menuPrincipal() {
        String[] opciones = {"CONVERSOR DE MONEDA", "CONVERSOR DE TEMPERATURA", "SALIR"};

        int opcionSeleccionada = JOptionPane.showOptionDialog(
                null,
                "Selecciona una opción:",
                "Menú",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        switch (opcionSeleccionada) {
            case CONVERSOR_MONEDA:
                ConversorMoneda conversorMoneda = new ConversorMoneda();
                conversorMoneda.mostrarConversorMoneda();
                break;
            case CONVERSOR_TEMPERATURA:
                ConversorTemperatura conversorTemperatura = new ConversorTemperatura();
                conversorTemperatura.mostrarConversorTemperatura();
                break;
            case SALIR:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}

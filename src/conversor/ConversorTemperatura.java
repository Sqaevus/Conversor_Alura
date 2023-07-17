package conversor;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.text.PlainDocument;

public class ConversorTemperatura {
    private JTextField valorTextField;
    private JComboBox<String> unidadOrigenComboBox;
    private JComboBox<String> unidadDestinoComboBox;

    public void mostrarConversorTemperatura() {
        valorTextField = new JTextField();
        valorTextField.setBackground(Color.WHITE);

        // Limitar el campo de ingreso a números y signo negativo (-)
        PlainDocument doc = (PlainDocument) valorTextField.getDocument();
        doc.setDocumentFilter(new NumberFilter());

        String[] opcionesTemperatura = {"Celsius", "Fahrenheit", "Kelvin"};
        unidadOrigenComboBox = new JComboBox<>(opcionesTemperatura);
        unidadDestinoComboBox = new JComboBox<>(opcionesTemperatura);

        unidadOrigenComboBox.setBackground(Color.WHITE);
        unidadDestinoComboBox.setBackground(Color.WHITE);

        Object[] message = {
                "Valor:", valorTextField,
                "De:", unidadOrigenComboBox,
                "A:", unidadDestinoComboBox
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Conversor de Temperatura", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            double valor = Double.parseDouble(valorTextField.getText());
            double resultado = 0;

            String unidadOrigen = (String) unidadOrigenComboBox.getSelectedItem();
            String unidadDestino = (String) unidadDestinoComboBox.getSelectedItem();

            if (unidadOrigen == null || unidadDestino == null) {
                JOptionPane.showMessageDialog(null, "Selecciona una unidad de temperatura de origen y destino.");
                mostrarConversorTemperatura(); // Vuelve a mostrar el conversor de temperatura
                return;
            }

            if (unidadOrigen.equals(unidadDestino)) {
                JOptionPane.showMessageDialog(null, "La unidad de temperatura de origen y destino son las mismas.");
                mostrarConversorTemperatura(); // Vuelve a mostrar el conversor de temperatura
                return;
            }

            resultado = convertirTemperatura(valor, unidadOrigen, unidadDestino);

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            JOptionPane.showMessageDialog(null, "El resultado es: " + decimalFormat.format(resultado));

            MenuPrincipal menu = new MenuPrincipal();
            menu.menuPrincipal(); // Vuelve al menú principal después de realizar la conversión
        } else {
            MenuPrincipal menu = new MenuPrincipal();
            menu.menuPrincipal(); // Vuelve al menú principal si se cancela la operación
        }
    }

    private static double convertirTemperatura(double valor, String unidadOrigen, String unidadDestino) {
        // Lógica para convertir entre las diferentes unidades de temperatura
        // Implementa la conversión según tus necesidades

        if (unidadOrigen.equals("Celsius")) {
            if (unidadDestino.equals("Fahrenheit")) {
                return (valor * 9 / 5) + 32;
            } else if (unidadDestino.equals("Kelvin")) {
                return valor + 273.15;
            }
        } else if (unidadOrigen.equals("Fahrenheit")) {
            if (unidadDestino.equals("Celsius")) {
                return (valor - 32) * 5 / 9;
            }
        } else if (unidadOrigen.equals("Kelvin")) {
            if (unidadDestino.equals("Celsius")) {
                return valor - 273.15;
            } else if (unidadDestino.equals("Fahrenheit")) {
                return (valor * 9 / 5) - 459.67;
            }
        }

        return valor; // Si no hay conversión definida, se devuelve el mismo valor
    }

    // Filtro para permitir solo números y el signo negativo (-)
    static class NumberFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            StringBuilder builder = new StringBuilder(text);
            for (int i = builder.length() - 1; i >= 0; i--) {
                char ch = builder.charAt(i);
                if (!Character.isDigit(ch) && ch != '-' && ch != '.') {
                    builder.deleteCharAt(i);
                }
            }
            super.insertString(fb, offset, builder.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            StringBuilder builder = new StringBuilder(text);
            for (int i = builder.length() - 1; i >= 0; i--) {
                char ch = builder.charAt(i);
                if (!Character.isDigit(ch) && ch != '-' && ch != '.') {
                    builder.deleteCharAt(i);
                }
            }
            super.replace(fb, offset, length, builder.toString(), attrs);
        }
    }
}
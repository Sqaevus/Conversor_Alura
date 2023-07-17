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

public class ConversorMoneda {
    private JTextField valorTextField;
    private JComboBox<String> monedaOrigenComboBox;
    private JComboBox<String> monedaDestinoComboBox;

    public void mostrarConversorMoneda() {
        valorTextField = new JTextField();
        valorTextField.setBackground(Color.WHITE);

        // Limitar el campo de ingreso a números y signo negativo (-)
        PlainDocument doc = (PlainDocument) valorTextField.getDocument();
        doc.setDocumentFilter(new NumberFilter());

        String[] opcionesMoneda = {"CLP", "USD", "EUR", "GBP", "JPY", "KRW"};
        monedaOrigenComboBox = new JComboBox<>(opcionesMoneda);
        monedaDestinoComboBox = new JComboBox<>(opcionesMoneda);

        monedaOrigenComboBox.setBackground(Color.WHITE);
        monedaDestinoComboBox.setBackground(Color.WHITE);

        Object[] message = {
                "Valor:", valorTextField,
                "De:", monedaOrigenComboBox,
                "A:", monedaDestinoComboBox
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Conversor de Moneda", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String valorText = valorTextField.getText();
            if (valorText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor válido.");
                mostrarConversorMoneda(); // Vuelve a mostrar el conversor de moneda
                return;
            }
            double valor = Double.parseDouble(valorText);
            double resultado = 0;

            String monedaOrigen = (String) monedaOrigenComboBox.getSelectedItem();
            String monedaDestino = (String) monedaDestinoComboBox.getSelectedItem();

            if (monedaOrigen == null || monedaDestino == null) {
                JOptionPane.showMessageDialog(null, "Selecciona una moneda de origen y destino.");
                mostrarConversorMoneda(); // Vuelve a mostrar el conversor de moneda
                return;
            }

            if (monedaOrigen.equals(monedaDestino)) {
                JOptionPane.showMessageDialog(null, "La moneda de origen y destino son las mismas.");
                mostrarConversorMoneda(); // Vuelve a mostrar el conversor de moneda
                return;
            }

            resultado = convertirMoneda(valor, monedaOrigen, monedaDestino);

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            JOptionPane.showMessageDialog(null, "El resultado es: " + decimalFormat.format(resultado));

            MenuPrincipal menu = new MenuPrincipal();
            menu.menuPrincipal(); // Vuelve al menú principal después de realizar la conversión
        } else {
            MenuPrincipal menu = new MenuPrincipal();
            menu.menuPrincipal(); // Vuelve al menú principal si se cancela la operación
        }
    }

    private static double convertirMoneda(double valor, String monedaOrigen, String monedaDestino) {
        // Lógica para convertir entre las diferentes monedas
        // Implementa la conversión según tus necesidades

        if (monedaOrigen.equals("CLP")) {
            if (monedaDestino.equals("USD")) {
                return valor / 794.81; // Tasa de cambio CLP a USD
            } else if (monedaDestino.equals("EUR")) {
                return valor / 882.32; // Tasa de cambio CLP a EUR
            } else if (monedaDestino.equals("GBP")) {
                return valor / 1097.56; // Tasa de cambio CLP a GBP
            } else if (monedaDestino.equals("JPY")) {
                return valor / 7.22; // Tasa de cambio CLP a JPY
            } else if (monedaDestino.equals("KRW")) {
                return valor / 0.066; // Tasa de cambio CLP a KRW
            }
        } else if (monedaOrigen.equals("USD")) {
            if (monedaDestino.equals("CLP")) {
                return valor * 794.81; // Tasa de cambio USD a CLP
            }
        } else if (monedaOrigen.equals("EUR")) {
            if (monedaDestino.equals("CLP")) {
                return valor * 882.32; // Tasa de cambio EUR a CLP
            }
        } else if (monedaOrigen.equals("GBP")) {
            if (monedaDestino.equals("CLP")) {
                return valor * 1097.56; // Tasade cambio GBP a CLP
            }
        } else if (monedaOrigen.equals("JPY")) {
            if (monedaDestino.equals("CLP")){
            	return valor * 7.22; // Tasa de cambio JPY a CLP
            }
            } else if (monedaOrigen.equals("KRW")) {
            if (monedaDestino.equals("CLP")) {
            return valor * 0.066; // Tasa de cambio KRW a CLP
            }
            }   return valor; // Si no hay conversión definida, se devuelve el mismo valor
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
    }}
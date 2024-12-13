import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class formfig extends JFrame {

    private JComboBox<String> figureSelector;
    private JPanel inputPanel;
    private JTextArea resultArea;
    private JButton button1;
    private JButton button2;

    public formfig() {
        setTitle("Calculadora de Área y Perímetro");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para seleccionar figura
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecciona una figura:"));
        figureSelector = new JComboBox<>(new String[]{"Cuadrado", "Rectángulo", "Círculo", "Triángulo", "Pentágono"});
        figureSelector.addActionListener(new FigureSelectorListener());
        topPanel.add(figureSelector);
        add(topPanel, BorderLayout.NORTH);

        //  Entradas de usuario
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        add(inputPanel, BorderLayout.CENTER);

        // Área de resultados
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Mostrar campos iniciales
        updateInputFields("Cuadrado");
    }

    private void updateInputFields(String figure) {
        inputPanel.removeAll();

        switch (figure) {
            case "Cuadrado":
                inputPanel.add(new JLabel("Lado:"));
                JTextField ladoField = new JTextField();
                inputPanel.add(ladoField);
                createCalculateButton(e -> {
                    double lado = Double.parseDouble(ladoField.getText());
                    showResults(lado * lado, 4 * lado);
                });
                break;

            case "Rectángulo":
                inputPanel.add(new JLabel("Base:"));
                JTextField baseField = new JTextField();
                inputPanel.add(baseField);
                inputPanel.add(new JLabel("Altura:"));
                JTextField alturaField = new JTextField();
                inputPanel.add(alturaField);
                createCalculateButton(e -> {
                    double base = Double.parseDouble(baseField.getText());
                    double altura = Double.parseDouble(alturaField.getText());
                    showResults(base * altura, 2 * (base + altura));
                });
                break;

            case "Círculo":
                inputPanel.add(new JLabel("Radio:"));
                JTextField radioField = new JTextField();
                inputPanel.add(radioField);
                createCalculateButton(e -> {
                    double radio = Double.parseDouble(radioField.getText());
                    showResults(Math.PI * radio * radio, 2 * Math.PI * radio);
                });
                break;

            case "Triángulo":
                inputPanel.add(new JLabel("Base:"));
                JTextField baseTriField = new JTextField();
                inputPanel.add(baseTriField);
                inputPanel.add(new JLabel("Altura:"));
                JTextField alturaTriField = new JTextField();
                inputPanel.add(alturaTriField);
                inputPanel.add(new JLabel("Lado 1:"));
                JTextField lado1Field = new JTextField();
                inputPanel.add(lado1Field);
                inputPanel.add(new JLabel("Lado 2:"));
                JTextField lado2Field = new JTextField();
                inputPanel.add(lado2Field);
                inputPanel.add(new JLabel("Lado 3:"));
                JTextField lado3Field = new JTextField();
                inputPanel.add(lado3Field);
                createCalculateButton(e -> {
                    double base = Double.parseDouble(baseTriField.getText());
                    double altura = Double.parseDouble(alturaTriField.getText());
                    double lado1 = Double.parseDouble(lado1Field.getText());
                    double lado2 = Double.parseDouble(lado2Field.getText());
                    double lado3 = Double.parseDouble(lado3Field.getText());
                    showResults((base * altura) / 2, lado1 + lado2 + lado3);
                });
                break;

            case "Pentágono":
                inputPanel.add(new JLabel("Lado:"));
                JTextField ladoPentField = new JTextField();
                inputPanel.add(ladoPentField);
                inputPanel.add(new JLabel("Apotema:"));
                JTextField apotemaField = new JTextField();
                inputPanel.add(apotemaField);
                createCalculateButton(e -> {
                    double lado = Double.parseDouble(ladoPentField.getText());
                    double apotema = Double.parseDouble(apotemaField.getText());
                    double perimetro = 5 * lado;
                    showResults((perimetro * apotema) / 2, perimetro);
                });
                break;
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void createCalculateButton(ActionListener listener) {
        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(listener);
        inputPanel.add(calculateButton);
    }

    private void showResults(double area, double perimeter) {
        resultArea.setText(String.format("Área: %.2f\nPerímetro: %.2f", area, perimeter));
    }

    private class FigureSelectorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedFigure = (String) figureSelector.getSelectedItem();
            updateInputFields(selectedFigure);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            formfig calculator = new formfig();
            calculator.setVisible(true);
        });
    }
}



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Expression;
import java.util.ArrayList;

public class CalculadoraCientifica extends JFrame implements ActionListener {
    JTextField display;
    JPanel buttonPanel;
    boolean eCientifica = false;
    String lastInput = "";

    //Botões da calculadora
    String[] basicButtons = {
            "OFF", "I/P", "⌫", "C",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "(", ")", "%", "SCI"
    };

    String[] sciButtons = {
            "OFF", "", "", "", "C",
            "x!", "nPr", "nCr", "e^x", "log", "Ln",
            "x³", "x²", "√", "∛", "%", "π",
            "sin⁻¹", "cos⁻¹", "tan⁻¹", "sin", "cos", "tan",
            "7", "8", "9", "⌫", "Ans", "| |",
            "4", "5", "6", "*", "-", "I/P",
            "0", ".", "(", ")", "=", "BSI"
    };

    java.util.List<String> history = new ArrayList<>();

    //Parte grafica da calculado, como tipo de cor do botão e posição e estilo
    public CalculadoraCientifica() {
        setTitle("Calculadora Científica");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(40, 40, 40));


        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 26));
        display.setBackground(new Color(222, 222, 222));
        display.setForeground(Color.BLACK);
        display.setPreferredSize(new Dimension(600, 100));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.CENTER);

        addButtons();

        setVisible(true);

    }

    void addButtons() {
        buttonPanel.removeAll();

        if (eCientifica) {
            buttonPanel.setLayout(new GridLayout(8, 6, 8, 8));
            for (String text : sciButtons) {
                if (text.isEmpty()) {
                    buttonPanel.add(new JLabel());
                } else {
                    addButton(text);
                }
            }
            setSize(600, 700);
        } else {
            buttonPanel.setLayout(new GridLayout(6, 4, 8, 8));
            for (String text : basicButtons) {
                addButton(text);
            }
            setSize(400, 550);
        }

        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    void addButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBackground(new Color(100, 100, 100));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.addActionListener(this);
        buttonPanel.add(btn);
    }

    //Lógica de calculo com os simbolos utilizado
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            switch (cmd) {
                case "OFF" -> System.exit(0);
                case "C" -> {
                    display.setText("");
                    lastInput = "";
                }
                case "=" -> {
                    String expression = display.getText();
                    if (expression.contains("P")) {
                        String[] parts = expression.split("P");
                        int n = Integer.parseInt(parts[0]);
                        int r = Integer.parseInt(parts[1]);
                        display.setText(String.valueOf(permutations(n, r)));
                    } else if (expression.contains("C")) {
                        String[] parts = expression.split("C");
                        int n = Integer.parseInt(parts[0]);
                        int r = Integer.parseInt(parts[1]);
                        display.setText(String.valueOf(combination(n, r)));
                    } else {
                        double result = safeEval(expression);
                        display.setText(Double.isNaN(result) ? "Error" : String.valueOf(result));
                        if (!Double.isNaN(result)) {
                            history.add(expression + "=" + result);
                            lastInput = expression + "=" + result;
                        }
                    }
                }

                case "⌫" -> {
                    String text = display.getText();
                    if (!text.isEmpty()){
                        display.setText(text.substring(0, text.length() - 1));
                    }
                }

                case "I/P" -> display.setText(lastInput);
                case "Ans" -> {
                    if (!history.isEmpty()) {
                        String[] parts = history.getLast().split("=");
                        display.setText(parts[1]);
                    }
                }

                case "Log" -> display.setText("log10(" + display.getText() + ")");
                case "Ln" -> display.setText("log(" + display.getText() + ")");
                case "e^x" -> display.setText("exp(" + display.getText() + ")");
                case "x²" -> display.setText("(" + display.getText() + ")^2");
                case "x³" -> display.setText("(" + display.getText() + ")^3");
                case "√" -> display.setText("sqrt(" + display.getText() + ")");
                case "∛" -> display.setText("cbrt(" + display.getText() + ")");
                case "x!" -> display.setText("fact(" + display.getText() + ")");
                case "nPr" -> display.setText(display.getText() + "P");
                case "nCr" -> display.setText("sin(" + display.getText() + "C");
                case "cos" -> display.setText("cos(" + display.getText() + ")");
                case "tan" -> display.setText("tan(" + display.getText() + ")");
                case "sin⁻¹" -> display.setText("asin(" + display.getText() + ")");
                case "cos⁻¹" -> display.setText("acos(" + display.getText() + ")");
                case "tan⁻¹" -> display.setText("atan(" + display.getText() + ")");
                case "π" -> display.setText(display.getText() + Math.PI);
                case "| |" -> display.setText("abs(" + display.getText() + ")");
                case "rem" -> display.setText(display.getText() + "%");
                case "SCI", "BSI" -> toggleScientific();
                default -> display.setText(display.getText() + cmd);
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    void toggleScientific() {
        eCientifica = !eCientifica;
        addButtons();
    }

    double safeEval(String expr) {
        try {
            ExpressionParser parser = new ExpressionParser(expr);
            return parser.parse();
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    long factorial(int n) {
        if (n < 0) return 0;
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    int permutations(int n, int r) {
        if (r < 0 || n < r) return 0;
        return (int) (factorial(n) / factorial(n - r));
    }

    int combination(int n, int r) {
        if (r < 0 || n < r) return 0;
        return (int) (factorial(n) / (factorial(r) * factorial(n - r)));
    }

    //Execução da calculadroa científica
    public static void main(String[] args) {
        new CalculadoraCientifica();
    }
}

class ExpressionParser{
    private final String input;
    private int pos = -1, ch;

    public ExpressionParser(String input){
        this.input = input;
        nextChar();
    }

    void nextChar(){
        ch = (++pos < input.length()) ? input.charAt(pos) : -1;
    }

    boolean eat(int charToEat){
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    double parse(){
        double x = parseExpression();
        if (pos < input.length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return x;
    }

    double parseExpression(){
        double x = parseTerm();
        for (;;){
            if (eat('+')) x += parseTerm();
            else if (eat('/')) x /= parseTerm();
            else  return x;
        }
    }

    double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if (eat('*')) x *= parseFactor();
            else if (eat('/')) x  /= parseFactor();
            else if (eat('%')) x %= parseFactor();
            else  return x;
        }
    }

    //Aplicando função de cálculo
    double parseFactor(){
        if (eat('+')) return parseFactor();
        if (eat('-')) return -parseFactor();

        double x;
        int startPos = this.pos;

        if (eat('(')) {
            x = parseExpression();
            eat(')');
        }else if ((ch >= '0' && ch <= '9') || ch == '.'){
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(input.substring(startPos, this.pos));
        }else if (ch >= 'a' && ch <= 'z'){
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = input.substring(startPos, this.pos);
            x = parseFactor();
            x = switch (func) {
                case "sqrt" -> Math.sqrt(x);
                case "cbrt" -> Math.cbrt(x);
                case "sin" -> Math.sin(Math.toRadians(x));
                case "cos" -> Math.cos(Math.toRadians(x));
                case "tan" -> Math.tan(Math.toRadians(x));
                case "asin" -> Math.toDegrees(Math.asin(x));
                case "acos" -> Math.toDegrees(Math.acos(x));
                case "atan" -> Math.toDegrees(Math.atan(x));
                case "log" -> Math.log(x);
                case "log10" -> Math.log10(x);
                case "exp" -> Math.exp(x);
                case "abs" -> Math.abs(x);
                case "fact" -> factorial((int)x);
                default -> throw new RuntimeException("Função desconhecida: " + func);
            };
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        if (eat('^')) x = Math.pow(x, parseFactor());

        return x;
    }

    private static long factorial(int n){
        if (n < 0) return 0;
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }
}
/* Реализуйте игру-угадайку, которая имеет одно текстовое поле и одну
кнопку. Он предложит пользователю угадать число между 0-20 и дает ему три
попытки. Если ему не удастся угадать, то будет выведено сообщение, что
пользователь допустил ошибку в угадывании и чточисло меньше / больше. Если
пользователь попытался три раза угадать, то программу завершается с
соответствующим сообщением. Если пользователь угадал, то программа должна
тоже завершаться с соответствующим сообщением. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Guess {
    // Константы попыток и максимального значения
    private static final int MAX_TRIES = 3;
    private static final int MAX_NUMBER = 20;
    private int randomNumber; // число, необходимое угадать
    private int attempts; // попытки

    // Конструктор
    public Guess() {
        randomNumber = new Random().nextInt(MAX_NUMBER + 1); // генерируем случайное число для угадывания
        attempts = 0; // обнуляем попытки
        GUI(); // вызываем граф.интерфейс
    }

    private void GUI() {
        // Создаём экземпляр фрейма
        JFrame frame = new JFrame("Игра-угадайка");
        frame.setLocationRelativeTo(null); // отображаем в центре экрана
        frame.setSize(300, 150); // определяем размер фрейма
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливаем реакцию окна на закрытие по умолчанию

        // Создаём экземпляр панели
        JPanel panel = new JPanel();
        // Определяем вертикальное расположение компонентов
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Добавляем заголовок, где будут отображаться сообщения
        JLabel messageLabel = new JLabel("Введите число от 0 до 20");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // располагаем по центру
        // Добавляем текстовое окно, куда будем вводить число
        JTextField guessField = new JTextField();
        guessField.setAlignmentX(Component.CENTER_ALIGNMENT); // располагаем по центру
        guessField.setPreferredSize(new Dimension(100, 30)); // определяем нужный формат
        // Добавляем кнопку
        JButton guessButton = new JButton("Угадать");
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT); // располагаем по центру

        // Добавляем слушателя к кнопке
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess(guessField.getText(), messageLabel); // применяем метод
            }
        });

        // Добавляем компоненты к панели, создаём расстояние между ними
        panel.add(messageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(guessField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(guessButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }

    // Метод, сравнивающий значения заданного и вводимого чисел
    private void checkGuess(String guessText, JLabel messageLabel) {
        try {
            int guess = Integer.parseInt(guessText); // преобразуем String в Integer
            attempts++; // увеличиваем количество попыток

            // Если число не входит в диапазон
            if (guess < 0 || guess > MAX_NUMBER) {
                messageLabel.setText("Число должно быть от 0 до 20.");
                return;
            }

            // Если числа совпали
            if (guess == randomNumber) {
                // Отображаем диалоговое окно
                JOptionPane.showMessageDialog(null, "Вы угадали число: " + randomNumber);
                System.exit(0); // завершаем работу программы
            } else if (attempts < MAX_TRIES) { // если попытки ещё не исчерпаны
                if (guess < randomNumber) { // если ввели число меньше загаданного
                    messageLabel.setText("Неправильно! Загаданное число больше.");
                }
                else { // если ввели число больше загаданного
                    messageLabel.setText("Неправильно! Загаданное число меньше.");
                }
            } else { // если попытки исчерпаны
                // Отображаем диалоговое окно
                JOptionPane.showMessageDialog(null, "Вы исчерпали все попытки! Загаданное число было: " + randomNumber);
                System.exit(0); // завершаем работу программы
            }
        } catch (NumberFormatException e) { // при некорректном виде
            messageLabel.setText("Пожалуйста, введите корректное число.");
        }
    }

    public static void main(String[] args) {
        // Безопасное создание и отображение GUI в потоке событий
        SwingUtilities.invokeLater(() -> new Guess());
    }
}
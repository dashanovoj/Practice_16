import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor {
    private JTextArea textArea; // текстовое окно, где будем редактировать текст

    // Конструктор
    public TextEditor() {
        // Создаём экземпляр фрейма
        JFrame frame = new JFrame("Редактор текста");
        frame.setSize(400, 300); // определяем размер фрейма
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // устанавливаем реакцию окна на закрытие по умолчанию

        // Создаём экземпляр текстового окна
        textArea = new JTextArea();
        textArea.setLineWrap(true); // разрешаем автоматический перенос текста на другую строку
        textArea.setWrapStyleWord(true); // перенос слов, а не частей слов

        // Создаём панель Меню, которая будет содержать меню для выбора цвета и шрифта
        JMenuBar menuBar = new JMenuBar();

        // Создаём меню "Цвет"
        JMenu colourMenu = new JMenu("Цвет");
        String[] colours = {"Синий", "Красный", "Чёрный"}; // строковый массив цветов
        /* Для каждого цвета в массиве создаем элемент меню
        и добавляем к нему обработчик событий */
        for (String colour : colours) {
            JMenuItem menuItem = new JMenuItem(colour);
            menuItem.addActionListener(new ColourActionListener(colour));
            colourMenu.add(menuItem);
        }
        menuBar.add(colourMenu); // добавляем меню "Цвет" в панель меню

        // Создаём меню "Шрифт"
        JMenu fontMenu = new JMenu("Шрифт");
        String[] fonts = {"Times New Roman", "MS Sans Serif", "Courier New"}; // строковый массив шрифтов
        /* Для каждого шрифта в массиве создаем элемент меню
        и добавляем к нему обработчик событий */
        for (String font : fonts) {
            JMenuItem menuItem = new JMenuItem(font);
            menuItem.addActionListener(new FontActionListener(font));
            fontMenu.add(menuItem);
        }
        menuBar.add(fontMenu); // добавляем меню "Шрифт" в панель меню

        frame.setJMenuBar(menuBar); // устанавливаем панель окна во фрейме
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER); // обеспечим возможность прокрутки текста, разместим текстовую область в центре окна
        frame.setVisible(true);
    }

    // Обработка событий выбора цвета
    private class ColourActionListener implements ActionListener {
        private String colour;

        // Конструктор
        public ColourActionListener(String colour) {
            this.colour = colour;
        }
        // Устанавливаем выбранный цвет
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (colour) {
                case "Синий":
                    textArea.setForeground(Color.BLUE);
                    break;
                case "Красный":
                    textArea.setForeground(Color.RED);
                    break;
                case "Чёрный":
                    textArea.setForeground(Color.BLACK);
                    break;
            }
        }
    }

    // Обработка событий выбора шрифта
    private class FontActionListener implements ActionListener {
        private String font;

        // Конструктор
        public FontActionListener(String font) {
            this.font = font;
        }

        @Override
        // Устанавливаем выбранный шрифт
        public void actionPerformed(ActionEvent e) {
            switch(font) {
                case "Times New Roman":
                    textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
                    break;
                case "MS Sans Serif":
                    textArea.setFont(new Font("MS Sans Serif", Font.PLAIN, 12));
                    break;
                case "Courier New":
                    textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Безопасное создание и отображение GUI в потоке событий
        SwingUtilities.invokeLater(() -> new TextEditor());
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame implements ActionListener {
    private int targetNumber;
    private int numberOfGuesses;
    private int userScore;
    private JTextField guessField;
    private JTextArea gameStatus;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel promptLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        topPanel.add(promptLabel);
        topPanel.add(guessField);
        topPanel.add(guessButton);

        add(topPanel, BorderLayout.NORTH);

        gameStatus = new JTextArea();
        gameStatus.setEditable(false);
        add(new JScrollPane(gameStatus), BorderLayout.CENTER);

        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        numberOfGuesses = 10;
        userScore = 0;

        gameStatus.setText("Welcome to the Guessing Game!\n");
        gameStatus.append("Try to guess the number between 1 and 100.\n");
        gameStatus.append("You have " + numberOfGuesses + " guesses left.\n");

        guessField.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (numberOfGuesses > 0) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                processGuess(userGuess);
            } catch (NumberFormatException ex) {
                gameStatus.append("Invalid input. Please enter a valid number.\n");
            }
        } else {
            gameStatus.append("Game over. Your score: " + userScore + "\n");
            guessField.setEnabled(false);
        }
    }

    private void processGuess(int userGuess) {
        numberOfGuesses--;

        if (userGuess == targetNumber) {
            userScore += 10;
            gameStatus.append("Congratulations! You guessed the correct number.\n");
            initializeGame();
        } else if (userGuess < targetNumber) {
            gameStatus.append("Try a higher number. " + numberOfGuesses + " guesses left.\n");
        } else {
            gameStatus.append("Try a lower number. " + numberOfGuesses + " guesses left.\n");
        }

        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessingGameGUI game = new GuessingGameGUI();
            game.setVisible(true);
        });
    }
}

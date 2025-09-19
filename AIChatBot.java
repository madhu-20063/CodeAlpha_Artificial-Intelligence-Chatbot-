import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AIChatBot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    // Rule-based knowledge base (FAQs)
    private Map<String, String> knowledgeBase;

    public AIChatBot() {
        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Knowledge base (rule-based)
        knowledgeBase = new HashMap<>();
        knowledgeBase.put("hi", "Hello! How can I help you today?");
        knowledgeBase.put("hello", "Hi there! Ask me anything.");
        knowledgeBase.put("bye", "Goodbye! Have a nice day.");
        knowledgeBase.put("what is ai", "AI stands for Artificial Intelligence, which enables machines to mimic human intelligence.");
        knowledgeBase.put("who are you", "I am a simple AI chatbot created in Java.");
        knowledgeBase.put("what is java", "Java is a versatile, object-oriented programming language used for building applications.");

        // Action listeners
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String userText = inputField.getText().trim().toLowerCase();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");
        inputField.setText("");

        String response = getResponse(userText);
        chatArea.append("Bot: " + response + "\n\n");
    }

    // Rule-based NLP (keyword detection)
    private String getResponse(String input) {
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }

        // Default fallback
        return "I'm still learning. Can you rephrase that?";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AIChatBot bot = new AIChatBot();
            bot.setVisible(true);
        });
    }
}

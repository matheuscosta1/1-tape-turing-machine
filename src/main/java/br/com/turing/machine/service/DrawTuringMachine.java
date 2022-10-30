package br.com.turing.machine.service;

import br.com.turing.machine.domain.CellCoordinate;
import br.com.turing.machine.domain.Symbol;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class DrawTuringMachine extends JPanel implements ActionListener {

    ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final String fileResource = "classpath:images/arrow.png";

    ImageIcon imageIcon = new ImageIcon(new ImageIcon(resourceLoader.getResource(fileResource).getURL()).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

    JButton processorButton = new JButton("Processar");
    JButton inputButton = new JButton("Enviar");

    JTextField userInput;
    JLabel imageLabel;

    JTextField outputActualState;

    ArrayList<CellCoordinate> coordinateList = new ArrayList<>();

    DrawTuringMachine() throws IOException {
        setLayout(null);

        userInput = new JTextField("", 30);

        outputActualState = new JTextField("", 30);

        userInput.setBounds(100, 500, 150, 100);

        userInput.setSize(new Dimension(300, 50));

        inputButton.setBounds(300, 600, 150, 100);

        inputButton.addActionListener(
                e -> {
                    try {
                        submitAction();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );


        processorButton.setBounds(100, 600, 150, 100);

        processorButton.setEnabled(false);

        processorButton.addActionListener(this);


        imageLabel = new JLabel(imageIcon, SwingConstants.CENTER);

        outputActualState.setText("Actual state: q0");

        add(imageLabel);

        add(processorButton, BorderLayout.SOUTH);
        add(userInput);
        add(inputButton);
        add(outputActualState);

    }

    private void submitAction() throws IOException {
        String word = userInput.getText();
        inputButton.setEnabled(false);
        userInput.setEnabled(false);
        draw(word);
    }

    public void draw(String word) {
        Graphics graphics = getGraphics();

        graphics.setFont(new Font("", Font.PLAIN, 30));
        FontMetrics fontMetrics = getFontMetrics(new Font("", Font.PLAIN, 30));
        int axisX = 100;
        int axisY = 200;

        for (int iterator = 0; iterator < word.length(); iterator++) {
            char actualCharacter = word.charAt(iterator);
            int height = fontMetrics.getHeight();
            int width = fontMetrics.charWidth(actualCharacter);

            graphics.drawRect(axisX, axisY, width, height);

            int drawStringYAxis = axisY + height;

            graphics.drawString(String.valueOf(actualCharacter), axisX, drawStringYAxis);

            if (coordinateList.size() < word.length()) {
                coordinateList.add(
                        CellCoordinate
                                .builder()
                                .xAxis(axisX)
                                .yAxis(axisY)
                                .drawStringYAxis(drawStringYAxis)
                                .symbol(Symbol.builder().character(String.valueOf(actualCharacter)).build())
                                .width(width)
                                .height(height)
                                .build()
                );
            }

            axisX = axisX + width;
        }

        imageLabel.setBounds(100, 250, 30, 30); //escreve a seta apontando para primeira celula (estado inicial)

        outputActualState.setBounds(100, 300, 130, 50); //escreve o estado atual na tela
        outputActualState.setEnabled(false);

        processorButton.setEnabled(true);
    }

    public void update() {
        Graphics graphics = getGraphics();
        graphics.setFont(new Font("", Font.PLAIN, 30));

        graphics.clearRect(100, 200, 29, 36);

        graphics.drawRect(100, 200, 29, 36);

        graphics.drawString("b", 100, 236);

        imageLabel.setBounds(120, 250, 30, 30);
        outputActualState.setText("Actual state: q1");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Máquina de Turing");

        frame.add(new DrawTuringMachine());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1600, 900));

    }
}

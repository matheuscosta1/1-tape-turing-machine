package br.com.turing.machine.service;

import br.com.turing.machine.domain.CellCoordinate;
import br.com.turing.machine.domain.Symbol;
import br.com.turing.machine.domain.Transition;
import br.com.turing.machine.domain.TuringMachine;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class DrawTuringMachine extends JPanel implements ActionListener {

    ResourceLoader resourceLoader = new DefaultResourceLoader();

    ReadTuringMachineTransitions readTuringMachineTransitions = new ReadTuringMachineTransitions();

    private final String arrowImageFilePath = "classpath:images/arrow.png";
    private final String inputFilePath = "classpath:entrada/maquina.json";

    ImageIcon imageIcon = new ImageIcon(new ImageIcon(resourceLoader.getResource(arrowImageFilePath).getURL()).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

    JButton processorButton = new JButton("Processar");
    JButton inputButton = new JButton("Enviar");

    JTextField userInput;
    JLabel arrow;
    JTextField outputActualState;

    ArrayList<CellCoordinate> coordinateList = new ArrayList<>();

    TuringMachine turingMachine;

    String actualState;

    Integer index = 0;

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

        arrow = new JLabel(imageIcon, SwingConstants.CENTER);

        add(arrow);
        add(processorButton, BorderLayout.SOUTH);
        add(userInput);
        add(inputButton);
        add(outputActualState);

        turingMachine = readTuringMachineTransitions.readFile(inputFilePath);

        actualState = turingMachine.getInitialState();

        outputActualState.setText("Actual state: ".concat(actualState));

    }

    private void submitAction() throws IOException {
        String initialState = turingMachine.getTransitions().get(0).getReadSymbol();
        String word = userInput.getText();
        String wordWithInitialState = initialState.concat(word);
        inputButton.setEnabled(false);
        userInput.setEnabled(false);
        int quantityOfBlankSymbols = 60 - wordWithInitialState.length();

        String wordWithBlankSymbols = wordWithInitialState.concat("B".repeat(quantityOfBlankSymbols));

        draw(wordWithBlankSymbols);

    }

    public void draw(String word) {
        Graphics graphics = getGraphics();

        graphics.setFont(new Font("", Font.PLAIN, 20));

        int axisX = 100;
        int axisY = 200;
        int height = 20;
        int width = 20;

        for (int iterator = 0; iterator < word.length(); iterator++) {
            char actualCharacter = word.charAt(iterator);


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

        arrow.setBounds(100, 250, 30, 30); //escreve a seta apontando para primeira celula (estado inicial)

        outputActualState.setBounds(100, 300, 130, 50); //escreve o estado atual na tela
        outputActualState.setEnabled(false);

        processorButton.setEnabled(true);
    }

    public void drawUpdate(Transition transition) {
        Graphics graphics = getGraphics();
        graphics.setFont(new Font("", Font.PLAIN, 20));

        int height = 20;
        int width = 20;

        CellCoordinate cellCoordinate = coordinateList.get(index);

        graphics.clearRect(cellCoordinate.getXAxis(), cellCoordinate.getYAxis(), width, height);

        graphics.drawRect(cellCoordinate.getXAxis(), cellCoordinate.getYAxis(), width, height);

        graphics.drawString(transition.getWriteSymbol(), cellCoordinate.getXAxis(), cellCoordinate.getDrawStringYAxis());


        if(transition.getDirection().equals("RIGHT")) {
            arrow.setBounds(cellCoordinate.getXAxis() + width, 250, width, height);
            index += 1;
        } else {
            arrow.setBounds(cellCoordinate.getXAxis() - width, 250, width, height);
            index -= 1;
        }

        actualState = transition.getDestinyState();

        cellCoordinate.setSymbol(Symbol.builder().character(transition.getWriteSymbol()).build()); //atualiza simbolo na transicao que está sendo processada de acordo com o novo simbolo que foi gravado na fita

        outputActualState.setText("Actual state: ".concat(actualState));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Optional<Transition> transition = processMachine();
        transition.ifPresent(this::drawUpdate);
    }

    private Optional<Transition> processMachine(){
        CellCoordinate cellCoordinate = coordinateList.get(index);

        return turingMachine.findTransitionByActualStateAndReadSymbol(actualState, cellCoordinate.getSymbol().getCharacter());
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

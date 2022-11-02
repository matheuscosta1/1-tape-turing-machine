package br.com.turing.machine.service;

import br.com.turing.machine.domain.*;
import br.com.turing.machine.validator.TuringMachineValidator;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
public class DrawTuringMachine extends JPanel implements ActionListener {
    private static final int HEIGHT = 20;
    private static final int WIDTH = 20;
    private static final int FONT_SIZE = 20;
    private static final int QUANTITY_OF_TAPE_CELL = 74;
    public static final String PROCESSOR_EVENT = "Processar";
    private final String arrowImageFilePath = "classpath:images/arrow.png";

    String actualState;

    Integer index = 0;

    ArrayList<CellTape> tape = new ArrayList<>();
    TuringMachine turingMachine;

    ResourceLoader resourceLoader = new DefaultResourceLoader();

    ReadTuringMachineTransitions readTuringMachineTransitions = new ReadTuringMachineTransitions();
    ImageIcon arrowImageIcon = new ImageIcon(new ImageIcon(resourceLoader.getResource(arrowImageFilePath).getURL()).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    JButton processorButton = new JButton("Processar");
    JButton inputButton = new JButton("Enviar");
    JButton skipProcessingButton = new JButton("Pular processamento");
    JTextField userInputWord;
    JLabel arrow;
    JTextField drawActualState;

    DrawTuringMachine() throws Exception {
        setLayout(null);

        String inputFilePath = "classpath:entrada/maquina-2.json";
        turingMachine = readTuringMachineTransitions.readFile(inputFilePath);
        actualState = turingMachine.getInitialState();

        drawActualState = new JTextField("", 30);
        drawActualState.setEnabled(false);

        arrow = new JLabel(arrowImageIcon, SwingConstants.CENTER);

        userInputWord = new JTextField("", 30);
        userInputWord.setBounds(100, 500, 150, 100);
        userInputWord.setSize(new Dimension(300, 50));
        userInputWord.setToolTipText("Entre com uma palavra para ser processada.");

        inputButton.setBounds(350, 600, 200, 100);
        inputButton.addActionListener(
                e -> {
                    try {
                        userInputWordSubmitAction();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );

        processorButton.setBounds(100, 600, 200, 100);
        processorButton.setEnabled(false);
        processorButton.addActionListener(this);

        skipProcessingButton.setBounds(100, 730, 200, 100);
        skipProcessingButton.setEnabled(false);
        skipProcessingButton.addActionListener(this);

        add(arrow);
        add(userInputWord);
        add(inputButton);
        add(processorButton, BorderLayout.SOUTH);
        add(skipProcessingButton);
        add(drawActualState);
    }

    private void userInputWordSubmitAction() throws IOException {
        TuringMachineValidator turingMachineValidator = new TuringMachineValidator(turingMachine);

        inputButton.setEnabled(false);
        userInputWord.setEnabled(false);

        String initialSymbol = turingMachine.getStartMaker();
        String word = userInputWord.getText();

        if(!turingMachineValidator.isValidWord(word)) {
            JOptionPane.showMessageDialog(null, "Algum símbolo da cadeia não pertence ao alfabeto.");
            System.exit(0);
        }

        String wordWithInitialState = initialSymbol != null ? initialSymbol.concat(word) : word;

        int quantityOfBlankSymbols = QUANTITY_OF_TAPE_CELL - wordWithInitialState.length();

        String wordWithBlankSymbols = wordWithInitialState.concat("B".repeat(quantityOfBlankSymbols));

        tapeDraw(wordWithBlankSymbols);
    }

    public void tapeDraw(String word) {

        Graphics graphics = getGraphics();

        graphics.setFont(new Font("", Font.PLAIN, FONT_SIZE));

        int axisX = 100;
        int axisY = 100;

        for (int iterator = 0; iterator < word.length(); iterator++) {
            char actualCharacter = word.charAt(iterator);

            graphics.drawRect(axisX, axisY, WIDTH, HEIGHT);

            int drawStringYAxis = axisY + HEIGHT;

            graphics.drawString(String.valueOf(actualCharacter), axisX, drawStringYAxis);

            if (tape.size() < word.length()) {
                saveEachCellTapeCoordinate(axisX, axisY, actualCharacter, drawStringYAxis);
            }

            axisX = movesOnAxisXForEachCell(axisX);
        }

        graphics.drawImage(arrowImageIcon.getImage(), 100, 130, null);

        drawBeginningActualState();

        processorButton.setEnabled(true);
        skipProcessingButton.setEnabled(true);
    }

    private void saveEachCellTapeCoordinate(int axisX, int axisY, char actualCharacter, int drawStringYAxis) {
        tape.add(
                CellTape
                        .builder()
                        .xAxis(axisX)
                        .yAxis(axisY)
                        .drawStringYAxis(drawStringYAxis)
                        .symbol(Symbol.builder().character(String.valueOf(actualCharacter)).build())
                        .width(WIDTH)
                        .height(HEIGHT)
                        .build()
        );
    }

    private void drawBeginningActualState() {
        drawActualState.setText("Actual state: ".concat(actualState));
        drawActualState.setBounds(100, 200, 130, 50);
    }

    private static int movesOnAxisXForEachCell(int axisX) {
        axisX = axisX + WIDTH;
        return axisX;
    }

    public void updateTapeDraw(Transition transition) {
        Graphics graphics = getGraphics();
        graphics.setFont(new Font("", Font.PLAIN, FONT_SIZE));

        CellTape cellTapeCoordinate = tape.get(index);

        clearOldRectTapeAndDrawNewRectWithNewSymbol(transition, graphics, cellTapeCoordinate);

        movesToTheLeftOrTheRightOnTapeBasedOnTransactionDirection(transition, graphics, cellTapeCoordinate);

        actualState = transition.getDestinyState();

        updateActualSymbolReadFromTapeInTapeCoordinateList(transition, cellTapeCoordinate);

        drawActualState.setText("Actual state: ".concat(actualState));

    }

    private void movesToTheLeftOrTheRightOnTapeBasedOnTransactionDirection(Transition transition, Graphics graphics, CellTape cellTapeCoordinate) {
        if(Direction.RIGHT.equals(transition.getDirection())) {
            graphics.clearRect(cellTapeCoordinate.getXAxis(), 130, WIDTH, HEIGHT);
            graphics.drawImage(arrowImageIcon.getImage(), cellTapeCoordinate.getXAxis() + WIDTH, 130, null);
            index += 1;
        } else {
            graphics.clearRect(cellTapeCoordinate.getXAxis(), 130, WIDTH, HEIGHT);
            graphics.drawImage(arrowImageIcon.getImage(), cellTapeCoordinate.getXAxis() - WIDTH, 130, null);
            index -= 1;
        }
    }

    private static void clearOldRectTapeAndDrawNewRectWithNewSymbol(Transition transition, Graphics graphics, CellTape cellTapeCoordinate) {
        graphics.clearRect(cellTapeCoordinate.getXAxis(), cellTapeCoordinate.getYAxis(), WIDTH, HEIGHT);

        graphics.drawRect(cellTapeCoordinate.getXAxis(), cellTapeCoordinate.getYAxis(), WIDTH, HEIGHT);

        graphics.drawString(transition.getWriteSymbol(), cellTapeCoordinate.getXAxis(), cellTapeCoordinate.getDrawStringYAxis());
    }

    private static void updateActualSymbolReadFromTapeInTapeCoordinateList(Transition transition, CellTape cellTapeCoordinate) {
        cellTapeCoordinate.setSymbol(Symbol.builder().character(transition.getWriteSymbol()).build());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(PROCESSOR_EVENT)) {
            Optional<Transition> transition = processMachine();
            processTuringMachineOneStepPerTime(transition);
        } else {
            processorButton.setEnabled(false);
            skipProcessingButton.setEnabled(false);

            processTuringMachineStepByStepTillTheEnd();
        }
    }

    private void processTuringMachineOneStepPerTime(Optional<Transition> transition) {
        if(transition.isPresent()) {
            transition.ifPresent(this::updateTapeDraw);
        } else {
            validateTuringMachineAcceptsWord();
        }
    }

    private void processTuringMachineStepByStepTillTheEnd() {
        new Thread(() -> {
            Optional<Transition> transition = processMachine();

            while (transition.isPresent()) {
                transition = processMachine();
                transition.ifPresent(this::updateTapeDraw);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            validateTuringMachineAcceptsWord();
        }).start();
    }

    private void validateTuringMachineAcceptsWord() {
        if(turingMachine.hasFinalStates()) {
            if(turingMachine.isWordAccepted(actualState)) {
                JOptionPane.showMessageDialog(null, "A cadeia foi aceita.");
            } else {
                JOptionPane.showMessageDialog(null, "A cadeia foi rejeitada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O processamento terminou.");
        }

    }

    private Optional<Transition> processMachine(){
        CellTape cellTapeCell = tape.get(index);

        return turingMachine.findTransitionByActualStateAndReadSymbol(actualState, cellTapeCell.getSymbol().getCharacter());
    }

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Máquina de Turing");

        frame.add(new DrawTuringMachine());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1600, 900));

    }
}

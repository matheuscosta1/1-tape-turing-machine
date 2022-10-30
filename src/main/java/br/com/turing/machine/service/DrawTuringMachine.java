package br.com.turing.machine.service;

import br.com.turing.machine.domain.CellCoordinate;
import br.com.turing.machine.domain.Symbol;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Service
public class DrawTuringMachine extends JPanel implements ActionListener {

  JButton processorButton = new JButton("Processar");
  JButton inputButton = new JButton("Enviar");

  JTextField userInput;

  ArrayList<CellCoordinate> coordinateList = new ArrayList<>();
  DrawTuringMachine() {
    setLayout(null);

    userInput = new JTextField("", 30);

    userInput.setBounds(100, 500, 150,100);

    userInput.setSize(new Dimension(300, 50));

    inputButton.setBounds(300, 600, 150,100);

    inputButton.addActionListener(
            e -> submitAction()
    );


    processorButton.setBounds(100, 600, 150,100);
    processorButton.addActionListener(this);

    add(processorButton, BorderLayout.SOUTH);
    add(userInput);
    add(inputButton);

  }

  private void submitAction() {
    String word = userInput.getText();
    inputButton.setEnabled(false);
    userInput.setEnabled(false);
    draw(word);
  }

  public void draw(String word) {
    Graphics graphic = getGraphics();

    graphic.setFont(new Font("", Font.PLAIN,30));
    FontMetrics fontMetrics = getFontMetrics(new Font("", Font.PLAIN,30));
    int axisX = 100;
    int axisY = 200;

    for (int iterator = 0; iterator < word.length(); iterator++) {
      char actualCharacter = word.charAt(iterator);
      int height = fontMetrics.getHeight();
      int width = fontMetrics.charWidth(actualCharacter);

      graphic.drawRect(axisX, axisY, width, height);

      int drawStringYAxis = axisY + height;

      graphic.drawString(String.valueOf(actualCharacter), axisX, drawStringYAxis);

      if(coordinateList.size() < word.length()) {
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
  }

  public void update() {
    Graphics graphics = getGraphics();
    graphics.setFont(new Font("", Font.PLAIN,30));

    graphics.clearRect(100, 200, 29, 36);

    graphics.drawRect(100, 200, 29, 36);

    graphics.drawString("b", 100, 236);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    update();
  }

  public static void main(String[] args) {

    JFrame frame = new JFrame("Máquina de Turing");

    frame.add(new DrawTuringMachine());
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(1600, 900));

  }
}

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package QandE;

/*
 * This is a 1.3 version (thanks to JFrame.EXIT_ON_CLOSE)
 * but could easily be converted to work in earlier releases.
 * There are many valid ways to implement the exercise.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingApp3 {
    JFrame f;
    JButton startButton, stopButton;
    JLabel label;

    public SwingApp3() {
        f = new JFrame("SwingApp3");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //1.3+
        createGUI(f);
        initListeners();
        f.pack();
        f.setVisible(true);
    }

    void initListeners() {
        ActionListener al = new MyActionListener();
        startButton.addActionListener(al);
        stopButton.addActionListener(al);
    }

    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                if (userOKs()) {
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    label.setText("Press Stop.");
                } else {
                    label.setText("Start canceled.");
                }
            } else {
                // e.getSource() == stopButton
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
                label.setText("Press Start.");
            }
        }

        boolean userOKs() {
            Object[] options = {"Go ahead", "Cancel"};
            int n = JOptionPane.showOptionDialog(
                      f, 
                      "Really start the job?\n"
                      + "It's going to take a long time.",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION,
                      JOptionPane.QUESTION_MESSAGE,
                      null,
                      options,
                      options[0]);
            if (n == JOptionPane.YES_OPTION) {
                return true;
            }
            return false;
        }
    }

    void createGUI(JFrame f) {
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        label = new JLabel("Press Start.", JLabel.CENTER);

        Container c = f.getContentPane();
        //Use the content pane's default BorderLayout layout manager.
        c.add(startButton, BorderLayout.WEST);
        c.add(stopButton, BorderLayout.EAST);
        c.add(label, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new SwingApp3();
    }
}

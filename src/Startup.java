import javafx.scene.media.MediaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Startup {
    public static void main(String[] args){
        JFrame mainWindow = new JFrame();
        JPanel buttonPane = new JPanel(new BorderLayout());
        JPanel buttonSubPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JFileChooser browseFiles = new JFileChooser();
        browseFiles.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        buttonPane.add(buttonSubPane,BorderLayout.CENTER);
        //buttonPane.add(new JScrollBar(),BorderLayout.EAST);
        //buttonPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        DimensionData dimensions = new DimensionData();
        JPanel dimensionPanel = new JPanel(new GridLayout(1,5));
        JButton updateButton = new JButton("Update");
        JTextField xDimension = new JTextField();
        JTextField yDimension = new JTextField();
        JTextField width = new JTextField("Width: ");
        JTextField height = new JTextField("Height: ");
        JButton addButton = new JButton("+");
        addButton.setPreferredSize(new Dimension(dimensions.getWidth(),dimensions.getHeight()));
        buttonSubPane.add(addButton);
        width.setEditable(false);
        height.setEditable(false);
        width.setBorder(BorderFactory.createEmptyBorder());
        height.setBorder(BorderFactory.createEmptyBorder());
        dimensionPanel.add(width);
        dimensionPanel.add(xDimension);
        dimensionPanel.add(height);
        dimensionPanel.add(yDimension);
        dimensionPanel.add(updateButton);
        mainWindow.add(dimensionPanel,BorderLayout.SOUTH);
        mainWindow.add(buttonPane,BorderLayout.CENTER);
        ArrayList<MusicPanel> groups = new ArrayList<MusicPanel>();

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dimensions.set(Integer.parseInt(xDimension.getText()), Integer.parseInt(yDimension.getText()));
                }catch(NumberFormatException e1){

                }
                addButton.setPreferredSize(new Dimension(dimensions.getWidth(),dimensions.getHeight()));
                for(MusicPanel panes : groups){
                    panes.setPreferredSize(new Dimension(dimensions.getWidth(),dimensions.getHeight()));
                }
                mainWindow.repaint();
            }
        } );
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSubPane.remove(addButton);
                browseFiles.showOpenDialog(null);
                try {
                    MusicPanel meds = new MusicPanel(dimensions, browseFiles.getSelectedFile());
                    meds.addListener(new MusicPanelListener(meds) {
                        public void actionPerformed(ActionEvent e) {
                            buttonSubPane.remove(this.getDelReference());
                            groups.remove(this.getDelReference());
                            mainWindow.repaint();
                        }
                    });
                    buttonSubPane.add(meds);
                    groups.add(meds);
                } catch(NullPointerException e1){
                } catch(MediaException e1){
                }
                buttonSubPane.add(addButton);
                mainWindow.repaint();
            }
        } );
        mainWindow.setVisible(true);
    }
}

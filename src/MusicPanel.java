import javax.swing.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MusicPanel extends JPanel {
    private JButton startButton;
    private JButton stopButton;
    private JButton delButton;
    private MediaPlayer music;
    private int width;
    private int height;
    private boolean playing;
    private DimensionData data;
    public MusicPanel(DimensionData data, File musical){
        super(new GridBagLayout());
        new JFXPanel();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        MediaPlayer music = new MediaPlayer(new Media(musical.toURI().toString()));
        GridBagConstraints constraints = new GridBagConstraints();
        startButton= new JButton();
        stopButton = new JButton();
        delButton = new JButton();
        startButton.setPreferredSize(new Dimension((int)(data.getWidth()*.7),(int)(data.getHeight()*.35)));
        stopButton.setPreferredSize(new Dimension((int)(data.getWidth()*.7),(int)(data.getHeight()*.35)));
        delButton.setPreferredSize(new Dimension((int)(data.getWidth()*.2),(int)(data.getHeight()*.2)));
        constraints.gridx=1;
        constraints.gridy=0;
        super.add(delButton,constraints);
        constraints.gridx=0;
        constraints.gridy=1;
        super.add(startButton,constraints);
        constraints.gridy=2;
        super.add(stopButton,constraints);
        startButton.setText("|>");
        stopButton.setText("[]");
        delButton.setText("X");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(playing) {
                    music.play();
                    startButton.setText("||");
                }else{
                    music.pause();
                    startButton.setText("|>");
                }
                playing=!playing;
            }
        } );
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                music.stop();
                startButton.setText("|>");
            }
        } );
    }
    public void addListener(ActionListener delListener){
        delButton.addActionListener(delListener);
    }
    @Override
    public void setPreferredSize(Dimension preferredSize){
        super.setPreferredSize(preferredSize);
        startButton.setPreferredSize(new Dimension((int)(preferredSize.getWidth()*.7),(int)(preferredSize.getHeight()*.35)));
        stopButton.setPreferredSize(new Dimension((int)(preferredSize.getWidth()*.7),(int)(preferredSize.getHeight()*.35)));
        delButton.setPreferredSize(new Dimension((int)(preferredSize.getWidth()*.2),(int)(preferredSize.getHeight()*.2)));
    }
}

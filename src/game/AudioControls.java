package game;

import city.cs.engine.SoundClip;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioControls {
    private JPanel panel1;
    private JLabel volLabel;
    private JSlider volSlider;
    private JButton mute;
    private SoundClip sound =  MyView.getBgMusic();


    public AudioControls() {

        double volume =1;
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //setting the mute button to no sound.
                sound.setVolume(0.0001);
                volSlider.setValue(1);
            }
        });

        volSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float volume = scale((float)volSlider.getValue(),1.0f,10.0f,0.0001f,2.0f);
                sound.setVolume(volume);
            }
        });
    }

    private float scale(float value, float minInput, float maxInput,
                        float minRange, float maxRange){
        return ((maxRange-minRange)*(value-minInput)/(maxInput-minInput)) + minRange;

    }

    public JPanel getPanelControls() {
        return panel1;
    }
}

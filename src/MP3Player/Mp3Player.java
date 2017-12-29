package MP3Player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Mp3Player {
    private Clip audioClip;
    private static Mp3Player uniqueInstance;
    private static String pathAnterior;
    public static Mp3Player getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new Mp3Player();
        return uniqueInstance;
    }
    public void play(String path) {
        try {
            pathAnterior = path;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }


    public void close(){
        audioClip.close();
    }

    public void changeVolume(float gain){
        FloatControl gainControl =
                (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(gain);
    }

    public void playAnterior(){
        if(!pathAnterior.equals(null)){
            play(pathAnterior);
        }
    }
}

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
    private static Boolean isPlaying;

    /**
     * @return Instancia de Mp3Player.
     */
    public static Mp3Player getInstance() {
        if(uniqueInstance == null)
            uniqueInstance = new Mp3Player();
        return uniqueInstance;
    }

    /**
     * Reproduce una canción a partir de un path del sistema de archivos del usuario.
     * @param path path de la canción
     */
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
            isPlaying = true;

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            //ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            //ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            //ex.printStackTrace();
        }
    }

    /**
     * Parar la reproducción de la canción que se estaba reproduciendo.
     */
    public void close(){
        if(isPlaying()) {
            audioClip.close();
            isPlaying = false;
        }
    }

    /**
     * Varia el volumen de la canción a partir del masterGain. En algunas distros de linux Java no deja.
     * Como no podemos utilizar librerias externas, esta es la única solución factible.
     * @param gain cambio que se desea en el volumen de la música.
     */
    public void changeVolume(float gain){
        if(audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl =
                    (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain);
        }
    }

    /**
     * Reproduce la canción con el path que se habia cargado en primera instáncia.
     */
    public void playAnterior(){
        if(!pathAnterior.equals(null)){
            play(pathAnterior);
        }
    }

    /**
     * Devuelve si está sonando
     * @return cierto si está sonando, falso si no;
     */
    public boolean isPlaying(){
        return isPlaying;
    }
}

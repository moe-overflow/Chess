package control;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;


public class MusicPlayer extends Thread
{
    private static final String MUSIC_FILE = "file:./src/utility/assets/KarpovNotKasparov-DeepFritz.mp3";

    @Override
    public void run()
    {
        playMusic();
    }

    protected void playMusic()
    {
        Media media = new Media(new File(MUSIC_FILE).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia( () -> mediaPlayer.seek(Duration.ZERO)); // repeat when finished
        mediaPlayer.play();
    }

    private static class AudioHolder
    {
        private static final MusicPlayer INSTANCE = new MusicPlayer();
    }

    public static MusicPlayer getInstance()
    {
        return AudioHolder.INSTANCE;
    }


}

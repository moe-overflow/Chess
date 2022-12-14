package control;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;


public class MusicPlayer extends Thread
{
    private static final String MUSIC_FILE =
    "C:\\Dev\\Java\\various projects\\Chess\\src\\main\\java\\resources\\KarpovNotKasparov-DeepFritz.mp3";

    //    private static final String MUSIC_FILE =
    //        "C:\\Coding\\Java\\various projects\\Chess\\src\\main\\java\\resources\\preview.mp3";

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

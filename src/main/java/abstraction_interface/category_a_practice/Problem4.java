package abstraction_interface.category_a_practice;

interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private static int nextId = 1000;
    private final String fileId;

    protected MediaFile() {
        fileId = "MF-" + (++nextId);
    }

    public String getFileId() {
        return fileId;
    }

    public abstract String getFormatInfo();
}

class AudioFile extends MediaFile implements Playable {
    private final String title;

    public AudioFile(String title) {
        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        return "Playing audio: " + title + " from 0:" + fromSecond;
    }

    @Override
    public String pause() {
        return "Paused audio: " + title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {
    private final String showName;
    private final int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        if (episodeNumber <= 0) {
            throw new IllegalArgumentException("Episode must be positive");
        }
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return play() + " from 0:" + fromSecond;
    }

    @Override
    public String pause() {
        return "Paused " + showName;
    }
}

public class Problem4 {
    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        AudioFile audio = new AudioFile("Morning Jazz");
        Podcast podcast = new Podcast("Tech Talk", 12);

        System.out.println(audio.play());
        System.out.println(audio.play(30));
        System.out.println(audio.getFormatInfo());
        System.out.println(podcast.play());

        Playable ref = audio;
        System.out.println(ref.play());
        launchAll(new Playable[]{ref, podcast});
    }
}

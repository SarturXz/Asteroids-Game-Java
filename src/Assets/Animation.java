package Assets;

import Utils.Timer;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int speed;
    private int index;
    private boolean isPlaying;
    private boolean loopeable;

    private final Timer timer;

    public Animation(BufferedImage[] frames, int speed, int startIndex, boolean startPlaying, boolean loopeable) {
        this.frames = frames;
        this.speed = speed;
        this.index = startIndex;
        this.isPlaying = startPlaying;
        this.loopeable = loopeable;

        timer = new Timer();
    }

    public void update(){
        timer.update();
        if (timer.isRunning()) return;
        timer.run(speed);

        if (!isPlaying) return;

        index++;
        if (index >= frames.length){
            if (loopeable) index = 0;
            else isPlaying = false;
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public BufferedImage[] getFrames() {
        return frames;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isLoopeable() {
        return loopeable;
    }

    public void setLoopeable(boolean loopeable) {
        this.loopeable = loopeable;
    }
}

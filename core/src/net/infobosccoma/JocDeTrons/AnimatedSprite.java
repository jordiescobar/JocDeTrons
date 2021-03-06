package net.infobosccoma.JocDeTrons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class que implementa un Sprite animat
 *
 * Created by Marc
 */
public class AnimatedSprite {

    //<editor-fold desc="Atributs">
     /* Enumeració per les direccions
     */
    public enum Direction {LEFT, RIGHT, STOPPED_FACE_LEFT, STOPPED_FACE_RIGHT};

    private Sprite sprite;
    private Animation animation;
    private TextureRegion[] framesLeft, framesRight;
    private Texture frame;
    private int textureCols, textureRows;
    private Direction direction;

    private float stateTime;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    /**
     * Constructor
     *
     * @param sprite        sprite associat al personatge
     * @param textureCols   columnes de la textura
     * @param textureRows   files de la textura
     * @param stoppedTexture textura a utilitzar quan el personatge està aturat
     */
    public AnimatedSprite(Sprite sprite, int textureCols, int textureRows, Texture stoppedTexture) {
        Texture framesTexture = sprite.getTexture();
        TextureRegion[][] tmp = TextureRegion.split(framesTexture,
                                                    framesTexture.getWidth() / textureCols,
                                                    framesTexture.getHeight() / textureRows);

        this.sprite = sprite;
        this.textureCols = textureCols;
        this.textureRows = textureRows;
        frame = stoppedTexture;
        direction = Direction.STOPPED_FACE_RIGHT;

        framesLeft = new TextureRegion[textureCols];
        for (int j = 0; j < textureCols; j++) {
            framesLeft[j] = tmp[1][j];
        }

        framesRight = new TextureRegion[textureCols];
        for (int j = 0; j < textureCols; j++) {
            framesRight[j] = tmp[0][j];
        }

        animation = new Animation(0.25f, framesRight);
        stateTime = 0f;
    }
    //</editor-fold>

    //<editor-fold desc="Mètodes d'instància">
    /**
     * Dibuixar l'sprite
     *
     * @param spriteBatch
     */
    public void draw(SpriteBatch spriteBatch) {
        if (direction == Direction.STOPPED_FACE_RIGHT) {

            spriteBatch.draw(frame, sprite.getX(), sprite.getY());
        } else {
            stateTime += Gdx.graphics.getDeltaTime() * 2;
            spriteBatch.draw(animation.getKeyFrame(stateTime, true), sprite.getX(), sprite.getY());
        }
    }
    //</editor-fold>

    //<editor-fold desc="Getters / Setters">
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        if (direction == Direction.LEFT) {
            animation = new Animation(0.25f, framesLeft);
        } else {
            animation = new Animation(0.25f, framesRight);
        }
    }


    public Direction getDirection() {
        return direction;
    }
    //</editor-fold>

}
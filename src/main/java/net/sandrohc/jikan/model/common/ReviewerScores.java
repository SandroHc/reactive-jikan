/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

/**
 * The scores a reviewer gave.
 */
public class ReviewerScores implements Serializable {

    /** The overall score. */
    public int overall;

    /** The score for the story. */
    public int story;

    /** The score for the art. Exclusive to manga. */
    public int art;

    /** The score for the animation. Exclusive to anime. */
    public int animation;

    /** The score for the sound. Exclusive to anime. */
    public int sound;

    /** The score for the character development. */
    public int character;

    /** The score for the enjoyment. */
    public int enjoyment;


    @Override
    public String toString() {
        return "ReviewerScores[overall=" + overall + ", story=" + story + ", art=" + art + ", animation=" + animation +
                ", sound=" + sound + ", character=" + character + ", enjoyment=" + enjoyment + ']';
    }

}

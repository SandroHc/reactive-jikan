/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

public class ReviewerScores implements Serializable {

    public int overall;

    public int story;

    public int art; // manga only

    public int animation; // anime only

    public int sound; // anime only

    public int character;

    public int enjoyment;


    @Override
    public String toString() {
        return "ReviewerScores[overall=" + overall + ", story=" + story + ", art=" + art + ", animation=" + animation +
                ", sound=" + sound + ", character=" + character + ", enjoyment=" + enjoyment + ']';
    }

}

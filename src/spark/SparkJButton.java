package spark;

import javax.swing.*;
import java.awt.*;

/**
 * Most common used button for the Spark Frame
 */
public class SparkJButton extends JButton
{
    private final float[] hsb = {0.0f, 0.0f, 0.93333334f};     //HSB value of the background colour

    /**
     * Constructor
     */
    public SparkJButton(String text, int fontsize)
    {
        super(text);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        this.setFont(new Font("VCR OSD MONO", Font.PLAIN, fontsize));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mumde.cs545;

/**
 *
 * @author Natnael
 */
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class Label extends SimpleTagSupport {

    String theColor;
    String words;   //This is called from JSP servlet to render custom tag   

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (theColor != null) {
            out.write(String.format("<span style='color:%s'>%s</span>", theColor, words));
        } else {
            out.write(String.format("<span>%s</span>", words));
        }
    }   //Need a setter for each attribute of custom tag   

    public void setTheColor(String theColor) {
        this.theColor = theColor;
    }

    public void setWords(String words) {
        this.words = words;
    }
}

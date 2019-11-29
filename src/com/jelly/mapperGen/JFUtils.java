package com.jelly.mapperGen;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFUtils {
    public static void positionCenter(JFrame f){
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fsize = f.getSize();
		
		if (fsize.height > screenSize.height){
			fsize.height = screenSize.height;
		}
		if (fsize.width > screenSize.width){
			fsize.width = screenSize.width;
		}
		f.setLocation((screenSize.width - fsize.width) / 2    ,(screenSize.height - fsize.height ) / 2  );
    }
}

 
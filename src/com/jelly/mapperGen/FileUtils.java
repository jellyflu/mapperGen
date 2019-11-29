package com.jelly.mapperGen;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
public class FileUtils {
	/**
     * 判断文件的编码格式
     * @param fileName  文件名字符串
     * @return 文件编码格式
     * @throws Exception
     */
    public static String getFileEncoding(String fileName) throws Exception{
        BufferedInputStream bin = null;
			try {
			 bin=	new BufferedInputStream( new FileInputStream(fileName));
		      int p = (bin.read() << 8) + bin.read();
		      String  code = null;
		      switch (p) {
						case 0xefbb:
						    code = "UTF-8";
						    break;
						case 0xfffe:
						    code = "Unicode";
						    break;
						case 0xfeff:
						    code = "UTF-16BE";
						    break;
						default:
						    code = "GBK";
		      }
		      return code;
			} finally{
				if(bin!=null){
					bin.close();
				}
			}
    }
}
package com.taobao.datax.plugins.reader.ftpreader;

import java.io.*;

public class TextFile {

    /**
     * 写入文件
     * */
    public static File write2File(String fileName,String content) {
        return write2File(new File(fileName),content);
    }
    public static File write2File(String fileName,String content,String charset) {
        return write2File(new File(fileName),content,charset);
    }
    public static File write2File(File file,String content){
        return write2File(file,content,"UTF-8");
    }
 	public static File write2File(File file,String content,String charset){
        FileOutputStream writerStream =null;
        BufferedWriter oWriter = null;
		try{

            writerStream = new FileOutputStream(file, true);
            oWriter = new BufferedWriter(new OutputStreamWriter(writerStream, charset));
            oWriter.append(content);
            oWriter.append('\n');
            oWriter.flush();
        }catch (Exception e) {
            throw new RuntimeException("write to file error",e);
        }finally {
            try{
                if(oWriter != null){

                    oWriter.close();
                }
                if(writerStream != null) {
                    writerStream.close();
                }
            }catch (Exception e) {
                throw new RuntimeException("close stream error",e);
            }


        }

		return file;
	}
    /**
     * 读取文件
     * */
    public static String readFile(File file)  throws Exception{
        StringBuffer content = new StringBuffer();
        InputStreamReader isr  = null;
        BufferedReader br = null;
        try {

            isr = new InputStreamReader(new FileInputStream(file), "UTF-8");

            br  = new BufferedReader(isr);
            String line = br.readLine();
            while(line != null) {
                content.append(line);
                content.append("\n");
                line = br.readLine();
            }
        }catch (Exception e){
            throw e;
        } finally {
            if(br != null) {
                br.close();
            }
            if(isr != null) {
                isr.close();
            }
        }



        return content.toString();

    }

	public static String getWebRootPath() {
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
		int index = path.lastIndexOf("WEB-INF");
		return File.separator + path.substring(0, index);
	}

	public static void main(String args[]) {
        TextFile.write2File("/data1/test/textfile","a");
        TextFile.write2File("/data1/test/textfile", "b");
    }

}

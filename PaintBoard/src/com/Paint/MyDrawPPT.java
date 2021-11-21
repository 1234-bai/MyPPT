package com.Paint;

import java.io.*;
import java.util.ArrayList;

public class MyDrawPPT extends MyDrawPPTIml{
    @Override
    public boolean loadPPT() {
        File file = DrawJPanelFileUtil.choseFile("myppt", "载入...", "确定", "open");
        if(file==null){
            return false;
        }
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String data;
            DrawJPanel drawBoard;
            ArrayList<String> strings = new ArrayList<>();

            //按行读文件
            while((data = bufferedReader.readLine())!=null){
                if(!data.equals("end")){
                    strings.add(data);
                }else{
                    drawBoard = DrawJPanelFileUtil.toDrawBoard(strings);
                    myPPT.add(drawBoard);
                    strings=new ArrayList<>();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean savePPT() {

        //生成整个PPT的String
        StringBuilder stringBuilder = new StringBuilder();
        for(DrawJPanel drawJPanel:myPPT){
            stringBuilder.append(drawJPanel.toString());
            stringBuilder.append("end\r\n");    //end作为一页ppt结束的标识
        }

        //存入文件中
        File file = DrawJPanelFileUtil.choseFile("myppt","另存为...","确定","save");
        if (file == null) {
            return false;
        }
        if (!file.getPath().endsWith(".myppt")) {
            file = new File(file.getPath() + ".myppt");
        }
        try {
            if (!file.exists()) {
                file.createNewFile();   //文件不存在则新建文件
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush(); //把缓冲区内容压入文件
            bufferedWriter.close(); //关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

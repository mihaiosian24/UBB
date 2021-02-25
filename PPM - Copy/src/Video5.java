import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video5 implements Runnable{
    public Video5 video;
    public ColorImage colorImage;
    public String name;
    public String file1;
    public String output1;
    public String file2;
    public String output2;
    public String file3;
    public String output3;
    public String file4;
    public String output4;
    public String file5;
    public String output5;
    public String file6;
    public String output6;
    public Thread thread;
    public int check=0;
    public Map<Integer,double[][]> blocks;
    public Object latch = new Object();
    public Object fwdlatch = new Object();
    public double o;
    public double[] cosinee;
    public double cc;
    public double cccc;
    Video5(){}


    Video5(ColorImage colorImage, String name, String file, String output,String file2, String output2,String file3, String output3,String file4, String output4,String file5, String output5,String file6, String output6, double o, double[] cosinee, double cc, double cccc){

        this.colorImage=colorImage;
        this.name=name;
        this.file1=file;
        this.output1=output;

        this.file2=file2;
        this.output2=output2;

        this.file3=file3;
        this.output3=output3;

        this.file4=file4;
        this.output4=output4;

        this.file5=file5;
        this.output5=output5;

        this.file6=file6;
        this.output6=output6;


        //this.ppm=new Ppm();
        blocks=new HashMap<>();
        //lock=new Object();
        latch=new Object();
        fwdlatch=new Object();
        this.o=o;
        this.cosinee=cosinee;
        this.cc=cc;
        this.cccc=cccc;
    }
    public void setVideo2(Video5 video) throws IOException {
        if(this.video!=null){
            this.video.setVideo2(video);
        }else {this.video=video;
            this.video.latch=fwdlatch;

        }
    }

    protected void writeLine(BufferedWriter out, String line) throws Exception {

        out.write(line);
        out.newLine();
    }

    public void color(){
        this.video.colorImage=colorImage;
    }
    @Override
    public void run() {

        double[][] GY=new double[8][8];
        double[][] GU=new double[8][8];
        double[][] GV=new double[8][8];
        int ce=0;
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file1))));
            int y=0;
            int x=0;
            int check=0;

            long start=System.currentTimeMillis();
            for(String string : lines) {

                check++;

                try {

                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);

                    boolean checkk = true;
                    while (checkk) {

                        try {
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);


                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);

                        } catch (Exception e) {


                            checkk = false;

                        }
                    }


                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

                        block = colorImage.decodeQuantBlock(block);

                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }

                    int c = ce % 3;
                    if (c == 0) GY = block;
                    if (c == 1) GU = block;

                    if (c == 2) {

                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }

                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }



                    ce++;

                }catch (Exception e){
                    System.out.println(ce%3);
                    for(int i=0;i<8;i++)
                        for(int j=0;j<8;j++){
                            System.out.println(GY[i][j]);
                        }


                    System.out.println(string);

                    e.printStackTrace();
                    System.out.println(e.getCause());

                    System.out.println(file1);
                    System.out.println(x);
                    System.out.println(y);
                }



            }
            long end=System.currentTimeMillis();

        }  catch (Exception e) {
            e.printStackTrace();
        }

        int i = 0;


        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {

            newppm3.writeFile(output1, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        colorImage=new ColorImage(colorImage.width,colorImage.height);




        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file2))));
            int y=0;
            int x=0;
            int check=0;

            long start=System.currentTimeMillis();
            for(String string : lines) {

                check++;


                try {

                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);

                    boolean checkk = true;
                    while (checkk) {

                        try {
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);


                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);

                        } catch (Exception e) {

                            checkk = false;

                        }
                    }

                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

                        block = colorImage.decodeQuantBlock(block);

                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }

                    int c = ce % 3;
                    if (c == 0) GY = block;
                    if (c == 1) GU = block;
                    if (c == 2) {

                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }

                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }


                    ce++;

                }catch (Exception e){
                    System.out.println(ce%3);
                    for( i=0;i<8;i++)
                        for(int j=0;j<8;j++){
                            System.out.println(GY[i][j]);
                        }


                    System.out.println(string);

                    e.printStackTrace();
                    System.out.println(e.getCause());

                    System.out.println(file2);
                    System.out.println(x);
                    System.out.println(y);
                }


            }
            long end=System.currentTimeMillis();


        }  catch (Exception e) {
            e.printStackTrace();
        }


        i = 0;

        Ppm newppm4 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {

            newppm3.writeFile(output2, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }


        colorImage=new ColorImage(colorImage.width,colorImage.height);

        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file3))));
            int y=0;
            int x=0;
            int check=0;

            long start=System.currentTimeMillis();
            for(String string : lines) {

                check++;

                try {

                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);

                    boolean checkk = true;
                    while (checkk) {

                        try {
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);


                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);

                        } catch (Exception e) {


                            checkk = false;

                        }
                    }

                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

                        block = colorImage.decodeQuantBlock(block);

                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }


                    int c = ce % 3;
                    if (c == 0) GY = block;
                    if (c == 1) GU = block;

                    if (c == 2) {

                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }

                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }



                    ce++;

                }catch (Exception e){
                    System.out.println(ce%3);
                    for( i=0;i<8;i++)
                        for(int j=0;j<8;j++){
                            System.out.println(GY[i][j]);
                        }


                    System.out.println(string);

                    e.printStackTrace();
                    System.out.println(e.getCause());

                    System.out.println(file3);
                    System.out.println(x);
                    System.out.println(y);
                }



            }
            long end=System.currentTimeMillis();


        }  catch (Exception e) {
            e.printStackTrace();
        }

        i = 0;


        Ppm newppm5 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {

            newppm3.writeFile(output3, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }

        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file4))));
            int y=0;
            int x=0;
            int check=0;

            long start=System.currentTimeMillis();
            for(String string : lines) {

                check++;


                try {

                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);

                    boolean checkk = true;
                    while (checkk) {

                        try {
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);


                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);

                        } catch (Exception e) {

                            checkk = false;

                        }
                    }

                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

                        block = colorImage.decodeQuantBlock(block);

                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }

                    int c = ce % 3;
                    if (c == 0) GY = block;
                    if (c == 1) GU = block;

                    if (c == 2) {

                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }

                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }



                    ce++;

                }catch (Exception e){
                    System.out.println(ce%3);
                    for( i=0;i<8;i++)
                        for(int j=0;j<8;j++){
                            System.out.println(GY[i][j]);
                        }


                    System.out.println(string);

                    e.printStackTrace();
                    System.out.println(e.getCause());

                    System.out.println(file4);
                    System.out.println(x);
                    System.out.println(y);
                }


            }
            long end=System.currentTimeMillis();


        }  catch (Exception e) {
            e.printStackTrace();
        }

        i = 0;


        Ppm newppm9 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {

            newppm3.writeFile(output4, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }

        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file5))));
            int y=0;
            int x=0;
            int check=0;

            long start=System.currentTimeMillis();
            for(String string : lines) {

                check++;

                try {

                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);

                    boolean checkk = true;
                    while (checkk) {

                        try {
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);


                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);

                        } catch (Exception e) {


                            checkk = false;

                        }
                    }

                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {
                        block = colorImage.decodeQuantBlock(block);

                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }


                    int c = ce % 3;
                    if (c == 0) GY = block;
                    if (c == 1) GU = block;

                    if (c == 2) {

                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }

                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }



                    ce++;

                }catch (Exception e){
                    System.out.println(ce%3);
                    for( i=0;i<8;i++)
                        for(int j=0;j<8;j++){
                            System.out.println(GY[i][j]);
                        }


                    System.out.println(string);

                    e.printStackTrace();
                    System.out.println(e.getCause());

                    System.out.println(file5);
                    System.out.println(x);
                    System.out.println(y);
                }



            }
            long end=System.currentTimeMillis();


        }  catch (Exception e) {
            e.printStackTrace();
        }



        Ppm newppm8 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {

            newppm3.writeFile(output5, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }




        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file6))));
            int y=0;
            int x=0;
            int check=0;

            long start=System.currentTimeMillis();
            for(String string : lines) {

                check++;


                try {

                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);

                    boolean checkk = true;
                    while (checkk) {

                        try {
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);


                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);

                        } catch (Exception e) {

                            checkk = false;

                        }
                    }

                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

                        block = colorImage.decodeQuantBlock(block);

                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }


                    int c = ce % 3;
                    if (c == 0) GY = block;
                    if (c == 1) GU = block;

                    if (c == 2) {

                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }

                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }



                    ce++;

                }catch (Exception e){
                    System.out.println(ce%3);
                    for( i=0;i<8;i++)
                        for(int j=0;j<8;j++){
                            System.out.println(GY[i][j]);
                        }


                    System.out.println(string);

                    e.printStackTrace();
                    System.out.println(e.getCause());

                    System.out.println(file6);
                    System.out.println(x);
                    System.out.println(y);
                }



            }
            long end=System.currentTimeMillis();


        }  catch (Exception e) {
            e.printStackTrace();
        }


        i = 0;


        Ppm newppm7 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {

            newppm3.writeFile(output6, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }



    }


    public void joiM(){
        try{
            this.thread.join();
            // this.write();
            if(video !=null)this.video.joiM();
            else System.out.println("DONE");
        } catch (Exception e) {
            System.out.println("NO MORE");
        }
    }
}

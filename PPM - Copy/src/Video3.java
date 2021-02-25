import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video3 implements Runnable{
    public Video3 video;
    public ColorImage colorImage;
    public String name;
    public String file[];
    public String output[];
    public Thread thread;
    public int check=0;
    public Map<Integer,double[][]> blocks;
    public Object latch = new Object();
    public Object fwdlatch = new Object();
    public double o;
    public double[] cosinee;
    public double cc;
    public double cccc;
    Video3(){}


    Video3(ColorImage colorImage, String name, String file[], String output[], double o, double[] cosinee, double cc, double cccc){
        this.file=file;
        this.colorImage=colorImage;
        this.name=name;
        this.output=output;
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
    public void setVideo2(Video3 video) throws IOException {
        if(this.video!=null){
            this.video.setVideo2(video);
        }else {this.video=video;
            this.video.latch=fwdlatch;
            //this.ppm=new Ppm(file);
        }
    }

    protected void writeLine(BufferedWriter out, String line) throws Exception {

        out.write(line);
        out.newLine();
    }
/*
    public void write()  {
        colorImage.GY=new ArrayList<>(colorImage.GY);colorImage.GU=new ArrayList<>(colorImage.GU);colorImage.GV=new ArrayList<>(colorImage.GV);

        for ( Integer integ : blocks.keySet()){
            int ce=integ%3;
            int pos=integ/3;
            if(ce==0) colorImage.GY.set(pos,blocks.get(integ));
            if(ce==1) colorImage.GU.set(pos,blocks.get(integ));

            if(ce==2) colorImage.GV.set(pos,blocks.get(integ));
        }
        //this.video.colorImage=colorImage;

        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }

        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        if(this.video!=null){
            // this.video.colorImage=colorImage;
            //this.video.write();
        }

    }*/
    public void color(){
        this.video.colorImage=colorImage;
    }
    @Override
    public void run() {
        // synchronized (colorImage){this.video.colorImage=colorImage.clone();}
        // this.video.colorImage.GY= ImmutableList.<double[][]>builder()
        //   .addAll(colorImage.GY)
        //  .build();
        //this.video.colorImage.GU= ImmutableList.<double[][]>builder()
        //   .addAll(colorImage.GY)
        //  .build();
        // this.video.colorImage.GV= ImmutableList.<double[][]>builder()
        //  .addAll(colorImage.GY)
        //  .build();
        //long start=System.currentTimeMillis();
        // this.video.colorImage=colorImage.clone();

/*
        if(this.video!=null) {
            // this.video.colorImage=colorImage;
            Thread thread = new Thread(this.video);
            this.thread = thread;
            this.thread.start();
        }*/
        double[][] GY=new double[8][8];
        double[][] GU=new double[8][8];
        double[][] GV=new double[8][8];
        int ce=0;
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file[0]))));
            int y=0;
            int x=0;
            int check=0;
            // System.out.println("ZZ");
            long start=System.currentTimeMillis();
            for(String string : lines) {
                // if(check<11&&check>=9){
                //   check++;
                // }if(check==12){check=0;}else{
                check++;

                // if (string == " 0" || check == 0) {
                //    check = 1;
                // } else {
                //System.out.println(string);
                //ce++;
                try {
                    // String[] ceva = string.split(",");
                    //System.out.println("CEVAAA");
                    //System.out.println(ceva);
                    // String position = ceva[0];
                    // String numbers = string;
                    // String[] list = string.split(" ");
                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);
                    // System.out.println(smth);
                    //System.out.println(string.substring(string.lastIndexOf(" ")-1));
                    boolean checkk = true;
                    while (checkk) {
                        // System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        //if(!list[i].equals("")){
                        try { // pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);

                            // smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
                            //list[i]));
                            // smth= Integer.parseInt(string.substring(string.indexOf(smth) +2,string.indexOf(" ")));

                            //smth=Integer.parseInt(string.substring(pos1,pos2));
                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);
                            // System.out.println(smth);
                            //System.out.println(smth);
                        } catch (Exception e) {
                            // System.out.println(string);
                            //  System.out.println(smth);
                            //smth=Integer.parseInt(string.substring(pos1));
                            // num.add(smth);

                            checkk = false;
                            // System.out.println(smth);
                        }
                    }
                    //System.out.println("IESEEEE");
                    //}
                    //  System.out.println("ZZ");
                    // long start=System.currentTimeMillis();
                    // double[][] block = colorImage.decodeBlockZZ(num);
                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

//                for(int i=0;i<8;i++)
//                    for(int j=0;j<8;j++){
//                        System.out.println(block[i][j]);
//                    }
                        // System.out.println("New Block");
                        // break;
                        // long end=System.currentTimeMillis();
                        // System.out.println(start-end);
                        // start=System.currentTimeMillis();
                        //  if(check==1){
                        //   for(int i=0;i<8;i++)
                        // for(int j=0;j<8;j++){
                        //   System.out.println(block[i][j]);
                        //   }
                        //   }
                        block = colorImage.decodeQuantBlock(block);
                        //  end=System.currentTimeMillis();
                        //  System.out.println(start-end);
                        //  start=System.currentTimeMillis();
                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }
                    //   end=System.currentTimeMillis();
                    //  System.out.println(start-end);

                    //int pos = Integer.parseInt(position);
                    //  int ce=pos%3;
                    // pos=pos/3;
                    //  blocks.put(pos, block);// colorImage.GY.set(pos,block);

                    // int ce = pos % 3;
                    // int posi = pos / 3;

                    // if(ce==0) colorImage.GY.set(posi,blocks.get(pos));
                    // if(ce==1) colorImage.GU.set(posi,blocks.get(pos));

                    int c = ce % 3;
                    if (c == 0) GY = block;//colorImage.GY.add(colorImage.GY.size(),block);
                    if (c == 1) GU = block;//colorImage.GU.add(colorImage.GU.size(),block);

                    if (c == 2) {
                        // colorImage.Color2(ce/67,ce%67,);
                        //colorImage.Color2(x,y,GY,GU,GV);

                        //colorImage.GV.add(colorImage.GV.size(),block);
                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }
                            // colorImage.Color2(x,y,colorImage.GY.get(colorImage.GY.size()-1),colorImage.GU.get(colorImage.GY.size()-1),colorImage.GV.get(colorImage.GY.size()-1));}
                            // System.out.println(colorImage.image[x][y][0]);
                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }
                    //colorImage.GV.add(colorImage.GV.size(),block);}
                    //colorImage.Color2(x, y, GY, GU, GV);


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
                    //e.getCause().
                    System.out.println(file);
                    System.out.println(x);
                    System.out.println(y);
                }
                //if(ce==2) colorImage.GV.set(posi,blocks.get(pos));

    /*
    colorImage.GY=ImmutableList.<double[][]>builder()
            .addAll(colorImage.GY)
            .build();
    */
                // colorImage.GY=ImmutableList.<double[][]>builder().addAll(colorImage.GY).build().subList(0,colorImage.GY.size());
                // colorImage.GU=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GU.size());
                //  colorImage.GV=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GV.size());
            }//}
            long end=System.currentTimeMillis();
            // System.out.println(start-end);
            // }

        }  catch (Exception e) {
            e.printStackTrace();
        }
/*
           if(video!=null)
            synchronized(colorImage) {



    }*/
        //  write();
        ///this.latch.await();

        int i = 0;
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(ce);
//colorImage.image=new int[536][1280][3];
/*
    for (int x = 0; x < colorImage.height; x = x + 8) {
        for (int y = 0; y < colorImage.width; y = y + 8) {
            try {
                colorImage.Color2(x, y,colorImage.GY.get(i), colorImage.GU.get(i), colorImage.GV.get(i));

            }catch(Exception e){
               System.out.println("ceva");
               e.printStackTrace();
            }
            i++;
       }
    }*/
        //int x=0;
        // int y=0;
//for(int h=0;h<colorImage.GY.size();h++){
        //  colorImage.Color2(h/colorImage.height,h%colorImage.width,colorImage.GY.get(h), colorImage.GU.get(h), colorImage.GV.get(h));
//}

        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            //System.out.println("SCRIE");
            newppm3.writeFile(output[0], false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        colorImage=new ColorImage(colorImage.width,colorImage.height);
        // this.video.colorImage=colorImage.clone();


            /*
        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }*/
        //long end=System.currentTimeMillis();
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(start-end);
//colorImage=null;
        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file[1]))));
            int y=0;
            int x=0;
            int check=0;
            // System.out.println("ZZ");
            long start=System.currentTimeMillis();
            for(String string : lines) {
                // if(check<11&&check>=9){
                //   check++;
                // }if(check==12){check=0;}else{
                check++;

                // if (string == " 0" || check == 0) {
                //    check = 1;
                // } else {
                //System.out.println(string);
                //ce++;
                try {
                    // String[] ceva = string.split(",");
                    //System.out.println("CEVAAA");
                    //System.out.println(ceva);
                    // String position = ceva[0];
                    // String numbers = string;
                    // String[] list = string.split(" ");
                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);
                    // System.out.println(smth);
                    //System.out.println(string.substring(string.lastIndexOf(" ")-1));
                    boolean checkk = true;
                    while (checkk) {
                        // System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        //if(!list[i].equals("")){
                        try { // pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);

                            // smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
                            //list[i]));
                            // smth= Integer.parseInt(string.substring(string.indexOf(smth) +2,string.indexOf(" ")));

                            //smth=Integer.parseInt(string.substring(pos1,pos2));
                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);
                            // System.out.println(smth);
                            //System.out.println(smth);
                        } catch (Exception e) {
                            // System.out.println(string);
                            //  System.out.println(smth);
                            //smth=Integer.parseInt(string.substring(pos1));
                            // num.add(smth);

                            checkk = false;
                            // System.out.println(smth);
                        }
                    }
                    //System.out.println("IESEEEE");
                    //}
                    //  System.out.println("ZZ");
                    // long start=System.currentTimeMillis();
                    // double[][] block = colorImage.decodeBlockZZ(num);
                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

//                for(int i=0;i<8;i++)
//                    for(int j=0;j<8;j++){
//                        System.out.println(block[i][j]);
//                    }
                        // System.out.println("New Block");
                        // break;
                        // long end=System.currentTimeMillis();
                        // System.out.println(start-end);
                        // start=System.currentTimeMillis();
                        //  if(check==1){
                        //   for(int i=0;i<8;i++)
                        // for(int j=0;j<8;j++){
                        //   System.out.println(block[i][j]);
                        //   }
                        //   }
                        block = colorImage.decodeQuantBlock(block);
                        //  end=System.currentTimeMillis();
                        //  System.out.println(start-end);
                        //  start=System.currentTimeMillis();
                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }
                    //   end=System.currentTimeMillis();
                    //  System.out.println(start-end);

                    //int pos = Integer.parseInt(position);
                    //  int ce=pos%3;
                    // pos=pos/3;
                    //  blocks.put(pos, block);// colorImage.GY.set(pos,block);

                    // int ce = pos % 3;
                    // int posi = pos / 3;

                    // if(ce==0) colorImage.GY.set(posi,blocks.get(pos));
                    // if(ce==1) colorImage.GU.set(posi,blocks.get(pos));

                    int c = ce % 3;
                    if (c == 0) GY = block;//colorImage.GY.add(colorImage.GY.size(),block);
                    if (c == 1) GU = block;//colorImage.GU.add(colorImage.GU.size(),block);

                    if (c == 2) {
                        // colorImage.Color2(ce/67,ce%67,);
                        //colorImage.Color2(x,y,GY,GU,GV);

                        //colorImage.GV.add(colorImage.GV.size(),block);
                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }
                            // colorImage.Color2(x,y,colorImage.GY.get(colorImage.GY.size()-1),colorImage.GU.get(colorImage.GY.size()-1),colorImage.GV.get(colorImage.GY.size()-1));}
                            // System.out.println(colorImage.image[x][y][0]);
                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }
                    //colorImage.GV.add(colorImage.GV.size(),block);}
                    //colorImage.Color2(x, y, GY, GU, GV);


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
                    //e.getCause().
                    System.out.println(file);
                    System.out.println(x);
                    System.out.println(y);
                }
                //if(ce==2) colorImage.GV.set(posi,blocks.get(pos));

    /*
    colorImage.GY=ImmutableList.<double[][]>builder()
            .addAll(colorImage.GY)
            .build();
    */
                // colorImage.GY=ImmutableList.<double[][]>builder().addAll(colorImage.GY).build().subList(0,colorImage.GY.size());
                // colorImage.GU=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GU.size());
                //  colorImage.GV=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GV.size());
            }//}
            long end=System.currentTimeMillis();
            // System.out.println(start-end);
            // }

        }  catch (Exception e) {
            e.printStackTrace();
        }
/*
           if(video!=null)
            synchronized(colorImage) {



    }*/
        //  write();
        ///this.latch.await();

        i = 0;
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(ce);
//colorImage.image=new int[536][1280][3];
/*
    for (int x = 0; x < colorImage.height; x = x + 8) {
        for (int y = 0; y < colorImage.width; y = y + 8) {
            try {
                colorImage.Color2(x, y,colorImage.GY.get(i), colorImage.GU.get(i), colorImage.GV.get(i));

            }catch(Exception e){
               System.out.println("ceva");
               e.printStackTrace();
            }
            i++;
       }
    }*/
        //int x=0;
        // int y=0;
//for(int h=0;h<colorImage.GY.size();h++){
        //  colorImage.Color2(h/colorImage.height,h%colorImage.width,colorImage.GY.get(h), colorImage.GU.get(h), colorImage.GV.get(h));
//}

        Ppm newppm4 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            //System.out.println("SCRIE");
            newppm3.writeFile(output[1], false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        // this.video.colorImage=colorImage.clone();

        colorImage=new ColorImage(colorImage.width,colorImage.height);
            /*
        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }*/
        //long end=System.currentTimeMillis();
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(start-end);
//colorImage=null;

        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file[2]))));
            int y=0;
            int x=0;
            int check=0;
            // System.out.println("ZZ");
            long start=System.currentTimeMillis();
            for(String string : lines) {
                // if(check<11&&check>=9){
                //   check++;
                // }if(check==12){check=0;}else{
                check++;

                // if (string == " 0" || check == 0) {
                //    check = 1;
                // } else {
                //System.out.println(string);
                //ce++;
                try {
                    // String[] ceva = string.split(",");
                    //System.out.println("CEVAAA");
                    //System.out.println(ceva);
                    // String position = ceva[0];
                    // String numbers = string;
                    // String[] list = string.split(" ");
                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);
                    // System.out.println(smth);
                    //System.out.println(string.substring(string.lastIndexOf(" ")-1));
                    boolean checkk = true;
                    while (checkk) {
                        // System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        //if(!list[i].equals("")){
                        try { // pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);

                            // smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
                            //list[i]));
                            // smth= Integer.parseInt(string.substring(string.indexOf(smth) +2,string.indexOf(" ")));

                            //smth=Integer.parseInt(string.substring(pos1,pos2));
                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);
                            // System.out.println(smth);
                            //System.out.println(smth);
                        } catch (Exception e) {
                            // System.out.println(string);
                            //  System.out.println(smth);
                            //smth=Integer.parseInt(string.substring(pos1));
                            // num.add(smth);

                            checkk = false;
                            // System.out.println(smth);
                        }
                    }
                    //System.out.println("IESEEEE");
                    //}
                    //  System.out.println("ZZ");
                    // long start=System.currentTimeMillis();
                    // double[][] block = colorImage.decodeBlockZZ(num);
                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

//                for(int i=0;i<8;i++)
//                    for(int j=0;j<8;j++){
//                        System.out.println(block[i][j]);
//                    }
                        // System.out.println("New Block");
                        // break;
                        // long end=System.currentTimeMillis();
                        // System.out.println(start-end);
                        // start=System.currentTimeMillis();
                        //  if(check==1){
                        //   for(int i=0;i<8;i++)
                        // for(int j=0;j<8;j++){
                        //   System.out.println(block[i][j]);
                        //   }
                        //   }
                        block = colorImage.decodeQuantBlock(block);
                        //  end=System.currentTimeMillis();
                        //  System.out.println(start-end);
                        //  start=System.currentTimeMillis();
                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }
                    //   end=System.currentTimeMillis();
                    //  System.out.println(start-end);

                    //int pos = Integer.parseInt(position);
                    //  int ce=pos%3;
                    // pos=pos/3;
                    //  blocks.put(pos, block);// colorImage.GY.set(pos,block);

                    // int ce = pos % 3;
                    // int posi = pos / 3;

                    // if(ce==0) colorImage.GY.set(posi,blocks.get(pos));
                    // if(ce==1) colorImage.GU.set(posi,blocks.get(pos));

                    int c = ce % 3;
                    if (c == 0) GY = block;//colorImage.GY.add(colorImage.GY.size(),block);
                    if (c == 1) GU = block;//colorImage.GU.add(colorImage.GU.size(),block);

                    if (c == 2) {
                        // colorImage.Color2(ce/67,ce%67,);
                        //colorImage.Color2(x,y,GY,GU,GV);

                        //colorImage.GV.add(colorImage.GV.size(),block);
                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }
                            // colorImage.Color2(x,y,colorImage.GY.get(colorImage.GY.size()-1),colorImage.GU.get(colorImage.GY.size()-1),colorImage.GV.get(colorImage.GY.size()-1));}
                            // System.out.println(colorImage.image[x][y][0]);
                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }
                    //colorImage.GV.add(colorImage.GV.size(),block);}
                    //colorImage.Color2(x, y, GY, GU, GV);


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
                    //e.getCause().
                    System.out.println(file);
                    System.out.println(x);
                    System.out.println(y);
                }
                //if(ce==2) colorImage.GV.set(posi,blocks.get(pos));

    /*
    colorImage.GY=ImmutableList.<double[][]>builder()
            .addAll(colorImage.GY)
            .build();
    */
                // colorImage.GY=ImmutableList.<double[][]>builder().addAll(colorImage.GY).build().subList(0,colorImage.GY.size());
                // colorImage.GU=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GU.size());
                //  colorImage.GV=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GV.size());
            }//}
            long end=System.currentTimeMillis();
            // System.out.println(start-end);
            // }

        }  catch (Exception e) {
            e.printStackTrace();
        }
/*
           if(video!=null)
            synchronized(colorImage) {



    }*/
        //  write();
        ///this.latch.await();


        i = 0;
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(ce);
//colorImage.image=new int[536][1280][3];
/*
    for (int x = 0; x < colorImage.height; x = x + 8) {
        for (int y = 0; y < colorImage.width; y = y + 8) {
            try {
                colorImage.Color2(x, y,colorImage.GY.get(i), colorImage.GU.get(i), colorImage.GV.get(i));

            }catch(Exception e){
               System.out.println("ceva");
               e.printStackTrace();
            }
            i++;
       }
    }*/
        //int x=0;
        // int y=0;
//for(int h=0;h<colorImage.GY.size();h++){
        //  colorImage.Color2(h/colorImage.height,h%colorImage.width,colorImage.GY.get(h), colorImage.GU.get(h), colorImage.GV.get(h));
//}

        Ppm newppm5 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            //System.out.println("SCRIE");
            newppm3.writeFile(output[2], false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        // this.video.colorImage=colorImage.clone();

        colorImage=new ColorImage(colorImage.width,colorImage.height);
            /*
        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }*/
        //long end=System.currentTimeMillis();
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(start-end);
//colorImage=null;

// synchronized (colorImage){this.video.colorImage=colorImage.clone();}
        // this.video.colorImage.GY= ImmutableList.<double[][]>builder()
        //   .addAll(colorImage.GY)
        //  .build();
        //this.video.colorImage.GU= ImmutableList.<double[][]>builder()
        //   .addAll(colorImage.GY)
        //  .build();
        // this.video.colorImage.GV= ImmutableList.<double[][]>builder()
        //  .addAll(colorImage.GY)
        //  .build();
        //long start=System.currentTimeMillis();
        // this.video.colorImage=colorImage.clone();

/*
        if(this.video!=null) {
            // this.video.colorImage=colorImage;
            Thread thread = new Thread(this.video);
            this.thread = thread;
            this.thread.start();
        }*/
       // double[][] GY=new double[8][8];
       // double[][] GU=new double[8][8];
       // double[][] GV=new double[8][8];
         //ce=0;
        //List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file[3]))));
            int y=0;
            int x=0;
            int check=0;
            // System.out.println("ZZ");
            long start=System.currentTimeMillis();
            for(String string : lines) {
                // if(check<11&&check>=9){
                //   check++;
                // }if(check==12){check=0;}else{
                check++;

                // if (string == " 0" || check == 0) {
                //    check = 1;
                // } else {
                //System.out.println(string);
                //ce++;
                try {
                    // String[] ceva = string.split(",");
                    //System.out.println("CEVAAA");
                    //System.out.println(ceva);
                    // String position = ceva[0];
                    // String numbers = string;
                    // String[] list = string.split(" ");
                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);
                    // System.out.println(smth);
                    //System.out.println(string.substring(string.lastIndexOf(" ")-1));
                    boolean checkk = true;
                    while (checkk) {
                        // System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        //if(!list[i].equals("")){
                        try { // pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);

                            // smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
                            //list[i]));
                            // smth= Integer.parseInt(string.substring(string.indexOf(smth) +2,string.indexOf(" ")));

                            //smth=Integer.parseInt(string.substring(pos1,pos2));
                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);
                            // System.out.println(smth);
                            //System.out.println(smth);
                        } catch (Exception e) {
                            // System.out.println(string);
                            //  System.out.println(smth);
                            //smth=Integer.parseInt(string.substring(pos1));
                            // num.add(smth);

                            checkk = false;
                            // System.out.println(smth);
                        }
                    }
                    //System.out.println("IESEEEE");
                    //}
                    //  System.out.println("ZZ");
                    // long start=System.currentTimeMillis();
                    // double[][] block = colorImage.decodeBlockZZ(num);
                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

//                for(int i=0;i<8;i++)
//                    for(int j=0;j<8;j++){
//                        System.out.println(block[i][j]);
//                    }
                        // System.out.println("New Block");
                        // break;
                        // long end=System.currentTimeMillis();
                        // System.out.println(start-end);
                        // start=System.currentTimeMillis();
                        //  if(check==1){
                        //   for(int i=0;i<8;i++)
                        // for(int j=0;j<8;j++){
                        //   System.out.println(block[i][j]);
                        //   }
                        //   }
                        block = colorImage.decodeQuantBlock(block);
                        //  end=System.currentTimeMillis();
                        //  System.out.println(start-end);
                        //  start=System.currentTimeMillis();
                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }
                    //   end=System.currentTimeMillis();
                    //  System.out.println(start-end);

                    //int pos = Integer.parseInt(position);
                    //  int ce=pos%3;
                    // pos=pos/3;
                    //  blocks.put(pos, block);// colorImage.GY.set(pos,block);

                    // int ce = pos % 3;
                    // int posi = pos / 3;

                    // if(ce==0) colorImage.GY.set(posi,blocks.get(pos));
                    // if(ce==1) colorImage.GU.set(posi,blocks.get(pos));

                    int c = ce % 3;
                    if (c == 0) GY = block;//colorImage.GY.add(colorImage.GY.size(),block);
                    if (c == 1) GU = block;//colorImage.GU.add(colorImage.GU.size(),block);

                    if (c == 2) {
                        // colorImage.Color2(ce/67,ce%67,);
                        //colorImage.Color2(x,y,GY,GU,GV);

                        //colorImage.GV.add(colorImage.GV.size(),block);
                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }
                            // colorImage.Color2(x,y,colorImage.GY.get(colorImage.GY.size()-1),colorImage.GU.get(colorImage.GY.size()-1),colorImage.GV.get(colorImage.GY.size()-1));}
                            // System.out.println(colorImage.image[x][y][0]);
                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }
                    //colorImage.GV.add(colorImage.GV.size(),block);}
                    //colorImage.Color2(x, y, GY, GU, GV);


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
                    //e.getCause().
                    System.out.println(file);
                    System.out.println(x);
                    System.out.println(y);
                }
                //if(ce==2) colorImage.GV.set(posi,blocks.get(pos));

    /*
    colorImage.GY=ImmutableList.<double[][]>builder()
            .addAll(colorImage.GY)
            .build();
    */
                // colorImage.GY=ImmutableList.<double[][]>builder().addAll(colorImage.GY).build().subList(0,colorImage.GY.size());
                // colorImage.GU=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GU.size());
                //  colorImage.GV=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GV.size());
            }//}
            long end=System.currentTimeMillis();
            // System.out.println(start-end);
            // }

        }  catch (Exception e) {
            e.printStackTrace();
        }
/*
           if(video!=null)
            synchronized(colorImage) {



    }*/
        //  write();
        ///this.latch.await();

         i = 0;
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(ce);
//colorImage.image=new int[536][1280][3];
/*
    for (int x = 0; x < colorImage.height; x = x + 8) {
        for (int y = 0; y < colorImage.width; y = y + 8) {
            try {
                colorImage.Color2(x, y,colorImage.GY.get(i), colorImage.GU.get(i), colorImage.GV.get(i));

            }catch(Exception e){
               System.out.println("ceva");
               e.printStackTrace();
            }
            i++;
       }
    }*/
        //int x=0;
        // int y=0;
//for(int h=0;h<colorImage.GY.size();h++){
        //  colorImage.Color2(h/colorImage.height,h%colorImage.width,colorImage.GY.get(h), colorImage.GU.get(h), colorImage.GV.get(h));
//}

        Ppm newppm9 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            //System.out.println("SCRIE");
            newppm3.writeFile(output[3], false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        // this.video.colorImage=colorImage.clone();

        colorImage=new ColorImage(colorImage.width,colorImage.height);
            /*
        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }*/
        //long end=System.currentTimeMillis();
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(start-end);
//colorImage=null;
        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file[4]))));
            int y=0;
            int x=0;
            int check=0;
            // System.out.println("ZZ");
            long start=System.currentTimeMillis();
            for(String string : lines) {
                // if(check<11&&check>=9){
                //   check++;
                // }if(check==12){check=0;}else{
                check++;

                // if (string == " 0" || check == 0) {
                //    check = 1;
                // } else {
                //System.out.println(string);
                //ce++;
                try {
                    // String[] ceva = string.split(",");
                    //System.out.println("CEVAAA");
                    //System.out.println(ceva);
                    // String position = ceva[0];
                    // String numbers = string;
                    // String[] list = string.split(" ");
                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);
                    // System.out.println(smth);
                    //System.out.println(string.substring(string.lastIndexOf(" ")-1));
                    boolean checkk = true;
                    while (checkk) {
                        // System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        //if(!list[i].equals("")){
                        try { // pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);

                            // smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
                            //list[i]));
                            // smth= Integer.parseInt(string.substring(string.indexOf(smth) +2,string.indexOf(" ")));

                            //smth=Integer.parseInt(string.substring(pos1,pos2));
                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);
                            // System.out.println(smth);
                            //System.out.println(smth);
                        } catch (Exception e) {
                            // System.out.println(string);
                            //  System.out.println(smth);
                            //smth=Integer.parseInt(string.substring(pos1));
                            // num.add(smth);

                            checkk = false;
                            // System.out.println(smth);
                        }
                    }
                    //System.out.println("IESEEEE");
                    //}
                    //  System.out.println("ZZ");
                    // long start=System.currentTimeMillis();
                    // double[][] block = colorImage.decodeBlockZZ(num);
                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

//                for(int i=0;i<8;i++)
//                    for(int j=0;j<8;j++){
//                        System.out.println(block[i][j]);
//                    }
                        // System.out.println("New Block");
                        // break;
                        // long end=System.currentTimeMillis();
                        // System.out.println(start-end);
                        // start=System.currentTimeMillis();
                        //  if(check==1){
                        //   for(int i=0;i<8;i++)
                        // for(int j=0;j<8;j++){
                        //   System.out.println(block[i][j]);
                        //   }
                        //   }
                        block = colorImage.decodeQuantBlock(block);
                        //  end=System.currentTimeMillis();
                        //  System.out.println(start-end);
                        //  start=System.currentTimeMillis();
                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }
                    //   end=System.currentTimeMillis();
                    //  System.out.println(start-end);

                    //int pos = Integer.parseInt(position);
                    //  int ce=pos%3;
                    // pos=pos/3;
                    //  blocks.put(pos, block);// colorImage.GY.set(pos,block);

                    // int ce = pos % 3;
                    // int posi = pos / 3;

                    // if(ce==0) colorImage.GY.set(posi,blocks.get(pos));
                    // if(ce==1) colorImage.GU.set(posi,blocks.get(pos));

                    int c = ce % 3;
                    if (c == 0) GY = block;//colorImage.GY.add(colorImage.GY.size(),block);
                    if (c == 1) GU = block;//colorImage.GU.add(colorImage.GU.size(),block);

                    if (c == 2) {
                        // colorImage.Color2(ce/67,ce%67,);
                        //colorImage.Color2(x,y,GY,GU,GV);

                        //colorImage.GV.add(colorImage.GV.size(),block);
                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }
                            // colorImage.Color2(x,y,colorImage.GY.get(colorImage.GY.size()-1),colorImage.GU.get(colorImage.GY.size()-1),colorImage.GV.get(colorImage.GY.size()-1));}
                            // System.out.println(colorImage.image[x][y][0]);
                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }
                    //colorImage.GV.add(colorImage.GV.size(),block);}
                    //colorImage.Color2(x, y, GY, GU, GV);


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
                    //e.getCause().
                    System.out.println(file);
                    System.out.println(x);
                    System.out.println(y);
                }
                //if(ce==2) colorImage.GV.set(posi,blocks.get(pos));

    /*
    colorImage.GY=ImmutableList.<double[][]>builder()
            .addAll(colorImage.GY)
            .build();
    */
                // colorImage.GY=ImmutableList.<double[][]>builder().addAll(colorImage.GY).build().subList(0,colorImage.GY.size());
                // colorImage.GU=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GU.size());
                //  colorImage.GV=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GV.size());
            }//}
            long end=System.currentTimeMillis();
            // System.out.println(start-end);
            // }

        }  catch (Exception e) {
            e.printStackTrace();
        }
/*
           if(video!=null)
            synchronized(colorImage) {



    }*/
        //  write();
        ///this.latch.await();

        i = 0;
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(ce);
//colorImage.image=new int[536][1280][3];
/*
    for (int x = 0; x < colorImage.height; x = x + 8) {
        for (int y = 0; y < colorImage.width; y = y + 8) {
            try {
                colorImage.Color2(x, y,colorImage.GY.get(i), colorImage.GU.get(i), colorImage.GV.get(i));

            }catch(Exception e){
               System.out.println("ceva");
               e.printStackTrace();
            }
            i++;
       }
    }*/
        //int x=0;
        // int y=0;
//for(int h=0;h<colorImage.GY.size();h++){
        //  colorImage.Color2(h/colorImage.height,h%colorImage.width,colorImage.GY.get(h), colorImage.GU.get(h), colorImage.GV.get(h));
//}

        Ppm newppm8 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            //System.out.println("SCRIE");
            newppm3.writeFile(output[4], false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        // this.video.colorImage=colorImage.clone();

        colorImage=new ColorImage(colorImage.width,colorImage.height);
            /*
        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }*/
        //long end=System.currentTimeMillis();
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(start-end);
//colorImage=null;

        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file[5]))));
            int y=0;
            int x=0;
            int check=0;
            // System.out.println("ZZ");
            long start=System.currentTimeMillis();
            for(String string : lines) {
                // if(check<11&&check>=9){
                //   check++;
                // }if(check==12){check=0;}else{
                check++;

                // if (string == " 0" || check == 0) {
                //    check = 1;
                // } else {
                //System.out.println(string);
                //ce++;
                try {
                    // String[] ceva = string.split(",");
                    //System.out.println("CEVAAA");
                    //System.out.println(ceva);
                    // String position = ceva[0];
                    // String numbers = string;
                    // String[] list = string.split(" ");
                    int pos1 = 0;
                    int pos2 = pos1 + 1;
                    Integer smth = Integer.parseInt(string.substring(pos1, pos2));

                    List<Integer> num = new ArrayList<>();
                    num.add(smth);
                    // System.out.println(smth);
                    //System.out.println(string.substring(string.lastIndexOf(" ")-1));
                    boolean checkk = true;
                    while (checkk) {
                        // System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        //if(!list[i].equals("")){
                        try { // pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
                            pos1 = string.indexOf(" ", pos1) + 1;
                            pos2 = string.indexOf(" ", pos1);

                            // smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
                            //list[i]));
                            // smth= Integer.parseInt(string.substring(string.indexOf(smth) +2,string.indexOf(" ")));

                            //smth=Integer.parseInt(string.substring(pos1,pos2));
                            smth = Integer.parseInt(string.substring(pos1, pos2));
                            num.add(smth);
                            // System.out.println(smth);
                            //System.out.println(smth);
                        } catch (Exception e) {
                            // System.out.println(string);
                            //  System.out.println(smth);
                            //smth=Integer.parseInt(string.substring(pos1));
                            // num.add(smth);

                            checkk = false;
                            // System.out.println(smth);
                        }
                    }
                    //System.out.println("IESEEEE");
                    //}
                    //  System.out.println("ZZ");
                    // long start=System.currentTimeMillis();
                    // double[][] block = colorImage.decodeBlockZZ(num);
                    double[][] block = colorImage.decodeBlockZZ(num);
                    try {

//                for(int i=0;i<8;i++)
//                    for(int j=0;j<8;j++){
//                        System.out.println(block[i][j]);
//                    }
                        // System.out.println("New Block");
                        // break;
                        // long end=System.currentTimeMillis();
                        // System.out.println(start-end);
                        // start=System.currentTimeMillis();
                        //  if(check==1){
                        //   for(int i=0;i<8;i++)
                        // for(int j=0;j<8;j++){
                        //   System.out.println(block[i][j]);
                        //   }
                        //   }
                        block = colorImage.decodeQuantBlock(block);
                        //  end=System.currentTimeMillis();
                        //  System.out.println(start-end);
                        //  start=System.currentTimeMillis();
                        block = colorImage.decodeDCTBlock(block,o,cosinee,cc,cccc);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("SICIIIIIIIIIII");
                    }
                    //   end=System.currentTimeMillis();
                    //  System.out.println(start-end);

                    //int pos = Integer.parseInt(position);
                    //  int ce=pos%3;
                    // pos=pos/3;
                    //  blocks.put(pos, block);// colorImage.GY.set(pos,block);

                    // int ce = pos % 3;
                    // int posi = pos / 3;

                    // if(ce==0) colorImage.GY.set(posi,blocks.get(pos));
                    // if(ce==1) colorImage.GU.set(posi,blocks.get(pos));

                    int c = ce % 3;
                    if (c == 0) GY = block;//colorImage.GY.add(colorImage.GY.size(),block);
                    if (c == 1) GU = block;//colorImage.GU.add(colorImage.GU.size(),block);

                    if (c == 2) {
                        // colorImage.Color2(ce/67,ce%67,);
                        //colorImage.Color2(x,y,GY,GU,GV);

                        //colorImage.GV.add(colorImage.GV.size(),block);
                        GV = block;
                        if (x < 528) {
                            if (y == 1272) {
                                y = 0;
                                x += 8;
                            } else {
                                y += 8;
                            }
                            // colorImage.Color2(x,y,colorImage.GY.get(colorImage.GY.size()-1),colorImage.GU.get(colorImage.GY.size()-1),colorImage.GV.get(colorImage.GY.size()-1));}
                            // System.out.println(colorImage.image[x][y][0]);
                            colorImage.Color3(x, y, GY, GU, GV);
                        }
                    }
                    //colorImage.GV.add(colorImage.GV.size(),block);}
                    //colorImage.Color2(x, y, GY, GU, GV);


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
                    //e.getCause().
                    System.out.println(file);
                    System.out.println(x);
                    System.out.println(y);
                }
                //if(ce==2) colorImage.GV.set(posi,blocks.get(pos));

    /*
    colorImage.GY=ImmutableList.<double[][]>builder()
            .addAll(colorImage.GY)
            .build();
    */
                // colorImage.GY=ImmutableList.<double[][]>builder().addAll(colorImage.GY).build().subList(0,colorImage.GY.size());
                // colorImage.GU=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GU.size());
                //  colorImage.GV=ImmutableList.<double[][]>builder().addAll(colorImage.GU).build().subList(0,colorImage.GV.size());
            }//}
            long end=System.currentTimeMillis();
            // System.out.println(start-end);
            // }

        }  catch (Exception e) {
            e.printStackTrace();
        }
/*
           if(video!=null)
            synchronized(colorImage) {



    }*/
        //  write();
        ///this.latch.await();


        i = 0;
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(ce);
//colorImage.image=new int[536][1280][3];
/*
    for (int x = 0; x < colorImage.height; x = x + 8) {
        for (int y = 0; y < colorImage.width; y = y + 8) {
            try {
                colorImage.Color2(x, y,colorImage.GY.get(i), colorImage.GU.get(i), colorImage.GV.get(i));

            }catch(Exception e){
               System.out.println("ceva");
               e.printStackTrace();
            }
            i++;
       }
    }*/
        //int x=0;
        // int y=0;
//for(int h=0;h<colorImage.GY.size();h++){
        //  colorImage.Color2(h/colorImage.height,h%colorImage.width,colorImage.GY.get(h), colorImage.GU.get(h), colorImage.GV.get(h));
//}

        Ppm newppm7 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            //System.out.println("SCRIE");
            newppm3.writeFile(output[5], false);
        }catch(Exception e){
            System.out.println("Print");
            e.printStackTrace();
        }
        // this.video.colorImage=colorImage.clone();


            /*
        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }*/
        //long end=System.currentTimeMillis();
        // System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        //System.out.println(start-end);
//colorImage=null;


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

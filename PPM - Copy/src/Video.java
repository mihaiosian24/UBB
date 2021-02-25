import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video  implements Runnable{
    public Video video;
    public ColorImage colorImage;
    public String name;
    public String file;
    public String output;
    public Thread thread;
    public int check=0;
    public Map<Integer,double[][]> blocks;
    public Object latch = new Object();
    public Object fwdlatch = new Object();
    public double o;
    public double[] cosinee;
    public double cc;
    public double cccc;
   Video(){}


   Video(ColorImage colorImage,String name,String file,String output,double o,double[] cosinee,double cc,double cccc){
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
   public void setVideo(Video video) throws IOException {
       if(this.video!=null){
           this.video.setVideo(video);
       }else {this.video=video;
            this.video.latch=fwdlatch;
       //this.ppm=new Ppm(file);
       }
   }

    protected void writeLine(BufferedWriter out, String line) throws Exception {

        out.write(line);
        out.newLine();
    }

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
            lines = Files.readAllLines(Paths.get(String.valueOf(new File(file))));
int y=0;
int x=0;
        int check=0;

            long start=System.currentTimeMillis();
        for(String string : lines) {

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
                //e.getCause().
                System.out.println(file);
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
                //System.out.println("SCRIE");
                newppm3.writeFile(output, false);
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


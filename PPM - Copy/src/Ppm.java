
//package com.beust;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//import javax.swing.text.html.ImageView;

//import org.apache.commons.io.IOUtils;

//import com.google.common.io.Files;

public class Ppm implements Initializable{
    @FXML
    private Pane PaneView;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
    @FXML
    private void setImage(ActionEvent event){
        //javafx.scene.image.ImageView imageview=new javafx.scene.image.ImageView(new ImageIcon("C:\\Users\\mihai\\Desktop\\Video\\GO\\thumb000.ppm").getImage()));//("file:///C:/Users/mihai/Desktop/pt iubi.jpg"));//new File("C:\\Users\\mihai\\Desktop\\Video\\GO\\thumb000.ppm").toURI().toString()));
       //if(imageview==null) System.out.println("CEEE");
       // PaneView.getChildren().add(imageview);
    }
    final int width, height;
    public int[][][] image;
    ColorImage colorImage;
    /**
     * Constructor for the objects. You need to set the width/height of the image to create.
     *
     * @param width  width
     * @param height height
     */
    public Ppm(int width, int height) {
        this.width = width;
        this.height = height;

        image = new int[height][width][3];
    }
    public Ppm(){this.width=0;this.height=0;}
public Ppm(Ppm ppm,ColorImage colorImage){
        this.width=ppm.width;
        this.height=ppm.height;
    image = new int[height][width][3];
        this.colorImage=colorImage;
}
    public Ppm(int width, int height, int[][][] image) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

       public Ppm(File file) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Paths.get(String.valueOf(file)));
        //Scanner sc = new Scanner(file);

        //List <String> lines;

        Iterator<String> it = lines.iterator();

        if (!"P3".equals(it.next())) {
            throw new IllegalArgumentException("Expected P3");
        }
        it.next();
        String[] wl = it.next().split(" ");
        // System.out.println(wl[0]);

        this.width = Integer.parseInt(wl[0]);
        this.height = Integer.parseInt(wl[1]);
        this.image = new int[height][width][3];
        int maxColor = Integer.parseInt(it.next());

        System.out.println(maxColor);
        System.out.println("MAxCOLOR");
        int i = 0;
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < width; y++) {
                //String[] colors = it.next().split(" ");

                int red = Integer.parseInt(it.next());
                int yellow = Integer.parseInt(it.next());
                int blue = Integer.parseInt(it.next());
                if (i < 10) {
                    //System.out.println(red);
                    i++;
                }
                image[x][y][0] = red;
                image[x][y][1] = yellow;
                image[x][y][2] = blue;
                if (i < 10) {
                    //System.out.println(image[x][y][0]);
                }
            }
        }
    }

    public Ppm(File file,boolean val) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Paths.get(String.valueOf(file)));
        //Scanner sc = new Scanner(file);

        //List <String> lines;

        Iterator<String> it = lines.iterator();

        if (!"P3".equals(it.next())) {
            throw new IllegalArgumentException("Expected P3");
        }
        //it.next();
        String[] wl = it.next().split(" ");
        // System.out.println(wl[0]);

        this.width = Integer.parseInt(wl[0]);
        this.height = Integer.parseInt(wl[1]);
        this.image = new int[height][width][3];
        int maxColor = Integer.parseInt(it.next());

        System.out.println(maxColor);
        System.out.println("MAxCOLOR");
        int i = 0;
        int y = 0;
        int x = 0;
        while (it.hasNext()) {
            //String[] colors = it.next().split(" ");
            String[] line = it.next().split(" ");
            if (line.length == 1) {
                System.out.println("EMPTYLINE");
            } else {
                /*
                int red = Integer.parseInt(it.next());
                int yellow = Integer.parseInt(it.next());
                int blue = Integer.parseInt(it.next());
                */
                //if (i < 10) {
                //System.out.println(red);
                //  i++;
                /// }
                //for(String red,String yellow,String blue in line){

                for (i = 0; i < line.length; i++) {
                    image[x][y][0] = Integer.parseInt(line[i]);
                    i++;
                    image[x][y][1] = Integer.parseInt(line[i]);
                    ;
                    i++;
                    image[x][y][2] = Integer.parseInt(line[i]);
                    ;
                    y++;
                    if (y == this.width) {
                        y = 0;
                        x++;
                    }
                }
            }
        }
    }

    /**
     * Sets a pixel. Values can be in range 0...1024. If you use values bigger than 255 then you
     * need to set scanMaxValue to true in writeFile.
     *
     * @param x x
     * @param y y
     * @param r r
     * @param g g
     * @param b b
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        image[x][y][0] = r;
        image[x][y][1] = g;
        image[x][y][2] = b;
    }

    public int[] getPixel(int x, int y) {
        return image[x][y];
    }

    protected void writeLine(BufferedWriter out, String line) throws Exception {
        //out.write(line, 0, line.length());
        //out.newLine();
        out.write(line);
        out.newLine();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int[][][] getImage() {
        return image;
    }

    /**
     * Writes a PPM-file to disk.
     *
     * @param fileName     File to write to.
     * @param scanMaxValue Set this to true if you use r/g/b values bigger than 255.
     */
    public void writeFile(String fileName, boolean scanMaxValue) throws Exception {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

        writeLine(out, "P3");
        writeLine(out, "" + width + " " + height);
/*
        if (scanMaxValue) {
            int maxVal = 0;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    maxVal = Math.max(maxVal, image[x][y][0]);
                    maxVal = Math.max(maxVal, image[x][y][1]);
                    maxVal = Math.max(maxVal, image[x][y][2]);
                }
            }

            writeLine(out, "" + maxVal);
        } else {*/
            writeLine(out, "255");

        int i = 0;
        //System.out.println(width);
       // System.out.println(height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //writeLine(out, "" + image[x][y][0] + " " + image[x][y][1] + " " + image[x][y][2]);

                writeLine(out, "" + image[y][x][0]+" "+image[y][x][1]+" "+image[y][x][2]);
               // writeLine(out, "" + image[y][x][1]);
                //writeLine(out, "" + image[y][x][2]);
            }
        }
        ;
       // System.out.println("AICI");
        out.close();
    }
    public void writeFile2(String fileName, boolean scanMaxValue) throws Exception {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

        writeLine(out, "P3");
        writeLine(out, "" + (width) + " " + height);
/*
        if (scanMaxValue) {
            int maxVal = 0;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    maxVal = Math.max(maxVal, image[x][y][0]);
                    maxVal = Math.max(maxVal, image[x][y][1]);
                    maxVal = Math.max(maxVal, image[x][y][2]);
                }
            }

            writeLine(out, "" + maxVal);
        } else {*/
        writeLine(out, "255");
        //out.write(image,0,image.length);

        int i = 0;
        //System.out.println(width);
        // System.out.println(height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x+=8) {
                //writeLine(out, "" + image[x][y][0] + " " + image[x][y][1] + " " + image[x][y][2]);

                //writeLine(out, "" + image[y][x][0]);//+" "+image[y][x][1]+" "+image[y][x][2]);
                 //writeLine(out, "" + image[y][x][1]);
                //writeLine(out, "" + image[y][x][2]);
                writeLine(out, "" + image[y][x][0]+" "+image[y][x][1]+" "+image[y][x][2]+" "+image[y][x+1][0]+" "+image[y][x+1][1]+" "+image[y][x+1][2]+" "+image[y][x+2][0]+" "+image[y][x+2][1]+" "+image[y][x+2][2]
                        +" "+image[y][x+3][0]+" "+image[y][x+3][1]+" "+image[y][x+3][2]+" "+image[y][x+4][0]+" "+image[y][x+4][1]+" "+image[y][x+4][2]+" "+image[y][x+5][0]+" "+image[y][x+5][1]+" "+image[y][x+5][2]
                        +" "+image[y][x+6][0]+" "+image[y][x+6][1]+" "+image[y][x+6][2]+" "+image[y][x+7][0]+" "+image[y][x+7][1]+" "+image[y][x+7][2]);
            }
        }
        ;
        // System.out.println("AICI");
        out.close();
    }

//    private BufferedImage parsePPM() throws IOException, PPMDecoderException {
//        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        WritableRaster raster = img.getRaster();
//        for (int y = 0; y < height; ++y)
//            for (int x = 0; x < width; ++x)
//                for (int i = 0; i < 3; ++i) {
//                    parser.nextToken();
//                    if (parser.ttype == StreamTokenizer.TT_EOF)
//                        throw new EOFException("image appears to be truncated");
//                    if (parser.ttype != StreamTokenizer.TT_NUMBER)
//                        throw new PPMDecoderException("non-numeric value for sample " + i
//                                + " of pixel at (" + x + "," + y + ")");
//                    raster.setSample(x, y, i, (int) parser.nval);
//                }
//        return img;
//    }

    private static final int size = 256;

    public static Ppm readFile(String fileName) throws IOException {
        return new Ppm(new File(fileName));
    }
    public static Ppm readFile2(String fileName) throws IOException {
        return new Ppm(new File(fileName),true);
    }
    public static File writeFile(String filename) throws IOException {
        Ppm ppm = new Ppm(new File(filename));
        for (int i = 0; i < ppm.getWidth(); i++) {
            for (int j = 0; j < ppm.getHeight(); j++) {
                //if (i %2 == 0) {
                int[] pixel = ppm.getPixel(i, j);
                ppm.setPixel(i, j, pixel[0], pixel[1], pixel[2]);
                //} else {
                // ppm.setPixel(i, j, 0, 0xff, 0);
            }
        }

        try {
            File f = new File(System.getProperty("java.io.tmpdir"), "nt-P4.ppm");
            ppm.writeFile(f.getAbsolutePath(), false);
            System.out.println(f);
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[][][] check(int[][][] ppm2 ){
        int check=0;
        int ii=0;
        for(int i=0;i<height;i+=8){
            for(int j=0;j<width;j+=8){
                for(int k=i;k<i+8;k++)
                    for(int z=j;z<j+8;z++){
                if(Math.abs(ppm2[k][z][0]-this.image[k][z][0])<13) {
                   //System.out.println("INTRAAAAAAAAAAAAAAAAAAAAA");
                   // ii++;
                    if (Math.abs(ppm2[k][z][1] - this.image[k][z][1]) < 50) {
                    if (Math.abs(ppm2[k][z][2] - this.image[k][z][2]) < 100) {



                }else check=1;
                    }else check=1;
                    }else check=1;
                }
            if(check==0){
                for(int k=i;k<i+8;k++)
                    for(int z=j;z<j+8;z++){
                        ppm2[k][z][0]=this.image[k][z][0];
                        ppm2[k][z][1]=this.image[k][z][1];
                        ppm2[k][z][2]=this.image[k][z][2];
                    }
            }
        check=0;}
        }
       // System.out.println(ii);
        return ppm2;
    }
/*
    public static void main(String[] args) throws Exception {


        System.out.println(Runtime.getRuntime().availableProcessors());
String string=" 1 -12 -133";
int ccc=string.indexOf(" ")+1;

//string=string.substring();
Integer smth=Integer.parseInt(string.substring(ccc,ccc+1));

        System.out.println(smth);
        System.out.println(string.indexOf(smth.toString()));
        //smth=Integer.parseInt(string.substring(string.indexOf(smth.toString()) +2,string.indexOf(" ",string.indexOf(smth.toString())+2)));
        //smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
        int pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
        smth=Integer.parseInt(string.substring(pos1,string.indexOf(" ",pos1)));
        System.out.println(smth);
       // System.out.println(smth);
        System.out.println(string.indexOf(smth.toString()));
        pos1=string.indexOf(" ",string.indexOf(smth.toString()))+1;
        try{
            smth=Integer.parseInt(string.substring(pos1,string.indexOf(" ",pos1)));
        //smth=Integer.parseInt(string.substring(string.indexOf(string.indexOf(" ",string.indexOf(smth.toString())))+1,string.indexOf(" ",string.indexOf(smth.toString())+2)));
            //smth=Integer.parseInt(string.substring(string.indexOf(" ",string.indexOf(smth.toString()))+1,pos1));
        //System.out.println(smth);
        }catch(Exception e){
           // smth=Integer.parseInt(string.substring(string.indexOf(smth.toString()) +2));
            smth=Integer.parseInt(string.substring(pos1));
            System.out.println(smth);
        }
        //string=string.c
       // ccc=string.indexOf(" "+smth)+1;
       // smth=Integer.parseInt(string.substring(ccc,ccc+1));
       // System.out.println(smth);
        //ccc=string.indexOf(" "+smth)+1;
       // int cvv=string.indexOf("");
       // smth=Integer.parseInt(string.substring(ccc,ccc+1));
        int matrix1[][]={{1,2,3},
                {1,2,3},
                {1,2,3}};
        int matrix2[][]={{1,2,3},
                {1,2,3},
                {1,2,3}};
        int matrix3[][]={{1,2,3},
                {1,2,3},
                {1,2,4}};
        if(Arrays.deepEquals(matrix1,matrix2)){
            System.out.println("DA");
        }
        if(Arrays.deepEquals(matrix1,matrix3)){
            System.out.println("NU");
        }
        if(!Arrays.deepEquals(matrix1,matrix3)){
            System.out.println("DA");
        }
        //HttpServer server= new HttpServer().createContext("htttp://Ride");
//        HttpServer server = HttpServer.create(new InetSocketAddress(2424), 0);
  //      server.createContext("/Ride", new MyHandler());
    //    server.setExecutor(null);
      //  server.start();
//        server.wait();
        //Thread.sleep(10000);
        Ppm ppm = readFile2("C:\\Users\\mihai\\Desktop\\Video\\thumb000.ppm");
        try {
            ppm.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial5.ppm", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
       // Ppm ppm = readFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3.ppm");
        try {
         //   ppm.writeFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3trial1.ppm", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        YComponent ycomponent = new YComponent(ppm.getWidth(), ppm.getHeight());
        ycomponent.FormMatrix(ppm);
        ycomponent.FormList();
        //System.out.println(ycomponent.GetLengthBlocks());
        UComponent ucomponent = new UComponent(ppm.getWidth(), ppm.getHeight());
        ucomponent.FormMatrix(ppm);
        ucomponent.FormList();
       // System.out.println(ucomponent.GetLengthBlocks());
        VComponent vcomponent = new VComponent(ppm.getWidth(), ppm.getHeight());
        vcomponent.FormMatrix(ppm);
        vcomponent.FormList();
        //System.out.println(vcomponent.GetLengthBlocks());
        //new PpmViewer(ppm);
        int[][][] image = new int[ppm.getWidth()][ppm.getHeight()][3];
        ColorImage colorImage = new ColorImage(ppm.getWidth(), ppm.getHeight());
        int i = 0;
        List<double[][]> Yblocks = ycomponent.getBlocks();
        List<double[][]> Ublocks = ucomponent.getBlocks();
        List<double[][]> Vblocks = vcomponent.getBlocks();
        for (int x = 0; x < ppm.getHeight(); x = x + 8) {
            for (int y = 0; y < ppm.getWidth(); y = y + 8) {
                colorImage.Color(x, y, Yblocks.get(i), Ublocks.get(i), Vblocks.get(i));
                i++;
            }
        }
        //System.out.println(i);
        Ppm newppm = new Ppm(ppm.getWidth(), ppm.getHeight(), colorImage.getImage());
        newppm.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial2.ppm", false);
        Ppm ppm2 = readFile2("C:\\Users\\mihai\\Desktop\\Video\\New\\thumb002.ppm");
        ppm2.image=ppm.check(ppm2.image);
        Ppm ppm3 = readFile2("C:\\Users\\mihai\\Desktop\\Video\\New\\thumb003.ppm");
        ppm3.image=ppm2.check(ppm3.image);
        if(Arrays.deepEquals(ppm2.image,ppm3.image)){
            System.out.println("DAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
        try {
            ppm2.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial5.ppm", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Ppm ppm = readFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3.ppm");
        try {
            //   ppm.writeFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3trial1.ppm", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        YComponent Ycomponent = new YComponent(ppm2.getWidth(), ppm2.getHeight());
        Ycomponent.FormMatrix(ppm2);
        Ycomponent.FormList();
        //System.out.println(ycomponent.GetLengthBlocks());
        UComponent Ucomponent = new UComponent(ppm2.getWidth(), ppm2.getHeight());
        Ucomponent.FormMatrix(ppm2);
        Ucomponent.FormList();
        //System.out.println(ucomponent.GetLengthBlocks());
        VComponent Vcomponent = new VComponent(ppm2.getWidth(), ppm2.getHeight());
        Vcomponent.FormMatrix(ppm2);
        Vcomponent.FormList();
        //System.out.println(vcomponent.GetLengthBlocks());
        //new PpmViewer(ppm);
        int[][][] image2 = new int[ppm2.getWidth()][ppm2.getHeight()][3];
        ColorImage colorImage2 = new ColorImage(ppm2.getWidth(), ppm2.getHeight());
        i = 0;
        List<double[][]> yblocks = Ycomponent.getBlocks();
       // System.out.println(yblocks.size());
        List<double[][]> ublocks = Ucomponent.getBlocks();
        List<double[][]> vblocks = Vcomponent.getBlocks();
        for (int x = 0; x < ppm.getHeight(); x = x + 8) {
            for (int y = 0; y < ppm.getWidth(); y = y + 8) {
                colorImage2.Color(x, y, yblocks.get(i), ublocks.get(i), vblocks.get(i));
                i++;
            }
        }
        //System.out.println(i);
        Ppm newppm4 = new Ppm(ppm2.getWidth(), ppm2.getHeight(), colorImage2.getImage());
        newppm4.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial6.ppm", false);

/*
        colorImage.getGY(Yblocks);
            colorImage.getGU(Ublocks);
            colorImage.getGV(Vblocks);
        System.out.println("Ajunge");
            colorImage.Quantisize();
            colorImage.deQuantification();
            List<List<double[][]>> blocks=new ArrayList<>();
            blocks=colorImage.DeDCT(Yblocks,Ublocks,Vblocks);
        i=0;
        for(int x=0;x< ppm.getHeight();x=x+8){
            for(int y=0;y<ppm.getWidth();y=y+8){
                colorImage.Color2(x,y, blocks.get(0).get(i),blocks.get(1).get(i),blocks.get(2).get(i));
                i++;
            }
        }
        Ppm newppm2 = new Ppm(ppm.getWidth(), ppm.getHeight(), colorImage.getImage());
        newppm2.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial3.ppm", false);
        System.out.println("YYYYYYYYYYYYYYYY");
        for(int h=0;h<8;h++){
            for(int g=0;g<8;g++){
               /// System.out.println(Yblocks.get(10719)[h][g]);
            }}
        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
        for(int h=0;h<8;h++){
            for(int g=0;g<8;g++){
              //  System.out.println(yblocks.get(10719)[h][g]);
            }}
        colorImage.getGY(Yblocks,ppm.height,ppm.width);
        colorImage.getGU(Ublocks,ppm.height,ppm.width);
        colorImage.getGV(Vblocks,ppm.height,ppm.width);
        colorImage2.getGY(yblocks,ppm.height,ppm.width);
        colorImage2.getGU(ublocks,ppm.height,ppm.width);
        colorImage2.getGV(vblocks,ppm.height,ppm.width);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for(int h=0;h<8;h++){
            for(int g=0;g<8;g++){
               /// System.out.println(colorImage.GY.get(10719)[h][g]);
            }}
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for(int h=0;h<8;h++){
            for(int g=0;g<8;g++){
               // System.out.println(colorImage2.GY.get(10719)[h][g]);
            }}
        System.out.println("Ajunge");
        //colorImage.Quantisize();
        //colorImage.GY=Yblocks;
        //colorImage.GU=Ublocks;
        //colorImage.GV=Vblocks;
        colorImage.Quantisize();
        colorImage2.Quantisize();
        for(int h=0;h<8;h++){
            for(int g=0;g<8;g++){
               // System.out.println(colorImage.GY.get(10719)[h][g]);
            }}
        System.out.println("22222222222222222222222222222222222222222222222");
        for(int h=0;h<8;h++){
            for(int g=0;g<8;g++){
               // System.out.println(colorImage2.GY.get(10719)[h][g]);
            }}
        colorImage.zigZag();

        try {
            ppm2.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial5.ppm", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Ppm ppm = readFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3.ppm");
        try {
            //   ppm.writeFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3trial1.ppm", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        YComponent YYcomponent = new YComponent(ppm2.getWidth(), ppm2.getHeight());
        YYcomponent.FormMatrix(ppm3);
        YYcomponent.FormList();
        //System.out.println(ycomponent.GetLengthBlocks());
        UComponent UYcomponent = new UComponent(ppm2.getWidth(), ppm2.getHeight());
        UYcomponent.FormMatrix(ppm3);
        UYcomponent.FormList();
        //System.out.println(ucomponent.GetLengthBlocks());
        VComponent VYcomponent = new VComponent(ppm2.getWidth(), ppm2.getHeight());
        VYcomponent.FormMatrix(ppm3);
        VYcomponent.FormList();
        ColorImage colorImage3=new ColorImage(ppm3.width,ppm3.height);
        List<double[][]> yyblocks = YYcomponent.getBlocks();
        // System.out.println(yblocks.size());
        List<double[][]> uyblocks = UYcomponent.getBlocks();
        List<double[][]> vyblocks = VYcomponent.getBlocks();
       // System.out.println(ppm3.height);
        colorImage3.getGY(yyblocks,ppm3.height,ppm3.width);
        colorImage3.getGU(uyblocks,ppm3.height,ppm3.width);
        colorImage3.getGV(vyblocks,ppm3.height,ppm3.width);
        colorImage3.Quantisize();
       // colorImage.writeFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\TRIAL",false);
        ppm.writeFirst("C:\\Users\\mihai\\Desktop\\Video\\Working\\TRIAL",colorImage);
        ppm.write("C:\\Users\\mihai\\Desktop\\Video\\Working\\TRIAL2",colorImage,colorImage2);
        ppm2.write("C:\\Users\\mihai\\Desktop\\Video\\Working\\TRIAL3",colorImage2,colorImage3);

        i=0;

        Ppm ppmt=new Ppm(ppm,colorImage);
        // Ppm ppmt2=new Ppm(ppm,colorImage);
        long start1=System.currentTimeMillis();
        colorImage.decodeZZ();
        List<List<double[][]>> blockss = new ArrayList<>();
        colorImage.deQuantification();

        System.out.println("CANCER");
        long c=System.currentTimeMillis();
        blockss=colorImage.DeDCT();
        long ce=System.currentTimeMillis();
        System.out.println(c-ce);
        colorImage.GY=blockss.get(0);
        colorImage.GU=blockss.get(1);
        colorImage.GV=blockss.get(2);
        try {
            for (int x = 0; x < ppm.getHeight(); x = x + 8) {
                for (int y = 0; y < ppm.getWidth(); y = y + 8) {
                    colorImage.Color2(x, y, blockss.get(0).get(i), blockss.get(1).get(i), blockss.get(2).get(i));
                    i++;
                }
            }
        }catch (Exception e){
            //System.out.println(x);
            System.out.println(i);
        }
        Ppm newppm3 = new Ppm(ppm.getWidth(), ppm.getHeight(), colorImage.getImage());

        try {
            newppm3.writeFile("C:\\Users\\mihai\\Desktop\\Video\\Working\\nt-P3trial4.ppm", false);
        }catch(Exception e){
            System.out.println("Print");
        }
        long end1=System.currentTimeMillis();
       // newppm3.TRIAL("C:\\Users\\mihai\\Desktop\\Video\\Working\\TRIAL2",colorImage,"C:\\Users\\mihai\\Desktop\\Video\\Working\\02.ppm");
        System.out.println(start1-end1);
for(int h=0;h<8;h++){
    for(int g=0;g<8;g++){
        System.out.println(colorImage.GY.get(10719)[h][g]);
    }}
   Object latch=new Object();
    start1=System.currentTimeMillis();
    colorImage.image=new int[536][1280][3];
    colorImage.GY=new ArrayList<>();
    colorImage.GU=new ArrayList<>();
    colorImage.GV=new ArrayList<>();
//    Video video=new Video(colorImage,"first","C:\\Users\\mihai\\Desktop\\Video\\Working\\TRIAL2","C:\\Users\\mihai\\Desktop\\Video\\Working\\04.ppm");
    //video.setVideo(new Video(colorImage2,"Mihai","C:\\Users\\mihai\\Desktop\\Video\\Working\\TRIAL3","C:\\Users\\mihai\\Desktop\\Video\\Working\\03.ppm"));
    //video.latch=latch;
        //video.lock=0;
        /*
    List<Thread> threads=new ArrayList<>();
    while(video.video!=null){
        Thread thread=new Thread(video);
        threads.add(thread);
        //thread.run();
        threads.get(threads.size()-1).start();
    }/*
    for(int k=1;k<=threads.size();k++){
        threads.get(threads.size()-k).run();
    }
    for(Thread thread:threads){
        thread.join();
    }
  //  Thread thread=new Thread(video);
  //  thread.start();
   // thread.join();
  // synchronized (latch){
   // latch.notify();}
   // video.join();
   //// video.write();
    end1=System.currentTimeMillis();
    System.out.println(start1-end1);
ColorImage cl=colorImage2.clone();
        System.out.println("EUU");
//ppm.Downsize(new File("C:\\Users\\mihai\\Desktop\\Video\\New"));
        start1=System.currentTimeMillis();
        List<Thread> threads=new ArrayList<>();
        double o=1.0/4.0;
        double[] myPI=new double[8];
        for(int f=0;f<8;f++){
            myPI[f]=f*Math.PI/16;
        }
        double[] arr=new double[8];
        for(int f=0;f<8;f++){
            arr[f]=f*2;
            arr[f]=arr[f]+1;
            //arr[f]=Math.cos(arr[f]);
        }
        double[] cosinee=new double[64];
        int l=0;
        for(int f=0;f<8;f++)
            for(int j=0;j<8;j++){
                cosinee[l]=Math.cos(arr[f]*myPI[j]);
                l+=1;
            }
        double cc=1.0/Math.sqrt(2)*1.0/Math.sqrt(2);
        double cccc=1.0/Math.sqrt(2);
        File[] files=new File("C:\\Users\\mihai\\Desktop\\Video\\Trial2").listFiles();

       int number=0;

        for (File filee : files ) {

            ColorImage color=new ColorImage(ppm.width,ppm.height);
            Video3 video1=new Video3(color,"Mihai",filee.toString(),"C:\\Users\\mihai\\Desktop\\Video\\GO\\"+filee.getName()+".ppm",o,cosinee,cc,cccc);

            Thread threadd=new Thread(video1);
            threads.add(threadd);
            threadd.setPriority(10);
            threadd.start();

       }
        for(int m=0;m<threads.size();m++){
            threads.get(m).join();
        }

        end1=System.currentTimeMillis();
        System.out.println(start1-end1);

    }*/
public static void main(String args[]) throws Exception {
    Ppm ppm=new Ppm(new File("./VideoMaterial/images in ppm p3/thumb0001.ppm"),false);
   File dir= new File("./VideoMaterial/Downsized");
   dir.mkdir();
   // ppm.Downsize(new File("./VideoMaterial/images in ppm p3"),dir);
    long start1=System.currentTimeMillis();
    List<Thread> threads=new ArrayList<>();
    double o=1.0/4.0;
    double[] myPI=new double[8];
    for(int f=0;f<8;f++){
        myPI[f]=f*Math.PI/16;
    }
    double[] arr=new double[8];
    for(int f=0;f<8;f++){
        arr[f]=f*2;
        arr[f]=arr[f]+1;
        //arr[f]=Math.cos(arr[f]);
    }
    double[] cosinee=new double[64];
    int l=0;
    for(int f=0;f<8;f++)
        for(int j=0;j<8;j++){
            cosinee[l]=Math.cos(arr[f]*myPI[j]);
            l+=1;
        }
    double cc=1.0/Math.sqrt(2)*1.0/Math.sqrt(2);
    double cccc=1.0/Math.sqrt(2);
    File[] files=dir.listFiles();

    int number=0;
File output=new File("./VideoMaterial/Output");
output.mkdir();

    for (int i=0;i<360;i++ ) {

            ColorImage color = new ColorImage(ppm.width, ppm.height);
            Video5 video1 = new Video5(color, "Mihai", files[i].toString(), output+"/"+files[i].getName()+".ppm",files[i+1].toString(), output+"/"+files[i+2].getName()+".ppm",files[i+3].toString(), output+"/"+files[i+3].getName()+".ppm",files[i+4].toString(), output+"/"+files[i+4].getName()+".ppm",files[i+5].toString(), output+"/"+files[i+5].getName()+".ppm",files[i+6].toString(), output+"/"+files[i+6].getName()+".ppm", o, cosinee, cc, cccc);
            i+=5;
            Thread threadd = new Thread(video1);
            threads.add(threadd);
            threadd.setPriority(10);
            threadd.start();

    }
    for(int m=0;m<threads.size();m++){
        threads.get(m).join();
    }

   long end1=System.currentTimeMillis();
    System.out.println((start1-end1)/1000*-1+":Seconds");

}
    public void Downsize(File file,File dir) throws Exception {

        File[] files=file.listFiles();
        String filename="";
        int i=0;

        ColorImage colorImage2=new ColorImage(this.width,this.height);
        ColorImage colorImage3=new ColorImage(this.width,this.height);
        for (File filee : files) {




                String name=filee.getName();
                name=name.split(".ppm")[0];


                    Ppm ppm3=readFile2(filee.toString());

                    YComponent YYcomponent = new YComponent(ppm3.getWidth(), ppm3.getHeight());
                    YYcomponent.FormMatrix(ppm3);
                    YYcomponent.FormList();

                    UComponent UYcomponent = new UComponent(ppm3.getWidth(), ppm3.getHeight());
                    UYcomponent.FormMatrix(ppm3);
                    UYcomponent.FormList();

                    VComponent VYcomponent = new VComponent(ppm3.getWidth(), ppm3.getHeight());
                    VYcomponent.FormMatrix(ppm3);
                    VYcomponent.FormList();
                    colorImage3=new ColorImage(ppm3.width,ppm3.height);
                    List<double[][]> yyblocks = YYcomponent.getBlocks();

                    List<double[][]> uyblocks = UYcomponent.getBlocks();
                    List<double[][]> vyblocks = VYcomponent.getBlocks();

                    colorImage3.getGY(yyblocks,ppm3.height,ppm3.width);
                    colorImage3.getGU(uyblocks,ppm3.height,ppm3.width);
                    colorImage3.getGV(vyblocks,ppm3.height,ppm3.width);
                    colorImage3.Quantisize();
                    this.writeFirst(dir+"/"+name,colorImage3);

                }
            }



    public void TRIAL(String filename,ColorImage colorImage,String output) throws IOException {
        long start=System.currentTimeMillis();
        List<String> lines;
        lines = Files.readAllLines(Paths.get(String.valueOf(new File(filename))));
        int check=0;
        for(String string : lines){
            if(string==" 0"||check==0){
                check=1;
            }
            else{
            String[] ceva=string.split(",");
            //System.out.println("CEVAAA");
            //System.out.println(ceva);
            String position=ceva[0];
           String numbers=ceva[1];
            String[] list=numbers.split(" ");
            List<Integer> num=new ArrayList<>();
            for(int i=0;i<list.length;i++){
                num.add(Integer.parseInt(list[i]));
            }
            double[][] block=colorImage.decodeBlockZZ(num);
                block=colorImage.decodeQuantBlock(block);
           // block=colorImage.decodeDCTBlock(block);

            int pos=Integer.parseInt(position);
            int ce=pos%3;
            pos=pos/3;
            if(ce==0) colorImage.GY.set(pos,block);
            else if(ce==1){
                colorImage.GU.set(pos,block);
            }else{
                colorImage.GV.set(pos,block);
            }


        }}

        int i=0;
        for (int x = 0; x < colorImage.height; x = x + 8) {
            for (int y = 0; y < colorImage.width; y = y + 8) {
                try{
                colorImage.Color2(x, y, colorImage.GY.get(i),colorImage.GU.get(i),colorImage.GV.get(i));
                i++;}catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        Ppm newppm3 = new Ppm(colorImage.width, colorImage.height, colorImage.getImage());
        try {
            newppm3.writeFile(output, false);
        }catch(Exception e){
            System.out.println("Print");
        }
        long end=System.currentTimeMillis();
        System.out.println("CEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println(start-end);
    }
    //Might be a problem with GU GV might bew equal chance are not BUT KEEP IN MIND
    public void write(String filename,ColorImage colorImage,ColorImage colorImage1) throws Exception {
        colorImage.setWriter(filename);
        BufferedWriter out=new BufferedWriter(new FileWriter(filename));
        for(int i=0;i<colorImage1.GY.size();i++){
            String line=colorImage.writeDiff(filename,colorImage1.GY.get(i),i,0);
            if(line!=null) writeLine(out,line);
            line=colorImage.writeDiff1(filename,colorImage1.GU.get(i),i,1);
            if(line!=null) writeLine(out,line);
            line=colorImage.writeDiff2(filename,colorImage1.GV.get(i),i,2);
            if(line!=null) writeLine(out,line);
        }
        out.close();

    }
    public void writeFirst(String filename,ColorImage colorImage) throws Exception {
      //  colorImage.setWriter(filename);
        BufferedWriter out=new BufferedWriter(new FileWriter(filename));
        for(int i=0;i<colorImage.GY.size();i++){
            String line=colorImage.writeBlock(colorImage.GY.get(i));
            if(line!=null) writeLine(out,line);
            line=colorImage.writeBlock(colorImage.GU.get(i));
            if(line!=null) writeLine(out,line);
            line=colorImage.writeBlock(colorImage.GV.get(i));
            if(line!=null) writeLine(out,line);
        }
        out.close();

    }
public void read(ColorImage colorImage,ColorImage colorImage2){
        for(int i=0;i<colorImage.GY.size();i++){

        }

}
    public void run() {

        colorImage.DecodeZZ2();
        /*
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();

        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();

        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
        colorImage.DecodeZZ2();
*/
        colorImage.deQuantification();











        //colorImage.deQuantification();

        List<List<double[][]>> blockss = new ArrayList<>();


        int i = 0;
        blockss = colorImage.GetME();

        for (int x = 0; x < this.getHeight(); x = x + 8) {
            for (int y = 0; y < this.getWidth(); y = y + 8) {
                colorImage.Color2(x, y, blockss.get(0).get(i), blockss.get(1).get(i), blockss.get(2).get(i));
                i++;
            }
        }

        Ppm newppm3 = new Ppm(this.getWidth(), this.getHeight(), colorImage.getImage());
try {
    newppm3.writeFile("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\nt-P3trial4.ppm", false);
}catch(Exception e){
    System.out.println("Print");
}
    }
}
class MyHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        //InputStream is = t.getRequestBody();
        //read(is); // .. read the request body

        File folder = new File("D:\\_DOCUMENTE_MIHAI.OSIAN\\Downloads\\Video\\Ride.mp4");
        File[] listOfFiles = folder.listFiles();

        //for (File file : listOfFiles) {
            if (folder.isFile()) {
                //System.out.println(file.getName());
                File response = folder;

                //response.setContentLength(resultDataA.length + resultDataB.length);
                t.sendResponseHeaders(200, response.length());

                t.getResponseHeaders().set("Content-Type", "video/mp4");
               // byte data[] = readFileData(folder, (int) folder.length());
                FileInputStream is=new FileInputStream(folder);
               // byte data[]=IOUtils.toByteArray(is);
                is.close();
                //FileOutputStream os = new FileOutputStream(folder);
                // ImageIO io= new ImageIO(file);
                OutputStream oss = t.getResponseBody();
              //  oss.write(data,0,data.length);
                oss.close();

            }
        }




    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }}
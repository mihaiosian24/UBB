import java.util.ArrayList;
import java.util.List;

public class UComponent {
    public double [][] Umatrix;
    List<double[][]> blocks=new ArrayList<double[][]>();
    List<Integer> positions=new ArrayList<Integer>();
    int width;
    int height;

    public UComponent(int width,int height){
        this.Umatrix=new double [height][width];
        this.width=width;
        this.height=height;
    }
    public double[][] getUmatrix(){
        return this.Umatrix;
    }
    public void FormMatrix(Ppm ppm){
        for(int x=0;x<ppm.getHeight();x++){
            for(int y=0;y<ppm.getWidth();y++){
                //this.Umatrix[x][y]=128-0.1687*ppm.getImage()[x][y][0]-0.3312*ppm.getImage()[x][y][1]+0.5*ppm.getImage()[x][y][2];
                this.Umatrix[x][y]=(int)(128-0.169*ppm.getImage()[x][y][0]-0.331*ppm.getImage()[x][y][1]+0.499*ppm.getImage()[x][y][2]);
            }
        }
    }


    public void FromNewMatrix(Ppm ppm){
        for(int x=0;x<ppm.getWidth();x+=2){
            for(int y=0;y<ppm.getHeight();y+=2){
                //this.Vmatrix[x][y]=0.615*ppm.getImage()[x][y][0]-0.515*ppm.getImage()[x][y][1]-0.1*ppm.getImage()[x][y][2];
                double red,yellow,blue;
                List<Double> doubles=this.getAvgColor(x,y,ppm.getImage());
                red=doubles.get(0);
                yellow=doubles.get(1);
                blue=doubles.get(2);
                this.Umatrix[x][y]=-0.147*red-0.289*yellow-0.436*blue;
                this.Umatrix[x][y+1]=-0.147*red-0.289*yellow-0.436*blue;
                this.Umatrix[x+1][y]=-0.147*red-0.289*yellow-0.436*blue;
                this.Umatrix[x+1][y+1]=-0.147*red-0.289*yellow-0.436*blue;
            }
        }
    }

    public List<Double> getAvgColor(int x,int y,int[][][] image){
        double red=0,yellow=0,blue=0;
        List<Double> doubles=new ArrayList<>();
        for(int i=x;i<x+2;i++)
            for(int j=y;j<y+2;j++){
                red+=image[x][y][0];
                yellow+=image[x][y][1];
                blue+=image[x][y][2];
            }
        doubles.add(red/4);
        doubles.add(yellow/4);
        doubles.add(blue/4);
        return doubles;
    }


    public void FormList(){
        int pos=0;
        for(int x=0;x<this.height;x=x+8){
            for(int y=0;y<this.width;y=y+8){
                //this.blocks.add(getBlock(x,y));
                double [][]block=this.getUBlock(this.getBlock(x,y));
                this.blocks.add(block);
                this.positions.add(pos);
                pos++;
            }}
    }


    public void FormNewList(){
        int pos=0;
        for(int x=0;x<this.width;x=x+8){
            for(int y=0;y<this.height;y=y+8){
                //this.blocks.add(getBlock(x,y));
                double [][]block=this.getBlock(x,y);
                this.blocks.add(block);
                this.positions.add(pos);
                pos++;
            }}
    }

    public double[][] getBlock(int x,int y){
        double [][] block=new double[8][8];
        int k=0;
        int z=0;
        for(int i=x;i<x+8;i++){
            z=0;
            for(int j=y;j<y+8;j++){
                try{
                    block[k][z]=this.Umatrix[i][j];
                    }
                catch(Exception e){
                    System.out.println(j);
                    System.out.println(y);
                    System.out.println("CEva");
                }
                z++;
            }
            k++;
        }

        return block;
    }

    public double[][] getUBlock(double[][] block){
        double [][] ublock=new double[4][4];
        double color=0;
        int k=0;
        for(int i=0;i<4;i+=1){
            int z=0;
            for(int j=0;j<4;j+=1){
                color=this.getColor2x2(i+k,j+z,block);
                ublock[i][j]=color;
                //ublock[i][j+1]=color;
                //ublock[i+1][j]=color;
                //ublock[i+1][j+1]=color;
                z+=1;
            }
            k+=1;
        }
        return ublock;

    }
    public int GetLengthBlocks(){
        return this.blocks.size();
    }

    public double getColor2x2(int x,int y,double[][] block){
        double color=0;

        for(int i=x;i<x+2;i++)
            for(int j=y;j<y+2;j++){
                color+=block[i][j];
            }
        color=color/4;
        return color;
    }
    public List<double[][]> getBlocks(){
        return this.blocks;
    }
}

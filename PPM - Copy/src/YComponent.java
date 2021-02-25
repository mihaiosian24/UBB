import java.util.ArrayList;
import java.util.List;

public class YComponent {
    public double [][] Ymatrix;
    List<double[][]> blocks=new ArrayList<double[][]>();
    List<Integer> positions=new ArrayList<Integer>();
    int width;
    int height;

    public YComponent(int width,int height){
        this.Ymatrix=new double [height][width];
        this.width=width;
        this.height=height;
        this.blocks=new ArrayList<>();
        this.positions=new ArrayList<>();
    }
    public double[][] getYmatrix(){
        return this.Ymatrix;
    }
    public void FormMatrix(Ppm ppm){
        for(int x=0;x<ppm.getHeight();x++){
            for(int y=0;y<ppm.getWidth();y++){
                this.Ymatrix[x][y]=(int)(0.299*ppm.getImage()[x][y][0]+0.587*ppm.getImage()[x][y][1]+0.114*ppm.getImage()[x][y][2]);
            }
        }
    }


    public void FormList(){
        int pos=0;
        for(int x=0;x<this.height;x=x+8){
            for(int y=0;y<this.width;y=y+8){
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
                block[k][z]=this.Ymatrix[i][j];
                    }
                catch(Exception e){
                    System.out.println(j);
                    System.out.println(y);
                }
                z++;
            }
            k++;
        }

        return block;
    }
    public int GetLengthBlocks(){
        return this.blocks.size();
    }

    public  ArrayList<double[][]> getBlocks(){
        return new ArrayList<>(this.blocks);
    }
}

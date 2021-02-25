import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ColorImage implements Runnable{
    public int[][][] image;
    public List<double [][]> GY;
    public List<double[][]> GU;
    public List<double [][]> GV;
    public List<List<List<Integer>>> zigZag;
    public List<Integer> ZigZag;
    public int [][] quant;
    public int[][] quant2;
    public int height;
    public int width;
    public BufferedWriter out;
    public ColorImage(int width,int height){
        this.image=new int [height][width][3];
        this.height=height;
        this.width=width;
        this.GY=new ArrayList<>();
        this.GU=new ArrayList<>();
        this.GV=new ArrayList<>();
        this.zigZag=new ArrayList<>();
        this.ZigZag=new ArrayList<>();
        this.quant=new int[8][];
        this.quant[0]=new int[]{6 ,  4 ,  4 ,  6 ,  10 , 16 , 20 , 24};
        this.quant[1]=new int[]{5  , 5 ,  6 ,  8 ,  10,  23,  24 , 22};
        this.quant[2]=new int[]{6 ,  5 ,  6 ,  10  ,16 , 23 , 28,  22};
        this.quant[3]=new int[]{6  , 7 ,  9 ,  12,  20,  35,  32 , 25};
        this.quant[4]=new int []{7   ,9 ,  15,  22 , 27,  44 , 41 , 31};
        this.quant[5]=new int []{10 , 14 , 22 , 26 , 32,  42 , 45 , 37};
        this.quant[6]=new int []{20 , 26 , 31 , 35 , 41 , 48 , 48 , 40};
        this.quant[7]=new int []{29 , 37 , 38,  39 , 45 , 40 , 41 , 40};
        this.quant2=new int[8][];
        this.quant2[0]=new int[]{1,18,24,47,99,99,99,99};
        this.quant2[1]=new int[]{18,21,26,99,99,99,99,99};
        this.quant2[2]=new int[]{24,26,99,99,99,99,99,99};
        this.quant2[3]=new int[]{47,99,99,99,99,99,99,99};
        this.quant2[4]=new int[]{99,99,99,99,99,99,99,99};
        this.quant2[5]=new int[]{99,99,99,99,99,99,99,99};
        this.quant2[6]=new int[]{99,99,99,99,99,99,99,99};
        this.quant2[7]=new int[]{99,99,99,99,99,99,99,99};






    }
    public List<double[][]>getgy(){
        return this.GY;
    }
    public List<double[][]>getgu(){
        return this.GU;
    }
    public List<double[][]>getgv(){
        return this.GV;
    }
@Override
public ColorImage clone(){
    ColorImage customer =null;
    try {
        customer = (ColorImage) super.clone();
        for(double[][] doublee:this.GY){
            customer.GY.add(doublee);
        }
        for(double[][] doublee:this.GU){
            customer.GU.add(doublee);
        }
        for(double[][] doublee:this.GV){
            customer.GV.add(doublee);
        }
        //customer.GY=
    }catch (CloneNotSupportedException e) {
        customer = new ColorImage(this.width,this.height);
        for(double[][] doublee:this.GY){
            customer.GY.add(doublee);
        }
        for(double[][] doublee:this.GU){
            customer.GU.add(doublee);
        }
        for(double[][] doublee:this.GV){
            customer.GV.add(doublee);
        }
        /*
        customer.GY=Arrays.copyOf(this.GY,this.GY.size());
        customer.GU=new ArrayList<double[][]>(this.GY);
        customer.GV=new ArrayList<double[][]>(this.GY);
      /*  Collections.copy(customer.GY,this.GY);
        Collections.copy(customer.GU,this.GU);
        Collections.copy(customer.GV,this.GV);*/

    }
   // customer.order=this.order.clone();
    return customer;
}
    public double[][] decodeQuantBlock(double[][] block){

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(block[i][j]!=0)
                block[i][j]=block[i][j]*this.quant[i][j];

        return block;
    }

    public double[][] decodeDCTBlock(double[][] block,double o,double[] cosinee,double cc,double ccc){
        double [][] GV=new double[8][8];
        /*
        long start=System.currentTimeMillis();

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
            double ccc=1.0/Math.sqrt(2);
            long end=System.currentTimeMillis();
        System.out.println(start-end);
        */for(int x=0;x<8;x++){
                int g=x*8;
            for(int y=0;y<8;y++){
                int h=y*8;
                double sum=0;
                double sumU=0;
                double sumV=0;
                for(int k=0;k<8;k++){
                    double dummy=cosinee[g+k];
                    for(int z=0;z<8;z++){
                        if(block[k][z]==0)
                        {

                        }else{
                            double puppet=dummy*cosinee[h+z];
                            double ceva=0;

                            if(k==0 && z==0)
                                ceva=cc;

                            else
                            if(k>0 && z>0)
                                ceva=1;
                            else
                                ceva=ccc;

                                sum+=ceva*(block[k][z])*puppet;

                        }}}
                GV[x][y]=o*sum+128;

    }}return GV;}
    public double[][] decodeZZZ(List<Integer> list){
        double[][] boub=new double[8][8];
        for(int i=0;i<8;i++){
            boub[i]= new double[]{0,0,0,0,0,0,0,0};
        }
        //boub[0][0]=list.get(1);

        int i=0;
        int lastpos=0;
        int val1=0;
        int val2;
        try{
        while(i<list.size()-2){
         val1=list.get(i);
         val2=list.get(i+1);
       //  if(val1==1 && lastpos==0){
        //     boub[0][0]=list.get(i+2);
        // }else
        if(val2>10 &&lastpos==0){
            boub[0][0]=val2;
        }else
        if(list.get(i+2)<0 && lastpos==0){
            lastpos=lastpos+val1;

            boub[lastpos/8][lastpos%8]=list.get(i+2);
        }else
        if(val1==0 && val2==0){
            break;
        }
        else {
            lastpos=lastpos+val1+1;
            //if(lastpos==64){lastpos=63;}
            boub[lastpos/8][lastpos%8]=list.get(i+2);
        }
        i+=3;
        }}catch(Exception e){
           // System.out.println("INSIDEEEEEEEEEEEEEEEEE");
           // e.printStackTrace();
           // System.out.println(i);
          //  System.out.println(lastpos);
          //  System.out.println(val1);
          //  for(int k=0;k<list.size();k++){
         //       System.out.println(list.get(k));
         //   }

        }
        return boub;


    }
    public double[][] decodeBlockZZ(List<Integer> list){
        int k=0;
        int z=0;
        int pos =-1;
        int check=0;
        int i=0,j=0;
        double[][] block=new double[8][8];
        /*
        if(k==0 && z==0 &&  list.size()>i+j+1&& list.get(i+j)==0&&list.get(i+j+1)==0)
        {
            j--;
        }*/

        while(k<8 && z<8  && list.size()>i+j+1){

            if(list.get(i)==0&&list.get(i+1)==0)
            {j+=3;
                //  System.out.println("V3");
                while(k<8&&z<8){
                    block[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}
                    }
                }
            }
            else{
            if(k==0 && z==0 && list.get(i+j)!=0 && list.get(i+j+1)!=0 && getCorrect(list.get(i+j),list.get(i+j+1))){
                // System.out.println(ZigZag.get(i+j));
                block[k][z]=list.get(i+j+1);
                j++;
                //System.out.println("V");
                //System.out.println(ZigZag.get(i+j));
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(i+j+3<list.size()&&list.get(i+j+1)>0&&list.get(i+j+2)>0&&list.get(i+j+3)!=0){
                //  System.out.println(ZigZag.get(i+j+1));
                int aux=0;
                //System.out.println("V1");
                while(aux<list.get(i+j+1)){
                    //System.out.println("!!!!");
                    block[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }

                block[k][z]=list.get(i+j+3);
                j+=3;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }}
            }else {

                if(list.get(i+j+1)==0 &&  list.get(i+j+2)!=0&&list.get(i+j+3)!=0 )
                {
                    //System.out.println("V2");
                    block[k][z]=list.get(i+j+3);
                    j+=3;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else {
                        if (k == 0 || k == 7) {

                            z++;
                            check = 1;
                        } else {
                            if (z == 0 || z == 7) {
                                k++;
                                check = 1;
                            } else {
                                k += pos;
                                z -= pos;
                            }
                        }}
                }
                else
                {j+=3;
                    //  System.out.println("V3");
                    while(k<8&&z<8){
                        block[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){

                                z++;
                                check=1;
                            }else{
                                if(z==0 || z==7 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }}
                        }
                    }
                }
            }
        }}
        return block;
    }
    public boolean getCorrect(int size,int number){
        if(size==1 && (number<=-2 || number>=2)){
            return false;
        }
        if(size==2 && (number<=-4 || number>=4)){
            return false;
        }
        if(size==3 && (number<=-8 || number>=8)){
            return false;
        }
        if(size==4 && (number<=-16 || number>=16)){
            return false;
        }
        if(size==5 && (number<=-32 || number>=32)){
            return false;
        }
        if(size==6 && (number<=-64 || number>=64)){
            return false;
        }
        if(size==7 && (number<=-128 || number>=128)){
            return false;
        }
        if(size==8 && (number<=-256 || number>=256)){
            return false;
        }
        if(size==9 && (number<=-512 || number>=512)){
            return false;
        }


        return true;
    }
    protected void writeLine( String line) throws Exception {
        if(out==null){
            System.out.println("GOL");
        }
        out.write(line);
        out.newLine();

    }

    public void setWriter(String fileName) throws IOException {
        this.out = new BufferedWriter(new FileWriter(fileName,true));
        if(out==null){
            System.out.println("GOL2");
        }
    }
        public void writeFile(String fileName, boolean scanMaxValue) throws Exception{
            this.out = new BufferedWriter(new FileWriter(fileName,true));
            writeLine( "P3");
            writeLine( "" + width + " " + height);

            //int [][][] image=image;
            int i = 0;

            for (int y = 0; y < ZigZag.size(); y++) {
                    //writeLine(out, "" + image[x][y][0] + " " + image[x][y][1] + " " + image[x][y][2]);
                //String string="";
               // while(ZigZag.get(y+)>0 || ZigZag.get(y+1)>0){
                  //    string=string+ZigZag.get(y).toString()+" ";
                    //  y++;
                  //}
                //string=string+ZigZag.get(y).toString()+" ";
                writeLine(ZigZag.get(y).toString());
            }


            out.close();
        }

        public String writeDiff(String fileName,double[][] GY,Integer position,int something) throws Exception {
          //  BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));

            //writeLine(out, "P3");
            //writeLine(out, "" + width + " " + height);

            //int [][][] image=image;
            //int i = 0;
            Integer val=position*3+something;
            String string="";//val.toString()+",";
            //for(int i=0;i<this.GY.size();i++){
            Object[] obj1={GY};
            Object[] obj2={this.GY.get(position)};
                //if(!Arrays.deepEquals(GY,this.GY.get(position))){
                //  if(!Arrays.deepEquals(obj1,obj2)){
                    //System.out.println(position);
                    double[][] inti;
                   // System.out.println("YYYYYYYYYYYYYYY");
                   // inti=this.getBlockDct(GY);
                    List<Integer> list1=new ArrayList<>();
                    list1=this.getBlockZig(GY);
                    for(int y=0;y<list1.size();y++){
                        string=string+" "+list1.get(y).toString();
                    }
                    return string;
                }


            //}

           // return null;
            //out.close();
       // }
        public String writeBlock(double [][] GY){
        String string="";
        List<Integer> list1=new ArrayList<>();
        list1=this.getBlockZig(GY);
            for(int y=0;y<list1.size();y++){
                string=string+list1.get(y).toString()+" ";
            }
            return string;
        }
    public String writeDiff1(String fileName,double[][] GY,Integer position,int something) throws Exception {
        //BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));

        //writeLine(out, "P3");
        //writeLine(out, "" + width + " " + height);

        //int [][][] image=image;
        //int i = 0;
        Integer val=position*3+something;
        String string="";//val.toString()+",";
        //for(int i=0;i<this.GY.size();i++){
        //if(!Arrays.deepEquals(GY,this.GU.get(position))){
        Object[] obj1={GY};
        Object[] obj2={this.GU.get(position)};
        //if(!Arrays.deepEquals(GY,this.GY.get(position))){
        //if(!Arrays.deepEquals(obj1,obj2)){
            //System.out.println("UUUUUUUUU");
            double[][] inti;
            // inti=this.getBlockDct(GY);
            List<Integer> list1=new ArrayList<>();
            list1=this.getBlockZig(GY);
            for(int y=0;y<list1.size();y++){
                string=string+" "+list1.get(y).toString();
            }
            return string;
      //  }


        //}

//return null;
        //out.close();
    }//return null;}
    public String writeDiff2(String fileName,double[][] GY,Integer position,int something) throws Exception {
        //BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

        //writeLine(out, "P3");
        //writeLine(out, "" + width + " " + height);

        //int [][][] image=image;
        //int i = 0;
        Integer val=position*3+something;
        String string="";//val.toString()+",";
        //for(int i=0;i<this.GY.size();i++){
        Object[] obj1={GY};
        Object[] obj2={this.GV.get(position)};
        //if(!Arrays.deepEquals(GY,this.GY.get(position))){
        //if(!Arrays.deepEquals(obj1,obj2)){
        //if(!Arrays.deepEquals(GY,this.GV.get(position))){
            double[][] inti;
            //System.out.println("VVVVVVVVVVVVVVVVVVVV");
            // inti=this.getBlockDct(GY);
            List<Integer> list1=new ArrayList<>();
            list1=this.getBlockZig(GY);
            for(int y=0;y<list1.size();y++){
                string=string+" "+list1.get(y).toString();
            }
            return string;
      // }

       //return null;
        //}



    }
 public List<Integer> getBlockZig(double[][] block){
    List<Integer> list1=new ArrayList<>();
        int zeros=0;
    int i=0;
    int j=0;
    int pos=-1;
    int check=0;
    // int j=0;
            while(j<8 && i<8){

        //List<Integer> value1=new ArrayList<>();
        //List<Integer> value2=new ArrayList<>();
        if(block[i][j]==0){
            zeros++;
        }
        else {

            int size=0;
            if(block[i][j]>=1||block[i][j]<=-1){
                size=1;
            }
            if(block[i][j]>=2||block[i][j]<=-2){
                size=2;
            }
            if(block[i][j]>=4||block[i][j]<=-4){
                size=3;
            }
            if(block[i][j]>=8||block[i][j]<=-8){
                size=4;
            }
            if(block[i][j]>=16||block[i][j]<=-16){
                size=5;
            }
            if(block[i][j]>=32||block[i][j]<=-32){
                size=6;
            }
            if(block[i][j]>=64||block[i][j]<=-64){
                size=7;
            }
            if(block[i][j]>=128||block[i][j]<=-128){
                size=8;
            }
            if(block[i][j]>=256||block[i][j]<=-256){
                size=9;
            }
            if(block[i][j]>=512||block[i][j]<=-512){
                size=10;
            }
            if(i!=0 || j!=0){
               // ZigZag.add(zeros);
                list1.add(zeros);
                zeros=0;}
            list1.add(size);

            list1.add((int)(block[i][j]));

            //list1.add(value1);
            //list1.add(value2);

        }
        if(check==1){
            pos=pos*(-1);
            check=0;
            i+=pos;
            j-=pos;
        }else{
            if(i==0 || i==7){
                j++;
                check=1;
            }else{
                if(j==0 || j==7 ){
                    i++;
                    check=1;
                }else{
                    i+=pos;
                    j-=pos;
                }
            }
        }




        List<Integer> zeross=new ArrayList<>();

    }
        if(zeros>0){
        list1.add(0);
        list1.add(0);
    }
    //list1.add(zeross);
    // System.out.println(list1.size());
        return list1;
}
public double[][] getBlockDct(double[][] gy){
    double [][] G=new double[8][8];
    for(int x=0;x<8;x++){
        for(int y=0;y<8;y++){
            double sum=0;
            for(int k=0;k<8;k++){
                for(int z=0;z<8;z++){

                    sum+=(gy[k][z]-128)*Math.cos((2*k+1)*x*Math.PI/16)*Math.cos((2*z+1)*y*Math.PI/16);
                }}

            if(x==0 && y==0)
                G[x][y]=1.0/4.0*1.0/Math.sqrt(2)*1.0/Math.sqrt(2)*sum;

            else
            if(x>0 && y>0)
                G[x][y]=1.0/4.0*1*1*sum;
            else
                G[x][y]=1.0/4.0*1*1.0/Math.sqrt(2)*sum;
            // System.out.println(sum);
            // System.out.println((1.0/4.0)*sum);
        }}return G;
}
    public List<double[][]> getUandV(double[][] u,double[][] v)
    {
        double [][] ub=new double[8][8];
        double [][] vb=new double[8][8];

        int k=0;

        for(int i =0;i<4;i++){
            int z=0;
            for(int j=0;j<4;j++){
                ub[k][z]=u[i][j];
                ub[k+1][z]=u[i][j];
                ub[k][z+1]=u[i][j];
                ub[k+1][z+1]=u[i][j];
                vb[k][z]=v[i][j];
                vb[k+1][z]=v[i][j];
                vb[k][z+1]=v[i][j];
                vb[k+1][z+1]=v[i][j];
                z+=2;
            }
            k+=2;
        }
        List<double[][]> blocks=new ArrayList<>();
        blocks.add(ub);
        blocks.add(vb);
        return blocks;
    }
    public void Color(int x, int y, double[][] YBlock,double[][] Ublock,double[][] VBlock){
        int k=0;
        for (int i=x;i<x+8;i++){
            int z=0;
           List<double[][]> blocks=this.getUandV(Ublock,VBlock);
            //double [][]VBloc=blocks.get(1);
            //double [][]Ubloc=blocks.get(0);
            for(int j=y;j<y+8;j++){
                int x4x4=k/2;
                int y4x4=z/2;
                this.image[i][j][0]= (int) (YBlock[k][z]+1.402*(VBlock[x4x4][y4x4]-128));
                this.image[i][j][2]= (int) (YBlock[k][z]+1.772*(Ublock[x4x4][y4x4]-128));
                this.image[i][j][1]= (int) (YBlock[k][z]-0.344*(Ublock[x4x4][y4x4]-128)-0.714*(VBlock[x4x4][y4x4]-128));
                /*this.image[i][j][0]= (int) (YBlock[k][z]+1.402*(VBloc[k][z]-128));
                this.image[i][j][2]= (int) (YBlock[k][z]+1.772*(Ubloc[k][z]-128));
                this.image[i][j][1]= (int) (YBlock[k][z]-0.344*(Ubloc[k][z]-128)-0.714*(VBloc[k][z]-128));
                *///this.image[i][j][0]=(int)VBlock[x4x4][y4x4];
                //this.image[i][j][1]=(int)VBlock[x4x4][y4x4];
                //this.image[i][j][2]=(int)VBlock[x4x4][y4x4];
                z++;
                if(image[i][j][0]<0)
                    image[i][j][0]=0;
                if(image[i][j][0]>255)
                    image[i][j][0]=255;
                if(image[i][j][1]<0)
                    image[i][j][1]=0;
                if(image[i][j][1]>255)
                    image[i][j][1]=255;
                if(image[i][j][2]<0)
                    image[i][j][2]=0;
                if(image[i][j][2]>255)
                    image[i][j][2]=255;
            }
            k++;
        }

    }
    public List<List<double[][]>> GetME(){
        List<List<double[][]>> LIST=new ArrayList<>();
        LIST.add(GY);
        LIST.add(GU);
        LIST.add(GV);
        return LIST;
    }

    public void Color2(int x, int y, double[][] YBlock,double[][] Ublock,double[][] VBlock){
        int k=0;
        for (int i=x;i<x+8;i++){
            int z=0;
            //List<double[][]> blocks=this.getUandV(Ublock,VBlock);
            //double [][]VBloc=blocks.get(1);
            //double [][]Ubloc=blocks.get(0);
            for(int j=y;j<y+8;j++){
                int x4x4=k/2;
                int y4x4=z/2;
                this.image[i][j][0]= (int) (YBlock[k][z]+1.402*(VBlock[k][z]-128));
                this.image[i][j][2]= (int) (YBlock[k][z]+1.772*(Ublock[k][z]-128));
                this.image[i][j][1]= (int) (YBlock[k][z]-0.344*(Ublock[k][z]-128)-0.714*(VBlock[k][z]-128));
                /*this.image[i][j][0]= (int) (YBlock[k][z]+1.402*(VBloc[k][z]-128));
                this.image[i][j][2]= (int) (YBlock[k][z]+1.772*(Ubloc[k][z]-128));
                this.image[i][j][1]= (int) (YBlock[k][z]-0.344*(Ubloc[k][z]-128)-0.714*(VBloc[k][z]-128));
                *///this.image[i][j][0]=(int)VBlock[x4x4][y4x4];
                //this.image[i][j][1]=(int)VBlock[x4x4][y4x4];
                //this.image[i][j][2]=(int)VBlock[x4x4][y4x4];
                z++;
                if(image[i][j][0]<0)
                    image[i][j][0]=0;
                if(image[i][j][0]>255)
                    image[i][j][0]=255;
                if(image[i][j][1]<0)
                    image[i][j][1]=0;
                if(image[i][j][1]>255)
                    image[i][j][1]=255;
                if(image[i][j][2]<0)
                    image[i][j][2]=0;
                if(image[i][j][2]>255)
                    image[i][j][2]=255;
            }
            k++;
        }

    }

    public void Color3(int x, int y, double[][] YBlock,double[][] Ublock,double[][] VBlock){
        int k=0;
        for (int i=x;i<x+8;i++){
            int z=0;
            //List<double[][]> blocks=this.getUandV(Ublock,VBlock);
            //double [][]VBloc=blocks.get(1);
            //double [][]Ubloc=blocks.get(0);
            for(int j=y;j<y+8;j++){
                int x4x4=k/2;
                int y4x4=z/2;
                this.image[i][j][0]= (int) (YBlock[k][z]+1.402*(VBlock[k][z]-128));
                this.image[i][j][2]= (int) (YBlock[k][z]+1.772*(Ublock[k][z]-128));
                this.image[i][j][1]= (int) (YBlock[k][z]-0.344*(Ublock[k][z]-128)-0.714*(VBlock[k][z]-128));
                /*this.image[i][j][0]= (int) (YBlock[k][z]+1.402*(VBloc[k][z]-128));
                this.image[i][j][2]= (int) (YBlock[k][z]+1.772*(Ubloc[k][z]-128));
                this.image[i][j][1]= (int) (YBlock[k][z]-0.344*(Ubloc[k][z]-128)-0.714*(VBloc[k][z]-128));
                *///this.image[i][j][0]=(int)VBlock[x4x4][y4x4];
                //this.image[i][j][1]=(int)VBlock[x4x4][y4x4];
                //this.image[i][j][2]=(int)VBlock[x4x4][y4x4];
                z++;
                if(image[i][j][0]<0)
                    image[i][j][0]=0;
                if(image[i][j][0]>255)
                    image[i][j][0]=255;
                if(image[i][j][1]<0)
                    image[i][j][1]=0;
                if(image[i][j][1]>255)
                    image[i][j][1]=255;
                if(image[i][j][2]<0)
                    image[i][j][2]=0;
                if(image[i][j][2]>255)
                    image[i][j][2]=255;
            }
            k++;
        }

    }
    public int [][][] getImage(){
        return this.image;
    }

    public void getGY(List<double [][]> YBlocks,int height,int width){
        int i=0;
        int check=0;
        int val=height*width/64;
       // System.out.println(val);
        while(i<val){
            double [][] G=new double[8][8];
            for(int x=0;x<8;x++){
                for(int y=0;y<8;y++){
                    double sum=0;
                    for(int k=0;k<8;k++){
                        for(int z=0;z<8;z++){

                            sum+=(YBlocks.get(i)[k][z]-128)*Math.cos((2*k+1)*x*Math.PI/16)*Math.cos((2*z+1)*y*Math.PI/16);
                        }}

                        if(x==0 && y==0)
                            G[x][y]=1.0/4.0*1.0/Math.sqrt(2)*1.0/Math.sqrt(2)*sum;

                        else
                            if(x>0 && y>0)
                                G[x][y]=1.0/4.0*1*1*sum;
                            else
                                G[x][y]=1.0/4.0*1*1.0/Math.sqrt(2)*sum;
                   // System.out.println(sum);
                   // System.out.println((1.0/4.0)*sum);
            }
        }this.GY.add(G);
            //if(check==0){
            //this.PrintBlock(G);check++;}
            //System.out.println(i);
             i++;
    }
}
    public void getGU(List<double [][]> UBlocks,int height,int width){
        int i=0;
        int val=height*width/64;
       while(i<val){
            double [][] G=new double[8][8];
           double [][] ublock=new double[8][8];
           //this.PrintBlock(this.getUandV(UBlocks.get(i),UBlocks.get(i)).get(0));
           ublock=this.getUandV(UBlocks.get(i),UBlocks.get(i)).get(0);
            for(int x=0;x<8;x++){
                for(int y=0;y<8;y++){
                    double sum=0;
                    for(int k=0;k<8;k++){
                        for(int z=0;z<8;z++){


                            sum+=(ublock[k][z]-128)*Math.cos((2*k+1)*x*Math.PI/16)*Math.cos((2*z+1)*y*Math.PI/16);
                        }}

                    if(x==0 && y==0)
                        G[x][y]=1.0/4.0*1.0/Math.sqrt(2)*1.0/Math.sqrt(2)*sum;

                    else
                    if(x>0 && y>0)
                        G[x][y]=1.0/4.0*1*1*sum;
                    else
                        G[x][y]=1.0/4.0*1*1.0/Math.sqrt(2)*sum;




                }
            }
           //System.out.println("Intra");
           this.GU.add(G);   i++;
        }
    }
    public void getGV(List<double [][]> VBlocks,int height,int width){
        int i=0;
        int val=height*width/64;
        while(i<val){
            double [][] G=new double[8][8];
            double [][] ublock=new double[8][8];
            ublock=this.getUandV(VBlocks.get(i),VBlocks.get(i)).get(0);
            for(int x=0;x<8;x++){
                for(int y=0;y<8;y++){
                    double sum=0;
                    for(int k=0;k<8;k++){
                        for(int z=0;z<8;z++){

                            sum+=(ublock[k][z]-128)*Math.cos((2*k+1)*x*Math.PI/16)*Math.cos((2*z+1)*y*Math.PI/16);
                        }}

                    if(x==0 && y==0)
                        G[x][y]=1.0/4.0*1.0/Math.sqrt(2)*1.0/Math.sqrt(2)*sum;

                    else
                    if(x>0 && y>0)
                        G[x][y]=1.0/4.0*1*1*sum;
                    else
                        G[x][y]=1.0/4.0*1*1.0/Math.sqrt(2)*sum;




                }
            }this.GV.add(G);      i++;
        }
    }

    public void Quantisize(){
        int check=0;
        //this.PrintBlock(this.GY.get(7499));
        for(int i=0;i<this.GU.size();i++){

            for(int x=0;x<8;x++)
                for(int y=0;y<8;y++){
                    //if(check==0) System.out.println(this.GU.get(i)[x][y]);
                    this.GY.get(i)[x][y]=(int)(this.GY.get(i)[x][y]/this.quant[x][y]);
                    this.GU.get(i)[x][y]=(int)(this.GU.get(i)[x][y]/this.quant[x][y]);
                    this.GV.get(i)[x][y]=(int)(this.GV.get(i)[x][y]/this.quant[x][y]);
                  // if (check==0){
                      //  System.out.println(this.GU.get(i)[x][y]);
                       // check++;
                   // }
                    }


        }
                    //this.PrintBlock(this.GY.get(7499));
    }
    public void Quantisize2(List<double[][]>Y,List<double[][]>U,List<double[][]>V){
        int check=0;
        //this.PrintBlock(this.GY.get(7499));

        for(int i=0;i<Y.size();i++){

            for(int x=0;x<8;x++)
                for(int y=0;y<8;y++){
                    Y.get(i)[x][y]=(int)(Y.get(i)[x][y]/this.quant[x][y]);
                    U.get(i)[x][y]=(int)(U.get(i)[x][y]/this.quant[x][y]);
                    V.get(i)[x][y]=(int)(V.get(i)[x][y]/this.quant[x][y]);
                    //if(check==0) System.out.println(this.GU.get(i)[x][y]);
                    //this.GU.get(i)[x][y]=(int)(this.GU.get(i)[x][y]/this.quant2[x][y]);
                    //this.GY.get(i)[x][y]=(int)(this.GY.get(i)[x][y]/this.quant2[x][y]);
                    //this.GV.get(i)[x][y]=(int)(this.GV.get(i)[x][y]/this.quant2[x][y]);
                    // if (check==0){
                    //  System.out.println(this.GU.get(i)[x][y]);
                    // check++;
                    // }
                }
            for(int x=0;x<8;x++)
                for(int y=0;y<8;y++){

                    this.GY.get(i)[x][y]=Y.get(i)[x][y];
                    this.GU.get(i)[x][y]=U.get(i)[x][y];
                    this.GV.get(i)[x][y]=V.get(i)[x][y];
                }
        }
        //this.PrintBlock(this.GY.get(7499));
    }
    public void PrintBlock(double [][] block){
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                System.out.println(block[i][j]);
    }

    public void deQuantification(){
        for(int i=0;i<this.GY.size();i++){
            for(int x=0;x<8;x++)
                for(int y=0;y<8;y++){
                    this.GU.get(i)[x][y]=this.GU.get(i)[x][y]*this.quant[x][y];
                    this.GY.get(i)[x][y]=this.GY.get(i)[x][y]*this.quant[x][y];
                    this.GV.get(i)[x][y]=this.GV.get(i)[x][y]*this.quant[x][y];
                }



        }
    }

    public void deQuantification2(){
        for(int i=0;i<this.GY.size();i++){
            for(int x=0;x<8;x++)
                for(int y=0;y<8;y++){
                    this.GU.get(i)[x][y]=this.GU.get(i)[x][y]*this.quant2[x][y];
                    this.GY.get(i)[x][y]=this.GY.get(i)[x][y]*this.quant2[x][y];
                    this.GV.get(i)[x][y]=this.GV.get(i)[x][y]*this.quant2[x][y];
                }
        }
    }


    public List<List<double[][]>> DeDCT(){
        int i=0;
       // int check=0;
       List<double[][]>Yblocks=new ArrayList<>();
       List<double[][]>Ublocks=new ArrayList<>();
       List<double[][]>Vblocks=new ArrayList<>();
     long start=System.currentTimeMillis();
     int val=height*width/64;
        double cv=1.0/4.0;
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
        double ccc=1.0/Math.sqrt(2);
     while(i<val){
            double [][] GY=new double[8][8];
            double [][] GU=new double[8][8];
            double [][] GV=new double[8][8];
         double [][] GYY=new double[8][8];
         double [][] GUU=new double[8][8];
         double [][] GVV=new double[8][8];
         GYY=this.GY.get(i);
         GUU=this.GU.get(i);
         GVV=this.GV.get(i);

            for(int x=0;x<8;x++){
                int g=x*8;
               // int XX=x>>1;
               // XX+=1;
                for(int y=0;y<8;y++){
                    int h=y*8;
                   // int YY=x>>1;
                   // YY+=1;
                    double sum=0;
                    double sumU=0;
                    double sumV=0;
                    for(int k=0;k<8;k++){
                        double dummy=cosinee[g+k];
                      //  double KK=k*Math.PI/16;
                        for(int z=0;z<8;z++){
                          //  double ZZ=z*Math.PI/16;
                            if(GYY[k][z]==0&GUU[k][z]==0&&GVV[k][z]==0)
                            {

                            }else{
                                double puppet=dummy*cosinee[h+z];
                                double ceva=0;

                            if(k==0 && z==0)
                                 ceva=cc;

                            else
                                if(k>0 && z>0)
                                   ceva=1;
                            else
                                ceva=ccc;
                            if(GYY[k][z]==0){
                               // sum+=0;
                            }else
                            sum+=ceva*(GYY[k][z])*puppet;
                            if(GUU[k][z]==0){
                                //sumU+=0;
                            }
                            else
                            sumU+=ceva*(GUU[k][z])*puppet;
                            if(GVV[k][z]==0){
                                //sumU+=0;
                            }
                            else
                            sumV+=ceva*(GVV[k][z])*puppet;

                        }}}

                        GY[x][y]=cv*sum+128;
                    GU[x][y]=cv*sumU+128;
                    GV[x][y]=cv*sumV+128;

                   //System.out.println((1.0/4.0)*sum);
                }}Yblocks.add(GY);
                Ublocks.add(GU);
                Vblocks.add(GV);

            //if(check==0){
            //this.PrintBlock(G);check++;}
            //System.out.println(i);
            i++;
        }
        long end=System.currentTimeMillis();
        //System.out.println("DCT");
        //System.out.println(start-end);
        List<List<double[][]>> blocks=new ArrayList<>();
        blocks.add(Yblocks);
        blocks.add(Ublocks);
        blocks.add(Vblocks);
        return blocks;

    }
    public List<List<Integer>> getBlockZigZag(double [][] block){
        List<List<Integer>> list1=new ArrayList<>();

        int zeros=0;
        int i=0;
        int j=0;
        int pos=-1;
        int check=0;
        // int j=0;
            while(j<8 && i<8){

                List<Integer> value1=new ArrayList<>();
                List<Integer> value2=new ArrayList<>();
                if(block[i][j]==0){
                    zeros++;
                }
                else {

                        int size=0;
                        if(block[i][j]>=1||block[i][j]<=-1){
                            size=1;
                        }
                        if(block[i][j]>=2||block[i][j]<=-2){
                            size=2;
                        }
                        if(block[i][j]>=4||block[i][j]<=-4){
                            size=3;
                        }
                        if(block[i][j]>=8||block[i][j]<=-8){
                            size=4;
                        }
                        if(block[i][j]>=16||block[i][j]<=-16){
                            size=5;
                        }
                        if(block[i][j]>=32||block[i][j]<=-32){
                            size=6;
                        }
                        if(block[i][j]>=64||block[i][j]<=-64){
                            size=7;
                        }
                        if(block[i][j]>=128||block[i][j]<=-128){
                            size=8;
                        }
                        if(block[i][j]>=256||block[i][j]<=-256){
                            size=9;
                        }
                        if(block[i][j]>=512||block[i][j]<=-512){
                            size=10;
                        }
                        if(i!=0 || j!=0){
                        ZigZag.add(zeros);
                            zeros=0;}
                        ZigZag.add(size);

                        ZigZag.add((int)(block[i][j]));

                        //list1.add(value1);
                        //list1.add(value2);

                    }
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        i+=pos;
                        j-=pos;
                    }else{
                    if(i==0 || i==7){
                        j++;
                        check=1;
                    }else{
                    if(j==0 || j==7 ){
                        i++;
                        check=1;
                    }else{
                        i+=pos;
                        j-=pos;
                    }
                    }
                    }




         List<Integer> zeross=new ArrayList<>();

    }
        if(zeros>0){
            ZigZag.add(0);
            ZigZag.add(0);
        }
            //list1.add(zeross);
       // System.out.println(list1.size());
        return list1;
    }

    public List<List<Integer>> getBlockZigZag2(double [][] block){
        List<List<Integer>> list1=new ArrayList<>();

        int zeros=0;
        int i=0;
        int j=0;
        int pos=-1;
        int check=0;
        // int j=0;
        while(j<4 && i<4){

                List<Integer> value1=new ArrayList<>();
                List<Integer> value2=new ArrayList<>();
                if(block[i][j]==0){

                    zeros++;
                    if(zeros==21){
                        System.out.println(i);
                        System.out.println(j);
                        System.out.println("AICIIII");
                    }
                }
                else {

                    int size=0;
                    if(block[i][j]>=1||block[i][j]<=-1){
                        size=1;
                    }
                    if(block[i][j]>=2||block[i][j]<=-2){
                        size=2;
                    }
                    if(block[i][j]>=4||block[i][j]<=-4){
                        size=3;
                    }
                    if(block[i][j]>=8||block[i][j]<=-8){
                        size=4;
                    }
                    if(block[i][j]>=16||block[i][j]<=-16){
                        size=5;
                    }
                    if(block[i][j]>=32||block[i][j]<=-32){
                        size=6;
                    }
                    if(block[i][j]>=64||block[i][j]<=-64){
                        size=7;
                    }
                    if(block[i][j]>=128||block[i][j]<=-128){
                        size=8;
                    }
                    if(block[i][j]>=256||block[i][j]<=-256){
                        size=9;
                    }
                    if(block[i][j]>=512||block[i][j]<=-512){
                        size=10;
                    }
                    if(i!=0 || j!=0){
                        ZigZag.add(zeros);
                        zeros=0;}
                    ZigZag.add(size);

                    ZigZag.add((int)(block[i][j]));

                    //list1.add(value1);
                    //list1.add(value2);

                }
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    i+=pos;
                    j-=pos;
                }else{
                    if(i==0 || i==3){
                        j++;
                        check=1;
                    }else{
                        if(j==0 || j==3 ){
                            i++;
                            check=1;
                        }else{
                            i+=pos;
                            j-=pos;
                        }
                    }
                }




                List<Integer> zeross=new ArrayList<>();

        }
        if(zeros>0){
            ZigZag.add(0);
            ZigZag.add(0);
        }return list1;}
    public void zigZag2(List<double[][]> Yblocks,List<double[][]> Ublocks,List<double[][]> Vblocks){
        for(int i=0;i<Yblocks.size();i++){
            List<double[][]> uandv=getUandV(Ublocks.get(i),Vblocks.get(i));
            zigZag.add(getBlockZigZag(Yblocks.get(i)));
            zigZag.add(getBlockZigZag2(Ublocks.get(i)));
            zigZag.add(getBlockZigZag2(Vblocks.get(i)));
        }
    }
    public void zigZag(){
       // PrintBlock(GY.get(0));
        for(int i=0;i<this.GY.size();i++){
            zigZag.add(getBlockZigZag(GY.get(i)));
            zigZag.add(getBlockZigZag(GU.get(i)));
            zigZag.add(getBlockZigZag(GV.get(i)));
        }
        //System.out.println("matrice");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                //System.out.println(GY.get(0)[i][j]);
            }
        }
        //System.out.println(zigZag.size());
        for(int j=0;j<zigZag.get(0).size();j++){
            //System.out.println(zigZag.get(0).get(j));
        }
    }

    public void processZigZag(){

    }

    public void decodeZigZag(){
        for(int i=0;i<zigZag.size();i+=3){
            int k=0;
            int z=0;
            int pos =-1;
            int check=0;
            for(int j=0;j<zigZag.get(i).size();j+=2){
                //if(j==zigZag.get(i).size())
                if(zigZag.get(i).get(j).size()==1){
                    this.GY.get(i/3)[k][z]=zigZag.get(i).get(j+1).get(0);
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){
                            z++;
                            check=1;
                        }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else {
                            k+=pos;
                            z-=pos;
                        }}}
                }
                else{
                    if(zigZag.get(i).get(j).size()==2&&zigZag.get(i).get(j).get(0)==0&&zigZag.get(i).get(j).get(1)!=0){
                        this.GY.get(i/3)[k][z]=zigZag.get(i).get(j+1).get(0);
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;
                                check=1;
                            }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}
                    }

                else{if(zigZag.get(i).get(j).get(0)!=0){
                    for(int aux=0;aux<zigZag.get(i).get(j).get(0);aux++){
                        this.GY.get(i/3)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;
                                check=1;
                            }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}
                    }
                    this.GY.get(i/3)[k][z]=zigZag.get(i).get(j+1).get(0);
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){
                            z++;

                            check=1;
                        }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }}}}
                        else{
                            while(k<8&&z<8){
                                this.GY.get(i/3)[k][z]=0;
                                if(check==1){
                                    pos=pos*(-1);
                                    check=0;
                                    k+=pos;
                                    z-=pos;
                                }else{
                                    if(k==0 || k==7){
                                        z++;
                                        check=1;
                                    }else{
                                        if(z==0 || z==7 ){

                                            k++;
                                            check=1;
                                        }else{
                                            k+=pos;
                                            z-=pos;
                                        }
                                    }
                                }
                            }
                    }

                }/* if(zigZag.get(i).get(j).get(1)==0){
                    while(k<8 && z<8){
                        System.out.println(k);
                        System.out.println(z);
                        this.GY.get(i/3)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;

                                check=1;
                            }else
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}
                    }
                    }
            */}
        }
            k=0;
            z=0;
            pos =-1;
            check=0;
            for(int j=0;j<zigZag.get(i+1).size();j+=2){
                if(zigZag.get(i+1).get(j).size()==1){
                    this.GU.get(i/3)[k][z]=zigZag.get(i+1).get(j+1).get(0);
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){
                            z++;
                            check=1;
                        }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;

                            z-=pos;
                        }}}
                }
                else{
                    if(zigZag.get(i+1).get(j).size()==2 && zigZag.get(i+1).get(j).get(0)==0&&zigZag.get(i+1).get(j).get(1)!=0){
                        this.GU.get(i/3)[k][z]=zigZag.get(i+1).get(j+1).get(0);
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;
                                check=1;
                            }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}
                    }

                    else{if(zigZag.get(i+1).get(j).get(0)!=0){
                        for(int aux=0;aux<zigZag.get(i+1).get(j).get(0);aux++){
                            this.GU.get(i/3)[k][z]=0;
                            if(check==1){
                                pos=pos*(-1);
                                check=0;
                                k+=pos;
                                z-=pos;
                            }else{
                                if(k==0 || k==7){
                                    z++;
                                    check=1;
                                }else{
                                if(z==0 || z==7 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }}}
                        }
                        this.GU.get(i/3)[k][z]=zigZag.get(i+1).get(j+1).get(0);
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;
                                check=1;
                            }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}}        else{
                        while(k<8&&z<8){
                            this.GU.get(i/3)[k][z]=0;
                            if(check==1){
                                pos=pos*(-1);
                                check=0;
                                k+=pos;
                                z-=pos;
                            }else{
                                if(k==0 || k==7){
                                    z++;
                                    check=1;
                                }else{
                                    if(z==0 || z==7 ){
                                        k++;
                                        check=1;
                                    }else{
                                        k+=pos;
                                        z-=pos;
                                    }
                                }
                            }
                        }
                    }
                    }/*if(zigZag.get(i+1).get(j).get(1)==0){
                        while(k<8&&z<8){
                            this.GU.get(i/3)[k][z]=0;
                            if(check==1){
                                pos=pos*(-1);
                                check=0;
                                k+=pos;
                                z-=pos;
                            }else{
                                if(k==0 || k==7){
                                    z++;

                                    check=1;
                                }else
                                if(z==0 || z==7 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }}
                        }
                    }
                */}
            }



            k=0;
            z=0;
            pos =-1;
            check=0;
            for(int j=0;j<zigZag.get(i+2).size();j+=2){
                if(zigZag.get(i+2).get(j).size()==1){
                    this.GV.get(i/3)[k][z]=zigZag.get(i+2).get(j+1).get(0);
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){
                            z++;
                            check=1;
                        }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }}}
                }
                else{
                    if(zigZag.get(i+2).get(j).size()==2&& zigZag.get(i+2).get(j).get(0)==0&&zigZag.get(i+2).get(j).get(1)!=0){
                        this.GV.get(i/3)[k][z]=zigZag.get(i+2).get(j+1).get(0);
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){

                                z++;
                                check=1;
                            }else{
                            if(z==0 || z==7 ){
                                k++;

                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}
                    }

                    else{if(zigZag.get(i+2).get(j).get(0)!=0){
                        for(int aux=0;aux<zigZag.get(i+2).get(j).get(0);aux++){
                            this.GV.get(i/3)[k][z]=0;
                            if(check==1){
                                pos=pos*(-1);
                                check=0;
                                k+=pos;
                                z-=pos;
                            }else{
                                if(k==0 || k==7){
                                    z++;
                                    check=1;
                                }else{
                                if(z==0 || z==7 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }}}
                        }
                        this.GV.get(i/3)[k][z]=zigZag.get(i+2).get(j+1).get(0);
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;
                                check=1;
                            }else{
                            if(z==0 || z==7 ){
                                k++;

                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}}        else{
                        while(k<8&&z<8){
                            this.GV.get(i/3)[k][z]=0;
                            if(check==1){
                                pos=pos*(-1);
                                check=0;
                                k+=pos;
                                z-=pos;
                            }else{
                                if(k==0 || k==7){

                                    z++;
                                    check=1;
                                }else{
                                    if(z==0 || z==7 ){
                                        k++;
                                        check=1;
                                    }else{
                                        k+=pos;
                                        z-=pos;
                                    }
                                }
                            }
                        }
                    }
                    }
                }/*if(zigZag.get(i+2).get(j).get(1)==0){
                    while(k<8&&z<8){
                        this.GV.get(i/3)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==7){
                                z++;

                                check=1;
                            }else
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}
                    }
                }
*/            }

        }
}
public void decodeZZ(){

    int block=0;
    //System.out.println(ZigZag);
    int j=0;
    int zk=0;
    for(int i=0;i<ZigZag.size() && this.ZigZag.size()>i+j+1;i+=j){
        zk++;
        j=0;
        int k=0;
        int z=0;
        int pos =-1;
        int check=0;
        if(k==0 && z==0 &&   this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
        {
            j--;
        }
        while(k<8&&z<8){


            if(k==0 && z==0 && this.ZigZag.get(i+j)!=0&&this.ZigZag.get(i+j+1)!=0){

                this.GY.get(block)[k][z]=ZigZag.get(i+j+1);
                //System.out.println("Intra in primu");
                //System.out.println(ZigZag.get(i+j));
                //System.out.println(ZigZag.get(i+j+1));
                j++;
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                //System.out.println("Intra in al doilea");
                int aux=0;
                while(aux<ZigZag.get(i+j+1)){
                    this.GY.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }
                //System.out.println("Oare");

                this.GY.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }}}
            }else if(i+j+3<ZigZag.size()&&this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0) {
                //System.out.println("Intra in al treilea");
                this.GY.get(block)[k][z] = this.ZigZag.get(i + j + 3);
                j += 3;
                if (check == 1) {
                    pos = pos * (-1);
                    check = 0;
                    k += pos;
                    z -= pos;
                } else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }
                }
            }
            else{/*
                    System.out.println("IN PLM");
                    System.out.println(ZigZag.get(i+j+1));
                    System.out.println(ZigZag.get(i+j+2));
                    System.out.println(ZigZag.get(i+j+3));
                   */ j+=3;while(k<8&&z<8){
                this.GY.get(block)[k][z]=0;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}
            }
            }
        }
        k=0;
        z=0;
        pos =-1;
        check=0;
        if(k==0 && z==0 &&   this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
        {
            j--;
        }
        while(k<8 && z<8&&this.ZigZag.size()>i+j+1){


            if(k==0 && z==0 && this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)!=0 && this.ZigZag.get(i+j+1)!=0){
                this.GU.get(block)[k][z]=ZigZag.get(i+j+1);
                j++;
                //System.out.println("U");

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(i+j+3<ZigZag.size()&&ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                //System.out.println(ZigZag.get(i+j+1));
                int aux=0;
                //System.out.println("U1");
                while(aux<ZigZag.get(i+j+1) ){
                    //System.out.println("!!!!");
                    this.GU.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }
                //System.out.println("????");
                this.GU.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }
                }

            }else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0)
            {this.GU.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;
                //System.out.println("U2");
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }
                }}
            else{
                // System.out.println(ZigZag.get(i+j+1));
                //System.out.println(ZigZag.get(i+j+2));
                j+=3;
                while(k<8 && z<8){
                    this.GU.get(block)[k][z]=0;
                    //System.out.println("U3");
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }}
                }
            }}

        k=0;
        z=0;
        pos =-1;
        check=0;
        if(k==0 && z==0 &&  this.ZigZag.size()>i+j+1&& this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
        {
            j--;
        }
        while(k<8 && z<8  && this.ZigZag.size()>i+j+1){


            if(k==0 && z==0 && ZigZag.get(i+j)!=0 && ZigZag.get(i+j+1)!=0){
                // System.out.println(ZigZag.get(i+j));
                this.GV.get(block)[k][z]=ZigZag.get(i+j+1);
                j++;
                //System.out.println("V");
                //System.out.println(ZigZag.get(i+j));
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(i+j+3<ZigZag.size()&&ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                //  System.out.println(ZigZag.get(i+j+1));
                int aux=0;
                //System.out.println("V1");
                while(aux<ZigZag.get(i+j+1)){
                    //System.out.println("!!!!");
                    this.GV.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }

                this.GV.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }}
            }else {

                if(this.ZigZag.get(i+j+1)==0 &&  this.ZigZag.get(i+j+2)!=0&&this.ZigZag.get(i+j+3)!=0 )
            {
                //System.out.println("V2");
                this.GV.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }}
            }
            else
            {j+=3;
                //  System.out.println("V3");
                while(k<8&&z<8){
                    this.GV.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}
                    }
                }
            }
        }
    }}
    }

    public void decodeZZ2(){

        int block=0;
        //System.out.println(ZigZag);
        int j=0;
        int zk=0;
        for(int i=0;i<ZigZag.size() && this.ZigZag.size()>i+j+1;i+=j){
            zk++;
            j=0;
            int k=0;
            int z=0;
            int pos =-1;
            int check=0;
            if(k==0 && z==0 &&   this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
            {
                j--;
            }
            while(k<8&&z<8){


                if(k==0 && z==0 && this.ZigZag.get(i+j)!=0&&this.ZigZag.get(i+j+1)!=0){

                    this.GY.get(block)[k][z]=ZigZag.get(i+j+1);
                    //System.out.println("Intra in primu");
                    //System.out.println(ZigZag.get(i+j));
                    //System.out.println(ZigZag.get(i+j+1));
                    j++;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }}}
                else if(ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                    //System.out.println("Intra in al doilea");
                    int aux=0;
                    while(aux<ZigZag.get(i+j+1)){
                        this.GY.get(block)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==3){

                                z++;
                                check=1;
                            }else{
                                if(z==0 || z==3 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }
                            }
                        }
                        aux++;

                    }
                    //System.out.println("Oare");

                    this.GY.get(block)[k][z]=this.ZigZag.get(i+j+3);
                    j+=3;

                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}}
                }else if(i+j+3<ZigZag.size()&&this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0) {
                    //System.out.println("Intra in al treilea");
                    this.GY.get(block)[k][z] = this.ZigZag.get(i + j + 3);
                    j += 3;
                    if (check == 1) {
                        pos = pos * (-1);
                        check = 0;
                        k += pos;
                        z -= pos;
                    } else {
                        if (k == 0 || k == 3) {

                            z++;
                            check = 1;
                        } else {
                            if (z == 0 || z == 3) {
                                k++;
                                check = 1;
                            } else {
                                k += pos;
                                z -= pos;
                            }
                        }
                    }
                }
                else if (ZigZag.get(i+j+1)==0 && ZigZag.get(i+j+2)==0){/*
                    System.out.println("IN PLM");
                    System.out.println(ZigZag.get(i+j+1));
                    System.out.println(ZigZag.get(i+j+2));
                    System.out.println(ZigZag.get(i+j+3));
                   */ j+=3;while(k<8&&z<8){
                    this.GY.get(block)[k][z]=0;

                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }}
                }
                }
            }
            k=0;
            z=0;
            pos =-1;
            check=0;
            System.out.println(ZigZag.get(i+j-3));
            System.out.println(ZigZag.get(i+j-2));
            System.out.println(ZigZag.get(i+j-1));
            System.out.println(ZigZag.get(i+j));
            System.out.println(ZigZag.get(i+j+1));
            System.out.println(ZigZag.get(i+j+2));
            if(k==0 && z==0 &&   this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
            {
                j--;
            }
            while(k<8 && z<8&&this.ZigZag.size()>i+j+1){


                if(k==0 && z==0 && this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)!=0 && this.ZigZag.get(i+j+1)!=0){
                    this.GU.get(block)[k][z]=ZigZag.get(i+j+1);
                    j++;
                    //System.out.println("U");

                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }}}
                else if(i+j+3<ZigZag.size()&&ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                    //System.out.println(ZigZag.get(i+j+1));
                    int aux=0;
                    //System.out.println("U1");
                    while(aux<ZigZag.get(i+j+1) ){
                        //System.out.println("!!!!");
                        this.GU.get(block)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==3){

                                z++;
                                check=1;
                            }else{
                                if(z==0 || z==3 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }
                            }
                        }
                        aux++;

                    }
                    //System.out.println("????");
                    this.GU.get(block)[k][z]=this.ZigZag.get(i+j+3);
                    j+=3;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else {
                        if (k == 0 || k == 3) {

                            z++;
                            check = 1;
                        } else {
                            if (z == 0 || z == 3) {
                                k++;
                                check = 1;
                            } else {
                                k += pos;
                                z -= pos;
                            }
                        }
                    }

                }else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0)
                {/*
                    System.out.println(k);
                    System.out.println(z);
                    System.out.println(ZigZag.get(i+j+1));
                    System.out.println(ZigZag.get(i+j+2));
                    System.out.println(ZigZag.get(i+j+3));
                   */
                    this.GU.get(block)[k][z]=this.ZigZag.get(i+j+3);
                    j+=3;
                    //System.out.println("U2");
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else {
                        if (k == 0 || k == 3) {

                            z++;
                            check = 1;
                        } else {
                            if (z == 0 || z == 3) {
                                k++;
                                check = 1;
                            } else {
                                k += pos;
                                z -= pos;
                            }
                        }
                    }}
                else{
                    // System.out.println(ZigZag.get(i+j+1));
                    //System.out.println(ZigZag.get(i+j+2));
                    j+=3;
                    while(k<8 && z<8){
                        this.GU.get(block)[k][z]=0;
                        //System.out.println("U3");
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==3){

                                z++;
                                check=1;
                            }else{
                                if(z==0 || z==3 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }
                            }}
                    }
                }}

            k=0;
            z=0;
            pos =-1;
            check=0;
            if(k==0 && z==0 &&  this.ZigZag.size()>i+j+1&& this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
            {
                j--;
            }
            while(k<8 && z<8  && this.ZigZag.size()>i+j+1){


                if(k==0 && z==0 && ZigZag.get(i+j)!=0 && ZigZag.get(i+j+1)!=0){
                    // System.out.println(ZigZag.get(i+j));
                    this.GV.get(block)[k][z]=ZigZag.get(i+j+1);
                    j++;
                    //System.out.println("V");
                    //System.out.println(ZigZag.get(i+j));
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }}}
                else if(i+j+3<ZigZag.size()&&ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                    //  System.out.println(ZigZag.get(i+j+1));
                    int aux=0;
                    //System.out.println("V1");
                    while(aux<ZigZag.get(i+j+1)){
                        //System.out.println("!!!!");
                        this.GV.get(block)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==3){

                                z++;
                                check=1;
                            }else{
                                if(z==0 || z==3 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }
                            }
                        }
                        aux++;

                    }

                    this.GV.get(block)[k][z]=this.ZigZag.get(i+j+3);
                    j+=3;

                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else {
                        if (k == 0 || k == 3) {

                            z++;
                            check = 1;
                        } else {
                            if (z == 0 || z == 3) {
                                k++;
                                check = 1;
                            } else {
                                k += pos;
                                z -= pos;
                            }
                        }}
                }else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0)
                {
                    //System.out.println("V2");
                    this.GV.get(block)[k][z]=this.ZigZag.get(i+j+3);
                    j+=3;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else {
                        if (k == 0 || k == 3) {

                            z++;
                            check = 1;
                        } else {
                            if (z == 0 || z == 3) {
                                k++;
                                check = 1;
                            } else {
                                k += pos;
                                z -= pos;
                            }
                        }}
                }
                else
                {j+=3;
                    //  System.out.println("V3");
                    while(k<8&&z<8){
                        this.GV.get(block)[k][z]=0;
                        if(check==1){
                            pos=pos*(-1);
                            check=0;
                            k+=pos;
                            z-=pos;
                        }else{
                            if(k==0 || k==3){

                                z++;
                                check=1;
                            }else{
                                if(z==0 || z==3 ){
                                    k++;
                                    check=1;
                                }else{
                                    k+=pos;
                                    z-=pos;
                                }}
                        }
                    }
                }
            }
        }
    }
public void DecodeZZ2()   { int block=0;
    //System.out.println(ZigZag);
    int j=0;
    int zk=0;
    for(int i=0;i<ZigZag.size() && this.ZigZag.size()>i+j+1;i+=j){
        zk++;
        j=0;
        int k=0;
        int z=0;
        int pos =-1;
        int check=0;
        if(k==0 && z==0 &&   this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
        {
            j--;
        }
        while(k<8&&z<8){
/*
            System.out.println(k);
            System.out.println(z);
            System.out.println("STOP");
            System.out.println(ZigZag.get(i+j+1));
            System.out.println(ZigZag.get(i+j+2));
  */
            if(k==0 && z==0 && this.ZigZag.get(i+j)!=0&&this.ZigZag.get(i+j+1)!=0){

                this.GY.get(block)[k][z]=ZigZag.get(i+j+1);
                //System.out.println("Intra in primu");
                //System.out.println(ZigZag.get(i+j));
                //System.out.println(ZigZag.get(i+j+1));
                j++;
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                //System.out.println("Intra in al doilea");
                int aux=0;

                while(aux<ZigZag.get(i+j+1)){

                    this.GY.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==7){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==7 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }
                //System.out.println("Oare");

                this.GY.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }}}
            }else if(i+j+3<ZigZag.size()&&this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0) {
                //System.out.println("Intra in al treilea");
                this.GY.get(block)[k][z] = this.ZigZag.get(i + j + 3);
                j += 3;
                if (check == 1) {
                    pos = pos * (-1);
                    check = 0;
                    k += pos;
                    z -= pos;
                } else {
                    if (k == 0 || k == 7) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 7) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }
                }
            }
            else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+2)==0){/*
                    System.out.println("IN PLM");
                    System.out.println(ZigZag.get(i+j+1));
                    System.out.println(ZigZag.get(i+j+2));
                    System.out.println(ZigZag.get(i+j+3));
                   */ j+=3;while(k<8&&z<8){
                this.GY.get(block)[k][z]=0;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==7){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==7 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}
            }
            }
        }
        k=0;
        z=0;
        pos =-1;
        check=0;
        if(k==0 && z==0 &&   this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
        {
            j--;
        }
        while(k<4 && z<4&&this.ZigZag.size()>i+j+1){


            if(k==0 && z==0 && this.ZigZag.size()>i+j+1&&this.ZigZag.get(i+j)!=0 && this.ZigZag.get(i+j+1)!=0){
                this.GU.get(block)[k][z]=ZigZag.get(i+j+1);
                j++;
                //System.out.println("U");

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==3){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==3 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(i+j+3<ZigZag.size()&&ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                //System.out.println(ZigZag.get(i+j+1));
                int aux=0;
                //System.out.println("U1");
                while(aux<ZigZag.get(i+j+1) ){
                    //System.out.println("!!!!");
                    /*
                    System.out.println(ZigZag.get(i+j+1));
                    System.out.println(ZigZag.get(i+j+2));
                    System.out.println(ZigZag.get(i+j+3));
                    System.out.println("AUX");
                    System.out.println(aux);
                    */
                    this.GU.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }
                //System.out.println("????");
                this.GU.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 3) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 3) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }
                }

            }else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0)
            {this.GU.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;
                //System.out.println("U2");
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 3) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 3) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }
                }}
            else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+2)==0){
                // System.out.println(ZigZag.get(i+j+1));
                //System.out.println(ZigZag.get(i+j+2));
                j+=3;
                //System.out.println("INTRAAAAAAAAAAAAAAAAAAAAAA");
                while(k<4 && z<4){
                    this.GU.get(block)[k][z]=0;
                    //System.out.println("U3");
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }}
                }
            }}

        k=0;
        z=0;
        pos =-1;
        check=0;
        if(k==0 && z==0 &&  this.ZigZag.size()>i+j+1&& this.ZigZag.get(i+j)==0&&this.ZigZag.get(i+j+1)==0)
        {
            j--;
        }
        while(k<4 && z<4  && this.ZigZag.size()>i+j+1){


            if(k==0 && z==0 && ZigZag.get(i+j)!=0 && ZigZag.get(i+j+1)!=0){
                // System.out.println(ZigZag.get(i+j));
                this.GV.get(block)[k][z]=ZigZag.get(i+j+1);
                j++;
                //System.out.println("V");
                //System.out.println(ZigZag.get(i+j));
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else{
                    if(k==0 || k==3){

                        z++;
                        check=1;
                    }else{
                        if(z==0 || z==3 ){
                            k++;
                            check=1;
                        }else{
                            k+=pos;
                            z-=pos;
                        }
                    }}}
            else if(i+j+3<ZigZag.size()&&ZigZag.get(i+j+1)>0&&ZigZag.get(i+j+2)>0&&ZigZag.get(i+j+3)!=0){
                //  System.out.println(ZigZag.get(i+j+1));
                int aux=0;
                //System.out.println("V1");
                while(aux<ZigZag.get(i+j+1)){
                    //System.out.println("!!!!");
                    this.GV.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }
                        }
                    }
                    aux++;

                }

                this.GV.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;

                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 3) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 3) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }}
            }else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+3)!=0 && this.ZigZag.get(i+j+2)!=0)
            {
                //System.out.println("V2");
                this.GV.get(block)[k][z]=this.ZigZag.get(i+j+3);
                j+=3;
                if(check==1){
                    pos=pos*(-1);
                    check=0;
                    k+=pos;
                    z-=pos;
                }else {
                    if (k == 0 || k == 3) {

                        z++;
                        check = 1;
                    } else {
                        if (z == 0 || z == 3) {
                            k++;
                            check = 1;
                        } else {
                            k += pos;
                            z -= pos;
                        }
                    }}
            }
            else if(this.ZigZag.get(i+j+1)==0 && this.ZigZag.get(i+j+2)==0)
            {j+=3;
                //  System.out.println("V3");
                while(k<4&&z<4){
                    this.GV.get(block)[k][z]=0;
                    if(check==1){
                        pos=pos*(-1);
                        check=0;
                        k+=pos;
                        z-=pos;
                    }else{
                        if(k==0 || k==3){

                            z++;
                            check=1;
                        }else{
                            if(z==0 || z==3 ){
                                k++;
                                check=1;
                            }else{
                                k+=pos;
                                z-=pos;
                            }}
                    }
                }
            }
        }
    }}
    @Override
    public void run() {
        this.DeDCT();
    }
}

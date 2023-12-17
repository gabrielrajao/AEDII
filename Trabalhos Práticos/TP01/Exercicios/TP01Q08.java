import java.io.*;

class TP01Q07{

    public static void main (String[] args){
        int n = 0;
        double numero = 0;
        int num = 0;
        n = MyIO.readInt();
        try{
            RandomAccessFile write = new RandomAccessFile("./arq.txt", "rw");
            for (int i = 0; i < n; i++){
                numero = MyIO.readDouble();
                write.writeDouble(numero);
            }
            write.close();
            RandomAccessFile raf = new RandomAccessFile("./arq.txt", "r");
            for(int i = n - 1; i >= 0; i--){
                int index = i * 8;
                raf.seek(index);
                numero = raf.readDouble();
                num = (int) numero;
                if((double) num == numero){
                        MyIO.println(num);
                }
                else{
                    MyIO.println(numero);
                }
            }
            raf.close();
        }catch(IOException e){
            //nada
        }
    }
}

import java.io.*;
import java.net.*;
import java.nio.charset.*;
class TP01Q07{


    //FILL VECTOR

    public static void fill (int[] vector){
        for(int i = 0 ; i < vector.length; i++){
            vector[i] = 0;
        }
    }

    //GET HTML

    public static String getHtml(String endereco,String nome){
      URI uri;
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;
      int [] x = new int[25];
      fill(x);
      int[][] p = new int[][]{{97,97},{101,101},{105,105},{111,111},{117,117},{160,225},{130,233},{161,237},{162,243},{163,250},{133,224},{138,232},{141,236},{149,242},{151,249},{198,227},{228,245},{131,226},{136,234},{140,238},{147,244},{150,251}};
      try {
         uri = new URI (endereco);
         url = uri.toURL();
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8")));

         while ((line = br.readLine()) != null) {
            for(int i = 0; i < line.length() ; i++){
                char index = line.charAt(i);
                int ind = (int) line.charAt(i);
                if(index == '<'){
                    if(i+3<line.length() &&line.charAt(i+1)=='b' && line.charAt(i+2)=='r' && line.charAt(i+3)=='>'){ x[23]++; i+=3;} else{
                        if(i+6<line.length() && line.charAt(i+1)=='t' && line.charAt(i+2)=='a' && line.charAt(i+3)=='b'&& line.charAt(i+4)=='l'&& line.charAt(i+5)=='e'&& line.charAt(i+6)=='>' ){ x[24]++; i+=6;}


                    }

                } else{
                    boolean conso = true;
                    for(int ha = 0; ha < p.length; ha++){
                        if(ind == p[ha][1] ){
                            x[ha]++;
                            ha = p.length;
                            conso = false;
                        }
                    }
                    if(conso){
                        if((index >= 'a' && index <= 'z')){
                            if(index!='a' && index!='e' &&index!='i' &&index!='o' &&index!='u' &&index!='A' &&index!='E' &&index!='I' &&  index!='O' &&index!='U' ){ x[22]++; }
                        }
                    }
                }
            }
         }
      } catch (MalformedURLException mue) {
      } catch (IOException ioe) {
      } catch (URISyntaxException use){
      } catch (IllegalArgumentException iae){
      }

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      } catch (NullPointerException npe){
      }
      for(int la = 0; la < p.length; la++){
        resp+=""+ (char) p[la][1] + "(" + x[la]+") ";
      }
      resp += "consoante("+x[22]+") <br>("+x[23]+") <table>("+x[24]+") " + nome;
      return resp;
   }

    //IS FIM

    public static boolean isFIM (String word){
        String end = "FIM";
        boolean result = true;
        int len = end.length();
        if(word.length() == len){
            for(int z = 0; z < len; z++){
                if(end.charAt(z) != word.charAt(z)){
                    result = false;
                    z = len;
                }
            }
        } else{
            result = false;
        }
        return result;
    }

    //MAIN
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        boolean t = true;
        String nome = "";
        String endereco = "";
        while(t){
                nome = "";
                endereco = "";
                nome = MyIO.readLine();
                if(isFIM(nome)){
                    break;
                }
                endereco = MyIO.readString();
                MyIO.println(getHtml(endereco,nome));
        }

    }
}

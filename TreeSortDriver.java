import java.util.ArrayList;

public class TreeSortDriver
{

   private static CD[] readMusic(String fileName)
   {
      //DO THIS complete this method using the FileIO class
      FileIO file = new FileIO(fileName, 1);
      String str = file.readLine();
      ArrayList<CD> cds = new ArrayList<CD>();

      while (!file.EOF())
      {
         String title = file.readLine();
         int year = Integer.parseInt(file.readLine());
         int rating = Integer.parseInt(file.readLine());
         int numTracks = Integer.parseInt(file.readLine());
         CD cd = new CD(title, str, year, rating, numTracks);

         cds.add(cd);
         int tracks = 1;

         while (tracks <= numTracks)
         {
            String track_str = file.readLine();
            String[] pieces = track_str.split(",");    //divide the string up into two pieces
            String len = pieces[0];
            String songTitle = pieces[1];
            cd.addSong(songTitle, len);
            tracks++;
         }

         str = file.readLine();
      }

      file.close();
      //create a CD[] of the correct size, populate it using a for-each statement
      CD[] cds_array = new CD()[cds.size()];
      int i = 0;
      for(CD cd : cds){
        cds_array[i] = cd;
        i++;
      }

      return cds_array;
   }

   public static void main (String[] args)
   {
      //use the Keyboard class, try-catch, and a while loop to continue calling readMusic
      //until a valid file name is entered
      //as checked exceptions have been converted to unchecked exceptions,
      //you must remember to do this with end user input, the compiler will not help you
      CD[] cds;
      Keyboard kb = new Keyboard();
      String fileName;

      boolean c = true;
      while(c){
        try{
          // read in filename
          fileName = kb.readString();
          cds = readMusic(fileName);
          c = false;
        }catch(Exception e){
          // Dont crash
        }
      }
      //once you have the array of CDs back from readMusic, sort them
      TreeSort sorter = new TreeSort();
      cds = (CD[])sorter.treeSort((KeyedItem[])cds);
      //and print them out to make sure that they are sorted
      for(int i = 0; i < cds.length; i++){
        System.out.println(cds[i].toString());
      }
   }
}

import datastore.FileResources;

/**
 *
 */
public class Main {
    public static void main(String ...args) {
        //File file = GetFromResources.file("testText.txt");
        //File file = new File("sdf");

        System.out.println(FileResources.getFileName("testText.txt"));
    }
}

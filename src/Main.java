import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress gameProgress1 = new GameProgress(50, 34, 10, 332.5);
        GameProgress gameProgress2 = new GameProgress(60, 24, 14, 352.5);
        GameProgress gameProgress3 = new GameProgress(70, 64, 12, 362.5);

        gameProgress1.gameSave("/Users/ivanporsak/IdeaProjects/Game/savegames/gameProgress1.dat");
        gameProgress2.gameSave("/Users/ivanporsak/IdeaProjects/Game/savegames/gameProgress2.dat");
        gameProgress3.gameSave("/Users/ivanporsak/IdeaProjects/Game/savegames/gameProgress3.dat");

        String saveDir = "/Users/ivanporsak/IdeaProjects/Game/savegames/";
        String zipFile = "zip.zip";
        List<String> stringList = new ArrayList<>();
        stringList.add("/Users/ivanporsak/IdeaProjects/Game/savegames/gameProgress1.dat");
        stringList.add("/Users/ivanporsak/IdeaProjects/Game/savegames/gameProgress2.dat");
        stringList.add("/Users/ivanporsak/IdeaProjects/Game/savegames/gameProgress3.dat");

        zipFiles(saveDir + zipFile, stringList);
        deleteFile(stringList);

    }

    public static void zipFiles (String fileZip, List<String> listFiles) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileZip);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : listFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[fis.available()];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

    public static void deleteFile(List<String> namesFile){
        for (String nameFile : namesFile) {
            if (nameFile != null){
            File file = new File(nameFile);
            file.delete();
            }
        }
    }
}



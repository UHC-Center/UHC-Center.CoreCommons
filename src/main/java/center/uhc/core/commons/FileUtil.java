package center.uhc.core.commons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {

    public static void deleteFolder(String location) {
        File file = new File(location);
        if (!file.exists())
            return;

        if (file.listFiles() != null) {
            for (File subFile : file.listFiles()) {
                if(subFile.isDirectory()) {
                    deleteFolder(subFile);
                } else {
                    subFile.delete();
                }
            }
        }
        file.delete();
    }

    public static void deleteFolder(File file) {
        if (!file.exists())
            return;

        if (file.listFiles() != null) {
            for (File subFile : file.listFiles()) {
                if(subFile.isDirectory()) {
                    deleteFolder(subFile);
                } else {
                    subFile.delete();
                }
            }
        }
        file.delete();
    }

    public static void deleteFile(File file) {
        if (!file.exists())
            return;

        file.delete();
    }

    public static void createFolder(String location) {
        File file = new File(location);

        file.mkdir();
    }

    public static void copyFiles(String initialLocation, String finalLocation) throws IOException {
        File i = new File(initialLocation);
        File f = new File(finalLocation);

        FileUtils.copyDirectory(i, f);
    }

    public static void copyFile(File initial, File location) throws IOException {
        FileUtils.copyFileToDirectory(initial, location);
    }
}

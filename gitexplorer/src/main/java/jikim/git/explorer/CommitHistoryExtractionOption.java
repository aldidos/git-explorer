package jikim.git.explorer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class CommitHistoryExtractionOption 
{
    public static final String OPTION_INPUT_PATH_TYPE_TARGET = "path-type-target";
    public static final String OPTION_INPUT_PATH_TYPE_DIR = "path-type-dir";

    String inputPathType;
    String inputPath;
    String outputDirPath;
    
    public static CommitHistoryExtractionOption read(String jsonPath) throws IOException
    {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath) );
        return gson.fromJson( reader, CommitHistoryExtractionOption.class );
    }
}

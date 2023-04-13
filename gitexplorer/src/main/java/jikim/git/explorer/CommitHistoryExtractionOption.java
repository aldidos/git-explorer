package jikim.git.explorer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class CommitHistoryExtractionOption 
{
    String baseRepositoryDirPath;
    String outputDirPath;
    
    public static CommitHistoryExtractionOption read(String jsonPath) throws IOException
    {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(jsonPath) );
        return gson.fromJson( reader, CommitHistoryExtractionOption.class );
    }
}

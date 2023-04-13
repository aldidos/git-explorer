package jikim.git.explorer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class AppInputOption 
{
    public static final String RUN_COMMAND_EXTRACT_COMMIT_HISTORY = "commit-history-extraction";

    String runCommand;

    String commitHistoryExtractionInputJSONPath;
    
    public static AppInputOption read(String jsonPath) throws IOException
    {
        Reader reader = Files.newBufferedReader( Paths.get(jsonPath) );
        Gson gson = new Gson();
        return gson.fromJson( reader, AppInputOption.class);
    }
}

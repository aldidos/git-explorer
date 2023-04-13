package jikim.git.explorer;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

import jikim.git.explorer.log.Log;

public class AppController 
{
    AppInputOption appInputOption;

    public AppController( AppInputOption appInputOption )
    {
        this.appInputOption = appInputOption;
    }

    public void run() throws IOException, NoHeadException, GitAPIException
    {
        switch ( appInputOption.runCommand )
        {
            case AppInputOption.RUN_COMMAND_EXTRACT_COMMIT_HISTORY : 

                Log.log("run commit history extraction.");

                CommitHistoryExtractionOption option = CommitHistoryExtractionOption.read( appInputOption.commitHistoryExtractionInputJSONPath );
                CommitHistoryExtraction chExtraction = new CommitHistoryExtraction(option);
                chExtraction.run();
                break;
        }
    }
    
}

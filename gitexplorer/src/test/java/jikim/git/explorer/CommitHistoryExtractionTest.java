package jikim.git.explorer;

import java.io.IOException;
import java.nio.file.Paths;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.junit.Before;
import org.junit.Test;

import jikim.git.explorer.utils.JSONWriteStream;

public class CommitHistoryExtractionTest 
{    
    CommitHistoryExtraction cheExtraction ; 
    CommitHistoryExtractionOption cheOption;

    @Before
    public void createCommitHistoryExtraction() throws IOException
    {        
        cheOption = new CommitHistoryExtractionOption();
        cheOption.inputPathType = CommitHistoryExtractionOption.OPTION_INPUT_PATH_TYPE_TARGET;
        cheOption.inputPath = "e:/gh_repos/dgl";
        cheOption.outputDirPath = "./data/commit-history/";        
        cheExtraction = new CommitHistoryExtraction( cheOption );
    }

    @Test
   public void testRun() throws NoHeadException, IOException, GitAPIException
    {        
        cheExtraction.run();
    }

    @Test
    public void testExtractCommitHistory() throws NoHeadException, IOException, GitAPIException
    {
        String repositoryPath = "e:/gh_repos/dgl/";
        String jsonPath = "./test_ext_ch.json";
        JSONWriteStream jsonWriteStream = new JSONWriteStream(jsonPath);
        cheExtraction.extractCommitHistory( Paths.get(repositoryPath).toFile(), jsonWriteStream );
        jsonWriteStream.close();
    }
}

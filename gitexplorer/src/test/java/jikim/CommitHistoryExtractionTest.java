package jikim;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.junit.Before;
import org.junit.Test;

import jikim.git.explorer.CommitHistoryExtraction;
import jikim.git.explorer.CommitHistoryExtractionOption;
import jikim.git.explorer.data.CommitHistory;

public class CommitHistoryExtractionTest 
{
    CommitHistoryExtraction chExtraction ; 

    @Before
    public void createCommitHistoryExtraction()
    {
        chExtraction = new CommitHistoryExtraction(new CommitHistoryExtractionOption());
    }

    @Test
    public void testExtractCommitHistory() throws NoHeadException, IOException, GitAPIException
    {
        String repositoryPath = "e:/gh_repos/dgl/";
        List<CommitHistory> listCommitHistory = chExtraction.extractCommitHistory( Paths.get(repositoryPath).toFile() );        
        assertNotEquals(0, listCommitHistory.size());
        
        CommitHistory.writeToJSON(listCommitHistory, "./test_ext_ch.json");
    }
}

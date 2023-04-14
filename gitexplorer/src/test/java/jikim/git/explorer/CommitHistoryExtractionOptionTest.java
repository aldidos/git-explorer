package jikim.git.explorer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class CommitHistoryExtractionOptionTest 
{

    @Test
    public void testRead() throws IOException
    {
        String jsonPath = "./input-form/commit-history-extraction-input.json";
        CommitHistoryExtractionOption option = CommitHistoryExtractionOption.read( jsonPath );

        assertEquals("./data/commit-history/", option.outputDirPath);
    }
    
}

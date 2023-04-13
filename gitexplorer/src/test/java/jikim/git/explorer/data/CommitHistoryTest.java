package jikim.git.explorer.data;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CommitHistoryTest 
{
    CommitHistory commitHistory;
    List<CommitHistory> listCommitHistory;

    @Before
    public void prepareTest()
    {
        CommitHistory ch = new CommitHistory("1111", "pa", "hello", null, null, null);
        listCommitHistory = new ArrayList<CommitHistory>( );
        listCommitHistory.add(ch);
    }    
    
    @Test
    public void testWriteReadCommitHistory()
    {
        String jsonPath = "./test_commit_history.json";        
        try {
            CommitHistory.writeToJSON( listCommitHistory, jsonPath );

            List<CommitHistory> readData = CommitHistory.readFromJSON( jsonPath );
            
            assertEquals( listCommitHistory.size(), readData.size());
            assertEquals( listCommitHistory.get(0).id, readData.get(0).id );

        } catch (IOException e) 
        {            
            e.printStackTrace();
        }
    }    
}

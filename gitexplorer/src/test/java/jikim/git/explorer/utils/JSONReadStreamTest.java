package jikim.git.explorer.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class JSONReadStreamTest 
{
    DummyData dummyData;    

    @Before
    public void prepareData()
    {
        dummyData = new DummyData(1, "name");
    }

    @Test
    public void testRead() throws IOException
    {
        String streamPath = "./test_write_stream.json";
        JSONReadStream readStream = new JSONReadStream( streamPath );

        dummyData = (DummyData)readStream.read(DummyData.class);
        readStream.close();

        assertEquals(1, dummyData.id);
    }    
    
}

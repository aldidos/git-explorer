package jikim.git.explorer.utils;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class JSONWriteStreamTest 
{   
    DummyData dummyData;    

    @Before
    public void prepareData()
    {
        dummyData = new DummyData(1, "name");
    }

    @Test
    public void testWrite() throws IOException
    {
        String streamPath = "./test_write_stream.json";
        JSONWriteStream writeStream = new JSONWriteStream( streamPath );

        writeStream.write(dummyData, DummyData.class);
        writeStream.write(dummyData, DummyData.class);
        writeStream.write(dummyData, DummyData.class);

        writeStream.close();
    }
    
    
}

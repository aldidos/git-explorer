package jikim.git.explorer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class AppInputOptionTest 
{
    @Test
    public void testRead() throws IOException
    {
        String jsonPath = "./input-form/app-input.json";
        AppInputOption appInputOption = AppInputOption.read(jsonPath);

        assertEquals("commit-history-extraction", appInputOption.runCommand);
    }
    
}

package jikim.git.explorer;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

public class App 
{
    public static void main(String[] args) throws IOException, NoHeadException, GitAPIException
    {
        AppInputOption appInputOption = AppInputOption.read(args[0]);

        AppController appController = new AppController( appInputOption );
        appController.run();      
    }
}

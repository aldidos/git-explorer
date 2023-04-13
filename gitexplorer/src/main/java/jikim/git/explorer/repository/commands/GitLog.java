package jikim.git.explorer.repository.commands;

import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

public class GitLog 
{
    public static Iterable<RevCommit> logAll(Repository repository) 
        throws NoHeadException, GitAPIException, IOException
    {
        Git git = new Git( repository ) ;
        Iterable<RevCommit> iter = git.log().all().call();
        git.close();
        return iter;
    }
    
}

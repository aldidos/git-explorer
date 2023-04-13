package jikim.git.explorer.repository.commands;

import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

public class GitBranch 
{
    public static List<Ref> listBranch(Repository repository) throws GitAPIException
    {
        try ( Git git = new Git( repository ) )
        {
            List<Ref> listBranches = git.branchList().call();
            return listBranches;
        }
    }
}

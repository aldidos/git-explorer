package jikim.git.explorer.repository;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class RepositoryFactory 
{
    public static Repository create(String repoPath) throws IOException
    {
        File gitDir = new File(repoPath);
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        builder.findGitDir( gitDir );
        return builder.build();
    }

    public static GitRepository createGitRepository(String repoPath) throws IOException
    {
        return new GitRepository( create(repoPath) );
    }
}

package jikim.git.explorer.repository;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.junit.Test;

public class RepositoryFactoryTest 
{
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithWrongPath() throws IOException
    {
        String repoPath = "e:/wrong-path/";
        Repository repository = RepositoryFactory.create(repoPath);
        assertNotNull(repository);
    }

    @Test
    public void testCreate() throws IOException
    {
        String repoPath = "e:/gh_repos/dgl/";
        Repository repository = RepositoryFactory.create(repoPath);
        assertNotNull(repository);        
    }
    
}

package jikim.git.explorer.repository;

import java.io.IOException;

import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class RevTreeFactory
{
    public static AbstractTreeIterator prepareTreeParser( Repository repository, RevCommit commit ) throws IOException
    {
        RevTree tree = ObjectParser.parseTree(repository, commit.getTree().getId());
        CanonicalTreeParser treeParser = new CanonicalTreeParser();
        try ( ObjectReader objectReader = repository.newObjectReader() )
        {
            treeParser.reset(objectReader, tree.getId());
        }        
        return treeParser;
    }

}

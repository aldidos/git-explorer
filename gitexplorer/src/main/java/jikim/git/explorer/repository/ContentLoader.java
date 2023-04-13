package jikim.git.explorer.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class ContentLoader 
{
    public static ObjectLoader loadFileObject(Repository repository, RevCommit commit, String filePath) 
        throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException
    {
        RevTree tree = commit.getTree();
        try (TreeWalk treeWalk = new TreeWalk(repository)) {
            treeWalk.addTree(tree);
            treeWalk.setRecursive(true);
            treeWalk.setFilter(PathFilter.create( filePath ));
            if (!treeWalk.next()) {
                throw new IllegalStateException( String.format("Did not find expected file '%s'", filePath) );
            }

            ObjectId objectId = treeWalk.getObjectId(0);
            ObjectLoader loader = repository.open(objectId);

            return loader;
        }
    }

    public static String loadFileContentText( Repository repository, RevCommit commit, String filePath ) 
        throws MissingObjectException, IncorrectObjectTypeException, CorruptObjectException, IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectLoader objectLoader = loadFileObject(repository, commit, filePath);
        objectLoader.copyTo(out);
        
        return out.toString();
    }

    public static String loadDiffText( Repository repository, DiffEntry diff ) 
        throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try( DiffFormatter formatter = new DiffFormatter(out) )
        {
            formatter.setRepository(repository);
            formatter.format(diff);
        }

        return out.toString();
    }
}

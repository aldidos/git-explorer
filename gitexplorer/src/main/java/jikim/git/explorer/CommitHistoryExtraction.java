package jikim.git.explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import jikim.git.explorer.data.CommitHistory;
import jikim.git.explorer.log.Log;
import jikim.git.explorer.repository.ObjectParser;
import jikim.git.explorer.repository.RepositoryFactory;
import jikim.git.explorer.repository.commands.GitDiff;
import jikim.git.explorer.repository.commands.GitLog;
import jikim.git.explorer.utils.JSONWriteStream;

public class CommitHistoryExtraction 
{
    CommitHistoryExtractionOption option;

    public CommitHistoryExtraction(CommitHistoryExtractionOption option)
    {
        this.option = option;
    }

    public void run() throws IOException, NoHeadException, GitAPIException
    {
        switch ( option.inputPathType )
        {
            case CommitHistoryExtractionOption.OPTION_INPUT_PATH_TYPE_DIR : 
                extractAllCommitHistory();
                break;

            case CommitHistoryExtractionOption.OPTION_INPUT_PATH_TYPE_TARGET :
                File repoDir = Paths.get( option.inputPath ).toFile();
                extractCommitHistory(repoDir);
                break;
        }        
    }

    public void extractAllCommitHistory() throws NoHeadException, IOException, GitAPIException
    {
        for ( File repoDir : Paths.get( option.inputPath ).toFile().listFiles() ) 
        {            
            extractCommitHistory(repoDir);
        }
    }

    public void extractCommitHistory(File repoDir) throws IOException, NoHeadException, GitAPIException
    {
        String jsonWritePath = option.outputDirPath + repoDir.getName() + ".json";
        JSONWriteStream writeStream = new JSONWriteStream( jsonWritePath );
        extractCommitHistory(repoDir, writeStream);            
        writeStream.close();
    }

    public void extractCommitHistory(File repoDir, JSONWriteStream writeStream) throws IOException, NoHeadException, GitAPIException
    {
        Log.log("from " + repoDir.toPath() );
        Repository repository = RepositoryFactory.create( repoDir.getAbsolutePath() );
        Iterable<RevCommit> iter = GitLog.logAll( repository );

        for ( RevCommit revCommit : iter ) 
        {
            RevCommit curCommit = ObjectParser.parseCommit( repository, revCommit.getId() );
            for( RevCommit pCommit : curCommit.getParents() ) 
            {
                RevCommit parentCommit = ObjectParser.parseCommit( repository, pCommit.getId() );
                List<DiffEntry> listDiffs = GitDiff.getDiffEntries(repository, curCommit, parentCommit);
                
                CommitHistory commitHistory = createCommitHistory(curCommit, parentCommit, listDiffs);
                writeStream.write(commitHistory, CommitHistory.class);
            }
        }
    }

    private CommitHistory createCommitHistory(RevCommit curCommit, RevCommit parentCommit,  List<DiffEntry> listDiffs) 
    {
        return new CommitHistory(curCommit.name(), parentCommit.name(), curCommit.getFullMessage(), curCommit.getAuthorIdent(), curCommit.getCommitterIdent(), listDiffs);
    }
    
}

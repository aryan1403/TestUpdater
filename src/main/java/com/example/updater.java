package com.example;

import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

public class updater extends botmaker {
    public void isUpdate(Update update) {
        String msg = update.getMessage().getText().toString();
        if (msg.equalsIgnoreCase("/update")) {
            System.out.println("Updating the bot\nPlease Wait!");
            runmsg(update.getMessage().getChatId().toString(), "Updating Bot Maker...");
            try {
                pull();
            } catch (GitAPIException | IOException e) {
                runmsg(update.getMessage().getChatId().toString(), "Cannot Update Bot!");
                e.printStackTrace();
            }
            runmsg(update.getMessage().getChatId().toString(),
                    "Bot Updated Successfully!!\nThanks to the Developer for such an update https://t.me/Hellion_OP");
        }
    }

    public void pull() throws IOException, GitAPIException {
        final File localPath;
        try (Repository repository = cloneRepository()) {
            localPath = repository.getWorkTree();

            System.out.println("Having repository: " + repository.getDirectory() + " with head: "
                    + repository.findRef(Constants.HEAD) + "/" + repository.resolve("HEAD") + "/"
                    + repository.resolve("refs/heads/master"));

            try (Git git = new Git(repository)) {
                PullResult call = git.pull().call();

                System.out.println("Pulled from the remote repository: " + call);
            }
        }

        // clean up here to not keep using more and more disk-space for these samples
        // FileUtils.deleteDirectory(localPath);
    }

    private static Repository cloneRepository() throws IOException, GitAPIException {
        String REMOTE_URL = "https://github.com/Hellboy-Aaryan/TestUpdater.git";
        // prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "", new File("E:/Telegram_Bot_JAVA/"));
        if (!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // then clone
        System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
        try (Git result = Git.cloneRepository().setURI(REMOTE_URL).setDirectory(localPath).call()) {
            // Note: the call() returns an opened repository already which needs to be
            // closed to avoid file handle leaks!
            return result.getRepository();
        }
    }

    public void clonerepository() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
        Git.cloneRepository().setURI("https://github.com/Hellboy-Aaryan/TestUpdater.git")
                .setDirectory(new File("E:/Telegram_Bot_JAVA/botmaker")).setCloneAllBranches(true).call();
    }
}
